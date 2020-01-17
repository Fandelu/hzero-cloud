package org.hzero.todoservice.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author yi.liang@hand-china.com
 * @version 0.0.1
 * @date 2020/1/10 16:08
 */
@Configuration
public class SwaggerApiConfig {

    public static final String EMPLOYEE = "Employee";
    public static final String TASK = "Task";
    public static final String FILE = "File";
    public static final String MESSAGE = "Message";


    public SwaggerApiConfig(Docket docket) {
        docket.tags(new Tag(EMPLOYEE, "员工"),
                new Tag(TASK, "任务"),
                new Tag(MESSAGE, "消息"),
                new Tag(FILE, "文件"));
    }
}
