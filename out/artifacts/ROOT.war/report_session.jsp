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
            <legend>Session Information:</legend>
            <% List<Mentee> Mentees = (List<Mentee>) request.getAttribute("Mentees") ;%>
            <!-- Name -->
            <label><span style="color:red">*</span> Student Name:</label>
            <br />
            <select class="form-control" name="fullname" id="fullSelect"
                    onchange="refreshpage()" required="">
                <option value="Mentee" selected>Mentee</option>
                <%  for (int i = 0; i < Mentees.size(); i++) {
                    String first = Mentees.get(i).getFirstName();
                    String last = Mentees.get(i).getLastName();
                    String full = first + " "+ last;%>
                <option value="<%= full %>"><%= full %></option>
                <% } %>
            </select>
            <br>
            <a href="graph_SessionForm" class="button">Session Form Reportt</a>
            <button type="button" id="reportButton" class="btn btn-primary mb-2" onClick="sessionReport()">Generate Session Form Report</button>
        </div>
    </div>
</div>

<jsp:include page="includes/footer.jsp"/>
<script>
    function sessionReport(){
        document.getElementById('reportButton').hidden = true;
        document.getElementById('ccsuLogo').hidden = true;
    }
</script>
</body>
</html>
