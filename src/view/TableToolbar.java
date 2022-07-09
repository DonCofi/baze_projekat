package view;

import controller.ActionManager;

import javax.swing.*;

public class TableToolbar extends JToolBar {

    public TableToolbar(){
        super(SwingConstants.HORIZONTAL);

        for(AbstractAction a: ActionManager.getInstance().getActions()){
            add(a);
        }
    }
}
