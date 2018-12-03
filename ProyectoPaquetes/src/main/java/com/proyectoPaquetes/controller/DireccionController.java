package com.proyectoPaquetes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.proyectoPaquetes.Service.DireccionService;

import com.proyectoPaquetes.command.SignUp.DireccionSignUpCommand;

@Slf4j

@CrossOrigin
@RestController
@RequestMapping(value = "/direccion", produces = "application/json")
public class DireccionController {


        @Autowired
        private DireccionService service;



        @RequestMapping(value = "/registrar/{idOrden}", consumes = "application/json", method = RequestMethod.POST)
        public ResponseEntity register(@Valid @RequestBody DireccionSignUpCommand command, @PathVariable("idOrden") String id) {
            return service.register(command,id);
        }






    }
