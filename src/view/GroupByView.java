package view;

import controller.DoGroupByAction;
import controller.DoReportAction;
import model.Entity;
import model.InformationResource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GroupByView extends JFrame {

    private FilterView fv;
    private List<String> columns;

    public GroupByView(FilterView fv){
        setTitle("Sort and filter");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(new Dimension(screenSize.width/4, screenSize.height/3));

        this.fv=fv;
        columns=new ArrayList<>();

        InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
        Entity e=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
        this.setLayout(new GridLayout(e.getChildCount()+1, 0));
        for(int i=0;i<e.getChildCount();i++){
            JCheckBox col=new JCheckBox(ir.getChildWithName(e.toString()).getChildAt(i).toString());
            columns.add(col.getText());
            if(fv.getColumnsToFilter().get(i).isSelected()){
                col.setSelected(true);
            }
            this.add(col);
        }
        JButton doGrouping=new JButton("OK");
        doGrouping.addActionListener(new DoGroupByAction(this));
        this.add(doGrouping);

        setLocationRelativeTo(null);
    }

    public List<String> getColumns(){
        return columns;
    }
}
