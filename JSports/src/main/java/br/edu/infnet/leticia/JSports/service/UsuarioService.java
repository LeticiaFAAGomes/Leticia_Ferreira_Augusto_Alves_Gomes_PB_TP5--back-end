package br.edu.infnet.leticia.JSports.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;

import br.edu.infnet.leticia.JSports.dto.UsuarioDTO;
import br.edu.infnet.leticia.JSports.enums.TipoUsuario;
import br.edu.infnet.leticia.JSports.utils.CsvUtils;

@Service
public class UsuarioService {

	private static final String CAMINHO = "src\\main\\java\\br\\edu\\infnet\\leticia\\JSports\\files\\autenticacoes_jsports.csv";

	public boolean executarCadastro(String nome, String email, String senha, String telefone, TipoUsuario tipoUsuario) {

		try {
			CsvUtils.salvarCadastro(new UsuarioDTO(nome, email, senha, telefone, tipoUsuario), CAMINHO);
			return true;
		} catch (IOException e) {
			System.err.print("\033[41m❌ O sistema não pôde gravar o arquivo especificado.");
			return false;
		}
	}

	public boolean executarLogin(String email, String senha) {

		try {
			return CsvUtils.encontrarCadastro(new UsuarioDTO(email, senha), CAMINHO);
		} catch (FileNotFoundException e) {
			System.err.println("\033[41m❌ Arquivo de autenticação não encontrado.");
		} catch (IOException e) {
			System.err.println("\033[41m❌ Erro ao ler o arquivo de autenticação.");
		}
		return false;
	}
}
