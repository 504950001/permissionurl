package top.zemal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.zemal.VO.RoleVO;
import top.zemal.content.ResponseConstants;
import top.zemal.content.Responses;
import top.zemal.enums.OperateType;
import top.zemal.model.Role;
import top.zemal.service.BaseService;

import java.util.List;

/**
 * @author zemal-tan
 * @description 用户、用户组、角色、角色组的增删改查
 * @create 2017-04-08 16:38
 **/

@RestController
@RequestMapping("/v1/role")
@Api(description = "角色接口")
public class RoleController {

    @Autowired
    BaseService baseService;

    @ApiOperation(value = "添加新角色", notes = "添加新角色")
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    Responses addUsers(@ModelAttribute RoleVO roleVO) {
        Role result = null;
        try {
            result = baseService.operateRole(OperateType.add.getIndex(),null, roleVO);
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

    @ApiOperation(value = "修改角色", notes = "修改角色")
    @RequestMapping(value = "/modifyRole", method = RequestMethod.POST)
    Responses modifyRole(@RequestParam Long pk, @ModelAttribute RoleVO roleVO) {
        Role result = null;
        try {
            result = baseService.operateRole(OperateType.modify.getIndex(),pk,roleVO);
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

    @ApiOperation(value = "查询角色", notes = "查询角色")
    @RequestMapping(value = "/searchRole", method = RequestMethod.POST)
    Responses searchRole(@RequestParam(name = "pk") Long pk) {
        Role result = null;
        try {
            result = baseService.operateRole(OperateType.search.getIndex(),pk,null);
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

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    Responses deleteRole(@RequestParam(name = "pk") Long pk) {
        Role result = null;
        try {
            result = baseService.operateRole(OperateType.delete.getIndex(),pk,null);
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

    @ApiOperation(value = "查询所有角色", notes = "查询所有角色")
    @RequestMapping(value = "/searchAllRole", method = RequestMethod.POST)
    Responses searchAllRole() {
        List<Role> result = null;
        try {
            result = (List<Role>) baseService.findObjectByPk(3,null);
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
