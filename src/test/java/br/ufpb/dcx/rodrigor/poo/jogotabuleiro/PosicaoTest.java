package br.ufpb.dcx.rodrigor.poo.jogotabuleiro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PosicaoTest {

     @Test
    void testEquals() {
        assertEquals(new Posicao(0,0), new Posicao(0,0));
        assertNotEquals(new Posicao(0,1), new Posicao(0,0));
        assertNotEquals(new Posicao(1,1), new Posicao(0,0));
        assertNotEquals(new Posicao(1,0), new Posicao(0,0));
    }
}