package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.EstadoChequeRechazadoDetalleCobroEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ILegajoChequeRechazadoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazadoDetalleCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjEnvioReversionesIce;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjLegajoChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjChequeRechazadoDetalleCobroSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjChequeRechazadoDetalleDocumentoSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjLegajoChequeRechazadoSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjLegajoChequeRechazadoSimplificadoWorkFlow;
import ar.com.telecom.shiva.persistencia.repository.DocumentoAdjuntoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.LegajoAdjuntoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.LegajoChequeRechazadoDetCobroRepositorio;
import ar.com.telecom.shiva.persistencia.repository.LegajoChequeRechazadoDetCobroSimplificadoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.LegajoChequeRechazadoDetDocumentoSimplificadoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.LegajoChequeRechazadoEnvioReversionesIceRepositorio;
import ar.com.telecom.shiva.persistencia.repository.LegajoChequeRechazadoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.LegajoChequeRechazadoSimplificadoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.LegajoChequeRechazadoSimplificadoWorkFlowRepositorio;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoFiltro;

public class LegajoChequeRechazadoDaoImpl extends Dao implements ILegajoChequeRechazadoDao {
		
	@Autowired LegajoChequeRechazadoRepositorio legajoChequeRechazadoRepositorio;
	
	@Autowired LegajoChequeRechazadoSimplificadoRepositorio legajoChequeRechazadoSimplificadoRepositorio;
	
	@Autowired LegajoChequeRechazadoSimplificadoWorkFlowRepositorio legajoChequeRechazadoSimplificadoWorkFlowRepositorio;
	
	@Autowired LegajoAdjuntoRepositorio legajoAdjuntoRepositorio;

	@Autowired DocumentoAdjuntoRepositorio documentoAdjuntoRepositorio;
	
	@Autowired LegajoChequeRechazadoDetCobroSimplificadoRepositorio legajoChequeRechazadoDetCobroSimplRepositorio;
	
	@Autowired LegajoChequeRechazadoDetCobroRepositorio legajoChequeRechazadoDetCobroRepositorio;
	
	@Autowired LegajoChequeRechazadoEnvioReversionesIceRepositorio legajoChequeRechazadoEnvioReversionesIceRepositorio;
	
	@Autowired LegajoChequeRechazadoDetDocumentoSimplificadoRepositorio legajoChequeRechazadoDetDocumentoSimplificadoRepositorio;
	
	@Override
	public ShvLgjLegajoChequeRechazado buscar(Object id) throws PersistenciaExcepcion{
		
		return legajoChequeRechazadoRepositorio.findOne((Long)id);
	}
	@Override
	public ShvLgjLegajoChequeRechazadoSimplificadoWorkFlow buscarSimplificadoConWorkFlow(Object id) throws PersistenciaExcepcion {
		
		return legajoChequeRechazadoSimplificadoWorkFlowRepositorio.findOne((Long)id);
	}
	@Override
	public List<ShvLgjLegajoChequeRechazado> listar(VistaSoporteLegajoChequeRechazadoFiltro filtro) throws PersistenciaExcepcion{
		return null;
	}
	
	@Override
	public ShvLgjLegajoChequeRechazado crear(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws PersistenciaExcepcion {
		
		ShvLgjLegajoChequeRechazado legajoChequeRechazadoBD = legajoChequeRechazadoRepositorio.save(legajoChequeRechazado);
		legajoChequeRechazadoRepositorio.flush();
		return legajoChequeRechazadoBD;
	}

	@Override
	public Long validarUnicidadDatosModificadosNoExisteEnOtroLegajo(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws PersistenciaExcepcion {
		
		Long idLegajoDuplicado = null;

		StringBuffer consulta = new StringBuffer();
		
		consulta.append("select id_legajo_cheque_rechazado ");
		consulta.append("from shv_lgj_legajo_cheque_rchazado sllcr ");
		consulta.append("left join shv_wf_workflow_estado swwe ");
		consulta.append("on sllcr.id_workflow = swwe.id_workflow ");
		consulta.append("left join shv_lgj_cheque_rechazado slcr ");
		consulta.append("on sllcr.id_legajo_cheque_rechazado = slcr.id_cheque_rechazado ");
		consulta.append("where swwe.estado <> ? ");
		consulta.append("and sllcr.id_banco_origen = ? ");
		consulta.append("and sllcr.numero_cheque = ? ");
		consulta.append("and sllcr.importe = ? ");
		consulta.append("and to_date(to_char(sllcr.fecha_deposito, 'dd/mm/yyyy'), ?) = to_date(?, ?) ");
	
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		// Agrego el estado por el cual dejamos legajos afuera de la busqueda
		parametros.addCampoAlQuery(Estado.LGJ_ANULADO.name(), Types.VARCHAR);
		
		// Agrego el banco origen
		parametros.addCampoAlQuery(legajoChequeRechazado.getBancoOrigen().getIdBanco(), Types.VARCHAR);
		
		// Agrego el numero de cheque
		parametros.addCampoAlQuery(legajoChequeRechazado.getNumeroCheque(), Types.VARCHAR);

		// Agrego el importe
		parametros.addCampoAlQuery(legajoChequeRechazado.getImporte(), Types.DECIMAL);
		
		// Formato to_date de fecha de deposito de la base
		parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		
		// Fecha deposito ingresada y su formato
		parametros.addCampoAlQuery(Utilidad.formatDatePicker(legajoChequeRechazado.getFechaDeposito()) + " 00:00:00", Types.VARCHAR);
		parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		
		// si la fecha de vencimiento viene informada, la tengo en cuenta en la búsqueda del legajo
		if (!Validaciones.isObjectNull(legajoChequeRechazado.getFechaVencimiento())) {
			consulta.append("and to_date(to_char(sllcr.fecha_vencimiento, 'dd/mm/yyyy'), ?) = to_date(?, ?) ");
	
			// Formato to_date de fecha de vencimiento de la base
			parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
			
			// Fecha vencimiento ingresada y su formato
			parametros.addCampoAlQuery(Utilidad.formatDatePicker(legajoChequeRechazado.getFechaVencimiento()) + " 00:00:00", Types.VARCHAR);
			parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		}
		
		parametros.setSql(consulta.toString());
		
		List<Map<String,Object>> listaLegajos;
		listaLegajos = buscarUsandoSQL(parametros);
		
		for (Map<String, Object> archivo : listaLegajos) {
			if (!Validaciones.isObjectNull(archivo.get("id_legajo_cheque_rechazado"))){
					idLegajoDuplicado = new Long(archivo.get("id_legajo_cheque_rechazado").toString());
				}
		}
		
		return idLegajoDuplicado;
	}	

	@Override
	public Long validarUnicidadChequeAsociadoShivaNoExistaEnOtroLegajo(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws PersistenciaExcepcion {
		
		Long idLegajoDuplicado = null;

		StringBuffer consulta = new StringBuffer();
		
		consulta.append("select id_legajo_cheque_rechazado ");
		consulta.append("from shv_lgj_legajo_cheque_rchazado sllcr ");
		consulta.append("left join shv_wf_workflow_estado swwe ");
		consulta.append("on sllcr.id_workflow = swwe.id_workflow ");
		consulta.append("left join shv_lgj_cheque_rechazado slcr ");
		consulta.append("on sllcr.id_cheque_rechazado = slcr.id_cheque_rechazado ");
		consulta.append("where swwe.estado <> ? ");
		consulta.append("and slcr.id_valor = ? ");

		QueryParametrosUtil parametros = new QueryParametrosUtil();
		parametros.addCampoAlQuery(Estado.LGJ_ANULADO.name(), Types.VARCHAR);
		parametros.addCampoAlQuery(legajoChequeRechazado.getChequeRechazado().getIdValor(), Types.NUMERIC);

		parametros.setSql(consulta.toString());
		
		List<Map<String,Object>> listaLegajos;
		listaLegajos = buscarUsandoSQL(parametros);
		
		for (Map<String, Object> archivo : listaLegajos) {
			if (!Validaciones.isObjectNull(archivo.get("id_legajo_cheque_rechazado"))){
					idLegajoDuplicado = new Long(archivo.get("id_legajo_cheque_rechazado").toString());
				}
		}
		
		return idLegajoDuplicado;
	}
	
	@Override
	public Long validarUnicidadChequeAsociadoIceNoExistaEnOtroLegajo(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws PersistenciaExcepcion {
		
		Long idLegajoDuplicado = null;

		StringBuffer consulta = new StringBuffer();
		
		consulta.append("select id_legajo_cheque_rechazado ");
		consulta.append("from shv_lgj_legajo_cheque_rchazado sllcr ");
		consulta.append("left join shv_wf_workflow_estado swwe ");
		consulta.append("on sllcr.id_workflow = swwe.id_workflow ");
		consulta.append("left join shv_lgj_cheque_rechazado slcr ");
		consulta.append("on sllcr.id_cheque_rechazado = slcr.id_cheque_rechazado ");
		consulta.append("where swwe.estado <> ? ");
		
		consulta.append("and slcr.id_banco_origen = ? ");
		consulta.append("and slcr.numero_cheque = ? ");
		consulta.append("and to_date(to_char(slcr.fecha_deposito, 'dd/mm/yyyy'), ?) = to_date(?, ?) ");
		consulta.append("and slcr.importe = ? ");
		consulta.append("and slcr.moneda = ? ");
		consulta.append("and slcr.sistema_origen = ? ");
		
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		
		// Estado
		parametros.addCampoAlQuery(Estado.LGJ_ANULADO.name(), Types.VARCHAR);

		// Banco Origen
		parametros.addCampoAlQuery(legajoChequeRechazado.getChequeRechazado().getBancoOrigen().getIdBanco(), Types.VARCHAR);
		
		// Numero de Cheque
		parametros.addCampoAlQuery(legajoChequeRechazado.getChequeRechazado().getNumeroCheque(), Types.VARCHAR);

		// Formato to_date de fecha de deposito de la base
		parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		
		// Fecha deposito ingresada y su formato (la fecha deposito es la fecha de imputación de cobro)
		parametros.addCampoAlQuery(Utilidad.formatDatePicker(legajoChequeRechazado.getChequeRechazado().getFechaDeposito()) + " 00:00:00", Types.VARCHAR);
		parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		
		// Importe
		parametros.addCampoAlQuery(legajoChequeRechazado.getChequeRechazado().getImporte(), Types.DECIMAL);
		
		// Moneda
		parametros.addCampoAlQuery(legajoChequeRechazado.getChequeRechazado().getMoneda(), Types.VARCHAR);
		
		// Sistema Origen
		parametros.addCampoAlQuery(legajoChequeRechazado.getChequeRechazado().getSistemaOrigen().name(), Types.VARCHAR);

		parametros.setSql(consulta.toString());
		
		List<Map<String,Object>> listaLegajos;
		listaLegajos = buscarUsandoSQL(parametros);
		
		for (Map<String, Object> archivo : listaLegajos) {
			if (!Validaciones.isObjectNull(archivo.get("id_legajo_cheque_rechazado"))) {
				idLegajoDuplicado = new Long(archivo.get("id_legajo_cheque_rechazado").toString());
			}
		}

		return idLegajoDuplicado;
	}
	public List<Bean> listarBusqueda(Filtro filtro) throws NegocioExcepcion {
		// TODO Consulta a la Vista Sql de FER!!!!!
		return new ArrayList<Bean>();
	}
	
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjLegajoChequeRechazado modificar(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws PersistenciaExcepcion {
		
		ShvLgjLegajoChequeRechazado legajoChequeRechazadoBD = legajoChequeRechazadoRepositorio.save(legajoChequeRechazado);
		legajoChequeRechazadoRepositorio.flush();

		return legajoChequeRechazadoBD;
	}
	
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjLegajoChequeRechazadoSimplificado modificar(ShvLgjLegajoChequeRechazadoSimplificado legajoChequeRechazado) throws PersistenciaExcepcion {
		
		ShvLgjLegajoChequeRechazadoSimplificado legajoChequeRechazadoBD = legajoChequeRechazadoSimplificadoRepositorio.save(legajoChequeRechazado);
		legajoChequeRechazadoSimplificadoRepositorio.flush();

		return legajoChequeRechazadoBD;
	}

	@Override
	public ShvLgjChequeRechazadoDetalleCobroSimplificado modificarCobrosRelacionados(ShvLgjChequeRechazadoDetalleCobroSimplificado detCobro) throws PersistenciaExcepcion {
		ShvLgjChequeRechazadoDetalleCobroSimplificado detCobroBD = legajoChequeRechazadoDetCobroSimplRepositorio.save(detCobro);
		legajoChequeRechazadoDetCobroSimplRepositorio.flush();
		return detCobroBD;
		
	}

	@Override
	public List<ShvLgjChequeRechazadoDetalleCobroSimplificado> listarDetCobroSimplificado(List<Long> listaIdDetCobro) throws PersistenciaExcepcion {
		
		List<ShvLgjChequeRechazadoDetalleCobroSimplificado> listaDetCobroSimplificado = new ArrayList<ShvLgjChequeRechazadoDetalleCobroSimplificado>();

		String consulta = "select * from shv_lgj_cheque_recha_det_cobro where id_cheque_rechazado_cobro in ( ";
		
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		for (Long id : listaIdDetCobro) {
			parametros.addCampoAlQuery(id, Types.NUMERIC);
			consulta += "?, ";
		}
		consulta = consulta.substring(0, consulta.length()-2);
		consulta +=" )";
		parametros.setSql(consulta);
		
		List<Map<String,Object>> listaDetCobroResultado;
		listaDetCobroResultado = buscarUsandoSQL(parametros);
		
		for (Map<String, Object> archivo : listaDetCobroResultado) {
			ShvLgjChequeRechazadoDetalleCobroSimplificado detCobroSimplificado = new ShvLgjChequeRechazadoDetalleCobroSimplificado();
			
			if (!Validaciones.isObjectNull(archivo.get("ID_CHEQUE_RECHAZADO_COBRO"))){
				detCobroSimplificado.setIdChequeRechazadoCobro(new Long(archivo.get("ID_CHEQUE_RECHAZADO_COBRO").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ID_CHEQUE_RECHAZADO"))){
				detCobroSimplificado.setIdChequeRechazado(new Long(archivo.get("ID_CHEQUE_RECHAZADO").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ESTADO"))){
				detCobroSimplificado.setEstado(EstadoChequeRechazadoDetalleCobroEnum.getEnumByName(archivo.get("ESTADO").toString()));
			}

			if (!Validaciones.isObjectNull(archivo.get("CODIGO_BANCO_EMISOR_CHEQUE"))){
				detCobroSimplificado.setCodigoBancoEmisorCheque(archivo.get("CODIGO_BANCO_EMISOR_CHEQUE").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ID_CLIENTE_LEGADO"))){
				detCobroSimplificado.setIdClienteLegado(new Long(archivo.get("ID_CLIENTE_LEGADO").toString()));
			}
			if (!Validaciones.isObjectNull(archivo.get("RAZON_SOCIAL"))){
				detCobroSimplificado.setRazonSocial(archivo.get("RAZON_SOCIAL").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("COBRO_REVERTIDO"))){
				detCobroSimplificado.setCobroRevertido(SiNoEnum.getEnumByName(archivo.get("COBRO_REVERTIDO").toString())); 
			}

			if (!Validaciones.isObjectNull(archivo.get("CODIGO_BANCO_COBRO"))){
				detCobroSimplificado.setCodigoBancoCobro(archivo.get("CODIGO_BANCO_COBRO").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("CODIGO_TAREA"))){
				detCobroSimplificado.setCodigoDeTarea(archivo.get("CODIGO_TAREA").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("CODIGO_EMPRESA_RECEPTORA"))){
				detCobroSimplificado.setCodigoEmpresaReceptora(archivo.get("CODIGO_EMPRESA_RECEPTORA").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("CODIGO_ENTIDAD_GESTORA"))){
				detCobroSimplificado.setCodigoEntidadGestora(archivo.get("CODIGO_ENTIDAD_GESTORA").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("CODIGO_POSTAL_SUCURSAL"))){
				detCobroSimplificado.setCodigoPostalSucursal(archivo.get("CODIGO_POSTAL_SUCURSAL").toString()); 
			}
			
			if (!Validaciones.isObjectNull(archivo.get("COMISION"))){
				detCobroSimplificado.setComision(new Long(archivo.get("COMISION").toString()));
			}

			if (!Validaciones.isObjectNull(archivo.get("CP_CLIENTE_CODIGO_BARRAS"))){
				detCobroSimplificado.setCpClienteCodigoBarras(archivo.get("CP_CLIENTE_CODIGO_BARRAS").toString()); 
			}

			if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_BANCO"))){
				detCobroSimplificado.setDescripcionBanco(archivo.get("DESCRIPCION_BANCO").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("FECHA_DE_ACREDITACION"))){
				detCobroSimplificado.setFechaDeAcreditacion((Date)archivo.get("FECHA_DE_ACREDITACION"));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("FECHA_DE_COBRO"))){
				detCobroSimplificado.setFechaDeCobro((Date)archivo.get("FECHA_DE_COBRO"));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("FECHA_DE_VCTO_ORIGINAL"))){
				detCobroSimplificado.setFechaDeVctoOriginal((Date)archivo.get("FECHA_DE_VCTO_ORIGINAL"));
			}

			if (!Validaciones.isObjectNull(archivo.get("FECHA_PETICION_REVERSION"))){
				detCobroSimplificado.setFechaPeticionReversion((Date)archivo.get("FECHA_PETICION_REVERSION"));
			}

			if (!Validaciones.isObjectNull(archivo.get("FORMA_DE_PAGO_DEL_CLIENTE"))){
				detCobroSimplificado.setFormaDePagoDelCliente(archivo.get("FORMA_DE_PAGO_DEL_CLIENTE").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("IDENTIFICADOR_DE_PAGO"))){
				detCobroSimplificado.setIdentificadorDePago(new Long(archivo.get("IDENTIFICADOR_DE_PAGO").toString()));
			}

			if (!Validaciones.isObjectNull(archivo.get("IMPORTE_ABSOLUTO"))){
				detCobroSimplificado.setImporteAbsoluto(new BigDecimal(archivo.get("IMPORTE_ABSOLUTO").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("IMPORTE_BONO"))){
				detCobroSimplificado.setImporteBono(new BigDecimal(archivo.get("IMPORTE_BONO").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("IMPORTE_CHEQUE"))){
				detCobroSimplificado.setImporteCheque(new BigDecimal(archivo.get("IMPORTE_CHEQUE").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("IMPORTE_COBRADO_OREVERSAR"))){
				detCobroSimplificado.setImporteCobradoOReversar(new BigDecimal(archivo.get("IMPORTE_COBRADO_OREVERSAR").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("IMPORTE_COMPROBANTE"))){
				detCobroSimplificado.setImporteComprobante(new BigDecimal(archivo.get("IMPORTE_COMPROBANTE").toString()));
			}

			if (!Validaciones.isObjectNull(archivo.get("IMPORTE_EFECTIVO"))){
				detCobroSimplificado.setImporteEfectivo(new BigDecimal(archivo.get("IMPORTE_EFECTIVO").toString())); 
			}
			
			if (!Validaciones.isObjectNull(archivo.get("IMPORTE_ORIGINAL"))){
				detCobroSimplificado.setImporteOriginal(new BigDecimal(archivo.get("IMPORTE_ORIGINAL").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("IMPORTE_OTRAS_MONEDAS"))){
				detCobroSimplificado.setImporteOtrasMonedas(new BigDecimal(archivo.get("IMPORTE_OTRAS_MONEDAS").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("MARCA_DE_AJUSTE"))){
				detCobroSimplificado.setMarcaDeAjuste(archivo.get("MARCA_DE_AJUSTE").toString()); 
			}

			if (!Validaciones.isObjectNull(archivo.get("MARCA_DEL_PAGO"))){
				detCobroSimplificado.setMarcaDePago(archivo.get("MARCA_DEL_PAGO").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("MONEDA"))){
				detCobroSimplificado.setMoneda(MonedaEnum.getEnumByName(archivo.get("MONEDA").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("NOMBRE_BANCO_DE_COBRO"))){
				detCobroSimplificado.setNombreBancoDeCobro(archivo.get("NOMBRE_BANCO_DE_COBRO").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("NOMBRE_ENTIDAD_GESTORA"))){
				detCobroSimplificado.setNombreEntidadGestora(archivo.get("NOMBRE_ENTIDAD_GESTORA").toString()); 
			}

			if (!Validaciones.isObjectNull(archivo.get("NUMERO_CHEQUE"))){
				detCobroSimplificado.setNumeroCheque(archivo.get("NUMERO_CHEQUE").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("NUMERO_CONVENIO"))){
				detCobroSimplificado.setNumeroConvenio(new Long(archivo.get("NUMERO_CONVENIO").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("NUMERO_CUOTA"))){
				detCobroSimplificado.setNumeroCuota(new Long(archivo.get("NUMERO_CUOTA").toString())); 
			}
			
			if (!Validaciones.isObjectNull(archivo.get("NUMERO_DE_AGENCIA"))){
				detCobroSimplificado.setNumeroDeAgencia(archivo.get("NUMERO_DE_AGENCIA").toString()); 
			}

			if (!Validaciones.isObjectNull(archivo.get("NUMERO_DE_COMPROBANTE"))){
				detCobroSimplificado.setNumeroDeComprobante(archivo.get("NUMERO_DE_COMPROBANTE").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("NUMERO_DE_SUCURSAL_COMPLETO"))){
				detCobroSimplificado.setNumeroDeSucursalCompleto(archivo.get("NUMERO_DE_SUCURSAL_COMPLETO").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("NUMERO_DE_TARJETA"))){
				detCobroSimplificado.setNumeroDeTarjeta(archivo.get("NUMERO_DE_TARJETA").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("NUMERO_LEGAL"))){
				detCobroSimplificado.setNumeroLegal(archivo.get("NUMERO_LEGAL").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("NUMERO_REFERENCIA_MIC"))){
				detCobroSimplificado.setNumeroReferenciaMic(archivo.get("NUMERO_REFERENCIA_MIC").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("PROVINCIA_DEL_COD_BARRAS"))){
				detCobroSimplificado.setProvinciaDelCodBarras(archivo.get("PROVINCIA_DEL_COD_BARRAS").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("RECIBO"))){
				detCobroSimplificado.setRecibo(archivo.get("RECIBO").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("REFERENCIA_DEL_COMPROBANTE"))){
				detCobroSimplificado.setReferenciaDelComprobante(archivo.get("REFERENCIA_DEL_COMPROBANTE").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("SUCURSAL_BANCO"))){
				detCobroSimplificado.setSucursalBanco(archivo.get("SUCURSAL_BANCO").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("TIPO_BONO"))){
				detCobroSimplificado.setTipoBono(archivo.get("TIPO_BONO").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("TIPO_CLIENTE_CODIGO_BARRAS"))){
				detCobroSimplificado.setTipoClienteCodigoBarras(archivo.get("TIPO_CLIENTE_CODIGO_BARRAS").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("TIPO_COMPROBANTE"))){
				detCobroSimplificado.setTipoComprobante(archivo.get("TIPO_COMPROBANTE").toString()); 
			}
			
			if (!Validaciones.isObjectNull(archivo.get("TIPO_DE_CAMBIO"))){
				detCobroSimplificado.setTipoDeCambio(new Long(archivo.get("TIPO_DE_CAMBIO").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("TIPO_DE_FACTURACION"))){
				detCobroSimplificado.setTipoDeFacturacion(archivo.get("TIPO_DE_FACTURACION").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("TIPO_DE_LECTURA"))){
				detCobroSimplificado.setTipoDeLectura(archivo.get("TIPO_DE_LECTURA").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("TIPO_DE_OPERACION"))){
				detCobroSimplificado.setTipoDeOperacion(archivo.get("TIPO_DE_OPERACION").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("TIPO_OTRAS_MONEDAS"))){
				detCobroSimplificado.setTipoOtrasMonedas(archivo.get("TIPO_OTRAS_MONEDAS").toString());
			}
			
			listaDetCobroSimplificado.add(detCobroSimplificado);
		}
		
		return listaDetCobroSimplificado;
	}

	@Override
	public void actualizarRegistrosDetalleCobroPorEnvioIce(List<ShvLgjChequeRechazadoDetalleCobro> listaShvMasRegistro) throws PersistenciaExcepcion {
		try{
			for (ShvLgjChequeRechazadoDetalleCobro detalleCobro : listaShvMasRegistro) {
				legajoChequeRechazadoDetCobroRepositorio.save(detalleCobro);
				legajoChequeRechazadoDetCobroRepositorio.flush();
			}
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvLgjChequeRechazadoDetalleCobro> listarDetalleCobrosPendientesDeEnviarIceOrdenadoPorBanco() throws PersistenciaExcepcion {
		try {
			String query = "from ShvLgjChequeRechazadoDetalleCobro where estado=? order by codigoBancoCobro";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(EstadoChequeRechazadoDetalleCobroEnum.PEND_ENVIAR_ICE);
			
			return (List<ShvLgjChequeRechazadoDetalleCobro>)buscarUsandoQueryConParametros(qp); 
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Guarda los adjuntos del legajo
	 */
	@Override
	public ShvLgjAdjunto insertarDocumentoAdjunto(ShvLgjAdjunto registroAdjunto) throws PersistenciaExcepcion {
		try {
			
			return legajoAdjuntoRepositorio.save(registroAdjunto);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public void eliminarDocumentoAdjuntoDelLegajo(ShvDocDocumentoAdjunto docAdjunto) throws PersistenciaExcepcion {

		List<ShvLgjAdjunto> lista = legajoAdjuntoRepositorio.buscarPorIdAdjuntoLegajos(docAdjunto.getIdValorAdjunto()); 
		
		if(Validaciones.isCollectionNotEmpty(lista)){
			ShvLgjAdjunto regAdjunto = lista.get(0);
			legajoAdjuntoRepositorio.delete(regAdjunto);
			legajoAdjuntoRepositorio.flush();
			documentoAdjuntoRepositorio.delete(docAdjunto);
			documentoAdjuntoRepositorio.flush();
		}
	}
	
	/**
	 * Lista los adjuntos del cobro online
	 */
	public List<ShvDocDocumentoAdjunto> buscarAdjuntosDelLegajo(Long idLegajo) throws PersistenciaExcepcion {
		try {
			List<ShvDocDocumentoAdjunto> list = new ArrayList<ShvDocDocumentoAdjunto>();
			
			List<ShvLgjAdjunto> lista = legajoAdjuntoRepositorio.buscarAdjuntosLegajos(idLegajo);  
			if(Validaciones.isCollectionNotEmpty(lista)){
				for (ShvLgjAdjunto legajoAdjunto : lista) {
					list.add(legajoAdjunto.getPk().getDocumentoAdjunto());
				}
			}
			return list;
	
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public ShvLgjEnvioReversionesIce grabarShvLgjEnvioReversionesIce(ShvLgjEnvioReversionesIce reversionIce)	throws PersistenciaExcepcion {
		try{
			reversionIce = legajoChequeRechazadoEnvioReversionesIceRepositorio.save(reversionIce);
			legajoChequeRechazadoEnvioReversionesIceRepositorio.flush();
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
		return reversionIce;
	}
	
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvLgjChequeRechazadoDetalleDocumentoSimplificado> listarDetalleDocumentoSimplificados() throws PersistenciaExcepcion {
		try {
			String query = "select docum from ShvLgjChequeRechazadoDetalleDocumentoSimplificado as docum "+
			", ShvLgjLegajoChequeRechazado as legajo "+
			"join legajo.workflow as w "+
			"join w.shvWfWorkflowEstado as we "+
			"join legajo.workflow.shvWfWorkflowEstado as we "+
			"where docum.idChequeRechazado=legajo.chequeRechazado.idChequeRechazado "+
			"and we.estado in (?,?)";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(Estado.LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_PEND_ENTREGA,Types.VARCHAR);
			qp.addCampoAlQuery(Estado.LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_ENTREGADO,Types.VARCHAR);
			
			return (List<ShvLgjChequeRechazadoDetalleDocumentoSimplificado>)buscarUsandoQueryConParametros(qp); 
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * 
	 * @param detalleDocumento
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@Override
	public ShvLgjChequeRechazadoDetalleDocumentoSimplificado actualizarChequeRechazadoDetalleDocumentoSimplificado(ShvLgjChequeRechazadoDetalleDocumentoSimplificado detalleDocumento)	throws PersistenciaExcepcion {
		try{
			detalleDocumento = legajoChequeRechazadoDetDocumentoSimplificadoRepositorio.save(detalleDocumento);
			legajoChequeRechazadoDetDocumentoSimplificadoRepositorio.flush();
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
		return detalleDocumento;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvLgjChequeRechazadoDetalleCobro> listarDetalleCobrosEnviadoAIcePorIdChequeRechazado(Long idChequeRechazado) throws PersistenciaExcepcion {
		try {
			String query = "from ShvLgjChequeRechazadoDetalleCobro as detcobro where detcobro.chequeRechazado.idChequeRechazado=? and detcobro.estado=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idChequeRechazado);
			qp.addParametro(EstadoChequeRechazadoDetalleCobroEnum.ENVIADO_ICE);
			
			return (List<ShvLgjChequeRechazadoDetalleCobro>)buscarUsandoQueryConParametros(qp); 
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}