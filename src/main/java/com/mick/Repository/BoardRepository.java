package com.mick.Repository;

import com.mick.Model.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<Board, Integer> {

    Board findByBoardRef(String ref);

    boolean existsBoardByBoardRef(String ref);
}
