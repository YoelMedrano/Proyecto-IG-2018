package com.proyectoPaquetes.Service;

import com.proyectoPaquetes.command.SignUp.DireccionSignUpCommand;
import com.proyectoPaquetes.model.Direccion;
import com.proyectoPaquetes.repository.DireccionRepository;
import com.proyectoPaquetes.repository.ClienteRepository;
import com.proyectoPaquetes.repository.OrdenRepository;
import com.proyectoPaquetes.response.DireccionResponse;
import com.proyectoPaquetes.response.ListDireccionResponse;
import com.proyectoPaquetes.response.NotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Slf4j

@Service("DireccionService")
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    public ResponseEntity<Object> register(DireccionSignUpCommand command,String idOrden) {
        log.debug("About to be processed [{}]", command);
        try {
        if (ordenRepository.existsById(Long.parseLong(idOrden))) {

            if(verificarDireccion(command.getLatitud(),command.getLongitud())) {

                Direccion direccion = new Direccion();

                direccion.setIdDireccion(System.currentTimeMillis());
                direccion.setIdOrden(Long.parseLong(idOrden));
                direccion.setDireccion1(command.getDireccion1());
                direccion.setDireccion2(command.getDireccion2());
                direccion.setCodigoPostal(command.getCodigoPostal());
                direccion.setCiudad(command.getCiudad());
                direccion.setPais(command.getPais());
                direccion.setTipoDeDireccion(command.getTipoDeDireccion());
                direccion.setLongitud(command.getLongitud());
                direccion.setLatitud(command.getLatitud());


                direccionRepository.save(direccion);

                log.info("Direccion Registrada Id = {} , OrdenId = {} ", direccion.getIdDireccion(), direccion.getIdOrden());

                return ResponseEntity.ok().body(buildNotifyResponse("Direccion registrada "));


            }else{
            return ResponseEntity.badRequest().body(buildNotifyResponse("La direccion ya fue registrada"));
        }

        }else{
            return ResponseEntity.badRequest().body(buildNotifyResponse("id de orden invalido"));
        }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(buildNotifyResponse("*Ocurrio un Error* : La direccion no se pudo registrar en el sistema."));

        }
    }


    public ResponseEntity<Object> buscarDireccion(String longitud,String latitud){
        try{
            Direccion direccion;

            direccion = direccionRepository.findByLongitudAndLatitud(Float.parseFloat(longitud),Float.parseFloat(latitud));

            if(direccion == null) {

                DireccionResponse respuesta = new DireccionResponse();

                respuesta.setIdDireccion(String.valueOf(direccion.getIdDireccion()));
                respuesta.setIdOrden(String.valueOf(direccion.getIdOrden()));
                respuesta.setDireccion1(direccion.getDireccion1());
                respuesta.setDireccion2(direccion.getDireccion2());
                respuesta.setCiudad(direccion.getCiudad());
                respuesta.setCodigoPostal(direccion.getCodigoPostal());
                respuesta.setTipoDeDireccion(direccion.getTipoDeDireccion());
                respuesta.setPais(direccion.getPais());

                respuesta.setLatitud(direccion.getLatitud());
                respuesta.setLongitud(direccion.getLongitud());

                return ResponseEntity.ok(respuesta);
            }  else
                return ResponseEntity.badRequest().body(buildNotifyResponse("No se encontro la orden"));


        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("Ocurrio un error al buscar la Orden"));

        }
    }




    public ResponseEntity<Object>  buscarDireccionesDeUnaOrden(String id){
        try{

            List<Direccion> direccion;

            direccion = direccionRepository.findAllByIdOrden(Long.parseLong(id));

            if (direccion != null) {



                List<DireccionResponse> listResponses = new ArrayList<>();
                direccion.forEach( i-> {

                    DireccionResponse respuesta = new DireccionResponse();

                    respuesta.setIdDireccion(String.valueOf(i.getIdDireccion()));
                    respuesta.setIdOrden(String.valueOf(i.getIdOrden()));
                    respuesta.setDireccion1(i.getDireccion1());
                    respuesta.setDireccion2(i.getDireccion2());
                    respuesta.setCiudad(i.getCiudad());
                    respuesta.setCodigoPostal(i.getCodigoPostal());
                    respuesta.setTipoDeDireccion(i.getTipoDeDireccion());
                    respuesta.setPais(i.getPais());

                    respuesta.setLatitud(i.getLatitud());
                    respuesta.setLongitud(i.getLongitud());


                            listResponses.add(respuesta);
                        }
                );
                ListDireccionResponse response = new ListDireccionResponse();

                response.setDirecciones(listResponses);

                return ResponseEntity.ok(response);

            } else
                return ResponseEntity.badRequest().body(buildNotifyResponse("No se Encontraron Direcciones de esta Orden"));


        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("Ocurrio un Error al buscar las direcciones"));


        }
    }


    boolean verificarDireccion(float latitud,float longitud){

        if(direccionRepository.existsByLongitudAndLatitud(longitud,latitud)){
            return false;
        }

        return true;
    }


    public ResponseEntity<Object> eliminarDireccion(String id) {
        try {

            direccionRepository.deleteById(Long.parseLong(id));

            return ResponseEntity.ok().body(buildNotifyResponse("La direccion ha sido eliminada"));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(buildNotifyResponse("La direccion no pudo ser eliminada"));

        }
    }



    private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }
}
