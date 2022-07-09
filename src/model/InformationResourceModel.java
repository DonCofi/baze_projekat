package model;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class InformationResourceModel extends DefaultTreeModel {
    public InformationResourceModel(InformationResource ir) {
        super(ir);
    }
}
