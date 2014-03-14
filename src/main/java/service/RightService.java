package service;

import java.util.List;

import pojos.Right;
import web.ui.TreeNode;

public interface RightService extends GenericService<Right>{

	public List<Right> getRightByRole();
	
	public List<TreeNode> getCheckedTree();
}
