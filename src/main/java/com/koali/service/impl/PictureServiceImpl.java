package com.koali.service.impl;

import com.koali.dao.PictureDao;
import com.koali.entity.Picture;
import com.koali.service.PictureService;
import com.koali.utils.FtpUtil;
import com.koali.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Elric on 2017/3/24.
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureDao pictureDao;
    @Value("${FTP_HOSTNAME}")
    private String FTP_HOSTNAME;
    @Value("${FTP_PORT}")
    private int FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_REMOTE_PATH}")
    private String FTP_REMOTE_PATH;
    @Value("${PIC_BASE_URL}")
    private String PIC_BASE_URL;

    public List<Picture> getAllPicture() {
        return pictureDao.getAllPictures();
    }
    public int InsertPicture(String picName, String content) {
        return pictureDao.InsertPicture(picName,content);
    }

    public boolean uploadFile(MultipartFile multipartFile) {
        try {
            String originalFileName = multipartFile.getOriginalFilename();
            String newName = IDUtils.genImageName();
            String picName = newName + originalFileName.substring(originalFileName.lastIndexOf("."));
            boolean result = FtpUtil.uploadPicture(FTP_HOSTNAME, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_REMOTE_PATH, picName, multipartFile.getInputStream());
            return result;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    }
}
