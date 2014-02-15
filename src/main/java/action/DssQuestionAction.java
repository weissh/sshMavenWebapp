package action;

import service.DssQuestionService;
import entity.DssQuestion;


public class DssQuestionAction {
	private DssQuestionService dssQuestionService;
	public DssQuestionService getDssQuestionService() {
		return dssQuestionService;
	}
	public void setDssQuestionService(DssQuestionService dssQuestionService) {
		this.dssQuestionService = dssQuestionService;
	}

	private DssQuestion dssQuestion;
	
	public DssQuestion getDssQuestion() {
		return dssQuestion;
	}
	public void setDssQuestion(DssQuestion dssQuestion) {
		this.dssQuestion = dssQuestion;
	}
	
	public String save(){
		DssQuestion dssQuestion=new DssQuestion();
		dssQuestion=(DssQuestion)(dssQuestionService.findByProperty("id", 1).get(0));
		dssQuestion.setDataDate("201401122");
		dssQuestion.setQuesContent("tes");
		dssQuestion.setQuesProv("安徽");
		dssQuestion.setQuesReply("n3o");
		dssQuestionService.attachDirty(dssQuestion);
		
		return "SUCCESS";
	}
	
}
