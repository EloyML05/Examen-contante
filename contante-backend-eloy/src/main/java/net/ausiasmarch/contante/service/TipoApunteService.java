package net.ausiasmarch.contante.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.TipoApunteEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.TipoApunteRepository;

@Service
public class TipoApunteService implements ServiceInterface<TipoApunteEntity>{
    
    @Autowired
    private TipoApunteRepository oTipoApunteRepository;

    @Autowired
    private RandomService oRandomService;

    // Datos de la columna descripcion
    private String[] arrdescripcion = {
            "Compra de materiales", "Venta de producto", "Gasto de oficina", "Servicio prestado", "Compra de activos",
            "Ingreso por consultoría", "Compra de licencias", "Reembolso de gastos", "Publicidad en redes",
            "Venta de servicios",
            "Mantenimiento de equipo", "Cobro por formación", "Adquisición de suministros", "Ingresos de eventos",
            "Gastos de viaje", "Facturación de servicios", "Alquiler de oficina", "Ingreso por publicidad",
            "Servicios externos",
            "Venta de activos"
    };

    // Datos de la columna comentarios
    private String[] arrcomentarios = {
            "Pago con transferencia", "Ingreso en efectivo", "Compra de papelería", "Cobro a cliente",
            "Equipo de computo",
            "Proyecto ABC", "Software anual", "Viaje de negocios", "Campaña en redes", "Contrato mensual",
            "Reparación de PC", "Curso de verano", "Compra de tinta", "Charla en empresa", "Hotel y transporte",
            "Soporte técnico", "Pago mensual", "Banner web", "Asesoría fiscal", "Mobiliario antiguo"
    };


    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            TipoApunteEntity oTipoApunteEntity = new TipoApunteEntity();
            oTipoApunteEntity.setDescripcion(arrdescripcion[oRandomService.getRandomInt(0, arrdescripcion.length - 1)]);
            oTipoApunteEntity.setComentarios(arrcomentarios[oRandomService.getRandomInt(0, arrcomentarios.length - 1)]);
            oTipoApunteRepository.save(oTipoApunteEntity);
        }
        return oTipoApunteRepository.count();
    }

    public Page<TipoApunteEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (filter.isPresent()) {
            return oTipoApunteRepository
                    .findByDescripcionContainingOrComentariosContaining(
                            filter.get(), filter.get(), oPageable);
        } else {
            return oTipoApunteRepository.findAll(oPageable);
        }
    }

    public TipoApunteEntity get(Long id) {
        return oTipoApunteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de Apunte no encontrado"));
    }

    public Long count() {
        return oTipoApunteRepository.count();
    }

    public Long delete(Long id) {
        oTipoApunteRepository.deleteById(id);
        return 1L;
    }

    public TipoApunteEntity create(TipoApunteEntity oTipoApunteEntity) {
        return oTipoApunteRepository.save(oTipoApunteEntity);
    }

    public TipoApunteEntity update(TipoApunteEntity oTipoApunteEntity) {
        TipoApunteEntity oTipoApunteEntityFromDatabase = oTipoApunteRepository.findById(oTipoApunteEntity.getId()).get();
        if (oTipoApunteEntity.getDescripcion() != null) {
            oTipoApunteEntityFromDatabase.setDescripcion(oTipoApunteEntity.getDescripcion());
        }
        if (oTipoApunteEntity.getComentarios() != null) {
            oTipoApunteEntityFromDatabase.setComentarios(oTipoApunteEntity.getComentarios());
        }
        return oTipoApunteRepository.save(oTipoApunteEntityFromDatabase);
    }

    public Long deleteAll() {
        oTipoApunteRepository.deleteAll();
        return this.count();
    }

}
