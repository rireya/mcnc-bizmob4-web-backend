package com.mcnc.bizmob.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.mcnc.bizmob.web.global.config.OCIStorageConfig;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(
		  type = FilterType.ASSIGNABLE_TYPE, classes = { OCIStorageConfig.class }
		))
public class BizmobWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizmobWebApplication.class, args);
		
	}
 
}
 