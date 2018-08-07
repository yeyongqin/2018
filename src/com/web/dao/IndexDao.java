package com.web.dao;

import com.web.vo.Index;

import java.util.List;

/**
 * 指标数据的Dao层接口
 * @author yyq
 */
public interface IndexDao {
    //传入一个值，获得指标所有数据
    List<Index> getAllIndexMessage(int count);
    //传入字符串，进行模糊查询，返回查询到的数据
    List<Index> findIndexMessage(String likeValue);
    //传入ID数组，删除相关数据
    void deleteIndexMessage(net.sf.json.JSONArray id);
    //传入指标对象，更新数据
    void updateIndexMessage(Index index);
    //传入新指标，添加数据到数据库
    void addIndexMessage(Index index);

}
