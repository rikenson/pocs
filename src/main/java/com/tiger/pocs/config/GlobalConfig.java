package com.tiger.pocs.config;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tiger.pocs.utils.LocalDateTypeAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

@Configuration
public class GlobalConfig {

    /**
     * @return MongoClient
     */
    @Primary
    @Bean("customGson")
    public Gson customGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
    }
}
