package com.spring.universidad.backend.modelo;

import com.spring.universidad.backend.enumeradores.Pizarron;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 *
 * @author nano
 */
@Entity
@Table(name ="aulas")
public class Aula implements Serializable{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "numero_aula",nullable = false)
  private Integer nroAula;
  
  @Column(name = "medidas_mtsxmts")
  private String medidas;
  
  @Column(name = "pupitres")
  private Integer cantidadPupitres;
  
  @Column(name = "tipo_pizarron")
  @Enumerated(EnumType.STRING)  
  private Pizarron pizarron;
  
  @Column(name = "fecha_alta")  
  private LocalDateTime fechaAlta;
  
  @Column(name = "fecha_modificacion")
  private LocalDateTime fechaModificacion;
  
  @ManyToOne(optional = true,cascade = { CascadeType.PERSIST,CascadeType.MERGE})
   @JoinColumn(name = "pabellon_id",foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
  private Pabellon pabellon;
  
  public Aula(){}

  public Aula(Integer id, Integer nroAula, String medidas, Integer cantidadPupitres, Pizarron pizarron, LocalDateTime fechaAlta, LocalDateTime fechaModificacion) {
    this.id = id;
    this.nroAula = nroAula;
    this.medidas = medidas;
    this.cantidadPupitres = cantidadPupitres;
    this.pizarron = pizarron;
    this.fechaAlta = fechaAlta;
    this.fechaModificacion = fechaModificacion;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getNroAula() {
    return nroAula;
  }

  public void setNroAula(Integer nroAula) {
    this.nroAula = nroAula;
  }

  public String getMedidas() {
    return medidas;
  }

  public void setMedidas(String medidas) {
    this.medidas = medidas;
  }

  public Integer getCantidadPupitres() {
    return cantidadPupitres;
  }

  public void setCantidadPupitres(Integer cantidadPupitres) {
    this.cantidadPupitres = cantidadPupitres;
  }

  public Pizarron getPizarron() {
    return pizarron;
  }

  public void setPizarron(Pizarron pizarron) {
    this.pizarron = pizarron;
  }

  public LocalDateTime getFechaAlta() {
    return fechaAlta;
  }

  public void setFechaAlta(LocalDateTime fechaAlta) {
    this.fechaAlta = fechaAlta;
  }

  public LocalDateTime getFechaModificacion() {
    return fechaModificacion;
  }

  public void setFechaModificacion(LocalDateTime fechaModificacion) {
    this.fechaModificacion = fechaModificacion;
  }

  @Override
  public String toString() {
    return "Aula{" + "id=" + id + ", nroAula=" + nroAula + ", medidas=" + medidas + ", cantidadPupitres=" + cantidadPupitres + ", pizarron=" + pizarron + ", fechaAlta=" + fechaAlta + ", fechaModificacion=" + fechaModificacion + '}';
  }

  public Pabellon getPabellon() {
    return pabellon;
  }

  public void setPabellon(Pabellon pabellon) {
    this.pabellon = pabellon;
  }

  @PrePersist
  public void antesPersistir(){
    this.fechaAlta=LocalDateTime.now();
  }
  
  @PreUpdate
   public void antesUpdate(){
    this.fechaModificacion=LocalDateTime.now();
  }
  
  @Override
  public int hashCode() {
    int hash = 3;
    hash = 37 * hash + Objects.hashCode(this.id);
    hash = 37 * hash + Objects.hashCode(this.nroAula);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Aula other = (Aula) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return Objects.equals(this.nroAula, other.nroAula);
  }
  
  
}
