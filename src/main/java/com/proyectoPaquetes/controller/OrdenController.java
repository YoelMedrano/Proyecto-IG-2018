package com.proyectoPaquetes.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.proyectoPaquetes.Service.OrdenService;

import com.proyectoPaquetes.command.SignUp.OrdenSignUpCommand;

@Slf4j

@CrossOrigin
@RestController
@RequestMapping(value = "/orden", produces = "application/json")
public class OrdenController {



        @Autowired
        private OrdenService ordenService;



        @RequestMapping(value = "/registrar/{id}", consumes = "application/json", method = RequestMethod.POST)
        public ResponseEntity register(@Valid @RequestBody OrdenSignUpCommand command, @PathVariable("id") String id) {
            return ordenService.register(command,id);
        }




}
