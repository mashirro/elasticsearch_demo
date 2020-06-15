package com.mashirro.elasticsearch_demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashirro.elasticsearch_demo.service.EsService;
import com.mashirro.elasticsearch_demo.util.EsClient;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class EsServiceImpl implements EsService {


    @Override
    public void insertEs(String index, String type, Object obj, String id) throws Exception {
        TransportClient client = null;
        try {
            client = EsClient.getInstance().init();
            //使用Jackson将bean序列化为JSON
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] bytes = objectMapper.writeValueAsBytes(obj);
            //prepareIndex:索引与给定索引和类型关联的文档。id是可选的，如果没有提供，则会自动生成一个id。
            //setSource:将文档设置为字节形式的索引。
            IndexResponse response = client.prepareIndex(index, type, id).setSource(bytes, XContentType.JSON).get();
        } catch (Exception e) {
            throw e;
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    @Override
    public void updateById(String index, String type, Object obj, String id) throws Exception {
        TransportClient client = null;
        try {
            client = EsClient.getInstance().init();
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] bytes = objectMapper.writeValueAsBytes(obj);
            UpdateResponse response = client.prepareUpdate(index, type, id).setDoc(bytes, XContentType.JSON).get();
        } catch (Exception e) {
            throw e;
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    @Override
    public Map<String, Object> getList(String index, String type, Map<String, Object> paramsMap) throws Exception {
        TransportClient client = EsClient.getInstance().init();
        //使用查询在一个或多个索引和一个或多个类型之间搜索。
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type);
        //MatchAllQueryBuilder matchAllQueryBuilder = new MatchAllQueryBuilder();
        //QueryBuilders.matchAllQuery()
        return null;
    }
}

