package ar.com.telecom.shiva.test.modulos;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.enumeradores.AcuseReciboEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoNotificacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoContactoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoNotificacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.mapeos.LegajoChequeRechazadoNotificacionMapeador;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoNotificacionServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjNotificacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjNotificacionCarta;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjNotificacionSimplificado;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoNotificacionCartaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoNotificacionDto;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class LegajoChequeRechazadoNotificacionTest extends SoporteContextoSpringTest {
	
	@Autowired ILegajoChequeRechazadoNotificacionServicio legajoChequeRechazadoNotificacionServicio;
	@Autowired LegajoChequeRechazadoNotificacionMapeador legajoChequeRechazadoNotificacionMapeador;

	@Test
	public void generarCartaLegajo(){
		try {
			legajoChequeRechazadoNotificacionServicio.generarCartaLegajoPdf(7L);
		} catch (NegocioExcepcion e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws WorkflowExcepcion
	 */
	@Test
	public void persistirSinCarta() {
		
		try {
			ShvLgjNotificacionSimplificado modelo = new ShvLgjNotificacionSimplificado();
			modelo.setIdLegajoChequeRechazado(426L);
			modelo.setAcuseRecibo(AcuseReciboEnum.DIRECCION_INEXISTENTE);
			modelo.setDatosContacto("Prueva con Notificacion crear");
			modelo.setDatosReceptor("Dato del receptor");
			modelo.setEstado(EstadoNotificacionEnum.ACTIVA);
			modelo.setFechaContacto(new Date());
			modelo.setFechaRecepcion(new Date());
			modelo.setObservaciones("Esta es una Observbaciion");
			modelo.setTipoNotificacion(TipoNotificacionEnum.ENTREGA_CHEQUE);
			modelo.setTipoContacto(TipoContactoEnum.EMAIL);
			modelo.setUsuarioAlta("u578936");
			modelo.setUsuarioDesc("YO");
			modelo = legajoChequeRechazadoNotificacionServicio.crear(modelo, Estado.LGJ_ABIERTO);
			System.out.println("FINALIZADO");
			System.out.println(": " + modelo.getIdNotificacion());
		} catch (NegocioExcepcion e) {
			e.printStackTrace();
		
		}
	}
	@Test
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void anularSinCarta() {
		
		try {
			
			legajoChequeRechazadoNotificacionServicio.anular(8L);
			System.out.println("FINALIZADO");
			
		} catch (NegocioExcepcion e) {
			e.printStackTrace();
		
		}
	}
	@Test
	public void persistirConCarta() {
		
		try {
			ShvLgjNotificacionSimplificado modelo = new ShvLgjNotificacionSimplificado();
			modelo.setIdLegajoChequeRechazado(426L);
			modelo.setAcuseRecibo(AcuseReciboEnum.DIRECCION_INEXISTENTE);
			modelo.setDatosContacto("Datos del contacto");
			modelo.setDatosReceptor("Dato del receptor");
			modelo.setEstado(EstadoNotificacionEnum.ACTIVA);
			modelo.setFechaContacto(new Date());
			modelo.setFechaRecepcion(new Date());
			modelo.setObservaciones("Esta es una Observbaciion");
			modelo.setTipoNotificacion(TipoNotificacionEnum.ENTREGA_CHEQUE);
			modelo.setTipoContacto(TipoContactoEnum.EMAIL);
			modelo.setUsuarioAlta("u578936");
			modelo.setUsuarioDesc("YO");
			

			ShvLgjNotificacionCarta carta = new ShvLgjNotificacionCarta();
			carta.setAnalistaFirmante("u578994");
			carta.setCalle("Cale 198");
			carta.setCodigoPostal("1648");
			carta.setDepartamento("12");
			carta.setNumero("1212");
//			carta.setProvincia("Buenos Aires");
			modelo.setCarta(carta);
			System.out.println("FINALIZADO");
			System.out.println(": " + modelo.getIdNotificacion());

			modelo = legajoChequeRechazadoNotificacionServicio.guardar(modelo);
		} catch (NegocioExcepcion e) {
			e.printStackTrace();
		
		}
	}
	
	
	@Test
	public void recuperarConCarta() {
		try {
			
			ShvLgjNotificacion modelo = legajoChequeRechazadoNotificacionServicio.buscar(7L);
			System.out.println(modelo.toString());
			System.out.println("FINALIZADO");
			System.out.println((modelo.getLegajoChequeRechazado() != null ? modelo.getLegajoChequeRechazado().getIdLegajoChequeRechazado().toString() : "asjfaiweohjiohowfowe"));
			
		} catch (NegocioExcepcion e) {
			e.printStackTrace();
		
		}
	}
	@Test
	public void persistirSinCartaDto() {
		
		try {
			LegajoChequeRechazadoNotificacionDto modelo = new LegajoChequeRechazadoNotificacionDto();
			modelo.setIdLegajoChequeRechazado(426L);
			modelo.setAcuseRecibo(AcuseReciboEnum.DIRECCION_INEXISTENTE.getIndice());
			modelo.setDatosContacto("Datos del contacto");
			modelo.setDatosReceptor("Dato del receptor");
			modelo.setEstado(EstadoNotificacionEnum.ACTIVA);
			modelo.setFechaContacto("29/03/2017");
			modelo.setFechaRecepcion("29/05/2017");
			modelo.setObservaciones("Esta es una Observbaciion");
			modelo.setTipoNotificacion(TipoNotificacionEnum.ENTREGA_CHEQUE.getIndice());
			modelo.setTipoContacto(TipoContactoEnum.EMAIL.getIndice());
			modelo.setUsuarioAlta("u578936");
			modelo.setUsuarioDesc("YO");

			LegajoChequeRechazadoNotificacionCartaDto carta = new LegajoChequeRechazadoNotificacionCartaDto();
			carta.setAnalistaFirmante("u578994");
			carta.setCalle("Cale 741");
			carta.setCodigoPostal("1648");
			carta.setDepartamento("12");
			carta.setNumero("1212");
			carta.setProvincia("Buenos Aires");

			ShvLgjNotificacionSimplificado model2 = (ShvLgjNotificacionSimplificado)legajoChequeRechazadoNotificacionMapeador.map(modelo, null);
			model2.setCarta((ShvLgjNotificacionCarta)legajoChequeRechazadoNotificacionMapeador.mapCarta(carta, null));
			legajoChequeRechazadoNotificacionServicio.guardar(model2);

			System.out.println("FINALIZADO");
			System.out.println(": " + model2.toString());
		} catch (NegocioExcepcion e) {
			e.printStackTrace();
		
		}
	}
}
