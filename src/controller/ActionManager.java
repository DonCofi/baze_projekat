package controller;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ActionManager {
    private static ActionManager instance=null;

    private List<AbstractAction> actions;

    private ActionManager(){
        actions=new ArrayList<>();

        actions.add(new FilterSortAction());
        actions.add(new InsertAction());
        actions.add(new UpdateAction());
        actions.add(new DeleteAction());
    }

    public List<AbstractAction> getActions(){
        return actions;
    }

    public static ActionManager getInstance(){
        if(instance==null){
            instance=new ActionManager();
        }
        return instance;
    }
}
