package ar.com.telecom.shiva.negocio.servicios.validacion.impl;

import java.text.ParseException;
import java.util.Date;

import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.validacion.IArchivoAvcValidacionServicio;
import ar.com.telecom.shiva.presentacion.bean.filtro.ArchivoAVCFiltro;

public class ArchivoAvcValidacionServicioImpl implements IArchivoAvcValidacionServicio {

	public void validacionFechaAltaDesdeHasta(ArchivoAVCFiltro archivoFiltro) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(archivoFiltro.getFechaDesde())) {
			if (Validaciones.validarFecha(archivoFiltro.getFechaDesde())) {
				if (!Validaciones.isNullOrEmpty(archivoFiltro.getFechaHasta())) {
					if (!Validaciones.validarFecha(archivoFiltro.getFechaHasta())) {
						throw new ValidacionExcepcion("fechaHasta","error.fechaIncorrecta");
					}else{
						try {
							Date desde = Utilidad.deserializeAndFormatDate(archivoFiltro.getFechaDesde(),Utilidad.DATE_FORMAT_PICKER);
							Date hasta = Utilidad.deserializeAndFormatDate(archivoFiltro.getFechaHasta(),Utilidad.DATE_FORMAT_PICKER);
							if(hasta.before(desde)){
								throw new ValidacionExcepcion("fechaDesde","error.desdeHasta");
							}
						} catch (ParseException e) {
							throw new ValidacionExcepcion(e.getMessage(),e);
						}
					}
				}
			}else{
				throw new ValidacionExcepcion("fechaDesde","error.fechaIncorrecta");
			}
		}else{
			if (!Validaciones.isNullOrEmpty(archivoFiltro.getFechaHasta())) {
				if (!Validaciones.validarFecha(archivoFiltro.getFechaHasta())) {
					throw new ValidacionExcepcion("fechaHasta","error.fechaIncorrecta");
				}
			}
		}
	}
}
