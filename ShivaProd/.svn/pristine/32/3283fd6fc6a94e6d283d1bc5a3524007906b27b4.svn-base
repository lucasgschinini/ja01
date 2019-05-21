package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoValoresClientesPuros;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IImputacionValoresClientesPurosDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteImputacionAutomaticaValoresClientesPurosFiltro;


public class ImputacionValoresClientesPurosDaoImpl extends Dao implements IImputacionValoresClientesPurosDao{

	@Autowired
	private IParametroServicio parametroServicio;
	
	@Override
	public List<VistaSoporteResultadoValoresClientesPuros> buscarValores(VistaSoporteImputacionAutomaticaValoresClientesPurosFiltro filtro) throws PersistenciaExcepcion {

		try {
			QueryParametrosUtil qp = obtenerSQLValores(filtro);
			List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);
			
			List<VistaSoporteResultadoValoresClientesPuros> listaResultado = new ArrayList<VistaSoporteResultadoValoresClientesPuros>();
			
			if(Validaciones.isCollectionNotEmpty(listaResultadoQuery)){
				for (Map<String, Object> registro : listaResultadoQuery) {
					
					VistaSoporteResultadoValoresClientesPuros resultado = new VistaSoporteResultadoValoresClientesPuros();
		
					if (!Validaciones.isObjectNull(registro.get("ID_VALOR"))) {
						resultado.setIdValor(((BigDecimal) registro.get("ID_VALOR")).longValue());
					}
					if (!Validaciones.isObjectNull(registro.get("ID_CLIENTE_LEGADO"))) {
						resultado.setIdClienteLegado(((BigDecimal) registro.get("ID_CLIENTE_LEGADO")).longValue());
					}
					if (!Validaciones.isObjectNull(registro.get("NRO_VALOR"))) {
						resultado.setNroValor(registro.get("NRO_VALOR").toString());
					}
					if (!Validaciones.isObjectNull(registro.get("IMPORTE"))) {
						resultado.setImporte((BigDecimal) registro.get("IMPORTE"));
					}
					if (!Validaciones.isObjectNull(registro.get("FECHA_ALTA"))) {
						resultado.setFechaAlta(String.valueOf(registro.get("FECHA_ALTA")));
					}
					if (!Validaciones.isObjectNull(registro.get("ID_MOTIVO"))) {
						resultado.setIdMotivo(((BigDecimal) registro.get("ID_MOTIVO")).longValue());
					}
					if (!Validaciones.isObjectNull(registro.get("SALDO_DISPONIBLE"))) {
						resultado.setSaldoDisponible((BigDecimal) registro.get("SALDO_DISPONIBLE"));
					}
					if (!Validaciones.isObjectNull(registro.get("FECHA_INGRESO_RECIBO"))) {
						resultado.setFechaIngresoRecibo((Date) registro.get("FECHA_INGRESO_RECIBO"));
					}
					// Consulta servidor plnx0267 / plnx0268 / plnx0270 - Shiva
					
//					if (!Validaciones.isObjectNull(registro.get("FECHA_EMISION"))) {
//						resultado.setFechaEmision(registro.get("FECHA_EMISION").toString());
//					}
					if (!Validaciones.isObjectNull(registro.get("FECHA_EMISION"))) {
						resultado.setFechaEmision((Date) registro.get("FECHA_EMISION"));
					}
					
					if (!Validaciones.isObjectNull(registro.get("FECHA_TRANSFERENCIA"))) {
						resultado.setFechaTransferencia((Date) registro.get("FECHA_TRANSFERENCIA"));
					}
					if (!Validaciones.isObjectNull(registro.get("FECHA_DEPOSITO"))) {
						resultado.setFechaDeposito((Date) registro.get("FECHA_DEPOSITO"));
					}
					if (!Validaciones.isObjectNull(registro.get("FECHA_ULTIMO_ESTADO"))) {
						resultado.setFechaUltimoEstado((Date) registro.get("FECHA_ULTIMO_ESTADO"));
					}
					if (!Validaciones.isObjectNull(registro.get("FECHA_VENCIMIENTO"))) {
						resultado.setFechaVencimiento((Date) registro.get("FECHA_VENCIMIENTO"));
					}
					if (!Validaciones.isObjectNull(registro.get("ID_TIPO_RETENCION"))) {
						resultado.setIdTipoRetencion(((BigDecimal) registro.get("ID_TIPO_RETENCION")).longValue());
					}
					if (!Validaciones.isObjectNull(registro.get("NRO_CUIT_RETENCION"))) {
						resultado.setNroCuitRetencion(registro.get("NRO_CUIT_RETENCION").toString());
					}
					if (!Validaciones.isObjectNull(registro.get("PROVINCIA_RETENCION"))) {
						resultado.setProvinciaRetencion(registro.get("PROVINCIA_RETENCION").toString());
					}
					if (!Validaciones.isObjectNull(registro.get("REFERENCIA_VALOR"))) {
						resultado.setReferenciaValor(registro.get("REFERENCIA_VALOR").toString());
					}
					if (!Validaciones.isObjectNull(registro.get("ID_TIPO_VALOR"))) {
						resultado.setIdTipoValor(((BigDecimal) registro.get("ID_TIPO_VALOR")).longValue());
					}
					if (!Validaciones.isObjectNull(registro.get("ID_EMPRESA"))) {
						resultado.setIdEmpresa(registro.get("ID_EMPRESA").toString());
					}
					if (!Validaciones.isObjectNull(registro.get("ID_SEGMENTO"))) {
						resultado.setIdSegmento(registro.get("ID_SEGMENTO").toString());
					}
					if (!Validaciones.isObjectNull(registro.get("FECHA_VALOR"))) {
						resultado.setFechaValor((Date) registro.get("FECHA_VALOR"));
					}
					if (!Validaciones.isObjectNull(registro.get("ID_SEGMENTO_CLIENTE"))) {
						resultado.setIdSegmentoCliente(registro.get("ID_SEGMENTO_CLIENTE").toString());
					}
					if (!Validaciones.isObjectNull(registro.get("RAZON_SOCIAL_CLIENTE"))) {
						resultado.setRazonSocialCliente(registro.get("RAZON_SOCIAL_CLIENTE").toString());
					}
					if (!Validaciones.isObjectNull(registro.get("CUIT_CLIENTE"))) {
						resultado.setCuitCliente(registro.get("CUIT_CLIENTE").toString());
					}
					if (!Validaciones.isObjectNull(registro.get("NUMERO_HOLDING_CLIENTE"))) {
						resultado.setNumHoldingCliente(((BigDecimal) registro.get("NUMERO_HOLDING_CLIENTE")).longValue());
					}
					if (!Validaciones.isObjectNull(registro.get("DESCRIPCION_HOLDING_CLIENTE"))) {
						resultado.setDescripcionHoldingCliente(registro.get("DESCRIPCION_HOLDING_CLIENTE").toString());
					}
					if (!Validaciones.isObjectNull(registro.get("ID_AGENCIA_NEGOCIO_CLIENTE"))) {
						resultado.setIdAgenciaNegocioCliente(((BigDecimal) registro.get("ID_AGENCIA_NEGOCIO_CLIENTE")).longValue());
					}
					if (!Validaciones.isObjectNull(registro.get("DESCR_AGENCIA_NEGOCIO_CLIENTE"))) {
						resultado.setDescripcionAgenciaNegocioCliente(registro.get("DESCR_AGENCIA_NEGOCIO_CLIENTE").toString());
					}
					if (!Validaciones.isObjectNull(registro.get("ID_PROVINCIA_CLIENTE"))) {
						resultado.setIdProvinciaCliente(registro.get("ID_PROVINCIA_CLIENTE").toString());
					}
					
					listaResultado.add(resultado);

				}
			}			
			
			return listaResultado;
			
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

	}	
	
private QueryParametrosUtil obtenerSQLValores(VistaSoporteImputacionAutomaticaValoresClientesPurosFiltro filtro) throws NegocioExcepcion {
		
		StringBuffer consulta = new StringBuffer("");
		QueryParametrosUtil qp = new QueryParametrosUtil();
		
		consulta.append("select valor.id_valor");
		consulta.append("	 , valor.id_cliente_legado");
		consulta.append("	 , valor.nro_valor");
		consulta.append("	 , valor.importe");
		consulta.append("	 , valor.fecha_alta");
		consulta.append("	 , valor.id_motivo");
		consulta.append("	 , valor.saldo_disponible");
		consulta.append("	 , valor.fecha_ingreso_recibo");
		consulta.append("	 , valor.fecha_emision");
		consulta.append("	 , valor.fecha_transferencia");
		consulta.append("	 , valor.fecha_deposito");
		consulta.append("	 , valor.fecha_ultimo_estado");
		consulta.append("	 , valor.fecha_vencimiento");
		consulta.append("	 , valor.id_tipo_retencion");
		consulta.append("	 , valor.referencia_valor");
		consulta.append("	 , valor.nro_cuit_retencion");
		consulta.append("	 , valor.provincia_retencion");
		consulta.append("	 , valor.id_tipo_valor");
		consulta.append("	 , valor.id_empresa");
		consulta.append("	 , valor.id_segmento");
		consulta.append("	 , valor.fecha_valor");
		consulta.append("	 , cliente.id_segmento as id_segmento_cliente");
		consulta.append("	 , cliente.razon_social as razon_social_cliente");
		consulta.append("	 , cliente.CUIT as cuit_cliente");
		consulta.append("	 , cliente.numero_holding as numero_holding_cliente");
		consulta.append("	 , cliente.descripcion_holding as descripcion_holding_cliente");
		consulta.append("	 , cliente.id_agencia_negocio as id_agencia_negocio_cliente");
		consulta.append("	 , cliente.descripcion_agencia_negocio as descr_agencia_negocio_cliente");
		consulta.append("	 , cliente.id_provincia as id_provincia_cliente");
		consulta.append(" from shv_sop_busqueda_valores valor");
		consulta.append(" full outer join shv_param_cliente cliente");
		consulta.append(" on cliente.id_cliente_legado = valor.id_cliente_legado");
		consulta.append(" where cliente.permite_uso_ta = ? ");
		consulta.append("   and cliente.permite_uso_tp = ? ");
		consulta.append("   and cliente.permite_uso_cv = ? ");
		consulta.append("   and cliente.permite_uso_ft = ? ");
		consulta.append("   and cliente.permite_uso_nx = ? ");
		
		if (EmpresaEnum.TA.equals(filtro.getEmpresaCliente())){
			qp.addCampoAlQuery(SiNoEnum.SI.name(), Types.VARCHAR);
		} else {
			qp.addCampoAlQuery(SiNoEnum.NO.name(), Types.VARCHAR);
		}
		
		if (EmpresaEnum.TP.equals(filtro.getEmpresaCliente())){
			qp.addCampoAlQuery(SiNoEnum.SI.name(), Types.VARCHAR);
		} else {
			qp.addCampoAlQuery(SiNoEnum.NO.name(), Types.VARCHAR);
		}
		
		if (EmpresaEnum.CV.equals(filtro.getEmpresaCliente())){
			qp.addCampoAlQuery(SiNoEnum.SI.name(), Types.VARCHAR);
		} else {
			qp.addCampoAlQuery(SiNoEnum.NO.name(), Types.VARCHAR);
		}
		
		if (EmpresaEnum.FT.equals(filtro.getEmpresaCliente())){
			qp.addCampoAlQuery(SiNoEnum.SI.name(), Types.VARCHAR);
		} else {
			qp.addCampoAlQuery(SiNoEnum.NO.name(), Types.VARCHAR);
		}
		
		if (EmpresaEnum.NX.equals(filtro.getEmpresaCliente())){
			qp.addCampoAlQuery(SiNoEnum.SI.name(), Types.VARCHAR);
		} else {
			qp.addCampoAlQuery(SiNoEnum.NO.name(), Types.VARCHAR);
		}		
		
		if (Validaciones.isCollectionNotEmpty(filtro.getListaEstados())){
			consulta.append(" and valor.id_estado_valor in (");
			String estadoDeValoresString = ""; 
			for (Estado estadoDeValor: filtro.getListaEstados()) {
				estadoDeValoresString += "?, ";
				qp.addCampoAlQuery(estadoDeValor.name(), Types.VARCHAR);
			}
			consulta.append(estadoDeValoresString.substring(0, estadoDeValoresString.length()-2) + ")");
		}
		
		String tipoDeValoresEnum = parametroServicio.getValorTexto(Constantes.LISTA_TIPO_VALOR_BATCH_IMPUTACION_AUTOMATICA_VALORES_CLIENTES_PUROS);
		List<TipoValorEnum> tipoDeValores = TipoValorEnum.obtenerListaDeTipoDeValores(tipoDeValoresEnum);
		
		if (Validaciones.isCollectionNotEmpty(tipoDeValores)){
			consulta.append(" and valor.id_tipo_valor in (");
			String tipoDeValoresString = "";
			for (TipoValorEnum tipoDeValor: tipoDeValores) {
				tipoDeValoresString += "?, ";
				qp.addCampoAlQuery(tipoDeValor.getIdTipoValor(), Types.INTEGER);
			}
			consulta.append(tipoDeValoresString.substring(0, tipoDeValoresString.length()-2) + ")");
		}
		
		qp.setSql(consulta.toString());

		return qp;
	}
}
	
