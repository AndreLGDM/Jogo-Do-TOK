package com.example.trabalhopoo2;

/**
 *
 * @author André
 * @author Gabriel
 */
public class EstadoJogo {

    private Localizacao localizacaoAnteriorTok;
    private int rodadaSalva = 0;
    private int rodada = 0;
    private Tok tok;

    public EstadoJogo(Tok tok) {
        this.tok = tok;
        localizacaoAnteriorTok = tok.getLocalizacao();
    }

    /**
     * Salva a localização de um objeto Tok.
     * Atualiza a localização anterior para a localização do Tok fornecido.
     *
     * @param tok O objeto Tok cuja localização será salva.
     */
    public void salvarLocalizacaoTok(Tok tok) {
        localizacaoAnteriorTok = tok.getLocalizacao();
    }

    /**
     * Salva a instância atual da rodada.
     * 
     * Este método copia a referência da rodada atual para a variável, permitindo a
     * posterior recuperação do estado da rodada.
     */
    public void salvarRodada() {
        rodadaSalva = rodada;
    }

    /**
     * Verifica se o tok do jogador foi movido para uma nova localização.
     * 
     * Este método compara a localização anterior do tok com a sua localização
     * atual para determinar se houve movimento desde a última verificação.
     * 
     * @return true caso a localização anterior seja diferente da localização atual
     *         e false caso a localização anterior seja igual a localização atual.
     */
    public boolean verificarTokMovido() {
        return !localizacaoAnteriorTok.equals(tok.getLocalizacao());
    }

    /**
     * Verifica se o Jogo está na primeira rodada.
     * 
     * Verifica se o contador de rodada está em 0 para determinar se é a primeira
     * rodada.
     * 
     * @return true caso o contador tok tenha valor igual a 0 e false caso o
     *         contador tenha valor diferente de 0.
     */
    public boolean verificarPrimeiraRodada() {
        return rodada == 0;
    }

    /**
     * Verifica se o jogo mudou de rodada.
     * 
     * Este método compara a rodada salva do jogo com sua rodada atual para
     * determinar se houve mudança de rodada desde a ultima verificação.
     * 
     * @return true caso a rodada salva seja diferente da rodada atual e false
     *         caso a rodada salva seja igual a rodada atual.
     */
    public boolean verificarRodada() {
        return this.rodada != rodadaSalva;
    }

    /**
     * Verifica qual jogador deve fazer sua jogada na rodada atual.
     * 
     * Esta função determina o jogador da vez com base no número da rodada. Se o
     * resto da divisão da rodada por 2 for igual a 0, o segundo jogador está na
     * vez;
     * caso contrário, é a vez do primeiro jogador.
     * 
     * @return true se for a vez do segundo jogador (resto da divisão por 2 é 0),
     *         false se for a vez do primeiro jogador (resto da divisão por 2 é
     *         diferente de 0).
     */
    public boolean verificarVezJogador() {
        return rodada % 2 == 0;
    }

    /**
     * Incrementa o número da rodada atual.
     * 
     * Este método aumenta o valor da rodada em 1, indicando a transição para a
     * próxima rodada do jogo ou evento em que está sendo utilizado.
     */
    public void incrementarRodada() {
        rodada++;
    }

    public int getRodada() {
        return rodada;
    }

}
