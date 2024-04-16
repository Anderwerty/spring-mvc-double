package org.example.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import static org.thymeleaf.templatemode.TemplateMode.HTML;

//3,4 spring ---> jsp
//5 spring ---> thymeleaf
@Configuration
@EnableWebMvc
@ComponentScan("org.example.bank.controller")
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/images");
        registry.addResourceHandler("/css/**").addResourceLocations("/css");
        registry.addResourceHandler("/js/**").addResourceLocations("/js");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("/WEB-INF/templates");
        resolver.setSuffix(".html");
        resolver.setApplicationContext(applicationContext);
        resolver.setCacheable(true);
        resolver.setTemplateMode(HTML);

        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(@Autowired ITemplateResolver templateResolver) {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setEnableSpringELCompiler(true);
        springTemplateEngine.setTemplateResolver(templateResolver);

        return springTemplateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(@Autowired ISpringTemplateEngine templateEngine) {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine);
        return thymeleafViewResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("messages");

        return resourceBundleMessageSource;
    }
}
