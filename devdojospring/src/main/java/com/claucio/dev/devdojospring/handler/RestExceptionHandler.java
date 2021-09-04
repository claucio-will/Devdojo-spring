package com.claucio.dev.devdojospring.handler;


import com.claucio.dev.devdojospring.exception.BadRequestException;
import com.claucio.dev.devdojospring.exception.BadRequestExceptionDetails;
import com.claucio.dev.devdojospring.exception.ExceptionDatails;
import com.claucio.dev.devdojospring.exception.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException badException) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Check Documentation!")
                        .details(badException.getMessage())
                        .developerMessage(getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders httpHeaders, HttpStatus status,
            WebRequest request) {

        //Pegando todas as mensagens
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        //Pegando todos os campos fields da exceção
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));

        //Pegando todas as mensagens do campo fieldMessages
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Check Documentation!")
                        .details("Check the field(s) errors")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldMessages)
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDatails exceptionDatails = ExceptionDatails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(ex.getCause().getMessage())
                .details(ex.getMessage())
                .developerMessage(getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDatails,headers,status);
      }
}
