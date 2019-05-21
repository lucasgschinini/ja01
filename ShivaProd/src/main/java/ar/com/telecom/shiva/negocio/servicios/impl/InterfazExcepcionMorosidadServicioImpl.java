package ar.com.telecom.shiva.negocio.servicios.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.ExcepcionMorosidadFechaEjecucionEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SubTipoExcepcionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.ShvParamCalendarioWrapper;
import ar.com.telecom.shiva.negocio.servicios.IInterfazExcepcionMorosidadServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroCalendarioServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvSopExcepcionMorosidad;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ExcepcionMorosidadFiltro;

public class InterfazExcepcionMorosidadServicioImpl implements IInterfazExcepcionMorosidadServicio {
	
	@Autowired	
	private IVistaSoporteServicio vistaSoporteServicio;
	@Autowired
	private IParametroCalendarioServicio parametroCalendarioServicio;
	@Autowired 
	private ILdapServicio ldapServicio;

	
	private static final int CLIENTE_LENGTH = 10;
	private static final int CUENTA_LENGTH = 13;
	private static final int ACUERDO_FACTURACION_LENGTH = 10;

	@SuppressWarnings("unused")
	private static final int SISTEMA_ORIGEN_LENGTH = 3;
	@SuppressWarnings("unused")
	private static final int TIPO_DOCUMENTO_LENGTH = 10;
	private static final int NUMERO_LEGAL_LENGTH = 13;
	private static final int NUMERO_REFERENCIA_LENGTH = 15;
	@SuppressWarnings("unused")
	private static final int TIPO_REMANENTE_LENGTH = 2;
	@SuppressWarnings("unused")
	private static final int FECHA_REMANENTE_LENGTH = 10;
	private static final int ID_VALOR_LENGTH = 10;
	private static final int SOCIEDAD_LENGTH = 4;
	private static final int PROVEEDOR_LENGTH = 10;
	private static final int CUIT_LENGTH = 13;
	private static final int EJERCICIO_FISCAL_SAP_LENGTH = 4;
	private static final int NUMERO_DE_DOCUMENTO_SAP_LENGTH = 10;
	private static final int POSICION_DE_DOCUMENTO_SAP_LENGTH = 3;
	@SuppressWarnings("unused")
	private static final int MONEDA_ORIGEN_DOCUMENTO = 3;
	@SuppressWarnings("unused")
	private static final int IMPORTE_EXCEPTUADO_EN_MONEDA_ORIGEN_DOCUMENTO_LENGTH = 18; // 13 de la parte entera + 1 de la coma + 4 de las decimales
	@SuppressWarnings("unused")
	private static final int REFERENCIA_DE_LA_EXCEPCION_LENGTH = 7;
	@SuppressWarnings("unused")
	private static final int ANALISTA_USUARIO_LENGTH = 9;
	@SuppressWarnings("unused")
	private static final int ANALISTA_NOMBRE_Y_APELLIDO_LENGTH = 50;
	@SuppressWarnings("unused")
	private static final int TIPO_DE_EXCEPCION_LENGTH = 3;
	@SuppressWarnings("unused")
	private static final int SUBTIPO_DE_EXCEPCION_LENGTH = 3;
	@SuppressWarnings("unused")
	private static final int FECHA_DE_CREACION_LENGTH = 10;
	@SuppressWarnings("unused")
	private static final int FECHA_DESDE_LENGTH = 10;
	@SuppressWarnings("unused")
	private static final int FECHA_HASTA_LENGTH = 10;
	
	
	
	
	@Override
	public void generarArchivoExcepcionMorosidad(String fecha) throws NegocioExcepcion {
		Date now = new Date();
		// Cargar el filtro
		if (Validaciones.isNullEmptyOrDash(fecha)) {
			throw new NegocioExcepcion("La fecha no puede ser nula");
		} else if (!Validaciones.validarFechaConParse(fecha)) {
			throw new NegocioExcepcion("El formato de la fecha es erroneo");
		}
		ExcepcionMorosidadFiltro excepcionMorosidadFiltro = new ExcepcionMorosidadFiltro();
		excepcionMorosidadFiltro.setFechaDesde(fecha + " 00:00:00");
		excepcionMorosidadFiltro.setFechaHasta(fecha + " 23:59:59");
		Traza.auditoria(getClass(), "Se buscan registros con modificacion de estado entre " + excepcionMorosidadFiltro.getFechaDesde() + " hasta " +  excepcionMorosidadFiltro.getFechaHasta());
		// Buscar en la base
		List<ShvSopExcepcionMorosidad> listaShvSopExcepcionMorosidad = vistaSoporteServicio.obtenerRegistrosExcepcionMorosidad(excepcionMorosidadFiltro);
		Traza.auditoria(getClass(), "Se obtuvieron " + listaShvSopExcepcionMorosidad.size() + " registros");
		
		if (!listaShvSopExcepcionMorosidad.isEmpty()) {
			List<StringBuffer> listaInterfazExcepcionMorosidad = new ArrayList<StringBuffer>();
			Set<ShvParamCalendarioWrapper> setShvParamCalendario = new HashSet<ShvParamCalendarioWrapper>();
			Map<String, UsuarioLdapDto> setUsuarioLdapDto = new HashMap<String, UsuarioLdapDto>();
			Date fechaDate = null;
			try {
				fechaDate = Utilidad.deserializeAndFormatDate(excepcionMorosidadFiltro.getFechaDesde(), Utilidad.DATE_TIME_FULL_FORMAT);
			} catch (ParseException e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
			setShvParamCalendario = parametroCalendarioServicio.buscaParamtroCalendarioApartirDe(
				fechaDate
			);
			if (setShvParamCalendario.isEmpty()) {
				Traza.auditoria(getClass(), "No se han encontrado, en ShvParamCalendario, fechas de feriados apartir de la fecha a informar!!!");
			}
			long cantidadRegistros = 0;
			// Procesar los registros de la lista
			for (ShvSopExcepcionMorosidad shvSopExcepcionMorosidad : listaShvSopExcepcionMorosidad) {
				listaInterfazExcepcionMorosidad.add(
					this.getValorVistaStringBuffer(
						shvSopExcepcionMorosidad,
						setShvParamCalendario,
						setUsuarioLdapDto,
						fecha,
						fechaDate
				));
				cantidadRegistros++;
			}
			// Armar archivo de Excepciones de morosidad
			this.crearReporteMorosidad(listaInterfazExcepcionMorosidad, fecha, now, cantidadRegistros);
			// grabar archivo	
		}
		
		
	}
	public StringBuffer getValorVistaStringBuffer(
		ShvSopExcepcionMorosidad shvSopExcepcionMorosidad,
		Set<ShvParamCalendarioWrapper> setShvParamCalendario,
		Map<String, UsuarioLdapDto> setUsuarioLdap,
		String fecha,
		Date fechaDate
	) throws NegocioExcepcion {
		StringBuffer str = new StringBuffer();

//Cliente
		str.append(
			Validaciones.isNullEmptyOrDash(shvSopExcepcionMorosidad.getCliente()) ? "" : Utilidad.rellenarCerosIzquierda(shvSopExcepcionMorosidad.getCliente(), CLIENTE_LENGTH)
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Cuenta
		str.append(
			Validaciones.isNullEmptyOrDash(shvSopExcepcionMorosidad.getCuenta()) ? "" : Utilidad.rellenarCerosIzquierda(shvSopExcepcionMorosidad.getCuenta(), CUENTA_LENGTH)
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Acuerdo de facturación
		str.append(
			Validaciones.isNullEmptyOrDash(shvSopExcepcionMorosidad.getAcuerdoFacturacion()) ? "" : Utilidad.rellenarCerosIzquierda(shvSopExcepcionMorosidad.getAcuerdoFacturacion(), ACUERDO_FACTURACION_LENGTH)
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Sistema Origen
		str.append(
			SistemaEnum.SAP.equals(shvSopExcepcionMorosidad.getSistemaOrigen()) ? shvSopExcepcionMorosidad.getSistemaOrigen().getDescripcionCorta().toUpperCase() : shvSopExcepcionMorosidad.getSistemaOrigen().getDescripcionCorta()
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Tipo documenyo
		str.append(
			shvSopExcepcionMorosidad.getTipoDocumento().toUpperCase()
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Número legal de comprobante
		str.append(
			Validaciones.isNullEmptyOrDash(shvSopExcepcionMorosidad.getNumeroLegalComprobante()) ? "" : Utilidad.rellenarCerosIzquierda(shvSopExcepcionMorosidad.getNumeroLegalComprobante(), NUMERO_LEGAL_LENGTH)
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Número Referencia
		str.append(
			Validaciones.isNullEmptyOrDash(shvSopExcepcionMorosidad.getNumeroReferencia()) ? "" : Utilidad.rellenarCerosIzquierda(shvSopExcepcionMorosidad.getNumeroReferencia(), NUMERO_REFERENCIA_LENGTH)
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Numero Remanente
		if (!Validaciones.isObjectNull(shvSopExcepcionMorosidad.getTipoRemanente())) {
			str.append(TipoRemanenteEnum.getEnumByCodigo(shvSopExcepcionMorosidad.getTipoRemanente()).nombreCorto());
		}
		str.append(Constantes.SEPARADOR_PIPE);
//Fecha de remanente
		str.append(	
			Validaciones.isObjectNull(shvSopExcepcionMorosidad.getFechaRemanente()) ? "" : Utilidad.formatDatePicker(shvSopExcepcionMorosidad.getFechaRemanente())
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Id Valor
		str.append(
			Validaciones.isNullEmptyOrDash(shvSopExcepcionMorosidad.getIdValor()) ? "" : Utilidad.rellenarCerosIzquierda(shvSopExcepcionMorosidad.getIdValor(), ID_VALOR_LENGTH)
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Sociaedad
		str.append(
			Validaciones.isNullEmptyOrDash(shvSopExcepcionMorosidad.getSociedad()) ? "" : Utilidad.rellenarCerosIzquierda(shvSopExcepcionMorosidad.getSociedad(), SOCIEDAD_LENGTH)
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Proveedor
		str.append(
			Validaciones.isNullEmptyOrDash(shvSopExcepcionMorosidad.getProveedor()) ? "" : Utilidad.rellenarCerosIzquierda(shvSopExcepcionMorosidad.getProveedor(), PROVEEDOR_LENGTH)
		);
		str.append(Constantes.SEPARADOR_PIPE);
//CUIT
		str.append(
			Validaciones.isNullEmptyOrDash(shvSopExcepcionMorosidad.getCuit()) ? "" : Utilidad.formatearCuit(Utilidad.rellenarCerosIzquierda(shvSopExcepcionMorosidad.getCuit(), CUIT_LENGTH-2))
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Ejercicio fiscal SAP
		str.append(
			Validaciones.isObjectNull(shvSopExcepcionMorosidad.getEjercicioFiscalSAP()) ? "" : Utilidad.rellenarCerosIzquierda(shvSopExcepcionMorosidad.getEjercicioFiscalSAP().toString(), EJERCICIO_FISCAL_SAP_LENGTH)
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Número de documento SAP
		str.append(
			Validaciones.isNullEmptyOrDash(shvSopExcepcionMorosidad.getNumeroDocumentoSAP()) ? "" : Utilidad.rellenarCerosIzquierda(shvSopExcepcionMorosidad.getNumeroDocumentoSAP(), NUMERO_DE_DOCUMENTO_SAP_LENGTH)
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Posición de documento SAP
		str.append(
			Validaciones.isObjectNull(shvSopExcepcionMorosidad.getPosicionDocumentoSAP()) ? "" : Utilidad.rellenarCerosIzquierda(shvSopExcepcionMorosidad.getPosicionDocumentoSAP().toString(), POSICION_DE_DOCUMENTO_SAP_LENGTH)
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Moneda origen documento
		str.append(
			Validaciones.isObjectNull(shvSopExcepcionMorosidad.getMonedaOrigenDocumento()) ? "" : shvSopExcepcionMorosidad.getMonedaOrigenDocumento().name()
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Importe exceptuado en moneda origen documento
		str.append(
			Validaciones.isObjectNull(shvSopExcepcionMorosidad.getImpExcepMonedaOrigenDoc()) ? "" : Utilidad.formatDecimales(shvSopExcepcionMorosidad.getImpExcepMonedaOrigenDoc(), 4)
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Referencia de la excepción
		str.append(
			Validaciones.isObjectNull(shvSopExcepcionMorosidad.getReferencia_excepcion()) ? "" : shvSopExcepcionMorosidad.getReferencia_excepcion()
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Analista (usuario)
		if (Validaciones.isObjectNull(shvSopExcepcionMorosidad.getIdAnalista())) {
			str.append(Constantes.SEPARADOR_PIPE);
		} else {
			str.append(shvSopExcepcionMorosidad.getIdAnalista());
			str.append(Constantes.SEPARADOR_PIPE);
			UsuarioLdapDto usuarioLdap = setUsuarioLdap.get(shvSopExcepcionMorosidad.getIdAnalista());
			if (Validaciones.isObjectNull(usuarioLdap)) {
				usuarioLdap = ldapServicio.buscarUsuarioPorUid(shvSopExcepcionMorosidad.getIdAnalista());
				setUsuarioLdap.put(shvSopExcepcionMorosidad.getIdAnalista(), usuarioLdap);
			}
			str.append(usuarioLdap.getNombreCompleto());
		}
		str.append(Constantes.SEPARADOR_PIPE);
// Tipo de excepción
		str.append(
			shvSopExcepcionMorosidad.getTipoExcepcion()
		);
		str.append(Constantes.SEPARADOR_PIPE);
// Subtipo de excepción
		str.append(
			SubTipoExcepcionEnum.getEnumByEstadoCobro(shvSopExcepcionMorosidad.getEstadoCobro())
		);
		str.append(Constantes.SEPARADOR_PIPE);
// Fecha de creación
		str.append(
			Validaciones.isObjectNull(shvSopExcepcionMorosidad.getFechaCreacion()) ? "" : Utilidad.formatDatePicker(shvSopExcepcionMorosidad.getFechaCreacion())
		);
		str.append(Constantes.SEPARADOR_PIPE);
//Fecha "desde"
		Date fechaDesde = null;
		if (ExcepcionMorosidadFechaEjecucionEnum.FECHA_DERIVACION.equals(shvSopExcepcionMorosidad.getFechaDesdeEnum())) {
			fechaDesde = shvSopExcepcionMorosidad.getFechaFerivacion();
		} else {
			fechaDesde = shvSopExcepcionMorosidad.getFechaVencimiento();
		}
		str.append(
			Utilidad.formatDatePicker(fechaDesde)
		);
		str.append(Constantes.SEPARADOR_PIPE);
// Fecha "hasta"
		Date date = parametroCalendarioServicio.calcularFechaHasta(fechaDesde, SiNoEnum.SI.equals(shvSopExcepcionMorosidad.getDiasCorridosSinFeriados()), shvSopExcepcionMorosidad.getDiasDuracionExcepcion(), setShvParamCalendario);
		str.append(
			Validaciones.isObjectNull(date) ? "" : Utilidad.formatDatePicker(date)
		);
		return str;
	}
	public void crearReporteMorosidad(List<StringBuffer> listaMorosidad, String fecha, Date fechaRun, long cantRegistros) throws NegocioExcepcion {
		try {
			Date fechaReporte = null;
			try {
				fechaReporte = Utilidad.parseDatePickerString(fecha);
			} catch (ParseException e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
			StringBuilder str = new StringBuilder("00|");
			str.append(cantRegistros);
			Traza.auditoria(getClass(), "Se crea un archivo con " + cantRegistros + " registros");
			ControlArchivo.escribirArchivos(
				listaMorosidad,
				Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.excepcionesMorosidad"),
				Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("procesoBatch.excepcionMorosidad.nombre.archivo"),
					"SHIVA",
					Utilidad.formatDateAAAAMMDD(fechaReporte),
					Utilidad.formatDateAAAAMMDD(fechaRun),
					Utilidad.formatDateHHMMSSTH3(fechaRun),
					"txt"
				),
				str.toString()
			);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	public IVistaSoporteServicio getVistaSoporteServicio() {
		return vistaSoporteServicio;
	}

	public void setVistaSoporteServicio(IVistaSoporteServicio vistaSoporteServicio) {
		this.vistaSoporteServicio = vistaSoporteServicio;
	}
	


}
