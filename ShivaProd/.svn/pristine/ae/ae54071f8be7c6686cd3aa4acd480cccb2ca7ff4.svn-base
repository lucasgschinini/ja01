/**
 * 
 */
package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.QueryMarcaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteCobrosOnline;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaCapPdfCap;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaDeudaPdfCap;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteMotorConciliacion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteOperacionesMasivas;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteRegistrosAvcSinConciliar;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaCobroHistorial;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaCobroTransaccion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobro;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroHistorial;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroTransaccion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoDetalleDocumento;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaOperacionMasivaHistorial;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaTalonarios;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaTareaPendiente;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValorDisponible;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoCobroCreditoDebito;
import ar.com.telecom.shiva.persistencia.dao.IVistaSoporteDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvSopExcepcionMorosidad;
import ar.com.telecom.shiva.presentacion.bean.filtro.ExcepcionMorosidadFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.TalonarioFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaCobroHistorialFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaDescobroHistorialFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroOnlineFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroTransaccionFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroOperacionRelacionadaFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroTransaccionFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteOperacionMasivaFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteRegistrosAvcSinConciliarFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteTareasPendientesFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteValoresDisponiblesFiltro;

/**
 * @author pablo.m.ibarrola
 *
 */
public class VistaSoporteServicioImpl implements IVistaSoporteServicio {

	@Autowired
	IVistaSoporteDao vistaSoporteDao;
	
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 ***************************************************************************/
	/**
	 * @return the vistaSoporteDao
	 */
	public IVistaSoporteDao getVistaSoporteDao() {
		return vistaSoporteDao;
	}

	/**
	 * @param vistaSoporteDao the vistaSoporteDao to set
	 */
	public void setVistaSoporteDao(IVistaSoporteDao vistaSoporteDao) {
		this.vistaSoporteDao = vistaSoporteDao;
	}
	
	/***************************************************************************
	 * FUNCIONES
	 ***************************************************************************/
	@Override
	public List<VistaSoporteResultadoBusquedaValorDisponible> consultarValoresDisponibles(
						VistaSoporteValoresDisponiblesFiltro filtro) throws NegocioExcepcion {
		
		List<VistaSoporteResultadoBusquedaValorDisponible> resultado = new ArrayList<VistaSoporteResultadoBusquedaValorDisponible>();

		try {
			resultado = vistaSoporteDao.consultarValoresDisponibles(filtro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		return resultado;
	}

	@Override
	public List<VistaSoporteResultadoBusquedaTareaPendiente> consultarTareasPendientes(
			VistaSoporteTareasPendientesFiltro filtro) throws NegocioExcepcion {
		
		List<VistaSoporteResultadoBusquedaTareaPendiente> resultado = new ArrayList<VistaSoporteResultadoBusquedaTareaPendiente>();

		try {
			resultado = vistaSoporteDao.consultarTareasPendientes(filtro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		return resultado;
	}
	
    @Override
    public List<VistaSoporteResultadoBusquedaValor> buscarValores(VistaSoporteBusquedaValoresFiltro filtro) throws NegocioExcepcion {
         
          try {
        	  List<VistaSoporteResultadoBusquedaValor> resultado = vistaSoporteDao.buscarValores(filtro);
                 return resultado;
          } catch (PersistenciaExcepcion e) {
                 throw new NegocioExcepcion(e.getMessage(), e);
          }
    }
	
    @Override
    public List<VistaSoporteResultadoBusquedaTalonarios> buscarTalonarios(TalonarioFiltro filtro) throws NegocioExcepcion {
         
          try {
        	  List<VistaSoporteResultadoBusquedaTalonarios> resultado = vistaSoporteDao.buscarTalonarios(filtro);
                 return resultado;
          } catch (PersistenciaExcepcion e) {
                 throw new NegocioExcepcion(e.getMessage(), e);
          }
    }
	
	/**
	 * @author fabio.giaquinta.ruiz
	 * Lista todos los registros avc en estados 'Pendientes de conciliar' o 
	 * 'Pendiente de Confirmar Alta de Valor' o 'Alta de Valor Rechazada'.
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteRegistrosAvcSinConciliar> listarRegistrosAVCSinConciliar(VistaSoporteRegistrosAvcSinConciliarFiltro filtro) throws NegocioExcepcion {
		
		List<VistaSoporteRegistrosAvcSinConciliar> resultado;
		
		try {
			
			resultado = vistaSoporteDao.listarRegistrosAVCSinConciliar(filtro);
						
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return resultado;
	}
	
	@Override
	public List<VistaSoporteCobrosOnline> listarCobrosOnline(
			VistaSoporteCobroOnlineFiltro cobroFiltro) throws NegocioExcepcion {

		List<VistaSoporteCobrosOnline> resultado;
		try {
			resultado = vistaSoporteDao.listarCobrosOnline(cobroFiltro);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return resultado;
		
	}

	@Override
	public List<VistaSoporteResultadoBusquedaCobroHistorial> obtenerHistorialCobro (VistaSoporteBusquedaCobroHistorialFiltro filtro) throws NegocioExcepcion {
		
		List<VistaSoporteResultadoBusquedaCobroHistorial> historial;
		
		try {
			historial = vistaSoporteDao.obtenerHistorialCobro(filtro);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return historial;
	}
	
	@Override
	public List<VistaSoporteResultadoBusquedaCobroTransaccion> obtenerTransaccionesCobro(VistaSoporteCobroTransaccionFiltro filtro) throws NegocioExcepcion {
		
		List<VistaSoporteResultadoBusquedaCobroTransaccion> listaTransaccionesModelo;
		
		try {
			listaTransaccionesModelo = vistaSoporteDao.obtenerTransaccionesCobro(filtro);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return listaTransaccionesModelo;
	}
	
	
	@Override
	public Map<Long, VistaSoporteResultadoBusquedaValor> getListaValoresPorIdsBoletas(
			VistaSoporteBusquedaValoresFiltro filtro) throws NegocioExcepcion {
		try {
			Map<Long, VistaSoporteResultadoBusquedaValor> resultadoADevolver = new HashMap<Long, VistaSoporteResultadoBusquedaValor>(0);
      	  	
			List<VistaSoporteResultadoBusquedaValor> resultadoValores = vistaSoporteDao.buscarValores(filtro);
      	  	
			for(VistaSoporteResultadoBusquedaValor infoValor : resultadoValores){
				resultadoADevolver.put(Long.valueOf(infoValor.getIdBoleta()), infoValor);
			}			
      	  	
            return resultadoADevolver;
        } catch (PersistenciaExcepcion e) {
               throw new NegocioExcepcion(e.getMessage(), e);
        }
	}

	@Override
	public List<VistaSoporteResultadoCobroCreditoDebito> obtenerDebitosPorReferencia(
			String idDebitoReferencia, String idCobro) throws NegocioExcepcion {
		
		List<VistaSoporteResultadoCobroCreditoDebito> resultado;
		try {
			resultado = vistaSoporteDao.obtenerDebitosPorReferencia(idDebitoReferencia, idCobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return resultado;
	}

	@Override
	public List<VistaSoporteResultadoCobroCreditoDebito> obtenerCreditosPorReferencia(
			String idCreditoReferencia, String idCobro) throws NegocioExcepcion {
		
		List<VistaSoporteResultadoCobroCreditoDebito> resultado;
		try {
			resultado = vistaSoporteDao.obtenerCreditosPorReferencia(idCreditoReferencia, idCobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return resultado;
	}
	@Override
	public List<VistaSoporteResultadoCobroCreditoDebito> obtenerDocumentoCapPorReferencia(
			String idCapReferencia, String idCobro) throws NegocioExcepcion {
		
		List<VistaSoporteResultadoCobroCreditoDebito> resultado;
		try {
			resultado = vistaSoporteDao.obtenerDocumentoCapPorReferencia(idCapReferencia, idCobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return resultado;
	}
	@Override
	public boolean obtenerMarcaCreditoEnCobrosPendienteProceso(String idCreditoReferencia) throws NegocioExcepcion {
		
		try {
			return vistaSoporteDao.obtenerMarcaCreditoEnCobrosPendienteProceso(idCreditoReferencia);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	@Override
	public boolean obtenerMarcaDebitoEnCobrosPendienteProceso(String idDebitoReferencia) throws NegocioExcepcion {
		
		try {
			return vistaSoporteDao.obtenerMarcaDebitoEnCobrosPendienteProceso(idDebitoReferencia);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public List<VistaSoporteResultadoBusquedaDescobro> listarDescobros(
			VistaSoporteDescobroFiltro descobroFiltro) throws NegocioExcepcion {

		List<VistaSoporteResultadoBusquedaDescobro> resultadoListaDescobros;
		
		try {
			resultadoListaDescobros = vistaSoporteDao.listarDescobros(descobroFiltro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return resultadoListaDescobros;
	}

	@Override
	public List<VistaSoporteResultadoBusquedaDescobroHistorial> obtenerHistorialDescobro (VistaSoporteBusquedaDescobroHistorialFiltro filtro) throws NegocioExcepcion {
		
		List<VistaSoporteResultadoBusquedaDescobroHistorial> historial;
		
		try {
			historial = vistaSoporteDao.obtenerHistorialDescobro(filtro);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return historial;
	}
	
	@Override
	public List<VistaSoporteResultadoBusquedaDescobroTransaccion> obtenerTransaccionesDescobro (VistaSoporteDescobroTransaccionFiltro filtro) throws NegocioExcepcion {
		
		List<VistaSoporteResultadoBusquedaDescobroTransaccion> listaTransaccionesModelo;
		
		try {
			listaTransaccionesModelo = vistaSoporteDao.obtenerTransaccionesDescobro(filtro);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return listaTransaccionesModelo;
	}
	
	@Override
	public List<VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas> obtenerOperacionesRelacionadasDescobro (VistaSoporteDescobroOperacionRelacionadaFiltro filtro) throws NegocioExcepcion {
		
		List<VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas> listaOperacionesRelacionadasModelo;
		
		try {
			listaOperacionesRelacionadasModelo = vistaSoporteDao.obtenerOperacionesRelacionadasDescobro(filtro);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return listaOperacionesRelacionadasModelo;
	}
	@Override
	public boolean obtenerMarca(Object valor, QueryMarcaEnum queryMarca) throws NegocioExcepcion {
		try {
			return vistaSoporteDao.obtenerMarca(valor, queryMarca);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public List<VistaSoporteOperacionesMasivas> listarOperacionesMasivas(
			VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro)
			throws NegocioExcepcion {

		List<VistaSoporteOperacionesMasivas> resultado;
		try {
			resultado = vistaSoporteDao.listarOperacionesMasivas(operacionMasivaFiltro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return resultado;
		
	}
	
	@Override
	public List<VistaSoporteResultadoBusquedaOperacionMasivaHistorial> obtenerHistorialOperacionMasiva (String idOperacioMasiva) throws NegocioExcepcion {
		
		List<VistaSoporteResultadoBusquedaOperacionMasivaHistorial> historial;
		
		try {
			historial = vistaSoporteDao.obtenerHistorialOperacionMasiva(idOperacioMasiva);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return historial;
	}

	@Override
	public boolean obtenerMarcaCapEnCobrosPendienteProceso(String idCapReferencia) throws NegocioExcepcion {
		
		try {
			return vistaSoporteDao.obtenerMarcaCapEnCobrosPendienteProceso(idCapReferencia);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public List<String> consultarResultadoVerificacionDocumentosCap(Long idCobro) throws NegocioExcepcion {

		try {
			return vistaSoporteDao.consultarResultadoVerificacionDocumentosCap(idCobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public List<VistaSoporteConsultaDeudaPdfCap> obtenerRegistrosDeudaPdf(Long idOperacion) throws NegocioExcepcion {
		try {
			return vistaSoporteDao.obtenerRegistrosDeudaPdf(idOperacion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public List<VistaSoporteConsultaCapPdfCap> obtenerRegistrosCapPdf(Long idOperacion) throws NegocioExcepcion {
		try {
			return vistaSoporteDao.obtenerRegistrosCapPdf(idOperacion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	/**
	 * 
	 */
	public List<ShvSopExcepcionMorosidad> obtenerRegistrosExcepcionMorosidad(ExcepcionMorosidadFiltro filtro) throws NegocioExcepcion {
		try {
			return vistaSoporteDao.obtenerRegistrosExcepcionMorosidad(filtro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public List<Bean> listarLegajosChequeRechazado(VistaSoporteLegajoChequeRechazadoFiltro legajoFiltro) throws NegocioExcepcion{

		try {
			return vistaSoporteDao.listarLegajosChequeRechazado(legajoFiltro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}

	@Override
	public List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listarCobrosRelacionados(VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro filtro) throws NegocioExcepcion {
		try {
			return vistaSoporteDao.listarCobrosRelacionados(filtro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado obtenerLegajoChequeRechazadoCobrosRelacionadosEstadoReversionShivaBy(Long idOperacion) throws NegocioExcepcion {
		return vistaSoporteDao.obtenerLegajoChequeRechazadoCobrosRelacionadosEstadoReversionShivaBy(idOperacion);
	}

	@Override
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> obtenerChequeRechazadoDetalleDocumentoBy(Long idLegajo) throws NegocioExcepcion {
		
		return vistaSoporteDao.obtenerChequeRechazadoDetalleDocumentoBy(idLegajo);
	}
	@Override
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> listarChequeRechazadoDetalleDocumento(Long idLegajo) throws NegocioExcepcion {
		try {
			return vistaSoporteDao.listarChequeRechazadoDetalleDocumento(idLegajo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}

	@Override
	public List<VistaSoporteMotorConciliacion> listarRegistrosMotorConciliacionPorReglaMenor() throws NegocioExcepcion {
		try {
			return vistaSoporteDao.listarRegistrosMotorConciliacionPorReglaMenor();
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
}
