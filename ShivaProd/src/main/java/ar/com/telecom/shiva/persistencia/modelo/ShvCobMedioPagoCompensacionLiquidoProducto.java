package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateUtils;

import ar.com.telecom.shiva.base.enumeradores.SubTipoCompensacionEnum;

@Entity
@Table(name = "SHV_COB_MED_PAG_COMP_LIQUIDO")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
public class ShvCobMedioPagoCompensacionLiquidoProducto extends ShvCobMedioPagoUsuario {
	
	private static final long serialVersionUID = 1L;

	@Column(name="NUMERO_COMPENSACION")	
	private String numeroCompensacion;

	@Column(name="FECHA")
	private Date fecha;
	
	@Column(name="TIPO_CAMBIO")
	private BigDecimal tipoCambio;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ID_SUBTIPO_COMP_LIQ_PROD")
	private SubTipoCompensacionEnum subTipo;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_MEDIO_PAGO_DOC_CAP", referencedColumnName="ID_MEDIO_PAGO_DOC_CAP") 	
	private ShvCobMedioPagoDocumentoCap documentoCap;
	
	@Override
	public Date getFechaValor() {
		return DateUtils.truncate(this.fecha, Calendar.DATE);
	}
	
	@Override
	public String getReferencia() {
		return numeroCompensacion != null ? numeroCompensacion.toString() : "";
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
	 * @return the subTipo
	 */
	public SubTipoCompensacionEnum getSubTipo() {
		return subTipo;
	}

	/**
	 * @param subTipo the subTipo to set
	 */
	public void setSubTipo(SubTipoCompensacionEnum subTipo) {
		this.subTipo = subTipo;
	}

	/**
	 * @return the documentoCap
	 */
	public ShvCobMedioPagoDocumentoCap getDocumentoCap() {
		return documentoCap;
	}

	/**
	 * @param documentoCap the documentoCap to set
	 */
	public void setDocumentoCap(
			ShvCobMedioPagoDocumentoCap medioPagoDocumentoCap) {
		this.documentoCap = medioPagoDocumentoCap;
	}
}
