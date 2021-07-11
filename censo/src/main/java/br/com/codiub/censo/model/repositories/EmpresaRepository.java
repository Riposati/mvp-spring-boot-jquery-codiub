package br.com.codiub.censo.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.codiub.censo.model.entities.Empresa;

public interface EmpresaRepository  extends JpaRepository<Empresa, Integer>{
	
	@Query(value="select id,empresa from empresa a where a.empresa= :empresa", nativeQuery=true)
    List<Empresa> getEmpresaByName(String empresa);
}