package org.hzero.todoservice.infra.repository.impl;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.todoservice.domain.entity.Task;
import org.hzero.todoservice.domain.repository.TaskRepository;
import org.hzero.todoservice.infra.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 资源库实现
 *
 * @author yi.liang@hand-china.com 2020-01-10 16:07:24
 */
@Component
public class TaskRepositoryImpl extends BaseRepositoryImpl<Task> implements TaskRepository {

    private TaskMapper taskMapper;

    public TaskRepositoryImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public Page<Task> pageTask(PageRequest pageRequest, Task task) {
        return PageHelper.doPage(pageRequest, () -> taskMapper.selectTask(task));
    }

    @Override
    public List<Task> selectTask(Task task) {
        return taskMapper.selectTask(task);
    }
}
