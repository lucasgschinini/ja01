package ar.com.telecom.shiva.presentacion.bean.filtro;

public class PerfilFiltro extends Filtro {

	
		private static final long serialVersionUID = 1L;
		
		private String tipoTarea;
		private String sociedad;
		private String sistema;
		
		public String getTipoTarea() {
			return tipoTarea;
		}

		public void setTipoTarea(String tipoTarea) {
			this.tipoTarea = tipoTarea;
		}

		/**
		 * @return the sociedad
		 */
		public String getSociedad() {
			return sociedad;
		}

		/**
		 * @param sociedad the sociedad to set
		 */
		public void setSociedad(String sociedad) {
			this.sociedad = sociedad;
		}

		/**
		 * @return the sistema
		 */
		public String getSistema() {
			return sistema;
		}

		/**
		 * @param sistema the sistema to set
		 */
		public void setSistema(String sistema) {
			this.sistema = sistema;
		}
	}


