package top.zemal.VO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestParam;
import top.zemal.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-24 15:41
 */
public class DepartmentVO {

    @ApiParam(required = true, value = "部门名称")
    private String name;// 部门名称，非空

    @ApiParam(required = true, value = "部门编号")
    private String sn;// 部门编号，非空

    @ApiModelProperty(value = "部门编号")
    private String dirPath; // 部门路径，用来查询所有后代部门

    @ApiModelProperty(value = "部门经理id")
    private Long managerId;// 部门经理

    @ApiModelProperty(value = "上级部门", required = false)
    private Long parentId;// 上级部门

    @ApiParam(defaultValue = "1", value = "状态 0为禁用,1为启用")
    private Integer state;//

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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
