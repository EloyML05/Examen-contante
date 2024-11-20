package net.ausiasmarch.contante.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "subcuenta")
public class SubCuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private int codigo;
    @NotNull
    @Size(min = 3, max = 255)
    private String descripcion;
    @NotNull
    private BigDecimal id_cuenta;
    @NotNull
      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")    
    private LocalDateTime momentstamp;
    @NotNull

    public SubCuentaEntity() {
    }

    public SubCuentaEntity(long id, int codigo, String descripcion,BigDecimal id_cuenta, LocalDateTime momentstamp) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.id_cuenta = id_cuenta;
        this.momentstamp = momentstamp;
    }

    public SubCuentaEntity( int codigo, String descripcion, BigDecimal id_cuenta, LocalDateTime momentstamp) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.id_cuenta = id_cuenta;
        this.momentstamp = momentstamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(BigDecimal id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public LocalDateTime getMomentstamp() {
        return momentstamp;
    }

    public void setMomentstamp(LocalDateTime momentstamp) {
        this.momentstamp = momentstamp;
    }

}
