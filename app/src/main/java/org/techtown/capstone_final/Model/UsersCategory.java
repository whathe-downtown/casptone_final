package org.techtown.capstone_final.Model;

public class UsersCategory {
    private String subject ;
    private String value;

    public UsersCategory(String subject, String value) {
        this.subject = subject;
        this.value = value;
    }
    public UsersCategory(){}
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
