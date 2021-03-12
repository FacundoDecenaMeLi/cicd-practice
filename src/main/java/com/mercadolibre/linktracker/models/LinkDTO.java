package com.mercadolibre.linktracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkDTO {
    private String uri;
    private Integer counter;
    @JsonIgnore
    private Integer id;
    private boolean active;
    private String password;
}
