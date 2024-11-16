package apptive.study.exception.member;

import apptive.study.exception.ErrorCode;
import apptive.study.exception.StudyException;

public class MemberNameDuplicateException extends StudyException {
    public MemberNameDuplicateException() {
        super(ErrorCode.MEMBER_NAME_DUPLICATE_ERROR);
    }
}
