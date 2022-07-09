package model;

import enumerations.AttributeType;
import enumerations.ConstraintType;
import observer.ObserverEventType;
import observer.ObserverNotification;

import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;

public class Attribute extends MutableNode {

    private Integer size;
    private AttributeType type;
    private String name;

    public Attribute(String name, Entity parent, AttributeType type, Integer size){
        this.name=name;
        this.parent=parent;
        setParent(parent);
        this.type=type;
        this.size=size;
        children=new ArrayList<>();
    }

    public AttributeType getType(){
        return type;
    }

    public Integer getSize(){
        return size;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void insert(MutableTreeNode node, int index) {
        if(node instanceof Constraint) {
            Constraint c = (Constraint) node;
            c.setParent(this);
            if(!children.contains(c)) {
                children.add(index, c);
            }
            notifyObservers(new ObserverNotification(c, ObserverEventType.ADD));
        }
    }

    @Override
    public void remove(MutableTreeNode node) {
        if(node instanceof Constraint) {
            Constraint c = (Constraint) node;
            children.remove(c);
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
