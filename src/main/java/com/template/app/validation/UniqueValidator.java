package com.template.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private String table;
    private String column;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.table = constraintAnnotation.table();
        this.column = constraintAnnotation.column();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String sql = "SELECT * FROM " + table + " WHERE " + column + " = :value";
        Map<String, Object> params = new HashMap<>();
        params.put("value", value);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, params);

        return result.isEmpty();
    }
}