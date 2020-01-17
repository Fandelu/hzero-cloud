package org.hand.file;

import org.hzero.autoconfigure.file.EnableHZeroFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.choerodon.resource.annoation.EnableChoerodonResourceServer;

@EnableHZeroFile
@EnableDiscoveryClient
@SpringBootApplication
public class HzeroFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(HzeroFileApplication.class, args);
    }
}


