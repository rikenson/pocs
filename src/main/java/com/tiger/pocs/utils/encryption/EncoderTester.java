package com.tiger.pocs.utils.encryption;

import com.tiger.pocs.domain.entity.rdbms.DetailsEntity;
import com.tiger.pocs.domain.entity.rdbms.SampleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class EncoderTester implements CommandLineRunner {

    private final CustomerEncoder customerEncoder;

    @Override
    public void run(String... args) {
        Map<String, Integer> propertiesToEncode = Map.of(
                "name", 2,
                "description", 2,
                "details.about", 2);

        var encodeSubject = SampleEntity.builder()
                .name("John").description("Doe")
                .details(DetailsEntity.builder().about("About John Doe").build())
                .build();

        String separator = "*****";
        log.info("{} Before encoding object: {}", separator, encodeSubject);

        var encodeObj = customerEncoder.encodeProperties(encodeSubject, propertiesToEncode);
        log.info("{} Encoded object: {}", separator, encodeObj);

        var decodeObj = customerEncoder.decodeProperties(encodeObj, propertiesToEncode);
        log.info("{} Decoded object: {}", separator, decodeObj);
    }

}

