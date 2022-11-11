package com.example.cs3560a2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    static List<User> userList;
    @FXML
    private Button addUser;
    @FXML
    private TreeView ul;
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

    @FXML
    private void showTotalUser(){
        showDialog("Total User: " + userList.size(), "Total User");
    }

    @FXML
    private void showGroupTotal(){
        showDialog("Total Group: " + groups.getGroupList().size(), "Total Group");
    }

    @FXML
    private void showTotalMessage(){
        String text = "";
        for(User user : userList){
            for(String message : user.getMessageList()){
                text += user.getUserID() + ": " + message + "\n";
            }
        }
        showDialog(text, "Total Message");
    }

    @FXML
    private void userView(){
        TreeItem temp = (TreeItem) ul.getSelectionModel().getSelectedItem();

        String id = (String) temp.getValue();
        User user = null;
        for (User user1 : userList){
            if(user1.getUserID().equals(id)){
                user = user1;
            }
        }
        URL location = getClass().getResource("/com/example/cs3560a2/tweetPage.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Scene scene = null;
        Stage stage = new Stage();
        try {
            Parent root = fxmlLoader.load();
            TweetPage controller = fxmlLoader.getController();
            controller.getCurrentUser(user);
            controller.start();
            scene = new Scene(root, 600, 700);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.show();
    }

    private void showDialog(String text, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Information Alert");
        alert.setContentText(text);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ul.setRoot(new TreeItem("Root"));
        userPane = new UserListPane(ul);//observer of the new created user
        groups = new Group(userPane);
    }
}