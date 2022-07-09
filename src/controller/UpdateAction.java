package controller;

import help.ImageResizer;
import model.Entity;
import view.MainFrame;
import view.UpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UpdateAction extends AbstractAction {

    public UpdateAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, ImageResizer.getInstance().loadSmallIcon("images/update.png"));
        putValue(LARGE_ICON_KEY, ImageResizer.getInstance().loadBigIcon("images/update.png"));
        putValue(NAME, "Update");
        putValue(SHORT_DESCRIPTION, "Update a table column.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(MainFrame.getInstance().getTm().getSelectedNode() instanceof Entity)){
            System.out.print("A table must be selected for this action\n");
            return;
        }
        UpdateView uv=new UpdateView();
        uv.setVisible(true);
    }
}
