package com.accenture.quote.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RedisCacheConfigInspector {

   @Autowired
   private CacheManager cacheManager;

   public void printCacheConfigurations(String cacheName) {
       Cache cache = cacheManager.getCache(cacheName);
       if (cache != null) {
           System.out.println("Quotes Cache Configuration:");
           System.out.println(Objects.requireNonNull(cache.get("clientId")));
       } else {
           System.out.println("No specific configuration found for 'quotesCache'.");
       }
   }
}
