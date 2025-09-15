package br.edu.infnet.leticia.JSports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.leticia.JSports.enums.TipoUsuario;
import br.edu.infnet.leticia.JSports.model.domain.Usuario;
import br.edu.infnet.leticia.JSports.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Usuario autenticar(String email, String senha) {
		return usuarioRepository.findByEmailAndSenha(email, senha).orElse(null);
	}

	public List<Usuario> listarPorTipo(TipoUsuario tipoUsuario) {
		return usuarioRepository.findByTipoUsuario(tipoUsuario);
	}

	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}

}
