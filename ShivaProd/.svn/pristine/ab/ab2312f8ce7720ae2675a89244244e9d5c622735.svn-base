package ar.com.telecom.shiva.base.jms.util.runnable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTramiteDeimosEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;
import ar.com.telecom.shiva.base.enumeradores.SemaforoGestionabilidadEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaDeudaEntrada;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaDeudaSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDebito;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosConsultaEstadoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosConsultaEstadoDocumentoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoDebito;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeimosServicio;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineDebitoMapeador;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.GestionabilidadDto;

public class ConsultaDebitosACobradoresThread extends Thread {


	SistemaEnum		            			cobrador; //Calipso, Mic, o deimos
	ShvCobEdDebito							debito;
	List<ShvCobEdDebito>   					listaDebitosConErrores;
	ICobroOnlineServicio    				cobroOnlineServicio;
	IClienteDeimosServicio					clienteDeimosServicio;
	EntradaCalipsoDeudaWS					entradaCalipsoDeudaWs;
	MicConsultaDeudaEntrada					micConsultaDeudaEntrada;
	EntradaDeimosConsultaEstadoDeudaWS		entradaDeimos;
	SalidaDeimosConsultaEstadoDocumentoWS 	respuestaDeimos;
	CobroOnlineDebitoMapeador 				debitoMapeador;
	
	public ConsultaDebitosACobradoresThread() {
	}

	/**
	 * Consulta los débitos en los cobradores: Calipso, Mic
	 * 
	 */
	
	public void run(){
		try {
			Traza.auditoria(ConsultaDebitosACobradoresThread.class,ObtenerInfoDebitoTraza() + "Comenzó el proceso del hilo.");
		
			debitoMapeador = (CobroOnlineDebitoMapeador) Configuracion.getBeanBatch("debitoMapeador");
			
			if (SistemaEnum.CALIPSO.equals(cobrador)){
				//Consultar a Calipso
				Traza.auditoria(ConsultaDebitosACobradoresThread.class,"Iniciando consulta a Calipso... " + this.getName());
				SalidaCalipsoDeudaWS salidaCalipsoDeudaWS = consultarCalipso();
				
				procesarInformacionCalipso(salidaCalipsoDeudaWS);
				
				Traza.auditoria(ConsultaDebitosACobradoresThread.class,"Terminó de consultar Tread Calipso..." + this.getName());
			
			}else{
				if (SistemaEnum.MIC.equals(cobrador)){
					//CONSULTAR A MIC
					Traza.auditoria(ConsultaDebitosACobradoresThread.class,"Iniciando consulta a MIC... " + this.getName());
					MicConsultaDeudaSalida micConsultaDeudaSalida = consultarMic();
					procesarInformacionMIC(micConsultaDeudaSalida);
					Traza.auditoria(ConsultaDebitosACobradoresThread.class,"Terminó de consultar Tread MIC..." + this.getName());
					
				}
			}
			
			cobroOnlineServicio.cambiarEstadoDebitoValidado(debito,this);
			
			Traza.auditoria(ConsultaDebitosACobradoresThread.class,"Finalizó el proceso del hilo.[ID COBRO]="+
					debito.getPk().getCobro().getIdCobro() + "| [ID DEBITO]=" + debito.getPk().getIdDebito());
		
		} catch (NegocioExcepcion e) {
			Traza.error(ConsultaDebitosACobradoresThread.class,e.getMessage() +"." + ObtenerInfoDebitoTraza());
		}

	}
	
	private MicConsultaDeudaSalida consultarMic() throws NegocioExcepcion{
		
		IMicJmsSyncServicio clienteMicServicio= (IMicJmsSyncServicio) Configuracion.getBeanBatch("micJmsSyncServicio");
		MicConsultaDeudaSalida	micConsultaDeudaSalida = null;
		try {
			micConsultaDeudaSalida =clienteMicServicio.consultarDeuda(micConsultaDeudaEntrada);
		} catch (NegocioExcepcion e) {
			Traza.error(ConsultaDebitosACobradoresThread.class,e.getMessage() +"." + ObtenerInfoDebitoTraza());
//			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return micConsultaDeudaSalida;
	}

	private SalidaCalipsoDeudaWS consultarCalipso() throws NegocioExcepcion{
		
		IClienteCalipsoServicio clienteCalipsoServicio= (IClienteCalipsoServicio) Configuracion.getBeanBatch("clienteCalipsoServicio");
		SalidaCalipsoDeudaWS salida = null;
		
		try {
			salida= clienteCalipsoServicio.consultaDebito(entradaCalipsoDeudaWs);
		} catch (NegocioExcepcion e) {
			Traza.error(ConsultaDebitosACobradoresThread.class,e.getMessage() +"." + ObtenerInfoDebitoTraza());
//			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return salida;
	}

	@SuppressWarnings("static-access")
	private void procesarInformacionCalipso(SalidaCalipsoDeudaWS salidaCalipsoDeudaWS) throws NegocioExcepcion{
		
		CobroDebitoDto cobroDebitoDto = new CobroDebitoDto();
		GestionabilidadDto gestionabilidadDto = new GestionabilidadDto();
		
		if (!Validaciones.isObjectNull(salidaCalipsoDeudaWS)) {
			
			if (OkNokEnum.OK.getDescripcion().equals(salidaCalipsoDeudaWS.getResultadoProcesamiento().getResultadoImputacion())){
					
				if (Validaciones.isCollectionNotEmpty(salidaCalipsoDeudaWS.getListaDebitos())){
					
					CalipsoDebito debitoRespuesta = salidaCalipsoDeudaWS.getListaDebitos().get(0);
					if (validarRespuestaCobradores(SistemaEnum.CALIPSO,debitoRespuesta,null)){
						
						if (SiNoEnum.SI.equals(debitoRespuesta.getMarcaMigradoDeimos())){
							if (!consultarDeimos(debitoRespuesta,null)){
								
								debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
								
							}
						}
					} else{
						debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
					}
				} else{
					debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
					debito.setResultadoValidacionDescripcionError("No se encontró el debito en el cobrador CALIPSO");
				}
			} else{
				debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
				debito.setResultadoValidacionCodigoError(salidaCalipsoDeudaWS.getResultadoProcesamiento().getCodigoError());
				debito.setResultadoValidacionDescripcionError(salidaCalipsoDeudaWS.getResultadoProcesamiento().getDescripcionError());
			}
				
			Traza.auditoria(ConsultaDebitosACobradoresThread.class,"Consultando..." + this.getName());
			
			try {
				this.sleep(5000);
			} catch (InterruptedException e) {
				Traza.error(ConsultaDebitosACobradoresThread.class,e.getMessage() +"." + ObtenerInfoDebitoTraza());
			}
			
		} else {
			debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
			debito.setResultadoValidacionDescripcionError("Error al consultar con el cobrador CALIPSO");
			Traza.auditoria(ConsultaDebitosACobradoresThread.class,"Resultado: sin datos" + this.getName());
			
		}
		
		try {
			debito.setIdDebitoReferencia(null);
			cobroDebitoDto = (CobroDebitoDto) debitoMapeador.map(debito);
			cobroDebitoDto = cobroOnlineServicio.setearAtributosPorConsulta(
				cobroDebitoDto,
				debito.getPk().getCobro().getIdCobro().toString()
			);
			cobroDebitoDto.setIdDebitoPantalla(cobroDebitoDto.getTipoComprobanteEnum() + "_" + cobroDebitoDto.getNroDoc());
			debito.setIdDebitoReferencia(cobroDebitoDto.getIdDebitoPantalla());
		} catch (NegocioExcepcion e) {
			Traza.error(ConsultaDebitosACobradoresThread.class,e.getMessage() +"." + ObtenerInfoDebitoTraza());
		}
		
		if (!EstadoDebitoImportadoEnum.VALIDACION_ERROR.equals(debito.getResultadoValidacionEstado())){
			ejecutarValidaciones(cobroDebitoDto,gestionabilidadDto);
		}
	}
	
	@SuppressWarnings("static-access")
	private void procesarInformacionMIC(MicConsultaDeudaSalida micConsultaDeudaSalida) throws NegocioExcepcion{

		CobroDebitoDto cobroDebitoDto = new CobroDebitoDto();
		GestionabilidadDto gestionabilidadDto = new GestionabilidadDto();
		
		if (!Validaciones.isObjectNull(micConsultaDeudaSalida)) {
			
			if (OkNokEnum.OK.equals(micConsultaDeudaSalida.getResultadoConsulta().getResultadoConsulta())){
					
				if (Validaciones.isCollectionNotEmpty(micConsultaDeudaSalida.getListaDebitos())){
					
					MicDebito debitoRespuesta = micConsultaDeudaSalida.getListaDebitos().get(0);
					if (validarRespuestaCobradores(SistemaEnum.MIC,null,debitoRespuesta)){
						
						if (SiNoEnum.SI.equals(debito.getMarcaMigradoDeimos())){
							
							if (!consultarDeimos(null,debitoRespuesta)){
								
								debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
							}
						}
					} else{
						debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
					}
				} else{
					debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
					debito.setResultadoValidacionDescripcionError("No se encontró el debito en el cobrador MIC");
					
				}
			} else{
				debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
				debito.setResultadoValidacionCodigoError(micConsultaDeudaSalida.getResultadoConsulta().getCodigoError());
				debito.setResultadoValidacionDescripcionError(micConsultaDeudaSalida.getResultadoConsulta().getDescripcionError());
			}
				
			Traza.auditoria(ConsultaDebitosACobradoresThread.class,"Consultando..." + this.getName());
			
			try {
				this.sleep(5000);
			} catch (InterruptedException e) {
				Traza.error(ConsultaDebitosACobradoresThread.class,e.getMessage() +"." + ObtenerInfoDebitoTraza());
			}
			
		} else {
			debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
			debito.setResultadoValidacionDescripcionError("Error al consultar con el cobrador MIC");
			Traza.auditoria(ConsultaDebitosACobradoresThread.class,"Resultado: sin datos" + this.getName());
		}
		
		try {
			debito.setIdDebitoReferencia(null);
			cobroDebitoDto = (CobroDebitoDto) debitoMapeador.map(debito);
			cobroDebitoDto = cobroOnlineServicio.setearAtributosPorConsulta(
				cobroDebitoDto,
				debito.getPk().getCobro().getIdCobro().toString()
			);

			cobroDebitoDto.setIdDebitoPantalla(cobroDebitoDto.getTipoComprobanteEnum() + "_" + cobroDebitoDto.getNroDoc());
			debito.setIdDebitoReferencia(cobroDebitoDto.getIdDebitoPantalla());
		} catch (NegocioExcepcion e) {
			Traza.error(ConsultaDebitosACobradoresThread.class,e.getMessage() +"." + ObtenerInfoDebitoTraza());
		}
		if (!EstadoDebitoImportadoEnum.VALIDACION_ERROR.equals(debito.getResultadoValidacionEstado())){
			ejecutarValidaciones(cobroDebitoDto,gestionabilidadDto);
		}
	}
	
	/**
	 * Ejecuta las validaciones restantes: Gestionabilidad y Saldo Maximo Debito
	 * @param cobroDebitoDto
	 * @param gestionabilidadDto
	 * @throws NegocioExcepcion 
	 */
	private void ejecutarValidaciones(CobroDebitoDto cobroDebitoDto, GestionabilidadDto gestionabilidadDto) throws NegocioExcepcion{
		
		if (consultarGestionabilidad(cobroDebitoDto,gestionabilidadDto)){
			
			if (!EstadoDebitoImportadoEnum.VALIDACION_ERROR.equals(debito.getResultadoValidacionEstado())){
				
				if (!consultarSaldoMaximoDebito(cobroDebitoDto)){
					debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
				} else {
					debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_OK);
				}
			}
		} else{
			debito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
		}
	}
	
	private boolean consultarDeimos(CalipsoDebito debitoCalipso, MicDebito debitoMic) throws NegocioExcepcion{
		
		boolean consultaOK = true;
		clienteDeimosServicio= (IClienteDeimosServicio) Configuracion.getBeanBatch("clienteDeimosServicio");
		try {
			if (!Validaciones.isObjectNull(debitoCalipso)){
				respuestaDeimos = clienteDeimosServicio.consultarDeimos(debitoCalipso, EmpresaEnum.TA);
				
			}else if (!Validaciones.isObjectNull(debitoMic)){
				respuestaDeimos = clienteDeimosServicio.consultarDeimos(debitoMic, EmpresaEnum.TA);
			}
			
			if (Validaciones.isObjectNull(respuestaDeimos)){
				consultaOK = false;
			} else{
				
				if (!setDatosDeimos()){
					consultaOK = false;
				}
			}
			
		} catch (NegocioExcepcion e) {
			Traza.error(ConsultaDebitosACobradoresThread.class,e.getMessage() +"." + ObtenerInfoDebitoTraza());
			debito.setResultadoValidacionDescripcionError("Error al consultar con DEIMOS");
			consultaOK = false;
//			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return consultaOK;
	}
	
	private boolean setDatosDeimos() {
			
		boolean resultadoOK=true;
		
		if(OkNokEnum.OK.getDescripcion().equals(respuestaDeimos.getResultadoProcesamiento().getResultadoImputacion())){
			if (!Validaciones.isObjectNull(respuestaDeimos.getInformacionGeneralApropiacion().getApropiada())){
				debito.setDmosApropiacionEnDeimos(respuestaDeimos.getInformacionGeneralApropiacion().getApropiada());
			}
			
			if (!Validaciones.isObjectNull(respuestaDeimos.getInformacionGeneralApropiacion().getCodigoEstadoTramite())){
				debito.setDmosEstadoTramite(EstadoTramiteDeimosEnum.getEnumtByCodigo(respuestaDeimos.getInformacionGeneralApropiacion().getCodigoEstadoTramite()));
			}
			
			if (!Validaciones.isObjectNull(respuestaDeimos.getInformacionGeneralApropiacion().getConvenio())){
				debito.setDmosNumeroConvenio(new Long(respuestaDeimos.getInformacionGeneralApropiacion().getConvenio()));
			}

			if (!Validaciones.isObjectNull(respuestaDeimos.getInformacionGeneralApropiacion().getCantidadCuotas())){
				debito.setDmosCantidadDeCuotas(respuestaDeimos.getInformacionGeneralApropiacion().getCantidadCuotas().longValue());
			}
			
			if (!Validaciones.isObjectNull(respuestaDeimos.getInformacionGeneralApropiacion().getCantidadCuotasPagas())){
				debito.setDmosCantidadDeCuotasPagas(respuestaDeimos.getInformacionGeneralApropiacion().getCantidadCuotasPagas().longValue());
			}
			
		}else{
			resultadoOK=false;
			
			if(!Validaciones.isNullOrEmpty(respuestaDeimos.getResultadoProcesamiento().getCodigoError())){
				debito.setResultadoValidacionCodigoError(respuestaDeimos.getResultadoProcesamiento().getCodigoError());
			}
			
			if(!Validaciones.isNullOrEmpty(respuestaDeimos.getResultadoProcesamiento().getDescripcionError())){
				debito.setResultadoValidacionDescripcionError(respuestaDeimos.getResultadoProcesamiento().getDescripcionError());
				Traza.error(ConsultaDebitosACobradoresThread.class,"ERROR Consulta a Deimos:" + 
						debito.getResultadoValidacionDescripcionError() + ObtenerInfoDebitoTraza());
			}
			
		}
		
		return resultadoOK;
			
	}

	private boolean consultarSaldoMaximoDebito(CobroDebitoDto cobroDebitoDto) {
		
		boolean saldoOK=true;
		String motivo = debito.getPk().getCobro().getMotivo().getDescripcion();
		BigDecimal saldoMaximo = cobroOnlineServicio.obtenerSaldoMaximoDebito(cobroDebitoDto, motivo);
		String msjError ="El importe a cobrar es incorrecto. ";
		
		//Máximo importe a cobrar: ";
		
		if (SistemaEnum.MIC.equals(debito.getSistemaOrigen())){
			if(SiNoEnum.SI.equals(debito.getCheckCobrarSegVencimiento())){
				if(SiNoEnum.SI.equals(debito.getCheckDestranferirTerceros())){
	        		// Caso 04: SI cobrar al 2do Vencimiento y SI destransferir a 3ros
	        		
					//Si el importe a cobrar es diferente al saldo maximo, error.
					if (!Validaciones.isNullEmptyOrDash(cobroDebitoDto.getSaldoMaxCaso04())){
						if (debito.getImporteACobrar().compareTo(Utilidad.stringToBigDecimal(cobroDebitoDto.getSaldoMaxCaso04())) != 0){
							msjError+= "El importe a cobrar debe ser ";
							saldoOK = setearErrorDebitoMaximo(saldoMaximo,msjError);
						}
					}
					
				} else {
	        		// Caso 03: SI cobrar al 2do Vencimiento y NO destransferir a 3ros
	        		
					// Si el importe a cobrar es diferente al saldo maximo, error.
					if (!Validaciones.isNullEmptyOrDash(cobroDebitoDto.getSaldoMaxCaso03())){
						if (debito.getImporteACobrar().compareTo(Utilidad.stringToBigDecimal(cobroDebitoDto.getSaldoMaxCaso03())) != 0){
							msjError+= "El importe a cobrar debe ser ";
							saldoOK = setearErrorDebitoMaximo(saldoMaximo,msjError);
						}
					}
	        	}
				
			} else {
				if(SiNoEnum.SI.equals(debito.getCheckDestranferirTerceros())){
	        		// NO cobrar al 2do Vencimiento y SI destransferir a 3ros

					// Si el importe a cobrar es diferente al saldo maximo, error.
					if (!Validaciones.isNullEmptyOrDash(cobroDebitoDto.getSaldoMaxCaso02())){
						if (debito.getImporteACobrar().compareTo(Utilidad.stringToBigDecimal(cobroDebitoDto.getSaldoMaxCaso02())) != 0){
							msjError+= "El importe a cobrar debe ser ";
							saldoOK = setearErrorDebitoMaximo(saldoMaximo,msjError);
						}
					}
	        	} else {
	        		if (TipoComprobanteEnum.DUC.equals(debito.getTipoComprobante())){
	        			if (!Validaciones.isNullEmptyOrDash(cobroDebitoDto.getSaldoMaxCaso01())){
	        				if (saldoMaximo.compareTo(debito.getImporteACobrar()) != 0){
	        					msjError+= "El importe a cobrar debe ser ";
	        					saldoOK = setearErrorDebitoMaximo(saldoMaximo,msjError);
	        				}
	        			}
	        		}
	        		return saldoOK;
	        	}
	        }
		
	    	if (SiNoEnum.NO.equals(debito.getCheckCobrarSegVencimiento()) && SiNoEnum.NO.equals(debito.getCheckDestranferirTerceros())) {
	    		if(saldoMaximo != null){
	    			if(saldoMaximo.compareTo(debito.getImporteACobrar()) < 0 ){
	    				msjError += "Máximo importe a cobrar: ";
	    				saldoOK = setearErrorDebitoMaximo(saldoMaximo,msjError);
	    			}
	    		}
	    	}
		
	    	return saldoOK;
		}


	    	
		if(saldoMaximo != null){
			if(saldoMaximo.compareTo(debito.getImporteACobrar()) < 0 ){
				msjError += "Máximo importe a cobrar: ";
				saldoOK = setearErrorDebitoMaximo(saldoMaximo,msjError);
			}
		}
		
		return saldoOK;
	}
	
	private boolean setearErrorDebitoMaximo (BigDecimal saldoMaximo, String msjError){
		
		debito.setResultadoValidacionDescripcionError(msjError + Utilidad.formatCurrency(saldoMaximo, 0));
		debito.setResultadoValidacionCodigoError(Constantes.ERROR_SALDO_MAXIMO_DEBITO);
		Traza.error(ConsultaDebitosACobradoresThread.class,ObtenerInfoDebitoTraza() + debito.getResultadoValidacionDescripcionError());
		return false;
	
	}
		

	private boolean consultarGestionabilidad(CobroDebitoDto cobroDebitoDto, GestionabilidadDto gestionabilidadDto) throws  NegocioExcepcion{
		
		boolean gestionabilidadOK = true;
		try {
//			cobroDebitoDto = (CobroDebitoDto) debitoMapeador.map(debito);
			//seteo el id debito pantalla
			
			gestionabilidadDto = cobroOnlineServicio.determinarGestionabilidadDeDeuda(cobroDebitoDto);
			
			if(!Validaciones.isObjectNull(gestionabilidadDto)){
				
				if(!Validaciones.isNullOrEmpty(gestionabilidadDto.getSemaforo())){
					
					if(SemaforoGestionabilidadEnum.ROJO.getDescripcion().equals(gestionabilidadDto.getSemaforo())) {//Si el semaforo es rojo, hay errores
						gestionabilidadOK = false;
						
						if(!Validaciones.isNullOrEmpty(gestionabilidadDto.getCodigoError())){
							debito.setResultadoValidacionCodigoError(gestionabilidadDto.getCodigoError());
						}
						if(!Validaciones.isNullOrEmpty(gestionabilidadDto.getDescripcionError())){
							debito.setResultadoValidacionDescripcionError(gestionabilidadDto.getDescripcionError());
							Traza.error(ConsultaDebitosACobradoresThread.class,ObtenerInfoDebitoTraza() + debito.getResultadoValidacionDescripcionError());
						}
						//Si el semaforo es amarillo, hay errores
					} else if(SemaforoGestionabilidadEnum.AMARILLO.getDescripcion().equals(gestionabilidadDto.getSemaforo())) {
						debito.setResultadoValidacionDescripcionError(gestionabilidadDto.getDescripcionError());
						debito.setResultadoValidacionCodigoError(gestionabilidadDto.getCodigoError());
					}
				}
			}
		} catch (NegocioExcepcion e) {
			Traza.error(ConsultaDebitosACobradoresThread.class, ObtenerInfoDebitoTraza() + 
					e.getMessage());
			debito.setResultadoValidacionDescripcionError(e.getMessage());
//			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return gestionabilidadOK;
	}


	/**
	 * Compara la respuesta de los cobradores, y setea los datos correctos al debito.
	 * @return
	 */
	private boolean validarRespuestaCobradores(SistemaEnum sistema,CalipsoDebito debitoRespuestaCalipso,MicDebito debitoRespuestaMic) {
		
		boolean resultadoValidacion = true;
		if (SistemaEnum.CALIPSO.equals(sistema)){
			
			/**VALIDACION DE DATOS OBTENIDOS DEL COBRADOR **/
			//idClienteLegado
			if(!Validaciones.isObjectNull(debitoRespuestaCalipso.getIdClienteLegado())){
				if(!debito.getIdClienteLegado().equals(Long.parseLong(debitoRespuestaCalipso.getIdClienteLegado()))){
					debito.setResultadoValidacionDescripcionError("ERROR, DIFERENTE NUMERO DE CLIENTE");
					Traza.error(ConsultaDebitosACobradoresThread.class,ObtenerInfoDebitoTraza() + debito.getResultadoValidacionDescripcionError());
					return false;
				}
				
			}
			
			//monedaOriginalFactura
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getMonedaOriginalFactura().getDescripcion())){
				if(!debito.getMoneda().getDescripcion().equals(debitoRespuestaCalipso.getMonedaOriginalFactura().getDescripcion())){
					debito.setResultadoValidacionDescripcionError("ERROR, DIFERENTE MONEDAS");
					Traza.error(ConsultaDebitosACobradoresThread.class,ObtenerInfoDebitoTraza() + debito.getResultadoValidacionDescripcionError());
					return false;
				}
			}

			//fechaVencimiento
			if(!Validaciones.isObjectNull(debitoRespuestaCalipso.getFechaVencimiento())){
				debito.setFechaVencimiento(debitoRespuestaCalipso.getFechaVencimiento());
			}
			
			
			//importe1erVencimientoMonedaOrigen
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getImporte1erVencimientoMonedaOrigen().toString())){
				debito.setImportePriVencMonedaOrigen(debitoRespuestaCalipso.getImporte1erVencimientoMonedaOrigen());
			}
			
			//importe1erVencimientoPesificado
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getImporte1erVencimientoPesificado().toString())){
				debito.setImportePriVencPesificado(debitoRespuestaCalipso.getImporte1erVencimientoPesificado());
			}
			
			//saldo1erVencimientoMonedaOrigen
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getSaldo1erVencimientoMonedaOrigen().toString())){
				debito.setSaldoPriVencMonedaOrigen(debitoRespuestaCalipso.getSaldo1erVencimientoMonedaOrigen());
			}
			
			//saldoPesificado
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getSaldoPesificado().toString())){
				debito.setSaldoPesificado(debitoRespuestaCalipso.getSaldoPesificado());
			}

			//codigoAcuerdoFacturacion
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getCodigoAcuerdoFacturacion())){
				debito.setAcuerdoFacturacion(debitoRespuestaCalipso.getCodigoAcuerdoFacturacion());
			}
			
			//estadoAcuerdoFacturacion
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getEstadoAcuerdoFacturacion())){
				debito.setEstadoAcuerdoFacturacion(debitoRespuestaCalipso.getEstadoAcuerdoFacturacion());
			}
			
			//Si el debito es automatico el estado origen sera ENV_A_DA sino el estado origen sera el estado morosidad
			
			EstadoOrigenEnum estadoOrigenEnum = null;
			
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getMarcaAdhesionDebitoAutomatico())){
				if (SiNoEnum.SI.getDescripcionCorta().equalsIgnoreCase(debitoRespuestaCalipso.getMarcaAdhesionDebitoAutomatico())) {
					estadoOrigenEnum = EstadoOrigenEnum.ENV_A_DA;
				} else if (!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getEstadoMorosidad())) {
					estadoOrigenEnum = EstadoOrigenEnum.getEnumByDescripcion(debitoRespuestaCalipso.getEstadoMorosidad());
				}
			} else if (!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getEstadoMorosidad())){
				 estadoOrigenEnum = EstadoOrigenEnum.getEnumByDescripcion(debitoRespuestaCalipso.getEstadoMorosidad());
			}
			
			if (!Validaciones.isObjectNull(estadoOrigenEnum)) {
				debito.setEstadoOrigen(estadoOrigenEnum);
			}
			
			//marcaReclamo
			if(!Validaciones.isObjectNull(debitoRespuestaCalipso.getMarcaReclamo())){
				if (MarcaReclamoEnum.RECLAMADA.descripcionAMostrar().equals(debitoRespuestaCalipso.getMarcaReclamo().getDescripcion())){
					debito.setMarcaReclamo(MarcaReclamoEnum.RECLAMADA);
				}else{
					debito.setMarcaReclamo(MarcaReclamoEnum.NO_RECLAMADA);
				}
			}
			
			//marcaMigradoDeimos
			if(!Validaciones.isObjectNull(debitoRespuestaCalipso.getMarcaMigradoDeimos())){
				debito.setMarcaMigradoDeimos(debitoRespuestaCalipso.getMarcaMigradoDeimos());
			}
			
			//tipoCambioActual
			if(!Validaciones.isObjectNull(debitoRespuestaCalipso.getTipoCambioActual())){
				debito.setTipoDeCambio(debitoRespuestaCalipso.getTipoCambioActual());
			}
			
			//fechaEmision
			if(!Validaciones.isObjectNull(debitoRespuestaCalipso.getFechaEmision())){
				debito.setFechaEmision(debitoRespuestaCalipso.getFechaEmision());
			}
			
			//fechaUltimoPagoParcial
			if(!Validaciones.isObjectNull(debitoRespuestaCalipso.getFechaUltimoPagoParcial())){
				debito.setFechaUltimoPagoParcial(debitoRespuestaCalipso.getFechaUltimoPagoParcial());
			}
			
			//idDocCtasCob
			if(!Validaciones.isObjectNull(debitoRespuestaCalipso.getIdDocCtasCob())){
				debito.setIdDocCuentaCobranza(new BigDecimal(debitoRespuestaCalipso.getIdDocCtasCob()));
			}
			
			//informacionAdicionalTagetikCalipso Razon Social Cliente
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getInformacionAdicionalTagetikCalipso().getRazonSocialCliente())){
				debito.setRazonSocialCliente(debitoRespuestaCalipso.getInformacionAdicionalTagetikCalipso().getRazonSocialCliente());
			}
			
			//informacionAdicionalTagetikCalipso Tipo Cliente
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getInformacionAdicionalTagetikCalipso().getTipoCliente())){
				debito.setTipoCliente(debitoRespuestaCalipso.getInformacionAdicionalTagetikCalipso().getTipoCliente());
			}
			
			//informacionAdicionalTagetikCalipso CUIT
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getInformacionAdicionalTagetikCalipso().getCuit())){
				debito.setCuit(debitoRespuestaCalipso.getInformacionAdicionalTagetikCalipso().getCuit());
			}
			
			//informacionAdicionalDacota unidadOperativa
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getInformacionAdicionalDacota().getUnidadOperativa())){
				debito.setUnidadOperativa(debitoRespuestaCalipso.getInformacionAdicionalDacota().getUnidadOperativa());
			}
			
			//informacionAdicionalDacota Subtipo
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getInformacionAdicionalDacota().getSubTipo())){
				debito.setSubtipo(debitoRespuestaCalipso.getInformacionAdicionalDacota().getSubTipo());
			}
			
			//informacionAdicionalDacota Holding
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getInformacionAdicionalDacota().getHolding())){
				debito.setHolding(debitoRespuestaCalipso.getInformacionAdicionalDacota().getHolding());
			}
			
			if(!Validaciones.isObjectNull(debitoRespuestaCalipso.getTipoCambioFechaCotizacion())){
				debito.setTipoCambioFechaCotizacion(debitoRespuestaCalipso.getTipoCambioFechaCotizacion());
			}
			
			if(!Validaciones.isObjectNull(debitoRespuestaCalipso.getImporte1erVencimientoPesificadoFechaCotizacion())){
				debito.setImporte1erVencimientoPesificadoFechaCotizacion(debitoRespuestaCalipso.getImporte1erVencimientoPesificadoFechaCotizacion());
			}
			
			if(!Validaciones.isObjectNull(debitoRespuestaCalipso.getSaldoPesificadoFechaCotizacion())){
				debito.setSaldoPesificadoFechaCotizacion(debitoRespuestaCalipso.getSaldoPesificadoFechaCotizacion());
			}
			
		}else if (SistemaEnum.MIC.equals(sistema)){
			
			//idClienteLegado
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getCodigoCliente())){
				if(!debito.getIdClienteLegado().equals(debitoRespuestaMic.getCodigoCliente().longValue())){
					debito.setResultadoValidacionDescripcionError("ERROR, DIFERENTE NUMERO DE CLIENTE");
					Traza.error(ConsultaDebitosACobradoresThread.class,ObtenerInfoDebitoTraza() + debito.getResultadoValidacionDescripcionError());
					return false;
				}
				
			}
			
			//Cuenta
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getCuenta())){
				debito.setCuenta(new Long(debitoRespuestaMic.getCuenta()));
			}
			
			//Si es tipo duc, setea los datos de duc		
			if(TipoComprobanteEnum.DUC.equals(debito.getTipoComprobante())){ 
				
//				if(!Validaciones.isObjectNull(debitoRespuestaMic.getDescripcionTipoDuc())){
//					debito.setTipoDuc(TipoDUCEnum.getEnumByDescripcion(debitoRespuestaMic.getDescripcionTipoDuc()));
//				}
				
				if(!Validaciones.isObjectNull(debitoRespuestaMic.getCodigoEstadoDuc())){
					debito.setEstadoDuc(EstadoDUCEnum.getEnumByCodigo(debitoRespuestaMic.getCodigoEstadoDuc()));
					debito.setEstadoOrigen(EstadoOrigenEnum.getEnumByName(debito.getEstadoDuc().name()));
				}
				
				if (!Validaciones.isObjectNull(debitoRespuestaMic.getNumeroReferenciaMic())){
					debito.setNumeroReferenciaDuc(debitoRespuestaMic.getNumeroReferenciaMic());
				}
				
				if(!Validaciones.isObjectNull(debitoRespuestaMic.getCodigoTipoDuc())){
					debito.setSubtipo(debitoRespuestaMic.getCodigoTipoDuc().toString());
					debito.setTipoDuc(TipoDUCEnum.getEnumByDescripcion(debitoRespuestaMic.getDescripcionTipoDuc()));
				}
				
				//if (!Validaciones.isObjectNull(debitoRespuestaMic.getDescripcionEstadoDuc())){
				//	debito.setEstadoOrigen(EstadoOrigenEnum.getEnumByDescripcion(debitoRespuestaMic.getDescripcionEstadoDuc()));
				//}
			} else {
				if (Validaciones.isObjectNull(debito.getNumeroReferenciaMic())){ 
					// si no es duc, y y el debito a validar no posee num referencia mic, setea el numero referencia mic que obtiene del cobrador
					if(!Validaciones.isObjectNull(debitoRespuestaMic.getNumeroReferenciaMic())){
						debito.setNumeroReferenciaMic(new Long(debitoRespuestaMic.getNumeroReferenciaMic()));
					}
				}
				
				//SubTipo
				if(!Validaciones.isObjectNull(debitoRespuestaMic.getInformacionAdicionalTagetik().getTipoFactura())){
					debito.setSubtipo(debitoRespuestaMic.getInformacionAdicionalTagetik().getTipoFactura().toString());
				}
				
				//Codigo Estado Factura
				if(!Validaciones.isNullOrEmpty(debitoRespuestaMic.getCodigoEstadoFactura())){
					debito.setEstadoOrigen(EstadoOrigenEnum.getEnumByCodigo(debitoRespuestaMic.getCodigoEstadoFactura()));
				}
			}
			
			//MARCA MIGRADO DEIMOS
			if(EstadoOrigenEnum.MIGRADA.codigo().equals(debitoRespuestaMic.getCodigoEstadoFactura())){
				debito.setMarcaMigradoDeimos(SiNoEnum.SI);
			}else{
				debito.setMarcaMigradoDeimos(SiNoEnum.NO);
			}
			
			
			//Tipo Comprobante
			if(Validaciones.isObjectNull(debito.getTipoComprobante())){
				debito.setTipoComprobante(debitoRespuestaMic.getTipoComprobante());
			}
			
			//Sucursal comprobante
			if(!Validaciones.isNullOrEmpty(debitoRespuestaMic.getSucursalComprobante())){
				debito.setSucursalComprobante(debitoRespuestaMic.getSucursalComprobante());
			}
			
			//Numero comprobante
			if (!Validaciones.isNullOrEmpty(debitoRespuestaMic.getNumeroComprobante())){
				debito.setNumeroComprobante(debitoRespuestaMic.getNumeroComprobante());
			}
			
			//Clase Comprobante
			if (!Validaciones.isObjectNull(debitoRespuestaMic.getClaseComprobante())){
				debito.setClaseComprobante(debitoRespuestaMic.getClaseComprobante());
			}
			
			//saldo1erVencimiento
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getSaldoAl1erVencimiento())){
				if((new BigDecimal(0)).equals(debitoRespuestaMic.getSaldoAl1erVencimiento())){
					debito.setResultadoValidacionDescripcionError("ERROR, El documento no tiene saldo");
					Traza.error(ConsultaDebitosACobradoresThread.class,ObtenerInfoDebitoTraza() + debito.getResultadoValidacionDescripcionError());
					return false;
				}else{
					debito.setSaldoPriVencMonedaOrigen(debitoRespuestaMic.getSaldoAl1erVencimiento());
				}
			}
			
			//Posibilidad Destranferencia
			
			if (!Validaciones.isObjectNull(debitoRespuestaMic.getPosibilidadDestransferencia())){
				debito.setPosibilidadDestransferencia(debitoRespuestaMic.getPosibilidadDestransferencia());
				if(SiNoEnum.NO.equals(debito.getPosibilidadDestransferencia())
					&& SiNoEnum.SI.equals(debito.getCheckDestranferirTerceros())){
					debito.setResultadoValidacionDescripcionError("ERROR, No se pueden destranferir terceros");
					Traza.error(ConsultaDebitosACobradoresThread.class,ObtenerInfoDebitoTraza() + debito.getResultadoValidacionDescripcionError());
					return false;
				}
			}
			
			if (!Validaciones.isObjectNull(debitoRespuestaMic.getFechaVencimiento()) && !Validaciones.isObjectNull(debitoRespuestaMic.getInformacionAdicionalDacota()) && !Validaciones.isObjectNull(debitoRespuestaMic.getInformacionAdicionalDacota().getFechaVencimientoMora())){
				if(debitoRespuestaMic.getFechaVencimiento().equals(debitoRespuestaMic.getInformacionAdicionalDacota().getFechaVencimientoMora()) && SiNoEnum.SI.equals(debito.getCheckCobrarSegVencimiento())){
					debito.setResultadoValidacionDescripcionError("ERROR, El Debito no tiene fecha a segundo vencimiento");
					Traza.error(ConsultaDebitosACobradoresThread.class,ObtenerInfoDebitoTraza() + debito.getResultadoValidacionDescripcionError());
					return false;
				}
			}
			
//			if(!Validaciones.isObjectNull(debito.getPosibilidadDestransferencia())
//					&& !Validaciones.isObjectNull(debitoRespuestaMic.getPosibilidadDestransferencia())){
//				if(SiNoEnum.SI.getDescripcion().equals(debito.getPosibilidadDestransferencia().getDescripcion()) 
//						&& SiNoEnum.NO.getDescripcion().equals(debitoRespuestaMic.getPosibilidadDestransferencia())){
//					debito.setResultadoValidacionDescripcionError("ERROR, NO SE PUEDEN DESTRANFERIR TERCEROS");
//					Traza.error(ConsultaDebitosACobradoresThread.class,ObtenerInfoDebitoTraza() + debito.getResultadoValidacionDescripcionError());
//					return false;
//				}else{
//				}
//			}
				
			//importe1erVencimiento
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getImporteAl1erVencimiento())){
				debito.setImportePriVencMonedaOrigen(debitoRespuestaMic.getImporteAl1erVencimiento());
			}
			
			//importe2doVencimiento
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getImporteAl2doVencimiento())){
				debito.setImporteSegVenc((debitoRespuestaMic.getImporteAl2doVencimiento()));
			}
			
			//Descripcion Estado acuerdo Facturación
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getCodigoEstadoAcuerdoFacturacion())){
				EstadoAcuerdoFacturacionEnum estadoAcuerdoFacturacionEnum = EstadoAcuerdoFacturacionEnum.getEnumByCodigoSinCero(debitoRespuestaMic.getCodigoEstadoAcuerdoFacturacion().toString());
				debito.setEstadoAcuerdoFacturacion(estadoAcuerdoFacturacionEnum!=null?estadoAcuerdoFacturacionEnum.name():null);
			}
			
			//Acuerdo
			if(!Validaciones.isNullOrEmpty(debitoRespuestaMic.getAcuerdo())){
				debito.setAcuerdoFacturacion(debitoRespuestaMic.getAcuerdo());
			}
			
			//Estado Conceptos de Terceros
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getEstadoConceptosTerceros())){
				debito.setEstadoConceptoTerceros(debitoRespuestaMic.getEstadoConceptosTerceros());

				//Importe 3eros Transferidos
				if(!Validaciones.isObjectNull(debitoRespuestaMic.getImporte3erosTransferidos())){
					debito.setImporteTercerosTransferidos(debitoRespuestaMic.getImporte3erosTransferidos());
				}
				//Importe 1er vencimiento con 3eros
				if(!Validaciones.isObjectNull(debitoRespuestaMic.getImporte1erVencimientoCon3eros())){
					debito.setImportePriVencTerceros(debitoRespuestaMic.getImporte1erVencimientoCon3eros());
				}
				
				//Importe 2er vencimiento con 3eros
				if(!Validaciones.isObjectNull(debitoRespuestaMic.getImporte2erVencimientoCon3eros())){
					debito.setImporteSegVencTerceros(debitoRespuestaMic.getImporte2erVencimientoCon3eros());
				}
				
			}
			
			//Codigo Estado Factura
//			if(!Validaciones.isNullOrEmpty(debitoRespuestaMic.getCodigoEstadoFactura())){
//				debito.setEstadoOrigen(EstadoOrigenEnum.getEnumByCodigo(debitoRespuestaMic.getCodigoEstadoFactura()));
//			}
			
			//Codigo Marca Reclamo
			if(!Validaciones.isNullOrEmpty(debitoRespuestaMic.getCodigoMarcaReclamo())){
				debito.setMarcaReclamo(MarcaReclamoEnum.getEnumByCodigo(debitoRespuestaMic.getCodigoMarcaReclamo()));
			}else{
				debito.setMarcaReclamo(MarcaReclamoEnum.SIN_MARCA);
			}
			
			//Codigo Marca C&Q
			if(!Validaciones.isNullOrEmpty(debitoRespuestaMic.getCodigoMarcaCyQ())){
				debito.setMarcaCyq(MarcaCyQEnum.getEnumByCodigo(debitoRespuestaMic.getCodigoMarcaCyQ()));
			}
			
			//fechaEmision
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getFechaEmision())){
				debito.setFechaEmision(debitoRespuestaMic.getFechaEmision());
			}
			
			//Numero de convenio
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getNumeroConvenio())){
				debito.setNumeroConvenio(new Long(debitoRespuestaMic.getNumeroConvenio()));
			}

			//Cuota
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getCuota())){
				debito.setCuota(new Long(debitoRespuestaMic.getCuota()));
			}
			
			//Fecha de ultimo pago parcial
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getFechaUltimoPagoParcial())){
				debito.setFechaUltimoPagoParcial(debitoRespuestaMic.getFechaUltimoPagoParcial());
			}
			
			//Fecha de puesta al cobro
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getFechaPuestaAlCobro())){
				debito.setFechaPuestaCobro(debitoRespuestaMic.getFechaPuestaAlCobro());
			}
			
			//Razón social cliente
			if(!Validaciones.isNullOrEmpty(debitoRespuestaMic.getInformacionAdicionalTagetik().getRazonSocialCliente())){
				debito.setRazonSocialCliente(debitoRespuestaMic.getInformacionAdicionalTagetik().getRazonSocialCliente());
			}
			
			//Tipo de Cliente
			if(!Validaciones.isNullOrEmpty(debitoRespuestaMic.getInformacionAdicionalTagetik().getTipoCliente())){
				debito.setTipoCliente(debitoRespuestaMic.getInformacionAdicionalTagetik().getTipoCliente());
			}
			
			//CUIT
			if(!Validaciones.isNullOrEmpty(debitoRespuestaMic.getInformacionAdicionalTagetik().getCuit())){
				debito.setCuit(debitoRespuestaMic.getInformacionAdicionalTagetik().getCuit());
			}
			
			//Ciclo de Facturación
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getInformacionAdicionalTagetik().getCicloFacturacion())){
				debito.setCicloFacturacion(new Integer(debitoRespuestaMic.getInformacionAdicionalTagetik().getCicloFacturacion()));
			}
			
			//Marketing Customer Group.
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getInformacionAdicionalTagetik().getMarketingCustomerGroup())){
				debito.setMarketingCustomerGroup(debitoRespuestaMic.getInformacionAdicionalTagetik().getMarketingCustomerGroup());
			}
			
			//Tipo de Factura
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getInformacionAdicionalTagetik().getTipoFactura())){
				debito.setTipoFactura(TipoFacturaEnum.getEnumByCodigo(debitoRespuestaMic.getInformacionAdicionalTagetik().getTipoFactura()));
			}

			//Fecha vencimiento mora
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getInformacionAdicionalDacota().getFechaVencimientoMora())){
				debito.setFechaVencimientoMora(debitoRespuestaMic.getInformacionAdicionalDacota().getFechaVencimientoMora());
			}
			
			//Indicador de Petición de Corte
			if(!Validaciones.isNullOrEmpty(debitoRespuestaMic.getInformacionAdicionalDacota().getIndicadorPeticionCorte())){
				debito.setIndicadorPeticionCorte(debitoRespuestaMic.getInformacionAdicionalDacota().getIndicadorPeticionCorte());
			}
			
			//Codigo Tarifa
			if(!Validaciones.isNullOrEmpty(debitoRespuestaMic.getInformacionAdicionalDacota().getCodigoTarifa())){
				debito.setCodigoTarifa(debitoRespuestaMic.getInformacionAdicionalDacota().getCodigoTarifa());
			}
			
			//Saldo tercero financiable no transferible
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getInformacionAdicionalSaldosTerceros().getSaldoTerceroFinanciableNOTransferible())){
				debito.setSaldoTerceroFinanciableNOTransferible(debitoRespuestaMic.getInformacionAdicionalSaldosTerceros().getSaldoTerceroFinanciableNOTransferible());
			}
			
			//Saldo tercero financiable transferible
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getInformacionAdicionalSaldosTerceros().getSaldoTerceroFinanciableTransferible())){
				debito.setSaldoTerceroFinanciableTransferible(debitoRespuestaMic.getInformacionAdicionalSaldosTerceros().getSaldoTerceroFinanciableTransferible());
			}

			//Saldo tercero no financiable transferible
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getInformacionAdicionalSaldosTerceros().getSaldoTerceroNOFinanciableTransferible())){
				debito.setSaldoTerceroNOFinanciableTransferible(debitoRespuestaMic.getInformacionAdicionalSaldosTerceros().getSaldoTerceroNOFinanciableTransferible());
			}
			
			
			
			if (!Validaciones.isObjectNull(debitoRespuestaMic.getFechaVencimiento())){
				debito.setFechaVencimiento(debitoRespuestaMic.getFechaVencimiento());
			}

			
		}
		return resultadoValidacion;
	}
	
	@SuppressWarnings({ "deprecation" })
	public String obtenerFecha(){
		String fecha = new Date().getHours()+":" + new Date().getMinutes()+ ":" + new Date().getSeconds();
		return fecha;
	}
	
	public String ObtenerInfoDebitoTraza(){
		return "[ID COBRO]=" + debito.getPk().getCobro().getIdCobro() + "| [ID DEBITO]=" + debito.getPk().getIdDebito() + ". ";
	}
	
	

	public EntradaCalipsoDeudaWS getEntradaCalipsoDeudaWs() {
		return entradaCalipsoDeudaWs;
	}

	public void setEntradaCalipsoDeudaWs(EntradaCalipsoDeudaWS entradaCalipsoDeudaWs) {
		this.entradaCalipsoDeudaWs = entradaCalipsoDeudaWs;
	}

	public MicConsultaDeudaEntrada getMicConsultaDeudaEntrada() {
		return micConsultaDeudaEntrada;
	}

	public void setMicConsultaDeudaEntrada(
			MicConsultaDeudaEntrada micConsultaDeudaEntrada) {
		this.micConsultaDeudaEntrada = micConsultaDeudaEntrada;
	}

	public ShvCobEdDebito getDebito() {
		return debito;
	}

	public void setDebito(ShvCobEdDebito debito) {
		this.debito = debito;
	}

	public List<ShvCobEdDebito> getListaDebitosConErrores() {
		return listaDebitosConErrores;
	}

	public void setListaDebitosConErrores(List<ShvCobEdDebito> listaDebitosConErrores) {
		this.listaDebitosConErrores = listaDebitosConErrores;
	}

	public ICobroOnlineServicio getCobroOnlineServicio() {
		return cobroOnlineServicio;
	}

	public void setCobroOnlineServicio(ICobroOnlineServicio cobroOnlineServicio) {
		this.cobroOnlineServicio = cobroOnlineServicio;
	}

	public EntradaDeimosConsultaEstadoDeudaWS getEntradaDeimos() {
		return entradaDeimos;
	}

	public void setEntradaDeimos(EntradaDeimosConsultaEstadoDeudaWS entradaDeimos) {
		this.entradaDeimos = entradaDeimos;
	}

	public SalidaDeimosConsultaEstadoDocumentoWS getRespuestaDeimos() {
		return respuestaDeimos;
	}

	public void setRespuestaDeimos(
			SalidaDeimosConsultaEstadoDocumentoWS respuestaDeimos) {
		this.respuestaDeimos = respuestaDeimos;
	}

	public SistemaEnum getCobrador() {
		return cobrador;
	}

	public void setCobrador(SistemaEnum cobrador) {
		this.cobrador = cobrador;
	}

}
