package com.mercadolibre.linktracker.exceptions;

import com.mercadolibre.linktracker.models.ErrorDTO;

public class WrongPasswordException extends LinkTrackerException{
    public WrongPasswordException(ErrorDTO errorDTO) {
        super(errorDTO);
    }
}
