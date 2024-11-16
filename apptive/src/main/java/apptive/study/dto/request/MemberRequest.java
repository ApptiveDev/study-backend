package apptive.study.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberRequest (
        @NotBlank(message = "회원의 이름은 필수입니다.")
        String name,

        Integer age
)
{}
