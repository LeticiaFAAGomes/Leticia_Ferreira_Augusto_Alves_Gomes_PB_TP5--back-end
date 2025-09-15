package br.edu.infnet.leticia.JSports.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.infnet.leticia.JSports.enums.ItemCategoria;
import br.edu.infnet.leticia.JSports.model.domain.ItemEsportivo;
import br.edu.infnet.leticia.JSports.model.domain.Usuario;
import br.edu.infnet.leticia.JSports.dto.ItemEsportivoDTO;
import br.edu.infnet.leticia.JSports.service.ItemEsportivoService;
import br.edu.infnet.leticia.JSports.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/itens")
public class ItemEsportivoController {

	@Autowired
	private ItemEsportivoService itemService;

	@Autowired
	private UsuarioService usuarioService;

    @Operation(
            summary = "Cadastrar novo item esportivo",
            description = "Cria um novo item esportivo vinculado a um vendedor e salva no banco"
    )
	@PostMapping
	public ResponseEntity<ItemEsportivoDTO> cadastrar(@RequestBody ItemEsportivoDTO dto) {
		Usuario vendedor = usuarioService.buscarPorId(dto.getUsuarioId());
		ItemEsportivo item = new ItemEsportivo(dto);
		item.setUsuario(vendedor);

		ItemEsportivo salvo = itemService.salvar(item);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo.toDTO());
	}

    @Operation(
            summary = "Listar itens por usuário",
            description = "Retorna todos os itens esportivos cadastrados por um vendedor específico"
    )
	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<ItemEsportivoDTO>> listarPorUsuario(@PathVariable Long id) {
		List<ItemEsportivo> itens = itemService.listarPorUsuario(id);
		List<ItemEsportivoDTO> dtos = itens.stream().map(ItemEsportivo::toDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

    @Operation(
            summary = "Listar itens por categoria",
            description = "Retorna todos os itens esportivos filtrados por categoria"
    )
	@GetMapping("/categoria")
	public ResponseEntity<List<ItemEsportivoDTO>> listarPorCategoria(@RequestParam ItemCategoria categoria) {
		List<ItemEsportivo> itens = itemService.listarPorCategoria(categoria);
		List<ItemEsportivoDTO> dtos = itens.stream().map(ItemEsportivo::toDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

    @Operation(
            summary = "Repor estoque de item esportivo",
            description = "Adiciona uma quantidade ao estoque de um item esportivo existente"
    )
	@PutMapping("/{id}/estoque")
	public ResponseEntity<ItemEsportivoDTO> reporEstoque(@PathVariable Long id, @RequestParam int quantidade) {
		Optional<ItemEsportivo> itemOpt = itemService.buscarPorId(id);
		if (itemOpt.isPresent()) {
			ItemEsportivo item = itemOpt.get();
			item.setQuantidade(item.getQuantidade() + quantidade);
			item.setDataAtualizacao();
			ItemEsportivo atualizado = itemService.atualizar(item);
			return ResponseEntity.ok(atualizado.toDTO());
		}
		return ResponseEntity.notFound().build();
	}

    @Operation(
            summary = "Alterar preço de item esportivo",
            description = "Atualiza o preço de um item esportivo existente com base no ID"
    )
	@PutMapping("/{id}/preco")
	public ResponseEntity<ItemEsportivoDTO> alterarPreco(@PathVariable Long id, @RequestParam BigDecimal preco) {
		Optional<ItemEsportivo> itemOpt = itemService.buscarPorId(id);
		if (itemOpt.isPresent()) {
			ItemEsportivo item = itemOpt.get();
			item.setPreco(preco);
			item.setDataAtualizacao();
			ItemEsportivo atualizado = itemService.atualizar(item);
			return ResponseEntity.ok(atualizado.toDTO());
		}
		return ResponseEntity.notFound().build();
	}
}
