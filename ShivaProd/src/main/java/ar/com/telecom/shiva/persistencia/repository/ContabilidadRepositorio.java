package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.telecom.shiva.persistencia.modelo.ShvCntContabilidadDetalle;

public interface ContabilidadRepositorio extends JpaRepository<ShvCntContabilidadDetalle, Long>{

}
