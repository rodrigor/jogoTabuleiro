package br.ufpb.dcx.rodrigor.poo.jogodamas;

import br.ufpb.dcx.rodrigor.poo.jogotabuleiro.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RegraDamasMovimentoSimples implements RegraMovimentacao {

    private String regraID = "movimentacaoSimples";
    @Override
    public boolean movimentoValido(Posicao origem, Posicao destino, Tabuleiro tabuleiro) throws MovimentoInvalidoException {
        PecaTabuleiro pecaOrigem = tabuleiro.getCasa(origem).get().getPeca();


        // Não permite movimento "pra trás". A peça não pode andar pra trás nem pode capturar pra trás
        if((pecaOrigem.getPosicaoInicio().equals(PosicaoInicio.TOP) & (destino.lin <= origem.lin)) ||
                (pecaOrigem.getPosicaoInicio().equals(PosicaoInicio.BOTTOM) & (destino.lin >= origem.lin))){
            throw new MovimentoInvalidoException("A peça não pode andar pra trás");
        }


        // Checar movimento da peça do jogo de Damas:
        List<MovimentoPossivel> possiveisSimples = new LinkedList<>();

        //movimento para casas vizinhas
        possiveisSimples.add((orig, dest) -> dest.col == orig.col -1 && dest.lin == orig.lin +1);
        possiveisSimples.add((orig, dest) -> dest.col == orig.col +1 && dest.lin == orig.lin +1);
        possiveisSimples.add((orig, dest) -> dest.col == orig.col -1 && dest.lin == orig.lin -1);
        possiveisSimples.add((orig, dest) -> dest.col == orig.col +1 && dest.lin == orig.lin -1);


        if(possiveisSimples.stream().anyMatch(possivel -> possivel.movimentoValido(origem, destino))){
            movimentarPeca(origem, destino, tabuleiro);
            return true;
        }

        //movimento de captura de peças vizinhas
        List<MovimentoPossivel> possiveisCaptura = new LinkedList<>();

        //TODO: melhorar o uso do optional usando ifPresent()
        //Lógica aqui: o getCasa retorna um Optional "null" caso a coordenada passada esteja fora dos limites do tabuleiro.
        //isso é necessário para que possamos testar situações onde a coordenada de origem seja na borda do tabuleiro.
        //Por isso verificamos primeiro se a cada "existe" getCasa(...).isPresent()

        // capturando superior esquerdo
        int[][] pos = {{-1,-1},
                               {-1,+1},
                               {+1,-1},
                               {+1,+1}};

        for (int i = 0; i < pos.length; i++) {
            int l = pos[i][0];
            int c = pos[i][1];
            if(tabuleiro.getCasa(origem.lin + l, origem.col + c).isPresent() &&
                    !tabuleiro.getCasa(origem.lin +l, origem.col + c).get().temPecaMesmaCor(pecaOrigem) &&
                    (destino.lin == origem.lin + 2*l &&
                            destino.col == origem.col + 2*c)){
                tabuleiro.getCasa(new Posicao(origem.lin + l, origem.col + c)).get().removerPeca();
                movimentarPeca(origem,destino,tabuleiro);
                return true;
            }
        }


//        if(tabuleiro.getCasa(origem.col - 1, origem.lin - 1).isPresent() &&
//                !tabuleiro.getCasa(origem.col - 1, origem.lin - 1).get().temPecaMesmaCor(pecaOrigem) &&
//                (destino.col == origem.col - 2 &&
//                        destino.lin == origem.lin - 2)){
//            tabuleiro.getCasa(new Posicao(origem.lin -1, origem.col -1)).get().removerPeca();
//            movimentarPeca(origem,destino,tabuleiro);
//            return true;
//        }

        //possiveisCaptura.add((orig, dest) -> tabuleiro.getCasa(orig.col - 1, orig.lin - 1).isPresent() && !tabuleiro.getCasa(orig.col - 1, orig.lin - 1).get().temPecaMesmaCor(pecaOrigem) && (dest.col == orig.col - 2 && dest.lin == orig.lin - 2));
//        possiveisCaptura.add((orig, dest) -> tabuleiro.getCasa(orig.col - 1, orig.lin + 1).isPresent() && !tabuleiro.getCasa(orig.col - 1, orig.lin + 1).get().temPecaMesmaCor(pecaOrigem) && (dest.col == orig.col - 2 && dest.lin == orig.lin + 2));
//        possiveisCaptura.add((orig, dest) -> tabuleiro.getCasa(orig.col + 1, orig.lin - 1).isPresent() && !tabuleiro.getCasa(orig.col + 1, orig.lin - 1).get().temPecaMesmaCor(pecaOrigem) && (dest.col == orig.col + 2 && dest.lin == orig.lin - 2));
//        possiveisCaptura.add((orig, dest) -> tabuleiro.getCasa(orig.col + 1, orig.lin + 1).isPresent() && !tabuleiro.getCasa(orig.col + 1, orig.lin + 1).get().temPecaMesmaCor(pecaOrigem) && (dest.col == orig.col + 2 && dest.lin == orig.lin + 2));

        if(possiveisCaptura.stream().anyMatch(possivel -> possivel.movimentoValido(origem, destino))){
            movimentarPeca(origem, destino,tabuleiro);
            //TODO: implementar captura da peça
        }else {
            throw new MovimentoInvalidoException("Movimento inválido");
        }

        return false;
    }

    public void movimentarPeca(Posicao origem, Posicao destino, Tabuleiro tabuleiro){
        PecaTabuleiro pecaOrigem = tabuleiro.getCasa(origem).get().getPeca();
        tabuleiro.getCasa(destino).get().setPeca(pecaOrigem);
        tabuleiro.getCasa(origem).get().removerPeca();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegraDamasMovimentoSimples that = (RegraDamasMovimentoSimples) o;
        return Objects.equals(regraID, that.regraID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regraID);
    }
}
