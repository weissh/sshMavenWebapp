package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import common.TreeNode;

import pojos.Right;
import pojos.Role;
import pojos.Staff;
import service.RightService;
import dao.RightDao;
import dao.RoleDao;

public class RightServiceImpl extends GenericServiceImpl<Right> implements
		RightService {

	private RightDao rightDao;

	private RoleDao roleDao;

	public RightDao getRightDao() {
		return rightDao;
	}

	public void setRightDao(RightDao rightDao) {
		this.rightDao = rightDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Right> getRightByRole() {
		List<Right> tree = new ArrayList<Right>();
		Set<Right> rights =  getRightByUser();
		List<Right> menus = this.rightDao.findBySql("from Right where menu=1");
		for (Right menu : menus) {
			Right menuTemp=menu.clone();
			menu.getChildren().clear();
			for (Right right : rights) {
				int rightId=right.getId();
				System.out.println(rightId);
				for(Right right2:menuTemp.getChildren()){
					if(right2.getId()==rightId){
						menu.getChildren().add(right);
					}
				}
			}
			tree.add(menu);
		}
		return tree;
	}

	@Override
	public List<Right> getCheckedTree() {
		List<Right> rightMenu=this.rightDao.findBySql("from Right where menu=1");
		Set<Right> userRights=getRightByUser();
		for(Right right:rightMenu){
			TreeNode node=(TreeNode) right;
			node.getChildren();
			Set<Right> childrenRights=right.getChildren();
			for(Right right2:userRights){
				if(childrenRights.contains(right2)){
					
				}
			}
		}
		return null;
	}

	private Set<Right> getRightByUser(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		Role role = staff.getRole();
		return role.getRights();
	}
}
