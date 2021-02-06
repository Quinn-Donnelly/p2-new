package com.quintindonnelly.paymentapp;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PaymentAppApplication {

	private final AmazonDynamoDBAsync db;

	public PaymentAppApplication(AmazonDynamoDBAsync db) {
		this.db = db;
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return (args) -> {
			CreateTableRequest request = new CreateTableRequest()
					.withAttributeDefinitions(new AttributeDefinition(
							"Name", ScalarAttributeType.S
					)).withKeySchema(
							new KeySchemaElement("Name", KeyType.HASH)
					).withProvisionedThroughput(new ProvisionedThroughput(
							1L, 1L
					)).withTableName("customers");

			try {
				db.createTable(request);
			} catch (AmazonServiceException e) {
				System.err.println(e.getErrorMessage());
				System.exit(1);
			}
		};
	}
}
