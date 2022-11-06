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

                try (Scanner sc = new Scanner(line)) {
                    String comando = sc.next();
                    if (comando.matches("ENESIMO|INSIRA|IMPRIMA|REMOVA|POSICAO|BUSCAR|MEDIA")) {
                        // comando.equals("ENESIMO") comando.equals("INSIRA") comando.equals("IMPRIMA") 
                        // comando.equals("REMOVA") comando.equals("POSICAO") comando.equals("BUSCAR")
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
                                if(ArvoreBinariaBusca.remover(raiz, chaveComando)){
                                    System.out.println(chaveComando+" removido");
                                    ArvoreBinariaBusca.calcularAltura(raiz);
                                    ArvoreBinariaBusca.contarSubNos(raiz);
                                }else{
                                    System.out.println(chaveComando +" não está na árvore, não pode ser removido");
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
