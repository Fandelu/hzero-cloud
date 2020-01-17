package org.hzero.todoservice.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hzero.boot.platform.lov.annotation.LovValue;

import java.util.UUID;

/**
 * @author yi.liang@hand-china.com 2020-01-10 16:07:24
 */
@ApiModel("任务")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "todo_task")
public class Task extends AuditDomain {

    public static final String FIELD_ID = "id";
    public static final String FIELD_EMP_ID = "empId";
    public static final String FIELD_STATE = "state";
    public static final String FIELD_TASK_NUMBER = "taskNumber";
    public static final String FIELD_TASK_DESCRIPTION = "taskDescription";
    public static final String FIELD_TENANT_ID = "tenantId";

    //
    // 业务方法(按public protected private顺序排列)
    // ------------------------------------------------------------------------------

    //
    // 数据库字段
    // ------------------------------------------------------------------------------


    @ApiModelProperty("")
    @Id
    @GeneratedValue
    private Long id;
    @ApiModelProperty(value = "员工ID")
    @NotNull
    private Long empId;

    @ApiModelProperty(value = "任务状态")
    @LovValue(lovCode = "HTDO.25497.TASK.STATUS", meaningField = "stateMeaning")
    private String state;

    @ApiModelProperty(value = "任务编号")
    @NotBlank
    private String taskNumber;

    @ApiModelProperty(value = "任务描述")
    private String taskDescription;

    @ApiModelProperty(value = "租户ID")
    @NotNull
    private Long tenantId;

    @Transient
    private String stateMeaning;

    //
    // 非数据库字段
    // ------------------------------------------------------------------------------

    @Transient
    @ApiModelProperty("员工姓名")
    private String empName;
    @Transient
    @ApiModelProperty("员工编号")
    private String empNumber;
    //
    // getter/setter
    // ------------------------------------------------------------------------------

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     */
    public Long getEmpId() {
        return empId;
    }

    public Task setEmpId(Long empId) {
        this.empId = empId;
        return this;
    }

    /**
     * @return
     */
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return
     */
    public String getTaskNumber() {
        return taskNumber;
    }

    public Task setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
        return this;
    }

    /**
     * @return
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * @return
     */
    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }


    public String getStateMeaning() {
        return stateMeaning;
    }

    public void setStateMeaning(String stateMeaning) {
        this.stateMeaning = stateMeaning;
    }
}
