package com.backend.apptive.DTO;

import com.backend.apptive.domain.Board;
import com.backend.apptive.domain.User;
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
public class BoardDTO {
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String userPhoneNumber;

    public static BoardDTO fromEntity(Board board){
        return BoardDTO.builder()
                .status(board.getStatus())
                .date(board.getDate())
                .userPhoneNumber(board.getUser().getPhoneNumber())
                .build();
    }
    public Board toEntity(){
        User user = new User();
        user.setPhoneNumber((this.userPhoneNumber));

        return Board.builder()
                .status(this.status)
                .date(this.date)
                .user(user)
                .build();
    }
}
