package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IRetencionServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorRetencion;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class RetencionServicioImpl implements IRetencionServicio {
	
	@Autowired
	private IValorServicio valorServicio;
	
	@Autowired
	private ICobroImputacionServicio cobroServicio;
	
	@Override
	public void generarReporte(String fechaHasta) throws NegocioExcepcion {
		String idValor = "";
		String contenido = "";
		ShvValValorRetencion retencion;
		BigDecimal total = new BigDecimal(0);
		
		Traza.auditoria(getClass(), "generarReporte", "Preparando la lista de retenciones para el reporte...");
		GregorianCalendar fechaDesdeDate = new GregorianCalendar();
		GregorianCalendar fechaHastaDate = new GregorianCalendar();
		try {
			fechaDesdeDate.setTime(Utilidad.parseMesAnioBarraString(fechaHasta));
		} catch (ParseException e1) {
			Traza.error(getClass(), e1.getMessage(), e1);
		}
		fechaHastaDate.setTime(fechaDesdeDate.getTime());
		fechaHastaDate.roll(GregorianCalendar.DATE,-1);
		String fechaDesde = Utilidad.formatDatePicker(fechaDesdeDate.getTime()); 	
		fechaHasta = Utilidad.formatDatePicker(fechaHastaDate.getTime()); 		
		Filtro retencionFiltro = new Filtro();
		retencionFiltro.setFechaDesde(fechaDesde);
		retencionFiltro.setFechaHasta(fechaHasta);
		List<ShvValValor> listaRetenciones = valorServicio.listarRetencionesParaReporte(retencionFiltro);
		Traza.auditoria(getClass(), "generarReporte - Resultado", "Lista de retenciones (cant): " + listaRetenciones.size());
		
		for (ShvValValor val : listaRetenciones) {
			try {
				idValor = val.getIdValor().toString();
				
				retencion = val.getValValorRetencion();
				contenido += Utilidad.rellenarEspaciosIzquierda((retencion.getParamJurisdiccion()!=null)?retencion.getParamJurisdiccion().getIdJurisdiccion():null, 3);
				String cuit = retencion.getCuit();
				if(!Validaciones.isNullOrEmpty(cuit) && cuit.length() == 11){
					contenido += Utilidad.rellenarEspaciosIzquierda(cuit.substring(0, 2), 2) + "-" 
							+ Utilidad.rellenarEspaciosIzquierda(cuit.substring(2, 10), 8) + "-"
							+ Utilidad.rellenarEspaciosIzquierda(cuit.substring(10, 11), 1);
				}else{
					contenido += Utilidad.rellenarEspaciosIzquierda("",13);
				}
				contenido += Utilidad.rellenarEspaciosIzquierda(Utilidad.formatDatePicker(retencion.getFechaEmision()), 10);
				contenido += Utilidad.rellenarCerosIzquierda("0", 4); //Deberia ser sucursal de certificacion -- Antes: retencion.getSucursalComprobante()
				contenido += Utilidad.rellenarCerosIzquierda(retencion.getNroConstanciaRetencion(), 16);
				
				if (poseeFacturaOnline(retencion)) {
					//TODO esta definido en la interface como 1 caracter pero tiene 2 en la base
					contenido += Utilidad.rellenarEspaciosIzquierda((retencion.getParamTipoComprobante()!=null)? 
							(TipoComprobanteEnum.getEnumByName(retencion.getParamTipoComprobante().getIdTipoComprobante()) != null) 
								? TipoComprobanteEnum.getEnumByName(retencion.getParamTipoComprobante().getIdTipoComprobante()).sigla():"F":"F", 1);
					String letraComprobante = getLetraComprobante(retencion);
					contenido += Utilidad.rellenarEspaciosIzquierda((letraComprobante != null)?letraComprobante:"A", 1);
					
					String sucursal = Utilidad.rellenarCerosIzquierda(retencion.getSucursalComprobante(), 4);
					String nroComprobante = Utilidad.rellenarCerosIzquierda(retencion.getNumeroComprobante(), 8);
					contenido += Utilidad.rellenarCerosIzquierda(sucursal+nroComprobante, 20);
				} else {
					ShvCobFactura facturaImputada = getFacturaImputada(retencion); 
					if (facturaImputada!=null) {
						String tipoComprobante =TipoComprobanteEnum.getEnumByName(facturaImputada.getTipoComprobante().getIdTipoComprobante()).sigla();
						if(!Validaciones.isNullOrEmpty(tipoComprobante)){
							contenido += Utilidad.rellenarEspaciosIzquierda((tipoComprobante != null)?tipoComprobante:"F", 1);
						} else {
							contenido += Constantes.WHITESPACE;
						}
						if(!Validaciones.isObjectNull(facturaImputada.getClaseComprobante())){
							String letraComprobante = getLetraComprobante(facturaImputada.getClaseComprobante().name());
							contenido += Utilidad.rellenarEspaciosIzquierda((letraComprobante != null)?letraComprobante:"A", 1);
							String sucursal = Utilidad.rellenarCerosIzquierda(facturaImputada.getSucursalComprobante(), 4);
							String nroComprobante = Utilidad.rellenarCerosIzquierda(facturaImputada.getNumeroComprobante(), 8);
							contenido += Utilidad.rellenarCerosIzquierda(sucursal+nroComprobante, 20);
						} else {
							contenido += Constantes.WHITESPACE;
							contenido += Utilidad.rellenarCerosIzquierda("0", 20);
						}
						
					} else {
						contenido += "F";
						contenido += "A";
						contenido += Utilidad.rellenarCerosIzquierda("0", 20);
					}
				}
				
				contenido += Utilidad.rellenarCerosIzquierda(Utilidad.formatCurrency(Utilidad.formatCurrency(val.getImporte(),2),false,false), 11);
				total = total.add(val.getImporte());
				contenido+=System.lineSeparator();
			} catch (NegocioExcepcion e) {
				String mensajeError = "Se produjo un error en el valor id: " + idValor;
				
				System.err.println(mensajeError);
				Traza.error(getClass(), mensajeError);
				throw new NegocioExcepcion(e.getMessage(),e);
			}
		}
		try {
			Date fechaParametro = Utilidad.parseDatePickerString(fechaHasta);
			String carpetaDestino = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.retencionesIIBB");
			String nombreArchivo = Constantes.SHIVA_APLICACION.toUpperCase()+".Retenciones."+Utilidad.formatDateAAAAMMDD(fechaParametro)+".txt";
		
			ControlArchivo.escribir(contenido, carpetaDestino, nombreArchivo);
			
			String mensajeTraza = "Se ha generado el archivo: " + nombreArchivo;
			System.out.println(mensajeTraza);
			Traza.auditoria(getClass(), "generarReporte", mensajeTraza);
		} catch (ShivaExcepcion | ParseException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	/**
	 * Verifico que si es factura online
	 * @param retencion
	 * @return
	 */
	private boolean poseeFacturaOnline(ShvValValorRetencion retencion) {
		if (Validaciones.isObjectNull(retencion.getParamTipoComprobante())
				&& Validaciones.isObjectNull(retencion.getParamTipoLetraComprobante())
				&& Validaciones.isNullOrEmpty(retencion.getNumeroComprobante())) {
			return false;	
		}
		return true;
	}
	
	/**
	 * Verifico que si fue imputado
	 * @param retencion
	 * @return
	 * @throws NegocioExcepcion 
	 */
	private ShvCobFactura getFacturaImputada(ShvValValorRetencion retencion) throws NegocioExcepcion {
		List<String> listaId = new ArrayList<String>();
		listaId.add(retencion.getIdValor().toString());
		
		List<ShvCobFactura> listaFactura = cobroServicio.buscarFacturaParaReporteRetencion(listaId);
		Traza.auditoria(getClass(), "getFacturaImputada", "Resultado ListaFactura: " + listaFactura.size());
		
		BigDecimal mayorImporteACobrar = new BigDecimal(0);
		ShvCobFactura facturaConMayorImporteACobrar = null;
		
		for (ShvCobFactura factura : listaFactura) {
			Set<ShvCobMedioPago> listaMp = factura.getTransaccion().getShvCobMedioPago();
			Iterator<ShvCobMedioPago> iteradorMp = listaMp.iterator();
			
			while (iteradorMp.hasNext()){
				ShvCobMedioPago mp = iteradorMp.next();
				if (mp instanceof ShvCobMedioPagoShiva){
					ShvCobMedioPagoShiva medioPagoShiva = ((ShvCobMedioPagoShiva) mp);
					if (medioPagoShiva.getIdValor().equals(retencion.getIdValor())){
						
						if (factura.getImporteCobrar().compareTo(mayorImporteACobrar) > 0) {
							facturaConMayorImporteACobrar = factura;
							mayorImporteACobrar = factura.getImporteCobrar();
						}
						break;
					}
				}
			}
		}
		
		Traza.auditoria(getClass(), "getFacturaImputada", "Resultado factura Imputada (idValor: "+retencion.getIdValor().toString()+"): " + facturaConMayorImporteACobrar);
		return facturaConMayorImporteACobrar;
	}
	
	/**
	 * Retorno el tipo de letra 
	 * @param retencion
	 * @return
	 */
	private String getLetraComprobante(ShvValValorRetencion retencion) {
		if (Validaciones.isObjectNull(retencion.getParamTipoLetraComprobante())) {
			return " ";
		} else {
			String letra = retencion.getParamTipoLetraComprobante().getDescripcion();
			return this.getLetraComprobante(letra);
		}
	}
	
	/**
	 * Retorno el tipo de letra 
	 * @param retencion
	 * @return
	 */
	private String getLetraComprobante(String letra) {
		if (Validaciones.isNullOrEmpty(letra)) {
			return " ";
		} else {
			if ("S".equalsIgnoreCase(letra) || "D".equalsIgnoreCase(letra)) {
				return " ";
			}
			return letra;
		}
	}
	
	
	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}

	public ICobroImputacionServicio getCobroServicio() {
		return cobroServicio;
	}

	public void setCobroServicio(ICobroImputacionServicio cobroServicio) {
		this.cobroServicio = cobroServicio;
	}
	
	
	
}
