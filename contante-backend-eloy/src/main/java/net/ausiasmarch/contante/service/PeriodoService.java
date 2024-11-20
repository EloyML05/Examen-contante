package net.ausiasmarch.contante.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.PeriodoEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.PeriodoRepository;


@Service
public class PeriodoService implements ServiceInterface<PeriodoEntity> {
    
    @Autowired
    PeriodoRepository oPeriodoRepository;

    @Autowired
    RandomService oRandomService;

    // Datos de la columna anyo
    private int[] arrAnyo = {
        2021, 2022, 2023, 2024, 2025,
        2021, 2022, 2023, 2024, 2025,
        2021, 2022, 2023, 2024, 2025,
        2021, 2022, 2023, 2024, 2025
    };

    // Datos de la columna descripcion
    private String[] arrDescripcion = {
        "Primer semestre", "Segundo semestre", "Evaluación anual", "Revisión presupuestaria", "Planificación de inversión",
        "Ajuste de personal", "Informe financiero", "Análisis de mercado", "Optimización de recursos", "Actualización de software",
        "Auditoría interna", "Control de calidad", "Balance de pérdidas y ganancias", "Revisión de contratos", "Proyección de ventas",
        "Iniciativas de sostenibilidad", "Capacitación del equipo", "Actualización de políticas", "Expansión de operaciones", "Evaluación de desempeño"
    };

    // Datos de la columna comentarios
    private String[] arrComentarios = {
        "Proceso inicial", "Actualización de políticas", "Cumplimiento de normativas", "Reducción de gastos",
        "Ampliación de infraestructura", "Evaluación de procesos", "Revisión de proyectos", "Objetivos estratégicos",
        "Nueva implementación", "Revisión de software", "Verificación anual", "Revisión semestral",
        "Ajuste de presupuesto", "Inversión en activos", "Revisión fiscal", "Capacitación de personal",
        "Contratación temporal", "Evaluación de resultados", "Nuevo proyecto", "Ajustes estructurales"
    };

    // Datos de la columna cerrado (booleano como BigDecimal 1 para true, 0 para false)
    private boolean[] arrCerrado = {
        true, false, true, false, true,
        false, true, false, true, false,
        true, false, true, false, true,
        false, true, false, true, false
    };

    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            PeriodoEntity periodoEntity = new PeriodoEntity();
            
            // Setear los valores aleatorios para cada columna
            periodoEntity.setAnyo(arrAnyo[oRandomService.getRandomInt(0, arrAnyo.length - 1)]);
            periodoEntity.setDescripcion(arrDescripcion[oRandomService.getRandomInt(0, arrDescripcion.length - 1)]);
            periodoEntity.setComentarios(arrComentarios[oRandomService.getRandomInt(0, arrComentarios.length - 1)]);
            periodoEntity.setCerrado(arrCerrado[oRandomService.getRandomInt(0, arrCerrado.length - 1)]);
            
            // Guardar el objeto en el repositorio
            oPeriodoRepository.save(periodoEntity);
        }
        return oPeriodoRepository.count();
    }

        public Page<PeriodoEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (filter.isPresent()) {
            return oPeriodoRepository
                   .findByDescripcionContainingOrComentariosContaining(
                            filter.get(), filter.get(), oPageable);
        } else {
            return oPeriodoRepository.findAll(oPageable);
        }
    }

        
    public PeriodoEntity get(Long id) {
        return oPeriodoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo no encontrado"));
        
    }

    public Long count() {
        return oPeriodoRepository.count();
    }

    public Long delete(Long id) {
        oPeriodoRepository.deleteById(id);
        return 1L;
    }

    
    public PeriodoEntity create(PeriodoEntity oPeriodoEntity) {
        return oPeriodoRepository.save(oPeriodoEntity);
    }

    public PeriodoEntity update(PeriodoEntity oPeriodoEntity) {
        PeriodoEntity oPeriodoEntityFromDatabase = oPeriodoRepository.findById(oPeriodoEntity.getId()).orElseThrow(() -> new RuntimeException("Periodo no encontrado"));
    
        // Actualizar campos solo si el valor nuevo no es nulo
        if (oPeriodoEntity.getAnyo() != 0) { // asumiendo que 'anyo' no puede ser 0 en la actualización
            oPeriodoEntityFromDatabase.setAnyo(oPeriodoEntity.getAnyo());
        }
        if (oPeriodoEntity.getDescripcion() != null) {
            oPeriodoEntityFromDatabase.setDescripcion(oPeriodoEntity.getDescripcion());
        }
        if (oPeriodoEntity.getComentarios() != null) {
            oPeriodoEntityFromDatabase.setComentarios(oPeriodoEntity.getComentarios());
        }
        oPeriodoEntityFromDatabase.setCerrado(oPeriodoEntity.isCerrado()); // actualizar siempre ya que es boolean
    
        return oPeriodoRepository.save(oPeriodoEntityFromDatabase);
    }

    public Long deleteAll() {
        oPeriodoRepository.deleteAll();
        return this.count();
    }
    
}
