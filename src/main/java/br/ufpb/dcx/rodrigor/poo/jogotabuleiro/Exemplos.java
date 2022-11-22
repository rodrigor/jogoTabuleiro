package br.ufpb.dcx.rodrigor.poo.jogotabuleiro;

import br.ufpb.dcx.rodrigor.poo.jogodamas.RegraDamasMovimentoSimples;

import static br.ufpb.dcx.rodrigor.poo.jogotabuleiro.PosicaoInicio.TOP;

public class Exemplos {

    public static void main(String[] args) throws MovimentoInvalidoException {

        PecaTabuleiro peca = new PecaTabuleiro(Cor.PRETO, new RegraDamasMovimentoSimples(), TOP);

        Tabuleiro tabuleiro = new Tabuleiro();

        tabuleiro.mover(new Posicao(0,0), new Posicao(0,3));

    }
}
