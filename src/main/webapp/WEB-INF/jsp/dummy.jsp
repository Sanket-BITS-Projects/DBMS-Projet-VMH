
</body>
</html>
<html>
<head>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
</head>
<body>

<button id="btnAdd"></button>
<button id="btnsave"></button>
 <table id="tblData">
<thead>
<tr>
    <th>Name</th>
    <th>Phone</th>
    <th>Email</th>
    <th></th>
</tr>
</thead>
<tbody>
</tbody>
</table>
<Script>
    var prescription = {};

    var arrayofmedicines = [];

    function Add(){

        var options = "";
        $.ajax({
            method: "GET",
            url: "medicines",
            dataType: 'json',
            async: false,
            success: function (response) {
                for(var i=0;i<response.result.length; i++){
                    options = options + ("<option value=\""+response.result[i].mId+"\">"+response.result[i].mName+"</option>");
                }
                $("#tblData tbody").append(
                    "<tr>"+
                    "<td> " +
                    "<select style='margin: 5 30 20 10; padding: 10 30;'>\n" +
                    options +
                    "  </select>"+
                    "</td>"+
                    "<td><input type='text'/></td>"+
                    "<td><input type='text'/></td>"+
                    "<td><img src='images/disk.png' class='btnSave'><img src='images/delete.png' class='btnDelete'/></td>"+
                    "</tr>");
            }
        });
    };

    function save(){

        $("#tblData tbody tr").each(function (row, tr) {
            let medicines = {};
            medicines.id = $(tr).find('td:eq(0)')[0].childNodes[1].value;
            medicines.usage = $(tr).find('td:eq(1)')[0].childNodes[0].value;
            arrayofmedicines.push(medicines);
        });
        prescription.medicines = arrayofmedicines;
    }

    $(function(){
        $("#btnAdd").bind("click", Add);
        $("#btnsave").bind("click", save);

    });

    console.log(prescription);


</Script>
</body>
</html>



