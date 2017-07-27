package top.zemal.VO;

import top.zemal.model.Menu;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-24 17:36
 */
public class PermissionVO {

    private String name;// 权限名称

    private String resource;// 资源地址
    // 额外增加的字段

    private Integer state = 1;// 状态:1:正常(默认),0:禁用

    private Long menuId;

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

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
