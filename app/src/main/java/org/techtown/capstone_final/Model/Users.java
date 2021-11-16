package org.techtown.capstone_final.Model;

public class Users {

    private String profilepic, name , userinfo;

    public Users(String profilepic, String name, String userinfo) {
        this.profilepic = profilepic;
        this.name = name;
        this.userinfo = userinfo;
    }

    public Users(String name){
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }
}
