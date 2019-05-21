package ar.com.telecom.shiva.negocio.servicios.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IReporteRegistrosAVCPendienteConciliarServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteRegistrosAvcSinConciliar;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteRegistrosAvcSinConciliarFiltro;


public class ReporteRegistrosAVCPendienteConciliarServicioImpl extends Servicio implements IReporteRegistrosAVCPendienteConciliarServicio {


	@Autowired 
	ILdapServicio ldapServicio;
	@Autowired
	private IVistaSoporteServicio vistaSoporteServicio;
	
	public ReporteRegistrosAVCPendienteConciliarServicioImpl() {
		
	}

	@Override
	public void generarArchivoReporteAvcPendienteConciliar(String fechaHasta) throws NegocioExcepcion {
		
			try {
				
				Traza.auditoria(getClass(), "Comienzo del metodo generarArchivoReporteAvcPendienteConciliar");
				
				VistaSoporteRegistrosAvcSinConciliarFiltro filtro = new VistaSoporteRegistrosAvcSinConciliarFiltro();
				filtro.setFechaHasta(fechaHasta);
				List<VistaSoporteRegistrosAvcSinConciliar> listaRegistrosAvc = vistaSoporteServicio.listarRegistrosAVCSinConciliar(filtro);
				
				Traza.auditoria(getClass(), "Se obtuvieron " + listaRegistrosAvc.size() + " registros para informar el Reporte de Registros avc pendiente de conciliar");
				
				List<StringBuffer> listaTxtReporte = new ArrayList<StringBuffer>();
				
				listaTxtReporte.add(crearTituloArchivoReporteAVCPendientesConciliar());
				
				for (VistaSoporteRegistrosAvcSinConciliar reg : listaRegistrosAvc) {
					
					listaTxtReporte.add(crearDetalleArchivoReporteAVCPendienteConciliarStringBuffer(reg));
				}
				
				crearArchivoReporteRegistrosAvcPendienteConciliar(listaTxtReporte, fechaHasta);
				
			} catch (NegocioExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		}
	

	private void crearArchivoReporteRegistrosAvcPendienteConciliar(List<StringBuffer> listaTxtReporte, String fechaHasta) throws NegocioExcepcion {
		
		try {

			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
			
			Date fechaDelDia = null;
			try {

				fechaDelDia = formatoDelTexto.parse(fechaHasta);

			} catch (ParseException e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
																			                                                            
			String nombreDeArchivo = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("procesoBatch.registro.avc.pendiente.conciliar.nombre.archivo"),
					"SHIVA",
					Utilidad.formatDateAAAAMMDD(fechaDelDia),
					"txt");
			
			ControlArchivo.escribirArchivosCP1252(
					listaTxtReporte,
					Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.reporteRegistrosAvcPendienteConciliar"),
					nombreDeArchivo,
					""
			);
			
			Traza.auditoria(getClass(), "Se creo el siguiente archivo: " + nombreDeArchivo);
				
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
	}

	private StringBuffer crearDetalleArchivoReporteAVCPendienteConciliarStringBuffer(VistaSoporteRegistrosAvcSinConciliar reg) throws LdapExcepcion {
		
		StringBuffer detalle = new StringBuffer();
		
		Traza.auditoria(getClass(), "Se esta procesando el Registro avc :" + reg.getIdRegistro());

		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getCodigoCliente()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getRazonSocial()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getFechaAltaFormateada()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getFechaDepositoFormateada()) + Constantes.SEPARADOR_PIPE);
		String importe = null;
		if (!Validaciones.isObjectNull(reg.getImporte())) {
			importe = Utilidad.formatCurrency(reg.getImporte(), 2);
		}
		detalle.append(Utilidad.generarSalidaConValorOVacio(importe) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getEstadoFormateado()) + Constantes.SEPARADOR_PIPE);
		
		if(!Validaciones.isObjectNull(reg.getAnalistaTeamComercial())){
			UsuarioLdapDto usuariosLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(reg.getAnalistaTeamComercial());
			if (!Validaciones.isObjectNull(usuariosLdap)){
				if(!Validaciones.isNullOrEmpty(usuariosLdap.getNombreCompleto())){
					detalle.append(Utilidad.generarSalidaConValorOVacio(usuariosLdap.getNombreCompleto()) + Constantes.SEPARADOR_PIPE);
				}else {
					detalle.append(Utilidad.generarSalidaConValorOVacio("" + Constantes.SEPARADOR_PIPE));
				}
			}else {
				detalle.append(Utilidad.generarSalidaConValorOVacio("" + Constantes.SEPARADOR_PIPE));
			}
		} else {
			detalle.append(Utilidad.generarSalidaConValorOVacio("" + Constantes.SEPARADOR_PIPE));
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getTipoValorFormateado()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getNumeroReferencia()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getNumeroBoleta()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getCuitFormateado()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getCodigoOrganismo()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getBancoOrigenFormateado()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getFechaVencimientoFormateado()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getErrorAltaInterdeposito()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getFechaErrorFormateado()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getObservacion() !=null ? reg.getObservacion().trim():"") + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getAcuerdoFormateado()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getNombreArchivo()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getFechaDerivacion()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(reg.getAnalistaDerivacion()) + Constantes.SEPARADOR_PIPE);
		if ("REVERSION".equals(reg.getTipoDto())){
			detalle.append(Utilidad.generarSalidaConValorOVacio(SiNoEnum.SI.getDescripcion()) + Constantes.SEPARADOR_PIPE);
		} else {
			detalle.append(Utilidad.generarSalidaConValorOVacio(SiNoEnum.NO.getDescripcion()) + Constantes.SEPARADOR_PIPE);
		}
		
		return detalle;
	}

	private StringBuffer crearTituloArchivoReporteAVCPendientesConciliar() {

		StringBuffer titulo = new StringBuffer();

		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_NUMCLIENTE + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_RAZONSOCIAL + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_FECHAALTA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_FECHADEPOSITO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_IMPORTE + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_ESTADO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_ANALISTATEAMCOMERCIAL + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_TIPOREGISTRO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_REFERENCIAVALOR + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_NUMBOLETA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_CUIT + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_CODIGOORGANISMO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_BANCOORIGEN + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_FECHAVENCIMIENTO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_ERRORALTAAUTOMATICA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_FECHAERRORALTAAUTOMATICA + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_OBSERVACIONES + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_NUMEROACUERDO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_NOMBREDELARCHIVO + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_FECHADERIVACION + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_ANALISTADERIVACION + Constantes.SEPARADOR_PIPE);
		titulo.append(Mensajes.REPORTE_REGISTRO_AVC_PENDIENTE_CONCILIAR_TITULO_VALORNOSHIVA + Constantes.SEPARADOR_PIPE);

		return titulo;
	}
	

}
