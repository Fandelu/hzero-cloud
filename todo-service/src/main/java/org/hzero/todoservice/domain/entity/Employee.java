package org.hzero.todoservice.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yi.liang@hand-china.com 2020-01-10 16:07:24
 */
@ApiModel("员工")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "todo_employee")
public class Employee extends AuditDomain {

    public static final String FIELD_ID = "id";
    public static final String FIELD_EMP_NAME = "empName";
    public static final String FIELD_EMP_NUMBER = "empNumber";
    public static final String FIELD_EMAIL = "email";

    //
    // 业务方法(按public protected private顺序排列)
    // ------------------------------------------------------------------------------

    //
    // 数据库字段
    // ------------------------------------------------------------------------------


    @ApiModelProperty("员工ID")
    @Id
    @GeneratedValue
    private Long id;
    @ApiModelProperty(value = "员工姓名")
    @NotBlank
    private String empName;
    @ApiModelProperty(value = "员工编号")
    @NotBlank
    private String empNumber;
    @ApiModelProperty(value = "邮件")
    @Email
    private String email;

    //
    // 非数据库字段
    // ------------------------------------------------------------------------------

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
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * @return
     */
    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
