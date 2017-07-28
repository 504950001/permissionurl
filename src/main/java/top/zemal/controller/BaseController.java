package top.zemal.controller;

import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import top.zemal.VO.DepartmentVO;
import top.zemal.VO.SessionCache;
import top.zemal.content.ResponseConstants;
import top.zemal.content.Responses;
import top.zemal.enums.OperateType;
import top.zemal.interceptor.AuthInterceptor;
import top.zemal.model.Department;
import top.zemal.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zemal-tan
 * @description 用户、用户组、权限、权限组的增删改查
 * @create 2017-04-08 16:38
 **/

@RestController
@RequestMapping("/v1/base")
@Api(description = "基础接口，用户、用户组、权限、权限组等的查询")
@CrossOrigin
public class BaseController {

    @Autowired
    BaseService baseService;

    @ApiOperation(value = "根据主键id获取信息", notes = "根据主键id获取信息，<br>" +
            "1.部门<br>" +
            "2.用户<br>" +
            "3.角色<br>" +
            "4.权限<br>" +
            "5.菜单(即权限具有的功能，可访问的url)")
    @RequestMapping(value = "/findObjectByPk/{objectType}/{objectId}", method = RequestMethod.GET)
    Responses findObjectByPk(
            @ApiParam(name = "objectType", value = "对象类型(上面对应的1-5数字)", required = true)
            @PathVariable("objectType") Integer objectType,
            @ApiParam(name = "objectId", value = "对象id(主键)", required = true)
            @PathVariable("objectId") Long objectId) {
        Object result = null;
        try {
            result = baseService.findObjectByPk(objectType, objectId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses(ResponseConstants.SUCCESS_FAILED,
                    ResponseConstants.CODE_FAILED,
                    e.getMessage(), result);
        }
        return new Responses(ResponseConstants.SUCCESS_OK,
                ResponseConstants.CODE_SUCCESS,
                ResponseConstants.CODE_SUCCESS_VALUE, result);
    }

    @ApiOperation(value = "获取信息对应类型下所有信息", notes = "获取信息对应类型下所有信息，<br>" +
            "1.部门<br>" +
            "2.用户<br>" +
            "3.角色<br>" +
            "4.权限<br>" +
            "5.菜单(即权限具有的功能，可访问的url)")
    @RequestMapping(value = "/findAllObject/{objectType}", method = RequestMethod.GET)
    Responses findAllObject(
            @ApiParam(name = "objectType", value = "对象类型(上面对应的1-5数字)", required = true)
            @PathVariable("objectType") Integer objectType) {
        Object result = null;
        try {
            result = baseService.findObjectByPk(objectType, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses(ResponseConstants.SUCCESS_FAILED,
                    ResponseConstants.CODE_FAILED,
                    e.getMessage(), result);
        }
        return new Responses(ResponseConstants.SUCCESS_OK,
                ResponseConstants.CODE_SUCCESS,
                ResponseConstants.CODE_SUCCESS_VALUE, result);
    }

    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Responses login(
            @ApiParam(name = "username", value = "用户名", required = true)
            @RequestParam("username") String username,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam("password") String password,
            HttpServletRequest request) {
        Object result = null;
        try {
            HttpSession session = request.getSession();
            result = baseService.login(username, password, session);
            System.out.println(((SessionCache)result).getUsername());
            System.out.println("login session_id:"+session.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses(ResponseConstants.SUCCESS_FAILED,
                    ResponseConstants.CODE_FAILED,
                    e.getMessage(), result);
        }
        return new Responses(ResponseConstants.SUCCESS_OK,
                ResponseConstants.CODE_SUCCESS,
                ResponseConstants.CODE_SUCCESS_VALUE, result);
    }

    @ApiOperation(value = "登录页面", notes = "登录页面")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    Responses getlogin() {
        Object result = null;
        try {
            baseService.getlogin();
            result = "用户未登录";
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses(ResponseConstants.SUCCESS_FAILED,
                    ResponseConstants.CODE_FAILED,
                    e.getMessage(), result);
        }
        return new Responses(ResponseConstants.SUCCESS_OK,
                ResponseConstants.NOT_LOG_IN,
                ResponseConstants.CODE_FAILED_VALUE, result);
    }

    @ApiOperation(value = "登录成功后的首页", notes = "登录成功后的首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    Responses index(HttpServletRequest request) {
        Object result = null;
        try {
            result = "登录成功后的首页";
            HttpSession session = request.getSession();
            System.out.println("index session_id:"+session.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses(ResponseConstants.SUCCESS_FAILED,
                    ResponseConstants.CODE_FAILED,
                    e.getMessage(), result);
        }
        return new Responses(ResponseConstants.SUCCESS_OK,
                ResponseConstants.CODE_SUCCESS,
                ResponseConstants.CODE_SUCCESS_VALUE, result);
    }

    @ApiOperation(value = "注销", notes = "注销")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    Responses logout(HttpServletRequest request, HttpServletResponse response) {
        Object result = null;
        try {
            baseService.logout();
            HttpSession session = request.getSession();
            session.removeAttribute(AuthInterceptor.SESSION_KEY);
//            response.sendRedirect(request.getContextPath()+"/v1/base/login");
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses(ResponseConstants.SUCCESS_FAILED,
                    ResponseConstants.CODE_FAILED,
                    e.getMessage(), result);
        }
        return new Responses(ResponseConstants.SUCCESS_OK,
                ResponseConstants.CODE_SUCCESS,
                ResponseConstants.CODE_SUCCESS_VALUE, result);
    }

    @ApiOperation(value = "未被授权页面", notes = "未被授权页面")
    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    Responses unauthorized(
    ) {
        Object result = null;
        try {
            result = "用户未被授权";
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses(ResponseConstants.SUCCESS_FAILED,
                    ResponseConstants.CODE_FAILED,
                    e.getMessage(), result);
        }
        return new Responses(ResponseConstants.SUCCESS_OK,
                ResponseConstants.UNAUTHORIZED,
                ResponseConstants.CODE_FAILED_VALUE, result);
    }

//    @ApiOperation(value = "test", notes = "test")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    Responses test() {
        Object result = null;
        try {
            result = baseService.test();
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses(ResponseConstants.SUCCESS_FAILED,
                    ResponseConstants.CODE_FAILED,
                    e.getMessage(), result);
        }
        return new Responses(ResponseConstants.SUCCESS_OK,
                ResponseConstants.CODE_SUCCESS,
                ResponseConstants.CODE_SUCCESS_VALUE, result);
    }
}
