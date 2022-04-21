package com.spring.universidad.backend.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name ="pabellones")
public class Pabellon implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer Id;
  
  @Column(name = "metros_cuadrados")
  private Double mts2;
  
  @Column(name = "nombre_pabellon",unique = true , nullable = false)  
  private String nombre;
  
  //ya sabe que tiene que agregar los atributos de la clase de Direccion como parte de la tabla y no 
  //como una tabla aparte.
  @Embedded
  /*Para modificar la tabla con las siguientes anotaciones */
  @AttributeOverrides({
    @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal")),
    @AttributeOverride(name="dpto", column=@Column(name = "departamento"))
  })
  private Direccion direccion;
  
  @Column(name = "fecha_alta")
  private LocalDateTime fechaAlta;
  
  
  @Column(name = "fecha_modificacion")  
  private LocalDateTime fechaModificacion;

  //con set nos permite tener un unico objeto dentro de nuestra coleecion set y no tenemos duplicados como con list o lista
  @OneToMany(mappedBy = "pabellon", fetch = FetchType.LAZY)
  private Set<Aula> aluas;
          
  public Pabellon() {
  }

  public Pabellon(Integer Id, Double mts2, String nombre, Direccion direccion, LocalDateTime fechaAlta, LocalDateTime fechaUltimaModificacion) {
    this.Id = Id;
    this.mts2 = mts2;
    this.nombre = nombre;
    this.direccion = direccion;
    this.fechaAlta = fechaAlta;
    this.fechaModificacion = fechaUltimaModificacion;
  }

  public Integer getId() {
    return Id;
  }

  public void setId(Integer Id) {
    this.Id = Id;
  }

  public Double getMts2() {
    return mts2;
  }

  public void setMts2(Double mts2) {
    this.mts2 = mts2;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Direccion getDireccion() {
    return direccion;
  }

  public void setDireccion(Direccion direccion) {
    this.direccion = direccion;
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

  public Set<Aula> getAluas() {
    return aluas;
  }

  public void setAluas(Set<Aula> aluas) {
    this.aluas = aluas;
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
  public String toString() {
    return "Pabellon{" + "Id=" + Id + ", mts2=" + mts2 + ", nombre=" + nombre + ", direccion=" + direccion + ", fechaAlta=" + fechaAlta + ", fechaUltimaModificacion=" + fechaModificacion + '}';
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 23 * hash + Objects.hashCode(this.Id);
    hash = 23 * hash + Objects.hashCode(this.nombre);
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
    final Pabellon other = (Pabellon) obj;
    if (!Objects.equals(this.nombre, other.nombre)) {
      return false;
    }
    return Objects.equals(this.Id, other.Id);
  }

}
