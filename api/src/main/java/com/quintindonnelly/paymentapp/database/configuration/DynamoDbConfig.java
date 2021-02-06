package com.quintindonnelly.paymentapp.database.configuration;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfig {
  @Value("${amazon.dynamodb.endpoint:http://localhost:8000}")
  private String endpoint;

  @Value("${amazon.dynamodb.region:us-east-1}")
  private String region;

  @Bean
  public AmazonDynamoDBAsync amazonDynamoDB() {
    return AmazonDynamoDBAsyncClientBuilder
        .standard()
        .withEndpointConfiguration(
            new AwsClientBuilder.EndpointConfiguration(endpoint, region))
        .build();
  }
}
