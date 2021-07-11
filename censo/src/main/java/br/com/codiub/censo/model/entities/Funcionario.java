package br.com.codiub.censo.model.entities;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@NoArgsConstructor
@AllArgsConstructor

@Table(name = "funcionarios")

public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	private String cpf;
	private String funcionario;
	private String lotacao;
	private String cargo;
//	private int secretaria; // ? 
	private long empresa;
	private String mae;
	private int matricula;
	private String senha;
	
	public BigInteger getId() {
		
		return this.id;
	}
	
	public void setCpf(String cpf) {
		
		this.cpf = cpf;
	}
	
	public String getCpf() {
		
		return this.cpf;
	}

	public String getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public String getLotacao() {
		return lotacao;
	}

	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}

//	public int getSecretaria() {
//		return secretaria;
//	}
//
//	public void setSecretaria(int secretaria) {
//		this.secretaria = secretaria;
//	}

	public long getEmpresa() {
		return empresa;
	}

	public void setEmpresa(long empresa) {
		this.empresa = empresa;
	}

	public String getNomeMae() {
		return mae;
	}

	public void setNomeMae(String nomeMae) {
		this.mae = nomeMae;
	}
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
		
}