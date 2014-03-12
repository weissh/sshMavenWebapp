package pojos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Right entity. @author MyEclipse Persistence Tools
 */

public class Right implements java.io.Serializable,Cloneable {

	// Fields
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String text;
	private String hrefTarget;
	private boolean leaf;
//	private boolean checked;
	private boolean expanded;
//	private String description;
	private boolean menu;
	private Boolean checked;
	
	
	//不需要通过权限查找相应的角色，故可不需要 private Set roles = new HashSet(0);
	private Set<Role> roles = new HashSet<Role>(0);
	private Set<Resource> resources =new HashSet<Resource>(0);
	private Set<Right> children=new HashSet<Right>();//

	// Constructors

	/** default constructor */
	public Right() {
	}

	/** full constructor */
	public Right(String text, Set<Role> roles, Set<Resource>resources) {
		this.roles = roles;
		this.resources=resources;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
//
//	public boolean isChecked() {
//		return checked;
//	}
//
//	public void setChecked(boolean checked) {
//		this.checked = checked;
//	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<Right> getChildren() {
		return children;
	}

	public void setChildren(Set<Right> children) {
		this.children = children;
	}
	
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Override
    public Right clone(){
        Right right = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(
                    baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            right = (Right) ois.readObject();
            ois.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return right;
    }
}