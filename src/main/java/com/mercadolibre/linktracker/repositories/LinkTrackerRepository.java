package com.mercadolibre.linktracker.repositories;

import com.mercadolibre.linktracker.exceptions.AlreadyExistException;
import com.mercadolibre.linktracker.exceptions.InvalidIDException;
import com.mercadolibre.linktracker.exceptions.WrongPasswordException;
import com.mercadolibre.linktracker.models.LinkDTO;
import com.mercadolibre.linktracker.models.ResponseDTO;

public interface LinkTrackerRepository {

    /**
     * Adds a new link or reactivates a invalidated link.
     *
     * @param linkDTO the link to be hidden
     * @return the ID, a message and a password
     * @throws AlreadyExistException if a link already exist and is active
     */
    ResponseDTO add(LinkDTO linkDTO) throws AlreadyExistException;

    /**
     * finds a link using an ID
     *
     * @param linkId   link id
     * @param password the password given when added the link
     * @return the link DTO
     * @throws InvalidIDException     if the given ID does not represent a link
     * @throws WrongPasswordException if the given password if wrong
     */
    LinkDTO findByID(Integer linkId, String password) throws InvalidIDException, WrongPasswordException;

    /**
     * Counts the amount of redirects of a link
     *
     * @param linkId the link's id of interest
     * @return the number of redirects
     * @throws InvalidIDException if the given ID does not represent a link
     */
    Integer countOccurrences(Integer linkId) throws InvalidIDException;

    /**
     * Removes the link with the given id, the removal is a logic one
     *
     * @param linkID the link's id of interest
     * @return the ID, a message and a password
     * @throws InvalidIDException if the given ID does not represent a link
     */
    ResponseDTO invalidateLink(Integer linkID) throws InvalidIDException;

}
