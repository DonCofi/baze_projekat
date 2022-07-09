package controller;

import model.Entity;
import view.MainFrame;
import view.UpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class DoUpdateAction extends AbstractAction {

    private UpdateView uv;
    private List<String> cols;
    private List<String> values;

    public DoUpdateAction(UpdateView uv){
        this.uv=uv;
        cols=new ArrayList<>();
        values=new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<JCheckBox> columns=uv.getColumns();
        List<JTextField> operations=uv.getOperations();
        for(JCheckBox cb:columns){
            if(cb.isSelected()){
                cols.add(cb.getText());
            }
        }
        int i=0;
        for(JTextField tf:operations){
            if(columns.get(i).isSelected()){
                String val=tf.getText();
                if(val.equals("")){
                    System.out.println("No update data entered");
                    return;
                }
                char split[]=val.toCharArray();
                if(!(split[0]>='0' && split[0]<='9')){
                    val="'"+val+"'";
                }
                values.add(val);
            }
            i++;
        }
        Entity entity= (Entity)MainFrame.getInstance().getTm().getSelectedNode();
        MainFrame.getInstance().getInit().updateData(cols, values);
        MainFrame.getInstance().getInit().readData(entity.toString());
        uv.dispatchEvent(new WindowEvent(uv, WindowEvent.WINDOW_CLOSING));
    }
}
