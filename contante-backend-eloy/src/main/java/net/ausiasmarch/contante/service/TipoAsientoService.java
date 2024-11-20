package net.ausiasmarch.contante.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.TipoAsientoEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.TipoAsientoRepository;

@Service
public class TipoAsientoService implements ServiceInterfaceTipoAsiento {
    
    @Autowired
    private TipoAsientoRepository oTipoAsientoRepository;

    @Autowired
    RandomService oRandomService;

    private String[] arrDescripciones = {"Asiento simple", "Asiento doble", "Asiento triple", "Asiento cuadruple", "Asiento quinuple",
        "Asiento sexuple", "Asiento septuple", "Asiento octuple", "Asiento novuple", "Asiento decuple", "Asiento decuple", "Asiento decuple",
        "Asiento decuple", "Asiento decuple", "Asiento decuple", "Asiento decuple"};

    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            TipoAsientoEntity oTipoAsientoEntity = new TipoAsientoEntity();
            oTipoAsientoEntity.setDescripcion(arrDescripciones[oRandomService.getRandomInt(0, arrDescripciones.length - 1)]);
            oTipoAsientoRepository.save(oTipoAsientoEntity);
        }
        return oTipoAsientoRepository.count();
    }

    public Page<TipoAsientoEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oTipoAsientoRepository
                    .findByDescripcionContaining(
                            filter.get(),oPageable);
        } else {
            return oTipoAsientoRepository.findAll(oPageable);
        }
    }

     public TipoAsientoEntity get(Long id) {
        return oTipoAsientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoAsiento no encontrado"));
        // return oUsuarioRepository.findById(id).get();
    }

    public Long count() {
        return oTipoAsientoRepository.count();
    }

    public Long delete(Long id) {
        oTipoAsientoRepository.deleteById(id);
        return 1L;
    }

    public TipoAsientoEntity create(TipoAsientoEntity oTipoAsientoEntity) {
        return oTipoAsientoRepository.save(oTipoAsientoEntity);
    }

    public TipoAsientoEntity update(TipoAsientoEntity oTipoAsientoEntity) {
        TipoAsientoEntity oTipoAsientoEntityFromDatabase = oTipoAsientoRepository.findById(oTipoAsientoEntity.getId()).get();
        if (oTipoAsientoEntity.getDescripcion() != null) {
            oTipoAsientoEntityFromDatabase.setDescripcion(oTipoAsientoEntity.getDescripcion());
        }
        return oTipoAsientoRepository.save(oTipoAsientoEntityFromDatabase);
    }

    public Long deleteAll() {
        oTipoAsientoRepository.deleteAll();
        return this.count();
    }
}
