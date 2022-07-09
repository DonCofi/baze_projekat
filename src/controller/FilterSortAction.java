package controller;

import help.ImageResizer;
import model.Entity;
import model.MutableNode;
import view.FilterView;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class FilterSortAction extends AbstractAction {
    public FilterSortAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, ImageResizer.getInstance().loadSmallIcon("images/filter-icon.png"));
        putValue(LARGE_ICON_KEY, ImageResizer.getInstance().loadBigIcon("images/filter-icon.png"));
        putValue(NAME, "Sort and filter");
        putValue(SHORT_DESCRIPTION, "Filter your data.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MutableNode node=MainFrame.getInstance().getTm().getSelectedNode();
        if(!(node instanceof Entity)){
            System.out.print("A table must be selected for this action\n");
            return;
        }
        MainFrame.getInstance().getInit().readData(node.toString());
        FilterView filterView=new FilterView();
        filterView.setVisible(true);
    }
}
