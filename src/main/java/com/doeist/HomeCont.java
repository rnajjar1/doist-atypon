package com.doeist;

import com.doeist.Model.Employee.Employee;
import com.doeist.Model.Task.Task;
import com.doeist.Model.Task.TaskBuilder;
import com.doeist.Repository.EmployeeServices.EmployeeService;
import com.doeist.Repository.TaskServices.TaskService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SessionAttributes({"employee","list"})
@Controller
public class HomeCont {

    private final TaskService taskService=new TaskService();
    private final EmployeeService employeeService=new EmployeeService();

    @RequestMapping(value = "/home")
    public String home(Authentication authentication,@ModelAttribute("userf")Employee employee,
                       Model model ) {
        model.addAttribute("employee",employeeService.getByEmail(employee.getEmail()));
        List<Task> taskList=taskService.getAllMyTasks(employee.getEmail());
            model.addAttribute("list", taskList);
        return authentication==null ? "redirect:/login" :"home";
    }

    @RequestMapping(value = "/my-tasks", method = RequestMethod.GET)
    public String myTasks(Authentication authentication,@ModelAttribute("employee")Employee employee,
                          Model model){
        System.out.println("employee name"+employee.getEmail());
        List<Task> taskList=taskService.getAllMyTasks(employee.getEmail());
            model.addAttribute("list", taskList);
        System.out.println("taskList" + taskList.size());
        return authentication==null ? "redirect:/login" :"home";
    }

    @RequestMapping(value = "/all-tasks", method = RequestMethod.GET)
    public String allTasks(Authentication authentication,@ModelAttribute("employee")Employee employee,
                          Model model){
        List<Task> taskList=taskService.getAllTasks();
            model.addAttribute("list", taskList);
        return authentication==null ? "redirect:/login" :"home";
    }
    @RequestMapping(value = "/create-task", method = RequestMethod.GET)
    public String createGet(Authentication authentication ) {
        System.out.println("here we go ");
        return authentication==null ? "redirect:/login" :"createTask";
    }

    @RequestMapping(value = "/create-task", method = RequestMethod.POST)
    public String createTask(@RequestParam Map<String,String> allRequestParams,
                             @ModelAttribute("employee")Employee employee){
        System.out.println("employeeeeeee" +employee.getName());
        Task task= getTask(allRequestParams,employeeService.getByEmail(employee.getEmail()));
        taskService.createTask(task);
        return "successfullyCreated";
    }

    @RequestMapping(value = "/update-task", method = RequestMethod.GET)
    public String updateGet(Authentication authentication) {
        return authentication==null? "redirect:/login" :"updateTask";
    }
    @RequestMapping(value = "/update-task", method = RequestMethod.POST)
    public String updateTask(@RequestParam Map<String,String> allRequestParams, Model model){
        int id =Integer.parseInt(allRequestParams.get("id"));
        String description= allRequestParams.get("description");
        String note =allRequestParams.get("note");
        boolean status=false;
        if (allRequestParams.get("status").equals("finished")){
            status=true;
        }
        boolean status2=status;
        taskService.updateTask(id,description,status2,note);

        return "successfullyUpdated";
    }


    @RequestMapping(value = "/delete-task", method = RequestMethod.GET)
    public String deleteGet(Authentication authentication) {
        return authentication==null? "redirect:/login" :"deleteTask";
    }

    @RequestMapping(value = "/delete-task", method = RequestMethod.POST)
    public String deleteTask(@RequestParam Map<String,String> allRequestParams) {
        taskService.deleteTask(Integer.parseInt(allRequestParams.get("id")));
        return "successfullyDeleted";
    }


    public Task getTask(Map<String, String> map,Employee employee){
        Task t=new TaskBuilder().getTask();
         t.setDescription(map.get("description"));
         t.setNote(map.get("note"));
        t.setEmployee(employee);
         boolean status=false;
         if (map.get("status").equals("finished")){
             status=true;
         }
        t.setPriority(map.get("priority"));
         t.setStatus(status);

         return t;
    }



}
