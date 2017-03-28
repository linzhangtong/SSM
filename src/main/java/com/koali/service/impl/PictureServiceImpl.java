package com.koali.service.impl;

import com.koali.dao.PictureDao;
import com.koali.entity.Picture;
import com.koali.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Elric on 2017/3/24.
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureDao pictureDao;

    public List<Picture> getAllPicture() {
        return pictureDao.getAllPictures();
    }
    public int InsertPicture(String picName, String content) {
        return pictureDao.InsertPicture(picName,content);
    }
}
