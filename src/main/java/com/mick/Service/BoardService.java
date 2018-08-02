package com.mick.Service;

import com.mick.Model.Board;
import com.mick.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Iterable<Board> findAll(){ return boardRepository.findAll(); }

    public void createBoard(Board board) {
        boardRepository.save(board);
    }

    public Board findByRef(String ref) { return boardRepository.findByBoardRef(ref); }

    public boolean existsByRef(String ref) { return boardRepository.existsBoardByBoardRef(ref); }
}
