package org.but.feec.airport.service;

public class EmailHolderService {

    private final static EmailHolderService EMAIL_HOLDER_SERVICE = new EmailHolderService();
    private String email;

    private EmailHolderService(){}

    public static EmailHolderService getInstance(){
        return EMAIL_HOLDER_SERVICE;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }


}
