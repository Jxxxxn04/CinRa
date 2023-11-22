package com.example.cinra.data.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EmptyErrorResponse<T> {

    public List<String> checkForEmptyValues(T object) {
        List<String> emptyFields = getEmptyFields(object);

        if (!emptyFields.isEmpty()) {
            return emptyFields;
        }

        return new ArrayList<>();
    }

    private List<String> getEmptyFields(T object) {
        List<String> emptyFields = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(object);

                // Add the field name to the list if it is null
                if (value == null) {
                    emptyFields.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return emptyFields;
    }
}
