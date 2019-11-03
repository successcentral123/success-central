package com.ccsu.cs530.successcentral.model;

public class SessionForm {

    private String first_name;
    private String second_name;
    private int session_number;
    private String date;
    private String pre_action_one = "na";
    private boolean bool_action_one = false;
    private String pre_action_two = "";
    private boolean bool_action_two = false;
    private String pre_action_three = "";
    private boolean bool_action_three = false;
    private String pre_action_four = "";
    private boolean bool_action_four = false;
    private String pre_action_five = "";
    private boolean bool_action_five = false;
    private String pre_action_six = "";
    private boolean bool_action_six = false;
    private int scale;
    private boolean campus_involvement = false;
    private boolean meaningful_relationships = false;
    private boolean financial_management = false;
    private boolean outside_responsibilities = false;
    private boolean study_time_management = false;
    private boolean academic_engagement = false;
    private boolean health_wellness = false;
    private String other;
    private boolean bool_other = false;
    private String issues_concerns;
    private String notes_comments;

    private String action_step_one;
    private String action_step_two;
    private String action_step_three;
    private String action_step_four;
    private String action_step_five;
    private String action_step_six;

    private String[] PreActions = new String [6];
    private String[] SessionTopics = new String [10];


    public SessionForm(){}

    public String getFirstName(){
        return first_name;
    }

    public void setFirstName(String name){
        this.first_name = name;
    }

    public String getLastName(){
        return second_name;
    }

    public void setLastName(String name){
        this.second_name = name;

    }

    public int getSessionNum() {
        return session_number;
    }

    public void setSessionNum(int session_number) {
        this.session_number = session_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPreActionSteps(String actions[]){

        for(int i = 0; i < actions.length; i++){
            PreActions[i] = actions[i];
            setPreActions(actions[i], i);
        }
    }

    public void setPreActions(String act, int i) {
        switch (i) {
            case 0:
                setPreActionOne(act);
                setBoolActionOne(true);
                break;
            case 1:
                setPreActionTwo(act);
                setBoolActionTwo(true);
                break;
            case 2:
                setPreActionThree(act);
                setBoolActionThree(true);
                break;
            case 3:
                setPreActionFour(act);
                setBoolActionFour(true);
                break;
            case 4:
                setPreActionFive(act);
                setBoolActionFive(true);
                break;
            case 5:
                setPreActionSix(act);
                setBoolActionSix(true);
                break;
            default:
                break;
        }
    }

    public String[] getPreActionsSteps(){
        return PreActions;

    }

    public String getPreActionOne() {
        return pre_action_one;
    }

    public void setPreActionOne(String pre_action_one) {
        this.pre_action_one = pre_action_one;
    }

    public boolean isBool_action_one() {
        return bool_action_one;
    }

    public void setBoolActionOne(boolean bool_action_one) {
        this.bool_action_one = bool_action_one;
    }

    public String getPreActionTwo() {
        return pre_action_two;
    }

    public void setPreActionTwo(String pre_action_two) {
        this.pre_action_two = pre_action_two;
    }

    public boolean isBool_action_two() {
        return bool_action_two;
    }

    public void setBoolActionTwo(boolean bool_action_two) {
        this.bool_action_two = bool_action_two;
    }

    public String getPreActionThree() {
        return pre_action_three;
    }

    public void setPreActionThree(String pre_action_three) {
        this.pre_action_three = pre_action_three;
    }

    public boolean isBool_action_three() {
        return bool_action_three;
    }

    public void setBoolActionThree(boolean bool_action_three) {
        this.bool_action_three = bool_action_three;
    }

    public String getPreActionFour() {
        return pre_action_four;
    }

    public void setPreActionFour(String pre_action_four) {
        this.pre_action_four = pre_action_four;
    }

    public boolean isBool_action_four() {
        return bool_action_four;
    }

    public void setBoolActionFour(boolean bool_action_four) {
        this.bool_action_four = bool_action_four;
    }

    public String getPreActionFive() {
        return pre_action_five;
    }

    public void setPreActionFive(String pre_action_five) {
        this.pre_action_five = pre_action_five;
    }

    public boolean isBool_action_five() {
        return bool_action_five;
    }

    public void setBoolActionFive(boolean bool_action_five) {
        this.bool_action_five = bool_action_five;
    }

    public String getPreActionSix() {
        return pre_action_six;
    }

    public void setPreActionSix(String pre_action_six) {
        this.pre_action_six = pre_action_six;
    }

    public boolean isBool_action_six() {
        return bool_action_six;
    }

    public void setBoolActionSix(boolean bool_action_six) {
        this.bool_action_six = bool_action_six;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setTopicsForm(String Topic[]){
        for(int i = 0; i < Topic.length; i++){
            SessionTopics[i] = Topic[i];
            setTopics(Topic[i]);
        }
    }

    public void setTopics(String topic) {
        switch (topic) {
            case "Campus Involvement":
                setCampusInvolement(true);
                break;
            case "Meaningful Relationships":
                setMeaningfulRelationships(true);
                break;
            case "Financial Management":
                setFinancialManagement(true);
                break;
            case "Outside Responsibilities":
                setOutsideResponsibilities(true);
                break;
            case "Study Skills/Time Management":
                setStudyTimeManagement(true);
                break;
            case "Academic Engagement":
                setAcademicEngagement(true);
                break;
            case "Health & Wellness":
                setHealthWellness(true);
                break;
            case "Other":
                setOther(true);
                break;
            default:
                break;
        }
    }

    public String [] getTopicsForm(){
        return SessionTopics;

    }

    public boolean isCampus_involvement() {
        return campus_involvement;
    }

    public void setCampusInvolement(boolean campus_involvement) {
        this.campus_involvement = campus_involvement;
    }

    public boolean isMeaningful_relationships() {
        return meaningful_relationships;
    }

    public void setMeaningfulRelationships(boolean meaningful_relationships) {
        this.meaningful_relationships = meaningful_relationships;
    }

    public boolean isFinancial_management() {
        return financial_management;
    }

    public void setFinancialManagement(boolean financialManagement) {
        this.financial_management = financialManagement;
    }

    public boolean isOutside_responsibilities() {
        return outside_responsibilities;
    }

    public void setOutsideResponsibilities(boolean outside_responsibilities) {
        this.outside_responsibilities = outside_responsibilities;
    }

    public boolean isStudy_time_management() {
        return study_time_management;
    }

    public void setStudyTimeManagement(boolean study_time_management) {
        this.study_time_management = study_time_management;
    }

    public boolean isAcademic_engagement() {
        return academic_engagement;
    }

    public void setAcademicEngagement(boolean academic_engagement) {
        this.academic_engagement = academic_engagement;
    }

    public boolean isHealth_wellness() {
        return health_wellness;
    }

    public void setHealthWellness(boolean health_wellness) {
        this.health_wellness = health_wellness;
    }

    public String getOther() {
        return other;
    }

    public void setOtherText(String other) {
        this.other = other;
    }

    public boolean isBool_other() {
        return bool_other;
    }

    public void setOther(boolean bool_other) {
        this.bool_other = bool_other;
    }

    public String getIssuesConcerns() {
        return issues_concerns;
    }

    public void setIssuesConcerns(String issues_concerns) {
        this.issues_concerns = issues_concerns;
    }

    public String getNotesComments() {
        return notes_comments;
    }

    public void setNotesComments(String notes_comments) {
        this.notes_comments = notes_comments;
    }

    public String getFirstActionStep() {
        return action_step_one;
    }

    public void setFirstActionStep(String action_step_one) {
        this.action_step_one = action_step_one;
    }

    public String getSecondActionStep() {
        return action_step_two;
    }

    public void setSecondActionStep(String action_step_two) {
        this.action_step_two = action_step_two;
    }

    public String getThirdActionStep() {
        return action_step_three;
    }

    public void setThirdActionStep(String action_step_three) {
        this.action_step_three = action_step_three;
    }

    public String getFourthActionStep() {
        return action_step_four;
    }

    public void setFourthActionStep(String action_step_four) {
        this.action_step_four = action_step_four;
    }

    public String getFifthActionStep() {
        return action_step_five;
    }

    public void setFifthActionStep(String action_step_five) {
        this.action_step_five = action_step_five;
    }

    public String getSixthActionStep() {
        return action_step_six;
    }

    public void setSixthActionStep(String action_step_six) {
        this.action_step_six = action_step_six;
    }

}
