package com.example.cs3560a2;

import java.util.ArrayList;
import java.util.List;

public class User extends Subject implements Visitor, Friend{
    private String userID;
    private List<String> messageList;

    private List<Friend> followList;
    private String creationTime;
    private String lastUpdate = "";

    public User(String userID, Observer paneObserver){
        this.userID = userID;
        messageList = new ArrayList<>();
        followList = new ArrayList<>();
        creationTime = String.valueOf(System.currentTimeMillis());
        attachObserver(paneObserver);
        notifyObserver();
    }

    public void sentMessage(String message){
        messageList.add(message);
        lastUpdate = String.valueOf(System.currentTimeMillis());
    }

    public String getUserID(){
        return userID;
    }

    public long getLastUpdateTime(){
        if(lastUpdate != ""){
            return Long.parseLong(lastUpdate);
        }else {
            return 0;
        }
    }

    public boolean setFollowList(User user){
        final boolean[] result = {true};
        followList.forEach(user1 -> {
            User user2 = (User) user1;
            if(user.userID == this.userID || user.userID == user2.userID){
                result[0] = false;
            }
        });

        if(!result[0]){
            return false;
        }else{
            followList.add(user);
            return true;
        }
    }

    public List<Friend> getFollowList() {
        return followList;
    }

    public List<String> getMessageList(){
        return messageList;
    }

    @Override
    public List<String> accept(Visitor user) {

        return user.visit(this);
    }

    @Override
    public List<String> visit(Friend user) {
        User user1 = (User) user;
        return user1.getMessageList();
    }

    public String getCreationTime(){
        return creationTime;
    }
}
