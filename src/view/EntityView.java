package view;

import app.Init;
import com.sun.tools.javac.Main;
import listeners.ValueListener;
import model.Entity;
import model.TableModel;
import observer.IViewObserver;
import observer.ObserverEventType;
import observer.ObserverNotification;

import javax.swing.*;
import java.awt.*;

public class EntityView extends JPanel implements IViewObserver {
    private Entity e;
    private JTable table;

    public EntityView(Entity e){
        super();
        this.e=e;
        table=new JTable();
        table.addMouseListener(new ValueListener(table));
        table.setPreferredScrollableViewportSize(new Dimension(1680,400));
        table.setFillsViewportHeight(true);
        JScrollPane tb=new JScrollPane(table);
        e.addObserver(this);
        setName(e.toString());
        table.setModel(MainFrame.getInstance().getInit().getTableModel());
        add(tb, BorderLayout.CENTER);
    }

    public Entity getEntity(){
        return e;
    }

    @Override
    public void update(ObserverNotification event) {
        if(event.getModelObserver() instanceof Entity){
            setName(e.toString());
            MainFrame.getInstance().getInit().readData(e.toString());
            table.setModel(MainFrame.getInstance().getInit().getTableModel());
        }
        if(event.getEventType()== ObserverEventType.ADD){
            event.getModelObserver().addObserver(this);
        }

    }
}
