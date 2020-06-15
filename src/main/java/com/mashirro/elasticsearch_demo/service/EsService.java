package com.mashirro.elasticsearch_demo.service;

import java.util.Map;

public interface EsService {

    /**
     * 插入es数据
     *
     * @param index 索引库
     * @param type  文档类型
     * @param obj   插入对象
     * @param id    主键
     * @throws Exception
     */
    public void insertEs(String index, String type, Object obj, String id) throws Exception;


    /**
     * 根据主键更新es数据
     *
     * @param index
     * @param type
     * @param obj
     * @param id
     */
    public void updateById(String index, String type, Object obj, String id) throws Exception;


    /**
     * 根据条件查询
     *
     * @param index
     * @param type
     * @param paramsMap 封装的条件Map
     * @return
     */
    public Map<String, Object> getList(String index, String type, Map<String, Object> paramsMap) throws Exception;

}
