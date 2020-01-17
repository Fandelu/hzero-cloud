package org.hzero.todoservice.app.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hzero.boot.imported.app.service.ValidatorHandler;
import org.hzero.boot.imported.infra.validator.annotation.ImportValidator;
import org.hzero.boot.imported.infra.validator.annotation.ImportValidators;
import org.hzero.core.util.ValidUtils;
import org.hzero.todoservice.domain.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

/**
 * @author yi.liang@hand-china.com
 * @version 0.0.1
 * @date 2020/1/15 18:02
 */
@ImportValidators({
        @ImportValidator(templateCode = "HTDO.25497.IMPORT.EMP.CLIENT")
})
public class ValidateServiceImpl extends ValidatorHandler {

    private static final Logger log = LoggerFactory.getLogger(ValidateServiceImpl.class);

    @Autowired
    private Validator validator;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean validate(String data) {
        log.info("@自定数据校验开始 ==>> {}", data);
        Employee employee;
        try {
            employee = objectMapper.readValue(data, Employee.class);
            ValidUtils.valid(validator, employee);
        } catch (Exception e) {
            log.error("validate fail.");
            return false;
        }

        return true;
    }
}
