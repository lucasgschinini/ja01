package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroHistorial;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroHistoricoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class DescobroHistorialMapeador extends MapeadorResultadoBusqueda {

	
	@Autowired 
	private ILdapServicio ldapServicio;
	
	
	@Override
	public DTO map(VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda)
			throws NegocioExcepcion {

		VistaSoporteResultadoBusquedaDescobroHistorial descobroHistorial = (VistaSoporteResultadoBusquedaDescobroHistorial) vistaSoporteResultadoBusqueda;
		
		DescobroHistoricoDto descobroHist = new DescobroHistoricoDto();
		
		descobroHist.setIdReversion(descobroHistorial.getIdReversion());
		descobroHist.setIdOperacion(descobroHistorial.getIdOperacion());
		descobroHist.setIdOperacionFormateado(descobroHistorial.getIdOperacion().toString());
		descobroHist.setEstado(Estado.getEnumByName(descobroHistorial.getEstado()).descripcion());
		if(!Validaciones.isNullOrEmpty(descobroHistorial.getSubEstado())) {
			descobroHist.setSubEstado(MarcaEnum.getEnumByName(descobroHistorial.getSubEstado()).descripcion());
		}
		UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUid(descobroHistorial.getUsuarioUltimaModificacion());
		if(!Validaciones.isObjectNull(usuarioLdap)) {
			descobroHist.setUsuarioModificacion((usuarioLdap.getNombreCompleto()));
		}
		descobroHist.setFechaModificacion(descobroHistorial.getFechaUltimaModificacion());
		descobroHist.setMensajeError(descobroHistorial.getMensajeError());
		descobroHist.setNroTransaccion(descobroHistorial.getNroTransaccion());
		descobroHist.setNroTransaccionFicticio(descobroHistorial.getNroTransaccionFicticio());
		descobroHist.setNroDocumentoDebito(descobroHistorial.getNroDocumentoDebito());
		descobroHist.setImporteACobrar(descobroHistorial.getImporteACobrar());
		descobroHist.setFechaCobro(descobroHistorial.getFechaCobro());
		descobroHist.setRefMedioPago(descobroHistorial.getRefMedioPago());
		descobroHist.setAcuerdoFactDestinoCargo(descobroHistorial.getAcuerdoFactDestinoCargo());
		
		return descobroHist;
	}

}
