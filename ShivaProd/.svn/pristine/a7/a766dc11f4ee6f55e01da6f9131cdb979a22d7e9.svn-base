package ar.com.telecom.shiva.test.soa;

import java.util.List;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaADCSalida;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCRespuestaDto;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionMicServicio;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionCobroServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring-context.xml" })
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class MicReProcesarMensajesRecibidosJmsTest {

	@Autowired 
	ICobroDao cobroDao;
	
	@Autowired
	ICobroBatchSoporteImputacionMicServicio cobroBatchSoporteImputacionMicServicio;
	
	@Autowired 
	MapeadorJMS micRespuestaADCMapeoJMS;
	
	@Autowired
	ITransaccionCobroServicio transaccionCobroServicio;
	
	
	@BeforeClass
	@SuppressWarnings("resource")
    public static void setUpClass() throws Exception {
		System.setProperty(Constantes.PROPIEDAD_ENTORNO, Constantes.ENTORNO_LOCAL);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
		DataSource testDataSource = (DataSource) context.getBean("testDataSource");
		SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		builder.bind("java:jboss/datasources/ShivaDS", testDataSource);
		builder.activate();
    }
	
	@Test
	public void reProcesarMensajesRecibidosTest() throws PersistenciaExcepcion, InterruptedException, NegocioExcepcion {
		//try {
			// Obtengo los cobros en estado Pendiente o en estado "cobro en proceso".
			List<Long> lista = null;//cobroDao.listarCobrosPendientes();
			
			Traza.auditoria(getClass(), 
    				Utilidad.reemplazarMensajes("Se ha encontrado {0} cobros pendientes / cobro en proceso", 
    						String.valueOf(lista.size())));
			
			int count = 0;
			for (Long idOperacion : lista) {
				count++;
				try {
					ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(idOperacion);
					
					tracearDatosImputacionCobro(cobro, true, count);
					//TODO solo para las pruebas unitarias
					//if (cobro.getOperacion().getIdOperacion().compareTo(Integer.valueOf("577")) == 0) {
						reImputarCobro(cobro);
					//}
				} catch (Exception e) {
					Traza.error(getClass(), 
							Utilidad.reemplazarMensajes("Se ha producido un error al imputar el cobro (idOperacion: {0})",
									String.valueOf(idOperacion)), e);
					Traza.auditoria(getClass(), "-------------------------------------------------------------------------------------");
				}
			}
		/*} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}*/
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	private void reImputarCobro(ShvCobCobro cobro) throws Exception {
		
		
		// Actualizo el estado a "COBRO EN PROCESO" si el estado actual es "PENDIENTE".
		if (Estado.COB_PROCESO.equals(cobro.getWorkflow().getEstado())){
			
			String mensaje = ""; 
			for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
				if(EstadoTransaccionEnum.EN_PROCESO_APROPIACION.equals(transaccion.getEstadoProcesamiento()) ){
					
					//Buscar el mensaje "completado" correspondiente al idOperacion / transaccion
					
					break;
				}
				
				if (EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento()) ){
					
					//Buscar el mensaje "completado" correspondiente al idOperacion 
					
					break;
				}
				
				if (EstadoTransaccionEnum.EN_PROCESO_CONFIRMACION.equals(transaccion.getEstadoProcesamiento()) ){
					
					//Buscar el mensaje "completado" correspondiente al idOperacion 
					
					break;
				}
			}
			
			
			if (Validaciones.isNullOrEmpty(mensaje)) {
				Traza.transaccionMQ(getClass(), "mensaje recibido a reprocesar: " + mensaje);
				
				String idOperacionTransaccion = "";
				//Ahi uso campos generales
				MicRespuestaADCSalida rta = 
					(MicRespuestaADCSalida) micRespuestaADCMapeoJMS.deserializar(mensaje, false, false);
				
				idOperacionTransaccion = rta.getIdOperacionTransaccion();
				
				Long idOperacion = new Long(idOperacionTransaccion.split("\\.")[0]);
				Integer numeroTransaccion = new Integer(rta.getIdOperacionTransaccion().split("\\.")[1]);
				Integer idTransaccion = null;
				
				if (idOperacion.compareTo(new Long("0")) == 0) {
					idOperacion = null;
				}
				
				if (numeroTransaccion.compareTo(new Integer("0")) != 0) {
					idTransaccion = transaccionCobroServicio.buscarIdTransaccion(idOperacion, numeroTransaccion);
				}
							
				//Si no esta entonces procesar la respuesta
				MensajeServicioEnum servicio = null;
				if (TipoInvocacionEnum.$01.equals(rta.getTipoInvocacion())) {
					servicio = MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA;
				} else 
				if (TipoInvocacionEnum.$02.equals(rta.getTipoInvocacion())) {
					servicio = MensajeServicioEnum.MIC_RESPUESTA_APROP_MP;	
				} else
				if (TipoInvocacionEnum.$03.equals(rta.getTipoInvocacion())) {
					servicio = MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA_y_MP;
				} else
				if (TipoInvocacionEnum.$04.equals(rta.getTipoInvocacion())) {
					servicio = MensajeServicioEnum.MIC_RESPUESTA_DESAPROPIACION;		
				} else
				if (TipoInvocacionEnum.$05.equals(rta.getTipoInvocacion())) {
					servicio = MensajeServicioEnum.MIC_RESPUESTA_CONFIRMACION;
				}
				
				if (idOperacion != null && servicio != null) {
					//Traceos
					Traza.transaccionMQ(getClass(), "procesarRespuestaOperacion: Se ha recibido el mensaje ("+rta.getIdOperacionTransaccion()+") de tipo: " + servicio.getDescripcion() + "("+ servicio.name()+")" );
					
					//Uso todos los campos
			    	rta = (MicRespuestaADCSalida) micRespuestaADCMapeoJMS.deserializar(mensaje, true, false);
			    	rta.setIdTransaccion(idTransaccion);
			    	
			    	procesarRespuestaOperacion(rta);
			    	
		    	} else {
					Traza.error(getClass(), "Mensaje recibido (" + idOperacionTransaccion + ") con operacion o servicio desconocido");
				}
			 
			}
		}
		tracearDatosImputacionCobro(cobro, false, 0);
		Traza.auditoria(getClass(), "-------------------------------------------------------------------------------------");
	}
	
	/**
	 * Se Tracean los datos de imputacion 
	 * @param cobro
	 */
	private void tracearDatosImputacionCobro(ShvCobCobro cobro, boolean esInicio, int contadorCobro){
		StringBuffer mensaje = new StringBuffer();
		if(esInicio){
			mensaje.append("Cobro " + contadorCobro + " a imputar: ")
				   .append("id: " + cobro.getIdCobro() + 
			               ", estado: " + cobro.getWorkflow().getEstado().descripcion());
		}else{
			mensaje.append("Cobro a imputado: ")
			   .append("id: " + cobro.getIdCobro() + 
		               ", estado: " + cobro.getWorkflow().getEstado().descripcion());
		}
		Traza.auditoria(getClass(), mensaje.toString());
		
		
		for(ShvCobTransaccion tran : cobro.getTransaccionesOrdenadas()){
			
			String idOperacion   = Utilidad.rellenarCerosIzquierda(tran.getOperacion().getIdOperacion().toString(), 7);
    		String numeroTransaccion = Utilidad.rellenarCerosIzquierda(tran.getNumeroTransaccion().toString(), 5);
    		String idOperacionTransaccion = idOperacion+"."+numeroTransaccion;
    		String salidaTraceo = "- Transaccion id: " + tran.getIdTransaccion() + " ("+idOperacionTransaccion+") estado: " + tran.getEstadoProcesamiento().descripcion();
			Traza.auditoria(getClass(), salidaTraceo);
			
			for(ShvCobFactura fact : tran.getShvCobFactura()){
				salidaTraceo = "-- Factura id: " + fact.getIdFactura() +
						" tipo: " + ((fact instanceof ShvCobFacturaMic)?"MIC":
							(fact instanceof ShvCobFacturaCalipso)?"CALIPSO":"-") + " estado: " + fact.getEstado().descripcion();
				Traza.auditoria(getClass(), salidaTraceo);
			}
			
			int count = 1;
			Traza.auditoria(getClass(), "-- Medios de pagos");
			for(ShvCobMedioPago mp : tran.getShvCobMedioPago()){
				String medioPagoTraceo = "--- MP " + count +" id: " + mp.getIdMedioPago(); 
				medioPagoTraceo += " tipo: " + ((mp instanceof ShvCobMedioPagoMic)?"MIC":(mp instanceof ShvCobMedioPagoCalipso)?"CALIPSO":
						(mp instanceof ShvCobMedioPagoUsuario)?"USUARIO":
						(mp instanceof ShvCobMedioPagoShiva)?"SHIVA":"-");
				medioPagoTraceo += " estado: " + mp.getEstado().descripcion();
						
				Traza.auditoria(getClass(), medioPagoTraceo);
				count++;
			}
		}
	}
	
	/**
	 * Procesa los mensajes de respuesta
	 * @param rta
	 * @throws Exception
	 */
	private void procesarRespuestaOperacion(MicRespuestaADCSalida rta) throws Exception {
		
		MicTransaccionADCRespuestaDto dto = new MicTransaccionADCRespuestaDto();
		dto.setIdOperacionTransaccion(rta.getIdOperacionTransaccion());
		dto.setIdTransaccion(rta.getIdTransaccion());
		dto.setTipoInvocacion(rta.getTipoInvocacion());
		String resultadoInvocacion = !Validaciones.isNullOrEmpty(rta.getResultadoLLamadaServicio().getResultadoInvocacion())?
				rta.getResultadoLLamadaServicio().getResultadoInvocacion():null;
		dto.setResultadoInvocacion(resultadoInvocacion);
		dto.setCodigoError(rta.getResultadoLLamadaServicio().getCodigoError());
		dto.setDescripcionError(rta.getResultadoLLamadaServicio().getDescripcionError());
		
		if (TipoInvocacionEnum.$01.equals(rta.getTipoInvocacion())) {
			
			dto.setIdCobranza(rta.getIdCobranza());
			
			//Detalle de cobro de factura
			dto.setInteresesGenerados(rta.getDetalleCobroFactura().getInteresesGenerados());
			dto.setInteresesBonificadosRegulados(rta.getDetalleCobroFactura().getInteresesBonificadosRegulados());
			dto.setInteresesBonificadosNoRegulados(rta.getDetalleCobroFactura().getInteresesBonificadosNoRegulados());
			
			cobroBatchSoporteImputacionMicServicio.micRecepcionApropiacionDeuda(dto);
			
		} else 
		if (TipoInvocacionEnum.$02.equals(rta.getTipoInvocacion())) {
			
			dto.setIdCobranza(rta.getIdCobranza());
			
			cobroBatchSoporteImputacionMicServicio.micRecepcionApropiacionMedioPago(dto);
			
		} else
		if (TipoInvocacionEnum.$03.equals(rta.getTipoInvocacion())) {
			
			dto.setIdCobranza(rta.getIdCobranza());
			
			//Detalle de cobro de factura
			dto.setInteresesGenerados(rta.getDetalleCobroFactura().getInteresesGenerados());
			dto.setInteresesBonificadosRegulados(rta.getDetalleCobroFactura().getInteresesBonificadosRegulados());
			dto.setInteresesBonificadosNoRegulados(rta.getDetalleCobroFactura().getInteresesBonificadosNoRegulados());
			
			cobroBatchSoporteImputacionMicServicio.micRecepcionApropiacionDeudaMedioPago(dto);
			
		} else
		if (TipoInvocacionEnum.$04.equals(rta.getTipoInvocacion())) {
			
			cobroBatchSoporteImputacionMicServicio.micRecepcionDesapropiacion(dto);
			
		} else
		if (TipoInvocacionEnum.$05.equals(rta.getTipoInvocacion())) {
			
			cobroBatchSoporteImputacionMicServicio.micRecepcionConfirmacion(dto);
			
		}
	}
}
