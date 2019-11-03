package com.ccsu.cs530.successcentral;

import com.ccsu.cs530.successcentral.model.SessionForm;
import com.ccsu.cs530.successcentral.service.CrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet("/session_form")
public class SessionFormServlet extends HttpServlet {
    private CrudService crud = new CrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // todo Need to set attributes for Previous Action Steps

//        SessionForm sessform = new SessionForm();
//        sessform = crud.getSessionForm(req.getParameter("firstname"), req.getParameter("lastname"), req.getParameter("sessionnum"));
//        req.setAttribute("preactionsteps", sessform.getActionStepArray());
        req.getRequestDispatcher("session_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        //todo these wont work until model and crud work
        SessionForm sessionform = new SessionForm();
        if (req.getParameter("firstname") != null) {
            sessionform.setFirstName(req.getParameter("firstname"));
        }
        if (req.getParameter("lastname") != null) {
            sessionform.setLastName(req.getParameter("lastname"));
        }
        if (req.getParameter("sessionnum") != null) {
            sessionform.setSessionNum(Integer.parseInt(req.getParameter("sessionnum")));
        }
        if (req.getParameter("date") != null) {
            sessionform.setDate(req.getParameter("date"));
        }

        //TODO the preaction steps. decided to send whole array and do the assignment in the model
        if (req.getParameter("preactionsteps") != null) {
            String[] preActionSteps = req.getParameterValues("preactionsteps");
            sessionform.setPreActionSteps(preActionSteps);
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
        if (req.getParameter("sixthtactionstep") != null) {
            sessionform.setSixthActionStep(req.getParameter("sixthtactionstep"));
        }
            // if the user already exists, update 'em
//        if (crud.getSessionForm(mentor.getEmail()).getEmail() != null) {
//            crud.updateUser(mentor);
//        } else {
//            crud.createUser(mentor);
//        }
            crud.createSessionForm(sessionform);
//            message = "<p class=\"text-success border border-success\">Mentor successfully created!</p>";
//        req.setAttribute("mentor", "true");
//        req.setAttribute("email", mentor.getEmail());
            req.getRequestDispatcher("form_success").forward(req, resp);

        }


    }
