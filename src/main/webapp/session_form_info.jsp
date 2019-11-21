<%--
  Created by IntelliJ IDEA.
  User: clarkpeterson
  Date: 10/21/19
  Time: 7:16 PM This is a test for the Commit
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" %>
<%@ page import="com.ccsu.cs530.successcentral.model.SessionForm" %>
<%@ page import="javax.mail.Session" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Session Form Info"/>
</jsp:include>
<body>
<jsp:include page="includes/navbar.jsp"/>
<%
    SessionForm sessionform = (SessionForm) request.getAttribute("session_form");
    //String name = sessionform.getFirstName() + sessionform.getLastName();
    //String date = sessionform.getDate();
    //String
%>


<div class="container">
    <!-- Display flash message -->
    <% if (request.getSession().getAttribute("message") != null) { %>
    <h3 class="text-center"><%= request.getSession().getAttribute("message") %></h3>
    <% request.getSession().setAttribute("message", null);} %>

    <div class="mx-5">
        <h1 class="text-center">Mentorship Session Form</h1>
        <form action="session_form" method="post" onsubmit="return valChecked(this)">

            <!-- Basic info -->
            <fieldset>
                <legend>Session Information:</legend>

                <!-- Name -->


                <!-- 17-Sep-2019 CDP Phase 2: Added a textbox for Session# Date  -->
                <!-- CCSU Info -->
                <div class="form-row">
                    <div class="col">
                        <label> Student Name: </label> &nbsp;
                        <b>
                            <%=sessionform.getFirstName()%>
                            <%=sessionform.getLastName()%>
                        </b>
                        <br>
                        <label>Session #:</label> &nbsp;
                        <b>
                            <%=sessionform.getSessionNum()%>
                        </b>
                        <br>
                        <label>Date:</label> &nbsp;
                        <b>
                            <%=sessionform.getDate()%>
                        </b>
                    </div>
                </div>
                <br />
            </fieldset>



            <fieldset>
                <legend>Assessment of Prior Session:</legend>
                <fieldset>
                    <label> Action Steps from the previous session (Check if completed): </label>
                    <div class="form-row">
                        <div class="col">
                        <b>
                            <%=sessionform.isBool_action_one()%> &nbsp;
                            <%=sessionform.getPreActionOne()%>
                            <br>
                            <%=sessionform.isBool_action_two()%> &nbsp;
                            <%=sessionform.getPreActionTwo()%>
                            <br>
                            <%=sessionform.isBool_action_three()%> &nbsp;
                            <%=sessionform.getPreActionThree()%>
                            <br>
                            <%=sessionform.isBool_action_four()%> &nbsp;
                            <%=sessionform.getPreActionFour()%>
                            <br>
                            <%=sessionform.isBool_action_five()%> &nbsp;
                            <%=sessionform.getPreActionFive()%>
                            <br>
                            <%=sessionform.isBool_action_six()%> &nbsp;
                            <%=sessionform.getPreActionSix()%>
                        </b>
                        </div>
                    </div>
                </fieldset>
                <br>
                <!-- 17-Sep-2019 CDP Phase 2: Change Gender to the 1-5 Scale -->
                <label>On a scale of 1 to 5, how happy is the Mentee with the outcome of the action steps from last session? </label>
                <b>
                    <%=sessionform.getScale()%>
                </b>
            </fieldset>

            <fieldset>
                <legend>Assessment of Current Session:</legend>

                <!-- 17-Sep-2019 CDP Phase 2: Change Ethnicity to the Current Session Topics -->
                <div class="form-row">
                    <div class="col">
                        <label>Current Session Topics. Check all that apply.</label>
                        <b>
                            <br>
                            <%=sessionform.isCampus_involvement()%> &nbsp;
                            <label> Campus Involvement </label>
                            <br>
                            <%=sessionform.isMeaningful_relationships()%> &nbsp;
                            <label> Meaningful Relationships </label>
                            <br>
                            <%=sessionform.isFinancial_management()%> &nbsp;
                            <label> Financial Management </label>
                            <br>
                            <%=sessionform.isOutside_responsibilities()%> &nbsp;
                            <label> Outside Responsibilities </label>
                            <br>
                            <%=sessionform.isStudy_time_management()%> &nbsp;
                            <label> Study Skills/Time Management </label>
                            <br>
                            <%=sessionform.isAcademic_engagement()%> &nbsp;
                            <label> Academic Engagement </label>
                            <br>
                            <%=sessionform.isHealth_wellness()%> &nbsp;
                            <label> Health & Wellness </label>
                            <br>
                            <%=sessionform.isBool_other()%> &nbsp;
                            <label> Other </label>
                        </b>
                    </div>
                    <div class="col">
                        <label>If you selected Other, please specify.</label>
                        <b>
                            <%=sessionform.getOther()%>
                        </b>
                    </div>
                </div>
                <br />
            </fieldset>


            <!-- Questionairre -->
            <fieldset>
                <legend>Session Notes:</legend>
                <!-- Issues & Concerns -->
                <label>Issues & Concerns:  </label> &nbsp;
                <b>
                    <%=sessionform.getIssuesConcerns()%>
                </b>
                <br />

                <!-- Notes & Comments -->
                <label>Notes & Comments:  </label> &nbsp;
                <b>
                    <%=sessionform.getNotesComments()%>
                </b>
                <br />
            </fieldset>

            <fieldset>
                <legend>New Action Steps:</legend>
                <label> New Action Step(s) to address Issues/Concerns: </label> &nbsp;
                <div class="form-row">
                    <div class="col">
                    <b>
                        <%=sessionform.getFirstActionStep()%>
                        <br>
                        <%=sessionform.getSecondActionStep()%>
                        <br>
                        <%=sessionform.getThirdActionStep()%>
                        <br>
                        <%=sessionform.getFourthActionStep()%>
                        <br>
                        <%=sessionform.getFifthActionStep()%>
                        <br>
                        <%=sessionform.getSixthActionStep()%>
                    </b>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>

<jsp:include page="includes/footer.jsp"/>
<script>function disableSubjectField() {
    if (document.getElementById('defaultCheck8a').checked) {
        document.getElementById('SessTopicOther').disabled = false;
    } else {
        document.getElementById('SessTopicOther').value = '';
        document.getElementById('SessTopicOther').disabled = true;
    }
}
</script>
</body>
</html>
