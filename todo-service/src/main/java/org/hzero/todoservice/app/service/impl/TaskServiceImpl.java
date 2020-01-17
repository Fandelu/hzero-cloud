package org.hzero.todoservice.app.service.impl;

import org.apache.ibatis.javassist.NotFoundException;
import org.hzero.boot.platform.code.builder.CodeRuleBuilder;
import org.hzero.todoservice.app.service.TaskService;
import org.hzero.todoservice.domain.entity.Task;
import org.hzero.todoservice.domain.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * 应用服务默认实现
 *
 * @author yi.liang@hand-china.com 2020-01-10 16:07:24
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;
    @Autowired
    CodeRuleBuilder codeRuleBuilder;

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task update(Task task) throws NotFoundException {
        // 查询Task是否存在
        Task taskExist = repository.selectByPrimaryKey(task);
        if (taskExist == null) {
            throw new NotFoundException("Task未找到！");
        } else {
            // 指定字段更新
            return repository.updateOptional(task, Task.FIELD_STATE, Task.FIELD_TASK_DESCRIPTION) > 0 ? task : null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task create(Task task) {
        String taskNumber = codeRuleBuilder.generateCode("HTDO.25497.TASK", null);
        log.info("generate a new task code ==>> {}", taskNumber);
        task.setTaskNumber(taskNumber);
        return repository.insert(task) > 0 ? task : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task deleteByTaskNumber(String taskNumber) throws NotFoundException {
        Task task = repository.selectOne(new Task().setTaskNumber(taskNumber));
        if (task == null) {
            throw new NotFoundException("Task未找到！");
        } else {
            return repository.delete(task) > 0 ? task : null;
        }
    }
}
