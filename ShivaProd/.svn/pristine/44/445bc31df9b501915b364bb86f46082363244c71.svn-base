package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvParamTeamComercial;

public interface ITeamComercialDao {
	
	public void borrarTodos();
	
	public void guardarTeamComercial(ShvParamTeamComercial teamComercial) throws PersistenciaExcepcion;

	public ShvParamTeamComercial buscarTeamComercial(String nroCliente) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param listaNroCliente
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<String> listarIdAnalistaTeamComercialPorListaNroCliente(List<String> listaNroCliente) throws PersistenciaExcepcion;

	/**
	 * 
	 * @return Ultimo Id de la tabla que tenga Empresa != 'TA' y Origen != 'DELTA'
	 * @throws PersistenciaExcepcion
	 */
	public Long obtenerUltimoId() throws PersistenciaExcepcion;

	/**
	 * 
	 * @param empresa
	 * @param origen
	 * @throws PersistenciaExcepcion
	 */
	void borrarPorEmpresaYOrigen(EmpresaEnum empresa, ClienteOrigenEnum origen) throws PersistenciaExcepcion;

}
