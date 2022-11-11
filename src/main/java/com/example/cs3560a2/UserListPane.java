package com.example.cs3560a2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserListPane implements Observer{
    @FXML
    private TreeView userPane;
    private HashMap<String, TreeItem> paneList;

    public UserListPane(TreeView userPane){
        this.userPane = userPane;
        paneList = new HashMap<>();
        System.out.println(userPane.getId());
    }

    @Override
    public void update(Subject subject) {
        if(subject instanceof Group){
            HashMap<String, List<User>> groupList = ((Group) subject).getGroupList();
            userPane.getRoot().getChildren().clear();
            groupList.forEach((key, value)->{
                TreeItem ti;
                if(!paneList.containsKey(key)){
                    if(key.equals("Root")){
                       ti = userPane.getRoot();
                    }else{
                        ti = new TreeItem(key);
                        userPane.getRoot().getChildren().setAll(ti);
                    }

                    paneList.put(key, ti);

                }else{
                    ti = paneList.get(key);

                    if(!key.equals("Root")){
                        userPane.getRoot().getChildren().setAll(ti);
                    }
                }

                value.forEach((user)->{
                    TreeItem newUser = new TreeItem<>(user.getUserID());
                    ti.getChildren().addAll(newUser);
                });

            });
        }
    }


}
