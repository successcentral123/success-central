<%--
  Created by IntelliJ IDEA.
  User: clarkpeterson
  Date: 10/21/19
  Time: 7:16 PM This is a test for the Commit
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>

</head>
<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Intake Form Report"/>
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
            <button type="button" id="mentorButton" class="btn btn-primary mb-2" onClick="mentorReport()">Mentor Intake Form Report</button>
            <button type="button" id="menteeButton" class="btn btn-primary mb-2" onClick="menteeReport()">Mentee Intake Form Report</button>
        </div>
    </div>

    <div class="form-row">
        <div class="col" align="left">
            <label id="mentorGen" hidden>Mentor Gender</label>
            <label id="menteeGen" hidden>Mentee Gender</label>

        </div>
        <div class="col" align="right">
            <label id="mentorEth" hidden>Mentor Ethnicity</label>
            <label id="menteeEth" hidden>Mentee Ethnicity</label>
        </div>
    </div>





</div>

<jsp:include page="includes/footer.jsp"/>
<script>
    function mentorReport(){
        document.getElementById('mentorButton').hidden = true;
        document.getElementById('menteeButton').hidden = true;
        document.getElementById('ccsuLogo').hidden = true;

        // if else statement wasn't working so I separated the functions into two functions for now
        // Eventually make one function, name it mentReport()
        // if(document.getElementById('mentorButton').checked = true){show these things}
            document.getElementById('mentorGen').hidden = false;
            document.getElementById('mentorEth').hidden = false;
            document.getElementById('subforPie1').hidden = false;
            document.getElementById('subforPie3').hidden = false;
    }

    function menteeReport(){
        document.getElementById('mentorButton').hidden = true;
        document.getElementById('menteeButton').hidden = true;
        document.getElementById('ccsuLogo').hidden = true;

        document.getElementById('menteeGen').hidden = false;
        document.getElementById('menteeEth').hidden = false;
        document.getElementById('subforPie2').hidden = false;
        document.getElementById('subforPie4').hidden = false;
    }
</script>
</body>
</html>
