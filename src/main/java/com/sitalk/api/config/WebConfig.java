package com.sitalk.api.config;

import com.sitalk.api.controller.common.HttpInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Value("${server.servlet.session.timeout}")
//    private int sessionTime;

    @Bean
    public HttpInterceptor httpInterceptor() {
        return new HttpInterceptor();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")		            //허용 맵핑 패턴
                //.allowedOrigins("*")				            //허용 Origins(IP주소 or All)
                //.allowedOriginPatterns("http://local-regtech.infohana.com", "http://dev-regtech.infohana.com")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "OPTIONS", "HEAD")		//허용 메소드
                .allowedHeaders("*")
                .allowCredentials(true);
                //.maxAge(sessionTime);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> excludePathPatternList = new ArrayList<>();
        excludePathPatternList.add("/internal/**");
        excludePathPatternList.add("/login/**");
        excludePathPatternList.add("/logout/**");
        excludePathPatternList.add("/admin/**");

        registry.addInterceptor(httpInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatternList);

    }
}
