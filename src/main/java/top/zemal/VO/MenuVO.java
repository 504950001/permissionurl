package top.zemal.VO;

import top.zemal.model.Menu;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-24 17:25
 */
public class MenuVO {

    //菜单编号
    @Column(name = "sn")
    private String sn;
    //菜单名称
    @Column(name = "name")
    private String name;
    // 上级菜单
    private Long parentId;
    //图标
    @Column(name = "icon_cls")
    private String iconCls;
    //地址
    @Column(name = "url")
    private String url;
    //简介
    @Column(name = "intro")
    private String intro;

    private Set<Long> childrenIds = new HashSet<>();

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Set<Long> getChildrenIds() {
        return childrenIds;
    }

    public void setChildrenIds(Set<Long> childrenIds) {
        this.childrenIds = childrenIds;
    }
}
