package br.com.caixa.investimentos.exception;



// Esta exceção resulta em HTTP 422 (Unprocessable Entity)

public class BusinessRuleException extends RuntimeException{

    private final String errorCode;

    public BusinessRuleException(String message) {
        super(message);
        this.errorCode = null;
    }

    public BusinessRuleException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
