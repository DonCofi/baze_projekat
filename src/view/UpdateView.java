package view;

import controller.DoInsertAction;
import controller.DoUpdateAction;
import controller.SearchAction;
import model.Entity;
import model.InformationResource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateView extends JFrame {

    private List<JCheckBox> columns;
    private List<JTextField> operations;

    public UpdateView(){
        setTitle("Update table");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(new Dimension(screenSize.width/2, screenSize.height/2));

        columns=new ArrayList<>();
        operations=new ArrayList<>();

        Entity e=(Entity) MainFrame.getInstance().getTm().getSelectedNode();

        this.setLayout(new GridLayout(e.getChildCount()+2, 2));
        InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
        Entity entity=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
        for(int i=0;i<ir.getChildWithName(entity.toString()).getChildCount();i++) {
            JCheckBox column = new JCheckBox(ir.getChildWithName(entity.toString()).getChildAt(i).toString());
            columns.add(column);
            add(column);
            JTextField operation = new JTextField();
            operations.add(operation);
            add(operation);
        }
        JButton doSearch=new JButton("Search");
        JButton doInsert=new JButton("OK");
        doSearch.addActionListener(new SearchAction(this));
        doInsert.addActionListener(new DoUpdateAction(this));
        add(doSearch);
        add(doInsert);

        setLocationRelativeTo(null);
    }

    public List<JCheckBox> getColumns(){
        return columns;
    }

    public List<JTextField> getOperations(){
        return operations;
    }
}
