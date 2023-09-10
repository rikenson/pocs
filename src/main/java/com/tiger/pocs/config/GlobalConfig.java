package com.tiger.pocs.config;

import com.google.gson.Gson;
import io.goodforgod.gson.configuration.GsonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GlobalConfig {

    /**
     * @return MongoClient
     */
    @Primary
    @Bean("customGson")
    public Gson customGson() {
        return new GsonConfiguration()
                .builder().create();
    }
}
