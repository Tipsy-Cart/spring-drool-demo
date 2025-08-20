package com.demo.controller;

import com.demo.bean.Invoice;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

@RestController
public class JSONValidator {

    @PostMapping(value = "/json")
    public Set<ValidationMessage> validate(@RequestBody Invoice invoice) throws IOException {
        try (InputStream schemaStream = JsonSchemaIdValidator.class
                .getClassLoader()
                .getResourceAsStream("Invoice-Schema.json")){
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(invoice));
            SchemaValidatorsConfig config = new SchemaValidatorsConfig();
            config.setFormatAssertionsEnabled(true);
            JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);
            JsonSchema schema = jsonSchemaFactory.getSchema(schemaStream, config);
            Set<ValidationMessage> validationMessages = schema.validate(jsonNode);
            return validationMessages;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

}
