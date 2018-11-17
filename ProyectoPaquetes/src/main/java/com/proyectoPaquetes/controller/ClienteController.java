package com.proyectoPaquetes.controller;

import com.proyectoPaquetes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.proyectoPaquetes.model.Cliente;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;


    @PostMapping("/registrar")
    public Cliente createQuestion(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/clientes")
    public Page<Cliente> getQuestions(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }



}
