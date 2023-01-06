package com.tableorder.tableorder.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tableorder.tableorder.entity.Role;

import javax.persistence.AttributeConverter;
import java.util.List;

public class ListRoleConverter implements AttributeConverter<List<Role>, String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Role> roles) {
        try {
            return objectMapper.writeValueAsString(roles);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> convertToEntityAttribute(String roles) {
        try {
            return objectMapper.readValue(roles, new TypeReference<List<Role>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
