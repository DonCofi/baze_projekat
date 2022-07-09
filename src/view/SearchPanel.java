package view;

import listeners.SearchOperationListener;
import model.Entity;
import model.InformationResource;
import view.FilterView;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {

    private JFrame frame;
    private JComboBox<String> columns;
    private JComboBox<String> operations;
    private JTextField value;
    private JComboBox<String> AndOr;

    public SearchPanel(JFrame frame){
        this.frame=frame;

        columns = new JComboBox<String>();
        columns.addItem("");
        operations = new JComboBox<String>();
        operations.setSelectedItem("");
        value = new JTextField(10);

        if(frame instanceof FilterView){
            for(int i = 0; i< ((FilterView) frame).getColumnsToFilter().size(); i++){
                columns.addItem(((FilterView) frame).getColumnsToFilter().get(i).getText());
            }
        }else if(frame instanceof UpdateView){
            for(int i=0;i<((UpdateView)frame).getColumns().size();i++){
                columns.addItem(((UpdateView)frame).getColumns().get(i).getText());
            }
        }else if(frame instanceof DeleteView){
            InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
            Entity entity=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
            for(int i=0;i< entity.getChildCount();i++){
                columns.addItem(ir.getChildWithName(entity.toString()).getChildAt(i).toString());
            }
        }
        columns.addItemListener(new SearchOperationListener(columns, operations));

        AndOr = new JComboBox<String>();
        AndOr.addItem("");
        AndOr.addItem("AND");
        AndOr.addItem("OR");
        AndOr.addItem("/");

        this.setLayout(new BorderLayout());
        this.add(columns, BorderLayout.WEST);
        this.add(operations, BorderLayout.CENTER);
        this.add(value, BorderLayout.EAST);
        this.add(AndOr, BorderLayout.SOUTH);
    }

    public JComboBox<String> getColumns()
    {
        return columns;
    }
    public JComboBox<String> getOperations()
    {
        return operations;
    }
    public JTextField getValue()
    {
        return value;
    }
    public JComboBox<String> getAndOr()
    {
        return AndOr;
    }
}
