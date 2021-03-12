package com.mercadolibre.linktracker.exceptions;

import com.mercadolibre.linktracker.models.ErrorDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class LinkTrackerException extends Exception {
    private final ErrorDTO errorDTO;
    private final HttpStatus status;

    public LinkTrackerException(ErrorDTO errorDTO) {
        super(errorDTO.getDescription());
        this.errorDTO = errorDTO;
        this.status = errorDTO.getStatus();
    }
}