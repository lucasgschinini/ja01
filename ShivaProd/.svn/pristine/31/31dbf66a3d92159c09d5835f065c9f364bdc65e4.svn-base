package ar.com.telecom.shiva.persistencia.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCalendario;

public interface ParametroCalendarioRepositorio extends JpaRepository<ShvParamCalendario, Long> {
	@Query("FROM ShvParamCalendario shvParamCalendario where TRUNC(shvParamCalendario.fecha) >= TRUNC(?1)")
	public List<ShvParamCalendario> buscaParamtroCalendarioApartirDe(Date fechaDesde);
}