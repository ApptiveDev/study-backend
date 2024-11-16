package apptive.study.exception;

import apptive.study.dto.ApiResponse;
import apptive.study.exception.errorResponse.ErrorResult;
import apptive.study.exception.errorResponse.ErrorValidationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorValidationResult errorValidationResult = new ErrorValidationResult();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorValidationResult.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.validationErrorResponse(errorValidationResult));
    }


    @ExceptionHandler(StudyException.class)
    public ResponseEntity<ApiResponse<?>> team24ExceptionHandler(StudyException e) {
        ErrorResult errorResult = new ErrorResult(e.getStatusCode(), e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.errorResponse(errorResult));
    }
}