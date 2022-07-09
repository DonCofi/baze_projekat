package view;

import controller.DoReportAction;
import enumerations.AttributeType;
import model.Attribute;
import model.Entity;
import model.InformationResource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ReportView extends JFrame {

    private List<JRadioButton> columns;
    private JButton report;

    public ReportView(JButton report){
        setTitle("Sort and filter");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(new Dimension(screenSize.width/4, screenSize.height/3));

        this.report=report;
        columns=new ArrayList<>();

        Entity e=(Entity) MainFrame.getInstance().getTm().getSelectedNode();
        this.setLayout(new GridLayout(e.getChildCount()+1, 0));
        ButtonGroup group=new ButtonGroup();
        InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
        Entity entity=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
        for(int i=0;i<e.getChildCount();i++){
            if(report.getText()=="AVARAGE"){
                Attribute attribute=(Attribute)entity.getChildAt(i);
                AttributeType type=attribute.getType();
                if(type== AttributeType.FLOAT || type==AttributeType.REAL || type==AttributeType.DECIMAL || type == AttributeType.NUMERIC){
                    JRadioButton col=new JRadioButton(ir.getChildWithName(entity.toString()).getChildAt(i).toString());
                    columns.add(col);
                    this.add(col);
                    group.add(col);
                }
            }else{
                JRadioButton col=new JRadioButton(ir.getChildWithName(entity.toString()).getChildAt(i).toString());
                columns.add(col);
                this.add(col);
                group.add(col);
            }
        }
        JButton doReport=new JButton("OK");
        doReport.addActionListener(new DoReportAction(report, this));
        this.add(doReport);

        setLocationRelativeTo(null);
    }

    public List<JRadioButton> getColumns(){
        return columns;
    }
}
