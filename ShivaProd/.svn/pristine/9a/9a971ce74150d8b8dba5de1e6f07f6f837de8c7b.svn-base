package ar.com.telecom.shiva.base.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.JSON;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class ControlArchivo {

	public ControlArchivo() {
	}
	
	
	/**
	 * Levanta un archivo (archivo)
	 * 
	 * @param archivo (El path absoluto del archivo)
	 * @return
	 */
	public synchronized static String[] leerArchivo(String archivo, String retorno) throws ShivaExcepcion
	{
		FileInputStream fstream = null;
		FileInputStream fstreamCharset = null;
		BufferedReader buffer = null;
		
		try {
			// Abrimos el archivo
			fstream = new FileInputStream(archivo);
			fstreamCharset = new FileInputStream(archivo);
			// Creamos el objeto de entrada
			DataInputStream entrada = new DataInputStream(fstream);

			//Detecto la codificación y me la guardo
			Charset decodificador = Utilidad.charsetDetector(fstreamCharset);
			
			// Creamos el Buffer de Lectura con la codificación detectada
			buffer = new BufferedReader(new InputStreamReader(entrada,decodificador));
			
			StringBuffer fileContents = new StringBuffer();
			char[] bufferChar = new char[100];
			int caracteres_leidos = 0;
			
			while (buffer.ready()) {
				caracteres_leidos = buffer.read(bufferChar);
				if (caracteres_leidos > 0) {
					fileContents.append(bufferChar, 0, caracteres_leidos);
				}
			}			
			
			if (fileContents != null && fileContents.toString() != null && fileContents.toString().length() > 0) {
				String lineas[] = fileContents.toString().split(retorno);
				return lineas;
			}
			
		} catch (FileNotFoundException e) {
			throw new ShivaExcepcion(e);
		} catch (IOException e) {
			throw new ShivaExcepcion(e);
		} catch (Exception e) {
			throw new ShivaExcepcion(e);
		} finally {
			try {
				
				if (buffer!=null) buffer.close();
				if (fstream!=null) fstream.close();
				if (fstreamCharset!=null) fstream.close();
				
			} catch (IOException e) {
				throw new ShivaExcepcion(e); 
			}
		}
		
		//notifyAll();
		return null;
	}
	/**
	 * Levanta un archivo (archivo)
	 * 
	 * @param archivo (El path absoluto del archivo)
	 * @return
	 */
	public synchronized static String[] leerArchivo(String archivo) throws ShivaExcepcion
	{
		FileInputStream fstream = null;
		FileInputStream fstreamCharset = null;
		BufferedReader buffer = null;
		
		try {
			// Abrimos el archivo
			fstream = new FileInputStream(archivo);
			fstreamCharset = new FileInputStream(archivo);
			// Creamos el objeto de entrada
			DataInputStream entrada = new DataInputStream(fstream);

			//Detecto la codificación y me la guardo
			Charset decodificador = Utilidad.charsetDetector(fstreamCharset);
			
			// Creamos el Buffer de Lectura con la codificación detectada
			buffer = new BufferedReader(new InputStreamReader(entrada,decodificador));
			
			StringBuffer fileContents = new StringBuffer();
			char[] bufferChar = new char[100];
			int caracteres_leidos = 0;
			
			while (buffer.ready()) {
				caracteres_leidos = buffer.read(bufferChar);
				if (caracteres_leidos > 0) {
					fileContents.append(bufferChar, 0, caracteres_leidos);
				}
			}
			String lineas[] = null;
			if (fileContents != null && fileContents.toString() != null && fileContents.toString().length() > 0) {
				if (Constantes.RETORNO_UNIX.equals(verificarFinArchivo(fileContents))) {
					lineas = fileContents.toString().split(Constantes.RETORNO_UNIX);
				} else { 
					lineas = fileContents.toString().split(Constantes.RETORNO_WIN);
				}
				return lineas;
			}
			
		} catch (FileNotFoundException e) {
			throw new ShivaExcepcion(e);
		} catch (IOException e) {
			throw new ShivaExcepcion(e);
		} catch (Exception e) {
			throw new ShivaExcepcion(e);
		} finally {
			try {
				
				if (buffer!=null) buffer.close();
				if (fstream!=null) fstream.close();
				if (fstreamCharset!=null) fstream.close();
				
			} catch (IOException e) {
				throw new ShivaExcepcion(e); 
			}
		}
		
		//notifyAll();
		return null;
	}
	
	private static String verificarFinArchivo(final StringBuffer fileContents) {
		int indice = 0;
		String salida = Utilidad.EMPTY_STRING;
		while (indice < fileContents.length() && Validaciones.isEmptyString(salida)) {
			if (fileContents.charAt(indice) == '\n') {
				int indiceAnterior = indice - 1;
				if (indiceAnterior >= 0) {
					if (fileContents.charAt(indiceAnterior) == '\r') {
						salida = Constantes.RETORNO_WIN;
					} else {
						salida = Constantes.RETORNO_UNIX;
					}
				}
			}
			indice++;
		}
		return salida;
	}
	/**
	 * Crea el archivo con el contenido
	 * @param content
	 * @param folder
	 * @param nameFile
	 */
	public static void escribir(String content, String folder, String nameFile) throws ShivaExcepcion {
		File f = new File(folder+nameFile);
		FileWriter w;
		
		try {
			f.setWritable(true);
			
			w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			wr.write(content);
			wr.close();
			bw.close();
			
			f.setReadOnly();
		} catch (IOException e) {
			throw new ShivaExcepcion(e);
		} 
		
	}

	/**
	 * Crea el archivo con el contenido
	 * @param content
	 * @param folder
	 * @param nameFile
	 */
	public static void escribir(byte[] content, String pathFileName) throws ShivaExcepcion {
		File f = new File(pathFileName);
		FileOutputStream w;
		
		try {
			f.setWritable(true);
			w = new FileOutputStream(f);
			w.write(content);
			w.close();
			f.setReadOnly();
		} catch (IOException e) {
			throw new ShivaExcepcion(e);
		}	
	}
	
	
	public static void escribirPdf(String content, String folder, String nameFile) throws ShivaExcepcion {
		try {
			byte[] barray= content.getBytes();
			OutputStream out = new FileOutputStream(folder+nameFile);
			for (Byte b: barray) {  
	            out.write(b);  
	        }  
			out.close();
		} catch (IOException e) {
			throw new ShivaExcepcion(e);
		}
	}
	
	/**
	 * Verificando el directorio
	 * @param path
	 * @return File
	 */
	public static File getDirectorio(String path) throws ShivaExcepcion {
		File directory = new File(path);
		
		try {
			if(!directory.isDirectory()){
				throw new ShivaExcepcion("El directorio [" + directory.getCanonicalPath() + "] no es un directorio valido");
			} else if(!directory.canRead() || !directory.canWrite()) {
				throw new ShivaExcepcion("El directorio [" + directory.getCanonicalPath() + "] no posee permisos de escritura/lectura");
			}
		} catch (IOException e) {
			throw new ShivaExcepcion(e);
		}
		return directory;
	}
	
	/**
	 * Devuelve una lista de un archivo de un directorio ingresado
	 * @throws ShivaExcepcion 
	 */
	public static File[] listaArchivosDirectorio(String sourceFolderRute) throws ShivaExcepcion {
		File folder = getDirectorio(sourceFolderRute);
		return folder.listFiles();
	}
	
	/**
	 * Filtro del tipo del archivo en un directorio
	 * @param filtro
	 * @return FilenameFilter
	 */
	public static FilenameFilter filtrarArchivos(final String filtro) {
	
		return new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (name.toUpperCase().endsWith(filtro)) {
					return true;
				}
				return false;
			}
		};	
	}
	
	/**
	 * Devuelve una lista de un archivo de un directorio ingresado y filtro 
	 */
	public static List<File> listaArchivosEnUnDirectorio(File dir, FilenameFilter filtroDeArchivo) {
		return Arrays.asList(dir.listFiles(filtroDeArchivo));		
	}
	
	/**
	 * Verifico que si existe archivos con respecto al filtro de archivos 
	 * @param directorio
	 * @param filtroDeArchivo
	 * @return
	 */
	public static boolean existeArchivosEnUnDirectorio(File directorio, FilenameFilter filtroDeArchivo) {
		return directorio.listFiles(filtroDeArchivo).length == 0? false:true;			
	}
	
	/**
	 * Descarga el archivo
	 * @param path
	 * @param resp
	 * @throws Exception
	 */
	public static void descargarArchivo(String path, HttpServletResponse resp) throws Exception{
		File fileObj = new File(path); 
		//fileObj.setReadOnly();
		
	    if ((!fileObj.exists()) || (!fileObj.isFile()) || (!fileObj.canRead())) { 
	    	throw new Exception("'file "+ fileObj.getPath()+ "' cannot be read."); 
	    }

	    long length = fileObj.length(); 
	    if (length <= Integer.MAX_VALUE) { 
	      resp.setContentLength((int)length); 
	    } else { 
	      resp.addHeader("Content-Length", Long.toString(length)); 
	    }
	    
	    resp.setHeader("Cache-Control", "private");
		resp.setHeader("Pragma", "private");
		
	    resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileObj.getName() + "\"");

	    ServletOutputStream out = resp.getOutputStream(); 
	    InputStream in = null; 
	    byte[] buffer = new byte[32768]; 
	    try{ 
	    	in = new FileInputStream(fileObj);

	    	int bytesRead; 
	    	while ((bytesRead = in.read(buffer)) >= 0) { 
	    		out.write(buffer, 0, bytesRead); 
	    	} 
	    } finally { 
	      if (in != null) { 
	    	  in.close(); 
	      } 
	    } 
	}
	
	
	/**
	 * Descarga el archivo
	 * @param path
	 * @param resp
	 * @throws Exception
	 */
	public static void descargarComoArchivo(byte[] archivo, String nombreArchivo, HttpServletResponse res) throws Exception {
		byte[] file = archivo;
		
		// Para solventar bug IE8 sobre puerto seguro no abre PDF
		res.setHeader("Cache-Control", "private");
		res.setHeader("Pragma", "private");
		
		res.addHeader("Content-Disposition", "attachment; filename="+ nombreArchivo);
		
		InputStream in = null;
		if (file == null) {
			in = new ByteArrayInputStream("Se ha producido un error al descargar el archivo (vacio)".getBytes());
		} else {
			
			if (file.length <= Integer.MAX_VALUE) { 
				res.setContentLength((int)file.length); 
			} else { 
				res.addHeader("Content-Length", Long.toString(file.length)); 
			}
			in = new ByteArrayInputStream(file);
		}
		
		IOUtils.copy(in, res.getOutputStream());
		res.flushBuffer();
	}
	
	/**
	 * Descarga el archivo
	 * @param path
	 * @param resp
	 * @throws Exception
	 */
	public static void descargarComoPDF(byte[] archivo, String nombreArchivo, HttpServletResponse res) throws Exception {
		byte[] file = archivo;
		
		// Para solventar bug IE8 sobre puerto seguro no abre PDF
		res.setHeader("Cache-Control", "private");
		res.setHeader("Pragma", "private");
		
		
		res.addHeader("Content-Disposition", "attachment; filename="+ nombreArchivo);
		res.setContentType("application/pdf");
		
		InputStream in = null;
		if (file == null) {
			in = new ByteArrayInputStream("Se ha producido un error al descargar el archivo (vacio)".getBytes());
		} else {
			
			if (file.length <= Integer.MAX_VALUE) { 
				res.setContentLength((int)file.length); 
			} else { 
				res.addHeader("Content-Length", Long.toString(file.length)); 
			}
			in = new ByteArrayInputStream(file);
		}
		
		IOUtils.copy(in, res.getOutputStream());
		res.flushBuffer();
	}
	
	
	/**
	 * 
	 * @param archivo
	 * @param resp
	 * @throws Exception
	 */
	public static void descargarTextoComoArchivo(byte[] archivo, String nombreArchivo, HttpServletResponse resp) throws Exception{
		
		if (archivo.length <= Integer.MAX_VALUE) { 
			resp.setContentLength((int)archivo.length); 
		} else { 
			resp.addHeader("Content-Length", Long.toString(archivo.length)); 
		}
		
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");
		
		resp.setHeader("Cache-Control", "private");
		resp.setHeader("Pragma", "private");
		
		ServletOutputStream out = resp.getOutputStream(); 
		InputStream in = null; 
		byte[] buffer = new byte[32768]; 
		try{
			in = new ByteArrayInputStream(archivo);
			
			int bytesRead; 
			while ((bytesRead = in.read(buffer)) >= 0) { 
				out.write(buffer, 0, bytesRead); 
			} 
		} finally { 
			if (in != null) { 
				in.close(); 
			} 
		} 
		
	}
	
	/**
	 * Crea el archivo con el contenido
	 * @param content
	 * @param folder
	 * @param nameFile
	 */
	public static void escribirArchivosTagetik(StringBuffer content, String folder, String nameFile, String cabecera) throws ShivaExcepcion {
		File f = new File(folder+nameFile);
		
		FileWriter w;
		try {
			f.setWritable(true);
			
			w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			if(cabecera != null){
				wr.write(cabecera);
				wr.write(Constantes.RETORNO_WIN);
			}
			wr.write(content.toString());
			wr.close();
			bw.close();
			
			f.setReadOnly();
		} catch (IOException e) {
			throw new ShivaExcepcion(e);
		}
	}
	
	/**
	 * Crea el archivo con el contenido y pie
	 * @param content
	 * @param folder
	 * @param nameFile
	 */
	public static void escribirArchivos(List<?> content, String folder, String nameFile, String pie) throws ShivaExcepcion {
		File f = new File(folder+nameFile);
		
		FileWriter w;
		try {
			f.setWritable(true);
			
			w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			for (Object factura : content) {
				wr.write(factura.toString());
				wr.write(Constantes.RETORNO_WIN);
			}
			
			if (!Validaciones.isNullOrEmpty(pie)) {
				wr.write(pie);
			}
			
			wr.close();
			bw.close();
			
			f.setReadOnly();
		} catch (IOException e) {
			throw new ShivaExcepcion(e);
		}
	}
	
	/**
	 * Crea el archivo con el contenido y pie
	 * @param content
	 * @param folder
	 * @param nameFile
	 */
	public static void escribirArchivosCP1252(List<?> content, String folder, String nameFile, String pie) throws ShivaExcepcion {
		File f = new File(folder+nameFile);
		
		try {
			f.setWritable(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),Constantes.ENCODING_CP1252));
			PrintWriter wr = new PrintWriter(bw);
			for (Object factura : content) {
				wr.write(factura.toString());
				wr.write(Constantes.RETORNO_WIN);
			}
			
			if (!Validaciones.isNullOrEmpty(pie)) {
				wr.write(pie);
			}
			
			wr.close();
			bw.close();
			
			f.setReadOnly();
		} catch (IOException e) {
			throw new ShivaExcepcion(e);
		}
	}
	
	/**
	 * Crea el archivo con el contenido y pie
	 * @param content
	 * @param folder
	 * @param nameFile
	 */
	public static void escribirArchivosUTF8(List<?> content, String folder, String nameFile, String pie) throws ShivaExcepcion {
	File f = new File(folder+nameFile);
		
		try {
			f.setWritable(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),Constantes.ENCODING_UTF8));
			PrintWriter wr = new PrintWriter(bw);
			for (Object factura : content) {
				wr.write(factura.toString());
				wr.write(Constantes.RETORNO_WIN);
			}
			
			if (!Validaciones.isNullOrEmpty(pie)) {
				wr.write(pie);
			}
			
			wr.close();
			bw.close();
			
			f.setReadOnly();
		} catch (IOException e) {
			throw new ShivaExcepcion(e);
		}

	}
	/***********************************************************************************+
	 * Validaciones del archivo o multipartfile
	 */
	
	/**
	 * Función que se fija si el archivo posee o no información.
	 * @param file
	 * @return <code>true</code> si el archivo está vacío o <code>false</code> en caso contrario. 
	 * @throws IOException
	 */
	public static boolean isFileEmpty(File file) throws IOException {
		return ((fileToByteArray(file)).length == 0);
	}
	
	/**
	 * Función que se fija si el multipartFile posee o no información.
	 * @param file
	 * @return <code>true</code> si el multipartfile está vacío o <code>false</code> en caso contrario. 
	 * @throws IOException
	 */
	public static boolean isMultipartFileEmpty(MultipartFile file) throws IOException {
		return (file.getSize() == 0);
	}
	
	/**
	 * Verifica si el archivo supera el peso maximo estipulado en <code>validaciones.properties</code>.
	 * @param file
	 * @return <code>true</code> si supera el peso máximo permitido, <code>false</code> de lo contrario.
	 */
	public static boolean superaPesoMaximoPermitido(MultipartFile file) {
		return (file.getSize() > Constantes.TAMANIO_MAX_POR_ARCHIVO);
	}
	
	/**
	 * Convierte un archivo cualqueira a un array de bytes.
	 * @param file
	 * @return Un array de bytes representando la serializacion del archivo enviado por parametro.
	 * @throws IOException
	 */
	public static byte[] fileToByteArray(File file) throws IOException {
		return (Files.readAllBytes(file.toPath()));
	}
	
	/**
	 * Calcula el tamaño del archivo enviado por paramentro.
	 * @param file
	 * @return El tamaño del archivo en bytes
	 * @throws IOException
	 */
	public static Long getFileSize (File file) throws IOException {
		return (Long.valueOf(fileToByteArray(file).length));
	}
	
	/**
	 * Separa la extensión del archivo enviado por paramentro y la devuelve.
	 * @param file
	 * @return Un <code>String</code> representando la extensión del archivo.
	 */
	public static String getFileExtension (File file) {
		String[] partitions = file.getName().split(".");
		return partitions[partitions.length -1];
	}	
	
	/**
	 * Separa la extensión del nombre del archivo enviado por paramentro y la devuelve.
	 * @param file
	 * @return Un <code>String</code> representando la extensión del archivo.
	 */
	public static String getFileExtension (String fileName) {
		String[] partitions = fileName.split("\\.");
		return partitions[partitions.length -1];
	}
	
	/**
	 * Corrobora de que la extensión del archivo pasado por parametro no se encuentre entre las extensiones no permitidas en
	 * el archivo <code>validaciones.properties</code>
	 * @param file
	 * @return <code>true</code> si la extensión se encuentra dentro de las no permitidas.
	 */
	public static boolean archivoTieneExtensionProhibida(String fileName) {
		String extension = ControlArchivo.getFileExtension(fileName);
		String[] extencionesPermitidas = Propiedades.VALIDACIONES_PROPIEDADES.getString("validacion.extensionesPosibles").split("\\|");
		
		for (String ext : extencionesPermitidas) {
			if (ext.equalsIgnoreCase(extension)) {
				return false;
			}
		}
		return true;
	}
	
	
	public static boolean archivoTieneCaracteresNoPermitidos(String fileName) {
		String nombre = fileName;
		String[] caracteresNoPermitidos = Propiedades.VALIDACIONES_PROPIEDADES.getString("validacion.caracteresNoPermitidos").split("\\|");
		
		for (String caracter : caracteresNoPermitidos) {
			if (nombre.contains(caracter)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Corrobora de que el nombre del archivo no se encuentre entre los nombres prohibidos que se especifican en <code>validaciones.properties</code>
	 * @param fileName
	 * @return <code>true</code> si el nombre se encuentra en la lista de no permitidos.
	 */
	public static boolean esArchivoProhibido (String fileName) {
		String[] archProhibidos = Propiedades.VALIDACIONES_PROPIEDADES.getString("validacion.nombreArchivosProhibidos").split("\\|");
		
		for (String name : archProhibidos) {
			if (name.equalsIgnoreCase(fileName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Busca el archivo con el 'nombreArchivo' en el 'pathOrigen' y lo copia en el 
	 * 'pathDestino'. Luego elimina el archivo del 'pathOrigen'.
	 * @param nombreArchivo
	 * @return
	 */
	public static void moverArchivo (String nombreArchivo, String pathOrigen, String pathDestino) throws ShivaExcepcion {
		try {
			String pathOrigenCompleto = pathOrigen + "//" + nombreArchivo;
			Path rutaOrigenCompleto = FileSystems.getDefault().getPath(pathOrigenCompleto);
			Path rutaOrigen = FileSystems.getDefault().getPath(pathOrigen);
			Path rutaDestino = FileSystems.getDefault().getPath(pathDestino);
			if (Files.exists(rutaOrigen)) {
				if (Files.exists(rutaDestino)) {
					// Escribo el archivo en el path destino
					byte[] archivo = Files.readAllBytes(rutaOrigenCompleto);
					escribir(new String(archivo), pathDestino, nombreArchivo);
					System.out.println("Se copio el archivo " + nombreArchivo + " a la carpeta " + pathDestino);
					Traza.auditoria(ControlArchivo.class, "Se copio el archivo " + nombreArchivo + " a la carpeta " + pathDestino);
					
					// Elimino el arcihvo del path origen
					File tempFile = new File(pathOrigenCompleto);
					tempFile.delete();
					System.out.println("Se ha borrado el archivo " + nombreArchivo + " de la carpeta " + pathOrigen);
					Traza.auditoria(ControlArchivo.class, "Se ha borrado el archivo " + nombreArchivo + " de la carpeta " + pathOrigen);
				} else {
					throw new ShivaExcepcion("Se intento mover el archivo "+nombreArchivo+" y el directorio destino no existen.");
				}
			} else {
				throw new ShivaExcepcion("Se intento mover el archivo "+nombreArchivo+" y el directorio origen no existen.");
			}
		} catch (IOException e) {
			throw new ShivaExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Mueve el archivo de la carpeta origen a la carpeta "origen\historico". 
	 * Se usa en los batch de reversion de cobros y procesar archivos AVC.
	 * @param nombreArchivo
	 * @param pathOrigen
	 * @throws NegocioExcepcion
	 */
	public static void moverArchivoACarpetaHistorico(String nombreArchivo, String pathOrigen) throws NegocioExcepcion {
		try {
			String pathDestino = pathOrigen + "//historico//";
			Path rutaDestino = FileSystems.getDefault().getPath(pathDestino);
			if (Files.exists(rutaDestino)) {
				moverArchivo(nombreArchivo,pathOrigen,pathDestino);
			}else{
				File file = new File(pathDestino);
				if (!file.exists()) {
					if (file.mkdir()) {
						System.out.println("Se ha creado la carpeta 'historico' en el path " + pathOrigen);
						Traza.auditoria(ControlArchivo.class, "Se ha copiado el archivo " + nombreArchivo + " a la carpeta de historicos");
						
						moverArchivo(nombreArchivo,pathOrigen,pathDestino);
					}
				}
			}
			
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	/**
	 * Mueve el archivo de la carpeta origen a la carpeta "origen\rechazado" o "origen\historico" segun el resultado y le agrega al nombre la facha de ejecucion. 
	 * @param nombreArchivo
	 * @param pathOrigen
	 * @throws NegocioExcepcion
	 */
	public static void moverArchivoSegunResultado(String nombreArchivo, String pathOrigen, String pathDestino) throws NegocioExcepcion {
		try {
			Date date = new Date();
			Path rutaDestino = FileSystems.getDefault().getPath(pathDestino);
			String nuevoNombreArchivo = nombreArchivo.replace("txt", Utilidad.formatDateAAAAMMDD_HHmmss(date)) + ".txt";
			
			if (Files.exists(rutaDestino)) {
				moverYCambiarNombreArchivo(nombreArchivo,pathOrigen,pathDestino, nuevoNombreArchivo);
			}else{
				File file = new File(pathDestino);
				if (!file.exists()) {
					if (file.mkdir()) {
						System.out.println("Se ha creado la carpeta en el path " + pathOrigen);
						Traza.auditoria(ControlArchivo.class, "Se ha copiado el archivo " + nombreArchivo + " a la carpeta de rechazado");
						
						moverYCambiarNombreArchivo(nombreArchivo,pathOrigen,pathDestino, nuevoNombreArchivo);
					}
				}
			}
			
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public static void moverYCambiarNombreArchivo (String nombreArchivo, String pathOrigen, String pathDestino, String nuevoNombreArchivo) throws ShivaExcepcion {
		try {
			String pathOrigenCompleto = pathOrigen + "//" + nombreArchivo;
			Path rutaOrigenCompleto = FileSystems.getDefault().getPath(pathOrigenCompleto);
			Path rutaOrigen = FileSystems.getDefault().getPath(pathOrigen);
			Path rutaDestino = FileSystems.getDefault().getPath(pathDestino);
			if (Files.exists(rutaOrigen)) {
				if (Files.exists(rutaDestino)) {
					// Escribo el archivo en el path destino
					byte[] archivo = Files.readAllBytes(rutaOrigenCompleto);
					escribir(new String(archivo), pathDestino, nuevoNombreArchivo);
					System.out.println("Se copio el archivo " + nombreArchivo + " a la carpeta " + pathDestino);
					Traza.auditoria(ControlArchivo.class, "Se copio el archivo " + nombreArchivo + " a la carpeta " + pathDestino);
					
					// Elimino el arcihvo del path origen
					File tempFile = new File(pathOrigenCompleto);
					tempFile.delete();
					System.out.println("Se ha borrado el archivo " + nombreArchivo + " de la carpeta " + pathOrigen);
					Traza.auditoria(ControlArchivo.class, "Se ha borrado el archivo " + nombreArchivo + " de la carpeta " + pathOrigen);
				} else {
					throw new ShivaExcepcion("Se intento mover el archivo "+nombreArchivo+" y el directorio destino no existen.");
				}
			} else {
				throw new ShivaExcepcion("Se intento mover el archivo "+nombreArchivo+" y el directorio origen no existen.");
			}
		} catch (IOException e) {
			throw new ShivaExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Se usa para convertir en bytes al inputStream
	 * @param is
	 * @return
	 */
	public static byte[] getBytesFromInputStream(InputStream is) {
		try (ByteArrayOutputStream os = new ByteArrayOutputStream();)
	    {
	        byte[] buffer = new byte[0xFFFF];

	        for (int len; (len = is.read(buffer)) != -1;)
	            os.write(buffer, 0, len);

	        os.flush();

	        return os.toByteArray();
	    }
	    catch (IOException e)
	    {
	        return null;
	    }
	}
	
	/**
	 * Se usa para buscar el archivo y convertir en bytes al inputStream
	 * @param is
	 * @return
	 */
	public static byte[] buscarArchivo(String filepath) throws NegocioExcepcion {
		try {
			File file = new File(filepath);
			InputStream in = new FileInputStream(file);
			return ControlArchivo.getBytesFromInputStream(in);
			
		} catch (FileNotFoundException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	/**
	 * Elimino un arhivo o el directorio con todos sus archivos y sus subdirectorios
	 * @param file
	 */
	public static void eliminarArchivoODirectorio(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				if (file.list().length == 0) {
					file.delete();
				} else {
					String files[] = file.list();
					for (String temp : files) {
						File fileDelete = new File(file, temp);
						eliminarArchivoODirectorio(fileDelete);
					}
					if (file.list().length == 0) {
						file.delete();
					}
				}
			} else {
				file.delete();
			}
		}
	}
	
	/**
	 * Elimino todos los archivos del directorio o un archivo
	 * @param file
	 */
	public static void eliminarArchivosDelDirectorio(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				String files[] = file.list();
				int cantFiles = files.length;
				for (String temp : files) {
					File fileDelete = new File(file, temp);
					fileDelete.delete();
				}
				if (cantFiles > 0) {
					Traza.advertencia(ControlArchivo.class, "Se han borrado " + cantFiles + " archivos del directorio: " + file.getAbsolutePath());
				}
			} else {
				String nombreArchivo = file.getAbsolutePath();
				file.delete();
				Traza.advertencia(ControlArchivo.class, "Se ha borrado el archivo: " + nombreArchivo);
			}
		}
	}
	
	/**
	 * Se guarda en algun lugar del directorio temporal
	 * @param multipart
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static File multipartToFile(MultipartFile file) throws NegocioExcepcion {
		try {
			//Con este constructor guarda fisicamente en el directorio user.dir (por ejemplo jboss/bin/nombre del archivo) 
			//File convFile = new File(file.getOriginalFilename());
			
			File convFile = null;
			if (Constantes.isServerWindows()) {
				convFile = new File(System.getProperty("user.home") + File.separator + file.getOriginalFilename());
			} else {
				convFile = new File(Constantes.DIRECTORIO_TEMPORAL_SHIVA + File.separator + file.getOriginalFilename());
			}
	        
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile); 
			fos.write(file.getBytes());
			fos.close();
//	        o
//			file.transferTo(convFile);

			return convFile;
	        
		} catch (IOException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
	}
	
	public static File convert(byte bytes[], MultipartFile file) throws NegocioExcepcion {
		
		File convFile = new File(file.getOriginalFilename());
		
		if (Constantes.isServerWindows()) {
			convFile = new File(System.getProperty("user.home") + File.separator + file.getOriginalFilename());
		} else {
			convFile = new File(Constantes.DIRECTORIO_TEMPORAL_SHIVA + File.separator + file.getOriginalFilename());
		}
        
		try {
			
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile); 
			fos.write(bytes);
			fos.close(); 
		
		} catch (IOException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
		
		return convFile;
	}
	
	/**
	 * Se convierte en un archivo y a veces guarda en algun lugar del directorio temporal
	 * @param multipart
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static File convertToFile(String nombreArchivo) {
		//Con este constructor guarda fisicamente en el directorio user.dir (por ejemplo jboss/bin/nombre del archivo) 
		//File convFile = new File(nombreArchivo);
		
		File convFile = null;
		if (Constantes.isServerWindows()) {
			convFile = new File(System.getProperty("user.home") + File.separator + nombreArchivo);
		} else {
			convFile = new File(Constantes.DIRECTORIO_TEMPORAL_SHIVA + File.separator + nombreArchivo);
		}
        
		return convFile;
	}
	
	/**
	 * Retorna el tipo del archivo que lo obtiene del nombre del archivo.
	 * @param nombreArchivo
	 * @return
	 */
	public static String getTipoArchivo(String nombreArchivo) {
		return (nombreArchivo.split(Constantes.UNDERSCORE))[0];
	}
	
	
	/**
	 * Via Ajax - Responder con JSON
	 * @param json
	 * @param response
	 * @throws Exception
	 */
	public static void responderJSON(JSON json, HttpServletResponse response) throws Exception {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonGenerator generator = objectMapper.getJsonFactory().createJsonGenerator(byteArrayOutputStream, JsonEncoding.UTF8);
		objectMapper.writeValue(generator, json);
		response.getWriter().write(new String(byteArrayOutputStream.toByteArray(), "UTF8"));
	}
	
	
}
