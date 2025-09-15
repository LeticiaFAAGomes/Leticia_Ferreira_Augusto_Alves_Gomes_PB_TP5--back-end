package br.edu.infnet.leticia.JSports.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.leticia.JSports.enums.TipoUsuario;
import br.edu.infnet.leticia.JSports.model.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmailAndSenha(String email, String senha);

	List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
}
