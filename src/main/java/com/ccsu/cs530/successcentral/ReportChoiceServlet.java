package com.ccsu.cs530.successcentral;

import com.ccsu.cs530.successcentral.service.CrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/report_intake")
public class ReportChoiceServlet extends HttpServlet {
    private CrudService crud = new CrudService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getSession().getAttribute("email") != null && req.getSession().getAttribute("isAdmin").equals("true")) {
            req.getRequestDispatcher("report_intake.jsp").forward(req, resp);








        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String year = req.getParameter("year");
        HttpSession newSession = req.getSession(true);
        newSession.setAttribute("year", year);


        if (req.getParameter("mentee") != null) {
            resp.sendRedirect("graph_MenteeIntake");


        } else if (req.getParameter("mentor") != null) {
            resp.sendRedirect("graph_MentorIntake");

        }




    }

}
