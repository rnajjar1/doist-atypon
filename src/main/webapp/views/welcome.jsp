<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <link rel="stylesheet" href="/resources/css/style.css">
</head>

<div class="login-box">
    <div class="login-wrap">
        <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign
            In</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
            <div class="login-form">
                <%--@elvariable id="error" type="com.doeist.LoginCont"--%>
                <form:form action="/login"  modelAttribute="error">
                    <div class="sign-in-htm">
                        <div class="group">
                            <label for="user" class="label">Email</label>
                            <input id="email" name="email" type="text" class="input">
                        </div>
                        <div class="group">
                            <label for="pass" class="label">Password</label>
                            <input id="password" name="password" type="password" class="input" data-type="password">
                        </div>
                        <div class="remember">
                            <input type="checkbox" id="remeber" name="remember-me">
                            <label for="remeber">
                                Remember me
                            </label>
                        </div>
                        <div class="group">
                            <input type="submit" class="button" value="Sign In">
                        </div>
                        <div class="hr"></div>
                        <div class="foot-lnk">
                            <a href="#forgot">Forgot Password?</a>
                        </div>
                    </div>
                </form:form>

                <div class="sign-up-htm">
                    <%--@elvariable id="userf" type="com.doeist.Model.Employee.Employee"--%>
                    <form:form action="/register" method="post" modelAttribute="userf">

                    <div class="group">
                                <label for="user" class="label" >Username</label>
                                <input id="user"  name="name" type="text" class="input" >
                                <form:errors path="name" cssClass="error" />
                    </div>
                        <div class="group">
                            <label for="pass" class="label">Password</label>
                            <input id="pass" name="password" type="password" class="input" data-type="password">
                            <form:errors path="password" cssClass="error" />
                        </div>
                        <div class="group">
                            <label for="pass" class="label">Confirm Password</label>
                            <input id="pass" name="matchingPassword" type="password" class="input" data-type="password">
                            <form:errors path="matchingPassword" cssClass="error" />
                        </div>
                        <div class="group">
                            <label for="pass" class="label">Email Address</label>
                            <input id="pass" name="email" type="text" class="input">
                            <form:errors path="email" cssClass="error" />
                        </div>
                        <div class="group">
                            <input type="submit" class="button" value="Sign Up">
                        </div>

                        <div class="hr"></div>
                        <div class="foot-lnk">
                            <label href="/login"  for="tab-1">Have an account?</label>
                        </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>