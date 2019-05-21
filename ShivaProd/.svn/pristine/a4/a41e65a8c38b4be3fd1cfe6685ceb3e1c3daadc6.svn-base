package ar.com.telecom.shiva.negocio.servicios.validacion.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.validacion.ILegajoChequeRechazadoValidacionServicio;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoFiltro;

public class LegajoChequeRechazadoValidacionServicioImpl implements ILegajoChequeRechazadoValidacionServicio {

	@Override
	public void validarFiltroCheques(VistaSoporteBusquedaValoresFiltro vistaSoporteBusquedaValoresFiltro) throws ValidacionExcepcion {
		boolean validarVinculados = false;
		boolean validarVinculados1 = false;
		List<String> campos = new ArrayList<String>();
		List<String> leyendas = new ArrayList<String>();

		if (!Validaciones.isNullOrEmpty(vistaSoporteBusquedaValoresFiltro.getFechaDesde())) {
			if (!Validaciones.validarFechaConParse(vistaSoporteBusquedaValoresFiltro.getFechaDesde())) {
				this.setError("error.fechaIncorrecta", "#errorfechaAltaDesdeCheque", leyendas, campos);
			} else {
				validarVinculados = true;
			}
			
		}
		if (!Validaciones.isNullOrEmpty(vistaSoporteBusquedaValoresFiltro.getFechaHasta())) {
			if (!Validaciones.validarFechaConParse(vistaSoporteBusquedaValoresFiltro.getFechaHasta())) {
				this.setError("error.fechaIncorrecta", "#errorfechaAltaHastaCheque", leyendas, campos);
			} else {
				validarVinculados1 = true;
			}
			
		}
		if (validarVinculados && validarVinculados1) { 
			if (!Validaciones.validarFechaDesdeHasta(vistaSoporteBusquedaValoresFiltro.getFechaDesde(), vistaSoporteBusquedaValoresFiltro.getFechaHasta())) {
				this.setError("error.fechaDesdeHastaIncorrecto", "#errorfechaAltaDesdeCheque", leyendas, campos);
			}
		}
		if (!Validaciones.isNullOrEmpty(vistaSoporteBusquedaValoresFiltro.getFechaVencimiento())) {
			if (!Validaciones.validarFechaConParse(vistaSoporteBusquedaValoresFiltro.getFechaVencimiento())) {
				//throw new ValidacionExcepcion("", "", "#");
				this.setError("error.fechaIncorrecta", "#errorfechaVencimientoCheque", leyendas, campos);
			}
		}
		validarVinculados = false;
		validarVinculados1 = false;
		if (!Validaciones.isNullOrEmpty(vistaSoporteBusquedaValoresFiltro.getImporteDesde())) {
			if (!Validaciones.isImporteFormato(vistaSoporteBusquedaValoresFiltro.getImporteDesde(), 2)) {
				//throw new ValidacionExcepcion("","", "");
				this.setError("error.numeroConDosDigitos", "#errorimportesDesde", leyendas, campos);
			} else {
				validarVinculados = true;
			}
		}
		if (!Validaciones.isNullOrEmpty(vistaSoporteBusquedaValoresFiltro.getImporteHasta())) {
			if (!Validaciones.isImporteFormato(vistaSoporteBusquedaValoresFiltro.getImporteHasta(), 2)) {
				this.setError("error.numeroConDosDigitos", "#errorimportesHasta", leyendas, campos);
			} else {
				validarVinculados1 = true;
			}
		}
		if (validarVinculados && validarVinculados1) {
			if (!Validaciones.validacionImporteDesdeHastaMejorado(vistaSoporteBusquedaValoresFiltro.getImporteDesde(), vistaSoporteBusquedaValoresFiltro.getImporteHasta())) {
				this.setError("error.importeDesdeHastaIncorrecto", "#errorimportesDesde", leyendas, campos);
			}
		}
		
		if (!Validaciones.isNullOrNumeric(vistaSoporteBusquedaValoresFiltro.getReferenciaDelValorFiltroLike())) {
			if (!Validaciones.validarNumericoComodin(vistaSoporteBusquedaValoresFiltro.getReferenciaDelValorFiltroLike())) {
				this.setError("error.numerico_comodin", "#errorNroCheque", leyendas, campos);
			}
		}
		if (!leyendas.isEmpty()) {
			throw new ValidacionExcepcion(campos, leyendas);
		}
		
		// Tengo que validar Clientes!!!!
	}
	
	@Override
	public void validarFiltroBusquedaLegajos(VistaSoporteLegajoChequeRechazadoFiltro filtro) throws ValidacionExcepcion {
		boolean validarVinculados = false;
		boolean validarVinculados1 = false;
		List<String> campos = new ArrayList<String>();
		List<String> leyendas = new ArrayList<String>();

		if (!Validaciones.isNullOrEmpty(filtro.getFechaAltaChequeDesde())) {
			if (!Validaciones.validarFechaConParse(filtro.getFechaAltaChequeDesde())) {
				this.setError("error.fechaIncorrecta", "#errorFechaAltaDesdeCheque", leyendas, campos);
			} else {
				validarVinculados = true;
			}
			
		}
		if (!Validaciones.isNullOrEmpty(filtro.getFechaAltaChequeHasta())) {
			if (!Validaciones.validarFechaConParse(filtro.getFechaAltaChequeHasta())) {
				this.setError("error.fechaIncorrecta", "#errorFechaAltaHastaCheque", leyendas, campos);
			} else {
				validarVinculados1 = true;
			}
			
		}
		if (validarVinculados && validarVinculados1) { 
			if (!Validaciones.validarFechaDesdeHasta(filtro.getFechaAltaChequeDesde(), filtro.getFechaAltaChequeHasta())) {
				this.setError("error.fechaDesdeHastaIncorrecto", "#errorFechaAltaDesdeCheque", leyendas, campos);
			}
		}


		validarVinculados = false;
		validarVinculados1 = false;
		
		
		if (!Validaciones.isNullOrEmpty(filtro.getFechaAltaLegajoDesde())) {
			if (!Validaciones.validarFechaConParse(filtro.getFechaAltaLegajoDesde())) {
				this.setError("error.fechaIncorrecta", "#errorFechaAltaDesdeLegajo", leyendas, campos);
			} else {
				validarVinculados = true;
			}
			
		}
		if (!Validaciones.isNullOrEmpty(filtro.getFechaAltaLegajoHasta())) {
			if (!Validaciones.validarFechaConParse(filtro.getFechaAltaLegajoHasta())) {
				this.setError("error.fechaIncorrecta", "#errorFechaAltaHastaLegajo", leyendas, campos);
			} else {
				validarVinculados1 = true;
			}
			
		}
		if (validarVinculados && validarVinculados1) { 
			if (!Validaciones.validarFechaDesdeHasta(filtro.getFechaAltaLegajoDesde(), filtro.getFechaAltaLegajoHasta())) {
				this.setError("error.fechaDesdeHastaIncorrecto", "#errorFechaAltaDesdeLegajo", leyendas, campos);
			}
		}
		
		
		
		if (!Validaciones.isNullOrEmpty(filtro.getFechaVencimiento())) {
			if (!Validaciones.validarFechaConParse(filtro.getFechaVencimiento())) {
				//throw new ValidacionExcepcion("", "", "#");
				this.setError("error.fechaIncorrecta", "#errorFechaVencimientoCheque", leyendas, campos);
			}
		}
		validarVinculados = false;
		validarVinculados1 = false;
		if (!Validaciones.isNullOrEmpty(filtro.getImporteDesde())) {
			if (!Validaciones.isImporteFormato(filtro.getImporteDesde(), 2)) {
				//throw new ValidacionExcepcion("","", "");
				this.setError("error.numeroConDosDigitos", "#errorImporteDesde", leyendas, campos);
			} else {
				validarVinculados = true;
			}
		}
		if (!Validaciones.isNullOrEmpty(filtro.getImporteHasta())) {
			if (!Validaciones.isImporteFormato(filtro.getImporteHasta(), 2)) {
				this.setError("error.numeroConDosDigitos", "#errorImporteHasta", leyendas, campos);
			} else {
				validarVinculados1 = true;
			}
		}
		if (validarVinculados && validarVinculados1) {
			if (!Validaciones.validacionImporteDesdeHastaMejorado(filtro.getImporteDesde(), filtro.getImporteHasta())) {
				this.setError("error.importeDesdeHastaIncorrecto", "#errorImporteDesde", leyendas, campos);
			}
		}
		
		if (!Validaciones.isNullOrNumeric(filtro.getNumeroCheque())) {
			if (!Validaciones.isNumeric(filtro.getNumeroCheque())) {
				this.setError("error.numerico", "#errorNroCheque", leyendas, campos);
			}
		}
		if (!leyendas.isEmpty()) {
			throw new ValidacionExcepcion(campos, leyendas);
		}
		
		// Tengo que validar Clientes!!!!
	}

	/***********************************************************************************
	 * Metodos privados que valida campos
	 **********************************************************************************/

	private void setError(String leyenda, String campo, List<String> leyendas, List<String> campos, String... args) {
		if (args.length > 0) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (int indice = 0; indice < args.length; indice++) {
				map.put("{" + indice +"}", args[indice]);
			}
			leyendas.add(
					Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString(
									leyenda
									),
									map
							));
			campos.add(campo);
		} else {
			leyendas.add(Propiedades.MENSAJES_PROPIEDADES.getString(leyenda));
			campos.add(campo);
		}
	}

	@Override
	public void validarComprobantes(MultipartFile file, String descripcion)throws ValidacionExcepcion, NegocioExcepcion {

		if (!Validaciones.esFormatoTexto(descripcion)) {
			throw new ValidacionExcepcion("", "error.formatoTexto", "#errorDescripcionComp");
		} 
		if (file.getOriginalFilename().length() > Constantes.CANT_MAXIMA_CARACTERES_NOMBRE_ARCHIVO) {
			throw new ValidacionExcepcion("","error.nombreArchivoMuyLargo", "#errorArchComprobante");
		} else {
			try {
				if (ControlArchivo.isMultipartFileEmpty(file)) {
					throw new ValidacionExcepcion("","valor.error.archivoVacio", "#errorArchComprobante");
				} else { 
					if (ControlArchivo.esArchivoProhibido(file.getOriginalFilename())) {
						throw new ValidacionExcepcion("","error.archivoProhibido", "#errorArchComprobante");
					} else { 
						if (ControlArchivo.archivoTieneExtensionProhibida(file.getOriginalFilename())) {
							throw new ValidacionExcepcion("","error.extensionProhibida", "#errorArchComprobante");
						} else { 
							if (ControlArchivo.superaPesoMaximoPermitido(file)) {
								throw new ValidacionExcepcion("","error.pesoMaximoSuperado", "#errorArchComprobante");
							} 
						}
					}
				}
			} catch (IOException e) {
				throw new NegocioExcepcion(e);
			}
		}
	
		
	}
}
