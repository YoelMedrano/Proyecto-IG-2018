package com.proyectoPaquetes.Service;

import com.proyectoPaquetes.command.SignUp.OrdenSignUpCommand;
import com.proyectoPaquetes.model.Orden;
import com.proyectoPaquetes.repository.OrdenRepository;
import com.proyectoPaquetes.repository.ClienteRepository;
import com.proyectoPaquetes.response.NotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j

@Service("OrdenService")
public class OrdenService {

        @Autowired
        private ClienteRepository clienteRepository;

        @Autowired
        private OrdenRepository ordenRepository;


        public ResponseEntity<Object> register(OrdenSignUpCommand command,String idCliente) {
            log.debug("About to be processed [{}]", command);

            if (clienteRepository.existsById(Long.parseLong(idCliente))) {
                try {


                    Orden orden = new Orden();

                    orden.setIdOrden(System.currentTimeMillis());
                    orden.setIdCliente(Long.parseLong(idCliente));
                    orden.setDireccionEntrega(command.getDireccionEntrega());
                    orden.setDireccionRecoleccion(command.getDireccionRecoleccion());


                    ordenRepository.save(orden);

                    log.info("Paquete Registrado Id = {} , ClienteId = {} ", orden.getIdOrden(), orden.getIdCliente());

                    return ResponseEntity.ok().body(buildNotifyResponse("Paquete registrado "));


                } catch (Exception e) {
                    return ResponseEntity.badRequest().body(buildNotifyResponse("*Ocurrio un Error* : El paquete no se pudo registrar en el sistema."));

                }
            }else{
                return ResponseEntity.badRequest().body(buildNotifyResponse("id invalido"));
            }

        }





    private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }
    }
