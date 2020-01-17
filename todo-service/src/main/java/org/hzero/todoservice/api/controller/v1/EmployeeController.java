package org.hzero.todoservice.api.controller.v1;

import io.swagger.annotations.Api;
import org.apache.ibatis.javassist.NotFoundException;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.hzero.todoservice.app.service.EmployeeService;
import org.hzero.todoservice.config.SwaggerApiConfig;
import org.hzero.todoservice.domain.entity.Employee;
import org.hzero.todoservice.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.hzero.mybatis.helper.SecurityTokenHelper;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 管理 API
 *
 * @author yi.liang@hand-china.com 2020-01-10 16:07:24
 */
@Api(tags = SwaggerApiConfig.EMPLOYEE)
@RestController("employeeController.v1")
@RequestMapping("/v1/employees")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<Employee>> list(@PathVariable("organizationId") Long tenantId, Employee employee, @ApiIgnore @SortDefault(value = Employee.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Employee> list = employeeRepository.pageAndSort(pageRequest, employee);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{id}")
    public ResponseEntity<Employee> detail(@PathVariable Long id) {
        Employee employee = employeeRepository.selectByPrimaryKey(id);
        return Results.success(employee);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        validObject(employee);
//        employeeRepository.insertSelective(employee);
        employeeService.create(employee);
        return Results.success(employee);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        SecurityTokenHelper.validToken(employee);
        employeeRepository.updateByPrimaryKeySelective(employee);
        return Results.success(employee);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Employee employee) {
        SecurityTokenHelper.validToken(employee);
        employeeRepository.deleteByPrimaryKey(employee);
        return Results.success();
    }

    @ApiOperation(value = "通过ID删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> removeByKey(@PathVariable("id") Long id) throws NotFoundException {
        return Results.success(employeeService.delete(id));
    }

}
