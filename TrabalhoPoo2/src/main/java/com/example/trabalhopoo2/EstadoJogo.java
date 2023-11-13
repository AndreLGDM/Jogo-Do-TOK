package com.example.trabalhopoo2;

public class EstadoJogo {

    private Jogador jogador;
    private Jogador jogador2;
    private Localizacao localizacaoAnteriorTok;
    private int rodada = 0;

    public EstadoJogo(Jogador jogador, Jogador jogador2) {
        this.jogador = jogador;
        this.jogador2 = jogador2;
        localizacaoAnteriorTok = jogador.getTok().getLocalizacao();
    }

    public void salvarLocalizacaoTok(Tok tok) {
        localizacaoAnteriorTok = tok.getLocalizacao();
    }

    public boolean verificarTokMovido() {
        return !localizacaoAnteriorTok.equals(jogador.getTok().getLocalizacao());
    }

    public boolean verificarPrimeiraRodada() {
        return rodada == 0;
    }

    public boolean verificarVezJogador() {
        return rodada % 2 == 0;
    }

    public void incrementarRodada() {
        rodada++;
    }
}
