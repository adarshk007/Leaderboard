package com.example.demo.service;

import lombok.Data;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Maintains the cache size. Reads cacheSize from application properties
 */

@Data
@Service
public class CacheSize {
    private Integer cacheSize;

    public CacheSize(Environment env) throws  Exception{
        String cacheSize =  env.getRequiredProperty("cacheSize");
        this.cacheSize = Integer.parseInt(cacheSize != null? cacheSize: "0");
    }

}
