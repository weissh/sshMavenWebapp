package service.impl;

import dao.RightDao;
import pojos.Right;
import service.RightService;

public class RightServiceImpl extends GenericServiceImpl<Right> implements RightService{

	private RightDao rightDao;

	public RightDao getRightDao() {
		return rightDao;
	}

	public void setRightDao(RightDao rightDao) {
		this.rightDao = rightDao;
	}
	
}
