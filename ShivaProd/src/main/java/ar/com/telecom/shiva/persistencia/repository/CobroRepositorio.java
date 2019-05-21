package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;

public interface CobroRepositorio  extends JpaRepository<ShvCobCobro, Long>{

}
