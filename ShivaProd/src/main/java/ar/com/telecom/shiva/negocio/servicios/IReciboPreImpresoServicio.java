package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ReciboPreImpresoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;


public interface IReciboPreImpresoServicio extends IServicio {
	
	/**
	 * 
	 * @param reciboDto
	 * @param usuarioModificacion
	 * @throws NegocioExcepcion
	 */
	public void guardarCompensaciones(ReciboPreImpresoDto reciboDto, String usuarioModificacion) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param listaIdRecibos
	 * @param usuarioLogueado
	 * @param idTalonario
	 * @param timeStamp
	 * @throws NegocioExcepcion
	 */
	public void anularRecibos(String listaIdRecibos, UsuarioSesion usuarioLogueado, String idTalonario, String timeStamp) throws NegocioExcepcion;

	/**
	 * Se valida la fecha de ingreso del recibo vs las fechas de ingreso recibo de los diferentes valores/boletas asociadas.
	 * Si existe diferencia entre las fechas de valores/boletas asociadas vs la fecha ingresada por el usuario en el recibo,
	 * se genera un mensaje de advertencia indicando tal situación.
	 * 
	 * @param reciboDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String validarFechaIngreso(ReciboPreImpresoDto reciboDto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param matrizValores
	 * @param numeroRecibo
	 * @param usuarioModificacion
	 * @throws NegocioExcepcion
	 */
	public void actualizacionEstado(List<List<ShvValValor>> matrizValores, String numeroRecibo, String usuarioModificacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param valorDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String validarRecibo(ValorDto valorDto) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param nroRecibo
	 * @param timeStamp
	 * @throws NegocioExcepcion
	 */
	public void verificarConcurrenciaSimplificado(String nroRecibo, Long timeStamp) throws NegocioExcepcion;

}
