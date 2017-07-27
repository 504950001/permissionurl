package top.zemal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.zemal.model.Permission;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-24 14:47
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
