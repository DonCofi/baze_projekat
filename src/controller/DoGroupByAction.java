package controller;

import view.GroupByView;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class DoGroupByAction extends AbstractAction {

    private GroupByView gv;
    private List<String> columns;

    public DoGroupByAction(GroupByView gv){
        this.gv=gv;
        columns=new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gv.getColumns().size()==0){
            System.out.println("No columns selected");
        }
        MainFrame.getInstance().getInit().addColumnGrouping(gv.getColumns());

        gv.dispatchEvent(new WindowEvent(gv, WindowEvent.WINDOW_CLOSING));
    }
}
