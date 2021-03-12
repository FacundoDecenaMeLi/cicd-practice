package com.mercadolibre.linktracker.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class ErrorDTO implements Serializable {
    private String name;
    private String description;
    private HttpStatus status;
}
