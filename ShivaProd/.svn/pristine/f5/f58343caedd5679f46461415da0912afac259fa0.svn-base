package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoAcuerdoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.ldap.elementos.RolLdap;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
//import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeltaServicio;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.conciliacion.MotorProcesoConciliacion;
import ar.com.telecom.shiva.negocio.dto.InterdepositoDto;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.dto.TransferenciaDto;
import ar.com.telecom.shiva.negocio.servicios.IBoletaSinValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.IConciliacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IMotorProcesamientoConciliacionAltaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;
import ar.com.telecom.shiva.negocio.servicios.IReglaConciliacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.servicios.validacion.IReglaConciliacionValidacionServicio;
import ar.com.telecom.shiva.persistencia.dao.IOrganismoDao;
import ar.com.telecom.shiva.persistencia.dao.impl.GenericoDaoImpl;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCliente;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCuit;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrganismo;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.TeamComercialDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValoresDto;

@SuppressWarnings("unchecked")
public class MotorProcesamientoConciliacionAltaServicioImpl extends Servicio implements IMotorProcesamientoConciliacionAltaServicio {

	@Autowired 
	private MotorProcesoConciliacion motorProcesoConciliacion;
	@Autowired 
	private IReglaConciliacionValidacionServicio reglaConciliacionValidacionServicio;
	@Autowired
	private IParametroServicio parametroServicio;
	@Autowired
	private IReglaConciliacionServicio reglaConciliacionServicio;
	@Autowired
	private IRegistroAVCServicio registroServicio;
	@Autowired
	private IBoletaSinValorServicio boletaSinValorServicio;
	@Autowired
	private IConciliacionServicio conciliacionServicio;
	@Autowired
	private IValorServicio valorServicio;
	
	@Autowired
	IClienteSiebelServicio clienteSiebelServicio;
	@Autowired 
	ILdapServicio ldapServicio;
	@Autowired
	private IOrganismoDao organismoDao;
	@Autowired
	private GenericoDaoImpl genericoDao;
	@Autowired
	private ITeamComercialServicio teamComercialServicio;
	@Autowired
	private IClienteServicio clienteServicio;
	/**
	 * Método principal del motor de conciliacion.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void ejecutarProcesoConciliacion(RegistroAVCDto registroAvc, HashMap<String, List<String>> cuerpoMailMap) throws NegocioExcepcion {

//		try{
//			if(registroAvc instanceof DepositoDto){
				// Deposito
				
//				Traza.advertencia(MotorProcesamientoConciliacionAltaServicioImpl.class,  "Procesando el registro avc deposito id: " + registroAvc.getIdRegistro());
//				Traza.advertencia(MotorProcesamientoConciliacionAltaServicioImpl.class, "Datos del registro: tipo Valor: " + prepararDatoParaMostrar(registroAvc.getTipoValor()) +
//						" número cliente: " + prepararDatoParaMostrar(registroAvc.getCodigoCliente()) + 
//						" importe: " + prepararDatoParaMostrar(String.valueOf(registroAvc.getImporteParaComparar())) + 
//						" número boleta: " + prepararDatoParaMostrar(String.valueOf(((DepositoDto)registroAvc).getNroBoleta())) + 
//						" acuerdo: " + prepararDatoParaMostrar(registroAvc.getIdAcuerdo()) +
//						" número cheque: " + prepararDatoParaMostrar(String.valueOf(((DepositoDto)registroAvc).getNumeroCheque())) +
//						" estado: " + ((DepositoDto)registroAvc).getEstadoFormateado()
//						);
//				
//				// Obtengo todas las reglas
//				List<ReglaConciliacionDto> reglas = obtenerReglasConciliacion();
//				
//				// Validamos las reglas
//				if(validarReglasConciliacion(reglas)){
//				
//					// Aplico las reglas de conciliacion
//					ReglaConciliacionDto reglaDto = null;
//					List<BoletaSinValorDto> listaBoletaConValor = null;
//					
//					// Busco las Boletas sin y con valor en estado Pendiente de conciliar.
//					switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(registroAvc.getTipoValor()))) {
//					    case BOLETA_DEPOSITO_CHEQUE:
//					    	reglaDto = obtenerReglasBoletaDepositoCheque(reglas);
//					    	listaBoletaConValor = obtenerBoletasConValorCheque();
//					    	break;
//					    case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
//					    	reglaDto = obtenerReglasBoletaDepositoChequeDiferido(reglas);
//					    	listaBoletaConValor = obtenerBoletasConValorChequeDiferido();
//					    	break;
//					    case BOLETA_DEPOSITO_EFECTIVO:
//					    	reglaDto = obtenerReglasBoletaDepositoEfectivo(reglas);
//					    	listaBoletaConValor = obtenerBoletasConValorEfectivo();
//					    	break;
//					    default:
//					}
//					
//					if(!Validaciones.isObjectNull(reglaDto)){
//						if(!Validaciones.isObjectNull(listaBoletaConValor)){
//							// por cada RAVC lo comparo con todas las boletas sin valor mas 
//							// con las boletas con valor segun el tipo de valor
//							aplicarReglasConciliacion(reglaDto, (DepositoDto) registroAvc,listaBoletaConValor,cuerpoMailMap);
//						}
//					}
//				}
//			}else{
				if(registroAvc instanceof InterdepositoDto){
					Traza.advertencia(MotorProcesamientoConciliacionAltaServicioImpl.class, "Procesando el registro avc interdeposito id: " + registroAvc.getIdRegistro());
					procesarInterdeposito((InterdepositoDto) registroAvc);
				} else if (registroAvc instanceof TransferenciaDto) {
					Traza.advertencia(MotorProcesamientoConciliacionAltaServicioImpl.class, "Procesando el registro avc transferencia id: " + registroAvc.getIdRegistro());
					procesarTransferencia((TransferenciaDto) registroAvc);
				}
//			}
//		}catch(ShivaExcepcion e){
//			throw new NegocioExcepcion(e.getMessage(), e);
//		}
	}
	
	public String prepararDatoParaMostrar(String dato){
		return Validaciones.isNullOrEmpty(dato)?"-":dato;
	}
	
	/**
	 * Recorre y procesa los registros AVC y las boletas que esten en 
	 * estado "Pendiente de conciliar" y aplica las reglas de conciliacion para
	 * relacionar un registro AVC con un boleta formando una conciliacion Total o Sugerida.
	 * 
	 * @param regla
	 * @param registroAvc
	 * @param listaBoletaSinValor
	 * @throws ShivaExcepcion 
	 * @throws NegocioExcepcion 
	 */
//	private void aplicarReglasConciliacion(ReglaConciliacionDto regla,DepositoDto registroAvc,
//			List<BoletaSinValorDto> listaBoletaSinValor,HashMap<String, List<String>> cuerpoMailMap) throws ShivaExcepcion, NegocioExcepcion {
//		
//		
//		Conciliacion conciliacionAux = null;
//		BoletaSinValorDto boletaAux = null;
//		
//		if(Validaciones.isCollectionNotEmpty(listaBoletaSinValor)){
//			// Recorro las boletas
//			for (BoletaSinValorDto boletaDto : listaBoletaSinValor) {
//				Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Comparando con la boleta número: " + boletaDto.getNumeroBoleta());	
//				Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Datos de la boleta  id: "+ boletaDto.getIdBoleta() +
//															" tipo Valor: "+ prepararDatoParaMostrar(boletaDto.getTipoValor()) +
//															" número cliente: " + prepararDatoParaMostrar(boletaDto.getCodCliente()) + 
//															" importe: " + prepararDatoParaMostrar(String.valueOf(boletaDto.getImporteParaComparar())) + 
//															" número boleta: " + prepararDatoParaMostrar(boletaDto.getNumeroBoleta()) + 
//															" acuerdo: " + prepararDatoParaMostrar(boletaDto.getAcuerdo()) + 
//															" número cheque: " + prepararDatoParaMostrar(boletaDto.getNumeroCheque()) +
//															" estado: " + boletaDto.getEstado());
//				
//				for(Conciliacion conciliacion : regla.getListaConciliacionOrdenada()){
//						if(conciliacion.getEstado().equals(EstadoReglaConciliacionEnum.ACTIVO)){
//							boolean coinciden = false; 
//							
//							for(CampoCoincidente campo : conciliacion.getListaCamposCoincidentes()){
//								if(compararCampo(campo,registroAvc,boletaDto)){
//									coinciden = true;
//								}else{
//									coinciden = false;
//									break;
//								}
//							}
//							
//							if(coinciden){
//								Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Concilió el registro id: "+registroAvc.getIdRegistro() +" con la boleta id: "+boletaDto.getIdBoleta()+" número: " + boletaDto.getNumeroBoleta()
//										+ " caso: " + conciliacion.getTipoConciliacion() + " " + (conciliacion.getNumeroConciliacion()-1));	
//								// Conciliacion Total
//								if(TipoConciliacionEnum.TOTAL.equals(conciliacion.getTipoConciliacion())){
//									// Actualizar el registro AVC y la boleta
//									conciliar(conciliacion,registroAvc,boletaDto,cuerpoMailMap);
//									boletaAux = boletaDto;
//									return;
//								}
//								
//								// Conciliacion Sugerida
//								// verifico el orden de la conciliacion para saber con cual me quedo.
//								if(Validaciones.isObjectNull(conciliacionAux)){
//									conciliacionAux = conciliacion;
//									boletaAux = boletaDto;
//								}else{
//									if(conciliacion.getOrdenConciliacion()<conciliacionAux.getOrdenConciliacion()){
//										conciliacionAux = conciliacion;
//										boletaAux = boletaDto;
//									}
//								}
//							} else {
//								Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "No conciliaron el registro id: "+registroAvc.getIdRegistro() +" con la boleta id: "+boletaDto.getIdBoleta()+" número: " + boletaDto.getNumeroBoleta()
//										+ "  con la regla: " + (TipoConciliacionEnum.TOTAL.equals(conciliacion.getTipoConciliacion())?TipoConciliacionEnum.TOTAL.getDescripcion():(conciliacion.getNumeroConciliacion()-1)));
//							}
//						}
//					}
//				}
//				
//			// Actualizar el registro AVC y la boleta con conciliacionAux y boletaAux
//			if(Validaciones.isObjectNull(conciliacionAux) && Validaciones.isObjectNull(boletaAux)){
//				Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "No Concilio el registro id: "+registroAvc.getIdRegistro() + " con ninguna boleta.");
//			}else{
//				Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Concilio definitivamente el registro id: "+registroAvc.getIdRegistro() +" con la boleta id: "+boletaAux.getIdBoleta()+" número: " + boletaAux.getNumeroBoleta()
//						+ "  con la regla: " + (TipoConciliacionEnum.TOTAL.equals(conciliacionAux.getTipoConciliacion())?TipoConciliacionEnum.TOTAL.getDescripcion():(conciliacionAux.getNumeroConciliacion()-1)));
//				conciliar(conciliacionAux,registroAvc,boletaAux,cuerpoMailMap);
//			}
//		} else {
//			Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "No hay Boletas pendientes para conciliar con el registro AVC.");
//		}
//	}

	/**
	 * Realiza la conciliacion, sea de tipo Total o Sugerida, cambiando los estados del registro AVC
	 * y de la boleta. Ademas verifica si la boleta tiene un valor asociado y toma las acciones 
	 * que correspondan.
	 * @param conciliacion
	 * @param registroAvc
	 * @param boletaDto
	 * @throws NegocioExcepcion
	 */
//	private void conciliar(Conciliacion conciliacion, DepositoDto registroAvc, BoletaSinValorDto boletaDto,HashMap<String, List<String>> cuerpoMailMap) throws NegocioExcepcion{
//		String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
//		registroAvc.setUsuarioModificacion(usuarioBatch);
//		boletaDto.setUsuarioModificacion(usuarioBatch);
//		
//		if(conciliacion.getTipoConciliacion().equals(TipoConciliacionEnum.TOTAL)){
//			// Conciliacion Total
//			
//			Long idValorAsociado = boletaSinValorServicio.getIdValorAsociadoABoleta(boletaDto,registroAvc.getTipoValor());
//			if(Validaciones.isObjectNull(idValorAsociado)){
//				// Si no tiene valor asociado lo creo
//				ValorDto valorDto = conciliacionServicio.crearValorDtoApartirDeRegistroAvcYBoleta(registroAvc,boletaDto);
//				ShvValValor valorCreado = valorServicio.crearValor(valorDto);
//				valorServicio.asociarValorConBoleta(valorCreado,Long.valueOf(boletaDto.getIdBoleta().toString()));
//				idValorAsociado = valorCreado.getIdValor();
//			}
//			
//			// valor -> DISPONIBLE usando disponibilizarBoleta
//			valorServicio.disponibilizarValorAsociadoBoleta(idValorAsociado,registroAvc.getFechaPago(),cuerpoMailMap);
//			
//			
//			// boleta -> CONCILIADO
//			ShvBolBoleta boletaActualizada = boletaSinValorServicio.establecerRegistroConciliado(boletaDto);
//			// registro -> CONCILIADO
//			ShvAvcRegistroAvc registroActualizado = registroServicio.establecerRegistroConciliado(registroAvc);
//			// Grabo en la tabla ShvAvcRegistroAvcBoleta
//			conciliacionServicio.guardarAsociacionDeConciliacion(boletaActualizada,registroActualizado);
//			
//		}else{
//			// Conciliacion Sugerida
//			
//			// registro -> CONCILIACION SUGERIDA
//			ShvAvcRegistroAvc registroActualizado = registroServicio.establecerRegistroConciliacionSugerida(registroAvc);
//			// boleta -> CONCILIACION SUGERIDA
//			ShvBolBoleta boletaActualizada = boletaSinValorServicio.establecerRegistroConciliacionSugerida(boletaDto);
//			// Grabo en la tabla ShvAvcRegistroAvcBoleta
//			conciliacionServicio.guardarAsociacionDeConciliacion(boletaActualizada,registroActualizado);
//		}
//	}

	/**
	 * Segun un campo Coincidente, compara los valores del registro AVC y de la boleta segun dicho campo. 
	 * @param campoCoincidente
	 * @param registroAvc
	 * @param boletaDto
	 * @return
	 * @throws ShivaExcepcion
	 */
//	private boolean compararCampo(CampoCoincidente campoCoincidente, RegistroAVCDto registroAvc, BoletaSinValorDto boletaDto) throws ShivaExcepcion {
//		try {
//			String datoRAVC = null;
//			String datoBoleta = null;
//			
//			List<Field>  campos =  Utilidad.getDTOFields(registroAvc);
//			for (Field campo : campos) {
//				
//				if (campo.getName().equalsIgnoreCase(campoCoincidente.getAtributoRegistro())){
//					campo.setAccessible(true);
//					if(!Validaciones.isObjectNull(campo.get(registroAvc))){
//						datoRAVC = campo.get(registroAvc).toString();
//					}
//					break;
//				}
//    		}
//			
//			Field[] bolDto = boletaDto.getClass().getDeclaredFields();
//			for (int i=0; i< bolDto.length; i++){
//				if (bolDto[i].getName().equals(campoCoincidente.getAtributoBoleta())){
//					bolDto[i].setAccessible(true);
//					if(!Validaciones.isObjectNull(bolDto[i].get(boletaDto))){
//						datoBoleta = bolDto[i].get(boletaDto).toString();
//					}
//					break;
//				}
//			}
//			
//			if(!Validaciones.isObjectNull(datoRAVC) && !Validaciones.isObjectNull(datoBoleta)){
//				if(datoRAVC.equalsIgnoreCase(datoBoleta)){
//					return true;
//				}
//			}
//			
//		}catch (IllegalArgumentException e){ 
//			throw new ShivaExcepcion(e);
//		}catch (IllegalAccessException e) {
//			throw new ShivaExcepcion(e);
//		}
//		return false;
//	}

	/**
	 * Valida las reglas de conciliacion
	 * @param reglas
	 * @return
	 * @throws NegocioExcepcion
	 */
//	private Boolean validarReglasConciliacion(List<ReglaConciliacionDto> reglas) throws NegocioExcepcion{
//		boolean resultado = false;
//		
//		for (ReglaConciliacionDto reglaConciliacion : reglas) {
//			//	Exista siempre al menos 1 regla de conciliación total, y solo 1.
//			if(reglaConciliacionValidacionServicio.validarCantConciliacionTotal(reglaConciliacion)){
//				
//				//	Exista siempre al menos una regla de conciliación parcial.
//				if(reglaConciliacionValidacionServicio.validarCantConciliacionParcial(reglaConciliacion)){
//					
//					//	El orden de las reglas de conciliación total debe ser mayor a aquellas de conciliación parcial.
//					if(reglaConciliacionValidacionServicio.validarOrdenConciliacionTotal(reglaConciliacion)){
//						
//						//	Las reglas de conciliación no se dupliquen.
//						if(!reglaConciliacionValidacionServicio.validarDuplicidadRegla(reglaConciliacion)){
//							resultado = true;
//						}
//					}
//				}
//			}
//			
//			if(!resultado){
//				return false;
//			}
//		}
//		
//		return resultado;
//	}

	/**
	 * Obtiene las reglas de los xml y luego consulta a 
	 * la base para saber el orden de las mismas.
	 * 
	 * @return Las reglas de conciliacion
	 * @throws NegocioExcepcion
	 */
//	private List<ReglaConciliacionDto> obtenerReglasConciliacion() throws NegocioExcepcion{
//		// Obtengo las reglas de los xml
//		List<ReglaConciliacionDto> reglas = motorProcesoConciliacion.getReglas();
//		
//		// Obtengo la lista de reglas de la base de datos
//		reglaConciliacionServicio.listarReglasConciliacion(reglas);
//		
//		return reglas;
//	}
	
	/**
	 * Obtenie las reglas de conciliacion para las Boletas Deposito Cheque.
	 * @param reglas
	 * @return
	 */
//	private ReglaConciliacionDto obtenerReglasBoletaDepositoCheque(List<ReglaConciliacionDto> reglas) {
//		for (ReglaConciliacionDto reglaConciliacionDto : reglas) {
//			if(reglaConciliacionDto.getTipoValor().equals(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE)
//					&& reglaConciliacionDto.getEstado().equals(EstadoReglaConciliacionEnum.ACTIVO)){
//				return reglaConciliacionDto;
//			}
//		}
//		return null;
//	}
	
	/**
	 * Obtenie las reglas de conciliacion para las Boletas Deposito Cheque Diferido.
	 * @param reglas
	 * @return
	 */
//	private ReglaConciliacionDto obtenerReglasBoletaDepositoChequeDiferido(List<ReglaConciliacionDto> reglas) {
//		for (ReglaConciliacionDto reglaConciliacionDto : reglas) {
//			if(reglaConciliacionDto.getTipoValor().equals(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO)
//					&& reglaConciliacionDto.getEstado().equals(EstadoReglaConciliacionEnum.ACTIVO)){
//				return reglaConciliacionDto;
//			}
//		}
//		return null;
//	}
	
	/**
	 * Obtenie las reglas de conciliacion para las Boletas Deposito Efectivo.
	 * @param reglas
	 * @return
	 */
//	private ReglaConciliacionDto obtenerReglasBoletaDepositoEfectivo(List<ReglaConciliacionDto> reglas) {
//		for (ReglaConciliacionDto reglaConciliacionDto : reglas) {
//			if(reglaConciliacionDto.getTipoValor().equals(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO)
//					&& reglaConciliacionDto.getEstado().equals(EstadoReglaConciliacionEnum.ACTIVO)){
//				return reglaConciliacionDto;
//			}
//		}
//		return null;
//	}
	
//	/**
//	 * Obtenie las Boletas con Valor del tipo Deposito Cheque y las boletas
//	 * sin valor en estado PENDIENTE.
//	 * @param reglas
//	 * @return
//	 */
//	private List<BoletaSinValorDto> obtenerBoletasConValorCheque() throws NegocioExcepcion {
//		return boletaSinValorServicio.listarBoletasPendientesConciliar(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE);
//	}
//	
//	/**
//	 * Obtiene las Boletas Deposito con Valor del tipo Cheque Diferido y las boletas
//	 * sin valor en estado PENDIENTE.
//	 * @param reglas
//	 * @return
//	 */
//	private List<BoletaSinValorDto> obtenerBoletasConValorChequeDiferido() throws NegocioExcepcion {
//		return boletaSinValorServicio.listarBoletasPendientesConciliar(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO);
//	}
//	
//	/**
//	 * Obtenie las Boletas con Valor del tipo Deposito Efectivo y las boletas
//	 * sin valor en estado PENDIENTE.
//	 * @param reglas
//	 * @return
//	 */
//	private List<BoletaSinValorDto> obtenerBoletasConValorEfectivo() throws NegocioExcepcion {
//		return boletaSinValorServicio.listarBoletasPendientesConciliar(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO);
//	}
	
	/**
	 * Procesa los registros AVC de tipo interdeposito.
	 * @param interdepositoDto
	 * @throws NegocioExcepcion
	 */
	private void procesarInterdeposito(InterdepositoDto interdepositoDto) throws NegocioExcepcion{
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "-----------------------------------------------------------------------------------------------------");
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, " ");
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Se va a procesar un registro de Interdeposito");
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "IdRegistro:         " + interdepositoDto.getIdRegistro());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Acuerdo:            " + interdepositoDto.getAcuerdo());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Cod. Organismo:     " + interdepositoDto.getCodigoOrganismo());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Cod. Interdepósito: " + interdepositoDto.getCodigoInterdeposito());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Importe:            " + interdepositoDto.getImporte());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Fecha Ingreso:      " + interdepositoDto.getFechaIngresoFormateada());
		
		String errorAltaInterdeposito = null;
		ShvParamOrganismo organismo = null;
		if (!Validaciones.isObjectNull(interdepositoDto.getCodigoOrganismo())) {
			try {
				organismo = organismoDao.buscarOrganismo(interdepositoDto.getCodigoOrganismo());
				
				if (Validaciones.isObjectNull(organismo)) {
					errorAltaInterdeposito = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.interdeposito.obligatorio.codigoOrganismo"), interdepositoDto.getCodigoOrganismo());
					Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAltaInterdeposito);
				} 
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		} else {
			errorAltaInterdeposito = Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.interdeposito.obligatorio.codigoOrganismoNoInformado");
			Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAltaInterdeposito);
		}
		buscarClienteYAnalista(interdepositoDto, (organismo != null)?organismo.getNumeroClienteAsociado():null, errorAltaInterdeposito, null);
	}
	
	/**
	 * Procesa los registros AVC de tipo interdeposito.
	 * @param transferenciaDto
	 * @throws NegocioExcepcion
	 */
	private void procesarTransferencia(TransferenciaDto transferenciaDto) throws NegocioExcepcion{
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "-----------------------------------------------------------------------------------------------------");
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, " ");
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Se va a procesar un registro de Transferencia");
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "IdRegistro:         " + transferenciaDto.getIdRegistro());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Acuerdo:            " + transferenciaDto.getAcuerdo());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Cod. Organismo:     " + transferenciaDto.getCodigoOrganismo());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Referencia: 		   " + transferenciaDto.getReferencia());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Importe:            " + transferenciaDto.getImporte());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Fecha Ingreso:      " + transferenciaDto.getFechaIngresoFormateada());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Observaciones:      " + transferenciaDto.getObservacion());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "CUIT:               " + transferenciaDto.getCuit());
		Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Codigo Cliente      " + transferenciaDto.getCodigoCliente());
		
		String errorAltaTransferencia = null;
		String idClienteLegado = null;
		String segmento125 = null;
		
		// 
		// Esto es para realizar nuevamente la búsqueda del CUIT una vez que el registro AVC ha sido volcado en la base, momento en que se 
		// realiza la búsqueda del CUIT dentro de observaciones
		//
		if (Validaciones.isObjectNull(transferenciaDto.getCuit())) {
			transferenciaDto.setCuit(Utilidad.cuitFormateado(transferenciaDto.getObservacion().toCharArray()));
			Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "El CUIT original del registro AVC es nulo. Lo vuelvo a obtener con el nuevo algoritmo: " + transferenciaDto.getCuit());
		}
		
		if (!Validaciones.isObjectNull(transferenciaDto.getCuit())) {
			try {
				
				List<ShvParamCuit> listaCuit = (List<ShvParamCuit>) genericoDao.listarPorValor(ShvParamCuit.class, "cuit", transferenciaDto.getCuit());
				
				if(!Validaciones.isObjectNull(listaCuit)){
					if (!Validaciones.isCollectionNotEmpty(listaCuit)) {
						errorAltaTransferencia = Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.incorrecto.cuit");
						Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAltaTransferencia);
					} else {
						if(listaCuit.size() > 1){
							String listaClienteSegmento = "";
							for(ShvParamCuit cuitActual:listaCuit){
								listaClienteSegmento += cuitActual.getIdClienteLegado() + " - " + cuitActual.getIdSegmento()+", ";
							}
							listaClienteSegmento = listaClienteSegmento.substring(0, listaClienteSegmento.length()-2);
							errorAltaTransferencia = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.incorrecto.cuit.variosSegmentos"), listaClienteSegmento);
							Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAltaTransferencia);
						}else{
							ShvParamCuit cuitModelo = listaCuit.get(0);
							idClienteLegado = String.valueOf(cuitModelo.getIdClienteLegado());
							segmento125 = String.valueOf(cuitModelo.getIdSegmento());
						}
						
					}
				}else{
					errorAltaTransferencia = Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.incorrecto.cuit");
					Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAltaTransferencia);
				}
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		} else {
			if (TipoAcuerdoEnum.TRANSFERENCIA.descripcion().equals(transferenciaDto.getIdAcuerdo())) {
				errorAltaTransferencia = Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.obligatorio.cuit");
				Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAltaTransferencia);
			} else {
				
				if (TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion().equals(transferenciaDto.getIdAcuerdo()) 
						|| TipoAcuerdoEnum.INTERDEPOSITO_94.descripcion().equals(transferenciaDto.getIdAcuerdo())) {
					// Es acuerdo 87 o 94
					try {
						List<ShvParamBancoCliente> listaBancoCliente = (List<ShvParamBancoCliente>) genericoDao.listar(ShvParamBancoCliente.class);
						if (listaBancoCliente != null){
							for (ShvParamBancoCliente banc : listaBancoCliente) {
								// IM02147295 - se arregla la validacion de codigo de banco
								if (banc.getCodigoOpBanco().equals(Utilidad.rellenarCerosIzquierda(String.valueOf(transferenciaDto.getCodigo()),3))){
									if (String.valueOf(banc.getIdAcuerdo()).equals(transferenciaDto.getIdAcuerdo()) && TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion().equals(transferenciaDto.getIdAcuerdo())){
										if (banc.getSucursal() != null && transferenciaDto.getSucursal() != null){
											if (banc.getSucursal().equals(String.valueOf(transferenciaDto.getSucursal()))){
												idClienteLegado= banc.getIdClienteLegado();
												break;
											}
										}
									} else if (TipoAcuerdoEnum.INTERDEPOSITO_94.descripcion().equals(String.valueOf(banc.getIdAcuerdo()))) {
										idClienteLegado= banc.getIdClienteLegado();
										break;
									}
								}
							}
							if (idClienteLegado == null){
								if (TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion().equals(transferenciaDto.getIdAcuerdo())){
									errorAltaTransferencia = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.bancoCliente.87"),
																TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion(),String.valueOf(transferenciaDto.getSucursal()),String.valueOf(transferenciaDto.getCodigo()));
								} else {
									errorAltaTransferencia = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.bancoCliente.94"),
																TipoAcuerdoEnum.INTERDEPOSITO_94.descripcion(),String.valueOf(transferenciaDto.getCodigo()));
								}
								Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, errorAltaTransferencia);
							}
						}
					} catch (PersistenciaExcepcion e) {
						throw new NegocioExcepcion(e.getMessage(), e);
					}
				} else {
					idClienteLegado = transferenciaDto.getCodigoCliente();
					if (idClienteLegado == null) {
						errorAltaTransferencia = Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.bancoCliente");
						Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, errorAltaTransferencia);
					}
				}
			}
		}
		buscarClienteYAnalista(transferenciaDto, idClienteLegado, errorAltaTransferencia, segmento125);
	}
	
	private void buscarClienteYAnalista(RegistroAVCDto dto, String clienteLegado, String errorAlta, String segmento125) throws NegocioExcepcion{
		ValorDto valorDto = new ValorDto();
		String clienteAgrupador = null;
		String razonSocial = null;
		ClienteBean clienteBean = null;
		

		if (Validaciones.isNullOrEmpty(errorAlta)){
			try {
				Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Se ha encontrado un cliente valido");
				Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Cod. Cliente: " + clienteLegado);
				Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, " ");
				Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Busco 'Cliente' en 'Siebel' y en 'ShvParamCliente'...");
				clienteBean = this.clienteServicio.consultarCliente(clienteLegado);
				valorDto.setCliente(this.clienteServicio.mapear(clienteBean));
			} catch (NegocioExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(),e);
			}
			
			if (Validaciones.isObjectNull(clienteBean)) {
				errorAlta = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.obligatorio.cliente"), clienteLegado);
				Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAlta);
			} else {
				// Con el cliente consultar a Delta para obtener el analista definido
				// en el Team Comercial del cliente
				Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, " ");
				Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Busco 'Team Comercial' en 'Delta'...");
	
				razonSocial = clienteBean.getRazonSocialClienteAgrupador();
				clienteAgrupador = clienteBean.getIdClientePerfil();
				TeamComercialDto salida = teamComercialServicio.buscarTeamComercial(clienteLegado);

				if (Validaciones.isObjectNull(salida)) {
					errorAlta = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.obligatorio.clienteDelta"), clienteLegado);
					Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAlta);
				} else {
					
					String legajoAnalista = null;
					String legajoAnalistaFormateado = null;
					if (!Validaciones.isNullOrEmpty(salida.getUserAnalistaCobranzaDatos())) {
						legajoAnalista = salida.getUserAnalistaCobranzaDatos();
						legajoAnalistaFormateado = salida.getUserAnalistaCobranzaDatos()+" - "+salida.getAnalistaCobranzaDatos();
						Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Se ha encontrado en 'Delta' un 'Asistente de Datos': " + legajoAnalistaFormateado);
					} else {
						errorAlta = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.clienteDelta.noPerfilesDatosCobranza"), clienteLegado);
						Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAlta);
					}
					
					if (!Validaciones.isNullOrEmpty(legajoAnalista)) {
						// Si existe analista
						// Consultar a LDAP el perfil y el segmento
						Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, " ");
						Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Busco 'roles de usuario' en 'Ldap'...");
						List<RolLdap> rolesLdap = (List<RolLdap>) ldapServicio.buscarRolesAsociadosAUid(legajoAnalista);
						
						if (rolesLdap.size() == 0) {
							errorAlta = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.clienteDelta.noPerfilesAsociados"), legajoAnalista, clienteLegado);
							Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAlta);
						} else {
							Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "El legajo '" + legajoAnalistaFormateado + "' obtenido desde 'Delta' posee perfiles asociados. Se procede a analizarlos...");
	
							Collection<RolLdap> listaRolesAnalistasTA = new ArrayList<RolLdap>();
							
							for (RolLdap rolLdap : rolesLdap) {
								if (rolLdap.esAnalistaTA()) {
									Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Rol recuperado de usuario: '" + rolLdap.getDescripcion() + "' es de tipo 'Analista - Telecom Argentina'.");
									listaRolesAnalistasTA.add(rolLdap);
								} else {
									Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Rol recuperado de usuario: '" + rolLdap.getDescripcion() + "' NO es de tipo 'Analista - Telecom Argentina'.");
								}
							}
							
							if (listaRolesAnalistasTA.isEmpty()) {
								errorAlta = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.clienteDelta.noPerfilAnalista"), legajoAnalistaFormateado, clienteLegado);
								Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAlta);
							} else {
								if (segmento125 == null && listaRolesAnalistasTA.size() > 1) {
									errorAlta = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.clienteDelta.variosPerfilesAsociados"), legajoAnalistaFormateado, clienteLegado);
									Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAlta);
								}
								
								if (segmento125 != null || listaRolesAnalistasTA.size() == 1) {
									Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Existe un perfil de 'Analista - Telecom Argentina' definido en 'Delta' para este cliente. Se procede al alta del valor...");
									
									RolLdap rol = listaRolesAnalistasTA.iterator().next();
									
									valorDto.setCodCliente(clienteLegado);
									valorDto.setCodClienteAgrupador(clienteAgrupador);
									valorDto.setRazonSocialClienteAgrupador(razonSocial);
									if (segmento125 != null){
										valorDto.setIdSegmento(segmento125);
									} else {
										valorDto.setIdSegmento(rol.getDescripcion().split("_")[Constantes.POSICION_SEGMENTO_LDAP]);
									}
									valorDto.setIdAnalista(legajoAnalista);
								}
							}	
						}
					}
				}
			}
		}
		String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
		ShvAvcRegistroAvc registroAvc = registroServicio.buscarRegistroAVCModelo(String.valueOf(dto.getIdRegistro())); 
		
		if (Validaciones.isNullOrEmpty(errorAlta)){
			
				valorDto.setIdEmpresa(Constantes.EMPRESA_TELECOM_ARGENTINA);
				valorDto.setCodOrganismo(dto.getCodigoOrganismo());
				valorDto.setIdOrigen(Constantes.ORIGEN_CONCILIACION_ID);
				valorDto.setImporte(dto.getImporte().toString().replace(".", ","));
				valorDto.setIdAcuerdo(dto.getIdAcuerdo());
				valorDto.setGenerarValorDispoblePorDefecto(true);
				valorDto.setUsuarioModificacion(usuarioBatch);
				valorDto.setMigracion(true);

				if (dto instanceof TransferenciaDto){
					valorDto.setFechaTransferencia(Utilidad.formatDateAndTime(((TransferenciaDto)dto).getFechaIngreso()));
					valorDto.setIdTipoValor(String.valueOf(TipoValorEnum.TRANSFERENCIA.getIdTipoValor()));
					valorDto.setNumeroReferencia(String.valueOf(((TransferenciaDto)dto).getReferencia()));
					valorDto.setCuit(((TransferenciaDto)dto).getCuit());
				} else {
					//es interdeposito
					valorDto.setFechaInterdeposito(Utilidad.formatDateAndTime(((InterdepositoDto)dto).getFechaIngreso()));
					valorDto.setIdTipoValor(String.valueOf(TipoValorEnum.INTERDEPÓSITO.getIdTipoValor()));
					valorDto.setNumeroInterdepositoCdif(((InterdepositoDto)dto).getCodigoInterdeposito());
				}
				
				// esto debo hacerlo porque el metodo de validar duplicidad recibe estos parametros
				ValoresDto valoresDto = new ValoresDto();
				valoresDto.getListaValoresDto().add(valorDto);
				UsuarioSesion usuarioSesion = new UsuarioSesion(usuarioBatch, usuarioBatch, null);
				
				try {
					if (TipoAcuerdoEnum.INTERDEPOSITO_94.descripcion().equals(dto.getIdAcuerdo())
							|| !valorServicio.validarDuplicidadAlta(valoresDto , usuarioSesion)) {
						
						ShvValValor valor = valorServicio.crearValor(valorDto );
						dto.setUsuarioModificacion(usuarioBatch);
						registroServicio.guardarRelacionConValor(registroAvc.getIdRegistroAvc(), valor);
						Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Se creo el valor transferencia id: " + valor.getIdValor()
								+ " a partir del registro avc id: " + dto.getIdRegistro());
					} else {
						if (dto instanceof TransferenciaDto) {
							if (TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion().equals(dto.getIdAcuerdo())){
								errorAlta = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.duplicidadValor.87"), String.valueOf(((TransferenciaDto)dto).getReferencia()));
							} else { // es 125
								errorAlta = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.duplicidadValor.125"), valorDto.getImporte(),
										String.valueOf(((TransferenciaDto)dto).getReferencia()),Utilidad.formatDateAndTime(((TransferenciaDto)dto).getFechaIngreso()),valorDto.getIdAcuerdo());
							}
						} else {
							errorAlta = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.interdeposito.duplicidadValor"), valorDto.getCodClienteAgrupador(),
										((InterdepositoDto)dto).getCodigoInterdeposito(),dto.getFechaIngresoFormateada());
						}
						Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class,errorAlta);
					}
				} catch (PersistenciaExcepcion e) {
					throw new NegocioExcepcion(e.getMessage(),e);
				}
				
				// Actualizacion de registro AVC
				actualizacionAVC(clienteLegado, errorAlta, clienteAgrupador, razonSocial, registroAvc, 
								dto instanceof TransferenciaDto ? ((TransferenciaDto)dto).getCuit() : null);
				if(Validaciones.isNullOrEmpty(errorAlta)){
					registroServicio.conciliarTransferenciaSinBoleta(registroAvc,usuarioBatch);
				} else {
					registroServicio.actualizarRegistroAvc(registroAvc);
				}
				
				if (!Validaciones.isObjectNull(valorDto.getCodClienteAgrupador())){
					Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "-----------------------------------------------------------------------------------------------------");
					Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "El registro AVC fue actualizado");
					Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "-----------------------------------------------------------------------------------------------------");
					Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, " ");
				}
		} else {
			actualizacionAVC(clienteLegado, errorAlta, clienteAgrupador, razonSocial, registroAvc,
					dto instanceof TransferenciaDto ? ((TransferenciaDto)dto).getCuit() : null);
			registroServicio.actualizarRegistroAvc(registroAvc);
			
			Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "-----------------------------------------------------------------------------------------------------");
			Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "Error: " + errorAlta);
			Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "El error fue impactado en la base de datos.");
			Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, "-----------------------------------------------------------------------------------------------------");
			Traza.auditoria(MotorProcesamientoConciliacionAltaServicioImpl.class, " ");
		}
	}

	/**
	 * 
	 * @param clienteLegado
	 * @param errorAlta
	 * @param clienteAgrupador
	 * @param razonSocial
	 * @param registroAvc
	 * @param cuit
	 */
	private void actualizacionAVC(String clienteLegado, String errorAlta,String clienteAgrupador, String razonSocial, ShvAvcRegistroAvc registroAvc, String cuit) {
		
		registroAvc.setCodigoCliente(clienteLegado);
		if (!Validaciones.isNullOrEmpty(clienteAgrupador)){
			registroAvc.setIdClientePerfil(Long.valueOf(clienteAgrupador));
			registroAvc.setRazonSocialClientePerfil(razonSocial);
		}
		
		if (registroAvc.getTransferencia() != null) {
			registroAvc.getTransferencia().setErrorAlta(errorAlta);
			registroAvc.getTransferencia().setFechaError(new Date());
			
			if (Validaciones.isObjectNull(registroAvc.getTransferencia().getCuit())) {
				registroAvc.getTransferencia().setCuit(cuit);
			}
			
		} else if (registroAvc.getInterdeposito() != null){
			registroAvc.getInterdeposito().setErrorAltaInterdeposito(errorAlta);
			registroAvc.getInterdeposito().setFechaError(new Date());
		}
	}
	
	
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public MotorProcesoConciliacion getMotorProcesoConciliacion() {
		return motorProcesoConciliacion;
	}

	public void setMotorProcesoConciliacion(
			MotorProcesoConciliacion motorProcesoConciliacion) {
		this.motorProcesoConciliacion = motorProcesoConciliacion;
	}
	
	public IReglaConciliacionValidacionServicio getReglaConciliacionValidacionServicio() {
		return reglaConciliacionValidacionServicio;
	}

	public void setReglaConciliacionValidacionServicio(
			IReglaConciliacionValidacionServicio reglaConciliacionValidacionServicio) {
		this.reglaConciliacionValidacionServicio = reglaConciliacionValidacionServicio;
	}

	public IReglaConciliacionServicio getReglaConciliacionServicio() {
		return reglaConciliacionServicio;
	}

	public void setReglaConciliacionServicio(
			IReglaConciliacionServicio reglaConciliacionServicio) {
		this.reglaConciliacionServicio = reglaConciliacionServicio;
	}

	public IRegistroAVCServicio getRegistroServicio() {
		return registroServicio;
	}

	public void setRegistroServicio(IRegistroAVCServicio registroServicio) {
		this.registroServicio = registroServicio;
	}

	public IBoletaSinValorServicio getBoletaSinValorServicio() {
		return boletaSinValorServicio;
	}

	public void setBoletaSinValorServicio(
			IBoletaSinValorServicio boletaSinValorServicio) {
		this.boletaSinValorServicio = boletaSinValorServicio;
	}


	public IConciliacionServicio getConciliacionServicio() {
		return conciliacionServicio;
	}

	public void setConciliacionServicio(IConciliacionServicio conciliacionServicio) {
		this.conciliacionServicio = conciliacionServicio;
	}

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

//
//	public IClienteDeltaServicio getClienteDeltaServicio() {
//		return clienteDeltaServicio;
//	}
//
//	public void setClienteDeltaServicio(IClienteDeltaServicio clienteDeltaServicio) {
//		this.clienteDeltaServicio = clienteDeltaServicio;
//	}

	public ILdapServicio getLdapServicio() {
		return ldapServicio;
	}

	public void setLdapServicio(ILdapServicio ldapServicio) {
		this.ldapServicio = ldapServicio;
	}

	public IClienteSiebelServicio getClienteSiebelServicio() {
		return clienteSiebelServicio;
	}

	public void setClienteSiebelServicio(
			IClienteSiebelServicio clienteSiebelServicio) {
		this.clienteSiebelServicio = clienteSiebelServicio;
	}

	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}

	public IOrganismoDao getOrganismoDao() {
		return organismoDao;
	}

	public void setOrganismoDao(IOrganismoDao organismoDao) {
		this.organismoDao = organismoDao;
	}

	public ITeamComercialServicio getTeamComercialServicio() {
		return teamComercialServicio;
	}

	public void setTeamComercialServicio(
			ITeamComercialServicio teamComercialServicio) {
		this.teamComercialServicio = teamComercialServicio;
	}

}
