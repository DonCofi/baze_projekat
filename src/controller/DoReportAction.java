package controller;

import view.MainFrame;
import view.ReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class DoReportAction extends AbstractAction {

    private JButton report;
    private ReportView rv;
    private String col;

    public DoReportAction(JButton report, ReportView rv){
        this.report=report;
        this.rv=rv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<rv.getColumns().size();i++){
            if(rv.getColumns().get(i).isSelected()){
                col=rv.getColumns().get(i).getText();
            }
        }
        if(col==null){
            System.out.println("No column selected");
            return;
        }
        MainFrame.getInstance().getInit().addColumnAggregation(report, col);

        rv.dispatchEvent(new WindowEvent(rv, WindowEvent.WINDOW_CLOSING));
    }
}
