package com.vntechies.awscloudwatchagentforawsxray.services.impls;

import com.vntechies.awscloudwatchagentforawsxray.constants.CredentialsConstant;
import com.vntechies.awscloudwatchagentforawsxray.entities.SongEntity;
import com.vntechies.awscloudwatchagentforawsxray.services.ISongService;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

@Service
public class SongService implements ISongService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongService.class);


    @Autowired
    private DynamoDbClient dynamoDbClient;

    @Override
    public SongEntity add(SongEntity songEntity) {
        LOGGER.info("[add] -> Executed");
        HashMap<String, AttributeValue> items = new HashMap<>();
        items.put("id", AttributeValue.builder().s(songEntity.getId()).build());
        items.put("name", AttributeValue.builder().s(songEntity.getName()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(CredentialsConstant.TABLE_NAME)
                .item(items)
                .build();
        try {
            LOGGER.info("START -> Put a new item");
            dynamoDbClient.putItem(request);
            LOGGER.info("END -> Put a new item successfully");
        } catch (Exception e) {
            LOGGER.error("END -> Put a new item failed");
            LOGGER.error(e.getMessage());
        }
        return songEntity;
    }
}
