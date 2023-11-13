/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.trabalhopoo2;

/**
 *
 * @author André
 * @author Gabriel
 */
public class Jogador {

    private String nome;
    private Tabuleiro tabuleiro;
    private Tok tok;
    private Peca[] pecas;

    public Jogador(String nome, Tabuleiro tabuleiro, Tok tok) {
        this.nome = nome;
        this.tabuleiro = tabuleiro;
        this.tok = tok;
        pecas = new Peca[5];

        if (nome.equals("Jogador1")) {
            for (int i = 0; i < 5; i++) {
                pecas[i] = new Peca(tabuleiro, new Localizacao(0, i));
                pecas[i].setNome("1");
            }
        } else if (nome.equals("Jogador2")) {
            for (int i = 0; i < 5; i++) {
                pecas[i] = new Peca(tabuleiro, new Localizacao(4, i));
                pecas[i].setNome("2");
            }
        }
    }

    /**
     * Obtém uma peça específica do jogador com base no índice.
     *
     * @param i O índice da peça desejada (de 0 a 4).
     * @return A peça correspondente ao índice especificado.
     */
    public Peca getPeca(int i) {
        return pecas[i];
    }

    public Tok getTok() {
        return tok;
    }
}
