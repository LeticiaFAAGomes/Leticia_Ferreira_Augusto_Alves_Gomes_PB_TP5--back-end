package br.edu.infnet.leticia.JSports.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.infnet.leticia.JSports.model.domain.Pedido;
import br.edu.infnet.leticia.JSports.dto.PedidoDTO;
import br.edu.infnet.leticia.JSports.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

    @Operation(
            summary = "Cadastrar novo pedido",
            description = "Cria um novo pedido com os dados fornecidos e salva no banco"
    )
	@PostMapping
	public ResponseEntity<PedidoDTO> cadastrar(@RequestBody PedidoDTO dto) {
		Pedido pedido = new Pedido(dto);
		Pedido salvo = pedidoService.salvar(pedido);
		return ResponseEntity.ok(salvo.toDTO());
	}

    @Operation(
            summary = "Listar todos os pedidos",
            description = "Retorna uma lista com todos os pedidos cadastrados"
    )
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> listarTodos() {
		List<Pedido> pedidos = pedidoService.listarTodos();
		List<PedidoDTO> dtos = pedidos.stream().map(p -> p.toDTO()).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

    @Operation(
            summary = "Buscar pedido por ID",
            description = "Retorna os dados de um pedido específico com base no ID informado"
    )
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoService.buscarPorId(id);
		if (pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get().toDTO());
		}
		return ResponseEntity.notFound().build();
	}

    @Operation(
            summary = "Listar pedidos por usuário",
            description = "Retorna todos os pedidos feitos por um usuário específico"
    )
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<PedidoDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
		List<Pedido> pedidos = pedidoService.listarPorUsuario(usuarioId);
		List<PedidoDTO> dtos = pedidos.stream().map(p -> p.toDTO()).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

    @Operation(
            summary = "Excluir pedido",
            description = "Remove um pedido do sistema com base no ID informado"
    )
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		pedidoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

    @Operation(
            summary = "Atualizar status do pedido",
            description = "Atualiza o status de um pedido existente com base no ID"
    )
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDTO> atualizar(@PathVariable Long id, @RequestBody PedidoDTO dtoAtualizado) {
		Optional<Pedido> existente = pedidoService.buscarPorId(id);
		if (existente.isPresent()) {
			Pedido pedido = existente.get();
			pedido.setStatus(dtoAtualizado.getStatus());
			Pedido atualizado = pedidoService.salvar(pedido);
			return ResponseEntity.ok(atualizado.toDTO());
		}
		return ResponseEntity.notFound().build();
	}
}
