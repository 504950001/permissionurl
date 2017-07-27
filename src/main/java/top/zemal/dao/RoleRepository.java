package top.zemal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.zemal.model.Role;

import java.util.List;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-24 14:48
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}
