package Beans;

public class RegisterBean {
    private String uname;
    private String fullname;
    private String upass;
    private String upass2;
    private String branchcode;
    private String semester;
    private String collegecode;
    private String phone;
    private String email;
    private String address;
    private String registertype;
    
    public String getUsername() {
        return uname;
    }
    public void setUsername(String u) {
        uname = u;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String u) {
        fullname = u;
    }
    public String getPassword() {
        return upass;
    }
    public void setPassword(String p) {
        upass = p;
    }
    public String getPassword2() {
        return upass2;
    }
    public void setPassword2(String p) {
        upass2 = p;
    }
    public String getBranch() {
        return branchcode;
    }
    public void setBranch(String t) {
        branchcode = t;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String t) {
        semester = t;
    }
    public String getCollege() {
        return collegecode;
    }
    public void setCollege(String t) {
        collegecode = t;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String t) {
        phone = t;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String t) {
        email = t;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String t) {
        address = t;
    }
    public String getRegisterType() {
        return registertype;
    }
    public void setRegisterType(String t) {
        registertype = "tbl" + t;
    }
}
