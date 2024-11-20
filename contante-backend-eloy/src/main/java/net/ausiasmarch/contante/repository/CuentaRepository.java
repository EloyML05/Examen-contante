package net.ausiasmarch.contante.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.contante.entity.CuentaEntity;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {

    Page<CuentaEntity> findByCodigoContainingOrDescripcionContaining (
           String filter1, String filter2, Pageable oPageable);
}

