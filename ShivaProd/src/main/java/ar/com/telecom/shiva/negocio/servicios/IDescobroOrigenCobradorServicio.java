package ar.com.telecom.shiva.negocio.servicios;

import java.io.File;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;

public interface IDescobroOrigenCobradorServicio {

	boolean procesarArchivoReversion(File archivo) throws ShivaExcepcion, NegocioExcepcion;

	ShvCobDescobro buscarDescobroPorIdOperacion(Long idOperacion) throws NegocioExcepcion;

//	List<Object[]> listarDescobrosParaSubdiario(Filtro subsidiarioFiltro) throws NegocioExcepcion;
}

