package br.ufpb.dcx.rodrigor.poo.jogodamas;

import br.ufpb.dcx.rodrigor.poo.jogotabuleiro.*;
import br.ufpb.dcx.rodrigor.util.Texto;

import static br.ufpb.dcx.rodrigor.poo.jogotabuleiro.PosicaoInicio.*;

public class JogoDamas {

    private Tabuleiro tabuleiro;

    public JogoDamas(){
        this.tabuleiro = new Tabuleiro();
    }

    Tabuleiro getTabuleiro(){
        return this.tabuleiro;
    }

    public void fazerSetup(){
        RegraMovimentacao regraDamas = new RegraDamasMovimentoSimples();

        //todo: refatorar
        tabuleiro.alocarPeca(0, 0, new PecaTabuleiro(Cor.PRETO,regraDamas, TOP));
        tabuleiro.alocarPeca(0,2,new PecaTabuleiro(Cor.PRETO,regraDamas, TOP));
        tabuleiro.alocarPeca(0,4,new PecaTabuleiro(Cor.PRETO,regraDamas, TOP));
        tabuleiro.alocarPeca(0,6,new PecaTabuleiro(Cor.PRETO,regraDamas, TOP));
        tabuleiro.alocarPeca(1,1,new PecaTabuleiro(Cor.PRETO,regraDamas, TOP));
        tabuleiro.alocarPeca(1,3,new PecaTabuleiro(Cor.PRETO,regraDamas, TOP));
        tabuleiro.alocarPeca(1,5,new PecaTabuleiro(Cor.PRETO,regraDamas, TOP));
        tabuleiro.alocarPeca(1,7,new PecaTabuleiro(Cor.PRETO,regraDamas, TOP));
        tabuleiro.alocarPeca(2,0,new PecaTabuleiro(Cor.PRETO,regraDamas, TOP));
        tabuleiro.alocarPeca(2,2,new PecaTabuleiro(Cor.PRETO,regraDamas, TOP));
        tabuleiro.alocarPeca(2,4,new PecaTabuleiro(Cor.PRETO,regraDamas, TOP));
        tabuleiro.alocarPeca(2,6,new PecaTabuleiro(Cor.PRETO,regraDamas, TOP));
        tabuleiro.alocarPeca(5,1,new PecaTabuleiro(Cor.BRANCO,regraDamas, BOTTOM));
        tabuleiro.alocarPeca(5,3,new PecaTabuleiro(Cor.BRANCO,regraDamas, BOTTOM));
        tabuleiro.alocarPeca(5,5,new PecaTabuleiro(Cor.BRANCO,regraDamas, BOTTOM));
        tabuleiro.alocarPeca(5,7,new PecaTabuleiro(Cor.BRANCO,regraDamas, BOTTOM));
        tabuleiro.alocarPeca(6,0,new PecaTabuleiro(Cor.BRANCO,regraDamas, BOTTOM));
        tabuleiro.alocarPeca(6,2,new PecaTabuleiro(Cor.BRANCO,regraDamas, BOTTOM));
        tabuleiro.alocarPeca(6,4,new PecaTabuleiro(Cor.BRANCO,regraDamas, BOTTOM));
        tabuleiro.alocarPeca(6,6,new PecaTabuleiro(Cor.BRANCO,regraDamas, BOTTOM));
        tabuleiro.alocarPeca(7,1,new PecaTabuleiro(Cor.BRANCO,regraDamas, BOTTOM));
        tabuleiro.alocarPeca(7,3,new PecaTabuleiro(Cor.BRANCO,regraDamas, BOTTOM));
        tabuleiro.alocarPeca(7,5,new PecaTabuleiro(Cor.BRANCO,regraDamas, BOTTOM));
        tabuleiro.alocarPeca(7,7,new PecaTabuleiro(Cor.BRANCO,regraDamas, BOTTOM));
    }

    public static void main(String[] args) {
        JogoDamas jogo = new JogoDamas();
        jogo.fazerSetup();
        try {
            jogo.lerComandos();
        }catch (Exception exception){
            System.out.println("Me desculpe, mas tivemos um problema interno. Volte mais tarde.");
            exception.printStackTrace();
        }
    }
    public void lerComandos(){
        String comando;
        boolean continua;
        do {
            System.out.println(tabuleiro);
            comando = Texto.readString("digite o movimento, ou 'sair':\n");
            continua = !comando.equalsIgnoreCase("sair");
            if(!continua) continue;
            try {
                String[] coordenadas = comando.split(" ");
                Posicao origem = Posicao.getInstance(coordenadas[0]);
                Posicao destino = Posicao.getInstance(coordenadas[1]);
                tabuleiro.mover(origem, destino);
            }catch(MovimentoInvalidoException exceptionMov){
                Texto.printAviso(exceptionMov.getMessage());
            }catch(Exception exception){
                Texto.printErro(exception.getMessage());
                //exception.printStackTrace();
            }

        }while(continua);
        Texto.printMsgOK("Até mais!");
    }



}
