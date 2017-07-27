package top.zemal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限具有的功能．也就是具有哪些url的访问权限
 */
@Entity
@Table(name = "sys_menu")
public class Menu implements Serializable{
	//主键
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	//菜单编号
	@Column(name = "sn")
	private String sn;
	//菜单名称
	@Column(name = "name")
	private String name;
	// 上级菜单
	@ManyToOne
	private Menu parent;
	//图标
	@Column(name = "icon_cls")
	private String iconCls;
	//地址
	@Column(name = "url")
	private String url;
	//简介
	@Column(name = "intro")
	private String intro;
	
	// 添加一个属性,装二级菜单,但是此属性不持久化,不交给hibernate管理，不配置到映射文件里面
	// 子菜单:此属性不交给hibernate处理，不在映射文件里面配置
	@OneToMany
	private List<Menu> children = new ArrayList<Menu>();

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", url=" + url + ", iconCls=" + iconCls + "]";
	}

	
}
