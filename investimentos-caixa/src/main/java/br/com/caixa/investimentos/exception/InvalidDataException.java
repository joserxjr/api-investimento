package br.com.caixa.investimentos.exception;


//Esta exceção resulta em HTTP 400 (Bad Request)
public class InvalidDataException extends RuntimeException{
    private final String fieldName;

    public InvalidDataException(String message) {
        super(message);
        this.fieldName = null;
    }

    public InvalidDataException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }


}
