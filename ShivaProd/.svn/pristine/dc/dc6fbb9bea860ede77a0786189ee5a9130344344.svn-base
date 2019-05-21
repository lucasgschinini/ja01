package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteOperacionesMasivas;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;


public class OperacionesMasivasVistaMapeador extends MapeadorResultadoBusqueda {
	
	@Autowired 
	private ILdapServicio ldapServicio;
	
	@Override
	public DTO map(VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda)	throws NegocioExcepcion{

		VistaSoporteOperacionesMasivas operacionMasiva = (VistaSoporteOperacionesMasivas) vistaSoporteResultadoBusqueda;
		
		OperacionMasivaDto operacionMasivaAux = new OperacionMasivaDto();
		
		//Id Operacion Masiva
		operacionMasivaAux.setIdOperacionMasiva(operacionMasiva.getIdOperacionMasiva());
		
		//Segmento
		
		operacionMasivaAux.setSegmento(operacionMasiva.getSegmento());
		
		//Nombre Archivo
		operacionMasivaAux.setNombreArchivo(operacionMasiva.getArchivoOriginal());
		
		
		/*Estado*/
		Estado e = Estado.getEnumByName(operacionMasiva.getEstadoOperacionMasiva());
		
		operacionMasivaAux.setEstadoOperacionMasiva(e);
	
		if(e!=null)
		operacionMasivaAux.setDescripcionEstado(e.descripcion());
		
		//Tipo de Operacion
		if(!Validaciones.isNullOrEmpty(operacionMasiva.getTipoDeOperacion())){
			operacionMasivaAux.setTipoDeOperacionMasiva(TipoArchivoOperacionMasivaEnum.getEnumByName(operacionMasiva.getTipoDeOperacion()).getDescripcion());
		}
		//Fecha Ultima Modificacion
		if(!Validaciones.isNullOrEmpty(operacionMasiva.getFechaUltimaModificacion())){
			operacionMasivaAux.setFechaUltimaModificacionFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(operacionMasiva.getFechaUltimaModificacion()));
		}else{
			operacionMasivaAux.setFechaUltimaModificacionFormatoJson("");
		}
		
		//Motivo Om
		operacionMasivaAux.setMotivoOM(operacionMasiva.getMotivoOm());
		
		//Descripcion Motivo
		operacionMasivaAux.setDescripcionMotivo(operacionMasiva.getDescripcionMotivo());
		
		//Analista
		UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasiva.getAnalista());
		operacionMasivaAux.setIdAnalista(operacionMasiva.getAnalista());
		operacionMasivaAux.setNombreAnalista(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		operacionMasivaAux.setMailAnalista(usuarioLdap != null?usuarioLdap.getMail():"");
		operacionMasivaAux.setUrlFotoAnalista(operacionMasivaAux.urlFotoUsuario(operacionMasiva.getAnalista()));
		
		/*Copropietario*/
		if (!Validaciones.isNullOrEmpty(operacionMasiva.getCopropietario())){
			UsuarioLdapDto usuarioCopropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasiva.getCopropietario());
			operacionMasivaAux.setNombreCopropietario(usuarioCopropietario.getNombreCompleto());
			operacionMasivaAux.setMailCopropietario(usuarioCopropietario.getMail());
			operacionMasivaAux.setIdCopropietario(operacionMasiva.getCopropietario());
			operacionMasivaAux.setUrlFotoCopropietario(operacionMasivaAux.urlFotoUsuario(operacionMasiva.getCopropietario()));
		}
		
		//Fecha Alta
		if(!Validaciones.isNullOrEmpty(operacionMasiva.getFechaAlta())){
			operacionMasivaAux.setFechaAltaFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(operacionMasiva.getFechaAlta()));
		}else{
			operacionMasivaAux.setFechaAltaFormatoJson("");
		}
		
		//Fecha Derivacion
		if(!Validaciones.isNullOrEmpty(operacionMasiva.getFechaDerivacion())){
			operacionMasivaAux.setFechaDerivacionFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(operacionMasiva.getFechaDerivacion()));
		}else{
			operacionMasivaAux.setFechaDerivacionFormatoJson("");
		}
		
		
		//Fecha Autorizacion
		
		if(!Validaciones.isNullOrEmpty(operacionMasiva.getFechaAutorizacionReferente())){
			operacionMasivaAux.setFechaAutorizacionFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(operacionMasiva.getFechaAutorizacionReferente()));
		}else{
			operacionMasivaAux.setFechaAutorizacionFormatoJson("");
		}
		
		//Fecha Procesamiento
		
		if(!Validaciones.isNullOrEmpty(operacionMasiva.getFechaDeProcesamiento())){
			operacionMasivaAux.setFechaProcesamientoFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(operacionMasiva.getFechaDeProcesamiento()));
		}else{
			operacionMasivaAux.setFechaProcesamientoFormatoJson("");
		}
		
		//Registros Ingresados
		operacionMasivaAux.setRegistrosIngresados(operacionMasiva.getRegistrosIngresados());
		
		//Registros Ingresados OK
		operacionMasivaAux.setRegistrosProcesadosCorrectamente(operacionMasiva.getRegistrosProcesadosCorrectamente());
				
		//Registros Ingresados ERROR
		operacionMasivaAux.setRegistrosProcesadosConError(operacionMasiva.getRegistrosProcesadosConError());
				
	
		return operacionMasivaAux;
	}
	
	
}
