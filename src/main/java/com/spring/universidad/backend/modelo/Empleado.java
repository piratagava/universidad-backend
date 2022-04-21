package com.spring.universidad.backend.modelo;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.spring.universidad.backend.enumeradores.TipoEmpleado;

@Entity
@Table(name = "empleados")
//hace join con nuestra tabla padre
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona {

	private BigDecimal sueldo;

	@Column(name = "tipo_empleado")
	@Enumerated(EnumType.STRING)
	private TipoEmpleado tipoEmpleado;

	@OneToOne(optional = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
	private Pabellon pabellon;

	public Empleado() {
	}

	public Empleado(BigDecimal sueldo, TipoEmpleado tipoEmpleado, Integer id, String nombre, String apellido,
			String dni, Direccion direccion) {
		super(id, nombre, apellido, dni, direccion);
		this.sueldo = sueldo;
		this.tipoEmpleado = tipoEmpleado;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	public TipoEmpleado getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public Pabellon getPabellon() {
		return pabellon;
	}

	public void setPabellon(Pabellon pabellon) {
		this.pabellon = pabellon;
	}

	@Override
	public String toString() {
		return super.toString() + 
				"\tEmpleado [sueldo=" + sueldo + ", tipoEmpleado=" + tipoEmpleado + ", pabellon=" + pabellon + "]";
	}

}
