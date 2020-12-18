package com.smartsimon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

import java.util.ArrayList;
import java.util.List;

@EnableBinding(Processor.class)
@SpringBootApplication
public class ProcessorServiceApplication {


    Logger logger = LoggerFactory.getLogger(ProcessorServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProcessorServiceApplication.class, args);
    }

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public List<PostPackage> setDestination(List<PostPackage> postPackages) {
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
