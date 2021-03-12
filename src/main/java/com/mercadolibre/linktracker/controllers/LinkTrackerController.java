package com.mercadolibre.linktracker.controllers;

import com.mercadolibre.linktracker.exceptions.*;
import com.mercadolibre.linktracker.models.ErrorDTO;
import com.mercadolibre.linktracker.models.LinkDTO;
import com.mercadolibre.linktracker.models.ResponseDTO;
import com.mercadolibre.linktracker.services.LinkTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LinkTrackerController {

    @Autowired
    private LinkTrackerService service;

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseDTO add(@RequestBody LinkDTO entry) throws InvalidURLException, AlreadyExistException {
        return service.addLink(entry);
    }


    @GetMapping("/link/{linkID}")
    @ResponseStatus(value = HttpStatus.PERMANENT_REDIRECT)
    public ModelAndView redirect(@PathVariable Integer linkID, String password) throws InvalidIDException, WrongPasswordException {
        LinkDTO link = service.findByID(linkID, password);
        return new ModelAndView("redirect:" + link.getUri());
    }

    @GetMapping("/metrics/{linkID}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Integer metrics(@PathVariable Integer linkID) throws InvalidIDException {
        return service.countOccurrences(linkID);
    }

    @PostMapping("/invalidate/{linkID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO invalidate(@PathVariable Integer linkID) throws InvalidIDException {
        return service.invalidateLink(linkID);
    }

    @ExceptionHandler(LinkTrackerException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(LinkTrackerException e) {
        return new ResponseEntity<>(e.getErrorDTO(), e.getStatus());
    }

}
