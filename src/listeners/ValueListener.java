package listeners;

import app.Init;
import enumerations.ConstraintType;
import model.*;
import view.EntityView;
import view.MainFrame;
import view.TableTabbedPane;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ValueListener extends MouseAdapter {

    private JTable table;

    public ValueListener(JTable table){
        this.table=table;
    }

    @Override
    public void mousePressed(MouseEvent e){
        int i=table.getSelectedRow();

        String pk=new String();
        InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
        Entity entity=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
        for(MutableNode a:entity.getChildren()){
            Attribute attribute=(Attribute)a;
            for(MutableNode c:attribute.getChildren()){
                Constraint constraint=(Constraint)c;
                if(constraint.getCt().equals(ConstraintType.PRIMARY_KEY)){
                    pk=attribute.toString();
                }
            }
        }
        int j=0;
        for(int k=0;k<table.getColumnCount();k++){
            if(table.getColumnName(k).equals(pk)){
                j=k;
                break;
            }
        }
        String value=(String) table.getValueAt(i, j);
        Init init=MainFrame.getInstance().getInit();
        TableTabbedPane relations=MainFrame.getInstance().getRelations();
        for(int br=0;br<relations.getComponentCount();br++){
            EntityView view=relations.getCurrentView();
            Entity ent=view.getEntity();
            init.setTableModel(ent.getTable());
            init.setColumns(ent.toString(), pk, value);
        }
        init.setTableModel(entity.getTable());
    }
}
