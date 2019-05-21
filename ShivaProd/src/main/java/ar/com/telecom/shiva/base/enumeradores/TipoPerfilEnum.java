package ar.com.telecom.shiva.base.enumeradores;

import ar.com.telecom.shiva.base.constantes.Constantes;

public enum TipoPerfilEnum {
	
	ANALISTA("Analista", "","ANALISTA"),
	CAJERO_PAGADOR("CajeroPagador", "","CAJEROPAGADOR"),
	SUPERVISOR("Supervisor", "Grupo de Supervisores","SUPERVISOR"),
	ADMIN_VALOR("AdminValor", "Grupo de Administrador de Valores","ADMINVALOR"),
	SUPERVISOR_ADMIN_VALOR("SupervisorAdminValor", "Grupo de Supervisor Administrador de Valores","SUPERVISORADMINVALOR"),
	SUPERVISOR_TALONARIO("SupervisorTalonario", "Grupo de Supervisor de Talonarios","SUPERVISORTALONARIO"),
	ADMIN_TALONARIO("AdminTalonario", "Grupo de Administrador de Talonarios","ADMINTALONARIO"),
	CONSULTA("Consulta", "Consulta","CONSULTA"),
	MANTENIMIENTO("Mantenimiento", "Mantenimiento","MANTENIMIENTO"),
	ANALISTA_COBRANZA("AnalistaCobranza","Analista Cobranza","ANALISTACOBRANZA"),
	SUPERVISOR_COBRANZA("SupervisorCobranza","Grupo de Supervisor de Cobranzas","SUPERVISORCOBRANZA"),
	REFERENTE_COBRANZA("ReferenteCobranza","Grupo de Referentes de Cobranzas","REFERENTECOBRANZA"),
	ANALISTA_OPERACION_MASIVA("AnalistaOperacionMasiva","Grupo de Supervisor de Cobranzas","ANALISTAOPERACIONMASIVA"),
	SUPERVISOR_OPERACION_MASIVA("SupervisorOperacionMasiva","Grupo de Supervisor de Operaciones Masivas","SUPERVISOROPERACIONMASIVA"),
	SUPERVISOR_OPERACIONES_ESPECIALES("SupervisorOperacionesEspeciales", "Grupo de Supervisores Operaciones Especiales", "SUPERVISOROPERACIONESESPECIALES"),
	ANALISTA_LEGAJOS_CHEQUES_RECHAZADOS("AnalistaLegajosChequesRechazados", "Grupo de Analistas de Legajos de Cheques Rechazados", "ANALISTALEGAJOSCHEQUESRECHAZADOS"),
	REFERENTE_CAJA("ReferenteCaja","Grupo de Referentes de Caja","REFERENTECAJA"),
	REFERENTE_OPERACIONES_EXTERNAS("ReferenteOperacionesExternas","Grupo de Referentes de Operaciones Externas","REFERENTEOPERACIONESEXTERNAS");
	
	String descripcion;
	String grupo;
	String nombreLdap;
	
	private TipoPerfilEnum(String descripcion, String grupo, String nombreLdap) {
	    this.descripcion = descripcion;
	    this.grupo = grupo;
	    this.nombreLdap = nombreLdap;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}

	public String grupo() {
	    return this.grupo;
	}
	
	public String nombreLdap() {
		return nombreLdap;
	}
	
	/**
	 * Recibe un String, busca el enum con ese nombre y devuelve un enum.
	 * @param descripcion
	 * @return
	 */
	public static TipoPerfilEnum getEnumByDescripcion(String descripcion) {
		
		for (TipoPerfilEnum campo : TipoPerfilEnum.values()) {
			String descPerfil = descripcion.split("_")[0]; 
			if (campo.descripcion().equalsIgnoreCase(descPerfil)) {
				return campo;
			}
		}
		        
		return null; 
	}
	
	/**
	 * Recibe un String, busca el enum con ese nombre y devuelve un enum.
	 * @param descripcion
	 * @return
	 */
	public static TipoPerfilEnum getEnumByNombreLdap(String nombreLdap) {
		
		for (TipoPerfilEnum campo : TipoPerfilEnum.values()) {
			String descNombreLdap = nombreLdap.split("_")[0]; 
			if (campo.nombreLdap().equalsIgnoreCase(descNombreLdap)) {
				return campo;
			}
		}
		        
		return null; 
	}
	
	public static String getApocopeResponsableApliManual(TipoPerfilEnum perfil) {
		String apocopeResponsable = Constantes.EMPTY_STRING;
		if (TipoPerfilEnum.REFERENTE_CAJA.equals(perfil)) {
			apocopeResponsable = "RC";
		} else if (TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS.equals(perfil)) {
			apocopeResponsable = "ROE";
		}
		return apocopeResponsable;
	}

}
