package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamParametro;

public interface ParametroRepositorio extends JpaRepository<ShvParamParametro, String>{

}
