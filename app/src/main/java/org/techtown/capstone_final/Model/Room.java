package org.techtown.capstone_final.Model;

public class Room {

    private String publisher,roomTitle,  roomprofilepic,roomcategory, roomContent, roomdate,roomTime,roomlocation,roomHeadcount,roomId;

    public Room(String publisher, String roomTitle, String roomprofilepic, String roomcategory, String roomContent, String roomdate, String roomTime, String roomlocation, String roomHeadcount, String roomId) {
        this.publisher = publisher;
        this.roomTitle = roomTitle;
        this.roomprofilepic = roomprofilepic;
        this.roomcategory = roomcategory;
        this.roomContent = roomContent;
        this.roomdate = roomdate;
        this.roomTime = roomTime;
        this.roomlocation = roomlocation;
        this.roomHeadcount = roomHeadcount;
        this.roomId = roomId;
    }

    public  Room(){}
    //MaintMade Room  constructor


//    //sececond Room Constructor
//    public Room(String roomDate, String roomTime, String roomPlace, String roomHeadcount) {
//        this.roomDate = roomDate;
//        this.roomTime = roomTime;
//        this.roomPlace = roomPlace;

//        this.roomHeadcount = roomHeadcount;
//    }


    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRoomTitle() {
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

    public String getRoomcategory() {
        return roomcategory;
    }

    public void setRoomcategory(String roomcategory) {
        this.roomcategory = roomcategory;
    }

    public String getRoomContent() {
        return roomContent;
    }

    public void setRoomContent(String roomContent) {
        this.roomContent = roomContent;
    }

    public String getRoomdate() {
        return roomdate;
    }

    public void setRoomdate(String roomdate) {
        this.roomdate = roomdate;
    }

    public String getRoomTime() {
        return roomTime;
    }

    public void setRoomTime(String roomTime) {
        this.roomTime = roomTime;
    }

    public String getRoomlocation() {
        return roomlocation;
    }

    public void setRoomlocation(String roomlocation) {
        this.roomlocation = roomlocation;
    }

    public String getRoomHeadcount() {
        return roomHeadcount;
    }

    public void setRoomHeadcount(String roomHeadcount) {
        this.roomHeadcount = roomHeadcount;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
