

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Virtual Medical Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Adminstyle.css">
    <script src="${pageContext.request.contextPath}/resources/js/patientformvalidation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
</head>
<body>

<div class="login-wrap">
    <div class="login-html">
        <label  class="sign-in">Sign In</label>
        <div class="login-form">
            <div class="sign-in-htm">
                <div class="group">
                    <label for="email" class="label">Email address</label>
                    <input id="email" type="text" class="input">
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="pass" type="password" class="input" data-type="password">
                    <small class="small_message">Error Message</small>
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Sign In" onclick="loginadmin()">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>