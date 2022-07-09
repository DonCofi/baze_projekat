package model;

import observer.ObserverEventType;
import observer.ObserverNotification;

import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;

public class InformationResource extends MutableNode {
    private static InformationResource instance=null;
    private String name;

    private InformationResource(){
        children=new ArrayList<>();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void insert(MutableTreeNode node, int index) {

        if (node instanceof Entity) {
            Entity e = (Entity) node;
            e.setParent(this);
            if (!children.contains(e)) {
                children.add(index, e);
            }
            notifyObservers(new ObserverNotification(e, ObserverEventType.ADD));
            for (int i = 0; i < e.getChildCount(); i++) {
                e.insert((MutableTreeNode) e.getChildAt(i), i);
            }
        }
    }

    @Override
    public void remove(MutableTreeNode node) {
        if(node instanceof Entity) {
            Entity e = (Entity) node;
            children.remove(e);
        }
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        return;
    }

    public void setName(String name){
        this.name=name;
    }

    public static InformationResource getInstance(){
        if(instance==null){
            instance=new InformationResource();
        }
        return instance;
    }
}
