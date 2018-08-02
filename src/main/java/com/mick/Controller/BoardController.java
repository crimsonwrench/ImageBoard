package com.mick.Controller;

import com.mick.Model.Board;
import com.mick.Model.Picture;
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
import java.util.List;
import java.util.Set;

@Controller
public class BoardController {

    private BoardService boardService;
    private ThreadService threadService;
    private PostService postService;
    private PictureService pictureService;

    @Autowired
    public BoardController(BoardService boardService, ThreadService threadService, PostService postService, PictureService pictureService) {
        this.boardService = boardService;
        this.threadService = threadService;
        this.postService = postService;
        this.pictureService = pictureService;
    }

    @GetMapping("/{ref}")
    public String threadList(@PathVariable("ref") String ref, Model model) {


        if(boardService.existsByRef(ref)) {
            List<Thread> threadList = threadService.loadThreads(ref);

            for (Thread thread : threadList) {
                thread.setLastPosts(postService.getLastPosts(thread));
            }

            model.addAttribute("currentBoard", boardService.findByRef(ref));
            model.addAttribute("threadList", threadList);
            model.addAttribute("newThread", new Thread());
            model.addAttribute("newPicture", new Picture());
            return "board";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/{ref}")
    public String createThread(@PathVariable("ref") String ref,
                               @ModelAttribute Thread thread,
                               @ModelAttribute("newPicture") Picture newPicture,
                               @RequestAttribute("file") MultipartFile file) {
        Board currentBoard = boardService.findByRef(ref);

        newPicture.setSource("/img/");
        newPicture.setName(file.getOriginalFilename());
        newPicture.setSize(file.getSize());

        try {
            BufferedImage filePicture = ImageIO.read(file.getInputStream());
            newPicture.setHeight(filePicture.getHeight());
            newPicture.setWidth(filePicture.getWidth());
            newPicture.setHash(newPicture.hashCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if(pictureService.existsByHash(newPicture.getHash())) {
            Picture match = pictureService.findByHashCode(newPicture.getHash());
            thread.setPicture(match);
        } else {
            pictureService.savePicture(file, newPicture);
            thread.setPicture(newPicture);
        }

        thread.setBoard(currentBoard);

        threadService.createThread(thread);
        return "redirect:/{ref}";
    }

    @GetMapping("/{ref}/rm/{id}")
    public String removeThread(@PathVariable("ref") String ref,
                               @PathVariable("id") int id) {
        threadService.deleteById(id);
        return "redirect:/{ref}";
    }
}