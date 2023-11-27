/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.trabalhopoo2;

import javax.swing.JLabel;

/**
 *
 * @author André
 * @author Gabriel
 */
public class Peca extends JLabel {

    private Tabuleiro tabuleiro;
    private Localizacao localizacao;

    public Peca(Tabuleiro tabuleiro, Localizacao localizacao) {
        this.tabuleiro = tabuleiro;
        setLocalizacao(localizacao);
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    /**
     * Define a nova localização do objeto no tabuleiro, atualizando a
     * representação do tabuleiro conforme necessário.
     *
     * Se o objeto já ocupava uma localização anteriormente, essa localização é
     * limpa no tabuleiro. Em seguida, a localização do objeto é definida como a
     * nova localização especificada. Finalmente, o objeto é colocado na nova
     * localização no tabuleiro.
     *
     * @param novaLocalizacao
     */
    public void setLocalizacao(Localizacao novaLocalizacao) {
        try {
            if (localizacao != null) {
                tabuleiro.limpar(localizacao);
            }
            localizacao = novaLocalizacao;
            tabuleiro.lugarPecaNoTabuleiro(this, localizacao);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Exceção: tentativa de acesso fora do limite");
        }
    }

    /**
     * Move o objeto para a posição a direita no tabuleiro, desde que a
     * localização a direita esteja livre. Se a localização a direita não
     * estiver livre, o objeto permanecerá na mesma posição.
     */
    public void moverParaDireita() {
        while (tabuleiro.localizacaoDireitaLivre(localizacao)) {
            setLocalizacao(new Localizacao(localizacao.getLinha(), localizacao.getColuna() + 1));
        }
    }

    /**
     * Move o objeto para a posição a esquerda no tabuleiro, desde que a
     * localização a esquerda esteja livre. Se a localização a esquerda não
     * estiver livre, o objeto permanecerá na mesma posição.
     */
    public void moverParaEsquerda() {
        while (tabuleiro.localizacaoEsquerdaLivre(localizacao)) {
            setLocalizacao(new Localizacao(localizacao.getLinha(), localizacao.getColuna() - 1));
        }
    }

    /**
     * Move o objeto para a posição abaixo no tabuleiro, desde que a localização
     * abaixo esteja livre. Se a localização abaixo não estiver livre, o objeto
     * permanecerá na mesma posição.
     */
    public void moverParaBaixo() {
        while (tabuleiro.localizacaoAbaixoLivre(localizacao)) {
            setLocalizacao(new Localizacao(localizacao.getLinha() + 1, localizacao.getColuna()));
        }
    }

    /**
     * Move o objeto para a posição acima no tabuleiro, desde que a localização
     * acima esteja livre. Se a localização acima não estiver livre, o objeto
     * permanecerá na mesma posição.
     */
    public void moverParaCima() {
        while (tabuleiro.localizacaoAcimaLivre(localizacao)) {
            setLocalizacao(new Localizacao(localizacao.getLinha() - 1, localizacao.getColuna()));
        }
    }
}
