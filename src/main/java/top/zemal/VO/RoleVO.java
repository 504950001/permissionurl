package top.zemal.VO;

import top.zemal.model.Permission;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-24 17:39
 */
public class RoleVO {

    private String sn;// 角色编号

    private String name;// 角色名称

    private Set<Long> permissionIds = new HashSet<>();

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

    public Set<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(Set<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
