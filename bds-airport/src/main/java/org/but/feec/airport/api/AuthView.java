package org.but.feec.airport.api;

/**
 * EmployeeAuthView
 */
public class AuthView {

    private String email;
    private String password;
    private String tab;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getTab() {
        return tab;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setTab(String tab) {
        this.tab = tab;
    }
    @Override
    public String toString() {
        return "EmployeeAuthView{" +
        "email="+this.email+
        "password="+password+"}";
    }
}