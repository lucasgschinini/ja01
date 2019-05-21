package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ArchivoCotizacionesSapNombreIncorrectoExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ArchivoCotizacionesSapVacioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionRegistroCotizacionSapExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.RegistroCotizacionSap;
import ar.com.telecom.shiva.negocio.dto.CotizacionDto;
import ar.com.telecom.shiva.negocio.mapeos.CotizacionMapeador;
import ar.com.telecom.shiva.negocio.servicios.ICotizacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICotizacionValidacionServicio;
import ar.com.telecom.shiva.persistencia.dao.ICotizacionDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvParamCotizacion;
import ar.com.telecom.shiva.presentacion.bean.filtro.CotizacionFiltro;

/**
 * 
 * @author 
 */
public class CotizacionServicioImpl extends Servicio implements ICotizacionServicio {
	
	private @Autowired ICotizacionDao cotizacionDao;
	private @Autowired ICotizacionValidacionServicio cotizacionValidacionServicio;
	private @Autowired CotizacionMapeador cotizacionMapeador;
	
	// Nombre del archivo q nos envia Sap: TCURR_SHIVA_aaaammdd
	private final String nombreArchivoCotizaciones = "TCURR_SHIVA_";

	/**
	 * 
	 * @param file
	 * @return
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, NegocioExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override
	public int procesarContenidoArchivoCotizacionesSap (File file) throws NegocioExcepcion {

		Traza.advertencia(this.getClass(), "Comienza el procesamiento del archivo: " + file.getName());
		
		String[] nombreArchivo = file.getName().split("_");
		String[] nombreArchivoModelo = nombreArchivoCotizaciones.split("_");

		int exit = Constantes.PROCESO_BATCH_RETORNO_EXIT_OK;

		try {

			if(nombreArchivo[0].equalsIgnoreCase(nombreArchivoModelo[0]) 
					&& nombreArchivo[1].equalsIgnoreCase(nombreArchivoModelo[1]) 
					&& Validaciones.validarFecha(Utilidad.cambiarFormatoFechaDDMMAAAA(nombreArchivo[2]))) {
	
				BufferedReader br = new BufferedReader(new FileReader(file));
				RegistroCotizacionSap registro = new RegistroCotizacionSap();
				Long nroRegistro = 0L;
	
				String linea = br.readLine();
	
				if (Validaciones.isNullOrEmpty(linea)) {
					br.close();

					String msgError = "Archivo: "+ file.getName() + " - " + Propiedades.MENSAJES_PROPIEDADES.getString("procesoBatch.cotizacionesSap.archivoVacio");
					Traza.error(this.getClass(), msgError);
					throw new ArchivoCotizacionesSapVacioExcepcion(msgError);
				}
	
				//procesa linea x linea del archivo seleccionado
				while (!Validaciones.isNullOrEmpty(linea)) {
	
					nroRegistro++;
					registro.setNumeroRegistro(nroRegistro);
					registro.setContenidoRegistro(linea);
	
					try {
						CotizacionDto cotizacionDto = cotizacionValidacionServicio.validarRegistroCotizacionesSap(registro);
	
						crear(cotizacionDto);

					} catch (ValidacionRegistroCotizacionSapExcepcion e) {
	
						for (String error : e.getListaErrores()) {
							Traza.advertencia(this.getClass(), error);
						}
						
						exit = Constantes.PROCESO_BATCH_RETORNO_EXIT_WARNING;
					}
					linea = br.readLine();
				}
				
				br.close();

			} else {
				String msgError = "El nombre del archivo es incorrecto: " + file.getName();
				Traza.error(this.getClass(), msgError);
				throw new ArchivoCotizacionesSapNombreIncorrectoExcepcion(msgError);
			}

		} catch (IOException ex) {
			throw new NegocioExcepcion(ex);

		} finally {
			Traza.advertencia(this.getClass(), ".... Fin el procesamiento del archivo: " + file.getName());
			Traza.advertencia(this.getClass(), ".");
		}
		
		return exit;
	}

	@Override
	public List<ShvParamCotizacion> listar(CotizacionFiltro filtro) throws NegocioExcepcion {

		try {
			List<ShvParamCotizacion> shvParamCotizacion = cotizacionDao.listar(filtro);
			return shvParamCotizacion;

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}

	@Override
	public ShvParamCotizacion crear(CotizacionDto cotizacionDto) throws  NegocioExcepcion {
		ShvParamCotizacion shvParamCotizacion = (ShvParamCotizacion) cotizacionMapeador.map(cotizacionDto, null); 
		
		try {
			shvParamCotizacion = cotizacionDao.crear(shvParamCotizacion);
		} catch (PersistenciaExcepcion e) {
			Traza.error(this.getClass(), "Error en el registro Nro: " + cotizacionDto.getNroRegistro());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return shvParamCotizacion;
	}

}
