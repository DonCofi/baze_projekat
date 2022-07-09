package model;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class EntityModel extends DefaultTreeModel {
    public EntityModel(Entity e) {
        super(e);
    }
}
