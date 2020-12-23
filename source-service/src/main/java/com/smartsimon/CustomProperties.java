package com.smartsimon;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("pulldata-task")
public class CustomProperties {
    private String testFileName;

}
