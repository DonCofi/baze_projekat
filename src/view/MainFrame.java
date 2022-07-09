package view;

import app.Init;
import listeners.RelationListener;
import observer.IViewObserver;
import observer.ObserverNotification;
import tree.TreeMenu;
import tree.TreeModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance=null;
    private Menu menu;
    private TreeMenu tm;
    private TreeModel treeModel;
    private Init init;
    private TableTabbedPane ttPane;
    private TableTabbedPane relations;
    private TableToolbar ttBar;

    public MainFrame(){
        setTitle("Projekat");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(new Dimension(screenSize.width, screenSize.height-50));

        menu=new Menu();
        setJMenuBar(menu);

        tm=new TreeMenu();
        treeModel=new TreeModel();
        tm.setModel(treeModel);

        JScrollPane sp=new JScrollPane(tm);

        ttBar=new TableToolbar();
        add(ttBar, BorderLayout.NORTH);

        ttPane=new TableTabbedPane();
        ttPane.addChangeListener(new RelationListener());
        add(ttPane, BorderLayout.CENTER);
        relations=new TableTabbedPane();
        add(relations, BorderLayout.SOUTH);
        JSplitPane tables=new JSplitPane(JSplitPane.VERTICAL_SPLIT, ttPane, relations);
        tables.setDividerLocation(screenSize.height/2-60);

        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp, tables);
        add(split, BorderLayout.CENTER);
        split.setDividerLocation(screenSize.width/10);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public Init getInit(){
        return init;
    }

    public void setInit(Init init){
        this.init=init;
    }

    public TreeMenu getTm(){
        return tm;
    }

    public TableTabbedPane getTp(){
        return ttPane;
    }

    public TableTabbedPane getRelations() { return relations; }


    public static MainFrame getInstance(){
        if(instance==null){
            instance=new MainFrame();
        }
        return instance;
    }
}
