package ar.com.telecom.shiva.base.enumeradores;

import java.sql.Types;

import ar.com.telecom.shiva.base.utils.Utilidad;

public enum QueryMarcaEnum {

	DESCOBRO_PENDIENTE_PROCESO_DEBITO_QUERY(
		"SELECT descobro.id_descobro FROM SHV_COB_DESCOBRO descobro INNER JOIN shv_wf_workflow_estado wfEstado ON(descobro.id_workflow = wfEstado.id_workflow) LEFT JOIN shv_cob_ed_debito debito ON(debito.id_cobro = descobro.id_cobro) WHERE debito.id_debito_referencia = ? AND wfEstado.estado in " + QueryMarcaEnum.DESCOBRO_PENDIENTE_PROCESO,
		"",
		Types.VARCHAR
	),
	DESCOBRO_PENDIENTE_PROCESO_DEBITO_QUERY_MIC_BY_NUMERO_REF_MIC(
		"SELECT descobro.id_descobro FROM SHV_COB_DESCOBRO descobro INNER JOIN shv_wf_workflow_estado wfEstado ON(descobro.id_workflow = wfEstado.id_workflow) LEFT JOIN shv_cob_ed_debito debito ON(debito.id_cobro = descobro.id_cobro) WHERE debito.numero_referencia_mic = ? AND wfEstado.estado in " + QueryMarcaEnum.DESCOBRO_PENDIENTE_PROCESO,
		"",
		Types.NUMERIC
	),
	DESCOBRO_PENDIENTE_PROCESO_CREDITO_QUERY(
		"SELECT descobro.id_descobro FROM SHV_COB_DESCOBRO descobro INNER JOIN shv_wf_workflow_estado wfEstado ON(descobro.id_workflow = wfEstado.id_workflow) LEFT JOIN shv_cob_ed_credito credito ON(credito.id_cobro = descobro.id_cobro) WHERE credito.id_credito_referencia = ? AND wfEstado.estado in " + QueryMarcaEnum.DESCOBRO_PENDIENTE_PROCESO,
		"",
		Types.VARCHAR
	),
	DESCOBRO_PENDIENTE_PROCESO_CREDITO_QUERY_MIC_BY_NUMERO_REF_MIC(
		"SELECT descobro.id_descobro FROM SHV_COB_DESCOBRO descobro INNER JOIN shv_wf_workflow_estado wfEstado ON(descobro.id_workflow = wfEstado.id_workflow) LEFT JOIN shv_cob_ed_credito credito ON(credito.id_cobro = descobro.id_cobro) WHERE credito.nro_referencia_mic = ? AND wfEstado.estado in " + QueryMarcaEnum.DESCOBRO_PENDIENTE_PROCESO,
		"",
		Types.NUMERIC
	),
	DESCOBRO_EDICION_ERROR_ERROR_PRIMER_DESCOBRO_DEBITO_QUERY(
		"SELECT descobro.id_descobro FROM SHV_COB_DESCOBRO descobro INNER JOIN shv_wf_workflow_estado wfEstado ON (descobro.id_workflow = wfEstado.id_workflow) LEFT JOIN shv_cob_ed_debito debito ON (debito.id_cobro = descobro.id_cobro) WHERE debito.id_debito_referencia = ? AND wfEstado.estado IN " + QueryMarcaEnum.DESCOBRO_EDICION_ERROR_ERROR_PRIMER_DESCOBRO,
		"",
		Types.VARCHAR
	),
	DESCOBRO_EDICION_ERROR_ERROR_PRIMER_DESCOBRO_CREDITO_QUERY(
		"SELECT descobro.id_descobro FROM SHV_COB_DESCOBRO descobro INNER JOIN shv_wf_workflow_estado wfEstado ON (descobro.id_workflow = wfEstado.id_workflow) LEFT JOIN shv_cob_ed_credito credito ON (credito.id_cobro = descobro.id_cobro) WHERE  credito.id_credito_referencia = ? AND wfEstado.estado IN " + QueryMarcaEnum.DESCOBRO_EDICION_ERROR_ERROR_PRIMER_DESCOBRO,
		"",
		Types.VARCHAR
	),
	COBRO_PENDIENTE_PROCESO_DEBITO_QUERY_MIC_BY_NUMERO_REF_MIC(
		"SELECT SCEC.ID_OPERACION FROM SHV_COB_ED_COBRO SCEC INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW INNER JOIN SHV_COB_ED_DEBITO SCEDD ON SCEC.ID_COBRO = SCEDD.ID_COBRO WHERE SWWE.ESTADO IN ('COB_PENDIENTE', 'COB_PROCESO') AND SCEDD.numero_referencia_mic = ? ",
		"",
		Types.NUMERIC
	),
	COBRO_PENDIENTE_PROCESO_CREDITO_QUERY_MIC_BY_NUMERO_REF_MIC(
		"SELECT SCEC.ID_OPERACION FROM SHV_COB_ED_COBRO SCEC INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW INNER JOIN SHV_COB_ED_CREDITO SCECC ON SCEC.ID_COBRO = SCECC.ID_COBRO WHERE SWWE.ESTADO IN ('COB_PENDIENTE', 'COB_PROCESO') AND SCECC.nro_referencia_mic = ? ",
		"",
		Types.NUMERIC
	),
	DESCOBRO_PENDIENTE_PROCESO_CAP_QUERY(
		"SELECT descobro.id_descobro FROM SHV_COB_DESCOBRO descobro INNER JOIN shv_wf_workflow_estado wfEstado ON(descobro.id_workflow = wfEstado.id_workflow) LEFT JOIN shv_cob_ed_documento_cap cap ON(cap.id_cobro = descobro.id_cobro) WHERE cap.id_cap_referencia = ? AND wfEstado.estado in " + QueryMarcaEnum.DESCOBRO_PENDIENTE_PROCESO,
		"",
		Types.VARCHAR
	),
	DESCOBRO_EDICION_ERROR_ERROR_PRIMER_DESCOBRO_CAP_QUERY(
		"SELECT descobro.id_descobro FROM SHV_COB_DESCOBRO descobro INNER JOIN shv_wf_workflow_estado wfEstado ON (descobro.id_workflow = wfEstado.id_workflow) LEFT JOIN shv_cob_ed_documento_cap cap ON (cap.id_cobro = descobro.id_cobro) WHERE  cap.id_cap_referencia = ? AND wfEstado.estado IN " + QueryMarcaEnum.DESCOBRO_EDICION_ERROR_ERROR_PRIMER_DESCOBRO,
		"",
		Types.VARCHAR
	);
	
	public String query = Utilidad.EMPTY_STRING;
	public String descripcion = Utilidad.EMPTY_STRING;
	public int tipoDatoParametro = Types.VARCHAR;

	private QueryMarcaEnum(String query, String descripcion, int tipoDatoParametro) {
		this.query = query;
		this.descripcion = descripcion;
		this.tipoDatoParametro = tipoDatoParametro;
	}

	
	private static final String DESCOBRO_PENDIENTE_PROCESO = 
		"( 'DES_EN_ERROR','DES_DESCOBRO_PROCESO', 'DES_PENDIENTE')";
	private static final String DESCOBRO_EDICION_ERROR_ERROR_PRIMER_DESCOBRO = 
		"('DES_EN_EDICION', 'DES_ERROR_EN_PRIMER_DESCOBRO')";
}
