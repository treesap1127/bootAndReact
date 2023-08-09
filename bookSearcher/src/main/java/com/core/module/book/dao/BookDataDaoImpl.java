package com.core.module.book.dao;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookDataDaoImpl<T> implements BookDataDao<T> {
    private final ElasticsearchOperations elasticsearchTemplate;
    private final RestHighLevelClient client;
    
	@Override
	public void bookIndexing(String indexNm, List<T> documents , Class<T> tClass ) {
        List<IndexQuery> queries = new ArrayList<>();
        for (T document : documents) {
            IndexQuery query = new IndexQueryBuilder()
                    .withObject(document)
                    .build();
            queries.add(query);
        }
        IndexOperations indexOps = elasticsearchTemplate.indexOps(tClass);
        if (!indexOps.exists()) {
            indexOps.create();
            indexOps.putMapping(indexOps.createMapping());
        }
        elasticsearchTemplate.bulkIndex(queries, IndexCoordinates.of(indexNm));
    }

}