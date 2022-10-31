package abb;

public class No {
    private int chave;
    private No esquerda;
    private No direita;
    private int subEsquerda;
    private int subDireita;
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

    public int getSubEsquerda(){
      return subEsquerda;
    }

    public void setSubEsquerda(int subEsquerda){
      this.subEsquerda = subEsquerda;
    }

    public int getSubDireita(){
      return subDireita;
    }

    public void setSubDireita(int subDireita){
      this.subDireita = subDireita;
    }

    public int getAltura(){
      return altura;
    }

    public void setAltura(int altura){
      this.altura = altura;
    }
}
