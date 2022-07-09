package controller;

import view.FilterView;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class DoFilterSort extends AbstractAction {
    private FilterView fv;
    private List<String> filters;
    private List<String> sorts;
    private List<String> orders;

    public DoFilterSort(FilterView fv){
        this.fv=fv;
        filters=new ArrayList<>();
        sorts=new ArrayList<>();
        orders=new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<fv.getColumnsToFilter().size();i++){
            if(fv.getColumnsToFilter().get(i).isSelected()){
                filters.add(fv.getColumnsToFilter().get(i).getText());
            }
        }
        for(int i=0;i<fv.getColumnsToSort().size();i++){
            if(fv.getColumnsToSort().get(i).isSelected()){
                sorts.add(fv.getColumnsToSort().get(i).getText());
            }
        }
        for(int i=0;i<fv.getAscentions().size();i++){
            if(fv.getAscentions().get(i).isSelected()){
                orders.add("asc");
            }else if(fv.getDescentions().get(i).isSelected()){
                orders.add("desc");
            }
        }
        MainFrame.getInstance().getInit().filterData(filters, sorts, orders);
        fv.dispatchEvent(new WindowEvent(fv, WindowEvent.WINDOW_CLOSING));
    }
}
