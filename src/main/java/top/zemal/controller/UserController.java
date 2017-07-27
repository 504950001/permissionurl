package top.zemal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.zemal.VO.UserVO;
import top.zemal.content.ResponseConstants;
import top.zemal.content.Responses;
import top.zemal.enums.OperateType;
import top.zemal.model.User;
import top.zemal.service.BaseService;

import java.util.List;

/**
 * @author zemal-tan
 * @description 用户、用户组、用户、用户组的增删改查
 * @create 2017-04-08 16:38
 **/

@RestController
@RequestMapping("/v1/user")
@Api(description = "用户接口")
@CrossOrigin
public class UserController {

    @Autowired
    BaseService baseService;

    @ApiOperation(value = "添加新用户", notes = "添加新用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    Responses addUsers(@ModelAttribute UserVO userVO) {
        User result = null;
        try {
            result = baseService.operateUser(OperateType.add.getIndex(),null, userVO);
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

    @ApiOperation(value = "修改用户", notes = "修改用户")
    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    Responses modifyUser(@RequestParam Long pk, @ModelAttribute UserVO userVO) {
        User result = null;
        try {
            result = baseService.operateUser(OperateType.modify.getIndex(),pk,userVO);
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

    @ApiOperation(value = "查询用户", notes = "查询用户")
    @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
    Responses searchUser(@RequestParam(name = "pk") Long pk) {
        User result = null;
        try {
            result = baseService.operateUser(OperateType.search.getIndex(),pk,null);
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

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    Responses deleteUser(@RequestParam(name = "pk") Long pk) {
        User result = null;
        try {
            result = baseService.operateUser(OperateType.delete.getIndex(),pk,null);
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

    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @RequestMapping(value = "/searchAllUser", method = RequestMethod.POST)
    Responses searchAllUser() {
        List<User> result = null;
        try {
            result = (List<User>) baseService.findObjectByPk(2,null);
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
