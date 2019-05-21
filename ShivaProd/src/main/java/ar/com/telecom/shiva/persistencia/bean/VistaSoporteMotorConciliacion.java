package ar.com.telecom.shiva.persistencia.bean;

import java.math.BigDecimal;
import java.util.Date;

public class VistaSoporteMotorConciliacion extends VistaSoporteResultadoBusqueda{

	private String regla;
	private String tipo;
	private Long ravcIdRegistroAvc;
	private Long ravcIdArchivoAvc;
	private Long ravcIdWorkflow;
	private Date ravcFechaUltimaModificacion;
	private String ravcEstadoWorkflow;
	private BigDecimal ravcImporte;
	private String ravcIdAcuerdo;
	private String ravcCodigoCliente; 
	private String ravcTipoValor;
	private String ravcNumeroBoleta;
	private String ravcNumeroCheque;  
	private Date ravcFechaDePago;
	private String ravcCodigoBanco;
	private Date ravcFechaVencimiento;
	private Long bolIdBoleta;
	private Long bolIdWorkflow;
	private Date boletaFechaUltimaModificacion;
	private String boletaEstadoWorkflow;
	private String bolNumeroBoleta;
	private String boletaIdAcuerdo;
	private Long valIdValor;
	private Long valIdWorkflow;
	private Date valorFechaUltimaModificacion;
	private String valorEstadoWorkflow;
	private String valClienteLegado;
	private String valTipoValor;
	private String valAcuerdo;
	private BigDecimal valImporte;
	private String valNumeroCheque;
	private String valIdEmpresa;
	private String valIdSegmento;
	private String valCodigoClienteAgrupador;
	private String valRazonSocialCliAgrup;
	private String valIdAnalista;
	private String valIdCopropietario;
	private String valIdMotivo;
	private String valOperacionAsociada;
	
	public String getRegla() {
		return regla;
	}
	public void setRegla(String regla) {
		this.regla = regla;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getRavcIdRegistroAvc() {
		return ravcIdRegistroAvc;
	}
	public void setRavcIdRegistroAvc(Long ravcIdRegistroAvc) {
		this.ravcIdRegistroAvc = ravcIdRegistroAvc;
	}
	public Long getRavcIdArchivoAvc() {
		return ravcIdArchivoAvc;
	}
	public void setRavcIdArchivoAvc(Long ravcIdArchivoAvc) {
		this.ravcIdArchivoAvc = ravcIdArchivoAvc;
	}
	public Long getRavcIdWorkflow() {
		return ravcIdWorkflow;
	}
	public void setRavcIdWorkflow(Long ravcIdWorkflow) {
		this.ravcIdWorkflow = ravcIdWorkflow;
	}
	public BigDecimal getRavcImporte() {
		return ravcImporte;
	}
	public void setRavcImporte(BigDecimal ravcImporte) {
		this.ravcImporte = ravcImporte;
	}
	public String getRavcIdAcuerdo() {
		return ravcIdAcuerdo;
	}
	public void setRavcIdAcuerdo(String ravcIdAcuerdo) {
		this.ravcIdAcuerdo = ravcIdAcuerdo;
	}
	public String getRavcCodigoCliente() {
		return ravcCodigoCliente;
	}
	public void setRavcCodigoCliente(String ravcCodigoCliente) {
		this.ravcCodigoCliente = ravcCodigoCliente;
	}
	public String getRavcTipoValor() {
		return ravcTipoValor;
	}
	public void setRavcTipoValor(String ravcTipoValor) {
		this.ravcTipoValor = ravcTipoValor;
	}
	public String getRavcNumeroBoleta() {
		return ravcNumeroBoleta;
	}
	public void setRavcNumeroBoleta(String ravcNumeroBoleta) {
		this.ravcNumeroBoleta = ravcNumeroBoleta;
	}
	public String getRavcNumeroCheque() {
		return ravcNumeroCheque;
	}
	public void setRavcNumeroCheque(String ravcNumeroCheque) {
		this.ravcNumeroCheque = ravcNumeroCheque;
	}
	public Long getBolIdBoleta() {
		return bolIdBoleta;
	}
	public void setBolIdBoleta(Long bolIdBoleta) {
		this.bolIdBoleta = bolIdBoleta;
	}
	public Long getBolIdWorkflow() {
		return bolIdWorkflow;
	}
	public void setBolIdWorkflow(Long bolIdWorkflow) {
		this.bolIdWorkflow = bolIdWorkflow;
	}
	public String getBolNumeroBoleta() {
		return bolNumeroBoleta;
	}
	public void setBolNumeroBoleta(String bolNumeroBoleta) {
		this.bolNumeroBoleta = bolNumeroBoleta;
	}
	public Long getValIdValor() {
		return valIdValor;
	}
	public void setValIdValor(Long valIdValor) {
		this.valIdValor = valIdValor;
	}
	public Long getValIdWorkflow() {
		return valIdWorkflow;
	}
	public void setValIdWorkflow(Long valIdWorkflow) {
		this.valIdWorkflow = valIdWorkflow;
	}
	public String getValClienteLegado() {
		return valClienteLegado;
	}
	public void setValClienteLegado(String valClienteLegado) {
		this.valClienteLegado = valClienteLegado;
	}
	public String getValTipoValor() {
		return valTipoValor;
	}
	public void setValTipoValor(String valTipoValor) {
		this.valTipoValor = valTipoValor;
	}
	public String getValAcuerdo() {
		return valAcuerdo;
	}
	public void setValAcuerdo(String valAcuerdo) {
		this.valAcuerdo = valAcuerdo;
	}
	public BigDecimal getValImporte() {
		return valImporte;
	}
	public void setValImporte(BigDecimal valImporte) {
		this.valImporte = valImporte;
	}
	public String getValNumeroCheque() {
		return valNumeroCheque;
	}
	public void setValNumeroCheque(String valNumeroCheque) {
		this.valNumeroCheque = valNumeroCheque;
	}
	public String getValIdEmpresa() {
		return valIdEmpresa;
	}
	public void setValIdEmpresa(String valIdEmpresa) {
		this.valIdEmpresa = valIdEmpresa;
	}
	public String getValIdSegmento() {
		return valIdSegmento;
	}
	public void setValIdSegmento(String valIdSegmento) {
		this.valIdSegmento = valIdSegmento;
	}
	public Date getRavcFechaDePago() {
		return ravcFechaDePago;
	}
	public void setRavcFechaDePago(Date ravcFechaDePago) {
		this.ravcFechaDePago = ravcFechaDePago;
	}
	public String getRavcCodigoBanco() {
		return ravcCodigoBanco;
	}
	public void setRavcCodigoBanco(String ravcCodigoBanco) {
		this.ravcCodigoBanco = ravcCodigoBanco;
	}
	public Date getRavcFechaVencimiento() {
		return ravcFechaVencimiento;
	}
	public void setRavcFechaVencimiento(Date ravcFechaVencimiento) {
		this.ravcFechaVencimiento = ravcFechaVencimiento;
	}
	public String getValCodigoClienteAgrupador() {
		return valCodigoClienteAgrupador;
	}
	public void setValCodigoClienteAgrupador(String valCodigoClienteAgrupador) {
		this.valCodigoClienteAgrupador = valCodigoClienteAgrupador;
	}
	public String getValRazonSocialCliAgrup() {
		return valRazonSocialCliAgrup;
	}
	public void setValRazonSocialCliAgrup(String valRazonSocialCliAgrup) {
		this.valRazonSocialCliAgrup = valRazonSocialCliAgrup;
	}
	public String getValIdAnalista() {
		return valIdAnalista;
	}
	public void setValIdAnalista(String valIdAnalista) {
		this.valIdAnalista = valIdAnalista;
	}
	public String getValIdCopropietario() {
		return valIdCopropietario;
	}
	public void setValIdCopropietario(String valIdCopropietario) {
		this.valIdCopropietario = valIdCopropietario;
	}
	public String getValIdMotivo() {
		return valIdMotivo;
	}
	public void setValIdMotivo(String valIdMotivo) {
		this.valIdMotivo = valIdMotivo;
	}
	public String getValOperacionAsociada() {
		return valOperacionAsociada;
	}
	public void setValOperacionAsociada(String valOperacionAsociada) {
		this.valOperacionAsociada = valOperacionAsociada;
	}
	public Date getRavcFechaUltimaModificacion() {
		return ravcFechaUltimaModificacion;
	}
	public void setRavcFechaUltimaModificacion(Date ravcFechaUltimaModificacion) {
		this.ravcFechaUltimaModificacion = ravcFechaUltimaModificacion;
	}
	public Date getBoletaFechaUltimaModificacion() {
		return boletaFechaUltimaModificacion;
	}
	public void setBoletaFechaUltimaModificacion(Date boletaFechaUltimaModificacion) {
		this.boletaFechaUltimaModificacion = boletaFechaUltimaModificacion;
	}
	public Date getValorFechaUltimaModificacion2() {
		return valorFechaUltimaModificacion;
	}
	public void setValorFechaUltimaModificacion(Date valorFechaUltimaModificacion) {
		this.valorFechaUltimaModificacion = valorFechaUltimaModificacion;
	}
	public String getRavcEstadoWorkflow() {
		return ravcEstadoWorkflow;
	}
	public void setRavcEstadoWorkflow(String ravcEstadoWorkflow) {
		this.ravcEstadoWorkflow = ravcEstadoWorkflow;
	}
	public String getBoletaEstadoWorkflow() {
		return boletaEstadoWorkflow;
	}
	public void setBoletaEstadoWorkflow(String boletaEstadoWorkflow) {
		this.boletaEstadoWorkflow = boletaEstadoWorkflow;
	}
	public String getValorEstadoWorkflow() {
		return valorEstadoWorkflow;
	}
	public void setValorEstadoWorkflow(String valorEstadoWorkflow) {
		this.valorEstadoWorkflow = valorEstadoWorkflow;
	}
	public String getBoletaIdAcuerdo() {
		return boletaIdAcuerdo;
	}
	public void setBoletaIdAcuerdo(String boletaIdAcuerdo) {
		this.boletaIdAcuerdo = boletaIdAcuerdo;
	}
	
	
	
}
