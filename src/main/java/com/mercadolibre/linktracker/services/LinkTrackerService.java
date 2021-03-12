package com.mercadolibre.linktracker.services;

import com.mercadolibre.linktracker.exceptions.AlreadyExistException;
import com.mercadolibre.linktracker.exceptions.InvalidIDException;
import com.mercadolibre.linktracker.exceptions.InvalidURLException;
import com.mercadolibre.linktracker.exceptions.WrongPasswordException;
import com.mercadolibre.linktracker.models.LinkDTO;
import com.mercadolibre.linktracker.models.ResponseDTO;

public interface LinkTrackerService {

    ResponseDTO addLink(LinkDTO linkDTO) throws InvalidURLException, AlreadyExistException;

    LinkDTO findByID(Integer linkID, String password) throws InvalidIDException, WrongPasswordException;

    Integer countOccurrences(Integer linkID) throws InvalidIDException;

    ResponseDTO invalidateLink(Integer linkID) throws InvalidIDException;

}
