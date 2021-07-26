package com.rmboni.awssdktutorial.controller;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.rmboni.awssdktutorial.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class SqsController {
    @Autowired
    private SqsService sqsService;

    @PostMapping("/send")
    public ResponseEntity<SendMessageResult> send(@Validated @RequestBody MessageRequest messageRequest) {
        return ResponseEntity.ok().body(sqsService.sendMessage(messageRequest.getMessage()));
    }

    @GetMapping("/receive")
    public ResponseEntity<List<Message>> receiveMessages() {
        return ResponseEntity.ok().body(sqsService.receiveMessages());
    }

    @DeleteMapping("/queue")
    public ResponseEntity.BodyBuilder deleteQueue() {
        sqsService.deleteQueue();
        return ResponseEntity.ok();
    }
}
