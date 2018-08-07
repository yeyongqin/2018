package com.web.service;

import com.web.vo.Index;
import net.sf.json.JSONArray;

import java.util.List;

/**
 * 指标业务逻辑接口
 * @author yyq
 */
public interface IndexService {
    //获取所有指标信息的业务逻辑
    List<Index> getAllIndexMessage(int page);
    List<Index> findIndexMessage();
    //删除指标信息的业务逻辑
    void deleteIndexMessage(JSONArray id);
    //更新指标指标信息的业务逻辑
    void updateIndexMessage(Index index);
    //添加指标信息的业务逻辑
    void addIndexMessage(Index index);
}
