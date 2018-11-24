package proyecto.paquetes.service;

import proyecto.paquetes.command.UserSignUpCommand;
import proyecto.paquetes.model.User;
import proyecto.paquetes.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.paquetes.command.UserLoginCommand;

import proyecto.paquetes.response.NotifyResponse;
import proyecto.paquetes.response.UserResponse;





import java.util.List;

import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;



@Slf4j
@Service("UserService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private User buildCustomer(UserSignUpCommand command) {
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setFirstName(command.getFirstName());
        user.setLastName(command.getLastName());
        user.setEmail(command.getEmail());
        user.setPassword(command.getPassword());

        return user;
    }

    public ResponseEntity<Object> login(UserLoginCommand command) {
        log.debug("About to process [{}]", command);
        User u = userRepository.findFirstByEmailIgnoreCaseContaining(command.getEmail());
        if (u == null) {
            log.info("Cannot find user with email={}", command.getEmail());

            return ResponseEntity.badRequest().body(buildNotifyResponse("Dirección de correo no válida."));
        } else {
            if (u.getPassword().equals(command.getPassword())) {
                log.info("Successful login for user={}", u.getId());

                UserResponse respuesta = new UserResponse();
                respuesta.setFirstName(u.getFirstName());
                respuesta.setLastName(u.getLastName());
                respuesta.setEmail(u.getEmail());
                respuesta.setId(String.valueOf(u.getId()));

                respuesta.setDate(u.getDate());

                return ResponseEntity.ok(respuesta);
            } else {
                log.info("{} is not valid password for user {}", command.getPassword(), u.getId());

                return ResponseEntity.badRequest().body(buildNotifyResponse("Proceso no válido. "));
            }
        }

    }
    public ResponseEntity<Object> register(UserSignUpCommand command) {
        log.debug("About to be processed [{}]", command);

        if (userRepository.existsByEmail(command.getEmail())) {
            log.info("La dirección de correo {} ya se encuentra en la base de datos.", command.getEmail());

            return ResponseEntity.badRequest().body(buildNotifyResponse("El usuario ya se encuentra registrado en el sistema."));
        } else {
            if (!command.getPassword().equals(command.getConfirmationPassword())) {
                log.info("The passwords are not equal");
                return ResponseEntity.badRequest().body(buildNotifyResponse("Las contrasenas no coinciden"));
            } else {
                User user = new User();


                user.setId(System.currentTimeMillis());
                user.setFirstName(command.getFirstName());
                user.setLastName(command.getLastName());
                user.setEmail(command.getEmail());
                user.setPassword(command.getPassword());
                user.setDate(command.getDate());

                userRepository.save(user);

                log.info("Registered user with ID={}", user.getId());

                return ResponseEntity.ok().body(buildNotifyResponse("Usuario registrado."));
            }
        }
    }
    public List<User> findCustomersByEmail(String email) {

        List<User> customers = userRepository.findFirst3ByEmailIgnoreCaseContaining(email);

        log.info("Found {} records with the partial email address={}", customers.size(), email);
        return customers;
    }


    private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }
}
