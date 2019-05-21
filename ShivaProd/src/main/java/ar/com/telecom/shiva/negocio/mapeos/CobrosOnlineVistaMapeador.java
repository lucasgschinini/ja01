package ar.com.telecom.shiva.negocio.mapeos;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteCobrosOnline;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.persistencia.dao.IMotivoCobroDao;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;


public class CobrosOnlineVistaMapeador extends MapeadorResultadoBusqueda {
	
	public final int LIMITE_CALIPSO = 15;
	
	@Autowired 
	private ILdapServicio ldapServicio;
	@Autowired
	private IMotivoCobroDao motivoCobroDao;
	

	@Override
	public DTO map(VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda)	throws NegocioExcepcion {

		VistaSoporteCobrosOnline cobro = (VistaSoporteCobrosOnline) vistaSoporteResultadoBusqueda;
		
		CobroDto cobroAux = new CobroDto();
		
		cobroAux.setEmpresa(cobro.getIdEmpresa());
		cobroAux.setSegmento(cobro.getIdSegmento());
		
		/*Estado*/
		Estado e = Estado.getEnumByName(cobro.getEstado());
		cobroAux.setEstado(e);	
		cobroAux.setDescripcionEstado(e.descripcion()); // seteo la descripcion en un string 
														//porque no puedo acceder al enum desde el json :(
		/*Marca*/
		if(!Validaciones.isNullOrEmpty(cobro.getSubEstado())){
			String[] marcas = cobro.getSubEstado().split("\\|");
			for (String marca : marcas) {
				MarcaEnum marcaEnum = MarcaEnum.getEnumByName(marca);
				if(!cobroAux.getMarcas().contains(marcaEnum)) {
					cobroAux.getMarcas().add(marcaEnum);
					if(Validaciones.isNullOrEmpty(cobroAux.getDescripcionMarca())) {
						//Primera marca
						cobroAux.setDescripcionMarca(marcaEnum.descripcion());
					}else {
						//Ya hay cargadas marcas
						cobroAux.setDescripcionMarca(cobroAux.getDescripcionMarca()+"<br>"+marcaEnum.descripcion());
					}	
				}
			}
		}
		
		/*Id Operacion*/
		cobroAux.setIdOperacion(cobro.getIdOperacionCobro());
		cobroAux.setIdOperacionFormateado(cobro.getIdOperacionCobro().toString());
		
		/*Id Cobro*/
		cobroAux.setIdCobro(cobro.getIdCobro());
		
		/*Id Cobro Padre*/
		if(!Validaciones.isObjectNull(cobro.getIdCobroPadre())) {
			cobroAux.setIdCobroPadre(cobro.getIdCobroPadre());
		}
		/* Cliente*/
		cobroAux.setClientesCobro(cobro.getCliente());
		
		/* Importe */

		cobroAux.setMonedaOperacion(!Validaciones.isObjectNull(MonedaEnum.getEnumByName(cobro.getMonedaImporte())) ? MonedaEnum.getEnumByName(cobro.getMonedaImporte()).getSigno2() : "");
		cobroAux.setImporteTotalDeudaFormateado(cobroAux.getMonedaOperacion() + Utilidad.formatCurrency(cobro.getImporteTotal(), false, true));

		/*Fechas*/
		cobroAux.setFechaAltaFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(cobro.getFechaAlta()));
		if (!Validaciones.isNullOrEmpty(cobro.getFechaDerivacion())){
			cobroAux.setFechaDerivacionFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(cobro.getFechaDerivacion()));	
		}
		if (!Validaciones.isNullOrEmpty(cobro.getFechaAprobReferCob())){
			cobroAux.setFechaAprobacionReferenteCobranzaFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(cobro.getFechaAprobReferCob()));	
		}
		if (!Validaciones.isNullOrEmpty(cobro.getFechaAprobSuperCob())){
			cobroAux.setFechaAprobacionSupervisorCobranzaFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(cobro.getFechaAprobSuperCob()));	
		}
		if (!Validaciones.isNullOrEmpty(cobro.getFechaImputacion())){
			cobroAux.setFechaImputacionFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(cobro.getFechaImputacion()));	
		}
		if (!Validaciones.isNullOrEmpty(cobro.getFechaUltimoEstado())){
			cobroAux.setFechaUltimoEstadoFormatoJson(cobro.getFechaUltimoEstado());			
		}
		if (!Validaciones.isNullOrEmpty(cobro.getFechaAprobOperEsp())){
			cobroAux.setFechaAprobacionOperacionesEspecialesFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(cobro.getFechaAprobOperEsp()));			
		}

		/*Analista*/
		UsuarioLdapDto usuarioAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(cobro.getAnalista());
		cobroAux.setNombreAnalista(usuarioAnalista.getNombreCompleto());
		cobroAux.setMailAnalista(usuarioAnalista.getMail());
		cobroAux.setIdAnalista(cobro.getAnalista());
		cobroAux.setUrlFotoAnalista(cobroAux.urlFotoUsuario(cobro.getAnalista()));
		
		/*Copropietario*/
		if (!Validaciones.isNullOrEmpty(cobro.getCopropietario())){
			UsuarioLdapDto usuarioCopropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(cobro.getCopropietario());
			cobroAux.setNombreCopropietario(usuarioCopropietario.getNombreCompleto());
			cobroAux.setMailCopropietario(usuarioCopropietario.getMail());
			cobroAux.setIdCopropietario(cobro.getCopropietario());
			cobroAux.setUrlFotoCopropietario(cobroAux.urlFotoUsuario(cobro.getCopropietario()));
		}
		
		/*Analista Team Comercial*/
		if (!Validaciones.isNullOrEmpty(cobro.getAnalistaTeamComercial())){
			String[] atc = cobro.getAnalistaTeamComercial().split("\\-");
			
			Set<String> listaAnalistasTeamComercialSinDuplicados = new HashSet<String>();
			
			// Valida si hay mas de un analista team comercial 
			for(String analistaTeam : atc){
				listaAnalistasTeamComercialSinDuplicados.add(analistaTeam);
			}
			
			if(Validaciones.isCollectionNotEmpty(listaAnalistasTeamComercialSinDuplicados)){
				if(listaAnalistasTeamComercialSinDuplicados.size() == 1){
					UsuarioLdapDto usuarioAnalistaTeamComercial = ldapServicio.buscarUsuarioPorUidEnMemoria(listaAnalistasTeamComercialSinDuplicados.iterator().next());
					if(!Validaciones.isObjectNull(usuarioAnalistaTeamComercial)){
						cobroAux.setNombreAnalistaTeamComercial(usuarioAnalistaTeamComercial.getNombreCompleto());
						cobroAux.setMailAnalistaTeamComercial(usuarioAnalistaTeamComercial.getMail());
						cobroAux.setIdAnalistaTeamComercial(listaAnalistasTeamComercialSinDuplicados.iterator().next());
						cobroAux.setUrlFotoAnalistaTeamComercial(cobroAux.urlFotoUsuario(listaAnalistasTeamComercialSinDuplicados.iterator().next()));
					}
				}else{
					cobroAux.setNombreAnalistaTeamComercial(ConstantesCobro.GRUPO_TEAM_COMERCIAL);
				}
			}
		}
		
		/*Marca Destino Aplicación */
		cobroAux.setTipoCobro(cobro.getTipoCobro());	
		
		/*ID Operación Sistema Externo */
		cobroAux.setCodigoOperacionExterna(cobro.getCodigoOperacionExterna());	
		
		/* Motivo Cobro*/
		cobroAux.setDescripcionMotivoCobro(cobro.getDescMotivoCobro());
		
		
//		cobroAux.setImporteTotalDeuda(cobro.getImporteTotal());
		
		// TODO u578936 se realiza en la linea 85.
		// Utilidad.formatCurrency ignora el segundo parametro!!!!
//		if (!Validaciones.isObjectNull(cobro.getImporteTotal())){
//			cobroAux.setImporteTotalDeudaFormateado((Utilidad.formatCurrency(cobro.getImporteTotal(), LIMITE_CALIPSO)));
//		}
		
		cobroAux.setIdReversionPadre(cobro.getIdReversionPadre());
		
		if (!Validaciones.isNullOrEmpty(cobro.getIdReversion())) {
			cobroAux.setIdReversion(cobro.getIdReversion());
		} else {
			cobroAux.setIdReversion("-");
		}
		
		if (!Validaciones.isNullOrEmpty(cobro.getIdOperacionReversion())) {
			cobroAux.setIdOperacionReversion(cobro.getIdOperacionReversion());
		}
		
		Estado estadoReversion = Estado.getEnumByName(cobro.getEstadoReversion());
		if(!Validaciones.isObjectNull(estadoReversion)) {
			cobroAux.setEstadoReversion(estadoReversion);	
			cobroAux.setEstadoReversionDescripcion(estadoReversion.descripcion());
		}
		
		if (!Validaciones.isNullOrEmpty(cobro.getIdOperacionMasiva())) {
			cobroAux.setIdOperacionMasiva(cobro.getIdOperacionMasiva());
		}
		
		if (!Validaciones.isNullOrEmpty(cobro.getNombreArchivo())) {
			cobroAux.setNombreArchivo(cobro.getNombreArchivo());
		}
		
		cobroAux.setOrigenDescobro(SistemaEnum.getEnumByName(cobro.getOrigenDescobro()));
		cobroAux.setTieneDuc(cobro.getTieneDuc());
		
		if (!Validaciones.isNullOrEmpty(cobro.getIdContradocumentoCap())) {
		cobroAux.setIdContradocumentoCap(cobro.getIdContradocumentoCap());
		}
		
		if (!Validaciones.isNullOrEmpty(cobro.getIdTipoMedioPago())) {
			cobroAux.setIdTipoMedioPago(cobro.getIdTipoMedioPago());
		}

		if (!Validaciones.isNullOrEmpty(cobro.getReferenteAprobadorCobro())) {
			cobroAux.setNombreApellidoUsuarioAprobacionReferenteCobranza(cobro.getReferenteAprobadorCobro());
		}
		if (!Validaciones.isNullOrEmpty(cobro.getReferenteCaja())) {
			cobroAux.setNombreApellidoReferenteCaja(cobro.getReferenteCaja());
			if (!Validaciones.isNullOrEmpty(cobro.getFechaReferenteCaja())){
//				cobroAux.setFechaReferenteCajaFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(cobro.getFechaReferenteCaja()));	
				cobroAux.setFechaReferenteCajaFormatoJson(cobro.getFechaReferenteCaja());	
			}
		}
		if (!Validaciones.isNullOrEmpty(cobro.getReferenteOperExterna())) {
			cobroAux.setNombreApellidoReferenteOperacionExterna(cobro.getReferenteOperExterna());
			if (!Validaciones.isNullOrEmpty(cobro.getFechaReferenteOperExterna())){
//				cobroAux.setFechaReferenteOperacionExternaFormatoJson(Utilidad.formatDateAndTimeFullFromDataBase(cobro.getFechaReferenteOperExterna()));	
				cobroAux.setFechaReferenteOperacionExternaFormatoJson(cobro.getFechaReferenteOperExterna());	
			}
		}
		if (!Validaciones.isNullOrEmpty(cobro.getAprobadorSuperOperEspe())) {
			cobroAux.setNombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales(cobro.getAprobadorSuperOperEspe());
		}
		
		return cobroAux;
	}
}
