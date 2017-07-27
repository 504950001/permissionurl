package top.zemal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zemal-tan
 * @description 用户信息
 * @create 2017-03-31 17:48
 **/
@Entity
@Table(name = "sys_user")
public class User  implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;// 员工账号，非空

    @Column(name = "real_name")
    private String realName;// 真实姓名，非空

    @Column(name = "password")
    private String password; // 密码，非空

    @Column(name = "tel")
    private String tel; // 电话，非空

    @Column(name = "email")
    private String email; // 邮箱

    @Column(name = "input_time")
    private Date inputTime = new Date(); // 录入时间

    @Column(name = "state")
    private Integer state = 1; // 状态：0-->正常(默认);-1-->离职

    @OneToOne
    private User manager; // 直属领导

    @ManyToOne
    private Department department;// 所属部门

    @ManyToMany
    private Set<Role> roles = new HashSet<Role>();// 角色

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getInputTime() {
        return inputTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", realName=" + realName + ", password=" + password
                + ", tel=" + tel + ", email=" + email + ", inputTime=" + inputTime + ", state=" + state + "]";
    }




    public User() {
    }

    //增加一个构造方法
    public User(String username) {
        this.username = username;
    }
}
