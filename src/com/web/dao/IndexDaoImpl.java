package com.web.dao;

import com.alibaba.fastjson.JSONArray;
import com.web.utils.HibernateUtils;
import com.web.vo.Index;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * 对数据库index的index_summary表数据进行增删改查
 * @author yyq
 */
public class IndexDaoImpl implements IndexDao {
    //获取Hibernate连接
    Session session = HibernateUtils.getCurrentSession();
    Transaction tx = session.beginTransaction();

    /**
     * 获取所有指标数据
     * @return
     */
    @Override
    public List<Index> getAllIndexMessage(int count) {
        //查询index_summary表所有信息，根据id逆序输出
        SQLQuery query = session.createSQLQuery("select * from index_summary order by id desc");
        query.addEntity(Index.class);
        query.setFirstResult(count);
        query.setMaxResults(10);
        List<Index> list = query.list();
        tx.commit();
        return list;
    }

    /**
     * 根据条件查询数据
     * @param likeValue
     * @return
     */
    @Override
    public List<Index> findIndexMessage(String likeValue) {
        return null;
    }

    /**
     * 根据ID删除数据
     * @param id
     */
    @Override
    public void deleteIndexMessage(net.sf.json.JSONArray id) {
        String hql = "";
        //遍历ID数组，根据ID进行删除数据
        for (int i = 0; i < id.size(); i++) {
            if(i == 0){
                hql = "id=" + id.get(i);
            } else {
                hql = hql + "or id="+ id.get(i);
            }
        }
        Query query = session.createQuery(String.format("delete from Index where %s", hql));
        query.executeUpdate();
        tx.commit();
    }

    /**
     *更新数据库中的数据
     * @param index
     */
    @Override
    public void updateIndexMessage(Index index) {
        Index indexMsg = (Index) session.get(Index.class,index.getId());
        indexMsg.setIndex(index);
        tx.commit();
    }

    /**
     * 添加新指标到数据库
     * @param index
     */
    @Override
    public void addIndexMessage(Index index) {
        session.save(index);
        tx.commit();
    }

}
