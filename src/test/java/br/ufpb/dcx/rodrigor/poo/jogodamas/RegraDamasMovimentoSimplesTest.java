package br.ufpb.dcx.rodrigor.poo.jogodamas;

import br.ufpb.dcx.rodrigor.poo.jogotabuleiro.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegraDamasMovimentoSimplesTest {


    JogoDamas jogo;
    RegraDamasMovimentoSimples regraSimoples;
    Tabuleiro tabuleiro;


    PecaTabuleiro peca;
    @BeforeEach
    void setUp() {
        jogo = new JogoDamas();
        regraSimoples = new RegraDamasMovimentoSimples();
        peca = new PecaTabuleiro(Cor.PRETO,regraSimoples,PosicaoInicio.TOP);
        tabuleiro = jogo.getTabuleiro();
    }


    @Test
    void movimentoInvalidoPecasVizinhas(){
        PecaTabuleiro peca = new PecaTabuleiro(Cor.BRANCO,regraSimoples,PosicaoInicio.BOTTOM);
        tabuleiro.getCasa(3,3).get().setPeca(peca);
        tabuleiro.getCasa(4,2).get().setPeca(new PecaTabuleiro(Cor.PRETO,regraSimoples,PosicaoInicio.TOP));
        tabuleiro.getCasa(4,4).get().setPeca(new PecaTabuleiro(Cor.PRETO,regraSimoples,PosicaoInicio.TOP));
        tabuleiro.getCasa(2,2).get().setPeca(new PecaTabuleiro(Cor.PRETO,regraSimoples,PosicaoInicio.TOP));
        tabuleiro.getCasa(2,4).get().setPeca(new PecaTabuleiro(Cor.PRETO,regraSimoples,PosicaoInicio.TOP));

        //    0   1   2   3   4   5   6   7
        //  ┌───┬───┬───┬───┬───┬───┬───┬───┐
        //0 │███│   │███│   │███│   │███│   │
        //1 │   │███│   │███│   │███│   │███│
        //2 │███│   │█●█│   │█●█│   │███│   │
        //3 │   │███│   │█◯█│   │███│   │███│
        //4 │███│   │█●█│   │█●█│   │███│   │
        //5 │   │███│   │███│   │███│   │███│
        //6 │███│   │███│   │███│   │███│   │
        //7 │   │███│   │███│   │███│   │███│
        //  └───┴───┴───┴───┴───┴───┴───┴───┘

        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(3,3), new Posicao(2,2)));
        assertEquals(peca,tabuleiro.getCasa(3,3).get().getPeca());
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(3,3), new Posicao(2,4)));
        assertEquals(peca,tabuleiro.getCasa(3,3).get().getPeca());
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(3,3), new Posicao(4,2)));
        assertEquals(peca,tabuleiro.getCasa(3,3).get().getPeca());
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(3,3), new Posicao(4,4)));
        assertEquals(peca,tabuleiro.getCasa(3,3).get().getPeca());


    }


    @Test
    void movimentoSimplesPecaBrancaSubindo() {

        //Testando movimento de peça branca, subindo no tabuleiro.

        PecaTabuleiro peca = new PecaTabuleiro(Cor.BRANCO,regraSimoples,PosicaoInicio.BOTTOM);
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

        // a peça não pode andar pra trás
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(5,3), new Posicao(6,2)));
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(5,3), new Posicao(6,4)));
        // a peça pode andar pra frente, nas diagonais livres
        assertDoesNotThrow(() -> tabuleiro.mover(new Posicao(5,3), new Posicao(4,2)));
        assertEquals(peca,tabuleiro.getCasa(4,2).get().getPeca());
        assertDoesNotThrow(() -> tabuleiro.mover(new Posicao(4,2), new Posicao(3,3)));
        assertEquals(peca,tabuleiro.getCasa(3,3).get().getPeca());
        //a peça não pode mover ortogonalmente pra frente
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(3,3), new Posicao(4,3)));

    }

    @Test
    void movimentoSimplesPecaPretaDescendo() {

        //Testando movimento de peça preta, descendo no tabuleiro.

        PecaTabuleiro peca = new PecaTabuleiro(Cor.PRETO,regraSimoples,PosicaoInicio.TOP);
        tabuleiro.getCasa(3,3).get().setPeca(peca);
        //    0   1   2   3   4   5   6   7
        //  ┌───┬───┬───┬───┬───┬───┬───┬───┐
        //0 │███│   │███│   │███│   │███│   │
        //1 │   │███│   │███│   │███│   │███│
        //2 │███│   │███│   │███│   │███│   │
        //3 │   │███│   │█●█│   │███│   │███│
        //4 │███│   │███│   │███│   │███│   │
        //5 │   │███│   │███│   │███│   │███│
        //6 │███│   │███│   │███│   │███│   │
        //7 │   │███│   │███│   │███│   │███│
        //  └───┴───┴───┴───┴───┴───┴───┴───┘

        // a peça não pode andar pra trás
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(3,3), new Posicao(2,2)));
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(3,3), new Posicao(2,4)));
        // a peça pode andar pra frente, nas diagonais livres
        assertDoesNotThrow(() -> tabuleiro.mover(new Posicao(3,3), new Posicao(4,2)));
        assertEquals(peca,tabuleiro.getCasa(4,2).get().getPeca());
        assertDoesNotThrow(() -> tabuleiro.mover(new Posicao(4,2), new Posicao(5,3)));
        assertEquals(peca,tabuleiro.getCasa(5,3).get().getPeca());
        //a peça não pode mover ortogonalmente pra frente
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(5,3), new Posicao(6,3)));

    }

    @Test
    void testeMovimentoCapturaPretoTop(){
        //Testando movimento de peça preta, descendo no tabuleiro.

        PecaTabuleiro peca = new PecaTabuleiro(Cor.PRETO,regraSimoples,PosicaoInicio.TOP);
        tabuleiro.getCasa(3,3).get().setPeca(peca);
        tabuleiro.getCasa(4,2).get().setPeca(new PecaTabuleiro(Cor.BRANCO,regraSimoples,PosicaoInicio.BOTTOM));
        tabuleiro.getCasa(4,4).get().setPeca(new PecaTabuleiro(Cor.BRANCO,regraSimoples,PosicaoInicio.BOTTOM));
        tabuleiro.getCasa(2,2).get().setPeca(new PecaTabuleiro(Cor.BRANCO,regraSimoples,PosicaoInicio.BOTTOM));
        tabuleiro.getCasa(2,4).get().setPeca(new PecaTabuleiro(Cor.BRANCO,regraSimoples,PosicaoInicio.BOTTOM));
        //    0   1   2   3   4   5   6   7
        //  ┌───┬───┬───┬───┬───┬───┬───┬───┐
        //0 │███│   │███│   │███│   │███│   │
        //1 │   │███│   │███│   │███│   │███│
        //2 │███│   │█◯█│   │█◯█│   │███│   │
        //3 │   │███│   │█●█│   │███│   │███│
        //4 │███│   │█◯█│   │█◯█│   │███│   │
        //5 │   │███│   │███│   │███│   │███│
        //6 │███│   │███│   │███│   │███│   │
        //7 │   │███│   │███│   │███│   │███│
        //  └───┴───┴───┴───┴───┴───┴───┴───┘

        // Não pode comer pra trás
        tabuleiro.getCasa(3,3).get().setPeca(peca);
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(3,3),new Posicao(1,1)));
        assertEquals(peca,tabuleiro.getCasa(3,3).get().getPeca());
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(3,3),new Posicao(1,5)));

        // diagonal esquerda pra baixo
        assertTrue(tabuleiro.getCasa(4,2).get().temPeca(),"Era pra existir uma peça para ser capturada");
        assertDoesNotThrow(() -> tabuleiro.mover(new Posicao(3,3),new Posicao(5,1)));
        assertEquals(peca,tabuleiro.getCasa(5,1).get().getPeca());
        assertFalse(tabuleiro.getCasa(4,2).get().temPeca(),"A peça não foi capturada"); // a peça foi capturada


        // diagonal direita pra baixo
        tabuleiro.getCasa(3,3).get().setPeca(peca);
        assertDoesNotThrow(() -> tabuleiro.mover(new Posicao(3,3),new Posicao(5,5)));
        assertEquals(peca,tabuleiro.getCasa(5,5).get().getPeca());
        assertFalse(tabuleiro.getCasa(4,4).get().temPeca(),"A peça não foi capturada"); // a peça foi capturada

        //System.out.println(tabuleiro);

    }

    @Test
    void testeMovimentoCapturaBrancoBottom(){
        //Testando movimento de peça preta, descendo no tabuleiro.

        PecaTabuleiro peca = new PecaTabuleiro(Cor.BRANCO,regraSimoples,PosicaoInicio.BOTTOM);
        tabuleiro.getCasa(3,3).get().setPeca(peca);
        tabuleiro.getCasa(4,2).get().setPeca(new PecaTabuleiro(Cor.PRETO,regraSimoples,PosicaoInicio.TOP));
        tabuleiro.getCasa(4,4).get().setPeca(new PecaTabuleiro(Cor.PRETO,regraSimoples,PosicaoInicio.TOP));
        tabuleiro.getCasa(2,2).get().setPeca(new PecaTabuleiro(Cor.PRETO,regraSimoples,PosicaoInicio.TOP));
        tabuleiro.getCasa(2,4).get().setPeca(new PecaTabuleiro(Cor.PRETO,regraSimoples,PosicaoInicio.TOP));

        //    0   1   2   3   4   5   6   7
        //  ┌───┬───┬───┬───┬───┬───┬───┬───┐
        //0 │███│   │███│   │███│   │███│   │
        //1 │   │███│   │███│   │███│   │███│
        //2 │███│   │█●█│   │█●█│   │███│   │
        //3 │   │███│   │█◯█│   │███│   │███│
        //4 │███│   │█●█│   │█●█│   │███│   │
        //5 │   │███│   │███│   │███│   │███│
        //6 │███│   │███│   │███│   │███│   │
        //7 │   │███│   │███│   │███│   │███│
        //  └───┴───┴───┴───┴───┴───┴───┴───┘

        // Não pode comer pra trás
        tabuleiro.getCasa(3,3).get().setPeca(peca);
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(3,3),new Posicao(5,1)));
        assertEquals(peca,tabuleiro.getCasa(3,3).get().getPeca());
        assertThrows(MovimentoInvalidoException.class,() -> tabuleiro.mover(new Posicao(3,3),new Posicao(5,5)));


        //Teste captura diagonal superior esquerda
        assertTrue(tabuleiro.getCasa(2,2).get().temPeca(),"Era pra existir uma peça para ser capturada");
        assertDoesNotThrow(() -> tabuleiro.mover(new Posicao(3,3),new Posicao(1,1)));
        assertEquals(peca,tabuleiro.getCasa(1,1).get().getPeca());
        assertFalse(tabuleiro.getCasa(2,2).get().temPeca(),"A peça não foi capturada"); // a peça foi capturada


        tabuleiro.getCasa(3,3).get().setPeca(peca);
        //Teste captura diagonal superior direita
        assertDoesNotThrow(() -> tabuleiro.mover(new Posicao(3,3),new Posicao(1,5)));
        assertEquals(peca,tabuleiro.getCasa(1,5).get().getPeca());
        assertFalse(tabuleiro.getCasa(2,4).get().temPeca(),"A peça não foi capturada"); // a peça foi capturada


    }



}