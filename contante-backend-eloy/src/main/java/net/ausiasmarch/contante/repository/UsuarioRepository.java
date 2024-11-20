package net.ausiasmarch.contante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import net.ausiasmarch.contante.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Page<UsuarioEntity> findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
            String filter2, String filter3, String filter4, String filter5, Pageable oPageable);

    // Método para obtener entidades con IDs pares
    @Query("SELECT u FROM UsuarioEntity u WHERE Mod( u.id, 2 ) = 0")
    Page<UsuarioEntity> findUsuariosConIdPar(Pageable pageable);

    @Query("SELECT u FROM UsuarioEntity u WHERE Mod( u.id, 2 ) = 0 AND u.nombre LIKE %?1%")
    Page<UsuarioEntity> findUsuariosConIdParPorNombre(String filter, Pageable pageable);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM UsuarioEntity u WHERE u.nombre LIKE %?1%")
    void deleteByNombre(String nombre);
}
