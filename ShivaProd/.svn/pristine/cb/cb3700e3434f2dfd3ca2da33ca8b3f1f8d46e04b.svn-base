package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;

public interface ICobroOnlineDebitoDao {
	
	ShvCobEdDebito guardarDebito(ShvCobEdDebito debitoModelo) throws PersistenciaExcepcion;
	
	void borrarDebitosDelCobro(Long idCobro) throws PersistenciaExcepcion;

	List<ShvCobEdDebito> listarDebitosPendienteValidacion() throws PersistenciaExcepcion;

	public List<Long> listarIdCobrosPorIdCobroYTipoComprobante(List<Long> idCobros, TipoComprobanteEnum tipoComprobante) throws PersistenciaExcepcion;

	public ShvCobEdDebito buscarDebito(long idDebito, long idCobro) throws PersistenciaExcepcion;
	
}
