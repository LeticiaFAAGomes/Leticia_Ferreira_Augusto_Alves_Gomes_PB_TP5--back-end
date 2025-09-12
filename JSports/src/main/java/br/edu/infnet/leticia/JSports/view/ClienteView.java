package br.edu.infnet.leticia.JSports.view;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import br.edu.infnet.leticia.JSports.utils.InputUtils;

@Component
public class ClienteView {
	
	public void iniciarMenuCliente(Scanner in) {
		int opcao;
		do {
			System.out.print("\033[0m\033[32m┌═══════ Categorias JSports ═════┐");
			System.out.format("\n│ %-1s | %-25s │", "N°", "Ações");
			System.out.print("\n├────┼───────────────────────────┤");
			System.out.format("\n│ %-30s │\n│ %-30s │\n│ %-30s │\n|%-31s │\n|%-31s │\n", "1  | Trajes Esportivos",
					"2  | Equipamentos de Treino", "3  | Outdoor", " 4  | Proteção e Acessórios", " 0  | Sair");
			System.out.print("└────┴───────────────────────────┘");

			opcao = InputUtils.inputInt(in, "\033[0;93m\nInsira sua opção: \033[1;93m");

			switch (opcao) {
			case 1 -> System.out.print("Trajes Esportivos");
			case 2 -> System.out.print("Equipamentos de Treino");
			case 3 -> System.out.print("Outdoor");
			case 4 -> System.out.print("Proteção e Acessórios");
			default -> System.out.println("Opção inválida.");
			}
		} while (opcao != 0);
	}
}
