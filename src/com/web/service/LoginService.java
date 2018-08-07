package com.web.service;

import com.web.vo.User;

/**
 * 登录业务逻辑接口
 * @author yyq
 */
public interface LoginService {
    //用户登录的业务逻辑
    User login(User user);
}
