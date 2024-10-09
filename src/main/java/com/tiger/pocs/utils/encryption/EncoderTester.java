package com.tiger.pocs.utils.encryption;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class EncoderTester {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> propertiesToEncode = Map.of(
                "firstName", 2,
                "lastName", 2,
                "nickName", 2,
                "identifier.id", 3,
                "identifier.nas.description", 2
        );
        var person = Person.builder()
                .firstName("John")
                .lastName("Doe")
                .nickName("JD")
                .identifier(Identifier.builder()
                        .type("SSN")
                        .id("123-45-6789")
                        .nas(NAS.builder()
                                .type("Nouvel Arrivant")
                                .id("123456789")
                                .description("NAS description")
                                .build())
                        .build())
                .build();


        var encodeObj = CustomerEncoder.encodeProperties(person, propertiesToEncode);
        log.info("Encoded object: {}", encodeObj);

    }
}

@Builder
@ToString
@Getter
@Setter
class Person {
    private String firstName;
    private String lastName;
    private String nickName;
    private Identifier identifier;
}

@Builder
@ToString
@Getter
@Setter
class Identifier {
    private String type;
    private String id;
    private NAS nas;
}

@Builder
@ToString
@Getter
@Setter
class NAS {
    private String type;
    private String id;
    private String description;
}



