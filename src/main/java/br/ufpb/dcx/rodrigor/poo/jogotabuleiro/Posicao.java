package br.ufpb.dcx.rodrigor.poo.jogotabuleiro;

import java.util.Objects;

public class Posicao {

    public int col;
    public int lin;


    public Posicao(){}


    public Posicao(int lin, int col){
        this.lin = lin;
        this.col = col;
    }


    @Override
    public String toString() {
        return "["+ lin +","+ col +"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicao posicao = (Posicao) o;
        return col == posicao.col && lin == posicao.lin;
    }

    /**
     * Gera uma instância de Posição a partir de um String no formato x,y
     * @param texto string no formato x,y
     * @return uma nova Posição
     */
    public static Posicao getInstance(String texto){
        String[] xy = texto.split(",");
        Posicao nova = new Posicao();
        nova.lin = Integer.parseInt(xy[0]);
        nova.col = Integer.parseInt(xy[1]);
        return nova;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, lin);
    }
}
