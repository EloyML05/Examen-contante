package net.ausiasmarch.contante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.contante.entity.AsientoEntity;

public interface AsientoRepository extends JpaRepository<AsientoEntity, Long> {

  Page<AsientoEntity> findByDescripcionContainingOrComentariosContaining(
      String filter2, String filter3, Pageable oPageable);

}
