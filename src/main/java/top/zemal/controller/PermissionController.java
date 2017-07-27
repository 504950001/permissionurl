package top.zemal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.zemal.VO.PermissionVO;
import top.zemal.content.ResponseConstants;
import top.zemal.content.Responses;
import top.zemal.enums.OperateType;
import top.zemal.model.Permission;
import top.zemal.service.BaseService;

import java.util.List;

/**
 * @author zemal-tan
 * @description 用户、用户组、权限、权限组的增删改查
 * @create 2017-04-08 16:38
 **/

@RestController
@RequestMapping("/v1/permission")
@Api(description = "权限接口")
@CrossOrigin
public class PermissionController {

    @Autowired
    BaseService baseService;

    @ApiOperation(value = "添加新权限", notes = "添加新权限")
    @RequestMapping(value = "/addPermission", method = RequestMethod.POST)
    Responses addUsers(@ModelAttribute PermissionVO permissionVO) {
        Permission result = null;
        try {
            Permission permission = new Permission();
            BeanUtils.copyProperties(permissionVO, permission);
            result = baseService.operatePermission(OperateType.add.getIndex(),null, permission);
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

    @ApiOperation(value = "修改权限", notes = "修改权限")
    @RequestMapping(value = "/modifyPermission", method = RequestMethod.POST)
    Responses modifyPermission(@RequestParam Long pk, @ModelAttribute PermissionVO permissionVO) {
        Permission result = null;
        try {
            Permission permission = new Permission();
            BeanUtils.copyProperties(permissionVO, permission);
            permission.setId(pk);
            result = baseService.operatePermission(OperateType.modify.getIndex(),permission.getId(),permission);
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

    @ApiOperation(value = "查询权限", notes = "查询权限")
    @RequestMapping(value = "/searchPermission", method = RequestMethod.POST)
    Responses searchPermission(@RequestParam(name = "pk") Long pk) {
        Permission result = null;
        try {
            result = baseService.operatePermission(OperateType.search.getIndex(),pk,null);
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

    @ApiOperation(value = "删除权限", notes = "删除权限")
    @RequestMapping(value = "/deletePermission", method = RequestMethod.POST)
    Responses deletePermission(@RequestParam(name = "pk") Long pk) {
        Permission result = null;
        try {
            result = baseService.operatePermission(OperateType.delete.getIndex(),pk,null);
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

    @ApiOperation(value = "查询所有权限", notes = "查询所有权限")
    @RequestMapping(value = "/searchAllPermission", method = RequestMethod.POST)
    Responses searchAllPermission() {
        List<Permission> result = null;
        try {
            result = (List<Permission>) baseService.findObjectByPk(4,null);
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
