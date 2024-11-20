package net.ausiasmarch.contante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.contante.entity.TipoCuentaEntity;

public interface TipoCuentaRepository extends JpaRepository<TipoCuentaEntity, Long> {

    Page<TipoCuentaEntity> findByDescripcionContainingOrCreditoOdebitoContainingOrComentariosContainingOrRealOnominalContaining(
            String filter2, String filter3, String filter4, String filter5, Pageable oPageable);


}
