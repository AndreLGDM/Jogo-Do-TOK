/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.trabalhopoo2;

import java.util.Scanner;

/**
 *
 * @author André
 * @author Gabriel
 */
public class Localizacao {

    private int linha;
    private int coluna;

    public Localizacao(int linha, int coluna) {
        try {
            if (linha > 4 || coluna > 4 || linha < 0 || coluna < 0) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.err.println("Exceção: Localização está fora das posições cabíveis do tabuleiro");
        }
        this.linha = linha;
        this.coluna = coluna;
        if (linha > 4 || coluna > 4 || linha < 0 || coluna < 0) {
            garantirValorAceitavel();
        }
    }

    /**
     * Este método garante que os valores de linha e coluna estejam dentro dos
     * limites aceitáveis para uma matriz 5x5 (entre 0 e 4).
     *
     * Se o valor de linha estiver fora dos limites, o usuário será solicitado a
     * fornecer um novo valor até que um valor válido seja inserido.
     *
     * Se o valor de coluna estiver fora dos limites, o usuário também será
     * solicitado a fornecer um novo valor até que um valor válido seja
     * inserido.
     */
    public void garantirValorAceitavel() {
        Scanner in = new Scanner(System.in);
        if (linha >= 5 || linha < 0) {
            do {
                System.out.print("Preencha novo valor linha(entre 0 e 4): ");
                linha = in.nextInt();
            } while (linha > 5);
        }
        if (coluna >= 5) {
            do {
                System.out.print("Preencha novo valor para coluna(entre 0 e 4): ");
                coluna = in.nextInt();
            } while (coluna > 5);
        }
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    @Override
    public String toString() {
        return "Linha: " + linha + " Coluna: " + coluna;
    }

}
