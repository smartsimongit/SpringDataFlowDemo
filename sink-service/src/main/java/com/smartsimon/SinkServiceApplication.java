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
    static private Boolean isReaded = Boolean.FALSE;

    @StreamListener(Sink.INPUT)
    public void orderDispatcher(List<String> stringList) {
        if (!isReaded) {
            StringBuilder text = new StringBuilder();
            for (String str : stringList) {
                text.append(str);
                text.append(" ");
            }
            logger.info("\nAfter transform we have   " + stringList.size() + " words");
            logger.info("\nTEXT IS \n" + text);
            isReaded = Boolean.TRUE;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SinkServiceApplication.class, args);
    }

}
