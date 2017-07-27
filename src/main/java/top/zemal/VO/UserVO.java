package top.zemal.VO;

import top.zemal.model.Department;
import top.zemal.model.Role;
import top.zemal.model.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-24 17:41
 */
public class UserVO {

    private String username;// 员工账号，非空

    @Column(name = "real_name")
    private String realName;// 真实姓名，非空

    @Column(name = "password")
    private String password; // 密码，非空

    @Column(name = "tel")
    private String tel; // 电话，非空

    @Column(name = "email")
    private String email; // 邮箱

    @Column(name = "state")
    private Integer state = 1; // 状态：

    private Long managerId; // 直属领导 User

    private Long departmentId;// 所属部门

    private Set<Long> roleIds = new HashSet<Long>();// 角色

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Set<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
