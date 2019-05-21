package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.registros.datos.entrada.MicOperacionMasivaEntrada;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvMasOperacionMasivaSimplificadoWorkFlow;


public interface IOperacionMasivaRegistroServicio {
	/**
	 * 
	 * @param listaShvMasRegistro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ShvMasRegistro> realizarGestionabiliadRegistrosOperacionMasiva(List<ShvMasRegistro> listaShvMasRegistro) throws NegocioExcepcion;
	/**
	 * 
	 * @param shvMasRegistro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvMasRegistro gestionabilidadDeRegistrosOperacionMasiva(ShvMasRegistro shvMasRegistro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param reg
	 * @throws NegocioExcepcion
	 */
	public List<ShvMasRegistro> volcarDatosArchivoEntraMic(MicOperacionMasivaEntrada reg) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param shvMas
	 * @throws NegocioExcepcion
	 */
	public void generarCobroByOperacionMasivaProcesada(ShvMasOperacionMasivaSimplificadoWorkFlow shvMas) throws NegocioExcepcion;
	
	public void generarCobroByListaShvMasRegistro(List<ShvMasRegistro> registros) throws NegocioExcepcion;
		
	
	public String generarArchivoOperacionMasivaRespuesta(List<ShvMasRegistro> listaRegistros, TipoArchivoOperacionMasivaEnum tipoOperacion);
	
	public String test();
}


