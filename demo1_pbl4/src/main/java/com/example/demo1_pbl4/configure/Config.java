//package com.example.demo1_pbl4.configure;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Scope;
//import org.springframework.core.Ordered;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//import org.thymeleaf.templateresolver.ITemplateResolver;
//
//public class Config implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("/homepage/homepage");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }
//
////    @Bean
////    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
////    public TodoList todos() {
////        return new TodoList();
////    }
//
//}
