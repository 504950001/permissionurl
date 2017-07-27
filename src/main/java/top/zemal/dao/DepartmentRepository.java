package top.zemal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.zemal.model.Department;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-24 14:44
 */
public interface DepartmentRepository extends
        JpaRepository<Department, Long> {

}
