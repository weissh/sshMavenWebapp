package service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import pojos.Department;
import pojos.Right;
import pojos.Role;
import pojos.Staff;
import service.StaffService;
import web.ui.TreeNode;
import web.ui.TreeStore;
import dao.RightDao;
import dao.StaffDao;

public class StaffServiceImpl extends GenericServiceImpl<Staff> implements StaffService{

	private StaffDao staffDao;
	private RightDao rightDao;

	public StaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	public RightDao getRightDao() {
		return rightDao;
	}

	public void setRightDao(RightDao rightDao) {
		this.rightDao = rightDao;
	}

	public Staff getStaffByUserNamePwd(String userName, String password) {
		List<Staff> staffs=this.findByProperty("userName", userName);
		if(staffs.size()>0&&staffs.get(0).getPassword().equals(password)){
			return staffs.get(0);
		}
		return null;
	}
	
	public Staff find(Integer id){
		return this.staffDao.find(id);
	}

	@Override
	public boolean login(String userName, String password) {
		List<Staff> staffs=this.findByProperty("userName", userName);
		if(staffs.size()>0&&staffs.get(0).getPassword().equals(password)){
			HttpSession session=ServletActionContext.getRequest().getSession();
//			Map<String, Object> sessionMap=ActionContext.getContext().getSession();
//			TreeStore treeStore=getRightByRole(staffs.get(0).getRole());
			session.setAttribute("staff", staffs.get(0));
			session.setAttribute("staffName", staffs.get(0).getStaffName());
//			sessionMap.put("treeStore", JSONObject.toJSONString(treeStore));
			session.setAttribute("roleId",staffs.get(0).getRole().getRoleId());
			session.setAttribute("roleName", staffs.get(0).getRole().getRoleName());
			return true;
		}
		return false;
	}
	
	
	public TreeStore getRightByRole(Role role) {
		
		List<Right> tree = new ArrayList<Right>();
		
		List<Right> menus=new ArrayList<Right>();
		Set<Right> rightList=new HashSet<Right>();
		Set<Right> rights =  role.getRights();
		for(Right right:rights){
			if(right.isMenu()){
				menus.add(right);
			}else{
				rightList.add(right);
			}
		}
		for (Right menu : menus) {
			Right menuTemp=menu.clone();
			menu.getChildren().clear();
			for (Right right : rightList) {
				int rightId=right.getId();
				for(Right right2:menuTemp.getChildren()){
					if(right2.getId()==rightId){
						menu.getChildren().add(right);
					}
				}
			}
			tree.add(menu);
		}
		//==================
		List<TreeNode> nodes=new ArrayList<TreeNode>();
		TreeNode root=new TreeNode();
		for(Right right:tree){
			TreeNode node=TreeNode.toNode(right);
			nodes.add(node);
		}
		root.setText("root");
		root.setChecked(null);
		root.setChildren(nodes);
		TreeStore treeStore=new TreeStore();
		treeStore.setRoot(root);
		return treeStore;
	}

	@Override
	public boolean deleteStaff(String staffIds) {
		String[] ids=staffIds.split(",");
		HttpSession session=ServletActionContext.getRequest().getSession();
		Staff staffTemp=(Staff) session.getAttribute("staff");
		String id=staffTemp.getStaffId()+"";
		for(String str:ids){
			if(id.equals(str)){
				return false;
			}
		}
		ArrayList<Staff> staffs=new ArrayList<Staff>();
		/**遍历id数组，查找相应记录并add到ArrayList中 */
		for(int i=0;i<ids.length;i++){
			Staff staff = this.staffDao.find(Integer.parseInt(ids[i]));
			staffs.add(staff);
		}
		this.staffDao.removeAll(staffs);
		return true;
	}
	
	@Override
	public boolean modifyPassword(int staffId, String password) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff=(Staff)session.getAttribute("staff");
		staff.setPassword(password);
		this.staffDao.update(staff);
		return true;
	}
	
	@Override
	public void update(int source, Department department) {
		this.staffDao.update(source, department);
	}
}
