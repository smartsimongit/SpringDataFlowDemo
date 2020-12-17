package com.smartsimon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableBinding(Source.class)
public class SourceServiceApplication {


	Logger logger=LoggerFactory.getLogger(SourceServiceApplication.class);

	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT,poller = @Poller(fixedDelay = "10000",maxMessagesPerPoll = "1"))
	public MessageSource<List<PostPackage>> addProducts(){
		List<PostPackage> products= Stream.of(
				new PostPackage(190000, "Processor")
				, new PostPackage(101000, "Processor")
				, new PostPackage(620000, "Processor")
		)
				.collect(Collectors.toList());
		logger.info("products : {}",products);
		return ()-> MessageBuilder.withPayload(products).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SourceServiceApplication.class, args);
	}

}
