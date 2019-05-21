package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvBolBoletaSimplificado;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;

public interface IBoletaDao {

	ShvBolBoleta insertarBoleta(ShvBolBoleta boleta) throws PersistenciaExcepcion;
	
	ShvBolBoleta actualizarBoleta(ShvBolBoleta boleta) throws PersistenciaExcepcion;
	
	ShvBolBoletaSimplificado actualizarBoleta(ShvBolBoletaSimplificado boleta) throws PersistenciaExcepcion;
	
	ShvBolBoleta buscarBoleta(Long id) throws PersistenciaExcepcion;
	
	List<ShvBolBoleta> listarTodasBoletas() throws PersistenciaExcepcion;
	
	Long proximoValorNumeroBoleta() throws PersistenciaExcepcion;

	List<ShvBolBoleta> buscarBoletasPendientesYRechazadasParaDepurar(BoletaSinValorFiltro filtro) throws PersistenciaExcepcion;

	ShvBolBoletaSimplificado buscarBoletaSimplificado(Long idBoleta)throws PersistenciaExcepcion;
}
