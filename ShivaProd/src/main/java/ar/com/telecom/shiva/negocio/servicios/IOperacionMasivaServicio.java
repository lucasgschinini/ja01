package ar.com.telecom.shiva.negocio.servicios;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.OperacionMasivaBatchEmailEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.negocio.bean.ImportarOperacionesMasivasAuxiliar;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasivaArchivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ArchivoOperacionMasivaProcesadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaArchivoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaHistoricaDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteOperacionMasivaFiltro;

/**
 * Servicio para el manejo de los Archivos de Opraciones Masivas
 * @author u564030
 *
 */
public interface IOperacionMasivaServicio {

	/**
	 * Procesa el un Archivo de Operaciones Masivas y guarda cada uno de sus registros
	 * @throws PersistenciaExcepcion 
	 */
	ArchivoOperacionMasivaProcesadoDto procesarArchivo(OperacionMasivaDto operacionMasiva, boolean hayCambioTarea) throws NegocioExcepcion, PersistenciaExcepcion;

	public OperacionMasivaDto buscarOperacionMasiva(Long idOperacionMasiva) throws NegocioExcepcion;
	
	public ShvMasOperacionMasiva buscarOperacionMasivaModelo(Long idOperacionMasiva) throws NegocioExcepcion;

	public ShvMasOperacionMasivaArchivo buscarArchivoOperacionMasivaModelo(Long idOperacionMasiva) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param operacionMasivaFiltro
	 * @param b
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<OperacionMasivaDto> listarOperacionesMasivas(VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro) throws NegocioExcepcion; // buscar operacionesMasivas

	public void aprobarOperacionMasivaCambiarEstadoWorkFlow(OperacionMasivaDto operacionMasivaDto,  UsuarioSesion usuarioModificacion, String observacion) throws NegocioExcepcion;

	public void rechazarAprobacionOperacionMasivaCambiarEstadoWorkFlow(OperacionMasivaDto operacionMasivaDto,  UsuarioSesion usuarioModificacion, String observacion) throws NegocioExcepcion;

	public ComprobanteDto buscarAdjuntoOperacionMasiva(Long idAdjunto) throws NegocioExcepcion;

	public void anularTarea(Long idOperacionMasiva, UsuarioSesion userSesion) throws NegocioExcepcion;

	public void generarArchivoEntradaMic() throws NegocioExcepcion;

	public List<OperacionMasivaHistoricaDto> obtenerHistorialOperacionMasiva(String parameter) throws NegocioExcepcion;
	
	public OperacionMasivaArchivoDto buscarListaComprobantesXNombreArchivo(String nombreArchivo) throws NegocioExcepcion, PersistenciaExcepcion;
	
	public OperacionMasivaArchivoDto buscarListaComprobantesXIdArchivo(String idArchivo) throws NegocioExcepcion, PersistenciaExcepcion;
	
	public void anularOperacionMasiva(String idOperacionMasiva, String idUsuario) throws NegocioExcepcion, PersistenciaExcepcion;
	
	public boolean procesarTareaOperacionesMasivasSiebel (int cantidadRegistros, int cantidadRegistrosPorVuelta) throws NegocioExcepcion, PersistenciaExcepcion;
	
	public void cargarDatosBanales(OperacionMasivaDto operacionMasiva) throws PersistenciaExcepcion, NegocioExcepcion;

	ArchivoOperacionMasivaProcesadoDto buscarArchivoOperacionMasivaParaDescargar(Long idArchivo) throws NegocioExcepcion;
	
	public void procesarRegistrosOperacionesMasivasSiebel(List<ShvMasRegistro> listaShvMasRegistro) throws NegocioExcepcion, PersistenciaExcepcion;
	
	public String obtenerObseHistorial(OperacionMasivaDto operacionMasivaDto, ShvMasOperacionMasiva operacionMasivaModelo) throws NegocioExcepcion;
	
	public void actualizarContadoresEnOperacionesMasivas() throws NegocioExcepcion;
	
	public void actualizarEstadoOperacionesMasivasSinRegistrosEnProceso() throws NegocioExcepcion;
	
	public String generarArchivoOperacionMasivaRespuesta(Long idOperacionMasiva) throws NegocioExcepcion, PersistenciaExcepcion;
	/**
	 * Envio de mail operacion masiva
	 * @param cuerpoMail
	 * @param tipoMail
	 * @throws NegocioExcepcion
	 */
	public void enviarMailRta(OperacionMasivaBatchEmailEnum tipoMail, String ... arg) throws NegocioExcepcion;
	
	/**
	 * Valida los registros de una archivo de operacionmasiva en el Alta del Online
	 * @param registros
	 * @param operacionMasiva
	 * @param file
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ImportarOperacionesMasivasAuxiliar validarRegistros(OperacionMasivaDto operacionMasiva) throws NegocioExcepcion;
	
	public FilenameFilter filtrarArchivosEntradaDeOperacionMasivaMic();
	
	public boolean procesarArchivoInterfazMicSalida(File file) throws NegocioExcepcion, ShivaExcepcion;
		
	
}


