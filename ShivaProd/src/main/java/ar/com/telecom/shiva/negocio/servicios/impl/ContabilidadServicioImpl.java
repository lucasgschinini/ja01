package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.OrigenContableEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IContabilidadServicio;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IContabilidadDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCntContabilidadCabecera;
import ar.com.telecom.shiva.persistencia.modelo.ShvCntContabilidadDetalle;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoOrigenContable;
import ar.com.telecom.shiva.presentacion.bean.dto.ContabilidadDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;


public class ContabilidadServicioImpl extends Servicio implements IContabilidadServicio {

	@Autowired 
	IGenericoDao genericoDao;
	@Autowired 
	IBancoDao bancoDao;
	@Autowired 
	IContabilidadDao contabilidadDao;
	
	private static final String ID_TIPO_MEDIO_PAGO_CHEQUE_PD = "02";
	
	private static final String TRES_CEROS 		 = "000";
	private static final String SIETE_CEROS 	 = "0000000";
	private static final String OCHO_CEROS 		 = "00000000";
	private static final String DIEZ_CEROS 		 = "0000000000";
	private static final String QUINCE_CEROS 	 = "000000000000000";
	private static final String DIECISIETE_CEROS = "00000000000000000";
	private static final String UN_ESPACIO 		 = " ";
	private static final String DOS_ESPACIOS 	 = "  ";
	private static final String TRES_ESPACIOS 	 = "   ";
	private static final String CUATRO_ESPACIOS  = "    ";
	private static final String OCHO_ESPACIOS 	 = "        ";
	private static final String DIEZ_ESPACIOS 	 = "          ";
	private static final String ONCE_ESPACIOS 	 = "           ";
	private static final String TRECE_ESPACIOS 	 = "             ";
	private static final String QUINCE_ESPACIOS  = "               ";
	private static final String VEINTE_ESPACIOS  = "                    ";

	public static final String ORIGEN_CONT_001 = "001"; // hacer enum ??
	public static final String ORIGEN_CONT_002 = "002";
	public static final String ORIGEN_CONT_004 = "004";
	public static final String ORIGEN_CONT_005 = "005";
	public static final String ORIGEN_CONT_006 = "006";
	public static final String ORIGEN_CONT_007 = "007";
	public static final String ORIGEN_CONT_008 = "008";
	public static final String ORIGEN_CONT_009 = "009";
	public static final String ORIGEN_CONT_010 = "010";
	public static final String ORIGEN_CONT_011 = "011";
	public static final String ORIGEN_CONT_012 = "012";
	public static final String ORIGEN_CONT_013 = "013";
	public static final String ORIGEN_CONT_014 = "014";
	public static final String ORIGEN_CONT_015 = "015";
	public static final String ORIGEN_CONT_016 = "016";
	public static final String ORIGEN_CONT_017 = "017";
	public static final String ORIGEN_CONT_018 = "018";
	public static final String ORIGEN_CONT_019 = "019";
	public static final String ORIGEN_CONT_020 = "020";
	public static final String ORIGEN_CONT_021 = "021";
	public static final String ORIGEN_CONT_022 = "022";	
	public static final String ORIGEN_CONT_030 = "030";
	public static final String ORIGEN_CONT_031 = "031";
	
	public static final String CONTABILIDAD_PROCESADO_CON_ERROR = "PROC. ERROR";//Utilizado cuando hay un error en un registro puntual
	public static final String CONTABILIDAD_PROCESADO = "PROCESADO";
	public static final String CONTABILIDAD_PROCESO   = "EN PROCESO";
	public static final String CONTABILIDAD_PENDIENTE = "PENDIENTE";
	public static final String CONTABILIDAD_PENDIENTE_SIN_ACREDITACION = "P. NO ACRED.";
	public static final String CONTABILIDAD_PENDIENTE_ACREDITACION = "PEND. ACRED.";
	
	public static final String ESTADO_INACTIVIDAD = "INACTIVO";
	
	public static final int    VALOR_GARANTIA_ID 	  = 8;
	
	private static final String TIPO_REGISTRO_CABECERA = "1";
	private static final String TIPO_REGISTRO_DETALLE = "2";
	private static final String CODIGO_INTERFAZ = "SHIVA";
	private static final String SEPARADOR = "";
	
	@SuppressWarnings("unchecked")
	public void contabilizar(ContabilidadDto contabilidadDto) throws NegocioExcepcion{
		ShvCntContabilidadDetalle contDetalle = new ShvCntContabilidadDetalle();
		List<ShvParamTipoOrigenContable> origen;
		try {
			origen = (List<ShvParamTipoOrigenContable>) genericoDao.listarPorValor(ShvParamTipoOrigenContable.class, "idTipoOrigenContable", contabilidadDto.getDescripcionTipoOrigenContable());
			contDetalle.setCodigoClienteLegado(contabilidadDto.getCodigoClienteLegado());
			if (!Validaciones.isNullOrEmpty(contabilidadDto.getCuit())) {
				contDetalle.setCuit(contabilidadDto.getCuit().replace("-", ""));
			}
			contDetalle.setEstado(contabilidadDto.getEstado());
			contDetalle.setFechaValor(contabilidadDto.getFechaValor());
			contDetalle.setFechaVencimiento(contabilidadDto.getFechaVencimiento());
			if (contabilidadDto.getIdAcuerdo() != null){
				contDetalle.setIdAcuerdo(contabilidadDto.getIdAcuerdo());
				contDetalle.setIdBanco(contabilidadDto.getIdAcuerdo().getBancoCuenta().getBanco());
			}
			contDetalle.setIdAnalista(contabilidadDto.getIdAnalista());
			contDetalle.setIdJurisdiccion(contabilidadDto.getIdJurisdiccion());
			contDetalle.setIdOrganismo(contabilidadDto.getIdOrganismo());
			contDetalle.setIdTipoMedioPago(contabilidadDto.getIdTipoMedioPago());
			if (!origen.isEmpty()){
				contDetalle.setIdTipoOrigenContable(origen.get(0));
			}
			contDetalle.setImporte(contabilidadDto.getImporte());
			contDetalle.setMoneda(contabilidadDto.getMoneda());
			contDetalle.setNumeroBoleta(contabilidadDto.getNumeroBoleta());
			contDetalle.setNumeroComprobante(contabilidadDto.getNumeroComprobante());
			contDetalle.setNumeroDocumentoContable(contabilidadDto.getNumeroDocumentoContable());
			contDetalle.setFechaCreacion(new Date());
			contDetalle.setIdValor(contabilidadDto.getIdValor());
			contDetalle.setIdCobro(contabilidadDto.getIdCobro());
			contDetalle.setIdTransaccion(contabilidadDto.getIdTransaccion());
			contDetalle.setIdCaja(contabilidadDto.getIdCaja());
			contDetalle.setIdTipoCliente(contabilidadDto.getIdTipoCliente());
			contDetalle.setMoneda(contabilidadDto.getMoneda());
			contDetalle.setTipoDeCambio((contabilidadDto.getTipoDeCambio() != null)?Utilidad.rellenarCerosAmbosLados(contabilidadDto.getTipoDeCambio(),8,7):null);
			contDetalle.setTransaccion(contabilidadDto.getTransaccion());
			contDetalle.setEstadoInactividad(contabilidadDto.getEstadoInactividad());
			contabilidadDao.insertarActualizarContabilidadDetalle(contDetalle);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public String procesarArchivoSAP(String fechaHasta) throws NegocioExcepcion{
		List<ShvCntContabilidadDetalle> listaDetalle = new ArrayList<ShvCntContabilidadDetalle>();
		List<ShvCntContabilidadDetalle> listaProcesar = new ArrayList<ShvCntContabilidadDetalle>();
		try {
			Filtro contabilidadFiltro = new Filtro();
			contabilidadFiltro.setFechaHasta(fechaHasta);
			GregorianCalendar fechaParametroExtraerJuliana = new GregorianCalendar();
			fechaParametroExtraerJuliana.setTime(Utilidad.parseDatePickerString(fechaHasta));
			GregorianCalendar fechaParametroSumoDia = new GregorianCalendar();
			BeanUtils.copyProperties(fechaParametroExtraerJuliana, fechaParametroSumoDia);
			fechaParametroSumoDia.roll(GregorianCalendar.DATE, 1);
			Date fechaParametro = fechaParametroSumoDia.getTime();
			
			listaDetalle = contabilidadDao.listarPendientes(contabilidadFiltro);
			listaDetalle.addAll(contabilidadDao.listarPendientesSinAcreditacion(contabilidadFiltro));
			String registro ="";
			long totalRegistros = 0;
			BigDecimal totalImporte = new BigDecimal(0);
			ShvCntContabilidadCabecera cabecera = new ShvCntContabilidadCabecera();
			cabecera.setFechaCreacion(new Date());
			cabecera.setEstado(CONTABILIDAD_PROCESO);
			String idCabecera = genericoDao.insertar(ShvCntContabilidadCabecera.class, cabecera);
			cabecera.setIdContabilidadCabecera(Long.valueOf(idCabecera));
			String nombreArchivo = String.valueOf(CODIGO_INTERFAZ+fechaParametroExtraerJuliana.get(GregorianCalendar.YEAR))+Utilidad.rellenarCerosIzquierda(String.valueOf(fechaParametroExtraerJuliana.get(GregorianCalendar.DAY_OF_YEAR)), 3)+".txt";
			String carpetaDestino= Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.contabilidad");
			@SuppressWarnings("unchecked")
			List<ShvParamTipoOrigenContable> origenAcreditacion = (List<ShvParamTipoOrigenContable>) genericoDao.listarPorValor(ShvParamTipoOrigenContable.class, "idTipoOrigenContable", ORIGEN_CONT_012);
			for (ShvCntContabilidadDetalle detalle : listaDetalle) {
				try {
					if (detalle.getIdTipoMedioPago()!= null && detalle.getIdTipoMedioPago().getIdTipoMedioPago().equals(ID_TIPO_MEDIO_PAGO_CHEQUE_PD) 
							&& (detalle.getIdTipoOrigenContable().getIdTipoOrigenContable().equals(ORIGEN_CONT_010) 
									|| detalle.getIdTipoOrigenContable().getIdTipoOrigenContable().equals(ORIGEN_CONT_011)
									|| detalle.getIdTipoOrigenContable().getIdTipoOrigenContable().equals(ORIGEN_CONT_013))
							&& !detalle.getEstado().equals(CONTABILIDAD_PENDIENTE_SIN_ACREDITACION)
							&& !ESTADO_INACTIVIDAD.equals(detalle.getEstadoInactividad())){
						if(detalle.getFechaVencimiento().before(fechaParametro)){
							ShvCntContabilidadDetalle acreditacion = new ShvCntContabilidadDetalle();
							BeanUtils.copyProperties(detalle, acreditacion);
							acreditacion.setIdTipoOrigenContable(origenAcreditacion.get(0));
							registro += armarRegistro(acreditacion);
							acreditacion.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PROCESO);
							acreditacion.setIdContabilidadDetalle(null);
							acreditacion.setFechaCreacion(cabecera.getFechaCreacion());
							acreditacion.setFechaProcesamiento(cabecera.getFechaCreacion());
							acreditacion.setIdContabilidadCabecera(cabecera);
							contabilidadDao.insertarActualizarContabilidadDetalle(acreditacion);
							listaProcesar.add(acreditacion);
							totalRegistros++;
							totalImporte = totalImporte.add(acreditacion.getImporte());
						} else {
							detalle.setAcreditado(false);
						}
					}
					registro += armarRegistro(detalle);
					detalle.setFechaProcesamiento(cabecera.getFechaCreacion());
					detalle.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PROCESO);
					detalle.setIdContabilidadCabecera(cabecera);
					contabilidadDao.insertarActualizarContabilidadDetalle(detalle);
					listaProcesar.add(detalle);
					totalRegistros++;
					totalImporte = totalImporte.add(detalle.getImporte());
				} catch (Exception e) {
					Traza.error(getClass(), "error en el registro con Id Contabilidad: "+detalle.getIdContabilidadDetalle().toString());
					e.printStackTrace();

					detalle.setEstado(CONTABILIDAD_PROCESADO_CON_ERROR);
					contabilidadDao.insertarActualizarContabilidadDetalle(detalle);
				}
			}
			listaDetalle = contabilidadDao.listarPendientesAcreditacion(contabilidadFiltro);
			for (ShvCntContabilidadDetalle detalle : listaDetalle) {
				if(detalle.getFechaVencimiento().before(fechaParametro)){
					ShvCntContabilidadDetalle acreditacion = new ShvCntContabilidadDetalle();
					BeanUtils.copyProperties(detalle, acreditacion);
					acreditacion.setIdTipoOrigenContable(origenAcreditacion.get(0));
					registro += armarRegistro(acreditacion);
					acreditacion.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PROCESO);
					acreditacion.setIdContabilidadDetalle(null);
					acreditacion.setIdContabilidadCabecera(cabecera);
					acreditacion.setFechaCreacion(cabecera.getFechaCreacion());
					acreditacion.setFechaProcesamiento(cabecera.getFechaCreacion());
					contabilidadDao.insertarActualizarContabilidadDetalle(acreditacion);
					listaProcesar.add(acreditacion);
					totalRegistros++;
					totalImporte = totalImporte.add(acreditacion.getImporte());
				} else {
					detalle.setAcreditado(false);
				}
			}
			cabecera.setFechaProcesamiento(new Date());
			cabecera.setCantidadRegistrosProcesados(totalRegistros);
			cabecera.setImporteRegistrosProcesados(totalImporte);
			cabecera.setEstado(CONTABILIDAD_PROCESADO);
			registro = armarCabecera(cabecera)+registro;
			ControlArchivo.escribir(registro, carpetaDestino, nombreArchivo);
			genericoDao.actualizar(ShvCntContabilidadCabecera.class, cabecera, "ID_CONTABILIDAD_CABECERA= "+idCabecera);
			for (ShvCntContabilidadDetalle detalle : listaProcesar) {
				if(detalle.isAcreditado()){
					detalle.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PROCESADO);
				} else {
					detalle.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE_ACREDITACION);
				}
				contabilidadDao.insertarActualizarContabilidadDetalle(detalle);
			}
			for (ShvCntContabilidadDetalle detalle : listaDetalle) {
				if(detalle.isAcreditado()){
					detalle.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PROCESADO);
					contabilidadDao.insertarActualizarContabilidadDetalle(detalle);
				}
			}
			return nombreArchivo;
		} catch (PersistenciaExcepcion | ShivaExcepcion | ParseException   e) {
			throw new NegocioExcepcion(e);
		}
		
	}

	private String armarCabecera(ShvCntContabilidadCabecera cabecera){
		cabecera.setFechaProcesamiento(new Date());
		String registro = TIPO_REGISTRO_CABECERA+SEPARADOR+
				Utilidad.rellenarEspaciosIzquierda(CODIGO_INTERFAZ, 10)+SEPARADOR
				+ TRES_ESPACIOS+SEPARADOR
				+ OCHO_CEROS+SEPARADOR
				+ DOS_ESPACIOS+SEPARADOR
				+ DOS_ESPACIOS+SEPARADOR
				+ UN_ESPACIO+SEPARADOR
				+ ONCE_ESPACIOS+SEPARADOR
				+ CUATRO_ESPACIOS+SEPARADOR
				+ TRES_ESPACIOS+SEPARADOR
				+ OCHO_ESPACIOS+SEPARADOR
				+ DIECISIETE_CEROS+SEPARADOR
				+ TRES_ESPACIOS+SEPARADOR
				+ OCHO_CEROS+SEPARADOR 
				+ OCHO_CEROS+SEPARADOR 
				+ TRECE_ESPACIOS+SEPARADOR
				+ DIEZ_ESPACIOS+SEPARADOR
				+ QUINCE_ESPACIOS+SEPARADOR
				+ TRES_ESPACIOS+SEPARADOR
				+ QUINCE_CEROS+SEPARADOR
				+ ONCE_ESPACIOS+SEPARADOR
				+ DIEZ_CEROS+SEPARADOR
				+ CUATRO_ESPACIOS+SEPARADOR
				+ VEINTE_ESPACIOS+SEPARADOR
				+ Utilidad.rellenarCerosIzquierda(String.valueOf(cabecera.getCantidadRegistrosProcesados()), 7)+SEPARADOR
				+ Utilidad.rellenarCerosAmbosLados(String.valueOf(cabecera.getImporteRegistrosProcesados()), 15, 2)+SEPARADOR
				+ Utilidad.formatDateAAAAMMDD(cabecera.getFechaProcesamiento())+SEPARADOR
				+ System.lineSeparator();
				
		return registro;
	}
	private String armarRegistro(ShvCntContabilidadDetalle detalle) throws PersistenciaExcepcion {
		String registro="";
		registro = TIPO_REGISTRO_DETALLE+SEPARADOR
				+ Utilidad.rellenarEspaciosIzquierda(CODIGO_INTERFAZ, 10)+SEPARADOR
				+ Utilidad.rellenarEspaciosIzquierda(detalle.getIdTipoOrigenContable().getIdTipoOrigenContable(),3)+SEPARADOR
				+ Utilidad.rellenarCerosIzquierda(Utilidad.formatDateAAAAMMDD(detalle.getFechaCreacion()),8)+SEPARADOR
				+ Utilidad.rellenarEspaciosIzquierda((detalle.getIdTipoMedioPago()!=null)?detalle.getIdTipoMedioPago().getIdTipoMedioPago():"",2)+SEPARADOR
				+ ((detalle.getIdTipoCliente() != null)?Utilidad.rellenarEspaciosIzquierda(String.valueOf(detalle.getIdTipoCliente().getIdTipoCliente()),2):DOS_ESPACIOS)+SEPARADOR
				+ ((detalle.getIdJurisdiccion() != null)?detalle.getIdJurisdiccion():UN_ESPACIO)+SEPARADOR
				+ Utilidad.rellenarEspaciosIzquierda(Validaciones.isObjectNull(detalle.getCodigoClienteLegado()) ? "" : detalle.getCodigoClienteLegado().toString(),11)+SEPARADOR
				+ ((detalle.getIdBanco() != null)?Utilidad.rellenarEspaciosIzquierda(detalle.getIdBanco().getIdBanco(),4):CUATRO_ESPACIOS)+SEPARADOR
				+ ((detalle.getIdAcuerdo() !=null)?Utilidad.rellenarEspaciosIzquierda(String.valueOf(detalle.getIdAcuerdo().getIdAcuerdo()),3):TRES_ESPACIOS)+SEPARADOR
				+ Utilidad.rellenarEspaciosIzquierda(detalle.getIdAnalista(),8).substring(0, 8)+SEPARADOR
				+ Utilidad.rellenarCerosAmbosLados(detalle.getImporte().toString(),15,2)+SEPARADOR
				+ Utilidad.rellenarEspaciosIzquierda(detalle.getMoneda(),3)+SEPARADOR
				+ ((detalle.getFechaValor() != null)?Utilidad.formatDateAAAAMMDD(detalle.getFechaValor()):OCHO_CEROS)+SEPARADOR
				+ ((detalle.getFechaVencimiento() != null)?Utilidad.formatDateAAAAMMDD(detalle.getFechaVencimiento()):OCHO_CEROS)+SEPARADOR
				+ ((detalle.getTransaccion() != null)?Utilidad.rellenarEspaciosIzquierda(detalle.getTransaccion(),13):TRECE_ESPACIOS)+SEPARADOR
				+ ((detalle.getNumeroBoleta() != null)?Utilidad.rellenarEspaciosIzquierda(detalle.getNumeroBoleta().toString(),10):DIEZ_ESPACIOS)+SEPARADOR
				+ ((detalle.getNumeroComprobante() != null)? Utilidad.rellenarEspaciosIzquierda(detalle.getNumeroComprobante(),15):QUINCE_ESPACIOS)+SEPARADOR
				+ ((detalle.getIdOrganismo() != null)?Utilidad.rellenarEspaciosIzquierda(detalle.getIdOrganismo(),3):TRES_CEROS)+SEPARADOR
				+ ((detalle.getTipoDeCambio() != null)?detalle.getTipoDeCambio():QUINCE_CEROS)+SEPARADOR
				+ ((detalle.getCuit() != null)?Utilidad.rellenarEspaciosIzquierda(detalle.getCuit().replace("-", ""),11):ONCE_ESPACIOS)+SEPARADOR
				+ ((detalle.getNumeroDocumentoContable() != null)?Utilidad.rellenarCerosIzquierda(detalle.getNumeroDocumentoContable().toString(),10):DIEZ_CEROS)+SEPARADOR
				+ ((detalle.getIdCaja() != null)?Utilidad.rellenarEspaciosIzquierda(detalle.getIdCaja(),4):CUATRO_ESPACIOS)+SEPARADOR
				+ VEINTE_ESPACIOS+SEPARADOR
				+ SIETE_CEROS+SEPARADOR
				+ DIECISIETE_CEROS+SEPARADOR
				+ OCHO_CEROS+SEPARADOR
				+ System.lineSeparator();
		return registro;
	}
	
	@SuppressWarnings("unchecked")
	public void anularContabilidad(Long idValor) throws NegocioExcepcion{
		try {
			List<ShvCntContabilidadDetalle> listaDetalles = contabilidadDao.buscarPorIdValor(idValor);

			for (ShvCntContabilidadDetalle detalle : listaDetalles) {

				if (!ESTADO_INACTIVIDAD.equals(detalle.getEstadoInactividad())
						&& !OrigenContableEnum.$043.equals(OrigenContableEnum.getEnumByCodigo(detalle.getIdTipoOrigenContable().getIdTipoOrigenContable()))) {

					ShvCntContabilidadDetalle detalleAnulacion = new ShvCntContabilidadDetalle();
					BeanUtils.copyProperties(detalle, detalleAnulacion);
					detalleAnulacion.setIdContabilidadDetalle(null);
					detalleAnulacion.setIdContabilidadCabecera(null);
					detalleAnulacion.setFechaProcesamiento(null);
					detalleAnulacion.setFechaCreacion(new Date());
					detalleAnulacion.setEstadoInactividad(ESTADO_INACTIVIDAD);

					if (detalle.getIdTipoMedioPago().getIdTipoMedioPago().equals(ID_TIPO_MEDIO_PAGO_CHEQUE_PD) 
							&& (detalle.getIdTipoOrigenContable().getIdTipoOrigenContable().equals(ORIGEN_CONT_010) 
									|| detalle.getIdTipoOrigenContable().getIdTipoOrigenContable().equals(ORIGEN_CONT_011)
									|| detalle.getIdTipoOrigenContable().getIdTipoOrigenContable().equals(ORIGEN_CONT_013))){
						evitarAcreditacion(detalle);
						detalleAnulacion.setImporte(detalleAnulacion.getImporte().negate());
						detalleAnulacion.setEstado(CONTABILIDAD_PENDIENTE_SIN_ACREDITACION);
					} else {
						detalleAnulacion.setEstado(CONTABILIDAD_PENDIENTE);
						if (detalle.getIdTipoOrigenContable().getIdTipoOrigenContable().equals(ORIGEN_CONT_030)){
							List<ShvParamTipoOrigenContable> origen = (List<ShvParamTipoOrigenContable>) genericoDao.listarPorValor(ShvParamTipoOrigenContable.class, "idTipoOrigenContable", ORIGEN_CONT_031);
							detalleAnulacion.setIdTipoOrigenContable(origen.get(0));
						} else {
							detalleAnulacion.setImporte(detalleAnulacion.getImporte().negate());
						}
					}
					contabilidadDao.insertarActualizarContabilidadDetalle(detalleAnulacion);
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public void evitarAcreditacion(ShvCntContabilidadDetalle detalle) throws NegocioExcepcion{
		try {
			if (detalle.getEstado().equals(CONTABILIDAD_PENDIENTE)){
				detalle.setEstado(CONTABILIDAD_PENDIENTE_SIN_ACREDITACION);
				contabilidadDao.insertarActualizarContabilidadDetalle(detalle);
			} else if (detalle.getEstado().equals(CONTABILIDAD_PENDIENTE_ACREDITACION)){
				detalle.setEstado(CONTABILIDAD_PROCESADO);
				contabilidadDao.insertarActualizarContabilidadDetalle(detalle);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public void desactivarDetalle(Long idValor, boolean solo30) throws NegocioExcepcion{
		try {
			List<ShvCntContabilidadDetalle> listaDetalle = contabilidadDao.buscarPorIdValor(idValor);
			for (ShvCntContabilidadDetalle detalle : listaDetalle) {
				if ((!solo30 || detalle.getIdTipoOrigenContable().getIdTipoOrigenContable().equals(ORIGEN_CONT_030)) && Validaciones.isNullOrEmpty(detalle.getEstadoInactividad())){
					detalle.setEstadoInactividad(ESTADO_INACTIVIDAD);
					contabilidadDao.insertarActualizarContabilidadDetalle(detalle);
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
			
		}
	}
	
	
	/**
	 * @return the genericoDao
	 */
	public IGenericoDao getGenericoDao() {
		return genericoDao;
	}

	/**
	 * @param genericoDao the genericoDao to set
	 */
	public void setGenericoDao(IGenericoDao genericoDao) {
		this.genericoDao = genericoDao;
	}

	/**
	 * @return the contabilidadDao
	 */
	public IContabilidadDao getContabilidadDao() {
		return contabilidadDao;
	}

	/**
	 * @param contabilidadDao the contabilidadDao to set
	 */
	public void setContabilidadDao(IContabilidadDao contabilidadDao) {
		this.contabilidadDao = contabilidadDao;
	}

	public IBancoDao getBancoDao() {
		return bancoDao;
	}

	public void setBancoDao(IBancoDao bancoDao) {
		this.bancoDao = bancoDao;
	}
	
	
}
