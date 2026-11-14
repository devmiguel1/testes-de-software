package application;

import batalha.Batalha;
import batalha.GeradorRecompensa;
import batalha.GeradorRecompensaPadrao;
import batalha.NotificadorBatalha;
import batalha.NotificadorConsole;
import batalha.RepositorioResultados;
import batalha.ServicoNotificacao;
import batalha.ServicoRecompensa;
import batalha.ServicoTerreno;
import batalha.Terreno;
import batalha.enums.TipoClima;
import batalha.enums.TipoTerreno;
import itens.Item;
import itens.Potion;
import java.time.Clock;
import java.time.ZoneId;
import pokesal.CharSal;
import pokesal.SquirtSal;
import treinador.GeradorAleatorio;
import treinador.Treinador;

public class Main {

  public static void main(String[] args) {
    RepositorioResultados repositorio = new RepositorioResultados();
    ServicoTerreno servicoTerreno =
        new ServicoTerreno(Clock.system(ZoneId.of("America/Bahia")), TipoClima.ENSOLARADO);

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
        new Item[] {new Potion(10), new Potion(10)}
    );

    t1.escolhaDePerk(1);

    Treinador t2 = new Treinador(
        gerador,
        "Rival",
        new SquirtSal(),
        new Item[] {new Potion(10), new Potion(10)}
    );

    t2.escolhaDePerk(2);


    // Configuração do terreno
    Terreno terreno = new Terreno(servicoTerreno);
    terreno.setTerreno(TipoTerreno.ASFALTO_QUENTE);

    // Inicialização da batalha com injeção de dependência
    repositorio.salvarResultado(new Batalha(servicoRecompensa,
        servicoNotificacao).iniciarBatalha(t1, t2, terreno));
    repositorio.batalhas
        .get(0)
        .getRegistro();

    // Exibição da pontuação dos treinadores
    System.out.println("\n--- PONTUAÇÃO ---");
    System.out.println(t1.getNome() + ": " + t1.getPontos());
    System.out.println(t2.getNome() + ": " + t2.getPontos());

  }
}

