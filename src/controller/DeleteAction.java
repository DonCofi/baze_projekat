package controller;

import help.ImageResizer;
import model.Entity;
import view.DeleteView;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractAction {

    public DeleteAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, ImageResizer.getInstance().loadSmallIcon("images/delete.png"));
        putValue(LARGE_ICON_KEY, ImageResizer.getInstance().loadBigIcon("images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete a table row.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(MainFrame.getInstance().getTm().getSelectedNode() instanceof Entity)){
            System.out.print("A table must be selected for this action\n");
            return;
        }
        DeleteView dv=new DeleteView();
        dv.setVisible(true);
    }
}
