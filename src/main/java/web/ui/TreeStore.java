/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.ui.TreeStore
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-3-16     caiwenming       v1.0.0         create
 *
 */
package web.ui;

public class TreeStore {

//	private int id;
//	private String model;
	private TreeNode root;
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getModel() {
//		return model;
//	}
//	public void setModel(String model) {
//		this.model = model;
//	}
	public TreeNode getRoot() {
		return root;
	}
	public void setRoot(TreeNode root) {
		this.root = root;
	}
}
