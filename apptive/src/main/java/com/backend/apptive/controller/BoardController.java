package com.backend.apptive.controller;

import com.backend.apptive.DTO.BoardDTO;
import com.backend.apptive.DTO.BoardResponseDTO;
import com.backend.apptive.domain.Board;
import com.backend.apptive.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSInput;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/register")
    public ResponseEntity<BoardResponseDTO> registerBoard(@RequestBody BoardDTO boardDTO){
        BoardResponseDTO responseDTO = boardService.registerBoard(boardDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public List<BoardDTO> getAllBoards(){
        return boardService.getAllBoards();
    }

    @GetMapping("/{boardId}")
    public BoardDTO getBoardById(@PathVariable Long boardId){
        return boardService.getBoardById(boardId);
    }

    @GetMapping("/user/{email}")
    public List<BoardDTO> getBoardsByUserEmail(@PathVariable String email){
        return boardService.getBoardsByUserEmail(email);
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<BoardResponseDTO> updateUserEmailById(@PathVariable Long boardId,@RequestBody Map<String, Object> requestBody) {
        String newUserEmail = (String) requestBody.get("newUserEmail");
        BoardResponseDTO responseDTO = boardService.updateUserById(boardId,newUserEmail);
        return ResponseEntity.ok(responseDTO);
    }

}
