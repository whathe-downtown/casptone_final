package org.techtown.capstone_final.Model;

public class Users {

    private String name, profilepic, userinfo, userhistory;

    public Users(String profilepic, String name, String userinfo, String userhistory) {
        this.name = name;
        this.profilepic = profilepic;

        this.userinfo = userinfo;
        this.userhistory = userhistory;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilepic() {
        return  this.profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUserinfo() {
        return  this.userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }

    public String getUserhistory() {
        return  this.userhistory;
    }

    public void setUserhistory(String userhistory) {
        this.userhistory = userhistory;
    }
}
