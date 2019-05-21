package ar.com.telecom.shiva.persistencia.dao.impl;

import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IOperacionTagetikDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.presentacion.bean.filtro.OperacionTagetikFiltro;

public class OperacionesTagetikDaoImpl extends Dao implements
		IOperacionTagetikDao {
	
	@Autowired
	IParametroServicio parametroServicio;
	
	/**
	 * El resultado de los cobros y los descobros será una consulta a la base de datos en 
	 * busca de Cobros en estado "Cobrado" o "Confirmado con Error" y de Descobros en estado "Descobrado".
	 * @return Una lista de facturas.
	 */
	public List<Map<String,Object>> obtenerFacturasTagetik(OperacionTagetikFiltro filtro) throws PersistenciaExcepcion {
		try {
			GregorianCalendar fechaFin = new GregorianCalendar();
			GregorianCalendar fechaInicio = new GregorianCalendar();
			fechaFin.setTime(Utilidad.parseDatePickerString(filtro.getFechaHasta()));
			fechaFin.add(GregorianCalendar.DATE, 1);
			BeanUtils.copyProperties(fechaFin, fechaInicio);
			fechaInicio.add(GregorianCalendar.DATE, -7);

			StringBuffer query = new StringBuffer();
			query.append("select CICLO, TIPO_FACTURA, TIPO_PAGO, fecha_emision, fecha_vencimiento, fecha_valor, id_tipo_medio_pago, ");
			query.append("marketing, caja, importe, id_cliente, razon_social, tipo_cliente, tipo_comprobante, clase_comprobante, ");
			query.append("suc_comprobante, num_comprobante, id_cuenta_cob, id_ope, mon, fecha_workflow, origen, descobro, tipo_cambio, ");
			query.append("IMPORTE_MED_PAGO_MONEDA_ORGEN, TIPO_CAMBIO_MEDIO_PAGO, MONEDA_MEDIO_PAGO, ID_CUENTA_COB_PADRE, SUBTIPO_DE_DOCUMENTO, MONEDA_OPERACION, NUMERO_TRANSACCION ");
			query.append("from SHV_SOP_TAGETIK ");
			query.append("where fecha_workflow >= ? And fecha_workflow <? ORDER BY id_ope ASC, id_cuenta_cob_padre DESC, NUMERO_TRANSACCION ASC, tipo_comprobante DESC, TO_DATE(TO_CHAR(fecha_valor, 'dd.mm.yyyy'), 'dd.mm.yyyy') DESC, MON DESC ");
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());

			qp.addCampoAlQuery(fechaInicio.getTime(), Types.DATE);
			qp.addCampoAlQuery(fechaFin.getTime(), Types.DATE);

			return buscarUsandoSQL(qp);
		} catch(Exception e){
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

}
