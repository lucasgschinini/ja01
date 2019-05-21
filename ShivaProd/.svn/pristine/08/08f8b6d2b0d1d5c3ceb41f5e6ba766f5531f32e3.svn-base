package ar.com.telecom.shiva.base.enumeradores;

import ar.com.telecom.shiva.base.constantes.Mensajes;

public enum OperacionMasivaBatchEmailEnum {
	HACIA_MIC_INICIO_PROCESO_OK(
		"",
		"operacionesMasivas.mail.asunto.inicio.proceso.entrada.mic.ok",
		Mensajes.OPERACION_MASIVAS_MAIL_DESTINATARIO_CREACION_ARCHIVO_ENTRADA_MIC,
		"operacionesMasivas.mail.Cuerpo.inicio.proceso.entrada.mic.ok"
	),
	HACIA_MIC_INICIO_PROCESO_NOK(
		"",
		"operacionesMasivas.mail.asunto.inicio.proceso.entrada.mic.nok",
		Mensajes.OPERACION_MASIVAS_MAIL_DESTINATARIO_CREACION_ARCHIVO_ENTRADA_MIC,
		"operacionesMasivas.mail.Cuerpo.inicio.proceso.entrada.mic.nok"
	),
	DESDE_MIC_VALIDACION_OK(
		"",
		"operacionesMasivas.mail.asunto.validacion.salida.mic.ok",
		Mensajes.OPERACION_MASIVAS_MAIL_DESTINATARIO_VALIDACION_ARCHIVO_SALIDA_MIC,
		"operacionesMasivas.mail.Cuerpo.validacion.salida.mic.ok"
	),
	DESDE_MIC_VALIDACION_NOK(
		"",
		"operacionesMasivas.mail.asunto.validacion.salida.mic.nok",
		Mensajes.OPERACION_MASIVAS_MAIL_DESTINATARIO_VALIDACION_ARCHIVO_SALIDA_MIC,
		"operacionesMasivas.mail.Cuerpo.validacion.salida.mic.nok"
	),
	DESDE_MIC_PROCESO_OK("",
		"operacionesMasivas.mail.asunto.proceso.salida.mic.ok",
		Mensajes.OPERACION_MASIVAS_MAIL_DESTINATARIO_PROCESO_ARCHIVO_SALIDA_MIC,
		"operacionesMasivas.mail.Cuerpo.proceso.salida.mic.ok"
	),
	DESDE_MIC_PROCESO_NOK(
		"",
		"operacionesMasivas.mail.asunto.proceso.salida.mic.nok",
		Mensajes.OPERACION_MASIVAS_MAIL_DESTINATARIO_PROCESO_ARCHIVO_SALIDA_MIC,
		"operacionesMasivas.mail.Cuerpo.proceso.salida.mic.nok"
	),
	SIEBEL_PROCESO_OK(
		"",
		"operacionesMasivas.mail.asunto.siebel.ok",
		Mensajes.OPERACION_MASIVAS_MAIL_DESTINATARIO_PROCESO_SIEBEL,
		"operacionesMasivas.mail.Cuerpo.siebel.ok"
	),
	SIEBEL_PROCESO_ERROR(
		"",
		"operacionesMasivas.mail.asunto.siebel.nok",
		Mensajes.OPERACION_MASIVAS_MAIL_DESTINATARIO_PROCESO_SIEBEL,
		"operacionesMasivas.mail.Cuerpo.siebel.nok"
	);

	public String descripcion = "";
	public String asunto = "";
	public String to = "";
	public String cuerpo = "";

	private OperacionMasivaBatchEmailEnum(String descripcion, String asunto, String to, String cuerpo) {
		this.descripcion = descripcion;
		this.asunto = asunto;
		this.to = to;
		this.cuerpo = cuerpo;
	}

	public static OperacionMasivaBatchEmailEnum getEnumByName(String name) {
		for (OperacionMasivaBatchEmailEnum ele : OperacionMasivaBatchEmailEnum.values()) {
			if (ele.name().equalsIgnoreCase(name)) {
				return ele;
			}
		}
		return null;
	}
}
