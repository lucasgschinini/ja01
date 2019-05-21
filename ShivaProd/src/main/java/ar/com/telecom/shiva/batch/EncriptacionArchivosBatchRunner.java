package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IEncriptacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class EncriptacionArchivosBatchRunner {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
 			Traza.auditoria(EncriptacionArchivosBatchRunner.class, "INICIO - BATCH ENCRIPTACION ARCHIVOS");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_ENCRIPTACION_ARCHIVOS_BATCH))
				throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//PROCESO
			//Este metodo es mas seguro
			//Se toma el original desde el backup, lo encripta y lo guarda en la tabla original (puede hacer varias veces)
			//Importante: Antes de ejecutar la encriptacion, se debera realizar backup de los archivos originales
			encriptarArchivosDesdeBackup();
			
			//Apto para los desarrolladores
			//Si cambio el algoritmo de encriptacion, podremos ejecutar este metodo 
			//pero hay tener muchisimo cuidado 
			//con respecto al metodo encriptarArchivos()
			//cambioEncriptacionArchivos();
			
			
			/**
			* Esto debido a varios cambios de algoritmos de encriptacion, 
			*  durante las pruebas unitarias, varios datos nuevos quedaron corruptas 
			* Esto se debe por invocar varias veces al metodo encriptarArchivos
			*
			* Con la primera version de este metodo encriptarArchivos, no es muy seguro
			* Hacerlo por unica vez sino chauuu pues estas encriptando varias veces
			* (tenes que saber exactamente N veces de ejecucion, para recuperar)
			* Por ejemplo: 
			* original --> encriptado1 --> encriptado2 ... encriptadoN
			* Para recuperar
			* encriptadoN --> encriptado2 --> encriptado1 --> original
			* 
			* Lamentablemente en el desarrollo algunos datos quedaron corruptos !!!!!!
			**/
			//encriptarArchivos();
			
			//Apto para los desarrolladores
			//Si cambio el algoritmo de encriptacion, podremos ejecutar este metodo 
			//pero hay tener muchisimo cuidado 
			//con respecto al metodo encriptarArchivos()
			//cambioEncriptacionArchivos();
			
			
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(EncriptacionArchivosBatchRunner.class, Mensajes.LISTENER_UNICA_INSTANCIA, e);
			System.exit(Constantes.SH_ERROR_INSTANCIA);
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			System.exit(Constantes.SH_ERROR);
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(EncriptacionArchivosBatchRunner.class, "Se ha finalizado el batch para encriptar archivos");
		}
		System.exit(0);
	}
	
	/**
	 * Permite encriptar los datos originales
	 * @throws ShivaExcepcion
	 */
	@SuppressWarnings("unused")
	private static void encriptarArchivos() throws ShivaExcepcion {
		try {
			IEncriptacionServicio servicio =  (IEncriptacionServicio) Configuracion.getBeanBatch("encriptacionServicio");
			servicio.encriptarArchivos();
			System.out.println("Se ha finalizado exitosamente el proceso encriptacion archivos");
			Traza.auditoria(EncriptacionArchivosBatchRunner.class, "---- Se ha finalizado exitosamente el proceso encriptacion de archivos");
		} catch (Throwable e) {
			Traza.error(EncriptacionArchivosBatchRunner.class, "Se ha producido un error en el proceso de encriptacion de archivos", e);
			Traza.advertencia(EncriptacionArchivosBatchRunner.class, "---- Se ha producido un error en el proceso de encriptacion de archivos");
			throw new ShivaExcepcion(e);
		}
	}
	
	/**
	 * Permite encriptar los datos originales desde el backup
	 * @throws ShivaExcepcion
	 */
	private static void encriptarArchivosDesdeBackup() throws ShivaExcepcion {
		try {
			IEncriptacionServicio servicio =  (IEncriptacionServicio) Configuracion.getBeanBatch("encriptacionServicio");
			servicio.encriptarArchivosDesdeBackup();
			System.out.println("Se ha finalizado exitosamente el proceso encriptacion archivos desde el backup");
			Traza.auditoria(EncriptacionArchivosBatchRunner.class, "---- Se ha finalizado exitosamente el proceso encriptacion de archivos desde el backup");
		} catch (Throwable e) {
			Traza.error(EncriptacionArchivosBatchRunner.class, "Se ha producido un error en el proceso de encriptacion de archivos desde el backup", e);
			Traza.advertencia(EncriptacionArchivosBatchRunner.class, "---- Se ha producido un error en el proceso de encriptacion de archivos desde el backup");
			throw new ShivaExcepcion(e);
		}
	}
	
	
	
	
	/**
	 * Permite cambiar de encriptacion a los datos que ya fueron encriptados
	 * @throws ShivaExcepcion
	 */
	@SuppressWarnings("unused")
	private static void cambioEncriptacionArchivos() throws ShivaExcepcion {
		try {
			IEncriptacionServicio servicio =  (IEncriptacionServicio) Configuracion.getBeanBatch("encriptacionServicio");
			servicio.cambioEncriptacionArchivos();
			System.out.println("Se ha finalizado exitosamente el proceso del cambio de encriptacion de archivos");
			Traza.auditoria(EncriptacionArchivosBatchRunner.class, "---- Se ha finalizado exitosamente el proceso del cambio de encriptacion de archivos");
		} catch (Throwable e) {
			Traza.error(EncriptacionArchivosBatchRunner.class, "Se ha producido un error en el proceso del cambio de encriptacion de archivos", e);
			Traza.advertencia(EncriptacionArchivosBatchRunner.class, "---- Se ha producido un error en el proceso del cambio de encriptacion de archivos");
			throw new ShivaExcepcion(e);
		}
	}
}
