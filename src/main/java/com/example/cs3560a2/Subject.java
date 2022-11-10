package com.example.cs3560a2;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observerList = new ArrayList<>();

    public void attachObserver(Observer observer){
        observerList.add(observer);
    }
    public void detachObserver(Observer observer){
        observerList.remove(observer);
    }

    public void notifyObserver(){
        for(Observer observer : observerList){
            observer.update(this);
        }
    }

}
