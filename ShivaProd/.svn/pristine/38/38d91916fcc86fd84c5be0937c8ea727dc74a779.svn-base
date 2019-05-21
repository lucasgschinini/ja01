package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoSuspension;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrigen;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoValor;
import ar.com.telecom.shiva.persistencia.modelo.util.IDatosComunesValor;

@Entity
@Table(name = "SHV_VAL_VALOR")
public class ShvValValor extends Modelo implements IDatosComunesValor {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_VAL_VALOR")
	@SequenceGenerator(name = "SEQ_SHV_VAL_VALOR", sequenceName = "SEQ_SHV_VAL_VALOR", allocationSize = 1)
	@Column(name = "ID_VALOR", nullable = false)
	private Long idValor;

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "valor")
	private ShvValBoletaDepositoCheque valorBoletaDepositoCheque;

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "valor")
	private ShvValBoletaDepositoChequePagoDiferido valorBoletaDepositoChequePD;

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "valor")
	private ShvValBoletaDepositoEfectivo valorBoletaEfectivo;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "SHV_VAL_VALOR_ADJUNTO", joinColumns = { @JoinColumn(name = "ID_VALOR", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ID_ADJUNTO", nullable = false, updatable = false) })
	private List<ShvDocDocumentoAdjunto> valorDocAdjunto = new ArrayList<ShvDocDocumentoAdjunto>();

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "valor")
	private ShvValValorCheque valValorCheque;

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "valor")
	private ShvValValorChequePagoDiferido valValorChequePD;

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "valor")
	private ShvValValorEfectivo valValorEfectivo;

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "valor")
	private ShvValValorInterdeposito valValorInterdeposito;

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "valor")
	private ShvValValorRetencion valValorRetencion;

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "valor")
	private ShvValValorTransferencia valValorTransferencia;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_WORKFLOW", referencedColumnName = "ID_WORKFLOW", nullable = false)
	private ShvWfWorkflow workFlow;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA", nullable = false)
	private ShvParamEmpresa empresa;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_SEGMENTO", referencedColumnName = "ID_SEGMENTO", nullable = false)
	private ShvParamSegmento segmento;

	@Formula("ID_CLIENTE_LEGADO||' - '||RAZON_SOCIAL_CLIENTE_PERFIL||';'||ID_CLIENTE_PERFIL")
	private String codigoClienteSiebelFormula;

	@Column(name = "ID_CLIENTE_PERFIL", nullable = false)
	private Long idClientePerfil;

	@Column(name = "ID_CLIENTE_LEGADO", nullable = false)
	private Long idClienteLegado;

	@Column(name = "RAZON_SOCIAL_CLIENTE_PERFIL")
	private String razonSocialClientePerfil;

	@Column(name = "EMAIL")
	private String Email;

	@Column(name = "ID_ANALISTA", nullable = false)
	private String idAnalista;

	@Column(name = "ID_COPROPIETARIO")
	private String idCopropietario;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TIPO_VALOR", referencedColumnName = "ID_TIPO_VALOR", nullable = false)
	private ShvParamTipoValor tipoValor;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ACUERDO", referencedColumnName = "ID_ACUERDO")
	private ShvParamAcuerdo acuerdo;

	@Column(name = "IMPORTE", nullable = false)
	private BigDecimal importe;

	@Column(name = "SALDO_DISPONIBLE")
	private BigDecimal saldoDisponible;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	@Column(name = "NUMERO_VALOR", nullable = false)
	private String numeroValor;

	@Column(name = "NUMERO_DOCUMENTO_CONTABLE")
	private Long numeroDocumentoContable;

	@Column(name = "OPERACION_ASOCIADA")
	private Integer operacionAsociada;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MOTIVO", referencedColumnName = "ID_MOTIVO")
	private ShvParamMotivo motivo;

	@ManyToOne
	@JoinColumn(name = "ID_VALOR_PADRE", referencedColumnName = "ID_VALOR")
	private ShvValValor valorPadre;

	@Column(name = "FECHA_VALOR")
	private Date fechaValor;

	@Column(name = "FECHA_ULTIMO_ESTADO")
	private Date fechaUltimoEstado;

	@Column(name = "FECHA_DISPONIBLE")
	private Date fechaDisponible;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MOTIVO_SUSPENSION", referencedColumnName = "ID_MOTIVO_SUSPENSION")
	private ShvParamMotivoSuspension motivoSuspension;

	@Column(name = "USUARIO_AUTORIZACION")
	private String usuarioAutorizacion;

	@Column(name = "USUARIO_EJECUTIVO")
	private String usuarioEjecutivo;

	@Column(name = "USUARIO_ASISTENTE")
	private String usuarioAsistente;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ORIGEN", referencedColumnName = "ID_ORIGEN")
	private ShvParamOrigen paramOrigen;

	@Column(name = "FECHA_ALTA", nullable = false)
	private Date fechaAlta;
	
	@Column(name = "OBSERVACIONES_CONFIRMAR_ALTA")
	private String observacionesConfirmarAlta;

	@Column(name = "CUIT")
	private Long cuit;

	@Column(name = "REFERENCIA_VALOR")
	private String referenciaValor;

	@Column(name = "NOMBRE_APELLIDO_ANALISTA")
	private String nombreApellidoAnalista;
	
	@Column(name = "NOMBRE_APELLIDO_COPROPIETARIO")
	private String nombreApellidoCopropietario;
	
	@Column(name = "NOMBRE_APELLIDO_AUTORIZANTE")
	private String nombreApellidoAutorizante;
	
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	
	@Column(name = "ID_HOLDING")
	private Long idHolding;
	
	@Column(name = "DESCRIPCION_HOLDING")
	private String descripcionHolding;
	
	@Column(name = "ID_AGENCIA_NEGOCIO")
	private String idAgenciaNegocio;
	
	@Column(name = "DESCRIPCION_AGENCIA_NEGOCIO")
	private String descripcionAgenciaNegocio;
	
	@Column(name = "SEGMENTO_AGENCIA_NEGOCIO")
	private String segmentoAgenciaNegocio;
	
	@Column(name = "USUARIO_CONFIRMACION")
	private String usuarioConfirmacion;
	
	@Column(name = "NOMBRE_APELLIDO_CONFIRMANTE")
	private String nombreApellidoConfirmante;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERMITE_USO_TA")
	private SiNoEnum permiteUsoTA;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERMITE_USO_TP")
	private SiNoEnum permiteUsoTP;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERMITE_USO_CV")
	private SiNoEnum permiteUsoCV;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERMITE_USO_FT")
	private SiNoEnum permiteUsoFT;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERMITE_USO_NX")
	private SiNoEnum permiteUsoNX;
	
	@Column(name = "ORIGEN")
	@Enumerated(EnumType.STRING)
	private ClienteOrigenEnum origen;

	@Column(name = "FECHA_NOTIF_DISPON_RETIRO_VAL")
	private Date fechaNotificacionDisponibilidadRetiroValor;
	
	private transient Boolean enviarMail;
	
	
	private transient ShvValConstanciaRecepcion constanciaRecepcion;
	
	//Utilizado en contabilidad
	public String getMoneda(){
		return Constantes.MONEDA_PES;
	}
	
	
	
	public Long getIdValor() {
		return idValor;
	}

	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}

	public ShvValBoletaDepositoCheque getValorBoletaDepositoCheque() {
		return valorBoletaDepositoCheque;
	}

	public void setValorBoletaDepositoCheque(
			ShvValBoletaDepositoCheque valorBoletaDepositoCheque) {
		this.valorBoletaDepositoCheque = valorBoletaDepositoCheque;
	}

	public ShvValBoletaDepositoChequePagoDiferido getValorBoletaDepositoChequePD() {
		return valorBoletaDepositoChequePD;
	}

	public void setValorBoletaDepositoChequePD(
			ShvValBoletaDepositoChequePagoDiferido valorBoletaDepositoChequePD) {
		this.valorBoletaDepositoChequePD = valorBoletaDepositoChequePD;
	}

	public ShvValBoletaDepositoEfectivo getValorBoletaEfectivo() {
		return valorBoletaEfectivo;
	}

	public void setValorBoletaEfectivo(ShvValBoletaDepositoEfectivo valorBoletaEfectivo) {
		this.valorBoletaEfectivo = valorBoletaEfectivo;
	}

	public List<ShvDocDocumentoAdjunto> getValorDocAdjunto() {
		return valorDocAdjunto;
	}

	public void setValorDocAdjunto(
			List<ShvDocDocumentoAdjunto> valorDocAdjunto) {
		this.valorDocAdjunto = valorDocAdjunto;
	}

	public ShvValValorCheque getValValorCheque() {
		return valValorCheque;
	}

	public void setValValorCheque(ShvValValorCheque valValorCheque) {
		this.valValorCheque = valValorCheque;
	}

	public ShvValValorChequePagoDiferido getValValorChequePD() {
		return valValorChequePD;
	}

	public void setValValorChequePD(
			ShvValValorChequePagoDiferido valValorChequePD) {
		this.valValorChequePD = valValorChequePD;
	}

	public ShvValValorEfectivo getValValorEfectivo() {
		return valValorEfectivo;
	}

	public void setValValorEfectivo(ShvValValorEfectivo valValorEfectivo) {
		this.valValorEfectivo = valValorEfectivo;
	}

	public ShvValValorInterdeposito getValValorInterdeposito() {
		return valValorInterdeposito;
	}

	public void setValValorInterdeposito(
			ShvValValorInterdeposito valValorInterdeposito) {
		this.valValorInterdeposito = valValorInterdeposito;
	}

	public ShvValValorRetencion getValValorRetencion() {
		return valValorRetencion;
	}

	public void setValValorRetencion(ShvValValorRetencion valValorRetencion) {
		this.valValorRetencion = valValorRetencion;
	}

	public ShvValValorTransferencia getValValorTransferencia() {
		return valValorTransferencia;
	}

	public void setValValorTransferencia(
			ShvValValorTransferencia valValorTransferencia) {
		this.valValorTransferencia = valValorTransferencia;
	}

	public ShvWfWorkflow getWorkFlow() {
		return this.workFlow;
	}

	public void setWorkFlow(ShvWfWorkflow workFlow) {
		this.workFlow = workFlow;
	}

	public ShvParamEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(ShvParamEmpresa empresa) {
		this.empresa = empresa;
	}

	public ShvParamSegmento getSegmento() {
		return segmento;
	}

	public void setSegmento(ShvParamSegmento segmento) {
		this.segmento = segmento;
	}

	public Long getIdClientePerfil() {
		return idClientePerfil;
	}

	public void setIdClientePerfil(Long idClientePerfil) {
		this.idClientePerfil = idClientePerfil;
	}

	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	public String getRazonSocialClientePerfil() {
		return razonSocialClientePerfil;
	}

	public void setRazonSocialClientePerfil(
			String razonSocialClientePerfil) {
		this.razonSocialClientePerfil = razonSocialClientePerfil;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	public String getIdCopropietario() {
		return idCopropietario;
	}

	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}

	public ShvParamTipoValor getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(ShvParamTipoValor tipoValor) {
		this.tipoValor = tipoValor;
	}

	public ShvParamAcuerdo getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(ShvParamAcuerdo acuerdo) {
		this.acuerdo = acuerdo;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getNumeroValor() {
		return numeroValor;
	}

	public void setNumeroValor(String numeroValor) {
		this.numeroValor = numeroValor;
	}

	public Long getNumeroDocumentoContable() {
		return numeroDocumentoContable;
	}

	public void setNumeroDocumentoContable(Long numeroDocumentoContable) {
		this.numeroDocumentoContable = numeroDocumentoContable;
	}

	public Integer getOperacionAsociada() {
		return operacionAsociada;
	}

	public void setOperacionAsociada(Integer operacionAsociada) {
		this.operacionAsociada = operacionAsociada;
	}

	public ShvParamMotivo getMotivo() {
		return motivo;
	}

	public void setMotivo(ShvParamMotivo motivo) {
		this.motivo = motivo;
	}

	public ShvValValor getValorPadre() {
		return valorPadre;
	}

	public void setValorPadre(ShvValValor valorPadre) {
		this.valorPadre = valorPadre;
	}

	public Date getFechaValor() {
		return fechaValor;
	}

	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	public Date getFechaUltimoEstado() {
		return fechaUltimoEstado;
	}

	public void setFechaUltimoEstado(Date fechaUltimoEstado) {
		this.fechaUltimoEstado = fechaUltimoEstado;
	}

	public Date getFechaDisponible() {
		return fechaDisponible;
	}

	public void setFechaDisponible(Date fechaDisponible) {
		this.fechaDisponible = fechaDisponible;
	}

	public ShvParamMotivoSuspension getMotivoSuspension() {
		return motivoSuspension;
	}

	public void setMotivoSuspension(ShvParamMotivoSuspension motivoSuspension) {
		this.motivoSuspension = motivoSuspension;
	}

	public String getUsuarioAutorizacion() {
		return usuarioAutorizacion;
	}

	public void setUsuarioAutorizacion(String usuarioAutorizacion) {
		this.usuarioAutorizacion = usuarioAutorizacion;
	}

	public String getUsuarioEjecutivo() {
		return usuarioEjecutivo;
	}

	public void setUsuarioEjecutivo(String usuarioEjecutivo) {
		this.usuarioEjecutivo = usuarioEjecutivo;
	}

	public String getUsuarioAsistente() {
		return usuarioAsistente;
	}

	public void setUsuarioAsistente(String usuarioAsistente) {
		this.usuarioAsistente = usuarioAsistente;
	}

	public ShvParamOrigen getParamOrigen() {
		return paramOrigen;
	}

	public void setParamOrigen(ShvParamOrigen paramOrigen) {
		this.paramOrigen = paramOrigen;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getCodigoClienteSiebelFormula() {
		return codigoClienteSiebelFormula;
	}

	public void setCodigoClienteSiebelFormula(String codigoClienteSiebelFormula) {
		this.codigoClienteSiebelFormula = codigoClienteSiebelFormula;
	}

	public Boolean getEnviarMail() {
		return enviarMail;
	}

	public void setEnviarMail(Boolean enviarMail) {
		this.enviarMail = enviarMail;
	}

	public ShvBolBoleta getBoleta() {
		ShvBolBoleta bolBoleta = null;
		if(!Validaciones.isObjectNull(this.getValorBoletaDepositoCheque())){
			bolBoleta = this.getValorBoletaDepositoCheque().getBoleta();
		}
		if(!Validaciones.isObjectNull(this.getValorBoletaDepositoChequePD())){
			bolBoleta = this.getValorBoletaDepositoChequePD().getBoleta();
		}
		if(!Validaciones.isObjectNull(this.getValorBoletaEfectivo())){
			bolBoleta = this.getValorBoletaEfectivo().getBoleta();
		}
		return bolBoleta;
	}
	
	public void setBoleta(ShvBolBoleta bolBoleta) {
		if(!Validaciones.isObjectNull(this.getValorBoletaDepositoCheque())){
			this.getValorBoletaDepositoCheque().setBoleta(bolBoleta);
		}
		if(!Validaciones.isObjectNull(this.getValorBoletaDepositoChequePD())){
			this.getValorBoletaDepositoChequePD().setBoleta(bolBoleta);
		}
		if(!Validaciones.isObjectNull(this.getValorBoletaEfectivo())){
			this.getValorBoletaEfectivo().setBoleta(bolBoleta);
		}
	}

	public String getObservacionesConfirmarAlta() {
		return observacionesConfirmarAlta;
	}

	public void setObservacionesConfirmarAlta(String observacionesConfirmarAlta) {
		this.observacionesConfirmarAlta = observacionesConfirmarAlta;
	}

	@Override
	public String getNumeroReferenciaDelValorFormateado() {
		String numeroReferenciaDelValor = "";
		if(!Validaciones.isObjectNull(this.getValorBoletaDepositoCheque())){
			if(!Validaciones.isObjectNull(this.getValorBoletaDepositoCheque().getNumeroCheque())){
				numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_BOLETA, this.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta().toString());
			}				
		} else if(!Validaciones.isObjectNull(this.getValorBoletaDepositoChequePD())){
			if(!Validaciones.isObjectNull(this.getValorBoletaDepositoChequePD().getNumeroCheque())){
				numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_BOLETA, this.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta().toString());
			}				
		} else if(!Validaciones.isObjectNull(this.getValorBoletaEfectivo())){
			if(!Validaciones.isObjectNull(this.getValorBoletaEfectivo().getBoleta())){
				if(!Validaciones.isObjectNull(this.getValorBoletaEfectivo().getBoleta())){
					numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_BOLETA, this.getValorBoletaEfectivo().getBoleta().getNumeroBoleta().toString());
				}
			}				
		} else if(!Validaciones.isObjectNull(this.getValValorCheque())){
			if(!Validaciones.isObjectNull(this.getValValorCheque().getNumeroCheque())){
				numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CHEQUE, this.getValValorCheque().getNumeroCheque().toString());
			}				
		} else if(!Validaciones.isObjectNull(this.getValValorChequePD())){
			if(!Validaciones.isObjectNull(this.getValValorChequePD().getNumeroCheque())){
				numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CHEQUE, this.getValValorChequePD().getNumeroCheque().toString());
			}				
		} else if(!Validaciones.isObjectNull(this.getValValorEfectivo())){
			if(!Validaciones.isObjectNull(this.getValValorEfectivo().getNumeroBoleta())){
				numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_BOLETA, this.getValValorEfectivo().getNumeroBoleta().toString());
			}				
		} else if(!Validaciones.isObjectNull(this.getValValorTransferencia())){
			numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_REFERENCIA, this.getValValorTransferencia().getNumeroReferencia().toString());
		} else if(!Validaciones.isObjectNull(this.getValValorInterdeposito())){
			numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_INTERDEPOSITO, this.getValValorInterdeposito().getNumeroInterdeposito().toString());
		} else if(!Validaciones.isObjectNull(this.getValValorRetencion())){
			numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CONSTANCIA, this.getValValorRetencion().getNroConstanciaRetencion());
		}
		return numeroReferenciaDelValor;
	}
	/**
	 * @author u573005, sprint3, se llama al metodo getNumeroValor()
	 * que desde la base de datos trae el campo resuelto
	 */
	@Override
	public String getNumeroValorFormateado() {
		return this.getNumeroValor();
	}

	@Override
	public String getClienteFormateado() {
		String clienteRazonSocial = "";
		if(!Validaciones.isObjectNull(this.getIdClienteLegado())){
			if(!Validaciones.isNullOrEmpty(this.getRazonSocialClientePerfil())){
				clienteRazonSocial = Utilidad.reemplazarMensajes(Mensajes.CAMPO_CLIENTE, this.getIdClienteLegado().toString(), this.getRazonSocialClientePerfil());
			}
		}		 
		return clienteRazonSocial;
	}

	public ShvValConstanciaRecepcion getConstanciaRecepcion() {
		return constanciaRecepcion;
	}

	public void setConstanciaRecepcion(ShvValConstanciaRecepcion constanciaRecepcion) {
		this.constanciaRecepcion = constanciaRecepcion;
	}



	public Long getCuit() {
		return cuit;
	}



	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}



	public String getReferenciaValor() {
		return referenciaValor;
	}



	public void setReferenciaValor(String referenciaValor) {
		this.referenciaValor = referenciaValor;
	}



	public String getNombreApellidoAnalista() {
		return nombreApellidoAnalista;
	}



	public void setNombreApellidoAnalista(String nombreApellidoAnalista) {
		this.nombreApellidoAnalista = nombreApellidoAnalista;
	}



	public String getNombreApellidoCopropietario() {
		return nombreApellidoCopropietario;
	}



	public void setNombreApellidoCopropietario(String nombreApellidoCopropietario) {
		this.nombreApellidoCopropietario = nombreApellidoCopropietario;
	}



	public String getNombreApellidoAutorizante() {
		return nombreApellidoAutorizante;
	}



	public void setNombreApellidoAutorizante(String nombreApellidoAutorizante) {
		this.nombreApellidoAutorizante = nombreApellidoAutorizante;
	}



	public String getRazonSocial() {
		return razonSocial;
	}



	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}



	public Long getIdHolding() {
		return idHolding;
	}



	public void setIdHolding(Long idHolding) {
		this.idHolding = idHolding;
	}



	public String getDescripcionHolding() {
		return descripcionHolding;
	}



	public void setDescripcionHolding(String descripcionHolding) {
		this.descripcionHolding = descripcionHolding;
	}



	public String getIdAgenciaNegocio() {
		return idAgenciaNegocio;
	}



	public void setIdAgenciaNegocio(String idAgenciaNegocio) {
		this.idAgenciaNegocio = idAgenciaNegocio;
	}



	public String getDescripcionAgenciaNegocio() {
		return descripcionAgenciaNegocio;
	}



	public void setDescripcionAgenciaNegocio(String descripcionAgenciaNegocio) {
		this.descripcionAgenciaNegocio = descripcionAgenciaNegocio;
	}



	public String getSegmentoAgenciaNegocio() {
		return segmentoAgenciaNegocio;
	}



	public void setSegmentoAgenciaNegocio(String segmentoAgenciaNegocio) {
		this.segmentoAgenciaNegocio = segmentoAgenciaNegocio;
	}



	public String getUsuarioConfirmacion() {
		return usuarioConfirmacion;
	}



	public void setUsuarioConfirmacion(String usuarioConfirmacion) {
		this.usuarioConfirmacion = usuarioConfirmacion;
	}



	public String getNombreApellidoConfirmante() {
		return nombreApellidoConfirmante;
	}



	public void setNombreApellidoConfirmante(String nombreApellidoConfirmante) {
		this.nombreApellidoConfirmante = nombreApellidoConfirmante;
	}



	/**
	 * @return the permiteUsoTA
	 */
	public SiNoEnum getPermiteUsoTA() {
		return permiteUsoTA;
	}



	/**
	 * @param permiteUsoTA the permiteUsoTA to set
	 */
	public void setPermiteUsoTA(SiNoEnum permiteUsoTA) {
		this.permiteUsoTA = permiteUsoTA;
	}



	/**
	 * @return the permiteUsoTP
	 */
	public SiNoEnum getPermiteUsoTP() {
		return permiteUsoTP;
	}



	/**
	 * @param permiteUsoTP the permiteUsoTP to set
	 */
	public void setPermiteUsoTP(SiNoEnum permiteUsoTP) {
		this.permiteUsoTP = permiteUsoTP;
	}



	/**
	 * @return the permiteUsoCV
	 */
	public SiNoEnum getPermiteUsoCV() {
		return permiteUsoCV;
	}



	/**
	 * @param permiteUsoCV the permiteUsoCV to set
	 */
	public void setPermiteUsoCV(SiNoEnum permiteUsoCV) {
		this.permiteUsoCV = permiteUsoCV;
	}



	/**
	 * @return the permiteUsoFT
	 */
	public SiNoEnum getPermiteUsoFT() {
		return permiteUsoFT;
	}



	/**
	 * @param permiteUsoFT the permiteUsoFT to set
	 */
	public void setPermiteUsoFT(SiNoEnum permiteUsoFT) {
		this.permiteUsoFT = permiteUsoFT;
	}



	/**
	 * @return the permiteUsoNX
	 */
	public SiNoEnum getPermiteUsoNX() {
		return permiteUsoNX;
	}



	/**
	 * @param permiteUsoNX the permiteUsoNX to set
	 */
	public void setPermiteUsoNX(SiNoEnum permiteUsoNX) {
		this.permiteUsoNX = permiteUsoNX;
	}



	/**
	 * @return the origen
	 */
	public ClienteOrigenEnum getOrigen() {
		return origen;
	}



	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(ClienteOrigenEnum origen) {
		this.origen = origen;
	}



	/**
	 * @return the fechaNotificacionDisponibilidadRetiroValor
	 */
	public Date getFechaNotificacionDisponibilidadRetiroValor() {
		return fechaNotificacionDisponibilidadRetiroValor;
	}



	/**
	 * @param fechaNotificacionDisponibilidadRetiroValor the fechaNotificacionDisponibilidadRetiroValor to set
	 */
	public void setFechaNotificacionDisponibilidadRetiroValor(Date fechaNotificacion) {
		this.fechaNotificacionDisponibilidadRetiroValor = fechaNotificacion;
	}
	

	
}