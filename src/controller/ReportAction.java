package controller;

import view.ReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ReportAction extends AbstractAction {

    private JButton report;

    public ReportAction(JButton report){
        this.report=report;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ReportView rv=new ReportView(report);
        rv.setVisible(true);
    }
}
