package service.impl;

import service.DssQuestionService;
import entity.DssQuestion;

public class DssQuestionServiceImpl extends GenericServiceImpl<DssQuestion> implements DssQuestionService {
	private dao.impl.entity.DssQuestionDaoImpl dssQuestionDao;

	public dao.impl.entity.DssQuestionDaoImpl getDssQuestionDao() {
		return dssQuestionDao;
	}

	public void setDssQuestionDao(dao.impl.entity.DssQuestionDaoImpl dssQuestionDao) {
		this.dssQuestionDao = dssQuestionDao;
	}
	
}
