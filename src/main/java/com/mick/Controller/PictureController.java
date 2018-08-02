package com.mick.Controller;

import com.mick.Service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class PictureController {

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping(
            value = "/img/{pic}",
            produces = {"image/jpg", "image/png", "image/jpeg"}
    )
    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable("pic") String pic) throws IOException {
        InputStream in = new FileInputStream("./img/"+pic);
        return in.readAllBytes();
    }
}
