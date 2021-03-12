package com.mercadolibre.linktracker.exceptions;

import com.mercadolibre.linktracker.models.ErrorDTO;

public class InvalidIDException extends LinkTrackerException{
    public InvalidIDException(ErrorDTO errorDTO) {
        super(errorDTO);
    }
}
