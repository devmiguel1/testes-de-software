package batalha;

import treinador.GeradorAleatorioInterface;

public class GeradorRecompensaPadrao implements GeradorRecompensa {

    private final GeradorAleatorioInterface gerador;

    public GeradorRecompensaPadrao(
            GeradorAleatorioInterface gerador) {
        this.gerador = gerador;
    }

    @Override
    public int gerarPontos() {
        return (int) (gerador.gerarAletorio() * 51) + 50;
    }
}