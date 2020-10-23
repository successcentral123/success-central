package com.ccsu.cs530.successcentral.service;

import com.ccsu.cs530.successcentral.model.Mentee;
import com.ccsu.cs530.successcentral.model.Mentor;
import com.ccsu.cs530.successcentral.model.User;
import com.ccsu.cs530.successcentral.model.SessionForm;
import com.ccsu.cs530.successcentral.util.DatabaseConnection;


import java.io.*;
import java.net.BindException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



import java.sql.ResultSetMetaData;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;



public class CrudService {
    private Connection con = DatabaseConnection.getConnection();



    // -------- CREATE ---------

    // Create a user in the db.
    // Remember! Email is the primary key.
    public void createUser(User user) {
        try {
            if (user.getEmail() == null) {
                throw new Exception();
            }

            String qry = "INSERT INTO user VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getStudentId());
            statement.setObject(6, user.getMajor());
            statement.setObject(7, user.getGrade());
            statement.setObject(8, user.getYear());
            statement.setString(9, user.getHobbies());
            statement.setObject(10, user.getCtHometown());
            statement.setString(11, user.getOtherHometown());
            statement.setObject(12, user.getParentEducation());
            statement.setString(13, user.getLanguage());
            statement.setString(14, user.getRace());
            statement.setString(15, user.getGender());
            statement.setString(16, "0"); //USER IS NOT AN ADMIN
            statement.setString(17,user.getYearRegistered());

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Could not create the user." + e);
        }
    }

    // Create a mentee in the db.
    public void createMentee(Mentee mentee) {
        try {
            String qry = "INSERT INTO mentee VALUES (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, mentee.getEmail());
            statement.setString(2, mentee.getLookingForward());
            statement.setString(3, mentee.getWhyMentor());
            statement.setString(4, mentee.getMentor() == null ? null : mentee.getMentor().getEmail());
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("There was a problem creating the mentee" + e);
        }
    }

    // Create a mentor in the db.
    public void createMentor(Mentor mentor) {
        try {
            // Create the mentor
            String qry = "INSERT INTO mentor VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, mentor.getEmail());
            statement.setString(2, mentor.getMentorReq());
            statement.setString(3, mentor.getAttitudeForDifference());
            statement.setString(4, mentor.getChallenge());
            statement.setString(5, mentor.getForSuccessfulFirstYear());
            statement.setObject(6, mentor.getApproved());
            statement.setObject(7, mentor.getSeniorMentor());
            statement.setObject(8,mentor.getMatchingStatus());

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Could not create the mentor." + e);
        }

    }

    //create an admin-level user in the db
    public void createAdmin(User admin){
        try {
            if (admin.getEmail() == null) {
                throw new Exception();
            }

            String qry = "INSERT INTO user (email, password, is_admin) VALUES (?,?,?)";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, admin.getEmail());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getIsAdmin() ? "1" : "0");
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Could not create the user." + e);
        }
    }

    //Stores the information from a current session form into
    //the database.
    public void createSessionForm(SessionForm sessionform){

        try {
            String qry = "INSERT INTO session_form VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(qry);
//            statement.setString(1, sessionform.getMentor() == null ? null : sessionform.getMentor().getEmail());
            statement.setString(1,sessionform.getMentor());
            statement.setString(2,sessionform.getFullName());
//            statement.setString(2, sessionform.getFirstName());
//            statement.setString(3, sessionform.getLastName());
            statement.setInt(3, sessionform.getSessionNum());
            statement.setString(4, sessionform.getDate());

            statement.setString(5, sessionform.getPreActionOne());
            statement.setBoolean(6, sessionform.isBool_action_one());
            statement.setString(7, sessionform.getPreActionTwo());
            statement.setBoolean(8, sessionform.isBool_action_two());
            statement.setString(9, sessionform.getPreActionThree());
            statement.setBoolean(10, sessionform.isBool_action_three());
            statement.setString(11, sessionform.getPreActionFour());
            statement.setBoolean(12, sessionform.isBool_action_four());
            statement.setString(13, sessionform.getPreActionFive());
            statement.setBoolean(14, sessionform.isBool_action_five());
            statement.setString(15, sessionform.getPreActionSix());
            statement.setBoolean(16, sessionform.isBool_action_six());

            statement.setInt(17, sessionform.getScale());

            statement.setBoolean(18, sessionform.isCampus_involvement());
            statement.setBoolean(19, sessionform.isMeaningful_relationships());
            statement.setBoolean(20, sessionform.isFinancial_management());
            statement.setBoolean(21, sessionform.isOutside_responsibilities());
            statement.setBoolean(22, sessionform.isStudy_time_management());
            statement.setBoolean(23, sessionform.isAcademic_engagement());
            statement.setBoolean(24, sessionform.isHealth_wellness());
            statement.setBoolean(25, sessionform.isBool_other());
            statement.setString(26, sessionform.getOther());
            statement.setString(27, sessionform.getIssuesConcerns());
            statement.setString(28, sessionform.getNotesComments());
            statement.setString(29, sessionform.getFirstActionStep());
            statement.setString(30, sessionform.getSecondActionStep());
            statement.setString(31, sessionform.getThirdActionStep());
            statement.setString(32, sessionform.getFourthActionStep());
            statement.setString(33, sessionform.getFifthActionStep());
            statement.setString(34, sessionform.getSixthActionStep());

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("There was a problem placing the session form in the database\n" + e);
        }

    }






    // -------- READ ---------

    // Get a user with a specific email from the db.
    // Remember that email is the primary key!
    public User getUser(String email) {
        User user = new User();
        try {
            if (email == null) {
                throw new Exception();
            }
            String qry = "SELECT * FROM user WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, email);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                user.setEmail(results.getString("email"));
                user.setPassword(results.getString("password"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setStudentId(results.getString("student_id"));
                user.setMajor(results.getString("major"));
                user.setGrade(results.getString("grade"));
                user.setYear(results.getString("year"));
                user.setHobbies(results.getString("hobbies"));
                user.setCtHometown(results.getString("ct_hometown"));
                user.setOtherHometown(results.getString("other_hometown"));
                user.setParentEducation(results.getBoolean("parent_education"));
                user.setLanguage(results.getString("language"));
                user.setRace(results.getString("race"));
                user.setGender(results.getString("gender"));
            }
        } catch (Exception e) {
            System.out.println("Could not get the user");
        }
        return user;
    }

    /**
     * Get the password of a user.
     * @param email the email of the user
     * @return the hashed password of that user.
     */
    public String getPassword(String email) {
        String pwd = null;
        try {
            if (email == null) {
                throw new Exception();
            }
            String qry = "SELECT password FROM user WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, email);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                pwd = results.getString("password");
            }
        } catch (Exception e) {
            System.out.println("Could not get the password of the Success Central user.");
        }
        return pwd;
    }

    public int getAdminCount(){
        String qry = "SELECT COUNT(*) FROM user WHERE is_admin = 1";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();
            results.next();
            return results.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Get a mentee with a specific email from the db.
    public Mentee getMentee(String email) {
        Mentee mentee = new Mentee();
        try {
            String qry = "SELECT *, town.name AS town_name FROM user NATURAL JOIN mentee JOIN major ON (user.major = major.id) JOIN town ON (user.ct_hometown = town.id) WHERE email=?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, email);

            ResultSet results = statement.executeQuery();
            if (results.next()) {
                mentee.setEmail(results.getString("email"));
                mentee.setPassword(results.getString("password"));
                mentee.setFirstName(results.getString("first_name"));
                mentee.setLastName(results.getString("last_name"));
                mentee.setStudentId(results.getString("student_id"));
                mentee.setMajor(results.getString("name"));
                mentee.setGrade(results.getString("grade"));
                mentee.setYear(results.getString("year"));
                mentee.setHobbies(results.getString("hobbies"));
                mentee.setCtHometown(results.getString("town_name"));
                mentee.setOtherHometown(results.getString("other_hometown"));
                mentee.setParentEducation(results.getBoolean("parent_education"));
                mentee.setLanguage(results.getString("language"));
                mentee.setRace(results.getString("race"));
                mentee.setGender(results.getString("gender"));
                mentee.setEmail(email);
                mentee.setLookingForward(results.getString("looking_forward"));
                mentee.setWhyMentor(results.getString("why_mentor"));

                if (results.getString("mentor") != null) {
                    mentee.setMentor(getMentor(results.getString("mentor")));
                }
            }
        } catch (Exception e) {
            System.out.println("Could not get the mentee");
        }
        return mentee;
    }


    // Get a mentor with a specific email from the db.
    public Mentor getMentor(String email) {
        Mentor mentor = new Mentor();
        try {
            String qry = "SELECT *, town.name AS town_name, major.name AS major_name\n" +
                    "FROM user NATURAL JOIN mentor JOIN town ON (user.ct_hometown = town.id) JOIN major ON (user.major = major.id) WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, email);

            ResultSet results = statement.executeQuery();
            if (results.next()) {
                mentor.setEmail(email);
                mentor.setPassword(results.getString("password"));
                mentor.setFirstName(results.getString("first_name"));
                mentor.setLastName(results.getString("last_name"));
                mentor.setStudentId(results.getString("student_id"));
                mentor.setMajor(results.getString("major_name"));
                mentor.setGrade(results.getString("grade"));
                mentor.setYear(results.getString("year"));
                mentor.setHobbies(results.getString("hobbies"));
                mentor.setCtHometown(results.getString("town_name"));
                mentor.setOtherHometown(results.getString("other_hometown"));
                mentor.setParentEducation(results.getBoolean("parent_education"));
                mentor.setLanguage(results.getString("language"));
                mentor.setRace(results.getString("race"));
                mentor.setGender(results.getString("gender"));
                mentor.setMentorReq(results.getString("mentor_req"));
                mentor.setAttitudeForDifference(results.getString("attitude_for_diff"));
                mentor.setChallenge(results.getString("challenge"));
                mentor.setForSuccessfulFirstYear(results.getString("for_successful_1st_year"));
                mentor.setApproved(results.getBoolean("is_approved"));
                mentor.setSeniorMentor(results.getBoolean("is_senior_mentor"));
                mentor.setMatchingStatus(results.getBoolean("matching_status"));
            }
        } catch (Exception e) {
            System.out.println("Could not get the mentee");
        }
        return mentor;
    }

    // Get a specfic Session From from the DB with a firstname lastname and session#
    public SessionForm getSessionForm(String fullname, int sessionnumber, String mentorEmail) {
        SessionForm sessionform = new SessionForm();
        String mentorName = "";
        try {
            String qry = "select concat(first_name, ' ' ,last_name) as fullname from user  where email = ? ;";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, mentorEmail);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                mentorName = results.getString("fullname");
            }

        } catch (Exception e) {
            System.out.println("Could not get the mentor name\n" +e);
        }
        try {
            if (fullname == null) { // todo what about no session number?
                throw new Exception();
            }
            String qry = "SELECT * FROM session_form WHERE full_name = ? AND session_number = ?" +
                    " AND mentor = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, fullname);
            statement.setInt(2, sessionnumber);
            statement.setString(3,mentorName);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
//                sessionform.setMentor(getMentor(results.getString("mentor")));
                sessionform.setMentor(results.getString("mentor"));
                sessionform.setFullName(results.getString("full_name"));
//                sessionform.setFirstName(results.getString("first_name"));
//                sessionform.setLastName(results.getString("last_name"));
                sessionform.setSessionNum(results.getInt("session_number"));
                sessionform.setDate(results.getString("day"));

                sessionform.setPreActionOne(results.getString("pre_action_one"));
                sessionform.setBoolActionOne(results.getBoolean("bool_action_one"));
                sessionform.setPreActionTwo(results.getString("pre_action_two"));
                sessionform.setBoolActionTwo(results.getBoolean("bool_action_two"));
                sessionform.setPreActionThree(results.getString("pre_action_three"));
                sessionform.setBoolActionThree(results.getBoolean("bool_action_three"));
                sessionform.setPreActionFour(results.getString("pre_action_four"));
                sessionform.setBoolActionFour(results.getBoolean("bool_action_four"));
                sessionform.setPreActionFive(results.getString("pre_action_five"));
                sessionform.setBoolActionFive(results.getBoolean("bool_action_five"));
                sessionform.setPreActionSix(results.getString("pre_action_six"));
                sessionform.setBoolActionSix(results.getBoolean("bool_action_six"));

                sessionform.setScale(results.getInt("scale"));

                sessionform.setCampusInvolement(results.getBoolean("campus_involvement"));
                sessionform.setMeaningfulRelationships(results.getBoolean("meaningful_relationships"));
                sessionform.setFinancialManagement(results.getBoolean("financial_management"));
                sessionform.setOutsideResponsibilities(results.getBoolean("outside_responsibilities"));
                sessionform.setStudyTimeManagement(results.getBoolean("study_time_management"));
                sessionform.setAcademicEngagement(results.getBoolean("academic_engagement"));
                sessionform.setHealthWellness(results.getBoolean("health_wellness"));
                sessionform.setOther(results.getBoolean("other_bool"));
                sessionform.setOtherText(results.getString("other_text"));

                sessionform.setIssuesConcerns(results.getString("issues_concerns"));
                sessionform.setNotesComments(results.getString("notes_comments"));

                sessionform.setFirstActionStep(results.getString("action_one"));
                sessionform.setSecondActionStep(results.getString("action_two"));
                sessionform.setThirdActionStep(results.getString("action_three"));
                sessionform.setFourthActionStep(results.getString("action_four"));
                sessionform.setFifthActionStep(results.getString("action_five"));
                sessionform.setSixthActionStep(results.getString("action_six"));
            }

        } catch (Exception e) {
            System.out.println("could not get Session Form \n" + e);
        }
        return sessionform;
    }






    public SessionForm getSessionFormAdmin(String fullname, int sessionnumber, String date) {
        SessionForm sessionform = new SessionForm();
        try {
            if (fullname == null) { // todo what about no session number?
                throw new Exception();
            }
            String qry = "SELECT * FROM session_form WHERE full_name = ? AND session_number = ?" +
                    " AND day = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, fullname);
            statement.setInt(2, sessionnumber);
            statement.setString(3,date);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
//                sessionform.setMentor(getMentor(results.getString("mentor")));
                sessionform.setMentor(results.getString("mentor"));
                sessionform.setFullName(results.getString("full_name"));
//                sessionform.setFirstName(results.getString("first_name"));
//                sessionform.setLastName(results.getString("last_name"));
                sessionform.setSessionNum(results.getInt("session_number"));
                sessionform.setDate(results.getString("day"));

                sessionform.setPreActionOne(results.getString("pre_action_one"));
                sessionform.setBoolActionOne(results.getBoolean("bool_action_one"));
                sessionform.setPreActionTwo(results.getString("pre_action_two"));
                sessionform.setBoolActionTwo(results.getBoolean("bool_action_two"));
                sessionform.setPreActionThree(results.getString("pre_action_three"));
                sessionform.setBoolActionThree(results.getBoolean("bool_action_three"));
                sessionform.setPreActionFour(results.getString("pre_action_four"));
                sessionform.setBoolActionFour(results.getBoolean("bool_action_four"));
                sessionform.setPreActionFive(results.getString("pre_action_five"));
                sessionform.setBoolActionFive(results.getBoolean("bool_action_five"));
                sessionform.setPreActionSix(results.getString("pre_action_six"));
                sessionform.setBoolActionSix(results.getBoolean("bool_action_six"));

                sessionform.setScale(results.getInt("scale"));

                sessionform.setCampusInvolement(results.getBoolean("campus_involvement"));
                sessionform.setMeaningfulRelationships(results.getBoolean("meaningful_relationships"));
                sessionform.setFinancialManagement(results.getBoolean("financial_management"));
                sessionform.setOutsideResponsibilities(results.getBoolean("outside_responsibilities"));
                sessionform.setStudyTimeManagement(results.getBoolean("study_time_management"));
                sessionform.setAcademicEngagement(results.getBoolean("academic_engagement"));
                sessionform.setHealthWellness(results.getBoolean("health_wellness"));
                sessionform.setOther(results.getBoolean("other_bool"));
                sessionform.setOtherText(results.getString("other_text"));

                sessionform.setIssuesConcerns(results.getString("issues_concerns"));
                sessionform.setNotesComments(results.getString("notes_comments"));

                sessionform.setFirstActionStep(results.getString("action_one"));
                sessionform.setSecondActionStep(results.getString("action_two"));
                sessionform.setThirdActionStep(results.getString("action_three"));
                sessionform.setFourthActionStep(results.getString("action_four"));
                sessionform.setFifthActionStep(results.getString("action_five"));
                sessionform.setSixthActionStep(results.getString("action_six"));
            }

        } catch (Exception e) {
            System.out.println("could not get Session Form \n" + e);
        }
        return sessionform;
    }










    public int getMaxSessionNumber(String name) {
        int sessionnum = 1;
        try {
            String qry = "SELECT MAX(session_number) FROM session_form WHERE full_name like ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, name);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                sessionnum = results.getInt("MAX(session_number)");
            }

        } catch (Exception e) {
            System.out.println("Could not get the session number\n" +e);
        }

        return sessionnum;
    }

    public int getMaxSessionNumberX(String name,String mentorEmail) {
        int sessionnum = 1;
        String mentorName = "";
        try {
            String qry = "select concat(first_name, ' ' ,last_name) as fullname from user  where email = ? ;";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, mentorEmail);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                mentorName = results.getString("fullname");
            }

        } catch (Exception e) {
            System.out.println("Could not get the mentor name\n" +e);
        }

        try {
            String qry = "SELECT MAX(session_number) FROM session_form WHERE full_name = ? " +
                    "and mentor = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, name);
            statement.setString(2, mentorName);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                sessionnum = results.getInt("MAX(session_number)");
            }

        } catch (Exception e) {
            System.out.println("Could not get the session number\n" +e);
        }

        return sessionnum;
    }










    public List<SessionForm> getSessionFormsMentor(int first, int total, String search, String sortBy,String name) {
        List<SessionForm> sessionformlist = new ArrayList<>();
        String tmpQry = "";

        try {
            String qry = "SELECT * FROM session_form where mentor="+"'"+name+"'";
            if(search != null){
                qry += "and mentor like '%"+search+"%' OR full_name like '%"+search+ "%' ";
            }
            if(sortBy != null && !sortBy.equals("")){
                if(sortBy.equals("day_0")){
                    qry += " ORDER BY day DESC, session_number DESC  ";
                } else if(sortBy.equals("day_1")){
                    qry += " ORDER BY day ASC, session_number ASC ";
                }else{
                    qry += " ORDER BY " + sortBy + ", session_number ASC ";
                }
            }

            qry += " LIMIT ?,?";

            tmpQry = qry;
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, first);
            statement.setInt(2, total);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                SessionForm sessionform = new SessionForm();
//                sessionform.setMentor(getMentor(results.getString("mentor")));
                sessionform.setMentor(results.getString("mentor"));
                sessionform.setFullName(results.getString("full_name"));
//                sessionform.setFirstName(results.getString("first_name"));
//                sessionform.setLastName(results.getString("last_name"));
                sessionform.setSessionNum(results.getInt("session_number"));
                sessionform.setDate(results.getString("day"));

                sessionform.setPreActionOne(results.getString("pre_action_one"));
                sessionform.setBoolActionOne(results.getBoolean("bool_action_one"));
                sessionform.setPreActionTwo(results.getString("pre_action_two"));
                sessionform.setBoolActionTwo(results.getBoolean("bool_action_two"));
                sessionform.setPreActionThree(results.getString("pre_action_three"));
                sessionform.setBoolActionThree(results.getBoolean("bool_action_three"));
                sessionform.setPreActionFour(results.getString("pre_action_four"));
                sessionform.setBoolActionFour(results.getBoolean("bool_action_four"));
                sessionform.setPreActionFive(results.getString("pre_action_five"));
                sessionform.setBoolActionFive(results.getBoolean("bool_action_five"));
                sessionform.setPreActionSix(results.getString("pre_action_six"));
                sessionform.setBoolActionSix(results.getBoolean("bool_action_six"));

                sessionform.setScale(results.getInt("scale"));

                sessionform.setCampusInvolement(results.getBoolean("campus_involvement"));
                sessionform.setMeaningfulRelationships(results.getBoolean("meaningful_relationships"));
                sessionform.setFinancialManagement(results.getBoolean("financial_management"));
                sessionform.setOutsideResponsibilities(results.getBoolean("outside_responsibilities"));
                sessionform.setStudyTimeManagement(results.getBoolean("study_time_management"));
                sessionform.setAcademicEngagement(results.getBoolean("academic_engagement"));
                sessionform.setHealthWellness(results.getBoolean("health_wellness"));
                sessionform.setOther(results.getBoolean("other_bool"));
                sessionform.setOtherText(results.getString("other_text"));

                sessionform.setIssuesConcerns(results.getString("issues_concerns"));
                sessionform.setNotesComments(results.getString("notes_comments"));

                sessionform.setFirstActionStep(results.getString("action_one"));
                sessionform.setSecondActionStep(results.getString("action_two"));
                sessionform.setThirdActionStep(results.getString("action_three"));
                sessionform.setFourthActionStep(results.getString("action_four"));
                sessionform.setFifthActionStep(results.getString("action_five"));
                sessionform.setSixthActionStep(results.getString("action_six"));

                sessionformlist.add(sessionform);
            }
        } catch (Exception e) {
            System.out.println("Could not get the session form "+tmpQry +"\n" +e);
        }

        return sessionformlist;
    }














    public List<SessionForm> getSessionForms(int first, int total, String search, String sortBy) {
        List<SessionForm> sessionformlist = new ArrayList<>();
        String tmpQry = "";

        try {
            String qry = "SELECT * FROM session_form ";
            if(search != null){
                qry += "WHERE mentor like '%"+search+"%' OR full_name like '%"+search+ "%' ";
            }
            if(sortBy != null && !sortBy.equals("")){
                if(sortBy.equals("day_0")){
                    qry += " ORDER BY day DESC, session_number DESC  ";
                } else if(sortBy.equals("day_1")){
                    qry += " ORDER BY day ASC, session_number ASC ";
                }else{
                    qry += " ORDER BY " + sortBy + ", session_number ASC ";
                }
            }

            qry += " LIMIT ?,?";

            tmpQry = qry;
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, first);
            statement.setInt(2, total);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                SessionForm sessionform = new SessionForm();
//                sessionform.setMentor(getMentor(results.getString("mentor")));
                sessionform.setMentor(results.getString("mentor"));
                sessionform.setFullName(results.getString("full_name"));
//                sessionform.setFirstName(results.getString("first_name"));
//                sessionform.setLastName(results.getString("last_name"));
                sessionform.setSessionNum(results.getInt("session_number"));
                sessionform.setDate(results.getString("day"));

                sessionform.setPreActionOne(results.getString("pre_action_one"));
                sessionform.setBoolActionOne(results.getBoolean("bool_action_one"));
                sessionform.setPreActionTwo(results.getString("pre_action_two"));
                sessionform.setBoolActionTwo(results.getBoolean("bool_action_two"));
                sessionform.setPreActionThree(results.getString("pre_action_three"));
                sessionform.setBoolActionThree(results.getBoolean("bool_action_three"));
                sessionform.setPreActionFour(results.getString("pre_action_four"));
                sessionform.setBoolActionFour(results.getBoolean("bool_action_four"));
                sessionform.setPreActionFive(results.getString("pre_action_five"));
                sessionform.setBoolActionFive(results.getBoolean("bool_action_five"));
                sessionform.setPreActionSix(results.getString("pre_action_six"));
                sessionform.setBoolActionSix(results.getBoolean("bool_action_six"));

                sessionform.setScale(results.getInt("scale"));

                sessionform.setCampusInvolement(results.getBoolean("campus_involvement"));
                sessionform.setMeaningfulRelationships(results.getBoolean("meaningful_relationships"));
                sessionform.setFinancialManagement(results.getBoolean("financial_management"));
                sessionform.setOutsideResponsibilities(results.getBoolean("outside_responsibilities"));
                sessionform.setStudyTimeManagement(results.getBoolean("study_time_management"));
                sessionform.setAcademicEngagement(results.getBoolean("academic_engagement"));
                sessionform.setHealthWellness(results.getBoolean("health_wellness"));
                sessionform.setOther(results.getBoolean("other_bool"));
                sessionform.setOtherText(results.getString("other_text"));

                sessionform.setIssuesConcerns(results.getString("issues_concerns"));
                sessionform.setNotesComments(results.getString("notes_comments"));

                sessionform.setFirstActionStep(results.getString("action_one"));
                sessionform.setSecondActionStep(results.getString("action_two"));
                sessionform.setThirdActionStep(results.getString("action_three"));
                sessionform.setFourthActionStep(results.getString("action_four"));
                sessionform.setFifthActionStep(results.getString("action_five"));
                sessionform.setSixthActionStep(results.getString("action_six"));

                sessionformlist.add(sessionform);
            }
        } catch (Exception e) {
            System.out.println("Could not get the session form "+tmpQry +"\n" +e);
        }

        return sessionformlist;
    }



    /**
     * Returns a list of Mentees for paging purposes.
     * @param first the first Mentor you'd like to return
     * @param total the total number of records per page
     * @return the list of Mentors to be found
     * @throws Exception
     */
    public List<Mentee> getMentees(int first, int total, String search,String sortBy) {
        List<Mentee> mentees = new ArrayList<>();
        String tmpQry = "";

        try {
            String qry = "SELECT * FROM user NATURAL JOIN mentee JOIN major ON user.major = major.id ";
            if(search != null){
                qry += "WHERE user.first_name like '%"+search+"%' OR user.last_name like '%"+search+"%' ";
            }
            if(sortBy != null && !sortBy.equals("")){
                if(sortBy.equals("matched_1")){
                    qry += " ORDER BY mentee.mentor DESC ";
                } else if(sortBy.equals("matched_0")){
                    qry += " ORDER BY mentee.mentor ASC ";
                }else{
                    qry += " ORDER BY user." + sortBy + " ";
                }
            }

            qry += " LIMIT ?,?";

            tmpQry = qry;

            PreparedStatement statement = this.con.prepareStatement(qry);
            statement.setInt(1, first);
            statement.setInt(2, total);

            ResultSet results = statement.executeQuery();


            while (results.next()) {
                Mentee mentee = new Mentee();
                mentee.setEmail(results.getString("email"));
                mentee.setPassword(results.getString("password"));
                mentee.setFirstName(results.getString("first_name"));
                mentee.setLastName(results.getString("last_name"));
                mentee.setStudentId(results.getString("student_id"));
                mentee.setMajor(results.getString("name"));
                mentee.setGrade(results.getString("grade"));
                mentee.setYear(results.getString("year"));
                mentee.setHobbies(results.getString("hobbies"));
                mentee.setCtHometown(results.getString("ct_hometown"));
                mentee.setOtherHometown(results.getString("other_hometown"));
                mentee.setParentEducation(results.getBoolean("parent_education"));
                mentee.setLanguage(results.getString("language"));
                mentee.setRace(results.getString("race"));
                mentee.setGender(results.getString("gender"));
                mentee.setLookingForward(results.getString("looking_forward"));
                mentee.setWhyMentor(results.getString("why_mentor"));
                mentee.setMentor(getMentor(results.getString("mentor")));

                mentees.add(mentee);
            }

        } catch (Exception e) {
            System.out.println("Could not get the mentors"+tmpQry);
        }

        return mentees;
    }






    public List<Mentee> getAllMenteesByRegisteredYear(String year) {
        List<Mentee> mentees = new ArrayList<>();
        try {
            String qry="";
            if(year.equals("Overall")){
                qry = "SELECT * FROM user NATURAL JOIN mentee JOIN major ON user.major = major.id" +
                        " order by user.last_name ASC";

            }
            else{
                qry = "SELECT * FROM user NATURAL JOIN mentee JOIN major ON user.major = major.id " +
                        "where user.year_registered="+"'"+year+"' order by user.last_name ASC";

            }


            PreparedStatement statement = this.con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Mentee mentee = new Mentee();
                mentee.setEmail(results.getString("email"));
                mentee.setPassword(results.getString("password"));
                mentee.setFirstName(results.getString("first_name"));
                mentee.setLastName(results.getString("last_name"));
                mentee.setStudentId(results.getString("student_id"));
                mentee.setMajor(results.getString("name"));
                mentee.setGrade(results.getString("grade"));
                mentee.setYear(results.getString("year"));
                mentee.setHobbies(results.getString("hobbies"));
                mentee.setCtHometown(results.getString("ct_hometown"));
                mentee.setOtherHometown(results.getString("other_hometown"));
                mentee.setParentEducation(results.getBoolean("parent_education"));
                mentee.setLanguage(results.getString("language"));
                mentee.setRace(results.getString("race"));
                mentee.setGender(results.getString("gender"));
                mentee.setLookingForward(results.getString("looking_forward"));
                mentee.setWhyMentor(results.getString("why_mentor"));
                mentee.setMentor(getMentor(results.getString("mentor")));

                mentees.add(mentee);
            }

        } catch (Exception e) {
            System.out.println("Could not get the mentors"+ e);
        }

        return mentees;
    }






    public List<Mentee> getAllMentees() {
        List<Mentee> mentees = new ArrayList<>();
        try {
            //String qry = "SELECT * FROM user NATURAL JOIN mentee JOIN major ON user.major = major.id ";
            String qry = "SELECT * FROM user NATURAL JOIN mentee JOIN major ON user.major = major.id order by user.last_name ASC";

            PreparedStatement statement = this.con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Mentee mentee = new Mentee();
                mentee.setEmail(results.getString("email"));
                mentee.setPassword(results.getString("password"));
                mentee.setFirstName(results.getString("first_name"));
                mentee.setLastName(results.getString("last_name"));
                mentee.setStudentId(results.getString("student_id"));
                mentee.setMajor(results.getString("name"));
                mentee.setGrade(results.getString("grade"));
                mentee.setYear(results.getString("year"));
                mentee.setHobbies(results.getString("hobbies"));
                mentee.setCtHometown(results.getString("ct_hometown"));
                mentee.setOtherHometown(results.getString("other_hometown"));
                mentee.setParentEducation(results.getBoolean("parent_education"));
                mentee.setLanguage(results.getString("language"));
                mentee.setRace(results.getString("race"));
                mentee.setGender(results.getString("gender"));
                mentee.setLookingForward(results.getString("looking_forward"));
                mentee.setWhyMentor(results.getString("why_mentor"));
                mentee.setMentor(getMentor(results.getString("mentor")));

                mentees.add(mentee);
            }

        } catch (Exception e) {
            System.out.println("Could not get the mentors"+ e);
        }

        return mentees;
    }


    /**
     * Return all mentees who don't have a mentor.
     * @return a list of mentees.
     */
    public List<Mentee> getOrphanMentees() {
        List<Mentee> mentees = new ArrayList<>();

        try {
            String qry = "SELECT *, town.name AS town_name, major.name AS major_name\n" +
                    "FROM user NATURAL JOIN mentee JOIN town ON (user.ct_hometown = town.id) JOIN major ON (user.major = major.id)\n" +
                    "WHERE mentee.mentor IS NULL";
            PreparedStatement statement = con.prepareStatement(qry);

            ResultSet results = statement.executeQuery();


            while (results.next()) {
                Mentee mentee = new Mentee();
                mentee.setEmail(results.getString("email"));
                mentee.setPassword(results.getString("password"));
                mentee.setFirstName(results.getString("first_name"));
                mentee.setLastName(results.getString("last_name"));
                mentee.setStudentId(results.getString("student_id"));
                mentee.setMajor(results.getString("major_name"));
                mentee.setGrade(results.getString("grade"));
                mentee.setYear(results.getString("year"));
                mentee.setHobbies(results.getString("hobbies"));
                mentee.setCtHometown(results.getString("town_name"));
                mentee.setOtherHometown(results.getString("other_hometown"));
                mentee.setParentEducation(results.getBoolean("parent_education"));
                mentee.setLanguage(results.getString("language"));
                mentee.setRace(results.getString("race"));
                mentee.setGender(results.getString("gender"));
                mentee.setLookingForward(results.getString("looking_forward"));
                mentee.setWhyMentor(results.getString("why_mentor"));
                mentee.setMentor(getMentor(results.getString("mentor")));



                mentees.add(mentee);

            }

        } catch (Exception e) {
            System.out.println("Could not get the orphan mentees. Poor bastards.");
        }

        return mentees;
    }


    /**
     * Return a list of mentees associated with a specific mentor, identified by mentor id
     * @param mentorEmail the email ID of the mentor.
     * @return the mentor's IDs.
     */
    public List<Mentee> getMyMentees(String mentorEmail) {
        List<Mentee> mentees = new ArrayList<>();
        try {
            String qry = "SELECT *, major.name as major_name\n" +
                    "FROM user NATURAL JOIN mentee JOIN major ON (user.major=major.id)\n" +
                    "WHERE mentee.mentor = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, mentorEmail);

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Mentee mentee = new Mentee();
                mentee.setEmail(results.getString("email"));
                mentee.setPassword(results.getString("password"));
                mentee.setFirstName(results.getString("first_name"));
                mentee.setLastName(results.getString("last_name"));
                mentee.setStudentId(results.getString("student_id"));
                mentee.setMajor(results.getString("major_name"));
                mentee.setGrade(results.getString("grade"));
                mentee.setYear(results.getString("year"));
                mentee.setHobbies(results.getString("hobbies"));
                mentee.setCtHometown(results.getString("ct_hometown"));
                mentee.setOtherHometown(results.getString("other_hometown"));
                mentee.setParentEducation(results.getBoolean("parent_education"));
                mentee.setLanguage(results.getString("language"));
                mentee.setRace(results.getString("race"));
                mentee.setGender(results.getString("gender"));
                mentee.setLookingForward(results.getString("looking_forward"));
                mentee.setWhyMentor(results.getString("why_mentor"));
                mentee.setMentor(getMentor(results.getString("mentor")));

                mentees.add(mentee);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong getting all the mentees for a given mentor" + e);
        }
        return mentees;
    }

    /**
     * Returns a list of Mentors which fits to the conditions
     * @param first the first Mentor you'd like to return
     * @param total the total number of records per page
     * @return the list of Mentors to be found
     * @throws Exception
     */
    public List<Mentor> getMentorsWithMenteeCount(int first, int total,String findName,String sortBy) {
        List<Mentor> mentors = new ArrayList<>();
        Map<Integer, String> majors = getAllMajors();
        String tmpQry = "";

        try {
            String qry = "SELECT COUNT(mentee.email) as mentee_count, user.*, mentor.*, major.*, town.name AS town_name ";
            qry += "FROM user NATURAL JOIN mentor LEFT JOIN mentee ON user.email = mentee.mentor JOIN major ON (user.major=major.id) JOIN town ON (user.ct_hometown = town.id) ";
            if(findName != null){
                qry += "WHERE user.first_name like '%"+findName+"%' OR user.last_name like '%"+findName+"%' ";
            }
            qry += "GROUP BY mentor.email ";
            if(sortBy != null && !sortBy.equals("")){
                if(sortBy.equals("mentee_cnt_desc")){
                    qry += " ORDER BY mentee_count DESC ";
                } else if(sortBy.equals("mentee_cnt_asc")) {
                    qry += " ORDER BY mentee_count ASC ";
                } else if(sortBy.equals("is_approved_1")){
                    qry += " ORDER BY mentor.is_approved DESC";
                } else if(sortBy.equals("is_approved_0")){
                    qry += " ORDER BY mentor.is_approved ASC";
                }else{
                    qry += " ORDER BY user." + sortBy + "";
                }
            }
            qry += " LIMIT ?,? ";

            tmpQry = qry;
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, first);
            statement.setInt(2, total);

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Mentor mentor = new Mentor();
                mentor.setEmail(results.getString("email"));
                mentor.setPassword(results.getString("password"));
                mentor.setFirstName(results.getString("first_name"));
                mentor.setLastName(results.getString("last_name"));
                mentor.setStudentId(results.getString("student_id"));
                mentor.setMajor(majors.get(Integer.parseInt(results.getString("major"))));
                mentor.setGrade(results.getString("grade"));
                mentor.setYear(results.getString("year"));
                mentor.setHobbies(results.getString("hobbies"));
                mentor.setCtHometown(results.getString("town_name"));
                mentor.setOtherHometown(results.getString("other_hometown"));
                mentor.setParentEducation(results.getBoolean("parent_education"));
                mentor.setLanguage(results.getString("language"));
                mentor.setRace(results.getString("race"));
                mentor.setGender(results.getString("gender"));
                mentor.setMentorReq(results.getString("mentor_req"));
                mentor.setAttitudeForDifference(results.getString("attitude_for_diff"));
                mentor.setChallenge(results.getString("challenge"));
                mentor.setForSuccessfulFirstYear(results.getString("for_successful_1st_year"));
                mentor.setApproved(results.getBoolean("is_approved"));
                mentor.setSeniorMentor(results.getBoolean("is_senior_mentor"));
                mentor.setMenteeCount(results.getInt("mentee_count"));

                mentors.add(mentor);
            }

        } catch (Exception e) {
            System.out.println("Could not get the mentors with mentee count. " + tmpQry);
        }

        return mentors;
    }

    /**
     * Returns a list of Mentors which fits to the conditions
     * @param first the first Mentor you'd like to return
     * @param total the total number of records per page
     * @return the list of Mentors to be found
     * @throws Exception
     */
    public List<Mentor> getMatchedMentorsWithMenteeCount(int first, int total,String findName,String sortBy) {
        List<Mentor> mentors = new ArrayList<>();
        Map<Integer, String> majors = getAllMajors();
        String tmpQry = "";

        try {
            String qry = "SELECT COUNT(mentee.email) as mentee_count, user.*, mentor.*, major.*, town.name AS town_name ";
            qry += "FROM user NATURAL JOIN mentor LEFT JOIN mentee ON user.email = mentee.mentor JOIN major ON (user.major=major.id) JOIN town ON (user.ct_hometown = town.id) ";
            qry += "GROUP BY mentor.email ";
            qry += "HAVING mentee_count>0 ";
            if(sortBy != null && !sortBy.equals("")){
                if(sortBy.equals("mentee_cnt_desc")){
                    qry += " ORDER BY mentee_count DESC ";
                } else if(sortBy.equals("mentee_cnt_asc")) {
                    qry += " ORDER BY mentee_count ASC ";
                } else if(sortBy.equals("is_approved_1")){
                    qry += " ORDER BY mentor.is_approved DESC";
                } else if(sortBy.equals("is_approved_0")){
                    qry += " ORDER BY mentor.is_approved ASC";
                }else{
                    qry += " ORDER BY user." + sortBy + "";
                }
            }
            qry += " LIMIT ?,? ";

            tmpQry = qry;
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, first);
            statement.setInt(2, total);

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Mentor mentor = new Mentor();
                mentor.setEmail(results.getString("email"));
                mentor.setPassword(results.getString("password"));
                mentor.setFirstName(results.getString("first_name"));
                mentor.setLastName(results.getString("last_name"));
                mentor.setStudentId(results.getString("student_id"));
                mentor.setMajor(majors.get(Integer.parseInt(results.getString("major"))));
                mentor.setGrade(results.getString("grade"));
                mentor.setYear(results.getString("year"));
                mentor.setHobbies(results.getString("hobbies"));
                mentor.setCtHometown(results.getString("town_name"));
                mentor.setOtherHometown(results.getString("other_hometown"));
                mentor.setParentEducation(results.getBoolean("parent_education"));
                mentor.setLanguage(results.getString("language"));
                mentor.setRace(results.getString("race"));
                mentor.setGender(results.getString("gender"));
                mentor.setMentorReq(results.getString("mentor_req"));
                mentor.setAttitudeForDifference(results.getString("attitude_for_diff"));
                mentor.setChallenge(results.getString("challenge"));
                mentor.setForSuccessfulFirstYear(results.getString("for_successful_1st_year"));
                mentor.setApproved(results.getBoolean("is_approved"));
                mentor.setSeniorMentor(results.getBoolean("is_senior_mentor"));
                mentor.setMenteeCount(results.getInt("mentee_count"));

                mentors.add(mentor);
            }

        } catch (Exception e) {
            System.out.println("Could not get the mentors with mentee count. " + tmpQry);
        }

        return mentors;
    }



    /**
     * Returns a list of Approvede Mentors
     * @param first the first Mentor you'd like to return
     * @param total the total number of records per page
     * @return the list of Mentors to be found
     * @throws Exception
     */
    public List<Mentor> getApprovedMentorsWithMenteeCount(int first, int total) {
        List<Mentor> mentors = new ArrayList<>();
        Map<Integer, String> majors = getAllMajors();

        try {
            String qry = "SELECT COUNT(mentee.email) as mentee_count, user.*, mentor.*, major.*, town.name AS town_name ";
            qry += "FROM user NATURAL JOIN mentor LEFT JOIN mentee ON user.email = mentee.mentor JOIN major ON (user.major=major.id) JOIN town ON (user.ct_hometown = town.id) ";
            qry += "WHERE is_approved=1 GROUP BY mentor.email LIMIT ?,?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, first);
            statement.setInt(2, total);

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Mentor mentor = new Mentor();
                mentor.setEmail(results.getString("email"));
                mentor.setPassword(results.getString("password"));
                mentor.setFirstName(results.getString("first_name"));
                mentor.setLastName(results.getString("last_name"));
                mentor.setStudentId(results.getString("student_id"));
                mentor.setMajor(majors.get(Integer.parseInt(results.getString("major"))));
                mentor.setGrade(results.getString("grade"));
                mentor.setYear(results.getString("year"));
                mentor.setHobbies(results.getString("hobbies"));
                mentor.setCtHometown(results.getString("town_name"));
                mentor.setOtherHometown(results.getString("other_hometown"));
                mentor.setParentEducation(results.getBoolean("parent_education"));
                mentor.setLanguage(results.getString("language"));
                mentor.setRace(results.getString("race"));
                mentor.setGender(results.getString("gender"));
                mentor.setMentorReq(results.getString("mentor_req"));
                mentor.setAttitudeForDifference(results.getString("attitude_for_diff"));
                mentor.setChallenge(results.getString("challenge"));
                mentor.setForSuccessfulFirstYear(results.getString("for_successful_1st_year"));
                mentor.setApproved(results.getBoolean("is_approved"));
                mentor.setSeniorMentor(results.getBoolean("is_senior_mentor"));
                mentor.setMenteeCount(results.getInt("mentee_count"));
                mentor.setMatchingStatus(results.getBoolean("matching_status"));

                mentors.add(mentor);
            }

        } catch (Exception e) {
            System.out.println("Could not get the mentors with mentee count. " +e);
        }

        return mentors;
    }


    /**
     * Get all the mentors in the program
     * @return a list of mentors.
     */
    public List<Mentor> getAllMentors() {
        int mentorCount = countMentors();
        return getMentorsWithMenteeCount(0, mentorCount, "","");
    }

    /**
     * Get all the mentors in the program
     * @return a list of mentors.
     */
    public List<Mentor> getApprovedMentors() {
        int mentorCount = countApprovedMentors();
        return getApprovedMentorsWithMenteeCount(0, mentorCount);
    }

    /**
     * Get a map of all majors and their corresponding IDs.
     * @return a map like "Accounting" -> 1, "Anthropology" -> 2
     */
    public Map<Integer, String> getAllMajors() {
        Map<Integer, String> map = new HashMap<>();

        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM major");
            System.out.println("Prepared Statement worked!");
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                map.put(results.getInt("id"), results.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Could not get all the majors.");
        }
        return map;
    }


    /**
     * Get a map of all Connecticut towns and their corresponding IDs.
     * @return a map of all Connecticut towns.
     */
    public Map<Integer, String> getAllConnecticutTowns() {
        Map<Integer, String> map = new HashMap<>();

        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM town");
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                map.put(results.getInt("id"), results.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Could not get all the majors.");
        }
        return map;
    }

    /**
     * Checks if user is an admin in the database
     * @param email of user
     * @return confirmation if user is admin
     */
    public boolean isAdmin(String email){
        try {
            if (email == null) {
                throw new Exception();
            }
            String qry = "SELECT is_admin FROM user WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, email);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                return results.getInt("is_admin") == 1;
            }
        } catch (Exception e) {
            System.out.println("Could not get admin status of the Success Central user.");
        }
        return false;
    }





    // -------- UPDATE ---------

    // Update a user in the db.
    public void updateUser(User user) {
        try {
            String qry = "UPDATE user SET password = ?, first_name = ?, last_name = ?, ";
            qry += "student_id = ?, major = ?, grade = ?, year = ?, hobbies = ?, ct_hometown = ?, other_hometown = ?, ";
            qry += "parent_education = ?, language = ?, race = ?, gender = ? ";
            qry += "WHERE email = ?";

            PreparedStatement statement = con.prepareStatement(qry);

            if (user.getEmail() == null) {
                throw new Exception();
            }

            statement.setString(1, user.getPassword());

            statement.setString(2, user.getFirstName());

            statement.setString(3, user.getLastName());

            statement.setString(4, user.getStudentId());

            statement.setObject(5, user.getMajor());

            statement.setObject(6, user.getGrade());

            statement.setObject(7, user.getYear());

            statement.setString(8, user.getHobbies());

            statement.setObject(9, user.getCtHometown());

            statement.setString(10, user.getOtherHometown());

            statement.setObject(11, user.getParentEducation());

            statement.setString(12, user.getLanguage());

            statement.setString(13, user.getRace());

            statement.setString(14, user.getGender());

            statement.setString(15, user.getEmail());


            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Could not update the entire user" + e);
        }
    }

    // Update a user in the db.
    public void updateUserPassword(User user) {
        try {
            String qry = "UPDATE user SET password = ? WHERE email = ?";

            PreparedStatement statement = con.prepareStatement(qry);

            if (user.getEmail() == null) {
                throw new Exception();
            }

            if (user.getPassword() != null) {
                statement.setString(1, user.getPassword());
            }
            statement.setString(2, user.getEmail());


            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Could not update the user password "+e);
        }
    }

    // Update a mentee in the db.
    public void updateMentee(Mentee mentee) {

        try {
            String qry = "UPDATE mentee SET looking_forward = ?, why_mentor = ?, mentor = ? WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, mentee.getLookingForward());
            statement.setString(2, mentee.getWhyMentor());
            statement.setString(3, mentee.getMentor() == null ? null : mentee.getMentor().getEmail());
            statement.setString(4, mentee.getEmail());
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Could not update the mentee");
        }
    }

    //Send Matching notice to mentor and his/her mentees
    // Update a mentee in the db.
    public void noticeMatched(String mentorEmail) {
        try {
            EmailService email = new EmailService();
            Mentor mentor = getMentor(mentorEmail);
            Map<Integer, String> majors = getAllMajors();

            email.matchNoticeToMentor(mentor,getMyMentees(mentorEmail),majors);

            for(int i=0;i<getMyMentees(mentorEmail).size();i++) {

                email.matchNoticeToMentee(getMyMentees(mentorEmail).get(i), mentor);
            }

        } catch (Exception e) {
            System.out.println("Could not send notices" + e);
        }
    }

    // Update a mentee in the db.
    public void updateMenteeWithMentor(String menteeEmail, String mentorEmail) {
        System.out.println("Trying to update mentee with mentor");
        System.out.println(menteeEmail);
        System.out.println(mentorEmail);
        try {

            String qry = "UPDATE mentee SET mentor = ? WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, mentorEmail);
            statement.setString(2, menteeEmail);
            statement.executeUpdate();
            System.out.println("Successful Update");

        } catch (Exception e) {
            System.out.println("Could not update the mentee");
        }
    }

    // Update a mentor in the db.
    public void updateMentor(Mentor mentor) {
        try {
            String qry = "UPDATE mentor SET mentor_req = ?, attitude_for_diff = ?, challenge = ?, for_successful_1st_year = ?, ";
            qry += "is_approved = ?, is_senior_mentor = ? WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, mentor.getMentorReq());
            statement.setString(2, mentor.getAttitudeForDifference());
            statement.setString(3, mentor.getChallenge());
            statement.setString(4, mentor.getForSuccessfulFirstYear());
            statement.setObject(5, mentor.getApproved() ? 1 : 0);
            statement.setObject(6, mentor.getSeniorMentor() ? 1 : 0);

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Could not update the mentor");
        }
    }

    // Update a mentor in the db.
    public void updateMentorLevel(Mentor mentor) {
        try {
            String qry = "UPDATE mentor SET is_senior_mentor=? WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            //            statement.setInt(5, mentor.getApproved() ? 1 : 0);
            statement.setInt(1, mentor.getSeniorMentor() ? 0 : 1);
            statement.setString(2, mentor.getEmail());
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Could not update the mentor");
        }
    }
    //update mentor matching status in the db
    public void updateMentorMatchingStatus(Mentor mentor) {
        try {
            String qry = "UPDATE mentor SET matching_status=? WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            //            statement.setInt(5, mentor.getApproved() ? 1 : 0);
            statement.setInt(1, mentor.getMatchingStatus() ? 0 : 1);
            statement.setString(2, mentor.getEmail());
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Could not update the mentor");
        }
    }

    //------ ACCEPT AS A MENTOR ------
    // Accept as a mentor (
    public void updateMentorApproval(Mentor mentor){
        try {
            String qry = "UPDATE mentor SET is_approved=? WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, mentor.getApproved() ? 0 : 1);
            statement.setString(2, mentor.getEmail());
            statement.executeUpdate();

            // emailing part
            EmailService email = new EmailService();
            email.sendEmailToApproved(mentor.getEmail());

        } catch (Exception e) {
            System.out.println("Could not update the mentor"+e);
        }
    }




    // ---------- DELETE ----------

    // -------- DELETE ---------
    // Delete a mentee in the db.
    public void deleteMentee(String email) {

        try {
            String qry1 = "DELETE FROM mentee WHERE email=?";
            PreparedStatement statement1 = con.prepareStatement(qry1);
//            statement.setString(1, mentee.getEmail());
            statement1.setString(1, email);
            statement1.executeUpdate();

            String qry2 = "DELETE FROM user WHERE email=?";
            PreparedStatement statement2 = con.prepareStatement(qry2);
            statement2.setString(1, email);
            statement2.executeUpdate();

        } catch (Exception e) {
            System.out.println("Could not delete the mentee");
        }
    }

    // -------- DELETE ---------
    // Delete a mentor in the db.
    // Update the mentee to have null
    public void deleteMentor(Mentor mentor) {

        try {
            String qry1 = "UPDATE mentee set mentor=null WHERE mentor=?";
            PreparedStatement statement1 = con.prepareStatement(qry1);
            statement1.setString(1,mentor.getEmail());
            statement1.executeUpdate();

            String qry2 = "DELETE FROM mentor WHERE email=?";
            PreparedStatement statement2 = con.prepareStatement(qry2);
            statement2.setString(1, mentor.getEmail());
            statement2.executeUpdate();

            String qry3 = "DELETE FROM user WHERE email=?";
            PreparedStatement statement3 = con.prepareStatement(qry3);
            statement3.setString(1, mentor.getEmail());
            statement3.executeUpdate();

        } catch (Exception e) {
            System.out.println("Could not delete the mentor");
        }
    }






    // -------- OTHER ----------

    /**
     * Return the total number of mentors.
     * @return total number of mentors.
     */
    public int countMentors() {
        int mentorCount = 0;
        try {
            String qry = "SELECT COUNT(*) FROM mentor";
            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();
            if (results.next()) { // need to do this to get first result
                mentorCount = results.getInt("COUNT(*)");
            }
        } catch (Exception e) {
            System.out.println("There was a problem counting the mentors");
        }
        return mentorCount;
    }
    /**
     * Return the total number of mentors.
     * @return total number of mentors.
     */
    public int countMatchedMentors() {
        int mentorCount = 0;
        try {
            String qry = "SELECT count(DISTINCT(mentor)) FROM mentee";
            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();
            if (results.next()) { // need to do this to get first result
                mentorCount = results.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("There was a problem counting the mentors");
        }
        return mentorCount;
    }


    /**
     * Return the total number of mentors.
     * @return total number of mentors.
     */
    public int countApprovedMentors() {
        int mentorCount = 0;
        try {
            String qry = "SELECT COUNT(*) FROM mentor WHERE is_approved=1";
            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();
            if (results.next()) { // need to do this to get first result
                mentorCount = results.getInt("COUNT(*)");
            }
        } catch (Exception e) {
            System.out.println("There was a problem counting the mentors");
        }
        return mentorCount;
    }

    /**
     * Return the total number of mentees.
     * @return total number of mentees.
     */
    public int countMentees() {
        int menteeCount = 0;
        try {
            String qry = "SELECT COUNT(*) FROM mentee";
            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();
            if (results.next()) { // need to do this to get first result
                menteeCount = results.getInt("COUNT(*)");
            }
        } catch (Exception e) {
            System.out.println("There was a problem counting the mentees");
        }
        return menteeCount;
    }

    /**
     * Return the user associated with a forgot password token.
     * @param token
     * @return the user associated with that token.
     */
    public String getUserEmailFromForgotPasswordToken(String token) {
        String userEmail = null;
        try {
            String qry = "SELECT email FROM password_reset WHERE token = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, token);
            ResultSet results = statement.executeQuery();
            if (results.next()) { // need to do this to get first result
                userEmail = results.getString("email");
            }
        } catch (Exception e) {
            System.out.println("There was a problem retrieving the user's email with the forgotten password");
        }
        return userEmail;
    }

    /**
     * Delete a request to change a password.
     * @param token the identifier of the password change request to be deleted.
     */
    public void deleteChangePasswordRequest(String token) {
        try {
            String qry = "DELETE FROM password_reset WHERE token = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, token);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("There was a problem deleting the expired password change request.");
        }
    }

    /**
     * Delete a request to change a password.
     * @param email the user's email
     */
    public void deleteChangePasswordRequestForUser(String email) {
        try {
            String qry = "DELETE FROM password_reset WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("There was a problem deleting the users password change requests.");
        }
    }

    /**
     * Create a change password record
     * @param email the email of the user.
     * @param token the unique identifier to pass in as a url parameter.
     */
    public void createPasswordReset(String email, String token) {
        try {
            String qry = "INSERT INTO password_reset VALUES (?,?)";
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setString(1, email);
            statement.setString(2, token);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("There was a problem creating the password reset");
        }
    }

    public int countSessionForms() {
        int sessionCount = 0;
        try {
            String qry = "SELECT COUNT(*) FROM session_form";
            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                sessionCount = results.getInt("COUNT(*)");
            }
        } catch (Exception e) {
            System.out.println("There was a problem counting the session forms");
        }

        return sessionCount;


    }



























    public JSONObject sessionTableMentee(String firstName, String lastName,String sessionNum1, String sessionNum2){


        JSONObject finalJObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            //String qry = "select * from user join mentee on user.email = mentee.email";
            //String qry = "select * from session_form where last_name = 'Mentee' and first_name = 'Geovanni Roberts' and (session_number = '1' or session_number = '2')";
            //String qry = "select * from session_form where last_name = " + "\'"+lastName+"\'" + " and " + "first_name = " + "\'" + firstName+ "\'"
            //        + " and (session_number = " + "\'"+sessionNum1+"\'" + " or session_number = " + sessionNum2 + ")";
            //String qry = "select * from session_form where last_name ="+   "\'Mentee\'";


            String qry = "select * from session_form where last_name = 'Mentee' and first_name = 'Geovanni Roberts' and (session_number = '1' or session_number = '2')";
            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();

            JSONArray jArray = new JSONArray();
            while (results.next()) {


                String first_name_json=results.getString("first_name");
                String last_name_json=results.getString("last_name");
                String session_number_json=results.getString("session_number");
                String day_json=results.getString("day");
                String pre_action_one_json=results.getString("pre_action_one");
                String bool_action_one_json=results.getString("bool_action_one");
                String pre_action_two_json=results.getString("pre_action_two");
                String bool_action_two_json=results.getString("bool_action_two");
                String pre_action_three_json=results.getString("pre_action_three");
                String pre_action_four_json=results.getString("pre_action_four");
                String bool_action_three_json=results.getString("bool_action_three");
                String bool_action_four_json=results.getString("bool_action_four");
                String pre_action_five_json=results.getString("pre_action_five");
                String bool_action_five_json=results.getString("bool_action_five");
                String pre_action_six_json=results.getString("pre_action_six");
                String bool_action_six_json=results.getString("bool_action_six");
                String scale_json=results.getString("scale");
                String campus_involvement_json=results.getString("campus_involvement");
                String meaningful_relationships_json=results.getString("meaningful_relationships");
                String financial_management_json=results.getString("financial_management");
                String outside_responsibilities_json=results.getString("outside_responsibilities");
                String study_time_management_json=results.getString("study_time_management");
                String academic_engagement_json=results.getString("academic_engagement");
                String health_wellness_json=results.getString("health_wellness");
                String other_bool_json=results.getString("other_bool");
                String other_text_json=results.getString("other_text");
                String issues_concerns_json=results.getString("issues_concerns");
                String notes_comments_json=results.getString("notes_comments");
                String action_one_json=results.getString("action_one");
                String action_two_json=results.getString("action_two");
                String action_three_json=results.getString("action_three");
                String action_four_json=results.getString("action_four");
                String action_five_json=results.getString("action_five");
                String action_six_json=results.getString("action_six");



                JSONObject jobj = new JSONObject();

                jobj.put("first_name",first_name_json);
                jobj.put("last_name",last_name_json);
                jobj.put("session_number",session_number_json);
                jobj.put("day",day_json);
                jobj.put("pre_action_one",pre_action_one_json);
                jobj.put("bool_action_one",bool_action_one_json);
                jobj.put("pre_action_two",pre_action_two_json);
                jobj.put("bool_action_two",bool_action_two_json);
                jobj.put("pre_action_three",pre_action_three_json);
                jobj.put("bool_action_three",bool_action_three_json);
                jobj.put("pre_action_four",pre_action_four_json);
                jobj.put("bool_action_four",bool_action_four_json);
                jobj.put("pre_action_five",pre_action_five_json);
                jobj.put("bool_action_five",bool_action_five_json);
                jobj.put("pre_action_six",pre_action_six_json);
                jobj.put("bool_action_six",bool_action_six_json);
                jobj.put("scale",scale_json);
                jobj.put("campus_involvement",campus_involvement_json);
                jobj.put("meaningful_relationships",meaningful_relationships_json);
                jobj.put("financial_management",financial_management_json);
                jobj.put("outside_responsibilities",outside_responsibilities_json);
                jobj.put("study_time_management",study_time_management_json);
                jobj.put("academic_engagement",academic_engagement_json);
                jobj.put("health_wellness",health_wellness_json);
                jobj.put("other_bool",other_bool_json);
                jobj.put("other_text",other_text_json);
                jobj.put("issues_concerns",issues_concerns_json);
                jobj.put("notes_comments",notes_comments_json);
                jobj.put("action_one",action_one_json);
                jobj.put("action_two",action_two_json);
                jobj.put("action_three",action_three_json);
                jobj.put("action_four",action_four_json);
                jobj.put("action_five",action_five_json);
                jobj.put("action_six",action_six_json);


                jArray.put(jobj);

            }

            finalJObject.put("data", jArray);

        } catch (Exception e) {
            System.out.println("There was a problem querying session_form table in database ");
        }
        return finalJObject;

    }



    public JSONObject sessionFormTable(String mentee){


        JSONObject finalJObject = new JSONObject();
        try {


            String qry = "select * from session_form where full_name = '" +mentee+ "' and " +
                    "day= (select max(day) from session_form where full_name = '" +mentee+ "' ) ";




            //String qry = "select * from session_form where full_name = '" +mentee+"'";
            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet rs2 = statement.executeQuery();

            JSONArray jArray = new JSONArray();
            while (rs2.next()) {

                String first_name_json=rs2.getString("mentor");
                String last_name_json=rs2.getString("full_name");
                String session_number_json=rs2.getString("session_number");
                String day_json=rs2.getString("day");
                String pre_action_one_json=rs2.getString("pre_action_one");
                String bool_action_one_json=rs2.getString("bool_action_one");
                String pre_action_two_json=rs2.getString("pre_action_two");
                String bool_action_two_json=rs2.getString("bool_action_two");
                String pre_action_three_json=rs2.getString("pre_action_three");
                String pre_action_four_json=rs2.getString("pre_action_four");
                String bool_action_three_json=rs2.getString("bool_action_three");
                String bool_action_four_json=rs2.getString("bool_action_four");
                String pre_action_five_json=rs2.getString("pre_action_five");
                String bool_action_five_json=rs2.getString("bool_action_five");
                String pre_action_six_json=rs2.getString("pre_action_six");
                String bool_action_six_json=rs2.getString("bool_action_six");
                String scale_json=rs2.getString("scale");
                String campus_involvement_json=rs2.getString("campus_involvement");
                String meaningful_relationships_json=rs2.getString("meaningful_relationships");
                String financial_management_json=rs2.getString("financial_management");
                String outside_responsibilities_json=rs2.getString("outside_responsibilities");
                String study_time_management_json=rs2.getString("study_time_management");
                String academic_engagement_json=rs2.getString("academic_engagement");
                String health_wellness_json=rs2.getString("health_wellness");
                String other_bool_json=rs2.getString("other_bool");
                String other_text_json=rs2.getString("other_text");
                String issues_concerns_json=rs2.getString("issues_concerns");
                String notes_comments_json=rs2.getString("notes_comments");
                String action_one_json=rs2.getString("action_one");
                String action_two_json=rs2.getString("action_two");
                String action_three_json=rs2.getString("action_three");
                String action_four_json=rs2.getString("action_four");
                String action_five_json=rs2.getString("action_five");
                String action_six_json=rs2.getString("action_six");



                JSONObject jobj = new JSONObject();

                jobj.put("mentor",first_name_json);
                jobj.put("full_name",last_name_json);
                jobj.put("session_number",session_number_json);
                jobj.put("day",day_json);
                jobj.put("pre_action_one",pre_action_one_json);
                jobj.put("bool_action_one",bool_action_one_json);
                jobj.put("pre_action_two",pre_action_two_json);
                jobj.put("bool_action_two",bool_action_two_json);
                jobj.put("pre_action_three",pre_action_three_json);
                jobj.put("bool_action_three",bool_action_three_json);
                jobj.put("pre_action_four",pre_action_four_json);
                jobj.put("bool_action_four",bool_action_four_json);
                jobj.put("pre_action_five",pre_action_five_json);
                jobj.put("bool_action_five",bool_action_five_json);
                jobj.put("pre_action_six",pre_action_six_json);
                jobj.put("bool_action_six",bool_action_six_json);
                jobj.put("scale",scale_json);
                jobj.put("campus_involvement",campus_involvement_json);
                jobj.put("meaningful_relationships",meaningful_relationships_json);
                jobj.put("financial_management",financial_management_json);
                jobj.put("outside_responsibilities",outside_responsibilities_json);
                jobj.put("study_time_management",study_time_management_json);
                jobj.put("academic_engagement",academic_engagement_json);
                jobj.put("health_wellness",health_wellness_json);
                jobj.put("other_bool",other_bool_json);
                jobj.put("other_text",other_text_json);
                jobj.put("issues_concerns",issues_concerns_json);
                jobj.put("notes_comments",notes_comments_json);
                jobj.put("action_one",action_one_json);
                jobj.put("action_two",action_two_json);
                jobj.put("action_three",action_three_json);
                jobj.put("action_four",action_four_json);
                jobj.put("action_five",action_five_json);
                jobj.put("action_six",action_six_json);

                jArray.put(jobj);

            }

            finalJObject.put("data", jArray);

        } catch (Exception e) {
            System.out.println("There was a problem querying user table in database ");
        }
        return finalJObject;

    }










    public JSONObject userTableMentee(String year){


        JSONObject finalJObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            String qry = "";
            if ( year.equals("Overall")){
                qry = "select * from user join mentee on user.email = mentee.email";

            }
            else{
                qry = "select * from user join mentee on user.email = mentee.email  " +
                        " where year_registered ='" + year+"'";


            }

            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();

            JSONArray jArray = new JSONArray();
            while (results.next()) {

                String  email_json=results.getString("email");
                String  password_json=results.getString("password");
                String  first_name_json=results.getString("first_name");
                String  last_name_json=results.getString("last_name");
                String  student_id_json=results.getString("student_id");
                String  major_json=results.getString("major");
                String  grade_json=results.getString("grade");
                String  year_json=results.getString("year");
                String  hobbies_json=results.getString("hobbies");
                String  ct_hometown_json=results.getString("ct_hometown");
                String  other_hometown_json=results.getString("other_hometown");
                String  parent_education_json=results.getString("parent_education");
                String  language_json=results.getString("language");
                String  race_json=results.getString("race");
                String  gender_json=results.getString("gender");
                String is_admin_json=results.getString("is_admin");



                JSONObject jobj = new JSONObject();

                jobj.put("email",email_json);
                jobj.put("password",password_json);
                jobj.put("first_name",first_name_json);
                jobj.put("last_name",last_name_json);
                jobj.put("student_id",student_id_json);
                jobj.put("major",major_json);
                jobj.put("grade",grade_json);
                jobj.put("year",year_json);
                jobj.put("hobbies",hobbies_json);
                jobj.put("ct_hometown",ct_hometown_json);
                jobj.put("other_hometown",other_hometown_json);
                jobj.put("parent_education",parent_education_json);
                jobj.put("language",language_json);
                jobj.put("race",race_json);
                jobj.put("gender",gender_json);
                jobj.put("is_admin",is_admin_json);

                jArray.put(jobj);

            }

            finalJObject.put("data", jArray);

        } catch (Exception e) {
            System.out.println("There was a problem querying user table in database ");
        }
        return finalJObject;

    }
























    public JSONObject userTableMentee(){


        JSONObject finalJObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            //String qry = "select * from user where is_admin = 0";
            String qry = "select * from user join mentee on user.email = mentee.email";
            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();

            JSONArray jArray = new JSONArray();
            while (results.next()) {

                String  email_json=results.getString("email");
                String  password_json=results.getString("password");
                String  first_name_json=results.getString("first_name");
                String  last_name_json=results.getString("last_name");
                String  student_id_json=results.getString("student_id");
                String  major_json=results.getString("major");
                String  grade_json=results.getString("grade");
                String  year_json=results.getString("year");
                String  hobbies_json=results.getString("hobbies");
                String  ct_hometown_json=results.getString("ct_hometown");
                String  other_hometown_json=results.getString("other_hometown");
                String  parent_education_json=results.getString("parent_education");
                String  language_json=results.getString("language");
                String  race_json=results.getString("race");
                String  gender_json=results.getString("gender");
                String is_admin_json=results.getString("is_admin");



                JSONObject jobj = new JSONObject();

                jobj.put("email",email_json);
                jobj.put("password",password_json);
                jobj.put("first_name",first_name_json);
                jobj.put("last_name",last_name_json);
                jobj.put("student_id",student_id_json);
                jobj.put("major",major_json);
                jobj.put("grade",grade_json);
                jobj.put("year",year_json);
                jobj.put("hobbies",hobbies_json);
                jobj.put("ct_hometown",ct_hometown_json);
                jobj.put("other_hometown",other_hometown_json);
                jobj.put("parent_education",parent_education_json);
                jobj.put("language",language_json);
                jobj.put("race",race_json);
                jobj.put("gender",gender_json);
                jobj.put("is_admin",is_admin_json);

                jArray.put(jobj);

            }

            finalJObject.put("data", jArray);

        } catch (Exception e) {
            System.out.println("There was a problem querying user table in database ");
        }
        return finalJObject;

    }




    public JSONObject userTableMentor(String year){


        JSONObject finalJObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            String qry = "";

            if ( year.equals("Overall")){
                qry = "select * from user join mentor on user.email = mentor.email ";
            }
            else {
                qry = "select * from user join mentor on user.email = mentor.email " +
                        " where year_registered ='" + year + "'";

            }



            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();

            JSONArray jArray = new JSONArray();
            while (results.next()) {


                String  email_json=results.getString("email");
                String  password_json=results.getString("password");
                String  first_name_json=results.getString("first_name");
                String  last_name_json=results.getString("last_name");
                String  student_id_json=results.getString("student_id");
                String  major_json=results.getString("major");
                String  grade_json=results.getString("grade");
                String  year_json=results.getString("year");
                String  hobbies_json=results.getString("hobbies");
                String  ct_hometown_json=results.getString("ct_hometown");
                String  other_hometown_json=results.getString("other_hometown");
                String  parent_education_json=results.getString("parent_education");
                String  language_json=results.getString("language");
                String  race_json=results.getString("race");
                String  gender_json=results.getString("gender");
                String is_admin_json=results.getString("is_admin");



                JSONObject jobj = new JSONObject();

                jobj.put("email",email_json);
                jobj.put("password",password_json);
                jobj.put("first_name",first_name_json);
                jobj.put("last_name",last_name_json);
                jobj.put("student_id",student_id_json);
                jobj.put("major",major_json);
                jobj.put("grade",grade_json);
                jobj.put("year",year_json);
                jobj.put("hobbies",hobbies_json);
                jobj.put("ct_hometown",ct_hometown_json);
                jobj.put("other_hometown",other_hometown_json);
                jobj.put("parent_education",parent_education_json);
                jobj.put("language",language_json);
                jobj.put("race",race_json);
                jobj.put("gender",gender_json);
                jobj.put("is_admin",is_admin_json);

                jArray.put(jobj);

            }

            finalJObject.put("data", jArray);

        } catch (Exception e) {
            System.out.println("There was a problem querying user table in database ");
        }

        return finalJObject;

    }







    public JSONObject data_MenteeOpenEndReport(){

        JSONObject finalJObject = new JSONObject();
        //JSONArray jsonArray = new JSONArray();

        try {
            //String qry = "select * from user where is_admin = 0";
            String qry = "select first_name,last_name,looking_forward,why_mentor from user join mentee on user.email = mentee.email";
            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();

            JSONArray jArray = new JSONArray();
            while (results.next()) {

                String  first_name_json=results.getString("first_name");
                String  last_name_json=results.getString("last_name");
                String  looking_forward_json=results.getString("looking_forward");
                String  why_mentor_json=results.getString("why_mentor");



                JSONObject jobj = new JSONObject();

                jobj.put("first_name",first_name_json);
                jobj.put("last_name",last_name_json);
                jobj.put("looking_forward",looking_forward_json);
                jobj.put("why_mentor",why_mentor_json);

                jArray.put(jobj);

            }

            finalJObject.put("data", jArray);

        } catch (Exception e) {
            System.out.println("There was a problem querying user table and mentee table in database ");
        }

        return finalJObject;

    }















    public JSONObject data_MentorOpenEndReport(){

        JSONObject finalJObject = new JSONObject();
        //JSONArray jsonArray = new JSONArray();

        try {
            //String qry = "select * from user where is_admin = 0";
            String qry = "select first_name,last_name,mentor_req,attitude_for_diff,challenge,for_successful_1st_year from user join mentor on user.email = mentor.email";
            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet results = statement.executeQuery();

            JSONArray jArray = new JSONArray();
            while (results.next()) {

                String  first_name_json=results.getString("first_name");
                String  last_name_json=results.getString("last_name");
                String  mentor_req_json=results.getString("mentor_req");
                String  attitude_for_diff_json=results.getString("attitude_for_diff");
                String  challenge_json=results.getString("challenge");
                String  for_successful_1st_year_json=results.getString("for_successful_1st_year");



                JSONObject jobj = new JSONObject();

                jobj.put("first_name",first_name_json);
                jobj.put("last_name",last_name_json);
                jobj.put("mentor_req",mentor_req_json);
                jobj.put("attitude_for_diff",attitude_for_diff_json);
                jobj.put("challenge",challenge_json);
                jobj.put("for_successful_1st_year",for_successful_1st_year_json);

                jArray.put(jobj);

            }

            finalJObject.put("data", jArray);

        } catch (Exception e) {
            System.out.println("There was a problem querying user table and mentor table in database ");
        }

        return finalJObject;

    }




    public int[] [] graphData_IntakeFormMentee(String year) throws JSONException {
        JSONObject json = userTableMentee(year);


        JSONArray data = json.getJSONArray("data");
        int female = 0;
        int male = 0;
        int otherGender = 0;


        int white = 0;
        int black_AfricanAmerican = 0;
        int hispanic = 0;
        int latino_Latina = 0;
        int nativeAmerican_American_Indian = 0;
        int asian = 0;
        int pacific_Islander = 0;
        int otherRace = 0;

        int parentEd = 0;
        int nonParentEd = 0;

        int freshman = 0;
        int sophomore = 0;

        if(data != null) {
            for (int i = 0; i < data.length(); i++) {
                JSONObject temp = data.getJSONObject(i);

                if (temp.getString("gender").equalsIgnoreCase("Male") && temp.getString("gender").equalsIgnoreCase("Male")) { male++; }
                if (temp.getString("gender").equalsIgnoreCase("Female")) { female++; }
                if (temp.getString("gender").equalsIgnoreCase("Other")) { otherGender++; }

                if (temp.getString("race").equalsIgnoreCase("White")) { white++; }
                if (temp.getString("race").equalsIgnoreCase("Black/African Am.")) { black_AfricanAmerican++; }
                if (temp.getString("race").equalsIgnoreCase("Hispanic")) { hispanic++; }
                if (temp.getString("race").equalsIgnoreCase("Latinx")) { latino_Latina++; }
                if (temp.getString("race").equalsIgnoreCase("Native American")) { nativeAmerican_American_Indian++; }
                if (temp.getString("race").equalsIgnoreCase("Asian")) { asian++; }
                if (temp.getString("race").equalsIgnoreCase("Pacific Islander")) { pacific_Islander++; }
                if (temp.getString("race").equalsIgnoreCase("Other")) { otherRace++; }

                try{
                    if (temp.getString("parent_education").equals("1")) { parentEd++; }
                }
                catch (Exception e){
                    System.out.println("This mentee had no input for parents education "+e);
                }

                try{
                    if (temp.getString("parent_education").equals("0")) { nonParentEd++; }

                }
                catch (Exception e){
                    System.out.println("This mentee had no input for parents education "+e);

                }



                if (temp.getString("year").equals("1")) { freshman++; }
                if (temp.getString("year").equals("2")) { sophomore++; }

            }
        }
        int result1 [] = {male,female,otherGender};
        int result2[] = {white,black_AfricanAmerican,hispanic,latino_Latina,
                nativeAmerican_American_Indian,asian,pacific_Islander,otherRace};
        int result3[] = {parentEd,nonParentEd};
        int result4[] = {freshman,sophomore};


        int result[][] = {result1,result2,result3,result4};

        return result;


    }






    public int[] [] graphData_IntakeFormMentor(String year) throws JSONException {
        JSONObject json = userTableMentor(year);

        JSONArray data = json.getJSONArray("data");
        int female = 0;
        int male = 0;
        int otherGender = 0;


        int white = 0;
        int black_AfricanAmerican = 0;
        int hispanic = 0;
        int latino_Latina = 0;
        int nativeAmerican_American_Indian = 0;
        int asian = 0;
        int pacific_Islander = 0;
        int otherRace = 0;

        int parentEd = 0;
        int nonParentEd = 0;

        if(data != null) {
            for (int i = 0; i < data.length(); i++) {
                JSONObject temp = data.getJSONObject(i);

                if (temp.getString("gender").equalsIgnoreCase("Male") && temp.getString("gender").equalsIgnoreCase("Male")) { male++; }
                if (temp.getString("gender").equalsIgnoreCase("Female")) { female++; }
                if (temp.getString("gender").equalsIgnoreCase("Other")) { otherGender++; }

                if (temp.getString("race").equalsIgnoreCase("White")) { white++; }
                if (temp.getString("race").equalsIgnoreCase("Black/African Am.")) { black_AfricanAmerican++; }
                if (temp.getString("race").equalsIgnoreCase("Hispanic")) { hispanic++; }
                if (temp.getString("race").equalsIgnoreCase("Latinx")) { latino_Latina++; }
                if (temp.getString("race").equalsIgnoreCase("Native American")) { nativeAmerican_American_Indian++; }
                if (temp.getString("race").equalsIgnoreCase("Asian")) { asian++; }
                if (temp.getString("race").equalsIgnoreCase("Pacific Islander")) { pacific_Islander++; }
                if (temp.getString("race").equalsIgnoreCase("Other")) { otherRace++; }

                if (temp.getString("parent_education").equals("1")) { parentEd++; }
                if (temp.getString("parent_education").equals("0")) { nonParentEd++; }

            }
        }
        int result1 [] = {male,female,otherGender};
        int result2[] = {white,black_AfricanAmerican,hispanic,latino_Latina,
                nativeAmerican_American_Indian,asian,pacific_Islander,otherRace};

        int result3[] = {parentEd,nonParentEd};

        int result[][] = {result1,result2,result3};

        return result;


    }





    //public int[] [] graphData_SessionForm(String lastName, String firstName,String sessNum1, String sessNum2) throws JSONException {
    public double[] [] graphData_SessionForm(String mentee) throws JSONException {
        //JSONObject json = sessionTableMentee(lastName,firstName,sessNum1,sessNum2);
        JSONObject json = sessionFormTable(mentee);


        JSONArray data = json.getJSONArray("data");
        double actStpCmp = 0;
        double totalActStep = 0;

        double campus_involvement = 0;
        double meaningful_relationships = 0;
        double financial_management = 0;
        double outside_responsibilities = 0;
        double study_time_management = 0;
        double academic_engagement = 0;
        double health_wellness = 0;
        double other_bool = 0;

        double scaleAvg = 0;



        if(data != null) {
            for (int i = 0; i < data.length(); i++) {
                JSONObject temp = data.getJSONObject(i);




                if (temp.getString("bool_action_one").equals("1")) { actStpCmp ++; }
                if (temp.getString("bool_action_two").equals("1")) { actStpCmp ++; }
                if (temp.getString("bool_action_three").equals("1")) { actStpCmp ++; }
                if (temp.getString("bool_action_four").equals("1")) { actStpCmp ++; }
                if (temp.getString("bool_action_five").equals("1")) { actStpCmp ++; }
                if (temp.getString("bool_action_six").equals("1")) { actStpCmp ++; }


                if (temp.getString("scale")!= null) { scaleAvg+= Integer.parseInt(temp.getString("scale") ); }


//                if (temp.getString("pre_action_one")!= null){ totalActStep++; }
//                if (temp.getString("pre_action_two")!= null) { totalActStep++; }
//                if (temp.getString("pre_action_three")!= null) { totalActStep++; }
//                if (temp.getString("pre_action_four")!= null) { totalActStep++; }
//                if (temp.getString("pre_action_five")!= null) { totalActStep++; }
//                if (temp.getString("pre_action_six")!= null) { totalActStep++; }



                if (temp.getString("pre_action_one")!= null && !temp.getString("pre_action_one").isEmpty()) { totalActStep++; }
                if (temp.getString("pre_action_two")!= null && !temp.getString("pre_action_two").isEmpty()) { totalActStep++; }
                if (temp.getString("pre_action_three")!= null && !temp.getString("pre_action_three").isEmpty()) { totalActStep++; }
                if (temp.getString("pre_action_four")!= null && !temp.getString("pre_action_four").isEmpty()) { totalActStep++; }
                if (temp.getString("pre_action_five")!= null && !temp.getString("pre_action_five").isEmpty()) { totalActStep++; }
                if (temp.getString("pre_action_six")!= null && !temp.getString("pre_action_six").isEmpty()) { totalActStep++; }

                if (temp.getString("campus_involvement").equals("1")) { campus_involvement ++; }
                if (temp.getString("meaningful_relationships").equals("1")) { meaningful_relationships++; }
                if (temp.getString("financial_management").equals("1")) { financial_management ++; }
                if (temp.getString("outside_responsibilities").equals("1")) { outside_responsibilities++; }

                if (temp.getString("study_time_management").equals("1")) { study_time_management++; }
                if (temp.getString("academic_engagement").equals("1")) { academic_engagement++; }
                if (temp.getString("health_wellness").equals("1")) { health_wellness++; }
                if (temp.getString("other_bool").equals("1")) { other_bool++; }





                 }

            }


        double result1 [] = {totalActStep-actStpCmp,actStpCmp};

        double result2[] = {campus_involvement,meaningful_relationships,financial_management,outside_responsibilities,
                study_time_management,academic_engagement,health_wellness,other_bool};

        double result3[] = {scaleAvg/2};

        double result4[] = {totalActStep,actStpCmp};

        double result[][] = {result1,result2,result3,result4};
        //int result[][] = {{},{},{56}};

        return result;


    }






    public File excelReport_IntakeFormMentee(String year) throws JSONException, FileNotFoundException, UnsupportedEncodingException {
        int [][] graphData = graphData_IntakeFormMentee(year);

        JSONObject jsonLookingForward =  data_MenteeOpenEndReport();
        JSONArray dataLookingForward = jsonLookingForward.getJSONArray("data");

        JSONObject jsonWhyMentor =  data_MenteeOpenEndReport();
        JSONArray dataWhyMentor = jsonWhyMentor.getJSONArray("data");

        //Map<String, Object[]> datax = new TreeMap<String, Object[]>();
        LinkedHashMap <String, Object[]> datax = new LinkedHashMap<String, Object[]>();


        datax.put("1", new Object[] {"Number of Male","Number of Females",
        "Number of Other Gender"});

        datax.put("2", new Object[] {Integer.toString(graphData[0][0]),
                Integer.toString(graphData[0][1]), Integer.toString(graphData[0][2])});

        datax.put("3", new Object[] {"Participants per Ethnic Group"});

        datax.put("4", new Object[] {"White","Black/African American","Hispanic","Latino/Latina"
                ,"Native American/American Indian","Asian","Pacific Islander","Other Race"});

        datax.put("5", new Object[] {Integer.toString(graphData[1][0]),
                Integer.toString(graphData[1][1]),Integer.toString(graphData[1][2]),
                Integer.toString(graphData[1][3]),Integer.toString(graphData[1][4]),
                Integer.toString(graphData[1][5]),Integer.toString(graphData[1][6]),
                Integer.toString(graphData[1][7])});

        datax.put("6", new Object[] {"First Generation College Student"});
        datax.put("7", new Object[] {"Non-First-Generation College Student","First-Generation College Student"});
        datax.put("8", new Object[] {Integer.toString(graphData[2][0]),
                Integer.toString(graphData[2][1])});

        datax.put("9", new Object[] {"What are you most looking forward to experiencing in college?"});
        datax.put("10", new Object[] {"First Name","Last Name","Reason"});
        int i = 0;

        for (i = 11; i < dataLookingForward.length()+11; i++) {
            JSONObject temp = dataLookingForward.getJSONObject(i-11);
            datax.put(Integer.toString(i), new Object[] {temp.getString("first_name"),temp.getString("last_name"),
                    temp.getString("looking_forward")});
        }


        datax.put(Integer.toString(i), new Object[] {"Why do you want a peer mentor?"});
        i++;
        datax.put(Integer.toString(i), new Object[] {"First Name","Last Name","Reason"});
        i++;
        for (int j = 0; j < dataWhyMentor.length(); j++) {
            JSONObject temp = dataWhyMentor.getJSONObject(j);
            datax.put(Integer.toString(i), new Object[] {temp.getString("first_name"),temp.getString("last_name"),
                    temp.getString("why_mentor")});
            i++;
        }

        datax.put(Integer.toString(i), new Object[] {"Number of Freshmen and Sophomores"});
        i++;
        datax.put(Integer.toString(i), new Object[] {"Freshmen","Sophomores"});
        i++;
        datax.put(Integer.toString(i), new Object[] {Integer.toString(graphData[3][0]),
                Integer.toString(graphData[3][1])});
        i++;




        File report = new File("MenteeIntakeReport.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Mentee Intake Report");

        Set<String> keyset = datax.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = datax.get(key);
            int cellnum = 0;
            for (Object objx : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if(objx instanceof String)
                    cell.setCellValue((String)objx);
                else if(objx instanceof Integer)
                    cell.setCellValue((Integer)objx);
            }
        }
        try
        {

            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(report);
            workbook.write(out);
            out.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }




        return report;
    }









    public File excelReport_IntakeFormMentor(String year) throws JSONException, FileNotFoundException, UnsupportedEncodingException {
        int [][] graphData = graphData_IntakeFormMentor(year);

        JSONObject jsonMentorReq =  data_MentorOpenEndReport();
        JSONArray dataMentorReq = jsonMentorReq.getJSONArray("data");

        JSONObject jsonAttitudeForDiff =  data_MentorOpenEndReport();
        JSONArray dataAttitudeForDiff = jsonAttitudeForDiff.getJSONArray("data");

        JSONObject jsonChallenge =  data_MentorOpenEndReport();
        JSONArray dataChallenge = jsonChallenge.getJSONArray("data");

        JSONObject jsonForSuccessful_1stYear =  data_MentorOpenEndReport();
        JSONArray dataForSuccessful_1stYear = jsonForSuccessful_1stYear.getJSONArray("data");





        //Map<String, Object[]> datax = new TreeMap<String, Object[]>();
        LinkedHashMap <String, Object[]> datax = new LinkedHashMap<String, Object[]>();


        datax.put("1", new Object[] {"Number of Male","Number of Females",
                "Number of Other Gender"});

        datax.put("2", new Object[] {Integer.toString(graphData[0][0]),
                Integer.toString(graphData[0][1]), Integer.toString(graphData[0][2])});

        datax.put("3", new Object[] {"Participants per Ethnic Group"});

        datax.put("4", new Object[] {"White","Black/African American","Hispanic","Latino/Latina"
                ,"Native American/American Indian","Asian","Pacific Islander","Other Race"});

        datax.put("5", new Object[] {Integer.toString(graphData[1][0]),
                Integer.toString(graphData[1][1]),Integer.toString(graphData[1][2]),
                Integer.toString(graphData[1][3]),Integer.toString(graphData[1][4]),
                Integer.toString(graphData[1][5]),Integer.toString(graphData[1][6]),
                Integer.toString(graphData[1][7])});

        datax.put("6", new Object[] {"First Generation College Student"});
        datax.put("7", new Object[] {"Non-First-Generation College Student","First-Generation College Student"});
        datax.put("8", new Object[] {Integer.toString(graphData[2][0]),
                Integer.toString(graphData[2][1])});

        datax.put("9", new Object[] {"What are the requirements to being a good mentor to someone who" +
                "may have a different background than you?"});

        datax.put("10", new Object[] {"First Name","Last Name","Reason"});
        int i = 11;


        for (int j = 0; j < dataMentorReq.length(); j++) {
            JSONObject temp = dataMentorReq.getJSONObject(j);
            datax.put(Integer.toString(i), new Object[] {temp.getString("first_name"),temp.getString("last_name"),
                    temp.getString("mentor_req")});
            i++;
        }


        datax.put(Integer.toString(i), new Object[] {"Why would you make a good mentor to first year and sophomores?"});
        i++;
        datax.put(Integer.toString(i), new Object[] {"First Name","Last Name","Reason"});
        i++;
        for (int j = 0; j < dataAttitudeForDiff.length(); j++) {
            JSONObject temp = dataAttitudeForDiff.getJSONObject(j);
            datax.put(Integer.toString(i), new Object[] {temp.getString("first_name"),temp.getString("last_name"),
                    temp.getString("attitude_for_diff")});
            i++;
        }

        datax.put(Integer.toString(i), new Object[] {"Describe a time when you encountered a challenge in " +
                " college, and how you dealt with it"});

        i++;
        datax.put(Integer.toString(i), new Object[] {"First Name","Last Name","Reason"});
        i++;

        for ( int j = 0; j < dataChallenge.length(); j++) {
            JSONObject temp = dataChallenge.getJSONObject(j);
            datax.put(Integer.toString(i), new Object[] {temp.getString("first_name"),temp.getString("last_name"),
                    temp.getString("challenge")});
            i++;
        }


        datax.put(Integer.toString(i), new Object[] {"What does it take for a first generation college student" +
                " to be successful"});

        i++;
        datax.put(Integer.toString(i), new Object[] {"First Name","Last Name","Reason"});
        i++;

        for (int j = 0; j < dataForSuccessful_1stYear.length(); j++) {
            JSONObject temp = dataForSuccessful_1stYear.getJSONObject(j);
            datax.put(Integer.toString(i), new Object[] {temp.getString("first_name"),temp.getString("last_name"),
                    temp.getString("for_successful_1st_year")});
            i++;
        }



        File report = new File("MentorIntakeReport.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Mentor Intake Report");

        Set<String> keyset = datax.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = datax.get(key);
            int cellnum = 0;
            for (Object objx : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if(objx instanceof String)
                    cell.setCellValue((String)objx);
                else if(objx instanceof Integer)
                    cell.setCellValue((Integer)objx);
            }
        }
        try
        {

            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(report);
            workbook.write(out);
            out.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return report;
    }










    //public File excelReport_SessionForm(String lastName,String firstName,String sessNum1,String sessNum2) throws JSONException, FileNotFoundException, UnsupportedEncodingException {
    public File excelReport_SessionForm(String fullname) throws JSONException, FileNotFoundException, UnsupportedEncodingException {
        //int [][] graphData = graphData_SessionForm(lastName, firstName, sessNum1, sessNum2);
        //double [][] graphData = graphData_SessionForm("Aaron Ba");
        double [][] graphData = graphData_SessionForm(fullname);

        //JSONObject jsonDataToExcel =  sessionTableMentee(lastName, firstName, sessNum1, sessNum2);
        //JSONObject jsonDataToExcel =  sessionFormTable("Aaron Ba");
        JSONObject jsonDataToExcel =  sessionFormTable(fullname);

        JSONArray dataDataToExcel = jsonDataToExcel.getJSONArray("data");



        //Map<String, Object[]> datax = new TreeMap<String, Object[]>();
        LinkedHashMap <String, Object[]> datax = new LinkedHashMap<String, Object[]>();


        datax.put("1", new Object[] {"Total Number of Action Step","Action Steps Completed"});

        datax.put("2", new Object[] {Double.toString(graphData[3][0]),
                Double.toString(graphData[3][1])});

        datax.put("3", new Object[] {"Session Topics Discussed"});

        datax.put("4", new Object[] {"Campus Involvement","Meaningful Relationships","Financial Management",
                "Outside Responsibilities","Study time Management","Academic Engagement","Health Wellness","Other"});



        datax.put("5", new Object[] {Double.toString(graphData[1][0]),
                Double.toString(graphData[1][1]),Double.toString(graphData[1][2]),
                Double.toString(graphData[1][3]),Double.toString(graphData[1][4]),
                Double.toString(graphData[1][5]),Double.toString(graphData[1][6]),
                Double.toString(graphData[1][7])});

        datax.put("6", new Object[] {"Average Session Rating"});
        datax.put("7", new Object[] {Double.toString(graphData[2][0])});



        int i = 8;

        for (int j = 0; j < dataDataToExcel.length(); j++) {
            JSONObject temp = dataDataToExcel.getJSONObject(j);

            datax.put(Integer.toString(i), new Object[] {"Other Session Topics Discussed"});
            i++;

//            datax.put(Integer.toString(i), new Object[] {temp.getString("full_name"),
//                    temp.getString("other_text")});
//            i++;


            datax.put(Integer.toString(i), new Object[] {"All Action Steps"});
            i++;

            datax.put(Integer.toString(i), new Object[] {"Name","Action Step One","Action Step Two",
                    "Action Step Three","Action Step Four","Action Step Five",
                    "Action step Six"});
            i++;

            datax.put(Integer.toString(i), new Object[] {temp.getString("full_name"),
                    temp.getString("action_one"),temp.getString("action_two"),
                    temp.getString("action_three"),temp.getString("action_four"),
                    temp.getString("action_five"),temp.getString("action_six")});
            i++;
            datax.put(Integer.toString(i), new Object[] {"Action Steps Completed"});
            i++;

            datax.put(Integer.toString(i), new Object[] {"Name","Action Step One Completed?","Action Step Two Completed?",
                    "Action Step Three Completed?","Action Step Four Completed?","Action Step Five Completed?",
                    "Action step Six Completed?"});
            i++;
            datax.put(Integer.toString(i), new Object[] {"1 for Yes 0 for No",
                    temp.getString("bool_action_one"),temp.getString("bool_action_two"),
                    temp.getString("bool_action_three"),temp.getString("bool_action_four"),
                    temp.getString("bool_action_five"),temp.getString("bool_action_six")});
            i++;
        }


        datax.put(Integer.toString(i), new Object[] {"Issues and Concern"});
        i++;

        datax.put(Integer.toString(i), new Object[] {"Name","Issues and Concern"});
        i++;

        for (int j = 0; j < dataDataToExcel.length(); j++) {
            JSONObject temp = dataDataToExcel.getJSONObject(j);
            datax.put(Integer.toString(i), new Object[] {temp.getString("full_name"),
                    temp.getString("issues_concerns")});
            i++;
        }


        datax.put(Integer.toString(i), new Object[] {"Notes and Comments"});
        i++;
        datax.put(Integer.toString(i), new Object[] {"Name","Notes and Comments"});
        i++;
        for (int j = 0; j < dataDataToExcel.length(); j++) {
            JSONObject temp = dataDataToExcel.getJSONObject(j);
            datax.put(Integer.toString(i), new Object[] {temp.getString("full_name"),
                    temp.getString("notes_comments")});
            i++;
        }



        File report = new File("SessionReport.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Mentor Intake Report");

        Set<String> keyset = datax.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = datax.get(key);
            int cellnum = 0;
            for (Object objx : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if(objx instanceof String)
                    cell.setCellValue((String)objx);
                else if(objx instanceof Integer)
                    cell.setCellValue((Integer)objx);
            }
        }
        try
        {

            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(report);
            workbook.write(out);
            out.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return report;
    }


}