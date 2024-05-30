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

    //GET
    @GetMapping
    public List<BoardDTO> getAllBoards(){
        return boardService.getAllBoards();
    }
    @GetMapping("/{boardId}")
    public BoardDTO getBoardById(@PathVariable("boardId") Long boardId){
        return boardService.getBoardById(boardId);
    }

    @GetMapping("/{name}/{phoneNumber}")
    public List<BoardDTO> getBoardsByUserPhoneNumber(@PathVariable("name") String name, @PathVariable("phoneNumber") String phoneNumber){
        return boardService.getBoardsByNameAndPhoneNumber(name, phoneNumber);
    }

    //Patch
    @PatchMapping("/{boardId}")
    public ResponseEntity<BoardResponseDTO> updateUserPhoneNumberById(@PathVariable Long boardId,@RequestBody Map<String, Object> requestBody) {
        String newUserPhoneNumber = (String) requestBody.get("newUserPhoneNumber");
        BoardResponseDTO responseDTO = boardService.updateUserById(boardId,newUserPhoneNumber);
        return ResponseEntity.ok(responseDTO);
    }
}
