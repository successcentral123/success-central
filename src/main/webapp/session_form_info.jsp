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
                            <%=sessionform.getFullName()%>
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
                            <%  String check1 ="";
                                if (sessionform.isBool_action_one() == true)
                                    check1 = "CHECKED";
                                else
                                    check1 = "UNCHECKED";
                            %>
                            <%=check1%> &nbsp;
                            <%=sessionform.getPreActionOne()%>
                            <br>
                            <%  String check2 ="";
                                if (sessionform.isBool_action_two() == true)
                                    check2 = "CHECKED";
                                else
                                    check2 = "UNCHECKED";
                            %>
                            <%=check2%> &nbsp;&nbsp;
                            <%=sessionform.getPreActionTwo()%>
                            <br>
                            <%  String check3 ="";
                                if (sessionform.isBool_action_three() == true)
                                    check3 = "CHECKED";
                                else
                                    check3 = "UNCHECKED";
                            %>
                            <%=check3%> &nbsp;&nbsp;
                            <%=sessionform.getPreActionThree()%>
                            <br>
                            <%  String check4 ="";
                                if (sessionform.isBool_action_four() == true)
                                    check4 = "CHECKED";
                                else
                                    check4 = "UNCHECKED";
                            %>
                            <%=check4%> &nbsp;&nbsp;
                            <%=sessionform.getPreActionFour()%>
                            <br>
                            <%  String check5 ="";
                                if (sessionform.isBool_action_five() == true)
                                    check5 = "CHECKED";
                                else
                                    check5 = "UNCHECKED";
                            %>
                            <%=check5%> &nbsp; &nbsp;
                            <%=sessionform.getPreActionFive()%>
                            <br>
                            <%  String check6 ="";
                                if (sessionform.isBool_action_six() == true)
                                    check6 = "CHECKED";
                                else
                                    check6 = "UNCHECKED";
                            %>
                            <%=check6%> &nbsp;&nbsp;
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
                            <%  String checkST1 ="";
                                if (sessionform.isCampus_involvement() == true)
                                    checkST1 = "CHECKED";
                                else
                                    checkST1 = "UNCHECKED";
                            %>
                            <%=checkST1%> &nbsp;
                            <label> Campus Involvement </label>
                            <br>
                            <%  String checkST2 ="";
                                if (sessionform.isMeaningful_relationships() == true)
                                    checkST2 = "CHECKED";
                                else
                                    checkST2 = "UNCHECKED";
                            %>
                            <%=checkST2%> &nbsp;
                            <label> Meaningful Relationships </label>
                            <br>
                            <%  String checkST3 ="";
                                if (sessionform.isFinancial_management() == true)
                                    checkST3 = "CHECKED";
                                else
                                    checkST3 = "UNCHECKED";
                            %>
                            <%=checkST3%> &nbsp;
                            <label> Financial Management </label>
                            <br>
                            <%  String checkST4 ="";
                                if (sessionform.isOutside_responsibilities() == true)
                                    checkST4 = "CHECKED";
                                else
                                    checkST4 = "UNCHECKED";
                            %>
                            <%=checkST4%> &nbsp;
                            <label> Outside Responsibilities </label>
                            <br>
                            <%  String checkST5 ="";
                                if (sessionform.isStudy_time_management() == true)
                                    checkST5 = "CHECKED";
                                else
                                    checkST5 = "UNCHECKED";
                            %>
                            <%=checkST5%> &nbsp;
                            <label> Study Skills/Time Management </label>
                            <br>
                            <%  String checkST6 ="";
                                if (sessionform.isAcademic_engagement() == true)
                                    checkST6 = "CHECKED";
                                else
                                    checkST6 = "UNCHECKED";
                            %>
                            <%=checkST6%> &nbsp;
                            <label> Academic Engagement </label>
                            <br>
                            <%  String checkST7 ="";
                                if (sessionform.isHealth_wellness() == true)
                                    checkST7 = "CHECKED";
                                else
                                    checkST7 = "UNCHECKED";
                            %>
                            <%=checkST7%> &nbsp;
                            <label> Health & Wellness </label>
                            <br>
                            <%  String checkST8 ="";
                                if (sessionform.isBool_other() == true)
                                    checkST8 = "CHECKED";
                                else
                                    checkST8 = "UNCHECKED";
                            %>
                            <%=checkST8%> &nbsp;
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
                <br>
                <b>
                    <%=sessionform.getIssuesConcerns()%>
                </b>
                <br />
                <br>
                <!-- Notes & Comments -->
                <label>Notes & Comments:  </label> &nbsp;
                <br>
                <b>
                    <%=sessionform.getNotesComments()%>
                </b>
                <br />
                <br>
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
