package listeners;

import view.MainFrame;
import view.SearchPanel;
import view.FilterView;
import view.SearchView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class CommitListener implements ActionListener {

    private String column;
    private String operation;
    private String value;
    private String concat;
    private SearchPanel sp;
    private JPanel glavni;
    private JFrame frame;
    private SearchView sv;

    public CommitListener(SearchPanel sp, JPanel glavni, JFrame frame, SearchView sv){
        this.sp=sp;
        this.glavni=glavni;
        this.frame=frame;
        this.sv=sv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int commit=0;
        column=(String)sp.getColumns().getSelectedItem();
        operation=(String) sp.getOperations().getSelectedItem();
        value=sp.getValue().getText();
        concat=(String) sp.getAndOr().getSelectedItem();
        if(column!=null && operation!=null && value!=null && concat!=null){
            if(concat.equals("AND") || concat.equals("OR")){
                sp = new SearchPanel(frame);
                glavni.add(sp, BorderLayout.SOUTH);
                glavni.updateUI();
                sv.setSp(sp);
            }else{
                commit=1;
            }
            MainFrame.getInstance().getInit().buildSearchQuery(column, operation, value, concat, commit);
            if(commit==1){
                sv.dispatchEvent(new WindowEvent(sv, WindowEvent.WINDOW_CLOSING));
            }
        }else{
            System.out.print("No data entered");
            sv.dispatchEvent(new WindowEvent(sv, WindowEvent.WINDOW_CLOSING));
        }
    }
}
