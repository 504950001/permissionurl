package top.zemal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色
 */
@Entity
@Table(name = "sys_role")
public class Role implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "sn")
	private String sn;// 角色编号

	@Column(name = "name")
	private String name;// 角色名称

	// 角色和权限 多对多关系
	@ManyToMany(cascade={CascadeType.ALL})
	private List<Permission> permissions = new ArrayList<Permission>();

	// 专门处理中间表的对象
	public List<Map<String, Long>> getPermissionMap() {
		List<Map<String, Long>> list = new ArrayList<Map<String, Long>>();
		// getPermissions()从页面(permissions[0].id=1)或者测试类设置的
		for (Permission permission : getPermissions()) {
			// map就是一个domain对象,类似与RolePermission
			Map<String, Long> map = new HashMap<String, Long>();
			// 当前操作的角色的id
			map.put("roleId", getId());
			// 当前角色选择的权限id
			map.put("permissionId", permission.getId());
			list.add(map);
		}
		return list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
