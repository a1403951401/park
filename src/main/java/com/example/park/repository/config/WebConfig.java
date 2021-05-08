package com.example.park.repository.config;

import com.baomidou.kisso.web.interceptor.SSOSpringInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@ControllerAdvice
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // kisso 拦截器配置
        registry.addInterceptor(new SSOSpringInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
    }
}