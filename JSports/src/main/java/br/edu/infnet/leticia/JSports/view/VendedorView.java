package br.edu.infnet.leticia.JSports.view;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import br.edu.infnet.leticia.JSports.utils.InputUtils;

@Component
public class VendedorView {
	
	public void iniciarMenuVendedor(Scanner in) {
		int opcao;
		do {
			System.out.print("\033[0m\033[33m┌═══════ Vendedor JSports ═══════┐");
			System.out.format("\n│ %-1s | %-25s │", "N°", "Ações");
			System.out.print("\n├────┼───────────────────────────┤");
			System.out.format("\n│ %-30s │\n│ %-30s │\n│ %-30s │\n|%-31s │\n|%-31s │\n", "1  | Cadastrar Produto",
					"2  | Gerenciar Produtos", "3  | Preparar Pedido", " 4  | Comprar como Cliente", " 0  | Sair");
			System.out.print("└────┴───────────────────────────┘");

			opcao = InputUtils.inputInt(in, "\033[0;93m\nInsira sua opção: \033[1;93m");

			switch (opcao) {
			case 1 -> System.out.println("Cadastrar Produto");
			case 2 -> System.out.println("Gerenciar Produtos");
			case 3 -> System.out.println("Preparar Pedido");
			case 4 -> System.out.println("Comprar como Cliente");
			default -> System.out.println("Opção inválida.");
			}
		} while (opcao != 0);
	}
}
