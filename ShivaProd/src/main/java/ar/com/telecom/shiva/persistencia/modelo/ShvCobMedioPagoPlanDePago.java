package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateUtils;

@Entity
@Table(name = "SHV_COB_MED_PAG_PLAN_PAGO")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
public class ShvCobMedioPagoPlanDePago extends ShvCobMedioPagoUsuario {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="FECHA")
	private Date fecha;
	
	@Column(name="TIPO_CAMBIO")
	private BigDecimal tipoCambio;

	@Override
	public Date getFechaValor() {
		return DateUtils.truncate(fecha, Calendar.DATE);
	}
	
	@Override
	public String getReferencia() {
		return new String("");
	}	
	
	@Override
	public String getIdBusquedaRespuestaCobrador() {
		return this.getIdMedioPago().toString();
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the tipoCambio
	 */
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	
}
