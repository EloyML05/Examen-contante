package net.ausiasmarch.contante.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.SubCuentaEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.SubCuentaRepository;

@Service
public class SubCuentaService implements ServiceInterface<SubCuentaEntity> {
    
    @Autowired
    private SubCuentaRepository oSubCuentaRepository;

    @Autowired
    private RandomService oRandomService;

// Datos de la columna codigo
private int[] arrcodigo = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
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
// Datos de la columna id_cuenta
private BigDecimal[] arrid_cuenta = {
    new BigDecimal("200.0000"), new BigDecimal("0.0000"), new BigDecimal("50.0000"), new BigDecimal("0.0000"),
    new BigDecimal("120.0000"), new BigDecimal("0.0000"), new BigDecimal("300.0000"), new BigDecimal("0.0000"),
    new BigDecimal("150.0000"), new BigDecimal("0.0000"), new BigDecimal("75.0000"), new BigDecimal("0.0000"),
    new BigDecimal("200.0000"), new BigDecimal("0.0000"), new BigDecimal("90.0000"), new BigDecimal("0.0000"),
    new BigDecimal("175.0000"), new BigDecimal("0.0000"), new BigDecimal("80.0000"), new BigDecimal("0.0000")
};

 // Datos de la columna momentstamp
LocalDateTime[] arrdateTimes = {
            LocalDateTime.of(2023, 1, 1, 10, 0),
            LocalDateTime.of(2023, 2, 2, 12, 30),
            LocalDateTime.of(2023, 3, 3, 14, 15),
            LocalDateTime.of(2023, 4, 4, 16, 45),
            LocalDateTime.of(2023, 5, 5, 18, 0),
            LocalDateTime.of(2023, 6, 6, 20, 30),
            LocalDateTime.of(2023, 7, 7, 22, 15),
            LocalDateTime.of(2023, 8, 8, 9, 45),
            LocalDateTime.of(2023, 9, 9, 11, 0),
            LocalDateTime.of(2023, 10, 10, 13, 30),
            LocalDateTime.of(2023, 11, 11, 15, 15),
            LocalDateTime.of(2023, 12, 12, 17, 45),
            LocalDateTime.of(2024, 1, 13, 19, 0),
            LocalDateTime.of(2024, 2, 14, 21, 30),
            LocalDateTime.of(2024, 3, 15, 23, 15),
            LocalDateTime.of(2024, 4, 16, 8, 45),
            LocalDateTime.of(2024, 5, 17, 10, 0),
            LocalDateTime.of(2024, 6, 18, 12, 30),
            LocalDateTime.of(2024, 7, 19, 14, 15),
            LocalDateTime.of(2024, 8, 20, 16, 45)
        };


public Long randomCreate(Long cantidad) {
    for (int i = 0; i < cantidad; i++) {
        SubCuentaEntity oSubCuentaEntity = new SubCuentaEntity();
        oSubCuentaEntity.setCodigo(arrcodigo[oRandomService.getRandomInt(0, arrcodigo.length - 1)]);
        oSubCuentaEntity.setDescripcion(arrdescripcion[oRandomService.getRandomInt(0, arrdescripcion.length - 1)]);
        oSubCuentaEntity.setId_cuenta(arrid_cuenta[oRandomService.getRandomInt(0, arrid_cuenta.length - 1)]);
        oSubCuentaEntity.setMomentstamp(arrdateTimes[oRandomService.getRandomInt(0, arrdateTimes.length - 1)]);
        
        oSubCuentaRepository.save(oSubCuentaEntity);
    }
    return oSubCuentaRepository.count();
}

public Page<SubCuentaEntity> getPage(Pageable oPageable, Optional<String> filter) {
    if (filter.isPresent()) {
        return oSubCuentaRepository
                .findByDescripcionContaining(
                        filter.get(), oPageable);
    } else {
        return oSubCuentaRepository.findAll(oPageable);
    }
}

public SubCuentaEntity get(Long id) {
    return oSubCuentaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SubCuenta no encontrado"));
}

public Long count() {
    return oSubCuentaRepository.count();
}

public Long delete(Long id) {
    oSubCuentaRepository.deleteById(id);
    return 1L;
}

public SubCuentaEntity create(SubCuentaEntity oSubCuentaEntity) {
    return oSubCuentaRepository.save(oSubCuentaEntity);
}

public SubCuentaEntity update(SubCuentaEntity oSubCuentaEntity) {
    SubCuentaEntity oSubCuentaEntityFromDatabase = oSubCuentaRepository.findById(oSubCuentaEntity.getId()).get();
    if (oSubCuentaEntity.getCodigo() != 0) {
        oSubCuentaEntityFromDatabase.setCodigo(oSubCuentaEntity.getCodigo());
    }
    if (oSubCuentaEntity.getDescripcion() != null) {
        oSubCuentaEntityFromDatabase.setDescripcion(oSubCuentaEntity.getDescripcion());
    }
    if (oSubCuentaEntity.getId_cuenta() != null) {
        oSubCuentaEntityFromDatabase.setId_cuenta(oSubCuentaEntity.getId_cuenta());
    }
    if (oSubCuentaEntity.getMomentstamp() != null) {
        oSubCuentaEntityFromDatabase.setMomentstamp(oSubCuentaEntity.getMomentstamp());
    }
    return oSubCuentaRepository.save(oSubCuentaEntityFromDatabase);
}

public Long deleteAll() {
    oSubCuentaRepository.deleteAll();
    return this.count();
}

}
