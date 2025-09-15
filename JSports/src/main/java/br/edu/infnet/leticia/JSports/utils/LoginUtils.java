package br.edu.infnet.leticia.JSports.utils;

import br.edu.infnet.leticia.JSports.enums.TipoUsuario;

public final class LoginUtils {

	public static String validarSenha(String senha) {

		StringBuilder msg = new StringBuilder();

		boolean ehSenhaNumero = validarNumero(senha);
		boolean ehSenhaQtd = validarQtdSenha(senha);
		boolean ehSenhaMaiuscula = validarLetraMaiuscula(senha);
		boolean ehSenhaCaractereEspecial = validarCaractereEspecial(senha);

		msg.append(ehSenhaQtd ? "\033[42m✅ Senha maior que 7 caracteres."
				: "\033[41m❌ A senha deve conter ao menos 8 caracteres");

		msg.append(
				ehSenhaNumero ? "\033[42m\n✅ Senha contém número." : "\033[41m\n❌ A senha deve ter ao menos um número");

		msg.append(ehSenhaMaiuscula ? "\033[42m\n✅ Senha tem letra maiúscula."
				: "\033[41m\n❌ A senha deve ter ao menos uma letra maiúscula.");

		msg.append(ehSenhaCaractereEspecial ? "\033[42m\n✅ Senha tem caractere especial.\n\n"
				: "\033[41m\n❌ A senha deve conter ao menos um caractere especial.\n\n");

		return msg.toString();
	}

	public static boolean validarQtdSenha(String senha) {

		return senha != null && senha.length() >= 8;
	}

	public static boolean validarLetraMaiuscula(String senha) {

		return senha != null && senha.matches(".*[A-Z].*");
	}

	public static boolean validarNumero(String senha) {

		return senha != null && senha.matches(".*\\d+.*");
	}

	public static boolean validarCaractereEspecial(String senha) {

		return senha != null && senha.matches(".*[^A-Za-z0-9].*");
	}

	public static boolean validarEmail(String email) {

		return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
	}

	public static boolean validarTelefone(String telefone) {

		return telefone != null && telefone.matches("^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$");
	}

	public static boolean validarTipoUsuario(int tipoUsuario) {

		return tipoUsuario == 1 || tipoUsuario == 2;
	}

	public static TipoUsuario definirTipoUsuario(int tipoUsuario) {

		switch (tipoUsuario) {
		case 1:
			return TipoUsuario.CLIENTE;
		case 2:
			return TipoUsuario.VENDEDOR;
		default:
			throw new IllegalArgumentException("Tipo de usuário inválido: " + tipoUsuario);
		}
	}

	public static boolean validarCpf(String cpf) {
		return cpf != null && cpf.matches("\\d{11}");
	}

	public static boolean validarCnpj(String cnpj) {
		return cnpj != null && cnpj.matches("\\d{14}");
	}

}
