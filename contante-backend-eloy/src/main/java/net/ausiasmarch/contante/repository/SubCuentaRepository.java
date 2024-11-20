package net.ausiasmarch.contante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.contante.entity.SubCuentaEntity;

public interface SubCuentaRepository extends JpaRepository<SubCuentaEntity, Long> {

    Page<SubCuentaEntity> findByDescripcionContaining(
        String filter1, Pageable oPageable);

}
