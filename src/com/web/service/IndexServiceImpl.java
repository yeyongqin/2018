package com.web.service;

import com.web.dao.IndexDao;
import com.web.dao.IndexDaoImpl;
import com.web.vo.Index;

import java.util.List;

/**
 * 指标各操作业务逻辑
 * @author yyq
 */
public class IndexServiceImpl implements IndexService {
    //调用dao层的接口方法
    IndexDao indexDao = new IndexDaoImpl();

    /**
     * 根据页码显示数据，以每页10条为基准
     * @param page
     * @return
     */
    @Override
    public List<Index> getAllIndexMessage(int page) {
        int count;
        count = (page-1)*10;
        return indexDao.getAllIndexMessage(count);
    }

    @Override
    public List<Index> findIndexMessage() {
        return null;
    }

    /**
     * 根据用户ID调用dao层方法删除数据
     * @param id
     */
    @Override
    public void deleteIndexMessage(net.sf.json.JSONArray id) {
        indexDao.deleteIndexMessage(id);
    }

    /**
     * 根据更新过的对象调用dao层方法更新数据库
     * @param index
     */
    @Override
    public void updateIndexMessage(Index index) {

        indexDao.updateIndexMessage(index);
    }

    /**
     * 根据新的对象调用dao层方法添加数据库信息
     * @param index
     */
    @Override
    public void addIndexMessage(Index index) {
        indexDao.addIndexMessage(index);
    }
}
