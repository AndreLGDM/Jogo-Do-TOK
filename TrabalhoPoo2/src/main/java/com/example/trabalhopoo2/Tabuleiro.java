/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.trabalhopoo2;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author André
 * @author Gabriel
 */
public class Tabuleiro extends JPanel {

    private final int altura = 5;
    private final int largura = 5;
    private final Object[][] tabuleiro;

    public Tabuleiro() {
        tabuleiro = new Object[altura][largura];
    }

    /**
     * Remove o objeto da localização especificada no tabuleiro.
     *
     * Este método permite que você remova o objeto da localização especificada
     * no tabuleiro, tornando a posição vazia (ou seja, definindo-a como nula).
     * A localização é determinada pela linha e coluna fornecidas no parâmetro.
     *
     * @param localizacao A localização que especifica a posição no tabuleiro da
     *                    qual deseja-se remover o objeto.
     */
    public void limpar(Localizacao localizacao) {
        tabuleiro[localizacao.getLinha()][localizacao.getColuna()] = null;
    }

    /**
     * Coloca uma peça em uma localização específica do tabuleiro.
     *
     * Este método permite que você coloque uma peça em uma localização
     * especificada no tabuleiro. A peça é colocada na posição especificada, que
     * é determinada pela linha e coluna fornecidas na localização.
     *
     * @param peca        A peça a ser colocada na localização especificada do
     *                    tabuleiro.
     * @param localizacao A localização que determina a posição no tabuleiro
     *                    onde a peça será colocada.
     */
    public void lugarPecaNoTabuleiro(Object peca, Localizacao localizacao) {
        tabuleiro[localizacao.getLinha()][localizacao.getColuna()] = peca;
    }

    /**
     * Retorna o objeto localizado na posição especificada no tabuleiro.
     *
     * Este método recebe uma localização como parâmetro e retorna o objeto
     * presente na posição correspondente no tabuleiro. A localização é
     * representada por uma linha e coluna, e o método acessa o tabuleiro para
     * obter o objeto presente nessa posição.
     *
     * @param localizacao A localização que especifica a posição no tabuleiro da
     *                    qual deseja-se obter o objeto.
     * @return O objeto presente na posição especificada no tabuleiro.
     */
    public Object getObjectAt(Localizacao localizacao) {
        return tabuleiro[localizacao.getLinha()][localizacao.getColuna()];
    }

    /**
     * Verifica a disponibilidade da localização a direita da fornercida.
     * 
     * Verifica se a localização à direita da localização fornecida no tabuleiro
     * está livre. Ele verifica se a coluna à direita da localização fornecida
     * não ultrapassa os limites do tabuleiro e se essa posição está vazia (ou
     * seja, não contém um elemento não nulo).
     *
     * @param localizacao A localização de referência a partir da qual se deseja
     *                    verificar a posição à direita.
     * @return true se a posição à direita estiver dentro dos limites do
     *         tabuleiro e estiver vazia; caso contrário, retorna false.
     */
    public boolean localizacaoDireitaLivre(Localizacao localizacao) {
        try {
            if (localizacao.getColuna() + 1 < largura) {
                return tabuleiro[localizacao.getLinha()][localizacao.getColuna() + 1] == null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Exceção: tentativa de acesso fora do limite");
        }
        return false;
    }

    /**
     * Verifica a disponibilidade da localização a esquerda da fornercida.
     * 
     * Verifica se a localização à esquerda da localização fornecida no
     * tabuleiro está livre. Ele verifica se a coluna à esquerda da localização
     * fornecida não ultrapassa os limites do tabuleiro e se essa posição está
     * vazia (ou seja, não contém um elemento não nulo).
     *
     * @param localizacao A localização de referência a partir da qual se deseja
     *                    verificar a posição à esquerda.
     * @return true se a posição à esquerda estiver dentro dos limites do
     *         tabuleiro e estiver vazia; caso contrário, retorna false.
     */
    public boolean localizacaoEsquerdaLivre(Localizacao localizacao) {
        try {
            if (localizacao.getColuna() - 1 > -1) {
                return tabuleiro[localizacao.getLinha()][localizacao.getColuna() - 1] == null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Exceção: tentativa de acesso fora do limite");
        }
        return false;
    }

    /**
     * Verifica a disponibilidade da localização abaixo da fornercida.
     * 
     * Verifica se a localização abaixo da localização fornecida no tabuleiro
     * está livre. Ele verifica se a linha inferior à localização fornecida não
     * ultrapassa os limites do tabuleiro e se essa posição está vazia (ou seja,
     * não contém um elemento não nulo).
     *
     * @param localizacao A localização de referência a partir da qual se deseja
     *                    verificar a posição abaixo.
     * @return true se a posição abaixo estiver dentro dos limites do tabuleiro
     *         e estiver vazia; caso contrário, retorna false.
     */
    public boolean localizacaoAbaixoLivre(Localizacao localizacao) {
        try {
            if (localizacao.getLinha() + 1 < altura) {
                return tabuleiro[localizacao.getLinha() + 1][localizacao.getColuna()] == null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Exceção: tentativa de acesso fora do Limite");
        }
        return false;
    }

    /**
     * Verifica a disponibilidade da localização acima da fornercida.
     * 
     * Verifica se a localização acima da localização fornecida no tabuleiro
     * está livre. Ele verifica se a linha superior à localização fornecida não
     * ultrapassa os limites do tabuleiro e se essa posição está vazia (ou seja,
     * não contém um elemento não nulo).
     *
     * @param localizacao A localização de referência a partir da qual se deseja
     *                    verificar a posição acima.
     * @return true se a posição acima estiver dentro dos limites do tabuleiro e
     *         estiver vazia; caso contrário, retorna false.
     */
    public boolean localizacaoAcimaLivre(Localizacao localizacao) {
        try {
            if (localizacao.getLinha() - 1 > -1) {
                return tabuleiro[localizacao.getLinha() - 1][localizacao.getColuna()] == null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Exceção: tentativa de acesso fora do Limite");
        }
        return false;
    }

    /**
     * Verifica se as posições ao lado da localização fornecida estão livres.
     * 
     * Verifica se as localizações ao redor da localização fornecida estão livres,
     * desconsiderando as posições diagonais. Ele verifica se a linha superior,
     * linha inferior, coluna a direita, coluna a esquerda não ultrapassam os
     * limites do tabuleiro e se essa posição está vazia (ou seja, não contém um
     * elemento não nulo).
     * 
     * @param localizacao localização de referência a partir da qual se deseja
     *                    verificar as posições adjacentes
     * @return LinkedList com a lista de localizações adjacentes vazias
     *         identificadas ou lista vazia caso não seja encontrada nenhuma.
     */
    public LinkedList<Localizacao> verificarAdjacentesLivres(Localizacao localizacao) {
        LinkedList<Localizacao> localizacoes = new LinkedList<Localizacao>();
        if (localizacao.getLinha() + 1 < altura
                && tabuleiro[localizacao.getLinha() + 1][localizacao.getColuna()] == null) {
            localizacoes.add(new Localizacao(localizacao.getLinha() + 1, localizacao.getColuna()));
        }
        if (localizacao.getLinha() - 1 > -1 && tabuleiro[localizacao.getLinha() - 1][localizacao.getColuna()] == null) {
            localizacoes.add(new Localizacao(localizacao.getLinha() - 1, localizacao.getColuna()));
        }
        if (localizacao.getColuna() + 1 < largura
                && tabuleiro[localizacao.getLinha()][localizacao.getColuna() + 1] == null) {
            localizacoes.add(new Localizacao(localizacao.getLinha(), localizacao.getColuna() + 1));
        }
        if (localizacao.getColuna() - 1 > -1
                && tabuleiro[localizacao.getLinha()][localizacao.getColuna() - 1] == null) {
            localizacoes.add(new Localizacao(localizacao.getLinha(), localizacao.getColuna() - 1));
        }
        return localizacoes;
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

}
