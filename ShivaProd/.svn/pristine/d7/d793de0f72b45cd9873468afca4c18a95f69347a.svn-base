package ar.com.telecom.shiva.negocio.servicios;

import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ImportarOtrosDebitoJsonResponse;

/**
 * Servicio para el manejo de los cobros Onlines
 * 
 */
public interface ICobroOnlineSoporteServicio extends IServicio {
	/**
	 * 
	 * @param file
	 * @param string
	 * @param idCobro
	 * @param monedaCobro
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ShivaExcepcion
	 * @throws PersistenciaExcepcion
	 */
	public ImportarOtrosDebitoJsonResponse resultadoValidacionesOtrosDebitos(MultipartFile file, String string, Long idCobro, MonedaEnum monedaCobro) throws NegocioExcepcion, ShivaExcepcion, PersistenciaExcepcion;

}