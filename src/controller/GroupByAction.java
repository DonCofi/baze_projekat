package controller;

import view.FilterView;
import view.GroupByView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GroupByAction extends AbstractAction {

    private FilterView fv;

    public GroupByAction(FilterView fv){
        this.fv=fv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GroupByView gv=new GroupByView(fv);
        gv.setVisible(true);
    }
}
