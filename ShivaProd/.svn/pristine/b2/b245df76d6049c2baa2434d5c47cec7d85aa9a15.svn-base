package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import org.springframework.validation.Errors;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.dto.ValorPorReversionDto;
import ar.com.telecom.shiva.persistencia.modelo.ShvValReversionValor;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;



public interface IValorPorReversionServicio extends IServicio {

	List<ValorPorReversionDto> listarPendientesYRechazados() throws NegocioExcepcion;
	
	ValorDto buscarValorPorReversion(String idValorPorReversion) throws NegocioExcepcion;

	void generarTarea(ValorDto valorDto, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	ShvValReversionValor buscarReversionValor(String idReversionValor) throws NegocioExcepcion;

	void confirmarTareaAltaValor(ValorDto valorDto) throws NegocioExcepcion;
	
	void rechazarTareaAltaValor(ValorDto valorDto) throws NegocioExcepcion;

	ValorDto buscarValorCreadoAPartirReversion(String valorPorReversion) throws NegocioExcepcion;

	boolean validarValorPorReversion(ValorDto valorDto, Errors errors, String tagError) throws NegocioExcepcion;

	void procesarValorPorReversion(ValorDto valorDto, UsuarioSesion userSesion) throws NegocioExcepcion;
	
	public void eliminarTareaAceptarAltaValorPorReversion(ValorDto valorDto, UsuarioSesion usuarioSesion) throws NegocioExcepcion;

	 //buscar, listar, crear, modificar,
	
}	
