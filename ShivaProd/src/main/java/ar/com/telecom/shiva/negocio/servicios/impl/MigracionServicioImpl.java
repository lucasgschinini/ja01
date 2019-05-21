package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EnviarMailBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.ImprimirBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaTeamComercialClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaTeamComercialClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeltaServicio;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.dto.ArchivoAVCDto;
import ar.com.telecom.shiva.negocio.dto.DepositoDto;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.dto.TransferenciaDto;
import ar.com.telecom.shiva.negocio.servicios.IArchivoAVCServicio;
import ar.com.telecom.shiva.negocio.servicios.IBoletaSinValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.IMigracionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorMedioPagoServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IContabilidadDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IOrganismoDao;
import ar.com.telecom.shiva.persistencia.dao.IOrigenDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCntContabilidadDetalle;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigBoletaDepositoCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigBoletaDepositoChequeDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigBoletaDepositoEfectivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigBoletaSinValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigEquivalencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigRegAvcArchivos;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigRegAvcCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigRegAvcChequeDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigRegAvcDepositoEfectivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigRegAvcTransferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigValorCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigValorChequeDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigValorEfectivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigValorInterdeposito;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigValorTransferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrganismo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrigen;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoOrigenContable;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class MigracionServicioImpl implements IMigracionServicio {
	
	@Autowired
	private IGenericoDao genericoDao;
	@Autowired
	private IValorServicio valorServicio;
	@Autowired
	private IBoletaSinValorServicio boletaServicio;
	@Autowired 
	private IClienteServicio clienteServicio;
	@Autowired 
	private IArchivoAVCServicio archivoAvcServicio;
	@Autowired
	private IParametroServicio parametroServicio;
	@Autowired
	private IOrganismoDao organismoDao;
	@Autowired
	private IBancoDao bancoDao;
	@Autowired
	private IOrigenDao origenDao;
	@Autowired
	private IValorMedioPagoServicio valorMedioPagoServicio;
	@Autowired 
	IContabilidadDao contabilidadDao;
	@Autowired
	IClienteDeltaServicio clienteDeltaServicio;
	@Autowired
	private IClienteSiebelServicio clienteSiebelServicio;
	
	private static final String TABLA_ERROR_AVC_ARCHIVO = "SHV_MIG_REG_AVC_ARCHIVOS_ER";
	private static final String TABLA_ERROR_AVC_TRANSFERENCIA = "SHV_MIG_REG_AVC_TRANSFE_ER";
	private static final String TABLA_ERROR_AVC_CHEQUE_DIFERIDO = "SHV_MIG_REG_AVC_CHEQUE_PD_ER";
	private static final String TABLA_ERROR_AVC_CHEQUE = "SHV_MIG_REGISTRO_AVC_CHEQUE_ER";
	private static final String TABLA_ERROR_AVC_EFECTIVO = "SHV_MIG_REG_AVC_DEP_EFEC_ER";
	private static final String TABLA_ERROR_BOLETA_SIN_VALOR = "SHV_MIG_BOLETA_SIN_VALOR_ER";
	private static final String TABLA_ERROR_BOLETA_CHEQUE = "SHV_MIG_BOLETA_DEP_CHEQUE_ER";
	private static final String TABLA_ERROR_BOLETA_CHEQUE_DIFERIDO = "SHV_MIG_BOL_DEP_CHEQUE_PD_ER";
	private static final String TABLA_ERROR_BOLETA_EFECTIVO = "SHV_MIG_BOLETA_DEP_EFECTIVO_ER";
	private static final String TABLA_ERROR_CHEQUE = "SHV_MIG_VALOR_CHEQUE_ER";
	private static final String TABLA_ERROR_CHEQUE_DIFERIDO = "SHV_MIG_VALOR_CHEQUE_PD_ER";
	private static final String TABLA_ERROR_EFECTIVO = "SHV_MIG_VALOR_EFECTIVO_ER";
	private static final String TABLA_ERROR_TRANSFERENCIA = "SHV_MIG_VALOR_TRANSFERENCIA_ER";
	private static final String TABLA_ERROR_INTERDEPOSITO = "SHV_MIG_VALOR_INTERDEPOSITO_ER";
	
	private static final String EQ_DESCRIPCION = "descripcion";
	private static final String EQ_BANCO_INTERDEPOSITO = "Banco Interdeposito";

	private static final String MIGRACION = "MIGRACION";
	
	private static final String TRAZA_MIGRACION = "Migración";
	private static final String TRAZA_ID_ARCHIVO_INVALIDO = "Id Archivo invalido";
	private static final String TRAZA_ORIGEN_INVALIDO = "origen invalido";
	private static final String TRAZA_DATOS_INVALIDOS = "Datos invalidos";
	private static final String TRAZA_NO_EXISTEN_REGISTROS_PARA_EL_ARCHIVO = "No existen registros para el Archivo";
	private static final String TRAZA_VALOR_DUPLICADO = "Valor duplicado: ";
	private static final String TRAZA_ID_ANALISTA_ERROR = "Id Analista nulo";
	private static final String TRAZA_SEPARADOR = " - ";
	private static final String TRAZA_ID = " Id: ";
	
	private static final String ARCHIVO_EXTENSION = ".txt";
	private static final String NOMBRE_ARCHIVO_SEPARADOR = ".";
	
	private static final String SUBTITULOS_DATOS_TABLA = "Datos existentes en las Tablas:";
	private static final String SUBTITULO_RESULTADO = "Resultado Migracion:";
	private static final String TOTAL = "Total";
	private static final String SEPARADOR_CIERRE = ") ";
	private static final String SEPARADOR_APERTURA = " (";
	private static final String ESPACIOS = "		";
	private static final String REGISTROS = "registros: ";
	private static final String MIGRADOS = "migrados: ";
	private static final String ERRONEOS = " - erroneos: ";
	
	private static final String BOL_ESTADO_CONCILIADO = "CONCILIADO";
	private static final String BOL_ESTADO_CONCILIADO_DIFERENCIA = "CONCILIADO_DIFERENCIA";
	private static final String EFECTIVO_AVC = "Efectivo AVC";
	private static final String CHEQUE_AVC = "Cheque AVC";
	private static final String CHEQUE_DIFERIDO_AVC = "Cheque Diferido AVC";
	private static final String TRANSFERENCIA_AVC = "Transferencia AVC";
	private static final String ARCHIVO_AVC = "Archivo AVC";
	private static final String BOLETA_SIN_VALOR = "Boleta Sin Valor";
	private static final String REGISTRO_AVC = "Registro AVC";
	private static final String VALOR = "Valor";
	
	private static final String ID_ORIGEN_DEFAULT = "6";
	
	private static final String ID_EMPRESA = "TA";
	
	@Override
	public void migracionValores() throws NegocioExcepcion {
		int contExito = 0;					int contError = 0;
		int contBoletaSinValorExito = 0;	int contBoletaSinValorError = 0;	Long contBoletaSinValorTabla = 0L;	
		int contBolChequeExito = 0;			int contBolChequeError = 0;			Long contBolChequeTabla = 0L;
		int contBolChequePDExito = 0;		int contBolChequePDError = 0;		Long contBolChequePDTabla = 0L;
		int contBolEfectivoExito = 0;		int contBolEfectivoError = 0;		Long contBolEfectivoTabla = 0L;
		int contEfectivoExito = 0;			int contEfectivoError = 0;			Long contEfectivoTabla = 0L;
		int contChequeDifExito = 0;			int contChequeDifError = 0;			Long contChequeDifTabla = 0L;
		int contChequeExito = 0;			int contChequeError = 0;			Long contChequeTabla = 0L;
		int contInterdepositoExito = 0;		int contInterdepositoError = 0;		Long contInterdepositoTabla = 0L;
		int contTransferenciaExito = 0;		int contTransferenciaError = 0;		Long contTransferenciaTabla = 0L;
		int contArchivoAvcExito = 0;		int contArchivoAvcError = 0;		Long contArchivoAvcTabla = 0L;
		int contAvcTransferenciaExito = 0;	int contAvcTransferenciaError = 0;	Long contAvcTransferenciaTabla = 0L;
		int contAvcChequeExito = 0;			int contAvcChequeError = 0;			Long contAvcChequeTabla = 0L;
		int contAvcChequePDExito = 0;		int contAvcChequePDError = 0;		Long contAvcChequePDTabla = 0L;
		int contAvcEfectivoExito = 0;		int contAvcEfectivoError = 0;		Long contAvcEfectivoTabla = 0L;
		String registro = "";
		try {
			contArchivoAvcTabla = 		genericoDao.count(ShvMigRegAvcArchivos.class);
			contBoletaSinValorTabla = 	genericoDao.count(ShvMigBoletaSinValor.class);
			contBolChequePDTabla = 		genericoDao.count(ShvMigBoletaDepositoChequeDiferido.class);
			contBolChequeTabla = 		genericoDao.count(ShvMigBoletaDepositoCheque.class);
			contBolEfectivoTabla = 		genericoDao.count(ShvMigBoletaDepositoEfectivo.class);
			contAvcChequeTabla = 		genericoDao.count(ShvMigRegAvcCheque.class);
			contAvcChequePDTabla = 		genericoDao.count(ShvMigRegAvcChequeDiferido.class);
			contAvcEfectivoTabla = 		genericoDao.count(ShvMigRegAvcDepositoEfectivo.class);
			contAvcTransferenciaTabla = genericoDao.count(ShvMigRegAvcTransferencia.class);
			contChequeDifTabla = 		genericoDao.count(ShvMigValorChequeDiferido.class);
			contChequeTabla = 			genericoDao.count(ShvMigValorCheque.class);
			contEfectivoTabla = 		genericoDao.count(ShvMigValorEfectivo.class);
			contInterdepositoTabla = 	genericoDao.count(ShvMigValorInterdeposito.class);
			contTransferenciaTabla = 	genericoDao.count(ShvMigValorTransferencia.class);
			
			Long total = contChequeTabla.longValue()+contChequeDifTabla.longValue()+contEfectivoTabla.longValue()+contBolChequeTabla.longValue()+contBolChequePDTabla.longValue()
					+contBolEfectivoTabla.longValue()+contInterdepositoTabla.longValue()+contTransferenciaTabla.longValue()+contBoletaSinValorTabla.longValue()+contArchivoAvcTabla.longValue()
					+contAvcTransferenciaTabla.longValue()+contAvcChequePDTabla.longValue()+contAvcChequeTabla.longValue()+contAvcEfectivoTabla.longValue();
			registro = System.lineSeparator()+SUBTITULOS_DATOS_TABLA+System.lineSeparator()
					+ESPACIOS+TipoValorEnum.CHEQUE.getDescripcion()+SEPARADOR_APERTURA+REGISTROS+contChequeTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+TipoValorEnum.CHEQUE_DIFERIDO.getDescripcion()+SEPARADOR_APERTURA+REGISTROS+contChequeDifTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+TipoValorEnum.EFECTIVO.getDescripcion()+SEPARADOR_APERTURA+REGISTROS+contEfectivoTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getDescripcion()+SEPARADOR_APERTURA+REGISTROS+contBolChequeTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getDescripcion()+SEPARADOR_APERTURA+REGISTROS+contBolChequePDTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getDescripcion()+SEPARADOR_APERTURA+REGISTROS+contBolEfectivoTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+TipoValorEnum.INTERDEPÓSITO.getDescripcion()+SEPARADOR_APERTURA+REGISTROS+contInterdepositoTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+TipoValorEnum.TRANSFERENCIA.getDescripcion()+SEPARADOR_APERTURA+REGISTROS+contTransferenciaTabla+")"+System.lineSeparator()
					+ESPACIOS+BOLETA_SIN_VALOR+SEPARADOR_APERTURA+REGISTROS+contBoletaSinValorTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+ARCHIVO_AVC+SEPARADOR_APERTURA+REGISTROS+contArchivoAvcTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+TRANSFERENCIA_AVC+SEPARADOR_APERTURA+REGISTROS+contAvcTransferenciaTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+CHEQUE_DIFERIDO_AVC+SEPARADOR_APERTURA+REGISTROS+contAvcChequePDTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+CHEQUE_AVC+SEPARADOR_APERTURA+REGISTROS+contAvcChequeTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+EFECTIVO_AVC+SEPARADOR_APERTURA+REGISTROS+contAvcEfectivoTabla+SEPARADOR_CIERRE+System.lineSeparator()
					+ESPACIOS+TOTAL+SEPARADOR_APERTURA+REGISTROS+total+SEPARADOR_CIERRE;
			
		} catch (PersistenciaExcepcion e2) {
			throw new NegocioExcepcion(e2.getMessage(),e2);
		}
		
		List<ValorDto> listaValoresDto = new ArrayList<ValorDto>();
		List<BoletaSinValorDto> listaBoletaDto = new ArrayList<BoletaSinValorDto>();
		Map<Long,List<RegistroAVCDto>> mapArchivos = new HashMap<Long,List<RegistroAVCDto>>();
		List<ArchivoAVCDto> listaArchivo = mapeoArchivoAvc(mapArchivos);
		
		if (!mapArchivos.isEmpty()) {
			List<Integer> listaContadores = mapeoRegistroAvc(mapArchivos, contAvcTransferenciaError, contAvcChequeError, contAvcChequePDError, contAvcEfectivoError, contError);
			contError += listaContadores.get(0);
			contAvcTransferenciaError = listaContadores.get(1);
			contAvcEfectivoError = listaContadores.get(2);
			contAvcChequePDError = listaContadores.get(3);
			contAvcChequeError = listaContadores.get(4);
			Iterator<Long> iterador = mapArchivos.keySet().iterator();
			while (iterador.hasNext()){
				Long next = iterador.next();
				if (mapArchivos.get(next).isEmpty()){
					Traza.error(getClass(), ARCHIVO_AVC+TRAZA_ID+next+TRAZA_SEPARADOR+TRAZA_NO_EXISTEN_REGISTROS_PARA_EL_ARCHIVO);
					ShvMigRegAvcArchivos archivoAvc;
					try {
						@SuppressWarnings("unchecked")
						List<ShvMigRegAvcArchivos> listaArchivoAvc =(List<ShvMigRegAvcArchivos>)genericoDao.listarPorValor(ShvMigRegAvcArchivos.class, "idRegAvcArchivos", next.toString());
						archivoAvc = listaArchivoAvc.get(0);
						genericoDao.insertarEnTabla(TABLA_ERROR_AVC_ARCHIVO,ShvMigRegAvcArchivos.class, archivoAvc);
					} catch (PersistenciaExcepcion e) {
						throw new NegocioExcepcion(e.getMessage(),e);
					}
					contArchivoAvcError++;
					contError++;
					iterador.remove();
					for (ArchivoAVCDto archivoDto : listaArchivo) {
						if(archivoDto.getId().equals(next)){
							listaArchivo.remove(archivoDto);
							break;
						}
					}
				}
			}
		}
		
		listaValoresDto.addAll(mapeoBoletaEfectivo());
		listaValoresDto.addAll(mapeoBoletaCheque());
		listaValoresDto.addAll(mapeoBoletaChequeDiferido());
		listaValoresDto.addAll(mapeoCheque());
		listaValoresDto.addAll(mapeoChequeDiferido());
		listaValoresDto.addAll(mapeoEfectivo());
		listaValoresDto.addAll(mapeoInterdeposito()); 
		listaValoresDto.addAll(mapeoTransferencia());
		
		listaBoletaDto = mapeoBoletaSinValor();
		
		Map<String, ClienteBean> idSiebel = new HashMap<String, ClienteBean>();
		ClienteBean clienteBean = null;
		
		for (ArchivoAVCDto archivo : listaArchivo) {
			try {
				archivoAvcServicio.crearArchivoConRegistrosAVC(archivo, mapArchivos.get(archivo.getId()));
				contArchivoAvcExito++;
				contExito += mapArchivos.get(archivo.getId()).size()+1;
				for (RegistroAVCDto registroDto : mapArchivos.get(archivo.getId())) {
					if (registroDto.getTipoValor().equals(TipoValorEnum.TRANSFERENCIA.getIdTipoValorString())){
						contAvcTransferenciaExito++;
					} else if (registroDto.getTipoValor().equals(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValorString())) {
						contAvcChequePDExito++;
					} else if (registroDto.getTipoValor().equals(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValorString())){	
						contAvcChequeExito++;
					} else {
						contAvcEfectivoExito++;
					}
				}
			} catch (Exception e) {
				contArchivoAvcError++;
				contError += mapArchivos.get(archivo.getId()).size()+1;
				Traza.error(getClass(), ARCHIVO_AVC+TRAZA_ID+archivo.getId()+TRAZA_SEPARADOR+ e.getMessage());
				try {
					@SuppressWarnings("unchecked")
					List<ShvMigRegAvcArchivos> listaArchivoAvc =(List<ShvMigRegAvcArchivos>)genericoDao.listarPorValor(ShvMigRegAvcArchivos.class, "idArchivo",  archivo.getId().toString());
					ShvMigRegAvcArchivos archivoAvc = listaArchivoAvc.get(0);
					genericoDao.insertarEnTabla(TABLA_ERROR_AVC_ARCHIVO,ShvMigRegAvcArchivos.class, archivoAvc);
					for (RegistroAVCDto registroDto : mapArchivos.get(archivo.getId())) {
						if (registroDto.getTipoValor().equals(TipoValorEnum.TRANSFERENCIA.getIdTipoValorString())){
							ShvMigRegAvcTransferencia transferenciaAvc = (ShvMigRegAvcTransferencia)genericoDao.listarPorValor(ShvMigRegAvcTransferencia.class, "idRegAvcTransferencia", registroDto.getId().toString()).iterator().next();
							genericoDao.insertarEnTabla(TABLA_ERROR_AVC_TRANSFERENCIA,ShvMigRegAvcTransferencia.class, transferenciaAvc);
							contAvcTransferenciaError++;
						} else if (registroDto.getTipoValor().equals(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValorString())) {
							ShvMigRegAvcChequeDiferido chequePD = (ShvMigRegAvcChequeDiferido)genericoDao.listarPorValor(ShvMigRegAvcChequeDiferido.class, "idRegAvcChequeDiferido", registroDto.getId().toString()).iterator().next();
							genericoDao.insertarEnTabla(TABLA_ERROR_AVC_CHEQUE_DIFERIDO,ShvMigRegAvcChequeDiferido.class, chequePD);
							contAvcChequePDError++;
						} else if (registroDto.getTipoValor().equals(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValorString())){
							ShvMigRegAvcCheque cheque = (ShvMigRegAvcCheque)genericoDao.listarPorValor(ShvMigRegAvcCheque.class, "idRegAvcCheque", registroDto.getId().toString()).iterator().next();
							genericoDao.insertarEnTabla(TABLA_ERROR_AVC_CHEQUE,ShvMigRegAvcCheque.class, cheque);
							contAvcChequeError++;
						} else {
							ShvMigRegAvcDepositoEfectivo efectivo = (ShvMigRegAvcDepositoEfectivo)genericoDao.listarPorValor(ShvMigRegAvcDepositoEfectivo.class, "idRegAvcDepositoEfectivo", registroDto.getId().toString()).iterator().next();
							genericoDao.insertarEnTabla(TABLA_ERROR_AVC_EFECTIVO,ShvMigRegAvcDepositoEfectivo.class, efectivo);
							contAvcEfectivoError++;
						}
					}
				} catch (PersistenciaExcepcion e1) {
					throw new NegocioExcepcion(e1.getMessage(),e1);
				}
			}
		}
		for (BoletaSinValorDto boleta : listaBoletaDto) {
			try{
				if (boleta.isErrorMapeo()){
					throw new NegocioExcepcion(TRAZA_DATOS_INVALIDOS);
				}
				if (!idSiebel.containsKey(boleta.getCodCliente())) {
					clienteBean = this.clienteServicio.consultarCliente(boleta.getCodCliente());
					idSiebel.put(boleta.getCodCliente(), clienteBean);
				} else {
					clienteBean = idSiebel.get(boleta.getCodCliente());
				}
				boleta.setCodClienteAgrupador(clienteBean.getIdClientePerfil());
				boleta.setRazonSocialClienteAgrupador(clienteBean.getRazonSocialClienteAgrupador());
				boleta.setUsuarioModificacion(parametroServicio.getValorTexto(Constantes.USUARIO_MIGRACION));
				boleta.setIdEmpresa(ID_EMPRESA);
				boletaServicio.crear(boleta);
				contExito++;
				contBoletaSinValorExito++;
			} catch (Exception e){
				contError++;
				contBoletaSinValorError++;
				Traza.error(getClass(), BOLETA_SIN_VALOR+TRAZA_ID+boleta.getId()+TRAZA_SEPARADOR+ e.getMessage());
				try {
					ShvMigBoletaSinValor boletaSinValor = (ShvMigBoletaSinValor)genericoDao.listarPorValor(ShvMigBoletaSinValor.class, "idBoletaSinValor", boleta.getId().toString()).iterator().next();
					genericoDao.insertarEnTabla(TABLA_ERROR_BOLETA_SIN_VALOR,ShvMigBoletaSinValor.class, boletaSinValor);
				} catch (PersistenciaExcepcion e1) {
					throw new NegocioExcepcion(e.getMessage(),e);
				}
				break;
			}
		}
		for (ValorDto valorDto : listaValoresDto) {
			try{
				if (valorDto.isErrorMapeo()){
					throw new NegocioExcepcion(TRAZA_DATOS_INVALIDOS);
				}
				if (!idSiebel.containsKey(valorDto.getCodCliente())){
					clienteBean = this.clienteServicio.consultarCliente(valorDto.getCodCliente());
					idSiebel.put(valorDto.getCodCliente(), clienteBean);
				} else {
					clienteBean = idSiebel.get(valorDto.getCodCliente());
				}
				valorDto.setCodClienteAgrupador(clienteBean.getIdClientePerfil());
				valorDto.setRazonSocialClienteAgrupador(clienteBean.getRazonSocialClienteAgrupador());
				valorDto.setUsuarioModificacion(parametroServicio.getValorTexto(Constantes.USUARIO_MIGRACION));
				valorDto.setIdEmpresa(ID_EMPRESA);
				valorDto.setCheckEnviarMailBoleta(false);
				valorDto.setCheckImprimirBoleta(false);
				valorDto.setMigracion(true);
				valorDto.setGenerarValorNoDispoblePorDefecto(false);
				valorDto.setBoletaEnviadaMailEstado(EnviarMailBoletaEstadoEnum.NO);
				valorDto.setBoletaImpresaEstado(ImprimirBoletaEstadoEnum.NO);
				if (Validaciones.isNullOrEmpty(valorDto.getIdAnalista())) {
					throw new NegocioExcepcion(TRAZA_ID_ANALISTA_ERROR);
				}
				ValorDto valorDuplicado = valorServicio.validarDuplicados(valorDto.getUsuarioModificacion(), valorDto);
				if(valorDuplicado.getExisteDuplicado()){
					throw new NegocioExcepcion(TRAZA_VALOR_DUPLICADO + valorServicio.armarMensajeDuplicado(valorDuplicado));
				}
				//guardo valores para correcciones
				String numeroBoleta = valorDto.getNumeroBoleta();
				String usuarioAutorizacion = valorDto.getUsuarioAutorizacion();
				
				Long id =valorServicio.crear(valorDto);
				if(!Validaciones.isNullOrEmpty(valorDto.getNroConstancia())){
					valorServicio.migrarConstancia(id,valorDto.getNroConstancia());
				}
				//Correcciones migracion
				ShvValValor valorModelo = valorServicio.buscarValValorPorIdValor(id.intValue());
				if(valorModelo.getBoleta() != null){
					valorModelo.getBoleta().setNumeroBoleta(Long.valueOf(numeroBoleta));
				}
				valorModelo.setUsuarioAutorizacion(usuarioAutorizacion);
				valorServicio.setearNumeroValor(valorModelo, valorModelo);
				valorServicio.actualizarValor(valorModelo);
				
				contExito++;
				switch (valorDto.getIdTipoValor()) {
				case "2":
					contBolChequeExito++;
					break;
				case "3":
					contBolChequePDExito++;
					break;
				case "4":
					contBolEfectivoExito++;
					break;
				case "5":
					contChequeExito++;
					break;
				case "6":
					contChequeDifExito++;
					break;
				case "7":
					contEfectivoExito++;
					break;
				case "8":
					contTransferenciaExito++;
					break;
				case "9":
					contInterdepositoExito++;
					break;
				default:
					break;
				}
			} catch(Exception e) {
				Traza.error(getClass(), VALOR+TRAZA_ID+valorDto.getIdValor()+TRAZA_SEPARADOR+ e.getMessage());
				contError++;
				try {
					switch (valorDto.getIdTipoValor()) {
					case "2":
						ShvMigBoletaDepositoCheque bolCheque = (ShvMigBoletaDepositoCheque)genericoDao.listarPorValor(ShvMigBoletaDepositoCheque.class, "idBoletaDepositoCheque", valorDto.getIdValor().toString()).iterator().next();
						contBolChequeError++;
						genericoDao.insertarEnTabla(TABLA_ERROR_BOLETA_CHEQUE,ShvMigBoletaDepositoCheque.class, bolCheque);
						break;
					case "3":
						ShvMigBoletaDepositoChequeDiferido bolChequePD = (ShvMigBoletaDepositoChequeDiferido)genericoDao.listarPorValor(ShvMigBoletaDepositoChequeDiferido.class, "idBoletaDepositoChequeDiferido", valorDto.getIdValor().toString()).iterator().next();
						contBolChequePDError++;
						genericoDao.insertarEnTabla(TABLA_ERROR_BOLETA_CHEQUE_DIFERIDO,ShvMigBoletaDepositoChequeDiferido.class, bolChequePD);
						break;
					case "4":
						ShvMigBoletaDepositoEfectivo bolEfectivo = (ShvMigBoletaDepositoEfectivo)genericoDao.listarPorValor(ShvMigBoletaDepositoEfectivo.class, "idBoletaDepositoEfectivo", valorDto.getIdValor().toString()).iterator().next();
						contBolEfectivoError++;
						genericoDao.insertarEnTabla(TABLA_ERROR_BOLETA_EFECTIVO,ShvMigBoletaDepositoEfectivo.class, bolEfectivo);
						break;
					case "5":
						ShvMigValorCheque cheque = (ShvMigValorCheque)genericoDao.listarPorValor(ShvMigValorCheque.class, "idValorCheque", valorDto.getIdValor().toString()).iterator().next();
						contChequeError++;
						genericoDao.insertarEnTabla(TABLA_ERROR_CHEQUE,ShvMigValorCheque.class, cheque);
						break;
					case "6":
						ShvMigValorChequeDiferido chequePD = (ShvMigValorChequeDiferido)genericoDao.listarPorValor(ShvMigValorChequeDiferido.class, "idValorChequeDiferido", valorDto.getIdValor().toString()).iterator().next();
						contChequeDifError++;
						genericoDao.insertarEnTabla(TABLA_ERROR_CHEQUE_DIFERIDO,ShvMigValorChequeDiferido.class, chequePD);
						break;
					case "7":
						ShvMigValorEfectivo efectivo = (ShvMigValorEfectivo)genericoDao.listarPorValor(ShvMigValorEfectivo.class, "idValorEfectivo", valorDto.getIdValor().toString()).iterator().next();
						contEfectivoError++;
						genericoDao.insertarEnTabla(TABLA_ERROR_EFECTIVO,ShvMigValorEfectivo.class, efectivo);
						break;
					case "8":
						ShvMigValorTransferencia transferencia = (ShvMigValorTransferencia)genericoDao.listarPorValor(ShvMigValorTransferencia.class, "idValorTransferencia", valorDto.getIdValor().toString()).iterator().next();
						contTransferenciaError++;
						genericoDao.insertarEnTabla(TABLA_ERROR_TRANSFERENCIA,ShvMigValorTransferencia.class, transferencia);
						break;
					case "9":
						ShvMigValorInterdeposito interdeposito = (ShvMigValorInterdeposito)genericoDao.listarPorValor(ShvMigValorInterdeposito.class, "idValorInterdeposito", valorDto.getIdValor().toString()).iterator().next();
						contInterdepositoError++;
						genericoDao.insertarEnTabla(TABLA_ERROR_INTERDEPOSITO,ShvMigValorInterdeposito.class, interdeposito);
						break;
					default:
						break;
					}
				} catch (PersistenciaExcepcion e1) {
					throw new NegocioExcepcion(e1.getMessage(),e1);
				}
			}
		}
		registro +=  System.lineSeparator()+SUBTITULO_RESULTADO+System.lineSeparator()
				+ESPACIOS+TipoValorEnum.CHEQUE.getDescripcion()+SEPARADOR_APERTURA+MIGRADOS+contChequeExito+ERRONEOS+contChequeError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+TipoValorEnum.CHEQUE_DIFERIDO.getDescripcion()+SEPARADOR_APERTURA+MIGRADOS+contChequeDifExito+ERRONEOS+contChequeDifError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+TipoValorEnum.EFECTIVO.getDescripcion()+SEPARADOR_APERTURA+MIGRADOS+contEfectivoExito+ERRONEOS+contEfectivoError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getDescripcion()+SEPARADOR_APERTURA+MIGRADOS+contBolChequeExito+ERRONEOS+contBolChequeError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getDescripcion()+SEPARADOR_APERTURA+MIGRADOS+contBolChequePDExito+ERRONEOS+contBolChequePDError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getDescripcion()+SEPARADOR_APERTURA+MIGRADOS+contBolEfectivoExito+ERRONEOS+contBolEfectivoError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+TipoValorEnum.INTERDEPÓSITO.getDescripcion()+SEPARADOR_APERTURA+MIGRADOS+contInterdepositoExito+ERRONEOS+contInterdepositoError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+TipoValorEnum.TRANSFERENCIA.getDescripcion()+SEPARADOR_APERTURA+MIGRADOS+contTransferenciaExito+ERRONEOS+contTransferenciaError+")"+System.lineSeparator()
				+ESPACIOS+BOLETA_SIN_VALOR+SEPARADOR_APERTURA+MIGRADOS+contBoletaSinValorExito+ERRONEOS+contBoletaSinValorError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+ARCHIVO_AVC+SEPARADOR_APERTURA+MIGRADOS+contArchivoAvcExito+ERRONEOS+contArchivoAvcError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+TRANSFERENCIA_AVC+SEPARADOR_APERTURA+MIGRADOS+contAvcTransferenciaExito+ERRONEOS+contAvcTransferenciaError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+CHEQUE_DIFERIDO_AVC+SEPARADOR_APERTURA+MIGRADOS+contAvcChequePDExito+ERRONEOS+contAvcChequePDError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+CHEQUE_AVC+SEPARADOR_APERTURA+MIGRADOS+contAvcChequeExito+ERRONEOS+contAvcChequeError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+EFECTIVO_AVC+SEPARADOR_APERTURA+MIGRADOS+contAvcEfectivoExito+ERRONEOS+contAvcEfectivoError+SEPARADOR_CIERRE+System.lineSeparator()
				+ESPACIOS+TOTAL+SEPARADOR_APERTURA+MIGRADOS+contExito+ERRONEOS+ contError+SEPARADOR_CIERRE+System.lineSeparator();
		
		String carpetaDestino= Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.migracion");
		String nombreArchivo= Constantes.SHIVA_APLICACION.toUpperCase()+NOMBRE_ARCHIVO_SEPARADOR+MIGRACION+NOMBRE_ARCHIVO_SEPARADOR+Utilidad.formatDateAAAAMMDD(new Date())+ARCHIVO_EXTENSION;
		Traza.auditoria(getClass(), TRAZA_MIGRACION, registro);
		contabilidadMigracion();
		try {
			ControlArchivo.escribir(registro, carpetaDestino, nombreArchivo);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void contabilidadMigracion() throws NegocioExcepcion {
		try {
			List<ShvCntContabilidadDetalle> listaInterdepositos = (List<ShvCntContabilidadDetalle>) genericoDao.listarPorValor(ShvCntContabilidadDetalle.class, "idTipoMedioPago", valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.INTERDEPÓSITO.getIdTipoValor())).get(0).getTipoMedioPago().getIdTipoMedioPago());
			ShvParamTipoOrigenContable origen = (ShvParamTipoOrigenContable) genericoDao.listarPorValor(ShvParamTipoOrigenContable.class, "idTipoOrigenContable", ContabilidadServicioImpl.ORIGEN_CONT_020).iterator().next();
			for (ShvCntContabilidadDetalle detalle : listaInterdepositos) {
				detalle.setIdTipoOrigenContable(origen);
				contabilidadDao.insertarActualizarContabilidadDetalle(detalle);
			}
			List<ShvCntContabilidadDetalle> listaBoletas = (List<ShvCntContabilidadDetalle>) genericoDao.listarPorValor(ShvCntContabilidadDetalle.class, "idTipoOrigenContable", ContabilidadServicioImpl.ORIGEN_CONT_010);
			
			ShvParamTipoOrigenContable origenBoletas = (ShvParamTipoOrigenContable) genericoDao.listarPorValor(ShvParamTipoOrigenContable.class, "idTipoOrigenContable", ContabilidadServicioImpl.ORIGEN_CONT_001).iterator().next();
			for (ShvCntContabilidadDetalle detalle : listaBoletas) {
				ShvCntContabilidadDetalle nuevoDetalle = new ShvCntContabilidadDetalle();
				BeanUtils.copyProperties(detalle, nuevoDetalle);
				nuevoDetalle.setIdTipoOrigenContable(origenBoletas);
				nuevoDetalle.setIdContabilidadDetalle(null);
				contabilidadDao.insertarActualizarContabilidadDetalle(nuevoDetalle);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ValorDto> mapeoBoletaEfectivo() throws NegocioExcepcion {
		List<ValorDto> listaDto = new ArrayList<ValorDto>();
		List<ShvMigBoletaDepositoEfectivo> listaMig = new ArrayList<ShvMigBoletaDepositoEfectivo>();
		try {
			listaMig = (List<ShvMigBoletaDepositoEfectivo>) genericoDao.listar(ShvMigBoletaDepositoEfectivo.class);
			for (ShvMigBoletaDepositoEfectivo boletaEfectivo : listaMig) {
				ValorDto dto = new ValorDto();
				try {	
					if(boletaEfectivo.getEstado().equals(BOL_ESTADO_CONCILIADO) || boletaEfectivo.getEstado().equals(BOL_ESTADO_CONCILIADO_DIFERENCIA)){
						dto.setGenerarValorDispoblePorDefecto(true);
					} else {
						dto.setGenerarValorDispoblePorDefecto(false);
					}
					dto.setIdTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValor()));
					dto.setIdValor(boletaEfectivo.getIdBoletaDepositoEfectivo());
					dto.setIdSegmento(boletaEfectivo.getIdSegmento());
					dto.setCodCliente(String.valueOf(boletaEfectivo.getIdClienteLegado()));
					dto.setIdAnalista(boletaEfectivo.getIdAnalista());
					dto.setIdCopropietario(boletaEfectivo.getIdCopropietario());
					dto.setIdAcuerdo(String.valueOf(boletaEfectivo.getNumeroAcuerdo()));
					dto.setImporte(Utilidad.formatCurrency(boletaEfectivo.getImporte(), false, false));
					dto.setSaldoDisponible(boletaEfectivo.getSaldoDisponible().toString());
					dto.setObservaciones(boletaEfectivo.getObservaciones());
					dto.setNumeroPartidaContable(String.valueOf(boletaEfectivo.getNumeroPartidaContable()));
					dto.setOperacionAsociada(String.valueOf(boletaEfectivo.getOperacionAsociada()));
					dto.setIdMotivo(String.valueOf(boletaEfectivo.getIdMotivo()));
					dto.setUsuarioAutorizacion(boletaEfectivo.getUsuarioAutorizacion());
					dto.setUsuarioEjecutivo(boletaEfectivo.getUsuarioEjecutivo());
					dto.setUsuarioAsistente(boletaEfectivo.getUsuarioAsistente());
					dto.setNumeroBoleta(String.valueOf(boletaEfectivo.getNumeroBoleta()));
					dto.setEmail(boletaEfectivo.getEmail());
					dto.setFechaAlta(boletaEfectivo.getFechaAlta());
					dto.setFechaDeposito(Utilidad.formatDatePicker(boletaEfectivo.getFechaDeposito()));
					dto.setFechaIngresoRecibo(Utilidad.formatDatePicker(boletaEfectivo.getFechaRecibo()));
					dto.setReciboPreImpreso(String.valueOf(boletaEfectivo.getIdReciboPreimpreso()));
					dto.setNroConstancia(String.valueOf(boletaEfectivo.getNroConstanciaRecepcion()));
					dto.setIdBoleta(String.valueOf(boletaEfectivo.getIdBoletaDepositoEfectivo()));
					mapearOrigen(boletaEfectivo.getIdOrigen(), dto,false);
				} catch (Exception e) {
					dto.setErrorMapeo(true);
				}  finally {
					listaDto.add(dto);
				}
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
		
		return listaDto;
	}
	@SuppressWarnings("unchecked")
	public List<ValorDto> mapeoBoletaCheque() throws NegocioExcepcion{
		List<ValorDto> listaDto = new ArrayList<ValorDto>();
		List<ShvMigBoletaDepositoCheque> listaMig = new ArrayList<ShvMigBoletaDepositoCheque>();
		try {
			listaMig = (List<ShvMigBoletaDepositoCheque>) genericoDao.listar(ShvMigBoletaDepositoCheque.class);
			for (ShvMigBoletaDepositoCheque boletaCheque : listaMig) {
				ValorDto dto = new ValorDto();
				try {
					if(boletaCheque.getEstado().equals(BOL_ESTADO_CONCILIADO) || boletaCheque.getEstado().equals(BOL_ESTADO_CONCILIADO_DIFERENCIA)){
						dto.setGenerarValorDispoblePorDefecto(true);
					} else {
						dto.setGenerarValorDispoblePorDefecto(false);
					}
					dto.setIdTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor()));
					dto.setIdValor(boletaCheque.getIdBoletaDepositoCheque());
					dto.setIdSegmento(boletaCheque.getIdSegmento());
					dto.setCodCliente(String.valueOf(boletaCheque.getIdClienteLegado()));
					dto.setIdAnalista(boletaCheque.getIdAnalista());
					dto.setIdCopropietario(boletaCheque.getIdCopropietario());
					dto.setIdAcuerdo(String.valueOf(boletaCheque.getNumeroAcuerdo()));
					dto.setImporte(Utilidad.formatCurrency(boletaCheque.getImporte(), false, false));
					dto.setSaldoDisponible(boletaCheque.getSaldoDisponible().toString());
					dto.setObservaciones(boletaCheque.getObservaciones());
					dto.setNumeroPartidaContable(String.valueOf(boletaCheque.getNumeroPartidaContable()));
					dto.setOperacionAsociada(String.valueOf(boletaCheque.getOperacionAsociada()));
					dto.setIdMotivo(String.valueOf(boletaCheque.getIdMotivo()));
					dto.setUsuarioAutorizacion(boletaCheque.getUsuarioAutorizacion());
					dto.setUsuarioEjecutivo(boletaCheque.getUsuarioEjecutivo());
					dto.setUsuarioAsistente(boletaCheque.getUsuarioAsistente());
					dto.setNumeroBoleta(String.valueOf(boletaCheque.getNumeroBoleta()));
					dto.setEmail(boletaCheque.getEmail());
					dto.setFechaAlta(boletaCheque.getFechaAlta());
					dto.setFechaDeposito(Utilidad.formatDatePicker(boletaCheque.getFechaDeposito()));
					dto.setFechaIngresoRecibo(Utilidad.formatDatePicker(boletaCheque.getFechaRecibo()));
					dto.setReciboPreImpreso(String.valueOf(boletaCheque.getIdReciboPreimpreso()));
					dto.setNroConstancia(String.valueOf(boletaCheque.getNroConstanciaRecepcion()));
					dto.setIdBoleta(String.valueOf(boletaCheque.getIdBoletaDepositoCheque()));
					dto.setNumeroCheque(String.valueOf(boletaCheque.getNumeroCheque()));
					mapearOrigen(boletaCheque.getIdOrigen(), dto,false);
					mapearBanco(boletaCheque.getIdBancoOrigen(), dto);
				} catch (Exception e) {
					dto.setErrorMapeo(true);
				}  finally {
					listaDto.add(dto);
				}
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
		return listaDto;
	}

	private void mapearBanco(String idBancoOrigen,
			ValorDto dto) throws PersistenciaExcepcion {
		ShvParamBanco banco = bancoDao.buscarBanco(idBancoOrigen);
		dto.setIdBancoOrigen(banco.getIdBanco());
		dto.setBancoOrigen(banco.getDescripcion());
	}
	@SuppressWarnings("unchecked")
	public List<ValorDto> mapeoBoletaChequeDiferido() throws NegocioExcepcion{
		List<ValorDto> listaDto = new ArrayList<ValorDto>();
		List<ShvMigBoletaDepositoChequeDiferido> listaMig = new ArrayList<ShvMigBoletaDepositoChequeDiferido>();
		try {
			listaMig = (List<ShvMigBoletaDepositoChequeDiferido>) genericoDao.listar(ShvMigBoletaDepositoChequeDiferido.class);
			for (ShvMigBoletaDepositoChequeDiferido boletaChequePD : listaMig) {
				ValorDto dto = new ValorDto();
				try {
					if(boletaChequePD.getEstado().equals(BOL_ESTADO_CONCILIADO) || boletaChequePD.getEstado().equals(BOL_ESTADO_CONCILIADO_DIFERENCIA)){
						dto.setGenerarValorDispoblePorDefecto(true);
					} else {
						dto.setGenerarValorDispoblePorDefecto(false);
					}
					dto.setIdTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor()));
					dto.setIdValor(boletaChequePD.getIdBoletaDepositoChequeDiferido());
					dto.setIdSegmento(boletaChequePD.getIdSegmento());
					dto.setCodCliente(String.valueOf(boletaChequePD.getIdClienteLegado()));
					dto.setIdAnalista(boletaChequePD.getIdAnalista());
					dto.setIdCopropietario(boletaChequePD.getIdCopropietario());
					dto.setIdAcuerdo(String.valueOf(boletaChequePD.getNumeroAcuerdo()));
					dto.setImporte(Utilidad.formatCurrency(boletaChequePD.getImporte(), false, false));
					dto.setSaldoDisponible(boletaChequePD.getSaldoDisponible().toString());
					dto.setObservaciones(boletaChequePD.getObservaciones());
					dto.setNumeroPartidaContable(String.valueOf(boletaChequePD.getNumeroPartidaContable()));
					dto.setOperacionAsociada(String.valueOf(boletaChequePD.getOperacionAsociada()));
					dto.setIdMotivo(String.valueOf(boletaChequePD.getIdMotivo()));
					dto.setUsuarioAutorizacion(boletaChequePD.getUsuarioAutorizacion());
					dto.setUsuarioEjecutivo(boletaChequePD.getUsuarioEjecutivo());
					dto.setUsuarioAsistente(boletaChequePD.getUsuarioAsistente());
					dto.setNumeroBoleta(String.valueOf(boletaChequePD.getNumeroBoleta()));
					dto.setEmail(boletaChequePD.getEmail());
					dto.setFechaAlta(boletaChequePD.getFechaAlta());
					dto.setFechaDeposito(Utilidad.formatDatePicker(boletaChequePD.getFechaDeposito()));
					dto.setFechaIngresoRecibo(Utilidad.formatDatePicker(boletaChequePD.getFechaRecibo()));
					dto.setReciboPreImpreso(String.valueOf(boletaChequePD.getIdReciboPreimpreso()));
					dto.setNroConstancia(String.valueOf(boletaChequePD.getNroConstanciaRecepcion()));
					dto.setIdBoleta(String.valueOf(boletaChequePD.getIdBoletaDepositoChequeDiferido()));
					dto.setNumeroCheque(String.valueOf(boletaChequePD.getNumeroCheque()));
					dto.setFechaVencimiento(Utilidad.formatDatePicker(boletaChequePD.getFechaVencimiento()));
					mapearOrigen(boletaChequePD.getIdOrigen(), dto,false);
					mapearBanco(boletaChequePD.getIdBancoOrigen(), dto);
				} catch (Exception e) {
					dto.setErrorMapeo(true);
				}  finally {
					listaDto.add(dto);
				}
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
		return listaDto;
	}
	@SuppressWarnings("unchecked")
	public List<BoletaSinValorDto> mapeoBoletaSinValor() throws NegocioExcepcion{
		List<BoletaSinValorDto> listaDto = new ArrayList<BoletaSinValorDto>();
		List<ShvMigBoletaSinValor> listaMig = new ArrayList<ShvMigBoletaSinValor>();
		try {
			listaMig = (List<ShvMigBoletaSinValor>) genericoDao.listar(ShvMigBoletaSinValor.class);
			for (ShvMigBoletaSinValor boleta : listaMig) {
				BoletaSinValorDto dto = new BoletaSinValorDto();
				try {
					dto.setId(boleta.getIdBoletaSinValor());
					dto.setIdSegmento(boleta.getIdSegmento());
					dto.setCodCliente(String.valueOf(boleta.getIdClienteLegado()));
					dto.setIdAnalista(boleta.getIdAnalista());
					dto.setIdCopropietario(boleta.getIdCopropietario());
					dto.setIdAcuerdo(String.valueOf(boleta.getNumeroAcuerdo()));
					dto.setImporte(Utilidad.formatCurrency(boleta.getImporte(), false, false));
					dto.setObservaciones(boleta.getObservaciones());
					dto.setOperacionAsociada(String.valueOf(boleta.getOperacionAsociada()));
					dto.setIdMotivo(String.valueOf(boleta.getIdMotivo()));
					dto.setNumeroBoleta(String.valueOf(boleta.getNumeroBoleta()));
					dto.setEmail(boleta.getEmail());
					if (boleta.getIdOrigen() != null){
						ShvParamOrigen origen = origenDao.buscarOrigen(String.valueOf(boleta.getIdOrigen()));
						if (origen != null){
							dto.setIdOrigen(String.valueOf(boleta.getIdOrigen()));
						} else {
							throw new NegocioExcepcion(TRAZA_ORIGEN_INVALIDO);
						}
					}
				} catch (Exception e) {
					dto.setErrorMapeo(true);
				}  finally {
					listaDto.add(dto);
				}
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
		return listaDto;
	}
	@SuppressWarnings("unchecked")
	public List<ValorDto> mapeoChequeDiferido() throws NegocioExcepcion{
		List<ValorDto> listaDto = new ArrayList<ValorDto>();
		List<ShvMigValorChequeDiferido> listaMig = new ArrayList<ShvMigValorChequeDiferido>();
		try {
			listaMig = (List<ShvMigValorChequeDiferido>) genericoDao.listar(ShvMigValorChequeDiferido.class);
			for (ShvMigValorChequeDiferido chequeDiferido : listaMig) {
				ValorDto dto = new ValorDto();
				try {
					dto.setIdTipoValor(String.valueOf(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor()));
					dto.setIdValor(chequeDiferido.getIdValorChequeDiferido());
					dto.setGenerarValorDispoblePorDefecto(true);
					dto.setIdSegmento(chequeDiferido.getIdSegmento());
					dto.setNumeroBoleta(String.valueOf(chequeDiferido.getNumeroBoleta()));
					dto.setCodCliente(String.valueOf(chequeDiferido.getIdClienteLegado()));
					dto.setIdAnalista(chequeDiferido.getIdAnalista());
					dto.setIdCopropietario(chequeDiferido.getIdCopropietario());
					dto.setIdAcuerdo(String.valueOf(chequeDiferido.getNumeroAcuerdo()));
					dto.setImporte(Utilidad.formatCurrency(chequeDiferido.getImporte(), false, false));
					dto.setSaldoDisponible(chequeDiferido.getSaldoDisponible().toString());
					dto.setObservaciones(chequeDiferido.getObservaciones());
					dto.setNumeroPartidaContable(String.valueOf(chequeDiferido.getNumeroPartidaContable()));
					dto.setOperacionAsociada(String.valueOf(chequeDiferido.getOperacionAsociada()));
					dto.setIdMotivo(String.valueOf(chequeDiferido.getIdMotivo()));
					dto.setUsuarioAutorizacion(chequeDiferido.getUsuarioAutorizacion());
					dto.setUsuarioEjecutivo(chequeDiferido.getUsuarioEjecutivo());
					dto.setUsuarioAsistente(chequeDiferido.getUsuarioAsistente());
					dto.setFechaAlta(chequeDiferido.getFechaAlta());
					dto.setFechaDeposito(Utilidad.formatDatePicker(chequeDiferido.getFechaDeposito()));
					dto.setIdBoleta(String.valueOf(chequeDiferido.getIdValorChequeDiferido()));
					dto.setNumeroCheque(String.valueOf(chequeDiferido.getNumeroCheque()));
					dto.setFechaVencimiento(Utilidad.formatDatePicker(chequeDiferido.getFechaVencimiento()));
					mapearOrigen(chequeDiferido.getIdOrigen(), dto,true);
					mapearBanco(chequeDiferido.getIdBancoOrigen(), dto);
				} catch (Exception e) {
					dto.setErrorMapeo(true);
				}  finally {
					listaDto.add(dto);
				}
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
		return listaDto;
	}
	@SuppressWarnings("unchecked")
	public List<ValorDto> mapeoCheque() throws NegocioExcepcion{
		List<ValorDto> listaDto = new ArrayList<ValorDto>();
		List<ShvMigValorCheque> listaMig = new ArrayList<ShvMigValorCheque>();
		try {
			listaMig = (List<ShvMigValorCheque>) genericoDao.listar(ShvMigValorCheque.class);
			for (ShvMigValorCheque cheque : listaMig) {
				ValorDto dto = new ValorDto();
				try {
					dto.setIdTipoValor(String.valueOf(TipoValorEnum.CHEQUE.getIdTipoValor()));
					dto.setIdValor(cheque.getIdValorCheque());
					dto.setGenerarValorDispoblePorDefecto(true);
					dto.setIdSegmento(cheque.getIdSegmento());
					dto.setNumeroBoleta(String.valueOf(cheque.getNumeroBoleta()));
					dto.setCodCliente(String.valueOf(cheque.getIdClienteLegado()));
					dto.setIdAnalista(cheque.getIdAnalista());
					dto.setIdCopropietario(cheque.getIdCopropietario());
					dto.setIdAcuerdo(String.valueOf(cheque.getNumeroAcuerdo()));
					dto.setImporte(Utilidad.formatCurrency(cheque.getImporte(), false, false));
					dto.setSaldoDisponible(cheque.getSaldoDisponible().toString());
					dto.setObservaciones(cheque.getObservaciones());
					dto.setNumeroPartidaContable(String.valueOf(cheque.getNumeroPartidaContable()));
					dto.setOperacionAsociada(String.valueOf(cheque.getOperacionAsociada()));
					dto.setIdMotivo(String.valueOf(cheque.getIdMotivo()));
					dto.setUsuarioAutorizacion(cheque.getUsuarioAutorizacion());
					dto.setUsuarioEjecutivo(cheque.getUsuarioEjecutivo());
					dto.setUsuarioAsistente(cheque.getUsuarioAsistente());
					dto.setFechaAlta(cheque.getFechaAlta());
					dto.setFechaDeposito(Utilidad.formatDatePicker(cheque.getFechaDeposito()));
					dto.setIdBoleta(String.valueOf(cheque.getIdValorCheque()));
					dto.setNumeroCheque(String.valueOf(cheque.getNumeroCheque()));
					mapearOrigen(cheque.getIdOrigen(), dto,true);
					mapearBanco(cheque.getIdBancoOrigen(), dto);
				} catch (Exception e) {
					dto.setErrorMapeo(true);
				}  finally {
					listaDto.add(dto);
				}
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
		return listaDto;
	}
	@SuppressWarnings("unchecked")
	public List<ValorDto> mapeoEfectivo() throws NegocioExcepcion{
		List<ValorDto> listaDto = new ArrayList<ValorDto>();
		List<ShvMigValorEfectivo> listaMig = new ArrayList<ShvMigValorEfectivo>();
		try {
			listaMig = (List<ShvMigValorEfectivo>) genericoDao.listar(ShvMigValorEfectivo.class);
			for (ShvMigValorEfectivo efectivo : listaMig) {
				ValorDto dto = new ValorDto();
				try {
					dto.setIdTipoValor(String.valueOf(TipoValorEnum.EFECTIVO.getIdTipoValor()));
					dto.setIdValor(efectivo.getIdValorEfectivo());
					dto.setGenerarValorDispoblePorDefecto(true);
					dto.setIdSegmento(efectivo.getIdSegmento());
					dto.setNumeroBoleta(String.valueOf(efectivo.getNumeroBoleta()));
					dto.setCodCliente(String.valueOf(efectivo.getIdClienteLegado()));
					dto.setIdAnalista(efectivo.getIdAnalista());
					dto.setIdCopropietario(efectivo.getIdCopropietario());
					dto.setIdAcuerdo(String.valueOf(efectivo.getNumeroAcuerdo()));
					dto.setImporte(Utilidad.formatCurrency(efectivo.getImporte(), false, false));
					dto.setSaldoDisponible(efectivo.getSaldoDisponible().toString());
					dto.setObservaciones(efectivo.getObservaciones());
					dto.setNumeroPartidaContable(String.valueOf(efectivo.getNumeroPartidaContable()));
					dto.setOperacionAsociada(String.valueOf(efectivo.getOperacionAsociada()));
					dto.setIdMotivo(String.valueOf(efectivo.getIdMotivo()));
					dto.setUsuarioAutorizacion(efectivo.getUsuarioAutorizacion());
					dto.setUsuarioEjecutivo(efectivo.getUsuarioEjecutivo());
					dto.setUsuarioAsistente(efectivo.getUsuarioAsistente());
					dto.setFechaAlta(efectivo.getFechaAlta());
					dto.setFechaDeposito(Utilidad.formatDatePicker(efectivo.getFechaDeposito()));
					dto.setIdBoleta(String.valueOf(efectivo.getIdValorEfectivo()));
					mapearOrigen(efectivo.getIdOrigen(), dto,true);
				} catch (Exception e) {
					dto.setErrorMapeo(true);
				}  finally {
					listaDto.add(dto);
				}
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
		return listaDto;
	}
	@SuppressWarnings("unchecked")
	public List<ValorDto> mapeoTransferencia() throws NegocioExcepcion{
		List<ValorDto> listaDto = new ArrayList<ValorDto>();
		List<ShvMigValorTransferencia> listaMig = new ArrayList<ShvMigValorTransferencia>();
		try {
			listaMig = (List<ShvMigValorTransferencia>) genericoDao.listar(ShvMigValorTransferencia.class);
			for (ShvMigValorTransferencia transferencia : listaMig) {
				ValorDto dto = new ValorDto();
				try {
					dto.setIdTipoValor(String.valueOf(TipoValorEnum.TRANSFERENCIA.getIdTipoValor()));
					dto.setIdValor(transferencia.getIdValorTransferencia());
					dto.setGenerarValorDispoblePorDefecto(true);
					dto.setIdSegmento(transferencia.getIdSegmento());
					dto.setCodCliente(String.valueOf(transferencia.getIdClienteLegado()));
					dto.setIdAnalista(transferencia.getIdAnalista());
					dto.setIdCopropietario(transferencia.getIdCopropietario());
					dto.setIdAcuerdo(String.valueOf(transferencia.getNumeroAcuerdo()));
					dto.setImporte(Utilidad.formatCurrency(transferencia.getImporte(), false, false));
					dto.setSaldoDisponible(transferencia.getSaldoDisponible().toString());
					dto.setObservaciones(transferencia.getObservaciones());
					dto.setNumeroPartidaContable(String.valueOf(transferencia.getNumeroPartidaContable()));
					dto.setOperacionAsociada(String.valueOf(transferencia.getOperacionAsociada()));
					dto.setIdMotivo(String.valueOf(transferencia.getIdMotivo()));
					dto.setUsuarioAutorizacion(transferencia.getUsuarioAutorizacion());
					dto.setUsuarioEjecutivo(transferencia.getUsuarioEjecutivo());
					dto.setUsuarioAsistente(transferencia.getUsuarioAsistente());
					dto.setFechaAlta(transferencia.getFechaAlta());
					dto.setFechaTransferencia(Utilidad.formatDatePicker(transferencia.getFechaTransferencia()));
					dto.setIdBoleta(String.valueOf(transferencia.getIdValorTransferencia()));
					dto.setIdBancoOrigen(transferencia.getIdBancoOrigen());
					dto.setNumeroReferencia(String.valueOf(transferencia.getNumeroReferencia()));
					mapearOrigen(transferencia.getIdOrigen(), dto,true);
				} catch (Exception e) {
					dto.setErrorMapeo(true);
				} finally {
					listaDto.add(dto);
				}
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
		return listaDto;
	}
	@SuppressWarnings("unchecked")
	public List<ValorDto> mapeoInterdeposito() throws NegocioExcepcion{
		List<ValorDto> listaDto = new ArrayList<ValorDto>();
		List<ShvMigValorInterdeposito> listaMig = new ArrayList<ShvMigValorInterdeposito>();
		List<ShvParamOrganismo> listaOrganismos = new ArrayList<ShvParamOrganismo>();
		List<ShvMigEquivalencia> listaEquivalencias = new ArrayList<ShvMigEquivalencia>();
		try {
			listaMig = (List<ShvMigValorInterdeposito>) genericoDao.listar(ShvMigValorInterdeposito.class);
			listaOrganismos = organismoDao.listarOrganismos();
			listaEquivalencias = (List<ShvMigEquivalencia>) genericoDao.listarPorValor(ShvMigEquivalencia.class,EQ_DESCRIPCION,EQ_BANCO_INTERDEPOSITO);
			for (ShvMigValorInterdeposito interdeposito : listaMig) {
				ValorDto dto = new ValorDto();
				try {
					for (ShvMigEquivalencia equivalencia : listaEquivalencias) {
						if(equivalencia.getOrigen().equals(interdeposito.getIdBancoDeposito())){
							dto.setIdAcuerdo(equivalencia.getDestino());
							break;
						}
					}
					dto.setIdTipoValor(String.valueOf(TipoValorEnum.INTERDEPÓSITO.getIdTipoValor()));
					dto.setIdValor(interdeposito.getIdValorInterdeposito());
					dto.setIdSegmento(interdeposito.getIdSegmento());
					dto.setIdBancoDeposito(interdeposito.getIdBancoDeposito());
					dto.setImporte(Utilidad.formatCurrency(interdeposito.getImporte(), false, false));
					dto.setSaldoDisponible(interdeposito.getSaldoDisponible().toString());
					dto.setObservaciones(interdeposito.getObservaciones());
					dto.setNumeroPartidaContable(String.valueOf(interdeposito.getNumeroPartidaContable()));
					dto.setOperacionAsociada(String.valueOf(interdeposito.getOperacionAsociada()));
					dto.setIdMotivo(String.valueOf(interdeposito.getIdMotivo()));
					dto.setUsuarioAutorizacion(interdeposito.getUsuarioAutorizacion());
					dto.setUsuarioEjecutivo(interdeposito.getUsuarioEjecutivo());
					dto.setUsuarioAsistente(interdeposito.getUsuarioAsistente());
					dto.setFechaAlta(interdeposito.getFechaAlta());
					dto.setIdBoleta(String.valueOf(interdeposito.getIdValorInterdeposito()));
					dto.setFechaInterdeposito(Utilidad.formatDatePicker(interdeposito.getFechaInterdeposito()));
					dto.setCodOrganismo(String.valueOf(interdeposito.getCodigoOrganismo()));
					dto.setNumeroInterdepositoCdif(String.valueOf(interdeposito.getNumeroInterdeposito()));
					dto.setGenerarValorDispoblePorDefecto(true);
					for (ShvParamOrganismo organismo : listaOrganismos) {
						if (organismo.getIdOrganismo().equals(dto.getCodOrganismo())){
							dto.setCodCliente(organismo.getNumeroClienteAsociado());
							break;
						}
					}
					// TeamComercial
					EntradaTeamComercialClienteWS entrada= new EntradaTeamComercialClienteWS(dto.getCodCliente());
					SalidaTeamComercialClienteWS salida = clienteDeltaServicio.consultarTeamComercialCliente(entrada);
					if (dto.getIdSegmento().equals(Constantes.SEGMENTO_WHOLE_SALE)){
						dto.setIdAnalista((salida.getAsistenteDeCobranzas() != null)?salida.getAsistenteDeCobranzas().getLegajo():null);
					} else if (dto.getIdSegmento().equals(Constantes.SEGMENTO_GRANDES_CLIENTES)) {
						dto.setIdAnalista((salida.getAsistenteDatos() != null)?salida.getAsistenteDatos().getLegajo():null);
					} else {
						dto.setIdAnalista(null);
					}
					mapearOrigen(interdeposito.getIdOrigen(), dto,true);
				} catch (Exception e) {
					dto.setErrorMapeo(true);
				} finally {
					listaDto.add(dto);
				}
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
		return listaDto;
	}

	private void mapearOrigen(Long idOrigen, ValorDto dto, boolean avisoDePago) throws PersistenciaExcepcion, NegocioExcepcion {
		ShvParamOrigen origen;
		if (avisoDePago){
			origen = origenDao.buscarOrigen(ID_ORIGEN_DEFAULT);
		} else {
			origen = origenDao.buscarOrigen(String.valueOf(idOrigen));
		}
		if (origen != null){
			dto.setIdOrigen(String.valueOf(idOrigen));
		} else {
			throw new NegocioExcepcion(TRAZA_ORIGEN_INVALIDO);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ArchivoAVCDto> mapeoArchivoAvc(Map<Long,List<RegistroAVCDto>> mapArchivos) throws NegocioExcepcion{
		try {
			List<ShvMigRegAvcArchivos> listaArchivos = (List<ShvMigRegAvcArchivos>) genericoDao.listar(ShvMigRegAvcArchivos.class);
			List<ArchivoAVCDto> listaArchivosDto = new ArrayList<ArchivoAVCDto>();
			for (ShvMigRegAvcArchivos archivo : listaArchivos) {
				ArchivoAVCDto archivoDto = new ArchivoAVCDto();
				archivoDto.setId(archivo.getIdArchivo());
				archivoDto.setNombreArchivo(archivo.getNombreArchivo());
				archivoDto.setUsuarioProcesamiento(archivo.getUsuarioProcesamiento());
				archivoDto.setFechaProcesamiento(archivo.getFechaProcesamiento());
				archivoDto.setNroAcuerdo(String.valueOf(archivo.getNumeroAcuerdo()));
				archivoDto.setLogProcesamiento(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.archivo.procesado.correctamente"));
				mapArchivos.put(archivo.getIdArchivo(), new ArrayList<RegistroAVCDto>());
				listaArchivosDto.add(archivoDto);
			}
			return listaArchivosDto;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> mapeoRegistroAvc(Map<Long,List<RegistroAVCDto>> mapArchivos, int contTransferenciaError, int contChequeError, int contChequePdError, int contEfectivoError, int contTotalError) throws NegocioExcepcion{
		try {
 			List<ShvMigRegAvcCheque> listaCheque = (List<ShvMigRegAvcCheque>) genericoDao.listar(ShvMigRegAvcCheque.class);
			List<ShvMigRegAvcChequeDiferido> listaChequeDiferido = (List<ShvMigRegAvcChequeDiferido>) genericoDao.listar(ShvMigRegAvcChequeDiferido.class);
			List<ShvMigRegAvcDepositoEfectivo> listaDepositoEfectivo = (List<ShvMigRegAvcDepositoEfectivo>) genericoDao.listar(ShvMigRegAvcDepositoEfectivo.class);
			List<ShvMigRegAvcTransferencia> listaTransferencia = (List<ShvMigRegAvcTransferencia>) genericoDao.listar(ShvMigRegAvcTransferencia.class);
			
			for (ShvMigRegAvcTransferencia transferencia : listaTransferencia) {
				TransferenciaDto transDto = mapeoAvcTransferencia(transferencia);
				if (mapArchivos.containsKey(transferencia.getIdArchivo()) && !transDto.isErrorMapeo()){
					mapArchivos.get(transferencia.getIdArchivo()).add(transDto);
				} else {
					if(transDto.isErrorMapeo()){
						Traza.error(getClass(), REGISTRO_AVC+TRAZA_ID+transferencia.getIdRegAvcTransferencia()+TRAZA_SEPARADOR+TRAZA_DATOS_INVALIDOS);
					} else {
						Traza.error(getClass(), REGISTRO_AVC+TRAZA_ID+transferencia.getIdRegAvcTransferencia()+TRAZA_SEPARADOR+TRAZA_ID_ARCHIVO_INVALIDO);
					}
					contTotalError++;
					contTransferenciaError++;
					genericoDao.insertarEnTabla(TABLA_ERROR_AVC_TRANSFERENCIA,ShvMigRegAvcTransferencia.class, transferencia);
				}
			}
			for (ShvMigRegAvcDepositoEfectivo efectivo : listaDepositoEfectivo) {
				DepositoDto efectivoDto = mapeoAvcEfectivo(efectivo);
				if (mapArchivos.containsKey(efectivo.getIdArchivo())){
					mapArchivos.get(efectivo.getIdArchivo()).add(efectivoDto);
				} else {
					if(efectivoDto.isErrorMapeo()){
						Traza.error(getClass(), TRAZA_ID+efectivo.getIdRegAvcDepositoEfectivo()+TRAZA_SEPARADOR+TRAZA_DATOS_INVALIDOS);
					} else {
						Traza.error(getClass(), TRAZA_ID+efectivo.getIdRegAvcDepositoEfectivo()+TRAZA_SEPARADOR+TRAZA_ID_ARCHIVO_INVALIDO);
					}
					contTotalError++;
					contEfectivoError++;
					genericoDao.insertarEnTabla(TABLA_ERROR_AVC_EFECTIVO,ShvMigRegAvcDepositoEfectivo.class, efectivo);
				}
			}
			for (ShvMigRegAvcChequeDiferido chequePD : listaChequeDiferido) {
				DepositoDto chequePDDto = mapeoAvcChequePD(chequePD);
				
				if (mapArchivos.containsKey(chequePD.getIdArchivo())){
					mapArchivos.get(chequePD.getIdArchivo()).add(chequePDDto);
				} else{
					if(chequePDDto.isErrorMapeo()){
						Traza.error(getClass(), TRAZA_ID+chequePD.getIdRegAvcChequeDiferido()+TRAZA_SEPARADOR+TRAZA_DATOS_INVALIDOS);
					} else {
						Traza.error(getClass(), TRAZA_ID+chequePD.getIdRegAvcChequeDiferido()+TRAZA_SEPARADOR+TRAZA_ID_ARCHIVO_INVALIDO);
					}
					contTotalError++;
					contChequePdError++;
					genericoDao.insertarEnTabla(TABLA_ERROR_AVC_CHEQUE_DIFERIDO,ShvMigRegAvcChequeDiferido.class, chequePD);
				}
			}
			for (ShvMigRegAvcCheque cheque : listaCheque) {
				DepositoDto chequeDto = mapeoAvcCheque(cheque);
				if (mapArchivos.containsKey(cheque.getIdArchivo())){
					mapArchivos.get(cheque.getIdArchivo()).add(chequeDto);
				} else {
					if(chequeDto.isErrorMapeo()){
						Traza.error(getClass(), TRAZA_ID+cheque.getIdRegAvcCheque()+TRAZA_SEPARADOR+TRAZA_DATOS_INVALIDOS);
					} else {
						Traza.error(getClass(), TRAZA_ID+cheque.getIdRegAvcCheque()+TRAZA_SEPARADOR+TRAZA_ID_ARCHIVO_INVALIDO);
					}
					contTotalError++;
					contChequeError++;
					genericoDao.insertarEnTabla(TABLA_ERROR_AVC_CHEQUE,ShvMigRegAvcCheque.class, cheque);
				}
			}
			List<Integer> listaContadores = new ArrayList<Integer>(); 
			listaContadores.add(contTotalError);
			listaContadores.add(contTransferenciaError);
			listaContadores.add(contEfectivoError);
			listaContadores.add(contChequePdError);
			listaContadores.add(contChequeError);
			return listaContadores;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	private DepositoDto mapeoAvcChequePD(ShvMigRegAvcChequeDiferido chequePD) throws NegocioExcepcion {
		DepositoDto chequePDDto = new DepositoDto();
		try {
			chequePDDto.setId(chequePD.getIdRegAvcChequeDiferido());
			chequePDDto.setImporte(chequePD.getImporte());
			chequePDDto.setAcuerdo(String.valueOf(chequePD.getNumeroAcuerdo()));
			chequePDDto.setCodigoCliente(String.valueOf(chequePD.getCodigoCliente()));
			chequePDDto.setIdRecInstrumento(chequePD.getIdRecInstrumento());
			chequePDDto.setSucursalDeposito(chequePD.getSucursalDeposito());
			chequePDDto.setRend(chequePD.getRend());
			chequePDDto.setFechaPago(chequePD.getFechaDePago());
			chequePDDto.setFormaPago(chequePD.getFormaPago());
			chequePDDto.setEstadoAcreditacion(chequePD.getEstadoAcreditacion());
			chequePDDto.setFechaAcreditacion(chequePD.getFechaAcreditacion());
			chequePDDto.setNroBoleta(chequePD.getNumeroBoleta());
			chequePDDto.setGrupoAcreedor(chequePD.getGrupoAcreedor());
			chequePDDto.setNombreCliente(chequePD.getNombreCliente());
			chequePDDto.setCodigoRechazo(chequePD.getCodigoRechazo());
			chequePDDto.setFechaVencimiento(chequePD.getFechaVencimiento());
			chequePDDto.setTipoValor(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValorString());
			separarDatosBanco(chequePDDto, chequePD.getDatosBancoCuentaCheque());
		} catch (Exception e) {
			chequePDDto.setErrorMapeo(true);
		}
		return chequePDDto;
	}

	private DepositoDto mapeoAvcCheque(ShvMigRegAvcCheque cheque) throws NegocioExcepcion {
		DepositoDto chequeDto = new DepositoDto();
		try {
			chequeDto.setId(cheque.getIdRegAvcCheque());
			chequeDto.setImporte(cheque.getImporte());
			chequeDto.setAcuerdo(String.valueOf(cheque.getNumeroAcuerdo()));
			chequeDto.setCodigoCliente(String.valueOf(cheque.getCodigoCliente()));
			chequeDto.setIdRecInstrumento(cheque.getIdRecInstrumento());
			chequeDto.setSucursalDeposito(cheque.getSucursalDeposito());
			chequeDto.setRend(cheque.getRend());
			chequeDto.setFechaPago(cheque.getFechaDePago());
			chequeDto.setFormaPago(cheque.getFormaPago());
			chequeDto.setEstadoAcreditacion(cheque.getEstadoAcreditacion());
			chequeDto.setFechaAcreditacion(cheque.getFechaAcreditacion());
			chequeDto.setNroBoleta(cheque.getNumeroBoleta());
			chequeDto.setGrupoAcreedor(cheque.getGrupoAcreedor());
			chequeDto.setNombreCliente(cheque.getNombreCliente());
			chequeDto.setCodigoRechazo(cheque.getCodigoRechazo());
			chequeDto.setTipoValor(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValorString());
			separarDatosBanco(chequeDto, cheque.getDatosBancoCuentaCheque());
		} catch (Exception e) {
			chequeDto.setErrorMapeo(true);
		}
		return chequeDto;
	}

	private TransferenciaDto mapeoAvcTransferencia(ShvMigRegAvcTransferencia transferencia) {
		TransferenciaDto transDto = new TransferenciaDto();
		try {
			transDto.setId(transferencia.getIdRegAvcTransferencia());
			transDto.setImporte(transferencia.getImporte());
			transDto.setAcuerdo(String.valueOf(transferencia.getNumeroAcuerdo()));
			transDto.setCodigoCliente(String.valueOf(transferencia.getCodigoCliente()));
			transDto.setFechaIngreso(transferencia.getFechaIngreso());
			transDto.setSucursal(transferencia.getSucursal());
			transDto.setReferencia(transferencia.getReferencia());
			transDto.setCodigo(transferencia.getCodigo());
			transDto.setObservacion(transferencia.getObservaciones());
			transDto.setTipoValor(TipoValorEnum.TRANSFERENCIA.getIdTipoValorString());
		} catch (Exception e) {
			transDto.setErrorMapeo(true);
		}
		return transDto;
	}

	private DepositoDto mapeoAvcEfectivo(ShvMigRegAvcDepositoEfectivo efectivo) {
		DepositoDto efectivoDto = new DepositoDto();
		try {
			efectivoDto.setId(efectivo.getIdRegAvcDepositoEfectivo());
			efectivoDto.setImporte(efectivo.getImporte());
			efectivoDto.setAcuerdo(String.valueOf(efectivo.getNumeroAcuerdo()));
			efectivoDto.setCodigoCliente(String.valueOf(efectivo.getCodigoCliente()));
			efectivoDto.setIdRecInstrumento(efectivo.getIdRecInstrumento());
			efectivoDto.setSucursalDeposito(efectivo.getSucursalDeposito());
			efectivoDto.setRend(efectivo.getRend());
			efectivoDto.setFechaPago(efectivo.getFechaDePago());
			efectivoDto.setFormaPago(efectivo.getFormaPago());
			efectivoDto.setEstadoAcreditacion(efectivo.getEstadoAcreditacion());
			efectivoDto.setFechaAcreditacion(efectivo.getFechaAcreditacion());
			efectivoDto.setNroBoleta(efectivo.getNumeroBoleta());
			efectivoDto.setGrupoAcreedor(efectivo.getGrupoAcreedor());
			efectivoDto.setNombreCliente(efectivo.getNombreCliente());
			efectivoDto.setCodigoRechazo(efectivo.getCodigoRechazo());
			efectivoDto.setTipoValor(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValorString());
		} catch (Exception e) {
			efectivoDto.setErrorMapeo(true);
		}
		return efectivoDto;
	}
	
	public void separarDatosBanco(DepositoDto depositoDto, String datosConcatenados) throws NegocioExcepcion{
 		String[] datosCheque = datosConcatenados.split("-");
 		if(datosCheque.length==5){ 
    		if(Validaciones.isNumeric(datosCheque[0])){
    			if(String.valueOf(datosCheque[0]).length()==3){
    				depositoDto.setCodigoBanco(Long.valueOf(datosCheque[0]));
    			}else{
    				throw new NegocioExcepcion(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoBancoCantidadIncorrecto"), datosCheque[0]));
    			}
    		 }else{
    			 throw new NegocioExcepcion(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoBancoNulo"), datosCheque[0]));
    		 }
    		 
    		 if(Validaciones.isNumeric(datosCheque[1])){
    			 if(String.valueOf(datosCheque[1]).length()==3){
    				 depositoDto.setSucursal(Long.valueOf(datosCheque[1]));		 
    			 }else{
    				 throw new NegocioExcepcion(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.SucursalIncorrecto"), datosCheque[1]));
    			 }
    		 }else{
    			 throw new NegocioExcepcion(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.SucursalNulo"), datosCheque[1]));
    		 }
    		 
    		 if(Validaciones.isNumeric(datosCheque[2])){
    			 if(String.valueOf(datosCheque[2]).length()==4){
    				 depositoDto.setCodigoPostal(Long.valueOf(datosCheque[2]));
    			 }else{
    				 throw new NegocioExcepcion(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoPostalIncorrecto"), datosCheque[2]));
    			 }
    		 }else{
    			 throw new NegocioExcepcion(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoPostalNulo"), datosCheque[2]));
    		 }	
    		 
    		 if(Validaciones.isNumeric(datosCheque[3])){
    			 if(String.valueOf(datosCheque[3]).length()==8){
    				 depositoDto.setNumeroCheque(Long.valueOf(datosCheque[3]));
    			 }else{
    				 throw new NegocioExcepcion(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.NumeroChequeIncorrecto"), datosCheque[3]));
    			 }
    		 }else{
    			 throw new NegocioExcepcion(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.NumeroChequeNulo"), datosCheque[3]));
    		 }
    		 
    		 if(Validaciones.isNumeric(datosCheque[4])){
    			 if(String.valueOf(datosCheque[4]).length()==11){
    				 depositoDto.setCuentaEmisora(Long.valueOf(datosCheque[4]));
    			 }else{
    				 throw new NegocioExcepcion(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CuentaEmisoraIncorrecto"), datosCheque[4]));
    			 }
    		 }else{
    			 throw new NegocioExcepcion(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CuentaEmisoraNulo"), datosCheque[4]));
    		 }
 		} else {
 			throw new NegocioExcepcion(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.DatosChequeCantidadIncorrecta"), datosCheque[4]));
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
	 * @return the valorServicio
	 */
	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	/**
	 * @param valorServicio the valorServicio to set
	 */
	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}

	/**
	 * @return the clienteSiebelServicio
	 */
	public IClienteSiebelServicio getClienteSiebelServicio() {
		return clienteSiebelServicio;
	}

	/**
	 * @param clienteSiebelServicio the clienteSiebelServicio to set
	 */
	public void setClienteSiebelServicio(
			IClienteSiebelServicio clienteSiebelServicio) {
		this.clienteSiebelServicio = clienteSiebelServicio;
	}

	/**
	 * @return the parametroServicio
	 */
	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	/**
	 * @param parametroServicio the parametroServicio to set
	 */
	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	/**
	 * @return the organismoDao
	 */
	public IOrganismoDao getOrganismoDao() {
		return organismoDao;
	}

	/**
	 * @param organismoDao the organismoDao to set
	 */
	public void setOrganismoDao(IOrganismoDao organismoDao) {
		this.organismoDao = organismoDao;
	}

	/**
	 * @return the clienteDeltaServicio
	 */
	public IClienteDeltaServicio getClienteDeltaServicio() {
		return clienteDeltaServicio;
	}

	/**
	 * @param clienteDeltaServicio the clienteDeltaServicio to set
	 */
	public void setClienteDeltaServicio(IClienteDeltaServicio clienteDeltaServicio) {
		this.clienteDeltaServicio = clienteDeltaServicio;
	}

	public IBoletaSinValorServicio getBoletaServicio() {
		return boletaServicio;
	}

	public void setBoletaServicio(IBoletaSinValorServicio boletaServicio) {
		this.boletaServicio = boletaServicio;
	}

	public IArchivoAVCServicio getArchivoAvcServicio() {
		return archivoAvcServicio;
	}

	public void setArchivoAvcServicio(IArchivoAVCServicio archivoAvcServicio) {
		this.archivoAvcServicio = archivoAvcServicio;
	}

	public IValorMedioPagoServicio getValorMedioPagoServicio() {
		return valorMedioPagoServicio;
	}

	public void setValorMedioPagoServicio(
			IValorMedioPagoServicio valorMedioPagoServicio) {
		this.valorMedioPagoServicio = valorMedioPagoServicio;
	}

	public IContabilidadDao getContabilidadDao() {
		return contabilidadDao;
	}

	public void setContabilidadDao(IContabilidadDao contabilidadDao) {
		this.contabilidadDao = contabilidadDao;
	}

	public IBancoDao getBancoDao() {
		return bancoDao;
	}

	public void setBancoDao(IBancoDao bancoDao) {
		this.bancoDao = bancoDao;
	}

	public IOrigenDao getOrigenDao() {
		return origenDao;
	}

	public void setOrigenDao(IOrigenDao origenDao) {
		this.origenDao = origenDao;
	}
	
	
}
