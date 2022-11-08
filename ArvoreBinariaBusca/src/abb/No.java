package abb;

/**
 * Classe para o nó da árvore binária de busca
 * @author Fabiana Pereira e Samuel Costa
 */
public class No {
    private int chave;
    private No esquerda;
    private No direita;
    private int qtdNosEsquerda;
    private int qtdNosDireita;
    private int altura;

    public No(int chave) {
        this.chave = chave;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public int getQtdNosEsquerda() {
        return qtdNosEsquerda;
    }

    public void setQtdNosEsquerda(int qtdNosEsquerda) {
        this.qtdNosEsquerda = qtdNosEsquerda;
    }

    public int getQtdNosDireita() {
        return qtdNosDireita;
    }

    public void setQtdNosDireita(int qtdNosDireita) {
        this.qtdNosDireita = qtdNosDireita;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
