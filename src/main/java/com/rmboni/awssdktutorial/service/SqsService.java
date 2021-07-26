package com.rmboni.awssdktutorial.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqsService {
    @Value("${sqs.queue.name}")
    private String queueName;

    private String queueUrl;

    private final AmazonSQS sqs;

    private final Logger logger = LoggerFactory.getLogger(SqsService.class);

    public SqsService() {
        sqs = AmazonSQSClientBuilder.defaultClient();
    }

    public void createQueue() {
        CreateQueueRequest createRequest = new CreateQueueRequest(queueName)
                .addAttributesEntry("DelaySeconds", "10");
        try {
            sqs.createQueue(createRequest);
            this.queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
            logger.info("Queue {} created with url {}", queueName, queueUrl);
        } catch(AmazonSQSException e) {
            if(!e.getErrorCode().equals("QueueAlreadyExists")) {
                logger.error("Error on creating queue", e);
                throw e;
            }
        }
    }

    public void deleteQueue() {
        if(this.queueUrl == null) {
            this.queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
        }

        logger.info("Deleting queue {}", queueUrl);
        sqs.deleteQueue(queueUrl);
    }

    public SendMessageResult sendMessage(String message) {
        this.createQueue();

        logger.info("Sending message: {}", message);
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message)
                .withDelaySeconds(5);
        return sqs.sendMessage(request);
    }

    public List<Message> receiveMessages() {
        logger.info("Receiving messages");
        ReceiveMessageRequest request = new ReceiveMessageRequest()
                .withQueueUrl(queueUrl)
                .withWaitTimeSeconds(10);
        List<Message> messages = sqs.receiveMessage(request).getMessages();
        logger.info("Received {} messages", messages.size());

        for(Message m: messages) {
            logger.info("Deleting message {}", m.getReceiptHandle());
            sqs.deleteMessage(queueUrl, m.getReceiptHandle());
        }

        return messages;
    }
}
