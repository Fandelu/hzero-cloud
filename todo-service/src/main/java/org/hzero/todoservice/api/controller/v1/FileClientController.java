package org.hzero.todoservice.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.file.FileClient;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.hzero.todoservice.config.SwaggerApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;

/**
 * @author yi.liang@hand-china.com
 * @version 0.0.1
 * @date 2020/1/15 9:44
 */
@Api(tags = SwaggerApiConfig.FILE)
@RestController
@RequestMapping("/v1/{organizationId}/files")
public class FileClientController{

    private static final Logger log = LoggerFactory.getLogger(FileClientController.class);

    @Autowired
    private FileClient fileClient;

    @ApiOperation("上传文件")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<String> uploadFile(@PathVariable("organizationId") Long organizationId, String bucketName, String path, MultipartFile file) {
        String fileName = file.getName();
        long fileSize = file.getSize();
        log.info("file name ==>> {}", fileName);
        log.info("file size ==>> {}", fileSize);
        return Results.success(fileClient.uploadFile(organizationId, bucketName, path, file));
    }

}
