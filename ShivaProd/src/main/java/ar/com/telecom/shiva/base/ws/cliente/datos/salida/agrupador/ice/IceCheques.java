package ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.dto.SOA;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;

public class IceCheques extends SOA {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String 				bancoEmisorCheque;
	private List<ClienteCheque>	clientes;
	private boolean 			cobroRevertido;
	private String 				codigoBancoDeCobro;
	private String 				codigoDeTarea;
	private String 				codigoEmpresaReceptora;
	private String 				codigoEntidadGestora;
	private String 				codigoOperacion;
	private String 				codigoPostalSucursal;
	private Long 				comision;
	private String 				cpClienteCodigoBarras;
	private String 				descripcionBanco;
	private Date 				fechaDeAcreditacion;
	private Date 				fechaDeCobro;
	private Date 				fechaDeVctoOriginal;
	private Date 				fechaPeticionReversion;
	private String 				formaDePagoDelCliente;
	private String 				idCheque;
	private Long				identificadorDePago;
	private BigDecimal 			importeAbsoluto;
	private BigDecimal			importeBono;
	private BigDecimal 			importeCheque;
	private BigDecimal			importeCobradoOReversar;
	private BigDecimal			importeComprobante;
	private BigDecimal			importeEfectivo;
	private BigDecimal			importeOriginal;
	private BigDecimal			importeOtrasMonedas;
	private String 				marcaDeAjuste;
	private String 				marcaDePago;
	private MonedaEnum 			moneda;
	private String 				nombreBancoDeCobro;
	private String 				nombreEntidadGestora;
	private String 				numeroCheque;
	private String 				numeroChequeCompleto;
	private Long 				numeroConvenio;
	private Long				numeroCuota;
	private String 				numeroDeAgencia;
	private String 				numeroDeComprobante;
	private String 				numeroDeSucursalCompleto;
	private String 				numeroDeTarjeta;
	private String 				numeroLegal;
	private String 				numeroReferenciaMic;
	private String 				provinciaDelCodBarras;
	private String 				recibo;
	private String 				referenciaDelComprobante;
	private String 				sucursalBanco;
	private String 				tipoBono;
	private String 				tipoClienteCodigoBarras;
	private String 				tipoComprobante;
	private Long				tipoDeCambio;
	private String 				tipoDeFacturacion;
	private String 				tipoDeLectura;
	private String 				tipoDeOperacion;
	private String 				tipoDocumentoRelacionado;
	private String 				tipoDocumentoRelacionadoTotfa;
	private String 				tipoOtrasMonedas;

	/**
	 * @return the bancoEmisorCheque
	 */
	public String getBancoEmisorCheque() {
		return bancoEmisorCheque;
	}
	/**
	 * @param bancoEmisorCheque the bancoEmisorCheque to set
	 */
	public void setBancoEmisorCheque(String bancoEmisorCheque) {
		this.bancoEmisorCheque = bancoEmisorCheque;
	}
	/**
	 * @return the clientes
	 */
	public List<ClienteCheque> getClientes() {
		return clientes;
	}
	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(List<ClienteCheque> clientes) {
		this.clientes = clientes;
	}
	/**
	 * @return the cobroRevertido
	 */
	public boolean isCobroRevertido() {
		return cobroRevertido;
	}
	/**
	 * @param cobroRevertido the cobroRevertido to set
	 */
	public void setCobroRevertido(boolean cobroRevertido) {
		this.cobroRevertido = cobroRevertido;
	}
	/**
	 * @return the codigoBancoDeCobro
	 */
	public String getCodigoBancoDeCobro() {
		return codigoBancoDeCobro;
	}
	/**
	 * @param codigoBancoDeCobro the codigoBancoDeCobro to set
	 */
	public void setCodigoBancoDeCobro(String codigoBancoDeCobro) {
		this.codigoBancoDeCobro = codigoBancoDeCobro;
	}
	/**
	 * @return the codigoDeTarea
	 */
	public String getCodigoDeTarea() {
		return codigoDeTarea;
	}
	/**
	 * @param codigoDeTarea the codigoDeTarea to set
	 */
	public void setCodigoDeTarea(String codigoDeTarea) {
		this.codigoDeTarea = codigoDeTarea;
	}
	/**
	 * @return the codigoEmpresaReceptora
	 */
	public String getCodigoEmpresaReceptora() {
		return codigoEmpresaReceptora;
	}
	/**
	 * @param codigoEmpresaReceptora the codigoEmpresaReceptora to set
	 */
	public void setCodigoEmpresaReceptora(String codigoEmpresaReceptora) {
		this.codigoEmpresaReceptora = codigoEmpresaReceptora;
	}
	/**
	 * @return the codigoEntidadGestora
	 */
	public String getCodigoEntidadGestora() {
		return codigoEntidadGestora;
	}
	/**
	 * @param codigoEntidadGestora the codigoEntidadGestora to set
	 */
	public void setCodigoEntidadGestora(String codigoEntidadGestora) {
		this.codigoEntidadGestora = codigoEntidadGestora;
	}
	/**
	 * @return the codigoPostalSucursal
	 */
	public String getCodigoPostalSucursal() {
		return codigoPostalSucursal;
	}
	/**
	 * @param codigoPostalSucursal the codigoPostalSucursal to set
	 */
	public void setCodigoPostalSucursal(String codigoPostalSucursal) {
		this.codigoPostalSucursal = codigoPostalSucursal;
	}
	/**
	 * @return the comision
	 */
	public Long getComision() {
		return comision;
	}
	/**
	 * @param comision the comision to set
	 */
	public void setComision(Long comision) {
		this.comision = comision;
	}
	/**
	 * @return the cpClienteCodigoBarras
	 */
	public String getCpClienteCodigoBarras() {
		return cpClienteCodigoBarras;
	}
	/**
	 * @param cpClienteCodigoBarras the cpClienteCodigoBarras to set
	 */
	public void setCpClienteCodigoBarras(String cpClienteCodigoBarras) {
		this.cpClienteCodigoBarras = cpClienteCodigoBarras;
	}
	/**
	 * @return the descripcionBanco
	 */
	public String getDescripcionBanco() {
		return descripcionBanco;
	}
	/**
	 * @param descripcionBanco the descripcionBanco to set
	 */
	public void setDescripcionBanco(String descripcionBanco) {
		this.descripcionBanco = descripcionBanco;
	}
	/**
	 * @return the fechaDeAcreditacion
	 */
	public Date getFechaDeAcreditacion() {
		return fechaDeAcreditacion;
	}
	/**
	 * @param fechaDeAcreditacion the fechaDeAcreditacion to set
	 */
	public void setFechaDeAcreditacion(Date fechaDeAcreditacion) {
		this.fechaDeAcreditacion = fechaDeAcreditacion;
	}
	/**
	 * @return the fechaDeCobro
	 */
	public Date getFechaDeCobro() {
		return fechaDeCobro;
	}
	/**
	 * @param fechaDeCobro the fechaDeCobro to set
	 */
	public void setFechaDeCobro(Date fechaDeCobro) {
		this.fechaDeCobro = fechaDeCobro;
	}
	/**
	 * @return the fechaDeVctoOriginal
	 */
	public Date getFechaDeVctoOriginal() {
		return fechaDeVctoOriginal;
	}
	/**
	 * @param fechaDeVctoOriginal the fechaDeVctoOriginal to set
	 */
	public void setFechaDeVctoOriginal(Date fechaDeVctoOriginal) {
		this.fechaDeVctoOriginal = fechaDeVctoOriginal;
	}
	/**
	 * @return the fechaPeticionReversion
	 */
	public Date getFechaPeticionReversion() {
		return fechaPeticionReversion;
	}
	/**
	 * @param fechaPeticionReversion the fechaPeticionReversion to set
	 */
	public void setFechaPeticionReversion(Date fechaPeticionReversion) {
		this.fechaPeticionReversion = fechaPeticionReversion;
	}
	/**
	 * @return the formaDePagoDelCliente
	 */
	public String getFormaDePagoDelCliente() {
		return formaDePagoDelCliente;
	}
	/**
	 * @param formaDePagoDelCliente the formaDePagoDelCliente to set
	 */
	public void setFormaDePagoDelCliente(String formaDePagoDelCliente) {
		this.formaDePagoDelCliente = formaDePagoDelCliente;
	}
	/**
	 * @return the idCheque
	 */
	public String getIdCheque() {
		return idCheque;
	}
	/**
	 * @param idCheque the idCheque to set
	 */
	public void setIdCheque(String idCheque) {
		this.idCheque = idCheque;
	}
	/**
	 * @return the identificadorDePago
	 */
	public Long getIdentificadorDePago() {
		return identificadorDePago;
	}
	/**
	 * @param identificadorDePago the identificadorDePago to set
	 */
	public void setIdentificadorDePago(Long identificadorDePago) {
		this.identificadorDePago = identificadorDePago;
	}
	/**
	 * @return the importeAbsoluto
	 */
	public BigDecimal getImporteAbsoluto() {
		return importeAbsoluto;
	}
	/**
	 * @param importeAbsoluto the importeAbsoluto to set
	 */
	public void setImporteAbsoluto(BigDecimal importeAbsoluto) {
		this.importeAbsoluto = importeAbsoluto;
	}
	/**
	 * @return the importeBono
	 */
	public BigDecimal getImporteBono() {
		return importeBono;
	}
	/**
	 * @param importeBono the importeBono to set
	 */
	public void setImporteBono(BigDecimal importeBono) {
		this.importeBono = importeBono;
	}
	/**
	 * @return the importeCheque
	 */
	public BigDecimal getImporteCheque() {
		return importeCheque;
	}
	/**
	 * @param importeCheque the importeCheque to set
	 */
	public void setImporteCheque(BigDecimal importeCheque) {
		this.importeCheque = importeCheque;
	}
	/**
	 * @return the importeCobradoOReversar
	 */
	public BigDecimal getImporteCobradoOReversar() {
		return importeCobradoOReversar;
	}
	/**
	 * @param importeCobradoOReversar the importeCobradoOReversar to set
	 */
	public void setImporteCobradoOReversar(BigDecimal importeCobradoOReversar) {
		this.importeCobradoOReversar = importeCobradoOReversar;
	}
	/**
	 * @return the importeComprobante
	 */
	public BigDecimal getImporteComprobante() {
		return importeComprobante;
	}
	/**
	 * @param importeComprobante the importeComprobante to set
	 */
	public void setImporteComprobante(BigDecimal importeComprobante) {
		this.importeComprobante = importeComprobante;
	}
	/**
	 * @return the importeEfectivo
	 */
	public BigDecimal getImporteEfectivo() {
		return importeEfectivo;
	}
	/**
	 * @param importeEfectivo the importeEfectivo to set
	 */
	public void setImporteEfectivo(BigDecimal importeEfectivo) {
		this.importeEfectivo = importeEfectivo;
	}
	/**
	 * @return the importeOriginal
	 */
	public BigDecimal getImporteOriginal() {
		return importeOriginal;
	}
	/**
	 * @param importeOriginal the importeOriginal to set
	 */
	public void setImporteOriginal(BigDecimal importeOriginal) {
		this.importeOriginal = importeOriginal;
	}
	/**
	 * @return the importeOtrasMonedas
	 */
	public BigDecimal getImporteOtrasMonedas() {
		return importeOtrasMonedas;
	}
	/**
	 * @param importeOtrasMonedas the importeOtrasMonedas to set
	 */
	public void setImporteOtrasMonedas(BigDecimal importeOtrasMonedas) {
		this.importeOtrasMonedas = importeOtrasMonedas;
	}
	/**
	 * @return the marcaDeAjuste
	 */
	public String getMarcaDeAjuste() {
		return marcaDeAjuste;
	}
	/**
	 * @param marcaDeAjuste the marcaDeAjuste to set
	 */
	public void setMarcaDeAjuste(String marcaDeAjuste) {
		this.marcaDeAjuste = marcaDeAjuste;
	}
	/**
	 * @return the marcaDePago
	 */
	public String getMarcaDePago() {
		return marcaDePago;
	}
	/**
	 * @param marcaDePago the marcaDePago to set
	 */
	public void setMarcaDePago(String marcaDePago) {
		this.marcaDePago = marcaDePago;
	}
	/**
	 * @return the moneda
	 */
	public MonedaEnum getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return the nombreBancoDeCobro
	 */
	public String getNombreBancoDeCobro() {
		return nombreBancoDeCobro;
	}
	/**
	 * @param nombreBancoDeCobro the nombreBancoDeCobro to set
	 */
	public void setNombreBancoDeCobro(String nombreBancoDeCobro) {
		this.nombreBancoDeCobro = nombreBancoDeCobro;
	}
	/**
	 * @return the nombreEntidadGestora
	 */
	public String getNombreEntidadGestora() {
		return nombreEntidadGestora;
	}
	/**
	 * @param nombreEntidadGestora the nombreEntidadGestora to set
	 */
	public void setNombreEntidadGestora(String nombreEntidadGestora) {
		this.nombreEntidadGestora = nombreEntidadGestora;
	}
	/**
	 * @return the numeroCheque
	 */
	public String getNumeroCheque() {
		return numeroCheque;
	}
	/**
	 * @param numeroCheque the numeroCheque to set
	 */
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	/**
	 * @return the numeroConvenio
	 */
	public Long getNumeroConvenio() {
		return numeroConvenio;
	}
	/**
	 * @param numeroConvenio the numeroConvenio to set
	 */
	public void setNumeroConvenio(Long numeroConvenio) {
		this.numeroConvenio = numeroConvenio;
	}
	/**
	 * @return the numeroCuota
	 */
	public Long getNumeroCuota() {
		return numeroCuota;
	}
	/**
	 * @param numeroCuota the numeroCuota to set
	 */
	public void setNumeroCuota(Long numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	/**
	 * @return the numeroDeAgencia
	 */
	public String getNumeroDeAgencia() {
		return numeroDeAgencia;
	}
	/**
	 * @param numeroDeAgencia the numeroDeAgencia to set
	 */
	public void setNumeroDeAgencia(String numeroDeAgencia) {
		this.numeroDeAgencia = numeroDeAgencia;
	}
	/**
	 * @return the numeroDeComprobante
	 */
	public String getNumeroDeComprobante() {
		return numeroDeComprobante;
	}
	/**
	 * @param numeroDeComprobante the numeroDeComprobante to set
	 */
	public void setNumeroDeComprobante(String numeroDeComprobante) {
		this.numeroDeComprobante = numeroDeComprobante;
	}
	/**
	 * @return the numeroDeSucursalCompleto
	 */
	public String getNumeroDeSucursalCompleto() {
		return numeroDeSucursalCompleto;
	}
	/**
	 * @param numeroDeSucursalCompleto the numeroDeSucursalCompleto to set
	 */
	public void setNumeroDeSucursalCompleto(String numeroDeSucursalCompleto) {
		this.numeroDeSucursalCompleto = numeroDeSucursalCompleto;
	}
	/**
	 * @return the numeroDeTarjeta
	 */
	public String getNumeroDeTarjeta() {
		return numeroDeTarjeta;
	}
	/**
	 * @param numeroDeTarjeta the numeroDeTarjeta to set
	 */
	public void setNumeroDeTarjeta(String numeroDeTarjeta) {
		this.numeroDeTarjeta = numeroDeTarjeta;
	}
	/**
	 * @return the numeroLegal
	 */
	public String getNumeroLegal() {
		return numeroLegal;
	}
	/**
	 * @param numeroLegal the numeroLegal to set
	 */
	public void setNumeroLegal(String numeroLegal) {
		this.numeroLegal = numeroLegal;
	}
	/**
	 * @return the numeroReferenciaMic
	 */
	public String getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}
	/**
	 * @param numeroReferenciaMic the numeroReferenciaMic to set
	 */
	public void setNumeroReferenciaMic(String numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}
	/**
	 * @return the provinciaDelCodBarras
	 */
	public String getProvinciaDelCodBarras() {
		return provinciaDelCodBarras;
	}
	/**
	 * @param provinciaDelCodBarras the provinciaDelCodBarras to set
	 */
	public void setProvinciaDelCodBarras(String provinciaDelCodBarras) {
		this.provinciaDelCodBarras = provinciaDelCodBarras;
	}
	/**
	 * @return the recibo
	 */
	public String getRecibo() {
		return recibo;
	}
	/**
	 * @param recibo the recibo to set
	 */
	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}
	/**
	 * @return the referenciaDelComprobante
	 */
	public String getReferenciaDelComprobante() {
		return referenciaDelComprobante;
	}
	/**
	 * @param referenciaDelComprobante the referenciaDelComprobante to set
	 */
	public void setReferenciaDelComprobante(String referenciaDelComprobante) {
		this.referenciaDelComprobante = referenciaDelComprobante;
	}
	/**
	 * @return the sucursalBanco
	 */
	public String getSucursalBanco() {
		return sucursalBanco;
	}
	/**
	 * @param sucursalBanco the sucursalBanco to set
	 */
	public void setSucursalBanco(String sucursalBanco) {
		this.sucursalBanco = sucursalBanco;
	}
	/**
	 * @return the tipoBono
	 */
	public String getTipoBono() {
		return tipoBono;
	}
	/**
	 * @param tipoBono the tipoBono to set
	 */
	public void setTipoBono(String tipoBono) {
		this.tipoBono = tipoBono;
	}
	/**
	 * @return the tipoClienteCodigoBarras
	 */
	public String getTipoClienteCodigoBarras() {
		return tipoClienteCodigoBarras;
	}
	/**
	 * @param tipoClienteCodigoBarras the tipoClienteCodigoBarras to set
	 */
	public void setTipoClienteCodigoBarras(String tipoClienteCodigoBarras) {
		this.tipoClienteCodigoBarras = tipoClienteCodigoBarras;
	}
	/**
	 * @return the tipoComprobante
	 */
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	/**
	 * @param tipoComprobante the tipoComprobante to set
	 */
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	/**
	 * @return the tipoDeCambio
	 */
	public Long getTipoDeCambio() {
		return tipoDeCambio;
	}
	/**
	 * @param tipoDeCambio the tipoDeCambio to set
	 */
	public void setTipoDeCambio(Long tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}
	/**
	 * @return the tipoDeFacturacion
	 */
	public String getTipoDeFacturacion() {
		return tipoDeFacturacion;
	}
	/**
	 * @param tipoDeFacturacion the tipoDeFacturacion to set
	 */
	public void setTipoDeFacturacion(String tipoDeFacturacion) {
		this.tipoDeFacturacion = tipoDeFacturacion;
	}
	/**
	 * @return the tipoDeLectura
	 */
	public String getTipoDeLectura() {
		return tipoDeLectura;
	}
	/**
	 * @param tipoDeLectura the tipoDeLectura to set
	 */
	public void setTipoDeLectura(String tipoDeLectura) {
		this.tipoDeLectura = tipoDeLectura;
	}
	/**
	 * @return the tipoDeOperacion
	 */
	public String getTipoDeOperacion() {
		return tipoDeOperacion;
	}
	/**
	 * @param tipoDeOperacion the tipoDeOperacion to set
	 */
	public void setTipoDeOperacion(String tipoDeOperacion) {
		this.tipoDeOperacion = tipoDeOperacion;
	}
	/**
	 * @return the tipoOtrasMonedas
	 */
	public String getTipoOtrasMonedas() {
		return tipoOtrasMonedas;
	}
	/**
	 * @param tipoOtrasMonedas the tipoOtrasMonedas to set
	 */
	public void setTipoOtrasMonedas(String tipoOtrasMonedas) {
		this.tipoOtrasMonedas = tipoOtrasMonedas;
	}
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	public String getNumeroChequeCompleto() {
		return numeroChequeCompleto;
	}
	public void setNumeroChequeCompleto(String numeroChequeCompleto) {
		this.numeroChequeCompleto = numeroChequeCompleto;
	}
	public String getTipoDocumentoRelacionado() {
		return tipoDocumentoRelacionado;
	}
	public void setTipoDocumentoRelacionado(String tipoDocumentoRelacionado) {
		this.tipoDocumentoRelacionado = tipoDocumentoRelacionado;
	}
	public String getTipoDocumentoRelacionadoTotfa() {
		return tipoDocumentoRelacionadoTotfa;
	}
	public void setTipoDocumentoRelacionadoTotfa(
			String tipoDocumentoRelacionadoTotfa) {
		this.tipoDocumentoRelacionadoTotfa = tipoDocumentoRelacionadoTotfa;
	}

	
	
/*	
	public String getBancoEmisorCheque() {
		return bancoEmisorCheque;
	}
	public void setBancoEmisorCheque(String bancoEmisorCheque) {
		this.bancoEmisorCheque = bancoEmisorCheque;
	}
	public String getCodigoBancoDeCobro() {
		return codigoBancoDeCobro;
	}
	public void setCodigoBancoDeCobro(String codigoBancoDeCobro) {
		this.codigoBancoDeCobro = codigoBancoDeCobro;
	}
	public String getDescripcionBanco() {
		return descripcionBanco;
	}
	public void setDescripcionBanco(String descripcionBanco) {
		this.descripcionBanco = descripcionBanco;
	}
	public Date getFechaDeCobro() {
		return fechaDeCobro;
	}
	public void setFechaDeCobro(Date fechaDeCobro) {
		this.fechaDeCobro = fechaDeCobro;
	}
	public BigDecimal getImporteAbsoluto() {
		return importeAbsoluto;
	}
	public void setImporteAbsoluto(BigDecimal importeAbsoluto) {
		this.importeAbsoluto = importeAbsoluto;
	}
	public MonedaEnum getMoneda() {
		return moneda;
	}
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}
	public Long getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(Long numeroCheque) {
		this.numeroCheque = numeroCheque;
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
	public BigDecimal getImporteCheque() {
		return importeCheque;
	}
	public void setImporteCheque(BigDecimal importeCheque) {
		this.importeCheque = importeCheque;
	}
	public List<ClienteCheque> getClientes() {
		return clientes;
	}
	public void setClientes(List<ClienteCheque> clientes) {
		this.clientes = clientes;
	}
	*/
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/*
	@Override
	public String toString() {
		return "IceCheques [clientes=" + clientes + ", bancoEmisorCheque="
				+ bancoEmisorCheque + ", codigoBancoDeCobro="
				+ codigoBancoDeCobro + ", descripcionBanco=" + descripcionBanco
				+ ", fechaDeCobro=" + fechaDeCobro + ", importeAbsoluto="
				+ importeAbsoluto + ", importeCheque=" + importeCheque
				+ ", moneda=" + moneda + ", numeroCheque=" + numeroCheque
				+ ", idClienteLegado=" + idClienteLegado + ", razonSocial="
				+ razonSocial + "]";
	}
	*/
	
}
