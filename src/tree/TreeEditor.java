package tree;

import exceptions.ExceptionHandler;
import model.MutableNode;

import javax.naming.InvalidNameException;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class TreeEditor extends DefaultTreeCellEditor implements ActionListener {

    private MutableNode node;
    private JTextField textBox=null;
    public TreeEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    public Component getTreeCellEditorComponent(JTree tree, Object node, boolean isSelected, boolean isExpanded, boolean isLeaf, int row) {
        this.node=(MutableNode) node;
        textBox=new JTextField(node.toString());
        textBox.addActionListener(this);
        return textBox;
    }

    public boolean canEditImmediately(EventObject event) {
        if(event instanceof MouseEvent) {
            return ((MouseEvent)event).getClickCount() == 3;
        }
        return super.canEditImmediately(event);
    }

    public void actionPerformed(ActionEvent event){
        String newName = event.getActionCommand();
        try {
            if(newName.isEmpty() || !Character.isLetterOrDigit(newName.charAt(0))) {
                throw new InvalidNameException();
            }
        }
        catch (NullPointerException e) {
            newName = node.toString();
        }
        catch (Exception e) {
            newName = ExceptionHandler.createMessageDialog(e);
            if(newName == null || newName.isEmpty() || !Character.isLetterOrDigit(newName.charAt(0))) {
                newName = node.toString();
            }
        }
        finally {
            if(textBox == null || newName == null) return;
            textBox.setText(newName);
            node.setName(newName);
            stopCellEditing();
        }
    }
}
