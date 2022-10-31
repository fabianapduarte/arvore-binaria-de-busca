package abb;

/**
 *
 * @author Fabiana Pereira e Samuel Costa
 */
public class ArvoreBinariaBusca {

    public static String preOrdem(No raiz, String sequenciaAnterior) {
        String sequencia = sequenciaAnterior + String.valueOf(raiz.getChave());

        if (raiz.getEsquerda() != null) {
            sequencia = preOrdem(raiz.getEsquerda(), sequencia);
        }
        if (raiz.getDireita() != null) {
            sequencia = preOrdem(raiz.getDireita(), sequencia);
        }

        return sequencia;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        No um = new No(1);
        No dois = new No(2);
        No tres = new No(3);
        No quatro = new No(4);
        No cinco = new No(5);
        No seis = new No(6);
        No sete = new No(7);
        No oito = new No(8);
        No nove = new No(9);        

        um.setEsquerda(dois);
        um.setDireita(tres);

        dois.setEsquerda(quatro);
        dois.setDireita(sete);

        tres.setEsquerda(cinco);
        tres.setDireita(seis);

        quatro.setDireita(nove);
        
        seis.setEsquerda(oito);

        String sequencia = "";
        sequencia = preOrdem(um, sequencia);
        System.out.println(sequencia);
    }

}
