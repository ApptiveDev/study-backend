package com.backend.apptive.DTO;

import com.backend.apptive.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardResponseDTO {
    private String title;
    private String content;
    private String userEmail;

    public static BoardResponseDTO toDTO(Board board){
        BoardResponseDTO dto = new BoardResponseDTO();
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setUserEmail(board.getUser().getEmail());

        return dto;
    }
}
