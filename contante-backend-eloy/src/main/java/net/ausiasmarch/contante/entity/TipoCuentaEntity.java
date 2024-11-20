package net.ausiasmarch.contante.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tipocuenta")
public class TipoCuentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 255)
    private String descripcion;
    @NotNull
    @Digits(integer = 1, fraction = 0)
    private Long credito_o_debito;
    @NotNull
    @Size(min = 3, max = 255)
    private String comentarios;
    @NotNull
    @Digits(integer = 1, fraction = 0)
    private Long real_o_nominal;
  
    public TipoCuentaEntity() {
    }

    public TipoCuentaEntity(Long id, String descripcion, Long creditoOdebito, String comentarios, Long realOnominal) {
        this.id = id;
        this.descripcion = descripcion;
        this.credito_o_debito = creditoOdebito;
        this.comentarios = comentarios;
        this.real_o_nominal = realOnominal;
    }

    public TipoCuentaEntity(String descripcion, Long creditoOdebito, String comentarios, Long realOnominal) {
        this.descripcion = descripcion;
        this.credito_o_debito = creditoOdebito;
        this.comentarios = comentarios;
        this.real_o_nominal = realOnominal;
        
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

    public Long getCreditoOdebito() {
        return credito_o_debito;
    }

    public void setCreditoOdebito(Long creditoOdebito) {
        this.credito_o_debito = creditoOdebito;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Long getRealOnominal() {
        return real_o_nominal;
    }

    public void setRealOnominal(Long realOnominal) {
        this.real_o_nominal = realOnominal;
    }
}
