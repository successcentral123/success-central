<%--
  Created by IntelliJ IDEA.
  User: clarkpeterson
  Date: 10/21/19
  Time: 7:16 PM This is a test for the Commit
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" %>
<%@ page import="com.ccsu.cs530.successcentral.model.SessionForm" %>
<%@ page import="com.ccsu.cs530.successcentral.model.Mentee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>

</head>
<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Session Form Report"/>
</jsp:include>
<body>
<jsp:include page="includes/navbar.jsp"/>


<div class="container">
    <!-- Display flash message -->
    <% if (request.getSession().getAttribute("message") != null) { %>
    <h3 class="text-center"><%= request.getSession().getAttribute("message") %></h3>
    <% request.getSession().setAttribute("message", null);} %>

    <div style="text-align: center";>
        <div class="mb-3">
            <img src="/assests/sc_logo-type-10x3.png" id="scLogo" alt="sclogo" width="200">
        </div>
        <div>
            <img class="mb-4"
                 src="/assests/ccsulogo.png"
                 alt="ccsulogo"
                 id="ccsuLogo"
                 width="150"/>
        </div>
        <div class="col">

<%--            <form action="" method="get" name = "ACTION" value="nothing" id = "myForm">--%>
<%--                <input type = "hidden" name="year" id="schoolYear" value="" />--%>

            <legend>Session Information:</legend>
            <% List<Mentee> Mentees = (List<Mentee>) request.getAttribute("Mentees") ;%>
            <!-- Name -->
            <label><span style="color:red">*</span> Student Name:</label>
            <br>
            <form action="/graph_SessionForm" method="get" >
                <select class="form-control" name="fullname" id="fullSelect" required="">
                    <option value="Mentee" selected>Mentee</option>
                    <%  for (int i = 0; i < Mentees.size(); i++) {
                        String first = Mentees.get(i).getFirstName();
                        String last = Mentees.get(i).getLastName();
                        String full = first + " "+ last;%>
                    <option value="<%= full %>"><%= full %></option>
                    <% } %>
                </select>
            <br>
            <input type="submit" class="btn btn-primary mb-2" name="ACTION" value="Session Report" onclick="return menteecheck()">
            </form>
            <form id="selectionform" method="post">
                <select name= "ddlYears" id="ddlYears" onchange="selectionChange()">
                    <option value="Overall" >Overall</option>
<%--                    <option value="Overall">Overall</option>--%>
                </select>
            </form>





        </div>
    </div>
</div>

<jsp:include page="includes/footer.jsp"/>
<script>
    function menteecheck() {
        var name = document.getElementById("fullSelect").value
        if (name === "Mentee") {
            alert("Please select a mentee.")
            return false
        }
        else
            return true
    }
    function sessionReport(){
        document.getElementById('reportButton').hidden = true;
        document.getElementById('ccsuLogo').hidden = true;
    }
</script>



<script type="text/javascript">
    window.onload = function () {


        //Reference the DropDownList.
        var ddlYears = document.getElementById("ddlYears");

        //Determine the Current Year.
        var currentYear = (new Date()).getFullYear();
        var lastMonth = (new Date()).getMonth()
        if(lastMonth < 3){
            currentYear = currentYear - 1
        }


        //Loop and add the Year values to DropDownList.
        for (var i = 2019; i <= currentYear; i++) {
            var option = document.createElement("OPTION");
            var year = i.toString()
            var nextYear = i + 1;
            nextYear = nextYear.toString().substr(2)
            year = year+"/"+nextYear;
            option.innerHTML = year;
            option.value = year;
            ddlYears.appendChild(option);
        }


    };
</script>

<% String index = "null"; %>

<script type="text/javascript">
    function passYear() {
        var ddlYears = document.getElementById("ddlYears");
        result = ddlYears.options[ddlYears.selectedIndex].value;


        localStorage.setItem("storageName",result);

        document.getElementById("schoolYear").value = result;

        document.forms.myForm.submit();
    }

</script>

<script>
    function selectionChange(){

        document.getElementById("selectionform").submit();
    }
</script>





</body>
</html>
