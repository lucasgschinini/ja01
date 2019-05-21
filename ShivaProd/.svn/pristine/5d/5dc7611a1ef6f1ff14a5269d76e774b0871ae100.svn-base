package ar.com.telecom.shiva.presentacion.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ArchivoAVCFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.TalonarioFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroOnlineFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteOperacionMasivaFiltro;

public class UsuarioSesion extends PermisosSesion {
	
	private static final long serialVersionUID = -5590617926902614130L;
	
	private String idSesion;
	private String usuario;
	private Date timeStartSesion;
	
	private String idUsuario;
	private String nombreCompleto;
	private String mail;
	private byte fileBytes[] = null;
	private String fileName = "";
	
	private List<String> retorno = new ArrayList<String>();
	private String currentThreadName;
	
	/**
	 * Constructor
	 * @param idSesion
	 * @param usuario
	 * @param roles
	 * @param menu
	 * @throws NegocioExcepcion 
	 */
	public UsuarioSesion(String idSesion, String usuario, Collection<? extends GrantedAuthority> roles) {
		super(roles);
		
		this.timeStartSesion = new Date();
		this.idSesion = idSesion;
		this.usuario = usuario;
		this.idUsuario = usuario;
	}
	
	/****************************************************************************************
	 * Getter & Setters
	 ***************************************************************************************/
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getTimeStartSesion() {
		return timeStartSesion;
	}

	public void setTimeStartSesion(Date timeStartSesion) {
		this.timeStartSesion = timeStartSesion;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCurrentThreadName() {
		return currentThreadName;
	}

	public void setCurrentThreadName(String currentThreadName) {
		this.currentThreadName = currentThreadName;
	}

	/**
	 * @return the fileBytes
	 */
	public byte[] getFileBytes() {
		return fileBytes;
	}
	/**
	 * @param fileBytes the fileBytes to set
	 */
	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the retorno
	 */
	public List<String> getRetorno() {
		return retorno;
	}

	/**
	 * @param retorno the retorno to set
	 */
	public void setRetorno(List<String> retorno) {
		this.retorno = retorno;
	}
	
	

	//Se inicializan algunas variables para que al entrar desde el Menu no quede ningun dato innecesario cargado.
	public void reiniciarVariablesDeSesion() {
		setBoletaSinValorFiltro(new BoletaSinValorFiltro());
		setArchivoAVCFiltro(new ArchivoAVCFiltro());
		setTalonarioFiltro(new TalonarioFiltro());
		setTareasFiltro(null);
		setCobroFiltro(new VistaSoporteCobroOnlineFiltro());
		setDescobroFiltro(new VistaSoporteDescobroFiltro());
		setOperacionMasivaFiltro(new VistaSoporteOperacionMasivaFiltro());
		// Para los filtro de tabla paginadas post Face III
		setControlPaginado(null);
		// Para la nueva pantalla de confirmacion de aplicacion manual
		setComprobantesAGuardar(new ArrayList<ComprobanteDto>());
		setCodigosOperacionesExternasAGuardar(new ArrayList<CodigoOperacionExternaDto>());
	}
}
