package br.edu.infnet.leticia.JSports.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.leticia.JSports.enums.ItemCategoria;
import br.edu.infnet.leticia.JSports.model.domain.ItemEsportivo;

@Repository
public interface ItemEsportivoRepository extends JpaRepository<ItemEsportivo, Long> {
	List<ItemEsportivo> findByUsuarioId(Long usuarioId);

	List<ItemEsportivo> findByCategoria(ItemCategoria categoria);
}
