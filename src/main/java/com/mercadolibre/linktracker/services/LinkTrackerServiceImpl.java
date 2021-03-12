package com.mercadolibre.linktracker.services;

import com.mercadolibre.linktracker.exceptions.AlreadyExistException;
import com.mercadolibre.linktracker.exceptions.InvalidIDException;
import com.mercadolibre.linktracker.exceptions.InvalidURLException;
import com.mercadolibre.linktracker.exceptions.WrongPasswordException;
import com.mercadolibre.linktracker.models.ErrorDTO;
import com.mercadolibre.linktracker.models.LinkDTO;
import com.mercadolibre.linktracker.models.ResponseDTO;
import com.mercadolibre.linktracker.repositories.LinkTrackerRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LinkTrackerServiceImpl implements LinkTrackerService {

    @Autowired
    private LinkTrackerRepository repository;

    @Override
    public ResponseDTO addLink(LinkDTO linkDTO) throws InvalidURLException, AlreadyExistException {
        boolean isValid = validateUri(linkDTO.getUri());
        if (!isValid) {
            invalidUrl();
        }
        return repository.add(linkDTO);
    }

    @Override
    public LinkDTO findByID(Integer linkID, String password) throws InvalidIDException, WrongPasswordException {
        return repository.findByID(linkID, password);
    }

    @Override
    public Integer countOccurrences(Integer linkID) throws InvalidIDException {
        return repository.countOccurrences(linkID);
    }

    @Override
    public ResponseDTO invalidateLink(Integer linkID) throws InvalidIDException {
        return repository.invalidateLink(linkID);
    }

    private boolean validateUri(String uri) {
        return new UrlValidator().isValid(uri);
    }

    private void invalidUrl() throws InvalidURLException {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setName("Invalid url");
        errorDTO.setDescription("The given url has an invalid format");
        errorDTO.setStatus(HttpStatus.BAD_REQUEST);
        throw new InvalidURLException(errorDTO);
    }
}
