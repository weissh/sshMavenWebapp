package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import pojos.Right;
import pojos.Role;
import pojos.Staff;
import service.RightService;
import web.ui.TreeNode;
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
	public List<TreeNode> getCheckedTree() {
		List<TreeNode> tree=new ArrayList<TreeNode>();
		List<Right> rightMenu=this.rightDao.findBySql("from Right where menu=1");
		List<TreeNode> nodes=new ArrayList<TreeNode>();
		for(Right right:rightMenu){
			TreeNode node=TreeNode.toNode(right);
			nodes.add(node);
		}
		List<TreeNode> list=new ArrayList<TreeNode>();
		Set<Right> userRights=getRightByUser();
		for(Right right:userRights){
			TreeNode node=TreeNode.toNode(right);
			list.add(node);
		}
		for (TreeNode node : nodes) {
			List<TreeNode> children=node.getChildren();
			TreeNode nodeClone=node.clone();
//			nodeClone.getChildren().clear();
			for (TreeNode node2 : list) {
				TreeNode node2Clone=node2.clone();
				List<TreeNode> deList=new ArrayList<TreeNode>();
				for(TreeNode node3:node.getChildren()){
					if(node3.getId()==node2.getId()){
						deList.add(node2);
						node2Clone.setChecked(true);
						children.add(node2Clone);
					}
				}
//				children.add(node2Clone);
				children.removeAll(deList);
			}
			nodeClone.getChildren().addAll(children);
			tree.add(nodeClone);
		}
		return nodes;
	}

	private Set<Right> getRightByUser(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		Role role = staff.getRole();
		return role.getRights();
	}
}
