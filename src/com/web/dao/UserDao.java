package com.web.dao;

import com.web.vo.User;

import java.util.List;

/**
 * 用户Dao层接口
 * @author yyq
 */
public interface UserDao {
    //通过用户名获取用户所有信息
    User getUserMessage(String username);
}
