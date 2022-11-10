package com.example.cs3560a2;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TweetPage implements Initializable {
    private User currentUser;
    public TweetPage(User user){
        currentUser = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(currentUser.getUserID());
    }
}
