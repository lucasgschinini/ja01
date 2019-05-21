package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateUtils;

import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamProvincia;


@Entity
@Table(name = "SHV_COB_MED_PAG_COMP_IIBB")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
public class ShvCobMedioPagoCompensacionIIBB extends ShvCobMedioPagoUsuario {
	
	private static final long serialVersionUID = 1L;

	@Column(name="NUMERO_COMPENSACION")	 	
	private String numeroCompensacion;

	@Column(name="FECHA")	 	
	private Date fecha;
	
	@Column(name="TIPO_CAMBIO")
	private BigDecimal tipoCambio;  
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA")
	private ShvParamProvincia provincia;
	
	
	@Override
	public Date getFechaValor() {
		return DateUtils.truncate(this.fecha, Calendar.DATE);
	}
	
	@Override
	public String getReferencia() {
		return numeroCompensacion.toString();
	}	
	
	@Override
	public String getIdBusquedaRespuestaCobrador() {
		return this.getIdMedioPago().toString();
	}

	/**
	 * @return the numeroActa
	 */
	public String getNumeroCompensacion() {
		return numeroCompensacion;
	}

	/**
	 * @param numeroActa the numeroActa to set
	 */
	public void setNumeroCompensacion(String numeroActa) {
		this.numeroCompensacion = numeroActa;
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

	/**
	 * @return the provincia
	 */
	public ShvParamProvincia getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(ShvParamProvincia provincia) {
		this.provincia = provincia;
	}
}