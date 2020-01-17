package org.hzero.todoservice.api.controller.v1;

import io.swagger.annotations.Api;
import org.apache.ibatis.javassist.NotFoundException;
import org.hzero.boot.platform.lov.adapter.LovAdapter;
import org.hzero.boot.platform.lov.annotation.ProcessLovValue;
import org.hzero.boot.platform.lov.dto.LovDTO;
import org.hzero.core.base.BaseConstants;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.hzero.todoservice.app.service.TaskService;
import org.hzero.todoservice.config.SwaggerApiConfig;
import org.hzero.todoservice.domain.entity.Task;
import org.hzero.todoservice.domain.repository.TaskRepository;
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
@Api(tags = SwaggerApiConfig.TASK)
@RestController("taskController.v1")
@RequestMapping("/v1/{organizationId}/tasks")
public class TaskController extends BaseController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private LovAdapter lovAdapter;

    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.PROJECT)
    @GetMapping
    public ResponseEntity<Page<Task>> list(@PathVariable("organizationId") Long tenantId, Task task, @ApiIgnore @SortDefault(value = Task.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        task.setTenantId(tenantId);
        Page<Task> list = taskRepository.pageAndSort(pageRequest, task);
        return Results.success(list);
    }

    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.PROJECT)
    @GetMapping("/{id}")
    public ResponseEntity<Task> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long id) {
        Task task = taskRepository.selectByPrimaryKey(id);
        return Results.success(task);
    }

    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.PROJECT)
    @PostMapping
    public ResponseEntity<Task> create(@PathVariable("organizationId") Long organizationId, @RequestBody Task task) {
        task.setTenantId(organizationId);
        validObject(task);
        taskService.create(task);
        return Results.success(task);
    }

    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.PROJECT)
    @PutMapping
    public ResponseEntity<Task> update(@PathVariable("organizationId") Long organizationId, @RequestBody Task task) throws NotFoundException {
        SecurityTokenHelper.validToken(task);
        taskService.update(task);
        return Results.success(task);
    }

    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.PROJECT)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Task task) {
        SecurityTokenHelper.validToken(task);
        taskRepository.deleteByPrimaryKey(task);
        return Results.success();
    }

    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @ApiOperation(value = "根据TaskNumber删除")
    @Permission(level = ResourceLevel.PROJECT)
    @DeleteMapping("/{taskNumber}")
    public ResponseEntity<Task> removeByTaskNumber(@PathVariable("organizationId") Long organizationId, @PathVariable("taskNumber") String taskNumber) throws NotFoundException {
        return Results.success(taskService.deleteByTaskNumber(taskNumber));
    }

    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @ApiOperation("分页查询")
    @Permission(level = ResourceLevel.PROJECT)
    @GetMapping("/page")
    public ResponseEntity<Page<Task>> pageTask(@PathVariable("organizationId") Long organizationId, Task task, PageRequest pageRequest) {
        task.setTenantId(organizationId);
        return Results.success(taskRepository.pageTask(pageRequest, task));
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation("Lov测试")
    @GetMapping("/lov/{lovCode}")
    public ResponseEntity<LovDTO> getLovValue(@PathVariable("organizationId") Long tenantId, @PathVariable(value = "lovCode") String lovCode) {
        return Results.success(lovAdapter.queryLovInfo(lovCode, tenantId));
    }


}
