package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.EstadoRegistroOperacionMasivaEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;

public interface IRegistroOperacionMasivaDao {

	ShvMasRegistro crear(ShvMasRegistro registroAVC) throws PersistenciaExcepcion;
	
	List<ShvMasRegistro> listarRegistrosOperacionesMasivasProcesados() throws PersistenciaExcepcion;
	
	List<ShvMasRegistro> listarRegistrosOperacionesMasivasPendientes() throws PersistenciaExcepcion;

	ShvMasRegistro buscarRegistroOperacionMasiva(String id) throws PersistenciaExcepcion;

	ShvMasRegistro modificar(ShvMasRegistro registroModelo) throws PersistenciaExcepcion;

	ShvMasRegistro buscarRegistroOperacionMasivaPorIdOperacionMasiva(String idOperacionMasiva) throws PersistenciaExcepcion;
	
	ShvMasRegistro buscarRegistroOperacionMasivaPorIdCobro(String idCobro)throws PersistenciaExcepcion;

	public List<ShvMasRegistro> buscarRegistrosXOperacionMasivaDistintosAEstado(EstadoRegistroOperacionMasivaEnum estado, Long idOperacionMasiva) throws PersistenciaExcepcion;
	
	public List<ShvMasRegistro> buscarRegistrosByIds(List<Long> ids);
		
	
	public List<ShvMasRegistro> buscarRegistrosOperacionMasivaByEstadoAndEstadosOperacionMasiva(
		EstadoRegistroOperacionMasivaEnum estadoReg,
		//TipoArchivoOperacionMasivaEnum tipo,
		List<Estado> estadosOperacionMasiva
	) throws PersistenciaExcepcion;

	public List<Long> obtenerIdOperacionShivaReutilizable() throws PersistenciaExcepcion;

	public String test();

	public List<ShvMasRegistro> modificar(List<ShvMasRegistro> shvMasRegistro) throws PersistenciaExcepcion;

	public ShvMasRegistro buscarRegistroByEstadoAndIdOperacion(EstadoRegistroOperacionMasivaEnum estado, Long idOperacion) throws PersistenciaExcepcion;
	public List<ShvMasRegistro> buscarRegistroByEstadoAndIdOperacionMasiva(EstadoRegistroOperacionMasivaEnum estado, Long idOperacionMasiva) throws PersistenciaExcepcion;

}
