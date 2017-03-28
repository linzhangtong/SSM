package com.koali.service.impl;

import com.koali.dao.UserDao;
import com.koali.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Elric on 2017/3/24.
 */
@Service
public class UserServiceImpl implements com.koali.service.UserService {
    @Autowired
    private UserDao userDao;
    public User CheckUser(String username, String pwd) {
        return userDao.getUserByName(username,pwd);
    }
}
