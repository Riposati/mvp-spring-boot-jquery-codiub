package br.com.codiub.censo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codiub.censo.model.entities.TiposEmails;
import br.com.codiub.censo.model.repositories.TiposEmailsRepository;

@RestController
@RequestMapping("/efetuar_o_login")
public class LoginCensoRestController {
	
	@Autowired
	private TiposEmailsRepository tiposEmailsRepository;
	
	@GetMapping()
	public List<TiposEmails> read() {
		 return tiposEmailsRepository.findAll();
	}

}