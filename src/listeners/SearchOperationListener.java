package listeners;

import enumerations.AttributeType;
import model.Attribute;
import model.Entity;
import org.w3c.dom.Attr;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SearchOperationListener implements ItemListener {

    private JComboBox<String> columns;
    private JComboBox<String> operations;

    public SearchOperationListener(JComboBox<String> columns, JComboBox<String> operations){
        this.columns=columns;
        this.operations=operations;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Entity entity= (Entity)MainFrame.getInstance().getTm().getSelectedNode();
        if (e.getStateChange() == ItemEvent.SELECTED)
        {
            Attribute attribute=(Attribute) entity.getChildWithName(columns.getSelectedItem().toString());
            AttributeType type=attribute.getType();
            if(type == AttributeType.VARCHAR || type == AttributeType.TEXT || type == AttributeType.CHAR || type == AttributeType.NVARCHAR)
            {
                operations.removeAllItems();
                operations.addItem("=");
                operations.addItem("like");
            }
            else if(type==AttributeType.FLOAT || type==AttributeType.REAL || type==AttributeType.DECIMAL || type==AttributeType.INT || type==AttributeType.NUMERIC)
            {
                operations.removeAllItems();
                operations.addItem(">");
                operations.addItem("<");
                operations.addItem("=");
            }
            else {
                operations.removeAllItems();
            }
        }
    }
}
