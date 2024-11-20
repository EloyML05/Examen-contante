package net.ausiasmarch.contante.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.TipousuarioEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.TipousuarioRepository;

@Service
public class TipousuarioService implements ServiceInterface<TipousuarioEntity> {

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    RandomService oRandomService;


    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            TipousuarioEntity oTipousuarioEntity = new TipousuarioEntity();
            oTipousuarioEntity.setDescripcion("Tipo usuario " + i + oRandomService.getRandomInt(999, 9999));
            
            oTipousuarioRepository.save(oTipousuarioEntity);
        }
        return oTipousuarioRepository.count();
    }

    public Page<TipousuarioEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oTipousuarioRepository
                    .findByDescripcionContaining(filter.get(), oPageable);
        } else {
            return oTipousuarioRepository.findAll(oPageable);
        }
    }

    public TipousuarioEntity get(Long id) {
        return oTipousuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        // return oTipousuarioRepository.findById(id).get();
    }

    public Long count() {
        return oTipousuarioRepository.count();
    }

    public Long delete(Long id) {
        oTipousuarioRepository.deleteById(id);
        return 1L;
    }

    public TipousuarioEntity create(TipousuarioEntity oTipousuarioEntity) {
        return oTipousuarioRepository.save(oTipousuarioEntity);
    }

    public TipousuarioEntity update(TipousuarioEntity oTipousuarioEntity) {
        TipousuarioEntity oTipousuarioEntityFromDatabase = oTipousuarioRepository.findById(oTipousuarioEntity.getId())
                .get();
        if (oTipousuarioEntity.getDescripcion() != null) {
            oTipousuarioEntityFromDatabase.setDescripcion(oTipousuarioEntity.getDescripcion());
        }
        return oTipousuarioRepository.save(oTipousuarioEntityFromDatabase);
    }

    public Long deleteAll() {
        oTipousuarioRepository.deleteAll();
        return this.count();
    }

}