/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.trabalhopoo2;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author André Medeiros
 * @author Gabriel Amorim
 */
public class JogoTokGui extends javax.swing.JFrame {

    private Tabuleiro tabuleiroObject = new Tabuleiro();
    private Tok pecaTok = new Tok(tabuleiroObject);
    private Jogador jogador1 = new Jogador("Jogador1", tabuleiroObject, pecaTok);
    private Jogador jogador2 = new Jogador("Jogador2", tabuleiroObject, pecaTok);
    private EstadoJogo estado = new EstadoJogo(pecaTok);
    private JPanel[][] cells;

    /**
     * Creates new form JogoTokGui
     */
    public JogoTokGui() {
        setIconImage(new ImageIcon(getClass().getResource("/com/example/trabalhopoo2/Tabuleiro.png")).getImage());
        initComponents();
        transformarPanelEmMatriz();
    }

    /**
     * Transforma um conjunto de JPanels em uma matriz e adiciona-os ao tabuleiro.
     *
     * Este método é responsável por criar uma matriz bidimensional de JPanels com
     * base
     * em um conjunto predefinido de JPanels e adicioná-los ao tabuleiro do jogo. A
     * matriz
     * 'cells' é atualizada para refletir essa transformação.
     *
     * @see Tabuleiro#getAltura()
     * @see Tabuleiro#getLargura()
     */
    private void transformarPanelEmMatriz() {
        cells = new JPanel[tabuleiroObject.getAltura()][tabuleiroObject.getLargura()];
        JPanel[][] jPanelArray = {
                { jPanel2, jPanel3, jPanel4, jPanel5, jPanel6 },
                { jPanel7, jPanel8, jPanel9, jPanel10, jPanel11 },
                { jPanel12, jPanel13, jPanel14, jPanel15, jPanel16 },
                { jPanel17, jPanel18, jPanel19, jPanel20, jPanel21 },
                { jPanel22, jPanel23, jPanel24, jPanel25, jPanel26 } };

        for (int i = 0; i < tabuleiroObject.getAltura(); i++) {
            for (int j = 0; j < tabuleiroObject.getLargura(); j++) {
                cells[i][j] = jPanelArray[i][j];
                cells[i][j].setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
                tabuleiro.add(cells[i][j]);
            }
        }
    }

    /**
     * Remove todos os botões presentes em cada célula do tabuleiro.
     *
     * Este método itera sobre a matriz de células do tabuleiro, chamando o método
     * {@code removerBotao} para remover todos os botões contidos em cada célula.
     * 
     * @see #removerBotao(Container)
     */
    private void removerTodosBotoes() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                removerBotao(cells[i][j]);
            }
        }
    }

    /**
     * Remove todos os botões contidos no Container fornecido.
     *
     * Este método percorre todos os componentes dentro do Container fornecido e
     * remove
     * aqueles que são instâncias de JButton, removendo assim todos os botões
     * presentes.
     *
     * @param container O Container do qual os botões serão removidos.
     */
    private void removerBotao(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                container.remove(component);
            }
        }
    }

    /**
     * Determina a Localizacao associada ao JPanel correspondente ao JLabel clicado.
     *
     * Este método é usado para identificar a posição no tabuleiro (Localizacao)
     * associada
     * ao JLabel clicado durante um evento de clique do mouse.
     *
     * @param label O JLabel clicado que desencadeou o evento de clique do mouse.
     * @param evt   O evento de clique do mouse associado.
     * @return A Localizacao correspondente ao JPanel associado ao JLabel clicado.
     *         Retorna null se a correspondência não for encontrada.
     */
    private Localizacao verificarQualJpanel(JLabel label, java.awt.event.MouseEvent evt) {
        label = (JLabel) evt.getSource();

        int coordenadaI = -1;
        int coordenadaJ = -1;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].isAncestorOf(label)) {
                    coordenadaI = i;
                    coordenadaJ = j;
                    return new Localizacao(coordenadaI, coordenadaJ);
                }
            }
        }
        return null;
    }

    /**
     * Cria e retorna um JPanel contendo uma imagem e os nomes dos autores do
     * projeto.
     *
     * Este método cria um JPanel verticalmente alinhado (BoxLayout.Y_AXIS) que
     * inclui
     * uma imagem dos autores e um JLabel com os nomes "André Medeiros" e "Gabriel
     * Amorim".
     *
     * @return Um JPanel contendo a imagem dos autores e seus nomes.
     */
    public JPanel imagemAutores() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        URL imageURL = getClass().getResource("/com/example/trabalhopoo2/Autores.png");

        ImageIcon icon = new ImageIcon(imageURL);
        JLabel label = new JLabel(icon);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        JLabel texto = new JLabel("André Medeiros e Gabriel Amorim");
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);
        texto.setFont(new Font("Jokerman", Font.ITALIC, 30));
        panel.add(texto);
        return panel;
    }

    /**
     * Exibe um alerta de final de jogo com base na localização atual da peça Tok.
     *
     * Este método verifica a localização da peça Tok e exibe uma mensagem de fim de
     * jogo
     * informando qual jogador venceu, dependendo se a peça está na primeira ou
     * última linha
     * do tabuleiro.
     */
    private void alertarFinalDeJogo() {
        if (pecaTok.getLocalizacao().getLinha() == 0) {
            JOptionPane.showMessageDialog(tabuleiro,
                    "TOK NA PRIMEIRA LINHA, JOGADOR 1 VENCEU O JOGO!!",
                    "FIM DE JOGO",
                    JOptionPane.WARNING_MESSAGE);
        } else if (pecaTok.getLocalizacao().getLinha() == 4) {
            JOptionPane.showMessageDialog(tabuleiro,
                    "TOK NA ULTIMA LINHA, JOGADOR 2 VENCEU O JOGO!!",
                    "FIM DE JOGO",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Verifica se o jogo já foi finalizado com base na localização da peça Tok.
     *
     * Este método verifica se a peça Tok está na primeira ou última linha do
     * tabuleiro. Se a condição for atendida, exibe um alerta informando que o jogo
     * foi finalizado e qual jogador venceu, retornando true. Caso contrário,
     * retorna false indicando que o jogo ainda não foi finalizado.
     *
     * @return true se o jogo foi finalizado, false caso contrário.
     */
    private boolean alertarJogoFinalizado() {
        if (pecaTok.getLocalizacao().getLinha() == 0) {
            JOptionPane.showMessageDialog(tabuleiro,
                    "JOGO JA FINALIZADO, JOGADOR 1 VENCEU O JOGO!!",
                    "FIM DE JOGO",
                    JOptionPane.WARNING_MESSAGE);
            return true;
        } else if (pecaTok.getLocalizacao().getLinha() == 4) {
            JOptionPane.showMessageDialog(tabuleiro,
                    "JOGO JA FINALIZADO, JOGADOR 2 VENCEU O JOGO!!",
                    "FIM DE JOGO",
                    JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

    /**
     * Exibe um alerta indicando que o Tok está preso.
     *
     * Este método verifica se a lista de posições adjacentes livres está vazia e se
     * é a vez do Jogador 1 ou do Jogador 2. Se a lista estiver vazia e for a vez do
     * Jogador 1, exibe um alerta indicando que o Tok está preso e que o Jogador 2
     * venceu o jogo. Se a lista estiver vazia e for a vez do Jogador 2, exibe um
     * alerta indicando que o Tok está preso e que o Jogador 1 venceu o jogo.
     *
     * @param adjacentesLivres Lista de posições adjacentes livres ao redor do Tok.
     */
    private void alertaTokPreso(LinkedList<Localizacao> adjacentesLivres) {
        if (adjacentesLivres.size() <= 0 && estado.verificarVezJogador()) {
            JOptionPane.showMessageDialog(tabuleiro, "TOK PRESO, JOGADOR 1 VENCEU O JOGO!!", "FIM DE JOGO",
                    JOptionPane.WARNING_MESSAGE);
            pecaTok.setLocalizacao(new Localizacao(0, 0));
        }

        if (adjacentesLivres.size() == 0 && !estado.verificarVezJogador()) {
            JOptionPane.showMessageDialog(tabuleiro, "TOK PRESO, JOGADOR 2 VENCEU O JOGO!!", "FIM DE JOGO",
                    JOptionPane.WARNING_MESSAGE);
            pecaTok.setLocalizacao(new Localizacao(4, 0));
        }
    }

    /**
     * Move uma peça na direção especificada.
     *
     * @param direcao A direção para a qual a peça será movida. Pode ser "Baixo",
     *                "Cima", "Direita" ou "Esquerda".
     * @param index   O índice da peça a ser movida dentro da lista de peças do
     *                jogador.
     */
    private void dizerQualMovimentoPeca(String direcao, int index) {
        Peca peca = (!estado.verificarVezJogador()) ? jogador1.getPeca(index) : jogador2.getPeca(index);

        switch (direcao) {
            case "Baixo":
                peca.moverParaBaixo();
                break;
            case "Cima":
                peca.moverParaCima();
                break;
            case "Direita":
                peca.moverParaDireita();
                break;
            case "Esquerda":
                peca.moverParaEsquerda();
                break;
            default:
        }
    }

    /**
     * Move o TOK (peça especial) na direção especificada.
     *
     * @param direcao A direção para a qual o TOK será movido. Pode ser "Baixo",
     *                "Cima", "Direita" ou "Esquerda".
     */
    private void dizerQualMovimentoTok(String direcao) {
        Tok tok = (!estado.verificarVezJogador()) ? jogador1.getTok() : jogador2.getTok();

        switch (direcao) {
            case "Baixo":
                tok.moverParaBaixo();
                break;
            case "Cima":
                tok.moverParaCima();
                break;
            case "Direita":
                tok.moverParaDireita();
                break;
            case "Esquerda":
                tok.moverParaEsquerda();
                break;
            default:
        }
    }

    /**
     * Move uma peça do jogador 1 no tabuleiro de acordo com as regras do jogo.
     *
     * @param valorI  A linha atual da peça a ser movida.
     * @param valorJ  A coluna atual da peça a ser movida.
     * @param index   O índice da peça a ser movida dentro da lista de peças do
     *                jogador 1.
     * @param label   O rótulo (JLabel) associado à peça a ser movida.
     * @param direcao A direção para a qual a peça será movida.
     */

    private void actionPecaJogador1(final int valorI, final int valorJ, int index, JLabel label, String direcao) {
        if (!estado.verificarVezJogador()) {
            if (estado.verificarTokMovido()) {
                dizerQualMovimentoPeca(direcao, index);
                cells[valorI][valorJ].remove(label);
                cells[jogador1.getPeca(index).getLocalizacao()
                        .getLinha()][jogador1.getPeca(index)
                                .getLocalizacao()
                                .getColuna()]
                        .add(label);
                removerTodosBotoes();
                tabuleiro.revalidate();
                tabuleiro.repaint();
                estado.salvarLocalizacaoTok(pecaTok);
                estado.incrementarRodada();
            } else {
                JOptionPane.showMessageDialog(tabuleiro, "Mova o Tok Primeiramente", "Alerta",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(tabuleiro, "Não é sua vez de Jogar", "Informação",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Move uma peça do jogador 2 no tabuleiro de acordo com as regras do jogo.
     *
     * @param valorI  A linha atual da peça a ser movida.
     * @param valorJ  A coluna atual da peça a ser movida.
     * @param index   O índice da peça a ser movida dentro da lista de peças do
     *                jogador 2.
     * @param label   O rótulo (JLabel) associado à peça a ser movida.
     * @param direcao A direção para a qual a peça será movida.
     */
    private void actionPecaJogador2(final int valorI, final int valorJ, int index, JLabel label, String direcao) {
        if (estado.verificarVezJogador()) {
            if (estado.verificarPrimeiraRodada()) {
                dizerQualMovimentoPeca(direcao, index);
                cells[valorI][valorJ].remove(label);
                cells[jogador2.getPeca(index).getLocalizacao().getLinha()][jogador2.getPeca(index)
                        .getLocalizacao()
                        .getColuna()].add(label);
                removerTodosBotoes();
                tabuleiro.revalidate();
                tabuleiro.repaint();
                estado.incrementarRodada();
            } else {
                if (estado.verificarTokMovido()) {
                    dizerQualMovimentoPeca(direcao, index);
                    cells[valorI][valorJ].remove(label);
                    cells[jogador2.getPeca(index).getLocalizacao().getLinha()][jogador2
                            .getPeca(index)
                            .getLocalizacao()
                            .getColuna()].add(label);
                    removerTodosBotoes();
                    tabuleiro.revalidate();
                    tabuleiro.repaint();
                    estado.salvarLocalizacaoTok(pecaTok);
                    estado.incrementarRodada();
                } else {
                    JOptionPane.showMessageDialog(tabuleiro, "Mova o Tok Primeiramente", "Alerta",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(tabuleiro, "Não é sua vez de Jogar", "Informação",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Move a peça TOK no tabuleiro de acordo com as regras do jogo.
     *
     * @param valorI  A linha atual da peça TOK.
     * @param valorJ  A coluna atual da peça TOK.
     * @param label   O rótulo (JLabel) associado à peça TOK a ser movida.
     * @param direcao A direção para a qual a peça TOK será movida.
     */
    private void actionPecaTok(final int valorI, final int valorJ, JLabel label, String direcao) {
        if (!estado.verificarPrimeiraRodada()) {
            if (!estado.verificarVezJogador()) {
                if (estado.verificarRodada()) {
                    estado.salvarLocalizacaoTok(pecaTok);
                    dizerQualMovimentoTok(direcao);
                    cells[valorI][valorJ].remove(label);
                    cells[jogador1.getTok().getLocalizacao().getLinha()][jogador1.getTok()
                            .getLocalizacao()
                            .getColuna()].add(label);
                    removerTodosBotoes();
                    estado.salvarRodada();
                    tabuleiro.revalidate();
                    tabuleiro.repaint();
                    alertarFinalDeJogo();
                } else {
                    JOptionPane.showMessageDialog(tabuleiro, "TOK já foi movido nesta rodada",
                            "Informação",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (estado.verificarRodada()) {
                    estado.salvarLocalizacaoTok(pecaTok);
                    dizerQualMovimentoTok(direcao);
                    cells[valorI][valorJ].remove(label);
                    cells[jogador2.getTok().getLocalizacao().getLinha()][jogador2.getTok()
                            .getLocalizacao()
                            .getColuna()].add(label);
                    removerTodosBotoes();
                    estado.salvarRodada();
                    tabuleiro.revalidate();
                    tabuleiro.repaint();
                    alertarFinalDeJogo();
                } else {
                    JOptionPane.showMessageDialog(tabuleiro, "TOK já foi movido nesta rodada",
                            "Informação",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(tabuleiro, "Na primeira rodada o TOK não é movido",
                    "Informação",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Move a peça do Jogador 1 no tabuleiro em resposta a eventos de clique do
     * mouse.
     *
     * @param label O rótulo (JLabel) associado à peça do Jogador 1 a ser movida.
     * @param evt   O evento de clique do mouse que acionou a movimentação da peça.
     */
    private void movimentarPecaJogador1(JLabel label, java.awt.event.MouseEvent evt) {
        if (!alertarJogoFinalizado()) {

            LinkedList<Localizacao> adjacentesLivres = tabuleiroObject
                    .verificarAdjacentesLivres(verificarQualJpanel(label, evt));
            Localizacao localizacaoAtual = (Localizacao) verificarQualJpanel(label, evt);

            final int valorI = localizacaoAtual.getLinha();
            final int valorJ = localizacaoAtual.getColuna();

            int index = (int) Integer.parseInt(label.getName());

            for (Localizacao adjacente : adjacentesLivres) {
                int linhaAdjacente = adjacente.getLinha();
                int colunaAdjacente = adjacente.getColuna();

                JButton novoBotao = new JButton("Novo Botão");
                novoBotao.setText(null);
                novoBotao.setPreferredSize(new Dimension(50, 50));
                novoBotao.setOpaque(false);
                novoBotao.setContentAreaFilled(false);
                novoBotao.setBorderPainted(false);

                if (linhaAdjacente > localizacaoAtual.getLinha()) {
                    cells[linhaAdjacente][colunaAdjacente].add(novoBotao);
                    novoBotao.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/com/example/trabalhopoo2/SetaBaixo.png")));
                    novoBotao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            actionPecaJogador1(valorI, valorJ, index, label, "Baixo");
                        }
                    });
                }
                if (colunaAdjacente > localizacaoAtual.getColuna()) {
                    cells[linhaAdjacente][colunaAdjacente].add(novoBotao);
                    novoBotao.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/com/example/trabalhopoo2/SetaDireita.png")));
                    novoBotao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            actionPecaJogador1(valorI, valorJ, index, label, "Direita");
                        }
                    });
                }
                if (linhaAdjacente < localizacaoAtual.getLinha()) {
                    cells[linhaAdjacente][colunaAdjacente].add(novoBotao);
                    novoBotao.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/com/example/trabalhopoo2/SetaCima.png")));
                    novoBotao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            actionPecaJogador1(valorI, valorJ, index, label, "Cima");
                        }
                    });
                }
                if (colunaAdjacente < localizacaoAtual.getColuna()) {
                    cells[linhaAdjacente][colunaAdjacente].add(novoBotao);
                    novoBotao.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/com/example/trabalhopoo2/SetaEsquerda.png")));
                    novoBotao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            actionPecaJogador1(valorI, valorJ, index, label, "Esquerda");
                        }
                    });
                }
            }
            tabuleiro.revalidate();
            tabuleiro.repaint();
        }
    }

    /**
     * Move a peça do Jogador 2 no tabuleiro em resposta a eventos de clique do
     * mouse.
     *
     * @param label O rótulo (JLabel) associado à peça do Jogador 1 a ser movida.
     * @param evt   O evento de clique do mouse que acionou a movimentação da peça.
     */
    private void movimentarPecaJogador2(JLabel label, java.awt.event.MouseEvent evt) {
        if (!alertarJogoFinalizado()) {

            LinkedList<Localizacao> adjacentesLivres = tabuleiroObject
                    .verificarAdjacentesLivres(verificarQualJpanel(label, evt));
            Localizacao localizacaoAtual = (Localizacao) verificarQualJpanel(label, evt);

            final int valorI = localizacaoAtual.getLinha();
            final int valorJ = localizacaoAtual.getColuna();

            int index = (int) Integer.parseInt(label.getName());

            for (Localizacao adjacente : adjacentesLivres) {
                int linhaAdjacente = adjacente.getLinha();
                int colunaAdjacente = adjacente.getColuna();

                JButton novoBotao = new JButton("Novo Botão");
                novoBotao.setText(null);
                novoBotao.setPreferredSize(new Dimension(50, 50));
                novoBotao.setOpaque(false);
                novoBotao.setContentAreaFilled(false);
                novoBotao.setBorderPainted(false);

                if (linhaAdjacente > localizacaoAtual.getLinha()) {
                    cells[linhaAdjacente][colunaAdjacente].add(novoBotao);
                    novoBotao.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/com/example/trabalhopoo2/SetaBaixo.png")));
                    novoBotao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            actionPecaJogador2(valorI, valorJ, index, label, "Baixo");
                        }
                    });
                }
                if (colunaAdjacente > localizacaoAtual.getColuna()) {
                    cells[linhaAdjacente][colunaAdjacente].add(novoBotao);
                    novoBotao.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/com/example/trabalhopoo2/SetaDireita.png")));
                    novoBotao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            actionPecaJogador2(valorI, valorJ, index, label, "Direita");
                        }
                    });
                }
                if (linhaAdjacente < localizacaoAtual.getLinha()) {
                    cells[linhaAdjacente][colunaAdjacente].add(novoBotao);
                    novoBotao.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/com/example/trabalhopoo2/SetaCima.png")));
                    novoBotao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            actionPecaJogador2(valorI, valorJ, index, label, "Cima");
                        }
                    });
                }
                if (colunaAdjacente < localizacaoAtual.getColuna()) {
                    cells[linhaAdjacente][colunaAdjacente].add(novoBotao);
                    novoBotao.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/com/example/trabalhopoo2/SetaEsquerda.png")));
                    novoBotao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            actionPecaJogador2(valorI, valorJ, index, label, "Esquerda");
                        }
                    });
                }
            }
            tabuleiro.revalidate();
            tabuleiro.repaint();
        }
    }

    /**
     * Move a peça TOK no tabuleiro em resposta a eventos de clique do
     * mouse.
     *
     * @param label O rótulo (JLabel) associado à peça do Jogador 1 a ser movida.
     * @param evt   O evento de clique do mouse que acionou a movimentação da peça.
     */
    private void movimentarPecaTOk(JLabel label, java.awt.event.MouseEvent evt) {
        if (!alertarJogoFinalizado()) {

            LinkedList<Localizacao> adjacentesLivres = tabuleiroObject
                    .verificarAdjacentesLivres(verificarQualJpanel(label, evt));
            Localizacao localizacaoAtual = (Localizacao) verificarQualJpanel(label, evt);

            final int valorI = localizacaoAtual.getLinha();
            final int valorJ = localizacaoAtual.getColuna();

            alertaTokPreso(adjacentesLivres);

            for (Localizacao adjacente : adjacentesLivres) {
                int linhaAdjacente = adjacente.getLinha();
                int colunaAdjacente = adjacente.getColuna();

                JButton novoBotao = new JButton("Novo Botão");
                novoBotao.setText(null);
                novoBotao.setPreferredSize(new Dimension(50, 50));
                novoBotao.setOpaque(false);
                novoBotao.setContentAreaFilled(false);
                novoBotao.setBorderPainted(false);

                if (linhaAdjacente > localizacaoAtual.getLinha()) {
                    cells[linhaAdjacente][colunaAdjacente].add(novoBotao);
                    novoBotao.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/com/example/trabalhopoo2/SetaBaixo.png")));
                    novoBotao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            actionPecaTok(valorI, valorJ, label, "Baixo");
                        }
                    });
                }
                if (colunaAdjacente > localizacaoAtual.getColuna()) {
                    cells[linhaAdjacente][colunaAdjacente].add(novoBotao);
                    novoBotao.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/com/example/trabalhopoo2/SetaDireita.png")));
                    novoBotao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            actionPecaTok(valorI, valorJ, label, "Direita");
                        }
                    });
                }
                if (linhaAdjacente < localizacaoAtual.getLinha()) {
                    cells[linhaAdjacente][colunaAdjacente].add(novoBotao);
                    novoBotao.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/com/example/trabalhopoo2/SetaCima.png")));
                    novoBotao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            actionPecaTok(valorI, valorJ, label, "Cima");
                        }
                    });
                }
                if (colunaAdjacente < localizacaoAtual.getColuna()) {
                    cells[linhaAdjacente][colunaAdjacente].add(novoBotao);
                    novoBotao.setIcon(new javax.swing.ImageIcon(
                            getClass().getResource("/com/example/trabalhopoo2/SetaEsquerda.png")));
                    novoBotao.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            actionPecaTok(valorI, valorJ, label, "Esquerda");
                        }
                    });
                }
            }
            tabuleiro.revalidate();
            tabuleiro.repaint();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">
    private void initComponents() {

        tabuleiro = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setName("0");
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel2.setName("1");
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setName("2");
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel4.setName("3");
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setName("4");
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel7.setName("0");
        jPanel23 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel8.setName("1");
        jPanel24 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel9.setName("2");
        jPanel25 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel10.setName("3");
        jPanel26 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel11.setName("4");
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JogoTokGui");
        setName("tabuleiro"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        tabuleiro.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));
        tabuleiro.setOpaque(false);
        tabuleiro.setPreferredSize(new java.awt.Dimension(500, 500));
        tabuleiro.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(135, 206, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel1.setBackground(new java.awt.Color(204, 204, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/trabalhopoo2/Peca1.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1)
                                .addContainerGap(27, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(24, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(24, 24, 24)));

        tabuleiro.add(jPanel2);
        jPanel2.setBounds(0, 0, 100, 100);

        jPanel3.setBackground(new java.awt.Color(0, 0, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel2.setBackground(new java.awt.Color(204, 204, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/trabalhopoo2/Peca1.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE)));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)));

        tabuleiro.add(jPanel3);
        jPanel3.setBounds(100, 0, 100, 100);

        jPanel4.setBackground(new java.awt.Color(135, 206, 250));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel3.setBackground(new java.awt.Color(204, 204, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/trabalhopoo2/Peca1.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(23, 23, 23)));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(23, 23, 23)));

        tabuleiro.add(jPanel4);
        jPanel4.setBounds(200, 0, 100, 100);

        jPanel5.setBackground(new java.awt.Color(0, 0, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel4.setBackground(new java.awt.Color(204, 204, 0));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/trabalhopoo2/Peca1.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel4)
                                .addContainerGap(25, Short.MAX_VALUE)));
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel4)
                                .addContainerGap(23, Short.MAX_VALUE)));

        tabuleiro.add(jPanel5);
        jPanel5.setBounds(300, 0, 100, 100);

        jPanel6.setBackground(new java.awt.Color(135, 206, 250));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel5.setBackground(new java.awt.Color(204, 204, 0));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/trabalhopoo2/Peca1.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel5)
                                .addContainerGap(26, Short.MAX_VALUE)));
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(23, 23, 23)));

        tabuleiro.add(jPanel6);
        jPanel6.setBounds(400, 0, 100, 100);

        jPanel7.setBackground(new java.awt.Color(0, 0, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel7);
        jPanel7.setBounds(0, 100, 100, 100);

        jPanel8.setBackground(new java.awt.Color(135, 206, 250));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel8);
        jPanel8.setBounds(100, 100, 100, 100);

        jPanel9.setBackground(new java.awt.Color(0, 0, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel9);
        jPanel9.setBounds(200, 100, 100, 100);

        jPanel10.setBackground(new java.awt.Color(135, 206, 250));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel10);
        jPanel10.setBounds(300, 100, 100, 100);

        jPanel11.setBackground(new java.awt.Color(0, 0, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel11);
        jPanel11.setBounds(400, 100, 100, 100);

        jPanel13.setBackground(new java.awt.Color(0, 0, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel13Layout.setVerticalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel13);
        jPanel13.setBounds(100, 200, 100, 100);

        jPanel20.setBackground(new java.awt.Color(135, 206, 250));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel20Layout.setVerticalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel20);
        jPanel20.setBounds(300, 300, 100, 100);

        jPanel21.setBackground(new java.awt.Color(0, 0, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel21Layout.setVerticalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel21);
        jPanel21.setBounds(400, 300, 100, 100);

        jPanel14.setBackground(new java.awt.Color(135, 205, 250));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/trabalhopoo2/TokIcon.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel6)
                                .addContainerGap(25, Short.MAX_VALUE)));
        jPanel14Layout.setVerticalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel6)
                                .addContainerGap(24, Short.MAX_VALUE)));

        tabuleiro.add(jPanel14);
        jPanel14.setBounds(200, 200, 100, 100);

        jPanel16.setBackground(new java.awt.Color(135, 206, 250));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel16Layout.setVerticalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel16);
        jPanel16.setBounds(400, 200, 100, 100);

        jPanel17.setBackground(new java.awt.Color(0, 0, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel17Layout.setVerticalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel17);
        jPanel17.setBounds(0, 300, 100, 100);

        jPanel15.setBackground(new java.awt.Color(0, 0, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel15);
        jPanel15.setBounds(300, 200, 100, 100);

        jPanel19.setBackground(new java.awt.Color(0, 0, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel19Layout.setVerticalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel19);
        jPanel19.setBounds(200, 300, 100, 100);

        jPanel18.setBackground(new java.awt.Color(135, 206, 250));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel18Layout.setVerticalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel18);
        jPanel18.setBounds(100, 300, 100, 100);

        jPanel12.setBackground(new java.awt.Color(135, 206, 250));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));
        jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE));

        tabuleiro.add(jPanel12);
        jPanel12.setBounds(0, 200, 100, 100);

        jPanel22.setBackground(new java.awt.Color(135, 206, 250));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel7.setBackground(new java.awt.Color(255, 0, 0));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/trabalhopoo2/Peca2.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel7)
                                .addContainerGap(29, Short.MAX_VALUE)));
        jPanel22Layout.setVerticalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel7)
                                .addContainerGap(25, Short.MAX_VALUE)));

        tabuleiro.add(jPanel22);
        jPanel22.setBounds(0, 400, 100, 100);

        jPanel23.setBackground(new java.awt.Color(0, 0, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel8.setBackground(new java.awt.Color(255, 0, 0));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/trabalhopoo2/Peca2.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel8)
                                .addContainerGap(23, Short.MAX_VALUE)));
        jPanel23Layout.setVerticalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(23, 23, 23)));

        tabuleiro.add(jPanel23);
        jPanel23.setBounds(100, 400, 100, 100);

        jPanel24.setBackground(new java.awt.Color(135, 206, 250));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel9.setBackground(new java.awt.Color(255, 0, 0));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/trabalhopoo2/Peca2.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(23, 23, 23)));
        jPanel24Layout.setVerticalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(23, 23, 23)));

        tabuleiro.add(jPanel24);
        jPanel24.setBounds(200, 400, 100, 100);

        jPanel25.setBackground(new java.awt.Color(0, 0, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel10.setBackground(new java.awt.Color(255, 0, 0));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/trabalhopoo2/Peca2.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel10)
                                .addContainerGap(25, Short.MAX_VALUE)));
        jPanel25Layout.setVerticalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addGap(23, 23, 23)));

        tabuleiro.add(jPanel25);
        jPanel25.setBounds(300, 400, 100, 100);

        jPanel26.setBackground(new java.awt.Color(135, 206, 250));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel11.setBackground(new java.awt.Color(255, 0, 0));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/trabalhopoo2/Peca2.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
                jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel11)
                                .addContainerGap(23, Short.MAX_VALUE)));
        jPanel26Layout.setVerticalGroup(
                jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel11)
                                .addContainerGap(23, Short.MAX_VALUE)));

        tabuleiro.add(jPanel26);
        jPanel26.setBounds(400, 400, 100, 100);

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setText("Jogo");

        jMenuItem1.setText("Reiniciar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Sair");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Autores");

        jMenuItem3.setText("Ver autores");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(tabuleiro, javax.swing.GroupLayout.PREFERRED_SIZE, 500,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(167, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(tabuleiro, javax.swing.GroupLayout.PREFERRED_SIZE, 500,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(36, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }// GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
        dispose();
        inicializarGui();
    }// GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem3ActionPerformed
        JOptionPane.showMessageDialog(tabuleiro, imagemAutores(), "Autores",
                JOptionPane.UNDEFINED_CONDITION);
    }// GEN-LAST:event_jMenuItem3ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel1MouseClicked
        removerTodosBotoes();
        movimentarPecaJogador1(jLabel1, evt);
    }// GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel2MouseClicked
        removerTodosBotoes();
        movimentarPecaJogador1(jLabel2, evt);
    }// GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel3MouseClicked
        removerTodosBotoes();
        movimentarPecaJogador1(jLabel3, evt);
    }// GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel4MouseClicked
        removerTodosBotoes();
        movimentarPecaJogador1(jLabel4, evt);
    }// GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel5MouseClicked
        removerTodosBotoes();
        movimentarPecaJogador1(jLabel5, evt);
    }// GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel6MouseClicked
        removerTodosBotoes();
        movimentarPecaTOk(jLabel6, evt);
    }// GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel7MouseClicked
        removerTodosBotoes();
        movimentarPecaJogador2(jLabel7, evt);
    }// GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel8MouseClicked
        removerTodosBotoes();
        movimentarPecaJogador2(jLabel8, evt);
    }// GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel9MouseClicked
        removerTodosBotoes();
        movimentarPecaJogador2(jLabel9, evt);
    }// GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel10MouseClicked
        removerTodosBotoes();
        movimentarPecaJogador2(jLabel10, evt);
    }// GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel11MouseClicked
        removerTodosBotoes();
        movimentarPecaJogador2(jLabel11, evt);
    }// GEN-LAST:event_jLabel11MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        inicializarGui();
    }

    public static void inicializarGui() {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JogoTokGui.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JogoTokGui.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JogoTokGui.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JogoTokGui.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JogoTokGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel tabuleiro;
    // End of variables declaration
}
