package br.edu.infnet.leticia.JSports.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import br.edu.infnet.leticia.JSports.enums.ItemCategoria;
import br.edu.infnet.leticia.JSports.enums.ItemSubcategoria;
import br.edu.infnet.leticia.JSports.model.domain.ItemEsportivo;
import br.edu.infnet.leticia.JSports.utils.InputUtils;

@Component
public class VendedorView {
	
	private List<ItemEsportivo> produtos = new ArrayList<>();
	
	public void iniciarMenuVendedor(Scanner in) {
		int opcao;
		do {
			System.out.print("\033[0m\033[33m\n┌═══════ Vendedor JSports ═══════┐");
			System.out.format("\n│ %-1s | %-25s │", "N°", "Ações");
			System.out.print("\n├────┼───────────────────────────┤");
			System.out.format("\n│ %-30s │\n│ %-30s │\n│ %-30s │\n|%-31s │\n|%-31s │\n", "1  | Cadastrar Produto",
					"2  | Gerenciar Produtos", "3  | Preparar Pedido", " 4  | Comprar como Cliente", " 0  | Sair");
			System.out.print("└────┴───────────────────────────┘");

			opcao = InputUtils.inputInt(in, "\033[0;93m\nInsira sua opção: \033[1;93m");

			switch (opcao) {
			case 1: 
				cadastrarItemEsportivo(in);
				break;
			case 2: 
				System.out.println("Gerenciar Produtos");
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
		
		produtos.add(itemEsportivo);
		
		System.out.printf("\n✅ O item %s foi cadastrado com sucesso!\n", nome);
	}
	
	public ItemCategoria inserirCategoria(Scanner in) {
		
		int categoria;
		
		do {
			System.out.print("\n\033[0m\033[40m┌══════ Categoria JSports ═══════┐");
			System.out.format("\n│ %-1s | %-25s │", "N°", "Ações");
			System.out.print("\n├────┼───────────────────────────┤");
			System.out.format("\n│ %-30s │\n│ %-30s │\n│ %-30s │\n|%-31s │\n", "1  | Trajes Esportivos",
					"2  | Equipamentos de Treino", "3  | Outdoor", " 4  | Acessorio de Proteção");
			System.out.print("└────┴───────────────────────────┘");

			categoria = InputUtils.inputInt(in, "\033[0;93m\nInsira a categoria desejada: \033[1;93m");

			switch (categoria) {
			case 1: 
				return  ItemCategoria.TRAJES_ESPORTIVOS;
			case 2: 
				return  ItemCategoria.EQUIPAMENTOS_TREINO;
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
			System.out.print("\n\033[0m\033[40m┌══════ Subcategoria JSports ═════┐");
			System.out.format("\n│ %-1s | %-25s │", "N°", "Ações");
			System.out.print("\n├────┼───────────────────────────┤");
			System.out.format("\n│ %-30s │\n│ %-30s │\n│ %-30s │\n", "1  | Camiseta",
					"2  | Calça", "3  | Jaqueta");
			System.out.print("└────┴───────────────────────────┘");

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
			System.out.print("\n\033[0m\033[40m┌══════ Subcategoria JSports ═══════┐");
			System.out.format("\n│ %-1s | %-25s │", "N°", "Ações");
			System.out.print("\n├────┼───────────────────────────┤");
			System.out.format("\n│ %-30s │\n│ %-30s │\n│ %-30s │\n", "1  | Peso",
					"2  | Corda", "3  | Esteira");
			System.out.print("└────┴───────────────────────────┘");

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
			System.out.print("\n\033[0m\033[40m┌══════ Subcategoria JSports ═══════┐");
			System.out.format("\n│ %-1s | %-25s │", "N°", "Ações");
			System.out.print("\n├────┼───────────────────────────┤");
			System.out.format("\n│ %-30s │\n│ %-30s │\n│ %-30s │\n", "1  | Barraca",
					"2  | Mochila", "3  | Lanterna");
			System.out.print("└────┴───────────────────────────┘");

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
			System.out.print("\n\033[0m\033[40m┌═════ Subcategoria JSports ══════┐");
			System.out.format("\n│ %-1s | %-25s │", "N°", "Ações");
			System.out.print("\n├────┼───────────────────────────┤");
			System.out.format("\n│ %-30s │\n│ %-30s │\n│ %-30s │\n", "1  | Capacete",
					"2  | Cotoveleira", "3  | Joelheira");
			System.out.print("└────┴───────────────────────────┘");

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
}
