package ar.com.telecom.shiva.test.modulos;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ReintentoExcepcion;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ImputacionCobroRto;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobProcHilosCobros;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class CobroBatchImputacionTest extends SoporteContextoSpringTest {
	
	@Autowired ICobroImputacionServicio cobroServicio;
//	@Autowired ICobroDao cobroDao;
//
	@Autowired ICobroBatchServicio cobroBatchServicio;
//	@Autowired IParametroServicio parametroServicio; 
	@Autowired ICobroDao cobroDao;
	

	@Test
	@Ignore
	public void completarDatosParaInformesCapPdf() throws NegocioExcepcion {

		cobroBatchServicio.generarPdfResumenCompensacion(5554l);
		cobroBatchServicio.generarPdfCartaCompensacion(5554l);
	}
	
	@Test
	public void imputarCobro() throws ReintentoExcepcion, NegocioExcepcion {

		ShvCobProcHilosCobros cobroPendiente = new ShvCobProcHilosCobros();
		cobroPendiente.setIdCobro(Long.valueOf(5743));
		cobroPendiente.setIdOperacion(Long.valueOf(8355444));
		cobroPendiente.setCantTransacciones(Integer.valueOf(1));
//		cobroPendiente.setContieneAplicacionManual(SiNoEnum.SI);
		
		ImputacionCobroRto datosEntrada = new ImputacionCobroRto(cobroPendiente, 1, 1000, false); 
		cobroServicio.imputarCobro(datosEntrada);
	}
	
}
