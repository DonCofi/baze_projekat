package model;

import observer.ObserverEventType;
import observer.ObserverNotification;

import javax.swing.*;
import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;

public class Entity extends MutableNode {

    private TableModel model;
    private String name;

    public Entity(String name){
        this.name=name;
        children=new ArrayList<>();
        model=new TableModel();
    }

    public TableModel getTable(){
        return model;
    }

    public void setTable(TableModel table){
        this.model=table;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void insert(MutableTreeNode node, int index) {
        if(node instanceof Attribute) {
            Attribute a = (Attribute) node;
            a.setParent(this);
            if(!children.contains(a)) {
                children.add(index, a);
            }
            notifyObservers(new ObserverNotification(a, ObserverEventType.ADD));
        }
    }

    @Override
    public void remove(MutableTreeNode node) {
        if(node instanceof Attribute) {
            Attribute a = (Attribute) node;
            children.remove(a);
        }
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        if(newParent instanceof InformationResource) {
            if(parent != null) {
                parent.removeFromParent();
            }
            parent = (InformationResource)newParent;
        }
    }
}
