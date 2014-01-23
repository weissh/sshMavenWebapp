package service.impl;

import dao.JournalDao;
import pojos.Journal;
import service.JournalService;

public class JournalServiceImpl extends GenericServiceImpl<Journal> implements JournalService{

	private JournalDao journalDao;

	public JournalDao getJournalDao() {
		return journalDao;
	}

	public void setJournalDao(JournalDao journalDao) {
		this.journalDao = journalDao;
	}
	
}
