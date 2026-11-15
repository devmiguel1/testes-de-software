package application;

import batalha.Batalha;
import batalha.GeradorRecompensa;
import batalha.GeradorRecompensaPadrao;
import batalha.NotificadorBatalha;
import batalha.NotificadorConsole;
import batalha.RegistroBatalhaConsole;
import batalha.RepositorioResultadosMemoria;
import batalha.ServicoNotificacao;
import batalha.ServicoRecompensa;
import batalha.ServicoTerreno;
import batalha.Terreno;
import batalha.Torneio;
import batalha.enums.TipoClima;
import batalha.enums.TipoTerreno;
import itens.Item;
import itens.Potion;
import java.time.Clock;
import java.time.ZoneId;
import java.util.List;
import pokesal.BulbaSal;
import pokesal.CharSal;
import pokesal.SquirtSal;
import pokesal.TotoSal;
import treinador.GeradorAleatorio;
import treinador.Treinador;

public class Main {

  public static void main(String[] args) {
    GeradorAleatorio gerador = new GeradorAleatorio();

    // Configuração do terreno
    ServicoTerreno servicoTerreno =
        new ServicoTerreno(Clock.system(ZoneId.of("America/Bahia")), TipoClima.ENSOLARADO);
    Terreno terreno = new Terreno(servicoTerreno);
    terreno.setTerreno(TipoTerreno.ASFALTO_QUENTE);

    // Configuração das recompensas
    GeradorRecompensa geradorRecompensa = new GeradorRecompensaPadrao(gerador);
    ServicoRecompensa servicoRecompensa = new ServicoRecompensa(geradorRecompensa);

    // Configuração das notificações
    NotificadorBatalha notificador = new NotificadorConsole();
    ServicoNotificacao servicoNotificacao = new ServicoNotificacao(notificador);

    // Configuração dos treinadores (nomes diferentes)
    Treinador t1 = new Treinador(gerador, "Ronaldo", new CharSal(),
        new Item[] {new Potion(10), new Potion(10)});
    t1.escolhaDePerk(1);

    Treinador t2 = new Treinador(gerador, "Rival", new SquirtSal(),
        new Item[] {new Potion(10), new Potion(10)});
    t2.escolhaDePerk(2);

    Treinador t3 = new Treinador(gerador, "Ash", new BulbaSal(),
        new Item[] {new Potion(10), new Potion(10)});
    t3.escolhaDePerk(3);

    Treinador t4 = new Treinador(gerador, "Misty", new TotoSal(),
        new Item[] {new Potion(10), new Potion(10)});
    t4.escolhaDePerk(1);

    // Montagem do torneio com injeção de dependência
    RepositorioResultadosMemoria repositorio = new RepositorioResultadosMemoria();
    Batalha batalha = new Batalha(servicoRecompensa, servicoNotificacao,
        new RegistroBatalhaConsole());
    Torneio torneio = new Torneio(batalha, repositorio, terreno);

    Treinador campeao = torneio.executar(List.of(t1, t2, t3, t4));

    // Resultados salvos no repositório
    System.out.println("\n--- RESULTADOS ---");
    repositorio.getResultados().forEach(System.out::println);
    System.out.println("Campeão: " + campeao.getNome());

    // Exibição da pontuação dos treinadores
    System.out.println("\n--- PONTUAÇÃO ---");
    for (Treinador t : List.of(t1, t2, t3, t4)) {
      System.out.println(t.getNome() + ": " + t.getPontos());
    }
  }
}
