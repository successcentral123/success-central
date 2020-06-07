package com.ccsu.cs530.successcentral;


import com.ccsu.cs530.successcentral.model.Mentee;
import com.ccsu.cs530.successcentral.service.CrudService;
import org.json.JSONException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/graph_MentorIntake")
public class IntakeReportMentorServlet extends HttpServlet {
    private CrudService crud = new CrudService();

    public IntakeReportMentorServlet() throws FileNotFoundException, UnsupportedEncodingException, JSONException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            String year = (String) session.getAttribute("year");

            req.setAttribute("graph", crud.graphData_IntakeFormMentor(year));
            //req.setAttribute("report", crud.excelReport_IntakeFormMentee());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("graph_MentorIntake.jsp").forward(req, resp);
    }





}
