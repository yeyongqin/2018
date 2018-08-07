package com.web.service;

import com.web.dao.UserDao;
import com.web.dao.UserDaoImpl;
import com.web.vo.User;

/**
 * 登录操作业务逻辑
 * @author yyq
 */
public class LoginServiceImpl implements LoginService {
    UserDao userDao = new UserDaoImpl();

    /**
     * 通过用户名调用dao层方法查询是否有该用户，若有返回该用户所有信息。
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.getUserMessage(user.getUsername());
    }

}
