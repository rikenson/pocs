package com.tiger.pocs.config;

import com.google.gson.Gson;
import io.goodforgod.gson.configuration.GsonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

@Configuration
public class GlobalConfig {

    @Value("${traversals.filename}")
    private String filename;

    @Primary
    @Bean("customGson")
    public Gson customGson() {
        return new GsonConfiguration()
                .builder().create();

    }


    @Bean("traversal")
    public List<LinkedHashMap<String, String>> traversals() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(filename);
        return yaml.load(inputStream);


    }
}
