<!doctype html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Datatable - srtdash</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <!-- amcharts css -->
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <!-- Start datatable css -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.18/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.jqueryui.min.css">
    <!-- style css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/newadminstyle.css">
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/AdminPageStyle.css">--%>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/AdminPageStyle.css">--%>
    <!-- modernizr css -->
</head>
<body>

<div class="tl1">
    <div style="margin-left:20%">
        <div >
            <h1>Welcome to LifePlus</h1>
            <h2>Administrator Page</h2>
        </div>
    </div>
</div>
<div>

    <div style="margin-top: 20px">

        <script>

            var apprData;

            $.ajax({
                method: "GET",
                url: "AppRevenue",
                dataType: 'json',
                async: false,
                success: function (response) {
                    apprData = response.result;
                }
            });
            if(apprData != null){
                document.write("<h2>"+ "App revenue for today is " + apprData.APP_REVENUE +"Rs/-"  + "</h2>" );
            }
            else {

            }

        </script>

    </div>


    <%--    <button class="button button1 " type="button" onclick="show(1)">Doctors List</button>--%>
    <!-- data table start -->
    <div class="col-12 mt-5">
        <div class="card">
            <div class="card-body">
                <h4 class="header-title">Patient List</h4>
                <div class="data-tables">
                    <script>

                        var i, docData;

                        $.ajax({
                            method: "GET",
                            url: "PatientLists",
                            dataType: 'json',
                            async: false,
                            success: function (response) {
                                docData = response.result;
                            }
                        });

                        function getFormattedDate(odate) {
                            var date = odate.toString();
                            var year = date.substring(0, 4);
                            var month = date.substring(5, 7);
                            var day = parseInt(date.substring(8, 10))
                            day = day + 1;
                            day = day.toString();

                            return day + "/" + month + "/" + year;

                        }
                        document.write("<table id='dataTable' class='text-center' " + "><thead class='bg-light text-capitalize' " + "><tr><th>" + "Name" + "</th><th>" + "Email" + "</th> <th>Address</th> <th>PhoneNumber</th> <th>Date of Birth</th> <th>Balance</th>  </tr></thead><tbody>");
                        Object.keys(docData).forEach(function (i) {
                            var dateformat = getFormattedDate(docData[i].dob);
                            // setBalance("₹" + docData[i].balance);
                            document.write("<tr><td>" + docData[i].pName + "</td><td>" + docData[i].email + "</td><td>" + docData[i].address + "</td><td>" + "+91 " +  docData[i].phone + "</td><td>"+ dateformat +"</td><td>" + docData[i].balance +"</td></tr>");
                        });
                        document.write("</tbody></table>");
                    </script>
                </div>
            </div>
        </div>
    </div>
    <!--Doctor start-->
    <div class="col-12 mt-5">
        <div class="card">
            <div class="card-body">
                <h4 class="header-title">Doctor List</h4>
                <div class="data-tables">
                    <script>
                        var i, docData;
                        $.ajax({
                            method: "GET",
                            url: "DoctorLists",
                            dataType: 'json',
                            async: false,
                            success: function (response) {
                                docData = response.result;
                            }
                        });
                        document.write("<table id='dataTable2' class='text-center' " + "><thead class='bg-light text-capitalize' " + "><tr><th>" + "Name" + "</th><th>" + "Email" + "</th> <th>Address</th> <th>PhoneNumber</th> <th>Date of Birth</th> <th>Balance</th>  </tr></thead><tbody>");
                        Object.keys(docData).forEach(function (i) {
                            var dateformat = getFormattedDate(docData[i].dob);
                            document.write("<tr><td>" + docData[i].pName + "</td><td>" + docData[i].email + "</td><td>" + docData[i].address + "</td><td>" + "+91 "+ docData[i].phone + "</td><td>"+ dateformat +"</td><td>" + docData[i].balance  +"</td></tr>");
                        });
                        document.write("</tbody></table>");
                    </script>
                </div>
            </div>
        </div>
    </div>


    <div class="col-12 mt-5">
        <div class="card">
            <div class="card-body">
                <h4 class="header-title">Appointment List</h4>
                <div class="data-tables datatable-primary">
                    <script>
                        var i, apntData;

                        $.ajax({
                            method: "GET",
                            url: "AppointmentlistForAdmin",
                            dataType: 'json',
                            async: false,
                            success: function (response) {
                                apntData = response.result;
                            }
                        });

                        document.write("<table id='dataTable3' class='text-center' " + "><thead class='text-capitalize' " + "><tr><th>" + "Patient Name" + "</th><th>" + "Doctor Name" + "</th> <th>Appointment Date</th> <th>Appointment Status</th> <th>Illness</th> <th>Description</th> <th>Fees Collected</th>  </tr></thead><tbody>");
                        if (Object.keys(apntData).length) {
                            Object.keys(apntData).forEach(function (i) {
                                if (apntData[i].doctorAccept === "1") {
                                    document.write("<tr><td>" + apntData[i].patient.pName + "</td><td>" + apntData[i].doctor.personByDId.pName + "</td><td>" + appointmentdate(apntData[i].aDateTime) + "</td><td>" + "Done" + "</td><td>" + "<Button id=" + i + " onclick=\"onillness()\">Show</button>" + "</td><td>" +  "<Button id=" + i + " onclick=\"onPrescreption()\">Show</button>" + "</td><td>" + apntData[i].doctor.fees + "</td></tr>");
                                } else {
                                    document.write("<tr><td>" + apntData[i].patient.pName + "</td><td>" + apntData[i].doctor.personByDId.pName + "</td><td>" + appointmentdate(apntData[i].aDateTime) + "</td><td>" + "Pending" + "</td><td>" + "<Button id=" + i + " onclick=\"onillness()\">Show</button>" + "</td><td>" +"--" + "</td><td>"+ "--"+ "</td></tr>");
                                }
                            });
                        }
                        document.write("</tbody></table>");




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
                            document.getElementById("overlay").innerHTML = "<div id=\"text\" style='    width: 50%;\n" +
                                "    padding: 20px 10px;\n" +
                                "    position: absolute;\n" +
                                "    top: 50%;\n" +
                                "    left: 50%;\n" +
                                "    font-size: 20px;\n" +
                                "    color: white;\n" +
                                "    background: black;\n" +
                                "    transform: translate(-50%,-50%);\n" +
                                "    -ms-transform: translate(-50%,-50%);'>\n" +
                                "        <TABLE border=\"0\" style=\"color: white;\" cellpadding=\"5px\">\n" +
                                "            <tr>\n" +
                                "                <td>" + apntData[event.target.id].patient.pName + "</td>\n" +
                                "                <td></td>\n" +
                                "            </tr>\n" +
                                "            <tr>\n" +
                                "                <td>LifePlus Cares</td>\n" +
                                "                <td></td>\n" +
                                "            </tr>\n" +
                                "            <tr>\n" +
                                "                <td>Appointment. No. " + apntData[event.target.id].aId + "</td>\n" +
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
                                "                <td>Doctor Name: " + apntData[event.target.id].doctor.personByDId.pName + "</td><td></td>\n" +
                                "            </tr>\n" +
                                "            <tr>\n" +
                                "                <td>Date: " + appointmentdate(apntData[event.target.id].aDateTime) + "</td><td></td>\n" +
                                "            </tr>\n" +
                                "            <tr>\n" +
                                "                <td>Illness Reason: " + apntData[event.target.id].illnessByIId.title + "</td><td></td>\n" +
                                "            </tr>\n" +
                                "        </TABLE>\n" +
                                "        <br>\n" +
                                "        \n" +
                                "<p style='margin: 0 0 0 9; font-size: 18; font-weight: bold;'>Illness Description:</p><p color=white style='font-size: 15px;text-align: left;'>" +
                                apntData[event.target.id].illnessByIId.description + "</p>"
                            "    </div>\n";
                            document.getElementById("overlay").style.display = "block";
                        }

                        function onPrescreption() {
                            var labtests='';
                            if(apntData[event.target.id].prescriptionByAId.labTestsByPrescId.length) {
                                for (lt of apntData[event.target.id].prescriptionByAId.labTestsByPrescId) {
                                    labtests += lt.ltName + "<br>";
                                }
                            }else{
                                labtests="None."
                            }
                            var medicines='';
                            if(apntData[event.target.id].prescriptionByAId.medicinesByPrescId.length)
                            {
                                for ( lt of apntData[event.target.id].prescriptionByAId.medicinesByPrescId) {
                                    medicines+=lt.mName + "<br>";
                                }
                            }else {
                                medicines="None."
                            }
                            document.getElementById("overlay").innerHTML = "    <div id=\"text\" style='    width: 50%;\n" +
                                "    padding: 20px 10px;\n" +
                                "    position: absolute;\n" +
                                "    top: 50%;\n" +
                                "    left: 50%;\n" +
                                "    font-size: 20px;\n" +
                                "    color: white;\n" +
                                "    background: black;\n" +
                                "    transform: translate(-50%,-50%);\n" +
                                "    -ms-transform: translate(-50%,-50%);'>\n" +
                                "        <TABLE border=\"0\" style=\"color: white;\" cellpadding=\"5px\">\n" +
                                "            <tr>\n" +
                                "                <td>" + apntData[event.target.id].doctor.personByDId.pName + "</td>\n" +
                                "                <td></td>\n" +
                                "            </tr>\n" +
                                "            <tr>\n" +
                                "                <td>LifePlus Cares</td>\n" +
                                "                <td></td>\n" +
                                "            </tr>\n" +
                                "            <tr>\n" +
                                "                <td>Appointment. No. " + apntData[event.target.id].aId + "</td>\n" +
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
                                "                <td>Patient Name: " + apntData[event.target.id].patient.pName+ "</td><td></td>\n" +
                                "            </tr>\n" +
                                "            <tr>\n" +
                                "                <td>Date: " + appointmentdate(apntData[event.target.id].aDateTime) + "</td><td></td>\n" +
                                "            </tr>\n" +
                                "        </TABLE>\n" +
                                "        <br>\n" +
                                "        \n" +
                                "<p style='margin: 0 0 0 9; font-size: 18; font-weight: bold;'>Prescription:</p><p color=white style='font-size: 15px;text-align: left;'>" +
                                apntData[event.target.id].prescriptionByAId.description + "<br>Course Duration: " +
                                apntData[event.target.id].prescriptionByAId.courseDuration +
                                "<p><div style='font-size: 15px;'>Medicines:</div><div style='font-size: 14px;'>"+
                                medicines  +
                                "</div><br><div style='font-size: 15px;'>Lab Tests:</div><div style='font-size: 14px;'>"+
                                labtests ;
                                // "</p></div></p>"+
                                // "<table><tr><td>"+
                                // "<button id='ordermed' style='background: green;\n" +
                                // "    color: white;cursor: grab;" +
                                // "    padding: 8px;\n" +
                                // "    margin: 10px;' onclick=ordermed()>Order Medicine</button>"+
                                // "</td><td><button id='booklabtest' style='background: green;\n" +
                                // "    color: white;cursor: grab;" +
                                // "    padding: 8px;\n" +
                                // "    margin: 10px;' onclick=\"booklabtest()\">Book Lab Test</button></td></tr></table>"+
                                // "    </div>\n";
                            document.getElementById("overlay").style.display = "block";
                        }

                        function off() {
                            document.getElementById("overlay").style.display = "none";
                        }

                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="overlay" style="
    position: fixed;
    display: none;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
   background: linear-gradient(black, transparent);
    z-index: 2;
    cursor: pointer;" onclick="off()"></div>


<!--Valuable patient-->
<%--<div class="col-12 mt-5">--%>
<%--    <div class="card">--%>
<%--        <div class="card-body">--%>
<%--            <h4 class="header-title">Doctor List</h4>--%>
<%--            <div class="data-tables">--%>
<%--                <script>--%>
<%--                    var i, valData;--%>
<%--                    $.ajax({--%>
<%--                        method: "GET",--%>
<%--                        url: "ValublePatient",--%>
<%--                        dataType: 'json',--%>
<%--                        async: false,--%>
<%--                        success: function (response) {--%>
<%--                            valData = response.result;--%>
<%--                        }--%>
<%--                    });--%>
<%--                    document.write("<table id='dataTable4' class='text-center' " + "><thead class='bg-light text-capitalize' " + "><tr><th>" + "Patient ID" + "</th><th>" + "Patient Name" + "</th> <th>Number Of Appointments</th></tr></thead><tbody>");--%>
<%--                    Object.keys(valData).forEach(function (i) {--%>
<%--                        document.write("<tr><td>" + valData[i].ID + "</td><td>" + valData[i].NAME + "</td><td>" + valData[i].valData[i].NO_OF_APPOINTMENTS +"</td></tr>");--%>
<%--                    });--%>
<%--                    document.write("</tbody></table>");--%>
<%--                </script>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<!-- jquery latest version -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<!-- bootstrap 4 js -->

<script src="${pageContext.request.contextPath}/resources/js/newadmin/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/newadmin/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/newadmin/slimscroll.min.js"></script>


<!-- Start datatable js -->
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.18/js/dataTables.bootstrap4.min.js"></script>

<!-- others plugins -->
<script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
</body>

</html>
