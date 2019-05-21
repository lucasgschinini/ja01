package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCliente;

public interface ParamClienteRepositorio extends JpaRepository<ShvParamCliente, Long> {

}
