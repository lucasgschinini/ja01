package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;

public class ActualizacionExitosaDto {

	private List<String> numerosBoletasAlta = new ArrayList<String>();
	private List<String> numerosBoletasDuplicados = new ArrayList<String>();
	private List<byte[]> archivosImprimirBoleta = new ArrayList<byte[]>();
	private List<byte[]> archivosImprimirConstancia = new ArrayList<byte[]>();
	private List<String> mensajesMostrarEnvioMail = new ArrayList<String>();
	private List<String> numeroConstanciaArchivo = new ArrayList<String>();
	private List<String> numeroBoletaArchivo = new ArrayList<String>();
	
	private Long numeroExito;
	private ShvValValor valorExito;

	public List<String> getNumerosBoletasAlta() {
		return numerosBoletasAlta;
	}

	public void setNumerosBoletasAlta(List<String> numerosBoletasAlta) {
		this.numerosBoletasAlta = numerosBoletasAlta;
	}

	public List<String> getNumerosBoletasDuplicados() {
		return numerosBoletasDuplicados;
	}

	public List<byte[]> getArchivosImprimirBoleta() {
		return archivosImprimirBoleta;
	}

	public void setArchivosImprimirBoleta(List<byte[]> archivosImprimirBoleta) {
		this.archivosImprimirBoleta = archivosImprimirBoleta;
	}

	public List<byte[]> getArchivosImprimirConstancia() {
		return archivosImprimirConstancia;
	}

	public void setArchivosImprimirConstancia(
			List<byte[]> archivosImprimirConstancia) {
		this.archivosImprimirConstancia = archivosImprimirConstancia;
	}

	public void setNumerosBoletasDuplicados(
			List<String> numerosBoletasDuplicados) {
		this.numerosBoletasDuplicados = numerosBoletasDuplicados;
	}

	public Long getNumeroExito() {
		return numeroExito;
	}

	public void setNumeroExito(Long numeroExito) {
		this.numeroExito = numeroExito;
	}

	public ShvValValor getValorExito() {
		return valorExito;
	}

	public void setValorExito(ShvValValor valorExito) {
		this.valorExito = valorExito;
	}

	public List<String> getMensajesMostrarEnvioMail() {
		return mensajesMostrarEnvioMail;
	}

	public void setMensajesMostrarEnvioMail(List<String> mensajesMostrarEnvioMail) {
		this.mensajesMostrarEnvioMail = mensajesMostrarEnvioMail;
	}

	public List<String> getNumeroConstanciaArchivo() {
		return numeroConstanciaArchivo;
	}

	public void setNumeroConstanciaArchivo(List<String> numeroConstanciaArchivo) {
		this.numeroConstanciaArchivo = numeroConstanciaArchivo;
	}

	public List<String> getNumeroBoletaArchivo() {
		return numeroBoletaArchivo;
	}

	public void setNumeroBoletaArchivo(List<String> numeroBoletaArchivo) {
		this.numeroBoletaArchivo = numeroBoletaArchivo;
	}

}
