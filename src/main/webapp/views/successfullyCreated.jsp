<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="/resources/css/home.css">


<head>

    <title>Doist</title>

    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i"
          rel="stylesheet">

</head>

<body>

<div>
    <div class="menu">
        <ul class="bar">
            <li><a class="active" href="/delete-task">Delete Task</a></li>
            <li><a href="/update-task">Update Task</a></li>
            <li><a href="/create-task">Create Task</a></li>
            <li><a href="#about">All Tasks</a></li>
            <li><a href="#about">My Tasks</a></li>
            <h2 class="Welcome" align="left">
                Welcome ${userf.getName()}
            </h2>
        </ul>
    </div>
    <div class="menu2">
        <ul class="navigation">
            <li><b href="#home">Profile</b></li>
            <li><b href="#news">Setting</b></li>
            <li><b href="#contact">Contact</b></li>
            <li><b href="#contact">Log out</b></li>

        </ul>
    </div>
    <div class="wrapper wrapper--w900">
        <div class="card card-6">
            <div class="card-body">
                Task is successfully created :)
            </div>
            <div class="card-footer">
                <form:form action="/create-task" method="get">
                    <button class="btn btn--radius-2 btn--blue-2" type="submit">Ok</button>
                </form:form>
            </div>
        </div>
    </div>

</div>


</body>

</html>
