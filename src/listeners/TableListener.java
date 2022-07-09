package listeners;

import app.Init;
import model.*;
import tree.TreeMenu;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableListener extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getClickCount()==2 && SwingUtilities.isLeftMouseButton(e)){
            TreeMenu tm= MainFrame.getInstance().getTm();
            MutableNode selected=tm.getSelectedNode();
            if(selected instanceof Entity){
                Entity entity=(Entity)selected;
                TableModel model=entity.getTable();
                Init init=MainFrame.getInstance().getInit();
                init.setTableModel(model);
                init.readData(entity.toString());
            }
        }
    }
}
