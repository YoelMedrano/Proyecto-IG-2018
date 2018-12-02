package com.proyectoPaquetes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoPaquetes.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {



}
