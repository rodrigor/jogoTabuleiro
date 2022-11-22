package br.ufpb.dcx.rodrigor.poo.jogotabuleiro;

import java.util.Objects;

public class PecaTabuleiro {

    private Cor cor;


    private PosicaoInicio posicaoInicio;
    private RegraMovimentacao regra;

//    public PecaTabuleiro(Cor cor){
//        this.cor = cor;
//    }

    public PecaTabuleiro(Cor cor, RegraMovimentacao regra, PosicaoInicio posicao){
        this.cor = cor;
        this.regra = regra;
        this.posicaoInicio = posicao;
    }

    public Cor getCor() {
        return cor;
    }


    public String toString(){
        return (cor.equals(Cor.PRETO)?"●":"◯");
    }

    public boolean movimentoValido(Posicao origem, Posicao destino, Tabuleiro tabuleiro) throws MovimentoInvalidoException {
        return regra.movimentoValido(origem,destino,tabuleiro);
    }

    public RegraMovimentacao getRegra() {
        return regra;
    }

    public void setRegra(RegraMovimentacao regra) {
        this.regra = regra;
    }

    public PosicaoInicio getPosicaoInicio() {
        return posicaoInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PecaTabuleiro that = (PecaTabuleiro) o;
        return getCor() == that.getCor() && getPosicaoInicio() == that.getPosicaoInicio() && Objects.equals(getRegra(), that.getRegra());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCor(), getPosicaoInicio(), getRegra());
    }
}

