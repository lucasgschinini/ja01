package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;


@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
	@JsonSubTypes({
		@Type(value = ConsultaSoporteResultadoBusquedaChequeRechazadoDto.class, name = "ConsultaSoporteResultadoBusquedaChequeRechazadoDto"),
	})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaSoporteResultadoBusquedaChequeRechazadoDto extends GestionDto{
	private static final long serialVersionUID = 20170531L;
	
	private String sistemaOrigen;
	private String tipoCheque;
	private String idTipoCheque;
	private String codBancoOrigen;
	private String descripcionBancoOrigen;
	private String nroCheque;
	private String fechaDeposito;
	private String fechaRecepcion;
	private String fechaVenc;
	private String moneda;
	private String importe;
	private String importeCheque;
	private String acuerdo;
	private String bancoDeposito;
	private String cuentaDeposito;
	private String estado;
	private String empresa;
	private String segmento;
	private String analista;
	private String copropietario;
	private String analistaTeamComercial;
	private String idInternoValor;
	private String idInternoIce;
	
	private String saldoDisponible;
	
	private String montoARevertir;
	private String montoARembolsar;
	
	private String codBancoDeCobro;
	
	private List<ClienteDto> clientes = new ArrayList<ClienteDto>();
	
	public ConsultaSoporteResultadoBusquedaChequeRechazadoDto() {}

	
/*****************************************************************************/
// Getter and Setter
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
	public String getFechaDeposito() {
		return fechaDeposito;
	}

	/**
	 * @param fechaDeposito the fechaDeposito to set
	 */
	public void setFechaDeposito(String fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}

	/**
	 * @return the fechaRecepcion
	 */
	public String getFechaRecepcion() {
		return fechaRecepcion;
	}

	/**
	 * @param fechaRecepcion the fechaRecepcion to set
	 */
	public void setFechaRecepcion(String fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	/**
	 * @return the fechaVenc
	 */
	public String getFechaVenc() {
		return fechaVenc;
	}

	/**
	 * @param fechaVenc the fechaVenc to set
	 */
	public void setFechaVenc(String fechaVenc) {
		this.fechaVenc = fechaVenc;
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the importeCheque
	 */
	public String getImporteCheque() {
		return importeCheque;
	}

	/**
	 * @param importeCheque the importeCheque to set
	 */
	public void setImporteCheque(String importeCheque) {
		this.importeCheque = importeCheque;
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
	public String getIdInternoValor() {
		return idInternoValor;
	}

	/**
	 * @param idInternoValor the idInternoValor to set
	 */
	public void setIdInternoValor(String idInternoValor) {
		this.idInternoValor = idInternoValor;
	}
	/**
	 * @return the copropietario
	 */
	public String getCopropietario() {
		return copropietario;
	}

	
	/**
	 * @return the saldoDisponible
	 */
	public String getSaldoDisponible() {
		return saldoDisponible;
	}


	/**
	 * @param saldoDisponible the saldoDisponible to set
	 */
	public void setSaldoDisponible(String saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	/**
	 * @param copropietario the copropietario to set
	 */
	public void setCopropietario(String copropietario) {
		this.copropietario = copropietario;
	}

	public String getIdPantalla() {
		StringBuffer id = new StringBuffer();
		if (SistemaEnum.SHIVA.getDescripcion().equals(this.sistemaOrigen)) {
			id.append(SistemaEnum.SHIVA.name());
			id.append("_");
			id.append(this.idInternoValor);
		} else {
			id.append(SistemaEnum.ICE.name());
			id.append("_");
			id.append(this.idInternoIce);
		}
		return id.toString();
	}


	public String getClaseString() {
		return this.getClass().getSimpleName();
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


	public String getSistemaOrigen() {
		return sistemaOrigen;
	}


	public void setSistemaOrigen(String sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}


	/**
	 * @return the montoARevertir
	 */
	public String getMontoARevertir() {
		return montoARevertir;
	}


	/**
	 * @param montoARevertir the montoARevertir to set
	 */
	public void setMontoARevertir(String montoARevertir) {
		this.montoARevertir = montoARevertir;
	}


	/**
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}


	/**
	 * @param importe the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}


	/**
	 * @return the clientes
	 */
	public List<ClienteDto> getClientes() {
		return clientes;
	}


	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(List<ClienteDto> clientes) {
		this.clientes = clientes;
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
	public String getIdInternoIce() {
		return idInternoIce;
	}


	/**
	 * @param idInternoIce the idInternoIce to set
	 */
	public void setIdInternoIce(String idInternoIce) {
		this.idInternoIce = idInternoIce;
	}


	/**
	 * @return the montoARembolsar
	 */
	public String getMontoARembolsar() {
		return montoARembolsar;
	}


	/**
	 * @param montoARembolsar the montoARembolsar to set
	 */
	public void setMontoARembolsar(String montoARembolsar) {
		this.montoARembolsar = montoARembolsar;
	}
}