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
            <h2 class="Welcome" align="left" color="white">
                Welcome ${employee.getName()}
            </h2>
        </ul>
    </div>
    <div class="menu2">
        <ul class="navigation">

        </ul>
    </div>
    <b href="<c:url value="/logout" />">Logout</b>
    <div class="wrapper wrapper--w900">
        <div class="card card-6">
            <div class="card-body">
                <div class="row">
                    <div class="container">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Description</th>
                                <th>Note</th>
                                <th>Status</th>
                                <th>Priority</th>
                                <th>Date</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="task" items="${list}">
                                <tr>
                                    <td>
                                        <c:out value="${task.getId()}"/>
                                    </td>
                                    <td>
                                        <c:out value="${task.getDescription()}"/>
                                    </td>
                                    <td>
                                        <c:out value="${task.getNote()}"/>
                                    </td>
                                    <td>
                                        <c:out value="${task.isStatus()}"/>
                                    </td>
                                    <td>
                                        <c:out value="${task.getPriority()}"/>
                                    </td>
                                    <td>
                                        <c:out value="${task.getDate()}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>

</html>
