package com.tiger.pocs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CustomInternalError extends CustomError {
}
