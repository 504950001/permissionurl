package top.zemal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.zemal.model.Menu;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-24 14:45
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
