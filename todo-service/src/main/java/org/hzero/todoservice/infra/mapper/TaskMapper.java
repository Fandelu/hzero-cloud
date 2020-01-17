package org.hzero.todoservice.infra.mapper;

import org.hzero.todoservice.domain.entity.Task;

import java.util.List;

import io.choerodon.mybatis.common.BaseMapper;

/**
 * Mapper
 *
 * @author yi.liang@hand-china.com 2020-01-10 16:07:24
 */

public interface TaskMapper extends BaseMapper<Task> {

    /**
     * 查询所有任务
     * @param task 查询参数
     * @return
     */
    List<Task> selectTask(Task task);
}
