package org.hzero.todoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import io.choerodon.resource.annoation.EnableChoerodonResourceServer;

/**
 * @author yi.liang@hand-china.com
 * @version 0.0.1
 * @date 2020/01/10
 */
@EnableChoerodonResourceServer
@SpringCloudApplication
public class TodoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoServiceApplication.class, args);
    }

}
