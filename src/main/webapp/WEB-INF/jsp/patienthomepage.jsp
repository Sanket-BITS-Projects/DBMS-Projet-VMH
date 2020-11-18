<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Portal</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/PatientHomepageStyle.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<body>

<!-- Sidebar -->
<Script>
    var patientDet;
    $.ajax({
        method: "GET",
        url: "Patientdetails",
        dataType: 'json',
        async: false,
        success: function (response) {
            patientDet = response.result;
            profileInfo(response.result);
        }
    });

    function profileInfo(data) {
        var dateformat = getFormattedDate(data.dob);
        setBalance("₹" + data.balance);
        document.write("<div class='w3-sidebar w3-light-grey w3-bar-block' style='width:20%'>  <button style=\"margin: 10;padding: 8px;\" onclick=\"onLogout()\">Logout</button>  <h3 style='margin-top: 0;' class='w3-bar-item'>Profile</h3>" +
            "    <div style='margin-left:7%;font-size: 14;'>" +
            data.pName + "<br>" +
            data.email + "<br>" +
            dateformat + "<br>" +
            "+91 " + data.phone + "<br>" +
            "    </div>" +
            "</div>");
    }

    function setBalance(bal) {
        this.bal = bal;
    }

    function getBalance() {
        return this.bal;
    }

    function getFormattedDate(odate) {
        var date = odate.toString();
        var year = date.substring(0, 4);
        var month = date.substring(5, 7);
        var day = parseInt(date.substring(8, 10))
        day = day + 1;
        day = day.toString();

        return day + "/" + month + "/" + year;

    }

    function onLogout() {
        $.ajax({
            method: "GET",
            url: "${pageContext.request.contextPath}/logout",
            success: function (result) {
                window.location.replace("${pageContext.request.contextPath}/index.html");
            },
            error: function (err) {
                console.log(err);
                alert("Something went wrong when logout!");
            }
        })
    }
</Script>

<!-- Page Content -->
<div style="margin-left:20%">

    <div class="w3-container w3-teal">
        <h1>Welcome to Virtual Medical Home</h1>
        <div style="text-align: right; margin-top: -5px">
            <script>
                document.write("<div class=\"tooltip right\">\n");
                document.write("<button class='AddAmountButton open-button'  onclick=\"openAmtForm()\"><b>Wallet Balance</b></button>" + " : " + getBalance());
                document.write("<span class='tooltiptext' style='left : -130%'>Add Amount</span></div>");
                document.write("<div class=\"form-popup\" id=\"amountForm\">\n");
                document.getElementById("amountForm").style.display = "none";
                document.write("  <form action=\"\" class=\"form-container\">\n" +
                    "    <button type=\"button\" class=\"closebtn cancel\" onclick=\"closeAmtForm()\">X  </button>\n" +
                    "\n" +
                    "    <label for=\"Amount\"><b>Amount</b></label>\n" +
                    "    <input type=\"text\" placeholder=\"Enter Amount\" name=\"Amount\" required>\n" +
                    "\n" +
                    "    <button type=\"submit\" class=\"btn\">Add</button>\n" +
                    "  </form>\n" +
                    "</div>");

                function openAmtForm() {
                    document.getElementById("amountForm").style.display = "block";
                }

                function closeAmtForm() {
                    document.getElementById("amountForm").style.display = "none";
                }
            </script>
        </div>
    </div>

    <div class="w3-container" id='appointmentModal'>
        <h2>We care for you.</h2>
        <p>Here is a list of all your previous appointments.</p>
        <p>Please click on the + button to create a new one.</p>
        <div style="text-align: right; margin-top: -5px">
            <div class="tooltip right">
                <button class="AddAppointmentButton" onclick="openappointmentForm()"><font size=6><b> + </b></font>
                </button>
                <span class="tooltiptext">Create new Appointment</span>
            </div>

            <script>
                document.write("<div class=\"form-popup\" style='top: 250px; right: 26px' id=\"appointmentForm\">\n");
                document.getElementById("appointmentForm").style.display = "none";
                document.write("  <div class=\"form-container\">\n" +
                    "    <button type=\"button\" class=\"closebtn cancel\" onclick=\"closeappointmentForm()\">X  </button>\n" +
                    "\n" +
                    "    <label for=\"spName\" style='float: left; margin-top: 30;'>Select Specialization :</label>\n" +
                    "<select name=\"spName\" id=\"spName\" style='margin: 5 30 20 10; padding: 10 30;'>\n" +
                    "    <option value=\"Physician\">Physician</option>\n" +
                    "    <option value=\"Pediatrician\">Pediatrician</option>\n" +
                    "    <option value=\"Gynecologist\">Gynecologist</option>\n" +
                    "    <option value=\"Surgeon\">Surgeon</option>\n" +
                    "    <option value=\"Psychiatrist\">Psychiatrist</option>\n" +
                    "    <option value=\"Cardiologist\">Cardiologist</option>\n" +
                    "    <option value=\"Dermatologist\">Dermatologist</option>\n" +
                    "    <option value=\"Endocrinologist\">Endocrinologist</option>\n" +
                    "    <option value=\"Gastroenterologist\">Gastroenterologist</option>\n" +
                    "    <option value=\"Neurologist\">Neurologist</option>\n" +
                    "    <option value=\"Anesthesiologist\">Anesthesiologist</option>\n" +
                    "    <option value=\"Orthopedist\">Orthopedist</option>\n" +
                    "    <option value=\"Radiologist\">Radiologist</option>\n" +
                    "  </select>" +
                    "\n" +
                    "    <button onclick='loadSearchPage()' class=\"btn\">Search</button>\n" +
                    "  </div>\n" +
                    "</div>");

                function loadSearchPage() {
                    var sp = document.getElementById("spName");
                    window.location.replace('SearchResults?spName=' + sp.options[sp.selectedIndex].value);
                }
            </script>
        </div>
    </div>
</div>
<div id="overlay" onclick="off()">
</div>

<script>

    var i, apntData;

    $.ajax({
        method: "GET",
        url: "PatientAppointments",
        dataType: 'json',
        async: false,
        success: function (response) {
            apntData = response.result;
        }
    });

    document.write("<table id='AppointmentTable'" + "><tr><th>" + "Doctor Name" + "</th><th>" + "Appointment Date" + "</th> <th>Status</th> <th>Amount Paid</th> <th>Prescription</th> <th>Feedback</th> </tr>");
    if (Object.keys(apntData).length) {
        Object.keys(apntData).forEach(function (i) {
            if (apntData[i].doctorAccept === "1") {
                document.write("<tr><td>" + apntData[i].doctor.personByDId.pName + "</td><td>" + apntData[i].aDateTime.substr(0,10) + "</td><td>" + "Done" + "</td><td>" + apntData[i].doctor.fees + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show</button>" + "</td><td>" + "<Button id=" + i + " onclick=Feedback(" + (apntData[i].aId) + ")>Provide</button>" + "</td></tr>");
            } else {
                document.write("<tr><td>" + apntData[i].doctor.personByDId.pName + "</td><td>" + apntData[i].aDateTime.substr(0,10) + "</td><td>" + "Pending" + "</td><td>" + "--" + "</td><td>" + "--" + "</td><td>" + "--" + "</td></tr>");
            }
        });
    } else {
        document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");
    }

    document.write("</table> </div>");

    function openappointmentForm() {
        document.getElementById("appointmentForm").style.display = "block";
    }

    function closeappointmentForm() {
        document.getElementById("appointmentForm").style.display = "none";
    }

    var appointmentForm = document.getElementById("appointmentModal")
    window.onclick = function (event) {
        if (event.target == appointmentForm) {
            document.getElementById("appointmentForm").style.display = "none";
            document.getElementById("amountForm").style.display = "none";
        }
    }

    function on() {
        document.getElementById("overlay").innerHTML = "    <div id=\"text\">\n" +
            "        <TABLE border=\"0\" style=\"color: white;\" cellpadding=\"5px\">\n" +
            "            <tr>\n" +
            "                <td>" + apntData[event.target.id].DoctorName + "</td>\n" +
            "                <td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Virtual Medical Home</td>\n" +
            "                <td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Appointment. No. " + apntData[event.target.id].AppointmentId + "</td>\n" +
            "                <td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td></td>\n" +
            "                <td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td></td>\n" +
            "                <td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Patient Name: " + patientDet[0].name + "</td><td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Date: " + apntData[event.target.id].AppointmentDate + "</td><td></td>\n" +
            "            </tr>\n" +
            "        </TABLE>\n" +
            "        <br>\n" +
            "        \n" +
            "<p style='margin: 0 0 0 9; font-size: 18; font-weight: bold;'>Prescription:</p><p color=white style='font-size: 15px;text-align: center;'>" +
            apntData[event.target.id].prescription + "</p>"
        "    </div>\n";
        document.getElementById("overlay").style.display = "block";
    }

    function off() {
        document.getElementById("overlay").style.display = "none";
    }

    document.write("<div class=\"form-popup\" style='display: block;\n" +
        "    background: white;\n" +
        "    height: 400px;\n" +
        "    width: 300px;right: 25%;\n" +
        "    top: 20%;' id=\"Feedback\"></div>\n");

    document.getElementById("Feedback").style.display = "none";

    function Feedback(id) {
        document.getElementById("Feedback").style.display = "block";
        document.getElementById("Feedback").innerHTML = "<button type=button style='font-weight: bold;padding: 5 10;   margin: 10 240;\n" +
            "    background: red;\n" +
            "    color: white;' onclick=closeFeedback()>X</button>" +
            "            <h1 style='margin-left: 20;\n" +
            "    margin-top: 0;'> Feedback</h1>\n" +
            "\n" +
            "            <label for=\"Rating\" style='margin: 10;'><b>Rating (between 1 and 5)</b></label>\n" +
            " <input  style='margin: 10;' type=\"range\" id=\"vol\" name=\"Rating\" min=\"1\" max=\"5\"><br><br>" +
            "\n" +
            "            <label  style='margin: 10;' for=\"Review\"><b>Review</b></label>\n" +
            "            <textarea style='margin: 10;' id=\"feedbackText\" placeholder=\"Enter Review\" name=\"Review\" rows='6' cols=30 required></textarea>\n" +
            "    <input type=\"hidden\" value=\"" + id + "\" name=\"apntId\" id=\"apntId\"/> " +
            "\n" +
            "            <button style='margin-top: 10;\n" +
            "    width: 90%;\n" +
            "    margin-left: 15;\n" +
            "    height: 35;\n" +
            "    color: white;\n" +
            "    font-weight: bold;\n" +
            "    background: #209a20;' onclick='SubmitFB()'>Submit</button>\n"
        ;
    }

    function SubmitFB() {
        let rating = document.getElementById("vol").value;
        let desc = document.getElementById("feedbackText").value;
        let apntId = document.getElementById("apntId").value;
        $.ajax({
            url: "${pageContext.request.contextPath}/giveFeedback",
            method: "POST",
            data: {
                "appointmentId": apntId,
                "rating": rating,
                "description": desc
            },
            success: function (data) {
                if (data.status === "OK") {
                    alert("Feedback Submitted.");
                }
            },
            error: function (err) {
                alert("Something went wrong!");
            }
        })

    }

    function closeFeedback() {
        document.getElementById("Feedback").style.display = "none";
    }
</script>

</body>
</html>
