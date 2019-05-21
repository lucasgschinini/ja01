package ar.com.telecom.shiva.base.utils.singleton;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.enumeradores.TipoRegistroEnum;
import ar.com.telecom.shiva.base.registros.datos.entrada.enumeradores.MicOperacionMasivaCamposEntradaEnum;
import ar.com.telecom.shiva.negocio.bean.ValidacionesAuxiliar;

/**
 * La clase ValidarAuxiliarSingleton no es Thread-safe
 * @author u578936
 *
 */
public class ValidarAuxiliarSingleton extends ValidacionesAuxiliar {

	private static ValidarAuxiliarSingleton SINGLETON = null;
	private StringBuffer validacionParcial = new StringBuffer();
	private String currentfileName = "";
	private long nrolinea = 0L;
	private TipoRegistroEnum tipoRegistro = TipoRegistroEnum.D;
	
	private ValidarAuxiliarSingleton() {
	}
	
	
	/**
	 * creador sincronizado para protegerse de posibles problemas
	 * multi-hilo para evitar instanciación múltiple
	 */
	private synchronized static void createInstance() {
		if (SINGLETON == null) { 
			SINGLETON = new ValidarAuxiliarSingleton();
		}
	}
	public static ValidarAuxiliarSingleton getInstance() {
		if (SINGLETON == null) {
			createInstance();
		}
		return SINGLETON;
	}
	public void setLinea(boolean sinNroLiena, String msg) {
		this.setString(sinNroLiena, msg);
	}
	public void setLinea(String msg) {
		this.setString(false, msg);
	}
	public void setLinea(MicOperacionMasivaCamposEntradaEnum campo, String msg) {
		this.setString(campo, false, msg);
	}
	private void setString(MicOperacionMasivaCamposEntradaEnum campo, boolean sinNroLinea, String msg) {
		if (!sinNroLinea) {
			this.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			this.getResultadoValidaciones().append(this.nrolinea);
			this.getResultadoValidaciones().append(". ");
			this.getResultadoValidaciones().append(campo.getNombreColumna());
			this.getResultadoValidaciones().append(". ");
		}
		this.getResultadoValidaciones().append(msg);
		this.getResultadoValidaciones().append(Constantes.RETORNO_WIN);
	}
	private void setString(boolean sinNroLinea, String msg) {
		if (!sinNroLinea) {
			this.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			this.getResultadoValidaciones().append(this.nrolinea);
			this.getResultadoValidaciones().append(". ");
		}
		this.getResultadoValidaciones().append(msg);
		this.getResultadoValidaciones().append(Constantes.RETORNO_WIN);
	}
	public void setLineHeader(String msg) {
		this.getResultadoValidaciones().append(tipoRegistro.descripcion());
		this.getResultadoValidaciones().append(": ");
		this.getResultadoValidaciones().append(msg);
		this.getResultadoValidaciones().append(Constantes.RETORNO_WIN);
	}
	public void setLineHeader(String definicion, String msg) {
		this.getResultadoValidaciones().append(definicion);
		this.getResultadoValidaciones().append(": ");
		this.getResultadoValidaciones().append(msg);
		this.getResultadoValidaciones().append(Constantes.RETORNO_WIN);
	}
	/**
	 * No esta sincronizado
	 */
	@Override
	public String toString() {
		String parcial = this.getResultadoValidaciones().toString();
		this.getResultadoValidaciones().delete(0, this.getResultadoValidaciones().length());
		//this.getResultadoValidaciones().append(currentfileName);
		//this.getResultadoValidaciones().append(": ");
		
		//this.getResultadoValidaciones().append(Constantes.RETORNO_WIN);
		//this.getResultadoValidaciones().append(Constantes.RETORNO_WIN);
		this.getResultadoValidaciones().append(parcial);
		
		String salida = this.getResultadoValidaciones().toString();
		this.getResultadoValidaciones().delete(0, this.getResultadoValidaciones().length());
		return salida;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Es un Singleton");
	}

	/**
	 * @return the currentfileName
	 */
	public String getCurrentfileName() {
		return currentfileName;
	}
	/**
	 * @param currentfileName the currentfileName to set
	 */
	public void setCurrentfileName(String currentfileName) {
		this.currentfileName = currentfileName;
		
	}

	public boolean isEmpty() {
		return this.getResultadoValidaciones().length() == 0;
	}
	public void clear() {
		this.getResultadoValidaciones().delete(0, this.getResultadoValidaciones().length());
	}
	public void reIniciar() {
		if (SINGLETON != null) { 
			SINGLETON = null;
			createInstance();
		}
	}
	/**
	 * @return the nrolinea
	 */
	public long getNrolinea() {
		return nrolinea;
	}
	/**
	 * @param nrolinea the nrolinea to set
	 */
	public void setNrolinea(long nrolinea) {
		this.nrolinea = nrolinea;
	}
	
	public static final int REG_CABECERA = 0;
	public static final int REG = 1;
	public static final int REG_PIE = 2;

	/**
	 * @return the tipoRegistro
	 */
	public TipoRegistroEnum getTipoRegistro() {
		return tipoRegistro;
	}


	/**
	 * @param tipoRegistro the tipoRegistro to set
	 */
	public void setTipoRegistro(TipoRegistroEnum tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}


	/**
	 * @return the validacionParcial
	 */
	public StringBuffer getValidacionParcial() {
		return validacionParcial;
	}


	/**
	 * @param validacionParcial the validacionParcial to set
	 */
	public void setValidacionParcial(StringBuffer validacionParcial) {
		this.validacionParcial = validacionParcial;
	}
	
}
