package action;

import entity.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import service.StudentService;

public class StudentAction {
	private int page=1;
	private int rows=10;
	private Map map;
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}

	private StudentService studentService;
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@JSON(serialize=false)
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	@JSON(serialize=false)
	public StudentService getStudentService() {
		return studentService;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	public String test(){
		List list=studentService.findByPage(page, rows);
		map=new HashMap();
		map.put("rows", list);
		map.put("total", studentService.getTotalRows());
		return "SUCCESS";
	}
	
	public String save(){
		
		return "SUCCESS";
	}
	
	
}
