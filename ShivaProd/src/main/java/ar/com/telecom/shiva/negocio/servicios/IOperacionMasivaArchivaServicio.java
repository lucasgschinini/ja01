package ar.com.telecom.shiva.negocio.servicios;

import java.io.File;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.registros.datos.entrada.MicOperacionMasivaEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaRegistroEntrada;


public interface IOperacionMasivaArchivaServicio {
	// TODO u578934 maxi
	// Procesar la respuesta del archivo de entrada generado por MIC como respuesta al procesamiento solicitado

	public void validarConsistencia(File file, MicOperacionMasivaEntrada reg) throws NegocioExcepcion;

	public String procesarLinea (int index, String linea, MicOperacionMasivaRegistroEntrada regSalida) throws NegocioExcepcion;

	public MicOperacionMasivaEntrada procesarRegistrosSalidaMic(String lineas[], MicOperacionMasivaEntrada reg, String nombreArchivo) throws NegocioExcepcion;

	public MicOperacionMasivaEntrada validarRegistrosSalidaMic(String lineas[], MicOperacionMasivaEntrada reg, String nombreArchivo) throws NegocioExcepcion;
}


