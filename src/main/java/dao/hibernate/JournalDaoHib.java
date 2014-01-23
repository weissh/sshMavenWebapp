package dao.hibernate;

import pojos.Journal;
import dao.JournalDao;

public class JournalDaoHib extends GenericDaoHib<Journal> implements JournalDao{

	public JournalDaoHib() {
		super(Journal.class);
	}

}
