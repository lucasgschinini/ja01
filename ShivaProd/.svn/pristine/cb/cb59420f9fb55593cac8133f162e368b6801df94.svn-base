package ar.com.telecom.shiva.negocio.servicios;

import java.util.Collection;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ActualizacionExitosaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public interface IBoletaSinValorServicio extends IServicio {

	String listar10CodigosClienteLegado(String usuarioLogueado) throws NegocioExcepcion;
	
	void anularBoletas(String listaBoletas, UsuarioSesion usuarioLogueado) throws NegocioExcepcion;
	
	List<BoletaSinValorDto> listarBoletasPendientesConciliar(TipoValorEnum tipoValor) throws NegocioExcepcion;

	ShvBolBoleta establecerRegistroConciliado(BoletaSinValorDto boletaDto) throws NegocioExcepcion;
	
	ShvBolBoleta establecerRegistroConciliacionSugerida(BoletaSinValorDto boletaDto) throws NegocioExcepcion;

	Long getIdValorAsociadoABoleta(BoletaSinValorDto boletaDto, String tipoValor) throws NegocioExcepcion;

	void establecerRegistroConciliadoConDiferencia(BoletaSinValorDto boletaDto) throws NegocioExcepcion;
	
	void deshacerConciliacionSugerida(BoletaSinValorDto boletaDto) throws NegocioExcepcion;
	
	List<String> enviarMail(String[] listaBoletas, UsuarioSesion userSesion) throws NegocioExcepcion, PersistenciaExcepcion;
	
	ActualizacionExitosaDto imprimirBoletas(String[] listaBoletas,UsuarioSesion userSesion) throws NegocioExcepcion, PersistenciaExcepcion;

	BoletaSinValorDto buscarPorNumeroBoleta(Long numeroBoleta)  throws NegocioExcepcion;
	
	BoletaSinValorDto buscarPorIdBoleta(Integer idBoleta) throws NegocioExcepcion;
	
	void anularBoletasPendientes(String fechaHasta) throws NegocioExcepcion;
	
	void cargarCombosParaBusquedaBoletaSinValor(ModelAndView mv,
			UsuarioSesion userSesion, Filtro filtro) throws ShivaExcepcion;
	
	public Collection<DTO> listarBoletasSinValor(Filtro filtro, UsuarioSesion userSesion) throws NegocioExcepcion; 
}	
