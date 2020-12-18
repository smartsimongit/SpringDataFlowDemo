package com.smartsimon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.List;

@SpringBootApplication
@EnableBinding(Sink.class)
public class SinkServiceApplication {
    Logger logger = LoggerFactory.getLogger(SinkServiceApplication.class);

    @StreamListener(Sink.INPUT)
    public void orderDispatcher(List<PostPackage> postPackages) {
        postPackages.forEach(postPackage -> {
            logger.info("it sending to  : " + postPackage.getDestination());
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(SinkServiceApplication.class, args);
    }

}
