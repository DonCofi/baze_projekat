package listeners;

import model.Entity;
import model.MutableNode;
import view.MainFrame;
import view.TableTabbedPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabListener extends MouseAdapter {
    public void mousePressed(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1 && e.getClickCount()==2){
            MutableNode node= MainFrame.getInstance().getTm().getSelectedNode();
            if(node instanceof Entity) {
                TableTabbedPane ttPane = MainFrame.getInstance().getTp();
                ttPane.openSelectedTable(node);
            }
        }
    }
}
