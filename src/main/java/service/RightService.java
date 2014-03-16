package service;

import java.util.List;

import pojos.Right;
import web.ui.TreeNode;
import web.ui.TreeStore;

public interface RightService extends GenericService<Right>{

	public TreeStore getRightByRole();
	
	public List<TreeNode> getCheckedTree(int roleId);
}
