package com.mercadolibre.linktracker.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorDTO {
    private String name;
    private String description;
    private HttpStatus status;
}
