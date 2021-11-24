package org.techtown.capstone_final.Model;

public class HorizonProfileDto {

    private String currentusername, currentuserprofile;

    public HorizonProfileDto(String currentusername, String currentuserprofile) {
        this.currentusername = currentusername;
        this.currentuserprofile = currentuserprofile;
    }

    public String getCurrentusername() {
        return currentusername;
    }

    public void setCurrentusername(String currentusername) {
        this.currentusername = currentusername;
    }

    public String getCurrentuserprofile() {
        return currentuserprofile;
    }

    public void setCurrentuserprofile(String currentuserprofile) {
        this.currentuserprofile = currentuserprofile;
    }
}
