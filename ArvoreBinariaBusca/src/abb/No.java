package abb;

public class No {
    private int chave;
    private No esquerda;
    private No direita;

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
}
