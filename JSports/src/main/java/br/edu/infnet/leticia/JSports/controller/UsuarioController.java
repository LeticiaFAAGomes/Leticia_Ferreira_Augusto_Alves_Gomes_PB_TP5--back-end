package br.edu.infnet.leticia.JSports.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.infnet.leticia.JSports.enums.TipoUsuario;
import br.edu.infnet.leticia.JSports.model.domain.Usuario;
import br.edu.infnet.leticia.JSports.dto.UsuarioDTO;
import br.edu.infnet.leticia.JSports.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Operation(summary = "Cadastrar novo usuário", description = "Recebe os dados de um usuário e salva no banco")
	@PostMapping
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO dto) {
		try {
			Usuario usuario = new Usuario(dto);
			Usuario salvo = usuarioService.salvar(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(salvo.toDTO());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

    @Operation(
            summary = "Realizar login",
            description = "Autentica o usuário com email e senha e retorna os dados se válido"
    )
	@GetMapping("/login")
	public ResponseEntity<UsuarioDTO> login(@RequestParam String email, @RequestParam String senha) {
		Usuario usuario = usuarioService.autenticar(email, senha);
		return usuario != null ? ResponseEntity.ok(usuario.toDTO())
				: ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

    @Operation(
            summary = "Listar usuários por tipo",
            description = "Retorna uma lista de usuários filtrados pelo tipo (CLIENTE, VENDEDOR, ADMIN)"
     )
	@GetMapping("/tipo")
	public ResponseEntity<List<UsuarioDTO>> listarPorTipo(@RequestParam TipoUsuario tipo) {
		List<Usuario> usuarios = usuarioService.listarPorTipo(tipo);
		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		List<UsuarioDTO> dtos = usuarios.stream().map(Usuario::toDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	public UsuarioDTO cadastrarInterno(UsuarioDTO dto) {
		Usuario usuario = new Usuario(dto);
		return usuarioService.salvar(usuario).toDTO();
	}

	public UsuarioDTO loginInterno(String email, String senha) {
		Usuario usuario = usuarioService.autenticar(email, senha);
		return usuario != null ? usuario.toDTO() : null;
	}
}
