package com.example.configs;

import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//@Configuration
//@EnableWebMvc
//@ComponentScan("com.example")
public class MyMvcConfig {

//    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".html");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
}
