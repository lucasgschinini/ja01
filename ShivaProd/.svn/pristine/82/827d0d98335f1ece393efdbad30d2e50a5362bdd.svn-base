package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvSegPerfil;

public interface PerfilRepositorio  extends JpaRepository<ShvSegPerfil, Integer>{
	
   @Query("FROM ShvSegPerfil p where upper(p.perfilAplicativo.descripcion) = ?1 ")
   List<ShvSegPerfil> buscarPerfil(String idPerfilAplicativo);

}
