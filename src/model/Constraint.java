package model;

import enumerations.ConstraintType;

import javax.swing.tree.MutableTreeNode;

public class Constraint extends MutableNode {

    private ConstraintType ct;

    public Constraint(ConstraintType ct){
        this.ct=ct;
        children=null;
    }

    public ConstraintType getCt(){
        return ct;
    }

    @Override
    public String toString() {
        return ct.toString();
    }

    @Override
    public void insert(MutableTreeNode node, int index) {
        return;
    }

    @Override
    public void remove(MutableTreeNode node) {
        return;
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        if(newParent instanceof Attribute) {
            if(parent != null) {
                parent.removeFromParent();
            }
            parent = (Attribute)newParent;
        }
    }
}
