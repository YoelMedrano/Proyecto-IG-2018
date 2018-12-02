package com.proyectoPaquetes.Service;

import com.proyectoPaquetes.command.ClienteLoginCommand;
import com.proyectoPaquetes.command.SignUp.BloqueoSignUpCommand;
import com.proyectoPaquetes.command.SignUp.ClienteSignUpCommand;
import com.proyectoPaquetes.model.Cliente;
import com.proyectoPaquetes.model.Bloqueo;
import com.proyectoPaquetes.repository.ClienteRepository;
import com.proyectoPaquetes.response.ClienteResponse;
import com.proyectoPaquetes.response.NotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.proyectoPaquetes.command.Validation;
import com.proyectoPaquetes.command.ClienteUpdateCommand;

import java.time.LocalDateTime;

@Slf4j

@Service("MapQuestService")
public class MapQuestService {


    private final String CLAVEDELCONSUMIDOR = "zrB5Gm0U8UNpuhKyVz6VBINjzAhmmCAJ";

    private final String SECRETODELCONSUMIDOR = "5UnSkjUiMhFhsjhr";

    public ResponseEntity<Object> searchDirecion( String searchTerm, String id){

        String apiAddress = "http://open.mapquestapi.com/geocoding/v1/address?key="+CLAVEDELCONSUMIDOR;
        String apiAddressGet = "http://open.mapquestapi.com/geocoding/v1/address?key="+CLAVEDELCONSUMIDOR+"&location=Washington,DC";

        log.info("Search has not been sucessfull");
        return ResponseEntity.badRequest().body(buildNotifyResponse("no_result."));

    }

    private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }

}
