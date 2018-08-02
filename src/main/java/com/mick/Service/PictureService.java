package com.mick.Service;

import com.mick.Model.Picture;
import com.mick.Repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Picture savePicture(MultipartFile data, Picture picture){

        picture.setSource("/img/");
        picture.setName(data.getOriginalFilename());
        picture.setSize(data.getSize());

        try {
            BufferedImage filePicture = ImageIO.read(data.getInputStream());
            picture.setHeight(filePicture.getHeight());
            picture.setWidth(filePicture.getWidth());
            picture.setHash(picture.hashCode());

            if (existsByHash(picture.getHash())) {
                return findByHashCode(picture.getHash());
            }
            else {
                try (FileOutputStream fos = new FileOutputStream("."+picture.getFullPath())) {
                    fos.write(data.getBytes());
                    pictureRepository.save(picture);
                    return picture;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return picture;
        }
    }

    public void removePicture(Picture picture) {
       try {
           File pictureFile = new File(picture.getFullPath());
           if(pictureFile.delete()){
               pictureRepository.delete(picture);
           } else {
               System.out.println("Failed to delete picture: " + picture.getFullPath());
           }
       } catch(Exception ex) {
           ex.printStackTrace();
       }
    }

    public Picture findByHashCode(int hash) { return pictureRepository.findByHash(hash); }

    public boolean existsByHash(int hash) {
        return pictureRepository.existsByHash(hash); }

}
