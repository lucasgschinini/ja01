package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateUtils;

import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

@Entity
@Table(name = "SHV_COB_MED_PAG_NOTA_CRED_MIC")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
public class ShvCobMedioPagoNotaCreditoMic extends ShvCobMedioPagoMic{
	
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_COMPROBANTE")
	private TipoComprobanteEnum  tipoComprobante;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CLASE_COMPROBANTE")
	private TipoClaseComprobanteEnum claseComprobante;
	
	@Column(name="SUCURSAL_COMPROBANTE")
	private String	sucursalComprobante;

	@Column(name="NUMERO_COMPROBANTE")
	private String nroComprobante;

	@Column(name="NRO_NOTA_CREDITO")
	private String numeroNotaCredito;
	
	@Column(name="ID_CLIENTE_LEGADO")	 	
	private Long idClienteLegado;
	
	@Column(name="FECHA_EMISION")
	private Date fechaEmision;
	
	@Column(name="RAZON_SOCIAL_CLIENTE_LEGADO")
	private String razonSocialClienteLegado;

	@Column(name="TIPO_CLIENTE")
	private String tipoCliente;
	
	@Column(name="CUIT")
	private String cuit;
	
	@Column(name="CICLO_FACTURACION")
	private Long cicloFacturacion;
	
	@Column(name="FECHA_VENCIMIENTO")
	private Date fechaVencimiento;
	
	@Column(name="MARKETING_CUSTOMER_GROUP")
	private String marketingCustomerGroup;
	
	@Column(name="TIPO_FACTURA")
	private Integer tipoFactura;
	
	@Override
	public Date getFechaValor() {
		return DateUtils.truncate(fechaEmision, Calendar.DATE);
	}
	
	@Override
	public String getReferencia() {
		StringBuffer numeroLegal = new StringBuffer();
		
        // Si el comprobante es "D" o "S", el mismo no se envía ya que para el usuario es un blanco. (aplicará solo para MIC)
        if (!Validaciones.isObjectNull(this.getClaseComprobante()) && !TipoClaseComprobanteEnum.D.equals(this.getClaseComprobante()) && !TipoClaseComprobanteEnum.S.equals(this.getClaseComprobante())) {
            numeroLegal.append(this.getClaseComprobante().name());
        }
        
        numeroLegal.append(Utilidad.rellenarCerosIzquierda(this.getSucursalComprobante(), 4));
        numeroLegal.append("-");
        numeroLegal.append(Utilidad.rellenarCerosIzquierda(this.getNroComprobante(), 8));
       
        return numeroLegal.toString();
    }	
	
	@Override
	public String getIdBusquedaRespuestaCobrador() {
		
		String idBusqueraRespuestaCobrador = TipoMedioPagoEnum.NC.name() + this.numeroNotaCredito;
		return idBusqueraRespuestaCobrador;
	}
	
	/**
	 * @return the numeroNotaCredito
	 */
	public String getNumeroNotaCredito() {
		return numeroNotaCredito;
	}

	/**
	 * @param numeroNotaCredito the numeroNotaCredito to set
	 */
	public void setNumeroNotaCredito(String nroNotaCredito) {
		this.numeroNotaCredito = nroNotaCredito;
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
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the razonSocialClienteLegado
	 */
	public String getRazonSocialClienteLegado() {
		return razonSocialClienteLegado;
	}

	/**
	 * @param razonSocialClienteLegado the razonSocialClienteLegado to set
	 */
	public void setRazonSocialClienteLegado(String razonSocialClienteLegado) {
		this.razonSocialClienteLegado = razonSocialClienteLegado;
	}

	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * @return the cicloFacturacion
	 */
	public Long getCicloFacturacion() {
		return cicloFacturacion;
	}

	/**
	 * @param cicloFacturacion the cicloFacturacion to set
	 */
	public void setCicloFacturacion(Long cicloFacturacion) {
		this.cicloFacturacion = cicloFacturacion;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the marketingCustomerGroup
	 */
	public String getMarketingCustomerGroup() {
		return marketingCustomerGroup;
	}

	/**
	 * @param marketingCustomerGroup the marketingCustomerGroup to set
	 */
	public void setMarketingCustomerGroup(String marketingCustomerGroup) {
		this.marketingCustomerGroup = marketingCustomerGroup;
	}

	/**
	 * @return the tipoFactura
	 */
	public Integer getTipoFactura() {
		return tipoFactura;
	}

	/**
	 * @param tipoFactura the tipoFactura to set
	 */
	public void setTipoFactura(Integer tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	/**
	 * @return the tipoComprobante
	 */
	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}

	/**
	 * @param tipoComprobante the tipoComprobante to set
	 */
	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	/**
	 * @return the claseComprobante
	 */
	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}

	/**
	 * @param claseComprobante the claseComprobante to set
	 */
	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}

	/**
	 * @return the sucursalComprobante
	 */
	public String getSucursalComprobante() {
		return sucursalComprobante;
	}

	/**
	 * @param sucursalComprobante the sucursalComprobante to set
	 */
	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}

	/**
	 * @return the nroComprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * @param nroComprobante the nroComprobante to set
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
}
