package app;

import view.MainFrame;

public class AppCore {
    public static void main(String[] args) {
        Init init=new Init();
        MainFrame application=MainFrame.getInstance();
        application.setInit(init);
        application.getInit().loadResource();
        application.setVisible(true);
    }
}
