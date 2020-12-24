package com.smartsimon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@EnableConfigurationProperties(CustomProperties.class)
@SpringBootApplication
@EnableBinding(Source.class)
public class SourceServiceApplication {

    private Boolean isReaded = Boolean.FALSE;
    Logger logger = LoggerFactory.getLogger(SourceServiceApplication.class);

    @Autowired
    private CustomProperties processorProperties;

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
    public MessageSource<List<String>> getStringFromFile() throws IOException {

        if (!isReaded) {
            List<String> stringList = readFile();
            return () -> MessageBuilder.withPayload(stringList).build();
        }
        return null;
    }


    public static void main(String[] args) {
        SpringApplication.run(SourceServiceApplication.class, args);
    }


    public List<String> readFile() throws IOException {

        logger.info("\nWe read file with name " + processorProperties.getTestFileName());
        File resource = new File("/root/scdf/" + processorProperties.getTestFileName());
        Scanner scanner = new Scanner(resource);
        scanner.useDelimiter(" ");
        List<String> stringList = new ArrayList<>();
        StringBuilder text = new StringBuilder();
        while (scanner.hasNext()) {
            String str = scanner.next();
            stringList.add(str);

            text.append(str);
            text.append(" ");

        }
        scanner.close();
        logger.info("\nBefore transform we have   " + stringList.size() + " words");
        logger.info("\nTEXT IS \n" + text);
        isReaded = Boolean.TRUE;

        return stringList;

    }

}
