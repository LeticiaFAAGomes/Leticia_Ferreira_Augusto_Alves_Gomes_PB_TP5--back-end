package br.edu.infnet.leticia.JSports.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {

	public static String inputStr(Scanner in, String enunciado) {

		String str = "";

		do {

			System.out.print(enunciado);
			str = in.nextLine();

		} while (str.length() <= 0);

		return str;
	}

	public static int inputInt(Scanner in, String mensagem) {
		
		int valor;
		
		while (true) {
			try {
				System.out.print(mensagem);
				valor = in.nextInt();
				in.nextLine();
				return valor;
			} catch (InputMismatchException e) {
				System.out.println("\033[41m❌ Entrada inválida. Digite um número inteiro.");
				in.nextLine();
			}
		}
	}
}
