package ar.com.telecom.shiva.test.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDebitoDao;
import ar.com.telecom.shiva.persistencia.dao.ICotizacionDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.IMensajeriaTransaccionDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvParamCotizacion;
import ar.com.telecom.shiva.presentacion.bean.filtro.CotizacionFiltro;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class QueriesHibernateTest extends SoporteContextoSpringTest{

	@Autowired IDescobroDao descobroDao;
	@Autowired IDescobroServicio descobroServicio;	
	@Autowired ICobroOnlineServicio cobroOnlineServicio;
	@Autowired ICobroDao cobroDao;
	@Autowired ICobroOnlineDebitoDao cobroOnlineDebitoDao;	
	@Autowired IMensajeriaTransaccionDao mensajeriaTransaccionDao;
	@Autowired IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;

	@Autowired ICotizacionDao cotizacionDao;
	
	@Test
	public void buscarcotizacion() throws Exception {
			Date fecha = Utilidad.parseDatePickerString("25/11/2111");
			String moneda= "U$S";
			CotizacionFiltro filtro = new CotizacionFiltro();
			filtro.setFechaValidezDesde(fecha);
			filtro.setMonedaDestino(MonedaEnum.getEnumBySigno2(moneda));
			ShvParamCotizacion cotizacion = cotizacionDao.buscar(1L);
			System.out.println(cotizacion.getFechaValidezDesde());
			System.out.println(cotizacion.getIdCotizacion());
			System.out.println(cotizacion.getMonedaProcedencia());
			System.out.println(cotizacion.getMonedaDestino());
			System.out.println(cotizacion.getTipoDeCambio());
	}
//	@Test
//	public void compararTransaccionDtoConTransaccionCobro() throws NegocioExcepcion {
//		
//		try {
//			Long idCobro = new Long("1283");
//			Long idOperacion = new Long("1283");
//			TransaccionesJsonResponse prueba = cobroOnlineServicio.buscarTransacciones(idCobro);
//			Utilidad.guionesNull(prueba.getAaData());
//			for(CobroTransaccionDto dto: prueba.getAaData()){
//				if(TipoComprobanteEnum.FAC.descripcion().equals(dto.getTipoDocumento())){
//					dto.setTrasladarIntereses(true);
//					dto.setPorcABonificar("50");
//					dto.setImporteABonificar("1000,00");
//					dto.setAcuerdoDestinoCargos("1");
//					dto.setEstadoAcuerdoDestinoCargos("Activo");
//					dto.setMensajeTransaccion("prueba tran");
//					dto.setMensajeCredito("prueba cred");
//					dto.setMensajeDebito("prueba deb");
//				}				
//			}
//			
//			CobroDto cobro = new CobroDto();
//			cobro.setIdOperacion(idOperacion);	
//			cobro.setTransacciones(prueba.getAaData());
//			
//			cobroOnlineServicio.modificarTransaccionesConIntereses(cobro);
//			
//			System.out.println("");
//		} catch (Exception e) {
//			System.out.println(e.getCause());
//		}
//	}
//	
//	@Test
//	public void listarCobrosADescobrarSimulacionEnProceso() throws NegocioExcepcion {
//		
//		List<ShvCobDescobro> descobro = descobroServicio.buscarDescobrosPendienteSimulacionBatch();
//		System.out.println(descobro.get(0).getIdDescobro());
//	}
//	
////	@Test
//	public void buscarDatosSimulacion() throws NegocioExcepcion {
//		
//		try {
//			List<ResultadoBusquedaDatosSimulacion> listaResultadoBusquedaDatosSimulacion = descobroDao.buscarDatosSimulacion(new Long(248));
//			System.out.println(listaResultadoBusquedaDatosSimulacion.get(0).getCopropietario());
//		} catch (PersistenciaExcepcion e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	/**
//	 * no borra todos los elementos del descobro hay que ver si conviene corregirlo
//	 * @throws NegocioExcepcion
//	 */
//	@Deprecated
////	@Test
//	public void borrarDescobro() throws NegocioExcepcion {
//		
//		try {
////			ShvCobCobro cobro = cobroDao.buscarCobro(new Long(374));
////			ShvCobDescobro descobro = cobro.getDescobro();
////			cobro.setDescobro(null);
////			cobroDao.modificar(cobro);
//			ShvCobDescobro descobro = descobroDao.buscarDescobro(new Long("143"));
//			descobroDao.borrarDescobro(descobro);
//			
//		} catch (PersistenciaExcepcion e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
////	@Test
//	public void buscarDatosImputacion() throws NegocioExcepcion {
//		
//		try {
//			List<Long> listaResultadoBusquedaDatosSimulacion = descobroDao.listarDescobrosPendientes();
//			System.out.println(listaResultadoBusquedaDatosSimulacion.get(0));
//		} catch (PersistenciaExcepcion e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
////	@Test
//	public void buscarMensajesDescobroEnviadosMIC() throws NegocioExcepcion {
//		
//		try {
//			List<ShvCobTransaccionMensajeriaDetalle> listaResultadoBusquedaDatosSimulacion = mensajeriaTransaccionDao.buscarMensajesDescobroEnviadosMIC(new Long(100006), new Integer(1));
//			System.out.println(listaResultadoBusquedaDatosSimulacion.get(0));
//		} catch (PersistenciaExcepcion e) {
//			System.out.println(e.getMessage());
//		}
//	}	
//	
////	@Test
//	public void buscarMensajePorIdOperacionTransaccionYEstadoMensaje() throws NegocioExcepcion {
//		
//		try {
//			CobMensajeriaTransaccionDto mensajeriaDto = 
//					(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajePorIdOperacionTransaccionYEstadoMensaje(new Long(100080), new Integer(402), MensajeServicioEnum.MIC_DESCOBRO, MensajeEstadoEnum.COMPLETADO);
//			
//			if(!Validaciones.isObjectNull(mensajeriaDto)){
//				System.out.println(" id transaccion mensajeria:" + mensajeriaDto.getIdTransaccionMensajeria() + " estado:" + mensajeriaDto.getEstado());
//			} else {
//				System.out.println("No se encontraron resultados.");
//			}
//		} catch (NegocioExcepcion e) {
//			System.out.println(e.getMessage());
//		}
//	}	
//	
//	
////	@Test
//	public void listarIdCobrosPorIdCobroYTipoComprobante() throws NegocioExcepcion {
//		
//		try {
//			List<Long> idCobrosSql = new ArrayList<Long>();
//			idCobrosSql.add(new Long(372));
//			idCobrosSql.add(new Long(373));
//			idCobrosSql.add(new Long(374));
//			idCobrosSql.add(new Long(375));
//			idCobrosSql.add(new Long(376));
//			idCobrosSql.add(new Long(377));
//			idCobrosSql.add(new Long(378));
//			
//			List<Long> idCobros = cobroOnlineDebitoDao.listarIdCobrosPorIdCobroYTipoComprobante(idCobrosSql, TipoComprobanteEnum.DUC);
//			
//			for(Long idCobro : idCobros){
//				System.out.println(idCobro.toString());
//			}
//		} catch (PersistenciaExcepcion e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}	
//	
//	@Test
//	public void listarDescobrosPendientes() throws NegocioExcepcion {
//		
//		try {
//			
//			List<Long> idDescobros = descobroDao.listarDescobrosPendientes();
//			
//			for(Long idDescobro : idDescobros){
//				System.out.println(idDescobro.toString());
//			}
//		} catch (PersistenciaExcepcion e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}	
}
