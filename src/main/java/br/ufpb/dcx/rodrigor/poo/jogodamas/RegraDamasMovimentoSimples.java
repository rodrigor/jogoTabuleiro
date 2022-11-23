package br.ufpb.dcx.rodrigor.poo.jogodamas;

import br.ufpb.dcx.rodrigor.poo.jogotabuleiro.*;


import java.util.Objects;


public class RegraDamasMovimentoSimples implements RegraMovimentacao {

    @Override
    public boolean movimentoValido(Posicao origem, Posicao destino, Tabuleiro tabuleiro) throws MovimentoInvalidoException {
        PecaTabuleiro pecaOrigem = tabuleiro.getCasa(origem).get().getPeca();

        if (tabuleiro.getCasa(destino).get().temPeca()){
            throw new MovimentoInvalidoException("Existe uma peça na casa destino");
        }

        // Não permite movimento "pra trás". A peça não pode andar pra trás nem pode capturar pra trás
        if((pecaOrigem.getPosicaoInicio().equals(PosicaoInicio.TOP) & (destino.lin <= origem.lin)) ||
                (pecaOrigem.getPosicaoInicio().equals(PosicaoInicio.BOTTOM) & (destino.lin >= origem.lin))){
            throw new MovimentoInvalidoException("A peça não pode andar pra trás");
        }


        Posicao[] direcoes = {
                new Posicao(-1,-1),
                new Posicao(-1,+1),
                new Posicao(+1,+1),
                new Posicao(+1,-1)
        };



        // Verificar casas vizinhas
        for(Posicao dir:direcoes){
            Posicao vizinho = new Posicao(origem.lin+dir.lin,origem.col+dir.col);
            if(destino.equals(vizinho)){
                tabuleiro.movimentarPeca(origem, destino);
                checarViraDama(destino, pecaOrigem);
                return true;
            }
        }

        // capturar peça
        // todo: permitir capturar várias peças em sequencia.
        for(Posicao dir: direcoes){
            Posicao vizinho = new Posicao(origem.lin + dir.lin, origem.col + dir.col);
            if(tabuleiro.getCasa(vizinho).isPresent() &&
                    !tabuleiro.getCasa(vizinho).get().temPecaMesmaCor(pecaOrigem) &&
                    (destino.lin == vizinho.lin + dir.lin &&
                            destino.col == vizinho.col + dir.col)){
                tabuleiro.removerPeca(vizinho); //todo: retornar peça capturada
                tabuleiro.movimentarPeca(origem,destino);
                checarViraDama(destino, pecaOrigem);
                return true;
            }
        }

        throw new MovimentoInvalidoException("Movimento inválido");
    }

    @Override
    public String getID() {
        return "DamasMovimentacaoSimples";
    }

    private static void checarViraDama(Posicao destino, PecaTabuleiro pecaOrigem) {
        int ULTIMA_LINHA_BOTTOM = 7;
        int ULTIMA_LINHA_TOP = 0;
        if(( pecaOrigem.getPosicaoInicio().equals(PosicaoInicio.TOP) & (destino.lin == ULTIMA_LINHA_BOTTOM) ) ||
                ( pecaOrigem.getPosicaoInicio().equals(PosicaoInicio.BOTTOM) & (destino.lin == ULTIMA_LINHA_TOP) )) {
            pecaOrigem.setRegra(new RegraDamasMovimentoDama());
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegraDamasMovimentoSimples that = (RegraDamasMovimentoSimples) o;
        return getID().equals(that.getID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID());
    }
}
