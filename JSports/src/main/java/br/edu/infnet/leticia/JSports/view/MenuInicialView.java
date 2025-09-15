package br.edu.infnet.leticia.JSports.view;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Scanner;

import br.edu.infnet.leticia.JSports.controller.UsuarioController;
import br.edu.infnet.leticia.JSports.dto.UsuarioDTO;
import br.edu.infnet.leticia.JSports.enums.TipoUsuario;
import br.edu.infnet.leticia.JSports.model.domain.Endereco;
import br.edu.infnet.leticia.JSports.model.domain.Usuario;
import br.edu.infnet.leticia.JSports.utils.InputUtils;
import br.edu.infnet.leticia.JSports.utils.LoginUtils;
import br.edu.infnet.leticia.JSports.utils.MenuUtils;

@Component
public class MenuInicialView implements CommandLineRunner {

	static Scanner in = new Scanner(System.in);

	@Autowired
	private UsuarioController usuarioController;

	@Autowired
	private VendedorView vendedorView;

	@Autowired
	private ClienteView clienteView;

	@Override
	public void run(String... args) {
		iniciarMenuPrincipal();
	}

	public void iniciarMenuPrincipal() {
		int opcao;
		do {
			MenuUtils.imprimirMenu("\033[30;47m", "Login JSports", Arrays.asList("N°", "Ações"), Arrays.asList(
					Arrays.asList("1", "Cadastrar"), Arrays.asList("2", "Fazer login"), Arrays.asList("3", "Sair")));

			opcao = InputUtils.inputInt(in, "\033[0;93m\nInsira sua opção: \033[1;93m");

			switch (opcao) {
			case 1 -> iniciarCadastro();
			case 2 -> iniciarLogin();
			case 3 -> System.out.println("Encerrando programa...");
			default -> System.out.println("Opção inválida.");
			}
		} while (opcao != 3);
	}

	public void iniciarCadastro() {
		System.out.println("\033[30;44m\n════ Cadastro de Novo Usuário ═══");

		String nome = InputUtils.inputStr(in, "\033[0;93mInsira seu nome: ");
		String email = gerenciarEmail();
		String senha = gerenciarSenha();
		String telefone = gerenciarTelefone();
		TipoUsuario tipoUsuario = gerenciarTipoUsuario();
		Usuario usuario = new Usuario();

		if (tipoUsuario == TipoUsuario.CLIENTE) {
			String cpf = solicitarCpf();
			usuario.setCpf(cpf);
		} else {
			String cnpj = solicitarCnpj();
			usuario.setCnpj(cnpj);
		}
		Endereco endereco = gerenciarEndereco();

		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setTelefone(telefone);
		usuario.setTipoUsuario(tipoUsuario);
		usuario.setEndereco(endereco);
		usuario.setDataCriacao();

		ResponseEntity<UsuarioDTO> response = usuarioController.cadastrar(usuario.toDTO());

		if (response.getStatusCode().is2xxSuccessful()) {
			System.out.println("\033[42m✅ Cadastro criado com sucesso: " + response.getBody().getNome() + "\n");
		} else {
			System.err.println("❌ Erro ao cadastrar usuário. Código: " + response.getStatusCode() + "\n");
		}
	}
	
	public String solicitarCnpj() {
		String cnpj;
		do {
			cnpj = InputUtils.inputStr(in, "Insira o CNPJ (14 dígitos): ");

			if (!LoginUtils.validarCnpj(cnpj)) {
				System.out.println("CNPJ inválido. Tente novamente.");
			}

		} while (!LoginUtils.validarCnpj(cnpj));
		return cnpj;
	}

	public String solicitarCpf() {
		String cpf;
		do {
			cpf = InputUtils.inputStr(in, "insira o CPF (11 dígitos): ");

			if (!LoginUtils.validarCpf(cpf)) {
				System.out.println("CNPJ inválido. Tente novamente.");
			}
		} while (!LoginUtils.validarCpf(cpf));
		return cpf;
	}

	public void iniciarLogin() {
		System.out.println("\033[30;43m\n════ Login de Usuário ═══");

		String email = gerenciarEmail();
		String senha = InputUtils.inputStr(in, "\033[0;93mInsira sua senha: ");

		ResponseEntity<UsuarioDTO> response = usuarioController.login(email, senha);

		if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			UsuarioDTO login = response.getBody();
			int tipo = login.getTipoUsuario() == TipoUsuario.VENDEDOR ? 1 : 2;

			if (tipo == 1) {
				vendedorView.iniciarMenuVendedor(in, login);
			} else {
				clienteView.iniciarMenuCliente(in, login);
			}
		} else {
			System.out.println("\033[41m❌ E-mail ou senha incorretos. Código: " + response.getStatusCode() + "\n");
		}
	}

	public String gerenciarEmail() {
		String email;
		boolean ehEmailValido;
		do {
			email = InputUtils.inputStr(in, "\033[0;93mInsira seu e-mail: ");
			ehEmailValido = LoginUtils.validarEmail(email);
			if (!ehEmailValido) {
				System.out.println("\033[41m❌ O e-mail deve seguir o padrão abc@email.com\033[0m");
			}
		} while (!ehEmailValido);
		return email.toLowerCase();
	}

	public String gerenciarTelefone() {
		String telefone;
		boolean ehTelefoneValido;
		do {
			telefone = InputUtils.inputStr(in, "\033[0;93mInsira seu telefone: ");
			ehTelefoneValido = LoginUtils.validarTelefone(telefone);
			if (!ehTelefoneValido) {
				System.out.println("\033[41m❌ O telefone deve seguir o padrão brasileiro com 10-11 dígitos\033[0m");
			}
		} while (!ehTelefoneValido);
		return telefone;
	}

	public String gerenciarSenha() {
		String senha;
		String msg;
		do {
			senha = InputUtils.inputStr(in, "\033[0;93mInsira sua senha: ");
			msg = LoginUtils.validarSenha(senha);
			System.out.print(msg);
		} while (msg.contains("❌"));
		return senha;
	}

	public TipoUsuario gerenciarTipoUsuario() {
		boolean ehTipoValido = false;
		TipoUsuario tipoValido = null;
		do {
			System.out.println("\033[0;93m1 - Cliente");
			System.out.println("2 - Vendedor");
			System.out.print("Insira o tipo de usuário: ");
			int tipoUsuario = in.nextInt();
			in.nextLine();
			ehTipoValido = LoginUtils.validarTipoUsuario(tipoUsuario);
			if (ehTipoValido) {
				tipoValido = LoginUtils.definirTipoUsuario(tipoUsuario);
			} else {
				System.err.println("\033[41m❌ Tipo de usuário inválido.");
			}
		} while (!ehTipoValido);
		return tipoValido;
	}

	public Endereco gerenciarEndereco() {
		System.out.println("\nInformações de Endereço:");
		String rua = InputUtils.inputStr(in, "Rua: ");
		String numero = InputUtils.inputStr(in, "Número: ");
		String bairro = InputUtils.inputStr(in, "Bairro: ");
		String decisao = InputUtils.inputStr(in, "Deseja inserir um complemento? (s/n)");
		String complemento = decisao == "s"? InputUtils.inputStr(in, "Complemento: ") : "N/A";
		String cidade = InputUtils.inputStr(in, "Cidade: ");
		String estado = InputUtils.inputStr(in, "Estado: ");
		String cep = InputUtils.inputStr(in, "CEP: ");
		return new Endereco(rua, numero, bairro, complemento, cidade, estado, cep);
	}

}
