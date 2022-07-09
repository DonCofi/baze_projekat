package controller;

import enumerations.AttributeType;
import model.Attribute;
import model.Entity;
import model.InformationResource;
import view.InsertView;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoInsertAction extends AbstractAction {

    private InsertView iv;
    private List<String> columns;
    private List<Object> values;

    public DoInsertAction(InsertView iv){
        this.iv=iv;
        columns=new ArrayList<>();
        values=new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
        Entity entity=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
        for(int i=0;i<ir.getChildWithName(entity.toString()).getChildCount();i++){
            columns.add(ir.getChildWithName(entity.toString()).getChildAt(i).toString());
        }
        for(int i=0;i<iv.getValues().size();i++){
            Attribute attribute=(Attribute)entity.getChildAt(i);
            AttributeType type=attribute.getType();
            Object val=iv.getValues().get(i).getText();
            if(val==null){
                System.out.print("Invalid values");
                return;
            }
            if(type == AttributeType.VARCHAR || type == AttributeType.TEXT || type == AttributeType.CHAR || type == AttributeType.NVARCHAR || type==AttributeType.DATE || type==AttributeType.DATETIME){
                values.add("'"+val+"'");
            }else if((val instanceof Float || val instanceof Double) && (type==AttributeType.FLOAT || type==AttributeType.REAL || type==AttributeType.DECIMAL || type == AttributeType.NUMERIC)){
                values.add(val);
            }else{
                values.add(val);
            }
        }
        MainFrame.getInstance().getInit().insertData(columns, values);
        MainFrame.getInstance().getInit().readData(entity.toString());
        iv.dispatchEvent(new WindowEvent(iv, WindowEvent.WINDOW_CLOSING));
    }
}
