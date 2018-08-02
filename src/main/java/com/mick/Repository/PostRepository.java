package com.mick.Repository;

import com.mick.Model.Post;
import com.mick.Model.Thread;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    List<Post> findAllByThreadId(int id);

    List<Post> findTop3ByThreadOrderByPostDateDesc(Thread thread);
}
