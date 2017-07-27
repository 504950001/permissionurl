package top.zemal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.zemal.model.Role;
import top.zemal.model.User;

import java.util.List;
import java.util.Set;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-24 14:48
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User where username = ?1")
    User findByUsername(String username);

    @Query("select roles from User where id = ?1")
    Set<Role> findRoleById(Long userId);
}
