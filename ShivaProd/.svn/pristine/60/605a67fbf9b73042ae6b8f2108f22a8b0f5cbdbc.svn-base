package ar.com.telecom.shiva.base.jms.util;

import java.util.Collection;
import java.util.Date;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.servicios.IJmsServicio;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsServicio;
import ar.com.telecom.shiva.base.jms.util.runnable.MicJmsThreadEnvioMensaje;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlThreadSingleton;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionDeudaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionDeudaMPDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionMPDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicConfirmacionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDesapropiacionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDescobroGeneracionCargosCreditoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDescobroGeneracionCargosDebitoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDescobroGeneracionCargosInteresDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicGeneracionCargosCreditoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicGeneracionCargosDebitoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDto;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionCobroServicio;

public class JmsMonitorMensajeria {
	
	private IJmsServicio micJmsServicio;
	
	private IParametroServicio parametroServicio;
	
	private ITransaccionCobroServicio transaccionCobroServicio;
	
	public JmsMonitorMensajeria() {}
	
    /**
     * Incorporo el dto en la bandeja de mensajes y creo una nueva mensajeria
     * @param id
     * @param msg
     */
    public DTO enviarMensaje(DTO objeto) throws NegocioExcepcion {
    	
    	try {
	    	//Obtengo el parametro concurrencias desde la bd.
    		int cantidadConcurrencias = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.JMS_CANTIDAD_CONCURRENCIAS).toString());
	    	ControlThreadSingleton.setConcurrencias(cantidadConcurrencias);
	    	
	    	if (objeto instanceof MicApropiacionDeudaDto
	    			|| objeto instanceof MicApropiacionMPDto
	    			|| objeto instanceof MicApropiacionDeudaMPDto
	    			|| objeto instanceof MicDesapropiacionDto
	    			|| objeto instanceof MicConfirmacionDto
	    			|| objeto instanceof MicGeneracionCargosDebitoDto
	    			|| objeto instanceof MicGeneracionCargosCreditoDto
	    			|| objeto instanceof MicTransaccionDescobroDto
	    			|| objeto instanceof MicDescobroGeneracionCargosCreditoDto
	    			|| objeto instanceof MicDescobroGeneracionCargosDebitoDto
	    			|| objeto instanceof MicDescobroGeneracionCargosInteresDto) { 
	    		
	    		
	    		IMicJmsServicio jmsServicio = (IMicJmsServicio) micJmsServicio;
	    		
	    		MicTransaccionDto dto = (MicTransaccionDto) objeto;
	    		
	    		String idOperacion = Utilidad.rellenarCerosIzquierda(dto.getIdOperacion().toString(), 7);
	    		
	    		Integer idTransaccion = null;
	    		String numTransaccion = "";
	    		if (dto.getNumeroTransaccion() != null) {
	    			numTransaccion = dto.getNumeroTransaccion().toString();
	    			idTransaccion = new Integer(dto.getIdTransaccion().toString());
	    		}
	    		
	    		String numeroTransaccion = Utilidad.rellenarCerosIzquierda(numTransaccion, 5);
	    		String idThread = idOperacion+"."+numeroTransaccion;
	    		
	    		//Piso el idOperacion del cobro con el del Descobro para que en la mensajeria lo guardo
	    		//Pero que el idThread quede con el del cobro
	    		if(objeto instanceof MicTransaccionDescobroDto
						//TODO - a Fabio 1Reversion PROBAR CONTRACARGOS QUE USE EL ID OPERACION DESCOBRO
						|| objeto instanceof MicDescobroGeneracionCargosCreditoDto
		    			|| objeto instanceof MicDescobroGeneracionCargosDebitoDto
		    			|| objeto instanceof MicDescobroGeneracionCargosInteresDto
		    			){
	    			MicTransaccionDto micTransaccionDto = (MicTransaccionDto) objeto;
					if(!Validaciones.isObjectNull(micTransaccionDto.getIdOperacionDescobroMensajeria())){
						idOperacion = Utilidad.rellenarCerosIzquierda(micTransaccionDto.getIdOperacionDescobroMensajeria().toString(), 7);
					}
				}
	    		
	    		//Crear mensajeria y devolver el id.
	    		CobMensajeriaTransaccionDto mensajeriaDto = new CobMensajeriaTransaccionDto();
	    		
	    		mensajeriaDto.setIdOperacion(new Long(idOperacion));
	    		mensajeriaDto.setIdTransaccion(idTransaccion);
	    		mensajeriaDto.setServicio(dto.getServicio());
	    		mensajeriaDto.setEstado(MensajeEstadoEnum.EN_PROCESO);
	    		mensajeriaDto.setFechaAlta(new Date());
	    		mensajeriaDto.setCantReintentos(new Integer(0));
	    		mensajeriaDto.setMensajeEnviado(null);
	    		mensajeriaDto.setRespuestaRecibida(null);
	    		mensajeriaDto = jmsServicio.crearMensajeria(mensajeriaDto);
	    		
	    		//Para devolver el id
	    		dto.setMensajeria(mensajeriaDto);
	    		
	    		Thread hilo = new MicJmsThreadEnvioMensaje(idThread, dto, jmsServicio);
	    		ControlThreadSingleton.agregarHilo(hilo);
	    		
	        	return dto;
	    	}
    	} catch (Exception e) {
    		Traza.error(getClass(), e.getMessage(), e);   		
    	}
    	return null;
	}
    

    /**
     * Reenvio Mensajes pendientes y incorporo en la bandeja de mensajes
     * @param id
     * @param msg
     */
    public void reenviarMensajesPendientes(TipoProcesoEnum tipoImputacion) throws NegocioExcepcion {
    	
    	try {
    		Traza.auditoria(getClass(), "Inicio del metodo reenviarMensajesPendientes a MIC");
    		
	    	//Obtengo el parametro concurrencias desde la bd.
    		int cantidadConcurrencias = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.JMS_CANTIDAD_CONCURRENCIAS).toString());
	    	ControlThreadSingleton.setConcurrencias(cantidadConcurrencias);
    		
	    	IMicJmsServicio jmsServicio = (IMicJmsServicio) micJmsServicio;
    		Collection<DTO> listaMensajesPendientes = jmsServicio.listarMensajesPendientes(tipoImputacion);
    		
    		Traza.auditoria(getClass(), 
    				Utilidad.reemplazarMensajes("Se ha encontrado {0} mensajes pendientes / enviados (MIC)", String.valueOf(listaMensajesPendientes.size())));
    		
    		for (DTO dto: listaMensajesPendientes) {
    			
    			if (dto instanceof CobMensajeriaTransaccionDto) {
	    			CobMensajeriaTransaccionDto cobroMensajeriaTransaccionDto = (CobMensajeriaTransaccionDto)dto;
	    			String idOperacion   = Utilidad.rellenarCerosIzquierda(
    						cobroMensajeriaTransaccionDto.getIdOperacion().toString(), 7);
	    			
	    			Integer numTransaccion = null;
	    			if (!Validaciones.isObjectNull(cobroMensajeriaTransaccionDto.getIdTransaccion())) {
	    				numTransaccion = 
	    						transaccionCobroServicio.buscarNumeroTransaccion(cobroMensajeriaTransaccionDto.getIdOperacion(), 
	    								cobroMensajeriaTransaccionDto.getIdTransaccion());
	    			}
		    		String numeroTransaccion = Utilidad.rellenarCerosIzquierda(String.valueOf(numTransaccion), 5);
	    			cobroMensajeriaTransaccionDto.setTipoImputacion(tipoImputacion);
		    		
	    			String idThread = idOperacion+"."+numeroTransaccion;
		    		
		    		Thread hilo = new MicJmsThreadEnvioMensaje(idThread, dto, jmsServicio);
		    		ControlThreadSingleton.agregarHilo(hilo);
    			}
	    	}
    		
    		Traza.auditoria(getClass(), "Finalizacion del metodo reenviarMensajesPendientes a MIC");
    	} catch (Exception e) {
    		Traza.error(getClass(), e.getMessage(), e);   		
    	}
	}
    
    
    /********************************************************************
     * Getters & Setters
     *******************************************************************/
	/**
	 * @return the micJmsServicio
	 */
	public IJmsServicio getMicJmsServicio() {
		return micJmsServicio;
	}

	/**
	 * @param micJmsServicio the micJmsServicio to set
	 */
	public void setMicJmsServicio(IJmsServicio micJmsServicio) {
		this.micJmsServicio = micJmsServicio;
	}

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	public ITransaccionCobroServicio getTransaccionCobroServicio() {
		return transaccionCobroServicio;
	}

	public void setTransaccionCobroServicio(
			ITransaccionCobroServicio transaccionCobroServicio) {
		this.transaccionCobroServicio = transaccionCobroServicio;
	}
}
