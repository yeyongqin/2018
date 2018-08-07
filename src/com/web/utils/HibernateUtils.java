package com.web.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate工具类，调用该类获取hibernate的session和事务
 * @author yyq
 */
public class HibernateUtils {
    private static Configuration configuration;
    private static SessionFactory sessionFactory;

    static{
        configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session openSession(){
        return sessionFactory.openSession();
    }

    public static Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public static void main(String[] args) {
        openSession();
    }
}