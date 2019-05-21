package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoChequeRechazadoDetalleCobroEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_LGJ_CHEQUE_RECHA_DET_COBRO")
public class ShvLgjChequeRechazadoDetalleCobroSimplificado extends Modelo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column (name="ID_CHEQUE_RECHAZADO_COBRO", updatable = false)
	private Long idChequeRechazadoCobro;
	
	@Column(name="ID_CHEQUE_RECHAZADO")
	private Long idChequeRechazado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")
	private EstadoChequeRechazadoDetalleCobroEnum estado;
	
	@Column(name="CODIGO_BANCO_EMISOR_CHEQUE")
	private String codigoBancoEmisorCheque;
	
	@Column(name="ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	
	@Column(name="RAZON_SOCIAL")
	private String razonSocial;
	
	@Enumerated(EnumType.STRING)
	@Column(name="COBRO_REVERTIDO")
	private SiNoEnum cobroRevertido;
	
	@Column(name="CODIGO_BANCO_COBRO")
	private String codigoBancoCobro;
	
	@Column(name="CODIGO_TAREA")
	private String codigoDeTarea;
	
	@Column(name="CODIGO_EMPRESA_RECEPTORA")
	private String codigoEmpresaReceptora;
	
	@Column(name="CODIGO_ENTIDAD_GESTORA")
	private String codigoEntidadGestora;
	
	@Column(name="CODIGO_POSTAL_SUCURSAL")
	private String codigoPostalSucursal;
	
	@Column(name="COMISION")
	private Long comision;
	
	@Column(name="CP_CLIENTE_CODIGO_BARRAS")
	private String cpClienteCodigoBarras;
	
	@Column(name="DESCRIPCION_BANCO")
	private String descripcionBanco;
	
	@Column(name="FECHA_DE_ACREDITACION")
	private Date fechaDeAcreditacion;
	
	@Column(name="FECHA_DE_COBRO")
	private Date fechaDeCobro;
	
	@Column(name="FECHA_DE_VCTO_ORIGINAL")
	private Date fechaDeVctoOriginal;
	
	@Column(name="FECHA_PETICION_REVERSION")
	private Date fechaPeticionReversion;
	
	@Column(name="FORMA_DE_PAGO_DEL_CLIENTE")
	private String formaDePagoDelCliente;
	
	@Column(name="IDENTIFICADOR_DE_PAGO")
	private Long identificadorDePago;
	
	@Column(name="IMPORTE_ABSOLUTO")
	private BigDecimal importeAbsoluto;
	
	@Column(name="IMPORTE_BONO")
	private BigDecimal importeBono;
	
	@Column(name="IMPORTE_CHEQUE")
	private BigDecimal importeCheque;
	
	@Column(name="IMPORTE_COBRADO_OREVERSAR")
	private BigDecimal importeCobradoOReversar;
	
	@Column(name="IMPORTE_COMPROBANTE")
	private BigDecimal importeComprobante;
	
	@Column(name="IMPORTE_EFECTIVO")
	private BigDecimal importeEfectivo;
	
	@Column(name="IMPORTE_ORIGINAL")
	private BigDecimal importeOriginal;
	
	@Column(name="IMPORTE_OTRAS_MONEDAS")
	private BigDecimal importeOtrasMonedas;
	
	@Column(name="MARCA_DE_AJUSTE")
	private String marcaDeAjuste;
	
	@Column(name="MARCA_DEL_PAGO")
	private String marcaDePago;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA")
	private MonedaEnum  moneda;
	
	@Column(name="NOMBRE_BANCO_DE_COBRO")
	private String nombreBancoDeCobro;
	
	@Column(name="NOMBRE_ENTIDAD_GESTORA")
	private String nombreEntidadGestora;
	
	@Column(name="NUMERO_CHEQUE")
	private String numeroCheque;
	
	@Column(name="NUMERO_CONVENIO")
	private Long numeroConvenio;
	
	@Column(name="NUMERO_CUOTA")
	private Long numeroCuota;
	
	@Column(name="NUMERO_DE_AGENCIA")
	private String numeroDeAgencia;
	
	@Column(name="NUMERO_DE_COMPROBANTE")
	private String numeroDeComprobante;
	
	@Column(name="NUMERO_DE_SUCURSAL_COMPLETO")
	private String numeroDeSucursalCompleto;
	
	@Column(name="NUMERO_DE_TARJETA")
	private String numeroDeTarjeta;
	
	@Column(name="NUMERO_LEGAL")
	private String numeroLegal;
	
	@Column(name="NUMERO_REFERENCIA_MIC")
	private String numeroReferenciaMic;
	
	@Column(name="PROVINCIA_DEL_COD_BARRAS")
	private String provinciaDelCodBarras;
	
	@Column(name="RECIBO")
	private String recibo;
	
	@Column(name="REFERENCIA_DEL_COMPROBANTE")
	private String referenciaDelComprobante;
	
	@Column(name="SUCURSAL_BANCO")
	private String sucursalBanco;
	
	@Column(name="TIPO_BONO")
	private String tipoBono;
	
	@Column(name="TIPO_CLIENTE_CODIGO_BARRAS")
	private String tipoClienteCodigoBarras;
	
	@Column(name="TIPO_COMPROBANTE")
	private String tipoComprobante;
	
	@Column(name="TIPO_DE_CAMBIO")
	private Long tipoDeCambio;
	
	@Column(name="TIPO_DE_FACTURACION")
	private String tipoDeFacturacion;
	
	@Column(name="TIPO_DE_LECTURA")
	private String tipoDeLectura;
	
	@Column(name="TIPO_DE_OPERACION")
	private String tipoDeOperacion;
	
	@Column(name="TIPO_OTRAS_MONEDAS")
	private String tipoOtrasMonedas;

	public Long getIdChequeRechazadoCobro() {
		return idChequeRechazadoCobro;
	}

	public void setIdChequeRechazadoCobro(Long idChequeRechazadoCobro) {
		this.idChequeRechazadoCobro = idChequeRechazadoCobro;
	}

	public Long getIdChequeRechazado() {
		return idChequeRechazado;
	}

	public void setIdChequeRechazado(Long chequeRechazado) {
		this.idChequeRechazado = chequeRechazado;
	}

	public EstadoChequeRechazadoDetalleCobroEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoChequeRechazadoDetalleCobroEnum estado) {
		this.estado = estado;
	}

	public String getCodigoBancoEmisorCheque() {
		return codigoBancoEmisorCheque;
	}

	public void setCodigoBancoEmisorCheque(String codigoBancoEmisorCheque) {
		this.codigoBancoEmisorCheque = codigoBancoEmisorCheque;
	}

	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public SiNoEnum getCobroRevertido() {
		return cobroRevertido;
	}

	public void setCobroRevertido(SiNoEnum cobroRevertido) {
		this.cobroRevertido = cobroRevertido;
	}

	public String getCodigoBancoCobro() {
		return codigoBancoCobro;
	}

	public void setCodigoBancoCobro(String codigoBancoCobro) {
		this.codigoBancoCobro = codigoBancoCobro;
	}

	public String getCodigoDeTarea() {
		return codigoDeTarea;
	}

	public void setCodigoDeTarea(String codigoDeTarea) {
		this.codigoDeTarea = codigoDeTarea;
	}

	public String getCodigoEmpresaReceptora() {
		return codigoEmpresaReceptora;
	}

	public void setCodigoEmpresaReceptora(String codigoEmpresaReceptora) {
		this.codigoEmpresaReceptora = codigoEmpresaReceptora;
	}

	public String getCodigoEntidadGestora() {
		return codigoEntidadGestora;
	}

	public void setCodigoEntidadGestora(String codigoEntidadGestora) {
		this.codigoEntidadGestora = codigoEntidadGestora;
	}

	public String getCodigoPostalSucursal() {
		return codigoPostalSucursal;
	}

	public void setCodigoPostalSucursal(String codigoPostalSucursal) {
		this.codigoPostalSucursal = codigoPostalSucursal;
	}

	public Long getComision() {
		return comision;
	}

	public void setComision(Long comision) {
		this.comision = comision;
	}

	public String getCpClienteCodigoBarras() {
		return cpClienteCodigoBarras;
	}

	public void setCpClienteCodigoBarras(String cpClienteCodigoBarras) {
		this.cpClienteCodigoBarras = cpClienteCodigoBarras;
	}

	public String getDescripcionBanco() {
		return descripcionBanco;
	}

	public void setDescripcionBanco(String descripcionBanco) {
		this.descripcionBanco = descripcionBanco;
	}

	public Date getFechaDeAcreditacion() {
		return fechaDeAcreditacion;
	}

	public void setFechaDeAcreditacion(Date fechaDeAcreditacion) {
		this.fechaDeAcreditacion = fechaDeAcreditacion;
	}

	public Date getFechaDeCobro() {
		return fechaDeCobro;
	}

	public void setFechaDeCobro(Date fechaDeCobro) {
		this.fechaDeCobro = fechaDeCobro;
	}

	public Date getFechaDeVctoOriginal() {
		return fechaDeVctoOriginal;
	}

	public void setFechaDeVctoOriginal(Date fechaDeVctoOriginal) {
		this.fechaDeVctoOriginal = fechaDeVctoOriginal;
	}

	public Date getFechaPeticionReversion() {
		return fechaPeticionReversion;
	}

	public void setFechaPeticionReversion(Date fechaPeticionReversion) {
		this.fechaPeticionReversion = fechaPeticionReversion;
	}

	public String getFormaDePagoDelCliente() {
		return formaDePagoDelCliente;
	}

	public void setFormaDePagoDelCliente(String formaDePagoDelCliente) {
		this.formaDePagoDelCliente = formaDePagoDelCliente;
	}

	public Long getIdentificadorDePago() {
		return identificadorDePago;
	}

	public void setIdentificadorDePago(Long identificadorDePago) {
		this.identificadorDePago = identificadorDePago;
	}

	public BigDecimal getImporteAbsoluto() {
		return importeAbsoluto;
	}

	public void setImporteAbsoluto(BigDecimal importeAbsoluto) {
		this.importeAbsoluto = importeAbsoluto;
	}

	public BigDecimal getImporteBono() {
		return importeBono;
	}

	public void setImporteBono(BigDecimal importeBono) {
		this.importeBono = importeBono;
	}

	public BigDecimal getImporteCheque() {
		return importeCheque;
	}

	public void setImporteCheque(BigDecimal importeCheque) {
		this.importeCheque = importeCheque;
	}

	public BigDecimal getImporteCobradoOReversar() {
		return importeCobradoOReversar;
	}

	public void setImporteCobradoOReversar(BigDecimal importeCobradoOReversar) {
		this.importeCobradoOReversar = importeCobradoOReversar;
	}

	public BigDecimal getImporteComprobante() {
		return importeComprobante;
	}

	public void setImporteComprobante(BigDecimal importeComprobante) {
		this.importeComprobante = importeComprobante;
	}

	public BigDecimal getImporteEfectivo() {
		return importeEfectivo;
	}

	public void setImporteEfectivo(BigDecimal importeEfectivo) {
		this.importeEfectivo = importeEfectivo;
	}

	public BigDecimal getImporteOriginal() {
		return importeOriginal;
	}

	public void setImporteOriginal(BigDecimal importeOriginal) {
		this.importeOriginal = importeOriginal;
	}

	public BigDecimal getImporteOtrasMonedas() {
		return importeOtrasMonedas;
	}

	public void setImporteOtrasMonedas(BigDecimal importeOtrasMonedas) {
		this.importeOtrasMonedas = importeOtrasMonedas;
	}

	public String getMarcaDeAjuste() {
		return marcaDeAjuste;
	}

	public void setMarcaDeAjuste(String marcaDeAjuste) {
		this.marcaDeAjuste = marcaDeAjuste;
	}

	public String getMarcaDePago() {
		return marcaDePago;
	}

	public void setMarcaDePago(String marcaDePago) {
		this.marcaDePago = marcaDePago;
	}

	public MonedaEnum getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	public String getNombreBancoDeCobro() {
		return nombreBancoDeCobro;
	}

	public void setNombreBancoDeCobro(String nombreBancoDeCobro) {
		this.nombreBancoDeCobro = nombreBancoDeCobro;
	}

	public String getNombreEntidadGestora() {
		return nombreEntidadGestora;
	}

	public void setNombreEntidadGestora(String nombreEntidadGestora) {
		this.nombreEntidadGestora = nombreEntidadGestora;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public Long getNumeroConvenio() {
		return numeroConvenio;
	}

	public void setNumeroConvenio(Long numeroConvenio) {
		this.numeroConvenio = numeroConvenio;
	}

	public Long getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(Long numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public String getNumeroDeAgencia() {
		return numeroDeAgencia;
	}

	public void setNumeroDeAgencia(String numeroDeAgencia) {
		this.numeroDeAgencia = numeroDeAgencia;
	}

	public String getNumeroDeComprobante() {
		return numeroDeComprobante;
	}

	public void setNumeroDeComprobante(String numeroDeComprobante) {
		this.numeroDeComprobante = numeroDeComprobante;
	}

	public String getNumeroDeSucursalCompleto() {
		return numeroDeSucursalCompleto;
	}

	public void setNumeroDeSucursalCompleto(String numeroDeSucursalCompleto) {
		this.numeroDeSucursalCompleto = numeroDeSucursalCompleto;
	}

	public String getNumeroDeTarjeta() {
		return numeroDeTarjeta;
	}

	public void setNumeroDeTarjeta(String numeroDeTarjeta) {
		this.numeroDeTarjeta = numeroDeTarjeta;
	}

	public String getNumeroLegal() {
		return numeroLegal;
	}

	public void setNumeroLegal(String numeroLegal) {
		this.numeroLegal = numeroLegal;
	}

	public String getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}

	public void setNumeroReferenciaMic(String numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}

	public String getProvinciaDelCodBarras() {
		return provinciaDelCodBarras;
	}

	public void setProvinciaDelCodBarras(String provinciaDelCodBarras) {
		this.provinciaDelCodBarras = provinciaDelCodBarras;
	}

	public String getRecibo() {
		return recibo;
	}

	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}

	public String getReferenciaDelComprobante() {
		return referenciaDelComprobante;
	}

	public void setReferenciaDelComprobante(String referenciaDelComprobante) {
		this.referenciaDelComprobante = referenciaDelComprobante;
	}

	public String getSucursalBanco() {
		return sucursalBanco;
	}

	public void setSucursalBanco(String sucursalBanco) {
		this.sucursalBanco = sucursalBanco;
	}

	public String getTipoBono() {
		return tipoBono;
	}

	public void setTipoBono(String tipoBono) {
		this.tipoBono = tipoBono;
	}

	public String getTipoClienteCodigoBarras() {
		return tipoClienteCodigoBarras;
	}

	public void setTipoClienteCodigoBarras(String tipoClienteCodigoBarras) {
		this.tipoClienteCodigoBarras = tipoClienteCodigoBarras;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public Long getTipoDeCambio() {
		return tipoDeCambio;
	}

	public void setTipoDeCambio(Long tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}

	public String getTipoDeFacturacion() {
		return tipoDeFacturacion;
	}

	public void setTipoDeFacturacion(String tipoDeFacturacion) {
		this.tipoDeFacturacion = tipoDeFacturacion;
	}

	public String getTipoDeLectura() {
		return tipoDeLectura;
	}

	public void setTipoDeLectura(String tipoDeLectura) {
		this.tipoDeLectura = tipoDeLectura;
	}

	public String getTipoDeOperacion() {
		return tipoDeOperacion;
	}

	public void setTipoDeOperacion(String tipoDeOperacion) {
		this.tipoDeOperacion = tipoDeOperacion;
	}

	public String getTipoOtrasMonedas() {
		return tipoOtrasMonedas;
	}

	public void setTipoOtrasMonedas(String tipoOtrasMonedas) {
		this.tipoOtrasMonedas = tipoOtrasMonedas;
	}
}
