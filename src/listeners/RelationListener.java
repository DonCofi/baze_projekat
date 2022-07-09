package listeners;

import app.Init;
import enumerations.ConstraintType;
import model.*;
import view.MainFrame;
import view.TableTabbedPane;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RelationListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
        InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
        Entity entity=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
        TableTabbedPane relations=MainFrame.getInstance().getRelations();
        openRelatedTables(ir, entity, relations);
    }

    public void openRelatedTables(InformationResource ir, Entity e, TableTabbedPane relations){
        String pk=new String();
        Init init=MainFrame.getInstance().getInit();
        for(int i=0;i<relations.getComponentCount();i++){
            relations.closeTable();
        }
        for(MutableNode a:e.getChildren()){
            Attribute attribute=(Attribute)a;
            for(MutableNode c:attribute.getChildren()){
                Constraint constraint=(Constraint)c;
                if(constraint.getCt().equals(ConstraintType.PRIMARY_KEY)){
                    pk=attribute.toString();
                }
            }
        }
        for(MutableNode en:ir.getChildren()){
            Entity entity=(Entity)en;
            for(MutableNode a:entity.getChildren()){
                Attribute attribute=(Attribute)a;
                for(MutableNode c:attribute.getChildren()){
                    Constraint constraint=(Constraint)c;
                    if(constraint.getCt().equals(ConstraintType.FOREIGN_KEY) && attribute.toString().equals(pk)){
                        TableModel model=entity.getTable();
                        init.setTableModel(model);
                        init.readData(entity.toString());
                        relations.openSelectedTable(entity);
                    }
                }
            }
        }
        init.setTableModel(e.getTable());
    }
}
