package ar.com.telecom.shiva.negocio.servicios;

import java.util.Map;

import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;

public interface IOperacionMasivaRegistroValidacionServicio {
	public boolean validarRegistro(String[] campos);

	public boolean validarParametrosGenerales(boolean aplica, String[] campos);

	public boolean validarCobranzaGenerales(boolean aplica, String[] campos, boolean carryRta);

	public boolean validarCobranzaApropiacionDeDeuda(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarDebitoImputadoCliente(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarDebitoImputadoDatosGenerales(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarDebitoImputadoTagetik(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarDebitoDakota(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarSaldoTerceros(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarCrediotAplicadoCliente(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarMedioPago(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarNotaCredito(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarCreditoRemanete(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarCreditoDatosGenerales(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarCreditoAplicadoTagetik(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarAcuerdoInteresPorMoraDatosGenerales(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarAcuerdoInteresPorMoraCliente(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarCreditoAplicadoDakota(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos);

	public boolean validarRespuestasResultadoDebito(boolean aplica, String[] campos, Map<String, TipoResultadoEnum> rtaMic);

	public boolean validarRespuestasResultadoCredito(boolean aplica, String[] campos, Map<String,TipoResultadoEnum> rtaMic);

	public boolean validarRespuestasResultadoReintegro(boolean aplica, String[] campos, Map<String, TipoResultadoEnum> rtaMic);

}
