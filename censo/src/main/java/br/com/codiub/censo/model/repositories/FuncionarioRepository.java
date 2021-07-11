package br.com.codiub.censo.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codiub.censo.model.entities.Funcionario;

public interface FuncionarioRepository  extends JpaRepository<Funcionario, Long>{

}