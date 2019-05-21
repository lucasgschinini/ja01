package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDocumentoCap;

public interface CobroOnlineDocumentoCapRepositorio extends JpaRepository<ShvCobEdDocumentoCap, Integer>{
	
	
	@Modifying
	@Query("delete From ShvCobEdDocumentoCap where otrosMedioPago.pk.cobro.idCobro = ?1")
	int borrarDocumentosCapsDelMedioDePagoDelCobro(Long idCobro);
}
