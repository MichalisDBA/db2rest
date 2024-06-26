package com.homihq.db2rest.mongo.repository;

import com.homihq.db2rest.core.dto.CreateResponse;
import com.homihq.db2rest.core.dto.DeleteResponse;
import com.homihq.db2rest.mongo.dialect.MongoDialect;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class MongoRepository {

    private final MongoTemplate mongoTemplate;
    private final MongoDialect dialect;


    public CreateResponse save(String collectionName, List<String> includedFields,
                               Map<String, Object> data) {

        var document = new Document();
        data.forEach(document::append);
        var savedDocument = mongoTemplate.save(document, collectionName);
        return new CreateResponse(1, savedDocument.getObjectId("_id"));

    }

    public DeleteResponse delete(Query query, String collectionName) {
        DeleteResult deleteResult = mongoTemplate.remove(query, collectionName);
        var rows = deleteResult.getDeletedCount();
        log.debug("Number of documents deleted - {}", rows);
        return DeleteResponse.builder().rows((int)rows).build();
    }

    public Object find(Query query, String collectionName) {
        return mongoTemplate.find(query, Object.class, collectionName);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> findOne(Query query, String collectionName) {
        return mongoTemplate.findOne(query, LinkedHashMap.class, collectionName);
    }
}
