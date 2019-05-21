package ar.com.telecom.shiva.negocio.servicios.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;

public class ConsultaSoporteResultadoBusquedaChequeRechazado implements Bean {
	private static final long serialVersionUID = 20170525L;

	private SistemaEnum sistemaOrigen;
	private String tipoCheque;
	private String idTipoCheque;
	private String codBancoOrigen;
	private String descripcionBancoOrigen;
	private String nroCheque;
	private Date fechaDeposito;
	private Date fechaRecepcion;
	private Date fechaVenc;
	private MonedaEnum moneda;
	private BigDecimal importe;
	private String acuerdo;
	private String bancoDeposito;
	private String cuentaDeposito;
	private String estado;
	private String empresa;
	private String segmento;
	private String analista;
	private String copropietario;
	private String analistaTeamComercial;
	private Long idInternoValor;
	private Long idInternoIce; // TODO Consultar a Pablo si usar el idInternoValor para discrimiar U578936
	

	private BigDecimal saldoDisponible;

	private BigDecimal importeCheque;

	private List<ClienteBean> clienteCheques = new ArrayList<ClienteBean>();
	
	private String codBancoDeCobro;
	
	public ConsultaSoporteResultadoBusquedaChequeRechazado() {}

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
	 * @return the tipoCheque
	 */
	public String getTipoCheque() {
		return tipoCheque;
	}

	/**
	 * @param tipoCheque the tipoCheque to set
	 */
	public void setTipoCheque(String tipoCheque) {
		this.tipoCheque = tipoCheque;
	}

	/**
	 * @return the codBancoOrigen
	 */
	public String getCodBancoOrigen() {
		return codBancoOrigen;
	}

	/**
	 * @param codBancoOrigen the codBancoOrigen to set
	 */
	public void setCodBancoOrigen(String codBancoOrigen) {
		this.codBancoOrigen = codBancoOrigen;
	}

	/**
	 * @return the descripcionBancoOrigen
	 */
	public String getDescripcionBancoOrigen() {
		return descripcionBancoOrigen;
	}

	/**
	 * @param descripcionBancoOrigen the descripcionBancoOrigen to set
	 */
	public void setDescripcionBancoOrigen(String descripcionBancoOrigen) {
		this.descripcionBancoOrigen = descripcionBancoOrigen;
	}

	/**
	 * @return the nroCheque
	 */
	public String getNroCheque() {
		return nroCheque;
	}

	/**
	 * @param nroCheque the nroCheque to set
	 */
	public void setNroCheque(String nroCheque) {
		this.nroCheque = nroCheque;
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
	 * @return the fechaRecepcion
	 */
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	/**
	 * @param fechaRecepcion the fechaRecepcion to set
	 */
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	/**
	 * @return the fechaVenc
	 */
	public Date getFechaVenc() {
		return fechaVenc;
	}

	/**
	 * @param fechaVenc the fechaVenc to set
	 */
	public void setFechaVenc(Date fechaVenc) {
		this.fechaVenc = fechaVenc;
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
	 * Para ICE es el importeAbsoluto
	 * 	Para Shiva es el importee
	 * @return the importeCheque
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importeCheque the importeCheque to set
	 */
	public void setImporte(BigDecimal importeCheque) {
		this.importe = importeCheque;
	}

	/**
	 * @return the acuerdo
	 */
	public String getAcuerdo() {
		return acuerdo;
	}

	/**
	 * @param acuerdo the acuerdo to set
	 */
	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}

	/**
	 * @return the bancoDeposito
	 */
	public String getBancoDeposito() {
		return bancoDeposito;
	}

	/**
	 * @param bancoDeposito the bancoDeposito to set
	 */
	public void setBancoDeposito(String bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}

	/**
	 * @return the cuentaDeposito
	 */
	public String getCuentaDeposito() {
		return cuentaDeposito;
	}

	/**
	 * @param cuentaDeposito the cuentaDeposito to set
	 */
	public void setCuentaDeposito(String cuentaDeposito) {
		this.cuentaDeposito = cuentaDeposito;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * @return the analista
	 */
	public String getAnalista() {
		return analista;
	}
	/**
	 * @param analista the analista to set
	 */
	public void setAnalista(String analista) {
		this.analista = analista;
	}
	/**
	 * @return the analistaTeamComercial
	 */
	public String getAnalistaTeamComercial() {
		return analistaTeamComercial;
	}
	/**
	 * @param analistaTeamComercial the analistaTeamComercial to set
	 */
	public void setAnalistaTeamComercial(String analistaTeamComercial) {
		this.analistaTeamComercial = analistaTeamComercial;
	}
	/**
	 * @return the idInternoValor
	 */
	public Long getIdInternoValor() {
		return idInternoValor;
	}
	/**
	 * @param idInternoValor the idInternoValor to set
	 */
	public void setIdInternoValor(Long idInternoValor) {
		this.idInternoValor = idInternoValor;
	}
	
	//************************************************************************//

	/**
	 * @return the saldoDisponible
	 */
	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}

	/**
	 * @param saldoDisponible the saldoDisponible to set
	 */
	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idInternoValor == null) ? 0 : idInternoValor.hashCode());
		result = prime * result
				+ ((sistemaOrigen == null) ? 0 : sistemaOrigen.hashCode());
		result = prime * result
				+ ((tipoCheque == null) ? 0 : tipoCheque.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		ConsultaSoporteResultadoBusquedaChequeRechazado other = (ConsultaSoporteResultadoBusquedaChequeRechazado) obj;
		if (sistemaOrigen != other.sistemaOrigen) {
			return false;
		}
		if (SistemaEnum.SHIVA.equals(this.sistemaOrigen)) {
			if (idInternoValor == null) {
				if (other.idInternoValor != null) {
					return false;
				}
			} else if (!idInternoValor.equals(other.idInternoValor)) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * @return the idTipoCheque
	 */
	public String getIdTipoCheque() {
		return idTipoCheque;
	}

	/**
	 * @param idTipoCheque the idTipoCheque to set
	 */
	public void setIdTipoCheque(String idTipoCheque) {
		this.idTipoCheque = idTipoCheque;
	}

	public BigDecimal getMontoARevertir() {
		if (SistemaEnum.SHIVA.equals(this.sistemaOrigen)) {
			return this.importe.subtract(this.saldoDisponible);	
		}
		return this.importeCheque;
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
	 * @return the clienteCheques
	 */
	public List<ClienteBean> getClienteCheques() {
		return clienteCheques;
	}

	/**
	 * @param clienteCheques the clienteCheques to set
	 */
	public void setClienteCheques(List<ClienteBean> clienteCheques) {
		this.clienteCheques = clienteCheques;
	}

	/**
	 * @return the codBancoDeCobro
	 */
	public String getCodBancoDeCobro() {
		return codBancoDeCobro;
	}

	/**
	 * @param codBancoDeCobro the codBancoDeCobro to set
	 */
	public void setCodBancoDeCobro(String codBancoDeCobro) {
		this.codBancoDeCobro = codBancoDeCobro;
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

	public String getCopropietario() {
		return copropietario;
	}

	public void setCopropietario(String copropietario) {
		this.copropietario = copropietario;
	}

	
	
}
