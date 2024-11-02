package apptive.study.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> invalidRequestHandler(MethodArgumentNotValidException e) {
//        Map<String, Object> errorResult = new HashMap<>();
//
//        errorResult.put("status","fail");
//        errorResult.put("code","400");
//        errorResult.put("validation", new HashMap<String, String>());
//
//        HashMap validation = (HashMap)errorResult.get("validation");
//
//        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
//            validation.put(fieldError.getField(), fieldError.getDefaultMessage());
//        }
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(errorResult);
//    }
//
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<?> invalidRequestHandler(IllegalStateException e) {
//        Map<String, String> errorResult = new HashMap<>();
//
//        errorResult.put("status","error");
//        errorResult.put("code","400");
//        errorResult.put("message","이미 등록된 사용자 이름입니다.");
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(errorResult);
//    }
}
