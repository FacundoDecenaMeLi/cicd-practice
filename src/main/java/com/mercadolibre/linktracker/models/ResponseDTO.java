package com.mercadolibre.linktracker.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private Integer linkID;
    private String message;
    private String password;
}
