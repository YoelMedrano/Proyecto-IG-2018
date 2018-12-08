package com.proyectoPaquetes.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoPaquetes.model.Direccion;
import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {


    Direccion findByLongitudAndLatitud(float longitud,float latitud);

    boolean  existsByLongitudAndLatitud(float longitud,float latitud);

    List<Direccion> findAllByIdOrden(Long idCliente);

}
