package com.challenge.careerswtich.controller;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.CompoundControl.Type;

import com.challenge.careerswtich.services.CheckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @Autowired
    private CheckService checkService;

    @GetMapping(path = "/check")
    public ResponseEntity<?> check(){
        return new ResponseEntity<>(checkService.check(), HttpStatus.OK);
    }   
}