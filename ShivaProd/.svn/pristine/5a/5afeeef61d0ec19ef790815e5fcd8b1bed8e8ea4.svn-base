package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoValor;
import ar.com.telecom.shiva.persistencia.modelo.util.IDatosComunesValor;

@Entity
@Table(name = "SHV_AVC_REGISTRO_AVC")
public class ShvAvcRegistroAvc extends Modelo implements IDatosComunesValor{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_AVC_REGISTRO_AVC")
	@SequenceGenerator(name="SEQ_SHV_AVC_REGISTRO_AVC", sequenceName="SEQ_SHV_AVC_REGISTRO_AVC", allocationSize = 1)
	@Column(name="ID_REGISTRO_AVC")
	private Long idRegistroAvc;
	
	
	@OneToOne(cascade={CascadeType.ALL}, mappedBy="registroAvc")
	private ShvAvcRegistroAvcDeposito deposito;
	
	@OneToOne(cascade={CascadeType.ALL}, mappedBy="registroAvc")
	private ShvAvcRegistroAvcTransferencia transferencia;
	
	@OneToOne(cascade={CascadeType.ALL}, mappedBy="registroAvc")
	private ShvAvcRegistroAvcInterdeposito interdeposito;
	

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ARCHIVO_AVC", referencedColumnName="ID_ARCHIVO_AVC") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvAvcArchivoAvc archivoAvc;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_WORKFLOW", referencedColumnName="ID_WORKFLOW")
	private ShvWfWorkflow workFlow;
	
	@Column(name="IMPORTE") 
	private BigDecimal importe;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ACUERDO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamAcuerdo acuerdo;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_VALOR")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamTipoValor tipoValor;
	
	@Column(name="CODIGO_CLIENTE")
	private String codigoCliente;
	
	@Column(name="OBSERVACION_ANULACION")
	private String observacionAnulacion;

	@Column(name="OBSERVACION_CONFIRMAR_ANUL")
	private String observacionConfirmarAnulacion;
	
	@Column(name="OBSERVACIONES")
	private String observaciones;
	
	@Column(name="RAZON_SOCIAL_CLIENTE_PERFIL")
	private String razonSocialClientePerfil;
	
	@Column(name="ID_CLIENTE_PERFIL")
	private Long idClientePerfil;
	
	@Column(name="FECHA_DERIVACION")
	private Date fechaDerivacion;
	
	@Column(name="ANALISTA_DERIVACION")
	private String analistaDerivacion;
	
	public Long getIdRegistroAvc() {
		return idRegistroAvc;
	}

	public void setIdRegistroAvc(Long idRegistroAvc) {
		this.idRegistroAvc = idRegistroAvc;
	}

	public ShvAvcArchivoAvc getArchivoAvc() {
		return archivoAvc;
	}

	public void setArchivoAvc(ShvAvcArchivoAvc archivoAvc) {
		this.archivoAvc = archivoAvc;
	}

	public ShvWfWorkflow getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(ShvWfWorkflow workFlow) {
		this.workFlow = workFlow;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public ShvParamAcuerdo getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(ShvParamAcuerdo acuerdo) {
		this.acuerdo = acuerdo;
	}

	public ShvAvcRegistroAvcDeposito getDeposito() {
		return deposito;
	}

	public void setDeposito(ShvAvcRegistroAvcDeposito deposito) {
		this.deposito = deposito;
	}

	public ShvAvcRegistroAvcTransferencia getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(ShvAvcRegistroAvcTransferencia transferencia) {
		this.transferencia = transferencia;
	}

	public ShvAvcRegistroAvcInterdeposito getInterdeposito() {
		return interdeposito;
	}

	public void setInterdeposito(ShvAvcRegistroAvcInterdeposito interdeposito) {
		this.interdeposito = interdeposito;
	}
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public ShvParamTipoValor getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(ShvParamTipoValor tipoValor) {
		this.tipoValor = tipoValor;
	}

	public String getObservacionAnulacion() {
		return observacionAnulacion;
	}

	public void setObservacionAnulacion(String observacionAnulacion) {
		this.observacionAnulacion = observacionAnulacion;
	}

	public String getRazonSocialClientePerfil() {
		return razonSocialClientePerfil;
	}

	public void setRazonSocialClientePerfil(String razonSocialClientePerfil) {
		this.razonSocialClientePerfil = razonSocialClientePerfil;
	}

	public Long getIdClientePerfil() {
		return idClientePerfil;
	}

	public void setIdClientePerfil(Long idClientePerfil) {
		this.idClientePerfil = idClientePerfil;
	}

	public Date getFechaDerivacion() {
		return fechaDerivacion;
	}

	public void setFechaDerivacion(Date fechaDerivacion) {
		this.fechaDerivacion = fechaDerivacion;
	}

	public String getAnalistaDerivacion() {
		return analistaDerivacion;
	}

	public void setAnalistaDerivacion(String analistaDerivacion) {
		this.analistaDerivacion = analistaDerivacion;
	}

	public String getObservacionConfirmarAnulacion() {
		return observacionConfirmarAnulacion;
	}

	public void setObservacionConfirmarAnulacion(
			String observacionConfirmarAnulacion) {
		this.observacionConfirmarAnulacion = observacionConfirmarAnulacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public String getNumeroReferenciaDelValorFormateado() {
		String numeroReferenciaDelValor = "";
		if(!Validaciones.isObjectNull(this.getDeposito())){
			if(!Validaciones.isObjectNull(this.getDeposito().getDepositoCheque())){
				if(!Validaciones.isObjectNull(this.getDeposito().getDepositoCheque().getNumeroCheque())){
					numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CHEQUE, this.getDeposito().getDepositoCheque().getNumeroCheque().toString());;
				}				
			} else if(!Validaciones.isObjectNull(this.getDeposito().getDepositoChequeDiferido())){
				if(!Validaciones.isObjectNull(this.getDeposito().getDepositoChequeDiferido().getNumeroCheque())){
					numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CHEQUE, this.getDeposito().getDepositoChequeDiferido().getNumeroCheque().toString());
				}				
			} else if(!Validaciones.isObjectNull(this.getDeposito().getDepositoEfectivo())){
				if(!Validaciones.isObjectNull(this.getDeposito().getNroBoleta())){
					numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_BOLETA, this.getDeposito().getNroBoleta().toString());
				}				
			}
		} else if(!Validaciones.isObjectNull(this.getTransferencia())){
			numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_REFERENCIA, this.getTransferencia().getReferencia().toString());
		} else if(!Validaciones.isObjectNull(this.getInterdeposito())){
			numeroReferenciaDelValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_INTERDEPOSITO, this.getInterdeposito().getCodigoInterdeposito().toString());
		}
		return numeroReferenciaDelValor;
	}

	@Override
	public String getNumeroValorFormateado() {
		String numeroValor = "";
		if (!Validaciones.isObjectNull(this.getDeposito())){
			if (!Validaciones.isObjectNull(this.getDeposito().getDepositoCheque())){
				numeroValor +=  Utilidad.reemplazarMensajes(Mensajes.CAMPO_BANCO_DESC, this.getDeposito().getDepositoCheque().getDescripcionBancoOrigen()) 
						+ " " + Constantes.SEPARADOR_PIPE + " "
						+  Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CHEQUE, this.getDeposito().getDepositoCheque().getNumeroCheque().toString());
			} else if (!Validaciones.isObjectNull(this.getDeposito().getDepositoChequeDiferido())){
				numeroValor += Utilidad.reemplazarMensajes(Mensajes.CAMPO_BANCO_DESC, this.getDeposito().getDepositoChequeDiferido().getDescripcionBancoOrigen())
						+ " " + Constantes.SEPARADOR_PIPE + " "
						+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CHEQUE, this.getDeposito().getDepositoChequeDiferido().getNumeroCheque().toString())
						+ " " + Constantes.SEPARADOR_PIPE + " "
						+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_FECHA_VTO, Utilidad.formatDatePicker(this.getDeposito().getDepositoChequeDiferido().getFechaVencimiento()));
			} else if (!Validaciones.isObjectNull(this.getDeposito().getDepositoEfectivo())){
				numeroValor += Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_BOLETA, this.getDeposito().getNroBoleta().toString());
			}
		} else if (!Validaciones.isObjectNull(this.getInterdeposito())){
			numeroValor += Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_INTERDEPOSITO, this.getInterdeposito().getCodigoInterdeposito().toString())
					+ " " + Constantes.SEPARADOR_PIPE + " "
					+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_CODIGO_ORGANISMO, this.getInterdeposito().getCodigoOrganismo());
		} else if (!Validaciones.isObjectNull(this.getTransferencia())) {
			numeroValor +=  Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_REFERENCIA, this.getTransferencia().getReferencia().toString());
		}
		return numeroValor;
	}

	@Override
	public String getClienteFormateado() {
		String clienteRazonSocial = "";
		if(!Validaciones.isObjectNull(this.getCodigoCliente())){
			if(!Validaciones.isNullOrEmpty(this.getRazonSocialClientePerfil())){
				clienteRazonSocial = Utilidad.reemplazarMensajes(Mensajes.CAMPO_CLIENTE, this.getCodigoCliente().toString(), this.getRazonSocialClientePerfil());
			}
		}		 
		return clienteRazonSocial;
	}
	
}
