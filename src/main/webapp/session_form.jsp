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
<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Session Form"/>
</jsp:include>
<body>
<jsp:include page="includes/navbar.jsp"/>


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
                <label><span style="color:red">*</span> Student Name</label>
                <div class="form-row">
                    <div class="col">
                        <input
                                type="text"
                                class="form-control"
                                placeholder="First name"
                                required=""
                                name="firstname"
                        />
                    </div>
                    <div class="col">
                        <input
                                type="text"
                                class="form-control"
                                placeholder="Last name"
                                required=""
                                name="lastname"
                        />
                    </div>
                </div>
                <br>

                <!-- 17-Sep-2019 CDP Phase 2: Added a textbox for Session# Date  -->
                <!-- CCSU Info -->
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label> <span style="color:red">*</span>Session #</label>
                        <input
                                type="text"
                                class="form-control"
                                placeholder="Session #"
                                required=""
                                name="sessionnum"
                        />
                    </div>
                    <div class="col">
                        <label><span style="color:red">*</span> Date</label>
                        <input
                                type="text"
                                class="form-control"
                                placeholder="DD-MMM-YYYY"
                                required=""
                                name="date"

                        />
                    </div>
                </div>
                <br />
            </fieldset>

            <fieldset>
                <legend>Assessment of Prior Session:</legend>

                <!-- 17-Sep-2019 CDP Phase 2: Action Steps from last session  -->
                <label><span style="color:red">*</span> Action Steps from the previous session (Check if completed): </label>
                <br>
                    <!-- 17-Sep-2019 CDP Phase 2: Need to set the values to be equal to the previous session's Action Items  -->
                    <div class="form-check" required="">
                        <input class="form-check-input" name="prevactionsteps" type="checkbox" id="defaultCheck1" value="Previous Action Item #1">
                        <label class="form-check-label" for="defaultCheck1"> Previous Action Item #1 </label></div>
                    <div class="form-check">
                        <input class="form-check-input" name="prevactionsteps" type="checkbox" id="defaultCheck2" value="Previous Action Item #2">
                        <label class="form-check-label" for="defaultCheck2"> Previous Action Item #2 </label></div>
                    <div class="form-check">
                        <input class="form-check-input" name="prevactionsteps" type="checkbox" id="defaultCheck3" value="Previous Action Item #3">
                        <label class="form-check-label" for="defaultCheck3"> Previous Action Item #3 </label></div>
                    <div class="form-check">
                        <input class="form-check-input" name="prevactionsteps" type="checkbox" id="defaultCheck4" value="Previous Action Item #4">
                        <label class="form-check-label" for="defaultCheck4"> Previous Action Item #4 </label></div>
                    <div class="form-check">
                        <input class="form-check-input" name="prevactionsteps" type="checkbox" id="defaultCheck5" value="Previous Action Item #5">
                        <label class="form-check-label" for="defaultCheck5"> Previous Action Item #5 </label></div>
                    <div class="form-check">
                        <input class="form-check-input" name="prevactionsteps" type="checkbox" id="defaultCheck6" value="Previous Action Item #6">
                        <label class="form-check-label" for="defaultCheck6"> Previous Action Item #6 </label></div>
                <br />

                <!-- 17-Sep-2019 CDP Phase 2: Change Gender to the 1-5 Scale -->
                <label><span style="color:red">*</span> On a scale of 1 to 5, how happy is the Mentee with the outcome of the action steps from last session? </label>
                <br>
                <div class="form-check">
                    <input
                            class="form-check-input"
                            type="radio"
                            id="exampleRadios1a"
                            value="5"
                            name="scale"
                            required=""
                    />
                    <label class="form-check-label" for="exampleRadios1a">5 - Very Satisfied </label></div>
                <div class="form-check" >
                    <input
                            class="form-check-input"
                            type="radio"
                            id="exampleRadios2a"
                            value="4"
                            name="scale"
                            required=""
                    />
                    <label class="form-check-label" for="exampleRadios2a"
                    >4 - Satisfied </label
                    ></div>
                <div class="form-check" >
                    <input
                            class="form-check-input"
                            type="radio"
                            id="exampleRadios3a"
                            value="3"
                            name="scale"
                            required=""
                    />
                    <label class="form-check-label" for="exampleRadios3a"
                    >3 - Neither Satisfied Nor Dissatisfied</label>
                </div>
                <div class="form-check" >
                    <input
                            class="form-check-input"
                            type="radio"
                            id="exampleRadios4a"
                            value="2"
                            name="scale"
                            required=""
                    />
                    <label class="form-check-label" for="exampleRadios4a"
                    >2 - Dissatisfied </label
                    ></div>
                <div class="form-check" >
                    <input
                            class="form-check-input"
                            type="radio"
                            id="exampleRadios5a"
                            value="1"
                            name="scale"
                            required=""
                    />
                    <label class="form-check-label" for="exampleRadios5a"
                    >1 - Very Dissatisfied </label
                    ></div>
                <br>
            </fieldset>
            <fieldset>
                <legend>Assessment of Current Session:</legend>

                <!-- 17-Sep-2019 CDP Phase 2: Change Ethnicity to the Current Session Topics -->
                <div class="form-row">
                    <div class="col">
                        <label><span style="color:red">*</span> Current Session Topics. Check all that apply.</label>
                        <div class="form-check" required="">
                            <input class="form-check-input" name="sessiontopics" type="checkbox" id="defaultCheck1a" value="Campus Involvement">
                            <label class="form-check-label" for="defaultCheck1a"> Campus Involvement </label></div>
                        <div class="form-check">
                            <input class="form-check-input" name="sessiontopics" type="checkbox" id="defaultCheck2a" value="Meaningful Relationships">
                            <label class="form-check-label" for="defaultCheck2a"> Meaningful Relationships </label></div>
                        <div class="form-check">
                            <input class="form-check-input" name="sessiontopics" type="checkbox" id="defaultCheck3a" value="Financial Management">
                            <label class="form-check-label" for="defaultCheck3a"> Financial Management </label></div>
                        <div class="form-check">
                            <input class="form-check-input" name="sessiontopics" type="checkbox" id="defaultCheck4a" value="Outside Responsibilities">
                            <label class="form-check-label" for="defaultCheck4a"> Outside Responsibilities </label></div>
                        <div class="form-check">
                            <input class="form-check-input" name="sessiontopics" type="checkbox" id="defaultCheck5a" value="Study Skills/Time Management">
                            <label class="form-check-label" for="defaultCheck5a"> Study Skills/Time Management </label></div>
                        <div class="form-check">
                            <input class="form-check-input" name="sessiontopics" type="checkbox" id="defaultCheck6a" value="Academic Engagement">
                            <label class="form-check-label" for="defaultCheck6a"> Academic Engagement </label></div>
                        <div class="form-check">
                            <input class="form-check-input" name="sessiontopics" type="checkbox" id="defaultCheck7a" value="Health & Wellness">
                            <label class="form-check-label" for="defaultCheck7a"> Health & Wellness </label></div>
                        <div class="form-check">
                            <input class="form-check-input" name="sessiontopics" type="checkbox" id="defaultCheck8a" value="Other" onclick="disableSubjectField()">
                            <label class="form-check-label" for="defaultCheck8a"> Other </label>
                        </div>
                    </div>
                    <div class="col">
                        <label>If you selected Other, please specify.</label>
                        <input
                                type="text"
                                class="form-control"
                                placeholder="Specify Session Topic"
                                name="othersesstopic"
                                id="SessTopicOther"
                                disabled
                        />
                    </div>
                </div>
                <br />
            </fieldset>


            <!-- Questionairre -->
            <fieldset>
                <legend>Session Notes:</legend>
                <!-- Issues & Concerns -->
                <label><span style="color:red">*</span> Issues & Concerns:  </label>
                <textarea class="form-control" name="issues_concerns" rows="3" required=""></textarea>
                <br />

                <!-- Notes & Comments -->
                <label><span style="color:red">*</span> Notes & Comments:  </label>
                <textarea class="form-control" name="notes_comments" rows="3" required=""></textarea>
                <br />
            </fieldset>

            <fieldset>
                <legend>New Action Steps:</legend>
                <label><span style="color:red">*</span> New Action Step(s) to address Issues/Concerns: </label>
                    <div class="col">
                        <input
                                type="text"
                                class="form-control"
                                placeholder="1st Action Step"
                                required=""
                                name="firstactionstep"
                        />
                        <br>
                        <input
                                type="text"
                                class="form-control"
                                placeholder="2nd Action Step"
                                required=""
                                name="secondactionstep"
                        />
                        <br>
                        <input
                                type="text"
                                class="form-control"
                                placeholder="3rd Action Step"
                                required=""
                                name="thirdactionstep"
                        />
                        <br>
                        <input
                                type="text"
                                class="form-control"
                                placeholder="4th Action Step"
                                required=""
                                name="fourthactionstep"
                        />
                        <br>
                        <input
                                type="text"
                                class="form-control"
                                placeholder="5th Action Step"
                                required=""
                                name="fifthactionstep"
                        />
                        <br>
                        <input
                                type="text"
                                class="form-control"
                                placeholder="6th Action Step"
                                required=""
                                name="sixthactionstep"
                        />
                    </div>
            </fieldset>

            <!-- Submit button -->
            <br>
            <button type="submit" class="btn btn-primary mb-2" onClick="return valChecked();">Submit</button>
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
