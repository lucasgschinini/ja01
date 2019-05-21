package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.dto.ArchivoAVCDto;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;

public interface IArchivoAVCServicio extends IServicio{

	ShvAvcArchivoAvc crearArchivoConRegistrosAVC(ArchivoAVCDto archivoAVC, List<RegistroAVCDto> listaRegistrosAVC) throws NegocioExcepcion;

	void enviarMailConProcesamientoArchivoAVC(ShvAvcArchivoAvc archivoAvcInsertado, String logProcesamiento, Boolean procesoOK, Long totalRegistroEnArchivo) throws NegocioExcepcion;

	void enviarMailPorErrorProcesoArchivoAVC(String name) throws NegocioExcepcion;
}
