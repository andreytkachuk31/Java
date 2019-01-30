package config;

import context.MyInjectBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller", "service"})
@Import(I18nConfig.class)
public class AppConfig {

    @Bean
    public MyInjectBeanPostProcessor myInjectBeanPostProcessor() {
        return new MyInjectBeanPostProcessor();
    }
}
