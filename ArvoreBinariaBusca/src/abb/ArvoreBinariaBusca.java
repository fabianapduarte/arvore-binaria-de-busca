package abb;

/**
 *
 * @author Fabiana Pereira e Samuel Costa
 */
public class ArvoreBinariaBusca {

    public static String preOrdem(No raiz, String sequenciaAnterior) {
        String sequencia = sequenciaAnterior + String.valueOf(raiz.getChave()) + " ";

        if (raiz.getEsquerda() != null) {
            sequencia = preOrdem(raiz.getEsquerda(), sequencia);
        }
        if (raiz.getDireita() != null) {
            sequencia = preOrdem(raiz.getDireita(), sequencia);
        }

        return sequencia;
    }

    public static void visitaAltura(No raiz) {
        int alt1, alt2;
        if (raiz.getEsquerda() == null) {
            alt1 = 0;
        } else {
            alt1 = raiz.getEsquerda().getAltura();
        }

        if (raiz.getDireita() == null) {
            alt2 = 0;
        } else {
            alt2 = raiz.getDireita().getAltura();
        }

        if (alt1 > alt2) {
            raiz.setAltura(alt1 + 1);
        } else {
            raiz.setAltura(alt2 + 1);
        }
    }

    public static void calcularAltura(No raiz) {
        if (raiz.getEsquerda() != null) {
            calcularAltura(raiz.getEsquerda());
        }
        if (raiz.getDireita() != null) {
            calcularAltura(raiz.getDireita());
        }
        visitaAltura(raiz);
    }

    public static void formato1(No raiz, String espaco, String fill) {
        if (raiz != null) {
            System.out.print(espaco + raiz.getChave());
            int digitos = Integer.toString(raiz.getChave()).length();
            String fillPrint = fill.substring(0, fill.length() - digitos + 1);
            System.out.print(fillPrint);
            System.out.print('\n');
            espaco = espaco.concat("      ");
            fill = fill.substring(0, fill.length() - 6);
            if (raiz.getEsquerda() != null) {
                formato1(raiz.getEsquerda(), espaco, fill);
            }
            if (raiz.getDireita() != null) {
                formato1(raiz.getDireita(), espaco, fill);
            }
        }
    }

    public static void formato2(No raiz) {
        System.out.print("(");
        System.out.print(raiz.getChave());
        if (raiz.getEsquerda() != null) {
            System.out.print(" ");
            formato2(raiz.getEsquerda());
        }
        if (raiz.getDireita() != null) {
            System.out.print(" ");
            formato2(raiz.getDireita());
        }
        System.out.print(")");
    }

    public static void imprimir(No raiz, int tipo) {
        if (tipo == 1) {
            String espaco = "";
            String fill = "";
            for (int i = 0; i < raiz.getAltura() * 6; i++) {
                fill = fill.concat("-");
            }
            formato1(raiz, espaco, fill);
        } else {
            formato2(raiz);
            System.out.print('\n');
        }
    }

    public static boolean preencher(No raiz, int chave) {
        boolean ret = true;
        if (chave < raiz.getChave()) {
            if (raiz.getEsquerda() == null) {
                raiz.setEsquerda(new No(chave));
            } else {
                ret = preencher(raiz.getEsquerda(), chave);
            }
        } else if (chave > raiz.getChave()) {
            if (raiz.getDireita() == null) {
                raiz.setDireita(new No(chave));
            } else {
                ret = preencher(raiz.getDireita(), chave);
            }
        } else {
            return false;
        }
        return ret;
    }

    public static boolean ehCheia(No raiz, int altura){
        boolean ret = true;
        boolean vazio = (raiz.getDireita()==null || raiz.getEsquerda()==null);
        if (vazio && altura>1) { //folha
            return false;
        }
        if(raiz.getEsquerda()!=null && altura>2){
            ret = ehCheia(raiz.getEsquerda(), altura-1);
        }
        if(raiz.getDireita()!=null && altura>2){
            ret = ehCheia(raiz.getDireita(), altura-1);
        }
        return ret;
    }

    public static boolean ehCompleta(No raiz, int altura){
        boolean ret = true;
        boolean vazio = (raiz.getDireita()==null || raiz.getEsquerda()==null);
        if ((vazio && altura>2)) { //folha
            return false;
        }
        if(raiz.getEsquerda()!=null && altura>3){
            ret = ehCompleta(raiz.getEsquerda(), altura-1);
        }
        if(raiz.getDireita()!=null && altura>3){
            ret = ehCompleta(raiz.getDireita(), altura-1);
        }
        return ret;
    }
    
    public static Boolean checarRemocao(No raiz, int chave) {
        int qtdNos = raiz.getQtdNosDireita()+raiz.getQtdNosEsquerda();
        raiz = remover(raiz, chave);
        contarSubNos(raiz);
        int qtdNosApos = raiz.getQtdNosDireita()+raiz.getQtdNosEsquerda();
        return (qtdNos!=qtdNosApos);
    }

    public static int trocar(No raiz){
        No no = raiz.getEsquerda();
        while(no.getDireita()!=null){
            no=no.getDireita();
        }
        int ret = no.getChave();
        return ret;
    }

    public static No remover(No raiz, int chave) {
        if (raiz != null) {
            if (chave < raiz.getChave()) {
                raiz.setEsquerda(remover(raiz.getEsquerda(), chave));
            } else if (chave > raiz.getChave()) {
                raiz.setDireita(remover(raiz.getDireita(), chave));
            }else {
                if (raiz.getEsquerda()==null) {
                    return raiz.getDireita();
                }else if (raiz.getDireita()==null) {
                    return raiz.getEsquerda();
                }else{
                    int temp = trocar(raiz);
                    raiz.setChave(temp);
                    raiz.setEsquerda(remover(raiz.getEsquerda(), temp));
                }
            }
        }
        return raiz;
    }

    public static void contarSubNos(No raiz) {
        if (raiz.getEsquerda() == null) {
            raiz.setQtdNosEsquerda(0);
        } else {
            contarSubNos(raiz.getEsquerda());
        }
        if (raiz.getDireita() == null) {
            raiz.setQtdNosDireita(0);
        } else {
            contarSubNos(raiz.getDireita());
        }
        if (raiz.getEsquerda() != null) {
            raiz.setQtdNosEsquerda(raiz.getEsquerda().getQtdNosEsquerda() + raiz.getEsquerda().getQtdNosDireita() + 1);
        }
        if (raiz.getDireita() != null) {
            raiz.setQtdNosDireita(raiz.getDireita().getQtdNosEsquerda() + raiz.getDireita().getQtdNosDireita() + 1);
        }
    }

    public static int posicao(No raiz, int elemento, int somaNosAnteriores) {
        int novaSoma;

        if (raiz.getChave() == elemento) {
            return somaNosAnteriores + raiz.getQtdNosEsquerda() + 1;
        } else if (raiz.getChave() > elemento) {
            No noEsquerda = raiz.getEsquerda();
            if (somaNosAnteriores != 0) {
                novaSoma = somaNosAnteriores + raiz.getQtdNosEsquerda() - noEsquerda.getQtdNosEsquerda() - noEsquerda.getQtdNosDireita() - 1;
            } else {
                novaSoma = 0;
            }
            return posicao(noEsquerda, elemento, novaSoma);
        } else {
            novaSoma = somaNosAnteriores + raiz.getQtdNosEsquerda() + 1;
            return posicao(raiz.getDireita(), elemento, novaSoma);
        }
    }

    public static int enesimoElemento(No raiz, int posicao) {
        int posicaoAtual = raiz.getQtdNosEsquerda() + 1;

        if (posicaoAtual == posicao) {
            return raiz.getChave();
        } else if (posicaoAtual > posicao) {
            return enesimoElemento(raiz.getEsquerda(), posicao);
        } else {
            return enesimoElemento(raiz.getDireita(), posicao - raiz.getQtdNosEsquerda() - 1);
        }
    }

    public static int mediana(No raiz) {
        int totalElementos = raiz.getQtdNosEsquerda() + raiz.getQtdNosDireita() + 1;

        if (totalElementos % 2 == 1) {
            return enesimoElemento(raiz, (totalElementos / 2) + 1);
        } else {
            return enesimoElemento(raiz, totalElementos / 2);
        }
    }

    public static No buscar(No raiz, int elemento) {
        if (raiz != null) {
            if (raiz.getChave() == elemento) {
                return raiz;
            } else if (raiz.getChave() > elemento) {
                return buscar(raiz.getEsquerda(), elemento);
            } else {
                return buscar(raiz.getDireita(), elemento);
            }
        } else {
            return null;
        }
    }

    public static double media(No raiz, int elemento) {
        No noParaMedia = buscar(raiz, elemento);
        int soma = somaElementos(noParaMedia, 0);
        int totalElementos = noParaMedia.getQtdNosEsquerda() + noParaMedia.getQtdNosDireita() + 1;
        return (double) soma / totalElementos;
    }

    public static int somaElementos(No raiz, int somaAnterior) {
        int soma = somaAnterior;

        if (raiz.getEsquerda() != null) {
            soma = somaElementos(raiz.getEsquerda(), soma);
        }

        soma += raiz.getChave();

        if (raiz.getDireita() != null) {
            soma = somaElementos(raiz.getDireita(), soma);
        }

        return soma;
    }
}