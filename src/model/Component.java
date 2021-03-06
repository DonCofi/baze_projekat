package model;

import observer.IModelObserver;
import observer.IViewObserver;
import observer.ObserverEventType;
import observer.ObserverNotification;

import java.util.ArrayList;
import java.util.List;

public abstract class Component implements IModelObserver {
    private String name;
    protected List<IViewObserver> viewObservers = new ArrayList<IViewObserver>();

    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyObservers(new ObserverNotification(this, ObserverEventType.RENAME));
    }

    private void createObserverList() {
        if(viewObservers==null) {
            viewObservers = new ArrayList<IViewObserver>();
        }
    }

    @Override
    public void addObserver(IViewObserver viewObserver) {
        createObserverList();
        if(viewObserver==null) {
            return;
        }
        if(this.viewObservers.contains(viewObserver)) {
            return;
        }
        this.viewObservers.add(viewObserver);
    }

    @Override
    public void removeObserver(IViewObserver viewObserver) {
        if(viewObserver==null || !viewObservers.contains(viewObserver)) {
            return;
        }
        viewObservers.remove(viewObserver);
    }

    @Override
    public void notifyObservers(ObserverNotification event) {
        createObserverList();
        for(IViewObserver viewObserver : viewObservers) {
            viewObserver.update(event);
        }
    }

    protected void clearObservers() {
        createObserverList();
        viewObservers.clear();
    }
}
