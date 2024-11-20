package net.ausiasmarch.contante.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "asiento")
public class AsientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    private String descripcion;

    @NotNull
    @Size(min = 0, max = 255)
    private String comentarios;

    @NotNull
    @Digits(integer = 1, fraction = 0)
    private int inventariable;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime momentstamp;

    @NotNull
    private Long id_tipoasiento;

    @NotNull
    private Long id_usuario;

    @NotNull
    private Long id_periodo;

    public AsientoEntity() {
    }

    public AsientoEntity(String descripcion, String comentarios, int inventariable,
            LocalDateTime momentstamp) {
        this.descripcion = descripcion;
        this.comentarios = comentarios;
        this.inventariable = inventariable;
        this.momentstamp = momentstamp;

    }

    public AsientoEntity(String descripcion, String comentarios, int inventariable,
            LocalDateTime momentstamp, Long id_tipoasiento, Long id_usuario, Long id_periodo) {
        this.descripcion = descripcion;
        this.comentarios = comentarios;
        this.inventariable = inventariable;
        this.momentstamp = momentstamp;
        this.id_tipoasiento = id_tipoasiento;
        this.id_usuario = id_usuario;
        this.id_periodo = id_periodo;
    }

    public AsientoEntity(Long id, String descripcion, String comentarios, int inventariable,
            LocalDateTime momentstamp, Long id_tipoasiento, Long id_usuario, Long id_periodo) {
        this.id = id;
        this.descripcion = descripcion;
        this.comentarios = comentarios;
        this.inventariable = inventariable;
        this.momentstamp = momentstamp;
        this.id_tipoasiento = id_tipoasiento;
        this.id_usuario = id_usuario;
        this.id_periodo = id_periodo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getInventariable() {
        return inventariable;
    }

    public void setInventariable(int inventariable) {
        this.inventariable = inventariable;
    }

    public LocalDateTime getMomentstamp() {
        return momentstamp;
    }

    public void setMomentstamp(LocalDateTime momentstamp) {
        this.momentstamp = momentstamp;
    }

    public Long getId_tipoasiento() {
        return id_tipoasiento;
    }

    public void setId_tipoasiento(Long id_tipoasiento) {
        this.id_tipoasiento = id_tipoasiento;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(Long id_periodo) {
        this.id_periodo = id_periodo;
    }

}