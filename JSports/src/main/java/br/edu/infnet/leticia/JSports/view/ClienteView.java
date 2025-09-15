package br.edu.infnet.leticia.JSports.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.infnet.leticia.JSports.dto.UsuarioDTO;
import br.edu.infnet.leticia.JSports.enums.FormaPagamento;
import br.edu.infnet.leticia.JSports.enums.ItemCategoria;
import br.edu.infnet.leticia.JSports.enums.StatusPagamento;
import br.edu.infnet.leticia.JSports.enums.StatusPedido;
import br.edu.infnet.leticia.JSports.model.Carrinho;
import br.edu.infnet.leticia.JSports.model.ItemCarrinho;
import br.edu.infnet.leticia.JSports.model.domain.ItemEsportivo;
import br.edu.infnet.leticia.JSports.model.domain.ItemPedido;
import br.edu.infnet.leticia.JSports.model.domain.Pagamento;
import br.edu.infnet.leticia.JSports.model.domain.Pedido;
import br.edu.infnet.leticia.JSports.model.domain.Usuario;
import br.edu.infnet.leticia.JSports.repository.ItemEsportivoRepository;
import br.edu.infnet.leticia.JSports.service.ItemEsportivoService;
import br.edu.infnet.leticia.JSports.service.PedidoService;
import br.edu.infnet.leticia.JSports.utils.InputUtils;
import br.edu.infnet.leticia.JSports.utils.MenuUtils;

@Component
public class ClienteView {

	@Autowired
	private ItemEsportivoService itemService;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ItemEsportivoRepository itemEsportivoRepository;

	private Carrinho carrinho = new Carrinho();

	public void iniciarMenuCliente(Scanner in, UsuarioDTO user) {
		int opcao;
		do {
			MenuUtils.imprimirMenu("\033[0m\033[30;46m", "Compras", Arrays.asList("N°", "Ações"),
					Arrays.asList(Arrays.asList("1", "Trajes Esportivos"), Arrays.asList("2", "Equipamentos de Treino"),
							Arrays.asList("3", "Outdoor"), Arrays.asList("4", "Proteção e Acessórios"),
							Arrays.asList("5", "Ver Carrinho"), Arrays.asList("6", "Remover Item do Carrinho"),
							Arrays.asList("7", "Comprar itens do carrinho"), Arrays.asList("8", "Cancelar Pedido"),
							Arrays.asList("9", "Consultar Pedidos"), Arrays.asList("10", "Avaliar Pedido Entregue"),
							Arrays.asList("0", "Sair.")));

			opcao = InputUtils.inputInt(in, "\033[0;93m\nInsira sua opção: \033[1;93m");

			switch (opcao) {
			case 1 -> listarPorCategoria(ItemCategoria.TRAJES_ESPORTIVOS, in);
			case 2 -> listarPorCategoria(ItemCategoria.EQUIPAMENTOS_TREINO, in);
			case 3 -> listarPorCategoria(ItemCategoria.OUTDOOR, in);
			case 4 -> listarPorCategoria(ItemCategoria.ACESSORIOS_DE_PROTECAO, in);
			case 5 -> listarCarrinho();
			case 6 -> removerItemCarrinho(in);
			case 7 -> comprar(in, user);
			case 8 -> cancelarPedido(in, user);
			case 9 -> consultarPedidos(user);
			case 10 -> avaliarPedido(in, user);
			case 0 -> System.out.println("Saindo do modo cliente...");
			default -> System.out.println("\033[41m❌ Opção inválida.");
			}
		} while (opcao != 0);
	}

	public void listarPorCategoria(ItemCategoria categoria, Scanner in) {
	    List<ItemEsportivo> produtos = itemService.listarPorCategoria(categoria);

	    if (produtos.isEmpty()) {
	        System.out.println("\033[41m❌ Nenhum produto disponível nessa categoria.");
	        return;
	    }

	    listarTodosItens(produtos);
	    if (!desejaAdicionarAoCarrinho(in)) return;

	    int id = InputUtils.inputInt(in, "Insira o id do produto: ");
	    int qtd = InputUtils.inputInt(in, "Insira a quantidade desejada: ");

	    adicionarProdutoAoCarrinho(produtos, id, qtd);
	}

	private boolean desejaAdicionarAoCarrinho(Scanner in) {
	    System.out.print("Deseja adicionar algum item ao carrinho? (s/n): ");
	    String resposta = in.nextLine().trim().toLowerCase();
	    return resposta.equals("s");
	}

	private void adicionarProdutoAoCarrinho(List<ItemEsportivo> produtos, int id, int qtd) {
	    for (ItemEsportivo item : produtos) {
	        if (item.getId() == id) {
	            if (item.getQuantidade() <= 0) {
	                System.out.printf("\033[41m❌ O produto \"%s\" está esgotado.\n", item.getNome());
	                return;
	            }

	            if (qtd > item.getQuantidade()) {
	                System.out.printf("\033[41m❌ Estoque insuficiente. Disponível: %d, Solicitado: %d\n",
	                    item.getQuantidade(), qtd);
	                return;
	            }

	            ItemCarrinho itemCarrinho = new ItemCarrinho();
	            itemCarrinho.setIdProduto(item.getId());
	            itemCarrinho.setNome(item.getNome());
	            itemCarrinho.setPrecoUnitario(item.getPreco());
	            itemCarrinho.setQuantidade(qtd);
	            itemCarrinho.setCategoria(item.getCategoria());

	            carrinho.adicionarItem(itemCarrinho);
	            System.out.println("\033[42m✅ Produto adicionado ao carrinho!");
	            return;
	        }
	    }

	    System.out.println("\033[41m❌ Produto não encontrado.");
	}


	public void listarTodosItens(List<ItemEsportivo> produtos) {
		if (produtos == null || produtos.isEmpty()) {
			System.out.println("\033[41m❌Nenhum produto disponível.");
			return;
		}

		List<List<String>> lista = new ArrayList<>();
		for (ItemEsportivo item : produtos) {
			List<String> linha = Arrays.asList(String.valueOf(item.getId()), item.getNome(),
					String.valueOf(item.getQuantidade()), String.valueOf(item.getPreco()),
					item.getCategoria().toString(), item.getDataCriacao().toString(),
					item.getDataAtualizacao().toString());
			lista.add(linha);
		}

		MenuUtils.imprimirMenu("\033[0m\033[40m", "Produtos Disponíveis",
				Arrays.asList("Id", "Nome", "Quantidade", "Preço", "Categoria", "Criação", "Atualização"), lista);
	}

	public void listarCarrinho() {
		if (carrinho.estaVazio()) {
			System.out.println("\033[41m❌ Carrinho vazio.");
			return;
		}

		List<List<String>> lista = new ArrayList<>();
		int contador = 1;

		for (ItemCarrinho item : carrinho.listar()) {
			BigDecimal subtotal = item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));

			List<String> linha = Arrays.asList(String.valueOf(contador++), item.getNome(),
					String.valueOf(item.getQuantidade()), String.format("R$ %.2f", subtotal),
					item.getCategoria().toString());
			lista.add(linha);
		}

		MenuUtils.imprimirMenu("\033[0m\033[40m", "Carrinho",
				Arrays.asList("Id", "Nome", "Quantidade", "Preço Total", "Categoria"), lista);
	}

	public void removerItemCarrinho(Scanner in) {
	    if (carrinho.estaVazio()) {
	        System.out.println("\033[41m❌ o carrinho está vázio. Nada há para remover.");
	        return;
	    }

	    listarCarrinho();
		int numero = InputUtils.inputInt(in, "Digite o número do item para remover: ");
		carrinho.removerPorIndice(numero - 1);
		System.out.println("\033[42m✅🗑️ Item removido.");
	}

	public List<ItemPedido> prepararItensParaPedido() {
	    List<ItemPedido> itensPedido = new ArrayList<>();

	    for (ItemCarrinho itemCarrinho : carrinho.listar()) {
	        Long idProduto = itemCarrinho.getIdProduto();
	        if (idProduto == null) {
	            throw new IllegalStateException("\033[41m❌ Item no carrinho sem ID. Não pode ser processado.");
	        }

	        ItemEsportivo produto = itemEsportivoRepository.findById(idProduto)
	            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

	        ItemPedido itemPedido = new ItemPedido();
	        itemPedido.setProduto(produto);
	        itemPedido.setQuantidade(itemCarrinho.getQuantidade());
	        itemPedido.setPrecoUnitario(itemCarrinho.getPrecoUnitario());

	        itensPedido.add(itemPedido);
	    }

	    return itensPedido;
	}

	public void comprar(Scanner in, UsuarioDTO user) {
	    if (carrinho.estaVazio()) {
	        System.out.println("\033[41m❌ O seu carrinho está vazio.");
	        return;
	    }

	    List<ItemPedido> itensPedido = prepararItensParaPedido();

	    for (ItemPedido itemPedido : itensPedido) {
	        ItemEsportivo produto = itemPedido.getProduto();
	        if (itemPedido.getQuantidade() > produto.getQuantidade()) {
	            System.out.printf("\033[41m❌ Estoque insuficiente para o item \"%s\".\n", produto.getNome());
	            return;
	        }
	    }

	    BigDecimal valor = carrinho.calcularTotal();
	    FormaPagamento formaPagamento = inserirPagamento(in);
	    Pagamento pagamento = new Pagamento(formaPagamento, valor);
	    pagamento.setStatusPagamento(StatusPagamento.CONFIRMADO);

	    Pedido pedido = new Pedido(itensPedido, StatusPedido.PENDENTE, pagamento, new Usuario(user));
	    pedidoService.salvar(pedido);

	    System.out.println("\033[42m✅ Pedido realizado com sucesso.");
	    carrinho.limpar();
	}


	public void cancelarPedido(Scanner in, UsuarioDTO cliente) {
	    List<Pedido> pedidos = pedidoService.listarPorUsuario(cliente.getId());
	    if (pedidos.isEmpty()) {
	        System.out.println("\033[41m❌ Nenhum pedido encontrado para cancelar.");
	        return;
	    }

	    consultarPedidos(cliente);
	    int id = InputUtils.inputInt(in, "Digite o ID do pedido que deseja cancelar: ");
	    
	    for (Pedido pedido : pedidos) {
	        if (pedido.getId().equals((long) id)) {
	            try {
	                pedidoService.cancelar(pedido);
	                System.out.println("\033[42m✅ Pedido cancelado e estoque restaurado.");
	            } catch (RuntimeException e) {
	                System.out.println("\033[41m❌ Erro ao cancelar pedido: " + e.getMessage());
	            }
	            return;
	        }
	    }
	    System.out.println("\033[41m❌ Pedido não encontrado.");
	}

	public void consultarPedidos(UsuarioDTO cliente) {
	    List<Pedido> pedidos = pedidoService.listarPorUsuario(cliente.getId());

	    if (pedidos.isEmpty()) {
	        System.out.println("\033[41m❌ Nenhum pedido encontrado.");
	        return;
	    }

	    List<List<String>> tabela = new ArrayList<>();
	    for (Pedido pedido : pedidos) {
	        tabela.add(Arrays.asList(
	            String.valueOf(pedido.getId()),
	            pedido.getStatus().toString(),
	            String.format("R$ %.2f", pedido.getTotal())
	        ));
	    }

	    MenuUtils.imprimirMenu("\033[0m\033[40m", "Pedidos do Cliente",
	        Arrays.asList("Id", "Status", "Total"), tabela);
	}


	public void avaliarPedido(Scanner in, UsuarioDTO cliente) {
	    List<Pedido> pedidos = pedidoService.listarPorUsuario(cliente.getId());
	    List<Pedido> entregues = pedidos.stream()
	        .filter(p -> p.getStatus() == StatusPedido.ENTREGUE)
	        .toList();

	    if (entregues.isEmpty()) {
	        System.out.println("\033[41m❌ Nenhum pedido entregue disponível para avaliação.");
	        return;
	    }

	    List<List<String>> tabela = new ArrayList<>();
	    for (Pedido pedido : entregues) {
	        tabela.add(Arrays.asList(
	            String.valueOf(pedido.getId()),
	            String.format("R$ %.2f", pedido.getTotal())
	        ));
	    }

	    MenuUtils.imprimirMenu("\033[0m\033[40m", "Pedidos Entregues",
	        Arrays.asList("ID", "Total"), tabela);

	    int id = InputUtils.inputInt(in, "Digite o ID do pedido que deseja avaliar: ");
	    for (Pedido pedido : entregues) {
	        if (pedido.getId().equals((long) id)) {
	            int nota = InputUtils.inputInt(in, "Digite sua nota de 1 a 5: ");
	            String comentario = InputUtils.inputStr(in, "Deixe um comentário: ");
	            pedido.setClassificacao(nota);
	            pedido.setComentario(comentario);
	            pedido.setStatus(StatusPedido.CONCLUIDO);
	            pedidoService.salvar(pedido);
	            System.out.println("\033[42m✅ Avaliação registrada.");
	            return;
	        }
	    }

	    System.out.println("\033[41m❌ Pedido não encontrado ou não entregue.");
	}

	public FormaPagamento inserirPagamento(Scanner in) {
		do {
			MenuUtils.imprimirMenu("\033[0m\033[40m", "Formas de Pagamento", Arrays.asList("N°", "Opção"),
					Arrays.asList(Arrays.asList("1", "PIX"), Arrays.asList("2", "Cartão de Crédito"),
							Arrays.asList("3", "Cartão de Débito")));

			int opcao = InputUtils.inputInt(in, "\nEscolha a forma de pagamento: ");

			return switch (opcao) {
			case 1 -> FormaPagamento.PIX;
			case 2 -> FormaPagamento.CARTAO_CREDITO;
			case 3 -> FormaPagamento.CARTAO_DEBITO;
			default -> {
				System.out.println("\033[41m❌ Forma de pagamento inválida.");
				yield null;
			}
			};
		} while (true);
	}

	public BigDecimal calcularFrete() {
		Random random = new Random();
		return BigDecimal.valueOf(random.nextDouble() * 80);
	}

	public BigDecimal calcularTotal() {
		return carrinho.calcularTotal().add(calcularFrete());
	}

	public void avaliarPedido(Pedido pedido, int nota, String comentario) {
		if (pedido.getStatus() == StatusPedido.ENTREGUE) {
			pedido.setClassificacao(nota);
			pedido.setComentario(comentario);
			pedidoService.salvar(pedido);
			System.out.printf("\033[42m⭐ Pedido #%d avaliado com nota %d: \"%s\"\n", pedido.getId(), nota, comentario);
			pedido.setStatus(StatusPedido.CONCLUIDO);
		} else {
			System.out.printf("\033[41m❌ O Pedido #%d ainda não foi entregue, portanto, a avaliação não é permitida.\n",
					pedido.getId());
		}
	}

}
