package dao.hibernate;

import pojos.Right;
import dao.RightDao;

public class RightDaoHib extends GenericDaoHib<Right> implements RightDao{

	public RightDaoHib() {
		super(Right.class);
	}

}
