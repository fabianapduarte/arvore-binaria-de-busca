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
        String sequencia = sequenciaAnterior + String.valueOf(raiz.getChave()) + " ";

        if (raiz.getEsquerda() != null) {
            sequencia = preOrdem(raiz.getEsquerda(), sequencia);
        }
        if (raiz.getDireita() != null) {
            sequencia = preOrdem(raiz.getDireita(), sequencia);
        }

        return sequencia;
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

    public static int posicao(No raiz, int elemento, int somaNosAnteriores) {
        int novaSoma;

        if (raiz.getChave() == elemento) {
            return somaNosAnteriores + 1;
        } else if (raiz.getChave() > elemento) {
            if (somaNosAnteriores != 0) {
                novaSoma = somaNosAnteriores - raiz.getQtdNosEsquerda();
            } else {
                novaSoma = raiz.getQtdNosEsquerda();
            }
            return posicao(raiz.getEsquerda(), elemento, novaSoma);
        } else {
            novaSoma = somaNosAnteriores + raiz.getQtdNosEsquerda() + 1;
            return posicao(raiz.getDireita(), elemento, novaSoma);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        No raiz;
        String abs = ArvoreBinariaBusca.class.getProtectionDomain().getCodeSource().getLocation()
                .getPath();
        String rel = "../../entrada/" + args[0] + ".txt";
        File file = new File(abs + rel);

        // Leitura da arvore
        Scanner scan;
        if (!file.exists()) {
            file = new File(abs + "../" + rel);
            if (!file.exists()) {
                System.out.println("Nome de arquivo errado!");
                System.exit(0);
            }
        }

        try {
            scan = new Scanner(file);
            raiz = new No(scan.nextInt());
            while (scan.hasNextInt()) {
                int chave = scan.nextInt();
                preencher(raiz, chave);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            raiz = null;
            System.out.println("Raiz inválida!");
        }

        // Leitura dos comandos
        rel = "../../entrada/" + args[1] + ".txt";
        file = new File(abs + rel);
        Scanner scanComandos;
        if (!file.exists()) {
            file = new File(abs + "../" + rel);
            if (!file.exists()) {
                System.out.println("Nome de arquivo errado!");
                System.exit(0);
            }
        }

        try {
            scanComandos = new Scanner(file);
            while (scanComandos.hasNextLine()) {
                String line = scanComandos.nextLine();

                Scanner sc = new Scanner(line);
                String comando = sc.next();
                if (comando.matches("ENESIMO|INSIRA|IMPRIMA|REMOVA|POSICAO|BUSCAR|MEDIA")) {
                    // comando.equals("ENESIMO") comando.equals("INSIRA") comando.equals("IMPRIMA")
                    // comando.equals("REMOVA") comando.equals("POSICAO") comando.equals("BUSCAR")
                    int chaveComando = sc.nextInt();
                    if (comando.equals("INSIRA")) {
                        // System.out.println("COMANDO: "+comando+" NUMERO: "+chaveComando);
                        if (preencher(raiz, chaveComando)) {
                            System.out.println(chaveComando + " adicionado");
                        } else {
                            System.out.println(
                                    chaveComando + " Já está na árvore, não pode ser inserido");
                        }
                    } else if (comando.equals("POSICAO")) {
                        int posicao = posicao(raiz, chaveComando, 0);
                        System.out.println(posicao);
                    }
                } else {
                    if (comando.equals("PREORDEM")) {
                        String sequencia = "";
                        sequencia = preOrdem(raiz, sequencia);
                        System.out.println(sequencia);
                    }
                }
                sc.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
