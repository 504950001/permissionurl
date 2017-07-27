package top.zemal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.zemal.VO.DepartmentVO;
import top.zemal.content.ResponseConstants;
import top.zemal.content.Responses;
import top.zemal.enums.OperateType;
import top.zemal.model.Department;
import top.zemal.model.User;
import top.zemal.service.BaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zemal-tan
 * @description 用户、用户组、权限、权限组的增删改查
 * @create 2017-04-08 16:38
 **/

@RestController
@RequestMapping("/v1/department")
@Api(description = "部门接口")
@CrossOrigin
public class DepartmentController {

    @Autowired
    BaseService baseService;

    @ApiOperation(value = "添加新部门", notes = "添加新部门")
    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
    Responses addUsers(@ModelAttribute DepartmentVO departmentVO) {
        Department result = null;
        try {
            Department department = new Department();
            BeanUtils.copyProperties(departmentVO, department);
            result = baseService.operateDepartment(OperateType.add.getIndex(),null, department);
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

    @ApiOperation(value = "修改部门", notes = "修改部门")
    @RequestMapping(value = "/modifyDepartment", method = RequestMethod.POST)
    Responses modifyDepartment(@RequestParam Long pk, @ModelAttribute DepartmentVO departmentVO) {
        Department result = null;
        try {
            Department department = new Department();
            BeanUtils.copyProperties(departmentVO, department);
            department.setId(pk);
            result = baseService.operateDepartment(OperateType.modify.getIndex(),department.getId(),department);
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

    @ApiOperation(value = "查询部门", notes = "查询部门")
    @RequestMapping(value = "/searchDepartment", method = RequestMethod.POST)
    Responses searchDepartment(@RequestParam(name = "pk") Long pk) {
        Department result = null;
        try {
            result = baseService.operateDepartment(OperateType.search.getIndex(),pk,null);
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

    @ApiOperation(value = "删除部门", notes = "删除部门")
    @RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST)
    Responses deleteDepartment(@RequestParam(name = "pk") Long pk) {
        Department result = null;
        try {
            result = baseService.operateDepartment(OperateType.delete.getIndex(),pk,null);
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

    @ApiOperation(value = "查询所有部门", notes = "查询所有部门")
    @RequestMapping(value = "/searchAllDepartment", method = RequestMethod.POST)
    Responses searchAllDepartment() {
        List<Department> result = null;
        try {
            result = (List<Department>) baseService.findObjectByPk(1,null);
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
