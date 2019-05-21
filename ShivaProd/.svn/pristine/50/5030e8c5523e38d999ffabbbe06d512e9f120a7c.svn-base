package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoChequeRechazadoDetalleCobroEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoReversionIceServicio;
import ar.com.telecom.shiva.persistencia.dao.ILegajoChequeRechazadoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazadoDetalleCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjEnvioReversionesIce;

public class LegajoChequeRechazadoReversionIceServicioImpl implements ILegajoChequeRechazadoReversionIceServicio{

	@Autowired
	private ILegajoChequeRechazadoDao legajoChequeRechazadoDao;

	
	private static final String TIPO_REGISTRO_CABECERA_SOPORTE = "01";
	private static final String TIPO_REGISTRO_CABECERA_ENTIDAD_SOPORTE = "03";
	private static final String TIPO_REGISTRO_INDIVIDUAL = "06";
	private static final String TIPO_REGISTRO_FIN_ENTIDAD_BANCARIA = "08";
	private static final String TIPO_REGISTRO_FIN_SOPORTE = "09";
	private static final int FILLER_CABECERA_SOPORTE = 350;
	private static final int FILLER_CABECERA_ENTIDAD_SOPORTE = 356;
	private static final int FILLER_INDIVIDUAL_GENERAL = 40;
	private static final int FILLER_FIN_ENTIDAD_SOPORTE = 349;
	private static final int FILLER_FIN_SOPORTE = 342;

	private static final String CODIGO_DE_OPERACION = "30";
	private static final String CODIGO_ENTIDAD_GESTORA = "08980";
	private static final String NOMBRE_ENTIDAD_GESTORA = "SHIVA";
	
	//INDIVIDUAL
	private static final String CODIGO_DE_TAREA = "31";
	private static final String TIPO_DE_OPERACION = "B";
	private static final String MARCA_DE_AJUSTE = "I";
	private static final String TIPO_DE_LECTURA = "S";
	private static final int FILLER_INDIVIDUAL = 4;
	
	//FILLER_CAMPOS_RELACIONADOS_CON_LA_ENTIDAD_Y_FECHA_DE_PAGO
	private static final int FILLER_CAMPOS_RELACIONADOS_CON_LA_ENTIDAD_Y_FECHA_DE_PAGO=10;
	
	/**
	 * Metodo que procesa un OperacionMasivaDto y si el procesamiento es OK, 
	 * luego de insertar en la base crea y retorna un ArchivoOperacionMasivaProcesadoDto.
	 * Si el procesamiento es incorrecto retorna null.
	 * @throws PersistenciaExcepcion 
	 */



	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void generarArchivoReversionesIce(String fechaHasta) throws NegocioExcepcion {
		try {
		
			EmpresaEnum empresa = EmpresaEnum.TA;
			Date fechaPlanificacion = Utilidad.parseDatePickerString(fechaHasta);
			String fechaPlanificacionString = Utilidad.formatDateAAAAMMDD(fechaPlanificacion);
			//[Nombre Aplicación].RevCob.[Empresa].[Fecha Planificación].[Nombre Aplicación Destino] -> SHIVA.RevCob.TA.20170714.ICE
			String nombreArchivo = "SHIVA.RevCob." + empresa.name() + "." +  fechaPlanificacionString + ".ICE" + Constantes.ARCHIVO_TXT;
			Traza.advertencia(getClass(), "Se crea el archivo " + nombreArchivo);
		
			//Buscar los registros a la tabla ShvLgjChequeRechazadoDetalleCobro
			List<ShvLgjChequeRechazadoDetalleCobro> listaRegistrosDetalleCobro = legajoChequeRechazadoDao.listarDetalleCobrosPendientesDeEnviarIceOrdenadoPorBanco();
			Traza.advertencia(getClass(), "Se encontraron "+listaRegistrosDetalleCobro.size()+" registros para procesar y cargar en el archivo.");
			
			List<StringBuffer> listaRegistros = new ArrayList<StringBuffer>();
			
			//agregar cabecera soporte
			listaRegistros.add(armarCabeceraSoporte(fechaPlanificacionString));
			
			//Variables
			Long cantidadBancos = 0L; 
			BigDecimal totalImportesCobrados = new BigDecimal(0);
			Long cantidadFacturas = 0L;
			//Variables de cada banco
			String codigoBancoDeCobro = null;
			BigDecimal totalImportesCobradosPorBanco = new BigDecimal(0); 
			int cantidadFacturaCobradasPorBanco = 0; 
			int cantidadRegistrosPorBanco = 0;
			
			for (ShvLgjChequeRechazadoDetalleCobro detalleCobro : listaRegistrosDetalleCobro) {
				
				Traza.advertencia(getClass(), "Se procesara el registro con idChequeRechazadoCobro: " + detalleCobro.getIdChequeRechazadoCobro());
				if(Validaciones.isNullOrEmpty(codigoBancoDeCobro)){
					//Asumo que es el primer registro a procesar
					codigoBancoDeCobro = detalleCobro.getCodigoBancoCobro();
					cantidadBancos++;
					listaRegistros.add(armarCabeceraEntidadBancaria(detalleCobro.getCodigoBancoCobro(),detalleCobro.getNombreBancoDeCobro()));
				} else {
					if(!codigoBancoDeCobro.equalsIgnoreCase(detalleCobro.getCodigoBancoCobro())){
						//asumo que no es el primer registro a procesar
						listaRegistros.add(armarFinEntidadBancaria(codigoBancoDeCobro, totalImportesCobradosPorBanco, cantidadFacturaCobradasPorBanco, cantidadRegistrosPorBanco));
						codigoBancoDeCobro = detalleCobro.getCodigoBancoCobro();
						totalImportesCobradosPorBanco = new BigDecimal(0); 
						cantidadFacturaCobradasPorBanco = 0; 
						cantidadRegistrosPorBanco = 0;
						cantidadBancos++;
						listaRegistros.add(armarCabeceraEntidadBancaria(detalleCobro.getCodigoBancoCobro(),detalleCobro.getNombreBancoDeCobro()));
					}
				}
				
				listaRegistros.add(armarRegistroIndividual(detalleCobro, fechaPlanificacionString));
				totalImportesCobrados = totalImportesCobrados.add(detalleCobro.getImporteCobradoOReversar());
				cantidadFacturas++;
				totalImportesCobradosPorBanco = totalImportesCobradosPorBanco.add(detalleCobro.getImporteCobradoOReversar());
				cantidadFacturaCobradasPorBanco++; 
				cantidadRegistrosPorBanco++;
			}
			
			if(Validaciones.isCollectionNotEmpty(listaRegistrosDetalleCobro)){
				//agregar fin de la ultima entidad bancaria
				listaRegistros.add(armarFinEntidadBancaria(codigoBancoDeCobro, totalImportesCobradosPorBanco, cantidadFacturaCobradasPorBanco, cantidadRegistrosPorBanco));
			}
			//agregar fin de soporte
			listaRegistros.add(armarFinSoporte(cantidadBancos, totalImportesCobrados, cantidadFacturas, listaRegistros.size()));
			
			//Se crea el archivo y se graba en el path
			crearArchivoEnviarIce(listaRegistros, listaRegistros.size(), nombreArchivo);
			
			//creo el registro en la base
			ShvLgjEnvioReversionesIce reversionIce =  generarRegistroEnEnvioReversionIce(new Date(), nombreArchivo, listaRegistrosDetalleCobro);
			listaRegistrosDetalleCobro = actualizarEstadoDetalleCobroAEnviadoAIce(reversionIce, listaRegistrosDetalleCobro);
			
			legajoChequeRechazadoDao.actualizarRegistrosDetalleCobroPorEnvioIce(listaRegistrosDetalleCobro);
			
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ParseException e1) {
			throw new NegocioExcepcion(e1.getMessage(),e1);
		}
		
	}

	/**
	 * Crear el registro en ShvLgjEnvioReversionesIce.
	 * 
	 * @param fechaActual
	 * @param nombreArchivo
	 * @param listaShvMasRegistro
	 * @return listaShvMasRegistro
	 */
	private ShvLgjEnvioReversionesIce generarRegistroEnEnvioReversionIce(Date fechaActual, String nombreArchivo,
			List<ShvLgjChequeRechazadoDetalleCobro> listaShvMasRegistro) throws NegocioExcepcion {
		try {
		
			ShvLgjEnvioReversionesIce reversionIce = new ShvLgjEnvioReversionesIce();
			reversionIce.setFechaEnvio(fechaActual);
			reversionIce.setNombreArchivo(nombreArchivo);
			reversionIce.setCantidadRegistrosProcesados(new Long(listaShvMasRegistro.size()));
			
			//grabar el ShvLgjEnvioReversionesIce
			reversionIce = legajoChequeRechazadoDao.grabarShvLgjEnvioReversionesIce(reversionIce);
			return reversionIce;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	/**
	 * Asociarlo a todos los registros
	 * de detalleCobro. Si no hay ningun detalleCobro igualmente lo crea y guarda en la base.
	 * 
	 * @param fechaActual
	 * @param nombreArchivo
	 * @param listaShvMasRegistro
	 * @return listaShvMasRegistro
	 */
	@Override
	public List<ShvLgjChequeRechazadoDetalleCobro> actualizarEstadoDetalleCobroARevertido(List<ShvLgjChequeRechazadoDetalleCobro> listaShvMasRegistro) {
		
		if(Validaciones.isCollectionNotEmpty(listaShvMasRegistro)){
			for (ShvLgjChequeRechazadoDetalleCobro detalleCobro : listaShvMasRegistro) {
				detalleCobro.setEstado(EstadoChequeRechazadoDetalleCobroEnum.REVERTIDO);
			}
		}
		
		return listaShvMasRegistro;
	}
	
	
	public List<ShvLgjChequeRechazadoDetalleCobro> actualizarEstadoDetalleCobroAEnviadoAIce(ShvLgjEnvioReversionesIce reversionIce,
			List<ShvLgjChequeRechazadoDetalleCobro> listaShvMasRegistro) {
		
		if(Validaciones.isCollectionNotEmpty(listaShvMasRegistro)){
			for (ShvLgjChequeRechazadoDetalleCobro detalleCobro : listaShvMasRegistro) {
				detalleCobro.setEstado(EstadoChequeRechazadoDetalleCobroEnum.ENVIADO_ICE);
				detalleCobro.setReversionIce(reversionIce);
			}
		}
		
		return listaShvMasRegistro;
	}
	
	/**
	 * 
	 * @param 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public StringBuffer armarCabeceraSoporte(String fechaActualString) throws NegocioExcepcion {
		StringBuffer str = new StringBuffer();

		//TIPO DE REGISTRO	Numérico	Longitud=2	"Identifica al header de archivo" ->	01
		str.append(TIPO_REGISTRO_CABECERA_SOPORTE);
		//CODIGO DE OPERACION	Alfanumérico	Longitud=2	"Identifica la operatoria" ->	"30"
		str.append(CODIGO_DE_OPERACION);
		//FECHA DE PROCESO	Numérico	Longitud=8	"Fecha en que la entidad emisora del archivo confecciona el mismo en formato AAAAMMDD" ->	Fecha batch de ejecución
		str.append(fechaActualString);
		//CODIGO ENTIDAD GESTORA	Alfanumérico	Longitud=5	"Código de la entidad emisora de la cinta, considerada como banco gestor."  ->	08980
		str.append(CODIGO_ENTIDAD_GESTORA);
		//NOMBRE ENTIDAD GESTORA	Alfanumérico	Longitud=25	"Nombre de la entidad gestora."	-> SHIVA
		str.append(Utilidad.rellenarEspaciosDerecha(NOMBRE_ENTIDAD_GESTORA, 25));
		//FILLER	Alfanumérico	Longitud=350	-> Espacios
		str.append(Utilidad.rellenarEspaciosDerecha("", FILLER_CABECERA_SOPORTE));
		
		return str;

	}
	
	/**
	 * 
	 * @param 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public StringBuffer armarCabeceraEntidadBancaria(String codigoBancoDeCobro, String nombreBancoDeCobro) throws NegocioExcepcion {
			StringBuffer str = new StringBuffer();

			//TIPO DE REGISTRO	Numérico	2	Identifica al header de banco->	03
			str.append(TIPO_REGISTRO_CABECERA_ENTIDAD_SOPORTE);
			//CODIGO BANCO DE COBRO	Alfanumérico	4	Código de la entidad que realizó los cobros. Unificar códigos de entidades con Telecom Personal.	<codigoBancoDeCobro>
			str.append(Utilidad.rellenarEspaciosDerecha(codigoBancoDeCobro, 4));
			//NOMBRE BANCO DE COBRO	Alfanumérico	30	Nombre de la entidad emisora.	<nombreBancoDeCobro>
			str.append(Utilidad.rellenarEspaciosDerecha(nombreBancoDeCobro, 30));
			//FILLER	Alfanumérico	356	Espacios
			str.append(Utilidad.rellenarEspaciosDerecha("", FILLER_CABECERA_ENTIDAD_SOPORTE));

			return str;
	}
	
	/**
	 * 
	 * @param 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public StringBuffer armarRegistroIndividual(ShvLgjChequeRechazadoDetalleCobro detalleCobro, String fechaActualString) throws NegocioExcepcion {
		StringBuffer str = new StringBuffer();

		//TIPO DE REGISTRO	Numérico	2	Identifica al registro individual	06
		str.append(TIPO_REGISTRO_INDIVIDUAL);
		//CODIGO EMPRESA RECEP	Alfanumérico	4	"Identifica a Telecom o Personal 0004 - Telecom	0316 - Personal "	<codigoEmpresaReceptora>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getCodigoEmpresaReceptora(),4));
		//CODIGO DE TAREA	Alfanumérico	2	"Identifica la tarea "31 - Reversion de cobro"
		str.append(CODIGO_DE_TAREA);
		//TIPO DE OPERACIÓN	Alfanumérico	1	"Código que identifica la forma en la que se llevó a cabo la operación "B - La operación es batch"
		str.append(TIPO_DE_OPERACION);
		//MARCA DE AJUSTE	Alfanumérico	1	"Marca que indica si el movimiento es un movimiento real o un ajuste "I - Imputación de operación"
		str.append(MARCA_DE_AJUSTE);
		//TIPO DE LECTURA	Alfanumérico	1	"Marca que indica cómo se llevó a cabo la lectura del documento "S - La operación se llevó a cabo sin factura (Telecom/Personal)"
		str.append(TIPO_DE_LECTURA);
		//FILLER	Alfanumérico	4	Espacios	
		str.append(Utilidad.rellenarEspaciosDerecha("", FILLER_INDIVIDUAL));
		
		//CAMPOS RELACIONADOS CON LA ENTIDAD Y FECHA DE PAGO:				
		//CODIGO BANCO DE COBRO	Alfanumérico	4	Identifica a la entidad donde el cliente realizó el pago (código de banco con ceros a la izquierda). 	<codigoBancoDeCobro>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getCodigoBancoCobro(),4));
		//SUCURSAL BANCO	Alfanumérico	4	Identifica la sucursal de la entidad donde el cliente pagó.	<sucursalBanco>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getSucursalBanco(),4));
		//NUMERO DE AGENCIA	Alfanumérico	8	Optativo si el tercero lo quiere informar	<numeroDeAgencia>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getNumeroDeAgencia(),8));
		//NUMERO DE SUCURSAL COMPLETO	Alfanumérico	6	Número de sucursal completo	<numeroDeSucursalCompleto>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getNumeroDeSucursalCompleto(),6));
		//COD POSTAL SUCURSAL	Alfanumérico	4	Código postal de la sucursal	<codigoPostalSucursal>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getCodigoPostalSucursal(),4));
		//FECHA DE COBRO	Numérico	8	Fecha real del día en que el cliente pagó, en formato AAAAMMDD. Si fuera una reversión, fecha del cobro original.	<fechaDeCobro>
		str.append(Utilidad.rellenarEspaciosDerecha(Utilidad.formatDateAAAAMMDD(detalleCobro.getFechaDeCobro()),8));
		//FECHA PETICION REVERSION	Numérico	8	Sólo para reversiones de cobro, en formato AAAAMMDD.	Fecha batch de ejecución
		str.append(Utilidad.rellenarEspaciosDerecha(fechaActualString,8));
		//FECHA DE ACREDITACIÓN	Numérico	8	Fecha de acreditación, en formato AAAAMMDD.	<fechaDeAcreditacion>
		str.append(Utilidad.rellenarEspaciosDerecha(Utilidad.formatDateAAAAMMDD(detalleCobro.getFechaDeAcreditacion()),8));
		//FILLER	Alfanumérico	10	Espacios	
		str.append(Utilidad.rellenarEspaciosDerecha("", FILLER_CAMPOS_RELACIONADOS_CON_LA_ENTIDAD_Y_FECHA_DE_PAGO));
			
		//CAMPOS RELACIONADOS CON EL DOCUMENTO:				
		//TIPO DE FACTURACION	Alfanumérico	2	"Debe permitir identificar a qué sistema y modalidad se refieren los cobros."	<tipoDeFacturacion>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getTipoDeFacturacion(),2));
		//REFERENCIA DEL COMPROBANTE	Alfanumérico	35	Debe tener espacio suficiente para poder informar los datos requeridos para identificar la referencia y datos identificatorios de cada cobrador, ya sea un cobro o una reversión de cobro.	<referenciaDelComprobante>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getReferenciaDelComprobante(),35));
		//FILLER	Alfanumérico	15	Espacios	
		str.append(Utilidad.rellenarEspaciosDerecha("",15));
		//FECHA DE VCTO ORIGINAL	Alfanumérico	8	Fecha primer vto, en formato AAAAMMDD. Fecha de pago si es cupón abierto para Personal.	<fechaDeVctoOriginal>
		str.append(Utilidad.rellenarEspaciosDerecha(Utilidad.formatDateAAAAMMDD(detalleCobro.getFechaDeVctoOriginal()),8));
		//IMPORTE ORIGINAL	Numérico	17	Importe original del comprobante, en centavos sin coma decimal (15,2)	<importeOriginal>
		str.append(Utilidad.rellenarCerosIzquierda(Utilidad.quitarComaDecimal(Utilidad.formatCurrency(detalleCobro.getImporteOriginal(),false,false)),17));
		//IMPORTE COBRADO/IMPORTE A REVERSAR	Numérico	17	Importe cobrado, en centavos sin coma decimal (15,2). Hasta el día del primer vto. se cobra el importe original exacto, y desde el día siguente en adelante, e inclusive pasado el vto. con mora, se debe sumar el recargo. En caso de ser una reversión, importe por el que hay que revertir el cobro.	<ImporteCobradoOReversar>
		str.append(Utilidad.rellenarCerosIzquierda(Utilidad.quitarComaDecimal(Utilidad.formatCurrency(detalleCobro.getImporteCobradoOReversar(),false,false)),17));
		//COMISION	Numérico	9	Comisión cobrada por la entidad,en centavos sin coma decimal (7,2)	<comision>
		str.append(Utilidad.rellenarCerosIzquierda(String.valueOf(detalleCobro.getComision()),9));
		//TIPO DE CLIENTE DEL CÓDIGO DE BARRAS	Alfanumérico	2	Tipo de cliente	<tipoClienteCodigoBarras>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getTipoClienteCodigoBarras(),2));
		//PROVINCIA DEL CÓDIGO DE BARRAS	Numérico	2	Código de provincia informado en el código de barras 	<ProvinciaDelCodBarras>
		str.append(Utilidad.rellenarCerosIzquierda(detalleCobro.getProvinciaDelCodBarras(),2));
		//CODIGO POSTAL CLIENTE DEL CODIGO DE BARRAS	Numérico	4	Código postal informado en el código de barras 	<cpClienteCodigoBarras>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getCpClienteCodigoBarras(),4));
		//IDENTIFICADOR DE PAGO	Numérico	9	Identificador de pago utilizado en las cobranzas integradas	<identificadorDePago>
		str.append(Utilidad.rellenarEspaciosDerecha(String.valueOf(detalleCobro.getIdentificadorDePago()),9));
		//MARCA DEL PAGO	Alfanumérico	1	Marca que indica si el pago asociado a la cobranza integrada es total o parcial	<marcaDePago>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getMarcaDePago(),1));
		//FILLER	Alfanumérico	10	Espacios	
		str.append(Utilidad.rellenarEspaciosDerecha("",10));
		
		//CAMPOS RELACIONADOS CON LOS MEDIOS DE PAGO:				
		//FORMA DE PAGO DEL CLIENTE	Alfanumérico	3	"""xxx"" pago en efectivo pesos, ""xxx"" pago con cheque, ""xxx"" pago con tarjeta, etc.  Los importes se que deben cargar dependen de la forma de pago	<formaDePagoDelCliente>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getFormaDePagoDelCliente(),3));
		//IMPORTE EFECTIVO	Numérico	17	Importe en efectivo, en centavos sin coma (15,2)	<importeEfectivo>
		str.append(Utilidad.rellenarCerosIzquierda(Utilidad.quitarComaDecimal(Utilidad.formatCurrency(detalleCobro.getImporteEfectivo(),false,false)),17));
		//NUMERO CHEQUE	Numérico	10	Número del cheque	<numeroChequeCompleto>
		str.append(Utilidad.rellenarCerosIzquierda(String.valueOf(detalleCobro.getNumeroChequeCompleto()),10));
		//BANCO EMISOR CHEQUE	Alfanumérico	4	Código banco emisor del cheque	<bancoEmisorCheque>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getCodigoBancoEmisorCheque(),4));
		//IMPORTE CHEQUE	Numérico	17	Importe del cheque en centavos sin coma (15,2)	<importeCheque>
		str.append(Utilidad.rellenarCerosIzquierda(Utilidad.quitarComaDecimal(Utilidad.formatCurrency(detalleCobro.getImporteCheque(),false,false)),17));
		//"TIPO BONO"	Alfanumérico	3	"Tipo de bono (si hubiera)	<tipoBono>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getTipoBono(),3));
		//IMPORTE BONO	Numérico	17	Importe cobrado en bonos, en centavos sin coma  (15,2)	<importeBono>
		str.append(Utilidad.rellenarCerosIzquierda(Utilidad.quitarComaDecimal(Utilidad.formatCurrency(detalleCobro.getImporteBono(),false,false)),17));
		//"TIPO COMPROBANTE"	Alfanumérico	3	"Tipo de comprobante (si hubiera)	<tipoComprobante>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getTipoComprobante(),3));
		//NRO DE COMPROBANTE	Alfanumérico	15	Número de comprobante que se utiliza como medio de pago.  En la cobranza integrada será el número de la nota de crédito	<numeroDeComprobante>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getNumeroDeComprobante(),15));
		//IMPORTE COMPROBANTE	Numérico	17	Importe comprobantes retención u otros, en centavos sin coma (15,2)	<importeComprobante>
		str.append(Utilidad.rellenarCerosIzquierda(Utilidad.quitarComaDecimal(Utilidad.formatCurrency(detalleCobro.getImporteComprobante(),false,false)),17));
		//"TIPO OTRAS MONEDAS"	Alfanumérico	3	"Tipo de moneda (si hubiera) 003 - EFECTIVO - DOLARES "	<tipoOtrasMonedas>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getTipoOtrasMonedas(),3));
		//IMPORTE OTRAS MONEDAS	Numérico	17	Importe otras monedas, en centavos sin coma  (15,2)	<importeOtrasMonedas>
		str.append(Utilidad.rellenarCerosIzquierda(Utilidad.quitarComaDecimal(Utilidad.formatCurrency(detalleCobro.getImporteOtrasMonedas(),false,false)),17));
		//CUPÓN/TICKET	Alfanumérico	13	Comprobante de pago para el cliente	<recibo>
		str.append(Utilidad.rellenarEspaciosDerecha(detalleCobro.getRecibo(),13));
		//TIPO DE CAMBIO	Numérico	7		<tipoDeCambio>
		str.append(Utilidad.rellenarCerosIzquierda(String.valueOf(detalleCobro.getTipoDeCambio()),7));
		//FILLER	Alfanumérico	40	Espacios
		str.append(Utilidad.rellenarEspaciosDerecha("", FILLER_INDIVIDUAL_GENERAL));

		return str;
	}
	
	/**
	 * 
	 * @param totalImportesCobrados 
	 * @param cantidadFacturaCobradas 
	 * @param cantidadRegistrosPorBanco 
	 * @param 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public StringBuffer armarFinEntidadBancaria(String codigoBancoDeCobro, BigDecimal totalImportesCobrados, int cantidadFacturaCobradas, int cantidadRegistrosPorBanco) throws NegocioExcepcion {
			StringBuffer str = new StringBuffer();

			//TIPO DE REGISTRO	Numérico	2	Identifica al trailer de banco	08
			str.append(TIPO_REGISTRO_FIN_ENTIDAD_BANCARIA);
			//	CODIGO BANCO DE COBRO	Alfanumérico	4	Identifica a la entidad que realizó los cobros	<codigoBancoDeCobro>
			str.append(Utilidad.rellenarEspaciosDerecha(codigoBancoDeCobro, 4));
			//	TOTAL IMPORTES COBRADOS	Numérico	17	Total importes cobrados de las facturas, en centavos sin coma decimal	Suma de los importes de los movimientos informados para el banco
			str.append(Utilidad.rellenarCerosIzquierda(Utilidad.quitarComaDecimal(Utilidad.formatCurrency(totalImportesCobrados,false,false)),17));
			//	TOTAL FACTURAS COBRADAS	Numérico	10	Número total de facturas cobradas	Cantidad de movimientos informados para el banco de cobro
			str.append(Utilidad.rellenarCerosIzquierda(String.valueOf(cantidadFacturaCobradas),10));
			//	TOTAL REGISTROS	Numérico	10	Número total de registros de la entidad, incluidos header, registros de detalle y trailer	Cantidad de registros informados para el banco de cobro más el header y el trailer
			//Se agrega un "+ 2" a la cantidad de registros contabilizando la cabecera y el fin de banco
			str.append(Utilidad.rellenarCerosIzquierda(String.valueOf(cantidadRegistrosPorBanco + 2),10));
			//	FILLER	Alfanumérico	349	Espacios
			str.append(Utilidad.rellenarEspaciosDerecha("", FILLER_FIN_ENTIDAD_SOPORTE));
			
			return str;
	}
	
	/**
	 * 
	 * @param 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public StringBuffer armarFinSoporte(Long cantidadBancos, BigDecimal totalImportesCobrados, Long cantidadFacturas, int catidadRegistrosDelArchivo) throws NegocioExcepcion {
			StringBuffer str = new StringBuffer();

			//TIPO DE REGISTRO	Numérico	2	Identifica al trailer de archivo	09
			str.append(TIPO_REGISTRO_FIN_SOPORTE);
			//ENTIDAD EMISORA	Alfanumérico	5	Código de la entidad emisora de la cinta, considerada como banco gestor.  Tener en cuenta el formato actual de MIC que incluye un 0 al final.	08980
			str.append(CODIGO_ENTIDAD_GESTORA);
			//NUMERO ENTIDADES	Numérico	6	Número de bancos informados	Cantidad de bancos de cobro informados
			str.append(Utilidad.rellenarCerosIzquierda(String.valueOf(cantidadBancos),6));
			//TOTAL IMPORTES COBRADOS	Numérico	17	Total importes cobrados de las facturas, en centavos sin coma decimal	Suma de los importes de los movimientos informados en el archivo
			str.append(Utilidad.rellenarCerosIzquierda(Utilidad.quitarComaDecimal(Utilidad.formatCurrency(totalImportesCobrados,false,false)),17));
			//TOTAL FACTURAS COBRADAS	Numérico	10	Número total de facturas cobradas	Cantidad de movimientos informados en el archivo
			str.append(Utilidad.rellenarCerosIzquierda(String.valueOf(cantidadFacturas),10));
			//TOTAL REGISTROS	Numérico	10	Número total de registros del archivo, incluidos header, registros de detalle y trailer	Cantidad de movimientos informados en el archivo más los header y los trailers
			//Se agrega un "+ 1" a la cantidad de registros contabilizando el registro de fin de soporte
			str.append(Utilidad.rellenarCerosIzquierda(String.valueOf(catidadRegistrosDelArchivo + 1),10));
			//FILLER	Alfanumérico	342	Espacios
			str.append(Utilidad.rellenarEspaciosDerecha("", FILLER_FIN_SOPORTE));
			
			return str;
	}
	
	
	/**
	 * 
	 */
	public void crearArchivoEnviarIce(List<StringBuffer> listaRegistros, int cantRegistros, String nombreArchivo) throws NegocioExcepcion {
		try {
			Traza.auditoria(getClass(), "Se crea un archivo para enviar a ICE con " + cantRegistros + " registros");
			ControlArchivo.escribirArchivos(listaRegistros,
				Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.reversion.ice"),
				nombreArchivo,
				null
			);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	
//	/***************************************************************************
//	 * GETTERS & SETTERS
//	 * ************************************************************************/
	public ILegajoChequeRechazadoDao getLegajoChequeRechazadoDao() {
		return legajoChequeRechazadoDao;
	}

	public void setLegajoChequeRechazadoDao(
			ILegajoChequeRechazadoDao legajoChequeRechazadoDao) {
		this.legajoChequeRechazadoDao = legajoChequeRechazadoDao;
	}

}
	