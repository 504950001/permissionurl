package top.zemal.VO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-27 10:55
 */
public class SessionCache {

    private Long id; // 用户id

    private String username;// 员工账号，非空

    private Set menuUrl = new HashSet();

    public SessionCache(){};

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

    public Set getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(Set menuUrl) {
        this.menuUrl = menuUrl;
    }
}
