package model;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class AttributeModel extends DefaultTreeModel {
    public AttributeModel(Attribute a) {
        super(a);
    }
}
