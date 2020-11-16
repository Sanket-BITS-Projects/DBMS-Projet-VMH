<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Virtual Medical Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/loginstyle.css">
    <script src="${pageContext.request.contextPath}/resources/js/patientformvalidation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
</head>
<body>


<div class="login-wrap">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
        <div class="login-form">
            <div class="sign-in-htm">
                <div class="group">
                    <label for="email" class="label">Email Address</label>
                    <input id="loginemail" type="text" class="input" name="email">
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="pass" type="password" class="input" data-type="password" name="password">
                    <small class="small_message">Message</small>
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Sign In" onclick="loginuser()">
                </div>
            </div>
             <div class="sign-up-htm">
                <div class="group">
                    <label for="username" class="label" required>name</label>
                    <input type="text"  class="input" id="username">
                    <small class="small_message">Error Message</small>
                </div>
                 <div class="group">
                     <label for="pass" class="label">Email Address</label>
                     <input type="text" class="input" name="email" id="email">
                     <small class="small_message">Error Message</small>
                 </div>
                 <div class="group">
                     <label for="pass" class="label">Phone Number</label>
                     <input  type="text" class="input" name="phone" id="phone">
                     <small class="small_message">Error Message</small>
                 </div>
                 <div class="group">
                     <label for="pass" class="label">Date-of-Birth</label>
                     <input  type="date"  class="input" name="dob" id="dob">
                     <small class="small_message">Error Message</small>
                 </div>
                 <div class="group">
                     <label for="pass" class="label">Address</label>
                     <input  type="text" class="input" id="Address">
                     <small class="small_message">Error Message</small>
                 </div>
                  <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input  name="password" type="password" class="input" data-type="password" id="password">
                    <small class="small_message">Error Message</small>
                </div>
                <div class="group">
                    <label for="pass" class="label">confirm Password</label>
                    <input  type="password" class="input" data-type="password" id="confirmpassword">
                    <small class="small_message">Error Message</small>
                </div>
                <div class="group">
                    <input for="tab-1" id="submit_btn" type="submit" class="button" value="Sign Up" onclick="checkInputs()">
                </div>
                <div class="hr"></div>
                <div class="foot-lnk">
                    <label for="tab-1">Already Member?</label>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>