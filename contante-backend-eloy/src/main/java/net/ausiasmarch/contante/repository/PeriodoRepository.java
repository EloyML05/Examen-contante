package net.ausiasmarch.contante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import net.ausiasmarch.contante.entity.PeriodoEntity;

public interface PeriodoRepository extends JpaRepository<PeriodoEntity, Long> {
     Page<PeriodoEntity> findByDescripcionContainingOrComentariosContaining (
           String filter1, String filter2, Pageable oPageable);
}
