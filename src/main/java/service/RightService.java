package service;

import java.util.List;

import pojos.Right;

public interface RightService extends GenericService<Right>{

	public List<Right> getRightByRole();
	
	public List<Right> getCheckedTree();
}
