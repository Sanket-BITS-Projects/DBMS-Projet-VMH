<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Portal</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/AdminPageStyle.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<body>

<!-- Sidebar -->

<!-- Page Content -->


    <div id="tl1">
        <div style="margin-left:20%">

            <div class="w3-container w3-teal">
                <h1>Welcome to Virtual Medical Home</h1>
                <h2>Nice to see you.</h2>
                <p>You can get all the data which you want on this page.</p>
            </div>

        </div>
    </div>

<div id="tr2">
    <div class="w3-container w3-teal">
        <h2>Profile of all doctors</h2>
        <script>

            var i, docData;

            $.ajax({
                method: "GET",
                url: "doctors",
                dataType: 'json',
                async: false,
                success: function (response) {
                    docData = response.result;
                }
            });

            document.write("<table id='AppointmentTable'" + "><tr><th>" + "ID" + "</th><th>" + "Doctor Name" + "</th> <th>E-Mail</th> <th>Phone Number</th> <th>Date of Birth</th>  </tr>");
            if (Object.keys(docData).length) {
                Object.keys(docData).forEach(function (i) {
                    if (docData[i].AppointmentStatus === "1") {
                        document.write("<tr><td>" + docData[i].DoctorName + "</td><td>" + docData[i].AppointmentDate + "</td><td>" + "Done" + "</td><td>" + docData[i].AmountPaid + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show</button>" + "</td><td>" + "<Button id='giveFB'>Provide</button>" + "</td></tr>");
                    } else {
                        document.write("<tr><td>" + docData[i].id + "</td><td>" + docData[i].name + "</td><td>" + docData[i].email + "</td><td>" + docData[i].phone + "</td><td>" + docData[i].dob + "</td></tr>");
                    }
                });
            } else {
                document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");
            }

            document.write("</table> </div>");

      </script>




    </div>
</div>

<div id="tl3">
    <div class="w3-container w3-teal">
        <h2>Profile of all patients</h2>
        <script>

            var i, patData;

            $.ajax({
                method: "GET",
                url: "PatientsNew",
                dataType: 'json',
                async: false,
                success: function (response) {
                    patData = response.result;
                }
            });

            document.write("<table id='AppointmentTable'" + "><tr><th>" + "ID" + "</th><th>" + "Patient Name" + "</th> <th>E-Mail</th> <th>Phone Number</th> <th>Date of Birth</th>  </tr>");
            if (Object.keys(patData).length) {
                Object.keys(patData).forEach(function (i) {
                    if (patData[i].AppointmentStatus === "1") {
                        document.write("<tr><td>" + patData[i].id + "</td><td>" + patData[i].name + "</td><td>" + "Done" + "</td><td>" + patData[i].email + "</td></tr>");
                    } else {
                        document.write("<tr><td>" + patData[i].id + "</td><td>" + patData[i].name + "</td><td>" + patData[i].email + "</td><td>" + patData[i].phone + "</td><td>" + patData[i].dob + "</td></tr>");
                    }
                });
            } else {
                document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");
            }

            document.write("</table> </div>");

        </script>

    </div>
</div>

<div id="tr4">
    <div class="w3-container w3-teal">
        <h2>List of all appointments</h2>

    </div>
</div>

<div id="tl5">
    <div class="w3-container w3-teal">
        <h2>All feedbacks</h2>

        <script>

            var i, fbkdata;

            $.ajax({
                method: "GET",
                url: "Feedbacklist",
                dataType: 'json',
                async: false,
                success: function (response) {
                    fbkData = response.result;
                }
            });

            document.write("<table id='AppointmentTable'" + "><tr><th>" + "Feedback ID" + "</th> <th>" + "Feedback" + "</th> <th>" + "Rating" + "</th> <th>Given by</th> <th>" + "Appointment ID" + "</th> <th>Date</th> <th>Doctor Name</th>  </tr>");
            if (Object.keys(fbkData).length) {
                Object.keys(fbkData).forEach(function (i) {
                    if (fbkData[i].AppointmentStatus === "1") {
                        document.write("<tr><td>" + fbkData[i].AppointmentId + "</td><td>" + fbkData[i].Date + "</td><td>" + "Done" + "</td><td>" + fbkData[i].AmountPaid + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show</button>" + "</td><td>" + "<Button id='giveFB'>Provide</button>" + "</td></tr>");
                    } else {
                        document.write("<tr> <td>" + fbkData[i].FeedbackId + "</td> <td>" + fbkData[i].Description + "</td> <td>" + fbkData[i].Rating + "</td>  <td>" + fbkData[i].PatientName + "</td> <td>" + fbkData[i].AppointmentId + "</td> <td>" + fbkData[i].Date + "</td><td>" + fbkData[i].DoctorName + "</td></tr>");
                    }
                });
            } else {
                document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");
            }
            document.write("</table> </div>");

        </script>
    </div>
</div>

<div id="tr6">
    <div class="w3-container w3-teal">
        <h2>Schedule of doctors</h2>
    </div>
</div>

<div id="tr7">
    <div class="w3-container w3-teal">
        <h2>Prescription List</h2>
        <script>

            var i, AptData;

            $.ajax({
                method: "GET",
                url: "AppointmentlistbyDate",
                dataType: 'json',
                async: false,
                success: function (response) {
                    AptData = response.result;
                }
            });

            document.write("<table id='AppointmentTable'" + "><tr><th>" + "ID" + "</th><th>" + "Doctor Name" + "</th> <th>E-Mail</th> <th>Phone Number</th> <th>Date of Birth</th>  </tr>");
            if (Object.keys(AptData).length) {
                Object.keys(AptData).forEach(function (i) {
                    if (AptData[i].AppointmentStatus === "1") {
                        document.write("<tr><td>" + AptData[i].DoctorName + "</td><td>" + AptData[i].AppointmentDate + "</td><td>" + "Done" + "</td><td>" + AptData[i].AmountPaid + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show</button>" + "</td><td>" + "<Button id='giveFB'>Provide</button>" + "</td></tr>");
                    } else {
                        document.write("<tr><td>" + AptData[i].id + "</td><td>" + AptData[i].name + "</td><td>" + AptData[i].email + "</td><td>" + AptData[i].phone + "</td><td>" + AptData[i].dob + "</td></tr>");
                    }
                });
            } else {
                document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");
            }

            document.write("</table> </div>");

        </script>
    </div>
</div>

</body>
</html>




<%--<div id="tl">All Patients</div>
<div id="tr">Top Right</div>
<div id="bl">Bottom Left</div>
<div id="br">Bottom Right</div>--%>
