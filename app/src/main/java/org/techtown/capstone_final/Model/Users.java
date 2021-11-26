package org.techtown.capstone_final.Model;

public class Users {

    private String name, profilepic, userinfo, userhistory , usercategory, useroneinfo;

    public Users(String name, String profilepic, String userinfo, String userhistory, String usercategory, String useroneinfo) {
        this.name = name;
        this.profilepic = profilepic;
        this.userinfo = userinfo;
        this.userhistory = userhistory;
        this.usercategory = usercategory;
        this.useroneinfo = useroneinfo;
    }

    public String getUseroneinfo() {
        return useroneinfo;
    }

    public void setUseroneinfo(String useroneinfo) {
        this.useroneinfo = useroneinfo;
    }

    public String getUsercategory() { return usercategory; }

    public void setUsercategory(String usercategory) { this.usercategory = usercategory; }

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
