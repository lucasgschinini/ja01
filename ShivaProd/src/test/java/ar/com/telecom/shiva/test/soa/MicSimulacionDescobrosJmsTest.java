package ar.com.telecom.shiva.test.soa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaDescobroSalida;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroDto;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;


public class MicSimulacionDescobrosJmsTest extends SoporteContextoSpringTest{

	@Autowired
	private IMicJmsSyncServicio micJmsSyncServicio;
	
	/**
	 * idOperacion: 101 - idTransaccion: 102
	 * Entrada:
	 //TODO - a Fabio 1Reversion PABLO MIC esta bien que se rellene con ceros el acuerdo cuando factura es no?
	 * CRM01S  09S000010100001SHIVA   N0000000000
	 *
	 * Salida:
	 */
	@Test
	public void simularDescobro() throws NegocioExcepcion {
		
		MicTransaccionDescobroDto mensajeMIC = new MicTransaccionDescobroDto();
		
		mensajeMIC.setIdOperacion(new Long(101));
		mensajeMIC.setIdTransaccion(new Integer(102));
		mensajeMIC.setNumeroTransaccion(new Integer(1));
		mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$09);
		mensajeMIC.setModoEjecucion(SiNoEnum.SI);
		mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		
		mensajeMIC.setFacturarContracargoFactura(SiNoEnum.NO);
		mensajeMIC.setFacturarContracargoCargo(SiNoEnum.NO);
		
		MicRespuestaDescobroSalida salida = micJmsSyncServicio.simularDescobro(mensajeMIC);
		
		System.out.println("simularDescobro (MIC):" + salida);
	}
	
	/**
	 * idOperacion: 101 - idTransaccion: 102
	 * Entrada:
	 * CRM01S  09S000010100001SHIVA   S9850696791
	 * 
	 * Salida:
	 */
//	@Test
	public void simularDescobroConContracargo() throws NegocioExcepcion {
		
		MicTransaccionDescobroDto mensajeMIC = new MicTransaccionDescobroDto();
		
		mensajeMIC.setIdOperacion(new Long(101));
		mensajeMIC.setIdTransaccion(new Integer(102));
		mensajeMIC.setNumeroTransaccion(new Integer(1));
		mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$09);
		mensajeMIC.setModoEjecucion(SiNoEnum.SI);
		mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		
		mensajeMIC.setFacturarContracargoFactura(SiNoEnum.NO);
		mensajeMIC.setFacturarContracargoCargo(SiNoEnum.SI);
		mensajeMIC.setAcuerdoFacturacionContracargoCargo("9850696791");
		
		MicRespuestaDescobroSalida salida = micJmsSyncServicio.simularDescobro(mensajeMIC);
		
		System.out.println("simularDescobroConContracargo (MIC):" + salida);
	}
	
	/**
	 * idOperacion:  - idTransaccion: 
	 * Entrada:
	 * CRM01S  09S000010100001SHIVA   S9850696791
	 * 
	 * Salida:
	 */
//	@Test
	public void simularDescobroConFacturaYCargo() throws NegocioExcepcion {
		
		MicTransaccionDescobroDto mensajeMIC = new MicTransaccionDescobroDto();
		
//		mensajeMIC.setIdOperacion(new Long(101)); completar
//		mensajeMIC.setIdTransaccion(new Integer(102));
//		mensajeMIC.setNumeroTransaccion(new Integer(1));
		mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$09);
		mensajeMIC.setModoEjecucion(SiNoEnum.SI);
		mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		
		mensajeMIC.setFacturarContracargoFactura(SiNoEnum.SI);
		mensajeMIC.setAcuerdoFacturacionContracargoCargo("completar");
		mensajeMIC.setFacturarContracargoCargo(SiNoEnum.SI);
		mensajeMIC.setAcuerdoFacturacionContracargoCargo("9850696791");
		
		MicRespuestaDescobroSalida salida = micJmsSyncServicio.simularDescobro(mensajeMIC);
		
		System.out.println("simularDescobroConFacturaYCargo (MIC):" + salida);
	}
}
