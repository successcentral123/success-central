package com.ccsu.cs530.successcentral;

import com.ccsu.cs530.successcentral.model.Mentee;
import com.ccsu.cs530.successcentral.model.SessionForm;
import com.ccsu.cs530.successcentral.service.CrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/report_session")
public class ReportSessionServlet extends HttpServlet {
    private CrudService crud = new CrudService();

    //String menteeChosen = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        if (req.getSession().getAttribute("email") != null && req.getSession().getAttribute("isAdmin").equals("true")) {

//            HttpSession session = req.getSession();
//            String year = (String) session.getAttribute("year");

            String action = req.getParameter("ACTION");



            if("Session Report".equals(action)){

                List<Mentee> Mentees = crud.getAllMentees();
                req.setAttribute("Mentees", Mentees);
                req.getRequestDispatcher("report_session.jsp").forward(req, resp);
            }
            else{
                List<Mentee> Mentees = crud.getAllMentees();
                req.setAttribute("Mentees", Mentees);
                req.getRequestDispatcher("report_session.jsp").forward(req, resp);
            }


        }





//        //menteeChosen = "Aaron Ba";
//        menteeChosen =""; // The goal is to get the fullname here
//        req.setAttribute("mentee",menteeChosen);
//
//        HttpSession session = req.getSession();
//        session.setAttribute("mentee",menteeChosen);
//
//        //getServletContext().getRequestDispatcher("SessionFormReportServlet").forward(req,resp);
//
//
//        req.getRequestDispatcher("session_form.jsp").forward(req, resp);
//
//

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if (req.getSession().getAttribute("email") != null && req.getSession().getAttribute("isAdmin").equals("true")) {

            HttpSession session = req.getSession();

//            String index = (String) session.getAttribute("year");
            String year = req.getParameter("ddlYears");


            String action = req.getParameter("ACTION");

            List<Mentee> Mentees = crud.getAllMenteesByRegisteredYear(year);
            req.setAttribute("Mentees", Mentees);
            req.getRequestDispatcher("report_session.jsp").forward(req, resp);



        }

//        if (req.getParameter("fullname") == null) {
//            //menteeChosen = req.getParameter("fullname");
//            menteeChosen = "Aaron Ba";
//        }
//        req.setAttribute("mentee",menteeChosen);
//        getServletContext().getRequestDispatcher("SessionFormReportServlet").forward(req,resp);



//        HttpSession session = req.getSession();
//        session.setAttribute("mentee",menteeChosen);
//        if (req.getParameter("fullname") != null) {
//            //menteeChosen = req.getParameter("fullname");
//            menteeChosen = "Aaron Ba";
//
//
//
//        }
//        req.getRequestDispatcher("form_success").forward(req, resp);

    }




}
