package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoletaSinValor;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;

public interface IFacturaDao {

	ShvBolBoleta insertarBoleta(ShvBolBoleta boleta) throws PersistenciaExcepcion;

	List<ShvBolBoletaSinValor> buscarBoletasSinValor(BoletaSinValorFiltro boletaFiltro) throws PersistenciaExcepcion;
}
