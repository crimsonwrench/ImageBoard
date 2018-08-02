package com.mick.Controller;

import com.mick.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private BoardService boardService;

    @Autowired
    public IndexController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("boardList", boardService.findAll());
        return "index";
    }
}