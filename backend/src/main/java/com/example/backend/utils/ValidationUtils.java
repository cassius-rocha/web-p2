package com.example.backend.utils;

import java.util.List;

public class ValidationUtils {

    public static <T> List<T> validateListNotEmpty(List<T> list, String errorMessage) {
        if (list == null || list.isEmpty()) {
            throw new RuntimeException(errorMessage);
        }
        return list;
    }
}