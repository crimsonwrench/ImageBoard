package com.mick.Repository;

import com.mick.Model.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PictureRepository extends CrudRepository<Picture, Integer> {
    boolean existsByHash(int hash);

    Picture findByHash(int hash);
}
