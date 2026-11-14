package application;

import batalha.Batalha;
import batalha.GeradorRecompensa;
import batalha.GeradorRecompensaPadrao;
import batalha.NotificadorBatalha;
import batalha.NotificadorConsole;
import batalha.RepositorioResultados;
import batalha.ServicoNotificacao;
import batalha.ServicoRecompensa;
import batalha.Terreno;
import batalha.enums.TipoTerreno;
import itens.Item;
import itens.Potion;
import java.util.ArrayList;
import java.util.List;
import pokesal.CharSal;
import pokesal.SquirtSal;
import treinador.GeradorAleatorio;
import treinador.Treinador;

public class Main {

  public static void main(String[] args) {

    List<RepositorioResultados> batalhas = new ArrayList<>();

    GeradorAleatorio gerador = new GeradorAleatorio();

    // Configuração das recompensas
    GeradorRecompensa geradorRecompensa =
            new GeradorRecompensaPadrao(gerador);

    ServicoRecompensa servicoRecompensa =
            new ServicoRecompensa(geradorRecompensa);

    // Configuração das notificações
    NotificadorBatalha notificador = new NotificadorConsole();

    ServicoNotificacao servicoNotificacao =
            new ServicoNotificacao(notificador);

    // Configuração dos treinadores
    Treinador t1 = new Treinador(
            gerador,
            "Ronaldo",
            new CharSal(),
            new Item[]{new Potion(10), new Potion(10)}
    );

    t1.escolhaDePerk(1);

    Treinador t2 = new Treinador(
            gerador,
            "Rival",
            new SquirtSal(),
            new Item[]{new Potion(10), new Potion(10)}
    );

    t2.escolhaDePerk(2);

    // Configuração do terreno
    Terreno terreno = new Terreno();
    terreno.setTerreno(TipoTerreno.ASFALTO_QUENTE);

    // Inicialização da batalha com injeção de dependência
    Batalha batalha = new Batalha(
            servicoRecompensa,
            servicoNotificacao
    );

    batalhas.add(batalha.iniciarBatalha(t1, t2, terreno));

    // Exibição do registro da batalha
    batalhas.get(0).getRegistro();

    // Exibição da pontuação dos treinadores
    System.out.println("\n--- PONTUAÇÃO ---");
    System.out.println(t1.getNome() + ": " + t1.getPontos());
    System.out.println(t2.getNome() + ": " + t2.getPontos());
  }
}

