package exceptions;

import view.MainFrame;

import javax.naming.InvalidNameException;
import javax.swing.*;
import java.sql.DataTruncation;
import java.sql.SQLException;

public class ExceptionHandler {
    public static void createDialog(Exception e) {
        JFrame frame = MainFrame.getInstance();
        if(e instanceof DataTruncation){
             JOptionPane.showMessageDialog(frame, "Invalid data entered");
        } /*else if(e instanceof SQLException){
            JOptionPane.showMessageDialog(frame, "Invalid sql entered");
        } */else if(e instanceof Exception) {
            JOptionPane.showMessageDialog(frame,
                    e.getMessage(),
                    e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
        return;
    }

    public static String createMessageDialog(Exception e){
        JFrame frame = MainFrame.getInstance();
        if(e instanceof InvalidNameException) {
            return JOptionPane.showInputDialog(frame,
                    "Enter a valid name here, or click cancel to use the previously defined one.",
                    e.getMessage(),
                    JOptionPane.WARNING_MESSAGE
            );
        }
        return null;
    }
}
