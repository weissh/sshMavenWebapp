/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.ui.TreeNode
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-3-13     caiwenming       v1.0.0         create
 *
 */
package web.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import pojos.Right;

public class TreeNode implements Cloneable{

	private  int id;
	private String text;
	private String hrefTarget;
	private boolean leaf;
	private boolean expanded;
	private Boolean checked;
	
	private List<TreeNode> children;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	public static TreeNode toNode(Right right ){
		TreeNode node=new TreeNode();
		node.setId(right.getId());
		node.setText(right.getText());
		node.setHrefTarget(right.getHrefTarget());
		node.setLeaf(right.getLeaf());
		node.setChecked(null);
		node.setExpanded(right.getExpanded());
		Set<Right>rights=right.getChildren();
		if(rights.size()>0){
			List<TreeNode>nodes=new ArrayList<TreeNode>();
			for(Right right2:rights){
				TreeNode node2=toNode(right2);
				nodes.add(node2);
			}
			node.setChildren(nodes);
		}
		return node;
	}
	
	public void visitTree(Set<Integer> ids){
		if(children==null||children.isEmpty()){
			return;
		}
		int size=children.size();
		for(int i=0;i<size;i++){
			TreeNode child=children.get(i);
			if(ids.contains(child.getId())){
				child.setChecked(true);
			}else {
				child.setChecked(false);
			}
			child.visitTree(ids);
		}
	}
	
	@Override
    public TreeNode clone(){
        TreeNode treeNode = null;
        try{
           treeNode=(TreeNode)super.clone();
        }catch(Exception e){
            e.printStackTrace();
        }
        return treeNode;
    }
	
}
