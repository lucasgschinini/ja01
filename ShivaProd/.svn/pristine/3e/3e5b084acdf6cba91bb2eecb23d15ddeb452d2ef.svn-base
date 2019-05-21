package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate4.type.EncryptedBinaryType;

@TypeDef(name="encryptedBinary", typeClass=EncryptedBinaryType.class,
    parameters= {@Parameter(name="encryptorRegisteredName", value="strongHibernateBinaryEncryptor")}) 

@Entity
@Table(name = "SHV_DOC_DOCUMENTO_ADJUNTO")
public class ShvDocDocumentoAdjunto extends Modelo {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_DOC_DOCUMENTO_ADJUNTO")
	@SequenceGenerator(name = "SEQ_SHV_DOC_DOCUMENTO_ADJUNTO", sequenceName = "SEQ_SHV_DOC_DOCUMENTO_ADJUNTO",  allocationSize = 1)
	@Column(name = "ID_ADJUNTO", unique = true, nullable = false)
	private Long idValorAdjunto;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "valorDocAdjunto")
	private List<ShvValValor> idValor;

	@Column(name = "NOMBRE_ARCHIVO", nullable = false)
	private String nombreArchivo;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Lob
	@Column(name = "ARCHIVO_AJUNTO")
	@Type(type="encryptedBinary")
	private byte[] archivoAdjunto;

	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Column(name = "USUARIO_CREACION", nullable = false)
	private String usuarioCreacion;

	public Long getIdValorAdjunto() {
		return idValorAdjunto;
	}

	public void setIdValorAdjunto(Long idValorAdjunto) {
		this.idValorAdjunto = idValorAdjunto;
	}

	public List<ShvValValor> getIdValor() {
		return idValor;
	}

	public void setIdValor(List<ShvValValor> idValor) {
		this.idValor = idValor;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getArchivoAdjunto() {
		return archivoAdjunto;
	}

	public void setArchivoAdjunto(byte[] archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		
		ShvDocDocumentoAdjunto objDocumentoAdjunto = (ShvDocDocumentoAdjunto) obj;
		
		if (this.getIdValorAdjunto() != null && objDocumentoAdjunto.getIdValorAdjunto() != null) {
			return this.getIdValorAdjunto().equals(objDocumentoAdjunto.getIdValorAdjunto());
		}
		
		return false;
	}

}
