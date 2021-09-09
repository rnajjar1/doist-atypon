<%--
  Created by IntelliJ IDEA.
  User: H4C-M
  Date: ٢٨/٠٨/٢٠٢١
  Time: ٠٥:٣٨ م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/resources/css/menu.css">

<html>
<head>
    <title>Home</title>
</head>
<body>
<div class="menu">
    <ul class="bar">
        <li><a class="active" href="#home">Delete Task</a></li>
        <li><a href="#news">Update Task</a></li>
        <li><a href="#contact">Create Task</a></li>
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
    </ul>
</div>


</body>
</html>
