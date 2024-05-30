package com.backend.apptive.repository;

import com.backend.apptive.DTO.BoardDTO;
import com.backend.apptive.domain.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    @EntityGraph(attributePaths = "user")
    List<Board> findAll();
}
