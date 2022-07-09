package view;

import controller.DoInsertAction;
import model.Entity;
import model.InformationResource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InsertView extends JFrame {

    List<JLabel> columns;
    List<JTextField> values;

    public InsertView(){
        setTitle("Sort and filter");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(new Dimension(screenSize.width/2, screenSize.height/2));

        columns=new ArrayList<>();
        values=new ArrayList<>();

        InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
        Entity e=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());

        this.setLayout(new GridLayout(e.getChildCount()+1, 2));

        for(int i=0;i<ir.getChildWithName(e.toString()).getChildCount();i++){
            JLabel col=new JLabel(ir.getChildWithName(e.toString()).getChildAt(i).toString());
            columns.add(col);
            add(col);
            JTextField field=new JTextField();
            values.add(field);
            add(field);
        }
        JButton doInsert=new JButton("OK");
        doInsert.addActionListener(new DoInsertAction(this));
        add(doInsert);

        setLocationRelativeTo(null);
    }

    public List<JLabel> getColumns(){
        return columns;
    }

    public List<JTextField> getValues(){
        return values;
    }
}
