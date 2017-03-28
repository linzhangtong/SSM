package com.koali.dao;

import com.koali.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Elric on 2017/3/24.
 */

public interface UserDao {
    /**如果查询到该用户就会返回1
     * @param username,pwd
     * @return数据库被修改的行数
     */
    User getUserByName(@Param("username") String username, @Param("pwd") String pwd);
}
