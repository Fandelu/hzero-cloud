package org.hzero.register;

import org.hzero.autoconfigure.register.EnableHZeroRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yi.liang@hand-china.com
 * @version 0.11.6
 * @date 2020/01/11
 */

@EnableHZeroRegister
@EnableEurekaServer
@SpringBootApplication
public class HzeroRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(HzeroRegisterApplication.class, args);
    }

}
