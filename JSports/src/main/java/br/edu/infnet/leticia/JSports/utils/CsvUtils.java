package br.edu.infnet.leticia.JSports.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.edu.infnet.leticia.JSports.dto.*;
import br.edu.infnet.leticia.JSports.enums.TipoUsuario;
import br.edu.infnet.leticia.JSports.model.Usuario;

public class CsvUtils {

	private static Usuario cadastro = new Usuario();

	public static void salvarCadastro(UsuarioDTO usuarioCadastrado, String caminho) throws IOException {

		cadastro.setDataCriacao();
		cadastro.setDataCriacao();
		cadastro.setId(
				encontrarId("src\\main\\java\\br\\edu\\infnet\\leticia\\JSports\\files\\autenticacoes_jsports.csv"));
		Long id = cadastro.getId();
		String dataCriacao = cadastro.getDataCriacao();
		String dataAtualizacao = cadastro.getDataAtualizacao();
		String nome = usuarioCadastrado.getNome();
		String email = usuarioCadastrado.getEmail();
		String senha = usuarioCadastrado.getSenha();
		String telefone = usuarioCadastrado.getTelefone();
		TipoUsuario tipoUsuario = usuarioCadastrado.getTipoUsuario();

		BufferedWriter gravador = new BufferedWriter(new FileWriter(caminho, true));
		File arquivo = new File(caminho);
		if (!arquivo.exists() || arquivo.length() == 0) {
			gravarLinha(gravador, "Id; Nome; E-mail; Senha; Telefone; TipoUsuario; DataCriacao; DataAtualizacao;");
		}

		for (int i = 0; i < 1; i++) {
			gravarLinha(gravador, String.format("%d; %s; %s; %s; %s; %s; %s; %s;", id, nome, email, senha, telefone,
					tipoUsuario, dataCriacao, dataAtualizacao));
		}

		gravador.close();

	}

	public static void gravarLinha(BufferedWriter gravador, String linha) {

		try {
			gravador.write(linha);
			gravador.newLine();

		} catch (IOException e) {
			System.err.print("[Erro] O sistema não pôde gravar o arquivo especificado.");
		}

	}

	public static boolean encontrarCadastro(UsuarioDTO usuarioLogin, String caminho) throws IOException {

		try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
			String linha;

			while ((linha = leitor.readLine()) != null) {
				String[] info = linha.split("; ");

				if (info[2].equals(usuarioLogin.getEmail()) && info[3].equals(usuarioLogin.getSenha())) {
					return true;
				}
			}
		}

		return false;
	}

	public static long encontrarId(String caminho) throws IOException {

		long id = 0;
		File arquivo = new File(caminho);

		if (!arquivo.exists() || arquivo.length() == 0)
			return 1;

		try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				if (linha.matches("\\d+.*")) {
					long atual = Long.parseLong(linha.split(";")[0].trim());
					if (atual > id)
						id = atual;
				}
			}
		}
		return id + 1;
	}

}
