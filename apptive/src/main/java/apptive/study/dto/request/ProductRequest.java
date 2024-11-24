package apptive.study.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ProductRequest (
        @NotBlank(message = "상품의 이름은 필수입니다.")
        String name,

        int price
)
{}
