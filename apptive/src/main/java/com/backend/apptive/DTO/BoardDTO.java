package com.backend.apptive.DTO;

import com.backend.apptive.domain.Board;
import com.backend.apptive.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private String userEmail;

    public static BoardDTO fromEntity(Board board){
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .userEmail(board.getUser().getEmail())
                .build();
    }
    public Board toEntity(){
        User user = new User();
        user.setEmail((this.userEmail));

        return Board.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .user(user)
                .build();
    }
}
