package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.telecom.shiva.persistencia.modelo.simple.ShvTalCompensacionSimplificado; 

public interface CompensacionRepositorioSimplificado extends JpaRepository<ShvTalCompensacionSimplificado, Long>{

}
