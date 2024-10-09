package com.tiger.pocs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CustomError {
    private String message;
    private HttpStatus status;
}
