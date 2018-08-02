package com.mick.Service;

import com.mick.Model.Picture;
import com.mick.Repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public void savePicture(MultipartFile file, Picture picture){
        try (FileOutputStream fos = new FileOutputStream("."+picture.getFullPath())) {
            fos.write(file.getBytes());
            pictureRepository.save(picture);
        } catch (Exception ex) {
            ex.printStackTrace();
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
