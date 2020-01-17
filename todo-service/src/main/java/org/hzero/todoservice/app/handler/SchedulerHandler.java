package org.hzero.todoservice.app.handler;

import org.hzero.boot.scheduler.api.dto.JobProgress;
import org.hzero.boot.scheduler.infra.annotation.JobHandler;
import org.hzero.boot.scheduler.infra.enums.ReturnT;
import org.hzero.boot.scheduler.infra.handler.IJobHandler;
import org.hzero.boot.scheduler.infra.tool.SchedulerTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author yi.liang@hand-china.com
 * @version 0.0.1
 * @date 2020/1/16 16:47
 */
@JobHandler("25497_EXECUTOR")
public class SchedulerHandler implements IJobHandler {
    private static final Logger log = LoggerFactory.getLogger(SchedulerHandler.class);

    @Override
    public void onCreate(SchedulerTool tool) {
        tool.info("任务开始执行！\n\n");
    }

    @Override
    public void onException(SchedulerTool tool) {
        tool.error("任务碰到异常了！\n\n");
        Integer progress = tool.getJobProgress().getProgress();
        tool.info(String.format("任务进度%d.\n\n", progress));
    }

    @Override
    public void onFinish(SchedulerTool tool, ReturnT returnT) {
        tool.info("任务执行完毕！\n\n");
    }

    @Override
    public ReturnT execute(Map<String, String> map, SchedulerTool tool) {
        log.info("25497_EXECUTOR is running!");
        tool.info("25497_EXECUTOR is running!");
        tool.updateProgress(100, "执行完毕！");
        return ReturnT.SUCCESS;
    }
}
