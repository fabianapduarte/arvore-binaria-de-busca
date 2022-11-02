package abb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public static void imprimir(No raiz, int tipo) {
        if (tipo==2) {
            System.out.print("(");
            System.out.print(raiz.getChave());
            for (int i = 0; i < raiz.getAltura(); i++) {
                System.out.print(raiz.getChave());
            }
            if (raiz.getEsquerda() != null) {
                System.out.print(" ");
                imprimir(raiz.getEsquerda(), tipo);
            }
            if (raiz.getDireita() != null) {
                System.out.print(" ");
                imprimir(raiz.getDireita(), tipo);
            }
            System.out.print(")");
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

    public static boolean remover(No raiz, int chave){
        boolean ret = true;
        No esquerda = raiz.getEsquerda();
        No direita = raiz.getDireita();
        if (esquerda!=null && chave==esquerda.getChave()) {
            raiz.setEsquerda(null);
        }else if (direita!=null && chave==direita.getChave()) {
            raiz.setDireita(null);
        }else{
            if (chave<raiz.getChave() && esquerda!=null) {
                ret = remover(esquerda, chave);
            }else if(chave>raiz.getChave() && direita!=null){
                ret = remover(direita, chave);
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        No raiz;
        String abs = ArvoreBinariaBusca.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String rel = "../../entrada/"+args[0]+".txt";
        File file = new File(abs+rel);

        //Leitura da arvore
        Scanner scan;
        if (!file.exists()){
            file = new File(abs+"../"+rel);
            if (!file.exists()){
                System.out.println("Nome de arquivo errado!");
                System.exit(0);
            }
        }
        
        try {
            scan = new Scanner(file);
            raiz = new No(scan.nextInt());
            while(scan.hasNextInt()) {
                int chave = scan.nextInt();
                preencher(raiz, chave);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            raiz = null;
            System.out.println("Raiz inválida!");
        }

        contarSubNos(raiz);
        // System.out.println(raiz.getSubEsquerda()); //3
        // System.out.println(raiz.getSubDireita()); //2
        // System.out.println(raiz.getEsquerda().getSubEsquerda()); //1
        // System.out.println(raiz.getEsquerda().getSubDireita()); //1
        // System.out.println(raiz.getEsquerda().getEsquerda().getSubDireita()); //0
        // System.out.println(raiz.getDireita().getSubDireita()); //1
        // System.out.println(raiz.getDireita().getSubEsquerda()); //0

        //Leitura dos comandos
        rel = "../../entrada/"+args[1]+".txt";
        file = new File(abs+rel);
        Scanner scanComandos;
        if (!file.exists()){
            file = new File(abs+"../"+rel);
            if (!file.exists()){
                System.out.println("Nome de arquivo errado!");
                System.exit(0);
            }
        }
        
        try {
            scanComandos = new Scanner(file);
            while(scanComandos.hasNextLine()) {
                String line = scanComandos.nextLine();

                Scanner sc = new Scanner(line);
                String comando = sc.next();
                if (comando.matches("ENESIMO|INSIRA|IMPRIMA|REMOVA|POSICAO|BUSCAR|MEDIA")) {
                    // comando.equals("ENESIMO") comando.equals("INSIRA") comando.equals("IMPRIMA") 
                    // comando.equals("REMOVA") comando.equals("POSICAO") comando.equals("BUSCAR")
                    int chaveComando = sc.nextInt();
                    switch (comando) {   
                        case "INSIRA":   
                            if(preencher(raiz, chaveComando)){
                                System.out.println(chaveComando+" adicionado");
                            }else{
                                System.out.println(chaveComando +" Já está na árvore, não pode ser inserido");
                            }
                            break;  
                        case "IMPRIMA": 
                            imprimir(raiz, chaveComando);
                            System.out.println("");
                            break;  
                        case "REMOVA": 
                            if(remover(raiz, chaveComando)){
                                System.out.println(chaveComando+" removido");
                            }else{
                                System.out.println(chaveComando +" não está na árvore, não pode ser removido");
                            }
                            break;  
                        default:   
                            System.out.println("Comando não encontrado");  
                        }
                } else {
                    if(comando.equals("PREORDEM")){
                        String sequencia = "";
                        sequencia = preOrdem(raiz, sequencia);
                        System.out.println(sequencia);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }   
    }
}