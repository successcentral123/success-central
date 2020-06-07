package com.ccsu.cs530.successcentral;

import com.ccsu.cs530.successcentral.service.CrudService;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/graph_MenteeIntake")
@WebServlet(urlPatterns = "IntakeReportMentee")
public class IntakeReportDwnLdMnteeServlet extends HttpServlet {
    private CrudService crud = new CrudService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String fileName = "MenteeIntakeReport.xlsx";
        resp.setContentType("APPLICATION/OCTET-STREAM");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        File excelfile = null;
        FileInputStream fis = null;
        try {
            HttpSession session = req.getSession();
            String year = (String) session.getAttribute("year");
            excelfile = crud.excelReport_IntakeFormMentee(year);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        fis = new FileInputStream(excelfile);

        int i;
        while((i=fis.read()) != -1){
            out.write(i);
        }
        out.close();
        fis.close();

        req.getRequestDispatcher("graph_MenteeIntake.jsp").forward(req, resp);

    }
}
