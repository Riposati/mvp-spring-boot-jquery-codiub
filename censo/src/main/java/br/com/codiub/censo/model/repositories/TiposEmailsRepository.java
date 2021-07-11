package br.com.codiub.censo.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codiub.censo.model.entities.TiposEmails;

public interface TiposEmailsRepository  extends JpaRepository<TiposEmails, Long>{

}