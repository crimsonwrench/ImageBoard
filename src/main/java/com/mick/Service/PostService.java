package com.mick.Service;

import com.mick.Model.Post;
import com.mick.Model.Thread;
import com.mick.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> loadPosts(int id) { return postRepository.findAllByThreadId(id); }

    public void createPost(Post post) { postRepository.save(post); }

    public List<Post> getLastPosts(Thread thread) {
        List<Post> returnList = postRepository.findTop3ByThreadOrderByPostDateDesc(thread);
        Collections.reverse(returnList);
        return returnList;
    }
}
