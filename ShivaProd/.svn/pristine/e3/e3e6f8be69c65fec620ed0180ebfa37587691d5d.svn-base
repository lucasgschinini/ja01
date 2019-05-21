package ar.com.telecom.shiva.presentacion.seguridad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IPermisosServicio;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.dto.menu.MenuDto;

/**
 * Responsable de validar el token. 
 */
public class TokenHandlerInterceptor extends HandlerInterceptorAdapter {

	@Autowired IPermisosServicio permisosServicio;
	@Autowired ILdapServicio ldapServicio;
	@Autowired IParametroServicio parametroServicio;

	private String urlExpiredLogin;
	private String urlExpiredSesion;
	private String urlErrorSinPerfiles;
	private String urlErrorCaptcha;

	private String rolesList;
	private String[] rolesGroups;
	private List<String[]> rolesInGroups = new ArrayList<>();


	@Override
	@Transactional(readOnly=true)
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,Origin,Content-Type, Accept");
		
		String requestURI = "";
		if (request instanceof HttpServletRequest) {
			requestURI = ((HttpServletRequest)request).getRequestURI();
		}

		if (requestURI.contains("ingresar")
				|| requestURI.contains("error")
				|| requestURI.contains("expirado")
				|| requestURI.contains("mantenimiento")
				|| requestURI.contains("expired")
				|| requestURI.contains("denegar")
				|| requestURI.contains("captcha")
				|| requestURI.contains("Captcha")
				|| requestURI.contains("usuarioNoAutorizado")
				|| requestURI.contains("usuarioSinPerfiles")
				|| requestURI.contains("salir")
				|| requestURI.contains("img")
				|| requestURI.contains("js")
				|| requestURI.contains("css")
				|| requestURI.contains("less")
				|| requestURI.contains("media")
				|| requestURI.contains("swf")
				|| requestURI.contains("fonts")) {

			return true;

		} else {
			String usuario = "anonymousUser";
			Collection<? extends GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

			Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (user instanceof LdapUserDetails) {
				LdapUserDetails ldapUser = (LdapUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				// Forzamos el usuario a mayusculas, ya que de otro modo los registros en la base 
				// o las busquedas quedarán erroneamente con mayuscula y minuscula, lo cuál puede generar resultados 
				// no correctos
				usuario = ldapUser.getUsername().toUpperCase();

				roles = getRolesLowered(ldapUser);

			} else {
				if (user instanceof String) {
					Traza.advertencia(getClass(), HttpServletResponse.SC_FORBIDDEN + " - " + Mensajes.USUARIO_EXPIRADO);
					response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso no autorizado");
					return false;
				}
			}

			//principal = login
			if (requestURI.contains("principal")) {
				Object authenticationDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
				if (authenticationDetails instanceof CustomWebAuthenticationDetails) {
					boolean isJcaptchaValido = ((CustomWebAuthenticationDetails)authenticationDetails).isCaptchaPassed(); 
					if (!isJcaptchaValido) {
						response.sendRedirect(response.encodeURL(request.getContextPath() + urlErrorCaptcha));
						return false;
					}
				}

				if (!Validaciones.isCollectionNotEmpty(roles)) {
					Traza.advertencia(getClass(), Utilidad.reemplazarMensajes(Mensajes.USUARIO_SIN_PERFILES, usuario));
					response.sendRedirect(response.encodeURL(request.getContextPath() + urlErrorSinPerfiles));
					return false;
				}

				UsuarioSesion usuarioSesion = new UsuarioSesion(request.getSession().getId(), usuario, roles);

				// Ahi va para guardar el thread actual para incluir el usuario en los logs
				boolean existeUsuarioLogueado = TokenSessionListener.existeUsuarioLogueado(usuarioSesion);
				if (existeUsuarioLogueado) {
					Traza.advertencia(getClass(), Utilidad.reemplazarMensajes(Mensajes.EXISTE_USUARIO_LOGUEADO, usuario));
				}
				Traza.auditoriaLogin(getClass(), Utilidad.reemplazarMensajes(Mensajes.USUARIO_AUTENTICADO, usuarioSesion.getUsuario()), usuarioSesion.getUsuario(), usuarioSesion.getRoles().toString());

				//Verifico que si el menu no este vacio.
				MenuDto menuDto = permisosServicio.obtenerMenuPerfil(Utilidad.getRolesToString(roles));
				if (Validaciones.isObjectNull(menuDto) || !Validaciones.isMapNotEmpty(menuDto.getNivel0())) {
					Traza.advertencia(getClass(), Utilidad.reemplazarMensajes(Mensajes.USUARIO_SIN_PERFILES, usuario));
					response.sendRedirect(response.encodeURL(request.getContextPath() + urlErrorSinPerfiles));
					return false;
				}
				menuDto.setMenuParameter();
				usuarioSesion.setMenu(menuDto);
				usuarioSesion.setPerfilesAcciones(permisosServicio.obtenerAccionesDePerfiles(Utilidad.getRolesToString(roles)));

				UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(usuarioSesion.getUsuario());
				if (usuarioLdap != null) {
					usuarioSesion.setNombreCompleto(usuarioLdap.getNombreCompleto());
					usuarioSesion.setMail(usuarioLdap.getMail());
				} else {
					/* Solo para local*/
					if (Constantes.ES_ENTORNO_DESA) {
						usuarioSesion.setNombreCompleto("nombreFulano apellidoFulano");
						usuarioSesion.setMail("fulano@accenture.com");
					} else {
						Traza.advertencia(getClass(), Mensajes.ACCESO_DENEGADO_LDAP);
						response.sendRedirect(response.encodeURL(request.getContextPath() + urlExpiredSesion));
						return false;
					}
				}

				request.getSession().setAttribute(Constantes.LOGIN, usuarioSesion);
				request.getSession().setAttribute(Constantes.LOGIN_USERNAME, usuarioSesion.getUsuario());

				Traza.auditoriaLogin(getClass(), Utilidad.reemplazarMensajes(Mensajes.USUARIO_LOGUEADO, usuarioSesion.getUsuario()), usuarioSesion.getUsuario());

				return true;

			} else {
				//Usuario ya autenticado en la session - Cualquier pagina 
				UsuarioSesion usuarioSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);

				if (Validaciones.isObjectNull(usuarioSesion)) {
					// Expirado
					Traza.advertencia(getClass(), Mensajes.USUARIO_EXPIRADO);
					response.sendRedirect(response.encodeURL(request.getContextPath() + urlExpiredSesion));
					return false;
				} else {
					usuario = usuarioSesion.getIdUsuario();
					
					// Verifico si la url es de un menu
					if (!Validaciones.isObjectNull(usuarioSesion.getMenu())) {
						
						if (
							!Validaciones.isObjectNull(request.getParameter(Constantes.IS_MENU_PARAN_NAME)) &&
							request.getParameter(Constantes.IS_MENU_PARAN_NAME).equals(Constantes.IS_MENU_PARAN_VALUE)
						) {
							if (usuarioSesion.getMenu().isUrlMenu(request.getContextPath(), requestURI)) {
								usuarioSesion.reiniciarVariablesDeSesion();
							}
						}
					}
				}
				

				boolean permitir = TokenSessionListener.permitirEsteUsuario(usuarioSesion);
				if (permitir) {
					//Para el uso de las trazas
					TokenSessionListener.addListSesionesAbiertas(usuarioSesion, false);

					Long limiteParaExportarBusquedaValores = parametroServicio.getLimiteParaExportarBusquedaValores();
					request.getSession().setAttribute("limiteParaExportarBusquedaValores", limiteParaExportarBusquedaValores);

					Long limiteParaExportarBusquedaCobros = parametroServicio.getLimiteParaExportarBusquedaCobros();
					request.getSession().setAttribute("limiteParaExportarBusquedaCobros", limiteParaExportarBusquedaCobros);

					return true;
				} else {
					// Doble usuario logueado
					Traza.advertencia(getClass(), Utilidad.reemplazarMensajes(Mensajes.EXISTE_USUARIO_LOGUEADO, usuario));
					response.sendRedirect(response.encodeURL(request.getContextPath() + urlExpiredLogin));
					return false;
				}
			}
		}
	}

	/**
	 * Usando la lista de orden.roles en shiva_${entorno}.properties
	 * chequeo si el rol más bajo está en los roles traidos del ldap del usuario
	 * Ejemplo: Si un usuario tiene roles ANALISTA y ADMINVALOR, se seleccionará ANALISTA, 
	 * hará break, y seguirá con el segundo grupo de roles (TALONARIO) y asi etc...
	 * 
	 * @param ldapUser
	 * @return
	 */
	private List<GrantedAuthority> getRolesLowered(LdapUserDetails ldapUser) {

		List<String> rolesSelected = new ArrayList<>();
		for (String[] rolesArray : rolesInGroups) {
			for (String role : rolesArray) {

				//CAJEROPAGADOR esta en la lista de [ANALISTA_TA_OGC, ANALISTA_TA_OYP, ANALISTA_PE_OGC, ANALISTA_PE_OYP, SUPERVISOR_TA_OGC ] ?
				if (ldapUser.getAuthorities().toString().contains(role)) {
					//mete ANALISTA que es el rol mas bajo y pasa al siguiente grupo SUPERVISORTALONARIO,ADMINTALONARIO
					rolesSelected.add(role); 
					break;
				}
			}
		}

		List<GrantedAuthority> rolesLowered = new ArrayList<GrantedAuthority>();
		String[] rolesInLdap = Utilidad.getRolesToString(ldapUser.getAuthorities()).split(",");
		for (String rolesLdap : rolesInLdap) {
			for (String rolesSelect : rolesSelected) {
				if (rolesLdap.startsWith(rolesSelect)) {
					rolesLowered.add((GrantedAuthority)new SimpleGrantedAuthority(rolesLdap));
				}
			}
		}

		return rolesLowered;
	}

	public String getUrlExpiredLogin() {
		return urlExpiredLogin;
	}

	public void setUrlExpiredLogin(String urlExpiredLogin) {
		this.urlExpiredLogin = urlExpiredLogin;
	}

	public String getUrlExpiredSesion() {
		return urlExpiredSesion;
	}

	public void setUrlExpiredSesion(String urlExpiredSesion) {
		this.urlExpiredSesion = urlExpiredSesion;
	}

	public String getUrlErrorSinPerfiles() {
		return urlErrorSinPerfiles;
	}

	public void setUrlErrorSinPerfiles(String urlErrorSinPerfiles) {
		this.urlErrorSinPerfiles = urlErrorSinPerfiles;
	}

	public String getRolesList() {
		return rolesList;
	}

	public void setRolesList(String rolesList) {
		this.rolesList = rolesList;
		this.rolesGroups = this.rolesList.split(";"); // de la propiedad orden.roles: CAJEROPAGADOR,ANALISTA,SUPERVISOR,ADMINVALOR,SUPERVISORADMINVALOR;SUPERVISORTALONARIO,ADMINTALONARIO
		for (String rolesGroup : rolesGroups) {
			rolesInGroups.add(rolesGroup.split(",")); // separo del string total: CAJEROPAGADOR,ANALISTA,SUPERVISOR,ADMINVALOR,SUPERVISORADMINVALOR
		}
	}
	public String getUrlErrorCaptcha() {
		return urlErrorCaptcha;
	}
	public void setUrlErrorCaptcha(String urlErrorCaptcha) {
		this.urlErrorCaptcha = urlErrorCaptcha;
	}

}
