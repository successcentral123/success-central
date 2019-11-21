package com.ccsu.cs530.successcentral;

import com.ccsu.cs530.successcentral.model.SessionForm;
import com.ccsu.cs530.successcentral.service.CrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/session_form_info")
public class SessionFormInfoServlet extends HttpServlet {
    private CrudService crud = new CrudService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("email") != null) {
            String firstname = req.getParameter("firstName");
            String lastname = req.getParameter("lastName");
            int sessionNum = Integer.parseInt(req.getParameter("seshNum"));


            SessionForm sessionform = crud.getSessionForm(firstname, lastname, sessionNum);

            // populate the request object to send to the jsp
            req.setAttribute("sessionform", sessionform);

            req.getRequestDispatcher("session_form_info.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login");
        }
    }
}
