package apptive.study.exception;

public class StudyException extends RuntimeException{
    private final ErrorCode errorCode;

    public StudyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public int getStatusCode(){
        return errorCode.getStatus().value();
    }
}