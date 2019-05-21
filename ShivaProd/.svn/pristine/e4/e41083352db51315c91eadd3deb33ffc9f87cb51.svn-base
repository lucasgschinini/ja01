package ar.com.telecom.shiva.negocio.bean;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import ar.com.telecom.shiva.base.enumeradores.EstadoBloqueoSapEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDocSapEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRenglonSapEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapConsultaPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapConsultaPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Partida;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Proveedor;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.ResultadoInvocacion;
import ar.com.telecom.shiva.negocio.servicios.IProveedorCapServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;
import ar.com.telecom.shiva.presentacion.bean.dto.PartidaDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroDocCapFiltro;

public class BuilderConsultaSap {

	// Quit
	private Map<String, ClienteProveedorSapVo> map = new HashMap<String, ClienteProveedorSapVo>();
	private int estado = -1;
	private List<String> listaErroresCuitNoEncontrados = new ArrayList<String>();
	private ResultadoInvocacion resultadoProveedor;
	private List<DocumentoCapDto> listaDosCap = new ArrayList<DocumentoCapDto>();
	List<DocumentoCapDto> listaVinculados = new ArrayList<DocumentoCapDto>();
	private ConfCobroDocCapFiltro filtro;

	private Acumulador<String> acumuladorCapPesos = new Acumulador<String>();
	private Acumulador<String> acumuladorCapEmiNoPesos = new Acumulador<String>();
	private Acumulador<String> acumuladorCapCobroNoPesos = new Acumulador<String>();
	private IProveedorCapServicio proveedorCapServicio;
	
	public BuilderConsultaSap() {}

	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	/**
	 * @return the listaErroresCuitNoEncontrados
	 */
	public List<String> getListaErroresCuitNoEncontrados() {
		return listaErroresCuitNoEncontrados;
	}
	/**
	 * @param listaErroresCuitNoEncontrados the listaErroresCuitNoEncontrados to set
	 */
	public void setListaErroresCuitNoEncontrados(
			List<String> listaErroresCuitNoEncontrados) {
		this.listaErroresCuitNoEncontrados = listaErroresCuitNoEncontrados;
	}
	/**
	 * @return the resultadoProveedor
	 */
	public ResultadoInvocacion getResultadoProveedor() {
		return resultadoProveedor;
	}
	/**
	 * @param resultadoProveedor the resultadoProveedor to set
	 */
	public void setResultadoProveedor(ResultadoInvocacion resultadoProveedor) {
		this.resultadoProveedor = resultadoProveedor;
	}
	/**
	 * @return the filtro
	 */
	public ConfCobroDocCapFiltro getFiltro() {
		return filtro;
	}
	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(ConfCobroDocCapFiltro filtro) {
		this.filtro = filtro;
	}
	/**
	 * @return the listaDosCap
	 */
	public List<DocumentoCapDto> getListaDosCap() {
		return listaDosCap;
	}
	/**
	 * @param listaDosCap the listaDosCap to set
	 */
	public void setListaDosCap(List<DocumentoCapDto> listaDosCap) {
		this.listaDosCap = listaDosCap;
	}
	public void addListaClientes(List<ClienteDto> clientes) {
		for(ClienteDto cliente : clientes) {
			if (Validaciones.isNullEmptyOrDash(cliente.getCuit())) {
				Traza.advertencia(getClass(), "El cliente " + cliente.getIdClienteLegado() + " no posee cuit.");
			} else {
				if (!map.containsKey(cliente.getCuit())) {
					map.put(cliente.getCuit(), new ClienteProveedorSapVo(cliente.getCuit(), cliente));
				} else {
					map.get(cliente.getCuit()). addCliente(cliente);
				}
			}
		}
	}
	public EntradaSapConsultaProveedoresWS creearConsultaProveedores() {
		if (map.size() == 0) {
			return null;
		}
		EntradaSapConsultaProveedoresWS entradaSapConsultaProveedores = new EntradaSapConsultaProveedoresWS();
		entradaSapConsultaProveedores.getListaCuits().addAll(this.map.keySet());
		return entradaSapConsultaProveedores;
	}
	public void setearSalidaConsultaProveedores(SalidaSapConsultaProveedoresWS salidaSapConsultaProveedores) {
		if (salidaSapConsultaProveedores.getListaProveedores().isEmpty()) {
			this.estado = PROVEEDORES_NO_ENCONTRADOS;
		} else {
			for (Proveedor proveedor : salidaSapConsultaProveedores.getListaProveedores()) {
				ClienteProveedorSapVo provVo = map.get(proveedor.getCuit());
				provVo.setCodigoBloqueoProveedor(proveedor.getIdBloqueo());
				provVo.setDescripcionBloqueoProveedor(proveedor.getDescripcionBloqueo());
				provVo.setCodigoProveedor(proveedor.getIdProveedor());
			}
			this.estado = PROVEEDORES;
		}
		listaErroresCuitNoEncontrados = salidaSapConsultaProveedores.getListaErroresCuitNoEncontrados();
		resultadoProveedor = salidaSapConsultaProveedores.getResultadoInvocacion();
	}

	/**
	 * 26/06/2018 PI Req 71029 Integración Shiva Central Finance
	 * 
	 * A partir de este requerimiento, se presenta una diferencia en el código de proveedor retornado por la consulta de proveedores
	 * y la consulta de partidas pendientes. El primero retorna el idProveedor que vive en Sap S/4 para el CUIT informado, que es un nuevo valor
	 * unico que unifica los mismos proveedores que viven en los diferentes Sap R/3 (telecom, personal, cable, etc), en tanto que
	 * la consulta de partidas va a retornar el idProveedor que vive en cada R/3. Porque? Porque la imputación se realiza en R/3, por ello
	 * debe tener los datos originales de la partida.
	 * 
	 * Dado que no tenemos nada que nos relacione el idProveedor de S/4 con el que vive en R/3 para luego poder cruzar y armar la gestionabilidad
	 * del documento, lo que vamos a hacer es limitar la busqueda clientes en Sap, al momento de consultar las partidas, modificando la grilla 
	 * de clientes, cambiando los check por los radio.
	 * De esta manera, solo vamos a permitir buscar documentos por un CUIT a la vez, con lo cuál sabremos que las partidas corresponden todas
	 * al mismo idProveedor retornado por S/4, y podremos armar la gestionabilidad de manera correcta.
	 * 
	 * Dicho esto, se modifica el método a continuación para que siempre retorne el primer elemento, ya que se asume que siempre tendrá uno solo. 
	 *  
	 * @param idProveedor
	 * @return
	 */
	private ClienteProveedorSapVo obtenerIdCliente(String idProveedor) {
		for (ClienteProveedorSapVo vo : map.values()) {
			// Retorno el primer elemento del map
			return vo;
		}
		return null;
	}

	public void setearSalidaSapConsultaPartidasWS(SalidaSapConsultaPartidasWS salidaSapConsultaPartidasWS) throws NegocioExcepcion {
		try {
			if (salidaSapConsultaPartidasWS.getListaPartidas().isEmpty()) {
				this.estado = PARTIDAS_NO_ENCONTRADOS;
			} else {
				this.filtro.convertirNumeroLegalSap();
				Set<String> claves = new TreeSet<String>();
				Set<String> clavesFiltradas = new TreeSet<String>();
				
				List<Partida> listaVinculadosPartida = new ArrayList<Partida>();
				
				Date fechaDesde = null;
				Date fechaHasta = null;
				
				if (!Validaciones.isNullOrEmpty(this.filtro.getFechaDesde())) {
					fechaDesde = Utilidad.parseDatePickerString(this.filtro.getFechaDesde());
				}
				
				if (!Validaciones.isNullOrEmpty(this.filtro.getFechaHasta())) {
					fechaHasta = Utilidad.parseDatePickerString(this.filtro.getFechaHasta());
				}
				
				for(Partida partida : salidaSapConsultaPartidasWS.getListaPartidas()) {
					PartidaDto partidaDto = new DocumentoCapDto();
						
					if (Validaciones.isNullOrEmpty(partida.getNumeroDocSAPVinculado())) {
						
						if (this.filtrarDocumentos(partida, fechaDesde, fechaHasta)) {
							mapearPartida(partidaDto, partida);
							DocumentoCapDto dto = (DocumentoCapDto) partidaDto;
							dto.setHuerfano(SiNoEnum.NO);
							if (EstadoBloqueoSapEnum.PESOS.equals(partidaDto.getBloqueoPago())) {
								this.acumuladorCapPesos.aAcumular(partidaDto.getNroDocumentoSap(), partidaDto.getImporteDocMonedaDoc());
//							Logica para no mostrar documentos K$
							} else {
								this.acumuladorCapEmiNoPesos.aAcumular(partidaDto.getNroDocumentoSap(), partidaDto.getImporteDocMonedaDoc());
								this.acumuladorCapCobroNoPesos.aAcumular(partidaDto.getNroDocumentoSap(), partidaDto.getImpDocPES());
								listaDosCap.add(dto);
								clavesFiltradas.add(((DocumentoCapDto)partidaDto).contruirIdReferencia());
							}
						}
						claves.add(this.crearIdReferencia(partida));
					} else {
//						Elimino todos los documentos de tipo K$
						if(!TipoDocumentoCapEnum.K$.toString().equals(partida.getClaseDocSAP())) {
							listaVinculadosPartida.add(partida);
						}
					}
				}
				if (!listaVinculadosPartida.isEmpty()) {
					for (Partida partida : listaVinculadosPartida) {
						if (claves.contains(this.crearIdReferenciaVincula(partida))) {
							if (clavesFiltradas.contains(this.crearIdReferenciaVincula(partida))) {
								DocumentoCapDto dto = new DocumentoCapDto();
								mapearPartida(dto, partida);
								dto.setHuerfano(SiNoEnum.NO);
								if (EstadoBloqueoSapEnum.PESOS.equals(dto.getBloqueoPago())) {
									this.acumuladorCapPesos.aAcumular(dto.getNroDocumentoSap(), dto.getImporteDocMonedaDoc());
//								Logica para no mostrar documentos K$
								} else {
									listaVinculados.add(dto);
									this.acumuladorCapEmiNoPesos.aAcumular(dto.getNroDocumentoSap(), dto.getImporteDocMonedaDoc());
									this.acumuladorCapCobroNoPesos.aAcumular(dto.getNroDocumentoSap(), dto.getImpDocPES());
								}
							}
						} else {
							if (this.filtrarDocumentos(partida, fechaDesde, fechaHasta)) {
								DocumentoCapDto dto = new DocumentoCapDto();
								mapearPartida(dto, partida);
								dto.setHuerfano(SiNoEnum.SI);
								dto.setTipoRenglon(TipoRenglonSapEnum.CAP);
								listaVinculados.add(dto);
							}
						}
					}
					//listaVinculados.removeAll(x);
					//listaDosCap.addAll(listaVinculados);
				}
			}
			// Agrego la suma de los documentos en estado de bloqueo $ (PESOS)
			for (DocumentoCapDto dto : listaDosCap) {
				dto.setImporteTotalMonedaOrigenDocumentosBloqueoPesos(acumuladorCapPesos.getAcumulado(dto.getNroDocumentoSap()));
				dto.setSaldoPesificadoEmisionDocumentosBloqueoNoPesos(acumuladorCapEmiNoPesos.getAcumulado(dto.getNroDocumentoSap()));
				dto.setSaldoPesificadoCobroDocumentosBloqueoNoPesos(acumuladorCapCobroNoPesos.getAcumulado(dto.getNroDocumentoSap()));
			}
			for (DocumentoCapDto dto : listaVinculados) {
				dto.setImporteTotalMonedaOrigenDocumentosBloqueoPesos(acumuladorCapPesos.getAcumulado(dto.getNroDocumentoSap()));
				dto.setSaldoPesificadoEmisionDocumentosBloqueoNoPesos(acumuladorCapEmiNoPesos.getAcumulado(dto.getNroDocumentoSap()));
				dto.setSaldoPesificadoCobroDocumentosBloqueoNoPesos(acumuladorCapCobroNoPesos.getAcumulado(dto.getNroDocumentoSap()));
			}
		} catch (ParseException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	private String crearIdReferenciaVincula(Partida partida) {
		StringBuilder str =  new StringBuilder();
		str.append(partida.getIdSociedad());
		str.append("-");
		str.append(partida.getNumeroDocSAPVinculado());
		str.append("-");
		str.append(partida.getEjercicioFiscalDocSAPVinculado());
		str.append("-");
		str.append(Integer.parseInt(partida.getPosicionDocSAPVinculado()));
		return str.toString();
	}
	private String crearIdReferencia(Partida partida) {
		StringBuilder str =  new StringBuilder();
		str.append(partida.getIdSociedad());
		str.append("-");
		str.append(partida.getNumeroDocSAP());
		str.append("-");
		str.append(partida.getEjercicioFiscalDocSAP());
		str.append("-");
		str.append(Integer.parseInt(partida.getPosicionDocSAP()));
		return str.toString();
	}

	private boolean filtrarDocumentos(Partida partida, Date fechaDesde, Date fechaHasta) {
		boolean moneda = false;
		boolean numeroLegal = false;
		boolean idDocCap = false;
		boolean fDesde = false;
		boolean tipoComprobante = false;
		boolean salida = false;
		
		if (!Validaciones.isObjectNull(this.filtro.getTipoComprobanteSap())) {
			if (validarTipoComprobante(partida, false)){
				tipoComprobante = true;
			}
		}else {
			//El filtro del tipo comprobante esta vacio
			if (validarTipoComprobante(partida, true)){
				tipoComprobante = true;
			}
		}
		
		if (tipoComprobante &&
				((!Validaciones.isNullOrEmpty(this.filtro.getMoneda())
				&& validarMoneda(partida)) || Validaciones.isNullOrEmpty(this.filtro.getMoneda()))
				){
			moneda = true;
		}
		
		if (tipoComprobante && moneda
				&& ((!Validaciones.isNullOrEmpty(this.filtro.getNumeroLegal())
						&& validarNumeroLegal(partida)) || Validaciones.isNullOrEmpty(this.filtro.getNumeroLegal()))
						){
			numeroLegal = true;
		}
		
		if (tipoComprobante && moneda && numeroLegal
				&& ((!Validaciones.isNullOrEmpty(this.filtro.getIdDocCap())
						&& validarIdDocCap(partida)) || Validaciones.isNullOrEmpty(this.filtro.getIdDocCap()))
						){
			idDocCap = true;
		}
		
		if (tipoComprobante && moneda && numeroLegal && idDocCap
				&& ((!Validaciones.isNullOrEmpty(this.filtro.getFechaDesde())
						&& validarFechaDesde(partida,fechaDesde)) || Validaciones.isNullOrEmpty(this.filtro.getFechaDesde()))
						){
			fDesde = true;
		}
		
		if (tipoComprobante && moneda && numeroLegal && idDocCap && fDesde
				&& ((!Validaciones.isNullOrEmpty(this.filtro.getFechaHasta())
						&& validarFechaHasta(partida,fechaHasta)) || Validaciones.isNullOrEmpty(this.filtro.getFechaHasta()))
						){
			salida = true;
		}
		return salida;
	}
	private boolean validarTipoComprobante(Partida partida, boolean filtroVacio) {
		
		if (filtroVacio){
			for (TipoDocumentoCapEnum tipoDoc :TipoDocumentoCapEnum.getEnums()){
				if (tipoDoc.getCodigo().equals(partida.getClaseDocSAP())){
					return true;
				}
			}
			return false;
		} else {
			if (this.filtro.getTipoComprobanteSap().name().equals(partida.getClaseDocSAP())){
				return true;
			}
			return false;
		}
	}
	private boolean validarMoneda(Partida partida){
		if (this.filtro.getMoneda().equals(partida.getMonedaDocProveedor())){
			return true;
		}
		return false;
	}
	private boolean validarNumeroLegal(Partida partida){
		
		if (this.filtro.getNumeroLegal().equals(partida.getNumeroLegalDocProveedor())){
			return true;
		}
		return false;
	}
	
	private boolean validarIdDocCap(Partida partida){
	
		if (this.filtro.getIdDocCap().equals(partida.getNumeroDocSAP())){
			return true;
		}
		return false;
	}
	
	private boolean validarFechaDesde(Partida partida, Date fechaDesde){
		
		if (fechaDesde.compareTo(partida.getFechaEmision()) <= 0){
			return true;
		}
		return false;
	
	}
	
	private boolean validarFechaHasta(Partida partida, Date fechaHasta){
		
		if (fechaHasta.compareTo(partida.getFechaEmision()) >= 0){
			return true;
		}
		return false;
	
	}
	
	public void mapearPartida (PartidaDto partidaDto, Partida partida){
		
		// Mapeo los datos que viene del webService se Sap estos datos se van a guardar como vienen en la base
		partidaDto.setCodigoSociedad(partida.getIdSociedad());
		partidaDto.setIdNumeroProveedor(partida.getIdProveedor());
		partidaDto.setAsignacion(partida.getAsignacion());
		if (Validaciones.isNumeric(partida.getEjercicioFiscalDocSAP())) {
			partidaDto.setEjercicioFiscalDocSAP(Integer.parseInt(partida.getEjercicioFiscalDocSAP()));
		}
		partidaDto.setNroDocumentoSap(partida.getNumeroDocSAP());
		if (!Validaciones.isNullEmptyOrDash(partida.getPosicionDocSAP())) {
			partidaDto.setPosicionDocSAP(Integer.parseInt(partida.getPosicionDocSAP()));
		}
		partidaDto.setFechaContableDocSAP(partida.getFechaContableDocSAP());
		partidaDto.setFechaDocProveedor(partida.getFechaEmision());
		partidaDto.setFechaRegistroSAP(partida.getFechaRegistroSAP());
		if (!Validaciones.isNullEmptyOrDash(partida.getMonedaDocProveedor())) {
			partidaDto.setMonedaDocProveedor(MonedaEnum.getEnumBySigla(partida.getMonedaDocProveedor()));
		}
		if (!Validaciones.isNullEmptyOrDash(partida.getMonedaLocal())) {
			partidaDto.setMonedaLocal(MonedaEnum.getEnumBySigla(partida.getMonedaLocal()));
		}
		partidaDto.setTipoCambioRegistradoSAP(partida.getTipoCambioAFechaEmision());
		partidaDto.setNumeroLegalDocProveedor(partida.getNumeroLegalDocProveedor());

		partidaDto.setClaseDocSAP(TipoDocumentoCapEnum.getEnumByCodigo(partida.getClaseDocSAP()));

		if (!Validaciones.isObjectNull(partida.getMesFiscalDocSAP())) {
			partidaDto.setMesFiscalDocSap(Integer.parseInt(partida.getMesFiscalDocSAP()));
		}

		partidaDto.setClaveContabilizacionSAP(partida.getClaveContabilizacionSAP());
		partidaDto.setIndicador(partida.getIndicador());
		partidaDto.setDivision(partida.getDivision());
		partidaDto.setImporteDoc(partida.getImporteRenglonPesosAFechaEmision());
		partidaDto.setImporteDocMonedaDoc(partida.getImporteRenglonMonedaOrigenAFechaEmision());
		partidaDto.setTextoPosicion(partida.getTextoPosicion());
		partidaDto.setFechaBase(partida.getFechaBase());
		partidaDto.setCondPago(partida.getCondPago());
		partidaDto.setViaPago(partida.getViaPago());

		if (!Validaciones.isNullEmptyOrDash(partida.getBloqueoPago()) && !" ".equals(partida.getBloqueoPago())) {
			partidaDto.setBloqueoPago(EstadoBloqueoSapEnum.getEnumByCodigo(partida.getBloqueoPago()));//<ZSTATUS>V</ZSTATUS>
		} else {
			partidaDto.setBloqueoPago(EstadoBloqueoSapEnum.BLANCO);//<ZSTATUS>V</ZSTATUS>
		}
		partidaDto.setDescripcionBloqueo(partida.getDescripcionBloqueo());
		partidaDto.setNumeroDocSAPVinculado(partida.getNumeroDocSAPVinculado());

		if (!Validaciones.isObjectNull(partida.getEjercicioFiscalDocSAPVinculado())) {
			partidaDto.setEjercicioFiscalDocSAPVinculado(Integer.parseInt(partida.getEjercicioFiscalDocSAPVinculado()));
		}
		if (!Validaciones.isNullEmptyOrDash(partida.getPosicionDocSAPVinculado())) {
			partidaDto.setPosicionDocSAPVinculado(Integer.parseInt(partida.getPosicionDocSAPVinculado()));
		}
		partidaDto.setClaveRef(partida.getClaveRef());
		partidaDto.setClaveRef2(partida.getClaveRef2());
		partidaDto.setFechaTipoCambio(partida.getFechaTipoCambio());
		partidaDto.setTipoCambioAFechaTipoCambio(partida.getTipoCambioAFechaTipoCambio());
		
		if (!Validaciones.isNullEmptyOrDash(partida.getEstadoDocSAP()) && !" ".equals(partida.getEstadoDocSAP())) {
			partidaDto.setEstadoDocSAP(EstadoDocSapEnum.getEnumByCodigo(partida.getEstadoDocSAP()));//<ZSTATUS>V</ZSTATUS>
		} else {
			partidaDto.setEstadoDocSAP(EstadoDocSapEnum.BLANCO);//<ZSTATUS>V</ZSTATUS>
		}
		partidaDto.setImpDocPES(partida.getImporteRenglonPesosAFechaShiva());
		partidaDto.setImpDocUSD(partida.getImporteRenglonMonedaDolarAFechaShiva());
		partidaDto.setImpTotalDocMonedaDoc(partida.getImporteTotalDocumentoMonedaOrigenAFechaEmision());
					  									
		partidaDto.setImpTotalDocMonedaLocal(partida.getImporteTotalDocumentoPesosAFechaEmision());
		partidaDto.setImpTotalDocPES(partida.getImporteTotalDocumentoPesosAFechaShiva());
		partidaDto.setImpTotalDocUSD(partida.getImporteTotalDocumentoMonedaDolarAFechaShiva());

		partidaDto.setImporteRenglonMonedaDolarAFechaEmision(partida.getImporteRenglonMonedaDolarAFechaEmision());


		ClienteProveedorSapVo cliente  = this.obtenerIdCliente(partidaDto.getIdNumeroProveedor());

		DocumentoCapDto docCap = (DocumentoCapDto) partidaDto;
		docCap = esProveedorInhabilitado(docCap,cliente);
		// Datos Comunes!!!
		docCap.setIdClienteLegado(Long.parseLong(cliente.getClienteLegado().get(0).getIdClienteLegado()));

		if (!Validaciones.isObjectNull(this.filtro.getMonedaImporte())) {	
			//Si el getEnumBySigla es null, entonces ya tenia seteada La MONEDA IMPORTE A COBRAR, SINO, LO SETEO
			if (!Validaciones.isObjectNull(MonedaEnum.getEnumBySigno2(this.filtro.getMonedaImporte()))){
				docCap.setMonedaImporte(MonedaEnum.getEnumBySigno2(this.filtro.getMonedaImporte()));
			} else {
				docCap.setMonedaImporte(MonedaEnum.getEnumByName(this.filtro.getMonedaImporte()));
			}
		}
		// Determino si es un documento relacionado
		if (Validaciones.isNullEmptyOrDash(partidaDto.getNumeroDocSAPVinculado()) || SiNoEnum.SI.equals(docCap.getHuerfano())) {
			// Documento Cap
			docCap.setTipoRenglon(TipoRenglonSapEnum.CAP);
			docCap.setImporteMonedaOrigen(Utilidad.formatCurrency(partida.getImporteTotalDocumentoMonedaOrigenAFechaEmision(), false, false, 2));
		} else {
			// Documento Cap vinculado
			docCap.setTipoRenglon(TipoRenglonSapEnum.REF);
		}
		docCap.setSistemaOrigen(SistemaEnum.SAP);
		// seteo el maximo pode defual
		
		docCap.setSinDifDeCambio(false);
		docCap.setIdDocumentoCapReferencia(docCap.contruirIdReferencia());
		
	}
	
	private DocumentoCapDto esProveedorInhabilitado(DocumentoCapDto docCap, ClienteProveedorSapVo cliente) {
		boolean proveedorInhabilitado = this.proveedorCapServicio.esProveedorInhabilitado(cliente.getCodigoBloqueoProveedor());
		if (!proveedorInhabilitado) {
			docCap.setProveedorInhabilitado(SiNoEnum.NO);
			docCap.setProveedorInhabilitadoCodigo(" ");
		} else {
			docCap.setProveedorInhabilitado(SiNoEnum.SI);
			docCap.setProveedorInhabilitadoCodigo(cliente.getCodigoBloqueoProveedor());

//			switch (cliente.getCodigoBloqueoProveedor()) {
//				//B, C, 0, 1, 2, 3, 4, 5 y 6
//			// Se podria sacar el Switch dado que todos los casos que sean diferencte a basi
//			case "B":
//				docCap.setProveedorInhabilitado(SiNoEnum.SI);
//				break;
//			case "C":
//				docCap.setProveedorInhabilitado(SiNoEnum.SI);
//				break;
//			case "0":
//				docCap.setProveedorInhabilitado(SiNoEnum.SI);
//				break;	
//			case "1":
//				docCap.setProveedorInhabilitado(SiNoEnum.SI);
//				break;
//			case "2":
//				docCap.setProveedorInhabilitado(SiNoEnum.SI);
//				break;
//			case "3":
//				docCap.setProveedorInhabilitado(SiNoEnum.SI);
//				break;
//			case "4":
//				docCap.setProveedorInhabilitado(SiNoEnum.SI);
//				break;
//			case "5":
//				docCap.setProveedorInhabilitado(SiNoEnum.SI);
//				break;
//			case "6":
//				docCap.setProveedorInhabilitado(SiNoEnum.SI);
//				break;
//			case "8":
//				docCap.setProveedorInhabilitado(SiNoEnum.SI);
//				break;	
//			case "9":
//				docCap.setProveedorInhabilitado(SiNoEnum.SI);
//				break;	
//			default:
//				docCap.setProveedorInhabilitado(SiNoEnum.SI);
//				break;
//			}
			
		}
		return docCap;
	}

	public EntradaSapConsultaPartidasWS creearConsultaPartidasWS() throws NegocioExcepcion {
		EntradaSapConsultaPartidasWS entradaSapConsultaPartidasWS = new EntradaSapConsultaPartidasWS();

		for (ClienteProveedorSapVo vo : map.values()) {
			if (!Validaciones.isNullEmptyOrDash(vo.getCodigoProveedor())) {
				entradaSapConsultaPartidasWS.getListaIdProveedores().add(vo.getCodigoProveedor());	
			}
		}
		try {

			// No es un filtro. es un parametro que utiliza sap.
			if (!Validaciones.isNullEmptyOrDash(this.filtro.getFechaTipoDeCambio())) {
				entradaSapConsultaPartidasWS.setFechaTipoCambio(Utilidad.parseDatePickerString(this.filtro.getFechaTipoDeCambio()));
			}
		} catch (ParseException e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}	
		if (!Validaciones.isObjectNull(this.filtro.getTipoComprobanteSap())) {
			entradaSapConsultaPartidasWS.setTipoDocumentoCap(this.filtro.getTipoComprobanteSap());
		}

		return entradaSapConsultaPartidasWS;
	}
	public static final int INICIAL = 0;
	public static final int PROVEEDORES = 1;
	public static final int PROVEEDORES_NO_ENCONTRADOS = 2;
	public static final int PARTIDAS_NO_ENCONTRADOS = 3;
	public static final int TESTS = 5;
	
	
	public List<DocumentoCapDto> calcularRenglones(List<DocumentoCapDto> lista) {
		List<DocumentoCapDto> caps = new ArrayList<DocumentoCapDto>();
		List<DocumentoCapDto> inv = new ArrayList<DocumentoCapDto>();
		// Clase interna
		_Current current = new _Current();

		for (DocumentoCapDto doc : lista) {
			if (!current.factura.equals(doc.getIdPantalla())) {
				if (current.dto != null) {
					current = this.volcar(current.dto, caps, inv, current);
				}
				inv.add(doc);
				current.semaforoCurrent = doc.obtenerSemaforoColor();
				current.factura = doc.getIdPantalla();
				current.dto = new DocumentoCapDto(doc);
				current.dto.setTipoRenglon(TipoRenglonSapEnum.AGRUPADOR);
				current.dto.setSemaforo(doc.getSemaforo());
				current = acumular(doc, current, true);
			} else if (!Validaciones.isNullEmptyOrDash(doc.obtenerSemaforoColor()) && !current.semaforoCurrent.equals(doc.obtenerSemaforoColor())) {
					current = volcar(current.dto, caps, inv, current);
					current.semaforoCurrent = doc.obtenerSemaforoColor();
					current.dto = new DocumentoCapDto(doc);
					current.dto.setTipoRenglon(TipoRenglonSapEnum.AGRUPADOR);
					current.dto.setSemaforo(doc.getSemaforo());
					current = acumular(doc, current, true);
					inv.add(doc);
			} else {
				current = acumular(doc, current, false);
				inv.add(doc);
			}
			
		}
		if (!inv.isEmpty()) {
			current = this.volcar(current.dto, caps, inv, current);
		
		}
		return caps;
	}
	private _Current acumular(PartidaDto partida, _Current current, boolean primero) {
		if (primero) {
			current.dto.setImporteDocMonedaDoc(partida.getImporteDocMonedaDoc());
			current.dto.setImportePesificadoDocAsociadoBd(BigDecimal.ZERO);
			current.dto.setImportePesificadoDocAsociadoEmisionBd(BigDecimal.ZERO);
			current.dto.setSaldoMonedaOrigenBd(partida.getImporteDocMonedaDoc());
			if (!current.dto.getMonedaImporte().equals(partida.getMonedaDocProveedor())) {
				current.dto.setSaldoPesificadoTotalAFechaShivaBd(partida.getImpDocPES());
				current.dto.setSaldoPesificadoTotalAFechaEmisionBd(partida.getImporteDoc());
			}
		} else {
			if (((DocumentoCapDto)partida).isEsReglonAsociado()) {
				if (Validaciones.isObjectNull(current.dto.getImportePesificadoDocAsociadoBd())) {
					current.dto.setImportePesificadoDocAsociadoBd(BigDecimal.ZERO);
					current.dto.setImportePesificadoDocAsociadoEmisionBd(BigDecimal.ZERO);
				}
				if (!current.dto.getMonedaImporte().equals(partida.getMonedaDocProveedor())) {
					current.dto.setImportePesificadoDocAsociadoBd(current.dto.getImportePesificadoDocAsociadoBd().add(partida.getImpDocPES()));
					current.dto.setImportePesificadoDocAsociadoEmisionBd(current.dto.getImportePesificadoDocAsociadoEmisionBd().add(partida.getImporteDoc()));
				} else {
					current.dto.setImportePesificadoDocAsociadoBd(current.dto.getImportePesificadoDocAsociadoBd().add(partida.getImporteDocMonedaDoc()));
					current.dto.setImportePesificadoDocAsociadoEmisionBd(current.dto.getImportePesificadoDocAsociadoEmisionBd().add(partida.getImporteDocMonedaDoc()));
				}
				((DocumentoCapDto)partida).setFechaEmisionDocumentoPadre(current.dto.getFechaDocProveedor());
			} else {
				current.dto.setSaldoMonedaOrigenBd(current.dto.getSaldoMonedaOrigenBd().add(partida.getImporteDocMonedaDoc()));
				if (!current.dto.getMonedaImporte().equals(partida.getMonedaDocProveedor())) {
					current.dto.setSaldoPesificadoTotalAFechaShivaBd(current.dto.getSaldoPesificadoTotalAFechaShivaBd().add(partida.getImpDocPES()));
					current.dto.setSaldoPesificadoTotalAFechaEmisionBd(current.dto.getSaldoPesificadoTotalAFechaEmisionBd().add(partida.getImporteDoc()));
				}
			}
		}
		
		return current;
	}

	
	
	private _Current volcar(DocumentoCapDto doc, List<DocumentoCapDto> caps, List<DocumentoCapDto> inv, _Current current) {
		if (doc.isEsReglonAgrupador()) {
			if (!doc.isEsNoGestionable()) {
				if (doc.getMonedaImporte().equals(doc.getMonedaDocProveedor())) {
					doc.setImporteGestionableTotalBd(doc.getSaldoMonedaOrigenBd().add(doc.getImportePesificadoDocAsociadoBd()));
					doc.setImporteGestionableBg(doc.getImporteGestionableTotalBd());
					doc.setImporteGestionableEmisionBd(doc.getSaldoMonedaOrigenBd().add(doc.getImportePesificadoDocAsociadoEmisionBd()));
				} else {
					doc.setImporteGestionableAFechaShivaTotalBd(doc.getSaldoPesificadoTotalAFechaShivaBd().add(doc.getImportePesificadoDocAsociadoBd()));
					//doc.setImporteGestionableAFechaEmisionTotalBd(doc.getSaldoPesificadoTotalAFechaEmisionBd().add(doc.getImportePesificadoDocAsociadoEmisionBd()));
					doc.setImporteGestionableAFechaEmisionTotalBd(doc.getSaldoPesificadoTotalAFechaEmisionBd().add(doc.getImportePesificadoDocAsociadoBd()));
					doc.setImporteGestionableBg(doc.getImporteGestionableAFechaShivaTotalBd());
					doc.setImporteGestionableEmisionBd(doc.getSaldoPesificadoTotalAFechaEmisionBd().add(doc.getImportePesificadoDocAsociadoEmisionBd()));
				}
			}
			
			doc.setImportePesificadoDocAsociado(Utilidad.formatCurrency(doc.getImportePesificadoDocAsociadoBd(), false, false, 2));
		}
	
		caps.add(doc);
		if (!inv.isEmpty()) {
			DocumentoCapDto aux = inv.get(inv.size() -1);
			if (aux.isEsReglonAsociado()) {
				aux.setTipoRenglon(TipoRenglonSapEnum.REF_FIN);
			} else {
				aux.setTipoRenglon(TipoRenglonSapEnum.CAP_FIN);
			}
		}
		caps.addAll(inv);
		inv.clear();
		return current;
	}
	private class _Current {
		String semaforoCurrent = "";
		String factura = "";
		DocumentoCapDto dto = null;
		//BigDecimal importe = new BigDecimal(0);
		public _Current() {}
	}
	/**
	 * Se setean datos para su visualizacion.
	 * Los importes y saldos por de default se usan en dolares a fecha emision
	 * @return
	 */
	public int setValoresVisualesinicial() {
		int cantidad = 0;
		String idPantalla = "";
		int indexInterna = 0;
		boolean even = false;
		for (DocumentoCapDto documentoCapDto : getListaDosCap()) {
			if (!documentoCapDto.getIdPantalla().equals(idPantalla)) {
				indexInterna = 0;
			}
			documentoCapDto.setPosicionEnDocumento(indexInterna);
			documentoCapDto.setPosicionInterna(indexInterna);
			indexInterna++;
			if (documentoCapDto.isEsReglonAgrupador()) {
				documentoCapDto.setEven(even);
				cantidad++;
				even = !even;
				//documentoCapDto.setPosicionInterna(index);
				if (!documentoCapDto.getIdPantalla().equals(idPantalla)) {
					
					idPantalla = documentoCapDto.getIdPantalla();
				}
				// Mapeo valores de importes por Default
				if (!documentoCapDto.isEsNoGestionable()) {
					documentoCapDto.setImporteGestionable(Utilidad.formatCurrency(documentoCapDto.getImporteGestionableBg(), false, false, 2));
				} else {
					documentoCapDto.setImporteGestionable("-");
				}
				// TODO Cambio a fecha emision
				documentoCapDto.setTipoCambio(Utilidad.formatDecimales(documentoCapDto.getTipoCambioRegistradoSAP(), 7));
				documentoCapDto.setSaldoMonedaOrigen(Utilidad.formatCurrency(documentoCapDto.getSaldoMonedaOrigenBd(), false, false, 2));
				// TODO Cambio a fecha emision
				documentoCapDto.setSaldoPesificado(Utilidad.formatCurrency(documentoCapDto.getSaldoPesificadoTotalAFechaShivaBd(), false, false, 2));
				documentoCapDto.setImportePesificadoDocAsociado(Utilidad.formatCurrency(documentoCapDto.getImportePesificadoDocAsociadoBd(), false, false, 2));
			} else if (documentoCapDto.isEsReglonCap()) {
				documentoCapDto.setImporteGestionable("-");
				
				documentoCapDto.setSaldoMonedaOrigen(Utilidad.formatCurrency(documentoCapDto.getImporteDocMonedaDoc(), false, false, 2));
				if (!documentoCapDto.isEsMismaMoneda()) {
					documentoCapDto.setSaldoPesificado(Utilidad.formatCurrency(documentoCapDto.getImpDocPES(), false, false, 2));
					// TODO cambio a fecha de emision
					documentoCapDto.setTipoCambio(Utilidad.formatDecimales(documentoCapDto.getTipoCambioRegistradoSAP(), 7));
				} else {
					documentoCapDto.setSaldoPesificado("-");
					documentoCapDto.setTipoCambio("-");
				}
			} else {
				documentoCapDto.setImportePesificadoDocAsociado(Utilidad.formatCurrency(documentoCapDto.getImpDocPES(), false, false, 2));
				// TODO cambio a fecha de emision
				documentoCapDto.setTipoCambio(Utilidad.formatDecimales(documentoCapDto.getTipoCambioRegistradoSAP(), 7));
			}
		}
		return cantidad;
	}
	public int setValoresVisuales() {
		int cantidad = 0;
		String idPantalla = "";
		int indexInterna = 0;
		boolean even = false;
		for (DocumentoCapDto documentoCapDto : getListaDosCap()) {
			if (!documentoCapDto.getIdPantalla().equals(idPantalla)) {
				indexInterna = 0;
			}
			documentoCapDto.setPosicionEnDocumento(indexInterna);
			documentoCapDto.setPosicionInterna(indexInterna);
			indexInterna++;
			if (documentoCapDto.isEsReglonAgrupador()) {
				documentoCapDto.setEven(even);
				cantidad++;
				even = !even;
				//documentoCapDto.setPosicionInterna(index);
				if (!documentoCapDto.getIdPantalla().equals(idPantalla)) {
					idPantalla = documentoCapDto.getIdPantalla();
				}
			} else if (documentoCapDto.isEsReglonCap()) {
				documentoCapDto.setImporteGestionable("-");
				if (documentoCapDto.isEsMismaMoneda()) {
					documentoCapDto.setSaldoPesificado("-");
					documentoCapDto.setTipoCambio("-");
				}
			}
		}
		return cantidad;
	}
	
	
	
	public List<DocumentoCapDto> getListaVinculados() {
		return listaVinculados;
	}
	public void setListaVinculados(List<DocumentoCapDto> listaVinculados) {
		this.listaVinculados = listaVinculados;
	}

	/**
	 * @return the proveedorCapServicio
	 */
	public IProveedorCapServicio getProveedorCapServicio() {
		return proveedorCapServicio;
	}

	/**
	 * @param proveedorCapServicio the proveedorCapServicio to set
	 */
	public void setProveedorCapServicio(IProveedorCapServicio proveedorCapServicio) {
		this.proveedorCapServicio = proveedorCapServicio;
	}
	
}
