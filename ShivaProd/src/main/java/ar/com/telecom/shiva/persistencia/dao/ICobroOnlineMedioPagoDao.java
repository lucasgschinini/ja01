package ar.com.telecom.shiva.persistencia.dao;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosMedioPago;

public interface ICobroOnlineMedioPagoDao {
	
	ShvCobEdOtrosMedioPago guardarMedioPago(ShvCobEdOtrosMedioPago medioPago) throws PersistenciaExcepcion;
	
	void borrarMediosPagoDelCobro(Long idCobro) throws PersistenciaExcepcion;
	
}
