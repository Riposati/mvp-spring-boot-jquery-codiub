package br.com.codiub.censo.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codiub.censo.model.entities.Email;

public interface EmailRepository  extends JpaRepository<Email, Integer>{
	
}