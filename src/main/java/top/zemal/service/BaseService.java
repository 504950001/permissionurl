package top.zemal.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zemal.TestMain;
import top.zemal.VO.*;
import top.zemal.config.CommonUrlConfig;
import top.zemal.dao.*;
import top.zemal.enums.OperateType;
import top.zemal.interceptor.AuthInterceptor;
import top.zemal.model.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-24 15:13
 */
@Service
public class BaseService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public Object findObjectByPk(Integer objectType, Long objectId){
        Object result = null;
        switch (objectType){
            case 1:
                result = (objectId == null ? departmentRepository.findAll() :
                        departmentRepository.findById(objectId));
                break; // 部门
            case 2:
                result = (objectId == null ? userRepository.findAll() :
                        userRepository.findById(objectId));
                break; // 用户
            case 3:
                result = (objectId == null ? roleRepository.findAll() :
                        roleRepository.findById(objectId));
                break; // 角色
            case 4:
                result = (objectId == null ? permissionRepository.findAll() :
                        permissionRepository.findById(objectId));
                break; // 权限
            case 5:
                result = (objectId == null ? menuRepository.findAll() :
                        menuRepository.findById(objectId));
                break; // 菜单
            default:
                break;
        }
        return result;
    }

    public Department operateDepartment(Integer operateType,
                                        Long pk, Department departmentVO){
        if (operateType == OperateType.add.getIndex()){ // 添加
            Department department = new Department();
            BeanUtils.copyProperties(departmentVO, department);
            return departmentRepository.save(department);
        }else if (operateType == OperateType.modify.getIndex()){ // 修改
            Department department = new Department();
            BeanUtils.copyProperties(departmentVO, department);
            department.setId(pk);
            return departmentRepository.save(department);
        }else if (operateType == OperateType.delete.getIndex()){ // 删除
            departmentRepository.deleteById(pk);
            return null;
        }
        else if (operateType == OperateType.search.getIndex()){ // 查询
            return departmentRepository.findById(pk).get();
        }
        return null;
    }

    public Menu operateMenu(Integer operateType,
                                  Long pk, MenuVO menuVO) throws Exception {
        if (operateType == OperateType.add.getIndex()){ // 添加
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuVO, menu);
            if (menuVO.getChildrenIds() != null && menuVO.getChildrenIds().size()>0){ // 直属部门
                List<Menu> menuList = menuRepository.findAllById(menuVO.getChildrenIds());
                if (menuList == null){
                    throw new Exception("error");
                }
                menu.setChildren(menuList);
            }
            return menuRepository.save(menu);
        }else if (operateType == OperateType.modify.getIndex()){ // 修改
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuVO, menu);
            menu.setId(pk);
            return menuRepository.save(menu);
        }else if (operateType == OperateType.delete.getIndex()){ // 删除
            menuRepository.deleteById(pk);
            return null;
        }
        else if (operateType == OperateType.search.getIndex()){ // 查询
            return menuRepository.findById(pk).get();
        }
        return null;
    }

    public Permission operatePermission(Integer operateType,
                                  Long pk, PermissionVO permissionVO){
        if (operateType == OperateType.add.getIndex()){ // 添加
            Permission permission = new Permission();
            BeanUtils.copyProperties(permissionVO, permission);
            if (permissionVO.getMenuId() != null){
                Menu menu = menuRepository.findById(permissionVO.getMenuId()).get();
                if (menu!=null){
                    permission.setMenu(menu);
                }
            }
            return permissionRepository.save(permission);
        }else if (operateType == OperateType.modify.getIndex()){ // 修改
            Permission permission = new Permission();
            BeanUtils.copyProperties(permissionVO, permission);
            permission.setId(pk);
            return permissionRepository.save(permission);
        }else if (operateType == OperateType.delete.getIndex()){ // 删除
            permissionRepository.deleteById(pk);
            return null;
        }
        else if (operateType == OperateType.search.getIndex()){ // 查询
            return permissionRepository.findById(pk).get();
        }
        return null;
    }

    public Role operateRole(Integer operateType,
                                  Long pk, RoleVO roleVO){
        if (operateType == OperateType.add.getIndex()){ // 添加
            Role role = new Role();
            BeanUtils.copyProperties(roleVO, role);
            if (roleVO.getPermissionIds() != null && roleVO.getPermissionIds().size() > 0){
                List<Permission> permissionList = permissionRepository.findAllById(roleVO.getPermissionIds());
                if (permissionList != null && permissionList.size() >0){
                    role.setPermissions(permissionList);
                }
            }
            return roleRepository.save(role);
        }else if (operateType == OperateType.modify.getIndex()){ // 修改
            Role role = roleRepository.findById(pk).get();
            BeanUtils.copyProperties(roleVO, role);
            if (roleVO.getPermissionIds() != null && roleVO.getPermissionIds().size() > 0){
                List<Permission> permissionList = permissionRepository.findAllById(roleVO.getPermissionIds());
                if (permissionList != null && permissionList.size() >0){
                    role.setPermissions(permissionList);
                }
            }
            return roleRepository.save(role);
        }else if (operateType == OperateType.delete.getIndex()){ // 删除
            roleRepository.deleteById(pk);
            return null;
        }
        else if (operateType == OperateType.search.getIndex()){ // 查询
            return roleRepository.findById(pk).get();
        }
        return null;
    }

    public User operateUser(Integer operateType,
                            Long pk, UserVO userVO) throws Exception {
        if (operateType == OperateType.add.getIndex()){ // 添加
            User user = new User();
            BeanUtils.copyProperties(userVO, user);
            if (userVO.getDepartmentId() != null){ // 直属部门
                Department department = departmentRepository.findById(userVO.getDepartmentId()).get();
                if (department == null){
                    throw new Exception("不存在主键id为"+userVO.getDepartmentId()+"的直属部门");
                }
                user.setDepartment(department);
            }
            if (userVO.getManagerId() != null){  // 直属领导
                User userManager = userRepository.findById(userVO.getManagerId()).get();
                if (userManager == null){
                    throw new Exception("不存在主键id为"+userVO.getManagerId()+"的上级直属领导");
                }
                user.setManager(userManager);
            }
            if (userVO.getRoleIds() != null && userVO.getRoleIds().size() > 0){  // 扮演的角色

                List<Role> roleList = roleRepository.findAllById(userVO.getRoleIds());
                if (roleList == null || roleList.size() == 0){
                    throw new Exception("不存在角色");
                }
                Set<Role> roleSet = new HashSet<>(roleList);
                user.setRoles(roleSet);
            }

            return userRepository.save(user);
        }else if (operateType == OperateType.modify.getIndex()){ // 修改
            User user = userRepository.findById(pk).get();
            if (user == null){
                throw new Exception("不存在主键id为"+pk+"用户");
            }
            BeanUtils.copyProperties(userVO, user);

            if (userVO.getDepartmentId() != null){ // 直属部门
                Department department = departmentRepository.findById(userVO.getDepartmentId()).get();
                if (department == null){
                    throw new Exception("不存在主键id为"+userVO.getDepartmentId()+"的直属部门");
                }
                user.setDepartment(department);
            }
            if (userVO.getManagerId() != null){  // 直属领导
                User userManager = userRepository.findById(userVO.getManagerId()).get();
                if (userManager == null){
                    throw new Exception("不存在主键id为"+userVO.getManagerId()+"的上级直属领导");
                }
                user.setManager(userManager);
            }
            if (userVO.getRoleIds() != null && userVO.getRoleIds().size() > 0){  // 扮演的角色

                List<Role> roleList = roleRepository.findAllById(userVO.getRoleIds());
                if (roleList == null || roleList.size() == 0){
                    throw new Exception("不存在角色");
                }
                Set<Role> roleSet = new HashSet<>(roleList);
                user.setRoles(roleSet);
            }
            return userRepository.save(user);
        }else if (operateType == OperateType.delete.getIndex()){ // 删除
            userRepository.deleteById(pk);
            return null;
        }
        else if (operateType == OperateType.search.getIndex()){ // 查询
            return userRepository.findById(pk).get();
        }
        return null;
    }


    public SessionCache login(String username, String password, HttpSession session){
        User loginUser = userRepository.findByUsername(username);

        if (loginUser == null) {
            throw new RuntimeException("请输入正确的的用户名!");
        }

        if (!password.equals(loginUser.getPassword())) {
            throw new RuntimeException("请输入正确的密码!");
        }

        // 获取权限信息写入session
        SessionCache sessionCache = new SessionCache();
        sessionCache.setId(loginUser.getId());
        sessionCache.setUsername(loginUser.getUsername());

        Set<String> urlSet = new HashSet<>(); // 存放可访问url

        Set<Role> roleSet = loginUser.getRoles();
        if (roleSet != null && roleSet.size() >0){
            for (Role role: roleSet){
                List<Permission> permissionList = role.getPermissions();
                if (permissionList != null && permissionList.size() >0){
                    for (Permission permission: permissionList){
                        Menu menu = permission.getMenu();
                        Set<String> urlChildrenSet = new HashSet<>(); // 存放可访问url
                        urlChildrenSet = getMenuUrls(menu, urlChildrenSet); // 每个权限的url集合
                        urlSet.addAll(urlChildrenSet);
                    }
                }
            }
        }
        if (urlSet != null && urlSet.size() >0)
            sessionCache.setMenuUrl(urlSet);
        session.setAttribute(AuthInterceptor.SESSION_KEY, sessionCache);
        return sessionCache;
    }

    public void getlogin(){
        System.out.println("---用户登录页面---");
    }

    public void logout(){
        System.out.println("---注销---");
    }


    @Autowired
    CommonUrlConfig commonUrlConfig;
    public List<String> test(){

//        Menu menu = TestMain.initMenu();
//        menuRepository.save(menu);

        List<String> stringList = commonUrlConfig.getUrl();
        for (String string: stringList){
            System.out.println(string);
        }
        return stringList;
    }

    public Set<String> getMenuUrls(Menu menu, Set<String> result){
//        Set<String> result = new HashSet<>();
        if (menu.getChildren() == null || menu.getChildren().size() ==0){
            result.add(menu.getUrl());
        }
        else if (menu.getChildren() != null && menu.getChildren().size() >0){
            result.add(menu.getUrl());
            List<Menu> menuList = menu.getChildren();
            for (Menu item: menuList){
                result.add(item.getUrl());
                getMenuUrls(item, result);
            }
        }
        return result;
    }
}
