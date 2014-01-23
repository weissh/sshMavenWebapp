package service.impl;

import dao.CostDao;
import pojos.Cost;
import service.CostService;

public class CostServiceImpl extends GenericServiceImpl<Cost> implements CostService{

	private CostDao costDao;

	public CostDao getCostDao() {
		return costDao;
	}

	public void setCostDao(CostDao costDao) {
		this.costDao = costDao;
	}
	
}
