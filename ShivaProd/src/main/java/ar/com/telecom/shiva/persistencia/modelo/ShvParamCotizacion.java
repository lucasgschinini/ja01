package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCotizacionSapEnum;

@Entity
@Table(name = "SHV_PARAM_COTIZACION")
public class ShvParamCotizacion extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_PARAM_COTIZACION")
	@SequenceGenerator(name="SEQ_SHV_PARAM_COTIZACION", sequenceName="SEQ_SHV_PARAM_COTIZACION", allocationSize = 1)
	@Column(name="ID_COTIZACION")
	private Long idCotizacion; 
	
	@Enumerated(EnumType.STRING) 
	@Column(name = "TIPO_COTIZACION")
	private TipoCotizacionSapEnum tipoCotizacion;
	
	@Enumerated(EnumType.STRING) 
	@Column(name = "MONEDA_PROCEDENCIA")
	private MonedaEnum monedaProcedencia;
	
	@Enumerated(EnumType.STRING) 
	@Column(name = "MONEDA_DESTINO")
	private MonedaEnum monedaDestino;
	
	@Column(name = "FECHA_VALIDEZ_DESDE")
	private Date fechaValidezDesde;
	
	@Column(name = "TIPO_CAMBIO")
	private BigDecimal tipoDeCambio;

	@Column(name = "AUD_REQUERIMIENTO_ORIGEN")
	private String audRequerimientoOrigen = "SHV_BATCH";

	/**
	 * @return the idCotizacion
	 */
	public Long getIdCotizacion() {
		return idCotizacion;
	}

	/**
	 * @param idCotizacion the idCotizacion to set
	 */
	public void setIdCotizacion(Long idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	/**
	 * @return the tipoCotizacion
	 */
	public TipoCotizacionSapEnum getTipoCotizacion() {
		return tipoCotizacion;
	}

	/**
	 * @param tipoCotizacion the tipoCotizacion to set
	 */
	public void setTipoCotizacion(TipoCotizacionSapEnum tipoCotizacion) {
		this.tipoCotizacion = tipoCotizacion;
	}

	/**
	 * @return the monedaProcedencia
	 */
	public MonedaEnum getMonedaProcedencia() {
		return monedaProcedencia;
	}

	/**
	 * @param monedaProcedencia the monedaProcedencia to set
	 */
	public void setMonedaProcedencia(MonedaEnum monedaProcedencia) {
		this.monedaProcedencia = monedaProcedencia;
	}

	/**
	 * @return the monedaDestino
	 */
	public MonedaEnum getMonedaDestino() {
		return monedaDestino;
	}

	/**
	 * @param monedaDestino the monedaDestino to set
	 */
	public void setMonedaDestino(MonedaEnum monedaDestino) {
		this.monedaDestino = monedaDestino;
	}

	/**
	 * @return the fechaValidezDesde
	 */
	public Date getFechaValidezDesde() {
		return fechaValidezDesde;
	}

	/**
	 * @param fechaValidezDesde the fechaValidezDesde to set
	 */
	public void setFechaValidezDesde(Date fechaValidezDesde) {
		this.fechaValidezDesde = fechaValidezDesde;
	}

	/**
	 * @return the tipoDeCambio
	 */
	public BigDecimal getTipoDeCambio() {
		return tipoDeCambio;
	}

	/**
	 * @param tipoDeCambio the tipoDeCambio to set
	 */
	public void setTipoDeCambio(BigDecimal tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}
	
	@Override
	public String toString() {
	
		StringBuffer cadena = new StringBuffer();
		
		cadena.append("idCotizacion: ");
		cadena.append(String.valueOf(idCotizacion));
		cadena.append(" | tipoCotizacion: ");
		cadena.append(String.valueOf(tipoCotizacion != null ? tipoCotizacion.name() : null));
		cadena.append(" | monedaProcedencia: ");
		cadena.append(String.valueOf(monedaProcedencia != null ? monedaProcedencia.name() : null));
		cadena.append(" | monedaDestino: ");
		cadena.append(String.valueOf(monedaDestino != null ? monedaDestino.name() : null));
		cadena.append(" | fechaValidezDesde: ");
		cadena.append(String.valueOf(fechaValidezDesde));
		cadena.append(" | tipoDeCambio: ");
		cadena.append(String.valueOf(tipoDeCambio));
		cadena.append(" | audRequerimientoOrigen: ");
		cadena.append(String.valueOf(audRequerimientoOrigen));
		
		return cadena.toString();
	}
}
