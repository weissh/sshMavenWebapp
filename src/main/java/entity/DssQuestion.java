package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DssQuestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dss_question", catalog = "test1")
public class DssQuestion extends AbstractDssQuestion implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public DssQuestion() {
	}

	/** full constructor */
	public DssQuestion(String dataDate, String quesProv, String quesContent,
			String quesReply) {
		super(dataDate, quesProv, quesContent, quesReply);
	}

}
