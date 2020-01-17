package org.hzero.todoservice.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hzero.boot.imported.app.service.IDoImportService;
import org.hzero.boot.imported.app.service.ImportHandler;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.hzero.todoservice.app.service.EmployeeService;
import org.hzero.todoservice.domain.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yi.liang@hand-china.com
 * @version 0.0.1
 * @date 2020/1/15 17:57
 */
@ImportService(templateCode = "HTDO.25497.IMPORT.EMP.CLIENT")
public class EmployeeImportServiceImpl extends ImportHandler {

    private static final Logger log = LoggerFactory.getLogger(EmployeeImportServiceImpl.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmployeeService employeeService;

    private Map<String, Object> args;

    @Override
    public Boolean doImport(String data) {
        log.info("@data ==>> {}", data);
        Employee employee;
        try {
            employee = objectMapper.readValue(data, Employee.class);
        } catch (IOException e) {
            log.error("<<<<<<<<<<<<<<< real value fail. >>>>>>>>>>>>>>>");
            return false;
        }
        Boolean result = employeeService.create(employee) != null;
        if (result) {
            log.info("<<<<<<<<<<<<<<< import success! >>>>>>>>>>>>>>>");
        } else {
            log.info("<<<<<<<<<<<<<<< import fail! >>>>>>>>>>>>>>>");
        }
        return result;
    }

    @Override
    public void onStart() {
        args = getArgs();
        Iterator<Map.Entry<String, Object>> iterator = args.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            log.info("@args ==>> key : {} -- value : {}", entry.getKey(), entry.getValue());
        }
        log.info("<<<<<<<<<<<<<<< starting import data into datasource! >>>>>>>>>>>>>>>");
    }

    @Override
    public void onFinish() {
        log.info("<<<<<<<<<<<<<<< import data into datasource finished! >>>>>>>>>>>>>>>");
    }
}
/*public class EmployeeImportServiceImpl implements IDoImportService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeImportServiceImpl.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public Boolean doImport(String data) {

        log.info("@data ==>> {}", data);
        Employee employee;
        try {
            employee = objectMapper.readValue(data, Employee.class);
        } catch (IOException e) {
            log.error("<<<<<<<<<<<<<<< real value fail. >>>>>>>>>>>>>>>");
            return false;
        }
        Boolean result = employeeService.create(employee) != null;
        if (result) {
            log.info("<<<<<<<<<<<<<<< import success! >>>>>>>>>>>>>>>");
        } else {
            log.info("<<<<<<<<<<<<<<< import fail! >>>>>>>>>>>>>>>");
        }
        return result;
    }

    @Override
    public void onStart() {
        log.info("<<<<<<<<<<<<<<< starting import data into datasource! >>>>>>>>>>>>>>>");
    }

    @Override
    public void onFinish() {
        log.info("<<<<<<<<<<<<<<< import data into datasource finished! >>>>>>>>>>>>>>>");
    }
}*/
