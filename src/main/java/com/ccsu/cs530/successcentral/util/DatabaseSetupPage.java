package com.ccsu.cs530.successcentral.util;


import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.SQLException;

@SuppressWarnings("serial")
@WebServlet("/dev/dbsetup")
public class DatabaseSetupPage extends HttpServlet {

    String userTableCreationQuery = "CREATE TABLE `success_central`.`user` (" +
            " `email` VARCHAR(100) NOT NULL ," +
            " `password` TEXT NULL DEFAULT NULL ," +
            " `first_name` VARCHAR(50) NULL DEFAULT NULL ," +
            " `last_name` VARCHAR(50) NULL DEFAULT NULL ," +
            " `student_id` VARCHAR(8) NULL DEFAULT NULL ," +
            " `major` INT(4) NULL DEFAULT NULL ," +
            " `grade` INT NULL DEFAULT NULL ," +
            " `year` INT NULL DEFAULT NULL ," +
            " `hobbies` TEXT NULL DEFAULT NULL ," +
            " `ct_hometown` INT(4) NULL DEFAULT NULL ," +
            " `other_hometown` VARCHAR(30) NULL DEFAULT NULL ," +
            " `parent_education` BOOLEAN NULL DEFAULT NULL ," +
            " `language` TEXT NULL DEFAULT NULL ," +
            " `race` TEXT NULL DEFAULT NULL ," +
            " `gender` VARCHAR(10) NULL DEFAULT NULL ," +
            " `is_admin` BOOLEAN NULL DEFAULT NULL ," +
            " PRIMARY KEY (`email`)," +
            " FOREIGN KEY (major) REFERENCES major(id)," +
            " FOREIGN KEY (ct_hometown) REFERENCES town(id))" +
            " ENGINE = InnoDB; ";

    String mentorTableCreationQuery = "CREATE TABLE `success_central`.`mentor` (" +
            " `email` VARCHAR(100) NOT NULL ," +
            " `mentor_req` TEXT NULL DEFAULT NULL ," +
            " `attitude_for_diff` TEXT NULL DEFAULT NULL ," +
            " `challenge` TEXT NULL DEFAULT NULL ," +
            " `for_successful_1st_year` TEXT NULL DEFAULT NULL ," +
            " `is_approved` BOOLEAN NULL DEFAULT NULL ," +
            " `is_senior_mentor` BOOLEAN NULL DEFAULT NULL ," +
            " PRIMARY KEY (`email`)," +
            " FOREIGN KEY (email) REFERENCES user(email))" +
            " ENGINE = InnoDB; ";

    String menteeTableCreationQuery = "CREATE TABLE `success_central`.`mentee` (" +
            " `email` VARCHAR(100) NOT NULL ," +
            " `looking_forward` TEXT NULL DEFAULT NULL ," +
            " `why_mentor` TEXT NULL DEFAULT NULL ," +
            " `mentor` VARCHAR(100) NULL DEFAULT NULL ," +
            " PRIMARY KEY (`email`)," +
            " FOREIGN KEY (email) REFERENCES user(email)," +
            " FOREIGN KEY (mentor) REFERENCES user(email) ON DELETE SET NULL)" +
            " ENGINE = InnoDB; ";

    String townTableCreationQuery = "CREATE TABLE `success_central`.`town` (" +
            " `id` INT(4) NOT NULL AUTO_INCREMENT , " +
            "`name` VARCHAR(30) NOT NULL , " +
            "PRIMARY KEY (`id`))" +
            " ENGINE = InnoDB;";

    String majorTableCreationQuery = "CREATE TABLE `success_central`.`major` (" +
            " `id` INT(4) NOT NULL AUTO_INCREMENT ," +
            " `name` VARCHAR(100) NOT NULL ," +
            " PRIMARY KEY (`id`)) " +
            "ENGINE = InnoDB;";

    String passwordResetTableCreationQuery = "CREATE TABLE `success_central`.`password_reset` (" +
            " `email` VARCHAR(255) NOT NULL ," +
            " `token` VARCHAR(255) NULL," +
            " PRIMARY KEY (`email`)) " +
            "ENGINE = InnoDB;";

    String sessionformTableCreationQuery = "CREATE TABLE 'success_central'.'session_form' (" +
            " 'first_name' varchar(50) NULL NOT NULL ," +
            " 'last_name' varchar(50) NULL NOT NULL ," +
            " 'session_number' int NOT NULL ," +
            " 'day' date NOT NULL ," +
            " 'pre_action_one' text NOT NULL ," +
            " 'bool_action_one' boolean NOT NULL DEFAULT 0 ," +
            " 'pre_action_two' text NULL DEFAULT NULL ," +
            " 'bool_action_two' boolean NULL DEFAULT 0 ," +
            " 'pre_action_three' text NULL DEFAULT NULL ," +
            " 'bool_action_three' boolean NULL DEFAULT 0 ," +
            " 'pre_action_four' text NULL DEFAULT NULL ," +
            " 'bool_action_four' boolean NULL DEFAULT 0 ," +
            " 'pre_action_five' text NULL DEFAULT NULL ," +
            " 'bool_action_five' boolean NULL DEFAULT 0 ," +
            " 'pre_action_six' text NULL DEFAULT NULL ," +
            " 'bool_action_six' boolean NULL DEFAULT 0 ," +
            " 'scale' int NULL DEFAULT NULL ," +
            " 'campus_involvement' boolean NULL DEFAULT 0 ," +
            " 'meaningful_relationships' boolean NULL DEFAULT 0 ," +
            " 'financial_management' boolean NULL DEFAULT 0 ," +
            " 'outside_responsibilities' boolean NULL DEFAULT 0 ," +
            " 'study_time_management' boolean NULL DEFAULT 0 ," +
            " 'academic_engagement' boolean NULL DEFAULT 0 ," +
            " 'health_wellness' boolean NULL DEFAULT 0 ," +
            " 'other_bool' boolean NULL DEFAULT 0 ," +
            " 'other_text' varchar(100) NULL DEFAULT NULL ," +
            " 'issues_concerns' text NULL DEFAULT NULL ," +
            " 'notes_comments' text NULL DEFAULT NULL ," +
            " 'action_one' text NOT NULL ," +
            " 'action_two' text NULL DEFAULT NULL ," +
            " 'action_three' text NULL DEFAULT NULL ," +
            " 'action_four' text NULL DEFAULT NULL ," +
            " 'action_five' text NULL DEFAULT NULL ," +
            " 'action_six' text) NULL DEFAULT NULL " +
            " ENGINE = InnoDB; ";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Set up databases?</h2>");
        writer.println("<h4><b>Warning:</b> This will clear and reset all database data.</h4>");
        writer.println("<form action=\"/dev/dbsetup\" method=\"post\">");
        writer.println("<input type=\"submit\" value=\"Reset\">");
        writer.println("</form>");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        DatabaseConnection dc = new DatabaseConnection();
        String message = "";
        try {
            dc.open();
            dc.executeUpdateCommand("DROP TABLE IF EXISTS `mentee`;");
            dc.executeUpdateCommand("DROP TABLE IF EXISTS `mentor`;");
            dc.executeUpdateCommand("DROP TABLE IF EXISTS `user`;");
            dc.executeUpdateCommand("DROP TABLE IF EXISTS `town`;");
            dc.executeUpdateCommand("DROP TABLE IF EXISTS `major`;");
            dc.executeUpdateCommand("DROP TABLE IF EXISTS `password_reset`;");
            dc.executeUpdateCommand("DROP TABLE IF EXISTS 'session_form;");
            message += "<p>All tables dropped.</p>";
            dc.executeUpdateCommand(majorTableCreationQuery);
            dc.executeUpdateCommand(townTableCreationQuery);
            dc.executeUpdateCommand(userTableCreationQuery);
            dc.executeUpdateCommand(mentorTableCreationQuery);
            dc.executeUpdateCommand(menteeTableCreationQuery);
            dc.executeUpdateCommand(passwordResetTableCreationQuery);
            dc.executeQueryCommand(sessionformTableCreationQuery);
            message += "<p>New tables added.</p>";
        } catch (SQLException | NamingException e) {
            message += "<p>Error: SQL Exception Phil Thrown.</p>";
            System.out.println(e);
        } finally {
            try {
                dc.close();
            } catch (SQLException e){
                message += "<p>Error: SQL Connection did not close correctly.</p>";
            }
        }

//        DataSource ds = null;
//        Connection con = null;
//        PreparedStatement pr = null;
//        InitialContext ic;
//        int success = 0;
//        String message = "";
//        try {
//            ic = new InitialContext();
//            ds = (DataSource)ic.lookup("java:/SCDB");
//            con = ds.getConnection();
//            pr = con.prepareStatement("DROP TABLE IF EXISTS `USER`;");
//            pr.executeUpdate();
//            pr = con.prepareStatement("DROP TABLE IF EXISTS `mentor`;");
//            pr.executeUpdate();
//            pr = con.prepareStatement("DROP TABLE IF EXISTS `mentee`;");
//            pr.executeUpdate();
//            //pr = con.prepareStatement("CREATE TABLE `success_central`.`USER` ( `id` INT(10) NOT NULL AUTO_INCREMENT , `email` VARCHAR(100) NOT NULL , `first_name` VARCHAR(50) NOT NULL , `last_name` VARCHAR(50) NOT NULL , `ccsu_id` VARCHAR(10) NOT NULL , `pass` VARCHAR(100) NOT NULL , PRIMARY KEY (`id`), UNIQUE (`email`), UNIQUE (`ccsu_id`)) ENGINE = InnoDB; ");
//            pr = con.prepareStatement(mentorTableCreationQuery);
//            success += pr.executeUpdate();
//            pr = con.prepareStatement(menteeTableCreationQuery);
//            success += pr.executeUpdate();
//            pr.close();
//            message = "Table created successfully!";
//        } catch (Exception e){
//            if (success == 2){
//                message = "All tables created, but an error occurred.<br>" + e.toString() + "<br>";
//            }
//            else
//            {
//                message = "Error occurred, all tables may not have been created.<br>" + e.toString() + "<br>";
//            }
//        } finally {
//            if (con != null){
//                try {
//                    con.close();
//                } catch (SQLException e)
//                {
//                    message += "Error closing SQL connection.<br>" + e.toString() + "<br>";
//                }
//            }
//        }
        writer.println("<h2>Databases reset results:</h2>");
        writer.println(message);
        writer.close();
    }


}