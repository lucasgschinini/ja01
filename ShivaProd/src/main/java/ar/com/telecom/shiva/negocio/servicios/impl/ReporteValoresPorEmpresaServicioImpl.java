package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.ControlMemoriaCPU;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IReporteValoresPorEmpresaServicio;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteHistoricoDeValores;
import ar.com.telecom.shiva.persistencia.dao.IReporteValoresPorEmpresaDao;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class ReporteValoresPorEmpresaServicioImpl extends Servicio implements IReporteValoresPorEmpresaServicio {
	
	@Autowired IReporteValoresPorEmpresaDao reporteValoresPorEmpresaDao;
	@Autowired ILdapServicio ldapServicio;
	
	/*
	 *Autor: Fernando Nicolas Quispe
	 *		 U591368
	 * */
	public void generarArchivoReporteValoresPorEmpresa(String fechaHasta) throws NegocioExcepcion{
		try {
			ControlVariablesSingleton.permitirTraceoSQL();
			IParametroServicio parametroServicio = (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Traza.auditoria(getClass(), "Comienzo del metodo generarArchivoReporteValoresPorEmpresa");
			List<StringBuffer> listaTxtReporteValoresPorEmpresa = new ArrayList<StringBuffer>();
			listaTxtReporteValoresPorEmpresa.add(crearTituloArchivoReporteValoresPorEmpresa());
			Traza.auditoria(getClass(), "Se realiza la consulta de la cantidad de registros por pagina");
			Long nRegXPag =  Long.valueOf(parametroServicio.getValorNumerico("reporteValoresXEmpresa.cantidadRegistrosXPagina").toString());
			Traza.auditoria(getClass(), "Se realiza la consulta de la cantidad de registros totales");
			Long cantidadEnBD = reporteValoresPorEmpresaDao.cantidadRegistros();
			List<VistaSoporteHistoricoDeValores> pagina = new ArrayList<VistaSoporteHistoricoDeValores>();
			Long nPagina = new Long(1);
			Long cantidadTotal = new Long(0);
			
			do{ 
				System.gc();
				Traza.auditoria(getClass(), "Memoria previa a la consulta: "+ControlMemoriaCPU.getInformacionGeneralSistema());
				pagina = reporteValoresPorEmpresaDao.buscarValores(nRegXPag, nPagina);
				nPagina++;
				
				for (VistaSoporteHistoricoDeValores valor : pagina) {
					listaTxtReporteValoresPorEmpresa.add(crearDetalleArchivoReporteValoresPorEmpresaStringBuffer(valor));
				}
				Traza.auditoria(getClass(), "Memoria posterior a la consulta: "+ControlMemoriaCPU.getInformacionGeneralSistema());
				cantidadTotal += pagina.size();
				
			}while (cantidadTotal < cantidadEnBD);
			Traza.auditoria(getClass(), "Se obtuvieron " +cantidadTotal.toString() + " valores para informar el Reporte de Valores por Empresa");
			
			
			crearArchivoReporteValoresPorEmpresa(listaTxtReporteValoresPorEmpresa, fechaHasta);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	
	/**
	 * Generacion del Archivo de Reporte de Valores Por Empresa
	 *Autor: Fernando Nicolas Quispe
	 *		 U591368
	 * */
	public void crearArchivoReporteValoresPorEmpresa(List<StringBuffer> listaArchivoReporteValoresPorEmpresa, String fechaHasta)
			throws NegocioExcepcion {
		try {

			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
			
			Date fechaDelDia = null;
			try {

				fechaDelDia = formatoDelTexto.parse(fechaHasta);

			} catch (ParseException e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
																			                                                            
			String nombreDeArchivo = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("procesoBatch.reporteValoresPorEmpresa.nombre.archivo"),
					"SHIVA",
					"TA",
					Utilidad.formatDateAAAAMMDD(fechaDelDia),
					"txt");
			
			ControlArchivo.escribirArchivos(
					listaArchivoReporteValoresPorEmpresa,
					Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.reporteValoresPorEmpresa"),
					nombreDeArchivo,
					""
			);
			
			Traza.auditoria(getClass(), "Se creo el siguiente archivo: " + nombreDeArchivo);
				
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	

	/**
	 * Formatea el detalle del Reporte de Valores Por Empresa	
	 * @param valor
	 * @return
	 * @throws NegocioExcepcion
	 */
	public StringBuffer crearDetalleArchivoReporteValoresPorEmpresaStringBuffer (VistaSoporteHistoricoDeValores valor) throws NegocioExcepcion {
		
		String nombreCompleto = "";
		String switchAplicaEmpresa = "";
		StringBuffer detalle = new StringBuffer();
		
		Traza.auditoria(getClass(), "Se esta procesando el Id Valor :" + valor.getIdValor());
		
		if(!Validaciones.isNullOrEmpty(valor.getAplicaTA())){
			switchAplicaEmpresa=valor.getAplicaTA();
		} else {
			switchAplicaEmpresa="Si";
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(switchAplicaEmpresa) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isNullOrEmpty(valor.getAplicaTP())){
			switchAplicaEmpresa=valor.getAplicaTP();
		} else {
			switchAplicaEmpresa="No";
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(switchAplicaEmpresa) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isNullOrEmpty(valor.getAplicaCV())){
			switchAplicaEmpresa=valor.getAplicaCV();
		} else {
			switchAplicaEmpresa="No";
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(switchAplicaEmpresa) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isNullOrEmpty(valor.getAplicaFT())){
			switchAplicaEmpresa=valor.getAplicaFT();
		} else {
			switchAplicaEmpresa="No";
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(switchAplicaEmpresa) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isNullOrEmpty(valor.getAplicaNX())){
			switchAplicaEmpresa=valor.getAplicaNX();
		} else {
			switchAplicaEmpresa="No";
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(switchAplicaEmpresa) + Constantes.SEPARADOR_PIPE);
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getEmpresa()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getSegmento()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getHolding()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getCuit()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getIdClienteLegado()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getRazonSocialClienteAgrupador()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDatePicker(valor.getFechaValor())) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getTipoValor()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getReferenciaValor()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valor.getImporte(), false, false, 2)) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valor.getSaldoDisponible(), false, false, 2)) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getEstadoValor()) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isNullOrEmpty(valor.getIdAnalista())){
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valor.getIdAnalista());
			nombreCompleto = (usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(nombreCompleto) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isNullOrEmpty(valor.getIdCopropietario())){
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valor.getIdCopropietario());
			nombreCompleto = (usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		} else {
			nombreCompleto = "";
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(nombreCompleto) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isNullOrEmpty(valor.getUsuarioAutorizacion())){
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valor.getUsuarioAutorizacion());
			nombreCompleto = (usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		} else {
			nombreCompleto = "";
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(nombreCompleto) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isNullOrEmpty(valor.getEjecutivo())){
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valor.getEjecutivo());
			nombreCompleto = (usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		} else {
			nombreCompleto = "";
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(nombreCompleto) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isNullOrEmpty(valor.getAsistente())){
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valor.getAsistente());
			nombreCompleto = (usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		} else {
			nombreCompleto = "";
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(nombreCompleto) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isNullOrEmpty(valor.getIdAnalistaTeamComercial())){
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valor.getIdAnalistaTeamComercial());
			nombreCompleto = (usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		} else {
			nombreCompleto = "";
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(nombreCompleto) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isNullOrEmpty(valor.getIdSupervisorTeamComercial())){
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valor.getIdSupervisorTeamComercial());
			nombreCompleto = (usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		} else {
			nombreCompleto = "";
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(nombreCompleto) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isNullOrEmpty(valor.getIdGerenteRegionalTeamComercial())){
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valor.getIdGerenteRegionalTeamComercial());
			nombreCompleto = (usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		} else {
			nombreCompleto = "";
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(nombreCompleto) + Constantes.SEPARADOR_PIPE);
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getIdValor()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getOrigen()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getBancoDeposito()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getIdAcuerdo()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getNroBoleta()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getDescripcionBancoOrigen()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getDescripcionTipoRetencion()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getProvinciaRetencion()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getNroCuitRetencion()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getCodigoOrganismo()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getNroRecibo()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getIdConstanciaRecepcion()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getOperacionAsociada()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getFacturaRelacionada()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getDocumentacionOrigRecibida()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getMotivo()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getValorPadre()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getNumeroDocumentoContable()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getMotivoSuspension()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getIdLegajoChequeRechazado()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDatePicker(valor.getFechaNotificacionRechazo())) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDatePicker(valor.getFechaRechazo())) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getArchivoDeValoresAConciliar()) + Constantes.SEPARADOR_PIPE);
		
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fechaAlta = null;
		try {

			fechaAlta = formatoDelTexto.parse(valor.getFechaAlta());

		} catch (ParseException e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDatePicker(fechaAlta)) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDatePicker(valor.getFechaIngresoRecibo())) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getFechaEmision()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDatePicker(valor.getFechaVencimiento())) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDatePicker(valor.getFechaTransferencia())) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDatePicker(valor.getFechaDeposito())) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDatePicker(valor.getFechaDisponible())) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDatePicker(valor.getFechaUltimoEstado())) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getBdImpresa()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getBdEnviadaMail()) + Constantes.SEPARADOR_PIPE);
		
		String observaciones = "";
		
		if (!Validaciones.isNullOrEmpty(valor.getObservaciones())){
			
			observaciones = valor.getObservaciones().replaceAll(Constantes.CARRIAGE_RETURN_R,Constantes.WHITESPACE).replaceAll(Constantes.RETORNO_UNIX, Constantes.WHITESPACE);
			
			if (observaciones.length() > 250){
				observaciones = observaciones.substring(0,250);
			}
			
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(observaciones) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getIdUnidadNegocio()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getDescripcionUnidadNegocio()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getDescripcionHolding()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getIdAgenciaNegocio()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(valor.getDescripcionAgenciaNegocio()) + Constantes.SEPARADOR_PIPE);		
		
		return detalle;		
	}
	
	
	/**
	 * Formatea el titulo del Reporte de Valores Por Empresa
	 *Autor: Juan Villagra
	 *		 U525214
	 * */
	public StringBuffer crearTituloArchivoReporteValoresPorEmpresa () throws NegocioExcepcion {

		StringBuffer titulo = new StringBuffer();
		
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_APLICATA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_APLICATP + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_APLICACV + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_APLICAFT + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_APLICANX + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_EMPRESA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_SEGMENTO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_HOLDING + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_CUIT + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_CLIENTELEGADO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_RAZONSOCIAL + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_FECHAVALOR + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_TIPODEVALOR + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_REFERENCIAVALOR + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_IMPORTE + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_SALDODISPONIBLE + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_ESTADOVALOR + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_ANALISTA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_COPROPIETARIO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_USUARIOAUTORIZACION + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_EJECUTIVOTEAMCOMERCIAL + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_ASISTENTETEAMCOMERCIAL + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_ANALISTATEAMCOMERCIAL + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_SUPERVISORTEAMCOMERCIAL + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_GERENTEREGIONALTEAMCOMERCIAL + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_IDVALOR + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_ORIGEN + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_BANCODEPOSITO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_NROACUERDO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_NROBOLETA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_DESCRIPCIONBANCOORIGEN + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_DESCRIPCIONTIPORETENCION + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_PROVINCIARETENCION + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_NROCUITRETENCION + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_CODIGOORGANISMO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_NRORECIBO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_NROCONSTANCIARECEPCION + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_OPERACIONASOCIADA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_FACTURARELACIONADA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_DOCUMENTACIONORIGINALRECIBIDA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_MOTIVO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_IDVALORRELACIONADO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_NRODOCUMENTOCONTABLE + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_MOTIVOSUSPENSION + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_LEGADOCHEQUERECHAZADO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_FECHANOTIFICACIONRECHAZO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_FECHARECHAZO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_ARCHIVOVALORESACONCILIAR + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_FECHAALTA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_FECHAINGRESORECIBO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_FECHAEMISION + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_FECHAVENCIMIENTO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_FECHATRANSFERENCIA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_FECHADEPOSITO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_FECHADISPONIBLE + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_FECHAULTIMOESTADO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_BDIMPRESA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_BDENVIADAMAIL + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_OBSERVACIONES + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_UNIDADNEGOCIO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_DESCRIPCIONUNIDADNEGOCIO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_DESCRIPCIONHOLDING + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_AGENCIANEGOCIO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_VALORES_POR_EMPRESA_TITULO_DESCRIPCIONAGENCIANEGOCIO + Constantes.SEPARADOR_PIPE);

		return titulo;
	}


	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Long crear(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void anular(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}

	
}
