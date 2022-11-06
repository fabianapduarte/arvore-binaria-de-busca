package abb;

/**
 *
 * @author Fabiana Pereira e Samuel Costa
 */
public class ArvoreBinariaBusca {

    public static String preOrdem(No raiz, String sequenciaAnterior) {
        String sequencia = sequenciaAnterior + String.valueOf(raiz.getChave())+" ";

        if (raiz.getEsquerda() != null) {
            sequencia = preOrdem(raiz.getEsquerda(), sequencia);
        }
        if (raiz.getDireita() != null) {
            sequencia = preOrdem(raiz.getDireita(), sequencia);
        }

        return sequencia;
    }

    public static void visitaAltura(No raiz){
        int alt1, alt2; 
        if (raiz.getEsquerda() == null) {
            alt1=0;
        }else{
            alt1=raiz.getEsquerda().getAltura();
        }
        
        if (raiz.getDireita() == null) {
            alt2=0;
        }else{
            alt2=raiz.getDireita().getAltura();
        }
        
        if (alt1>alt2) {
            raiz.setAltura(alt1+1);
        }else{
            raiz.setAltura(alt2+1);
        }
    }

    public static void calcularAltura(No raiz){
        if (raiz.getEsquerda()!=null) {
            calcularAltura(raiz.getEsquerda());
        }
        if (raiz.getDireita()!=null) {
            calcularAltura(raiz.getDireita());
        }
        visitaAltura(raiz);
    }

    public static void formato1(No raiz, String espaco, String fill){
        if (raiz!=null) {
            System.out.print(espaco+raiz.getChave());
            int digitos = Integer.toString(raiz.getChave()).length();
            String fillPrint = fill.substring(0, fill.length()-digitos+1);
            System.out.print(fillPrint);
            System.out.print('\n');
            espaco = espaco.concat("      ");
            fill = fill.substring(0, fill.length()-6);
            if (raiz.getEsquerda()!=null) {
                formato1(raiz.getEsquerda(), espaco, fill);
            }
            if (raiz.getDireita()!=null) {
                formato1(raiz.getDireita(), espaco, fill);
            }
        }
    }

    public static void formato2(No raiz){
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
        if (tipo==1) {
            String espaco = "";
            String fill = "";
            for (int i = 0; i < raiz.getAltura()*6; i++) {
                fill = fill.concat("-");
            }
            formato1(raiz, espaco, fill);
        } else {
            formato2(raiz);
            System.out.print('\n');
        }
    }

    public static boolean preencher(No raiz, int chave){
        boolean ret = true;
        if (chave<raiz.getChave()) {
            if (raiz.getEsquerda()==null) {
                raiz.setEsquerda(new No(chave));
            } else {
                ret = preencher(raiz.getEsquerda(), chave);
            }
        } else if(chave>raiz.getChave()){
            if (raiz.getDireita()==null) {
                raiz.setDireita(new No(chave));
            } else {
                ret = preencher(raiz.getDireita(), chave);
            }
        }else{
            return false;
        }
        return ret;
    }

    public static boolean ehCheia(No raiz, int altura){
        boolean ret = true;
        if ((raiz.getDireita()==null || raiz.getEsquerda()==null) && altura!=1) { //folha
            return false;
        }
        if(raiz.getEsquerda()!=null){
            ret = ehCheia(raiz.getEsquerda(), altura-1);
        }
        if(raiz.getDireita()!=null){
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
        if(raiz.getEsquerda()!=null){
            ret = ehCompleta(raiz.getEsquerda(), altura-1);
        }
        if(raiz.getDireita()!=null){
            ret = ehCompleta(raiz.getDireita(), altura-1);
        }
        return ret;
    }

    public static boolean isFolha(No raiz){
        return (raiz.getEsquerda()==null && raiz.getDireita()==null);
    }

    public static boolean subArvoresNaoVazias(No raiz){
        return (raiz.getEsquerda()!=null && raiz.getDireita()!=null);
    }

    public static No filhoNaoNulo(No raiz){
        No no = raiz.getEsquerda();
        if (no == null) {
            no = raiz.getDireita();
        }
        return no;
    }
    
    public static int maior(No raiz){
        No no = raiz.getEsquerda();
        while(no.getDireita()!=null){
            no=no.getDireita();
        }
        int ret = no.getChave();
        no.setChave(raiz.getChave());
        no.setEsquerda(null);
        no.setDireita(null);
        return ret;
    }

    public static boolean remover(No raiz, int chave){
        boolean ret = true;
        No esquerda = raiz.getEsquerda();
        No direita = raiz.getDireita();
        if (esquerda!=null && chave==esquerda.getChave()) {
            if(isFolha(esquerda)){
                raiz.setEsquerda(null);
            }else if(subArvoresNaoVazias(esquerda)) {
                esquerda.setChave(maior(esquerda));
                ret = remover(esquerda.getEsquerda(), chave);
            }else{
                raiz.setEsquerda(filhoNaoNulo(esquerda));
            }
        }else if (direita!=null && chave==direita.getChave()) {
            if(isFolha(direita)){
                raiz.setDireita(null);
            }else if(subArvoresNaoVazias(direita)){
                direita.setChave(maior(direita));
                ret = remover(direita.getEsquerda(), chave);
            }else{
                raiz.setDireita(filhoNaoNulo(direita));
            }
        }else{
            if (chave<raiz.getChave() && esquerda!=null) {
                ret = remover(esquerda, chave);
            }else if(chave>raiz.getChave() && direita!=null){
                ret = remover(direita, chave);
            }else if(chave==raiz.getChave()){
                System.out.println("root "+raiz.getChave());
                raiz = (null);
            }else{
                return false;
            }
        }
        return ret;
    }

    public static void contarSubNos(No raiz){
        if (raiz.getEsquerda()==null){
            raiz.setSubEsquerda(0);
        }else{
            contarSubNos(raiz.getEsquerda());
        }
        if (raiz.getDireita()==null){
            raiz.setSubDireita(0);
        }else{
            contarSubNos(raiz.getDireita());  
        }
        if (raiz.getEsquerda()!=null) {
            raiz.setSubEsquerda(raiz.getEsquerda().getSubEsquerda()+raiz.getEsquerda().getSubDireita()+1);
        }
        if (raiz.getDireita()!=null) {
            raiz.setSubDireita(raiz.getDireita().getSubEsquerda()+raiz.getDireita().getSubDireita()+1);
        }
    }
}