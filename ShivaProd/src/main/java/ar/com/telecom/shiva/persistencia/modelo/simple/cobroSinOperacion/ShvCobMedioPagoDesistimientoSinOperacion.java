package ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateUtils;

@Entity
@Table(name = "SHV_COB_MED_PAG_DESISTIMIENTO")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
public class ShvCobMedioPagoDesistimientoSinOperacion extends ShvCobMedioPagoUsuarioSinOperacion {
	
	private static final long serialVersionUID = 1L;

	@Column(name="NUMERO_ACTA")	 	
	private String numeroActa;

	@Column(name="FECHA")	 	
	private Date fecha;

	@Override
	public Date getFechaValor() {
		return DateUtils.truncate(fecha, Calendar.DATE);
	}
	
	@Override
	public String getReferencia() {
		return numeroActa;
	}	
	
	@Override
	public String getIdBusquedaRespuestaCobrador() {
		return this.getIdMedioPago().toString();
	}

	/**
	 * @return the numeroActa
	 */
	public String getNumeroActa() {
		return numeroActa;
	}

	/**
	 * @param numeroActa the numeroActa to set
	 */
	public void setNumeroActa(String numeroActa) {
		this.numeroActa = numeroActa;
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
}
