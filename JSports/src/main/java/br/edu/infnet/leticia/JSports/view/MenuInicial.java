package br.edu.infnet.leticia.JSports.view;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

import br.edu.infnet.leticia.JSports.controller.UsuarioController;
import br.edu.infnet.leticia.JSports.enums.TipoUsuario;
import br.edu.infnet.leticia.JSports.model.Endereco;
import br.edu.infnet.leticia.JSports.utils.InputUtils;
import br.edu.infnet.leticia.JSports.utils.LoginUtils;

@Component
public class MenuInicial implements CommandLineRunner {

	static Scanner in = new Scanner(System.in);

	@Autowired
	private UsuarioController usuarioController;

	@Override
	public void run(String... args) {

		iniciarMenuPrincipal();
	}

	public void iniciarMenuPrincipal() {

		int opcao;
		do {
			System.out.print("\033[0m\033[36m┌════ Login JSports ═══┐");
			System.out.format("\n│ %-1s | %-15s │", "N°", "Ações");
			System.out.print("\n├────┼─────────────────┤");
			System.out.format("\n│ %-20s │\n│ %-20s │\n│ %-20s │\n", "1  | Cadastrar", "2  | Fazer login", "0  | Sair");
			System.out.print("└────┴─────────────────┘");

			opcao = InputUtils.inputInt(in, "\033[0;93m\nInsira sua opção: \033[1;93m");

			switch (opcao) {
			case 1 -> iniciarCadastro();
			case 2 -> iniciarLogin();
			case 0 -> System.out.print("Encerrando programa...");
			default -> System.out.println("Opção inválida.");
			}
		} while (opcao != 0);
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
				System.err.println("❌ Tipo de usuário inválido.");
			}
		} while (!ehTipoValido);

		return tipoValido;
	}

	public Endereco gerenciarEndereco() {

		String rua = InputUtils.inputStr(in, "Rua: ");
		String numero = InputUtils.inputStr(in, "Número: ");
		String bairro = InputUtils.inputStr(in, "Bairro: ");
		String complemento = InputUtils.inputStr(in, "Complemento: ");
		String cidade = InputUtils.inputStr(in, "Cidade: ");
		String estado = InputUtils.inputStr(in, "Estado: ");
		String cep = InputUtils.inputStr(in, "CEP: ");

		return new Endereco(rua, numero, bairro, complemento, cidade, estado, cep);
	}

	public void iniciarCadastro() {
		System.out.println("\033[45m\n════ Cadastro de Novo Usuário ═══");

		String nome = InputUtils.inputStr(in, "\033[0;93mInsira seu nome: ");
		String email = gerenciarEmail();
		String senha = gerenciarSenha();
		String telefone = gerenciarTelefone();
		TipoUsuario tipoUsuario = gerenciarTipoUsuario();

		boolean sucesso = usuarioController.realizarCadastro(nome, email, senha, telefone, tipoUsuario);

		if (sucesso) {
			System.out.println("✅ Cadastro criado com sucesso.\n");
		} else {
			System.err.println("❌ Não foi possível realizar o cadastro.\n");
		}
	}

	public void iniciarLogin() {

		System.out.println("\033[45m\n════ Login de Usuário ═══");

		String email = gerenciarEmail();
		String senha = InputUtils.inputStr(in, "\033[0;93mInsira sua senha: ");

		boolean login = usuarioController.realizarLogin(email, senha);

		if (login) {
			System.out.println("✅ Login realizado com sucesso.\n");
		} else {
			System.out.println("\033[41m❌ E-mail ou senha incorretos.\n");
		}
	}
}
