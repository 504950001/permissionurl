package top.zemal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门模型
 * 
 * @author xy
 */
@Entity
@Table(name = "sys_department")
public class Department implements Serializable{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;// 部门名称，非空

	@Column(name = "sn")
	private String sn;// 部门编号，非空

	@Column(name = "dir_path")
	private String dirPath; // 部门路径，用来查询所有后代部门

	@ManyToOne
	private User manager;// 部门经理

	@ManyToOne
	private Department parent;// 上级部门

	@Column(name = "state")
	private Integer state;//

	@OneToMany
	private List<Department> children = new ArrayList<Department>();// 子部门

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<Department> getChildren() {
		return children;
	}

	public void setChildren(List<Department> children) {
		this.children = children;
	}
}
