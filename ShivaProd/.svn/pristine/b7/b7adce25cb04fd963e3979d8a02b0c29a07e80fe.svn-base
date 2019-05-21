package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.dto.DepositoDto;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ConciliacionSugeridaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public interface IConciliacionServicio extends IServicio {

	boolean procesarArchivosRegistrosAVC() throws NegocioExcepcion;
	
	List<ConciliacionSugeridaDto> listarConciliacionesSugeridas() throws NegocioExcepcion;
	List<RegistroAVCDto> listarRegistrosAvcSinConciliar() throws NegocioExcepcion;
	void confirmarConciliacionesSugeridas(String[] listaConciliacionesSugeridas, String usuarioModificacion) throws NegocioExcepcion, PersistenciaExcepcion;
	void deshacerConciliacionesSugeridas(String[] listaConciliacionesSugeridas, String usuario) throws NegocioExcepcion;
	ValorDto crearValorDtoApartirDeRegistroAvcYBoleta(DepositoDto registroAvc, BoletaSinValorDto boletaDto) throws NegocioExcepcion;
	void guardarAsociacionDeConciliacion(ShvBolBoleta boleta, ShvAvcRegistroAvc registroAvc) throws NegocioExcepcion;
	int cantRegistros();
}
