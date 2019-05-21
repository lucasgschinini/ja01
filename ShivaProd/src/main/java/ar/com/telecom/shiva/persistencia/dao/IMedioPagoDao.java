package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;

public interface IMedioPagoDao {
	
	public List<ShvCobMedioPagoDebitoProximaFacturaCalipso> buscarMedioPagoDebProxCalipsoPorIdMedioPago(Integer idMedioPago) throws PersistenciaExcepcion;
	
	public List<ShvCobMedioPagoDebitoProximaFacturaMic> buscarMedioPagoDebProxMicPorIdMedioPago(Integer idMedioPago) throws PersistenciaExcepcion;

}
