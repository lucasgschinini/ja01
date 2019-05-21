package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;

@Entity
@Table(name = "SHV_COB_DESCOBRO_OPERAC_RELAC")
public class ShvCobDescobroOperacionRelacionada extends Modelo{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_DESCOBRO_OP_REL")
    @SequenceGenerator(name="SEQ_SHV_COB_DESCOBRO_OP_REL", sequenceName="SEQ_SHV_COB_DESCOBRO_OP_REL", allocationSize = 1)
	@Column(name="ID_OPERACION_RELACIONADA")
	private Long idOperacionRelacionada;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_DESCOBRO", referencedColumnName="ID_DESCOBRO")
	private ShvCobDescobro descobro;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA")		
	private SistemaEnum sistema;
	
	@Column(name="ID_OPERACION")
	private Long idOperacion;
	
	@Column(name="NRO_TRANSACCION")	
	private Integer nroTransaccion;
	
	@Column(name="ID_COBRANZA")
	private Long idCobranza;

	@Column(name="ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	
	@Column(name="IMPORTE_COBRADO")
	private BigDecimal importeCobrado;
	
	@Column(name="FECHA_COBRANZA")
	private Date fechaCobranza;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_COMPROBANTE")
	private TipoComprobanteEnum tipoComprobante;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CLASE_COMPROBANTE")
	private TipoClaseComprobanteEnum claseComprobante;
	
	@Column(name="SUCURSAL_COMPROBANTE")
	private String  sucursalComprobante;
	
	@Column(name="NUMERO_COMPROBANTE")
	private String  numeroComprobante;
	
	@Column(name="ID_DOCUMENTO_CUENTAS_COBRANZA")
	private Long idDocumentoCuentasCobranza;
	
	@Column(name = "NUMERO_REFERENCIA_MIC")
	private Long numeroReferenciaMic;
	
	@Column(name="ID_OPERACION_PADRE")
	private Long idOperacionPadre;
	
	@Column(name="NRO_TRANSACCION_PADRE")	
	private Integer nroTransaccionPadre;

	public Long getIdOperacionRelacionada() {
		return idOperacionRelacionada;
	}

	public void setIdOperacionRelacionada(Long idOperacionRelacionada) {
		this.idOperacionRelacionada = idOperacionRelacionada;
	}

	public SistemaEnum getSistema() {
		return sistema;
	}

	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Integer getNroTransaccion() {
		return nroTransaccion;
	}

	public void setNroTransaccion(Integer nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}

	public Long getIdCobranza() {
		return idCobranza;
	}

	public void setIdCobranza(Long idCobranza) {
		this.idCobranza = idCobranza;
	}

	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	public BigDecimal getImporteCobrado() {
		return importeCobrado;
	}

	public void setImporteCobrado(BigDecimal importeCobrado) {
		this.importeCobrado = importeCobrado;
	}

	public Date getFechaCobranza() {
		return fechaCobranza;
	}

	public void setFechaCobranza(Date fechaCobranza) {
		this.fechaCobranza = fechaCobranza;
	}

	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}

	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}

	public String getSucursalComprobante() {
		return sucursalComprobante;
	}

	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public Long getIdDocumentoCuentasCobranza() {
		return idDocumentoCuentasCobranza;
	}

	public void setIdDocumentoCuentasCobranza(Long idDocumentoCuentasCobranza) {
		this.idDocumentoCuentasCobranza = idDocumentoCuentasCobranza;
	}

	public Long getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}

	public void setNumeroReferenciaMic(Long numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}

	public Long getIdOperacionPadre() {
		return idOperacionPadre;
	}

	public void setIdOperacionPadre(Long idOperacionPadre) {
		this.idOperacionPadre = idOperacionPadre;
	}

	public Integer getNroTransaccionPadre() {
		return nroTransaccionPadre;
	}

	public void setNroTransaccionPadre(Integer nroTransaccionPadre) {
		this.nroTransaccionPadre = nroTransaccionPadre;
	}

	public ShvCobDescobro getDescobro() {
		return descobro;
	}

	public void setDescobro(ShvCobDescobro descobro) {
		this.descobro = descobro;
	}
}
