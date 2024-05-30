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
        User user = userRepository.findByPhoneNumber(boardDTO.getUserPhoneNumber())
                .orElseThrow(()-> new UserNotFoundException("No user: "+boardDTO.getUserPhoneNumber()));

        Board board = new Board();
        board.setStatus(boardDTO.getStatus());
        board.setDate(boardDTO.getDate());
        board.setUser(user);

        Board savedBoard = boardRepository.save(board);

        BoardResponseDTO responseDTO = new BoardResponseDTO();
        responseDTO.setStatus(savedBoard.getStatus());
        responseDTO.setDate(savedBoard.getDate());
        responseDTO.setUserPhoneNumber(user.getPhoneNumber());
        return responseDTO;
    }

    @Override
    public List<BoardDTO> getAllBoards(){
        return boardRepository.findAll().stream()
                .map(board -> BoardDTO.builder()
                        .status(board.getStatus())
                        .date(board.getDate())
                        .userPhoneNumber(board.getUser().getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public  BoardDTO getBoardById(Long postId){
        return boardRepository.findById(postId)
                .map(board -> BoardDTO.builder()
                        .status(board.getStatus())
                        .date(board.getDate())
                        .userPhoneNumber(board.getUser().getPhoneNumber())
                        .build())
                .orElseThrow(()-> new BoardNotFoundException("No Board with: "+postId));
    }

    public List<BoardDTO> getBoardsByNameAndPhoneNumber(String name, String phoneNumber){
        User user = userRepository.findByNameAndPhoneNumber(name, phoneNumber)
                .orElseThrow(()-> new UserNotFoundException("No Board with: "+name+" "+phoneNumber));
        return user.getBoardList().stream()
                .map(board -> BoardDTO.builder()
                        .status(board.getStatus())
                        .date(board.getDate())
                        .userPhoneNumber(board.getUser().getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public BoardResponseDTO updateUserById(Long boardId, String newUserphoneNumber){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new BoardNotFoundException("No Board with id: "+boardId));
        User user = userRepository.findByPhoneNumber(newUserphoneNumber)
                .orElseThrow(()->new UserNotFoundException("No User with phoneNumber: "+newUserphoneNumber));
        board.setUser(user);
        Board updatedBoard = boardRepository.save(board);
        return BoardResponseDTO.toDTO(updatedBoard);
    }
}
