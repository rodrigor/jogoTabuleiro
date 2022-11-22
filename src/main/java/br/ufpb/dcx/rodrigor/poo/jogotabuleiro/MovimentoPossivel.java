package br.ufpb.dcx.rodrigor.poo.jogotabuleiro;

@FunctionalInterface
public interface MovimentoPossivel {

    public boolean movimentoValido(Posicao origem, Posicao destino);

}
