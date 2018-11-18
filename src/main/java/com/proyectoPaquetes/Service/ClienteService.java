package com.proyectoPaquetes.Service;


import com.proyectoPaquetes.command.ClienteLoginCommand;
import com.proyectoPaquetes.command.ClienteSignUpCommand;
import com.proyectoPaquetes.model.Cliente;
import com.proyectoPaquetes.repository.ClienteRepository;
import com.proyectoPaquetes.response.ClienteResponse;
import com.proyectoPaquetes.response.NotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Calendar;

import java.time.LocalDateTime;

@Slf4j

@Service("ClienteService")
public class ClienteService {



    @Autowired
    private ClienteRepository clienteRepository;




    public ResponseEntity<Object> login(ClienteLoginCommand command) {
        log.debug("About to process [{}]", command);
        Cliente u = clienteRepository.findFirstByCorreoElectronicoIgnoreCaseContaining(command.getCorreoElectronico());
        if (u == null) {
            log.info("Cannot find user with email={}", command.getCorreoElectronico());

            return ResponseEntity.badRequest().body(buildNotifyResponse("Dirección de correo no válida."));
        } else {
            if (u.getContrasena().equals(command.getContrasena())) {
                log.info("Successful login for user={}", u.getIdCliente());

                ClienteResponse respuesta = new ClienteResponse();
                respuesta.setIdCliente(String.valueOf(u.getIdCliente()));
                respuesta.setNombre(u.getNombre());
                respuesta.setApellido(u.getApellido());
                respuesta.setCorreoElectronico(u.getCorreoElectronico());
                respuesta.setContrasena(u.getContrasena());
                respuesta.setFechaNacimiento(String.valueOf(u.getFechaNacimiento()));

                return ResponseEntity.ok(respuesta);
            } else {
                log.info("{} is not valid password for user {}", command.getContrasena(), u.getIdCliente());

                return ResponseEntity.badRequest().body(buildNotifyResponse("Proceso no válido. "));
            }
        }

    }


//-----------------------------------------------------------------------------------------------------------
    //SERVICIO PARA REGISTRAR USUARIO

    public ResponseEntity<Object> register(ClienteSignUpCommand command) {
        log.debug("About to be processed [{}]", command);



        if (clienteRepository.existsByCorreoElectronico(command.getCorreoElectronico())) {
            log.info("La dirección de correo {} ya se encuentra en la base de datos.", command.getCorreoElectronico());

            return ResponseEntity.badRequest().body(buildNotifyResponse("El usuario ya se encuentra registrado en el sistema."));
        } else {

                if (!command.getContrasena().equals(command.getConfirmacioncontrasena())) {
                    log.info("Las contrasenas no coinciden");
                    return ResponseEntity.badRequest().body(buildNotifyResponse("Las contrasenas no coinciden"));
                } else {
                    try {
                    if(esMayorDeEdad(command.getFechaNacimiento())){

                    Cliente user = new Cliente();

                        user.setIdCliente(System.currentTimeMillis());
                        user.setNombre(command.getNombre());
                        user.setApellido(command.getApellido());
                        user.setCorreoElectronico(command.getCorreoElectronico());
                        user.setContrasena(command.getContrasena());
                        user.setFechaNacimiento(command.getFechaNacimiento());

                        clienteRepository.save(user);

                        log.info("Usuario Registrado ", user.getIdCliente());

                        return ResponseEntity.ok().body(buildNotifyResponse("Usuario registrado."));
                    }else{
                        return ResponseEntity.badRequest().body(buildNotifyResponse("El Usuario no es mayor de edad "));

                    }

                }catch(Exception e){
                log.info("Contrasena Invalida ", command.getContrasena());
                return ResponseEntity.badRequest().body(buildNotifyResponse("*Contrasena Invalida* : El usuario no se pudo registrar en el sistema."));

                    }
                }
            }

    }


    private Boolean esMayorDeEdad(Date fecha){

        Date data = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("d");//dd/MM/yyyy
        String op = formatar.format(data);
        formatar = new SimpleDateFormat("M");//dd/MM/yyyy
        String op2 = formatar.format(data);
        formatar = new SimpleDateFormat("y");//dd/MM/yyyy
        String op3 = formatar.format(data);

        Calendar calendar = Calendar.getInstance(); // Obtiene una instancia de Calendar
        calendar.setTime(fecha); // Asigna la fecha al Calendar

        if((Integer.parseInt(op3)-calendar.get(Calendar.YEAR)) >18){
            return true;

        }else if((Integer.parseInt(op3)-calendar.get(Calendar.YEAR)) ==18){
            if((calendar.get(Calendar.MONTH)+1<Integer.parseInt(op2))) {
                return true;

            }else if((calendar.get(Calendar.MONTH)+1==Integer.parseInt(op2))){
                if((calendar.get(Calendar.DAY_OF_MONTH)+1<=Integer.parseInt(op))){
                    return true;

                }else return false;

            }else return false;

        }else{
            return false;
        }


      //  log.info("es :{} {} {}",op,op2,op3);

    }


    private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }
}
