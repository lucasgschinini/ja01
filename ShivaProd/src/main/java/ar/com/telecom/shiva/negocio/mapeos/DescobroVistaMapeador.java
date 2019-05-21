package ar.com.telecom.shiva.negocio.mapeos;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobro;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class DescobroVistaMapeador extends MapeadorResultadoBusqueda {

	public final int LIMITE_CALIPSO = 15;

	@Autowired 
	private ILdapServicio ldapServicio;
	
	@Override
	public DTO map(VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda)
			throws NegocioExcepcion {

		VistaSoporteResultadoBusquedaDescobro descobro = (VistaSoporteResultadoBusquedaDescobro) vistaSoporteResultadoBusqueda;
		
		DescobroDto descobroAux = new DescobroDto();
		
		descobroAux.setIdEmpresa(descobro.getIdEmpresa());
		descobroAux.setIdSegmento(descobro.getIdSegmento());
		descobroAux.setIdCobro(descobro.getIdCobro());
		descobroAux.setIdDescobro(descobro.getIdReversion());
		descobroAux.setIdOperacionDescobro(descobro.getIdOperacionReversion());
		descobroAux.setIdDescobroFormateado(descobro.getIdReversion().toString());
		descobroAux.setIdDescobroPadre(descobro.getIdReversionPadre());
		
		if(!Validaciones.isObjectNull(descobro.getIdMotivo())) {
			descobroAux.setIdMotivoReversion(descobro.getIdMotivo().toString());
			descobroAux.setDescripcionMotivoReversion(descobro.getDescripcionMotivo());
		}
		
		if(!Validaciones.isObjectNull(descobro.getIdReversionPadre())){
			descobroAux.setIdDescobroPadreFormateado(descobro.getIdReversionPadre().toString());
		}
		descobroAux.setIdOperacionCobro(descobro.getIdOperacionCobro());
		descobroAux.setIdOperacionCobroFormateado(descobro.getIdOperacionCobro().toString());
		
		descobroAux.setClientesDescobro(descobro.getCliente());
		
		/*Estado*/
		Estado e = Estado.getEnumByName(descobro.getEstado());
		descobroAux.setEstado(e);	
		descobroAux.setDescripcionEstado(e.descripcion());
		
		/*Marca*/
		if(!Validaciones.isNullOrEmpty(descobro.getSubEstado())){
			MarcaEnum m = MarcaEnum.getEnumByName(descobro.getSubEstado());
			descobroAux.setMarca(m);	
			descobroAux.setDescripcionMarca(m.descripcion());
		}
		
		/*Analista*/
		UsuarioLdapDto usuarioAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(descobro.getAnalista());
		if(!Validaciones.isObjectNull(usuarioAnalista)) {
			descobroAux.setNombreAnalista(usuarioAnalista.getNombreCompleto());
			descobroAux.setMailAnalista(usuarioAnalista.getMail());
			descobroAux.setIdAnalista(descobro.getAnalista());
			descobroAux.setUrlFotoAnalista(descobroAux.urlFotoUsuario(descobro.getAnalista()));
		}
		
		/*Copropietario*/
		if (!Validaciones.isNullOrEmpty(descobro.getCopropietario())){
			UsuarioLdapDto usuarioCopropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(descobro.getCopropietario());
			if(!Validaciones.isObjectNull(usuarioAnalista)) {
				descobroAux.setNombreCopropietario(usuarioCopropietario.getNombreCompleto());
				descobroAux.setMailCopropietario(usuarioCopropietario.getMail());
				descobroAux.setIdCopropietario(descobro.getCopropietario());
				descobroAux.setUrlFotoCopropietario(descobroAux.urlFotoUsuario(descobro.getCopropietario()));
			}
		}
		
		
		/*Analista Team Comercial*/
		if (!Validaciones.isNullOrEmpty(descobro.getAnalistaTeamComercial())){
			String[] atc = descobro.getAnalistaTeamComercial().split("\\-");
			
			Set<String> listaAnalistasTeamComercialSinDuplicados = new HashSet<String>();
			
			// Valida si hay mas de un analista team comercial 
			for(String analistaTeam : atc){
				listaAnalistasTeamComercialSinDuplicados.add(analistaTeam);
			}
			
			if(Validaciones.isCollectionNotEmpty(listaAnalistasTeamComercialSinDuplicados)){
				if(listaAnalistasTeamComercialSinDuplicados.size() == 1){
					UsuarioLdapDto usuarioAnalistaTeamComercial = ldapServicio.buscarUsuarioPorUidEnMemoria(listaAnalistasTeamComercialSinDuplicados.iterator().next());
					if(!Validaciones.isObjectNull(usuarioAnalistaTeamComercial)){
						descobroAux.setNombreAnalistaTeamComercial(usuarioAnalistaTeamComercial.getNombreCompleto());
						descobroAux.setMailAnalistaTeamComercial(usuarioAnalistaTeamComercial.getMail());
						descobroAux.setIdAnalistaTeamComercial(listaAnalistasTeamComercialSinDuplicados.iterator().next());
						descobroAux.setUrlFotoAnalistaTeamComercial(descobroAux.urlFotoUsuario(listaAnalistasTeamComercialSinDuplicados.iterator().next()));
					}
				}else{
					descobroAux.setNombreAnalistaTeamComercial(ConstantesCobro.GRUPO_TEAM_COMERCIAL);
				}
			}
		}

		/*Marca Destino Aplicación */
		descobroAux.setTipoCobro(descobro.getTipoCobro());	
		
		/*ID Operación Sistema Externo */
		descobroAux.setCodigoOperacionExterna(descobro.getCodigoOperacionExterna());	
		
		if (!Validaciones.isObjectNull(descobro.getImporteTotal())) {
			MonedaEnum monedaOperacion = MonedaEnum.getEnumByName(descobro.getMonedaImporte());
			if (!Validaciones.isObjectNull(monedaOperacion)) {
				descobroAux.setImporteTotalFormateado(monedaOperacion.getSigno2() + (Utilidad.formatCurrency(descobro.getImporteTotal(), false, true)));
			} else {
				descobroAux.setImporteTotalFormateado((Utilidad.formatCurrency(descobro.getImporteTotal(), false, true)));
			}
		}		
		
		/*Fechas*/
		if (!Validaciones.isObjectNull(descobro.getFechaAlta())){
			descobroAux.setFechaAltaFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(descobro.getFechaAlta()));
		}
		if (!Validaciones.isNullOrEmpty(descobro.getFechaDerivacion())){
			descobroAux.setFechaDerivacionFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(descobro.getFechaDerivacion()));	
		}
		if(!Validaciones.isNullOrEmpty(descobro.getFechaReversion())) {
			descobroAux.setFechaReversionFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(descobro.getFechaReversion()));
		}
		if(!Validaciones.isNullOrEmpty(descobro.getFechaUltimoEstado())) {
			descobroAux.setFechaUltimoEstadoFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(descobro.getFechaUltimoEstado()));
		}
		
		descobroAux.setObservacion(descobro.getObservacion());
		
		return descobroAux;
	}
}
