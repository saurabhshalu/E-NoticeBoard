package Beans;

public class LoginBean {
    private String uname;
    private String upass;
    private String logintype;
    
    public String getUsername() {
        return uname;
    }
    public void setUsername(String u) {
        uname = u;
    }
    public String getPassword() {
        return upass;
    }
    public void setPassword(String p) {
        upass = p;
    }
    public String getLoginType() {
        return logintype;
    }
    public void setLoginType(String t) {
        logintype = "tbl" + t;
    }
}
