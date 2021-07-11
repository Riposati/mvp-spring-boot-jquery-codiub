package br.com.codiub.censo.controller;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codiub.censo.model.entities.Email;
import br.com.codiub.censo.model.entities.Funcionario;
import br.com.codiub.censo.model.repositories.EmailRepository;
import br.com.codiub.censo.model.repositories.EmpresaRepository;
import br.com.codiub.censo.model.repositories.FuncionarioRepository;

@RestController
@RequestMapping("/cadastra-dados-formulario")

public class InsertCensoRestController {

	private FuncionarioRepository repository;
	private EmpresaRepository repositoryEmpresa;
	private EmailRepository repositoryEmail;

	public InsertCensoRestController(FuncionarioRepository funcionarioRepository, EmpresaRepository empresaRepository,
			EmailRepository emailRepository) {
		this.repository = funcionarioRepository;
		this.repositoryEmpresa = empresaRepository;
		this.repositoryEmail = emailRepository;
	}

	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * Perform encryption and decryption algorithm encryption once, twice decryption
	 */
	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	@PostMapping()
	public void create(@RequestBody List<Map<String, String>> client) {
		Map<String, String> formInputs = new HashMap<String, String>();

		for (Map<String, String> formInput : client) {
			formInputs.put(formInput.get("name"), formInput.get("value"));
		}

		// System.out.println(formInputs); tudo que veio do front

		// pra ver o mapa apenas printar o formInputs
		// pra pegar as chaves do mapa => formInputs.keySet()
		// pra pegar os valores => formInputs.values()

		Funcionario f = new Funcionario();

		f.setCpf(formInputs.get("cpf"));
		f.setFuncionario(formInputs.get("funcionario"));
		f.setLotacao(formInputs.get("lotacao"));
		f.setCargo(formInputs.get("cargo"));
		f.setMatricula(Integer.parseInt(formInputs.get("matricula")));

		f.setSenha(convertMD5(formInputs.get("senha")));
//		System.out.println("encryption:" + convertMD5(formInputs.get("senha")));
//		System.out.println("decrypted:" + convertMD5(convertMD5(formInputs.get("senha"))));

		f.setNomeMae(formInputs.get("mae"));

		int aux = repositoryEmpresa.getEmpresaByName(formInputs.get("empresa")).size();
		if (aux != 0)
			f.setEmpresa(repositoryEmpresa.getEmpresaByName(formInputs.get("empresa")).get(0).getId());

		this.repository.save(f); // salva o funcionario aqui!
		// System.out.println(f.getId()); / / pega ultimo ID inserido no banco pelo
		// hibernate

		formInputs.remove("cpf");
		formInputs.remove("funcionario");
		formInputs.remove("lotacao");
		formInputs.remove("cargo");
		formInputs.remove("matricula");
		formInputs.remove("senha");
		formInputs.remove("mae");
		formInputs.remove("empresa");

		// System.out.println(formInputs.keySet()); // somente tem email e tipo email
		// nesse ponto

		long auxV = 1L;

		for (int i = 0; i < formInputs.size(); i++) {

			if (formInputs.get("email" + i) == null)
				break;

			Email e = new Email();
			e.setIdUsuario(f.getId());
			e.setEmail(formInputs.get("email" + i));

			String a = formInputs.get("tipoemail" + i);
//			System.out.println(a);

			if (a.equals("Pessoal")) {
				auxV = 1L;
			}
			if (a.equals("Corporativo")) {
				auxV = 2L;
			}

			e.setTipoEmail(auxV);
			
			this.repositoryEmail.save(e); // salva todos os emails aqui
		}

		formInputs.clear();
	}
}