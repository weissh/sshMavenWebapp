package service;

import pojos.Right;
import web.ui.TreeStore;

public interface RightService extends GenericService<Right>{

	public TreeStore getRightByRole();
	
	public TreeStore getCheckedTree(int roleId);
}
