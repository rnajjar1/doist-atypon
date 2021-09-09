package com.doeist;

import com.doeist.Model.Employee.Employee;
import com.doeist.Model.Employee.EmployeeBuilder;
import com.doeist.Repository.EmployeeServices.EmployeeService;
import com.doeist.SignUp.UserAlreadyExistException;
import com.doeist.SignUp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@ControllerAdvice
@SessionAttributes({"userf", "error"})
@Controller
public class LoginCont {

    @Autowired
    private UserService userService;

    private EmployeeService employeeService = new EmployeeService();

    private Employee employee = new EmployeeBuilder().getEmployee();


    @RequestMapping(value = "/menu")
    public String login3Post(ModelMap model, @RequestParam("email") String email,
                             @RequestParam("password") String password
            , RedirectAttributes redirectAttributes) {
        System.out.println("why this ");
        Employee employee = new EmployeeBuilder().setEmail(email).setPassword(password).getEmployee();
        model.addAttribute("userf", employee);
        redirectAttributes.addFlashAttribute("userf", employee);

        return "redirect:/home";
    }


    @RequestMapping(value = "/fail")
    public String failLogin(ModelMap model) {
        model.addAttribute("error","Email or Password is incorrect" );

        return "welcome";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(Model model) {
        System.out.println("ehlfkdj;flkjlkj;lkj");
            model.addAttribute("userf", employee);
        return "welcome";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("userf") @Valid Employee userf,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult have error");
            model.addAttribute("userf", userf);
            return "welcome";
        }
        try {
            employee = new EmployeeBuilder().setEmail(userf.getEmail())
                    .setPassword(userf.getPassword()).setName(userf.getName())
                    .setMatchingPassword(userf.getMatchingPassword()).getEmployee();
            userService.register(userf);

        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("email", "userf.email", "An account already exists for this email.");
            model.addAttribute("userf", userf);
            return "welcome";
        }
        return "redirect:/login";
    }

}
