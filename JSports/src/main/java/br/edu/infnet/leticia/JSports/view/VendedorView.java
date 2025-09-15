package br.edu.infnet.leticia.JSports.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.infnet.leticia.JSports.dto.UsuarioDTO;
import br.edu.infnet.leticia.JSports.enums.ItemCategoria;
import br.edu.infnet.leticia.JSports.enums.StatusPedido;
import br.edu.infnet.leticia.JSports.model.domain.ItemEsportivo;
import br.edu.infnet.leticia.JSports.model.domain.ItemPedido;
import br.edu.infnet.leticia.JSports.model.domain.Pedido;
import br.edu.infnet.leticia.JSports.model.domain.Usuario;
import br.edu.infnet.leticia.JSports.service.ItemEsportivoService;
import br.edu.infnet.leticia.JSports.service.PedidoService;
import br.edu.infnet.leticia.JSports.utils.InputUtils;
import br.edu.infnet.leticia.JSports.utils.MenuUtils;

@Component
public class VendedorView {

	@Autowired
	private ItemEsportivoService itemService;
	@Autowired
	private ClienteView clienteView;

	@Autowired
	private PedidoService pedidoService;

	public void iniciarMenuVendedor(Scanner in, UsuarioDTO user) {
		int opcao;
		do {
			MenuUtils.imprimirMenu("\033[0m\033[30;43m", "Vendedor JSports", Arrays.asList("N°", "Ações"),
					Arrays.asList(Arrays.asList("1", "Cadastrar Produto"), Arrays.asList("2", "Gerenciar Produtos"),
							Arrays.asList("3", "Preparar Pedido"), Arrays.asList("4", "Comprar como Cliente"),
							Arrays.asList("5", "Ver relatório de vendas"), Arrays.asList("0", "Sair")));

			opcao = InputUtils.inputInt(in, "\033[0;93m\nInsira sua opção: \033[1;93m");

			switch (opcao) {
			case 1:
				cadastrarItemEsportivo(in, user);
				break;
			case 2:
				gerenciarItensEsportivos(in, user);
				break;
			case 3:
				try {
					prepararPedido();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.println("🔄 Alternando para o modo cliente...");
				clienteView.iniciarMenuCliente(in, user);
				break;
			case 5:
				exibirRelatorioDeVendas(user);
				break;
			case 0:
				return;
			default:
				System.out.println("\033[41m❌ Opção inválida.");
				break;
			}
		} while (opcao != 0);
	}

	public void cadastrarItemEsportivo(Scanner in, UsuarioDTO user) {
		System.out.println("\033[45m\n════ Cadastro de Item Esportivo ═══");
		String nome = InputUtils.inputStr(in, "\033[0;93mInsira o nome do item esportivo: \033[1;93m");
		int quantidade = InputUtils.inputInt(in, "\033[0;93mInsira a quantidade disponivel em estoque: \033[1;93m");
		ItemCategoria categoria = inserirCategoria(in);
		BigDecimal preco = InputUtils.inputDec(in, "\033[0;93mInsira o preço do produto: \033[1;93m");

		ItemEsportivo itemEsportivo = new ItemEsportivo(nome, quantidade, preco, categoria);
		itemEsportivo.setUsuario(new Usuario(user));
		itemService.salvar(itemEsportivo);

		System.out.printf("\033[42m\n✅ O item %s foi cadastrado com sucesso!\n", nome);
	}

	public ItemCategoria inserirCategoria(Scanner in) {
		int categoria;
		do {
			MenuUtils.imprimirMenu("\033[0m\033[40m", "Categoria", Arrays.asList("N°", "Ações"),
					Arrays.asList(Arrays.asList("1", "Trajes Esportivos"), Arrays.asList("2", "Equipamentos de Treino"),
							Arrays.asList("3", "Outdoor"), Arrays.asList("4", "Acessorio de Proteção")));

			categoria = InputUtils.inputInt(in, "\033[0;93m\nInsira a categoria desejada: \033[1;93m");

			switch (categoria) {
			case 1:
				return ItemCategoria.TRAJES_ESPORTIVOS;
			case 2:
				return ItemCategoria.EQUIPAMENTOS_TREINO;
			case 3:
				return ItemCategoria.OUTDOOR;
			case 4:
				return ItemCategoria.ACESSORIOS_DE_PROTECAO;
			default:
				System.out.println("\033[41m❌ Opção inválida.");
				break;
			}
		} while (true);
	}

	public void gerenciarItensEsportivos(Scanner in, UsuarioDTO user) {
		List<ItemEsportivo> itensEsportivos = itemService.listarPorUsuario(user.getId());

		System.out.println("\033[45m\n════ Itens Esportivos Cadastrados ═══");
		if (itensEsportivos.size() > 0) {
			do {
				int item;
				MenuUtils.imprimirMenu("\n\033[0m\033[40m", "Gerenciamento", Arrays.asList("N°", "Ações"),
						Arrays.asList(Arrays.asList("1", "Repor Estoque"), Arrays.asList("2", "Alterar preco"),
								Arrays.asList("3", "Sair")));
				item = InputUtils.inputInt(in, "\033[0;93m\nInsira a opção desejada: \033[1;93m");

				switch (item) {
				case 1:
					reporEstoque(in, itensEsportivos);
					break;
				case 2:
					alterarPreco(in, itensEsportivos);
					break;
				case 3:
					return;
				default:
					System.out.println("\033[41m❌ Opção inválida.");
					break;
				}
			} while (true);
		} else {
			System.out.println("\n\033[41m❌ Não é possível gerenciar sem itens cadastrados.");
		}
	}

	public void listarItensEsportivos(List<ItemEsportivo> itensEsportivos) {
		if (itensEsportivos.size() > 0) {
			List<List<String>> lista = criarLista(itensEsportivos);
			MenuUtils.imprimirMenu("\033[0m\033[40m", "Itens Cadastrados",
					Arrays.asList("Id", "Nome", "Quantidade", "Preço", "Categoria", "Criação", "Atualização"), lista);
		} else {
			System.out.println("\n\033[41m❌ Não há itens cadastrados");
		}
	}

	public List<List<String>> criarLista(List<ItemEsportivo> itensEsportivos) {
		List<List<String>> lista = new ArrayList<>();
		for (ItemEsportivo itemEsportivo : itensEsportivos) {
			List<String> linha = Arrays.asList(String.valueOf(itemEsportivo.getId()), itemEsportivo.getNome(),
					String.valueOf(itemEsportivo.getQuantidade()), String.valueOf(itemEsportivo.getPreco()),
					itemEsportivo.getCategoria().toString(), itemEsportivo.getDataCriacao().toString(),
					itemEsportivo.getDataAtualizacao().toString());
			lista.add(linha);
		}
		return lista;
	}

	public void reporEstoque(Scanner in, List<ItemEsportivo> itensEsportivos) {
		listarItensEsportivos(itensEsportivos);
		int idEscolhido = InputUtils.inputInt(in, "Insira o id do produto: ");
		for (ItemEsportivo item : itensEsportivos) {
			if (item.getId() == idEscolhido) {
				int qtd = InputUtils.inputInt(in, "Quantidade para repor: ");
				item.setQuantidade(item.getQuantidade() + qtd);
				item.setDataAtualizacao();
				itemService.atualizar(item);

				List<ItemEsportivo> atualizados = itemService.listarPorUsuario(item.getUsuario().getId());
				listarItensEsportivos(atualizados);
				return;
			}
		}
	}

	public void alterarPreco(Scanner in, List<ItemEsportivo> itensEsportivos) {
		listarItensEsportivos(itensEsportivos);
		int idEscolhido = InputUtils.inputInt(in, "Insira o id do produto: ");
		for (ItemEsportivo item : itensEsportivos) {
			if (item.getId() == idEscolhido) {
				BigDecimal preco = InputUtils.inputDec(in, "Novo preço: ");
				item.setPreco(preco);
				item.setDataAtualizacao();
				itemService.atualizar(item);

				List<ItemEsportivo> atualizados = itemService.listarPorUsuario(item.getUsuario().getId());
				listarItensEsportivos(atualizados);
				return;
			}
		}
	}

	public List<List<String>> criarListaPorCategoria(ItemCategoria categoria, UsuarioDTO user) {
		List<ItemEsportivo> itensEsportivos = itemService.listarPorUsuario(user.getId());
		List<List<String>> lista = new ArrayList<>();

		for (ItemEsportivo item : itensEsportivos) {
			if (item.getCategoria() == categoria) {
				List<String> linha = Arrays.asList(String.valueOf(item.getId()), item.getNome(),
						String.valueOf(item.getQuantidade()), String.valueOf(item.getPreco()),
						item.getCategoria().toString(), item.getDataCriacao().toString(),
						item.getDataAtualizacao().toString());
				lista.add(linha);
			}
		}

		return lista;
	}

	public void prepararPedido() throws InterruptedException {
		List<Pedido> pedidos = pedidoService.listarPorStatusComItens(StatusPedido.PENDENTE);

		if (pedidos.isEmpty()) {
			System.err.println("\n\033[41m❌ Nenhum pedido pendente.");
			return;
		}

		for (Pedido pedido : pedidos) {
			System.out.printf("\nProcessando pedido #%d do(a) cliente %s...\n", pedido.getId(),
					pedido.getUsuario().getNome());

			separarPedido(pedido);

			Thread.sleep(2000);
			embalarPedido(pedido);

			Thread.sleep(2000);
			enviarPedido(pedido);
			gerarNumeroRastreamento(pedido);

			pedido.setStatus(StatusPedido.POSTADO);
			pedidoService.salvar(pedido);
			System.out.printf("\033[42m✅ Pedido #%d atualizado para ENVIADO com rastreamento: %s\n", pedido.getId(),
					pedido.getNumeroRastreamento());

			gerenciamentoLogistica(pedido);
		}
	}

	private void separarPedido(Pedido pedido) {
		System.out.println("📦 Separando itens...");
	}

	private void embalarPedido(Pedido pedido) {
		System.out.println("🎁 Embalando pedido...");
	}

	private void enviarPedido(Pedido pedido) {
		System.out.println("🚚 Enviando pedido...");
	}

	private void gerarNumeroRastreamento(Pedido pedido) {
		String codigo = "JSP" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		pedido.setNumeroRastreamento(codigo);
	}

	private void gerenciamentoLogistica(Pedido pedido) throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("🚚 Pedido a caminho...");
		pedido.setStatus(StatusPedido.EM_TRANSITO);
		pedidoService.salvar(pedido);

		Thread.sleep(3000);
		System.out.println("🚚 Atenção, o pedido está em rota de entrega!");
		pedido.setStatus(StatusPedido.ROTA_DE_ENTREGA);
		pedidoService.salvar(pedido);

		Thread.sleep(1000);
		System.out.println("🚚 Pedido entregue!");
		pedido.setStatus(StatusPedido.ENTREGUE);
		pedidoService.salvar(pedido);
	}

	public void exibirRelatorioDeVendas(UsuarioDTO vendedor) {
		List<Pedido> pedidos = pedidoService.listarTodosComItens();

		List<List<String>> tabelaItens = new ArrayList<>();
		BigDecimal totalVendas = calcularItensVendidos(pedidos, vendedor, tabelaItens);

		int pendentes = contarPorStatus(pedidos, StatusPedido.PENDENTE);
		int cancelados = contarPorStatus(pedidos, StatusPedido.CANCELADO);
		int emTransito = contarPorStatus(pedidos, StatusPedido.EM_TRANSITO);
		int entregues = contarPorStatus(pedidos, StatusPedido.ENTREGUE);
		int concluidos = contarPorStatus(pedidos, StatusPedido.CONCLUIDO);

		exibirItensVendidos(tabelaItens);
		exibirResumo(pendentes, cancelados, emTransito, concluidos, entregues, totalVendas);
	}

	private BigDecimal calcularItensVendidos(List<Pedido> pedidos, UsuarioDTO vendedor,
			List<List<String>> tabelaItens) {
		Map<String, Integer> contagemPorItem = new HashMap<>();
		Map<String, BigDecimal> valorPorItem = new HashMap<>();
		BigDecimal totalVendas = BigDecimal.ZERO;

		for (Pedido pedido : pedidos) {
			if (pedido.getStatus() != StatusPedido.ENTREGUE && pedido.getStatus() != StatusPedido.CONCLUIDO)
				continue;

			for (ItemPedido itemPedido : pedido.getItens()) {
				ItemEsportivo produto = itemPedido.getProduto();
				if (produto.getUsuario() != null && produto.getUsuario().getId().equals(vendedor.getId())) {
					String nome = produto.getNome();
					int quantidade = itemPedido.getQuantidade();

					if (quantidade <= 0)
						continue;

					BigDecimal precoUnitario = itemPedido.getPrecoUnitario();
					BigDecimal valorTotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));

					contagemPorItem.put(nome, contagemPorItem.getOrDefault(nome, 0) + quantidade);
					valorPorItem.put(nome, valorPorItem.getOrDefault(nome, BigDecimal.ZERO).add(valorTotal));

					totalVendas = totalVendas.add(valorTotal);
				}
			}
		}

		tabelaItens.clear();

		for (String nome : contagemPorItem.keySet()) {
			int quantidade = contagemPorItem.get(nome);
			if (quantidade > 0) {
				tabelaItens.add(Arrays.asList(nome, String.valueOf(quantidade),
						String.format("R$ %.2f", valorPorItem.get(nome))));
			}
		}

		return totalVendas;
	}

	private int contarPorStatus(List<Pedido> pedidos, StatusPedido status) {
		int total = 0;
		for (Pedido pedido : pedidos) {
			if (pedido.getStatus() == status)
				total++;
		}
		return total;
	}

	private void exibirItensVendidos(List<List<String>> tabelaItens) {
		System.out.println("\n\033[0m📊 Relatório de Vendas do Vendedor:");
		if (!tabelaItens.isEmpty()) {
			MenuUtils.imprimirMenu("\033[30;42m", "Itens Vendidos",
					Arrays.asList("Produto", "Quantidade", "Valor Total"), tabelaItens);
		} else {
			System.err.println("\n\033[41m❌ Não há itens vendidos.");
		}
	}

	private void exibirResumo(int pendentes, int cancelados, int emTransito, int concluidos, int entregues, BigDecimal totalVendas) {
		List<List<String>> resumo = Arrays.asList(Arrays.asList("Pedidos Pendentes", String.valueOf(pendentes)),
				Arrays.asList("Pedidos Cancelados", String.valueOf(cancelados)),
				Arrays.asList("Pedidos em transito", String.valueOf(emTransito)),
				Arrays.asList("Pedidos entregues", String.valueOf(entregues)),
				Arrays.asList("Pedidos concluidos", String.valueOf(concluidos)),
				Arrays.asList("Valor Total de Vendas", "R$ " + totalVendas.toString()));

		MenuUtils.imprimirMenu("\033[30;46m", "Resumo de Pedidos", Arrays.asList("Indicador", "Valor"), resumo);
	}

}
