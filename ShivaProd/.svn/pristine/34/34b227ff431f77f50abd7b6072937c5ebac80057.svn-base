package ar.com.telecom.shiva.presentacion.bean;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ArchivoAVCFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.TalonarioFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroOnlineFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteOperacionMasivaFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteTareasPendientesFiltro;
import ar.com.telecom.shiva.presentacion.bean.paginacion.ControlPaginacion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.CreditosControlPaginacion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.DebitosControlPaginacion;

public class ParametrosSesion implements Serializable {

	private static final long serialVersionUID = 1;
	
	private BoletaSinValorFiltro boletaSinValorFiltro = new BoletaSinValorFiltro();
	private ArchivoAVCFiltro archivoAVCFiltro = new ArchivoAVCFiltro();
	private TalonarioFiltro talonarioFiltro = new TalonarioFiltro();
	private VistaSoporteTareasPendientesFiltro tareasFiltro = null;
	private VistaSoporteCobroOnlineFiltro cobroFiltro = new VistaSoporteCobroOnlineFiltro();
	private VistaSoporteDescobroFiltro descobroFiltro = new VistaSoporteDescobroFiltro();
	private VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro = new VistaSoporteOperacionMasivaFiltro();
	private VistaSoporteLegajoChequeRechazadoFiltro legajoChqueRechazadoFiltro = new VistaSoporteLegajoChequeRechazadoFiltro();	
	
	private DebitosControlPaginacion debPaginado;
	private CreditosControlPaginacion credPaginado;
	
	private ControlPaginacion controlPaginado;
	
	private Boolean volviendoABusqueda = false;
	private Boolean volviendoAResultadoConciliacion = false;
	private Boolean archivoValidado = false;
	private boolean activarBoton = false;
	
	private List<CodigoOperacionExternaDto> codigosOperacionesExternasAGuardar = new ArrayList<CodigoOperacionExternaDto>();
	private List<ComprobanteDto> comprobantesAGuardar = new ArrayList<ComprobanteDto>();
	private List<ComprobanteDto> comprobantesConIdPantalla = new ArrayList<ComprobanteDto>();
	private DescobroDto descobroDtoDetalle;
	// No toda la navegacion esta hecha de esta manera
	private Deque<String> caminoDeVuelta = new ArrayDeque<String>();
	
	private Object archivoOperacionMasiva;
	private OperacionMasivaDto operacionMasivaDto; 
	
	/****************************************************************************************
	 * Getter & Setters
	 ***************************************************************************************/
	

	public Boolean getVolviendoABusqueda() {
		return volviendoABusqueda;
	}

	public void setVolviendoABusqueda(Boolean volviendoABusqueda) {
		this.volviendoABusqueda = volviendoABusqueda;
	}

	public Boolean getVolviendoAResultadoConciliacion() {
		return volviendoAResultadoConciliacion;
	}

	public void setVolviendoAResultadoConciliacion(
			Boolean volviendoAResultadoConciliacion) {
		this.volviendoAResultadoConciliacion = volviendoAResultadoConciliacion;
	}

	public BoletaSinValorFiltro getBoletaSinValorFiltro() {
		return boletaSinValorFiltro;
	}

	public void setBoletaSinValorFiltro(BoletaSinValorFiltro boletaSinValorFiltro) {
		this.boletaSinValorFiltro = boletaSinValorFiltro;
	}

	public ArchivoAVCFiltro getArchivoAVCFiltro() {
		return archivoAVCFiltro;
	}

	public void setArchivoAVCFiltro(ArchivoAVCFiltro archivoAVCFiltro) {
		this.archivoAVCFiltro = archivoAVCFiltro;
	}

	public TalonarioFiltro getTalonarioFiltro() {
		return talonarioFiltro;
	}

	public void setTalonarioFiltro(TalonarioFiltro talonarioFiltro) {
		this.talonarioFiltro = talonarioFiltro;
	}
	
	public VistaSoporteCobroOnlineFiltro getCobroFiltro() {
		return cobroFiltro;
	}
	
	public void setCobroFiltro(VistaSoporteCobroOnlineFiltro cobroFiltro) {
		this.cobroFiltro = cobroFiltro;
	}

	public VistaSoporteOperacionMasivaFiltro getOperacionMasivaFiltro() {
		return operacionMasivaFiltro;
	}

	public void setOperacionMasivaFiltro(
			VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro) {
		this.operacionMasivaFiltro = operacionMasivaFiltro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public VistaSoporteTareasPendientesFiltro getTareasFiltro() {
		return tareasFiltro;
	}

	public void setTareasFiltro(VistaSoporteTareasPendientesFiltro tareasFiltro) {
		this.tareasFiltro = tareasFiltro;
	}

	public DebitosControlPaginacion getDebPaginado() {
		return debPaginado;
	}

	public void setDebPaginado(DebitosControlPaginacion debPaginado) {
		this.debPaginado = debPaginado;
	}

	public CreditosControlPaginacion getCredPaginado() {
		return credPaginado;
	}

	public void setCredPaginado(CreditosControlPaginacion credPaginado) {
		this.credPaginado = credPaginado;
	}

	/**
	 * @return the descobroFiltro
	 */
	public VistaSoporteDescobroFiltro getDescobroFiltro() {
		return descobroFiltro;
	}

	/**
	 * @param descobroFiltro the descobroFiltro to set
	 */
	public void setDescobroFiltro(VistaSoporteDescobroFiltro descobroFiltro) {
		this.descobroFiltro = descobroFiltro;
	}

	public List<ComprobanteDto> getComprobantesAGuardar() {
		return comprobantesAGuardar;
	}

	public void setComprobantesAGuardar(List<ComprobanteDto> comprobantesAGuardar) {
		this.comprobantesAGuardar = comprobantesAGuardar;
	}

	public List<ComprobanteDto> getComprobantesConIdPantalla() {
		return comprobantesConIdPantalla;
	}

	public void setComprobantesConIdPantalla(
			List<ComprobanteDto> comprobantesConIdPantalla) {
		this.comprobantesConIdPantalla = comprobantesConIdPantalla;
	}

	public DescobroDto getDescobroDtoDetalle() {
		return descobroDtoDetalle;
	}

	public void setDescobroDtoDetalle(DescobroDto descobroDtoDetalle) {
		this.descobroDtoDetalle = descobroDtoDetalle;
	}

	/**
	 * @return the caminoDeVuelta
	 */
	public Deque<String> getCaminoDeVuelta() {
		return caminoDeVuelta;
	}

	/**
	 * @param caminoDeVuelta the caminoDeVuelta to set
	 */
	public void setCaminoDeVuelta(Deque<String> caminoDeVuelta) {
		this.caminoDeVuelta = caminoDeVuelta;
	}

	public Object getArchivoOperacionMasiva() {
		return archivoOperacionMasiva;
	}

	public void setArchivoOperacionMasiva(Object archivoOperacionMasiva) {
		this.archivoOperacionMasiva = archivoOperacionMasiva;
	}

	public OperacionMasivaDto getOperacionMasivaDto() {
		return operacionMasivaDto;
	}

	public void setOperacionMasivaDto(OperacionMasivaDto operacionMasivaDto) {
		this.operacionMasivaDto = operacionMasivaDto;
	}

	public Boolean getArchivoValidado() {
		return archivoValidado;
	}

	public void setArchivoValidado(Boolean archivoValidado) {
		this.archivoValidado = archivoValidado;
	}

	public boolean isActivarBoton() {
		return activarBoton;
	}

	public void setActivarBoton(boolean activarBoton) {
		this.activarBoton = activarBoton;
	}

	/**
	 * @return the controlPaginado
	 */
	public ControlPaginacion getControlPaginado() {
		return controlPaginado;
	}

	/**
	 * @param controlPaginado the controlPaginado to set
	 */
	public void setControlPaginado(ControlPaginacion controlPaginado) {
		this.controlPaginado = controlPaginado;
	}

	/**
	 * @return the legajoChqueRechazadoFiltro
	 */
	public VistaSoporteLegajoChequeRechazadoFiltro getLegajoChqueRechazadoFiltro() {
		return legajoChqueRechazadoFiltro;
	}

	/**
	 * @param legajoChqueRechazadoFiltro the legajoChqueRechazadoFiltro to set
	 */
	public void setLegajoChqueRechazadoFiltro(VistaSoporteLegajoChequeRechazadoFiltro legajoChqueRechazadoFiltro) {
		this.legajoChqueRechazadoFiltro = legajoChqueRechazadoFiltro;
	}

	/**
	 * @return the codigosOperacionesExternasAGuardar
	 */
	public List<CodigoOperacionExternaDto> getCodigosOperacionesExternasAGuardar() {
		return codigosOperacionesExternasAGuardar;
	}

	/**
	 * @param codigosOperacionesExternasAGuardar the codigosOperacionesExternasAGuardar to set
	 */
	public void setCodigosOperacionesExternasAGuardar(
			List<CodigoOperacionExternaDto> codigosOperacionesExternasAGuardar) {
		this.codigosOperacionesExternasAGuardar = codigosOperacionesExternasAGuardar;
	}
}
