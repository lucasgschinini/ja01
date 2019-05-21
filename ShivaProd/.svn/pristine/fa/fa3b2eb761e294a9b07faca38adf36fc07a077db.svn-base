package ar.com.telecom.shiva.persistencia.modelo.simple;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_COB_TRANSACCION")
public class ShvCobTransaccionSimple extends Modelo {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TRANSACCION")	
	private Integer idTransaccion;
	
	@Column(name="NUMERO_TRANSACCION")	
	private Integer numeroTransaccion;
	
	@Column(name="ID_OPERACION")
	private Long idOperacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")	
	private EstadoTransaccionEnum estadoProcesamiento;
	
	@Column(name="GRUPO")
	private Long grupo;
	
	@Column(name="NUMERO_TRANSACCION_DEPENDENCIA")	
	private Integer numeroTransaccionDependencia;
	
	public Integer getNumeroTransaccionDependencia() {
		return numeroTransaccionDependencia;
	}

	public void setNumeroTransaccionDependencia(Integer numeroTransaccionDependencia) {
		this.numeroTransaccionDependencia = numeroTransaccionDependencia;
	}
	
	
	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	
	public EstadoTransaccionEnum getEstadoProcesamiento() {
		return estadoProcesamiento;
	}

	public void setEstadoProcesamiento(EstadoTransaccionEnum estadoProcesamiento) {
		this.estadoProcesamiento = estadoProcesamiento;
	}

	public Integer getNumeroTransaccion() {
		return numeroTransaccion;
	}

	public void setNumeroTransaccion(Integer numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Long getGrupo() {
		return grupo;
	}

	public void setGrupo(Long grupo) {
		this.grupo = grupo;
	}
	
	/**
	 * Retorna el Numero de Transaccion con formato 0000000.00000
	 * @return
	 */
	public String getOperacionTransaccionFormateado() {
	
		String operacionTransaccionFormateado = Utilidad.rellenarCerosIzquierda(String.valueOf(this.getIdOperacion()), 7) + "." +
			Utilidad.rellenarCerosIzquierda(String.valueOf(getNumeroTransaccion()), 5);
		
		return operacionTransaccionFormateado;
	}

	
	
}
