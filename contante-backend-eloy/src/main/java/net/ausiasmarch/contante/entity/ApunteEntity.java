package net.ausiasmarch.contante.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "apunte")
public class ApunteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Digits(integer = 4, fraction = 4)
    private BigDecimal debe;
    @NotNull
    @Digits(integer = 4, fraction = 4)
    private BigDecimal haber;
    @NotNull
    @Size(min = 3, max = 255)
    private String descripcion;
    @NotNull
    @Size(min = 3, max = 255)
    private String comentarios;
    @NotNull
      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")    
    private LocalDateTime momentstamp;
    @NotNull
    @Max(value = 128)
    private Long orden;
    @NotNull
    private Long id_asiento;
    @NotNull
    private Long id_subcuenta;
    @NotNull
    private Long id_tipoapunte;

    public ApunteEntity() {
    }

    public ApunteEntity(Long id, BigDecimal debe, BigDecimal haber, String descripcion, String comentarios,
            LocalDateTime momentstamp,
            long orden, long id_asiento, long id_subcuenta, long id_tipoapunte) {
        this.id = id;
        this.debe = debe;
        this.haber = haber;
        this.descripcion = descripcion;
        this.comentarios = comentarios;
        this.momentstamp = momentstamp;
        this.orden = orden;
        this.id_asiento = id_asiento;
        this.id_subcuenta = id_subcuenta;
        this.id_tipoapunte = id_tipoapunte;
    }

    public ApunteEntity(BigDecimal debe, BigDecimal haber, String descripcion, String comentarios, LocalDateTime momentstamp,
            long orden, long id_asiento, long id_subcuenta, long idtipo) {
        this.debe = debe;
        this.haber = haber;
        this.descripcion = descripcion;
        this.comentarios = comentarios;
        this.momentstamp = momentstamp;
        this.orden = orden;
        this.id_asiento = id_asiento;
        this.id_subcuenta = id_subcuenta;
        this.id_tipoapunte = idtipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
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

    public LocalDateTime getMomentstamp() {
        return momentstamp;
    }

    public void setMomentstamp(LocalDateTime momentstamp) {
        this.momentstamp = momentstamp;
    }

    public long getOrden() {
        return orden;
    }

    public void setOrden(long orden) {
        this.orden = orden;
    }

    public long getId_asiento() {
        return id_asiento;
    }

    public void setId_asiento(long id_asiento) {
        this.id_asiento = id_asiento;
    }

    public long getId_subcuenta() {
        return id_subcuenta;
    }

    public void setId_subcuenta(long id_subcuenta) {
        this.id_subcuenta = id_subcuenta;
    }

    public long getId_tipoapunte() {
        return id_tipoapunte;
    }

    public void setId_tipoapunte(long id_tipoapunte) {
        this.id_tipoapunte = id_tipoapunte;
    }

}
