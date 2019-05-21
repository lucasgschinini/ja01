package ar.com.telecom.shiva.presentacion.bean.dto.ldap;

import java.util.ArrayList;
import java.util.Collection;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;

public class UsuarioLdapDto extends DTO {
	
	private static final long serialVersionUID = -4119979548731350868L;
	
	private String tuid;
	private String nombreCompleto;
	private String mail;
	
	private Collection<RolLdapDto> rol = new ArrayList<RolLdapDto>();

	public String getTuid() {
		return tuid;
	}

	public void setTuid(String tuid) {
		this.tuid = tuid;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Collection<RolLdapDto> getRol() {
		return rol;
	}

	public void setRol(Collection<RolLdapDto> rol) {
		this.rol = rol;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * Retorna si el usuario posee perfil de Analista
	 * 
	 * @return
	 */
	public boolean esAnalista() {
		return verificarExistenciaPerfil(Constantes.ANALISTA);
	}
	
	/**
	 * Retorna si el usuario posee perfil de Cajero Pagador
	 * 
	 * @return
	 */
	public boolean esCajeroPagador() {
		return verificarExistenciaPerfil(Constantes.CAJERO_PAGADOR);
	}

	/**
	 * Retorna si el usuario posee perfil de Supervisor
	 * 
	 * @return
	 */
	public boolean esSupervisor() {
		return verificarExistenciaPerfil(Constantes.SUPERVISOR);
	}
	
	/**
	 * Retorna si el usuario posee perfil de Administrador de Valores
	 * 
	 * @return
	 */
	public boolean esAdministradorDeValores() {
		return verificarExistenciaPerfil(Constantes.ADMIN_VALOR);
	}
	
	/**
	 * Retorna si el usuario posee perfil de Supervisor Administrador de Valores
	 * 
	 * @return
	 */
	public boolean esSupervisorAdministradorDeValores() {
		return verificarExistenciaPerfil(Constantes.SUPERVISOR_ADMIN_VALOR);
	}
	
	/**
	 * Retorna si el usuario posee perfil de Administrador de Talonarios
	 * 
	 * @return
	 */
	public boolean esAdministradorDeTalonarios() {
		return verificarExistenciaPerfil(Constantes.ADMIN_TALONARIO);
		
	}
	
	/**
	 * Retorna si el usuario posee perfil de Supervisor Administrador de Talonarios
	 * 
	 * @return
	 */
	public boolean esSupervisorAdministradorDeTalonarios() {
		return verificarExistenciaPerfil(Constantes.SUPERVISOR_TALONARIO);
	}
	
	/**
	 * Retorna si el usuario posee perfil de Analista de Cobranza
	 * 
	 * @return 
	 */
	public boolean esAnalistaCobranza() {
		return verificarExistenciaPerfil(Constantes.ANALISTA_COBRANZA);
	}

	/**
	 * Verifica la existencia de un perfil dado dentro de la lista de roles del usuario
	 * 
	 * @param perfil
	 * @return
	 */
	private boolean verificarExistenciaPerfil(String perfil) {
		
		for (RolLdapDto rolLDap: rol) {
			 if (perfil.equalsIgnoreCase(rolLDap.getPerfil())) {
				 return true;
			 }
		}
		return false;
	}
	
	 /**
     * Retorna si el usuario posee perfil de Analista de Cobranza
     *
      * @return
      */
     public boolean esSupervisorCobranza() {
     	return verificarExistenciaPerfil(Constantes.SUPERVISOR_COBRANZA_NAME);
     }
     
     /**
      * Retorna si el usuario posee perfil de Referente de Cobranza
      *
      * @return
      */
     public boolean esReferenteCobranza() {
    	 return verificarExistenciaPerfil(Constantes.REFERENTE_COBRANZA);
     }
     
     /**
      * Retorna si el usuario posee perfil de Analista Operacion Masiva
      *
      * @return
      */
     public boolean esAnalistaOperacionMasiva() {
    	 return verificarExistenciaPerfil(Constantes.ANALISTA_OPERACION_MASIVA);
     }
     
     /**
      * Retorna si el usuario posee perfil de Supervisor Operaciones Especiales
      *
      * @return
      */
     public boolean esSupervisorOperacionesEspeciales() {
    	 return verificarExistenciaPerfil(Constantes.SUPERVISOR_OPERACIONES_ESPECIALES);
     }
     
     /**
      * Retorna si el usuario posee perfil de Analista de Legajos de Cheques Rechazados
      *
      * @return
      */
     public boolean esAnalistaLegajosChequesRechazados() {
    	 return verificarExistenciaPerfil(Constantes.ANALISTA_LEGAJOS_CHEQUES_RECHAZADOS);
     }
     
     /**
      * Retorna si el usuario posee perfil de Referente de Caja
      *
      * @return
      */
     public boolean esReferenteCaja() {
    	 return verificarExistenciaPerfil(Constantes.REFERENTE_CAJA);
     }
     
     /**
      * Retorna si el usuario posee perfil de Referente de Operaciones externas
      *
      * @return
      */
     public boolean esReferenteOperacionesExternas() {
    	 return verificarExistenciaPerfil(Constantes.REFERENTE_OPERACIONES_EXTERNAS);
     }
      
}