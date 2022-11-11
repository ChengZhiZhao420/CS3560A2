package com.example.cs3560a2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.cs3560a2.HelloController.userList;

public class TweetPage implements Initializable {
    private ObservableList<String> items;
    private User currentUser;

    @FXML
    private TextField followID;

    @FXML
    private Button follow;

    @FXML
    private ListView followList;

    @FXML
    private TextArea tweetText;

    @FXML
    private Button sentTweet;

    @FXML
    private ListView followerPost;

    public void getCurrentUser(User user){
        currentUser = user;
        System.out.println(user.getUserID());
    }

    @FXML
    private void followUser(){
        String followUserID = followID.getText();
        System.out.println(followUserID);
        User user2 = null;
        for (User user : userList) {
            if(user.getUserID().equals(followUserID)){
                user2 = user;
            }
        }

        currentUser.setFollowList(user2);
    }

    @FXML
    private void tweet(){
        String tweetContent = tweetText.getText();
        currentUser.sentMessage(tweetContent);
        System.out.println(tweetContent);
    }

    public void start(){
        List<Friend> followList = currentUser.getFollowList();
        for (Friend friend : followList){
            List<String> messageList = friend.accept(currentUser);
            for (String s : messageList) {
                items.add(((User) friend).getUserID() + ": " + s);
                followerPost.setItems(items);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         items = FXCollections.observableArrayList ();

    }
}
