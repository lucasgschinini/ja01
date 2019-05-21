package ar.com.telecom.shiva.negocio.mapeos;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalCompensacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalReciboPreImpreso;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoChequePagoDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoEfectivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorChequePagoDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorEfectivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorRetencion;
import ar.com.telecom.shiva.presentacion.bean.dto.ReciboPreImpresoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class ReciboPreImpresoMapeador extends Mapeador {
	
	@Autowired 
	private ILdapServicio ldapServicio;

	@Autowired 
	private ValorMapeador valorMapeo;

	@Override
	public DTO map(Modelo modelo) throws NegocioExcepcion {
		
		ShvTalReciboPreImpreso reciboModelo = (ShvTalReciboPreImpreso) modelo;
		ReciboPreImpresoDto reciboDto = new ReciboPreImpresoDto();
		reciboDto.setFechaUltimaModificacion(reciboModelo.getWorkflow().getFechaUltimaModificacion());
		reciboDto.setValores(new HashSet<ValorDto>());
		String valor = "<p style='line-height:12pt; font-size: 11px;' >";
		
		if (reciboModelo.getShvValBoletaDepositoCheque() != null) {
			for (ShvValBoletaDepositoCheque cheque : reciboModelo.getShvValBoletaDepositoCheque()) {
				if (!cheque.getValor().getWorkFlow().getEstado().equals(Estado.VAL_ANULADO)){
					ValorDto valorDto = (ValorDto)valorMapeo.map(cheque.getValor());
					valor += getValorFormateado(valorDto); 
					reciboDto.getValores().add(valorDto);
				}
			}
		}
		if (reciboModelo.getShvValBoletaDepositoChequePagoDiferido() != null) {
			for (ShvValBoletaDepositoChequePagoDiferido lista : reciboModelo.getShvValBoletaDepositoChequePagoDiferido()) {
				if (!lista.getValor().getWorkFlow().getEstado().equals(Estado.VAL_ANULADO)){
					ValorDto valorDto = (ValorDto)valorMapeo.map(lista.getValor());
					valor += getValorFormateado(valorDto); 
					reciboDto.getValores().add(valorDto);
				}
			}
		}
		
		if (reciboModelo.getShvValBoletaDepositoEfectivo() != null) {
			for (ShvValBoletaDepositoEfectivo lista : reciboModelo.getShvValBoletaDepositoEfectivo()) {
				if (!lista.getValor().getWorkFlow().getEstado().equals(Estado.VAL_ANULADO)){
					ValorDto valorDto = (ValorDto)valorMapeo.map(lista.getValor());
					valor += getValorFormateado(valorDto); 
					reciboDto.getValores().add(valorDto);
				}
			}
		}

		if (reciboModelo.getShvValValorRetencion() != null) {
			for (ShvValValorRetencion lista : reciboModelo.getShvValValorRetencion()) {
				if (!lista.getValor().getWorkFlow().getEstado().equals(Estado.VAL_ANULADO)){
					ValorDto valorDto = (ValorDto)valorMapeo.map(lista.getValor());
					valor += getValorFormateado(valorDto); 
					reciboDto.getValores().add(valorDto);
				}
			}
		}

		if (reciboModelo.getValorEfectivo() != null) {
			for (ShvValValorEfectivo lista : reciboModelo.getValorEfectivo()) {
				if (!Estado.VAL_ANULADO.equals(lista.getValor().getWorkFlow().getEstado())) {
					ValorDto valorDto = (ValorDto) valorMapeo.map(lista.getValor());
					valor += getValorFormateado(valorDto); 
					reciboDto.getValores().add(valorDto);
				}
			}
		}
		
		if (reciboModelo.getValorCheque() != null) {
			for (ShvValValorCheque lista : reciboModelo.getValorCheque()) {
				if (!Estado.VAL_ANULADO.equals(lista.getValor().getWorkFlow().getEstado())) {
					ValorDto valorDto = (ValorDto) valorMapeo.map(lista.getValor());
					valor += getValorFormateado(valorDto); 
					reciboDto.getValores().add(valorDto);
				}
			}
		}
		
		if (reciboModelo.getValorChequePagoDiferido() != null) {
			for (ShvValValorChequePagoDiferido lista : reciboModelo.getValorChequePagoDiferido()) {
				if (!Estado.VAL_ANULADO.equals(lista.getValor().getWorkFlow().getEstado())) {
					ValorDto valorDto = (ValorDto) valorMapeo.map(lista.getValor());
					valor += getValorFormateado(valorDto); 
					reciboDto.getValores().add(valorDto);
				}
			}
		}

		valor += "</p>";
		reciboDto.setListaValores(valor);
		if (!Validaciones.isNullOrEmpty(String.valueOf(reciboModelo.getTalonario().getUsuarioGestor()))){
			reciboDto.setUsuarioGestor(reciboModelo.getTalonario().getUsuarioGestor().getNombreYApellido());
			reciboDto.setIdUsuarioGestor(String.valueOf(reciboModelo.getTalonario().getUsuarioGestor().getIdGestor()));
		}

		//Usuario Anulacion (LDAP)
		UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(reciboModelo.getUsuarioAnulacion());
		reciboDto.setUsuarioAnulacion(usuarioLdap != null?usuarioLdap.getTuid():"");
		reciboDto.setNombreUsuarioAnulacion(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		reciboDto.setMailUsuarioAnulacion(usuarioLdap != null?usuarioLdap.getMail():"");
		
		String compensacion = "<p style='line-height:12pt; font-size: 11px;' >";
		for ( ShvTalCompensacion lista : reciboModelo.getShvTalCompensacion()){
			compensacion += "Referencia: " + (Validaciones.isObjectNull(lista.getReferencia() ) ? "" : lista.getReferencia());
			compensacion += "</br> Importe: " + (Validaciones.isObjectNull(lista.getImporte()) ? "" : Utilidad.formatCurrency(lista.getImporte(), 2));
			compensacion += "</br> </br>";
		}
		compensacion += "</p>";
		reciboDto.setListaCompensaciones(compensacion);

		reciboDto.setImporte(Validaciones.isObjectNull(reciboModelo.getImporte()) ? "" : Utilidad.formatCurrency(reciboModelo.getImporte(), 2));
		reciboDto.setEstado(reciboModelo.getWorkflow().getEstado().descripcion());
		reciboDto.setUsuarioAnulacion(Validaciones.isObjectNull(reciboModelo.getUsuarioAnulacion()) ? "" : String.valueOf(reciboModelo.getUsuarioAnulacion()));
		reciboDto.setFechaAnulacion(Validaciones.isObjectNull(reciboModelo.getFechaAnulacion()) ? "" : Utilidad.formatDateAndTimeFull(reciboModelo.getFechaAnulacion()));	
		reciboDto.setFechaIngreso(Validaciones.isObjectNull(reciboModelo.getFechaIngreso())?"":Utilidad.formatDatePicker(reciboModelo.getFechaIngreso()));
		reciboDto.setEmpresa(Validaciones.isObjectNull(reciboModelo.getTalonario().getEmpresa().getDescripcion()) ? "" : reciboModelo.getTalonario().getEmpresa().getDescripcion());
		reciboDto.setSegmento(Validaciones.isObjectNull(reciboModelo.getTalonario().getSegmento().getDescripcion()) ? "" : reciboModelo.getTalonario().getSegmento().getDescripcion());
		reciboDto.setNroTalonario(Validaciones.isObjectNull(reciboModelo.getTalonario().getIdTalonario()) ? "" : String.valueOf(reciboModelo.getTalonario().getIdTalonario()));
		reciboDto.setNroRecibo(Validaciones.isObjectNull(reciboModelo.getNumeroRecibo()) ? "" : reciboModelo.getNumeroRecibo());

		reciboDto.setCompensaciones(reciboModelo.getShvTalCompensacion());
		reciboDto.setId(reciboModelo.getIdReciboPreimpreso());
		return reciboDto;
	}
	
	public Modelo map(DTO dto, Modelo modelo) throws NegocioExcepcion {
		return null;
	}

	/**
	 * Este método se usa en la grilla de busqueda de talonarios, para presentar la información de los recibos
	 *
	 * @param valorDto
	 * @return
	 */
	private String getValorFormateado(ValorDto valorDto) {
		
		String valor = 
			"<div class='contenedor-columna' style='text-align: left;'><div style='width: 140px;'> "
			+ "<img alt='Usuario' class='bloqueUsuario' src='"
				+ valorDto.urlFotoUsuario(valorDto.getIdAnalista())+"'"
			+ "style='cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;'" 
			+ " onerror='javascript:bloqueUsuario()'>"
				+ valorDto.getNombreAnalista()+" <br>"
			+ "<a href='sip:"+valorDto.getMailAnalista()+"'  title='Chat'><i class='icon-comment' style=' margin-top: 3px'></i></a>"
			+ "<a href='mailto:"+valorDto.getMailAnalista()+"'  title='Email'><i class='icon-envelope' style=' margin-left: 3px; margin-top: 2px'></i></a>"
	 		+ "</div></div>"+"<div class='contenedor-columna'style='text-align: left;'>"
			+(valorDto.getTipoValor()
			+ "</br> &nbsp; &nbsp; &nbsp; Cliente: " 
				+ (Validaciones.isObjectNull(valorDto.getCodCliente()) ? "" : valorDto.getCodCliente())
			+ "&nbsp;" 
				+ (Validaciones.isObjectNull(valorDto.getRazonSocialClienteAgrupador()) ? "" : valorDto.getRazonSocialClienteAgrupador()))
			+ "</br> &nbsp; &nbsp; &nbsp; " 
				+ (Validaciones.isObjectNull(valorDto.getNumeroValorFormateadoConRetornoCarro()) ? "" : valorDto.getNumeroValorFormateadoConRetornoCarro())
			+ "</br> &nbsp; &nbsp; &nbsp; Fecha Deposito: "
				+ (Validaciones.isObjectNull(valorDto.getFechaDeposito()) ? "" : valorDto.getFechaDeposito())
			+ "</br> &nbsp; &nbsp; &nbsp; Importe: "
				+ (Validaciones.isObjectNull(valorDto.getImporte()) ? "" : valorDto.getImporte())
			+ "</br> &nbsp; &nbsp; &nbsp; Acuerdo: "
				+ (Validaciones.isObjectNull(valorDto.getIdAcuerdo()) ? "" : valorDto.getIdAcuerdo());
			valor += "</br></br></div></br>";
			
		return valor;
		
	}

	/**
	 * @return the valorMapeador
	 */
	public ValorMapeador getValorMapeo() {
		return valorMapeo;
	}

	/**
	 * @param valorMapeador the valorMapeador to set
	 */
	public void setValorMapeador(ValorMapeador valorMapeo) {
		this.valorMapeo = valorMapeo;
	}
}
