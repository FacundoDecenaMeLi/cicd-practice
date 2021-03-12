package com.mercadolibre.linktracker.exceptions;

import com.mercadolibre.linktracker.models.ErrorDTO;

public class AlreadyExistException extends LinkTrackerException{
    public AlreadyExistException(ErrorDTO errorDTO) {
        super(errorDTO);
    }
}
