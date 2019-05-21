package ar.com.telecom.shiva.persistencia.dao;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCredito;

public interface ICobroOnlineCreditoDao {
	
	ShvCobEdCredito guardarCredito(ShvCobEdCredito cliente) throws PersistenciaExcepcion;
	
	void borrarCreditosDelCobro(Long idCobro) throws PersistenciaExcepcion;
}
