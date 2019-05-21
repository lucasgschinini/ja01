package ar.com.telecom.shiva.presentacion.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.AccionesDeUsuarioEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuDto;

public class PermisosSesion extends ParametrosSesion {
	
	private static final long serialVersionUID = 1;
	
	private MenuDto menu;
	private Collection<? extends GrantedAuthority> roles;
	private Collection<String> perfilesAcciones = new ArrayList<String>();
	
	/**
	 * Constructor 
	 */
	public PermisosSesion()  {
	}
	
	/**
	 * Constructor
	 * @param idSesion
	 * @param usuario
	 * @param roles
	 * @param menu
	 * @param perfilesAcciones
	 * @throws NegocioExcepcion 
	 */
	public PermisosSesion(Collection<? extends GrantedAuthority> roles) {
		this.roles = roles;
	}
	
	/**
	 * Metodo que obtiene los perfiles a partir de los roles del usuario ingresado
	 * @return
	 */
	public Collection<String> getPerfiles() {
		List<String> list = new ArrayList<String>();
		if (Validaciones.isCollectionNotEmpty(roles)) {
			String[] perfiles = dividirEnPerfiles(Utilidad.getRolesToString(roles));
			for (String perfil : perfiles) {
				String[] array = dividirAtributosPerfil(perfil);
				if (array.length >= (Constantes.POSICION_PERFIL_LDAP+1)) {
					String idPerfil= dividirAtributosPerfil(perfil)[Constantes.POSICION_PERFIL_LDAP];
					if (!list.contains(idPerfil)) {
						list.add(idPerfil);
					}
				}
			}
		}
		
		return list;
	}
	
	/**
	 * Obtiene los perfiles completos (Empresa_Perfil_Segmento) del Ususario.
	 * @return Una <code>Collection</code> con todos los roles del usuario logueado.
	 */
	public Collection<String> getPerfilesCompletos() {
		List<String> list = new ArrayList<String>();
		if (Validaciones.isCollectionNotEmpty(roles)) {
			String[] perfiles = dividirEnPerfiles(Utilidad.getRolesToString(roles));
			for (String perfil : perfiles) {
				if (!list.contains(perfil)) {
					list.add(perfil);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * Metodo que obtiene los ID de las empresas a partir de los roles del usuario ingresado
	 * @return
	 */
	public Collection<String> getIdEmpresas() {
		List<String> list = new ArrayList<String>();
		if (Validaciones.isCollectionNotEmpty(roles)) {
			String[] perfiles = dividirEnPerfiles(Utilidad.getRolesToString(roles));
			for (String perfil : perfiles) {
				String[] array = dividirAtributosPerfil(perfil);
				if (array.length >= (Constantes.POSICION_EMPRESA_LDAP+1)) {
					String idEmpresa= dividirAtributosPerfil(perfil)[Constantes.POSICION_EMPRESA_LDAP];
					if (!list.contains(idEmpresa)) {
						list.add(idEmpresa);
					}
				}
			}
		}
		
		return list;
	}
	
	/**
	 * Metodo que obtiene los ID de los segmentos a partir de los roles del usuario ingresado
	 * @return
	 */
	public Collection<String> getIdSegmentos() {
		List<String> list = new ArrayList<String>();
		if (Validaciones.isCollectionNotEmpty(roles)) {
			String[] perfiles = dividirEnPerfiles(Utilidad.getRolesToString(roles));
			for (String perfil : perfiles) {
				String[] array = dividirAtributosPerfil(perfil);
				if (array.length == (Constantes.POSICION_SEGMENTO_LDAP+1)) {
					String idSegmento= dividirAtributosPerfil(perfil)[Constantes.POSICION_SEGMENTO_LDAP];
					if (!list.contains(idSegmento)) {
						list.add(idSegmento);
					}
				}
			}
		}
		
		return list;
	}

	/**
	 * Metodo que obtiene los ID de las empresas a partir de los roles del usuario ingresado
	 * y de un perfil seleccionado
	 * @return
	 */
	public Collection<String> getIdEmpresas(String perfilSeleccionado) {
		List<String> list = new ArrayList<String>();
		if (Validaciones.isCollectionNotEmpty(roles)) {
			String[] perfiles = dividirEnPerfiles(Utilidad.getRolesToString(roles));
			for (String perfil : perfiles) {
				String[] array = dividirAtributosPerfil(perfil);
				if (array.length >= (Constantes.POSICION_PERFIL_LDAP+1)) {
					String idPerfil= dividirAtributosPerfil(perfil)[Constantes.POSICION_PERFIL_LDAP];
					
					if (perfilSeleccionado.equalsIgnoreCase(idPerfil)) {
						if (array.length >= (Constantes.POSICION_EMPRESA_LDAP+1)) {
							String idEmpresa= dividirAtributosPerfil(perfil)[Constantes.POSICION_EMPRESA_LDAP];
							if (!list.contains(idEmpresa)) {
								list.add(idEmpresa);
							}
						}
					}
				}
			}
		}
		
		return list;
	}
	
	/**
	 * Metodo que obtiene los ID de los segmentos a partir de los roles del usuario ingresado 
	 * y de un perfil seleccionado
	 * @return
	 */
	public Collection<String> getIdSegmentos(String perfilSeleccionado) {
		List<String> list = new ArrayList<String>();
		if (Validaciones.isCollectionNotEmpty(roles)) {
			String[] perfiles = dividirEnPerfiles(Utilidad.getRolesToString(roles));
			for (String perfil : perfiles) {
				String[] array = dividirAtributosPerfil(perfil);
				if (array.length >= (Constantes.POSICION_PERFIL_LDAP+1)) {
					String idPerfil= dividirAtributosPerfil(perfil)[Constantes.POSICION_PERFIL_LDAP];
					
					if (perfilSeleccionado.equalsIgnoreCase(idPerfil)) {
						if (array.length == (Constantes.POSICION_SEGMENTO_LDAP+1)) {
							String idSegmento= dividirAtributosPerfil(perfil)[Constantes.POSICION_SEGMENTO_LDAP];
							if (!list.contains(idSegmento)) {
								list.add(idSegmento);
							}
						}
					}
				}
			}
		}
		
		return list;
	}
	
	
	/**
	 * Determino si el usuario logeado es un supervisor talonario
	 * @return
	 */
	public boolean esSupervisorTalonario() {
		Collection<String> perfiles = getPerfiles();
		for (String perfil : perfiles) {
			if(Constantes.SUPERVISOR_TALONARIO.equalsIgnoreCase(perfil)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Verifico si es supervisor Talonario
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	
	public boolean getEsSupervisorTalonario() {
		return this.esSupervisorTalonario();
	}
	
	/**
	 * Determino si el usuario logeado es un supervisor
	 * @return
	 */
	public boolean esSupervisor() {
		Collection<String> perfiles = getPerfiles();
		for (String perfil : perfiles) {
			if(Constantes.SUPERVISOR.equalsIgnoreCase(perfil)){
				return true;
			}
		}
		return false;
	}
	
	public boolean esSupervisorOperacionesEspeciales() {
		Collection<String> perfiles = getPerfiles();
		for (String perfil : perfiles) {
			if(Constantes.SUPERVISOR_OPERACIONES_ESPECIALES.equalsIgnoreCase(perfil)){
				return true;
			}
		}
		return false;
	}
	
	public boolean getEsSupervisorOperacionesEspeciales(){
		return this.esSupervisorOperacionesEspeciales();
	}
	/**
	 * Verifico que si es supervisor
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsSupervisor() {
		return this.esSupervisor();
	}

	/**
	 * Determino si el usuario logeado es un Analista
	 * @return
	 */
	public boolean esAnalista() {
		Collection<String> perfiles = getPerfiles();
		for (String perfil : perfiles) {
			if(Constantes.ANALISTA.equalsIgnoreCase(perfil)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Determino si el usuario logeado es un Cajero Pagador
	 * @return
	 */
	public boolean esCajeroPagador() {
		Collection<String> perfiles = getPerfiles();
		for (String perfil : perfiles) {
			if(Constantes.CAJERO_PAGADOR.equalsIgnoreCase(perfil)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Verifico que si es Cajero Pagador
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsCajeroPagador() {
		return this.esCajeroPagador();
	}

	/**
	 * Determino que si el usuario logueado es administrador de talonarios
	 * @return
	 */
	public Boolean esAdminTalonario() {
		Collection<String> perfiles = getPerfiles();
		for (String perfil : perfiles) {
			if(Constantes.ADMIN_TALONARIO.equalsIgnoreCase(perfil)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Verifico que si es administrador de talonarios
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsAdminTalonario() {
		return this.esAdminTalonario();
	}
	
	public boolean getEsAnalista() {
		return this.esAnalista();
	}
	
	/**
	 * Determino que si el usuario logueado es administrador de valores
	 * @return
	 */
	public Boolean esAdminValor() {
		Collection<String> perfiles = getPerfiles();
		for (String perfil : perfiles) {
			if(Constantes.ADMIN_VALOR.equalsIgnoreCase(perfil)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifico que si es administrador de valores
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsAdminValor() {
		return this.esAdminValor();
	}
	
	/**
	 * Determino que si el usuario logueado es supervisor administrador de valores
	 * @return
	 */
	public Boolean esSupervisorAdminValor() {
		Collection<String> perfiles = getPerfiles();
		for (String perfil : perfiles) {
			if(Constantes.SUPERVISOR_ADMIN_VALOR.equalsIgnoreCase(perfil)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifico que si es supeervisor administrador de valores
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsSupervisorAdminValor() {
		return this.esSupervisorAdminValor();
	}
	
    /**
    * Determino que si el usuario logueado es perfil de Consulta
    * @return
    */
    public Boolean esPerfilConsulta() {
          Collection<String> perfiles = getPerfiles();
          for (String perfil : perfiles) {
        	  if (Constantes.CONSULTA.equalsIgnoreCase(perfil)) {
        		  return true;
              }
          }
          return false;
    }
	
	/**
	 * Verifico que si es perfil de Consulta
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsPerfilConsulta() {
		return this.esPerfilConsulta();
	}
	
    /**
    * Determino que si el usuario logueado es perfil de Mantenimiento
    * @return
    */
    public Boolean esPerfilMantenimiento() {
          Collection<String> perfiles = getPerfiles();
          for (String perfil : perfiles) {
        	  if (Constantes.MANTENIMIENTO.equalsIgnoreCase(perfil)) {
        		  return true;
              }
          }
          return false;
    }
    
	/**
	 * Verifico que si es perfil mantenimiento
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsPerfilMantenimiento() {
		return this.esPerfilMantenimiento();
	} 
	
	/**
    * Determino que si el usuario logueado es perfil de SupervisorCobranza
    * @return
    */
    public Boolean esPerfilSupervisorCobranza() {
          Collection<String> perfiles = getPerfiles();
          for (String perfil : perfiles) {
        	  if (Constantes.SUPERVISOR_COBRANZA.equalsIgnoreCase(perfil)) {
        		  return true;
              }
          }
          return false;
    }
    
    /**
     * Determino que si el usuario logueado es perfil de ReferenteCobranza
     * @return
     */
     public Boolean esReferenteCobranza() {
           Collection<String> perfiles = getPerfiles();
           for (String perfil : perfiles) {
         	  if (Constantes.REFERENTE_COBRANZA.equalsIgnoreCase(perfil)) {
         		  return true;
               }
           }
           return false;
     }
    
	/**
	 * Verifico que si es perfil SupervisorCobranza
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsPerfilSupervisorCobranza() {
		return this.esPerfilSupervisorCobranza();
	}  
	
	/**
	 * Verifico que si es perfil ReferenteCobranza
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsReferenteCobranza() {
		return this.esReferenteCobranza();
	}    
		
	/**
    * Determino que si el usuario logueado es perfil de AnalistaCobranza
    * @return
    */
    public Boolean esPerfilAnalistaCobranza() {
          Collection<String> perfiles = getPerfiles();
          for (String perfil : perfiles) {
        	  if (Constantes.ANALISTA_COBRANZA.equalsIgnoreCase(perfil)) {
        		  return true;
              }
          }
          return false;
    }
    
	/**
	 * Verifico que si es perfil AnalistaCobranza
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsPerfilAnalistaCobranza() {
		return this.esPerfilAnalistaCobranza();
	}
	
	/**
    * Determino que si el usuario logueado es perfil de AnalistaOperacionMasiva
    * @return
    */
    public Boolean esPerfilAnalistaOperacionMasiva() {
          Collection<String> perfiles = getPerfiles();
          for (String perfil : perfiles) {
        	  if (Constantes.ANALISTA_OPERACION_MASIVA.equalsIgnoreCase(perfil)) {
        		  return true;
              }
          }
          return false;
    }
    
	/**
	 * Verifico que si es perfil AnalistaOperacionMasiva
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsPerfilAnalistaOperacionMasiva() {
		return this.esPerfilAnalistaOperacionMasiva();
	}  
	
	/**
    * Determino que si el usuario logueado es perfil de SupervisorOperacionMasiva
    * @return
    */
    public Boolean esPerfilSupervisorOperacionMasiva() {
          Collection<String> perfiles = getPerfiles();
          for (String perfil : perfiles) {
        	  if (Constantes.SUPERVISOR_OPERACION_MASIVA.equalsIgnoreCase(perfil)) {
        		  return true;
              }
          }
          return false;
    }
    
	/**
	 * Verifico que si es perfil SupervisorOperacionMasiva
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsPerfilSupervisorOperacionMasiva() {
		return this.esPerfilSupervisorOperacionMasiva();
	}  

	/**
     * Determino que si el usuario logueado es perfil de SupervisorOperacionesEspeciales
     * @return
     */
     public Boolean esPerfilSupervisorOperacionesEspeciales() {
          Collection<String> perfiles = getPerfiles();
          for (String perfil : perfiles) {
        	  if (Constantes.SUPERVISOR_OPERACIONES_ESPECIALES.equalsIgnoreCase(perfil)) {
        		  return true;
              }
          }
          return false;
     }
    
	/**
	 * Verifico que si es perfil SupervisorOperacionesEspeciales
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsPerfilSupervisorOperacionesEspeciales() {
		return this.esPerfilSupervisorOperacionesEspeciales();
	}  
	
	/**
     * Determino que si el usuario logueado es perfil de Analista de Legajos de Cheques Rechazados
     * @return
     */
     public Boolean esAnalistaLegajosChequesRechazados() {
          Collection<String> perfiles = getPerfiles();
          for (String perfil : perfiles) {
        	  if (Constantes.ANALISTA_LEGAJOS_CHEQUES_RECHAZADOS.equalsIgnoreCase(perfil)) {
        		  return true;
              }
          }
          return false;
     }
    
	/**
	 * Verifico que si es perfil Analista de Legajos de Cheques Rechazados
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsAnalistaLegajosChequesRechazados() {
		return this.esAnalistaLegajosChequesRechazados();
	}  
	
	/**
     * Determino que si el usuario logueado es perfil de Referente de Caja
     * @return
     */
     public Boolean esReferenteCaja() {
           Collection<String> perfiles = getPerfiles();
           for (String perfil : perfiles) {
         	  if (Constantes.REFERENTE_CAJA.equalsIgnoreCase(perfil)) {
         		  return true;
               }
           }
           return false;
     }
    
	/**
	 * Verifico que si es perfil Referente de Caja
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsReferenteCaja() {
		return this.esReferenteCaja();
	}
	
	/**
     * Determino que si el usuario logueado es perfil de Referente de Operaciones externas
     * @return
     */
     public Boolean esReferenteOperacionesExternas() {
           Collection<String> perfiles = getPerfiles();
           for (String perfil : perfiles) {
         	  if (Constantes.REFERENTE_OPERACIONES_EXTERNAS.equalsIgnoreCase(perfil)) {
         		  return true;
               }
           }
           return false;
     }
    
	/**
	 * Verifico que si es perfil Referente de Operaciones externas
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getEsReferenteOperacionesExternas() {
		return this.esReferenteOperacionesExternas();
	}
	
	/*************************************************************************************
	 * Permisos a los botones
	 ************************************************************************************/
	
	/**
	 * Verifico que si tiene permiso para habilitar el boton
	 * @return
	 */
	protected boolean tienePermiso(String accion) {
		if (perfilesAcciones.contains(accion)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Para enviar al jsp usando sessionScope 
	 * @return
	 */
	public boolean getPuedeAnularBoletaSinValor() {
		return tienePermiso(AccionesDeUsuarioEnum.ANULAR_BOLETA_SIN_VALOR.name());
	}
	
	/**
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getPuedeEnviarMailBoletaSinValor() {
		return tienePermiso(AccionesDeUsuarioEnum.ENVIAR_MAIL_BOLETA_SIN_VALOR.name());
	}
	
	/**
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getPuedeImprimirBoletaSinValor() {
		return tienePermiso(AccionesDeUsuarioEnum.IMPRIMIR_BOLETA_SIN_VALOR.name());
	}
	
	/**
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getPuedeModificarBoletaSinValor() {
		return tienePermiso(AccionesDeUsuarioEnum.MODIFICAR_BOLETA_SIN_VALOR.name());
	}
	
	/**
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getPuedeModificarTalonario() {
		return tienePermiso(AccionesDeUsuarioEnum.MODIFICAR_TALONARIO.name());
	}
	
	/**
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getPuedeRechazarTalonario() {
		return tienePermiso(AccionesDeUsuarioEnum.RECHAZAR_TALONARIO.name());
	}
	
	/**
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getPuedeAnularTalonario() {
		return tienePermiso(AccionesDeUsuarioEnum.ANULAR_TALONARIO.name());
	}
	
	/**
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getPuedeRendirTalonario() {
		return tienePermiso(AccionesDeUsuarioEnum.RENDIR_TALONARIO.name());
	}
	
	/**
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	public boolean getPuedeModificarReciboPreImpreso() {
		return tienePermiso(AccionesDeUsuarioEnum.MODIFICAR_RECIBO_PRE_IMPRESO.name());
	}
	
	/**
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	
	public boolean getPuedeModificarCobro(){
		
		return tienePermiso(AccionesDeUsuarioEnum.MODIFICAR_COBRO.name());
	}
	
	/**
	 * Para enviar al jsp usando sessionScope
	 * @return
	 */
	
	public boolean getPuedeAnularCobro(){
		
		return tienePermiso(AccionesDeUsuarioEnum.ANULAR_COBRO.name());
	}
	
	/****************************************************************************************
	 * Metodos Privados
	 ***************************************************************************************/
	/**
	 * Divide al perfil de la session en atributos
	 * @param perfilSesion --> Empresa_Perfil_Segmento
	 * @return {Empresa,Perfil,Segmento}
	 */
	private String[] dividirAtributosPerfil(String perfilSesion) {
		return perfilSesion.split("_");
	}
	
	/**
	 * Divide perfiles de la session en perfiles
	 * @param perfilSesion --> "Empresa_Perfil_Segmento,Empresa_Perfil_Segmento" 
	 * @return {Empresa_Perfil_Segmento,Empresa_Perfil_Segmento}
	 */
	private String[] dividirEnPerfiles(String perfilSesion) {
		return perfilSesion.split(",");
	}
	
	/****************************************************************************************
	 * Getter & Setters
	 ***************************************************************************************/
	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}
	public void setRoles(Collection<? extends GrantedAuthority> roles) {
		this.roles = roles;
	}
	
	public Collection<String> getPerfilesAcciones() {
		return perfilesAcciones;
	}
	public void setPerfilesAcciones(Collection<String> perfilesAcciones) {
		this.perfilesAcciones = perfilesAcciones;
	}

	public MenuDto getMenu() {
		return menu;
	}
	public void setMenu(MenuDto menu) {
		this.menu = menu;
	}
	
}
