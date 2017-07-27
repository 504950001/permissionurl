package top.zemal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.zemal.VO.MenuVO;
import top.zemal.content.ResponseConstants;
import top.zemal.content.Responses;
import top.zemal.enums.OperateType;
import top.zemal.model.Menu;
import top.zemal.service.BaseService;

import java.util.List;

/**
 * @author zemal-tan
 * @description 用户、用户组、权限、权限组的增删改查
 * @create 2017-04-08 16:38
 **/

@RestController
@RequestMapping("/v1/menu")
@Api(description = "菜单接口")
public class MenuController {

    @Autowired
    BaseService baseService;

    @ApiOperation(value = "添加新菜单", notes = "添加新菜单")
    @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
    Responses addUsers(@ModelAttribute MenuVO menuVO) {
        Menu result = null;
        try {
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuVO, menu);
            result = baseService.operateMenu(OperateType.add.getIndex(),null, menu);
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

    @ApiOperation(value = "修改菜单", notes = "修改菜单")
    @RequestMapping(value = "/modifyMenu", method = RequestMethod.POST)
    Responses modifyMenu(@RequestParam Long pk, @ModelAttribute MenuVO MenuVO) {
        Menu result = null;
        try {
            Menu menu = new Menu();
            BeanUtils.copyProperties(MenuVO, menu);
            menu.setId(pk);
            result = baseService.operateMenu(OperateType.modify.getIndex(),menu.getId(),menu);
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

    @ApiOperation(value = "查询菜单", notes = "查询菜单")
    @RequestMapping(value = "/searchMenu", method = RequestMethod.POST)
    Responses searchMenu(@RequestParam(name = "pk") Long pk) {
        Menu result = null;
        try {
            result = baseService.operateMenu(OperateType.search.getIndex(),pk,null);
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

    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
    Responses deleteMenu(@RequestParam(name = "pk") Long pk) {
        Menu result = null;
        try {
            result = baseService.operateMenu(OperateType.delete.getIndex(),pk,null);
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

    @ApiOperation(value = "查询所有菜单", notes = "查询所有菜单")
    @RequestMapping(value = "/searchAllMenu", method = RequestMethod.POST)
    Responses searchAllMenu() {
        List<Menu> result = null;
        try {
            result = (List<Menu>) baseService.findObjectByPk(5,null);
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
