package BusinessLayer;

import PresentationLayer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private List<Observer> observers;

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addObserver(Observer observer) {
        observers = new ArrayList<>();
        observers.add(observer);
    }
}
