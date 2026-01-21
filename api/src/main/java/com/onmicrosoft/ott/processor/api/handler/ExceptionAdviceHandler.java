package com.onmicrosoft.ott.processor.api.handler;

import static com.onmicrosoft.ott.processor.core.businessexception.ErrorCode.VALIDATION_FAILED;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onmicrosoft.ott.processor.core.businessexception.CustomException;
import com.onmicrosoft.ott.processor.core.spring.api.FieldError;
import com.onmicrosoft.ott.processor.core.spring.api.ResponseApi;
import com.onmicrosoft.ott.processor.core.spring.exception.ClientSideException;
import com.onmicrosoft.ott.processor.core.spring.exception.ServerSideException;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdviceHandler {

    @Autowired private ObjectMapper objectMapper;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<FieldError> errors = new ArrayList<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.add(new FieldError(error.getField(), error.getDefaultMessage())));

        log.error("MethodArgumentNotValidException: [{}]", errors);
        return new ResponseEntity<>(
                ResponseApi.error(VALIDATION_FAILED.getCode(), VALIDATION_FAILED.getMessage(), errors),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidationExceptions(ConstraintViolationException ex) {
        log.error("ConstraintViolationException: VALIDATION_FAILED [{}]", ex.getMessage());
        return new ResponseEntity<>(
                ResponseApi.error(VALIDATION_FAILED.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        log.info("Request invalid: {} {}", ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(
                ResponseApi.error(ex.getCode(), ex.getMessage()), ex.getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        log.error("Request invalid: {}", ex.getMessage());
        return new ResponseEntity<>(
                ResponseApi.error(VALIDATION_FAILED.getCode(), VALIDATION_FAILED.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientSideException.class)
    public ResponseEntity<Object> handleClientSideException(ClientSideException ex) {
        log.error("Request invalid: {} {}", ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(
                ResponseApi.error(ex.getCode(), ex.getMessage(), ex.getPayload()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerSideException.class)
    public ResponseEntity<Object> handleServerSideException(ServerSideException ex) {
        log.error("Service error: ", ex);
        return new ResponseEntity<>(
                ResponseApi.error(ex.getCode(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleServerSideException(Exception ex) {
        log.error("Server unknown error", ex);
        return new ResponseEntity<>(
                ResponseApi.error("SERVER_ERROR", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // TODO: Implement this
    //    @ExceptionHandler(ApiException.class)
    //    @SneakyThrows
    //    public ResponseEntity<Object> handleApiException(ApiException ex) {
    //        log.error("Out-coming request has error: ", ex);
    //        ResponseApi responseApi = objectMapper.readValue(ex.getMessage(), ResponseApi.class);
    //        return ResponseEntity.badRequest().body(responseApi);
    //    }

    // TODO: Implement this
    //    @ExceptionHandler(IdempotenceException.class)
    //    @SneakyThrows
    //    public ResponseEntity<ResponseApi> handleIdempotenceException(IdempotenceException ex) {
    //        log.error("Out-coming request has error: ", ex);
    //        return ResponseEntity.badRequest()
    //                .body(ResponseApi.error("IDEMPOTENCE_ERROR", "IDEMPOTENCE_ERROR"));
    //    }
}
