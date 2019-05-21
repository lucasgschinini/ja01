package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.batch.bean.SubdiarioBatch;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.ISubdiarioServicio;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IParametroDao;

public class SubdiarioServicioImpl implements ISubdiarioServicio {
	@Autowired
	ICobroImputacionServicio cobroServicio;
	@Autowired
	IDescobroServicio descobroServicio;
	@Autowired
	IGenericoDao genericoDao;
	@Autowired
	IParametroDao parametroDao;
	
	private static final String ESPACIOS_1 = /*84*/"                                                                                    ";
	private static final String ESPACIOS_2 = /*80*/"                                                                                ";
	private static final String ESPACIOS_3 = /*62*/"                                                             ";
	private static final String ESPACIOS_4 = /*81*/"                                                                                 ";
	private static final String ESPACIOS_5 = /*118*/"                                                                                                                     ";
	private static final String ESPACIOS_6 = /*137*/"                                                                                                                                         ";
	private static final String LINEA_1 = ESPACIOS_1+"TELECOM ARGENTINA S.A."+System.lineSeparator();
	private static final String LINEA_2 = ESPACIOS_2+"A.M. de Justo 50 (1107) C.A.B.A"+ESPACIOS_3;
	private static final String LINEA_3 = ESPACIOS_4+"SUBDIARIO DE COBRANZAS SHIVA"+System.lineSeparator();
	private static final String LINEA_4 = /*64 */"Fecha Sistema Desde: {0}  Fecha Sistema Hasta: {1}";
	private static final String LINEA_5 = "TOTAL GRAL"+ESPACIOS_6;
	private static final String PAGINA = /*8*/"Página: {0}"+System.lineSeparator();
	private static final String FECHA = /*17*/"Fecha: {0}"+System.lineSeparator();
	private static final String FECHA_SISTEMA = /*25 mas linea en blanco*/"Fecha Sistema: {0}"+System.lineSeparator();
	private static final String CABECERA = /*190*/"Id de Transacción Nro. Rec/Nota Reemb Tipo Op. Tipo Doc. Nro. Doc.         Nro. Cliente"
											+ " Razón Social                                            U.M.    Importe Cobrado    Importe Cobrado U$S    Fecha Cobro Of. Cobro"
			                                + System.lineSeparator();	
	
	private static final String SEPARADOR_COLUMNAS = " ";

	private static String EXT_ARCH  = ".txt";
	private static String NOMBRE_ARCHIVO = "SHIVA.SubdiarioCobranza.";
	
	@Override
	public void generarSubdiario(String fechaProcesamiento) throws NegocioExcepcion {
		GregorianCalendar fechaUltimoDiaMes = new GregorianCalendar(); 
		try {
			fechaUltimoDiaMes.setTime(Utilidad.parseMesAnioBarraString(fechaProcesamiento));
		} catch (ParseException e1) {
			throw new NegocioExcepcion(e1.getMessage(),e1);
		}
		fechaUltimoDiaMes.roll(GregorianCalendar.DATE, -1);
		
		TreeMap<GregorianCalendar, List<SubdiarioBatch>> map = generarMatriz(fechaUltimoDiaMes);
		String contenido = "";
		int contPaginas = 1;
		Iterator<GregorianCalendar> iteradorFecha= map.keySet().iterator();
		Iterator<SubdiarioBatch> iteradorFactura;
		String cuerpo = "";
		int contLineas = 0;
		BigDecimal total = new BigDecimal(0);
		BigDecimal totalDolares = new BigDecimal(0);
		String fechaDesde = null;
		GregorianCalendar fechaSistema = null;

		while (iteradorFecha.hasNext()){
			fechaSistema = iteradorFecha.next();
			iteradorFactura = map.get(fechaSistema).iterator();
			

			while (iteradorFactura.hasNext()){

				if (Validaciones.isObjectNull(fechaDesde)) {
					fechaDesde = Utilidad.formatDatePicker(fechaSistema.getTime());
				}
				
				cuerpo += Utilidad.reemplazarMensajes(FECHA_SISTEMA,Utilidad.formatDatePicker(fechaSistema.getTime()))+System.lineSeparator();
				if(contLineas == 0){
					cuerpo += CABECERA;
					contLineas++;
				}
				contLineas = contLineas+2;
				while (contLineas < 21 && iteradorFactura.hasNext()){ //inserta los registros
					SubdiarioBatch subdiario = iteradorFactura.next();
					cuerpo += armarLineaFactura(subdiario);
					if (!Constantes.MONEDA_PES.equals(subdiario.getMoneda())
							&& (TipoComprobanteEnum.FAC.name().equals(subdiario.getTipoComprobante()) || TipoComprobanteEnum.DEB.name().equals(subdiario.getTipoComprobante()))) {

						// Si el documento es en dólares podemos tener dos opciones:
						// cobro en pesos, entonces el importe en moneda origen debe venir informado
						// cobro en dolares, como la moneda es la misma que la del documento, tomo directamente el importe que ya viene en dolares. Aqui no existe importe pesificado 
						
						BigDecimal importeDolares = subdiario.getImporte();
						if (!Validaciones.isObjectNull(subdiario.getImporteMonedaOrigen())) {
							importeDolares = subdiario.getImporteMonedaOrigen();
						}
						
						totalDolares = totalDolares.add(importeDolares);
						
						if (!Validaciones.isObjectNull(subdiario.getImporteAplicadoEnPesos())) {
							total = total.add(subdiario.getImporteAplicadoEnPesos());
						}
						
					}else{
						total = total.add(subdiario.getImporte());
					}
					
					contLineas++;
				}
				if (contLineas == 20){//para que no se agregue la fecha en la ultima linea
					cuerpo+=System.lineSeparator();
					contLineas++;
				}
				if (contLineas == 21){ //21+4 - Fin de pagina
					String stringContPaginas = String.valueOf(contPaginas);
					contenido += LINEA_1+LINEA_2+Utilidad.reemplazarMensajes(FECHA,Utilidad.formatDatePicker(new Date()))+LINEA_3;
					contenido += Utilidad.reemplazarMensajes(LINEA_4, fechaDesde, Utilidad.formatDatePicker(fechaSistema.getTime())) + ESPACIOS_5.substring(stringContPaginas.length()) + Utilidad.reemplazarMensajes(PAGINA, stringContPaginas);
					contenido += cuerpo;
					contLineas = 0;
					cuerpo = "";
					contPaginas++;
					
					fechaDesde = null;
				}
			}
		}
		if (cuerpo != ""){
			String stringContPaginas = String.valueOf(contPaginas);
			contenido += LINEA_1+LINEA_2+Utilidad.reemplazarMensajes(FECHA,Utilidad.formatDatePicker(new Date()))+LINEA_3;
			contenido += Utilidad.reemplazarMensajes(LINEA_4,fechaDesde,Utilidad.formatDatePicker(fechaSistema.getTime()))+ESPACIOS_5.substring(stringContPaginas.length())+Utilidad.reemplazarMensajes(PAGINA,stringContPaginas);
			contenido += cuerpo;
		}
		contenido +=LINEA_5+Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(Utilidad.formatCurrency(total, 2),false,false), 20);
		contenido +=Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(Utilidad.formatCurrency(totalDolares, 2),false,false), 23);
		
		try {
			String nombreArchivoSubdiarioFechaActual = NOMBRE_ARCHIVO + Utilidad.formatDateAAAAMMDD(fechaUltimoDiaMes.getTime()) + EXT_ARCH;
		
			String carpetaDestino = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.subdiario");

			ControlArchivo.escribir(contenido, carpetaDestino, nombreArchivoSubdiarioFechaActual);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	private TreeMap<GregorianCalendar, List<SubdiarioBatch>> generarMatriz(GregorianCalendar fechaUltimoDiaMes) throws NegocioExcepcion {
		TreeMap<GregorianCalendar,List<SubdiarioBatch>> map = new TreeMap<GregorianCalendar,List<SubdiarioBatch>>();
		List<SubdiarioBatch> listaResultados = cobroServicio.listarCobrosParaSubdiario(fechaUltimoDiaMes);
		for (SubdiarioBatch registro : listaResultados) {
			GregorianCalendar fecha = new GregorianCalendar();
			fecha.setTime(registro.getFechaProcesamiento());
			fecha.set(GregorianCalendar.SECOND, 0);
			fecha.set(GregorianCalendar.MINUTE, 0);
			fecha.set(GregorianCalendar.HOUR_OF_DAY, 0);
			fecha.set(GregorianCalendar.MILLISECOND, 0);
			if (map.containsKey(fecha)){
				map.get(fecha).add(registro);
			} else {
				List<SubdiarioBatch> listaSubdiario = new ArrayList<SubdiarioBatch>();
				listaSubdiario.add(registro);
				map.put(fecha, listaSubdiario);
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @param subdiario
	 * @return
	 * @throws NegocioExcepcion
	 */
	private String armarLineaFactura(SubdiarioBatch subdiario) throws NegocioExcepcion {

		StringBuffer resultado = new StringBuffer(); 
		// Id de Transaccion (17 posiciones total)	
		
		resultado.append(Utilidad.rellenarEspaciosDerecha(subdiario.getNumeroTransaccionFicticio(), 17));

		// Separador (1 posicion)
		resultado.append(SEPARADOR_COLUMNAS);

		// Numero de Recibo / Nota de Reembolso (19 posiciones total)
		resultado.append(Utilidad.rellenarEspaciosDerecha(subdiario.getNumeroRecibo(), 19));
		
		// Separador (1 posicion)
		resultado.append(SEPARADOR_COLUMNAS);

		// Tipo de Operación (8 posiciones total)
		resultado.append(Utilidad.rellenarEspaciosDerecha(TipoOperacionEnum.getEnumByName(subdiario.getTipoOperacion()).tipoSubdiario(), 8));

		// Separador (1 posicion)
		resultado.append(SEPARADOR_COLUMNAS);

		// Documento Legal (10 posiciones tipo comprobante + 15 numero legal: 25 posiciones total)
		resultado.append(Utilidad.rellenarEspaciosDerecha(subdiario.getTipoComprobante(), 10));
		

		// Si el comprobante es "D" o "S", el mismo no se envía ya que para el usuario es un blanco.
		if (!Validaciones.isNullOrEmpty(subdiario.getDocumentoLegal())){
			resultado.append(Utilidad.rellenarEspaciosIzquierda(subdiario.getDocumentoLegal().replace("S-", "").replace("D-", ""), 15));
		}else{
			resultado.append(Utilidad.rellenarEspaciosIzquierda("", 15));
		}
		
		// Separador (1 posicion)
		resultado.append(SEPARADOR_COLUMNAS);

		// Codigo de cliente legado (14 posiciones total)
		resultado.append(Utilidad.rellenarEspaciosIzquierda(String.valueOf(subdiario.getClienteLegado()), 14));
		
		// Separador (1 posicion)
		resultado.append(SEPARADOR_COLUMNAS);

		// Razon Social (54 posiciones)
		resultado.append(Utilidad.rellenarEspaciosDerecha(subdiario.getRazonSocial(), 54).substring(0, 54));
		
		// Separador (1 posicion)
		resultado.append(SEPARADOR_COLUMNAS);

		
		if ((TipoOperacionEnum.DESCOBRO.tipoSubdiario().equals(TipoOperacionEnum.getEnumByName(subdiario.getTipoOperacion()).tipoSubdiario())
				&& !TipoComprobanteEnum.CRE.getValor().equals(subdiario.getTipoComprobante()))
				
				|| (TipoOperacionEnum.COBRO.tipoSubdiario().equals(TipoOperacionEnum.getEnumByName(subdiario.getTipoOperacion()).tipoSubdiario())
						&& TipoComprobanteEnum.CRE.getValor().equals(subdiario.getTipoComprobante()))
				
				|| TipoOperacionEnum.REINTEGRO.tipoSubdiario().equals(TipoOperacionEnum.getEnumByName(subdiario.getTipoOperacion()).tipoSubdiario())
				
				|| TipoOperacionEnum.GANANCIA.tipoSubdiario().equals(TipoOperacionEnum.getEnumByName(subdiario.getTipoOperacion()).tipoSubdiario())){
			
			subdiario.setImporte(subdiario.getImporte().multiply(new BigDecimal(-1)));
			if (!Validaciones.isObjectNull(subdiario.getImporteAplicadoEnPesos())) {
				subdiario.setImporteAplicadoEnPesos(subdiario.getImporteAplicadoEnPesos().multiply(new BigDecimal(-1)));
			}
			if (!Validaciones.isObjectNull(subdiario.getImporteMonedaOrigen())) {
				subdiario.setImporteMonedaOrigen(subdiario.getImporteMonedaOrigen().multiply(new BigDecimal(-1)));
			}
		}
		
		if ("$".equals(subdiario.getMoneda())){
			subdiario.setMoneda("PES"); 
		}

		if (Constantes.MONEDA_PES.equals(subdiario.getMoneda())){
			
			// Moneda (5 posiciones)
			resultado.append(Utilidad.rellenarEspaciosIzquierda(Constantes.MONEDA_$, 5));
			
			// Separador (1 posicion)
			resultado.append(SEPARADOR_COLUMNAS);

			// Importe Cobrado (18 posiciones)
			resultado.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(Utilidad.formatCurrency(subdiario.getImporte(), 2), false, false), 18));

			// Separador (1 posicion)
			resultado.append(SEPARADOR_COLUMNAS);

			// Importe Cobrado U$S
			resultado.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(Utilidad.formatCurrency(BigDecimal.ZERO, 2),false,false), 22));

		} else if (Constantes.MONEDA_DOL.equals(subdiario.getMoneda())){
			
			// Moneda (5 posiciones)
			resultado.append(Utilidad.rellenarEspaciosIzquierda(Constantes.MONEDA_U$D, 5));

			// Separador (1 posicion)
			resultado.append(SEPARADOR_COLUMNAS);
			
			if (subdiario.getIdFactura() != 0 && !TipoComprobanteEnum.DUC.name().equals(subdiario.getTipoComprobante())){ 

				// Importe Cobrado (18 posiciones)
				if (!Validaciones.isObjectNull(subdiario.getImporteAplicadoEnPesos())) {
					resultado.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(Utilidad.formatCurrency(subdiario.getImporteAplicadoEnPesos(), 2), false, false), 18));	
				} else {
					resultado.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(Utilidad.formatCurrency(BigDecimal.ZERO, 2), false, false), 18));
				}

				// Separador (1 posicion)
				resultado.append(SEPARADOR_COLUMNAS);

				// Importe Cobrado U$S
				if (!Validaciones.isObjectNull(subdiario.getImporteMonedaOrigen())) {
					resultado.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(Utilidad.formatCurrency(subdiario.getImporteMonedaOrigen(), 2), false, false), 22));
				} else {
					resultado.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(Utilidad.formatCurrency(subdiario.getImporte(), 2), false, false), 22));
				}
				
			} else {
				// Importe Cobrado (18 posiciones)
				resultado.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(Utilidad.formatCurrency(BigDecimal.ZERO, 2),false,false), 18));
				
				// Separador (1 posicion)
				resultado.append(SEPARADOR_COLUMNAS);

				// Importe Cobrado U$S
				resultado.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(Utilidad.formatCurrency(subdiario.getImporte() , 2), false, false), 22));
			}
		}
		
		// Separador (1 posicion)
		resultado.append(SEPARADOR_COLUMNAS);

		// Fecha de Cobro (14 posiciones)
		resultado.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatDatePicker(subdiario.getFechaValor()), 14));

		// Separador (1 posicion)
		resultado.append(SEPARADOR_COLUMNAS);

		// Oficina de Cobro (9 posiciones)
		resultado.append(Utilidad.rellenarEspaciosIzquierda(subdiario.getIdCaja(), 9));
		
		// Retorno de carro
		resultado.append(System.lineSeparator());

		return resultado.toString();
	}
	
	public ICobroImputacionServicio getCobroServicio() {
		return cobroServicio;
	}

	public void setCobroServicio(ICobroImputacionServicio cobroServicio) {
		this.cobroServicio = cobroServicio;
	}
}
