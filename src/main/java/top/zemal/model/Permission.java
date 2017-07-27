package top.zemal.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 系统权限管理
 */
@Entity
@Table(name = "sys_permission")
public class Permission implements Serializable{

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;// 权限名称

	@Column(name = "resource")
	private String resource;// 资源地址
	// 额外增加的字段

	@Column(name = "state")
	private Integer state = 1;// 状态:1:正常(默认),0:禁用

	@ManyToOne
	private Menu menu;

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Permission() {

	}

	public Permission(String name, String resource) {
		this.name = name;
		this.resource = resource;
	}

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

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + "]";
	}

}
