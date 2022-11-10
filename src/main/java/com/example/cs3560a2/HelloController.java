package com.example.cs3560a2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private List<User> userList;
    @FXML
    private Button addUser;
    @FXML
    private VBox ul;
    @FXML
    private TextField idField;
    @FXML
    private TextField groupField;

    private UserListPane userPane;

    private Group groups;
    public HelloController() {
        userList = new ArrayList<>();
    }

    @FXML
    private void addButtonHandler(){
        String id = idField.getText();
        if(id.length() != 0){
            User newUser = new User(id, groups);
            userList.add(newUser);
        }
    }

    @FXML
    private void addGroupHandler(){
        String groupName = groupField.getText();
        if(groupName.length() != 0){
            groups.setGroup(groupName);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userPane = new UserListPane(ul);//observer of the new created user
        groups = new Group(userPane);

    }
}