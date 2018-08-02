package com.mick.Controller;

import com.mick.Model.Post;
import com.mick.Model.Thread;
import com.mick.Service.BoardService;
import com.mick.Service.PostService;
import com.mick.Service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ThreadController {


    private PostService postService;
    private ThreadService threadService;
    private BoardService boardService;

    @Autowired
    public ThreadController(PostService postService, ThreadService threadService, BoardService boardService) {
        this.postService = postService;
        this.threadService = threadService;
        this.boardService = boardService;
    }

    @GetMapping("/{ref}/res/{id}.html")
    public String postList(@PathVariable("ref") String ref,
                           @PathVariable("id") int id, Model model) {
        model.addAttribute("currentBoard", boardService.findByRef(ref));
        model.addAttribute("currentThread", threadService.findById(id));
        model.addAttribute("postList", postService.loadPosts(id));
        model.addAttribute("newPost", new Post());
        return "thread";
    }

    @PostMapping("/{ref}/res/{id}.html")
    public String createThread(@PathVariable("ref") String ref,
                               @PathVariable("id") int id,
                               @ModelAttribute Post post) {
        Thread currentThread = threadService.findById(id);
        post.setThread(currentThread);
        postService.createPost(post);
        return "redirect:/{ref}/res/{id}.html";
    }

}
