package br.ufpb.dcx.rodrigor.poo.jogotabuleiro;

public interface RegraMovimentacao {

    boolean movimentoValido(Posicao origem, Posicao destino, Tabuleiro tabuleiro) throws MovimentoInvalidoException;
}
