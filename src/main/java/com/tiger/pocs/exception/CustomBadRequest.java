package com.tiger.pocs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomBadRequest extends CustomError {

    private Map<String, String> errors;

    public CustomBadRequest(String validationFailed, HttpStatus httpStatus, Map<String, String> errors) {
        super(validationFailed, httpStatus);
        this.errors = errors;
    }
}
