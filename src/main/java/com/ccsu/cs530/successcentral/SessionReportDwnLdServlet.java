package com.ccsu.cs530.successcentral;
import com.ccsu.cs530.successcentral.service.CrudService;
import org.json.JSONException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/graph_MenteeIntake")
@WebServlet(urlPatterns = "SessionReport")
public class SessionReportDwnLdServlet extends HttpServlet {
    private CrudService crud = new CrudService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String fileName = "SessionReport.xlsx";
        resp.setContentType("APPLICATION/OCTET-STREAM");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        File excelfile = null;
        FileInputStream fis = null;
        try {
            excelfile = crud.excelReport_SessionForm("Mentee", "Geovanni Roberts","1","2");
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

        req.getRequestDispatcher("graph_SessionForm.jsp").forward(req, resp);

    }
}
