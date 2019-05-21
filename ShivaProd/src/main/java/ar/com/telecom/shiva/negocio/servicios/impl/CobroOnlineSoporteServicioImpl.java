package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.ImportacionDebitosAuxiliar;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineDebitoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineOtrosDebitoMapeador;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.servicios.bean.ConfReglaBean;
import ar.com.telecom.shiva.negocio.servicios.bean.ConfReglasCamposImportacionBean;
import ar.com.telecom.shiva.negocio.servicios.validacion.ICobroOnlineValidacionServicio;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineOtrosDebitoDao;
import ar.com.telecom.shiva.persistencia.dao.IParametroConfReglaCampoDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoComprobanteDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamConfCampo;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroOtrosDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ImportarOtrosDebitoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;


/**
 * 
 * @author U587496 Guido.Settecerze
 *
 */
public class CobroOnlineSoporteServicioImpl extends Servicio implements ICobroOnlineSoporteServicio {
	
	@Autowired
	private ICobroOnlineDao cobroOnlineDao;
	@Autowired
	private ICobroOnlineValidacionServicio cobroOnlineValidacionServicio;
	@Autowired
	private CobroOnlineDebitoMapeador debitoMapeador;
	@Autowired
	private ICobroOnlineServicio cobroOnlineServicio;
	@Autowired
	private IClienteServicio clienteServicio;
	@Autowired
	private ICobroOnlineOtrosDebitoDao cobroOtrosDebitoDao;
	@Autowired
	private IParametroConfReglaCampoDao parametroConfReglaCampoDao;
	@Autowired 
	private IParametroConfReglaCampoDao parametroConfReglaCampoDaoImpl;
	@Autowired 
	private CobroOnlineOtrosDebitoMapeador otrosDebitoMapeador;
	@Autowired
	private IParametroServicio parametroServicio;
	@Autowired
	private ITipoComprobanteDao tipoComprobanteDao;
	
	
	/**
	 * @author u587496 Guido.Settecerze
	 * @param  multipartFile
	 * @throws NegocioExcepcion 
	 * @throws PersistenciaExcepcion 
	 */
	public ImportarOtrosDebitoJsonResponse resultadoValidacionesOtrosDebitos(MultipartFile multipartFile, String cantDebitosGrilla, Long idCobro, MonedaEnum monedaCobro) throws NegocioExcepcion, ShivaExcepcion, PersistenciaExcepcion {
		String[] registros = null;
		File file = ControlArchivo.multipartToFile(multipartFile);
		ImportarOtrosDebitoJsonResponse importarDebitoDtoResultFinal = new ImportarOtrosDebitoJsonResponse();
		
		importarDebitoDtoResultFinal = this.resultadoValidacionesErrores(multipartFile, cantDebitosGrilla, idCobro, monedaCobro);
		
		if (!Validaciones.isNullOrEmpty(importarDebitoDtoResultFinal.getErrors())) {
			return importarDebitoDtoResultFinal;
		} else {
			importarDebitoDtoResultFinal.setSuccess(true);
//			ConfReglasCamposImportacionBean configuracion = new ConfReglasCamposImportacionBean();
//			registros=configuracion.getCampos();
			try {
				registros = ControlArchivo.leerArchivo(file.getPath(), Constantes.RETORNO_WIN);
				if (registros.length == 1) {
					registros[0] = registros[0].replaceAll(Constantes.RETORNO_UNIX, Constantes.RETORNO_WIN); 
					registros = registros[0].split(Constantes.RETORNO_WIN);
				}
			} catch (ShivaExcepcion e1) {
				throw new NegocioExcepcion(e1.getMessage(), e1);
			}

			//List<ClienteDto> listaClientes = new ArrayList<ClienteDto>(); 
			List<CobroOtrosDebitoDto> listaOtrosDebitos = new ArrayList<CobroOtrosDebitoDto>(); 
			//Set<String> listaIdClienteLegado = new HashSet<String>();
			Map<String, ClienteDto> mapCliente = new HashMap<String, ClienteDto>();

			int registroNumero = 1;

			List<ShvParamConfCampo> listarCamposImportacion = parametroConfReglaCampoDaoImpl.listarCamposImportacion();

			ShvParamConfCampo paramCliente = null; 
			for (ShvParamConfCampo param : listarCamposImportacion) {
				if ("cliente".equals(param.getNombre())) {
					paramCliente = param;
					break;
				}
			}
			for (String reg : registros) {
				String[] campos;
				CobroOtrosDebitoDto dto = new CobroOtrosDebitoDto();
				if (reg.split(Constantes.SEMICOLON).length == listarCamposImportacion.size() ) {
					campos = reg.split(Constantes.SEMICOLON);
				} else {
					String[] aux = reg.concat("ÑXZ").split(Constantes.SEMICOLON);//Se hace esto raro porque el split no splitea como corresponde al tener ;; en el final.
					String str = "";
					for(int i=0; i<=aux.length-1; i++) {
						if (i!=aux.length-1) {
							str += aux[i]+";";
						} else {
							str += aux[i];
						}
					}
					 campos = str.replace("ÑXZ", " ").split(Constantes.SEMICOLON);
				}

				String codigoCliente = "";
				StringBuffer erroCliente = new StringBuffer();
				
				if (campos[paramCliente.getOrdenCampos().intValue() - 1].length()>10){
					codigoCliente = campos[paramCliente.getOrdenCampos().intValue() - 1];
				} else {
					codigoCliente = Utilidad.addStartingZeros(campos[paramCliente.getOrdenCampos().intValue() - 1].trim(), 10);
				}
	
				ClienteDto clienteDto = null;
				if (!mapCliente.containsKey(codigoCliente)) {
					//listaIdClienteLegado.add(codigoCliente);
					
					try{
						ClienteBean clienteBean = clienteServicio.consultarCliente(codigoCliente);
						clienteDto = clienteServicio.mapear(clienteBean);
						mapCliente.put(codigoCliente, clienteDto);
					} catch (Throwable ex) {
						Traza.error(getClass(), ex.getMessage(), ex);
						importarDebitoDtoResultFinal.setSuccess(false);
						erroCliente.append(ConstantesCobro.LINEA_NRO).append(registroNumero).append(ConstantesCobro.CAUSA);
						if (ex.getCause()!=null && ex.getCause() instanceof RemoteAccessException) {
							erroCliente.append(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.caida"));
						} else {
							erroCliente.append(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.error"));
						}
						erroCliente.append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS).append(codigoCliente);
						erroCliente.append(Constantes.CLOSE_PARENTESIS);
						erroCliente.append(Constantes.PUNTO);
						erroCliente.append(Constantes.CARRIAGE_RETURN);
						
						if (importarDebitoDtoResultFinal.getErrors()!="" && importarDebitoDtoResultFinal.getErrors()!=null) {
							StringBuilder str = new StringBuilder(importarDebitoDtoResultFinal.getErrors());
							str.append(erroCliente.toString());
							importarDebitoDtoResultFinal.setErrors(str.toString());
						}else{
							importarDebitoDtoResultFinal.setErrors(erroCliente.toString());
						}
						
					}
					
				} else {
					clienteDto = mapCliente.get(codigoCliente);
				}
				
				if (importarDebitoDtoResultFinal.getErrors()!="" && importarDebitoDtoResultFinal.getErrors()!=null && Validaciones.isObjectNull(clienteDto)) {
					importarDebitoDtoResultFinal.setSuccess(false);
					erroCliente.append(ConstantesCobro.LINEA_NRO).append(registroNumero).append(ConstantesCobro.CAUSA);
					erroCliente.append(ConstantesCobro.ERROR_EL_CAMPO).append(paramCliente.getNombre()).append(ConstantesCobro.ERROR_POSEE_CLIENTE_NO_VALIDO);
					erroCliente.append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS).append(codigoCliente);
					erroCliente.append(Constantes.CLOSE_PARENTESIS);
					erroCliente.append(Constantes.PUNTO);
					erroCliente.append(Constantes.CARRIAGE_RETURN);
					
					if (importarDebitoDtoResultFinal.getErrors()!="" && importarDebitoDtoResultFinal.getErrors()!=null) {
						StringBuilder str = new StringBuilder(importarDebitoDtoResultFinal.getErrors());
						str.append(erroCliente.toString());
						importarDebitoDtoResultFinal.setErrors(str.toString());
					}else{
						importarDebitoDtoResultFinal.setErrors(erroCliente.toString());
					}
				}
				
				List<TipoComprobanteEnum> listaComprobanteDuplicado = new ArrayList<>();
				listaComprobanteDuplicado.add(TipoComprobanteEnum.DE_);
				listaComprobanteDuplicado.add(TipoComprobanteEnum.DE2);
				
				List<ConfReglaBean> listConfiguracion =	parametroConfReglaCampoDaoImpl.obtenerCamposReglaImportacionActivosOrdenadosMapeados(monedaCobro, listaComprobanteDuplicado);
				
				dto = (CobroOtrosDebitoDto) otrosDebitoMapeador.map(campos, dto, listarCamposImportacion, monedaCobro, file.getName(), listConfiguracion);
				dto.setCliente(Long.parseLong(codigoCliente));

				listaOtrosDebitos.add(dto);
				registroNumero++;
			}
			if (!listaOtrosDebitos.isEmpty()) {
				importarDebitoDtoResultFinal.setotrosDebitos(listaOtrosDebitos);
			}
			if (!mapCliente.isEmpty()) {
				importarDebitoDtoResultFinal.setClientes(new ArrayList<ClienteDto>());
				for (ClienteDto clienteDto : mapCliente.values()) {
					if (!Validaciones.isObjectNull(clienteDto)) {
						importarDebitoDtoResultFinal.getClientes().add(clienteDto);
					}
				}
				
			}
			return importarDebitoDtoResultFinal;
		}
	}

	/**
	 * @author u587496 Guido.Settecerze
	 * @param file
	 * @return
	 * @throws NegocioExcepcion 
	 * @throws PersistenciaExcepcion 
	 */
	private ImportarOtrosDebitoJsonResponse resultadoValidacionesErrores(MultipartFile file, String cantDebitosGrilla, Long idCobro, MonedaEnum monedaCobro) throws NegocioExcepcion, PersistenciaExcepcion {
		ImportarOtrosDebitoJsonResponse importarDebitoDtoResultFinal = new ImportarOtrosDebitoJsonResponse();
		
		//Valido todo lo que es nombre de archivo
		importarDebitoDtoResultFinal = this.validarRestriccionesArchivoOtrosDebitos(file);
		
		if (!Validaciones.isNullOrEmpty(importarDebitoDtoResultFinal.getErrors())) {
			return importarDebitoDtoResultFinal;
		} else {
			//Valido todo lo que es registros de archivo
			importarDebitoDtoResultFinal = this.procesarRegistrosImportarDebitos(file, cantDebitosGrilla, idCobro, monedaCobro);
		}
		return importarDebitoDtoResultFinal;
	} 

	/**
	 * Procesa un archivo de Debitos. Si es correcto retorna Lista con Clientes, sino Lista con Errores.
	 * 
	 * @author u587496 Guido.Settecerze
	 * @param registros
	 * @throws PersistenciaExcepcion 
	 */
	private ImportarOtrosDebitoJsonResponse procesarRegistrosImportarDebitos(MultipartFile multipartFile, String cantDebitosGrilla, Long idCobro, MonedaEnum monedaCobro) throws NegocioExcepcion, PersistenciaExcepcion {
		File file = ControlArchivo.multipartToFile(multipartFile);
		String[] registros = null;
		boolean exedeLimiteCantRegistros = false;
		try {
			registros = ControlArchivo.leerArchivo(file.getPath(), Constantes.RETORNO_WIN);
			if (registros.length == 1) {
				registros[0] = registros[0].replaceAll(Constantes.RETORNO_UNIX, Constantes.RETORNO_WIN);
				registros = registros[0].split(Constantes.RETORNO_WIN);
			}
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		int nroRegistro = 1;
		boolean	errorEnCampos = false;
		boolean	errorDuplicidad = false;
		boolean	errorFormato = false;
		boolean errorDuplicidadDebitosPersistidos = false;
		boolean errorFinal = false;
		ImportarOtrosDebitoJsonResponse importarDebitoDto = new ImportarOtrosDebitoJsonResponse();

		CobroDto cobroDto = cobroOnlineServicio.buscarCobro(idCobro);
		ImportacionDebitosAuxiliar importarDebitosAuxiliar = new ImportacionDebitosAuxiliar(cobroDto, true);

		ConfReglasCamposImportacionBean configuracion = new ConfReglasCamposImportacionBean();
		// Obtengo la lista de la definicion de campos que comprenden el archivo de importacion
		try {
			configuracion.setListarCamposImportacion(parametroConfReglaCampoDaoImpl.listarCamposImportacion());
			// obtengo los dominios por totales Segun la moneda del cobro
			configuracion.getDominiosRta().put("sociedad", parametroConfReglaCampoDaoImpl.listaSociedadesActivos(monedaCobro));
			configuracion.getDominiosRta().put("sistema", parametroConfReglaCampoDaoImpl.listaSistemasActivos(monedaCobro));
			configuracion.getDominiosRta().put("tipoComprobante", parametroConfReglaCampoDaoImpl.listaTipoComprobanteActivos(monedaCobro));
			configuracion.getDominiosRta().put("moneda", parametroConfReglaCampoDaoImpl.listaMonedaActivos(monedaCobro));
			configuracion.setMonedaCobro(monedaCobro);
			configuracion.setConfiguracion(parametroConfReglaCampoDaoImpl.obtenerCamposReglaImportacionActivosOrdenadosMapeados(monedaCobro, null));
			configuracion.setImportarDebitosAuxiliar(importarDebitosAuxiliar);
			configuracion.setTipoComprobantesExcluido(parametroServicio.getValorTexto("cobros.conf.otros.debito.importacion.tipo.comprobante.excluidos"));
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
//		for (@SuppressWarnings("unused") String reg : registros) {
//			nroRegistroAux++;
//		}
//		exedeLimiteCantRegistros = cobroOnlineValidacionServicio.validacionCantDebitosMaximos(registros.length, cantDebitosGrilla, importarDebitosAuxiliar);

		for (String reg : registros) {
			String[] campos = reg.concat("ÑXZ").split(Constantes.SEMICOLON);//Se hace esto raro porque el split no splitea como corresponde al tener ;; en el final.
			String str = "";
			for(int i=0; i<=campos.length-1; i++) {
				if(i!=campos.length-1){
					str += campos[i]+";";
				}else{
					str += campos[i];
				}
			}
			String[] camposATR = str.replace("ÑXZ", " ").split(Constantes.SEMICOLON);
			camposATR[camposATR.length -1] = camposATR[camposATR.length -1].trim();
			configuracion.setNroRegistro(nroRegistro);
			configuracion.setCampos(camposATR);
			errorFormato = configuracion.validarFormato();
			if (errorFormato) {
				errorFinal = true;
			}
			// TODO FALTA DUPLICIDAD
			if (!errorFormato) {
				errorDuplicidad = configuracion.validarDuplicidaEnArchivo();
				if(errorDuplicidad){
					errorFinal = true;
				}
			}
			if (!errorFormato && !errorDuplicidad) {
				errorEnCampos = configuracion.validarRegistro(parametroConfReglaCampoDaoImpl, monedaCobro, cobroDto.getIdMotivoCobro());
				configuracion.limpiarParametro();
				if (errorEnCampos) {
					errorFinal = true;
				}
			}
			if (!errorFormato && !errorEnCampos) {
				errorDuplicidadDebitosPersistidos = configuracion.validarDuplicidadEnCobro();
				if (errorDuplicidadDebitosPersistidos) {
					errorFinal = true;
				}
			}
			// Limpio los mensajes de error
			
			nroRegistro++;
		}
		if (!errorFinal) {
			if (errorFormato || errorDuplicidad || exedeLimiteCantRegistros || errorEnCampos || errorDuplicidadDebitosPersistidos) {
				errorFinal = true;
			}
		}
		if (errorFinal) {
			importarDebitoDto = setErrorsImportarOtrosDebitoDto(importarDebitoDto, importarDebitosAuxiliar);
		}
		return importarDebitoDto;
	}
	/**
	 * 
	 * @param importarDebitoDto
	 * @param importacionDebitosAuxiliar
	 * @return
	 */
	private ImportarOtrosDebitoJsonResponse setErrorsImportarOtrosDebitoDto(ImportarOtrosDebitoJsonResponse importarDebitoDto, ImportacionDebitosAuxiliar importacionDebitosAuxiliar) {
		Traza.error(getClass(), importacionDebitosAuxiliar.getResultadoValidaciones().toString());
		importarDebitoDto.setSuccess(false);
		importarDebitoDto.setErrors(importacionDebitosAuxiliar.getResultadoValidaciones().toString());
		return importarDebitoDto;
	}
	
	/**
	 * Valida las restricciones de los archivos.
	 * 
	 * @author u587496 Guido.Settecerze
	 * @param campos
	 * @param nroRegistro
	 * @return
	 */
	private ImportarOtrosDebitoJsonResponse validarRestriccionesArchivoOtrosDebitos(MultipartFile file) throws NegocioExcepcion {
		ImportarOtrosDebitoJsonResponse importarDebitoDto = new ImportarOtrosDebitoJsonResponse();

		StringBuffer resultadoValidaciones = new StringBuffer();
		resultadoValidaciones = this.validarRestriccionesArchivo(file);
		importarDebitoDto.setErrors(resultadoValidaciones.toString());
		if (importarDebitoDto.getErrors().length() > 0) {
			importarDebitoDto.setSuccess(false);
		} else {
			importarDebitoDto.setSuccess(true);
		}
		return importarDebitoDto;
	}

	/**
	 * @author U587496 Guido.Settecerze
	 * @param file
	 * @return
	 * @throws NegocioExcepcion
	 */
	private StringBuffer validarRestriccionesArchivo(MultipartFile file) throws NegocioExcepcion {

		StringBuffer resultadoValidaciones = new StringBuffer();
		//Validaciones de archivo.
		resultadoValidaciones.delete(0, resultadoValidaciones.length());
		if (file.getOriginalFilename().length() > Constantes.CANT_MAXIMA_CARACTERES_NOMBRE_ARCHIVO) {
			resultadoValidaciones.append(Propiedades.MENSAJES_PROPIEDADES.getString("error.nombreArchivoMuyLargo"));					
		} else {
			try {
				String fileExtension = ControlArchivo.getFileExtension(file.getOriginalFilename());
				if (ControlArchivo.isMultipartFileEmpty(file)) {
					resultadoValidaciones.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.error.archivoVacio"));
				} else { 
					//if (ControlArchivo.esArchivoProhibido(file.getOriginalFilename())) {
					//	resultadoValidaciones.append("error.archivoProhibido");
					//} else { 
					if (!Validaciones.esNombreArchivoOtrosDebitos(file.getOriginalFilename())) {
						resultadoValidaciones.append(Propiedades.MENSAJES_PROPIEDADES.getString("error.ImportarOtrosDebitos.formatArchivo"));
					} else { 
						if (ControlArchivo.archivoTieneExtensionProhibida(file.getOriginalFilename())) {
							resultadoValidaciones.append(Propiedades.MENSAJES_PROPIEDADES.getString("error.extensionProhibida"));
						} else { 
							if (ControlArchivo.superaPesoMaximoPermitido(file)) {
								resultadoValidaciones.append(Propiedades.MENSAJES_PROPIEDADES.getString("error.pesoMaximoSuperado"));
							} else { 
								if (!fileExtension.equals("txt")) {
									resultadoValidaciones.append(Propiedades.MENSAJES_PROPIEDADES.getString("error.extenciontxtEsperada"));
							}else if (ControlArchivo.archivoTieneCaracteresNoPermitidos(file.getOriginalFilename())) {
								resultadoValidaciones.append( "error.caracteresInvalidos");
								}
							}
						}
					}
					//}
				}
			} catch (IOException e) {
				throw new NegocioExcepcion(e);
			}
		}
		return resultadoValidaciones;
	}


	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Long crear(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void anular(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}



	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}

}
