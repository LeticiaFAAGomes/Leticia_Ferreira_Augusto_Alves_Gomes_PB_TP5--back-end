package br.edu.infnet.leticia.JSports.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import br.edu.infnet.leticia.JSports.enums.ItemCategoria;
import br.edu.infnet.leticia.JSports.enums.ItemSubcategoria;
import br.edu.infnet.leticia.JSports.model.domain.ItemEsportivo;
import br.edu.infnet.leticia.JSports.utils.InputUtils;
import br.edu.infnet.leticia.JSports.utils.MenuUtils;

@Component
public class VendedorView {

	private List<ItemEsportivo> itensEsportivos = new ArrayList<>();

	public void iniciarMenuVendedor(Scanner in) {

		int opcao;
		do {
			MenuUtils.imprimirMenu("\033[0m\033[33m", "Vendedor JSports", Arrays.asList("N°", "Ações"),
					Arrays.asList(Arrays.asList("1", "Cadastrar Produto"), Arrays.asList("2", "Gerenciar Produtos"),
							Arrays.asList("3", "Preparar Pedido"), Arrays.asList("4", "Comprar como Cliente"),
							Arrays.asList("0", "Sair")));

			opcao = InputUtils.inputInt(in, "\033[0;93m\nInsira sua opção: \033[1;93m");

			switch (opcao) {
			case 1:
				cadastrarItemEsportivo(in);
				break;
			case 2:
				gerenciarItensEsportivos(in);
				break;
			case 3:
				System.out.println("Preparar Pedido");
				break;
			case 4:
				System.out.println("Comprar como Cliente");
				break;
			case 0:
				return;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		} while (opcao != 0);
	}

	public void cadastrarItemEsportivo(Scanner in) {

		System.out.println("\033[45m\n════ Cadastro de Item Esportivo ═══");
		String nome = InputUtils.inputStr(in, "\033[0;93mInsira o nome do item esportivo: \033[1;93m");
		int quantidade = InputUtils.inputInt(in, "\033[0;93mInsira a quantidade disponivel em estoque: \033[1;93m");
		ItemCategoria categoria = inserirCategoria(in);
		ItemSubcategoria subcategoria = inserirSubcategoria(in, categoria);
		BigDecimal preco = InputUtils.inputDec(in, "\033[0;93mInsira o preço do produto: \033[1;93m");
		ItemEsportivo itemEsportivo = new ItemEsportivo(nome, quantidade, preco, categoria, subcategoria);
		itensEsportivos.add(itemEsportivo);

		System.out.printf("\n✅ O item %s foi cadastrado com sucesso!\n", nome);
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
				System.out.println("Opção inválida.");
			}
		} while (true);
	}

	public ItemSubcategoria inserirSubcategoria(Scanner in, ItemCategoria categoria) {

		if (categoria == ItemCategoria.TRAJES_ESPORTIVOS) {
			return inserirSubcategoriaTraje(in, categoria);

		} else if (categoria == ItemCategoria.EQUIPAMENTOS_TREINO) {
			return inserirSubcategoriaEquipamento(in, categoria);

		} else if (categoria == ItemCategoria.OUTDOOR) {
			return inserirSubcategoriaOutdoor(in, categoria);
		} else {
			return inserirSubcategoriaAcessorio(in, categoria);
		}

	}

	public ItemSubcategoria inserirSubcategoriaTraje(Scanner in, ItemCategoria categoria) {
		do {
			int subcategoria;
			MenuUtils.imprimirMenu("\033[0m\033[40m", "Subcategoria", Arrays.asList("N°", "Ações"), Arrays.asList(
					Arrays.asList("1", "Camiseta"), Arrays.asList("2", "Calça"), Arrays.asList("3", "Jaqueta")));

			subcategoria = InputUtils.inputInt(in, "\033[0;93m\nInsira a categoria desejada: \033[1;93m");

			switch (subcategoria) {
			case 1:
				return ItemSubcategoria.CAMISETA;
			case 2:
				return ItemSubcategoria.CALCA;
			case 3:
				return ItemSubcategoria.JAQUETA;
			default:
				System.out.println("Opção inválida.");
			}
		} while (true);
	}

	public ItemSubcategoria inserirSubcategoriaEquipamento(Scanner in, ItemCategoria categoria) {
		do {
			int subcategoria;
			MenuUtils.imprimirMenu("\n\033[0m\033[40m", "Subcategoria", Arrays.asList("N°", "Ações"), Arrays
					.asList(Arrays.asList("1", "Peso"), Arrays.asList("2", "Corda"), Arrays.asList("3", "Esteira")));

			subcategoria = InputUtils.inputInt(in, "\033[0;93m\nInsira a categoria desejada: \033[1;93m");

			switch (subcategoria) {
			case 1:
				return ItemSubcategoria.PESO;
			case 2:
				return ItemSubcategoria.CORDA;
			case 3:
				return ItemSubcategoria.ESTEIRA;
			default:
				System.out.println("Opção inválida.");
			}
		} while (true);
	}

	public ItemSubcategoria inserirSubcategoriaOutdoor(Scanner in, ItemCategoria categoria) {
		do {
			int subcategoria;
			MenuUtils.imprimirMenu("\n\033[0m\033[40m", "Subcategoria", Arrays.asList("N°", "Ações"), Arrays.asList(
					Arrays.asList("1", "Barraca"), Arrays.asList("2", "Mochila"), Arrays.asList("3", "Lanterna")));

			subcategoria = InputUtils.inputInt(in, "\033[0;93m\nInsira a categoria desejada: \033[1;93m");

			switch (subcategoria) {
			case 1:
				return ItemSubcategoria.BARRACA;
			case 2:
				return ItemSubcategoria.MOCHILA;
			case 3:
				return ItemSubcategoria.LANTERNA;
			default:
				System.out.println("Opção inválida.");
			}
		} while (true);
	}

	public ItemSubcategoria inserirSubcategoriaAcessorio(Scanner in, ItemCategoria categoria) {
		do {
			int subcategoria;
			MenuUtils.imprimirMenu("\n\033[0m\033[40m", "Subcategoria", Arrays.asList("N°", "Ações"),
					Arrays.asList(Arrays.asList("1", "Capacete"), Arrays.asList("2", "Cotoveleira"),
							Arrays.asList("3", "Joelheira")));

			subcategoria = InputUtils.inputInt(in, "\033[0;93m\nInsira a categoria desejada: \033[1;93m");

			switch (subcategoria) {
			case 1:
				return ItemSubcategoria.CAPACETE;
			case 2:
				return ItemSubcategoria.COTOVELEIRA;
			case 3:
				return ItemSubcategoria.JOELHEIRA;
			default:
				System.out.println("Opção inválida.");
			}
		} while (true);
	}

	public void gerenciarItensEsportivos(Scanner in) {

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
					reporEstoque(in);
					break;
				case 2:
					alterarPreco(in);
					break;
				case 3:
					return;
				default:
					System.out.println("Opção inválida.");
				}
			} while (true);
		} else {
			System.out.println("\nNão é possível gerenciar sem itens cadastrados.");
		}

	}

	public void listarItensEsportivos() {

		if (itensEsportivos.size() > 0) {
			List<List<String>> lista = new ArrayList<List<String>>();
			Long c = 1L;
			for (ItemEsportivo itemEsportivo : itensEsportivos) {
				itemEsportivo.setId(c);
				List<String> linha = Arrays.asList(String.valueOf(itemEsportivo.getId()), itemEsportivo.getNome(),
						String.valueOf(itemEsportivo.getQuantidade()), String.valueOf(itemEsportivo.getPreco()),
						itemEsportivo.getCategoria().toString(), itemEsportivo.getSubcategoria().toString(),
						itemEsportivo.getDataCriacao().toString(), itemEsportivo.getDataAtualizacao().toString());
				lista.add(linha);
				c++;
			}
			MenuUtils.imprimirMenu("\033[0m\033[40m", "Itens Cadastrados", Arrays.asList("Id", "Nome", "Quantidade",
					"Preço", "Categoria", "Subcategoria", "Criação", "Atualização"), lista);
		} else {
			System.out.println("\nNão há itens cadastrados");
		}

	}

	public void reporEstoque(Scanner in) {
		do {
			int idEscolhido;
			listarItensEsportivos();
			idEscolhido = InputUtils.inputInt(in,
					"\033[0;93m\nInsira o id do produto que desejada alterar: \033[1;93m");
			for (ItemEsportivo item : itensEsportivos) {
				if (item.getId() == idEscolhido) {
					int qtd = InputUtils.inputInt(in, "\033[0;93m\nInsira a quantidade para repor: \033[1;93m");
					int resultado = item.getQuantidade() + qtd;
					item.setDataAtualizacao();
					item.setQuantidade(resultado);
					listarItensEsportivos();
					return;
				}
			}

		} while (true);
	}

	public void alterarPreco(Scanner in) {
		do {
			int idEscolhido;
			listarItensEsportivos();
			idEscolhido = InputUtils.inputInt(in,
					"\033[0;93m\nInsira o id do produto que desejada alterar: \033[1;93m");
			for (ItemEsportivo item : itensEsportivos) {
				if (item.getId() == idEscolhido) {
					BigDecimal preco = InputUtils.inputDec(in, "\033[0;93m\nInsira o novo preço: \033[1;93m");
					item.setDataAtualizacao();
					item.setPreco(preco);
					listarItensEsportivos();
					return;
				}
			}

		} while (true);
	}

}
