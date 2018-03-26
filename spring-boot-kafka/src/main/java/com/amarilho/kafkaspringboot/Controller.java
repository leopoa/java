package com.amarilho.kafkaspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    Sender kafkaSender;

    @RequestMapping(value = "/producer")
    public ResponseEntity<String> producer(@RequestParam("message") String message) {
        return ResponseEntity.ok(kafkaSender.send(message));
    }

    @RequestMapping(value = "/producer/{topic}")
    public ResponseEntity<String> producer(@PathVariable("topic") String topic,
                                           @RequestParam("message") String message) {
        return ResponseEntity.ok(kafkaSender.send(topic, message));
    }

}

