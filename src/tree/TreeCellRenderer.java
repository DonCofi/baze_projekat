package tree;

import help.ImageResizer;
import model.Attribute;
import model.Constraint;
import model.Entity;
import model.InformationResource;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class TreeCellRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);

        if (value instanceof InformationResource) {
            setIcon(ImageResizer.getInstance().loadSmallIcon("images/workspace.png"));
        }

        else if (value instanceof Entity) {
            setIcon(ImageResizer.getInstance().loadSmallIcon("images/project.png"));
        }

        else if (value instanceof Attribute) {
            setIcon(ImageResizer.getInstance().loadSmallIcon("images/document.png"));
        }else if(value instanceof Constraint){
            setIcon(ImageResizer.getInstance().loadSmallIcon("images/document.png"));
        }

        setFont(new Font(getFont().getFamily(), Font.BOLD, 14));
        return this;
    }
}
