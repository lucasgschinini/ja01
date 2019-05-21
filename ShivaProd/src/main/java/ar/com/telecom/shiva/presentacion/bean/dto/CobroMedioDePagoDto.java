package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CobroMedioDePagoDto extends DTO {
	
	private static final long serialVersionUID = 1L;

	private String clientesLegados;
	private Long idMedioPago;
	private String idMpTipoCredito;
	private String descMpTipoCredito;
	private String importe;
	private String nroActa;
	private String fecha;
	private SiNoEnum checkMedioPagoEnProceso;
	private String nroCompensacion;
	// Compensaciones IIBB
	private String provincia;
	private String idProvincia;
	// Compensaciones 
	private String subTipo;
	private String subTipoDescripcion;
	private String nroDeDocumentoInterno;

	
	private String monedaImporteUtilizar;
	
	private String monedaImporteUtilizarSigno2;


	//Para el mapeo - evitar repetidos
	private List<CobroMedioPagoClienteDto> listaMedioPagoCliente;
	private List<DocumentoCapDto> listaDocumentoCap;
	
	
	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getNroActa() {
		return nroActa;
	}

	public void setNroActa(String nroActa) {
		this.nroActa = nroActa;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public SiNoEnum getCheckMedioPagoEnProceso() {
		return checkMedioPagoEnProceso;
	}

	public void setCheckMedioPagoProceso(SiNoEnum checkMedioPagoProceso) {
		this.checkMedioPagoEnProceso = checkMedioPagoProceso;
	}

	public List<CobroMedioPagoClienteDto> getListaMedioPagoCliente() {
		return listaMedioPagoCliente;
	}

	public void setListaMedioPagoCliente(
			List<CobroMedioPagoClienteDto> listaMedioPagoCliente) {
		this.listaMedioPagoCliente = listaMedioPagoCliente;
	}

	public Long getIdMedioPago() {
		return idMedioPago;
	}

	public void setIdMedioPago(Long idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	public String getClientesLegados() {
		return clientesLegados;
	}

	public void setClientesLegados(String clientesLegados) {
		this.clientesLegados = clientesLegados;
		
		if (!Validaciones.isNullOrEmpty(clientesLegados)) {
			listaMedioPagoCliente = new ArrayList<CobroMedioPagoClienteDto>();
			for (String idCliente : clientesLegados.split(" - ")) {
				CobroMedioPagoClienteDto clientes = new CobroMedioPagoClienteDto();
				clientes.setIdClienteLegado(idCliente);		
				listaMedioPagoCliente.add(clientes);
			}
		}
	}

	public String getNroCompensacion() {
		return nroCompensacion;
	}

	public void setNroCompensacion(String nroCompensacion) {
		this.nroCompensacion = nroCompensacion;
	}

	public TipoCreditoEnum getMpTipoCredito() {
		return TipoCreditoEnum.getEnumByValor(this.idMpTipoCredito);
	}

	public String getIdMpTipoCredito() {
		return idMpTipoCredito;
	}

	public void setIdMpTipoCredito(String idMpTipoCredito) {
		this.idMpTipoCredito = idMpTipoCredito;
	}

	public String getDescMpTipoCredito() {
		return descMpTipoCredito;
	}

	public void setDescMpTipoCredito(String descMpTipoCredito) {
		this.descMpTipoCredito = descMpTipoCredito;
	}

	public String getMonedaImporteUtilizar() {
		return monedaImporteUtilizar;
	}

	public void setMonedaImporteUtilizar(String monedaImporteUtilizar) {
		this.monedaImporteUtilizar = monedaImporteUtilizar;
	}

	/**
	 * @return the monedaImporteUtilizarSigno2
	 */
	public String getMonedaImporteUtilizarSigno2() {
		return monedaImporteUtilizarSigno2;
	}

	/**
	 * @param monedaImporteUtilizarSigno2 the monedaImporteUtilizarSigno2 to set
	 */
	public void setMonedaImporteUtilizarSigno2(String monedaImporteUtilizarSigno2) {
		this.monedaImporteUtilizarSigno2 = monedaImporteUtilizarSigno2;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the idProvincia
	 */
	public String getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @param idProvincia the idProvincia to set
	 */
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}

	/**
	 * @return the subTipo
	 */
	public String getSubTipo() {
		return subTipo;
	}

	/**
	 * @param subtipo the subTipo to set
	 */
	public void setSubTipo(String subtipo) {
		this.subTipo = subtipo;
	}

	/**
	 * @return the nroDeDocumentoInterno
	 */
	public String getNroDeDocumentoInterno() {
		return nroDeDocumentoInterno;
	}

	/**
	 * @param nroDeDocumentoInterno the nroDeDocumentoInterno to set
	 */
	public void setNroDeDocumentoInterno(String nroDeDocumentoInterno) {
		this.nroDeDocumentoInterno = nroDeDocumentoInterno;
	}

	/**
	 * @return the listaDocumentoCap
	 */
	public List<DocumentoCapDto> getListaDocumentoCap() {
		return listaDocumentoCap;
	}

	/**
	 * @param listaDocumentoCap the listaDocumentoCap to set
	 */
	public void setListaDocumentoCap(List<DocumentoCapDto> listaDocumentoCap) {
		this.listaDocumentoCap = listaDocumentoCap;
	}

	/**
	 * @return the subTipoDescripcion
	 */
	public String getSubTipoDescripcion() {
		return subTipoDescripcion;
	}

	/**
	 * @param subTipoDescripcion the subTipoDescripcion to set
	 */
	public void setSubTipoDescripcion(String subTipoDescripcion) {
		this.subTipoDescripcion = subTipoDescripcion;
	}

	/**
	 * @return the importeFixed
	 */
	public String getImporteFixed() {
		if(!Validaciones.isNullEmptyOrDash(importe)){
		BigDecimal importeFixed = Utilidad.formatCurrencyBD(new BigDecimal(Utilidad.quitarComaDecimal(importe)), 2);
			return Utilidad.formatCurrency(importeFixed, false, true);
		}else{
			return importe;
		}
	}
}