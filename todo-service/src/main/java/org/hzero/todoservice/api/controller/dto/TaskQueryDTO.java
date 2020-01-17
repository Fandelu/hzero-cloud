package org.hzero.todoservice.api.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yi.liang@hand-china.com
 * @version 0.0.1
 * @date 2020/1/10 16:39
 */
@ApiModel("任务查询参数")
public class TaskQueryDTO {

    @ApiModelProperty("任务ID")
    private Long id;
    @ApiModelProperty("员工ID")
    private Long empId;
    @ApiModelProperty("任务编号")
    private String taskNumber;
    @ApiModelProperty("任务描述")
    private String taskDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
