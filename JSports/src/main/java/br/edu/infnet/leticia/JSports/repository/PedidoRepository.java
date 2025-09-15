package br.edu.infnet.leticia.JSports.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.infnet.leticia.JSports.enums.StatusPedido;
import br.edu.infnet.leticia.JSports.model.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	List<Pedido> findByUsuarioId(Long usuarioId);

	@Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.carrinho WHERE p.usuario.id = :usuarioId")
	List<Pedido> buscarPedidosComItens(@Param("usuarioId") Long usuarioId);

	@Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.carrinho WHERE p.status = :status")
	List<Pedido> buscarPorStatusComItens(@Param("status") StatusPedido status);
	
	@Query("SELECT DISTINCT p FROM Pedido p JOIN FETCH p.itens i JOIN FETCH i.produto prod JOIN FETCH prod.usuario")
	List<Pedido> listarTodosComItens();

	List<Pedido> findByStatus(StatusPedido status);
}
