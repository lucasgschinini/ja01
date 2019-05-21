package ar.com.telecom.shiva.negocio.mapeos;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTarea;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTareaCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTareaDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTareaOperacionMasiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTareaRegistroAVC;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTareaTalonario;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTareaValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTareaValorReversion;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class TareaMapeador extends Mapeador {
	
	@Autowired 
	private ILdapServicio ldapServicio;
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvWfTarea tarea = (ShvWfTarea) vo;
		TareaDto dto = new TareaDto();
		
		dto.setId(tarea.getIdTarea());
		dto.setIdWorkflow(tarea.getIdWorkflow());
		dto.setTipoTarea(tarea.getTipoTarea());
		dto.setFechaCreacion(tarea.getFechaCreacion());
		dto.setUsuarioCreacion(tarea.getUsuarioCreacion());
		dto.setPerfilAsignacion(tarea.getPerfilAsignacion());
		dto.setFechaEjecucion(tarea.getFechaEjecucion());
		dto.setUsuarioEjecucion(tarea.getUsuarioEjecucion());
		dto.setNombreUsuarioEjecucion(tarea.getNombreUsuarioEjecucion());
		dto.setGestionaSobre(tarea.getGestionaSobre());
		dto.setEstado(tarea.getEstado());
		dto.setObservacion(tarea.getObservaciones());
		Collection<UsuarioLdapDto> usuarioLdapLista = ldapServicio.buscarUsuariosPorPerfilEnMemoria(tarea.getPerfilAsignacion());
		StringBuffer supervisoresMail = new StringBuffer("");
		StringBuffer supervisoresChat = new StringBuffer("");
	
		for (UsuarioLdapDto usuario: usuarioLdapLista) {			
			if (!Validaciones.isNullOrEmpty(usuario.getMail())) {
				supervisoresMail.append(usuario.getMail()+";");  
				supervisoresChat.append("<sip:"+usuario.getMail()+">");  
			}			
		}
		
		if(usuarioLdapLista.size()>0){			
			dto.setIdUsuarioAsignado(((usuarioLdapLista.size()==1)?usuarioLdapLista.iterator().next().getTuid():""));
			dto.setNombreUsuarioAsignado(((usuarioLdapLista.size()==1)?usuarioLdapLista.iterator().next().getNombreCompleto():dto.getGrupoAsignado()));	
		}
		dto.setMailUsuarioAsignado(supervisoresMail.toString());
		dto.setChatUsuarioAsignado(supervisoresChat.toString());
		
		if (tarea.getTareaValor()!=null) {
			dto.setIdItem(tarea.getTareaValor().getIdValor());
			dto.setIdValor(tarea.getTareaValor().getIdValor());
		}
		if (tarea.getTareaTalonario()!=null) {
			dto.setIdItem(tarea.getTareaTalonario().getIdTalonario());
			dto.setIdTalonario(tarea.getTareaTalonario().getIdTalonario());
		}
		
		if (tarea.getTareaRegistroAVC()!=null) {
			dto.setIdItem(tarea.getTareaRegistroAVC().getIdRegistroAvc());
			dto.setIdRegistroAVC(tarea.getTareaRegistroAVC().getIdRegistroAvc());
		}
		
		if (tarea.getTareaValorPorReversion()!=null) {
			dto.setIdItem(tarea.getTareaValorPorReversion().getIdValorPorReversion());
			dto.setIdValorPorReversion(tarea.getTareaValorPorReversion().getIdValorPorReversion());
		}
		
		if (tarea.getTareaCobro()!=null) {
			dto.setIdItem(tarea.getTareaCobro().getIdCobro());
			dto.setIdCobro(tarea.getTareaCobro().getIdCobro());
			if(tarea.getTareaCobro().getIdOperacion() !=null){
				dto.setIdOperacion(tarea.getTareaCobro().getIdOperacion());
			}
			if (!Validaciones.isObjectNull(tarea.getTareaCobro().getImporte())) {
				dto.setImporte(tarea.getTareaCobro().getImporte().toString().replaceAll(".", ","));
			}
			if (!Validaciones.isObjectNull(tarea.getTareaCobro().getMonedaImporte())){
				dto.setMonedaImporte(tarea.getTareaCobro().getMonedaImporte().getSigno2());
			}
		}
		
		if (tarea.getTareaOperacionMasiva()!=null) {
			dto.setIdItem(tarea.getTareaOperacionMasiva().getIdOperacionMasiva());
			dto.setIdOperacionMasiva(tarea.getTareaOperacionMasiva().getIdOperacionMasiva());
			dto.setImporte(tarea.getTareaOperacionMasiva().getImporte().toString().replaceAll(".", ","));
			dto.setRefBandeja(tarea.getTareaOperacionMasiva().getReferencia());
		}
		
		if (!Validaciones.isObjectNull(tarea.getTareaDescobro())) {
			dto.setIdItem(tarea.getTareaDescobro().getIdDescobro());
			dto.setIdCobro(tarea.getTareaDescobro().getIdDescobro());
			
			if(tarea.getTareaDescobro().getIdOperacion() !=null){
				dto.setIdOperacion(tarea.getTareaDescobro().getIdOperacion());
			}
			
			if (!Validaciones.isObjectNull(tarea.getTareaDescobro().getMonedaImporte())){
				dto.setMonedaImporte(tarea.getTareaDescobro().getMonedaImporte().getSigno2());
			}
			if (!Validaciones.isObjectNull(tarea.getTareaDescobro().getImporte())) {
				dto.setImporte(tarea.getTareaDescobro().getImporte().toString().replaceAll(".", ","));
			}
		}
		
		if(!Validaciones.isObjectNull(tarea.getSistema())){
			dto.setSistema(tarea.getSistema());
		}
		
		if(!Validaciones.isObjectNull(tarea.getSociedad())){
			dto.setSociedad(tarea.getSociedad());
		}
		
		if(!Validaciones.isObjectNull(tarea.getTipoAccion())){
			dto.setTipoAccion(tarea.getTipoAccion());
		}
		
		return dto;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		ShvWfTarea tarea = (ShvWfTarea) (vo != null ? vo: new ShvWfTarea());
		
		tarea.setIdWorkflow(tareaDto.getIdWorkflow());
		tarea.setTipoTarea(tareaDto.getTipoTarea());
		tarea.setFechaCreacion(tareaDto.getFechaCreacion());
		tarea.setUsuarioCreacion(tareaDto.getUsuarioCreacion());
		tarea.setFechaEjecucion(tareaDto.getFechaEjecucion());
		tarea.setPerfilAsignacion(tareaDto.getPerfilAsignacion());
		tarea.setUsuarioEjecucion(tareaDto.getUsuarioEjecucion());
		tarea.setGestionaSobre(tareaDto.getGestionaSobre());
		tarea.setEstado(tareaDto.getEstado());
		tarea.setUsuarioAsignacion(tareaDto.getUsuarioAsignacion());
		tarea.setNombreUsuarioEjecucion(tareaDto.getNombreUsuarioEjecucion());
		tarea.setTipoAccion(tareaDto.getTipoAccion());
		tarea.setObservaciones(tareaDto.getObservacion());
		if(!Validaciones.isObjectNull(tareaDto.getSistema())){
			tarea.setSistema(tareaDto.getSistema());
		}
		
		if(!Validaciones.isObjectNull(tareaDto.getSociedad())){
			tarea.setSociedad(tareaDto.getSociedad());
		}
		
		if (tarea.getTareaValor()==null && tareaDto.getIdValor()!=null) {
			ShvWfTareaValor tareaValor = new ShvWfTareaValor();
			tareaValor.setTarea(tarea);
			tareaValor.setIdValor(tareaDto.getIdValor());
			tarea.setTareaValor(tareaValor);
		} 
		if (tarea.getTareaTalonario()==null && tareaDto.getIdTalonario()!=null) {
			ShvWfTareaTalonario tareaTalonario = new ShvWfTareaTalonario();
			tareaTalonario.setTarea(tarea);
			tareaTalonario.setIdTalonario(tareaDto.getIdTalonario());
			tarea.setTareaTalonario(tareaTalonario);
		} 
		if (tarea.getTareaRegistroAVC()==null && tareaDto.getIdRegistroAVC()!=null) {
			ShvWfTareaRegistroAVC tareaRegistro = new ShvWfTareaRegistroAVC();
			tareaRegistro.setTarea(tarea);
			tareaRegistro.setIdRegistroAvc(tareaDto.getIdRegistroAVC());
			tarea.setTareaRegistroAVC(tareaRegistro);
		}
		if (tarea.getTareaValorPorReversion()==null && tareaDto.getIdValorPorReversion()!=null) {
			ShvWfTareaValorReversion tareaValorPorReversion = new ShvWfTareaValorReversion();
			tareaValorPorReversion.setTarea(tarea);
			tareaValorPorReversion.setIdValorPorReversion(tareaDto.getIdValorPorReversion());
			tarea.setTareaValorPorReversion(tareaValorPorReversion);
		}
		if (tarea.getTareaOperacionMasiva()==null && tareaDto.getIdOperacionMasiva()!=null) {
			ShvWfTareaOperacionMasiva tareaOperacionMasiva = new ShvWfTareaOperacionMasiva();
			tareaOperacionMasiva.setTarea(tarea);
			tareaOperacionMasiva.setImporte(Utilidad.stringToBigDecimal(tareaDto.getImporte()));
			tareaOperacionMasiva.setReferencia(tareaDto.getRefBandeja());
			tareaOperacionMasiva.setIdOperacionMasiva(tareaDto.getIdOperacionMasiva());
			tarea.setTareaOperacionMasiva(tareaOperacionMasiva);
		}
		if (tarea.getTareaCobro()==null && tareaDto.getIdCobro()!=null) {
			ShvWfTareaCobro tareaCobro = new ShvWfTareaCobro();
			tareaCobro.setTarea(tarea);
			tareaCobro.setIdCobro(tareaDto.getIdCobro());
			tareaCobro.setMonedaImporte(MonedaEnum.getEnumBySigno2(tareaDto.getMonedaImporte()));
			tareaCobro.setImporte(Utilidad.stringToBigDecimal(tareaDto.getImporte()));
			if(tareaDto.getIdOperacion() != null) {
				tareaCobro.setIdOperacion(tareaDto.getIdOperacion());
			}
			
			if(!Validaciones.isNullOrEmpty(tareaDto.getNroCliente())) {
				tareaCobro.setIdClienteLegado(new Long(tareaDto.getNroCliente()));
				tareaCobro.setRazonSocial(tareaDto.getRazonSocial());
			}
			
			
			tarea.setTareaCobro(tareaCobro);
		} 
		
		if (Validaciones.isObjectNull(tarea.getTareaDescobro()) 
				&& !Validaciones.isObjectNull(tareaDto.getIdDescobro())) {
			
			ShvWfTareaDescobro tareaDescobro = new ShvWfTareaDescobro();
			tareaDescobro.setTarea(tarea);
			tareaDescobro.setIdDescobro(tareaDto.getIdDescobro());
			tareaDescobro.setImporte(Utilidad.stringToBigDecimal(tareaDto.getImporte()));
			tareaDescobro.setIdOperacion(tareaDto.getIdOperacion());
			
			if(!Validaciones.isNullOrEmpty(tareaDto.getNroCliente())) {
				tareaDescobro.setIdClienteLegado(new Long(tareaDto.getNroCliente()));
				tareaDescobro.setRazonSocial(tareaDto.getRazonSocial());
			}
			
			tareaDescobro.setMonedaImporte(MonedaEnum.getEnumBySigno2(tareaDto.getMonedaImporte()));
			tarea.setTareaDescobro(tareaDescobro);
		} 
		
		return tarea;
	}
	
	public ILdapServicio getLdapServicio() {
		return ldapServicio;
	}

	public void setLdapServicio(ILdapServicio ldapServicio) {
		this.ldapServicio = ldapServicio;
	}
}
