package com.tcps.gaowy.serviceandroid.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class IllegalLicenseValidator implements ConstraintValidator<IllegalLicense, Object> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void initialize(IllegalLicense accident) {
        log.info("开始验证车辆牌照");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String sql = "SELECT count(1) FROM tab_bus_info WHERE license = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{o}, Integer.class) > 0;
    }

}
