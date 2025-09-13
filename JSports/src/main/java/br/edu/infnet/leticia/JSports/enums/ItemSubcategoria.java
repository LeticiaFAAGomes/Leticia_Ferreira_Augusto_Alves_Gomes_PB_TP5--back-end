package br.edu.infnet.leticia.JSports.enums;

public enum ItemSubcategoria {
	
    CAMISETA(ItemCategoria.TRAJES_ESPORTIVOS),
    CALCA(ItemCategoria.TRAJES_ESPORTIVOS),
    JAQUETA(ItemCategoria.TRAJES_ESPORTIVOS),

    PESO(ItemCategoria.EQUIPAMENTOS_TREINO),
    CORDA(ItemCategoria.EQUIPAMENTOS_TREINO),
    ESTEIRA(ItemCategoria.EQUIPAMENTOS_TREINO),

    BARRACA(ItemCategoria.OUTDOOR),
    MOCHILA(ItemCategoria.OUTDOOR),
    LANTERNA(ItemCategoria.OUTDOOR),

    CAPACETE(ItemCategoria.ACESSORIOS_DE_PROTECAO),
    JOELHEIRA(ItemCategoria.ACESSORIOS_DE_PROTECAO),
    COTOVELEIRA(ItemCategoria.ACESSORIOS_DE_PROTECAO);
	
    private final ItemCategoria categoria;

    ItemSubcategoria(ItemCategoria categoria) {
        this.categoria = categoria;
    }

    public ItemCategoria getCategoria() {
        return categoria;
    }
}
