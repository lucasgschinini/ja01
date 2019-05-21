package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_RESP_WF_TAREA")
	public class ShvParamRespWfTarea extends Modelo {

		private static final long serialVersionUID = 1L;

		@Id
		@Column (name="ID_RESP_WF_TAREA")
		private Long idRespWfTarea;

		@Column(name = "ID_EMPRESA")
		private String idEmpresa;	
		
		@Column(name = "ID_SEGMENTO")
		private String idSegmento;	
		
		@Column(name = "ID_SOCIEDAD")
		private String idSociedad;

		@Column(name = "SISTEMA_ORIGEN")
		private String sistemaOrigen;

		@Column(name = "TIPO_TAREA")
		private String tipoTarea;
		
		@Enumerated(EnumType.STRING)
		@Column(name = "PERFIL", nullable = false)
		private TipoPerfilEnum perfil;

		/**
		 * @return the idRespWfTarea
		 */
		public Long getIdRespWfTarea() {
			return idRespWfTarea;
		}

		/**
		 * @param idRespWfTarea the idRespWfTarea to set
		 */
		public void setIdRespWfTarea(Long idRespWfTarea) {
			this.idRespWfTarea = idRespWfTarea;
		}

		/**
		 * @return the idEmpresa
		 */
		public String getIdEmpresa() {
			return idEmpresa;
		}

		/**
		 * @param idEmpresa the idEmpresa to set
		 */
		public void setIdEmpresa(String idEmpresa) {
			this.idEmpresa = idEmpresa;
		}

		/**
		 * @return the idSegmento
		 */
		public String getIdSegmento() {
			return idSegmento;
		}

		/**
		 * @param idSegmento the idSegmento to set
		 */
		public void setIdSegmento(String idSegmento) {
			this.idSegmento = idSegmento;
		}

		/**
		 * @return the idSociedad
		 */
		public String getIdSociedad() {
			return idSociedad;
		}

		/**
		 * @param idSociedad the idSociedad to set
		 */
		public void setIdSociedad(String idSociedad) {
			this.idSociedad = idSociedad;
		}

		/**
		 * @return the sistema
		 */
		public String getSistema() {
			return sistemaOrigen;
		}

		/**
		 * @param sistema the sistema to set
		 */
		public void setSistema(String sistema) {
			this.sistemaOrigen = sistema;
		}

		/**
		 * @return the tipoTarea
		 */
		public String getTipoTarea() {
			return tipoTarea;
		}

		/**
		 * @param tipoTarea the tipoTarea to set
		 */
		public void setTipoTarea(String tipoTarea) {
			this.tipoTarea = tipoTarea;
		}

		/**
		 * @return the perfil
		 */
		public TipoPerfilEnum getPerfil() {
			return perfil;
		}

		/**
		 * @param perfil the perfil to set
		 */
		public void setPerfil(TipoPerfilEnum perfil) {
			this.perfil = perfil;
		}
	   
		
}
