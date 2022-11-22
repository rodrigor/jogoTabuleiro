package br.ufpb.dcx.rodrigor.poo.jogotabuleiro;

import java.util.Optional;

public class Tabuleiro {

    private int tamTabuleiro;
    // tabuleiro[lin][col]
    private CasaTabuleiro[][] tabuleiro;

    public Tabuleiro(int tamTabuleiro) {
        this.tamTabuleiro = tamTabuleiro;
        tabuleiro = new CasaTabuleiro[tamTabuleiro][tamTabuleiro];
        iniciarTabuleiro();
    }

    public Tabuleiro(){
        this(8);
    }

    private void iniciarTabuleiro(){
        for (int lin = 0; lin < tamTabuleiro; lin++) {
            for (int col = 0; col < tamTabuleiro; col++) {
                Cor corPeca = (lin+col)%2 == 0? Cor.PRETO : Cor.BRANCO;
                tabuleiro[lin][col] = new CasaTabuleiro(corPeca);
            }
        }
    }

    /**
        x e y têm valores de 0 ao tamanho do tabuleiro -1.
     **/
    public Optional<CasaTabuleiro> getCasa(int lin, int col){
        if(lin < 0 || col < 0 || lin >= tamTabuleiro || col >= tamTabuleiro){
            return Optional.empty();
        }
        return Optional.of(tabuleiro[lin][col]);
    }

    public Optional<CasaTabuleiro> getCasa(Posicao posicao){
        return this.getCasa(posicao.lin,posicao.col);
    }

    public void mover(Posicao origem, Posicao destino) throws MovimentoInvalidoException {
        // Início das verificações dos parâmetros do método
        if(origem == null){
            throw new IllegalArgumentException("origem é null! ");
        }else if(destino == null){
            throw new IllegalArgumentException("destino é null! ");
        }else if(origem.col < 0 ||
                origem.lin < 0 ||
                origem.col >= this.tamTabuleiro ||
                origem.lin >= this.tamTabuleiro){
            throw new IllegalArgumentException("Posição origem fora dos limites do tabuleiro. " +
                                               "TamTabuleiro"+this.tamTabuleiro+"- Origem:"+origem);
        } else if(destino.col < 0 ||
                destino.lin < 0 ||
                destino.col >=this.tamTabuleiro ||
                destino.lin >= this.tamTabuleiro){
            throw new IllegalArgumentException("Posição destino fora dos limites do tabuleiro. " +
                    "TamTabuleiro"+this.tamTabuleiro+"- Destino:"+destino);
        } else if(origem.equals(destino)){
            throw new IllegalArgumentException("Posição Origem = Destino!!: orig:"+origem+", dest:"+destino);
        } else if(!this.getCasa(origem).get().temPeca()){
            throw new MovimentoInvalidoException("Não existe peça na origem");
        }

        PecaTabuleiro pecaOrigem= this.getCasa(origem).get().getPeca();

        if (this.getCasa(destino).get().temPeca()){
            throw new MovimentoInvalidoException("Movimento inválido");
        }
        // Fim das verificações dos parâmetros do método


        pecaOrigem.movimentoValido(origem,destino,this);
    }




    public int getTamanho() {
        return this.tamTabuleiro;
    }

    @Override
    public String toString() {
        StringBuilder tabtxt = new StringBuilder();
        // ┌ ┬ ┐ └ ┴ ┘ ┤ ├ │ ┼  ─
        String hborder = "─".repeat(3);
        String topBorder =    "  ┌"+(hborder+"┬").repeat(tabuleiro.length-1)+hborder+"┐\n";
//        String midBorder =    " ├"+(hborder+"┼").repeat(tabuleiro.length-1)+hborder+"┤\n";
        String bottonBorder = "  └"+(hborder+"┴").repeat(tabuleiro.length-1)+hborder+"┘\n";

        boolean nextX;
        // top numbers
        tabtxt.append("  ");
        for (int x = 0; x < tabuleiro.length; x++) {
            tabtxt.append(String.format("  %s ", x));
        }
        tabtxt.append("\n");


        tabtxt.append(topBorder);
        for (int y = 0; y < tabuleiro.length; y++) {
            tabtxt.append(y+" ");
            for (int x = 0; x < tabuleiro.length; x++) {
                nextX = x+1 < tabuleiro.length;

                CasaTabuleiro casa = tabuleiro[y][x];
                String cortxt = casa.temPeca()?casa.getCor().equals(Cor.PRETO)?"█"+casa.getPeca()+"█":" "+casa.getPeca()+" ":casa.getCor().equals(Cor.PRETO)?"███":"   ";
                tabtxt.append("│"+cortxt+(nextX?"":"│\n"));
            }
        }
        tabtxt.append(bottonBorder);

        return tabtxt.toString();
    }



    public static void main(String[] args) {
        Tabuleiro t = new Tabuleiro();
        System.out.println(t);
    }
}
