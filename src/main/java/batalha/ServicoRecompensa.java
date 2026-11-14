package batalha;

import treinador.Treinador;

public class ServicoRecompensa {

    private final GeradorRecompensa gerador;

    public ServicoRecompensa(GeradorRecompensa gerador) {
        this.gerador = gerador;
    }

    public int concederRecompensa(Treinador vencedor) {

        if (vencedor == null) {
            throw new IllegalArgumentException(
                    "O treinador vencedor nao pode ser nulo."
            );
        }

        int pontos = gerador.gerarPontos();

        if (pontos < 0) {
            throw new IllegalStateException(
                    "A quantidade de pontos nao pode ser negativa."
            );
        }

        vencedor.adicionarPontos(pontos);

        return pontos;
    }
}