package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.comparador.ComparatorTagetikBatch;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.batch.bean.TagetikBatch;
import ar.com.telecom.shiva.negocio.servicios.IOperacionesTagetikServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.dao.IOperacionTagetikDao;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.OperacionTagetikFiltro;

public class OperacionesTagetikServicioImpl extends Servicio implements IOperacionesTagetikServicio {
	
	private static final String COMPANIA_TELECOM = "00001";
	private static final String ORIGEN_CALIPSO = "CAS";
	private static final String TIPO_CAMBIO_MIC = "001,00000";
	private static final String TIPO_REGISTRO_DETALLE = "2";
	private static final String SALTO_LINEA = "\n";
	public static String NOMBRE_ARCHIVO_MIC = "SHIVA.Tagetik.MIC.";
	public static String NOMBRE_ARCHIVO_CALIPSO = "SHIVA.Tagetik.Calipso.";
	
	public static String EXT_ARCH  = ".txt";
	public static String GUION_BAJO= "_";
    public static final String SEMICOLON = ";";
    /** Constant for non value dates **/
    public static final String DATE_ZERO = "00.00.0000";
    
    public static final String ESTADO_FACTURA = "11";
    
    public static final int INDEX_FECHA = 0;
    public static final int INDEX_FACTURA = 1;
    public static final int INDEX_DESCOBRO = 2;
    
    
    private static final String EMPTY_10 = "          ";
    private static final String EMPTY_2 = "  ";
    private static final String EMPTY = " ";
	
	private ByteArrayOutputStream doc;
	
	public static int cantRegistrosMic = 0;

	@Autowired
	private IParametroServicio parametroServicio;
	@Autowired
	private IOperacionTagetikDao operacionTagetikDao;
//	@Autowired
//	private OperacionTruncaMapeador operacionTruncaMapeador;
	
	@Override
	public void generarArchivoOperacionesTagetik(String fechaHasta) throws NegocioExcepcion {
		try {
			OperacionTagetikFiltro opeTagetikFiltro = new OperacionTagetikFiltro();
			opeTagetikFiltro.setFechaHasta(fechaHasta);
			

					
			List<Map<String,Object>> resultado = operacionTagetikDao.obtenerFacturasTagetik(opeTagetikFiltro);
			List<TagetikBatch> listaTagetikCalipso = new ArrayList<TagetikBatch>();
			List<TagetikBatch> listaTageTikMic = new ArrayList<TagetikBatch>();
			//Armo los objetos Tagetik Batc
			for (Map<String,Object> registro : resultado) {
				TagetikBatch tagetik = new TagetikBatch(registro);
				if (SistemaEnum.MIC.name().equals(tagetik.getOrigen())) {
					listaTageTikMic.add(tagetik);
				} else {
					listaTagetikCalipso.add(tagetik);
				}
			}

			try {
				Date fecha = Utilidad.parseDatePickerString(fechaHasta);
				String cabeceraMic = null;
				String carpetaDestino = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.tagetik");

				if (Validaciones.isCollectionNotEmpty(listaTageTikMic)) {
					StringBuffer mic = new StringBuffer();
					for (TagetikBatch tag : listaTageTikMic) {	
						generarLineaRegistroMic(tag, mic);
					}
					cabeceraMic = crearCabeceraMic(fecha);
					ControlArchivo.escribirArchivosTagetik(mic, carpetaDestino, NOMBRE_ARCHIVO_MIC+Utilidad.formatDateAAAAMMDD(fecha)+EXT_ARCH,cabeceraMic);
				}

				if (Validaciones.isCollectionNotEmpty(listaTagetikCalipso)) {
					Collections.sort(listaTagetikCalipso, new ComparatorTagetikBatch());
					StringBuffer calipso = new StringBuffer();

					completarImporteEnMonedaOrigen(listaTagetikCalipso, calipso); 
					ControlArchivo.escribirArchivosTagetik(calipso, carpetaDestino, NOMBRE_ARCHIVO_CALIPSO+Utilidad.formatDateAAAAMMDD(fecha) + EXT_ARCH, null);
				}
			} catch (ShivaExcepcion | ParseException e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		} catch (PersistenciaExcepcion e) {
 			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
	}


	private String crearCabeceraMic(Date fecha) {
		String fechaEjecucion = Utilidad.formatDatePointSeparated(fecha);
		String cabeceraMic = Constantes.MIC+SEMICOLON+"1"+SEMICOLON+fechaEjecucion+SEMICOLON+ String.format("%09d",cantRegistrosMic);
		return cabeceraMic;
	}
	private static void completarImporteEnMonedaOrigen(List<TagetikBatch> listaTagetikCalipso, StringBuffer calipso) throws NegocioExcepcion {
		//Collections.sort(listaTagetikCalipso, new ComparatorTagetikBatch());
		Iterator<TagetikBatch> iteratorTagetik = listaTagetikCalipso.iterator();

		Long idOperacion = 0l;
		TagetikBatch tagetik = null;
		boolean salir  = false;
		boolean salirOperacion = false;
		boolean procesar = false;
		
//		// obtengo el primer registro
		if (iteratorTagetik.hasNext()) {
			tagetik = iteratorTagetik.next();
			idOperacion = tagetik.getIdOperacion();	
		}
		// recorro los registros restantes los registros
		List<TagetikBatch> tagetikOperacion = new ArrayList<TagetikBatch>();
		Set<String> iddocctascobs = new HashSet<String>();
		while (!salir) {
			salirOperacion = false;
			while (!salirOperacion) {
				if (idOperacion.compareTo(tagetik.getIdOperacion()) != 0) {
					salirOperacion = true;
					idOperacion = tagetik.getIdOperacion();
					procesar = true;
					if (procesar) {
						procesarTagetikPorOperacion(tagetikOperacion, iddocctascobs, calipso);
						tagetikOperacion.clear();
						iddocctascobs.clear();
						tagetikOperacion.add(tagetik);
						iddocctascobs.add(tagetik.getIdCuentaCob());
					}
						
				} else { 
					tagetikOperacion.add(tagetik);
					iddocctascobs.add(tagetik.getIdCuentaCob());
				}
				
				if (iteratorTagetik.hasNext()) {
					tagetik = iteratorTagetik.next();
				} else {
					if (!tagetikOperacion.isEmpty()) {
						procesarTagetikPorOperacion(tagetikOperacion, iddocctascobs, calipso);
						
					}
					salir = true;
					salirOperacion = true;
				}

			}
		}
		return;
	}
	
	private static void procesarTagetikPorOperacion(List<TagetikBatch> listaTagetik, Set<String> iddocctascobs, StringBuffer calipso) throws NegocioExcepcion {
		if (listaTagetik.size() == 0) {
			return;
		}
		// Limpio iddocctascobs padre de los registros que son "Dif" pero no son creados en la operacion
		for (TagetikBatch tagetik : listaTagetik) {
			if ("DIF".equals(tagetik.getSubtipoDocumento())) {
				if (!iddocctascobs.contains(tagetik.getIdCuentaCobPadre())) {
					// Si no lo contiene es un cobro aislado o sea el documento Diferencia de cambio no se creo en la operacion
					tagetik.setIdCuentaCobPadre("");
				}
			}
		}
		int index = 0;
		
		//String iddocctascob = listaTagetik.get(0).getIdCuentaCob();
		Integer numeroTransaccion = listaTagetik.get(0).getNumeroTransaccion();
		Date fecha = new Date(listaTagetik.get(0).getFechaValor().getTime());
		BigDecimal importeEnMonedaOrigenAcu = BigDecimal.ZERO;
		
		while (index < listaTagetik.size()) {
			TagetikBatch tagetik = listaTagetik.get(index);
			if (numeroTransaccion.compareTo(tagetik.getNumeroTransaccion()) != 0) {
				numeroTransaccion = tagetik.getNumeroTransaccion();
				//fecha.setTime(tagetik.getFechaValor().getTime());
				importeEnMonedaOrigenAcu = BigDecimal.ZERO;
			}
			importeEnMonedaOrigenAcu = calcularImporteEnMonedaOrigen(importeEnMonedaOrigenAcu, listaTagetik, tagetik, index, numeroTransaccion, fecha);
			generarLineaResgitroCalipso(tagetik, calipso);
			index++;
		}
	}
	
	private static BigDecimal calcularImporteEnMonedaOrigen(
		BigDecimal importeEnMonedaOrigenAcu,
		List<TagetikBatch> listaTagetik,
		TagetikBatch tagetik,
		int index,
		Integer numeroTransaccion,
		Date fecha
	) {
		if (
			TipoComprobanteEnum.DEB.equals(tagetik.getTipoComprobante()) ||
			TipoComprobanteEnum.FAC.equals(tagetik.getTipoComprobante())
		) {
			if (Validaciones.isNullEmptyOrDash(tagetik.getIdCuentaCobPadre())) {
				boolean ultimoElemento = false;
				if (listaTagetik.size() > index + 1) {
					if (
						//!numer.equals(listaTagetik.get(index + 1).getIdCuentaCob()) ||
						//!Utilidad.formatDatePointSeparated(listaTagetik.get(index + 1).getFechaValor()).equals(Utilidad.formatDatePointSeparated(fecha))
						numeroTransaccion.compareTo(listaTagetik.get(index + 1).getNumeroTransaccion()) != 0 ||
						(
							!TipoComprobanteEnum.DEB.equals(listaTagetik.get(index + 1).getTipoComprobante()) &&
							!TipoComprobanteEnum.FAC.equals(listaTagetik.get(index + 1).getTipoComprobante())
						)
					) {
						ultimoElemento = true;
					}
				} else {
					// es el ultimo elemento
					ultimoElemento = true;
				}
				importeEnMonedaOrigenAcu = tagetik.calcularImporte(importeEnMonedaOrigenAcu, ultimoElemento);
			} else {
				tagetik.calcularImporte();
			}
		}
		return importeEnMonedaOrigenAcu;
	} 

	
	private static StringBuffer generarLineaRegistroMic(TagetikBatch tag, StringBuffer mic)throws NegocioExcepcion{
		mic.append(TIPO_REGISTRO_DETALLE);
		mic.append(SEMICOLON);
		if (!Validaciones.isNullOrEmpty(String.valueOf(tag.getCiclo()))){
			mic.append(String.format("%02d", tag.getCiclo()));
		} else {
			mic.append(EMPTY_2);
		}
		mic.append(SEMICOLON);
		
		// Tipo de Factura
		if (!Validaciones.isNullOrEmpty(String.valueOf(tag.getTipoFactura()))){
			mic.append(String.format("%2s", tag.getTipoFactura()));
		} else {
			mic.append(EMPTY_2);
		}
		mic.append(SEMICOLON);
		
		//pago parcial cancelatorio o un pago total
		if (TipoPagoEnum.PAGO_TOTAL.equals(tag.getTipoPago()) || TipoPagoEnum.PAGO_PARCIAL_CANCELATORIO.equals(tag.getTipoPago())) {
			mic.append(String.format("%2s", ESTADO_FACTURA));
		} else {//si es pago parcial o nulo
			mic.append(EMPTY_2);
		}
		mic.append(SEMICOLON);
		
		// Fecha de emision
		if (tag.getFechaEmision() != null) {
			mic.append(Utilidad.formatDatePointSeparated(tag.getFechaEmision()));
		} else {
			mic.append(EMPTY_10);
		}

		mic.append(SEMICOLON);

		// Fecha de vencimiento
		if (tag.getFechaVencimiento() != null) {
			mic.append(Utilidad.formatDatePointSeparated(tag.getFechaVencimiento()));
		} else {
			mic.append(EMPTY_10);
		}
		mic.append(SEMICOLON);
		
		// Fecha Valor
		if (tag.getFechaValor() != null) {
			mic.append(Utilidad.formatDatePointSeparated(tag.getFechaValor()));
		} else {
			mic.append(EMPTY_10);
		}
		mic.append(SEMICOLON);
		
		// Marketing Customer Group
		mic.append(String.format("%4s",tag.getMarketingGroup()));
		mic.append(SEMICOLON);
		
		mic.append(tag.getFechaWorkflow());
		mic.append(SEMICOLON);
	    
		mic.append(tag.getIdTipoMedioPago());
		mic.append(SEMICOLON);
		
		if (tag.getDescobro()!= null){
			mic.append(tag.getDescobro());
		} else {
			mic.append(EMPTY);
		}
	    	mic.append(SEMICOLON);
	    
		mic.append(tag.getCaja());
		mic.append(SEMICOLON);
		
		mic.append(MonedaEnum.PES.getSigla());
		mic.append(SEMICOLON);
		
		// tipo de cambio
		mic.append(TIPO_CAMBIO_MIC);
		mic.append(SEMICOLON);
		
		String importe = Utilidad.formatCurrency(tag.getImporte(),false,false);
		if (importe.startsWith("-")){
			mic.append("-"+Utilidad.rellenarCerosAmbosLadosDeLaComa(importe.substring(1, importe.length()), 10, 2));
		} else {
			mic.append(Utilidad.rellenarCerosAmbosLadosDeLaComa(importe, 11, 2));
		}
		
		mic.append(SALTO_LINEA);
		cantRegistrosMic++;
		return mic;
	}		

	private static StringBuffer generarLineaResgitroCalipso(TagetikBatch tag, StringBuffer calipso) throws NegocioExcepcion {
		
		// Origen
		calipso.append(ORIGEN_CALIPSO);
		calipso.append(SEMICOLON);
		// Compañía
		calipso.append(COMPANIA_TELECOM);
		calipso.append(SEMICOLON);
		// Cliente
		if(!Validaciones.isNullOrEmpty(String.valueOf(tag.getIdCliente()))){
			calipso.append(String.valueOf(tag.getIdCliente()));
		}
		calipso.append(SEMICOLON);
		// Razón Social
		if(!Validaciones.isNullOrEmpty(tag.getRazonSocial())){
			calipso.append(tag.getRazonSocial());
		}
		calipso.append(SEMICOLON);
		// Tipo de cliente
		if(!Validaciones.isNullOrEmpty(String.valueOf(tag.getTipoCliente()))){
			calipso.append(String.valueOf(tag.getTipoCliente()));
		}
		calipso.append(SEMICOLON);
		// Tipo de documento
		if(tag.getTipoComprobante() != null){
			calipso.append(tag.getTipoComprobante().name());
		}
		calipso.append(SEMICOLON);
		// TODO Subtipo de documento
		if (!Validaciones.isObjectNull(tag.getSubtipoDocumento())) {
			calipso.append(tag.getSubtipoDocumento());
		}
		calipso.append(SEMICOLON);
		// Clase
		if(tag.getClaseComprobante() != null){
			calipso.append(String.valueOf(tag.getClaseComprobante().name()));
		}
		calipso.append(SEMICOLON);
		// Sucursal
		if(!Validaciones.isNullOrEmpty(tag.getSucComprobante())){
			calipso.append(tag.getSucComprobante());
		}
		calipso.append(SEMICOLON);
		// Número
		if(!Validaciones.isNullOrEmpty(tag.getNumComprobante())){
			calipso.append(String.valueOf(tag.getNumComprobante()));
		}
		calipso.append(SEMICOLON);
		// Iddocctascob
		if (!Validaciones.isNullOrEmpty(tag.getIdCuentaCob())) {
			calipso.append(tag.getIdCuentaCob());
		}
		calipso.append(SEMICOLON);
		// TODO Iddocctascob del documento padre
		if (!Validaciones.isNullOrEmpty(tag.getIdCuentaCobPadre())) {
			calipso.append(tag.getIdCuentaCobPadre());
		}
		calipso.append(SEMICOLON);
		// Idcob !!!
		calipso.append(tag.getIdOperacion());
		calipso.append(SEMICOLON);
		// Moneda
		if (!Validaciones.isObjectNull(tag.getMoneda())){
			if (MonedaEnum.DOL.equals(tag.getMoneda())) {
				calipso.append(tag.getMoneda().name());
			} else {
				calipso.append(tag.getMoneda().getSigla());
			}
		}
		calipso.append(SEMICOLON);
		// Tipo de Cambio
		if (!Validaciones.isNullOrEmpty(String.valueOf(tag.getTipoCambio()))){
			calipso.append(Utilidad.rellenarCerosAmbosLadosDeLaComa(Utilidad.formatDecimales(tag.getTipoCambio(), 5),3,5));
		}
		calipso.append(SEMICOLON);
		// Fecha del valor
		if(!Validaciones.isNullOrEmpty(String.valueOf(tag.getFechaValor()))){
			calipso.append(Utilidad.formatDatePointSeparated(tag.getFechaValor()));
		}
		calipso.append(SEMICOLON);
		// Fecha de operación
		calipso.append(tag.getFechaWorkflow());
		calipso.append(SEMICOLON);
		// Medio de pago
		calipso.append(tag.getIdTipoMedioPago());
		calipso.append(SEMICOLON);
		// TODO Moneda del medio de pago
		if (!Validaciones.isObjectNull(tag.getMonedaMedioPago())){
			if (MonedaEnum.DOL.equals(tag.getMonedaMedioPago())) {
				calipso.append(tag.getMonedaMedioPago().name());
			} else {
				calipso.append(tag.getMonedaMedioPago().getSigla());
			}
		}
		calipso.append(SEMICOLON);
		// TODO Tipo de cambio del medio de pago
		if (!Validaciones.isObjectNull(tag.getTipoCambioMedioPago())) {
			calipso.append(Utilidad.rellenarCerosAmbosLadosDeLaComa(Utilidad.formatDecimales(tag.getTipoCambioMedioPago(), 5),3,5));
		}
		calipso.append(SEMICOLON);
		// TODO Importe del medio de pago en moneda origen
		if (!Validaciones.isObjectNull(tag.getImporteMedioPagoMonedaOrigen())) {
			calipso.append(Utilidad.formatCurrency(tag.getImporteMedioPagoMonedaOrigen(),false,false));
		}
		calipso.append(SEMICOLON);
		// Canal 
		calipso.append(tag.getCaja());
		calipso.append(SEMICOLON);
		// Importe en moneda origen
		calipso.append(Utilidad.formatCurrency(tag.getImporte(),false,false));
		calipso.append(SEMICOLON);
		// Marca de descobro
		if(tag.getDescobro() != null){
			calipso.append(tag.getDescobro());
		}
		calipso.append(SALTO_LINEA);
		
		return calipso;
	}
	

	

	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		return null;
	}

	@Override
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		return null;
	}

	@Override
	public Long crear(DTO dto) throws NegocioExcepcion {
		return null;
	}

	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
	}

	@Override
	public void anular(DTO dto) throws NegocioExcepcion {
	}

	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		return null;
	}

	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {
	}
	
	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	public IOperacionTagetikDao getOperacionTagetikDao() {
		return operacionTagetikDao;
	}

	public void setOperacionTagetikDao(IOperacionTagetikDao operacionTagetikDao) {
		this.operacionTagetikDao = operacionTagetikDao;
	}

//	public OperacionTruncaMapeador getOperacionTruncaMapeador() {
//		return operacionTruncaMapeador;
//	}
//
//	public void setOperacionTruncaMapeador(
//			OperacionTruncaMapeador operacionTruncaMapeador) {
//		this.operacionTruncaMapeador = operacionTruncaMapeador;
//	}

	public ByteArrayOutputStream getDoc() {
		return doc;
	}

	public void setDoc(ByteArrayOutputStream doc) {
		this.doc = doc;
	}

}
