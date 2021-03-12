package com.mercadolibre.linktracker.repositories;

import com.mercadolibre.linktracker.exceptions.AlreadyExistException;
import com.mercadolibre.linktracker.exceptions.InvalidIDException;
import com.mercadolibre.linktracker.exceptions.WrongPasswordException;
import com.mercadolibre.linktracker.models.ErrorDTO;
import com.mercadolibre.linktracker.models.LinkDTO;
import com.mercadolibre.linktracker.models.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class LinkTrackerRepositoryImpl implements LinkTrackerRepository {

    private static final List<LinkDTO> links = new LinkedList<>();

    @Override
    public ResponseDTO add(LinkDTO linkDTO) throws AlreadyExistException {
        ResponseDTO responseDTO = new ResponseDTO();

        LinkDTO link = findByUrl(linkDTO.getUri());
        if (link != null) {
            if (link.isActive()) {
                existException();
            } else {
                link.setActive(true);
                responseDTO.setLinkID(link.getId());
                responseDTO.setMessage("link revalidated");
                responseDTO.setPassword(link.getPassword());
                return responseDTO;
            }
        }

        linkDTO.setCounter(0);
        linkDTO.setActive(true);
        linkDTO.setPassword((links.size() + 1) + "clave");
        linkDTO.setId(links.size());
        links.add(linkDTO);

        responseDTO.setLinkID(linkDTO.getId());
        responseDTO.setMessage("link added correctly");
        responseDTO.setPassword(links.size() + "clave");

        return responseDTO;
    }

    private void existException() throws AlreadyExistException {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setName("already exists");
        errorDTO.setDescription("the given url already exist, there is no need to add it again");
        errorDTO.setStatus(HttpStatus.CONFLICT);
        throw new AlreadyExistException(errorDTO);
    }

    @Override
    public LinkDTO findByID(Integer linkId, String password) throws InvalidIDException, WrongPasswordException {
        try {
            LinkDTO link = links.get(linkId);
            if (!link.isActive()) {
                idException();
            }
            if (!link.getPassword().equals(password)) {
                passwordException();
            }
            link.setCounter(link.getCounter() + 1);
            return link;
        } catch (IndexOutOfBoundsException e) {
            idException();
        }
        return null;
    }

    @Override
    public Integer countOccurrences(Integer linkId) throws InvalidIDException {
        try {
            LinkDTO link = links.get(linkId);
            if (!link.isActive()) {
                idException();
            }
            return link.getCounter();
        } catch (IndexOutOfBoundsException e) {
            idException();
        }
        return null;
    }

    @Override
    public ResponseDTO invalidateLink(Integer linkID) throws InvalidIDException {
        try {
            LinkDTO link = links.get(linkID);
            if (!link.isActive()) {
                idException();
            }
            link.setActive(false);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("the link has been invalidated correctly");
            responseDTO.setLinkID(linkID);
            return responseDTO;
        } catch (IndexOutOfBoundsException e) {
            idException();
        }
        return null;
    }

    private LinkDTO findByUrl(String url) {
        for (int i = 0; i < links.size(); i++) {
            LinkDTO link = links.get(i);
            if (link.getUri().equals(url)) {
                link.setId(i);
                return link;
            }
        }
        return null;
    }


    private void idException() throws InvalidIDException {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setName("Invalid ID");
        errorDTO.setDescription("The given ID does not exist");
        errorDTO.setStatus(HttpStatus.NOT_FOUND);
        throw new InvalidIDException(errorDTO);
    }

    private void passwordException() throws WrongPasswordException {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setName("wrong password");
        errorDTO.setDescription("the password is even not specified or absent");
        errorDTO.setStatus(HttpStatus.FORBIDDEN);
        throw new WrongPasswordException(errorDTO);
    }
}
