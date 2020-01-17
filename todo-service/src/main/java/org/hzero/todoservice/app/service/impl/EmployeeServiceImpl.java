package org.hzero.todoservice.app.service.impl;

import org.apache.ibatis.javassist.NotFoundException;
import org.hzero.boot.platform.code.builder.CodeRuleBuilder;
import org.hzero.todoservice.app.service.EmployeeService;
import org.hzero.todoservice.domain.entity.Employee;
import org.hzero.todoservice.domain.entity.Task;
import org.hzero.todoservice.domain.repository.EmployeeRepository;
import org.hzero.todoservice.domain.repository.TaskRepository;
import org.hzero.todoservice.infra.mapper.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 应用服务默认实现
 *
 * @author yi.liang@hand-china.com 2020-01-10 16:07:24
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CodeRuleBuilder codeRuleBuilder;

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Employee create(Employee employee) {
        // 让数据库自动生成ID
        employee.setId(null);
        String empNumber = codeRuleBuilder.generateCode("HTDO.25497.EMP.NUMBER", null);
        log.info("generate a new empNumber ==>> {}", empNumber);
        employee.setEmpNumber(empNumber);
        return employeeRepository.insertSelective(employee) > 0 ? employee : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Employee delete(Long id) throws NotFoundException {
        Employee employeeExist = employeeRepository.selectByPrimaryKey(id);
        if (employeeExist == null) {
            throw new NotFoundException("员工未找到！");
        } else {
            List<Task> taskList = taskRepository.selectTask(new Task().setEmpId(id));
            // 删除员工下的任务
            taskRepository.batchDelete(taskList);
            return employeeRepository.delete(employeeExist) > 0 ? employeeExist : null;
        }
    }
}
