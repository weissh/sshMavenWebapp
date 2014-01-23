package dao.hibernate;

import pojos.Cost;
import dao.CostDao;

public class CostDaoHib extends GenericDaoHib<Cost> implements CostDao {

	public CostDaoHib() {
		super(Cost.class);
	}

}
