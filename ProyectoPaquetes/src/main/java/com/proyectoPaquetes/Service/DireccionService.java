package com.proyectoPaquetes.Service;

import com.proyectoPaquetes.command.SignUp.DireccionSignUpCommand;
import com.proyectoPaquetes.model.Direccion;
import com.proyectoPaquetes.repository.DireccionRepository;
import com.proyectoPaquetes.repository.ClienteRepository;
import com.proyectoPaquetes.repository.OrdenRepository;
import com.proyectoPaquetes.response.NotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j

@Service("DireccionService")
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    public ResponseEntity<Object> register(DireccionSignUpCommand command,String idOrden) {
        log.debug("About to be processed [{}]", command);

        if (ordenRepository.existsById(Long.parseLong(idOrden))) {
            try {


                Direccion direccion = new Direccion();

                direccion.setIdDireccion(System.currentTimeMillis());
                direccion.setIdOrden(Long.parseLong(idOrden));
                direccion.setDireccion1(command.getDireccion1());
                direccion.setDireccion2(command.getDireccion2());
                direccion.setCodigoPostal(command.getCodigoPostal());
                direccion.setCiudad(command.getCiudad());
                direccion.setPais(command.getPais());
                direccion.setLongitud(command.getLongitud());
                direccion.setLatitud(command.getLatitud());


                direccionRepository.save(direccion);

                log.info("Direccion Registrada Id = {} , OrdenId = {} ", direccion.getIdDireccion(), direccion.getIdOrden());

                return ResponseEntity.ok().body(buildNotifyResponse("Direccion registrada "));


            } catch (Exception e) {
                return ResponseEntity.badRequest().body(buildNotifyResponse("*Ocurrio un Error* : La direccion no se pudo registrar en el sistema."));

            }
        }else{
            return ResponseEntity.badRequest().body(buildNotifyResponse("id de orden invalido"));
        }

    }


    private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }
}
