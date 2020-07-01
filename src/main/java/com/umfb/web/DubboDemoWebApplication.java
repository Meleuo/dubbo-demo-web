package com.umfb.web;

import com.umfb.config.zk.ZooKeeperPropertyPlaceholderConfigurer;
import com.umfb.utils.UmfbApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@SpringBootApplication
@EnableAutoConfiguration
public class DubboDemoWebApplication {
	private static final Logger logger = LoggerFactory.getLogger(DubboDemoWebApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DubboDemoWebApplication.class, args);

		final ApplicationContext ac = SpringApplication.run(DubboDemoWebApplication.class, args);
		UmfbApplicationContext.setApplicationContext(ac);
	}

	@Bean
	public UmfbApplicationContext initAppEnd(ApplicationContext ac) {
		UmfbApplicationContext.setApplicationContext(ac);
		UmfbApplicationContext app = new UmfbApplicationContext();
		logger.warn("------------------------------web war started------------------------------");
		return app;

	}

	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	@Bean
	public PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new ZooKeeperPropertyPlaceholderConfigurer();
		configurer.setIgnoreResourceNotFound(true);
		configurer.setLocations(resourcePatternResolver.getResource("classpath:application.properties"));
		return configurer;
	}
}
