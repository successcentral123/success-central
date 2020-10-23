package com.ccsu.cs530.successcentral;

import com.ccsu.cs530.successcentral.model.SessionForm;
import com.ccsu.cs530.successcentral.model.Mentor;
import com.ccsu.cs530.successcentral.model.Mentee;
import com.ccsu.cs530.successcentral.service.CrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet("/session_form")
public class SessionFormServlet extends HttpServlet {
    private CrudService crud = new CrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // todo want to set attributes for Previous Action Steps by pulling from database
        // todo this does not work
        String myEmail = (String)req.getSession().getAttribute("email");
        List<Mentee> myMentees = crud.getMyMentees(myEmail);
        if (req.getParameter("fullname") != null) {
            String fullname = (String) req.getParameter("fullname");
            //int maxSession = crud.getMaxSessionNumber(fullname);
            int maxSession = crud.getMaxSessionNumberX(fullname,myEmail);
            SessionForm preSession = crud.getSessionForm(fullname, maxSession,myEmail);
            req.setAttribute("preSession", preSession);
            req.setAttribute("fullname", fullname);
            req.setAttribute("myMentees", myMentees);
        }
        else
            req.setAttribute("myMentees", myMentees);

        if (req.getParameter("fullname") != null && req.getParameter("fullname").equals("Mentee")) {
            req.setAttribute("isRefresh", 0);
        }
        else if(req.getParameter("fullname") == null){
            req.setAttribute("isRefresh", 0);
        }
        else {
            req.setAttribute("isRefresh", 1);
        }
        req.getRequestDispatcher("session_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String mentorEmail = (String)req.getSession().getAttribute("email");
        Mentor mentor = crud.getMentor(mentorEmail);
        String mentorFirst = mentor.getFirstName();
        String mentorLast = mentor.getLastName();
        String mentorName = mentor.getFirstName() + " " + mentor.getLastName();
        // Instantiate sessionform and then it to database
        SessionForm sessionform = new SessionForm();
        sessionform.setMentor(mentorName);
        List<Mentee> menteelist = new ArrayList<>();
        menteelist = crud.getMyMentees(mentorEmail);

        req.setAttribute("isRefresh", 0);

//        if (req.getParameter("firstname") != null) {
//
//            sessionform.setFirstName(req.getParameter("firstname"));
//        }
//        if (req.getParameter("lastname") != null) {
//            sessionform.setLastName(req.getParameter("lastname"));
//        }
        if (req.getParameter("fullname") != null) {

            sessionform.setFullName(req.getParameter("fullname"));
        }
        if (req.getParameter("sessionnum") != null) {
            sessionform.setSessionNum(Integer.parseInt(req.getParameter("sessionnum")));
        }
        if (req.getParameter("date") != null) {
            sessionform.setDate(req.getParameter("date"));
        }

        //sends the check box values as an array
        if (req.getParameter("prevactionsteps") != null) {
            String[] boolPreAction = req.getParameterValues("prevactionsteps");
            sessionform.setPreActionSteps(boolPreAction);
        }
        // sets the input of the input boxes
        if (req.getParameter("preactionone") != null) {
            sessionform.setPreActionOne(req.getParameter("preactionone"));
        }
        if (req.getParameter("preactiontwo") != null) {
            sessionform.setPreActionTwo(req.getParameter("preactiontwo"));
        }
        if (req.getParameter("preactionthree") != null) {
            sessionform.setPreActionThree(req.getParameter("preactionthree"));
        }
        if (req.getParameter("preactionfour") != null) {
            sessionform.setPreActionFour(req.getParameter("preactionfour"));
        }
        if (req.getParameter("preactionfive") != null) {
            sessionform.setPreActionFive(req.getParameter("preactionfive"));
        }
        if (req.getParameter("preactionsix") != null) {
            sessionform.setPreActionSix(req.getParameter("preactionsix"));
        }

        if (req.getParameter("scale") != null) {
            sessionform.setScale(Integer.parseInt(req.getParameter("scale")));
        }

        // gets array of all the checked topics
        if (req.getParameter("sessiontopics") != null) {
            String[] topicsFromForm = req.getParameterValues("sessiontopics");
            sessionform.setTopicsForm(topicsFromForm);

            // sets the other text box topic
            String topicsString = "";
            for (int i = 0; i < topicsFromForm.length; i++) {
                topicsString = topicsFromForm[i];
                switch (topicsString) {
                    case "Other":
                        sessionform.setOtherText(req.getParameter("othersesstopic"));
                        break;
                    default:
                        break;
                }
            }
        }

        if (req.getParameter("issues_concerns") != null) {
            sessionform.setIssuesConcerns(req.getParameter("issues_concerns"));
        }
        if (req.getParameter("notes_comments") != null) {
            sessionform.setNotesComments(req.getParameter("notes_comments"));
        }
        if (req.getParameter("firstactionstep") != null) {
            sessionform.setFirstActionStep(req.getParameter("firstactionstep"));
        }
        if (req.getParameter("secondactionstep") != null) {
            sessionform.setSecondActionStep(req.getParameter("secondactionstep"));
        }
        if (req.getParameter("thirdactionstep") != null) {
            sessionform.setThirdActionStep(req.getParameter("thirdactionstep"));
        }
        if (req.getParameter("fourthactionstep") != null) {
            sessionform.setFourthActionStep(req.getParameter("fourthactionstep"));
        }
        if (req.getParameter("fifthactionstep") != null) {
            sessionform.setFifthActionStep(req.getParameter("fifthactionstep"));
        }
        if (req.getParameter("sixthactionstep") != null) {
            sessionform.setSixthActionStep(req.getParameter("sixthactionstep"));
        }


//        message = "<p class=\"text-success border border-success\">Session form successfully created!</p>";

        crud.createSessionForm(sessionform);
        // sets form type to seesion_form so form_success can show correct page message
        req.setAttribute("form_type", "session_form");
        req.getRequestDispatcher("form_success").forward(req, resp);

        }


    }
