package com.smartsimon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@EnableBinding(Processor.class)
@EnableConfigurationProperties(CustomProperties.class)
@SpringBootApplication
public class ProcessorServiceApplication {
    static private Boolean isReaded = Boolean.FALSE;
    public int countBugs = 0;
    Logger logger = LoggerFactory.getLogger(ProcessorServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProcessorServiceApplication.class, args);
    }

    @Autowired
    private CustomProperties processorProperties;

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public List<String> editStringList(List<String> stringList) {
    if (!isReaded) {    logger.info("\nString List Before Transformation keep " + stringList.size() + " element(s)");
        logger.info("\nBad Word today is " + processorProperties.getBadWord());}
        List<String> newList = new ArrayList<>();
        for (String str : stringList) {
            if (str.equals(processorProperties.getBadWord())) {
                if (!isReaded) { logger.info("\nwe find bad word # " + (++countBugs));}
            }
            //todo можно ужадить
            if (!str.equals(processorProperties.getBadWord()))  {
                newList.add(str);
            }
        }
        isReaded = Boolean.TRUE;
        return newList;
    }
}