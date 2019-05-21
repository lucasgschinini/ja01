package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.TipoOperacionEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDetalleAplicacionManual;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaImporteTransaccionesAplicacionManual;
import ar.com.telecom.shiva.persistencia.modelo.ShvArcArchivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobAplicacionManualBatchDetalle;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroCodigoOperacionExternaSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCodigoOperacionExternaSimplificado;

public interface IProcesarEntradaDetalleAplicacionesManualesDao {
	
	public List<ResultadoBusquedaDetalleAplicacionManual> obtenerDatosRegistro(Long idOperacion, TipoOperacionEnum tipoOperacion)throws PersistenciaExcepcion;
	
	List<ShvCobAplicacionManualBatchDetalle> obtenerListaShvCobAplicacionManualBatchDetalle(List<String> listaIdTransacciones, TipoOperacionEnum tipoOperacion) throws PersistenciaExcepcion;

	public ShvArcArchivo insertarArcArchivo(ShvArcArchivo archivo) throws PersistenciaExcepcion;
	
	public ShvArcArchivo buscarArchivoEntradaPorNombreArchivo(String name) throws PersistenciaExcepcion;
	
	public ShvCobAplicacionManualBatchDetalle actualizarShvCobAplicacionManualBatchDetalle(ShvCobAplicacionManualBatchDetalle detModelo) throws PersistenciaExcepcion;
	
	public List<ResultadoBusquedaImporteTransaccionesAplicacionManual> obtenerImporteTransaccionesAplicacionManual (Long idOperacion) throws PersistenciaExcepcion;

	public ShvCobEdCodigoOperacionExternaSimplificado guardarShvCobEdCodigoOperacionExternaSimplificado(ShvCobEdCodigoOperacionExternaSimplificado codOpExterna) throws PersistenciaExcepcion;
	
	public ShvCobDescobroCodigoOperacionExternaSimplificado guardarShvCobDescobroCodigoOperacionExternaSimplificado(ShvCobDescobroCodigoOperacionExternaSimplificado codOpExterna) throws PersistenciaExcepcion;
	
}
