package ar.com.telecom.shiva.negocio.mapeos;


import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaReciboPreimpreso;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalCompensacion;
import ar.com.telecom.shiva.presentacion.bean.dto.ReciboPreImpresoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class ResultadoBusquedaReciboPreimpresoMapeador extends MapeadorResultadoBusqueda {

	@Autowired 
	private ILdapServicio ldapServicio;

	@Override
	public DTO map (VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda) throws NegocioExcepcion {
		VistaSoporteResultadoBusquedaReciboPreimpreso reciboVista = (VistaSoporteResultadoBusquedaReciboPreimpreso) vistaSoporteResultadoBusqueda;
	
		ReciboPreImpresoDto reciboDto = new ReciboPreImpresoDto();
		reciboDto.setFechaUltimaModificacion(reciboVista.getFechaUltimaModificacion());
		reciboDto.setValores(new HashSet<ValorDto>());
		
		//Usuario Anulacion (LDAP)
		UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(reciboVista.getUsuarioAnulacion());
		reciboDto.setUsuarioAnulacion(usuarioLdap != null?usuarioLdap.getTuid():"");
		reciboDto.setNombreUsuarioAnulacion(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		reciboDto.setMailUsuarioAnulacion(usuarioLdap != null?usuarioLdap.getMail():"");
		
		reciboDto.setId(reciboVista.getIdReciboPreimpreso());
		reciboDto.setImporte(Validaciones.isObjectNull(reciboVista.getImporteRecibo()) ? "" : Utilidad.formatCurrency(reciboVista.getImporteRecibo(), 2));
		if(!Validaciones.isNullEmptyOrDash(reciboVista.getEstado())){
			reciboDto.setEstado(Estado.getEnumByName(reciboVista.getEstado()).descripcion());
		}
		reciboDto.setUsuarioAnulacion(Validaciones.isObjectNull(reciboVista.getUsuarioAnulacion()) ? "" : String.valueOf(reciboVista.getUsuarioAnulacion()));
		reciboDto.setFechaAnulacion(Validaciones.isObjectNull(reciboVista.getFechaAnulacion()) ? "" : Utilidad.formatDateAndTimeFull(reciboVista.getFechaAnulacion()));	
		reciboDto.setFechaIngreso(Validaciones.isObjectNull(reciboVista.getFechaIngreso())?"":Utilidad.formatDatePicker(reciboVista.getFechaIngreso()));
		reciboDto.setEmpresa(Validaciones.isObjectNull(reciboVista.getEmpresa()) ? "" : reciboVista.getEmpresa());
		reciboDto.setSegmento(Validaciones.isObjectNull(reciboVista.getSegmento()) ? "" : reciboVista.getSegmento());
		reciboDto.setNroTalonario(Validaciones.isObjectNull(reciboVista.getIdTalonario()) ? "" : String.valueOf(reciboVista.getIdTalonario()));
		reciboDto.setNroRecibo(Validaciones.isObjectNull(reciboVista.getNumeroRecibo()) ? "" : reciboVista.getNumeroRecibo());
		reciboDto.setUsuarioGestor(Validaciones.isObjectNull(reciboVista.getGestorAsignado())? "" : reciboVista.getGestorAsignado());
		//Lista de valores
		reciboDto.setValores(new HashSet<ValorDto>());
		String valor = "<p style='line-height:12pt; font-size: 11px;' >";
		for (ValorDto valorDto : reciboVista.getListaValores()) {
				usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valorDto.getIdAnalista());
				valorDto.setIdAnalista(usuarioLdap != null?usuarioLdap.getTuid():"");
				valorDto.setNombreAnalista(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
				valorDto.setMailAnalista(usuarioLdap != null?usuarioLdap.getMail():"");
				valor += getValorFormateado(valorDto); 
				reciboDto.getValores().add(valorDto);
		}
		valor += "</p>";
		reciboDto.setListaValores(valor);
		
		//Lista de compensaciones
		String compensacion = "<p style='line-height:12pt; font-size: 11px;' >";
		for ( ShvTalCompensacion compensacionVista : reciboVista.getListaCompensaciones()){
			compensacion += "Referencia: " + compensacionVista.getReferencia() ;
			compensacion += "</br> Importe: " + Utilidad.formatCurrency(compensacionVista.getImporte(),2);
			compensacion += "</br> </br>";
		}
			
		compensacion += "</p>";
		reciboDto.setListaCompensaciones(compensacion);
		reciboDto.setCompensaciones(reciboVista.getListaCompensaciones());
		
		return reciboDto;
		
	}

	/**
	 * 
	 * @param valorDto
	 * @return
	 */
	private String getValorFormateado(ValorDto valorDto) {
		
		String valor = 
			"<div class='contenedor-columna' style='text-align: left;'><div style='width: 140px;'> "
			+ "<img alt='Usuario' class='bloqueUsuario' src='"
				+ valorDto.urlFotoUsuario(valorDto.getIdAnalista())+"'"
			+ "style='cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;'" 
			+ " onerror='javascript:bloqueUsuario()'>"
				+ valorDto.getNombreAnalista()+" <br>"
			+ "<a href='sip:"+valorDto.getMailAnalista()+"'  title='Chat'><i class='icon-comment' style=' margin-top: 3px'></i></a>"
			+ "<a href='mailto:"+valorDto.getMailAnalista()+"'  title='Email'><i class='icon-envelope' style=' margin-left: 3px; margin-top: 2px'></i></a>"
	 		+ "</div></div>"+"<div class='contenedor-columna'style='text-align: left;'>"
			+(valorDto.getTipoValor()
			+ "</br> &nbsp; &nbsp; &nbsp; Cliente: " 
				+ (Validaciones.isObjectNull(valorDto.getCodCliente()) ? "" : valorDto.getCodCliente())
			+ "&nbsp;" 
				+ (Validaciones.isObjectNull(valorDto.getRazonSocialClienteAgrupador()) ? "" : valorDto.getRazonSocialClienteAgrupador()))
			+ "</br> &nbsp; &nbsp; &nbsp; " 
				+ (Validaciones.isObjectNull(valorDto.getNumeroValorFormateadoConRetornoCarro()) ? "" : valorDto.getNumeroValorFormateadoConRetornoCarro())
			+ "</br> &nbsp; &nbsp; &nbsp; Fecha Deposito: "
				+ (Validaciones.isObjectNull(valorDto.getFechaDeposito()) ? "" : valorDto.getFechaDeposito())
			+ "</br> &nbsp; &nbsp; &nbsp; Importe: "
				+ (Validaciones.isObjectNull(valorDto.getImporte()) ? "" : valorDto.getImporte())
			+ "</br> &nbsp; &nbsp; &nbsp; Acuerdo: "
				+ (Validaciones.isObjectNull(valorDto.getIdAcuerdo()) ? "" : valorDto.getIdAcuerdo());
			valor += "</br></br></div></br>";
			
		return valor;
		
	}
}
	
