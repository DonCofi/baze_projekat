package view;

import controller.*;
import model.Entity;
import model.InformationResource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FilterView extends JFrame {
    private List<JCheckBox> columnsToFilter;
    private List<JCheckBox> columnsToSort;
    private List<JRadioButton> ascentions;
    private List<JRadioButton> descentions;

    public FilterView(){
        setTitle("Sort and filter");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(new Dimension(screenSize.width/2, screenSize.height/2));

        columnsToFilter=new ArrayList<>();
        columnsToSort=new ArrayList<>();
        ascentions=new ArrayList<>();
        descentions=new ArrayList<>();

        this.setLayout(new GridLayout(3, 3));

        JLabel filter=new JLabel("Select");
        add(filter);
        JLabel sort=new JLabel("Order by");
        add(sort);
        JLabel ok=new JLabel("ASCENDING/DESCENDING");
        add(ok);

        InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
        Entity entity=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
        JPanel panelFilter=new JPanel(new GridLayout(entity.getChildCount(), 0));
        for(int i=0;i<entity.getChildCount();i++){
            JCheckBox col=new JCheckBox(ir.getChildWithName(entity.toString()).getChildAt(i).toString());
            panelFilter.add(col);
            columnsToFilter.add(col);
        }
        add(panelFilter);

        JPanel panelSort=new JPanel(new GridLayout(entity.getChildCount(), 0));
        for(int i=0;i<entity.getChildCount();i++){
            JCheckBox col=new JCheckBox(ir.getChildWithName(entity.toString()).getChildAt(i).toString());
            panelSort.add(col);
            columnsToSort.add(col);
        }
        add(panelSort);

        JPanel panelOrder=new JPanel(new GridLayout(entity.getChildCount(), 2));
        for(int i=0;i<entity.getChildCount();i++){
            JRadioButton asc=new JRadioButton("ascending");
            ascentions.add(asc);
            JRadioButton desc=new JRadioButton("descending");
            descentions.add(desc);
            ButtonGroup filterColumns=new ButtonGroup();
            filterColumns.add(asc);
            filterColumns.add(desc);
            panelOrder.add(asc);
            panelOrder.add(desc);
        }
        add(panelOrder);

        JPanel panelReportF=new JPanel(new GridLayout(2, 0));
        JButton countF=new JButton("COUNT");
        countF.setName("Fcount");
        countF.addActionListener(new ReportAction(countF));
        JButton avarageF=new JButton("AVARAGE");
        avarageF.setName("Favarage");
        avarageF.addActionListener(new ReportAction(avarageF));
        panelReportF.add(countF);
        panelReportF.add(avarageF);
        add(panelReportF);

        JPanel panelReportS=new JPanel(new GridLayout(2, 0));
        JButton countS=new JButton("COUNT");
        countS.addActionListener(new ReportAction(countS));
        countS.setName("Scount");
        JButton avarageS=new JButton("AVARAGE");
        avarageS.setName("Savarage");
        avarageS.addActionListener(new ReportAction(avarageS));
        panelReportS.add(countS);
        panelReportS.add(avarageS);
        add(panelReportS);

        JPanel panelOK=new JPanel(new GridLayout(2, 1));
        JButton doSearch=new JButton("Search");
        JButton groupBy=new JButton("GROUP BY");
        groupBy.addActionListener(new GroupByAction(this));
        doSearch.addActionListener(new SearchAction(this));
        JButton cancel=new JButton("Cancel");
        JButton doFilter=new JButton("OK");
        cancel.addActionListener(new CancelAction(this));
        doFilter.addActionListener(new DoFilterSort(this));
        panelOK.add(doSearch);
        panelOK.add(groupBy);
        panelOK.add(cancel);
        panelOK.add(doFilter);
        add(panelOK);

        setLocationRelativeTo(null);
    }

    public List<JCheckBox> getColumnsToFilter(){
        return columnsToFilter;
    }

    public List<JCheckBox> getColumnsToSort(){
        return columnsToSort;
    }

    public List<JRadioButton> getAscentions(){
        return ascentions;
    }

    public List<JRadioButton> getDescentions(){
        return descentions;
    }
}
