package config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

@Configuration
@ComponentScan({"controller", "service"})
@EnableWebMvc
@EnableCaching
public class AppConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        cacheManager.setCaches(Collections.singletonList(cacheFactory().getObject()));

        return cacheManager;
    }

    @Bean
    public ConcurrentMapCacheFactoryBean cacheFactory() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();

        cacheFactoryBean.setName("books");

        return cacheFactoryBean;
    }
}
