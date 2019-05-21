package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvParamTeamComercial;
import ar.com.telecom.shiva.presentacion.bean.dto.TeamComercialDto;

public class TeamComercialMapeador extends Mapeador {
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvParamTeamComercial modelo = (ShvParamTeamComercial) vo;
		TeamComercialDto teamComercialDto = new TeamComercialDto();
		
		try {
			teamComercialDto.setIdTeamComercial(modelo.getIdTeamComercial());
			teamComercialDto.setNroDeCliente(modelo.getNroDeCliente());
			teamComercialDto.setUserGerenteRegional(modelo.getUserGerenteRegional());
			teamComercialDto.setGerenteRegional(modelo.getGerenteRegional());
			teamComercialDto.setUserGerenteComercial(modelo.getUserGerenteComercial());
			teamComercialDto.setGerenteComercial(modelo.getGerenteComercial());
			teamComercialDto.setUserEjecutivoCuenta(modelo.getUserEjecutivoCuenta());
			teamComercialDto.setEjecutivoCuenta(modelo.getEjecutivoCuenta());
			teamComercialDto.setUserIngenieroCuenta(modelo.getUserIngenieroCuenta());
			teamComercialDto.setIngenieroCuenta(modelo.getIngenieroCuenta());
			teamComercialDto.setUserAnalistaCobranzaDatos(modelo.getUserAnalistaCobranzaDatos());
			teamComercialDto.setAnalistaCobranzaDatos(modelo.getAnalistaCobranzaDatos());
			teamComercialDto.setUserAnalistaCobranzaVoz(modelo.getUserAnalistaCobranzaVoz());
			teamComercialDto.setAnalistaCobranzaVoz(modelo.getAnalistaCobranzaVoz());
			teamComercialDto.setUserAnalistaContratoVoz(modelo.getUserAnalistaContratoVoz());
			teamComercialDto.setAnalistaContratoVoz(modelo.getAnalistaContratoVoz());
			teamComercialDto.setUserSupervisorContratoVoz(modelo.getUserSupervisorContratoVoz());
			teamComercialDto.setSupervisorContratoVoz(modelo.getSupervisorContratoVoz());
			teamComercialDto.setUserSupervisorCobranzaVoz(modelo.getUserSupervisorCobranzaVoz());
			teamComercialDto.setSupervisorCobranzaVoz(modelo.getSupervisorCobranzaVoz());
			teamComercialDto.setUserSupervisorCobranzaDatos(modelo.getUserSupervisorCobranzaDatos());
			teamComercialDto.setSupervisorCobranzaDatos(modelo.getSupervisorCobranzaDatos());
			teamComercialDto.setUserResponsableCaring(modelo.getUserResponsableCaring());
			teamComercialDto.setResponsableCaring(modelo.getResponsableCaring());
			teamComercialDto.setUserAnalistaCaring(modelo.getUserAnalistaCaring());
			teamComercialDto.setAnalistaCaring(modelo.getAnalistaCaring());

		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return teamComercialDto;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		TeamComercialDto teamComercialDto = (TeamComercialDto) dto;
		
		ShvParamTeamComercial modelo = (ShvParamTeamComercial)
				(vo != null ? vo : new ShvParamTeamComercial());
		try {
			modelo.setIdTeamComercial(teamComercialDto.getIdTeamComercial());
			modelo.setNroDeCliente(teamComercialDto.getNroDeCliente());
			modelo.setUserGerenteRegional(teamComercialDto.getUserGerenteRegional());
			modelo.setGerenteRegional(teamComercialDto.getGerenteRegional());
			modelo.setUserGerenteComercial(teamComercialDto.getUserGerenteComercial());
			modelo.setGerenteComercial(teamComercialDto.getGerenteComercial());
			modelo.setUserEjecutivoCuenta(teamComercialDto.getUserEjecutivoCuenta());
			modelo.setEjecutivoCuenta(teamComercialDto.getEjecutivoCuenta());
			modelo.setUserIngenieroCuenta(teamComercialDto.getUserIngenieroCuenta());
			modelo.setIngenieroCuenta(teamComercialDto.getIngenieroCuenta());
			modelo.setUserAnalistaCobranzaDatos(teamComercialDto.getAnalistaCobranzaDatos());
			modelo.setAnalistaCobranzaDatos(teamComercialDto.getAnalistaCobranzaDatos());
			modelo.setUserAnalistaCobranzaVoz(teamComercialDto.getUserAnalistaCobranzaVoz());
			modelo.setAnalistaCobranzaVoz(teamComercialDto.getAnalistaCobranzaVoz());
			modelo.setUserAnalistaContratoVoz(teamComercialDto.getUserAnalistaContratoVoz());
			modelo.setAnalistaContratoVoz(teamComercialDto.getAnalistaContratoVoz());
			modelo.setUserSupervisorContratoVoz(teamComercialDto.getUserSupervisorContratoVoz());
			modelo.setSupervisorContratoVoz(teamComercialDto.getSupervisorContratoVoz());
			modelo.setUserSupervisorCobranzaVoz(teamComercialDto.getUserSupervisorCobranzaVoz());
			modelo.setSupervisorCobranzaVoz(teamComercialDto.getSupervisorCobranzaVoz());
			modelo.setUserSupervisorCobranzaDatos(teamComercialDto.getUserSupervisorCobranzaDatos());
			modelo.setSupervisorCobranzaDatos(teamComercialDto.getSupervisorCobranzaDatos());
			modelo.setUserResponsableCaring(teamComercialDto.getUserResponsableCaring());
			modelo.setResponsableCaring(teamComercialDto.getResponsableCaring());
			modelo.setUserAnalistaCaring(teamComercialDto.getUserAnalistaCaring());
			modelo.setAnalistaCaring(teamComercialDto.getAnalistaCaring());
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return modelo;
	}

}
