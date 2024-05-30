package com.backend.apptive.DTO;

import com.backend.apptive.domain.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardResponseDTO {
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String userPhoneNumber;

    public static BoardResponseDTO toDTO(Board board){
        BoardResponseDTO dto = new BoardResponseDTO();
        dto.setStatus(board.getStatus());
        dto.setDate(board.getDate());
        dto.setUserPhoneNumber(board.getUser().getPhoneNumber());
        return dto;
    }
}
