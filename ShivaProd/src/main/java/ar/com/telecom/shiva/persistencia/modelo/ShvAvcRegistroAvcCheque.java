package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "SHV_AVC_REG_AVC_CHEQUE")
public class ShvAvcRegistroAvcCheque extends Modelo{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_REGISTRO_AVC")
	@GeneratedValue(generator="SEQ_SHV_AVC_REGISTRO_AVC")
	@GenericGenerator(name = "SEQ_SHV_AVC_REGISTRO_AVC", strategy="foreign", parameters=@Parameter(name="property", value="deposito")) 
	private Long idRegistroAvc;
	
	@OneToOne  
	@PrimaryKeyJoinColumn  
	private ShvAvcRegistroAvcDeposito deposito;

	@Column(name="CODIGO_BANCO") 	
	private Long codigoBanco;
	
	/**
	 * @author u573005, sprint3, se agrega este campo para obtener la descripcion del banco
	 * que se utilizara para armar los mails de forma unificada con los valores
	 */
	@Formula("(select b.descripcion from shv_param_banco b where b.id_banco = substr('0000' || CODIGO_BANCO, -4))")
	private String descripcionBancoOrigen;
	
	@Column(name="SUCURSAL") 		
	private Long sucursal;
	
	@Column(name="CODIGO_POSTAL") 			
	private Long codigoPostal;
	
	@Column(name="NUMERO_CHEQUE") 	
	private Long numeroCheque;
	
	@Column(name="CUENTA_EMISORA") 	
	private Long cuentaEmisora;
	
	public Long getIdRegistroAvc() {
		return idRegistroAvc;
	}
	public void setIdRegistroAvc(Long idRegistroAvc) {
		this.idRegistroAvc = idRegistroAvc;
	}
	public ShvAvcRegistroAvcDeposito getDeposito() {
		return deposito;
	}
	public void setDeposito(ShvAvcRegistroAvcDeposito deposito) {
		this.deposito = deposito;
	}
	public Long getCodigoBanco() {
		return codigoBanco;
	}
	public void setCodigoBanco(Long codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	public Long getSucursal() {
		return sucursal;
	}
	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}
	public Long getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(Long codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public Long getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(Long numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	public Long getCuentaEmisora() {
		return cuentaEmisora;
	}
	public void setCuentaEmisora(Long cuentaEmisora) {
		this.cuentaEmisora = cuentaEmisora;
	}
	public String getDescripcionBancoOrigen() {
		return descripcionBancoOrigen;
	}
	public void setDescripcionBancoOrigen(String descripcionBancoOrigen) {
		this.descripcionBancoOrigen = descripcionBancoOrigen;
	}	
}
