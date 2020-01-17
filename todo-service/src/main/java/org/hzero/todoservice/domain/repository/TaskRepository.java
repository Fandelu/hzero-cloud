package org.hzero.todoservice.domain.repository;

import org.hzero.mybatis.base.BaseRepository;
import org.hzero.todoservice.domain.entity.Task;

import java.util.List;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 资源库
 *
 * @author yi.liang@hand-china.com 2020-01-10 16:07:24
 */
public interface TaskRepository extends BaseRepository<Task> {

    /**
     * 分页查询任务
     *
     * @param pageRequest 分页参数
     * @param task        查询参数
     * @return 分页数据
     */
    Page<Task> pageTask(PageRequest pageRequest, Task task);

    /**
     * 查询Task
     *
     * @param task 查询参数
     * @return
     */
    List<Task> selectTask(Task task);
}
