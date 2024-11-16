package apptive.study.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
    MEMBER_NAME_DUPLICATE_ERROR(HttpStatus.BAD_REQUEST, "회원의 이름은 중복될 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
