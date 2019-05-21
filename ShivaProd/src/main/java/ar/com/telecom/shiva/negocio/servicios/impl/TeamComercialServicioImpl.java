package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.persistencia.dao.ITeamComercialDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvParamTeamComercial;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TeamComercialDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class TeamComercialServicioImpl extends Servicio implements ITeamComercialServicio {

	@Autowired
	private ITeamComercialDao teamComercialDao;

	@Autowired
	private IParametroServicio parametroServicio;

	@Autowired
	private ILdapServicio ldapServicio;
	
	private final int cantidadColumnasArchivoTeamComercial = 25; 


	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation=Propagation.REQUIRED)
	public void actualizarTeamComercial(String strFechaParam) throws NegocioExcepcion {
		BufferedReader br = null;
		String linea = null;
		String[] registros = null;
		Integer nroLinea = 0;
		Integer registrosOK = 0;
		ShvParamTeamComercial team = null;
		String pathOrigen = "";
		String pathArchivo = "";
		String nombreArchivo = "";
		double fechaHoraInicioNanoTime = System.nanoTime();
		Date fechaHoraInicio = Utilidad.obtenerFechaActual();
		boolean archivoConErrores = false;
		try {
			Date fechaParametro = Utilidad.parseDatePickerString(strFechaParam);
			nombreArchivo = "SHIVA.TeamComercial." + Utilidad.formatDateAAAAMMDD(fechaParametro);
			pathOrigen = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.teamComercial").trim();
			pathArchivo = pathOrigen + File.separator + nombreArchivo;
			
			//
			// Se verifica el contenido del archivo
			//
			br = new BufferedReader(new FileReader(pathArchivo));
			while ((linea = br.readLine()) != null) {
				nroLinea++;
				if (!linea.contains("NRODECLIENTE") && !linea.contains("Cantidad de Registros") && !linea.trim().equals("")) {
					registros = linea.split(";",-1);
					if (registros.length > cantidadColumnasArchivoTeamComercial) {
						String mensaje = "La linea Nro. " + nroLinea + " no tiene la cantidad de columnas correcta. Tiene más columnas que las necesarias (" + cantidadColumnasArchivoTeamComercial + " columnas)";
						Traza.auditoria(TeamComercialServicioImpl.class, mensaje);
						archivoConErrores = true;
					} else if (registros.length < cantidadColumnasArchivoTeamComercial) {
						String mensaje = "La linea Nro. " + nroLinea + " no tiene la cantidad de columnas correcta. Tiene menos columnas que las necesarias (" + cantidadColumnasArchivoTeamComercial + " columnas)";
						Traza.auditoria(TeamComercialServicioImpl.class, mensaje);
						archivoConErrores = true;
					}
				}
			}
			br.close();
			if (archivoConErrores) {
				throw new NegocioExcepcion("El archivo tiene registros incorrectos");
			}
			
			//
			// Se borran los registros anteriores
			//	
			Long ultimoId = teamComercialDao.obtenerUltimoId();
			teamComercialDao.borrarPorEmpresaYOrigen(EmpresaEnum.TA, ClienteOrigenEnum.DELTA);
			
			// Encoding por defecto
			Traza.auditoria(TeamComercialServicioImpl.class, "File.enconding por defecto del sistema: " + System.getProperty("file.encoding"));
			// Encoding que vamos a usar para la lectura del archivo
			Traza.auditoria(TeamComercialServicioImpl.class, "Para la lectura del archivo de team comercial forzamos el uso a : " + Constantes.ENCODING_CP1252);
			
			nroLinea = 0;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(pathArchivo), Constantes.ENCODING_CP1252));
			
			while ((linea = br.readLine()) != null) {
				if (!linea.contains("NRODECLIENTE") && !linea.contains("Cantidad de Registros") && !linea.trim().equals("")) {
					nroLinea++;
					registros = linea.split(";",-1);
					team = new ShvParamTeamComercial();
					team.setIdTeamComercial(nroLinea.longValue() + ultimoId);
					team.setNroDeCliente(this.limpiar(registros[0]));
					team.setUserGerenteRegional(this.limpiar(registros[1]));
					team.setGerenteRegional(this.limpiar(registros[2]));
					team.setUserGerenteComercial(this.limpiar(registros[3]));
					team.setGerenteComercial(this.limpiar(registros[4]));
					team.setUserEjecutivoCuenta(this.limpiar(registros[5]));
					team.setEjecutivoCuenta(this.limpiar(registros[6]));
					team.setUserIngenieroCuenta(this.limpiar(registros[7]));
					team.setIngenieroCuenta(this.limpiar(registros[8]));
					team.setUserAnalistaCobranzaDatos(this.limpiar(registros[9]));
					team.setAnalistaCobranzaDatos(this.limpiar(registros[10]));
					team.setUserAnalistaCobranzaVoz(this.limpiar(registros[11]));
					team.setAnalistaCobranzaVoz(this.limpiar(registros[12]));
					//U562163
					team.setUserAnalistaContratoVoz(this.limpiar(registros[13]));
					team.setAnalistaContratoVoz(this.limpiar(registros[14]));
					team.setUserSupervisorContratoVoz(this.limpiar(registros[15]));
					team.setSupervisorContratoVoz(this.limpiar(registros[16]));
					team.setUserSupervisorCobranzaVoz(this.limpiar(registros[17]));
					team.setSupervisorCobranzaVoz(this.limpiar(registros[18]));
					team.setUserSupervisorCobranzaDatos(this.limpiar(registros[19]));
					team.setSupervisorCobranzaDatos(this.limpiar(registros[20]));
					team.setUserResponsableCaring(this.limpiar(registros[21]));
					team.setResponsableCaring(this.limpiar(registros[22]));
					team.setUserAnalistaCaring(this.limpiar(registros[23]));
					team.setAnalistaCaring(this.limpiar(registros[24]));
					
					team.setEmpresa(EmpresaEnum.TA);
					team.setOrigen(ClienteOrigenEnum.DELTA);
					this.teamComercialDao.guardarTeamComercial(team);
					
					registrosOK++;
				}
			}
			
			if (registrosOK == 0) {
				String mensaje = "No se procesaron registros. El archivo no tiene registros.";
				Traza.auditoria(TeamComercialServicioImpl.class, mensaje);
				throw new NegocioExcepcion(mensaje);
			}
						
		} catch (FileNotFoundException e) {
			Traza.error(TeamComercialServicioImpl.class, "El sistema no puede encontrar el archivo " + pathArchivo, e);
			throw new NegocioExcepcion(e.getMessage(), e);		
		} catch (IOException e) {
			Traza.error(TeamComercialServicioImpl.class, "Error al intentar leer el archivo " + pathArchivo, e);
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (Exception e) {
			Traza.error(TeamComercialServicioImpl.class, "Error no previsto: " + e.getMessage(), e);
			throw new NegocioExcepcion(e.getMessage(), e);
		} finally {
			//
			// Log cantidad de transacciones y tiempo empleado
			//
			Traza.loguearInfoProcesamiento(this.getClass(), "actualizarTeamComercial", fechaHoraInicio, fechaHoraInicioNanoTime, registrosOK);
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				Traza.error(TeamComercialServicioImpl.class, "Error: " + ex.getMessage(), ex );
			}
		}
		
		try {
			this.zipArchivoHistorico(pathArchivo, fechaHoraInicio);
			this.borrarArchivosHistoricosAntiguos();
		} catch (Exception e) {
			Traza.auditoria(TeamComercialServicioImpl.class, "Error al zipear archivo historico");
		}
		
	}
	
	private void zipArchivoHistorico(String pathArchivo, Date fechaHoraInicio) throws NegocioExcepcion {
		try {
			byte[] buffer = new byte[1024];
			File archivoOriginal = new File(pathArchivo);
			File pathHistorico = new File(archivoOriginal.getParent() + File.separator + "historico"
					+ File.separator + archivoOriginal.getName().replaceAll("[^a-zA-Z0-9_]", "_") + "_" + Utilidad.formatDateHHMMSSTH3(fechaHoraInicio));
			if (!pathHistorico.exists()) {
				if (pathHistorico.mkdirs()) {
					Traza.auditoria(TeamComercialServicioImpl.class, "Directorio historico creado: " + pathHistorico.getAbsolutePath());
				} else {
					Traza.error(TeamComercialServicioImpl.class, "Error al crear el directorio historico: " + pathHistorico.getAbsolutePath());
					throw new NegocioExcepcion("Error al crear el directorio historico");
				}
			}
			File archivoCopia = new File(pathHistorico.getAbsolutePath() + File.separator + archivoOriginal.getName());
			
			InputStream inStream = new FileInputStream(archivoOriginal);
			OutputStream outStream = new FileOutputStream(archivoCopia);
			
			int length;
    	    while ((length = inStream.read(buffer)) > 0){
    	    	outStream.write(buffer, 0, length);
    	    }
			
    	    inStream.close();
    	    outStream.close();
			
    	    // Preparo los archivos a zipear (solo archivos, no directorios)
    	    List<String> fileList = new ArrayList<String>();
            String[] nodes = pathHistorico.list();
    		File file = null;
            for (String node : nodes) {
            	file = new File(pathHistorico, node);
            	if (file.isFile()) {
            		fileList.add(node);
            	}
            }
                
            // Zip
            FileOutputStream fos = new FileOutputStream(pathHistorico.getAbsolutePath() + ".zip");
        	ZipOutputStream zos = new ZipOutputStream(fos);
     
        	for (String fileToZip : fileList) {
        		ZipEntry ze= new ZipEntry(fileToZip);
            	zos.putNextEntry(ze);
               	FileInputStream in = new FileInputStream(pathHistorico.getAbsolutePath() + File.separator + fileToZip);
            	int len;
            	while ((len = in.read(buffer)) > 0) {
            		zos.write(buffer, 0, len);
            	}
            	in.close();
        	}
     
        	zos.closeEntry();
        	zos.close();
    	    
    	    ControlArchivo.eliminarArchivoODirectorio(pathHistorico);
    	    ControlArchivo.eliminarArchivoODirectorio(archivoOriginal);
    	    
		} catch (IOException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	} 

	private String limpiar(String str) {
		return (str != null) ? str.trim() : str;
	}
	
	public TeamComercialDto buscarTeamComercial(String nroDeCliente) throws NegocioExcepcion {
		try {
			ShvParamTeamComercial teamComercialModelo = teamComercialDao.buscarTeamComercial(nroDeCliente);
			if (!Validaciones.isObjectNull(teamComercialModelo)){
				TeamComercialDto teamComercialDto = (TeamComercialDto) defaultMapeador.map(teamComercialModelo);
				return teamComercialDto;
			} else {
				return null;
			}
		
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	private void borrarArchivosHistoricosAntiguos() {
		try {
			String pathHistorico = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.teamComercial").trim() + File.separator + "historico";
			File carpetaHistoricos = new File(pathHistorico);
			Date hoy = Utilidad.obtenerFechaActual();
			String[] nodes = carpetaHistoricos.list();
    		File file = null;
    		int diasVigenciaHist = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.TEAM_COMERCIAL_BATCH_DIAS_VIGENCIA_HIST).toString());
    		for (String node : nodes) {
            	file = new File(pathHistorico, node);
            	if (file.isFile() && file.getName().contains("SHIVA_TeamComercial")) {
            		if (Utilidad.diferenciaDias(hoy, new Date(file.lastModified())) > diasVigenciaHist) {
            			file.delete();
            			Traza.auditoria(TeamComercialServicioImpl.class, "Se elimino el histórico " + file.getName() + " (ult. actualización del archivo " + new Date(file.lastModified()) + ")");
            		}
            	}
            }
		} catch (Exception e) {
			Traza.error(TeamComercialServicioImpl.class, "Error al eliminar archivos historicos: " + e.getMessage(), e);
		}
	}
	
	@Override
	public List<String> listarIdAnalistaTeamComercialPorListaClientes(Set<ShvCobEdCliente> clientes) throws NegocioExcepcion {
		try {
			List<String> listaNroClientes = new ArrayList<String>();
			if (Validaciones.isCollectionNotEmpty(clientes)) {

				for (ShvCobEdCliente cliente : clientes) {
					listaNroClientes.add(cliente.getIdClienteLegado().toString());
				}

				return teamComercialDao.listarIdAnalistaTeamComercialPorListaNroCliente(listaNroClientes);
			}
			return null;
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), e.getMessage());					
			throw new NegocioExcepcion(e.getMessage(), e);
		} 
	}

	@Override
	public Set<String> listarIdAnalistaTeamComercialPorListaClientesDto(Set<ClienteDto> clientes) throws NegocioExcepcion {
		try {
			List<String> listaNroClientes = new ArrayList<String>();
			if (Validaciones.isCollectionNotEmpty(clientes)) {
				for (ClienteDto cliente : clientes) {
					listaNroClientes.add(Utilidad.removeStartingZeros(cliente.getIdClienteLegado()));
				}
				Set<String> idAnalistaTeamComercialesUnico = new HashSet<String>();
				List<String>  listaIdAnalistaTeamComercial = teamComercialDao.listarIdAnalistaTeamComercialPorListaNroCliente(listaNroClientes);
				for (String idAnalistaTeamComercial : listaIdAnalistaTeamComercial) {
					idAnalistaTeamComercialesUnico.add(idAnalistaTeamComercial);
				}
				return idAnalistaTeamComercialesUnico;
			}
			return null;
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), e.getMessage());					
			throw new NegocioExcepcion(e.getMessage(), e);
		} 
	}
	@Override
	public UsuarioLdapDto obtenerAnalistaTeamComercial(Set<ClienteDto> clientes) throws NegocioExcepcion {
		Set<String> idAnalistaTeamComercialesUnico = this.listarIdAnalistaTeamComercialPorListaClientesDto(clientes);
		UsuarioLdapDto usuarioAnalistaTeamComercial = null;

		if (!Validaciones.isObjectNull(idAnalistaTeamComercialesUnico) && !idAnalistaTeamComercialesUnico.isEmpty()) {
			if (idAnalistaTeamComercialesUnico.size() == 1) {
				usuarioAnalistaTeamComercial = ldapServicio.buscarUsuarioPorUidEnMemoria(idAnalistaTeamComercialesUnico.iterator().next());
			} else {
				usuarioAnalistaTeamComercial = new UsuarioLdapDto();
				usuarioAnalistaTeamComercial.setNombreCompleto(ConstantesCobro.GRUPO_TEAM_COMERCIAL);
			}
		}
		return usuarioAnalistaTeamComercial;
	}

	public ITeamComercialDao getTeamComercialDao() {
		return teamComercialDao;
	}

	public void setTeamComercialDao(ITeamComercialDao teamComercialDao) {
		this.teamComercialDao = teamComercialDao;
	}

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

}
