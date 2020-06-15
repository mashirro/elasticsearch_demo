package com.mashirro.elasticsearch_demo;

import com.mashirro.elasticsearch_demo.util.EsClient;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.UnknownHostException;

@SpringBootTest
class ElasticsearchDemoApplicationTests {

	private TransportClient esClient = null;

	@Test
	void contextLoads() throws UnknownHostException {
		initEsClient();
		testMatchAllQuery();
		testFullTextQuery();
	}


	void initEsClient() throws UnknownHostException {
		esClient = EsClient.getInstance().init();
	}

	/**
	 * Match All Query
	 */
	void testMatchAllQuery(){
		SearchRequestBuilder builder = esClient.prepareSearch("my_xfjxx").setTypes("xfjxx");
		MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
		//matchAllQueryBuilder.boost(1.2f);
		//最简单的查询，它匹配所有文档，给它们一个_score为1.0。可以通过boost参数修改_score:
		builder.setQuery(matchAllQueryBuilder);
		SearchResponse searchResponse = builder.get();
		SearchHit[] hits = searchResponse.getHits().getHits();
		for (SearchHit hit : hits) {
			System.out.println(hit.getScore()+"--"+hit.getSource());
		}
		System.out.println("------------------华丽的分割线--------------------");
	}


	//Full text queries
	void testFullTextQuery(){

	}

}
