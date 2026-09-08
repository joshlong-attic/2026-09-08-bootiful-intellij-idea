package com.example.idea4;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MyConfigurationProperties.class)
class MyConfiguration {
}

@ConfigurationProperties(prefix = "my")
record MyConfigurationProperties(int favoriteNumber) {
}
