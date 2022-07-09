package controller;

import help.ImageResizer;
import model.Entity;
import view.InsertView;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InsertAction extends AbstractAction {

    public InsertAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, ImageResizer.getInstance().loadSmallIcon("images/insert.png"));
        putValue(LARGE_ICON_KEY, ImageResizer.getInstance().loadBigIcon("images/insert.png"));
        putValue(NAME, "Insert");
        putValue(SHORT_DESCRIPTION, "Insert a row into a table.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(MainFrame.getInstance().getTm().getSelectedNode() instanceof Entity)){
            System.out.print("A table must be selected for this action\n");
            return;
        }
        InsertView iv=new InsertView();
        iv.setVisible(true);
    }
}
