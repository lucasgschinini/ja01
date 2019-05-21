package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EnviarMailBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.ImprimirBoletaEstadoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;


public class ResultadoBusquedaValorMapeador extends MapeadorResultadoBusqueda {

	@Autowired 
	private ILdapServicio ldapServicio;

	public DTO map (VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda) throws NegocioExcepcion {
		
		VistaSoporteResultadoBusquedaValor valorVista = (VistaSoporteResultadoBusquedaValor) vistaSoporteResultadoBusqueda;
		
		ValorDto val = new ValorDto();
		val.setId(valorVista.getIdValor());
		val.setEmpresa(valorVista.getEmpresa());
		val.setEmpresasAsociadas(valorVista.getEmpresasAsociadas());
		val.setFechaNtifDisponRetiroVal(Utilidad.formatDatePicker(valorVista.getFechaNtifDisponRetiroVal()));
		val.setCuitCliente(valorVista.getCuitCliente());
		val.setSegmento(valorVista.getSegmento());
		val.setCodCliente(valorVista.getIdClienteLegado());
		val.setRazonSocialClienteAgrupador(valorVista.getRazonSocialClienteAgrupador());
		val.setTipoValor(valorVista.getTipoValor());
		val.setEstadoValor(valorVista.getEstadoValor());
		val.setIdEstadoValor(valorVista.getIdEstadoValor());
		val.setOrigen(valorVista.getOrigen());
		val.setIdAcuerdo(valorVista.getIdAcuerdo());
		if(!Validaciones.isNullOrEmpty(valorVista.getNroValor())){
			val.setNumeroValor(valorVista.getNroValor().replace("|", "<br>"));
		}		
		val.setImporte(Utilidad.formatCurrency(valorVista.getImporte(), true, true));
		
		if (Validaciones.isNullOrEmpty(valorVista.getBdImpresa())) {
			val.setBoletaImpresaEstado(ImprimirBoletaEstadoEnum.VACIO);
		} else {
			val.setBoletaImpresaEstado(ImprimirBoletaEstadoEnum.getEnum(valorVista.getBdImpresa()));
		}
		
		if (Validaciones.isNullOrEmpty(valorVista.getBdEnviadaMail())) {
			val.setBoletaEnviadaMailEstado(EnviarMailBoletaEstadoEnum.VACIO);
		} else {
			val.setBoletaEnviadaMailEstado(EnviarMailBoletaEstadoEnum.getEnum(valorVista.getBdEnviadaMail()));
		}
		
		val.setFechaAltaValor(valorVista.getFechaAlta());
		/* Analista */
		if(Validaciones.isNullOrEmpty(valorVista.getIdAnalista()) == false){
			val.setIdAnalista(valorVista.getIdAnalista());
			val.setNombreAnalista(obtenerNombre(valorVista.getNombreApellidoAnalista()));
		}
		val.setReciboPreImpreso(valorVista.getNroRecibo());
		val.setConstancia(valorVista.getIdConstanciaRecepcion());
		val.setOperacionAsociada(valorVista.getOperacionAsociada());
		val.setFacturaRelacionada(valorVista.getFacturaRelacionada());
		val.setDocumentacionOriginalRecibida(valorVista.getDocumentacionOrigRecibida());
//		val.setMotivo(valorVista.getMotivo());
		val.setBancoDeposito(valorVista.getBancoDeposito());
		/* Copropietario */
		if(!Validaciones.isNullOrEmpty(valorVista.getIdCopropietario())){
			val.setIdCopropietario(valorVista.getIdCopropietario());
			val.setCopropietario(obtenerNombre(valorVista.getNombreApellidoCopropietario()));
		}
		// Usuario Autorización
		if(!Validaciones.isNullOrEmpty(valorVista.getUsuarioAutorizacion())){
			val.setIdUsuarioAutorizacion(valorVista.getUsuarioAutorizacion());
			val.setUsuarioAutorizacion(obtenerNombre(valorVista.getNombreApellidoUsuarioAutorizacion()));
		}
		val.setNumeroDocumentoContable(valorVista.getNumeroDocumentoContable());
		val.setMotivoSuspension(valorVista.getMotivoSuspension());
		// Datos del Legajo de Cheque Rechazado
		if(!Validaciones.isObjectNull(valorVista.getIdLegajoChequeRechazado())){
			val.setIdLegajoChequeRechazado(valorVista.getIdLegajoChequeRechazado());
		}
		if(!Validaciones.isObjectNull(valorVista.getFechaNotificacionRechazo())){
			val.setFechaNotificacionRechazo(Utilidad.formatDatePicker(valorVista.getFechaNotificacionRechazo()));
		}
		if(!Validaciones.isObjectNull(valorVista.getFechaRechazo())){
			val.setFechaRechazo(Utilidad.formatDatePicker(valorVista.getFechaRechazo()));
		}
		// Usuario Ejecutivo
		if(!Validaciones.isNullOrEmpty(valorVista.getEjecutivo())) {
			val.setIdUsuarioEjecutivo(valorVista.getEjecutivo());
			val.setUsuarioEjecutivo(obtenerNombre(valorVista.getNombreApellidoEjecutivo()));
		}
				
		// Usuario Asistente
		if(!Validaciones.isNullOrEmpty(valorVista.getAsistente())){
			val.setIdUsuarioAsistente(valorVista.getAsistente());
			val.setUsuarioAsistente(obtenerNombre(valorVista.getNombreApellidoAsistente()));
		}
				
		val.setSaldoDisponible(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), true, true));
		val.setFechaIngresoRecibo(Utilidad.formatDatePicker(valorVista.getFechaIngresoRecibo()));
		val.setFechaEmision(valorVista.getFechaEmision());
		val.setFechaEmisionCheque(valorVista.getFechaEmisionCheque());
		val.setFechaVencimiento(Utilidad.formatDatePicker(valorVista.getFechaVencimiento()));
		val.setFechaTransferencia(Utilidad.formatDatePicker(valorVista.getFechaTransferencia()));
//		val.setValorPadre(valorVista.getValorPadre());
		val.setArchivoValoresAconciliar(valorVista.getArchivoDeValoresAConciliar());
		val.setFechaDeposito(Utilidad.formatDatePicker(valorVista.getFechaDeposito()));
		val.setFechaUltimoEstado(Utilidad.formatDatePicker(valorVista.getFechaUltimoEstado()));
		val.setFechaDisponible(Utilidad.formatDatePicker(valorVista.getFechaDisponible()));
		val.setIdTipoValor(valorVista.getIdTipoValor());
		val.setIdValor(valorVista.getIdValor());
		val.setIdOrigen(valorVista.getIdOrigen());
		val.setIdEmpresa(valorVista.getIdEmpresa());
		val.setIdSegmento(valorVista.getIdSegmento());
		
		/**
		 * @author u573005 - Fabio Giaquinta
		 * Req. 11 Sprint 3
		 */
		
		// Usuario Analista Team Comercial
		if(!Validaciones.isNullOrEmpty(valorVista.getIdAnalistaTeamComercial())){
			val.setIdAnalistaTeamComercial(valorVista.getIdAnalistaTeamComercial());
			val.setUsuarioAnalistaTeamComercial(obtenerNombre(valorVista.getNombreAnalistaTeamComercial()));
		}
		
		val.setCodigoOrganismo(valorVista.getCodigoOrganismo());
		val.setNroBoleta(valorVista.getNroBoleta());
		val.setNroCuitRetencion(valorVista.getNroCuitRetencion());
		val.setProvincia(valorVista.getProvinciaRetencion());
		val.setReferenciaValor(valorVista.getReferenciaValor());
		val.setBancoOrigen(valorVista.getDescripcionBancoOrigen());
		val.setIdTipoRetencion(valorVista.getIdTipoRetencion());
		val.setTipoRetencion(valorVista.getDescripcionTipoRetencion());
		
		val.setFechaValor(valorVista.getFechaValor());
		val.setObservaciones(valorVista.getObservaciones());
		if(!Validaciones.isNullOrEmpty(valorVista.getCobroFormateado())){
			val.setCobroFormateado(valorVista.getCobroFormateado().replace("|", ", <br>"));
		}
		
		// Usuario Supervisor Team Comercial
		if(!Validaciones.isNullOrEmpty(valorVista.getIdSupervisorTeamComercial())){
			val.setIdSupervisorTeamComercial(valorVista.getIdSupervisorTeamComercial());
			val.setUsuarioSupervisorTeamComercial(obtenerNombre(valorVista.getNombreSupervisorTeamComercial()));
		}
		
		// Usuario Gerente Regional Team Comercial
		if(!Validaciones.isNullOrEmpty(valorVista.getIdGerenteRegionalTeamComercial())){
			val.setIdGerenteRegionalTeamComercial(valorVista.getIdGerenteRegionalTeamComercial());
			val.setUsuarioGerenteRegionalTeamComercial(obtenerNombre(valorVista.getNombreGerenteRegionalTeamComercial()));
		}

		return val;
	}

	private String obtenerNombre(String nombre){
		String nombreAux = "";
		
		if(!Validaciones.isNullEmptyOrDash(nombre)){
			String[] nombreDividido = nombre.toLowerCase().trim().split(" ");
			for (String string : nombreDividido) {
				if(!Validaciones.isNullOrEmpty(string)){
				nombreAux += (nombreAux.length() == 0 ? "" : " ") + string.substring(0, 1).toUpperCase() + string.substring(1);
				}
			}
		}
		
		return nombreAux;
	}
	/**
	 * @return the ldapServicio
	 */
	public ILdapServicio getLdapServicio() {
		return ldapServicio;
	}

	/**
	 * @param ldapServicio the ldapServicio to set
	 */
	public void setLdapServicio(ILdapServicio ldapServicio) {
		this.ldapServicio = ldapServicio;
	}
	
}
