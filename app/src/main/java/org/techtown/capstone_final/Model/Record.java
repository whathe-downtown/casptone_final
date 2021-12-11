package org.techtown.capstone_final.Model;

public class Record {
    private String useruid , roomtitle , roomcategory, roomlocation , roomdate;

    public Record(String useruid, String roomtitle, String roomcategory, String roomlocation, String roomdate) {
        this.useruid = useruid;
        this.roomtitle = roomtitle;
        this.roomcategory = roomcategory;
        this.roomlocation = roomlocation;
        this.roomdate = roomdate;
    }

    public Record(){}

    public String getUseruid() {
        return useruid;
    }

    public void setUseruid(String useruid) {
        this.useruid = useruid;
    }

    public String getRoomtitle() {
        return roomtitle;
    }

    public void setRoomtitle(String roomtitle) {
        this.roomtitle = roomtitle;
    }

    public String getRoomcategory() {
        return roomcategory;
    }

    public void setRoomcategory(String roomcategory) {
        this.roomcategory = roomcategory;
    }

    public String getRoomlocation() {
        return roomlocation;
    }

    public void setRoomlocation(String roomlocation) {
        this.roomlocation = roomlocation;
    }

    public String getRoomdate() {
        return roomdate;
    }

    public void setRoomdate(String roomdate) {
        this.roomdate = roomdate;
    }
}
