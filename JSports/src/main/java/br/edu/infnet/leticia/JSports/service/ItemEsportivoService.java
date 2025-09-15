package br.edu.infnet.leticia.JSports.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.leticia.JSports.enums.ItemCategoria;
import br.edu.infnet.leticia.JSports.model.domain.ItemEsportivo;
import br.edu.infnet.leticia.JSports.repository.ItemEsportivoRepository;

@Service
public class ItemEsportivoService {

	@Autowired
	private ItemEsportivoRepository itemRepository;

	public ItemEsportivo salvar(ItemEsportivo item) {
		return itemRepository.save(item);
	}

	public List<ItemEsportivo> listarPorUsuario(Long usuarioId) {
		return itemRepository.findByUsuarioId(usuarioId);
	}

	public List<ItemEsportivo> listarPorCategoria(ItemCategoria categoria) {
		return itemRepository.findByCategoria(categoria);
	}

	public Optional<ItemEsportivo> buscarPorId(Long id) {
		return itemRepository.findById(id);
	}

	public ItemEsportivo atualizar(ItemEsportivo item) {
		return itemRepository.save(item);
	}
}
