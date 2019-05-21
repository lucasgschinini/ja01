package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




import ar.com.telecom.shiva.base.comparador.ComparatorShvLgjChequeRechazadoDetalleCliente;
import ar.com.telecom.shiva.base.comparador.ComparatorShvLgjChequeRechazadoDetalleCobro;
import ar.com.telecom.shiva.base.enumeradores.EstadoChequeRechazadoDetalleCobroEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoChequeEnum;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;


@Entity
@Table(name = "SHV_LGJ_CHEQUE_RECHAZADO")
public class ShvLgjChequeRechazado extends Modelo {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_LGJ_CHEQUE_RECHAZADO")
	@SequenceGenerator(name = "SEQ_SHV_LGJ_CHEQUE_RECHAZADO", sequenceName = "SEQ_SHV_LGJ_CHEQUE_RECHAZADO", allocationSize = 1)
	@Column(name="ID_CHEQUE_RECHAZADO", updatable = false)
	private Long idChequeRechazado;
	
	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER, mappedBy = "chequeRechazado", orphanRemoval=true)
	private Set<ShvLgjChequeRechazadoDetalleCliente> clientes = new TreeSet<ShvLgjChequeRechazadoDetalleCliente>(new ComparatorShvLgjChequeRechazadoDetalleCliente());

	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER, mappedBy = "chequeRechazado", orphanRemoval=true)
	private Set<ShvLgjChequeRechazadoDetalleCobro> cobros = new TreeSet<ShvLgjChequeRechazadoDetalleCobro>(new ComparatorShvLgjChequeRechazadoDetalleCobro());

	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER, mappedBy = "chequeRechazado", orphanRemoval=true)
	private Set<ShvLgjChequeRechazadoDetalleDocumento> documentos = new HashSet<ShvLgjChequeRechazadoDetalleDocumento>();

	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_ORIGEN")
	private SistemaEnum sistemaOrigen;
	
	@Column(name="NUMERO_CHEQUE")
	private String numeroCheque;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_BANCO_ORIGEN") 
	private ShvParamBanco bancoOrigen;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_CHEQUE")
	private TipoChequeEnum tipoCheque;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA")
	private MonedaEnum moneda;
	
	@Column(name="IMPORTE")
	private BigDecimal importe;
	@Column(name="IMPORTE_CHEQUE")
	private BigDecimal importeCheque;
	
	@Column(name="FECHA_DEPOSITO")
	private Date fechaDeposito;

	@Column(name="COD_BANCO_DE_COBRO")
	private String codigoBancoDeCobro;

	@Column(name="ID_VALOR")
	private Long idValor;

	@Column(name="ID_INTERNO_ICE")
	private Long idInternoIce;
	

	public ShvLgjChequeRechazado(){
	}

	/**
	 * @return the idChequeRechazado
	 */
	public Long getIdChequeRechazado() {
		return idChequeRechazado;
	}

	/**
	 * @param idChequeRechazado the idChequeRechazado to set
	 */
	public void setIdChequeRechazado(Long idChequeRechazado) {
		this.idChequeRechazado = idChequeRechazado;
	}

	/**
	 * @return the sistemaOrigen
	 */
	public SistemaEnum getSistemaOrigen() {
		return sistemaOrigen;
	}

	/**
	 * @param sistemaOrigen the sistemaOrigen to set
	 */
	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
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
	 * @return the bancoOrigen
	 */
	public ShvParamBanco getBancoOrigen() {
		return bancoOrigen;
	}

	/**
	 * @param bancoOrigen the bancoOrigen to set
	 */
	public void setBancoOrigen(ShvParamBanco bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}

	/**
	 * @return the tipoCheque
	 */
	public TipoChequeEnum getTipoCheque() {
		return tipoCheque;
	}

	/**
	 * @param tipoCheque the tipoCheque to set
	 */
	public void setTipoCheque(TipoChequeEnum tipoCheque) {
		this.tipoCheque = tipoCheque;
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
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the fechaDeposito
	 */
	public Date getFechaDeposito() {
		return fechaDeposito;
	}

	/**
	 * @param fechaDeposito the fechaDeposito to set
	 */
	public void setFechaDeposito(Date fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}

	/**
	 * @return the idValor
	 */
	public Long getIdValor() {
		return idValor;
	}

	/**
	 * @param idValor the idValor to set
	 */
	public void setIdValor(Long idValor) {
		this.idValor = idValor;
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

	public Set<ShvLgjChequeRechazadoDetalleCliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<ShvLgjChequeRechazadoDetalleCliente> clientes) {
		this.clientes = clientes;
	}

	public Set<ShvLgjChequeRechazadoDetalleCobro> getCobros() {
		return cobros;
	}

	public void setCobros(Set<ShvLgjChequeRechazadoDetalleCobro> cobro) {
		this.cobros = cobro;
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
	 * @return the idInternoIce
	 */
	public Long getIdInternoIce() {
		return idInternoIce;
	}

	/**
	 * @param idInternoIce the idInternoIce to set
	 */
	public void setIdInternoIce(Long idInternoIce) {
		this.idInternoIce = idInternoIce;
	}
	
	/**
	 * Verifica si los cobros se encuentran revertidos
	 * @return
	 */
	public boolean isCobrosRevertidos() {
		
		boolean retorno = true;
		
		for (ShvLgjChequeRechazadoDetalleCobro detalleCobro : cobros) {
			if (!EstadoChequeRechazadoDetalleCobroEnum.REVERTIDO.equals(detalleCobro.getEstado())) {
				retorno  = false;
				break;
			}
		}
		
		return retorno;
	}
	
	/**
	 * Verifica si existe al menos un cobro revertido
	 * 
	 * @return
	 */
	public boolean isAlMenosUnCobroRevertido() {
		
		boolean retorno = false;
		
		for (ShvLgjChequeRechazadoDetalleCobro detalleCobro : cobros) {
			if (EstadoChequeRechazadoDetalleCobroEnum.REVERTIDO.equals(detalleCobro.getEstado())) {
				retorno  = true;
				break;
			}
		}
		
		return retorno;
	}

	/**
	 * @return the documentos
	 */
	public Set<ShvLgjChequeRechazadoDetalleDocumento> getDocumentos() {
		return documentos;
	}

	/**
	 * @param documentos the documentos to set
	 */
	public void setDocumentos(Set<ShvLgjChequeRechazadoDetalleDocumento> documentos) {
		this.documentos = documentos;
	}
	

}