package br.edu.infnet.leticia.JSports.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.leticia.JSports.enums.StatusPagamento;
import br.edu.infnet.leticia.JSports.enums.StatusPedido;
import br.edu.infnet.leticia.JSports.model.domain.ItemEsportivo;
import br.edu.infnet.leticia.JSports.model.domain.ItemPedido;
import br.edu.infnet.leticia.JSports.model.domain.Pedido;
import br.edu.infnet.leticia.JSports.repository.ItemEsportivoRepository;
import br.edu.infnet.leticia.JSports.repository.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ItemEsportivoRepository itemEsportivoRepository;

    @Transactional
    public Pedido salvar(Pedido pedido) {
        if (pedido.getId() != null) {
            System.out.println("📝 Atualizando pedido existente - sem alterar estoque");
            return pedidoRepository.saveAndFlush(pedido);
        }

        System.out.println("🆕 Novo pedido - processando estoque...");

        List<ItemPedido> itensGerenciados = new ArrayList<>();

        for (ItemPedido itemPedido : pedido.getItens()) {
            Long produtoId = itemPedido.getProduto().getId();

            if (!itemEsportivoRepository.existsById(produtoId)) {
                throw new RuntimeException("O produto não existe no banco: ID " + produtoId);
            }

            ItemEsportivo produtoGerenciado = itemEsportivoRepository.getReferenceById(produtoId);
            int estoqueAtual = produtoGerenciado.getQuantidade();
            int quantidadeSolicitada = itemPedido.getQuantidade();

            System.out.printf("   %s: Estoque atual = %d, Solicitado = %d\n",
                produtoGerenciado.getNome(), estoqueAtual, quantidadeSolicitada);

            if (quantidadeSolicitada > estoqueAtual) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produtoGerenciado.getNome());
            }

            int novoEstoque = estoqueAtual - quantidadeSolicitada;
            produtoGerenciado.setQuantidade(novoEstoque);

            System.out.printf("   ✅ Estoque atualizado: %s = %d\n", produtoGerenciado.getNome(), novoEstoque);

            itemPedido.setPedido(pedido);
            itensGerenciados.add(itemPedido);
        }

        pedido.setItens(itensGerenciados);
        return pedidoRepository.saveAndFlush(pedido);
    }


    @Transactional
    public Pedido cancelar(Pedido pedido) {
        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            throw new RuntimeException("Só é possível cancelar pedidos pendentes");
        }
        
        System.out.println("❌ Cancelando pedido - restaurando estoque...");
        
        for (ItemEsportivo item : pedido.getCarrinho()) {
            ItemEsportivo itemDoBanco = itemEsportivoRepository.findById(item.getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            
            int estoqueAnterior = itemDoBanco.getQuantidade();
            int novoEstoque = estoqueAnterior + item.getQuantidade();
            itemDoBanco.setQuantidade(novoEstoque);
            itemEsportivoRepository.save(itemDoBanco);
            
            System.out.printf("🔄 Estoque restaurado - %s: %d → %d\n", 
                itemDoBanco.getNome(), estoqueAnterior, novoEstoque);
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        pedido.getPagamento().setStatusPagamento(StatusPagamento.CANCELADO);
        
        return pedidoRepository.saveAndFlush(pedido);
    }

    public List<Pedido> listarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        return pedidoRepository.buscarPedidosComItens(usuarioId);
    }

    public void excluir(Long id) {
        pedidoRepository.deleteById(id);
    }

    public List<Pedido> listarPorStatusComItens(StatusPedido status) {
        return pedidoRepository.buscarPorStatusComItens(status);
    }
    
    public List<Pedido> listarTodosComItens() {
        return pedidoRepository.listarTodosComItens();
    }
}