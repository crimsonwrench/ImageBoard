package com.mick.Repository;

import com.mick.Model.Thread;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepository extends CrudRepository<Thread, Integer> {

    List<Thread> findAllByBoard_BoardRef (String ref);

    Thread findById(int id);
}
