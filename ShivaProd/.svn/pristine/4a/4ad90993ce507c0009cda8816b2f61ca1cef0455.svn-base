package ar.com.telecom.shiva.negocio.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalCompensacion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamGestor;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ReciboPreImpresoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TalonarioDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.TalonarioFiltro;

public interface ITalonarioServicio extends IServicio {
	
	public Boolean existeTalonarioRango(String desde, String hasta, String nroSerie) throws NegocioExcepcion;

	public Boolean existeTalonarioRango(String rangoDesde, String rangoHasta, String idTalonario, String nroSerie) throws NegocioExcepcion;
	
	public List<Estado> listarComboEstados() throws NegocioExcepcion;

	public List<ShvParamGestor> comboGestores(TalonarioDto talonarioDto) throws NegocioExcepcion;
	
	
	/** acciones de talonarios */
	
	public void cargarCombosTalonarios(ModelAndView mv,
			UsuarioSesion userSesion, Filtro filtro) throws ShivaExcepcion;

	public void rechazoTalonario(TalonarioDto talonarioDto) throws NegocioExcepcion;
	
	public void eliminarTareaRevisionTalonario(TalonarioDto talonarioDto, UsuarioSesion usuarioSesion) throws NegocioExcepcion;
	
	public void rendirTalonario(TalonarioDto talonarioDto) throws NegocioExcepcion;
	
	public void rechazarRendicionTalonario(TalonarioDto talonario, UsuarioSesion userSesion) throws NegocioExcepcion;
	
//	public void autorizarRendicionTalonario(String talonarioId, UsuarioSesion userSesion) throws NegocioExcepcion;
	public void autorizarRendicionTalonario(TalonarioDto talonario, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public void asignarGestor(TalonarioDto talonarioDto) throws NegocioExcepcion;
		
	/** Recibos */
	
	public ReciboPreImpresoDto buscarRecibo(String idRecibo) throws NegocioExcepcion;
	
	public List<ReciboPreImpresoDto> buscarRecibos(String talonarioId) throws NegocioExcepcion;
	
	public List<ShvTalCompensacion> listaCompensaciones(Integer idRecibo) throws NegocioExcepcion;

	public String validarFechaIngresoRecibos(String idTalonario) throws NegocioExcepcion;
	
	public String validarFechaIngresoNula(TalonarioDto talonarioDto) throws NegocioExcepcion;
	
	public ArrayList<TalonarioDto> buscarTalonarios(TalonarioFiltro talonarioFiltro) throws NegocioExcepcion;
}
