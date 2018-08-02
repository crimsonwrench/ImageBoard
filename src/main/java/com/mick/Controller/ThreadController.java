package com.mick.Controller;

import com.mick.Model.Picture;
import com.mick.Model.Post;
import com.mick.Model.Thread;
import com.mick.Service.BoardService;
import com.mick.Service.PictureService;
import com.mick.Service.PostService;
import com.mick.Service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


@Controller
public class ThreadController {


    private PostService postService;
    private ThreadService threadService;
    private BoardService boardService;
    private PictureService pictureService;

    @Autowired
    public ThreadController(PostService postService, ThreadService threadService, BoardService boardService, PictureService pictureService) {
        this.postService = postService;
        this.threadService = threadService;
        this.boardService = boardService;
        this.pictureService = pictureService;
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
    public String createPost(@PathVariable("ref") String ref,
                               @PathVariable("id") int id,
                               @ModelAttribute("newPost") Post post,
                               @RequestAttribute("file") MultipartFile file) {
        Thread currentThread = threadService.findById(id);

        Picture postPicture = new Picture();

        if(!file.isEmpty())
            post.setPicture(pictureService.savePicture(file, postPicture));

        post.setThread(currentThread);
        postService.createPost(post);
        return "redirect:/{ref}/res/{id}.html";
    }

}
