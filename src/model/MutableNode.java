package model;

import observer.IViewObserver;
import observer.ObserverEventType;
import observer.ObserverNotification;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class MutableNode extends Component implements MutableTreeNode {
    protected List<MutableNode> children;
    protected MutableNode parent;

    public void addChild(MutableNode child){
        children.add(child);
    }

    @Override
    public void insert(MutableTreeNode child, int index) {
        children.add(index, (MutableNode)child);
    }

    @Override
    public void remove(int index) {
        children.remove(index);
    }

    @Override
    public void remove(MutableTreeNode node) {
        children.remove(node);
    }

    @Override
    public void setUserObject(Object object) {
        return;
    }

    @Override
    public void removeFromParent() {
        if(parent != null) {
            parent.remove(this);
            parent = null;
            notifyObservers(new ObserverNotification(this, ObserverEventType.REMOVE));
            clearObservers();
        }
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        this.parent= (MutableNode) newParent;
    }

    public TreeNode getChildWithName(String name){
        for(MutableNode mn:children){
            if(mn.toString().equals(name)){
                return  mn;
            }
        }
        return  null;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        if(children != null){
            return  children.get(childIndex);
        }
        return null;
    }

    @Override
    public int getChildCount() {
        if(children != null) {
            return children.size();
        }
        return 0;
    }

    public List<MutableNode> getChildren(){
        return this.children;
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        if(children != null) {
            return children.indexOf(node);
        }
        return -1;
    }

    @Override
    public boolean getAllowsChildren() {
        return children != null;
    }

    @Override
    public boolean isLeaf() {
        if(children != null) {
            return children.size() == 0;
        }
        return true;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return Collections.enumeration(children);
    }

    @Override
    public void removeObserver(IViewObserver viewObserver) {
        super.removeObserver(viewObserver);
        if(children != null) {
            for(MutableNode node : children) {
                node.removeObserver(viewObserver);
            }
        }
    }

    @Override
    public void notifyObservers(ObserverNotification event) {
        super.notifyObservers(event);
    }

    @Override
    protected void clearObservers() {
        super.clearObservers();
        if(children != null) {
            for(MutableNode node : children) {
                node.clearObservers();
            }
        }
    }
}
