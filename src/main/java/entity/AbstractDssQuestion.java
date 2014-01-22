package entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractDssQuestion entity provides the base persistence definition of the
 * DssQuestion entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractDssQuestion implements java.io.Serializable {

	// Fields

	private Integer questionId;
	private String dataDate;
	private String quesProv;
	private String quesContent;
	private String quesReply;

	// Constructors

	/** default constructor */
	public AbstractDssQuestion() {
	}

	/** full constructor */
	public AbstractDssQuestion(String dataDate, String quesProv,
			String quesContent, String quesReply) {
		this.dataDate = dataDate;
		this.quesProv = quesProv;
		this.quesContent = quesContent;
		this.quesReply = quesReply;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "question_id", unique = true, nullable = false)
	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Column(name = "data_date", nullable = false, length = 20)
	public String getDataDate() {
		return this.dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	@Column(name = "ques_prov", nullable = false, length = 10)
	public String getQuesProv() {
		return this.quesProv;
	}

	public void setQuesProv(String quesProv) {
		this.quesProv = quesProv;
	}

	@Column(name = "ques_content", nullable = false, length = 200)
	public String getQuesContent() {
		return this.quesContent;
	}

	public void setQuesContent(String quesContent) {
		this.quesContent = quesContent;
	}

	@Column(name = "ques_reply", nullable = false, length = 200)
	public String getQuesReply() {
		return this.quesReply;
	}

	public void setQuesReply(String quesReply) {
		this.quesReply = quesReply;
	}

}