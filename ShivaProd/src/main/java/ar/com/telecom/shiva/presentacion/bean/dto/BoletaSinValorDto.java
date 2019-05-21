package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EnviarMailBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.ImprimirBoletaEstadoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

public class BoletaSinValorDto extends GestionDto{

	private static final long serialVersionUID = 1L;

	private Long idBoleta;
	
	private String empresa;
	private String segmento;
	private String codCliente;
	private String codClienteAgrupador;
	private String razonSocialClienteAgrupador;
	private String razonSocial; // Este dato por el momento no viene de siebel

	private String numeroHolding;
	private String nombreHolding;
	private String email;
	private String idAnalista;
	private String nombreAnalista;
	private String mailAnalista;
	private String idCopropietario;
	private String copropietario;
	private String importe;
	private String idMotivo;
	private String motivo;
	private String operacionAsociada;
	private String idOrigen;
	private String origen;
	private String idAcuerdo;
	private String acuerdo;
	private String idBancoDeposito;
	private String bancoDeposito;
	private String idNroCuenta;
	private String nroCuenta;
	private String observaciones;
	private boolean checkImprimirBoleta = false;
	private boolean checkEnviarMailBoleta = false;
	private String observacionMail;
	private String codEstado;
	private String estado;
	private String numeroBoleta;
	private String tipoValor;
	private ImprimirBoletaEstadoEnum boletaImpresaEstado = ImprimirBoletaEstadoEnum.NO;
	private EnviarMailBoletaEstadoEnum boletaEnviadaMailEstado = EnviarMailBoletaEstadoEnum.NO;
	private BigDecimal importeParaComparar;
	
	/* Flags para saber si hay mas de un elemento para los combos*/
	private boolean comboOrigen=false;
	private boolean comboAcuerdo=false;
	private boolean comboBanco=false;
	private boolean comboCuenta=false;
	private boolean comboCopropietario=true;
	private boolean comboMotivo= false;
	
	
	/*Numero de cheque que se usa para la conciliacion de una boleta con valor de tipo Cheque o Cheque Diferido*/
	private String numeroCheque;
	
	private boolean migracion;
	private boolean errorMapeo = false;
	
	/*datos del js*/
	private String idTipoValor;
	private boolean enviarBoletaMail;
	private boolean imprimirBoleta;

	public boolean isErrorMapeo() {
		return errorMapeo;
	}
	public void setErrorMapeo(boolean errorMapeo) {
		this.errorMapeo = errorMapeo;
	}
	public boolean isMigracion() {
		return migracion;
	}
	public void setMigracion(boolean migracion) {
		this.migracion = migracion;
	}
	public BoletaSinValorDto() {
	}
	
	public BoletaSinValorDto(Long id) {
		super.setId(id);
		this.setIdBoleta(id);
	}

	
	/*    Formatos     */
	public boolean getEsEstadoAnulado() {
		return (Estado.BOL_ANULADO.descripcion().equalsIgnoreCase(estado));
	}
	public boolean getEsEstadoPendiente() {
		return (Estado.BOL_PENDIENTE.descripcion().equalsIgnoreCase(estado));
	}

	public String getImporteParaComparacion(){
		if(Utilidad.PESOS_CHAR.equalsIgnoreCase(importe.substring(0, 1))){
			return Utilidad.formatCurrency(Utilidad.stringToBigDecimal(importe.substring(1)),2);
		}
		return Utilidad.formatCurrency(Utilidad.stringToBigDecimal(importe),2);
	}
	
	/****************************************************************************
	 * Setters & getters
	 ****************************************************************************/
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getSegmento() {
		return segmento;
	}
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getCodClienteAgrupador() {
		return codClienteAgrupador;
	}
	public void setCodClienteAgrupador(String codClienteAgrupador) {
		this.codClienteAgrupador = codClienteAgrupador;
	}
	public String getRazonSocialClienteAgrupador() {
		return razonSocialClienteAgrupador;
	}

	public void setRazonSocialClienteAgrupador(String razonSocialClienteAgrupador) {
		this.razonSocialClienteAgrupador = razonSocialClienteAgrupador;
	}

	public String getNumeroHolding() {
		return numeroHolding;
	}

	public void setNumeroHolding(String numeroHolding) {
		this.numeroHolding = numeroHolding;
	}

	public String getNombreHolding() {
		return nombreHolding;
	}

	public void setNombreHolding(String nombreHolding) {
		this.nombreHolding = nombreHolding;
	}

	public ImprimirBoletaEstadoEnum getBoletaImpresaEstado() {
		return boletaImpresaEstado;
	}

	public void setBoletaImpresaEstado(ImprimirBoletaEstadoEnum boletaImpresaEstado) {
		this.boletaImpresaEstado = boletaImpresaEstado;
	}

	public EnviarMailBoletaEstadoEnum getBoletaEnviadaMailEstado() {
		return boletaEnviadaMailEstado;
	}

	public void setBoletaEnviadaMailEstado(EnviarMailBoletaEstadoEnum boletaEnviadaMailEstado) {
		this.boletaEnviadaMailEstado = boletaEnviadaMailEstado;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdAnalista() {
		return idAnalista;
	}
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}
	public String getNombreAnalista() {
		return nombreAnalista;
	}
	public void setNombreAnalista(String nombreAnalista) {
		this.nombreAnalista = nombreAnalista;
	}
	public String getMailAnalista() {
		return mailAnalista;
	}

	public void setMailAnalista(String mailAnalista) {
		this.mailAnalista = mailAnalista;
	}

	public String getCopropietario() {
		return copropietario;
	}
	public void setCopropietario(String copropietario) {
		if(Propiedades.MENSAJES_PROPIEDADES.getString("combo.seleccionar").equalsIgnoreCase(copropietario)){
			this.copropietario="";
		}else{
			this.copropietario = copropietario;
		}
	}
	
	public String getIdCopropietario() {
		return idCopropietario;
	}

	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}

	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		if(Propiedades.MENSAJES_PROPIEDADES.getString("combo.seleccionar").equalsIgnoreCase(motivo)){
			this.motivo="";
		}else{
			this.motivo = motivo;
		}
	}
	
	public String getAcuerdo() {
		return acuerdo;
	}
	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}
	
	public String getIdBancoDeposito() {
		return idBancoDeposito;
	}

	public void setIdBancoDeposito(String idBancoDeposito) {
		this.idBancoDeposito = idBancoDeposito;
	}

	public String getIdNroCuenta() {
		return idNroCuenta;
	}

	public void setIdNroCuenta(String idNroCuenta) {
		this.idNroCuenta = idNroCuenta;
	}

	public String getBancoDeposito() {
		return bancoDeposito;
	}
	public void setBancoDeposito(String bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}
	public String getNroCuenta() {
		return nroCuenta;
	}
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	public String getOperacionAsociada() {
		return operacionAsociada;
	}
	public void setOperacionAsociada(String operacionAsociada) {
		this.operacionAsociada = operacionAsociada;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public boolean getCheckImprimirBoleta() {
		return checkImprimirBoleta;
	}
	public void setCheckImprimirBoleta(boolean checkImprimirBoleta) {
		this.checkImprimirBoleta = checkImprimirBoleta;
	}
	public boolean getCheckEnviarMailBoleta() {
		return checkEnviarMailBoleta;
	}
	public void setCheckEnviarMailBoleta(boolean checkEnviarMailBoleta) {
		this.checkEnviarMailBoleta = checkEnviarMailBoleta;
	}
	public String getObservacionMail() {
		return observacionMail;
	}
	public void setObservacionMail(String observacionMail) {
		this.observacionMail = observacionMail;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	public String getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(String idOrigen) {
		this.idOrigen = idOrigen;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNumeroBoleta() {
		return numeroBoleta;
	}
	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
		
	public boolean getComboOrigen() {
		return comboOrigen;
	}
	
	public void setComboOrigen(boolean comboOrigen) {
		this.comboOrigen = comboOrigen;
	}
	public boolean getComboAcuerdo() {
		return comboAcuerdo;
	}
	public void setComboAcuerdo(boolean comboAcuerdo) {
		this.comboAcuerdo = comboAcuerdo;
	}
	public boolean getComboBanco() {
		return comboBanco;
	}
	public void setComboBanco(boolean comboBanco) {
		this.comboBanco = comboBanco;
	}
	public boolean getComboCuenta() {
		return comboCuenta;
	}
	public void setComboCuenta(boolean comboCuenta) {
		this.comboCuenta = comboCuenta;
	}

	public boolean isComboCopropietario() {
		return comboCopropietario;
	}

	public void setComboCopropietario(boolean comboCopropietario) {
		this.comboCopropietario = comboCopropietario;
	}
	
	public boolean isComboMotivo() {
		return comboMotivo;
	}
	public void setComboMotivo(boolean comboMotivo) {
		this.comboMotivo = comboMotivo;
	}


	public String getCodEstado() {
		return codEstado;
	}
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	public String getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(String idMotivo) {
		this.idMotivo = idMotivo;
	}

	public String getIdAcuerdo() {
		return idAcuerdo;
	}

	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	public BigDecimal getImporteParaComparar() {
		return importeParaComparar;
	}

	public void setImporteParaComparar(BigDecimal importeParaComparar) {
		this.importeParaComparar = importeParaComparar;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	
	public Long getIdBoleta() {
		return idBoleta;
	}
	
	public void setIdBoleta(Long idBoleta) {
		this.idBoleta = idBoleta;
	}
	public String getIdTipoValor() {
		return idTipoValor;
	}
	public void setIdTipoValor(String idTipoValor) {
		this.idTipoValor = idTipoValor;
	}
	public boolean isEnviarBoletaMail() {
		return enviarBoletaMail;
	}
	public void setEnviarBoletaMail(boolean enviarBoletaMail) {
		this.enviarBoletaMail = enviarBoletaMail;
	}
	public boolean isImprimirBoleta() {
		return imprimirBoleta;
	}
	public void setImprimirBoleta(boolean imprimirBoleta) {
		this.imprimirBoleta = imprimirBoleta;
	}

}