package br.ufpb.dcx.rodrigor.poo.jogodamas;

import br.ufpb.dcx.rodrigor.poo.jogotabuleiro.*;

import java.util.Optional;

public class RegraDamasMovimentoDama implements RegraMovimentacao {

    @Override
    public boolean movimentoValido(Posicao origem, Posicao destino, Tabuleiro tabuleiro) throws MovimentoInvalidoException {
        //todo: Falta implementar a captura de peças pela Dama
        // todo: Não é possível "atravessar" peças da mesma cor, apenas capturar peças do adversário.
        Posicao[] direcoes = {
                new Posicao(-1,-1),
                new Posicao(-1,+1),
                new Posicao(+1,+1),
                new Posicao(+1,-1)
        };

        for(Posicao direcao: direcoes){
            if(valido(new Posicao(origem.lin+ direcao.lin, origem.col+ direcao.col), destino, tabuleiro,direcao)){
                movimentarPeca(origem,destino,tabuleiro);
                return true;
            }
        }
        throw new MovimentoInvalidoException("Movimento inválido");

    }

    @Override
    public String getID() {
        return "regraDama";
    }

    boolean valido(Posicao origem, Posicao destino, Tabuleiro tabuleiro, Posicao direcao){
        if(origem.equals(destino)) return true;
        Optional<CasaTabuleiro> casaDestino = tabuleiro.getCasa(origem.lin, origem.col);
        if(casaDestino.isEmpty()) return false;
        if(casaDestino.get().temPeca()) return false;
        return valido(new Posicao(origem.lin+ direcao.lin, origem.col+ direcao.col),destino,tabuleiro,direcao);
    }

    public void movimentarPeca(Posicao origem, Posicao destino, Tabuleiro tabuleiro){
        PecaTabuleiro pecaOrigem = tabuleiro.getCasa(origem).get().getPeca();
        tabuleiro.getCasa(destino).get().setPeca(pecaOrigem);
        tabuleiro.removerPeca(origem);

    }

}
