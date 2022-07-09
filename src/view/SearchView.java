package view;

import listeners.CommitListener;

import javax.swing.*;
import java.awt.*;

public class SearchView extends JFrame {

    private SearchPanel sp;
    private JPanel glavni;
    private JButton commit;
    private JFrame frame;

    public SearchView(JFrame frame){
        this.frame=frame;
        this.setLayout(new BorderLayout());
        this.setSize(1000,800);
        this.setLocationRelativeTo(null);
        glavni = new JPanel();
        sp = new SearchPanel(frame);
        commit = new JButton("Commit");
        commit.addActionListener(new CommitListener(sp, glavni, frame, this));
        glavni.add(sp, BorderLayout.EAST);
        this.add(commit, BorderLayout.NORTH);
        this.add(glavni, BorderLayout.CENTER);
    }

    public void setSp(SearchPanel sp){
        this.sp=sp;
    }
}
