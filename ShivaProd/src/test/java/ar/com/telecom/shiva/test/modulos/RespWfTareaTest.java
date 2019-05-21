package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IParamRespWfTareaServicio;
import ar.com.telecom.shiva.presentacion.bean.filtro.PerfilFiltro;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class RespWfTareaTest  extends SoporteContextoSpringTest{
	@Autowired IParamRespWfTareaServicio paramRespWfTareaServicio;
	
	@Test
	public void buscarPerfil() {
			try {
				
				System.out.println("Buscar tipo de tarea por perfil");
				PerfilFiltro tipoTarea = new PerfilFiltro();
				tipoTarea.setEmpresa(EmpresaEnum.TP.toString());
				tipoTarea.setSistema(SistemaEnum.OPEN.toString());
				tipoTarea.setSociedad(SociedadEnum.FIBERCORP.toString());
				tipoTarea.setSegmento("OGC");
				tipoTarea.setTipoTarea(TipoTareaEnum.AUTR_REND_TAL.toString());

				TipoPerfilEnum perfil2 = paramRespWfTareaServicio.buscarPerfil(tipoTarea);
				System.out.println(perfil2.name());

				System.out.println("FINALIZADO");
			} catch (Exception e) {
				System.out.println("Error al buscar perfil: " + ((NegocioExcepcion) e).getMensajeAuxiliar());
		}
	

	}
}
