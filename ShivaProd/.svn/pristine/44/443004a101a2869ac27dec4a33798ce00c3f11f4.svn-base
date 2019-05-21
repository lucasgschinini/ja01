package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaOperacionMasivaHistorial;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaHistoricaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class OperacionesMasivasHistorialMapeador extends MapeadorResultadoBusqueda{

	
	@Autowired 
	private ILdapServicio ldapServicio;
	
	
	@Override
	public DTO map(VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda)
			throws NegocioExcepcion {

		OperacionMasivaHistoricaDto operMasivaDto = new OperacionMasivaHistoricaDto();
		VistaSoporteResultadoBusquedaOperacionMasivaHistorial operMasivaAux = (VistaSoporteResultadoBusquedaOperacionMasivaHistorial) vistaSoporteResultadoBusqueda;
		
		operMasivaDto.setIdOperacionMasiva(operMasivaAux.getIdOperacionMasiva());
		Estado estado = Estado.getEnumByName(operMasivaAux.getEstado());
		if(!Validaciones.isObjectNull(estado)){
			operMasivaDto.setEstado(estado.descripcion());
		}
		

		UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUid(operMasivaAux.getIdUsuarioModificacion());
		if(!Validaciones.isObjectNull(usuarioLdap)) {
			operMasivaDto.setIdUsuarioModificacion(operMasivaAux.getIdUsuarioModificacion());
			operMasivaDto.setNombreCompletoUsuarioMod((usuarioLdap.getNombreCompleto()));
			operMasivaDto.setMailUsuarioModificacion(usuarioLdap.getMail());
			operMasivaDto.setIdUsuarioModificacion(operMasivaAux.getIdUsuarioModificacion());
			operMasivaDto.setUrlFotoUsuarioModificacion(operMasivaDto.urlFotoUsuario(operMasivaAux.getIdUsuarioModificacion()));
		}
		operMasivaDto.setFechaModificacion(operMasivaAux.getFechaModificacion());
		operMasivaDto.setObservaciones(operMasivaAux.getObservaciones());

		
		return operMasivaDto;
	}

}
