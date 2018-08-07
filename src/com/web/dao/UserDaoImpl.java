package com.web.dao;

import com.web.utils.HibernateUtils;
import com.web.vo.HostHolder;
import com.web.vo.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 对User表进行数据库操作
 * @author yyq
 */
public class UserDaoImpl implements UserDao {
    //获取HIbernate连接
    Session session = HibernateUtils.getCurrentSession();
    Transaction tx = session.beginTransaction();


    /**
     * 通过用户名查询用户数据
     * @param username
     * @return
     */
    @Override
    public User getUserMessage(String username) {
        User user = (User) session
                .createQuery("from User where username = ?")
                .setString(0, username).uniqueResult();
        return user;
    }
}
