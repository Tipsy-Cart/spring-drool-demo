package com.demo.controller;

import com.demo.bean.Invoice;
import com.demo.bean.ValidationError;
import com.demo.config.CustomDateFormatValidator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class JSONValidator {

    private static final Pattern ARRAY_INDEX_PATTERN = Pattern.compile("\\[(\\d+)\\]");


    @PostMapping(value = "/json")
    public Map<String, String> validate(@RequestBody Invoice invoice) throws IOException {
        try (InputStream schemaStream = JsonSchemaIdValidator.class.getClassLoader().getResourceAsStream("Schema/Invoice-Schema.json");
             InputStream errorStream = JsonSchemaIdValidator.class.getClassLoader().getResourceAsStream("Schema/Invoice-Error-Message.json");){
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(invoice));
            SchemaValidatorsConfig config = new SchemaValidatorsConfig();
            config.setFormatAssertionsEnabled(true);
            Map<String, ValidationError> validationErrorMap = mapper.readValue(errorStream, new TypeReference<Map<String, ValidationError>>() {});
            JsonMetaSchema metaSchema = JsonMetaSchema.builder(JsonMetaSchema.getV202012())
                    .format(new CustomDateFormatValidator())
                    .build();
            JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012, builder -> builder.metaSchema(metaSchema));
            JsonSchema schema = jsonSchemaFactory.getSchema(schemaStream, config);
            Set<ValidationMessage> validationMessages = schema.validate(jsonNode);
            Map<String, String> errors = new HashMap<>();
            validationMessages.forEach(vm -> {
                String location = vm.getInstanceLocation().toString();
                String property = vm.getProperty();
                String type = vm.getType();
                property = null != property ? "." + property : "";
                String errorKey = location + property + "|" + type;
                System.out.println(errorKey);
                Matcher matcher = ARRAY_INDEX_PATTERN.matcher(errorKey);
                List<Integer> indices = new ArrayList<>();
                while (matcher.find()) {
                    indices.add(Integer.parseInt(matcher.group(1)));
                }
                if(indices.isEmpty()){
                    ValidationError validationError = validationErrorMap.get(errorKey);
                    if(null != validationError){
                        errors.put(validationError.getField(), validationError.getMessage());
                    }
                } else {
                    String path = errorKey.replaceAll("\\[\\d+\\]", "[%d]");
                    ValidationError validationError = validationErrorMap.get(path);
                    if(null != validationError){
                        errors.put(String.format(validationError.getField(), indices.toArray()), validationError.getMessage());
                    }
                }


            });
            return errors;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /*@PostMapping(value = "/everit")
    public  List<ValidationException> everit(@RequestBody Invoice invoice) throws IOException {
        try (InputStream schemaStream = JsonSchemaIdValidator.class
                .getClassLoader()
                .getResourceAsStream("Schema/Invoice-Schema.json")){

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(new JSONObject(mapper.writeValueAsString(invoice)));
            return List.of();
        }catch (ValidationException e){
            List<ValidationException> causingExceptions = e.getCausingExceptions();
            return causingExceptions;
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }*/

}
