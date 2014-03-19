package service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;

import pojos.Right;
import pojos.Role;
import pojos.Staff;
import service.RightService;
import web.ui.TreeNode;
import web.ui.TreeStore;
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
	public TreeStore getRightByRole() {
		
		List<Right> tree = new ArrayList<Right>();
		
		List<Right> menus=new ArrayList<Right>();
		Set<Right> rightList=new HashSet<Right>();
		Set<Right> rights =  getRightByUser();
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
				System.out.println(rightId);
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
	public TreeStore getCheckedTree(int roleId) {
		List<Right> rightMenu=this.rightDao.findBySql("from Right where menu=1");
		List<TreeNode> nodes=new ArrayList<TreeNode>();
		for(Right right:rightMenu){
			TreeNode node=TreeNode.toNode(right);
			nodes.add(node);
		}
		TreeNode root=new TreeNode();
		root.setChildren(nodes);
		Set<Integer> rightIds=new HashSet<Integer>();
		Set<Right> userRights=getRightByRoleId(roleId);
		if(userRights!=null){
			for(Right right:userRights){
				rightIds.add(right.getId());
			}
		}
		root.visitTree(rightIds);
		root.setChecked(false);
		TreeStore treeStore=new TreeStore();
		treeStore.setId("treeStoreForRole");
		treeStore.setRoot(root);
		Map<String, Object> sessionMap=ActionContext.getContext().getSession();
		sessionMap.put("treeStoreForRole", JSONObject.toJSONString(treeStore));
//		Map<String, Object> requestMap=ActionContext.getContext().get
//		return root.getChildren();
		return treeStore;
	}

	private Set<Right> getRightByUser(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		Role role = this.roleDao.find(staff.getRole().getRoleId());
		return role.getRights();
	}
	private Set<Right> getRightByRoleId(int roleId){
		Role role=this.roleDao.find(roleId);
		return role.getRights();
	}
}
