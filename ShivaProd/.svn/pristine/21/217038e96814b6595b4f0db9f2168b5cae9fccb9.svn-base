package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "SHV_AVC_REG_AVC_DEPOSITO")
public class ShvAvcRegistroAvcDeposito extends Modelo{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_REGISTRO_AVC")
	@GeneratedValue(generator="SEQ_SHV_AVC_REGISTRO_AVC")
	@GenericGenerator(name = "SEQ_SHV_AVC_REGISTRO_AVC", strategy="foreign", parameters=@Parameter(name="property", value="registroAvc")) 
	private Long idRegistroAvc;
	
	@OneToOne  
	@PrimaryKeyJoinColumn  
	private ShvAvcRegistroAvc registroAvc;
	
	@OneToOne(cascade={CascadeType.ALL}, mappedBy="deposito")
	private ShvAvcRegistroAvcEfectivo depositoEfectivo;
	
	@OneToOne(cascade={CascadeType.ALL}, mappedBy="deposito")
	private ShvAvcRegistroAvcCheque depositoCheque;
	
	@OneToOne(cascade={CascadeType.ALL}, mappedBy="deposito")
	private ShvAvcRegistroAvcChequeDiferido depositoChequeDiferido;

	@Column(name="ID_REC_INSTRUMENTO")	
	private Long idRecInstrumento;
	
	@Column(name="REND") 				
	private Long rend;
	
	@Column(name="FECHA_DE_PAGO") 		
	private Date fechaPago;
	
	@Column(name="FORMA_PAGO") 			
	private String formaPago;
	
	@Column(name="ESTADO_ACREDITACION") 
	private String estadoAcreditacion;
	
	@Column(name="FECHA_ACREDITACION")	
	private Date fechaAcreditacion;
	
	@Column(name="NUMERO_BOLETA") 		
	private Long nroBoleta;
	
	@Column(name="SUCURSAL_DEPOSITO") 	
	private Long sucursalDeposito;
	
	@Column(name="GRUPO_ACREEDOR") 		
	private String grupoAcreedor;
	
	@Column(name="NOMBRE_CLIENTE") 		
	private String nombreCliente;
	
	@Column(name="CODIGO_RECHAZO") 		
	private String codigoRechazo;
	
	
	public Long getIdRegistroAvc() {
		return idRegistroAvc;
	}
	public void setIdRegistroAvc(Long idRegistroAvc) {
		this.idRegistroAvc = idRegistroAvc;
	}
	public ShvAvcRegistroAvc getRegistroAvc() {
		return registroAvc;
	}
	public void setRegistroAvc(ShvAvcRegistroAvc registroAvc) {
		this.registroAvc = registroAvc;
	}
	public Long getIdRecInstrumento() {
		return idRecInstrumento;
	}
	public void setIdRecInstrumento(Long idRecInstrumento) {
		this.idRecInstrumento = idRecInstrumento;
	}
	public Long getRend() {
		return rend;
	}
	public void setRend(Long rend) {
		this.rend = rend;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getEstadoAcreditacion() {
		return estadoAcreditacion;
	}
	public void setEstadoAcreditacion(String estadoAcreditacion) {
		this.estadoAcreditacion = estadoAcreditacion;
	}
	public Date getFechaAcreditacion() {
		return fechaAcreditacion;
	}
	public void setFechaAcreditacion(Date fechaAcreditacion) {
		this.fechaAcreditacion = fechaAcreditacion;
	}
	public Long getNroBoleta() {
		return nroBoleta;
	}
	public void setNroBoleta(Long nroBoleta) {
		this.nroBoleta = nroBoleta;
	}
	public Long getSucursalDeposito() {
		return sucursalDeposito;
	}
	public void setSucursalDeposito(Long sucursalDeposito) {
		this.sucursalDeposito = sucursalDeposito;
	}
	public String getGrupoAcreedor() {
		return grupoAcreedor;
	}
	public void setGrupoAcreedor(String grupoAcreedor) {
		this.grupoAcreedor = grupoAcreedor;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getCodigoRechazo() {
		return codigoRechazo;
	}
	public void setCodigoRechazo(String codigoRechazo) {
		this.codigoRechazo = codigoRechazo;
	}
	public ShvAvcRegistroAvcEfectivo getDepositoEfectivo() {
		return depositoEfectivo;
	}
	public void setDepositoEfectivo(ShvAvcRegistroAvcEfectivo depositoEfectivo) {
		this.depositoEfectivo = depositoEfectivo;
	}
	public ShvAvcRegistroAvcCheque getDepositoCheque() {
		return depositoCheque;
	}
	public void setDepositoCheque(ShvAvcRegistroAvcCheque depositoCheque) {
		this.depositoCheque = depositoCheque;
	}
	public ShvAvcRegistroAvcChequeDiferido getDepositoChequeDiferido() {
		return depositoChequeDiferido;
	}
	public void setDepositoChequeDiferido(
			ShvAvcRegistroAvcChequeDiferido depositoChequeDiferido) {
		this.depositoChequeDiferido = depositoChequeDiferido;
	}
	
}
