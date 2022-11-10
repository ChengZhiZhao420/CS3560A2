package com.example.cs3560a2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserListPane implements Observer{
    @FXML
    private VBox userPane;
    private List<String> paneList;

    public UserListPane(VBox userPane){
        this.userPane = userPane;
        paneList = new ArrayList<>();
        System.out.println(userPane.getId());
    }

    @Override
    public void update(Subject subject) {
        if(subject instanceof Group){
            HashMap<String, List<User>> groupList = ((Group) subject).getGroupList();

            groupList.forEach((key, value)->{
                VBox newHBox;
                if(!paneList.contains(key)){
                    TitledPane newTitlePane = new TitledPane();
                    newHBox = new VBox();
                    newHBox.setId("box");
                    newTitlePane.setText(key);
                    newTitlePane.setId(key);
                    newTitlePane.setContent(newHBox);

                    paneList.add(key);
                    userPane.getChildren().add(newTitlePane);

                }else{
                    newHBox = (VBox) userPane.lookup("#" + key).lookup("#box");
                    newHBox.getChildren().clear();
                }

                value.forEach((user)->{
                    Button userID = new Button(user.getUserID());
                    userID.setOnAction(e->{
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("tweetPage.fxml"));
                        Stage stage = new Stage();
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 600, 700);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        stage.setTitle("Hello!");
                        stage.setScene(scene);
                        stage.show();
                    });
                    newHBox.getChildren().add(userID);
                });

            });
        }
    }
}
