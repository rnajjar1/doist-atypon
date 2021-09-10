<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="/resources/css/home.css">


<head>

    <title>Doist</title>

    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i"
          rel="stylesheet">

    <!-- Main CSS-->
</head>

<body>

<div>
    <div class="menu">
        <ul class="bar">
            <li><a class="active" href="/delete-task">Delete Task</a></li>
            <li><a href="/update-task">Update Task</a></li>
            <li><a href="/create-task">Create Task</a></li>
            <li><a href="/all-tasks">All Tasks</a></li>
            <li><a href="/my-tasks">My Tasks</a></li>
            <h2 class="Welcome" align="left">
                Welcome ${employee.getName()}
            </h2>
        </ul>
    </div>
    <div class="menu2">
        <ul class="navigation">
            <li><b href="#home">Profile</b></li>
            <li><b href="#news">Setting</b></li>
            <li><b href="#contact">Contact</b></li>
            <li><b href="/login">Log out</b></li>

        </ul>
    </div>
    <div class="wrapper wrapper--w900">
        <div class="card card-6">
            <form:form action="/create-task" method="post">
                <div class="card-body">
                    <div class="form-row">
                        <div class="name">Description</div>
                        <div class="value">
                            <div class="input-group">
                                <textarea class="textarea--style-6" name="description"
                                          placeholder="Describe your task . . ."></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="name">Note</div>
                        <div class="value">
                            <input class="input--style-6" type="text" name="note">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="name">Status</div>
                        <div class="container">
                            <label>Finished</label>
                            <input type="radio" name="status" value="finished">
                        </div>
                        <div class="container">
                            <label>Not Finished</label>
                            <input type="radio" name="status" value="notFinished">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="name">Priority</div>
                        <div class="container2">
                            <label>High</label>
                            <input type="radio" name="priority" value="high">
                        </div>
                        <div class="container2">
                            <label>Medium</label>
                            <input type="radio" name="priority" value="medium">
                        </div>
                        <div class="container2">
                            <label>Low</label>
                            <input type="radio" name="priority" value="low">
                        </div>
                    </div>

                </div>
                <div class="card-footer">
                    <button class="btn btn--radius-2 btn--blue-2" type="submit">Save Task</button>
                </div>
            </form:form>

        </div>
    </div>

</div>


</body>

</html>
