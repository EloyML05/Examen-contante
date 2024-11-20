package net.ausiasmarch.contante.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.ausiasmarch.contante.entity.TipoAsientoEntity;


public interface ServiceInterfaceTipoAsiento {

    public Long randomCreate(Long cantidad);

    public Page<TipoAsientoEntity> getPage(Pageable oPageable, Optional<String> filter);

    public TipoAsientoEntity get(Long id);

    public Long count();

    public Long delete(Long id);

    public TipoAsientoEntity create(TipoAsientoEntity oTipoAsientoEntity);

    public TipoAsientoEntity update(TipoAsientoEntity oTipoAsientoEntity);

    public Long deleteAll();

}

