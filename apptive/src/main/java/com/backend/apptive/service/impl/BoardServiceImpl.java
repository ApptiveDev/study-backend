package com.backend.apptive.service.impl;

import com.backend.apptive.DTO.BoardDTO;
import com.backend.apptive.DTO.BoardResponseDTO;
import com.backend.apptive.domain.Board;
import com.backend.apptive.domain.User;
import com.backend.apptive.exception.BoardNotFoundException;
import com.backend.apptive.exception.UserNotFoundException;
import com.backend.apptive.repository.BoardRepository;
import com.backend.apptive.repository.UserRepository;
import com.backend.apptive.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public BoardResponseDTO registerBoard(BoardDTO boardDTO) {
        User user = userRepository.findByEmail(boardDTO.getUserEmail())
                .orElseThrow(()-> new UserNotFoundException("No user: "+boardDTO.getUserEmail()));
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(board.getContent());
        board.setUser(user);

        Board savedBoard = boardRepository.save(board);

        BoardResponseDTO responseDTO = new BoardResponseDTO();
        responseDTO.setId(savedBoard.getId());
        responseDTO.setTitle(savedBoard.getTitle());
        responseDTO.setContent(savedBoard.getContent());
        responseDTO.setUserEamil(user.getEmail());
        return responseDTO;
    }

    @Override
    public List<BoardDTO> getAllBoards(){
        return boardRepository.findAll().stream()
                .map(board -> BoardDTO.builder()
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public  BoardDTO getBoardById(Long postId){
        return boardRepository.findById(postId)
                .map(board -> BoardDTO.builder()
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build())
                .orElseThrow(()-> new BoardNotFoundException("No Board with: "+postId));

    }

    public List<BoardDTO> getBoardsByUserEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("No Board with: "+email));
        return user.getBoardList().stream()
                .map(board -> BoardDTO.builder()
                        .title(board.getTitle())
                        .build())
                .collect(Collectors.toList());
    }
}
