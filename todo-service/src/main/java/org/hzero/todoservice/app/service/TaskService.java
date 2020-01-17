package org.hzero.todoservice.app.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.hzero.todoservice.domain.entity.Task;

/**
 * 应用服务
 *
 * @author yi.liang@hand-china.com 2020-01-10 16:07:24
 */
public interface TaskService {
    /**
     * 更新Task
     *
     * @param task 新的Task
     * @return 更新后的Task
     * @throws NotFoundException
     */
    Task update(Task task) throws NotFoundException;

    /**
     * 创建Task
     *
     * @param task 新的Task
     * @return 新建的Task
     */
    Task create(Task task);

    /**
     * 通过编号删除Task
     *
     * @param taskNumber Task编号
     * @throws NotFoundException
     * @return
     */
    Task deleteByTaskNumber(String taskNumber) throws NotFoundException;
}
