package com.ciandt.summit.bootcamp2022.config;


import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class ApiConfigCache extends CachingConfigurerSupport {

   @Bean
    public net.sf.ehcache.CacheManager ehCacheManager(){
       CacheConfiguration tenSecondCache = new CacheConfiguration();
       tenSecondCache.setName("ten-minutes-cache");
       tenSecondCache.setMemoryStoreEvictionPolicy("LFU");
       tenSecondCache.setMaxEntriesLocalHeap(1000);
       tenSecondCache.setTimeToLiveSeconds(600);

     net.sf.ehcache.config.Configuration  config = new net.sf.ehcache.config.Configuration();
     config.addCache(tenSecondCache);
     return  net.sf.ehcache.CacheManager.newInstance(config);
   }

    @Bean
    @Override
    public CacheManager cacheManager(){
        return new EhCacheCacheManager(ehCacheManager());
    }
}
