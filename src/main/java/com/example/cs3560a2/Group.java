package com.example.cs3560a2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Group extends Subject implements Observer{

    private HashMap<String, List<User>> groupList;

    public Group(UserListPane userListObserver){
        attachObserver(userListObserver);
        groupList = new HashMap<>();
        groupList.put("Root", new ArrayList<>());
        notifyObserver();
    }

    public boolean setGroup(String groupName){
        if(!groupList.containsKey(groupName)){
            groupList.put(groupName, new ArrayList<>());
            notifyObserver();
            return true;
        }else{
            return false;
        }
    }

    public boolean setGroupMember(String groupName, User user){
        if(groupList.containsKey(groupName)){
            groupList.get(groupName).add(user);
            notifyObserver();
            return true;
        }else{
            return false;
        }
    }

    public HashMap<String, List<User>> getGroupList(){
        return groupList;
    }

    @Override
    public void update(Subject subject) {
        if(subject instanceof User){
            setGroupMember("Root", ((User) subject));
        }
    }
}
