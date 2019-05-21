package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.List;

import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IEncriptacionServicio;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoAdjuntoDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjuntoAux;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjuntoBck;

public class EncriptacionServicioImpl implements IEncriptacionServicio {

	@Autowired	
	private IDocumentoAdjuntoDao documentoAdjuntoDao;

	@Autowired	
	private IGenericoDao genericoDao;

	@Autowired 
	StandardPBEByteEncryptor byteEncryptor;
	@Autowired 
	StandardPBEByteEncryptor otherByteEncryptorII;
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation=Propagation.REQUIRED)
	public void encriptarArchivos() throws NegocioExcepcion {
		final int cantXPag = 100;
		int paginas = 0;
		try {
			List<ShvDocDocumentoAdjuntoAux> adjuntosSinEncriptar = null;
			byte[] encryptedBytes = null;
			
			Long count = genericoDao.count(ShvDocDocumentoAdjuntoAux.class);
			paginas = count.intValue()/cantXPag + ((count.intValue()%cantXPag > 0)? 1 : 0);
			
			for (int pag=1; pag <= paginas; pag++) {
				adjuntosSinEncriptar = documentoAdjuntoDao.listarDocsAdjuntos(pag, cantXPag);
				
				for (ShvDocDocumentoAdjuntoAux adjuntoSinEncriptar : adjuntosSinEncriptar) {
					if (adjuntoSinEncriptar.getArchivoAdjunto() != null) {
						try {
							encryptedBytes = byteEncryptor.encrypt(adjuntoSinEncriptar.getArchivoAdjunto());
							
							adjuntoSinEncriptar.setArchivoAdjunto(encryptedBytes);
							documentoAdjuntoDao.guardar(adjuntoSinEncriptar);
							
							String mensaje = adjuntoSinEncriptar.getIdValorAdjunto() + ": encriptado";
							System.out.println(mensaje);
							Traza.auditoria(getClass(), mensaje);
							
						} catch (Exception e1) {
							String mensaje = adjuntoSinEncriptar.getIdValorAdjunto() + ": error al encriptar --> "+ e1.toString();
							System.out.println(mensaje);
							Traza.auditoria(getClass(), mensaje);
						}
					}
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation=Propagation.REQUIRED)
	public void encriptarArchivosDesdeBackup() throws NegocioExcepcion {
		final int cantXPag = 100;
		int paginas = 0;
		try {
			List<ShvDocDocumentoAdjuntoBck> adjuntosSinEncriptar = null;
			byte[] encryptedBytes = null;
			
			Long count = genericoDao.count(ShvDocDocumentoAdjuntoBck.class);
			paginas = count.intValue()/cantXPag + ((count.intValue()%cantXPag > 0)? 1 : 0);
			
			for (int pag=1; pag <= paginas; pag++) {
				adjuntosSinEncriptar = documentoAdjuntoDao.listarDocsAdjuntosBck(pag, cantXPag);
				
				for (ShvDocDocumentoAdjuntoBck adjuntoSinEncriptar : adjuntosSinEncriptar) {
					if (adjuntoSinEncriptar.getArchivoAdjunto() != null) {
						try {
							//Comentar si vamos a prod -- Solo pruebas unitarias
							//String filepath = "C:\\Desarrollo\\encriptaciones\\O_" + adjuntoSinEncriptar.getNombreArchivo();
							//ControlArchivo.escribir(adjuntoSinEncriptar.getArchivoAdjunto(), filepath);
							//Fin - Comentar si vamos a prod
							
							encryptedBytes = byteEncryptor.encrypt(adjuntoSinEncriptar.getArchivoAdjunto());
							
							//Comentar si vamos a prod - solo pruebas unitarias
							//String filepathE = "C:\\Desarrollo\\encriptaciones\\E_" + adjuntoSinEncriptar.getNombreArchivo();
							//ControlArchivo.escribir(encryptedBytes, filepathE);
							//Fin - Comentar si vamos a prod
							
							ShvDocDocumentoAdjuntoAux adjuntoAEncriptar = 
									documentoAdjuntoDao.buscarDocumentoAdjuntoAux(adjuntoSinEncriptar.getIdValorAdjunto());
							
							if (adjuntoAEncriptar != null) {
								adjuntoAEncriptar.setArchivoAdjunto(encryptedBytes);
								documentoAdjuntoDao.guardar(adjuntoAEncriptar);
								
								String mensaje = adjuntoAEncriptar.getIdValorAdjunto() + ": encriptado";
								System.out.println(mensaje);
								Traza.auditoria(getClass(), mensaje);
							}
							
						} catch (Exception e1) {
							String mensaje = adjuntoSinEncriptar.getIdValorAdjunto() + ": error al encriptar --> "+ e1.toString();
							System.out.println(mensaje);
							Traza.auditoria(getClass(), mensaje);
						}
					}
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation=Propagation.REQUIRED)
	public void cambioEncriptacionArchivos() throws NegocioExcepcion {
		final int cantXPag = 100;
		int paginas = 0;
		try {
			List<ShvDocDocumentoAdjuntoAux> adjuntosSinEncriptar = null;
			byte[] encryptedBytes = null;
			byte[] original = null;
			
			Long count = genericoDao.count(ShvDocDocumentoAdjuntoAux.class);
			paginas = count.intValue()/cantXPag + ((count.intValue()%cantXPag > 0)? 1 : 0);
			
			for (int pag=1; pag <= paginas; pag++) {
				adjuntosSinEncriptar = documentoAdjuntoDao.listarDocsAdjuntos(pag, cantXPag);
				
				for (ShvDocDocumentoAdjuntoAux adjuntoSinEncriptar : adjuntosSinEncriptar) {
					if (adjuntoSinEncriptar.getArchivoAdjunto() != null) {
						original = null;
						
						try {
							original = otherByteEncryptorII.decrypt(adjuntoSinEncriptar.getArchivoAdjunto());
							
							if (original != null) {
								encryptedBytes = byteEncryptor.encrypt(adjuntoSinEncriptar.getArchivoAdjunto());
								adjuntoSinEncriptar.setArchivoAdjunto(encryptedBytes);
								documentoAdjuntoDao.guardar(adjuntoSinEncriptar);
								String mensaje = adjuntoSinEncriptar.getIdValorAdjunto() + ": cambiado de encriptacion";
								System.out.println(mensaje);
								Traza.auditoria(getClass(), mensaje);
							}
							
						} catch (Exception e1) {
							String mensaje = adjuntoSinEncriptar.getIdValorAdjunto() + ": error al desencriptar --> "+ e1.toString();
							System.out.println(mensaje);
							Traza.auditoria(getClass(), mensaje);
						}
					}
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	public IDocumentoAdjuntoDao getDocumentoAdjuntoDao() {
		return documentoAdjuntoDao;
	}

	public void setDocumentoAdjuntoDao(IDocumentoAdjuntoDao documentoAdjuntoDao) {
		this.documentoAdjuntoDao = documentoAdjuntoDao;
	}

	public IGenericoDao getGenericoDao() {
		return genericoDao;
	}

	public void setGenericoDao(IGenericoDao genericoDao) {
		this.genericoDao = genericoDao;
	}

	public StandardPBEByteEncryptor getByteEncryptor() {
		return byteEncryptor;
	}

	public void setByteEncryptor(StandardPBEByteEncryptor byteEncryptor) {
		this.byteEncryptor = byteEncryptor;
	}

	public StandardPBEByteEncryptor getOtherByteEncryptorII() {
		return otherByteEncryptorII;
	}

	public void setOtherByteEncryptorII(StandardPBEByteEncryptor otherByteEncryptorII) {
		this.otherByteEncryptorII = otherByteEncryptorII;
	}
}