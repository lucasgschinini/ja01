package ar.com.telecom.shiva.negocio.servicios.validacion.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.bean.ImportarOperacionesMasivasAuxiliar;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.IOperacionMasivaValidacionServicio;
import ar.com.telecom.shiva.persistencia.dao.IArchivoOperacionMasivaDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasivaArchivo;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteOperacionMasivaFiltro;

public class OperacionMasivaValidacionServicioImpl implements IOperacionMasivaValidacionServicio {

	@Autowired
	private IArchivoOperacionMasivaDao archivoOperacionMasivaDao;
	@Autowired
	private IOperacionMasivaServicio operacionMasivaServicio;
	
	@Autowired
	private IParametroServicio parametroServicio;
	
	private final String SEMICOLON = ";";
	
	/** Archivo Aplicar Deuda - Indice de campos del registro **/
	public static final int IMPORTE_DEUDA = 5;
	
	/** Archivo Desestiemiento - Indice de campos del registro **/
	public static final int IMPORTE_DESISTIMIENTO = 2;
	
	/** Archivo Ganancias - Indice de campos del registro **/
	public static final int IMPORTE_GANANCIAS = 4;
	
	/** Archivo Reintegro - Indice de campos del registro **/
	public static final int IMPORTE_REINTEGRO = 6;

	
	public void validacionEmpresa(String empresa) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(empresa)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}

	public void validacionSegmento(String segmento) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(segmento)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}

	public void validacionMotivo(String motivo) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(motivo)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}
	
	/**
	 * 
	 */
	public OperacionMasivaDto validarDuplicidad(OperacionMasivaDto operacionMasiva) throws ValidacionExcepcion {
		try {
			BigDecimal importeTotal = new BigDecimal(0);
			ImportarOperacionesMasivasAuxiliar importarOperacionesMasivasAuxiliar = null;

			File file = ControlArchivo.convert(operacionMasiva.getFileBytes() ,operacionMasiva.getFileOperacionMasiva());
			String nombreArchivo = file.getName();
			TipoArchivoOperacionMasivaEnum tipoArchivo = 
					TipoArchivoOperacionMasivaEnum.getEnumByName(ControlArchivo.getTipoArchivo(file.getName()));

			operacionMasiva.setTipoOperacionMasiva(tipoArchivo);
			// Valido la cantidad maxima registros por archivo
			String[] registros = ControlArchivo.leerArchivo(file.getPath(), Constantes.RETORNO_UNIX);
			int cantMaximaRegistros = validarCantidadRegistros(tipoArchivo, registros.length);
			if (cantMaximaRegistros > 0) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.cantidad.maxima.registros"), 
						nombreArchivo, String.valueOf(registros.length), String.valueOf(cantMaximaRegistros));
				operacionMasiva.setResultadoValidaciones(mensajeError);
			}

			// Valido la duplicidad del nombre del archivo
			ShvMasOperacionMasivaArchivo archivoDuplicadoNombre = archivoOperacionMasivaDao.validarDuplicidadNombre(nombreArchivo);
			if (!Validaciones.isObjectNull(archivoDuplicadoNombre)) {
				String formatDatePicker = Utilidad.formatDatePicker(archivoDuplicadoNombre.getDocumentoAdjunto().getFechaCreacion());
				String ultimaSecuencia = archivoOperacionMasivaDao.ultimaSecuenciaDuplicidadNombre(nombreArchivo);

				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.archivo.procesado"),
						nombreArchivo, formatDatePicker, ultimaSecuencia);

				operacionMasiva.setResultadoValidaciones(mensajeError);
			}

			if (Validaciones.isEmptyString(operacionMasiva.getResultadoValidaciones())) {
				importarOperacionesMasivasAuxiliar = operacionMasivaServicio.validarRegistros(operacionMasiva);
				if (importarOperacionesMasivasAuxiliar.getResultadoValidaciones().length() != 0) {
					operacionMasiva.setResultadoValidaciones(importarOperacionesMasivasAuxiliar.getResultadoValidaciones().toString());
				} else {
					int index = -1;
					if (tipoArchivo!=null) {
						switch (tipoArchivo) {
						case DEUDA:
							index = IMPORTE_DEUDA;
							break;
						case DSIST:
							index = IMPORTE_DESISTIMIENTO;
							break;
						case GNCIA:
							index = IMPORTE_GANANCIAS;
							break;
						case REINT:
							index = IMPORTE_REINTEGRO;
							break;
							//				case CONVENIO:
							// No especificado
							//					break;
						default:
							break;
						}
					}

					if (index >= 0) {
						for (String reg : registros) {
							String[] campos = this.obtenerCampos(reg);
							if (!Validaciones.isNullOrEmpty(campos[index])) {
								if (Validaciones.isNumeric(campos[index])) {
									BigDecimal imp = new BigDecimal(campos[index]);
									BigDecimal valueOf = Utilidad.formatCurrencyBD(imp, 2);
									importeTotal = importeTotal.add(valueOf);
								}
							}
						}
					}

					ShvMasOperacionMasivaArchivo archivoDuplicadoContenido = archivoOperacionMasivaDao.validarDuplicidadContenido(tipoArchivo,registros.length,importeTotal);
					if (!Validaciones.isObjectNull(archivoDuplicadoContenido)){
						String formatDatePicker = Utilidad.formatDatePicker(archivoDuplicadoContenido.getDocumentoAdjunto().getFechaCreacion());
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.archivo.duplicado"), 
								tipoArchivo.getDescripcionCorta(), String.valueOf(registros.length), 
								Utilidad.formatCurrency(importeTotal,2), formatDatePicker, archivoDuplicadoContenido.getNombreArchivo());

						operacionMasiva.setResultadoValidaciones(mensajeError);
					}
					if (Validaciones.isEmptyString(operacionMasiva.getResultadoValidaciones())) {
						operacionMasiva.setResultadoValidaciones(Mensajes.VALIDACION_OK_STRING);
					}
				}
			}
		} catch (ShivaExcepcion e) {
			throw new ValidacionExcepcion(e.getMessage(),e);
		} catch (PersistenciaExcepcion e) {
			throw new ValidacionExcepcion(e.getMessage(),e);
		} catch (NegocioExcepcion e) {
			throw new ValidacionExcepcion(e.getMessage(),e);
		}

		return operacionMasiva;
	}
	
	 
	
	/**
	 * Retorna un array con los campos del registro
	 * @param registro
	 * @return
	 */
	private String[] obtenerCampos(String registro) {
		// Se agrega un espacio al registro para que si el último campo no vienen nada, no rebote 
		// el archivo por cantidad de campos necesarios
		return Utilidad.limpiarArrayString((registro + " ").split(SEMICOLON));
	}
	
	/**
	 * Valido la cantidad de registros del archivo recibido
	 * @param tipoArchivo
	 * @param cantRegistrosArchivo
	 * @return
	 * @throws NegocioExcepcion
	 */
	private int validarCantidadRegistros(TipoArchivoOperacionMasivaEnum tipoArchivo, int cantRegistrosArchivo) throws NegocioExcepcion {
		
		int cantidadMaximaRegistros = Integer.valueOf(parametroServicio.getValorNumerico("operacion.masiva.cant.reg." + tipoArchivo.name()).toString());
		if (cantRegistrosArchivo > cantidadMaximaRegistros) {
			return cantidadMaximaRegistros;
		}
		
		return 0;
	}
	
	
	public IArchivoOperacionMasivaDao getArchivoOperacionMasivaDao() {
		return archivoOperacionMasivaDao;
	}

	public void setArchivoOperacionMasivaDao(
			IArchivoOperacionMasivaDao archivoOperacionMasivaDao) {
		this.archivoOperacionMasivaDao = archivoOperacionMasivaDao;
	}

	@Override
	public void validarFiltroBusquedaOperacionesMasivas(
			VistaSoporteOperacionMasivaFiltro operacioMasivaFiltro)
			throws ValidacionExcepcion {
	
		
		if(!Validaciones.isObjectNull(operacioMasivaFiltro)){

			List<String> camposError = new ArrayList<String>();
			List<String> codigosLeyenda = new ArrayList<String>();

			/*
			 * Validacion Empresa
			 */
			if (Validaciones.isNullOrEmpty(operacioMasivaFiltro.getIdEmpresa())) {
				camposError.add("#errorEmpresa");
				codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("cobro.busqueda.mensaje.error.empresa.sin.seleccionar"));
			}
			/*
			 * Validacion Id Operacion Masiva
			 */
			if (!Validaciones.isNullOrEmpty(operacioMasivaFiltro.getIdOperacionMasiva())){
				if (!Validaciones.isNumeric(operacioMasivaFiltro.getIdOperacionMasiva(),1,7)) {
					camposError.add("#errorIdOperacionMasiva");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
				}
			}




			/*
			 * Validaciones Fecha Desde y Fecha Hasta
			 */
			if (!Validaciones.isNullOrEmpty(operacioMasivaFiltro.getFechaDesde())) {
				if (!Validaciones.validarFecha(operacioMasivaFiltro.getFechaDesde())) {
					camposError.add("#errorFechaDesde");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.fechaIncorrecta"));
				}
			}

			if (!Validaciones.isNullOrEmpty(operacioMasivaFiltro.getFechaHasta())) {
				if (!Validaciones.validarFecha(operacioMasivaFiltro.getFechaHasta())) {
					camposError.add("#errorFechaHasta");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.fechaIncorrecta"));
				}
			}

			if (!Validaciones.isNullOrEmpty(operacioMasivaFiltro.getFechaDesde())
					&& !Validaciones.isNullOrEmpty(operacioMasivaFiltro.getFechaHasta())) {

				if (Validaciones.validarFecha(operacioMasivaFiltro.getFechaDesde())
						&& Validaciones.validarFecha(operacioMasivaFiltro.getFechaHasta())) {

					if (!Validaciones
							.validarFechaDesdeHasta(operacioMasivaFiltro.getFechaDesde(), operacioMasivaFiltro.getFechaHasta())) {
						camposError.add("#errorFechaDesde");
						codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.fechaDesdeHastaIncorrecto"));
					}
				}
			}

			if (Validaciones.isCollectionNotEmpty(camposError)){
				throw new ValidacionExcepcion(camposError,codigosLeyenda);
			}
		}



	}

	@Override
	public List<OperacionMasivaDto> listarOperacionesMasivas(
			VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro)
			throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

}
