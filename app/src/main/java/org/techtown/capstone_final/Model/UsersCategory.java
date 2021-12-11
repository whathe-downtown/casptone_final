package org.techtown.capstone_final.Model;

public class UsersCategory {
    private String subject ;
    private int value;

    public UsersCategory(String subject, int value) {
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
