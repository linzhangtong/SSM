package com.koali.dao;

import com.koali.entity.Picture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Elric on 2017/3/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class PictureDaoTest {
    @Resource
    private PictureDao pictureDao;
    @Test
    public void getAllPictures() throws Exception {
        List<Picture> pictures =  pictureDao.getAllPictures();
        for (Picture pic:pictures){
            System.out.println(pic);
        }
    }

    @Test
    public void insertPicture() throws Exception {

    }

}