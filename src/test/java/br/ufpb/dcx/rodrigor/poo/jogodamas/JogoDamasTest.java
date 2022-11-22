package br.ufpb.dcx.rodrigor.poo.jogodamas;

import br.ufpb.dcx.rodrigor.poo.jogotabuleiro.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JogoDamasTest {

    JogoDamas jogoDamas;

    @BeforeEach
    void setup(){
        jogoDamas = new JogoDamas();
    }

    /**
     *     0   1   2   3   4   5   6   7
     *   ┌───┬───┬───┬───┬───┬───┬───┬───┐
     * 0 │█●█│   │█●█│   │█●█│   │█●█│   │
     * 1 │   │█●█│   │█●█│   │█●█│   │█●█│
     * 2 │█●█│   │█●█│   │█●█│   │█●█│   │
     * 3 │   │███│   │███│   │███│   │███│
     * 4 │███│   │███│   │███│   │███│   │
     * 5 │   │█◯█│   │█◯█│   │█◯█│   │█◯█│
     * 6 │█◯█│   │█◯█│   │█◯█│   │█◯█│   │
     * 7 │   │█◯█│   │█◯█│   │█◯█│   │█◯█│
     *   └───┴───┴───┴───┴───┴───┴───┴───┘
     */
    @Test
    void fazerSetupDamas() {

        jogoDamas.fazerSetup();
        Tabuleiro tabDamas = jogoDamas.getTabuleiro();

        //testando o tabuleiro com as peças
        PecaTabuleiro pretaTop = new PecaTabuleiro(Cor.PRETO,new RegraDamasMovimentoSimples(), PosicaoInicio.TOP);
        PecaTabuleiro brancaBottom = new PecaTabuleiro(Cor.BRANCO,new RegraDamasMovimentoSimples(), PosicaoInicio.BOTTOM);
        for (int l = 0; l < tabDamas.getTamanho(); l++) {
            for (int c = 0; c < tabDamas.getTamanho(); c++) {
                Posicao posicao = new Posicao(l,c);
                if(((l + c) % 2) == 0){
                    if(l < 3) { // primeiras 3 linhas no TOPO
                        assertEquals(pretaTop, tabDamas.getCasa(posicao).get().getPeca(),posicao.toString());
                    } else if (l < 5) { // duas linhas no meio: sem peças
                        assertFalse(tabDamas.getCasa(posicao).get().temPeca(),posicao.toString());
                    } else { // últimas 3 linhas de baixo: peças brancas
                        assertEquals(brancaBottom, tabDamas.getCasa(posicao).get().getPeca(),posicao.toString());
                    }
                } else {
                    assertFalse(tabDamas.getCasa(posicao).get().temPeca(),posicao.toString()+tabDamas.getCasa(posicao));
                }
            }
        }

    }
}