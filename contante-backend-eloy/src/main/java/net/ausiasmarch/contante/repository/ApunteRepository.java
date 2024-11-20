package net.ausiasmarch.contante.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.contante.entity.ApunteEntity;

public interface  ApunteRepository extends JpaRepository<ApunteEntity, Long> {

    Page<ApunteEntity> findByDescripcionContainingOrComentariosContaining (
           String filter1, String filter2, Pageable oPageable);

}

