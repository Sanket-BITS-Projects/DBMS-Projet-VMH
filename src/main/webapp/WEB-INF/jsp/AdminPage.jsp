<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <!-- Font Awesome -->--%>
<%--    <link href="https://use.fontawesome.com/releases/v5.11.2/css/all.css" rel="stylesheet">--%>
<%--    <!-- Google Fonts Roboto -->--%>
<%--    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet">--%>
<%--    <!-- Bootstrap core CSS -->--%>
<%--    <link href="${pageContext.request.contextPath}/resources/css/css/bootstrap.min.css" rel="stylesheet">--%>
<%--    <!-- Material Design Bootstrap -->--%>
<%--    <link href="${pageContext.request.contextPath}/resources/css/css/mdb.min.css" rel="stylesheet">--%>
<%--    <!-- Custom styles -->--%>
<%--    <link href="${pageContext.request.contextPath}/resources/css/css/style.css" rel="stylesheet">--%>
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
                <h2>Administrator Page</h2>
<%--                <p>You can get all the data which you want on this page.</p>--%>
            </div>
<%--            <div>--%>
<%--            <button class="button" type="button"onclick="show(1)">Doctors List</button>--%>
<%--            <button class="button" type="button"onclick="show(2)">Patients List</button>--%>
<%--            <button class="button" type="button"onclick="show(3)">Appointments List</button>--%>
<%--            <button class="button" type="button"onclick="show(4)">Feedbacks List</button>--%>
<%--            <button class="button" type="button" onclick="show(5)">Prescription List</button>--%>
<%--            <button class="button" type="button" onclick="show(6)">Schedule List</button>--%>
<%--            </div>--%>

        </div>
    </div>

<script>
    function openPage(pageName, elmnt, color) {
        // Hide all elements with class="tabcontent" by default */
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }

        // Remove the background color of all tablinks/buttons
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].style.backgroundColor = "";
        }

        // Show the specific tab content
        document.getElementById(pageName).style.display = "block";

        // Add the specific color to the button used to open the tab content
        elmnt.style.backgroundColor = color;
    }

    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();
</script>

<!-- Tab links -->
<button class="tablink" onclick="openPage('Home', this, 'seagreen')">Home</button>
<button class="tablink" onclick="openPage('News', this, 'green')" id="defaultOpen">News</button>
<button class="tablink" onclick="openPage('Contact', this, 'blue')">Contact</button>
<button class="tablink" onclick="openPage('About', this, 'orange')">About</button>

<div id="Home" class="tabcontent">
    <h3>Home</h3>
    <h2 class="head1">Profile of all doctors</h2>
    <script>

        var i, docData;

        $.ajax({
            method: "GET",
            url: "/DoctorAppointmentsForAdmin",
            dataType: 'json',
            async: false,
            success: function (response) {
                docData = response.result;
            }
        });

        document.write("<table id='AppointmentTable'" + "><tr style=\"text-align:right\"><th>" + "ID" + "</th><th>" + "Doctor Name" + "</th> <th>E-Mail</th> <th>Phone Number</th> <th>Date of Birth</th>  </tr>");
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

<div id="News" class="tabcontent">
    <h3>News</h3>
    <p>Some news this fine day!</p>
</div>

<div id="Contact" class="tabcontent">
    <h3>Contact</h3>
    <p>Get in touch, or swing by for a cup of coffee.</p>
</div>

<div id="About" class="tabcontent">
    <h3>About</h3>
    <p>Who we are and what we do.</p>
</div>
<!-- Classic tabs -->
<%--<div class="classic-tabs">--%>

<%--    <ul class="nav tabs-cyan" id="myClassicTab" role="tablist">--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link  waves-light active show" id="profile-tab-classic" data-toggle="tab" href="#profile-classic"--%>
<%--               role="tab" aria-controls="profile-classic" aria-selected="true">Profile</a>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link waves-light" id="follow-tab-classic" data-toggle="tab" href="#follow-classic" role="tab"--%>
<%--               aria-controls="follow-classic" aria-selected="false">Follow</a>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link waves-light" id="contact-tab-classic" data-toggle="tab" href="#contact-classic" role="tab"--%>
<%--               aria-controls="contact-classic" aria-selected="false">Contact</a>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link waves-light" id="awesome-tab-classic" data-toggle="tab" href="#awesome-classic" role="tab"--%>
<%--               aria-controls="awesome-classic" aria-selected="false">Be awesome</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
<%--    <div class="tab-content border-right border-bottom border-left rounded-bottom" id="myClassicTabContent">--%>
<%--        <div class="tab-pane fade active show" id="profile-classic" role="tabpanel" aria-labelledby="profile-tab-classic">--%>
<%--            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium,--%>
<%--                totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta--%>
<%--                sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia--%>
<%--                consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui--%>
<%--                dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora--%>
<%--                incidunt ut labore et dolore magnam aliquam quaerat voluptatem.</p>--%>
<%--        </div>--%>
<%--        <div class="tab-pane fade" id="follow-classic" role="tabpanel" aria-labelledby="follow-tab-classic">--%>
<%--            <p>Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut--%>
<%--                aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse--%>
<%--                quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?</p>--%>
<%--        </div>--%>
<%--        <div class="tab-pane fade" id="contact-classic" role="tabpanel" aria-labelledby="contact-tab-classic">--%>
<%--            <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum--%>
<%--                deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non--%>
<%--                provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.--%>
<%--                Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est--%>
<%--                eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas--%>
<%--                assumenda est, omnis dolor repellendus. </p>--%>
<%--        </div>--%>
<%--        <div class="tab-pane fade" id="awesome-classic" role="tabpanel" aria-labelledby="awesome-tab-classic">--%>
<%--            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et--%>
<%--                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip--%>
<%--                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore--%>
<%--                eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia--%>
<%--                deserunt mollit anim id est laborum.</p>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--</div>--%>
<!-- Classic tabs -->
<%--<script>--%>
<%--    var nr;--%>
<%--function show(nr) {--%>
<%--document.getElementById("table1");--%>
<%--document.getElementById("table2");--%>
<%--document.getElementById("table3");--%>
<%--document.getElementById("table4");--%>
<%--document.getElementById("table5");--%>
<%--}--%>
<%--</script>--%>

<%--function myFunction() {--%>
<%--<div id="tr2">--%>
<%--    <div class="w3-container w3-teal">--%>
<%--        <h2>Profile of all doctors</h2>--%>
<%--        <script>--%>
<%--             function myFunction() {--%>
<%--                 //var i, docData;--%>

<%--                 // $.ajax({--%>
<%--                 //     method: "GET",--%>
<%--                 //     url: "doctors",--%>
<%--                 //     dataType: 'json',--%>
<%--                 //     async: false,--%>
<%--                 //     success: function (response) {--%>
<%--                 //         docData = response.result;--%>
<%--                 //     }--%>
<%--                 // });--%>

<%--                 document.write("<table id='AppointmentTable'" + "><tr><th>" + "ID" + "</th><th>" + "Doctor Name" + "</th> <th>E-Mail</th> <th>Phone Number</th> <th>Date of Birth</th>  </tr>");--%>
<%--                 // if (Object.keys(docData).length) {--%>
<%--                 //     Object.keys(docData).forEach(function (i) {--%>
<%--                 //         if (docData[i].AppointmentStatus === "1") {--%>
<%--                 //             document.write("<tr><td>" + docData[i].DoctorName + "</td><td>" + docData[i].AppointmentDate + "</td><td>" + "Done" + "</td><td>" + docData[i].AmountPaid + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show</button>" + "</td><td>" + "<Button id='giveFB'>Provide</button>" + "</td></tr>");--%>
<%--                 //         } else {--%>
<%--                 //             document.write("<tr><td>" + docData[i].id + "</td><td>" + docData[i].name + "</td><td>" + docData[i].email + "</td><td>" + docData[i].phone + "</td><td>" + docData[i].dob + "</td></tr>");--%>
<%--                 //         }--%>
<%--                 //     });--%>
<%--                 // } else {--%>
<%--                 //     document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");--%>
<%--                 // }--%>
<%--                 //--%>
<%--                 // document.write("</table> </div>");--%>
<%--             }--%>
<%--      </script>--%>




<%--    </div>--%>
<%--</div>--%>

<%--&lt;%&ndash;<div id="tl3">&ndash;%&gt;--%>
<%--    <div class="w3-container w3-teal">--%>
<%--        <h2>Profile of all patients</h2>--%>
<%--        <script>--%>

<%--            var i, patData;--%>

<%--            // $.ajax({--%>
<%--            //     method: "GET",--%>
<%--            //     url: "PatientsNew",--%>
<%--            //     dataType: 'json',--%>
<%--            //     async: false,--%>
<%--            //     success: function (response) {--%>
<%--            //         patData = response.result;--%>
<%--            //     }--%>
<%--            // });--%>

<%--            //document.write("<table id='AppointmentTable'" + "><tr><th>" + "ID" + "</th><th>" + "Patient Name" + "</th> <th>E-Mail</th> <th>Phone Number</th> <th>Date of Birth</th>  </tr>");--%>
<%--            // if (Object.keys(patData).length) {--%>
<%--            //     Object.keys(patData).forEach(function (i) {--%>
<%--            //         if (patData[i].AppointmentStatus === "1") {--%>
<%--            //             document.write("<tr><td>" + patData[i].id + "</td><td>" + patData[i].name + "</td><td>" + "Done" + "</td><td>" + patData[i].email + "</td></tr>");--%>
<%--            //         } else {--%>
<%--            //             document.write("<tr><td>" + patData[i].id + "</td><td>" + patData[i].name + "</td><td>" + patData[i].email + "</td><td>" + patData[i].phone + "</td><td>" + patData[i].dob + "</td></tr>");--%>
<%--            //         }--%>
<%--            //     });--%>
<%--            // } else {--%>
<%--            //     document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");--%>
<%--            // }--%>
<%--            //--%>
<%--            // document.write("</table> </div>");--%>

<%--        </script>--%>

<%--    </div>--%>
<%--</div>--%>

<%--<div id="tr4">--%>
<%--    <div class="w3-container w3-teal">--%>
<%--        <h2>List of all appointments</h2>--%>

<%--    </div>--%>
<%--</div>--%>

<%--<div id="tl5">--%>
<%--    <div class="w3-container w3-teal">--%>
<%--        <h2>All feedbacks</h2>--%>

<%--        <script>--%>

<%--            var i, fbkdata;--%>

<%--            $.ajax({--%>
<%--                method: "GET",--%>
<%--                url: "Feedbacklist",--%>
<%--                dataType: 'json',--%>
<%--                async: false,--%>
<%--                success: function (response) {--%>
<%--                    fbkData = response.result;--%>
<%--                }--%>
<%--            });--%>

<%--            //document.write("<table id='AppointmentTable'" + "><tr><th>" + "Feedback ID" + "</th> <th>" + "Feedback" + "</th> <th>" + "Rating" + "</th> <th>Given by</th> <th>" + "Appointment ID" + "</th> <th>Date</th> <th>Doctor Name</th>  </tr>");--%>
<%--            // if (Object.keys(fbkData).length) {--%>
<%--            //     Object.keys(fbkData).forEach(function (i) {--%>
<%--            //         if (fbkData[i].AppointmentStatus === "1") {--%>
<%--            //             document.write("<tr><td>" + fbkData[i].AppointmentId + "</td><td>" + fbkData[i].Date + "</td><td>" + "Done" + "</td><td>" + fbkData[i].AmountPaid + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show</button>" + "</td><td>" + "<Button id='giveFB'>Provide</button>" + "</td></tr>");--%>
<%--            //         } else {--%>
<%--            //             document.write("<tr> <td>" + fbkData[i].FeedbackId + "</td> <td>" + fbkData[i].Description + "</td> <td>" + fbkData[i].Rating + "</td>  <td>" + fbkData[i].PatientName + "</td> <td>" + fbkData[i].AppointmentId + "</td> <td>" + fbkData[i].Date + "</td><td>" + fbkData[i].DoctorName + "</td></tr>");--%>
<%--            //         }--%>
<%--            //     });--%>
<%--            // } else {--%>
<%--            //     document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");--%>
<%--            // }--%>
<%--            // document.write("</table> </div>");--%>

<%--        </script>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<div id="tr6">--%>
<%--    <div class="w3-container w3-teal">--%>
<%--        <h2>Schedule of doctors</h2>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<div id="tr7">--%>
<%--    <div class="w3-container w3-teal">--%>
<%--        <h2>Prescription List</h2>--%>
<%--        <script>--%>

<%--            var i, AptData;--%>

<%--            // $.ajax({--%>
<%--            //     method: "GET",--%>
<%--            //     url: "AppointmentlistbyDate",--%>
<%--            //     dataType: 'json',--%>
<%--            //     async: false,--%>
<%--            //     success: function (response) {--%>
<%--            //         AptData = response.result;--%>
<%--            //     }--%>
<%--            // });--%>

<%--            //document.write("<table id='AppointmentTable'" + "><tr><th>" + "ID" + "</th><th>" + "Doctor Name" + "</th> <th>E-Mail</th> <th>Phone Number</th> <th>Date of Birth</th>  </tr>");--%>
<%--            // if (Object.keys(AptData).length) {--%>
<%--            //     Object.keys(AptData).forEach(function (i) {--%>
<%--            //         if (AptData[i].AppointmentStatus === "1") {--%>
<%--            //             document.write("<tr><td>" + AptData[i].DoctorName + "</td><td>" + AptData[i].AppointmentDate + "</td><td>" + "Done" + "</td><td>" + AptData[i].AmountPaid + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show</button>" + "</td><td>" + "<Button id='giveFB'>Provide</button>" + "</td></tr>");--%>
<%--            //         } else {--%>
<%--            //             document.write("<tr><td>" + AptData[i].id + "</td><td>" + AptData[i].name + "</td><td>" + AptData[i].email + "</td><td>" + AptData[i].phone + "</td><td>" + AptData[i].dob + "</td></tr>");--%>
<%--            //         }--%>
<%--            //     });--%>
<%--            // } else {--%>
<%--            //     document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");--%>
<%--            // }--%>
<%--            //--%>
<%--            // document.write("</table> </div>");--%>

<%--        </script>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<table >--%>
<%--    <tr>--%>
<%--        <td>--%>
<%--            <button class="button button1 " type="button" onclick="show(1)">Doctors List</button><br>--%>
<%--            <a href="#" onclick='show(1);'>Doctors List</a>--%>

<%--            <button class="button button2" type="button" onclick="show(2)">Patients List</button><br>--%>
<%--&lt;%&ndash;            <a href="#" onclick='show(2);'>Patients List</a>&ndash;%&gt;--%>

<%--            <button class="button button3" type="button" onclick="show(3)">Appointments List</button><br>--%>
<%--&lt;%&ndash;            <a href="#" onclick='show(3);'>Appointments List</a>&ndash;%&gt;--%>

<%--            <button class="button button4" type="button" onclick="show(4)">Prescription List</button><br>--%>
<%--&lt;%&ndash;            <a href="#" onclick='show(4);'>Prescription List</a>&ndash;%&gt;--%>

<%--            <button class="button button5" type="button" onclick="show(5)">Feedback List</button><br>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            &nbsp;--%>
<%--        </td>--%>
<%--        <td >--%>
<%--            <div  id="table1" >--%>
<%--                <h2 style="text-align:center" class="head1">Profile of all doctors</h2>--%>
<%--                <script>--%>

<%--                        var i, docData;--%>

<%--                        $.ajax({--%>
<%--                            method: "GET",--%>
<%--                            url: "/DoctorAppointmentsForAdmin",--%>
<%--                            dataType: 'json',--%>
<%--                            async: false,--%>
<%--                            success: function (response) {--%>
<%--                                docData = response.result;--%>
<%--                            }--%>
<%--                        });--%>

<%--                        document.write("<table class='center' id='AppointmentTable'" + "><tr style=\"text-align:right\"><th>" + "ID" + "</th><th>" + "Doctor Name" + "</th> <th>E-Mail</th> <th>Phone Number</th> <th>Date of Birth</th>  </tr>");--%>
<%--                        // if (Object.keys(docData).length) {--%>
<%--                        //     Object.keys(docData).forEach(function (i) {--%>
<%--                        //         if (docData[i].AppointmentStatus === "1") {--%>
<%--                        //             document.write("<tr><td>" + docData[i].DoctorName + "</td><td>" + docData[i].AppointmentDate + "</td><td>" + "Done" + "</td><td>" + docData[i].AmountPaid + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show</button>" + "</td><td>" + "<Button id='giveFB'>Provide</button>" + "</td></tr>");--%>
<%--                        //         } else {--%>
<%--                        //             document.write("<tr><td>" + docData[i].id + "</td><td>" + docData[i].name + "</td><td>" + docData[i].email + "</td><td>" + docData[i].phone + "</td><td>" + docData[i].dob + "</td></tr>");--%>
<%--                        //         }--%>
<%--                        //     });--%>
<%--                        // } else {--%>
<%--                        //     document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");--%>
<%--                        // }--%>
<%--                        //--%>
<%--                         document.write("</table> </div>");--%>

<%--                </script>--%>
<%--            </div>--%>


<%--            <div id="table2">--%>
<%--                <h2 style="text-align:center" class="head1">Patients List</h2>--%>
<%--                <script>--%>

<%--                    var i, AptData;--%>

<%--                    // $.ajax({--%>
<%--                    //     method: "GET",--%>
<%--                    //     url: "AppointmentlistbyDate",--%>
<%--                    //     dataType: 'json',--%>
<%--                    //     async: false,--%>
<%--                    //     success: function (response) {--%>
<%--                    //         AptData = response.result;--%>
<%--                    //     }--%>
<%--                    // });--%>

<%--                    document.write("<table class='center' id='AppointmentTable'" + "><tr><th>" + "ID" + "</th><th>" + "Patient" + "</th> <th>E-Mail</th> <th>Phone Number</th> <th>Date of Birth</th>  </tr>");--%>
<%--                    // if (Object.keys(AptData).length) {--%>
<%--                    //     Object.keys(AptData).forEach(function (i) {--%>
<%--                    //         if (AptData[i].AppointmentStatus === "1") {--%>
<%--                    //             document.write("<tr><td>" + AptData[i].DoctorName + "</td><td>" + AptData[i].AppointmentDate + "</td><td>" + "Done" + "</td><td>" + AptData[i].AmountPaid + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show</button>" + "</td><td>" + "<Button id='giveFB'>Provide</button>" + "</td></tr>");--%>
<%--                    //         } else {--%>
<%--                    //             document.write("<tr><td>" + AptData[i].id + "</td><td>" + AptData[i].name + "</td><td>" + AptData[i].email + "</td><td>" + AptData[i].phone + "</td><td>" + AptData[i].dob + "</td></tr>");--%>
<%--                    //         }--%>
<%--                    //     });--%>
<%--                    // } else {--%>
<%--                    //     document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");--%>
<%--                    // }--%>

<%--                    document.write("</table> </div>");--%>

<%--                </script>--%>
<%--            </div>--%>
<%--            <div id="table3">--%>
<%--                <h2 style="text-align:center">Appointments List</h2>--%>
<%--                <script>--%>

<%--                    var i, AptData;--%>

<%--                    // $.ajax({--%>
<%--                    //     method: "GET",--%>
<%--                    //     url: "AppointmentlistbyDate",--%>
<%--                    //     dataType: 'json',--%>
<%--                    //     async: false,--%>
<%--                    //     success: function (response) {--%>
<%--                    //         AptData = response.result;--%>
<%--                    //     }--%>
<%--                    // });--%>

<%--                    document.write("<table class='center' id='AppointmentTable'" + "><tr><th>" + "ID" + "</th><th>" + "Appointment" + "</th> <th>E-Mail</th> <th>Phone Number</th> <th>Date of Birth</th>  </tr>");--%>
<%--                    // if (Object.keys(AptData).length) {--%>
<%--                    //     Object.keys(AptData).forEach(function (i) {--%>
<%--                    //         if (AptData[i].AppointmentStatus === "1") {--%>
<%--                    //             document.write("<tr><td>" + AptData[i].DoctorName + "</td><td>" + AptData[i].AppointmentDate + "</td><td>" + "Done" + "</td><td>" + AptData[i].AmountPaid + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show</button>" + "</td><td>" + "<Button id='giveFB'>Provide</button>" + "</td></tr>");--%>
<%--                    //         } else {--%>
<%--                    //             document.write("<tr><td>" + AptData[i].id + "</td><td>" + AptData[i].name + "</td><td>" + AptData[i].email + "</td><td>" + AptData[i].phone + "</td><td>" + AptData[i].dob + "</td></tr>");--%>
<%--                    //         }--%>
<%--                    //     });--%>
<%--                    // } else {--%>
<%--                    //     document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");--%>
<%--                    // }--%>

<%--                    document.write("</table> </div>");--%>

<%--                </script>--%>
<%--            </div>--%>
<%--            <div id="table4">--%>
<%--                <h2 style="text-align:center">Prescription List</h2>--%>
<%--                <script>--%>

<%--                    var i, AptData;--%>

<%--                    // $.ajax({--%>
<%--                    //     method: "GET",--%>
<%--                    //     url: "AppointmentlistbyDate",--%>
<%--                    //     dataType: 'json',--%>
<%--                    //     async: false,--%>
<%--                    //     success: function (response) {--%>
<%--                    //         AptData = response.result;--%>
<%--                    //     }--%>
<%--                    // });--%>

<%--                    document.write("<table class='center' id='AppointmentTable'" + "><tr><th>" + "ID" + "</th><th>" + "Prescription" + "</th> <th>E-Mail</th> <th>Phone Number</th> <th>Date of Birth</th>  </tr>");--%>
<%--                    // if (Object.keys(AptData).length) {--%>
<%--                    //     Object.keys(AptData).forEach(function (i) {--%>
<%--                    //         if (AptData[i].AppointmentStatus === "1") {--%>
<%--                    //             document.write("<tr><td>" + AptData[i].DoctorName + "</td><td>" + AptData[i].AppointmentDate + "</td><td>" + "Done" + "</td><td>" + AptData[i].AmountPaid + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show</button>" + "</td><td>" + "<Button id='giveFB'>Provide</button>" + "</td></tr>");--%>
<%--                    //         } else {--%>
<%--                    //             document.write("<tr><td>" + AptData[i].id + "</td><td>" + AptData[i].name + "</td><td>" + AptData[i].email + "</td><td>" + AptData[i].phone + "</td><td>" + AptData[i].dob + "</td></tr>");--%>
<%--                    //         }--%>
<%--                    //     });--%>
<%--                    // } else {--%>
<%--                    //     document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");--%>
<%--                    // }--%>

<%--                    document.write("</table> </div>");--%>

<%--                </script>--%>
<%--            </div>--%>

<%--            <div id="table5">--%>
<%--                <h2 style="text-align:center">Feedback List</h2>--%>
<%--                <script>--%>

<%--                    var i, AptData;--%>

<%--                    // $.ajax({--%>
<%--                    //     method: "GET",--%>
<%--                    //     url: "AppointmentlistbyDate",--%>
<%--                    //     dataType: 'json',--%>
<%--                    //     async: false,--%>
<%--                    //     success: function (response) {--%>
<%--                    //         AptData = response.result;--%>
<%--                    //     }--%>
<%--                    // });--%>

<%--                    document.write("<table class='center' id='AppointmentTable'" + "><tr><th>" + "ID" + "</th><th>" + "Feedback" + "</th> <th>E-Mail</th> <th>Phone Number</th> <th>Date of Birth</th>  </tr>");--%>
<%--                    // if (Object.keys(AptData).length) {--%>
<%--                    //     Object.keys(AptData).forEach(function (i) {--%>
<%--                    //         if (AptData[i].AppointmentStatus === "1") {--%>
<%--                    //             document.write("<tr><td>" + AptData[i].DoctorName + "</td><td>" + AptData[i].AppointmentDate + "</td><td>" + "Done" + "</td><td>" + AptData[i].AmountPaid + "</td><td>" + "<Button id=" + i + " onclick=\"on()\">Show</button>" + "</td><td>" + "<Button id='giveFB'>Provide</button>" + "</td></tr>");--%>
<%--                    //         } else {--%>
<%--                    //             document.write("<tr><td>" + AptData[i].id + "</td><td>" + AptData[i].name + "</td><td>" + AptData[i].email + "</td><td>" + AptData[i].phone + "</td><td>" + AptData[i].dob + "</td></tr>");--%>
<%--                    //         }--%>
<%--                    //     });--%>
<%--                    // } else {--%>
<%--                    //     document.write("<tr><td colspan=6 style='text-align: center;'>" + "No Previous Appointment History." + "</td></tr>");--%>
<%--                    // }--%>

<%--                    document.write("</table> </div>");--%>

<%--                </script>--%>
<%--            </div>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--    <script>--%>
<%--        document.getElementById("table1").style.display = "none";--%>
<%--        document.getElementById("table2").style.display = "none";--%>
<%--        document.getElementById("table3").style.display = "none";--%>
<%--        document.getElementById("table4").style.display = "none";--%>
<%--        document.getElementById("table5").style.display = "none";--%>
<%--    function show(nr) {--%>
<%--        if (nr === 1) {--%>
<%--            document.getElementById("table1").style.display = "block";--%>
<%--            document.getElementById("table2").style.display = "none";--%>
<%--            document.getElementById("table3").style.display = "none";--%>
<%--            document.getElementById("table4").style.display = "none";--%>
<%--            document.getElementById("table5").style.display = "none";--%>
<%--        }--%>
<%--        if (nr === 2) {--%>
<%--            document.getElementById("table2").style.display = "block";--%>
<%--            document.getElementById("table1").style.display = "none";--%>
<%--            document.getElementById("table3").style.display = "none";--%>
<%--            document.getElementById("table4").style.display = "none";--%>
<%--            document.getElementById("table5").style.display = "none";--%>
<%--        }--%>
<%--        if (nr === 3) {--%>
<%--            document.getElementById("table3").style.display = "block";--%>
<%--            document.getElementById("table2").style.display = "none";--%>
<%--            document.getElementById("table1").style.display = "none";--%>
<%--            document.getElementById("table4").style.display = "none";--%>
<%--            document.getElementById("table5").style.display = "none";--%>
<%--        }--%>
<%--        if (nr === 4) {--%>
<%--            document.getElementById("table4").style.display = "block";--%>
<%--            document.getElementById("table1").style.display = "none";--%>
<%--            document.getElementById("table3").style.display = "none";--%>
<%--            document.getElementById("table2").style.display = "none";--%>
<%--            document.getElementById("table5").style.display = "none";--%>
<%--        }--%>
<%--        if (nr === 5) {--%>
<%--            document.getElementById("table5").style.display = "block";--%>
<%--            document.getElementById("table2").style.display = "none";--%>
<%--            document.getElementById("table1").style.display = "none";--%>
<%--            document.getElementById("table4").style.display = "none";--%>
<%--            document.getElementById("table3").style.display = "none";--%>
<%--        }--%>
<%--    }--%>

<%--    </script>--%>
</table>
</body>
</html>




<%--<div id="tl">All Patients</div>
<div id="tr">Top Right</div>
<div id="bl">Bottom Left</div>
<div id="br">Bottom Right</div>--%>
