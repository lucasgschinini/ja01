package ar.com.telecom.shiva.base.jms.util.runnable;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.EstadoConsultaSaldoChequeRechazadoEnum;
import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaDeudaEntrada;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaDeudaSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDebito;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoDebito;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.negocio.bean.NroDocumentoLegal;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjChequeRechazadoDetalleDocumentoSimplificado;

public class ConsultaSaldoACobradoresChequeRechazadoThread extends Thread {


	SistemaEnum		            						cobrador; //Calipso, Mic
	ShvLgjChequeRechazadoDetalleDocumentoSimplificado	detDocumento;
	ILegajoChequeRechazadoServicio						legajoChequeRechazadoServicio;
	EntradaCalipsoDeudaWS								entradaCalipsoDeudaWs;
	MicConsultaDeudaEntrada								micConsultaDeudaEntrada;
	
	/**
	 * Consulta los débitos en los cobradores: Calipso, Mic
	 * 
	 */
	
	public void run(){
		try {
			Traza.auditoria(ConsultaSaldoACobradoresChequeRechazadoThread.class,ObtenerDetDocumentoTraza() + "Comenzó el proceso del hilo.");
		
			if (SistemaEnum.CALIPSO.equals(cobrador)){
				//Consultar a Calipso
				Traza.auditoria(ConsultaSaldoACobradoresChequeRechazadoThread.class,"Iniciando consulta a Calipso... " + this.getName());
				SalidaCalipsoDeudaWS salidaCalipsoDeudaWS = consultarCalipso();
				procesarInformacionCalipso(salidaCalipsoDeudaWS);
				Traza.auditoria(ConsultaSaldoACobradoresChequeRechazadoThread.class,"Terminó de consultar Tread Calipso..." + this.getName());
			
			}else{
				if (SistemaEnum.MIC.equals(cobrador)){
					//CONSULTAR A MIC
					Traza.auditoria(ConsultaSaldoACobradoresChequeRechazadoThread.class,"Iniciando consulta a MIC... " + this.getName());
					MicConsultaDeudaSalida micConsultaDeudaSalida = consultarMic();
					procesarInformacionMIC(micConsultaDeudaSalida);
					Traza.auditoria(ConsultaSaldoACobradoresChequeRechazadoThread.class,"Terminó de consultar Tread MIC..." + this.getName());
					
				}
			}
			
			legajoChequeRechazadoServicio.obtenerDetDocumentoActualizado(detDocumento,this);
			
			Traza.auditoria(ConsultaSaldoACobradoresChequeRechazadoThread.class,"Finalizó el proceso del hilo " + ObtenerDetDocumentoTraza());
		
		} catch (NegocioExcepcion e) {
			Traza.error(ConsultaSaldoACobradoresChequeRechazadoThread.class,e.getMessage() +"." + ObtenerDetDocumentoTraza());
		}

	}
	
	private MicConsultaDeudaSalida consultarMic() throws NegocioExcepcion{
		
		IMicJmsSyncServicio clienteMicServicio= (IMicJmsSyncServicio) Configuracion.getBeanBatch("micJmsSyncServicio");
		MicConsultaDeudaSalida	micConsultaDeudaSalida = null;
		try {
			micConsultaDeudaSalida =clienteMicServicio.consultarDeuda(micConsultaDeudaEntrada);
		} catch (NegocioExcepcion e) {
			Traza.error(ConsultaSaldoACobradoresChequeRechazadoThread.class,e.getMessage() +"." + ObtenerDetDocumentoTraza());
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
			Traza.error(ConsultaSaldoACobradoresChequeRechazadoThread.class,e.getMessage() +"." + ObtenerDetDocumentoTraza());
//			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return salida;
	}

	@SuppressWarnings("static-access")
	private void procesarInformacionCalipso(SalidaCalipsoDeudaWS salidaCalipsoDeudaWS) throws NegocioExcepcion{
		
		if (!Validaciones.isObjectNull(salidaCalipsoDeudaWS)) {
			
			if (OkNokEnum.OK.getDescripcion().equals(salidaCalipsoDeudaWS.getResultadoProcesamiento().getResultadoImputacion())){
					
				if (Validaciones.isCollectionNotEmpty(salidaCalipsoDeudaWS.getListaDebitos())){
					
					CalipsoDebito debitoRespuesta = salidaCalipsoDeudaWS.getListaDebitos().get(0);
					if (validarRespuestaCobradores(SistemaEnum.CALIPSO,debitoRespuesta,null)){
						detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.FINALIZADO_OK);
					} else {
						detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.FINALIZADO_ERROR);
					}
				} else{
					detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.FINALIZADO_ERROR);
				}
			} else {
				if (OkNokEnum.NOK.getDescripcion().equals(salidaCalipsoDeudaWS.getResultadoProcesamiento().getResultadoImputacion())
						&& Constantes.CALIPSO_COD_DOCUMENTO_SIN_SALDO.equals(salidaCalipsoDeudaWS.getResultadoProcesamiento().getCodigoError())){
					detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.FINALIZADO_OK);
					detDocumento.setSaldoDocumento(BigDecimal.ZERO);
				}
				detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.FINALIZADO_ERROR);
			}
				
			Traza.auditoria(ConsultaSaldoACobradoresChequeRechazadoThread.class,"Consultando..." + this.getName());
			
			try {
				this.sleep(5000);
			} catch (InterruptedException e) {
				Traza.error(ConsultaSaldoACobradoresChequeRechazadoThread.class,e.getMessage() +"." + ObtenerDetDocumentoTraza());
			}
		} else {
			detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.FINALIZADO_ERROR);
		}
	}
	
	@SuppressWarnings("static-access")
	private void procesarInformacionMIC(MicConsultaDeudaSalida micConsultaDeudaSalida) throws NegocioExcepcion{

		if (!Validaciones.isObjectNull(micConsultaDeudaSalida)) {
			
			if (OkNokEnum.OK.equals(micConsultaDeudaSalida.getResultadoConsulta().getResultadoConsulta())){
					
				if (Validaciones.isCollectionNotEmpty(micConsultaDeudaSalida.getListaDebitos())){
					
					MicDebito debitoRespuesta = micConsultaDeudaSalida.getListaDebitos().get(0);
					if(validarRespuestaCobradores(SistemaEnum.MIC,null,debitoRespuesta)){
						detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.FINALIZADO_OK);
					} else {
						detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.FINALIZADO_ERROR);
					}
						
				} else {
					detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.FINALIZADO_ERROR);
				}
			} else {
				detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.FINALIZADO_ERROR);
			}
				
			Traza.auditoria(ConsultaSaldoACobradoresChequeRechazadoThread.class,"Consultando..." + this.getName());
			
			try {
				this.sleep(5000);
			} catch (InterruptedException e) {
				Traza.error(ConsultaSaldoACobradoresChequeRechazadoThread.class,e.getMessage() +"." + ObtenerDetDocumentoTraza());
			}
		} else {
			detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.FINALIZADO_ERROR);
		}
	}
	
	/**
	 * Compara la respuesta de los cobradores, y setea los datos correctos al detDocumento.
	 * @return
	 */
	private boolean validarRespuestaCobradores(SistemaEnum sistema,CalipsoDebito debitoRespuestaCalipso,MicDebito debitoRespuestaMic) {
		
		if (SistemaEnum.CALIPSO.equals(sistema)){
			
			/**VALIDACION DE DATOS OBTENIDOS DEL COBRADOR **/
			IdDocumento documento = new IdDocumento();
			NroDocumentoLegal nroDocLegal = new NroDocumentoLegal();
			nroDocLegal = Utilidad.obtenerNroDocumentoLegal(detDocumento.getNumeroLegal());
			documento.setTipoComprobante(TipoComprobanteEnum.FAC);
			documento.setNumeroComprobante(nroDocLegal.getNumero());
			documento.setSucursalComprobante(nroDocLegal.getSucursal());
			
			if(!Validaciones.isObjectNull(nroDocLegal.getClase())){
				documento.setClaseComprobante(nroDocLegal.getClase());
			}

			if (!nroDocLegal.getNumero().equals(debitoRespuestaCalipso.getIdDocumento().getNumeroComprobante())
					|| !nroDocLegal.getSucursal().equals(debitoRespuestaCalipso.getIdDocumento().getSucursalComprobante())
					|| (!Validaciones.isObjectNull(nroDocLegal.getClase()) &&!nroDocLegal.getClase().equals(debitoRespuestaCalipso.getIdDocumento().getClaseComprobante()))){
						Traza.error(ConsultaSaldoACobradoresChequeRechazadoThread.class,ObtenerDetDocumentoTraza() + " ERROR, DIFERENTE DOCUMENTO");
						return false;
			}
			
			//idClienteLegado
			if(!Validaciones.isObjectNull(debitoRespuestaCalipso.getIdClienteLegado())){
				if(!detDocumento.getIdClienteLegado().equals(Long.parseLong(debitoRespuestaCalipso.getIdClienteLegado()))){
					Traza.error(ConsultaSaldoACobradoresChequeRechazadoThread.class,ObtenerDetDocumentoTraza() + " ERROR, DIFERENTE NUMERO DE CLIENTE");
					return false;
				}
			}
			
			//saldo1erVencimientoMonedaOrigen
			if(!Validaciones.isNullOrEmpty(debitoRespuestaCalipso.getSaldo1erVencimientoMonedaOrigen().toString())){
				detDocumento.setSaldoDocumento(debitoRespuestaCalipso.getSaldo1erVencimientoMonedaOrigen());
			}
			
		}else if (SistemaEnum.MIC.equals(sistema)){
			
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getNumeroReferenciaMic())){
				if(!detDocumento.getNumeroReferenciaMic().equals(debitoRespuestaMic.getNumeroReferenciaMic())){
					Traza.error(ConsultaSaldoACobradoresChequeRechazadoThread.class,ObtenerDetDocumentoTraza() + " ERROR, DIFERENTE NUMERO DE REFERENCIA MIC");
					return false;
				}
			}
				
			//saldo1erVencimiento
			if(!Validaciones.isObjectNull(debitoRespuestaMic.getSaldoAl1erVencimiento())){
				detDocumento.setSaldoDocumento(debitoRespuestaMic.getSaldoAl1erVencimiento());
				
			}
		}
		
		return true;
	}
	
	public String ObtenerDetDocumentoTraza(){
		return "[ID DETALLE DOCUMENTO]=" + detDocumento.getIdChequeRechazadoDocumento();
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

	public SistemaEnum getCobrador() {
		return cobrador;
	}

	public void setCobrador(SistemaEnum cobrador) {
		this.cobrador = cobrador;
	}

	public ILegajoChequeRechazadoServicio getLegajoChequeRechazadoServicio() {
		return legajoChequeRechazadoServicio;
	}

	public void setLegajoChequeRechazadoServicio(
			ILegajoChequeRechazadoServicio legajoChequeRechazadoServicio) {
		this.legajoChequeRechazadoServicio = legajoChequeRechazadoServicio;
	}

	public ShvLgjChequeRechazadoDetalleDocumentoSimplificado getDetDocumento() {
		return detDocumento;
	}

	public void setDetDocumento(
			ShvLgjChequeRechazadoDetalleDocumentoSimplificado detDocumento) {
		this.detDocumento = detDocumento;
	}
}
