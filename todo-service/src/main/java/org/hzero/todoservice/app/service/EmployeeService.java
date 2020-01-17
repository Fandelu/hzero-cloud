package org.hzero.todoservice.app.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.hzero.todoservice.domain.entity.Employee;

/**
 * 应用服务
 *
 * @author yi.liang@hand-china.com 2020-01-10 16:07:24
 */
public interface EmployeeService {

    /**
     * 创建员工
     *
     * @param employee 新员对象
     * @return 新员工
     */
    Employee create(Employee employee);

    /**
     * 删除员工
     *
     * @param id 员工ID
     * @return 被删除员工
     */
    Employee delete(Long id) throws NotFoundException;
}
