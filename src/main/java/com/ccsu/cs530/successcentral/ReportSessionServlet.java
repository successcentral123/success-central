package com.ccsu.cs530.successcentral;

import com.ccsu.cs530.successcentral.model.Mentee;
import com.ccsu.cs530.successcentral.service.CrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/report_session")
public class ReportSessionServlet extends HttpServlet {
    private CrudService crud = new CrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getSession().getAttribute("email") != null && req.getSession().getAttribute("isAdmin").equals("true")) {
            req.getRequestDispatcher("report_session.jsp").forward(req, resp);
            String myEmail = (String)req.getSession().getAttribute("email");
            List<Mentee> myMentees = crud.getMyMentees(myEmail);
            String fullname = (String) req.getParameter("fullname");
            req.setAttribute("fullname", fullname);
            req.setAttribute("myMentees", myMentees);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    }

}
