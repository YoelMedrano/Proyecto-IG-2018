package com.proyectoPaquetes.Service;


import com.proyectoPaquetes.command.SignUp.PaqueteSignUpCommand;
import com.proyectoPaquetes.model.Paquete;
import com.proyectoPaquetes.repository.ClienteRepository;
import com.proyectoPaquetes.repository.PaqueteRepository;
import com.proyectoPaquetes.response.NotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j

@Service("PaqueteService")
public class PaqueteService {


        @Autowired
        private PaqueteRepository paqueteRepository;
        @Autowired
        private ClienteRepository clienteRepository;


            public ResponseEntity<Object> register(PaqueteSignUpCommand command,String idCliente,String idOrden) {
                log.debug("About to be processed [{}]", command);

                if (clienteRepository.existsById(Long.parseLong(idCliente))) {
                    try {


                        Paquete paquete = new Paquete();

                        paquete.setIdPaquete(System.currentTimeMillis());
                        paquete.setIdCliente(Long.parseLong(idCliente));
                        paquete.setIdOrden(Long.parseLong(idOrden));
                        paquete.setNombreApellidoEntrega(command.getNombreApellidoEntrega());
                        paquete.setPesoKgs(Double.parseDouble(command.getPesoKgs()));
                        paquete.setDescripcionPaquete(command.getDescripcionPaquete());

                        paqueteRepository.save(paquete);

                        log.info("Paquete Registrado Id = {} , ClienteId = {} ", paquete.getIdPaquete(), paquete.getIdCliente());

                        return ResponseEntity.ok().body(buildNotifyResponse("Paquete registrado "));


                    } catch (Exception e) {
                        return ResponseEntity.badRequest().body(buildNotifyResponse("*Ocurrio un Error* : El paquete no se pudo registrar en el sistema."));

                    }
                }else{
                    return ResponseEntity.badRequest().body(buildNotifyResponse("id invalido"));
                }

            }

    public ResponseEntity<Object> eliminarPaquete(String id) {
        try {

            paqueteRepository.deleteById(Long.parseLong(id));

            return ResponseEntity.ok().body(buildNotifyResponse("El paquete ha sido eliminado"));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("El paquete no pudo ser eliminado "));

        }
    }


/*
        public ResponseEntity<Object> update(ClienteUpdateCommand command, String id) {
            log.debug("About to process [{}]", command);
            if (!clienteRepository.existsById(Long.parseLong(id))) {
                log.info("Cannot find user with ID={}", id);
                return ResponseEntity.badRequest().body(buildNotifyResponse("id invalido"));
            } else {
                if(validation.esMayorDeEdad(command.getFechaNacimiento())){
                    Cliente user = new Cliente();
                    clienteRepository.deleteById(Long.parseLong(id));

                    user.setIdCliente(Long.parseLong(id));
                    user.setNombre(command.getNombre());
                    user.setApellido(command.getApellido());
                    user.setCorreoElectronico(command.getCorreoElectronico());
                    user.setContrasena(command.getContrasena());
                    user.setFechaNacimiento(command.getFechaNacimiento());

                    clienteRepository.save(user);

                    log.info("Updated user with ID={}", user.getIdCliente());

                    return ResponseEntity.ok().body(buildNotifyResponse("La operación ha sido exitosa."));
                }else{
                    return ResponseEntity.badRequest().body(buildNotifyResponse("El Usuario no es mayor de edad "));

                }
            }
        }
*/





        private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
            NotifyResponse respuesta = new NotifyResponse();
            respuesta.setMessage(message);
            respuesta.setTimestamp(LocalDateTime.now());
            return respuesta;
        }
    }




