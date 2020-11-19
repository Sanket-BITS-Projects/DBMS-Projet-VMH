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
    var DoctorDet;
    $.ajax({
        method: "GET",
        url: "Doctordetails",
        dataType: 'json',
        async: false,
        success: function (response) {
            DoctorDet = response.result;
            profileInfo(response.result);
        }
    });

    function profileInfo(data) {
        var dateformat = getFormattedDate(data.personByDId.dob);
        setBalance("₹" + data.personByDId.balance);
        document.write("<div class='w3-sidebar w3-light-grey w3-bar-block' style='width:20%'>  <button style=\"margin: 10;padding: 8px;\" onclick=\"onLogout()\">Logout</button>  <h3 class='w3-bar-item'>Profile</h3>" +
            "    <div style='margin-left:7%;font-size: 14;'>" +
            data.personByDId.pName + "<br>" +
            data.personByDId.email + "<br>" +
            data.specializations[0].speciality + "<br>" +
            "fees: " + "₹" + data.fees + "<br>" +
            dateformat + "<br>" +
            "+91 " + data.personByDId.phone + "<br>" +
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
        var day = parseInt(date.substring(8, 10));
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
        <h1>Welcome to LifePlus Cares </h1>
        <div style="text-align: right; margin-top: -5px">
            <script>
                document.write("<div class=\"tooltip right\">\n");
                document.write("<button class='AddAmountButton open-button'  onclick=\"openAmtForm()\"><b>Wallet Balance</b></button>" + " : " + getBalance());
                document.write("<span class='tooltiptext' style='left : -130%'>Withdraw Amount</span></div>");
                document.write("<div class=\"form-popup\" id=\"amountForm\">\n");
                document.getElementById("amountForm").style.display = "none";
                document.write("  <form action=\"\" class=\"form-container\">\n" +
                    "    <button type=\"button\" class=\"closebtn cancel\" onclick=\"closeAmtForm()\">X  </button>\n" +
                    "\n" +
                    "    <label for=\"Amount\"><b>Amount</b></label>\n" +
                    "    <input type=\"text\" placeholder=\"Enter Amount\" name=\"Amount\" required>\n" +
                    "\n" +
                    "    <button type=\"submit\" class=\"btn\">Withdraw</button>\n" +
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
        <h2>Caring for everyone is our main moto.</h2>
        <p>Here is a list of all your previous patients who visited you .</p>
        <p>Please click on the Pending Appointments button to see your pending appointments.</p>
        <div style="text-align: right; margin-top: -5px">
            <div>
                <button class="btn" onclick="openpendingappointment()" style="cursor: pointer;
    margin: 10;
    padding: 8;
    background: #2a4e1d;
    color: white;
    font-weight: bold;">Pending Appointments
                </button>
            </div>
        </div>
    </div>

</div>
<div id="overlay" onclick="off()">
</div>

<script>

    var i, apntData;

    $.ajax({
        method: "GET",
        url: "doctorAppointmentHistory",
        dataType: 'json',
        async: false,
        success: function (response) {
            apntData = response.result;
        }
    });

    document.write("<table id='AppointmentTable'" + "><tr><th>" + "Patient Name" + "</th><th>" + "Appointment Date" + "</th> <th>Fees Collected</th> <th>Illness</th> <th>Prescription</th></tr>");
    if (Object.keys(apntData).length) {
        Object.keys(apntData).forEach(function (i) {
            document.write("<tr><td>" + apntData[i].patient.name + "</td><td>" + appointmentdate(apntData[i].appointment.time) + "</td><td>" + apntData[0].commission.fees + "</td><td>" + "<Button id=" + i + " onclick=\"onillness()\">Show illness</button>" + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show prescription</button>" + "</td></tr>");
        });
    } else {
        document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");
    }

    document.write("</table> </div>");


    var appointmentForm = document.getElementById("appointmentModal");
    window.onclick = function (event) {
        if (event.target == appointmentForm) {
            document.getElementById("appointmentForm").style.display = "none";
            document.getElementById("amountForm").style.display = "none";
        }
    };

    function appointmentdate(odate) {

        var date = odate.toString();
        var year = date.substring(0, 4);
        var month = date.substring(5, 7);
        var day = parseInt(date.substring(8, 10));
        day = day + 1;
        day = day.toString();
        return day + "/" + month + "/" + year;

    }


    function onillness() {
        document.getElementById("overlay").innerHTML = "    <div id=\"text\">\n" +
            "        <TABLE border=\"0\" style=\"color: white;\" cellpadding=\"5px\">\n" +
            "            <tr>\n" +
            "                <td>" + apntData[event.target.id].patient.name + "</td>\n" +
            "                <td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Virtual Medical Home</td>\n" +
            "                <td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Appointment. No. " + apntData[event.target.id].appointment.id + "</td>\n" +
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
            "                <td>Doctor Name: " + apntData[event.target.id].doctor.name + "</td><td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Date: " + appointmentdate(apntData[event.target.id].appointment.time) + "</td><td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Illness Reason: " + apntData[event.target.id].illness.title + "</td><td></td>\n" +
            "            </tr>\n" +
            "        </TABLE>\n" +
            "        <br>\n" +
            "        \n" +
            "<p style='margin: 0 0 0 9; font-size: 18; font-weight: bold;'>Illness Description:</p><p color=white style='font-size: 15px;text-align: center;'>" +
            apntData[event.target.id].illness.description + "</p>"
        "    </div>\n";
        document.getElementById("overlay").style.display = "block";
    }


    function on() {
        document.getElementById("overlay").innerHTML = "    <div id=\"text\">\n" +
            "        <TABLE border=\"0\" style=\"color: white;\" cellpadding=\"5px\">\n" +
            "            <tr>\n" +
            "                <td>" + apntData[event.target.id].doctor.name + "</td>\n" +
            "                <td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Virtual Medical Home</td>\n" +
            "                <td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Appointment. No. " + apntData[event.target.id].appointment.id + "</td>\n" +
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
            "                <td>Patient Name: " + apntData[event.target.id].patient.name + "</td><td></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Date: " + appointmentdate(apntData[event.target.id].appointment.time) + "</td><td></td>\n" +
            "            </tr>\n" +
            "        </TABLE>\n" +
            "        <br>\n" +
            "        \n" +
            "<p style='margin: 0 0 0 9; font-size: 18; font-weight: bold;'>Prescription:</p><p color=white style='font-size: 15px;text-align: center;'>" +
            apntData[event.target.id].prescription.description + "</p>"
        "    </div>\n";
        document.getElementById("overlay").style.display = "block";
    }

    function off() {
        document.getElementById("overlay").style.display = "none";
    }

    function openpendingappointment() {
        window.location.replace("DoctorPendingApnt");
    }
</script>

</body>
</html>
