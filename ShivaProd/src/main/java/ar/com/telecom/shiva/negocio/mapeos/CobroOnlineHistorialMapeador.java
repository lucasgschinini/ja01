package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaCobroHistorial;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroHistoricoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class CobroOnlineHistorialMapeador  extends MapeadorResultadoBusqueda {
	
	@Autowired 
	private ILdapServicio ldapServicio;

	@Override
	public DTO map(VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda) throws NegocioExcepcion {
		
		CobroHistoricoDto cobroHistDto = new CobroHistoricoDto();
		VistaSoporteResultadoBusquedaCobroHistorial cobroAux = (VistaSoporteResultadoBusquedaCobroHistorial) vistaSoporteResultadoBusqueda;
		
		cobroHistDto.setIdOperacion(cobroAux.getIdOperacion());
		cobroHistDto.setIdOperacionFormateado(cobroAux.getIdOperacionFormateado());
		cobroHistDto.setEstadoModificacion(Estado.getEnumByName(cobroAux.getEstadoModificacion()).descripcion());
		if(!Validaciones.isNullOrEmpty(cobroAux.getMarcaModificacion()) && !MarcaEnum.SIMULACION_ONLINE_FINALIZADA_CON_ERROR.name().equals(cobroAux.getMarcaModificacion()) && !MarcaEnum.SIMULACION_ONLINE_FINALIZADA_CON_EXITO.name().equals(cobroAux.getMarcaModificacion())) {
			cobroHistDto.setMarcaModificacion(MarcaEnum.getEnumByName(cobroAux.getMarcaModificacion()).descripcion() + (!Validaciones.isNullEmptyOrDash(cobroAux.getObservacionSistSoc()) ? " " + cobroAux.getObservacionSistSoc() : ""));
		}
		UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUid(cobroAux.getIdUsuarioModificacion());
		cobroHistDto.setIdUsuarioModificacion(cobroAux.getIdUsuarioModificacion());
		if(!Validaciones.isObjectNull(usuarioLdap)) {
			cobroHistDto.setNombreCompletoUsuarioMod(usuarioLdap.getNombreCompleto());
		}
		
		cobroHistDto.setFechaModificacion(cobroAux.getFechaModificacion());
		
		if(!Validaciones.isObjectNull(cobroAux.getObservaciones())) {
			if (Estado.COB_RECHAZADO.descripcion().equals(cobroHistDto.getEstadoModificacion())) {
				cobroHistDto.setObservaciones(
					Utilidad.mostrarDatosModificados(cobroAux.getObservaciones(),
						//cobroHistDto.getEstadoModificacion(),
						Utilidad.DATOS_MODIFICADOS_SOLO_DATOS
				));
			} else if (   Estado.COB_PROCESO.descripcion().equals(cobroHistDto.getEstadoModificacion())
						||Estado.COB_PROCESO_APLICACION_EXTERNA.descripcion().equals(cobroHistDto.getEstadoModificacion())
						||Estado.COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.descripcion().equals(cobroHistDto.getEstadoModificacion())
						||Estado.COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_PROCESAR_MIC.descripcion().equals(cobroHistDto.getEstadoModificacion())
						||Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.descripcion().equals(cobroHistDto.getEstadoModificacion())
						||Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.descripcion().equals(cobroHistDto.getEstadoModificacion())
						||Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.descripcion().equals(cobroHistDto.getEstadoModificacion())
						||Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PEND_PROC_MIC.descripcion().equals(cobroHistDto.getEstadoModificacion())) {
				if(MarcaEnum.RECHAZADO_POR_REFERENTE_OPERACIONES_EXTERNAS.name().equals(cobroAux.getMarcaModificacion())
						|| MarcaEnum.RECHAZADO_POR_REFERENTE_CAJA.name().equals(cobroAux.getMarcaModificacion())){
					cobroHistDto.setObservaciones(Utilidad.mostrarDatosModificados(cobroAux.getObservaciones(),Utilidad.DATOS_MODIFICADOS_SOLO_DATOS));
				}
			} else {
				cobroHistDto.setObservaciones(Utilidad.mostrarDatosModificados(cobroAux.getObservaciones(),Utilidad.DATOS_MODIFICADOS_SOLO_DATOS));
			}
		}
		
		cobroHistDto.setMensajeError(cobroAux.getMensajeError());
		cobroHistDto.setNumeroTransaccion(cobroAux.getNumeroTransaccion());
		cobroHistDto.setNumeroTransaccionFicticio(cobroAux.getNumeroTransaccionFicticio());
		cobroHistDto.setNumeroDocumentoDebito(cobroAux.getNumeroDocumentoDebito());
		cobroHistDto.setImporte(cobroAux.getImporte());
		cobroHistDto.setFechaCobro(cobroAux.getFechaCobro());
		cobroHistDto.setRefMedioPago(cobroAux.getRefMedioPago());
		cobroHistDto.setAcuerdoTrasladoCargo(cobroAux.getAcuerdoTrasladoCargo());
		
		return cobroHistDto;
	}

}
