<%--
  Created by IntelliJ IDEA.
  User: I520345
  Date: 6/6/2020
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/SearchResultsStyle.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
    <title>Search Results</title>
</head>
<body>
<script>
    var p = new URLSearchParams(window.location.search);
    var doclist;
    $.ajax({
        method: "POST",
        url: "SearchDoctor",
        data: {spName: p.get("spname")},
        dataType: 'json',
        async: false,
        success: function (response) {
            doclist=response.result;
            setData(response.result);
        }
    });
    function setData(doclist) {
        list=Object.keys(doclist);
        document.write("<h2 style='background: #4a824a;\n" +
            "    padding: 30px;\n" +
            "    color: white;'>Search Results (" + list.length + ")</h2>")
        document.write("<table cellspacing='0' width=80% style='margin-left: 10%;border: 0px' id=\"DocTable\">");
        if (list) {
            list.forEach(function (i) {
                document.write("<tr>\n" +
                    "        <td>"+
                    doclist[i].DoctorName +
                    "</td><td style='text-align: right;'>"+
                    doclist[i].DoctorSpeciality +
                    "</td></tr><tr style='background-color: "+ getColor(i)+"'><td>"+
                    doclist[i].DoctorExperience +
                    " Years of experience</td><td style='text-align: right;'>₹ "+
                    doclist[i].DoctorCommission+"" +
                    "</td></tr><tr style='margin-bottom: 15px'><td>Cures: "+
                    doclist[i].DoctorsExpertFields+
                    "</td><td style='text-align: right;'><button id="+i+" onclick='newApnt()'>Request Appointment</button></td>\n" +
                    "    </tr>");
            })
        }
        document.write("</table>")
    }
    var Did;

    function getColor(i) {
        if(i%2 === 0){
            return 'white';
        }else return '#f2f2f2';
    }
    function newApnt() {
        Did=event.target.id;
        document.getElementById("myForm").style.display = "block";
    }
    function closeForm() {
        document.getElementById("myForm").style.display = "none";
    }
    function Create() {
        var ill=document.getElementById("Illness").value;
        var desc=document.getElementById("Desc").value;
        var date=document.getElementById("txtDate").value;
        if(ill==="" || desc==="" || date===""){
            alert("please fill out all the fields.")
        }else{
            $.ajax({
                method: "POST",
                url: "NewAppointment",
                data: {Title: ill,
                    Description: desc,
                    Appointment_Date: date,
                    DoctorID: doclist[Did].DoctorID
                },
                dataType: 'json',
                async: false,
                success: function (response) {
                    alert("Appointment Successfully created.");
                    window.location.replace("patienthomepage");
                }
            });
        }
    }
    $(function(){
        var dtToday = new Date();

        var month = dtToday.getMonth() + 1;
        var day = dtToday.getDate();
        var year = dtToday.getFullYear();
        if(month < 10)
            month = '0' + month.toString();
        if(day < 10)
            day = '0' + day.toString();

        var maxDate = year + '-' + month + '-' + day;
        $('#txtDate').attr('min', maxDate);
    });
</script>
<div class="form-popup" id="myForm">
    <div class="form-container">
        <button type="button" style="width: 2px;margin-left: 90%;padding: 10 20 10 10;" class="btn cancel" onclick="closeForm()"><b>X</b></button>
        <br>
        <label for="Illness"><b>Illness</b></label>
        <input required type="text" placeholder="Enter Illness" name="Illness" id="Illness">

        <label for="Desc"><b>Describe</b></label>
        <input required type="text" placeholder="Description" name="Desc" id="Desc">

        <label for="AptDate"><b>Choose Date</b></label>
        <input  required type="date" placeholder="Apointment Date" name="AptDate" id="txtDate">

        <button onclick="Create()" class="btn">Create Appointment</button>
    </div>
</div>

</body>
</html>
