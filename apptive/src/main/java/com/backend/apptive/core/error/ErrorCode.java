package com.backend.apptive.core.error;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum ErrorCode {

    ALREADY_SAVED_NAME(409, "이미 존재하는 이름입니다.");
    private final int status;
    private final String message;
}


