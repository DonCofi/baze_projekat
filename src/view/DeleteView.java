package view;

import controller.DoDeleteAction;
import controller.SearchAction;
import model.Entity;
import model.InformationResource;

import javax.swing.*;
import java.awt.*;

public class DeleteView extends JFrame {

    public DeleteView(){
        setTitle("Delete rows");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(new Dimension(screenSize.width/5, screenSize.height/5));

        this.setLayout(new GridLayout(2, 0));
        JButton doInsert=new JButton("OK");
        JButton doSearch=new JButton("Search");
        doSearch.addActionListener(new SearchAction(this));
        doInsert.addActionListener(new DoDeleteAction(this));
        add(doSearch);
        add(doInsert);

        setLocationRelativeTo(null);
    }
}
