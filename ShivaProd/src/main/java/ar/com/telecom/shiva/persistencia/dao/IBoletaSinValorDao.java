package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoletaSinValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvBolBoletaSinValorSimple;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;

public interface IBoletaSinValorDao {

	ShvBolBoletaSinValor buscarBoletaSinValor(Long numeroBoleta) throws PersistenciaExcepcion;
	
	List<ShvBolBoletaSinValor> buscarBoletasSinValor(BoletaSinValorFiltro boletaFiltro) throws PersistenciaExcepcion;

	List<ShvBolBoletaSinValor> buscarBoletasSinValor(BoletaSinValorFiltro boletaFiltro, UsuarioSesion userSesion) throws PersistenciaExcepcion;
	
	List<ShvBolBoletaSinValor> listarTodasBoletasSinValor() throws PersistenciaExcepcion;
	
	List<ShvBolBoletaSinValorSimple> listarCodigosClienteLegado(String usuarioLogueado) throws PersistenciaExcepcion;

	ShvBolBoletaSinValor buscarBoletaSinValorPorNumeroBoleta(String boleta) throws PersistenciaExcepcion;
	
	List<ShvBolBoleta> listarBoletasSinValorPendientesConciliar() throws PersistenciaExcepcion;

	List<ShvValValor> listarBoletasConValorPendientesConciliar(TipoValorEnum tipoValor) throws PersistenciaExcepcion;
	
}
