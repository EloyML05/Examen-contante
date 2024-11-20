package net.ausiasmarch.contante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.contante.entity.TipoAsientoEntity;

public interface TipoAsientoRepository extends JpaRepository <TipoAsientoEntity, Long> {
    
    Page<TipoAsientoEntity> findByDescripcionContaining(
            String filter, Pageable oPageable);

}
