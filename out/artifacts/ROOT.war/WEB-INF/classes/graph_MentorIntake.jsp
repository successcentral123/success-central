<%--
  Created by IntelliJ IDEA.
  User: geovanni
  Date: 11/23/19
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map" %>


<%@ page import="org.json.*"%>
<%@ page import="java.io.File" %>


<!DOCTYPE html>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.js"></script>
</head>
<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Intake Form Report"/>
</jsp:include>
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
<script>
    <%int[][] graphData = (int[][])request.getAttribute("graph"); %>;

</script>


<canvas id="pie-chart2" width="800" height="450"></canvas>


<script>
    var finalData = []
    <%for (int i = 0; i < graphData[0].length; i++ ) {%>
    finalData.push(<%=graphData[0][i]%>);
    <%}%>

    new Chart(document.getElementById("pie-chart2"), {
        type: 'pie',
        data: {
            labels: ["Male","Female","Other"],
            datasets: [{
                label: "",
                backgroundColor: ["#3e95cd", "#8e5ea2"],
                //data: [2478,5267,734,784,433]
                data:finalData
            }]
        },

        options: {
            title: {
                display: true,
                text: 'Number of Mentees per Gender'
            },
            tooltips: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        var dataset = data.datasets[tooltipItem.datasetIndex];
                        var meta = dataset._meta[Object.keys(dataset._meta)[0]];
                        var total = meta.total;
                        var currentValue = dataset.data[tooltipItem.index];
                        var percentage = parseFloat((currentValue/total*100).toFixed(1));
                        return currentValue + ' (' + percentage + '%)';
                    },
                    title: function(tooltipItem, data) {
                        return data.labels[tooltipItem[0].index];
                    }
                }
            }
        }
    });


</script>



<canvas id="pie-chart3" width="800" height="450"></canvas>


<script>
    var finalData = []

    <%for (int i = 0; i < graphData[1].length; i++ ) {%>
    finalData.push(<%=graphData[1][i]%>);
    <%}%>

    new Chart(document.getElementById("pie-chart3"), {
        type: 'pie',
        data: {
            labels: ["White","Black/African Am.","Hispanic","Latino/Latina",
                "Native American","Asian","Pacific Islander","Other"],
            datasets: [{
                label: "",
                backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850","#cd3ea2","#3ecd5d","#cd8a3e"],
                //data: [2478,5267,734,784,433]
                data:finalData
            }]
        },

        options: {
            title: {
                display: true,
                text: 'Number of Participant per Ethnic Group/Race'
            },
            tooltips: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        var dataset = data.datasets[tooltipItem.datasetIndex];
                        var meta = dataset._meta[Object.keys(dataset._meta)[0]];
                        var total = meta.total;
                        var currentValue = dataset.data[tooltipItem.index];
                        var percentage = parseFloat((currentValue/total*100).toFixed(1));
                        return currentValue + ' (' + percentage + '%)';
                    },
                    title: function(tooltipItem, data) {
                        return data.labels[tooltipItem[0].index];
                    }
                }
            }
        }
    });


</script>


<canvas id="pie-chart4" width="800" height="450"></canvas>
<script>
    var finalData = []

    <%for (int i = 0; i < graphData[2].length; i++ ) {%>
    finalData.push(<%=graphData[2][i]%>);
    <%}%>

    new Chart(document.getElementById("pie-chart4"), {
        type: 'pie',
        data: {
            labels: ["Non-First-Generation College Student","First-Generation College Student"],
            datasets: [{
                label: "",
                backgroundColor: ["#3e95cd", "#8e5ea2"],
                //data: [2478,5267,734,784,433]
                data:finalData
            }]
        },

        options: {
            title: {
                display: true,
                text: 'Number of First-Generation and Non-First-Generation College Student'
            },
            tooltips: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        var dataset = data.datasets[tooltipItem.datasetIndex];
                        var meta = dataset._meta[Object.keys(dataset._meta)[0]];
                        var total = meta.total;
                        var currentValue = dataset.data[tooltipItem.index];
                        var percentage = parseFloat((currentValue/total*100).toFixed(1));
                        return currentValue + ' (' + percentage + '%)';
                    },
                    title: function(tooltipItem, data) {
                        return data.labels[tooltipItem[0].index];
                    }
                }
            }
        }
    });


</script>


<a href="IntakeReportMentor">Download Report</a>



<jsp:include page="includes/footer.jsp"/>
</body>
</html>