package entity;

import org.apache.struts2.json.annotations.JSON;


/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer unicode;
	private Integer cno;
	private String cname;
	private String cteacher;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** full constructor */
	public Student(Integer cno, String cname, String cteacher) {
		this.cno = cno;
		this.cname = cname;
		this.cteacher = cteacher;
	}

	// Property accessors

	public Integer getUnicode() {
		return this.unicode;
	}

	public void setUnicode(Integer unicode) {
		this.unicode = unicode;
	}

	public Integer getCno() {
		return this.cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCteacher() {
		return this.cteacher;
	}

	public void setCteacher(String cteacher) {
		this.cteacher = cteacher;
	}

}