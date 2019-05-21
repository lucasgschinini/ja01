package ar.com.telecom.shiva.negocio.servicios.validacion;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.negocio.batch.bean.DepositoBatch;
import ar.com.telecom.shiva.negocio.batch.bean.InterdepositoBatch;
import ar.com.telecom.shiva.negocio.batch.bean.TransferenciaBatch;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCliente;

public interface IProcesarArchivoValidacionServicio {

	DepositoBatch procesarDepositos (String i, String linea, String tipoAcuerdo) throws ValidacionExcepcion;
	
	TransferenciaBatch procesarTransferencias (String i, String linea, String tipoAcuerdo, List<ShvParamBancoCliente> listaBancoCliente) throws ValidacionExcepcion;
	
	String agregarAlLogError(String i, String reemplazarMensajes);

	InterdepositoBatch procesarInterdepositos(String valueOf, String string, String acuerdo, List<ShvParamBancoCliente> listaBancoCliente, String secuencialArchivo) throws ValidacionExcepcion;

}
