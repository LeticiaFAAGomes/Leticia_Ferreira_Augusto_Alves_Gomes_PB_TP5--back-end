package br.edu.infnet.leticia.JSports.utils;

import java.util.List;


public class MenuUtils {

    public static void imprimirMenu(String cor, String titulo, List<String> cabecalhos, List<List<String>> opcoes) {
    	
        int numColunas = cabecalhos.size();
        int[] larguras = new int[numColunas];

        larguras = definirLarguraMinimaCabecalho(cabecalhos, numColunas, larguras);
        larguras = definirLarguraMinimaOpcoes(opcoes, larguras);
        int larguraTotal = calcularLarguraTotal(numColunas, larguras);

        String tituloFormatado = centralizarTitulo(titulo, larguraTotal);
        String linhaTopo = "┌" + "═".repeat(larguraTotal- 2) + "┐";
        String linhaBase = "└" + "═".repeat(larguraTotal- 2) + "┘";

        StringBuilder separador = separarSecoes(larguras);
        separador.setCharAt(separador.length() - 1, '┤');

        System.out.println(cor + linhaTopo);
        System.out.println("│" + tituloFormatado + "│");
        System.out.println(separador);

        System.out.print("│");
        imprimirCabecalho(numColunas, larguras, cabecalhos);
        System.out.println();
        System.out.println(separador);

        imprimirOpcoes(opcoes, numColunas, larguras);

        System.out.println(linhaBase + "\033[0m");
    }
    
    public static int[] definirLarguraMinimaCabecalho(List<String> cabecalhos, int numColunas, int[] larguras) {
        for (int i = 0; i < numColunas; i++) {
            larguras[i] = cabecalhos.get(i).length();
        }
        
        return larguras;
    }
    
    public static int[] definirLarguraMinimaOpcoes(List<List<String>> opcoes, int[] larguras) {
        for (List<String> linha : opcoes) {
            for (int i = 0; i < linha.size(); i++) {
                larguras[i] = Math.max(larguras[i], linha.get(i).length());
            }
        }
        return larguras;
    }
    
    public static int calcularLarguraTotal(int numColunas, int[] larguras) {
    	
        int larguraTotal = numColunas * 3 + 1; 
        for (int largura : larguras) {
            larguraTotal += largura;
        }
        
        return larguraTotal;
    }
    
    public static String centralizarTitulo(String titulo, int larguraTotal) {
    	
        int espacos = larguraTotal - titulo.length();
        int esquerda = espacos / 2;
        int direita = espacos - esquerda;

    	//return " ".repeat(esquerda - 2) + titulo + " ".repeat(direita);
        return " ".repeat(Math.max(0, esquerda - 2)) + titulo + " ".repeat(Math.max(0, direita));

    }
    
    public static StringBuilder separarSecoes(int[] larguras) {
        StringBuilder separador = new StringBuilder("├");
        for (int largura : larguras) {
            separador.append("─".repeat(largura + 2)).append("┼");
        }
        
		return separador;
    }
    
    public static void imprimirCabecalho(int numColunas, int[] larguras, List<String> cabecalhos) {
        for (int i = 0; i < numColunas; i++) {
            System.out.printf(" %-" + larguras[i] + "s │", cabecalhos.get(i));
        }
    }
    
    public static void imprimirOpcoes(List<List<String>> opcoes, int numColunas, int[] larguras) {
        for (List<String> linha : opcoes) {
            System.out.print("│");
            for (int i = 0; i < numColunas; i++) {
                System.out.printf(" %-" + larguras[i] + "s │", linha.get(i));
            }
            System.out.println();
        }
    }
    
    
}
