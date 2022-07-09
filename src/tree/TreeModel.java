package tree;

import model.InformationResource;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class TreeModel extends DefaultTreeModel {
    public TreeModel() {
        super(InformationResource.getInstance());
    }
}
