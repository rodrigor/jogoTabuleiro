package br.ufpb.dcx.rodrigor.poo.jogodamas;

import br.ufpb.dcx.rodrigor.poo.jogotabuleiro.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegraDamasMovimentoDamaTest {


    JogoDamas jogo;
    RegraDamasMovimentoDama regraDama;
    Tabuleiro tabuleiro;


    PecaTabuleiro peca;
    @BeforeEach
    void setUp() {
        jogo = new JogoDamas();
        regraDama = new RegraDamasMovimentoDama();
        peca = new PecaTabuleiro(Cor.PRETO, regraDama, PosicaoInicio.TOP);
        tabuleiro = jogo.getTabuleiro();
    }

    @Test
    void movimentoDiagonalSemCaptura() {
        PecaTabuleiro peca = new PecaTabuleiro(Cor.BRANCO, regraDama,PosicaoInicio.BOTTOM);
        tabuleiro.getCasa(5,3).get().setPeca(peca);
        //    0   1   2   3   4   5   6   7
        //  ┌───┬───┬───┬───┬───┬───┬───┬───┐
        //0 │███│   │███│   │███│   │███│   │
        //1 │   │███│   │███│   │███│   │███│
        //2 │███│   │███│   │███│   │███│   │
        //3 │   │███│   │███│   │███│   │███│
        //4 │███│   │███│   │███│   │███│   │
        //5 │   │███│   │█◯█│   │███│   │███│
        //6 │███│   │███│   │███│   │███│   │
        //7 │   │███│   │███│   │███│   │███│
        //  └───┴───┴───┴───┴───┴───┴───┴───┘

        assertDoesNotThrow(()-> tabuleiro.mover(new Posicao(5,3),new Posicao(2,0)));
        assertEquals(peca,tabuleiro.getCasa(2,0).get().getPeca());
        assertDoesNotThrow(()-> tabuleiro.mover(new Posicao(2,0),new Posicao(0,2)));
        assertEquals(peca,tabuleiro.getCasa(0,2).get().getPeca());
        assertDoesNotThrow(()-> tabuleiro.mover(new Posicao(0,2),new Posicao(5,7)));
        assertEquals(peca,tabuleiro.getCasa(5,7).get().getPeca());
        assertDoesNotThrow(()-> tabuleiro.mover(new Posicao(5,7),new Posicao(2,4)));
        assertEquals(peca,tabuleiro.getCasa(2,4).get().getPeca());
        assertThrows(MovimentoInvalidoException.class, () -> tabuleiro.mover(new Posicao(2,4),new Posicao(4,4)));

    }
}