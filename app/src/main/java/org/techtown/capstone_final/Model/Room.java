package org.techtown.capstone_final.Model;

public class Room {

    String roomTitle,  roomprofilepic, roomContent, roomDate,roomTime,roomPlace,roomHeadcount;

    public Room(String roomTitle, String roomprofilepic, String roomContent, String roomDate, String roomTime, String roomPlace, String roomHeadcount) {
        this.roomTitle = roomTitle;
        this.roomprofilepic = roomprofilepic;
        this.roomContent = roomContent;
        this.roomDate = roomDate;
        this.roomTime = roomTime;
        this.roomPlace = roomPlace;
        this.roomHeadcount = roomHeadcount;
    }
    public  Room(){}
    //MaintMade Room  constructor

    public Room(String roomTitle, String roomDate, String roomPlace) {
        this.roomTitle = roomTitle;
        this.roomDate = roomDate;
        this.roomPlace = roomPlace;
    }

//    //sececond Room Constructor
//    public Room(String roomDate, String roomTime, String roomPlace, String roomHeadcount) {
//        this.roomDate = roomDate;
//        this.roomTime = roomTime;
//        this.roomPlace = roomPlace;

//        this.roomHeadcount = roomHeadcount;
//    }

    public String getRoomTitle(String key) {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getRoomprofilepic() {
        return roomprofilepic;
    }

    public void setRoomprofilepic(String roomprofilepic) {
        this.roomprofilepic = roomprofilepic;
    }

    public String getRoomContent() {
        return roomContent;
    }

    public void setRoomContent(String roomContent) {
        this.roomContent = roomContent;
    }

    public String getRoomDate() {
        return roomDate;
    }

    public void setRoomDate(String roomDate) {
        this.roomDate = roomDate;
    }

    public String getRoomTime() {
        return roomTime;
    }

    public void setRoomTime(String roomTime) {
        this.roomTime = roomTime;
    }

    public String getRoomPlace() {
        return roomPlace;
    }

    public void setRoomPlace(String roomPlace) {
        this.roomPlace = roomPlace;
    }

    public String getRoomHeadcount() {
        return roomHeadcount;
    }

    public void setRoomHeadcount(String roomHeadcount) {
        this.roomHeadcount = roomHeadcount;
    }

}
