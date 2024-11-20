package net.ausiasmarch.contante.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.CuentaEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.CuentaRepository;

@Service
public class CuentaService implements ServiceInterface<CuentaEntity> {

    @Autowired
    CuentaRepository oCuentaRepository;

    @Autowired
    RandomService oRandomService;

    private String[] arrCodigo = { "1234", "5678", "9012", "3456", "7890" };

    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            CuentaEntity oCuentaEntity = new CuentaEntity();
            oCuentaEntity.setCodigo(arrCodigo[oRandomService.getRandomInt(0, arrCodigo.length - 1)]);
            oCuentaRepository.save(oCuentaEntity);
        }
        return oCuentaRepository.count();
    }

    public Page<CuentaEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oCuentaRepository
                    .findByCodigoContainingOrDescripcionContaining(filter.get(), filter.get(), oPageable);
        } else {
            return oCuentaRepository.findAll(oPageable);
        }
    }

    public CuentaEntity get(Long id) {
        return oCuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
    }

    public Long count() {
        return oCuentaRepository.count();
    }

    public Long delete(Long id) {
        oCuentaRepository.deleteById(id);
        return 1L;
    }

    public CuentaEntity create(CuentaEntity oCuentaEntity) {
        return oCuentaRepository.save(oCuentaEntity);
    }

    public CuentaEntity update(CuentaEntity oCuentaEntity) {
        CuentaEntity oCuentaEntityFromDatabase = oCuentaRepository.findById(oCuentaEntity.getId()).get();
        if (oCuentaEntity.getCodigo() != null) {
            oCuentaEntityFromDatabase.setCodigo(oCuentaEntity.getCodigo());
        }
        if (oCuentaEntity.getDescripcion() != null) {
            oCuentaEntityFromDatabase.setDescripcion(oCuentaEntity.getDescripcion());
        }
        if (oCuentaEntity.getId_tipocuenta() != null) {
            oCuentaEntityFromDatabase.setId_tipocuenta(oCuentaEntity.getId_tipocuenta());
        }
        return oCuentaRepository.save(oCuentaEntityFromDatabase);
    }

    public Long deleteAll() {
        oCuentaRepository.deleteAll();
        return this.count();
    }

}
