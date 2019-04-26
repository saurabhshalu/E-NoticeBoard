package Beans;

public class NoticeBean {
    private long id;
    private String title;
    private String body;
    private String attachment;
    private String semester;
    private String branchcode;
    private String collegecode;
    private String start_date;
    private String end_date;
    private String professorcode;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String u) {
        title = u;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String p) {
        body = p;
    }
    public String getAttachment() {
        return attachment;
    }
    public void setAttachment(String t) {
        attachment = t;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String t) {
        semester = t;
    }
    public String getBranch() {
        return branchcode;
    }
    public void setBranch(String t) {
        branchcode = t;
    }
    public String getCollege() {
        return collegecode;
    }
    public void setCollege(String t) {
        collegecode = t;
    }
    public String getStartDate() {
        return start_date;
    }
    public void setStartDate(String t) {
        start_date = t;
    }
    public String getEndDate() {
        return end_date;
    }
    public void setEndDate(String t) {
        end_date = t;
    }
    public String getProfessorCode() {
        return professorcode;
    }
    public void setProfessorCode(String t) {
        professorcode = t;
    }
    public void setId(long u) {
        id = u;
    }
    public long getId() {
        return id;
    }
}
