package controller;

import view.FilterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class CancelAction extends AbstractAction {

    private FilterView fv;

    public CancelAction(FilterView fv){
        this.fv=fv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fv.dispatchEvent(new WindowEvent(fv, WindowEvent.WINDOW_CLOSING));
    }
}
