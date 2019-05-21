package ar.com.telecom.shiva.negocio.mapeos;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoBusquedaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
/**
 * @author u578936 M.A.Uehara
 *
 */
public class BusquedaLegajosChequeRechazadoMapeador extends Mapeador implements ILegajoChequeRechazadoMapeador {

	@Autowired private ILdapServicio ldapServicio;
	
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {

		
		return null;
	}
	
	/**
	 * 
	 */
	public DTO map(Modelo vo) throws NegocioExcepcion {
	
		return null;
	}
	public DTO map(Bean be) throws NegocioExcepcion {
		LegajoBusquedaDto dto = new LegajoBusquedaDto();
		VistaSoporteResultadoBusquedaLegajoChequeRechazado bean = (VistaSoporteResultadoBusquedaLegajoChequeRechazado) be;

		
		dto.setIdLegajo(bean.getIdLegajo());;
		dto.setEmpresa(bean.getEmpresa());
		dto.setSegmento(bean.getSegmento());
		dto.setCliente(bean.getCliente());
		dto.setImporte(Utilidad.formatCurrency(new BigDecimal(bean.getImporte()), true, true, 2));

		if (!Validaciones.isObjectNull(bean.getFechaNotificacion())) {
			dto.setFechaNotificacion(Utilidad.formatDatePicker(bean.getFechaNotificacion()));
		}
		if (!Validaciones.isObjectNull(bean.getFechaAlta())) {
			dto.setFechaAltaString(Utilidad.formatDatePicker(bean.getFechaAlta()));
		}
		if (!Validaciones.isObjectNull(bean.getFechaCierre())) {
			dto.setFechaCierre(Utilidad.formatDatePicker(bean.getFechaCierre()));
		}
		if (!Validaciones.isObjectNull(bean.getFechaUltimoEstado())) {
			dto.setFechaUltimoEstado(Utilidad.formatDatePicker(bean.getFechaUltimoEstado()));
		}
		dto.setCuit(bean.getCuit());
		dto.setBancoOrigen(bean.getBancoOrigen());
		dto.setNroCheque(bean.getNroCheque());
		dto.setEstado(bean.getEstado());
		dto.setMotivo(bean.getMotivo());
		
		
//		Set<ClienteSiebelDto> setClientes = new HashSet<ClienteSiebelDto>(dto.getCl);
//		UsuarioLdapDto usuarioLdap = teamComercialServicio.obtenerAnalistaTeamComercial(setClientes); 
//		
//		if(!Validaciones.isObjectNull(usuarioLdap)) {
//			dto.setAnalistaTeamComercial(usuarioLdap.getNombreCompleto());
//		}
		
		/*Analista*/
		UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(bean.getAnalista());
		dto.setIdAnalista(bean.getAnalista());
		dto.setAnalista(usuarioLdap.getNombreCompleto());
		dto.setMailAnalista(usuarioLdap.getMail());
		dto.setUrlFotoAnalista(dto.urlFotoUsuario(bean.getAnalista()));
		
		/* Copropietario */
		usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(bean.getCopropietario());
		dto.setIdCopropietario(bean.getCopropietario());
		dto.setCopropietario(usuarioLdap!=null?usuarioLdap.getNombreCompleto():"");
		dto.setMailCopropietario(usuarioLdap != null?usuarioLdap.getMail():"");
		dto.setUrlFotoCopropietario(dto.urlFotoUsuario(bean.getCopropietario()));
		
		/*Analista Team Comercial*/
		if (!Validaciones.isNullOrEmpty(bean.getAnalistaTeamComercial())){
			String[] atc = bean.getAnalistaTeamComercial().split("\\-");
			
			Set<String> listaAnalistasTeamComercialSinDuplicados = new HashSet<String>();
			
			// Valida si hay mas de un analista team comercial 
			for(String analistaTeam : atc){
				listaAnalistasTeamComercialSinDuplicados.add(analistaTeam);
			}
			
			if(Validaciones.isCollectionNotEmpty(listaAnalistasTeamComercialSinDuplicados)){
				if(listaAnalistasTeamComercialSinDuplicados.size() == 1){
					UsuarioLdapDto usuarioAnalistaTeamComercial = ldapServicio.buscarUsuarioPorUidEnMemoria(listaAnalistasTeamComercialSinDuplicados.iterator().next());
					if(!Validaciones.isObjectNull(usuarioAnalistaTeamComercial)){
						dto.setAnalistaTeamComercial(usuarioAnalistaTeamComercial.getNombreCompleto());
						dto.setMailAnalistaTeamComercial(usuarioAnalistaTeamComercial.getMail());
						dto.setUrlFotoAnalistaTeamComercial(dto.urlFotoUsuario(listaAnalistasTeamComercialSinDuplicados.iterator().next()));
					}
				}else{
					dto.setAnalistaTeamComercial(ConstantesCobro.GRUPO_TEAM_COMERCIAL);
				}
			}
		}
		
		return dto;
	}
}
