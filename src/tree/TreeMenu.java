package tree;

import listeners.TabListener;
import listeners.TableListener;
import model.InformationResource;
import model.InformationResourceModel;
import model.MutableNode;
import observer.IViewObserver;
import observer.ObserverEventType;
import observer.ObserverNotification;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;

public class TreeMenu extends JTree implements IViewObserver {
    public TreeMenu(){
        addTreeSelectionListener(new TreeController());
        setCellEditor(new TreeEditor(this, new TreeCellRenderer()));
        setCellRenderer(new TreeCellRenderer());
        setEditable(true);
        setInvokesStopCellEditing(true);

        this.addMouseListener(new TableListener());
        this.addMouseListener(new TabListener());
    }

    public void update(ObserverNotification event) {
        if(event.getEventType() == ObserverEventType.ADD) {
            event.getModelObserver().addObserver(this);
        }
        this.expandRow(0);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public MutableNode getSelectedNode() {
        return (MutableNode) this.getLastSelectedPathComponent();
    }

    public void resetSelectedNode() {
        this.setSelectionPath(null);
    }

    public MutableNode getRoot() {
        return (MutableNode) this.getModel().getRoot();
    }

    public void setRoot(InformationResource ws) {
        this.setModel(new InformationResourceModel(ws));
        ws.addObserver(this);
        for(int i = 0; i < ws.getChildCount(); i++) {
            MutableNode child = (MutableNode) ws.getChildAt(i);
            ws.insert(child, i);
        }
    }

    public void startEditing() {
        if(this.getSelectionPath() != null) {
            this.startEditingAtPath(this.getSelectionPath());
        }
    }
}
