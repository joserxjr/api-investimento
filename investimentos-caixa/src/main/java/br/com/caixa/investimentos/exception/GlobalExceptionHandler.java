package br.com.caixa.investimentos.exception;


import br.com.caixa.investimentos.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {


    /*
     * Trata exceções de recurso não encontrado.
     * HTTP 404 - Not Found
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Recurso Não Encontrado",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /*
     * Trata exceções de regra de negócio.
     * HTTP 422 - Unprocessable Entity
     */

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessRuleException(
            BusinessRuleException ex,HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Regra de negocios violada",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    /*
     * Trata exceções de dados inválidos.
     * HTTP 400 - Bad Request
     */
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidDataException(
            InvalidDataException ex, HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Dados inválidos",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

     /* Trata erros de validação do Bean Validation (@Valid).
            * HTTP 400 - Bad Request
             * Quando usamos @Valid nos DTOs, o Spring valida automaticamente.
     * Se a validação falha, esta exceção é lançada.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        ApiErrorResponse error = new ApiErrorResponse();
        error.setTimestamp(java.time.LocalDateTime.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Erro de Validação");
        error.setMessage("Um ou mais campos estão inválidos");
        error.setPath(request.getRequestURI());

        // Coleta todos os erros de validação
        List<ApiErrorResponse.FieldError> fieldErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            fieldErrors.add(new ApiErrorResponse.FieldError(
                    fieldError.getField(),
                    fieldError.getDefaultMessage(),
                    fieldError.getRejectedValue()
            ));
        });
        error.setErrors(fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /*
     * Trata erros quando o JSON enviado está malformado.
     * HTTP 400 - Bad Request
     *
     * Ex: JSON com sintaxe inválida, vírgulas faltando, etc.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException
    (HttpMessageNotReadableException  ex, HttpServletRequest request){
        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "JSON Malformado",
                "O corpo da requisição está malformado ou contém dados inválidos",
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /*
     * Trata erros de conversão de tipo em parâmetros de URL.
     * HTTP 400 - Bad Request
     *
     * Ex: Esperava Long mas recebeu String não numérica
     * /perfil-risco/abc (abc não pode ser convertido para Long)
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleTypeMismatchException(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        String message = String.format(
                "O parâmetro '%s' deve ser do tipo %s, mas foi fornecido: '%s'",
                ex.getName(),
                ex.getRequiredType().getSimpleName(),
                ex.getValue()
        );

        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Tipo de Parâmetro Inválido",
                message,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /*
     * Handler genérico para exceções não tratadas especificamente.
     * HTTP 500 - Internal Server Error
     *
     * Este é o "catch-all" para qualquer exceção inesperada.
     * Em produção, você deve logar estes erros para investigação.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        // Em produção, você deve logar este erro para análise
        System.err.println("Erro não tratado: " + ex.getClass().getName());
        ex.printStackTrace();

        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro Interno do Servidor",
                "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


}
