package controller;

import model.Entity;
import view.DeleteView;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class DoDeleteAction extends AbstractAction {

    private DeleteView dv;

    public DoDeleteAction(DeleteView dv){
        this.dv=dv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Entity entity= (Entity)MainFrame.getInstance().getTm().getSelectedNode();
        MainFrame.getInstance().getInit().deleteData();
        MainFrame.getInstance().getInit().readData(entity.toString());
        dv.dispatchEvent(new WindowEvent(dv, WindowEvent.WINDOW_CLOSING));
    }
}
