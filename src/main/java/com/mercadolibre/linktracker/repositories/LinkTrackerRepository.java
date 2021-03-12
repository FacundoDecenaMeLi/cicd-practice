package com.mercadolibre.linktracker.repositories;

import com.mercadolibre.linktracker.exceptions.AlreadyExistException;
import com.mercadolibre.linktracker.exceptions.InvalidIDException;
import com.mercadolibre.linktracker.exceptions.WrongPasswordException;
import com.mercadolibre.linktracker.models.LinkDTO;
import com.mercadolibre.linktracker.models.ResponseDTO;

public interface LinkTrackerRepository {

    ResponseDTO add(LinkDTO linkDTO) throws AlreadyExistException;

    LinkDTO findByID(Integer linkId, String password) throws InvalidIDException, WrongPasswordException;

    Integer countOccurrences(Integer linkId) throws InvalidIDException;

    ResponseDTO invalidateLink(Integer linkID) throws InvalidIDException;

}
