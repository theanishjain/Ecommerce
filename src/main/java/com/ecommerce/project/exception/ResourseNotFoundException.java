package com.ecommerce.project.exception;

public class ResourseNotFoundException extends RuntimeException {

    String field;
    String fieldName;
    String resourceName;
    Long id;

    public ResourseNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("%s not found with %s: %s", resourceName, field, fieldName));
        this.field = field;
        this.fieldName = fieldName;
        this.resourceName = resourceName;
    }

    public ResourseNotFoundException(String resourceName, String field, Long id) {
        super(String.format("%s not found with %s: %s", resourceName,field, id));
        this.field = field;
        this.resourceName = resourceName;
        this.id = id;
    }
}
