package org.training.user.service.utils;

import java.util.Arrays;

public class FieldChecker {

    /**
     * Checks if an object has empty fields.
     *
     * @param object the object to check
     * @return true if the object has empty fields, false otherwise
     */
    public static boolean hasEmptyFields(Object object) {

        if (object == null){
            return true;
        }

        return Arrays.stream(object.getClass().getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .anyMatch(field -> {
                    try {
                        Object value = field.get(object);

                        if (value != null) {
                            if (field.getType().isEnum()) {
                                return false;
                            } else if (!field.getType().isPrimitive() && !field.getType().getName().startsWith("java.")) {
                                return hasEmptyFields(value);
                            }
                        }

                        return value == null;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });

    }
}
