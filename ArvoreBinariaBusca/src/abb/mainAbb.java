package abb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class mainAbb {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        No raiz;
        String abs = ArvoreBinariaBusca.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String rel = "../../../entrada/";
        File file = new File(abs+rel+args[0]+".txt");

        //Leitura da arvore
        Scanner scan;
        if (!file.exists()){
            System.out.println("Nome de arquivo 1 errado! - "+args[0]);
            System.exit(0);
        }
        
        try {
            scan = new Scanner(file);
            raiz = new No(scan.nextInt());
            while(scan.hasNextInt()) {
                int chave = scan.nextInt();
                ArvoreBinariaBusca.preencher(raiz, chave);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            raiz = null;
            System.out.println("Raiz inválida!");
        }

        ArvoreBinariaBusca.calcularAltura(raiz);
        ArvoreBinariaBusca.contarSubNos(raiz);

        //Leitura dos comandos
        file = new File(abs+rel+args[1]+".txt");
        Scanner scanComandos;
        if (!file.exists()){
            System.out.println("Nome de arquivo 2 errado! - "+args[1]);
            System.exit(0);
        }
        
        try {
            scanComandos = new Scanner(file);
            while(scanComandos.hasNextLine()) {
                String line = scanComandos.nextLine();

                try (Scanner sc = new Scanner(line)) {
                    String comando = sc.next();
                    if (comando.matches("ENESIMO|INSIRA|IMPRIMA|REMOVA|POSICAO|BUSCAR|MEDIA")) {
                        int chaveComando = sc.nextInt();
                        switch (comando) {   
                            case "INSIRA":   
                                if(ArvoreBinariaBusca.preencher(raiz, chaveComando)){
                                    System.out.println(chaveComando+" adicionado");
                                    ArvoreBinariaBusca.calcularAltura(raiz);
                                    ArvoreBinariaBusca.contarSubNos(raiz);
                                }else{
                                    System.out.println(chaveComando +" Já está na árvore, não pode ser inserido");
                                }
                                break;  
                            case "IMPRIMA": 
                                ArvoreBinariaBusca.imprimir(raiz, chaveComando);
                                break;  
                            case "REMOVA": 
                                if( ArvoreBinariaBusca.checarRemocao(raiz, chaveComando)){
                                    System.out.println(chaveComando+" removido");
                                    ArvoreBinariaBusca.calcularAltura(raiz);
                                }else{
                                    System.out.println(chaveComando +" não está na árvore, não pode ser removido");
                                }
                                break;
                            case "POSICAO":
                                int posicao = ArvoreBinariaBusca.posicao(raiz, chaveComando, 0);
                                System.out.println(posicao);
                                break;
                            case "ENESIMO":
                                int elemento = ArvoreBinariaBusca.enesimoElemento(raiz, chaveComando);
                                System.out.println(elemento);
                                break;
                            case "MEDIA":
                                double media = ArvoreBinariaBusca.media(raiz, chaveComando);
                                System.out.println(String.format("%,.3f", media));
                                break;
                            case "BUSCAR":
                                No no = ArvoreBinariaBusca.buscar(raiz, chaveComando);
                                if(no!=null){
                                    System.out.println("Chave encontrada");
                                }else{
                                    System.out.println("Chave não encontrada");
                                }
                                break;
                            default:   
                                System.out.println("Comando não encontrado");  
                            }
                    } else {
                        switch (comando) {   
                            case "PREORDEM": 
                                String sequencia = "";
                                sequencia = ArvoreBinariaBusca.preOrdem(raiz, sequencia);
                                System.out.println(sequencia);
                                break;  
                            case "CHEIA": 
                                if(ArvoreBinariaBusca.ehCheia(raiz, raiz.getAltura())){
                                    System.out.println("A árvore é cheia");
                                }else{
                                    System.out.println("A árvore não é cheia");
                                }
                                break;
                            case "COMPLETA": 
                                if(ArvoreBinariaBusca.ehCompleta(raiz, raiz.getAltura())){
                                    System.out.println("A árvore é completa");
                                }else{
                                    System.out.println("A árvore não é completa");
                                }
                                break;
                            case "MEDIANA":
                                int mediana = ArvoreBinariaBusca.mediana(raiz);
                                System.out.println(mediana);
                                break;
                            default:   
                                System.out.println("Comando não encontrado");  
                            }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }   
    }
}
