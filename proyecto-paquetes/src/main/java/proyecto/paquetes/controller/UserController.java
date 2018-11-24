package proyecto.paquetes.controller;

import proyecto.paquetes.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.paquetes.command.UserSignUpCommand;
import proyecto.paquetes.command.UserLoginCommand;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value="/user", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/register", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity register(@Valid @RequestBody UserSignUpCommand command) {
        return userService.register(command);
    }

    @RequestMapping(value = "/login", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity login(@Valid @RequestBody UserLoginCommand command) {
        return userService.login(command);
    }
}
