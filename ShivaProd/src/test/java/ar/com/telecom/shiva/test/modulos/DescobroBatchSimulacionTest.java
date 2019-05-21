package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDescobroWS;
import ar.com.telecom.shiva.negocio.servicios.IDescobroSimulacionServicio;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class DescobroBatchSimulacionTest extends SoporteContextoSpringTest {
	
	@Autowired IDescobroSimulacionServicio descobroBatchSimulacionServicio;
	@Autowired IDescobroDao descobroDao;
	
	@Test
	public void simularDescobro() {
		
		//141 - 100004 - 100014 factura y medio pago calipso sin intereses
		//164 - 100003 Id operacion cobro factura y medio de pago mic sin intereses
		//165 - 100002 id operacion cobro
		//163 - 100006 id operacion cobro factura y medio de pago mic con intereses
		//142 - 100008 - 100015 factura y medio de pago calipso con intereses
		//261 - 100140 - factura y medio de pago calipso con dos transacciones sin intereses
		//263 - 100161 - factura y medio de pago calipso con dos transacciones con intereses
		//201 - 100080 - factura y medio de pago mic con debito a proxima con dos transacciones sin intereses
		//383 - 100287 - Factura y medio de pago calipso con diferencia de cambio
		try {
			ShvCobDescobro descobro = descobroDao.buscarDescobro(new Long(383));
			descobroBatchSimulacionServicio.simularDescobroProcesamientoBatch(descobro,
					Propiedades.MENSAJES_PROPIEDADES.getString(
							Constantes.USUARIO_NOMBRE_BATCH) 
							);
			
			System.out.println("FINALIZADO");
		} catch (NegocioExcepcion e) {
			e.printStackTrace();
//			throw new Exception(e);
		} catch (PersistenciaExcepcion e) {
			e.printStackTrace();
		}
		
	}
	
//	@Test
	public void simularDescobroPruebaEnum() {
		
		EntradaCalipsoDescobroWS entrada = new EntradaCalipsoDescobroWS();
		entrada.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO);
		String prueba = "";
		
		if(entrada.getTipoMensaje().esMensajeDescobrosCalipso()){
			prueba = "entro con " + entrada.getTipoMensaje().name();
		} else {
			prueba = " no entro " + entrada.getTipoMensaje().name();
		}
		System.out.println(prueba);
		
	}
	

}
