
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Virtual Medical Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/loginstyle.css">
    <script src="${pageContext.request.contextPath}/resources/js/Doctorformvalidation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="login-wrap-doctor">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>

        <div class="login-form">

            <div class="sign-in-htm">
                <div class="group">
                    <label for="email" class="label">Email Address</label>
                    <input id="loginemail" type="text" class="input">
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="pass" type="password" class="input" data-type="password">
                    <small class="small_message">Error Message</small>
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Sign In" onclick="logindoctor()">
                </div>
            </div>

            <div class="sign-up-htm">
                <div class="group">
                    <label for="name" class="label">name</label>
                    <input id="name" type="text" class="input">
                    <small class="small_message">Error Message</small>
                </div>
                <div class="group">
                    <label for="pass" class="label">Email Address</label>
                    <input id="email" type="text" class="input">
                    <small class="small_message">Error Message</small>
                </div>
                <div class="group">
                    <label for="pass" class="label">Phone Number</label>
                    <input id="phone" type="text" class="input">
                    <small class="small_message">Error Message</small>
                </div>
                <div class="group">
                    <label for="pass" class="label">Date-of-Birth</label>
                    <input id="dob" type="date" class="input">
                    <small class="small_message">Error Message</small>
                </div>
                <div class="group">
                    <label for="pass" class="label">Address</label>
                    <input  type="text" class="input" id="Address">
                    <small class="small_message">Error Message</small>
                </div>
                <div class="group">
                    <label class="label">Specialization</label>
                    <select  id="specialization" class="input">
                            <option value=1 class = "option">Physician</option>
                            <option value=2 class = "option">Pediatrician</option>
                            <option value=3 class = "option">Gynecologist</option>
                            <option value=4 class = "option">Surgeon</option>
                            <option value=5 class = "option">Psychiatrist</option>
                            <option value=6 class = "option"> Cardiologist</option>
                            <option value=7 class = "option">Dermatologist</option>
                            <option value=8 class = "option">Endocrinologist</option>
                            <option value=9 class = "option">Gastroenterologist</option>
                            <option value=10 class = "option">Neurologist</option>
                            <option value=11 class = "option">Anesthesiologist</option>
                            <option value=12 class = "option">Orthopedist</option>
                            <option value=13 class = "option">Radiologist</option>\n
                    </select>
                    <small class="small_message">Error Message</small>
                </div>
                <div class="group">
                    <label for="pass" class="label">Fees</label>
                    <input id="fees" type="text" class="input">
                    <small class="small_message">Error Message</small>
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="password" type="password" class="input" data-type="password">
                    <small class="small_message">Error Message</small>
                </div>
                <div class="group">
                    <label for="pass" class="label">confirm Password</label>
                    <input id="confirmpassword" type="password" class="input" data-type="password">
                    <small class="small_message">Error Message</small>
                </div>

                <div class="group">
                    <input type="submit" class="button" value="Sign Up" onclick="checkDoctorInputs()">
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