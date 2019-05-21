package ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateUtils;

import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;

@Entity
@Table(name = "SHV_COB_MED_PAG_REMANENTE")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
public class ShvCobMedioPagoRemanenteSinOperacion extends ShvCobMedioPagoMicSinOperacion {
	
	private static final long serialVersionUID = 1L;

	@Column(name="ID_CLIENTE_LEGADO")	 	
	private Long idClienteLegado;
	
	@Column(name="CUENTA_REMANENTE")	
	private Long cuentaRemanente;
	
	@Column(name="TIPO_REMANENTE")	
	private Long tipoRemanente;
	
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;
	
	@Override
	public Date getFechaValor() {
		return DateUtils.truncate(fechaAlta, Calendar.DATE);
	}
	
	@Override
	public String getReferencia() {
		return cuentaRemanente.toString() + tipoRemanente.toString();
	}	
	
	@Override
	public String getIdBusquedaRespuestaCobrador() {
		return TipoMedioPagoEnum.RT.name() + TipoRemanenteEnum.getEnumByCodigo(tipoRemanente).name() + cuentaRemanente;
	}
	
	/**
	 * @return the cuentaRemanente
	 */
	public Long getCuentaRemanente() {
		return cuentaRemanente;
	}

	/**
	 * @param cuentaRemanente the cuentaRemanente to set
	 */
	public void setCuentaRemanente(Long cuentaRemanente) {
		this.cuentaRemanente = cuentaRemanente;
	}

	public Long getTipoRemanente() {
		return tipoRemanente;
	}

	public void setTipoRemanente(Long tipoRemanente) {
		this.tipoRemanente = tipoRemanente;
	}

	/**
	 * @return the idClienteLegado
	 */
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
}
