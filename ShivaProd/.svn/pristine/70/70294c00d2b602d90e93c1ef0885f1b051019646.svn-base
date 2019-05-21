package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenReportePerfilesAuditoria;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenResultadoAcciones;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenShvSegPerfil;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenShvSegPerfilAplicativo;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoParametroEntradaPerfilesAplicativoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ReportePerfilesExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.ReportePerfilesAuditoriaEntrada;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IReportePerfilesAuditoriaServicio;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaAcciones;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IReportePerfilesAuditoriaDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvArcArchivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvSegPerfil;
import ar.com.telecom.shiva.persistencia.modelo.ShvSegPerfilAplicativo;

public class ReportePerfilesAuditoriaServicioImpl implements IReportePerfilesAuditoriaServicio {

	@Autowired
	private IReportePerfilesAuditoriaDao reportePerfilesAuditoriaDao;
	@Autowired
	private IParametroServicio parametroServicio;

	@Autowired
	IGenericoDao genericoDao;

	@Autowired
	private MailServicio mailServicio;

	@Override
	public int generarReporte(String[] parametro) throws NegocioExcepcion, PersistenciaExcepcion {
		int exit = 0;
		try {

			// Valida los parametros de entrada
			Traza.auditoria(getClass(), "~");
			Traza.auditoria(getClass(), "INICIO DE VALIDACION DE LOS PARÁMETROS DE ENTRADA");
			Traza.auditoria(getClass(), "~");
			List<TipoParametroEntradaPerfilesAplicativoEnum> listaParametros = validarParametrosDeEntrada(parametro);
			Traza.auditoria(getClass(), "~");
			Traza.auditoria(getClass(), "FIN DE VALIDACION DE LOS PARÁMETROS DE ENTRADA");
			Traza.auditoria(getClass(), "~");
			// Procesa el archivo de entrada de tuID
			List<ReportePerfilesAuditoriaEntrada> listaPerfiles = procesarArchivoEntrada();
			// Generacion de los archivos
			List<ShvArcArchivo> listaArchivosSalida = generarArchivos(listaPerfiles, listaParametros);
			// Inserta los archivos de salida creados en la tabla
			// SHV_ARC_ARCHIVO
			for (ShvArcArchivo archivo : listaArchivosSalida) {
				insertarArcArchivo(archivo);
			}
			// Envia el mail de procesamiento ok
			enviarMailConDetalleDeProcesamiento(null);
		} catch (ReportePerfilesExcepcion e) {
			Traza.auditoria(getClass(), "~");
			Traza.auditoria(getClass(), "OCURRIO EXCEPCION");
			Traza.auditoria(getClass(), "~");
			// Envia el mail de procesamiento no ok
			enviarMailConDetalleDeProcesamiento(e);
			exit = Constantes.PROCESO_BATCH_RETORNO_EXIT_WARNING;
		}
		return exit;
	}

	/**
	 * 
	 * @param listaPerfiles
	 * @param listaParametros
	 */
	private List<ShvArcArchivo> generarArchivos(List<ReportePerfilesAuditoriaEntrada> listaPerfiles, List<TipoParametroEntradaPerfilesAplicativoEnum> listaParametros) {
		List<ShvArcArchivo> listaArchivos = new ArrayList<ShvArcArchivo>();
		for (TipoParametroEntradaPerfilesAplicativoEnum parametro : listaParametros) {
			switch (parametro) {

			case SHIVA_ACTIONS:
				listaArchivos.add(crearArchivoShivaActions());
				break;
			case SHIVA_DEFAULT_USER:
				listaArchivos.add(crearArchivoShivaDefaultUser(listaPerfiles));
				break;
			case SHIVA_USER_ACTION:
				listaArchivos.add(crearArchivoShivaUserAction(listaPerfiles));
				break;
			case SHIVA_ROLE:
				listaArchivos.add(crearArchivoShivaRole());
				break;
			case SHIVA_PROFILE:
				listaArchivos.add(crearArchivoShivaProfile());
				break;
			}
		}
		return listaArchivos;
	}

	/**
	 * 
	 * @param parametro
	 * @return
	 * @throws ReportePerfilesExcepcion
	 */
	private List<TipoParametroEntradaPerfilesAplicativoEnum> validarParametrosDeEntrada(String[] parametro) throws ReportePerfilesExcepcion {

		List<TipoParametroEntradaPerfilesAplicativoEnum> listaParametros = new ArrayList<TipoParametroEntradaPerfilesAplicativoEnum>();
		int posicion = 0;

		Traza.auditoria(getClass(), "~");
		Traza.auditoria(getClass(), "SE INICIA LA VALIDACION DE LOS PARÁMETROS DE ENTRADA");
		Traza.auditoria(getClass(), "~");

		if (parametro.length > 0) {

			for (String str : parametro) {

				posicion++;
				Traza.auditoria(getClass(), "parametro ingresado en la posicion [" + posicion + "]: " + str);

				if (!Validaciones.isNullOrEmpty(str)) {

					if (!Validaciones.isObjectNull(TipoParametroEntradaPerfilesAplicativoEnum.getEnumByName(str))) {

						switch (TipoParametroEntradaPerfilesAplicativoEnum.getEnumByName(str)) {

						case SHIVA_ACTIONS:
							listaParametros.add(TipoParametroEntradaPerfilesAplicativoEnum.SHIVA_ACTIONS);
							break;
						case SHIVA_DEFAULT_USER:
							listaParametros.add(TipoParametroEntradaPerfilesAplicativoEnum.SHIVA_DEFAULT_USER);
							break;
						case SHIVA_USER_ACTION:
							listaParametros.add(TipoParametroEntradaPerfilesAplicativoEnum.SHIVA_USER_ACTION);
							break;
						case SHIVA_ROLE:
							listaParametros.add(TipoParametroEntradaPerfilesAplicativoEnum.SHIVA_ROLE);
							break;
						case SHIVA_PROFILE:
							listaParametros.add(TipoParametroEntradaPerfilesAplicativoEnum.SHIVA_PROFILE);
							break;
						}
					} else {
						List<String> lista = new ArrayList<String>();
						lista.add(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.entrada.error.parametro"), str, String.valueOf(posicion)));
						Traza.error(this.getClass(), lista.get(0));
						Traza.auditoria(getClass(), "~");
						Traza.auditoria(getClass(), "ERROR EN LOS PARÁMETROS DE ENTRADA");
						Traza.auditoria(getClass(), "~");
						throw new ReportePerfilesExcepcion(lista.get(0), lista, false);
					}
				} else {
					List<String> lista = new ArrayList<String>();
					lista.add(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.entrada.error.parametro.vacio"), String.valueOf(posicion)));
					Traza.error(this.getClass(), lista.get(0));
					Traza.auditoria(getClass(), "~");
					Traza.auditoria(getClass(), "ERROR EN LOS PARÁMETROS DE ENTRADA");
					Traza.auditoria(getClass(), "~");
					throw new ReportePerfilesExcepcion(lista.get(0), lista, false);
				}

			}
		} else {
			// retorno la lista completa si el parámetro está vacío
			Traza.auditoria(getClass(), "No se ingresaron parametros.");
			Traza.auditoria(getClass(), "se van a generar estos tipos de archivos:");
			for (TipoParametroEntradaPerfilesAplicativoEnum tipoParam : TipoParametroEntradaPerfilesAplicativoEnum.listarEstados()) {
				Traza.auditoria(getClass(), tipoParam.name());
			}
			return TipoParametroEntradaPerfilesAplicativoEnum.listarEstados();
		}

		Traza.auditoria(getClass(), "se van a generar estos tipos de archivos:");
		for (TipoParametroEntradaPerfilesAplicativoEnum tipoParam : listaParametros) {
			Traza.auditoria(getClass(), tipoParam.name());
		}
		return listaParametros;
	}

	/**
	 * Metodo para procesar el archivo de entrada
	 */
	public List<ReportePerfilesAuditoriaEntrada> procesarArchivoEntrada() throws NegocioExcepcion, ReportePerfilesExcepcion {

		List<ReportePerfilesAuditoriaEntrada> listaPerfiles = new ArrayList<ReportePerfilesAuditoriaEntrada>();

		try {

			File pathOrigen = ControlArchivo.getDirectorio(Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.reportePerfilesAuditoria.entrada"));

			FilenameFilter filtroArchivo = filtrarArchivosDeReporte();
			if (ControlArchivo.existeArchivosEnUnDirectorio(pathOrigen, filtroArchivo)) {

				List<File> archivosAProcesar = ControlArchivo.listaArchivosEnUnDirectorio(pathOrigen, filtroArchivo);

				// Recorro todos los archivos existentes en el directorio
				for (File file : archivosAProcesar) {

					String nombreArchivo = file.getName();

					Traza.auditoria(getClass(), "~");
					Traza.auditoria(getClass(), "Se arranca con el procesamiento del archivo: " + nombreArchivo);
					Traza.auditoria(getClass(), "~");

					// Lectura del archivo
					String[] registrosArchivo = ControlArchivo.leerArchivo(file.getAbsolutePath());

					listaPerfiles = procesarArchivo(registrosArchivo, nombreArchivo, pathOrigen.getPath());

				}
			} else {
				List<String> listaError = new ArrayList<String>();
				listaError.add(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.entrada.error.archivo.no.encontrado"));

				Traza.advertencia(getClass(), "No se ha encontrado ningún archivo con el formato SHIVA_INFO_USERS.txt");
				throw new ReportePerfilesExcepcion("", listaError, true);
			}

		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		return listaPerfiles;

	}

	/**
	 * Metodo para grabar el archivo procesado en la tabla SHV_ARC_ARCHIVO
	 * 
	 * @param nombreArchivo
	 * @param i
	 * @throws PersistenciaExcepcion
	 */
	private void insertarArcArchivo(ShvArcArchivo archivo) throws PersistenciaExcepcion {

		archivo.setProceso(Constantes.PROCESO_BATCH_REPORTE_PERFILES_AUDITORIA);

		archivo = reportePerfilesAuditoriaDao.insertarArcArchivo(archivo);

		Traza.auditoria(getClass(), "Se ha insertado el archivo " + archivo.getNombreArchivo() + " en la base de datos");

	}

	/**
	 * Metodo que se encarga de validar y procesar los registros del archivo
	 * 
	 * @param registroArchivo
	 * @param string
	 * @param sistema
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ReportePerfilesExcepcion
	 * @throws ShivaExcepcion
	 */
	public List<ReportePerfilesAuditoriaEntrada> procesarArchivo(String[] registroArchivo, String nombreArchivo, String path) throws NegocioExcepcion, ReportePerfilesExcepcion {

		List<ReportePerfilesAuditoriaEntrada> listaPerfiles = new ArrayList<ReportePerfilesAuditoriaEntrada>();
		boolean tieneErrores = false;
		List<String> listaErrores = new ArrayList<String>();
		String pathDestino;
		try {
			if (!Validaciones.isObjectNull(registroArchivo) && registroArchivo.length > 1) {
				for (int i = 1; i < registroArchivo.length; i++) {

					ReportePerfilesAuditoriaEntrada registro = new ReportePerfilesAuditoriaEntrada();

					String[] datos = registroArchivo[i].split("\\;");
					registro.setRegistroArchivo(registroArchivo[i]);
					registro.setNroRegistro(i);

					if (tieneCantidadDeColumnasCorrectas(registro, datos)) {
						registro.setSistema(datos[0]);
						registro.setAplicacion(datos[1]);
						registro.setValor(datos[2]);
						registro.setPerfil(datos[3]);
						registro.setUsuario(datos[4]);
						registro.setApellido(datos[5]);
						registro.setNombre(datos[6]);

						if (esValidoFormato(registro)) {
							listaPerfiles.add(registro);
						} else {
							tieneErrores = true;
							listaErrores.add(registro.getDescripcionError());
						}

					} else {
						tieneErrores = true;
						listaErrores.add(registro.getDescripcionError());
					}
				}

			} else {
				Traza.auditoria(getClass(), "El siguiente archivo se encuentra vacío: " + nombreArchivo);
				listaErrores.add(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.entrada.archivo.vacio"));
				tieneErrores = true;
			}
			if (tieneErrores) {

				// Muevo el archivo del path original a "Rechazado"
				Traza.auditoria(getClass(), "El archivo " + nombreArchivo + " se procesó con errores. Se moverá a la carpeta rechazado");
				pathDestino = path + "/rechazado/";
				ControlArchivo.moverArchivoSegunResultado(nombreArchivo, path, pathDestino);
				Traza.auditoria(getClass(), "Se ha movido el archivo " + nombreArchivo + " a la carpeta rechazado exitosamente");

				throw new ReportePerfilesExcepcion("", listaErrores, true);
			} else {
				// Muevo el archivo del path original a "Historico"
				Traza.auditoria(getClass(), "El archivo " + nombreArchivo + " se procesó con éxito. Se moverá a la carpeta historico");
				pathDestino = path + "/historico/";
				ControlArchivo.moverArchivoSegunResultado(nombreArchivo, path, pathDestino);

				Traza.auditoria(getClass(), "Se ha movido el archivo " + nombreArchivo + " a la carpeta historico exitosamente");

				// Guarda el archivo de entrada en la tabla ShvArcArchivo
				ShvArcArchivo archivo = new ShvArcArchivo();
				archivo.setNombreArchivo(nombreArchivo);
				archivo.setFechaRecepcion(new Date());
				archivo.setCantidadRegistros(Long.valueOf(registroArchivo.length));
				insertarArcArchivo(archivo);
			}
		} catch (PersistenciaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPerfiles;
	}

	/**
	 * Metodo que indica si tiene la cantidad de columnas correctas
	 * 
	 * @param registro
	 * @param datos
	 * @param posicionRTA
	 * @return
	 */
	public boolean tieneCantidadDeColumnasCorrectas(ReportePerfilesAuditoriaEntrada registro, String[] datos) {

		Traza.auditoria(getClass(), "~ Linea " + registro.getNroRegistro() + ":  Valida la cantidad de columnas...");

		boolean tieneCantidadDeColumnasCorrectas = true;
		int cantColumnas = datos.length;

		if ((cantColumnas % 7) != 0) {

			registro.setDescripcionError(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.cant.columnas"), String.valueOf(registro.getNroRegistro())));
			tieneCantidadDeColumnasCorrectas = false;

			Traza.auditoria(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.cant.columnas"), String.valueOf(registro.getNroRegistro())));

		}

		return tieneCantidadDeColumnasCorrectas;

	}

	/**
	 * Se valida que venga informado los campos
	 * 
	 * @param registro
	 * @param respuesta
	 * @return
	 */
	public boolean esValidoFormato(ReportePerfilesAuditoriaEntrada registro) {

		Traza.auditoria(getClass(), "~ Linea " + registro.getNroRegistro() + ": Valida el formato de los campos...");

		// Sistema
		if (Validaciones.isNullOrEmpty(registro.getSistema())) {
			Traza.auditoria(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.sistema.no.informado"), String.valueOf(registro.getNroRegistro())));

			registro.setDescripcionError(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.sistema.no.informado"), String.valueOf(registro.getNroRegistro())));

			return false;

		} else if (!Constantes.SHIVA_APLICACION.equalsIgnoreCase(registro.getSistema())) {
			Traza.auditoria(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.sistema.invalido"), String.valueOf(registro.getNroRegistro())));

			registro.setDescripcionError(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.sistema.invalido"), String.valueOf(registro.getNroRegistro())));

			return false;
		}
		// Aplicacion
		if (Validaciones.isNullOrEmpty(registro.getAplicacion())) {
			Traza.auditoria(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.aplicacion.no.informada"), String.valueOf(registro.getNroRegistro())));

			registro.setDescripcionError(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.aplicacion.no.informada"), String.valueOf(registro.getNroRegistro())));

			return false;

		} 

		// Valor
		if (Validaciones.isNullOrEmpty(registro.getValor())) {
			Traza.auditoria(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.valor.no.informado"), String.valueOf(registro.getNroRegistro())));

			registro.setDescripcionError(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.valor.no.informado"), String.valueOf(registro.getNroRegistro())));

			return false;

		} else if (!esValorValido(registro.getValor())) {
			Traza.auditoria(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.valor.invalido"), String.valueOf(registro.getNroRegistro())));

			registro.setDescripcionError(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.valor.invalido"), String.valueOf(registro.getNroRegistro())));

			return false;
		}

		// Perfil
		if (Validaciones.isNullOrEmpty(registro.getPerfil())) {
			Traza.auditoria(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.perfil.no.informado"), String.valueOf(registro.getNroRegistro())));

			registro.setDescripcionError(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.perfil.no.informado"), String.valueOf(registro.getNroRegistro())));

			return false;
		} else if (!esPerfilValido(registro.getPerfil())) {
			Traza.auditoria(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.perfil.invalido"), String.valueOf(registro.getNroRegistro())));

			registro.setDescripcionError(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.perfil.invalido"), String.valueOf(registro.getNroRegistro())));

			return false;
		}

		// Usuario
		if (Validaciones.isNullOrEmpty(registro.getUsuario())) {
			Traza.auditoria(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.usuario.no.informado"), String.valueOf(registro.getNroRegistro())));

			registro.setDescripcionError(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.usuario.no.informado"), String.valueOf(registro.getNroRegistro())));

			return false;

		}

		// Apellido
		if (Validaciones.isNullOrEmpty(registro.getApellido())) {
			Traza.auditoria(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.apellido.no.informado"), String.valueOf(registro.getNroRegistro())));

			registro.setDescripcionError(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.apellido.no.informado"), String.valueOf(registro.getNroRegistro())));

			return false;
		}

		// Nombre
		if (Validaciones.isNullOrEmpty(registro.getNombre())) {
			Traza.auditoria(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.nombre.no.informado"), String.valueOf(registro.getNroRegistro())));

			registro.setDescripcionError(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.error.registro.nombre.no.informado"), String.valueOf(registro.getNroRegistro())));

			return false;

		}

		return true;
	}

	private boolean esValorValido(String valor) {
		boolean esValido = false;
		String perfil;
		try {
			perfil = reportePerfilesAuditoriaDao.buscarValor(valor);

			if (!Validaciones.isNullOrEmpty(perfil)) {
				esValido = true;
			}
		} catch (PersistenciaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esValido;
	}

	private boolean esPerfilValido(String perfil) {
		boolean esValido = false;
		try {
			ShvSegPerfil segPerfil = reportePerfilesAuditoriaDao.buscarPerfil(perfil);
			if (!Validaciones.isObjectNull(segPerfil)) {
				esValido = true;
			}
		} catch (PersistenciaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esValido;
	}

	/**
	 * Metodo donde se envia mail con detalle del procesamiento
	 * 
	 * @param cuerpoo
	 * @param sistema
	 * @param estado
	 * @throws NegocioExcepcion
	 */
	private void enviarMailConDetalleDeProcesamiento(ReportePerfilesExcepcion e) throws NegocioExcepcion {
		try {

			Traza.auditoria(getClass(), "~ Comienza la lógica de envío de mail con detalle de procesamiento...");

			String destinatarios = parametroServicio.getValorTexto(Constantes.DESTINATARIO_MAIL_REPORTE_PERFILES_AUDITORIA);
			String destinatariosCC = parametroServicio.getValorTexto(Constantes.DESTINATARIO_COPIA_MAIL_REPORTE_PERFILES_AUDITORIA);

			// Asunto
			String asunto = Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.asunto");

			// Cuerpo
			StringBuffer cuerpoMail = new StringBuffer();

			if (!Validaciones.isObjectNull(e)) {
				cuerpoMail.append(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.cuerpo.no.ok"));

				if (e.isTieneErrorEnArchivo()) {
					cuerpoMail.append("en el archivo SHIVA_INFO_USERS.txt:" + "<br>");
				} else {
					cuerpoMail.append("en los parámetros de entrada:" + "<br>");
				}
				for (String error : e.getListaErrores()) {
					cuerpoMail.append(error);
					cuerpoMail.append("<br>");
				}

			} else {
				cuerpoMail.append(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.perfiles.auditoria.cuerpo.ok"));
			}

			// Si tengo destinatarios, armo mail y lo envío
			if (!Validaciones.isNullOrEmpty(destinatarios)) {

				Mail mailDetalleProcesamiento = new Mail(destinatarios, destinatariosCC, asunto, cuerpoMail);

				mailServicio.sendMail(mailDetalleProcesamiento);

			}

			Traza.auditoria(getClass(), "~ Fin de envío de mail.");
			Traza.auditoria(getClass(), "~");

		} catch (Exception e1) {
			throw new NegocioExcepcion(e1.getMessage(), e1);
		}
	}

	/**
	 * Crea el archivo SHIVA_PROFILE el cual contiene todos los perfiles existentes en shiva
	 */
	private ShvArcArchivo crearArchivoShivaProfile() {

		List<StringBuffer> listaDetalle = new ArrayList<StringBuffer>();
		ShvArcArchivo archivo = new ShvArcArchivo();
		try {
			Traza.auditoria(getClass(), "Comienza la creación del archivo SHIVA_PROFILE...");
			List<ShvSegPerfilAplicativo> listaPerfiles = reportePerfilesAuditoriaDao.buscarSegPerfilAplicativo();
			Collections.sort(listaPerfiles, new ComparatorOrdenShvSegPerfilAplicativo());

			for (ShvSegPerfilAplicativo perfil : listaPerfiles) {
				StringBuffer detalle = new StringBuffer();
				detalle.append(perfil.getIdPerfilAplicativo());
				detalle.append(Constantes.TAB);
				detalle.append(perfil.getDescripcion().toUpperCase());
				detalle.append(Constantes.TAB);
				detalle.append(Constantes.ESPANOL);
				detalle.append(Constantes.TAB);
				listaDetalle.add(detalle);
			}

			String nombreDeArchivo = TipoParametroEntradaPerfilesAplicativoEnum.SHIVA_PROFILE.getDescripcion();

			ControlArchivo.escribirArchivosUTF8(listaDetalle, Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.reportePerfilesAuditoria.salida"), nombreDeArchivo, null);

			Traza.auditoria(getClass(), "Se creo el siguiente archivo: " + nombreDeArchivo);

			archivo.setNombreArchivo(nombreDeArchivo);
			archivo.setCantidadRegistros(Long.valueOf(listaDetalle.size()));
			archivo.setFechaEnvio(new Date());

		} catch (ShivaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return archivo;
	}

	/**
	 * Crea el archivo SHIVA_ROLE el cual contiene el id_perfil, el nombre y el lenguaje
	 */
	private ShvArcArchivo crearArchivoShivaRole() {

		List<StringBuffer> listaDetalle = new ArrayList<StringBuffer>();
		ShvArcArchivo archivo = new ShvArcArchivo();
		try {
			Traza.auditoria(getClass(), "Comienza la creación del archivo SHIVA_ROLE...");
			List<ShvSegPerfil> listaPerfiles = reportePerfilesAuditoriaDao.buscarSegPerfil();
			Collections.sort(listaPerfiles, new ComparatorOrdenShvSegPerfil());

			for (ShvSegPerfil perfil : listaPerfiles) {
				StringBuffer detalle = new StringBuffer();
				detalle.append(perfil.getIdPerfil());
				detalle.append(Constantes.TAB);
				detalle.append(perfil.getNombre().toUpperCase());
				detalle.append(Constantes.TAB);
				detalle.append(Constantes.ESPANOL);
				detalle.append(Constantes.TAB);
				listaDetalle.add(detalle);
			}

			String nombreDeArchivo = TipoParametroEntradaPerfilesAplicativoEnum.SHIVA_ROLE.getDescripcion();

			ControlArchivo.escribirArchivosUTF8(listaDetalle, Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.reportePerfilesAuditoria.salida"), nombreDeArchivo, null);

			Traza.auditoria(getClass(), "Se creo el siguiente archivo: " + nombreDeArchivo);

			archivo.setNombreArchivo(nombreDeArchivo);
			archivo.setCantidadRegistros(Long.valueOf(listaDetalle.size()));
			archivo.setFechaEnvio(new Date());

		} catch (ShivaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return archivo;
	}

	/**
	 * Crea el archivo SHIVA_DEFAULT_USER el cual contiene el usuario, el nombre el apellido y el sistema
	 * @param listaPerfiles
	 */
	private ShvArcArchivo crearArchivoShivaDefaultUser(List<ReportePerfilesAuditoriaEntrada> listaPerfiles) {

		List<StringBuffer> listaDetalle = new ArrayList<StringBuffer>();
		ShvArcArchivo archivo = new ShvArcArchivo();
		try {
			Traza.auditoria(getClass(), "Comienza la creación del archivo SHIVA_DEFAULT_USER...");
			Collections.sort(listaPerfiles, new ComparatorOrdenReportePerfilesAuditoria());
			HashMap<String, ReportePerfilesAuditoriaEntrada> listaPerfil = new HashMap<String, ReportePerfilesAuditoriaEntrada>();
			for (ReportePerfilesAuditoriaEntrada perfil : listaPerfiles) {

				if (!listaPerfil.containsKey(perfil.getUsuario())) {

					StringBuffer detalle = new StringBuffer();
					detalle.append(perfil.getUsuario().toUpperCase());
					detalle.append(Constantes.TAB);
					detalle.append(perfil.getNombre());
					detalle.append(Constantes.TAB);
					detalle.append(perfil.getApellido());
					detalle.append(Constantes.TAB);
					detalle.append(Constantes.EMPTY_STRING);
					detalle.append(Constantes.TAB);
					detalle.append(Constantes.EMPTY_STRING);
					detalle.append(Constantes.TAB);
					detalle.append(Constantes.EMPTY_STRING);
					detalle.append(Constantes.TAB);
					detalle.append(perfil.getSistema().toUpperCase());
					detalle.append(Constantes.TAB);
					detalle.append(Constantes.EMPTY_STRING);
					detalle.append(Constantes.TAB);
					detalle.append(Constantes.WHITESPACE);
					detalle.append(Constantes.TAB);
					detalle.append(Constantes.WHITESPACE);
					detalle.append(Constantes.TAB);
					detalle.append(Constantes.EMPTY_STRING);
					detalle.append(Constantes.TAB);
					detalle.append(Constantes.EMPTY_STRING);
					detalle.append(Constantes.TAB);
					detalle.append(Constantes.EMPTY_STRING);
					listaDetalle.add(detalle);
					listaPerfil.put(perfil.getUsuario(), perfil);
				}
			}

			String nombreDeArchivo = TipoParametroEntradaPerfilesAplicativoEnum.SHIVA_DEFAULT_USER.getDescripcion();
			ControlArchivo.escribirArchivosUTF8(listaDetalle, Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.reportePerfilesAuditoria.salida"), nombreDeArchivo, null);

			Traza.auditoria(getClass(), "Se creo el siguiente archivo: " + nombreDeArchivo);

			archivo.setNombreArchivo(nombreDeArchivo);
			archivo.setCantidadRegistros(Long.valueOf(listaDetalle.size()));
			archivo.setFechaEnvio(new Date());

		} catch (ShivaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return archivo;
	}

	/**
	 * Crea el archivo con todas las acciones que existen
	 */
	private ShvArcArchivo crearArchivoShivaActions() {

		List<StringBuffer> listaDetalle = new ArrayList<StringBuffer>();
		ShvArcArchivo archivo = new ShvArcArchivo();
		try {
			Traza.auditoria(getClass(), "Comienza la creación del archivo SHIVA_USER_ACTION...");
			List<ResultadoBusquedaAcciones> listaAcciones = reportePerfilesAuditoriaDao.listarAcciones();
			if (!Validaciones.isObjectNull(listaAcciones)) {
				Collections.sort(listaAcciones, new ComparatorOrdenResultadoAcciones());

				for (ResultadoBusquedaAcciones accion : listaAcciones) {
					StringBuffer detalle = new StringBuffer();
					detalle.append(accion.getIdAccion());
					detalle.append(Constantes.TAB);
					detalle.append(accion.getDescripcionAccion());
					detalle.append(Constantes.TAB);
					detalle.append(Constantes.ESPANOL);
					listaDetalle.add(detalle);
				}

				String nombreDeArchivo = TipoParametroEntradaPerfilesAplicativoEnum.SHIVA_ACTIONS.getDescripcion();

				ControlArchivo.escribirArchivosUTF8(listaDetalle, Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.reportePerfilesAuditoria.salida"), nombreDeArchivo, null);

				Traza.auditoria(getClass(), "Se creo el siguiente archivo: " + nombreDeArchivo);

				archivo.setNombreArchivo(nombreDeArchivo);
				archivo.setCantidadRegistros(Long.valueOf(listaDetalle.size()));
				archivo.setFechaEnvio(new Date());

			}
		} catch (ShivaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return archivo;
	}

	/**
	 * Crea el archivo SHIVA_USER_ACTION que tiene las acciones por perfil aplicativo
	 */
	private ShvArcArchivo crearArchivoShivaUserAction(List<ReportePerfilesAuditoriaEntrada> listaPerfiles) {

		List<StringBuffer> listaDetalle = new ArrayList<StringBuffer>();
		ShvArcArchivo archivo = new ShvArcArchivo();
		List<ResultadoBusquedaAcciones> listaAccionesResultado = new ArrayList<ResultadoBusquedaAcciones>();
		try {
			Traza.auditoria(getClass(), "Comienza la creación del archivo SHIVA_USER_ACTION...");
			Collections.sort(listaPerfiles, new ComparatorOrdenReportePerfilesAuditoria());
			for (ReportePerfilesAuditoriaEntrada perfil : listaPerfiles) {
				String empresa = null;
				String segmento = null;
				String[] detallePerfil = perfil.getValor().split("_");
				TipoPerfilEnum tipoPerfil = TipoPerfilEnum.getEnumByNombreLdap(detallePerfil[2]);
				if (detallePerfil.length > 3) {
					empresa = detallePerfil[3];
					if (detallePerfil.length > 4) {
						segmento = detallePerfil[4];
					}
				}

				List<ResultadoBusquedaAcciones> listaAcciones = reportePerfilesAuditoriaDao.buscarAccionesPorPerfil(tipoPerfil, empresa, segmento);
				if (Validaciones.isCollectionNotEmpty(listaAcciones)) {
					for (ResultadoBusquedaAcciones accion : listaAcciones) {
						accion.setUsuario(perfil.getUsuario());
						listaAccionesResultado.add(accion);
					}

				}

			}
			Collections.sort(listaAccionesResultado, new ComparatorOrdenResultadoAcciones());
			for (ResultadoBusquedaAcciones accion : listaAccionesResultado) {
				StringBuffer detalle = new StringBuffer();
				detalle.append(accion.getUsuario().toUpperCase());
				detalle.append(Constantes.TAB);
				detalle.append(accion.getIdPerfil());
				detalle.append(Constantes.TAB);
				detalle.append(accion.getIdAccion());
				detalle.append(Constantes.TAB);
				detalle.append(Constantes.TAB);
				detalle.append(accion.getIdPerfilAplicativo());
				detalle.append(Constantes.TAB);
				listaDetalle.add(detalle);
			}
			String nombreDeArchivo = TipoParametroEntradaPerfilesAplicativoEnum.SHIVA_USER_ACTION.getDescripcion();

			ControlArchivo.escribirArchivosUTF8(listaDetalle, Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.reportePerfilesAuditoria.salida"), nombreDeArchivo, null);

			Traza.auditoria(getClass(), "Se creo el siguiente archivo: " + nombreDeArchivo);

			archivo.setNombreArchivo(nombreDeArchivo);
			archivo.setCantidadRegistros(Long.valueOf(listaDetalle.size()));
			archivo.setFechaEnvio(new Date());

		} catch (ShivaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return archivo;
	}

	/**
	 * Filtro del formato de archivos de reporte en un directorio. El formato es
	 * SHIVA_INFO_USERS.txt
	 * 
	 * @param filtro
	 * @return FilenameFilter
	 */
	private static FilenameFilter filtrarArchivosDeReporte() {

		return new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (!Validaciones.isObjectNull(name) && name.equals(Constantes.SHIVA_INFO_USERS)) {
					return true;
				}
				return false;
			}
		};
	}
}
