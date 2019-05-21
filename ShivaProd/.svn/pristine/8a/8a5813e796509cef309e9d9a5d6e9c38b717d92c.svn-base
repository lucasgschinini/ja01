package ar.com.telecom.shiva.presentacion.bean.paginacion;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaDeudaEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionFactura;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionPaginadoDebito;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.Cliente;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.InformacionParaPaginadoDebito;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroDebitoFiltro;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaCalipsoDebito;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaMicDebito;

public class DebitosControlPaginacion extends ControlPaginacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ConfCobroDebitoFiltro filtro;
	
	private Map<Integer, InfoPaginaCalipsoDebito> infoPaginaCalipso;
	
	private Map<Integer, InfoPaginaMicDebito> infoPaginaMic;
	
	private int totalCalipso = 0;
	
	private int totalMic = 0;
	
	private InformacionParaPaginadoDebito inicioCalipso;
	
	private MicInformacionPaginadoDebito inicioMic;
	
	public DebitosControlPaginacion() {
		super();
		this.cantPorPagina = ConstantesCobro.CANTIDAD_POR_PAGINA_DEB;
		
		this.infoPaginaCalipso = new HashMap<Integer, InfoPaginaCalipsoDebito>();
		this.infoPaginaMic = new HashMap<Integer, InfoPaginaMicDebito>();
		
		this.inicioCalipso = reiniciarInformacionPaginadoCalipso();
		this.inicioMic = reiniciarInformacionPaginadoMIC();
		
		this.filtro = new ConfCobroDebitoFiltro();
	}
	
	public DebitosControlPaginacion(ConfCobroDebitoFiltro filtro) {
		super();
		this.cantPorPagina = ConstantesCobro.CANTIDAD_POR_PAGINA_DEB;
		
		this.infoPaginaCalipso = new HashMap<Integer, InfoPaginaCalipsoDebito>();
		this.infoPaginaMic = new HashMap<Integer, InfoPaginaMicDebito>();
		
		this.inicioCalipso = reiniciarInformacionPaginadoCalipso();
		this.inicioMic = reiniciarInformacionPaginadoMIC();
		
		this.filtro = filtro;
	}
	
	public void limpiarPaginado(ConfCobroDebitoFiltro filtro) {
		this.paginaActual = 1;
		this.totalPaginas = 0;
		this.cantPorPagina = ConstantesCobro.CANTIDAD_POR_PAGINA_DEB;
		this.totalRegistros = 0;
		this.cantRegistrosMostrados = 0;
		this.filtro = filtro;
	}
	
	/**
	 * 
	 * @return
	 */
	public InformacionParaPaginadoDebito reiniciarInformacionPaginadoCalipso() {
		InformacionParaPaginadoDebito infoPagCalipso = new InformacionParaPaginadoDebito();
		infoPagCalipso.setCantidadDeRegistrosARetornar(new BigInteger(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_DEB)));
		return infoPagCalipso;
	}
	
	/**
	 * 
	 * @return
	 */
	public MicInformacionPaginadoDebito reiniciarInformacionPaginadoMIC() {
		MicInformacionPaginadoDebito infoPagMic = new MicInformacionPaginadoDebito();
		infoPagMic.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_DEB);
		return infoPagMic;
	}
	
	/**
	 * Me permite armar las distintas entradas de acuerdo al filtro
	 * @return
	 */
	public EntradaCalipsoDeudaWS obtenerEntradaCalipso() {
		EntradaCalipsoDeudaWS eEntrada = new EntradaCalipsoDeudaWS();
		try {
			
			String[] idsClientes = this.filtro.getIdsClientes().trim().split(",");
			Cliente cliente = null;
			for (String idCliente : idsClientes) {
				// En este punto siempre almenos un cliente es de menos o igual 10 caracteres
				if (idCliente.trim().length() <= 10) {
					cliente = new Cliente();
					cliente.setIdClienteLegado(new BigInteger(idCliente));
					eEntrada.getListaClientes().add(cliente);
				}	
			}
			
			IdDocumento idDocumento = new IdDocumento();
			if (!Validaciones.isNullOrEmpty(this.filtro.getTipoDoc())) {
				if (!TipoComprobanteEnum.DUC.name().equalsIgnoreCase(this.filtro.getTipoDoc())) {
					idDocumento.setTipoComprobante(TipoComprobanteEnum.getEnumByName(this.filtro.getTipoDoc()));
				} else {
					idDocumento.setTipoComprobante(TipoComprobanteEnum.TOD);
				}
			} else {
				idDocumento.setTipoComprobante(TipoComprobanteEnum.TOD);
			}
			if (!Validaciones.isNullOrEmpty(this.getFiltro().getNroDoc())) {
				idDocumento.setNumeroComprobante(this.getFiltro().getNroDoc().trim());
			}
			if (!Validaciones.isNullOrEmpty(this.getFiltro().getNroDoc())) {
				String nroDoc = this.getFiltro().getNroDoc().trim();
				if (Validaciones.validarNroDocumentoDebitosCalipso(nroDoc)) {
					idDocumento.setClaseComprobante(
							TipoClaseComprobanteEnum.getEnumByName(nroDoc.split("-")[0]));
					idDocumento.setSucursalComprobante(nroDoc.split("-")[1]);
					idDocumento.setNumeroComprobante(nroDoc.split("-")[2]);
				}
			}
			eEntrada.setIdDocumento(idDocumento);
			
			if (!Validaciones.isNullOrEmpty(this.filtro.getMoneda())) {
				eEntrada.setMoneda(MonedaEnum.getEnumBySigla(this.filtro.getMoneda()));
			} else {
				eEntrada.setMoneda(MonedaEnum.TOD);
			}
			if (Validaciones.isNumeric(this.getFiltro().getAcuerdoFac())) {
				eEntrada.setAcuerdo(new BigInteger(this.getFiltro().getAcuerdoFac().trim()));
			}
			if (!Validaciones.isNullOrEmpty(this.filtro.getVencDesde())) {
				eEntrada.setFechaVencimientoDesde(Utilidad.parseDatePickerString(this.filtro.getVencDesde().trim()));
			}
			if (!Validaciones.isNullOrEmpty(this.filtro.getVencHasta())) {
				eEntrada.setFechaVencimientoHasta(Utilidad.parseDatePickerString(this.filtro.getVencHasta().trim()));
			}
			if(!Validaciones.isNullEmptyOrDash(this.filtro.getFechaTipoCambio())){
				eEntrada.setFechaCotizacion(Utilidad.parseDatePickerString(this.filtro.getFechaTipoCambio()));
			}
			
		} catch (ParseException e) {
			Traza.error(getClass(), "Error al parsear fecha", e);
		}
		return eEntrada;
	}
	
	/**
	 * Me permite armar las distintas entradas de acuerdo al filtro
	 * @return
	 */
	public MicConsultaDeudaEntrada obtenerEntradaMic() {
		MicConsultaDeudaEntrada eEntrada = new MicConsultaDeudaEntrada();

		try {
			String[] idsClientes = this.filtro.getIdsClientes().trim().split(",");
			for (String idCliente : idsClientes) {
				// En este punto siempre almenos un cliente es de menos o igual 10 caracteres
				if (idCliente.trim().length() <= 10) {
					eEntrada.getListaClientes().add(new BigInteger(idCliente));
				}
			}

			eEntrada.setInformacionFactura(new MicInformacionFactura());
			if (!Validaciones.isNullOrEmpty(this.filtro.getTipoDoc())) {
				eEntrada.getInformacionFactura().setTipoComprobante(TipoComprobanteEnum.getEnumByName(this.filtro.getTipoDoc()));	
			} else {
				eEntrada.getInformacionFactura().setTipoComprobante(TipoComprobanteEnum.TOD);
			}
			
			if (!TipoComprobanteEnum.DUC.name().equalsIgnoreCase(this.filtro.getTipoDoc())) {
				if (!Validaciones.isNullOrEmpty(this.getFiltro().getNroDoc())) {
					String nroDoc = this.getFiltro().getNroDoc().trim();
					if (Validaciones.validarNroDocumentoMIC_1_debitos(nroDoc)) {
						eEntrada.getInformacionFactura().setClaseComprobante(
								TipoClaseComprobanteEnum.getEnumByName(nroDoc.split("-")[0]));
						eEntrada.getInformacionFactura().setSucursalComprobante(nroDoc.split("-")[1]);
						eEntrada.getInformacionFactura().setNumeroComprobante(nroDoc.split("-")[2]);
					}
					if (Validaciones.validarNroDocumentoMIC_2_debitos(nroDoc)) {
						eEntrada.getInformacionFactura().setSucursalComprobante(nroDoc.split("-")[0]);
						eEntrada.getInformacionFactura().setNumeroComprobante(nroDoc.split("-")[1]);
					}
				}
			}
			// Con nnnn-nnnnnnnnnnn o AB-nnnn-nnnnnnnnnnn y referencia pueden viajar juntos
			if (!Validaciones.isNullOrEmpty(this.getFiltro().getRefMIC())) {
				eEntrada.getInformacionFactura().setNumeroReferenciaMIC(this.getFiltro().getRefMIC().trim());
			} 
			if (!Validaciones.isNullOrEmpty(this.getFiltro().getNroDoc())){
				//En caso de ser DUC
				if (Validaciones.validarNroDocumentoDuc(this.getFiltro().getNroDoc().trim())) {
					eEntrada.getInformacionFactura().setNumeroReferenciaMIC(this.getFiltro().getNroDoc().trim());
				}
			}
			if (Validaciones.isNumeric(this.getFiltro().getAcuerdoFac())) {
				eEntrada.getInformacionFactura().setAcuerdo(new BigInteger(this.getFiltro().getAcuerdoFac().trim()));
			}
			if (!Validaciones.isNullOrEmpty(this.filtro.getVencDesde())) {
				eEntrada.getInformacionFactura().setFechaVencimientoDesde(Utilidad.parseDatePickerString(this.filtro.getVencDesde().trim()));
			}
			if (!Validaciones.isNullOrEmpty(this.filtro.getVencHasta())) {
				eEntrada.getInformacionFactura().setFechaVencimientoHasta(Utilidad.parseDatePickerString(this.filtro.getVencHasta().trim()));
			}
		} catch (ParseException e) {
			Traza.error(getClass(), "Error al parsear fecha", e);
		}
		
		return eEntrada;
	
	}
	
	/**
	 * 
	 * @param accion
	 * @param entradaCalipso
	 * @param entradaMic
	 */
	public void setPuntoDeInicio(String accion, EntradaCalipsoDeudaWS entradaCalipso, MicConsultaDeudaEntrada entradaMic) {
		entradaCalipso.setInformacionParaPaginado(this.inicioCalipso);
		entradaMic.setInformacionPaginado(this.inicioMic);
		
		if (ConstantesCobro.PAGINADO_ANTERIOR.equals(accion)) {
			this.paginaActual--;
			if (this.paginaActual > 1) {
				entradaCalipso.setInformacionParaPaginado(this.infoPaginaCalipso.get(this.paginaActual - 1).getFin());
				entradaMic.setInformacionPaginado(this.infoPaginaMic.get(this.paginaActual - 1).getFin());
			} 
		} else {
			if (ConstantesCobro.PAGINADO_SIGUIENTE.equals(accion)) {
				entradaCalipso.setInformacionParaPaginado(this.infoPaginaCalipso.get(this.paginaActual).getFin());
				entradaMic.setInformacionPaginado(this.infoPaginaMic.get(this.paginaActual).getFin());
				if (paginaActual < this.totalPaginas) {
					this.paginaActual++;
				}
			}
		}
		List<Integer> keyList = new ArrayList<Integer>();
		keyList.addAll(this.infoPaginaCalipso.keySet());
		Collections.sort(keyList);
		for (Integer pag : keyList) {
			if (this.paginaActual < pag) {
				this.infoPaginaCalipso.remove(pag);
			}
		}
		keyList = new ArrayList<Integer>();
		keyList.addAll(this.infoPaginaMic.keySet());
		Collections.sort(keyList);
		for (Integer pag : keyList) {
			if (this.paginaActual < pag) {
				this.infoPaginaMic.remove(pag);
			}
		}
	}
	
	/**
	 * Permite clonar los datos 
	 */
	public DebitosControlPaginacion clone() {
		DebitosControlPaginacion clone = new DebitosControlPaginacion();
		clone.setFiltro(this.filtro.clone());
		clone.setPaginaActual(this.paginaActual);
		clone.setTotalPaginas(this.totalPaginas);
		clone.setCantPorPagina(this.cantPorPagina);
		clone.setTotalRegistros(this.totalRegistros);
		clone.setCantRegistrosMostrados(this.cantRegistrosMostrados);
		clone.setInhabilitarAnt(this.inhabilitarAnt);
		clone.setInhabilitarSig(this.inhabilitarSig);
		return clone;
	}
	
	public ConfCobroDebitoFiltro getFiltro() {
		return filtro;
	}

	public void setFiltro(ConfCobroDebitoFiltro filtro) {
		this.filtro = filtro;
	}

	public Map<Integer, InfoPaginaCalipsoDebito> getInfoPaginaCalipso() {
		return infoPaginaCalipso;
	}

	public void setInfoPaginaCalipso(
			Map<Integer, InfoPaginaCalipsoDebito> infoPaginaCalipso) {
		this.infoPaginaCalipso = infoPaginaCalipso;
	}

	public Map<Integer, InfoPaginaMicDebito> getInfoPaginaMic() {
		return infoPaginaMic;
	}

	public void setInfoPaginaMic(Map<Integer, InfoPaginaMicDebito> infoPaginaMic) {
		this.infoPaginaMic = infoPaginaMic;
	}

	public InformacionParaPaginadoDebito getInicioCalipso() {
		return inicioCalipso;
	}

	public void setInicioCalipso(InformacionParaPaginadoDebito inicioCalipso) {
		this.inicioCalipso = inicioCalipso;
	}

	public MicInformacionPaginadoDebito getInicioMic() {
		return inicioMic;
	}

	public void setInicioMic(MicInformacionPaginadoDebito inicioMic) {
		this.inicioMic = inicioMic;
	}

	public int getTotalCalipso() {
		return totalCalipso;
	}

	public void setTotalCalipso(int totalCalipso) {
		this.totalCalipso = totalCalipso;
	}

	public int getTotalMic() {
		return totalMic;
	}

	public void setTotalMic(int totalMic) {
		this.totalMic = totalMic;
	}
		
}