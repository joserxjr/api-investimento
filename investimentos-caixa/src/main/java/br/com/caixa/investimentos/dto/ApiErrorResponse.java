package br.com.caixa.investimentos.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {

private LocalDateTime timestamp;
private Integer status;
private String error;
private String message;
private String path;
private List<FieldError> errors; // Para validações que falham em múltiplos campos

public ApiErrorResponse() {
    this.timestamp = LocalDateTime.now();
}

public ApiErrorResponse(Integer status, String error, String message, String path) {
    this.timestamp = LocalDateTime.now();
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
}


public LocalDateTime getTimestamp() {
    return timestamp;
}

public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
}

public Integer getStatus() {
    return status;
}

public void setStatus(Integer status) {
    this.status = status;
}

public String getError() {
    return error;
}

public void setError(String error) {
    this.error = error;
}

public String getMessage() {
    return message;
}

public void setMessage(String message) {
    this.message = message;
}

public String getPath() {
    return path;
}

public void setPath(String path) {
    this.path = path;
}

public List<FieldError> getErrors() {
    return errors;
}

public void setErrors(List<FieldError> errors) {
    this.errors = errors;
}

/**
 * Representa um erro em um campo específico (usado em validações)
 */
public static class FieldError {
    private String field;
    private String message;
    private Object rejectedValue;

    public FieldError(String field, String message, Object rejectedValue) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
}

}
