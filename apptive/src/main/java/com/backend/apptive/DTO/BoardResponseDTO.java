package com.backend.apptive.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String userEamil;
}
