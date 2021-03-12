package com.mercadolibre.linktracker.exceptions;

import com.mercadolibre.linktracker.models.ErrorDTO;

public class InvalidURLException extends LinkTrackerException{
    public InvalidURLException(ErrorDTO errorDTO) {
        super(errorDTO);
    }
}
