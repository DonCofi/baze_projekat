package controller;

import view.FilterView;
import view.SearchView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SearchAction extends AbstractAction {

    private JFrame frame;

    public SearchAction(JFrame frame){
        this.frame=frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SearchView sv=new SearchView(frame);
        sv.setVisible(true);
    }
}
