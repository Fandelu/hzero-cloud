package org.hzero.todoservice.infra.repository.impl;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.todoservice.domain.entity.Employee;
import org.hzero.todoservice.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

/**
 *  资源库实现
 *
 * @author yi.liang@hand-china.com 2020-01-10 16:07:24
 */
@Component
public class EmployeeRepositoryImpl extends BaseRepositoryImpl<Employee> implements EmployeeRepository {

  
}
