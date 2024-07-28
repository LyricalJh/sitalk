package com.sitalk.api;

import com.sitalk.api.config.ConfigBean;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties({ConfigBean.class})
public class SiTalkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiTalkApiApplication.class, args);
	}

}
