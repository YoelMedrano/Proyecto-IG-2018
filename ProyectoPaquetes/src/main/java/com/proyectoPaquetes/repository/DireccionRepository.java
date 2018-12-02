package com.proyectoPaquetes.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoPaquetes.model.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

}
