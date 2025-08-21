package com.demo.config;

import com.networknt.schema.ExecutionContext;
import com.networknt.schema.Format;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomDateFormatValidator implements Format {

    private static final String NAME = "custom-date";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean matches(ExecutionContext executionContext, String value) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
