package ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion;

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

import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCap;

@Entity
@Table(name = "SHV_COB_MED_PAG_COMP_LIQUIDO")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
public class ShvCobMedioPagoCompensacionLiquidoProductoSinOperacion extends ShvCobMedioPagoUsuarioSinOperacion {
	
	private static final long serialVersionUID = 1L;

	@Column(name="NUMERO_COMPENSACION")	 	
	private String numeroCompensacion;

	@Column(name="FECHA")	 	
	private Date fecha;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_MEDIO_PAGO_DOC_CAP", referencedColumnName="ID_MEDIO_PAGO_DOC_CAP") 	
	private ShvCobMedioPagoDocumentoCap documentoCap;
	
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

	public ShvCobMedioPagoDocumentoCap getDocumentoCap() {
		return documentoCap;
	}

	public void setDocumentoCap(ShvCobMedioPagoDocumentoCap documentoCap) {
		this.documentoCap = documentoCap;
	}
}
