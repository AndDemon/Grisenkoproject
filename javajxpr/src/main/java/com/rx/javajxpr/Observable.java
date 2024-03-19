package com.rx.javajxpr;
import java.util.ArrayList;
import java.util.List;

// Інтерфейс спостерігача (Observer)
interface Observer {
    void update(List<Integer> data);
}

// Клас спостережуваного об'єкта (Observable)
public class Observable {
    private List<Observer> observers = new ArrayList<>();
    private List<Integer> collection = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void addData(int data) {
        collection.add(data);
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(collection);
        }
    }
}
