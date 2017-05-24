package com.koali.utils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;

public class FtpUtil {
    public static boolean uploadPicture(String FTP_HOSTNAME,int FTP_PORT,String FTP_USERNAME,String FTP_PASSWORD,String FTP_REMOTE_PATH,String picName,InputStream fileInputStream)throws IOException {
        //创建一个FtpClient
        FTPClient ftpClient = new FTPClient();
        //连接的ip和端口号
        ftpClient.connect(FTP_HOSTNAME,FTP_PORT);
        //登陆的用户名和密码
        ftpClient.login(FTP_USERNAME,FTP_PASSWORD);
        //将文件转换成输入流
        //修改文件的存放地址
        ftpClient.changeWorkingDirectory(FTP_REMOTE_PATH);
        //设置文件的上传格式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //将我们的文件流以什么名字存放
        boolean result = ftpClient.storeFile(picName,fileInputStream);
        //退出
        ftpClient.logout();
        return result;
    }
}