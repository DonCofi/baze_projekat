package view;

import app.Init;
import enumerations.ConstraintType;
import model.*;
import observer.IViewObserver;
import observer.ObserverEventType;
import observer.ObserverNotification;

import javax.swing.*;
import java.awt.*;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class TableTabbedPane extends JTabbedPane implements IViewObserver {

    private List<EntityView> tables;

    public TableTabbedPane(){
        super();
        tables=new ArrayList<>();
        setTabLayoutPolicy(SCROLL_TAB_LAYOUT);
    }

    private void createEntityView(Entity e){
        EntityView view=new EntityView(e);
        e.addObserver(this);
        tables.add(view);
        this.add(view);
    }

    private EntityView getViewForE(Entity e){
        for(EntityView view:tables){
            if(view.getEntity()==e){
                return view;
            }
        }
        return null;
    }

    public void openSelectedTable(MutableNode node){
        if(node instanceof Entity){
            Entity e=(Entity)node;
            EntityView view=getViewForE(e);
            if(!(tables.contains(view))){
                createEntityView(e);
            }else if(view!=null && this.indexOfComponent(view)==-1){
                addTab(e.toString(), view);
            }
        }
    }

    public void closeTable(){
        int index=this.getSelectedIndex();
        if(index!=-1){
            this.removeTabAt(index);
        }
    }

    public EntityView getCurrentView(){
        Component curr=this.getSelectedComponent();
        if(curr instanceof EntityView){
            return  (EntityView)curr;
        }
        return null;
    }

    @Override
    public void update(ObserverNotification event) {
        if(event.getModelObserver() instanceof Entity){
            Entity e=(Entity)event.getModelObserver();
            if(event.getEventType()== ObserverEventType.ADD){
                createEntityView(e);
            }
            EntityView view=getViewForE(e);
            int index=this.indexOfComponent(view);
            if(event.getEventType()==ObserverEventType.RENAME){
                if(index!=-1){
                    this.setTitleAt(index, e.toString());
                }
            }else if(event.getEventType()==ObserverEventType.REMOVE){
                tables.remove(view);
                if(index!=-1){
                    this.removeTabAt(index);
                }
            }
        }
    }
}
