package org.hzero.todoservice.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.Receiver;
import org.hzero.core.util.Results;
import org.hzero.todoservice.config.SwaggerApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;

/**
 * @author yi.liang@hand-china.com
 * @version 0.0.1
 * @date 2020/1/15 10:14
 */
@Api(tags = SwaggerApiConfig.MESSAGE)
@RestController("messageController.v1")
@RequestMapping("/v1/{organizationId}/messages")
public class MessageClientController {

    @Autowired
    private MessageClient messageClient;

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation("发送站内消息")
    @GetMapping("/send-web")
    public ResponseEntity<?> sentWebNotice(@PathVariable("organizationId") @ApiParam(value = "租户ID", example = "0", required = true) Long tenantId,
                                           @ApiParam(value = "消息模板编码", required = true) String messageTemplateCode,
                                           @ApiParam(value = "验证码", required = true) String captcha) {
        List<Receiver> receivers = new ArrayList<>(1);
        Receiver receiver = new Receiver().setUserId(1L).setTargetUserTenantId(tenantId);
        receivers.add(receiver);
        Map<String, String> args = new HashMap<>(1);
        args.put("captcha", captcha);
        messageClient.sendWebMessage(tenantId, messageTemplateCode, "zh_CN", receivers, args);
        return Results.success();
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation("发送邮件")
    @GetMapping("/send-email")
    public ResponseEntity<?> setEmail(@PathVariable("organizationId") Long tenantId, String captcha) {
        Receiver receiver = new Receiver().setEmail("yi.liang@hand-china.com");
        List<Receiver> receiverList = new ArrayList<>(1);
        receiverList.add(receiver);

        String messageTemplateCode = "HIAM.MODIFY_PASSWORD";

        Map<String, String> args = new HashMap<>(1);
        args.put("captcha", captcha);

        messageClient.sendEmail(tenantId, "HZERO", messageTemplateCode, receiverList, args);
        return Results.success();
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation("发送短信")
    @GetMapping("/send-sms")
    public ResponseEntity<?> sendSMS(@PathVariable("organizationId") Long tenantId, String captcha) {

        String serverCode = "HZERO_BAIDU";

        String messageTemplateCode = "HIAM.CAPTCHA";

        Receiver receiver = new Receiver().setPhone("15200393907").setIdd("+86");
        List<Receiver> receiverList = new ArrayList<>(1);
        receiverList.add(receiver);

        Map<String, String> args = new HashMap<>(1);
        args.put("captcha", captcha);

        messageClient.sendSms(tenantId, serverCode, messageTemplateCode, "zh_CN", receiverList, args);

        return Results.success();
    }

}
