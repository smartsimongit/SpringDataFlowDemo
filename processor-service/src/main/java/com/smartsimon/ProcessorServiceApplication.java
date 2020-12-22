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
import java.util.List;

@EnableBinding(Processor.class)
@EnableConfigurationProperties(CustomProperties.class)
@SpringBootApplication
public class ProcessorServiceApplication {


    Logger logger = LoggerFactory.getLogger(ProcessorServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProcessorServiceApplication.class, args);
    }

    @Autowired
    private CustomProperties processorProperties;

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public List<PostPackage> setDestination(List<PostPackage> postPackages) {
        logger.info("test var is " + processorProperties.getTestStringVar());
        List<PostPackage> newPostPackagesList = new ArrayList<>();
        for (PostPackage postPackage : postPackages) {
            if (postPackage.getIndex() == 101000) {
                postPackage.setDestination("Msc");
            }
            if (postPackage.getIndex() == 190000) {
                postPackage.setDestination("SPb");
            }
            if (postPackage.getIndex() == 620000) {
                postPackage.setDestination("Ekb");
            }
            logger.info("Index is {}, and destination is {} ", postPackage.getIndex(), postPackage.getDestination());
            newPostPackagesList.add(postPackage);
        }
        return newPostPackagesList;
    }
}
